package org.apache.poi.ss.usermodel;

import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.ss.format.CellFormat;
import org.apache.poi.ss.format.CellFormatResult;
import org.apache.poi.ss.formula.ConditionalFormattingEvaluator;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public class DataFormatter {
    private static final Logger LOG;
    private static final String defaultFractionFractionPartFormat = "#/##";
    private static final String defaultFractionWholePartFormat = "#";
    private static final String invalidDateTimeString;
    private DateFormatSymbols dateSymbols;
    private DecimalFormatSymbols decimalSymbols;
    private DateFormat defaultDateformat;
    private Format defaultNumFormat;
    private boolean emulateCSV;
    private final Map<String, Format> formats;
    private Format generalNumberFormat;
    private Locale locale;
    private boolean localeIsAdapting;
    private final PropertyChangeSupport pcs;
    private boolean use4DigitYearsInAllDateFormats;
    private boolean useCachedValuesForFormulaCells;
    private static final Pattern numPattern = Pattern.compile("[0#]+");
    private static final Pattern daysAsText = Pattern.compile("([d]{3,})", 2);
    private static final Pattern amPmPattern = Pattern.compile("(([AP])[M/P]*)", 2);
    private static final Pattern rangeConditionalPattern = Pattern.compile(".*\\[\\s*(>|>=|<|<=|=)\\s*[0-9]*\\.*[0-9].*");
    private static final Pattern localePatternGroup = Pattern.compile("(\\[\\$[^-\\]]*-[0-9A-Z]+])");
    private static final Pattern colorPattern = Pattern.compile("(\\[BLACK])|(\\[BLUE])|(\\[CYAN])|(\\[GREEN])|(\\[MAGENTA])|(\\[RED])|(\\[WHITE])|(\\[YELLOW])|(\\[COLOR\\s*\\d])|(\\[COLOR\\s*[0-5]\\d])", 2);
    private static final Pattern fractionPattern = Pattern.compile("(?:([#\\d]+)\\s+)?(#+)\\s*/\\s*([#\\d]+)");
    private static final Pattern fractionStripper = Pattern.compile("(\"[^\"]*\")|([^ ?#\\d/]+)");
    private static final Pattern alternateGrouping = Pattern.compile("([#0]([^.#0])[#0]{3})");
    private static final Pattern decimalFormatFix = Pattern.compile("0+#");

    static {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < 255; i++) {
            buf.append('#');
        }
        invalidDateTimeString = buf.toString();
        LOG = PoiLogManager.getLogger((Class<?>) DataFormatter.class);
    }

    public DataFormatter() {
        this(false);
    }

    public DataFormatter(boolean emulateCSV) {
        this(LocaleUtil.getUserLocale(), true, emulateCSV);
    }

    public DataFormatter(Locale locale) {
        this(locale, false);
    }

    public DataFormatter(Locale locale, boolean emulateCSV) {
        this(locale, false, emulateCSV);
    }

    public DataFormatter(Locale locale, boolean localeIsAdapting, boolean emulateCSV) {
        this.formats = new HashMap();
        this.emulateCSV = false;
        this.use4DigitYearsInAllDateFormats = false;
        this.useCachedValuesForFormulaCells = false;
        this.localeIsAdapting = true;
        this.pcs = new PropertyChangeSupport(this);
        checkForLocaleChange(locale);
        this.localeIsAdapting = localeIsAdapting;
        this.emulateCSV = emulateCSV;
    }

    public void setEmulateCSV(boolean emulateCSV) {
        this.emulateCSV = emulateCSV;
    }

    public boolean isEmulateCSV() {
        return this.emulateCSV;
    }

    public void setUseCachedValuesForFormulaCells(boolean useCachedValuesForFormulaCells) {
        this.useCachedValuesForFormulaCells = useCachedValuesForFormulaCells;
    }

    public boolean useCachedValuesForFormulaCells() {
        return this.useCachedValuesForFormulaCells;
    }

    public void setUse4DigitYearsInAllDateFormats(boolean use4DigitYearsInAllDateFormats) {
        this.use4DigitYearsInAllDateFormats = use4DigitYearsInAllDateFormats;
    }

    public boolean use4DigitYearsInAllDateFormats() {
        return this.use4DigitYearsInAllDateFormats;
    }

    private Format getFormat(Cell cell, ConditionalFormattingEvaluator cfEvaluator) {
        ExcelNumberFormat numFmt;
        if (cell == null || (numFmt = ExcelNumberFormat.from(cell, cfEvaluator)) == null) {
            return null;
        }
        int formatIndex = numFmt.getIdx();
        String formatStr = numFmt.getFormat();
        if (StringUtil.isBlank(formatStr)) {
            return null;
        }
        return getFormat(cell.getNumericCellValue(), formatIndex, formatStr, isDate1904(cell));
    }

    private boolean isDate1904(Cell cell) {
        if (cell != null && (cell.getSheet().getWorkbook() instanceof Date1904Support)) {
            return ((Date1904Support) cell.getSheet().getWorkbook()).isDate1904();
        }
        return false;
    }

    private Format getFormat(double cellValue, int formatIndex, String formatStrIn, boolean use1904Windowing) {
        Object cellValueO;
        if (formatStrIn == null) {
            throw new IllegalArgumentException("Missing input format for value " + cellValue + " and index " + formatIndex);
        }
        checkForLocaleChange();
        String formatStr = formatStrIn.replace("\\%", "'%'");
        if (formatStr.contains(";") && (formatStr.indexOf(59) != formatStr.lastIndexOf(59) || rangeConditionalPattern.matcher(formatStr).matches())) {
            try {
                CellFormat cfmt = CellFormat.getInstance(this.locale, formatStr);
                if (cellValue != 0.0d && DateUtil.isADateFormat(formatIndex, formatStr)) {
                    cellValueO = DateUtil.getJavaDate(cellValue, use1904Windowing);
                } else {
                    cellValueO = Double.valueOf(cellValue);
                }
                return new CellFormatResultWrapper(cfmt.apply(cellValueO));
            } catch (Exception e) {
                LOG.atWarn().withThrowable(e).log("Formatting failed for format {}, falling back", formatStr);
            }
        }
        if (this.emulateCSV && cellValue == 0.0d && formatStr.contains(defaultFractionWholePartFormat) && !formatStr.contains("0")) {
            formatStr = formatStr.replace(defaultFractionWholePartFormat, "");
        }
        Format format = this.formats.get(formatStr);
        if (format != null) {
            return format;
        }
        if ("General".equalsIgnoreCase(formatStr) || "@".equals(formatStr)) {
            return this.generalNumberFormat;
        }
        Format format2 = createFormat(cellValue, formatIndex, formatStr);
        this.formats.put(formatStr, format2);
        return format2;
    }

    public Format createFormat(Cell cell) {
        int formatIndex = cell.getCellStyle().getDataFormat();
        String formatStr = cell.getCellStyle().getDataFormatString();
        return createFormat(cell.getNumericCellValue(), formatIndex, formatStr);
    }

    private Format createFormat(double cellValue, int formatIndex, String sFormat) {
        String colour;
        int at;
        checkForLocaleChange();
        String formatStr = sFormat;
        int i = 0;
        if (formatStr != null) {
            Matcher colourM = colorPattern.matcher(formatStr);
            while (colourM.find() && (at = formatStr.indexOf((colour = colourM.group()))) != -1) {
                String nFormatStr = formatStr.substring(0, at) + formatStr.substring(colour.length() + at);
                if (nFormatStr.equals(formatStr)) {
                    break;
                }
                formatStr = nFormatStr;
                colourM = colorPattern.matcher(formatStr);
            }
        }
        if (formatStr != null) {
            Matcher m = localePatternGroup.matcher(formatStr);
            while (m.find()) {
                String match = m.group();
                String symbol = match.substring(match.indexOf(36) + 1, match.indexOf(45));
                if (symbol.indexOf(36) > -1) {
                    symbol = symbol.substring(0, symbol.indexOf(36)) + IOUtils.DIR_SEPARATOR_WINDOWS + symbol.substring(symbol.indexOf(36));
                }
                formatStr = m.replaceAll(symbol);
                m = localePatternGroup.matcher(formatStr);
            }
        }
        if (StringUtil.isBlank(formatStr)) {
            return getDefaultFormat(cellValue);
        }
        if ("General".equalsIgnoreCase(formatStr) || "@".equals(formatStr)) {
            return this.generalNumberFormat;
        }
        if (formatStr == null) {
            return null;
        }
        if (DateUtil.isADateFormat(formatIndex, formatStr) && DateUtil.isValidExcelDate(cellValue)) {
            return createDateFormat(formatStr, cellValue);
        }
        if (formatStr.contains("#/") || formatStr.contains("?/")) {
            String[] chunks = formatStr.split(";");
            int length = chunks.length;
            while (true) {
                String wholePart = defaultFractionWholePartFormat;
                if (i < length) {
                    String chunk1 = chunks[i];
                    String chunk = chunk1.replace("?", defaultFractionWholePartFormat);
                    Matcher matcher = fractionStripper.matcher(chunk);
                    String chunk2 = matcher.replaceAll(StringUtils.SPACE);
                    Matcher fractionMatcher = fractionPattern.matcher(chunk2.replaceAll(" +", StringUtils.SPACE));
                    if (!fractionMatcher.find()) {
                        i++;
                    } else {
                        if (fractionMatcher.group(1) == null) {
                            wholePart = "";
                        }
                        return new FractionFormat(wholePart, fractionMatcher.group(3));
                    }
                } else {
                    return new FractionFormat(defaultFractionWholePartFormat, defaultFractionFractionPartFormat);
                }
            }
        } else {
            if (numPattern.matcher(formatStr).find()) {
                return createNumberFormat(formatStr, cellValue);
            }
            if (!this.emulateCSV) {
                return null;
            }
            return new ConstantStringFormat(cleanFormatForNumber(formatStr));
        }
    }

    String adjustTo4DigitYearsIfConfigured(String format) {
        if (this.use4DigitYearsInAllDateFormats) {
            int ypos2 = format.indexOf("yy");
            if (ypos2 < 0) {
                return format;
            }
            int ypos3 = format.indexOf("yyy");
            int ypos4 = format.indexOf("yyyy");
            if (ypos4 == ypos2) {
                String part1 = format.substring(0, ypos2 + 4);
                String part2 = format.substring(ypos2 + 4);
                return part1 + adjustTo4DigitYearsIfConfigured(part2);
            }
            if (ypos3 != ypos2) {
                String part12 = format.substring(0, ypos2 + 2);
                String part22 = format.substring(ypos2 + 2);
                return part12 + "yy" + adjustTo4DigitYearsIfConfigured(part22);
            }
            return format;
        }
        return format;
    }

    private Format createDateFormat(String pFormatStr, double cellValue) {
        char c;
        char c2;
        char c3;
        String formatStr = adjustTo4DigitYearsIfConfigured(pFormatStr).replace("\\-", ProcessIdUtil.DEFAULT_PROCESSID).replace("\\,", CollectionUtils.COMMA).replace("\\.", ".").replace("\\ ", StringUtils.SPACE).replace("\\/", PackagingURIHelper.FORWARD_SLASH_STRING).replace(";@", "").replace("\"/\"", PackagingURIHelper.FORWARD_SLASH_STRING).replace("\"\"", "'").replace("\\T", "'T'");
        boolean hasAmPm = false;
        Matcher amPmMatcher = amPmPattern.matcher(formatStr);
        while (amPmMatcher.find()) {
            formatStr = amPmMatcher.replaceAll("@");
            hasAmPm = true;
            amPmMatcher = amPmPattern.matcher(formatStr);
        }
        String formatStr2 = formatStr.replace('@', 'a');
        Matcher dateMatcher = daysAsText.matcher(formatStr2);
        char c4 = 'D';
        if (dateMatcher.find()) {
            String match = dateMatcher.group(0).toUpperCase(Locale.ROOT).replace('D', 'E');
            formatStr2 = dateMatcher.replaceAll(match);
        }
        StringBuilder sb = new StringBuilder();
        char[] chars = formatStr2.toCharArray();
        boolean mIsMonth = true;
        List<Integer> ms = new ArrayList<>();
        boolean isElapsed = false;
        int j = 0;
        while (j < chars.length) {
            char c5 = chars[j];
            if (c5 == '\'') {
                sb.append(c5);
                do {
                    j++;
                    if (j >= chars.length) {
                        break;
                    }
                    c3 = chars[j];
                    sb.append(c3);
                } while (c3 != '\'');
                c = c4;
            } else if (c5 == '[' && !isElapsed) {
                isElapsed = true;
                mIsMonth = false;
                sb.append(c5);
                c = c4;
            } else if (c5 == ']' && isElapsed) {
                isElapsed = false;
                sb.append(c5);
                c = c4;
            } else {
                char c6 = 'M';
                if (!isElapsed) {
                    if (c5 == 'h') {
                        c = 'D';
                    } else if (c5 == 'H') {
                        c = 'D';
                    } else if (c5 != 'm' && c5 != 'M') {
                        if (c5 != 's' && c5 != 'S') {
                            if (Character.isLetter(c5)) {
                                ms.clear();
                                if (c5 == 'y') {
                                    c2 = 'D';
                                } else if (c5 == 'Y') {
                                    c2 = 'D';
                                } else {
                                    if (c5 != 'd') {
                                        c2 = 'D';
                                        if (c5 != 'D') {
                                            sb.append(c5);
                                            mIsMonth = true;
                                            c = c2;
                                        }
                                    } else {
                                        c2 = 'D';
                                    }
                                    sb.append('d');
                                    mIsMonth = true;
                                    c = c2;
                                }
                                sb.append('y');
                                mIsMonth = true;
                                c = c2;
                            } else {
                                if (Character.isWhitespace(c5)) {
                                    ms.clear();
                                }
                                sb.append(c5);
                            }
                        }
                        sb.append('s');
                        Iterator<Integer> it = ms.iterator();
                        while (it.hasNext()) {
                            int index = it.next().intValue();
                            if (sb.charAt(index) == c6) {
                                sb.replace(index, index + 1, "m");
                            }
                            c6 = 'M';
                        }
                        ms.clear();
                        mIsMonth = true;
                        c = 'D';
                    } else if (mIsMonth) {
                        sb.append('M');
                        ms.add(Integer.valueOf(sb.length() - 1));
                    } else {
                        sb.append('m');
                    }
                    if (hasAmPm) {
                        sb.append('h');
                    } else {
                        sb.append('H');
                    }
                    mIsMonth = false;
                } else if (c5 == 'h' || c5 == 'H') {
                    sb.append('H');
                } else if (c5 == 'm' || c5 == 'M') {
                    sb.append('m');
                } else if (c5 == 's' || c5 == 'S') {
                    sb.append('s');
                } else {
                    sb.append(c5);
                }
                c = 'D';
            }
            j++;
            c4 = c;
        }
        String formatStr3 = sb.toString();
        try {
            return new ExcelStyleDateFormatter(formatStr3, this.dateSymbols);
        } catch (IllegalArgumentException iae) {
            LOG.atDebug().withThrowable(iae).log("Formatting failed for format {}, falling back", formatStr3);
            return getDefaultFormat(cellValue);
        }
    }

    private String cleanFormatForNumber(String formatStrIn) {
        String formatStr = formatStrIn.replace("\\%", "'%'");
        StringBuilder sb = new StringBuilder(formatStr);
        if (this.emulateCSV) {
            int i = 0;
            while (i < sb.length()) {
                char c = sb.charAt(i);
                if ((c == '_' || c == '*' || c == '?') && (i <= 0 || sb.charAt(i - 1) != '\\')) {
                    if (c == '?') {
                        sb.setCharAt(i, Chars.SPACE);
                    } else if (i < sb.length() - 1) {
                        if (c == '_') {
                            sb.setCharAt(i + 1, Chars.SPACE);
                        } else {
                            sb.deleteCharAt(i + 1);
                        }
                        sb.deleteCharAt(i);
                        i--;
                    }
                }
                i++;
            }
        } else {
            int i2 = 0;
            while (i2 < sb.length()) {
                char c2 = sb.charAt(i2);
                if ((c2 == '_' || c2 == '*') && (i2 <= 0 || sb.charAt(i2 - 1) != '\\')) {
                    if (i2 < sb.length() - 1) {
                        sb.deleteCharAt(i2 + 1);
                    }
                    sb.deleteCharAt(i2);
                    i2--;
                }
                i2++;
            }
        }
        int i3 = 0;
        while (i3 < sb.length()) {
            char c3 = sb.charAt(i3);
            if (c3 == '\\' || c3 == '\"') {
                sb.deleteCharAt(i3);
                i3--;
            } else if ((c3 == '+' || c3 == '-') && i3 > 0 && sb.charAt(i3 - 1) == 'E') {
                sb.deleteCharAt(i3);
                i3--;
            }
            i3++;
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class InternalDecimalFormatWithScale extends Format {
        private final DecimalFormat df;
        private final BigDecimal divider;
        private static final Pattern endsWithCommas = Pattern.compile("(,+)$");
        private static final BigDecimal ONE_THOUSAND = BigDecimal.valueOf(1000L);

        private static String trimTrailingCommas(String s) {
            return s.replaceAll(",+$", "");
        }

        public InternalDecimalFormatWithScale(String pattern, DecimalFormatSymbols symbols) {
            this.df = new DecimalFormat(trimTrailingCommas(pattern), symbols);
            DataFormatter.setExcelStyleRoundingMode(this.df);
            Matcher endsWithCommasMatcher = endsWithCommas.matcher(pattern);
            if (endsWithCommasMatcher.find()) {
                String commas = endsWithCommasMatcher.group(1);
                BigDecimal temp = BigDecimal.ONE;
                for (int i = 0; i < commas.length(); i++) {
                    temp = temp.multiply(ONE_THOUSAND);
                }
                this.divider = temp;
                return;
            }
            this.divider = null;
        }

        boolean requiresScaling() {
            return this.divider != null;
        }

        private Object scaleInput(Object obj) {
            if (this.divider != null) {
                if (obj instanceof BigDecimal) {
                    return ((BigDecimal) obj).divide(this.divider, RoundingMode.HALF_UP);
                }
                if (obj instanceof Double) {
                    return Double.valueOf(((Double) obj).doubleValue() / this.divider.doubleValue());
                }
                throw new UnsupportedOperationException("cannot scaleInput of type " + obj.getClass());
            }
            return obj;
        }

        @Override // java.text.Format
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            return this.df.format(scaleInput(obj), toAppendTo, pos);
        }

        @Override // java.text.Format
        public Object parseObject(String source, ParsePosition pos) {
            throw new UnsupportedOperationException();
        }
    }

    private Format createNumberFormat(String formatStr, double cellValue) {
        char grouping;
        String format = cleanFormatForNumber(formatStr);
        DecimalFormatSymbols symbols = this.decimalSymbols;
        Matcher agm = alternateGrouping.matcher(format);
        if (agm.find() && (grouping = agm.group(2).charAt(0)) != ',') {
            symbols = DecimalFormatSymbols.getInstance(this.locale);
            symbols.setGroupingSeparator(grouping);
            String oldPart = agm.group(1);
            String newPart = oldPart.replace(grouping, ',');
            format = format.replace(oldPart, newPart);
        }
        if (decimalFormatFix.matcher(format).matches()) {
            format = defaultFractionWholePartFormat;
        }
        try {
            return new InternalDecimalFormatWithScale(format, symbols);
        } catch (IllegalArgumentException iae) {
            LOG.atDebug().withThrowable(iae).log("Formatting failed for format {}, falling back", formatStr);
            return getDefaultFormat(cellValue);
        }
    }

    public Format getDefaultFormat(Cell cell) {
        return getDefaultFormat(cell.getNumericCellValue());
    }

    private Format getDefaultFormat(double cellValue) {
        checkForLocaleChange();
        if (this.defaultNumFormat != null) {
            return this.defaultNumFormat;
        }
        return this.generalNumberFormat;
    }

    private String performDateFormatting(Date d, Format dateFormat) {
        String format;
        Format df = dateFormat != null ? dateFormat : this.defaultDateformat;
        synchronized (df) {
            format = df.format(d);
        }
        return format;
    }

    private String getFormattedDateString(Cell cell, ConditionalFormattingEvaluator cfEvaluator) {
        String performDateFormatting;
        if (cell == null) {
            return null;
        }
        Format dateFormat = getFormat(cell, cfEvaluator);
        if (dateFormat == null) {
            if (this.defaultDateformat == null) {
                DateFormatSymbols sym = DateFormatSymbols.getInstance(LocaleUtil.getUserLocale());
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", sym);
                sdf.setTimeZone(LocaleUtil.getUserTimeZone());
                dateFormat = sdf;
            } else {
                dateFormat = this.defaultDateformat;
            }
        }
        synchronized (dateFormat) {
            if (dateFormat instanceof ExcelStyleDateFormatter) {
                ((ExcelStyleDateFormatter) dateFormat).setDateToBeFormatted(cell.getNumericCellValue());
            }
            Date d = cell.getDateCellValue();
            performDateFormatting = performDateFormatting(d, dateFormat);
        }
        return performDateFormatting;
    }

    private String getFormattedNumberString(Cell cell, ConditionalFormattingEvaluator cfEvaluator) {
        if (cell == null) {
            return null;
        }
        Format numberFormat = getFormat(cell, cfEvaluator);
        double d = cell.getNumericCellValue();
        if (numberFormat == null) {
            return Double.toString(d);
        }
        String formatted = null;
        if (numberFormat instanceof InternalDecimalFormatWithScale) {
            InternalDecimalFormatWithScale idfws = (InternalDecimalFormatWithScale) numberFormat;
            if (idfws.requiresScaling()) {
                formatted = idfws.format(Double.valueOf(d));
            }
        }
        if (formatted == null) {
            try {
                formatted = numberFormat.format(BigDecimal.valueOf(d));
            } catch (NumberFormatException e) {
                formatted = numberFormat.format(Double.valueOf(d));
            }
        }
        return formatted.replaceFirst("E(\\d)", "E+$1");
    }

    public String formatRawCellContents(double value, int formatIndex, String formatString) {
        return formatRawCellContents(value, formatIndex, formatString, false);
    }

    public String formatRawCellContents(double value, int formatIndex, String formatString, boolean use1904Windowing) {
        boolean use1904Windowing2;
        String formatString2;
        int formatIndex2;
        double value2;
        String result;
        checkForLocaleChange();
        if (!DateUtil.isADateFormat(formatIndex, formatString)) {
            use1904Windowing2 = use1904Windowing;
            formatString2 = formatString;
            formatIndex2 = formatIndex;
            value2 = value;
        } else {
            if (DateUtil.isValidExcelDate(value)) {
                Format dateFormat = getFormat(value, formatIndex, formatString, use1904Windowing);
                if (dateFormat instanceof ExcelStyleDateFormatter) {
                    ((ExcelStyleDateFormatter) dateFormat).setDateToBeFormatted(value);
                }
                Date d = DateUtil.getJavaDate(value, use1904Windowing);
                return performDateFormatting(d, dateFormat);
            }
            use1904Windowing2 = use1904Windowing;
            formatString2 = formatString;
            formatIndex2 = formatIndex;
            value2 = value;
            if (this.emulateCSV) {
                return invalidDateTimeString;
            }
        }
        double value3 = value2;
        int formatIndex3 = formatIndex2;
        String formatString3 = formatString2;
        Format numberFormat = getFormat(value3, formatIndex3, formatString3, use1904Windowing2);
        if (numberFormat == null) {
            return String.valueOf(value3);
        }
        String textValue = NumberToTextConverter.toText(value3);
        if (textValue.indexOf(69) > -1) {
            result = numberFormat.format(Double.valueOf(value3));
        } else {
            result = numberFormat.format(new BigDecimal(textValue));
        }
        String fslc = formatString3.toLowerCase(Locale.ROOT);
        if ((fslc.contains("general") || fslc.contains("e+0")) && result.contains("E") && !result.contains("E-")) {
            return result.replaceFirst("E", "E+");
        }
        return result;
    }

    public String formatCellValue(Cell cell) {
        return formatCellValue(cell, null);
    }

    public String formatCellValue(Cell cell, FormulaEvaluator evaluator) {
        return formatCellValue(cell, evaluator, null);
    }

    public String formatCellValue(Cell cell, FormulaEvaluator evaluator, ConditionalFormattingEvaluator cfEvaluator) {
        checkForLocaleChange();
        if (cell == null) {
            return "";
        }
        CellType cellType = cell.getCellType();
        if (cellType == CellType.FORMULA) {
            if (evaluator == null) {
                if (this.useCachedValuesForFormulaCells) {
                    try {
                        cellType = cell.getCachedFormulaResultType();
                    } catch (Exception e) {
                        return cell.getCellFormula();
                    }
                } else {
                    return cell.getCellFormula();
                }
            } else {
                cellType = evaluator.evaluateFormulaCell(cell);
            }
        }
        switch (cellType) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell, cfEvaluator)) {
                    return getFormattedDateString(cell, cfEvaluator);
                }
                return getFormattedNumberString(cell, cfEvaluator);
            case STRING:
                return cell.getRichStringCellValue().getString();
            case BOOLEAN:
                return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
            case BLANK:
                return "";
            case ERROR:
                return FormulaError.forInt(cell.getErrorCellValue()).getString();
            default:
                throw new IllegalStateException("Unexpected celltype (" + cellType + ")");
        }
    }

    public void setDefaultNumberFormat(Format format) {
        for (Map.Entry<String, Format> entry : this.formats.entrySet()) {
            if (entry.getValue() == this.generalNumberFormat) {
                entry.setValue(format);
            }
        }
        this.defaultNumFormat = format;
    }

    public void addFormat(String excelFormatStr, Format format) {
        this.formats.put(excelFormatStr, format);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DecimalFormat createIntegerOnlyFormat(String fmt) {
        DecimalFormatSymbols dsf = DecimalFormatSymbols.getInstance(Locale.ROOT);
        DecimalFormat result = new DecimalFormat(fmt, dsf);
        result.setParseIntegerOnly(true);
        return result;
    }

    public static void setExcelStyleRoundingMode(DecimalFormat format) {
        setExcelStyleRoundingMode(format, RoundingMode.HALF_UP);
    }

    public static void setExcelStyleRoundingMode(DecimalFormat format, RoundingMode roundingMode) {
        format.setRoundingMode(roundingMode);
    }

    public PropertyChangeSupport getLocaleChangedObservable() {
        return this.pcs;
    }

    private void checkForLocaleChange() {
        checkForLocaleChange(LocaleUtil.getUserLocale());
    }

    private void checkForLocaleChange(Locale newLocale) {
        if (this.localeIsAdapting && !newLocale.equals(this.locale)) {
            updateLocale(newLocale);
            this.pcs.firePropertyChange("locale", this.locale, newLocale);
        }
    }

    public void updateLocale(Locale newLocale) {
        if (!this.localeIsAdapting || newLocale.equals(this.locale)) {
            return;
        }
        this.locale = newLocale;
        this.dateSymbols = DateFormatSymbols.getInstance(this.locale);
        this.decimalSymbols = DecimalFormatSymbols.getInstance(this.locale);
        this.generalNumberFormat = new ExcelGeneralNumberFormat(this.locale);
        this.defaultDateformat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", this.dateSymbols);
        this.defaultDateformat.setTimeZone(LocaleUtil.getUserTimeZone());
        this.formats.clear();
        Format zipFormat = ZipPlusFourFormat.instance;
        addFormat("00000\\-0000", zipFormat);
        addFormat("00000-0000", zipFormat);
        Format phoneFormat = PhoneFormat.instance;
        addFormat("[<=9999999]###\\-####;\\(###\\)\\ ###\\-####", phoneFormat);
        addFormat("[<=9999999]###-####;(###) ###-####", phoneFormat);
        addFormat("###\\-####;\\(###\\)\\ ###\\-####", phoneFormat);
        addFormat("###-####;(###) ###-####", phoneFormat);
        Format ssnFormat = SSNFormat.instance;
        addFormat("000\\-00\\-0000", ssnFormat);
        addFormat("000-00-0000", ssnFormat);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class SSNFormat extends Format {
        public static final Format instance = new SSNFormat();
        private static final DecimalFormat df = DataFormatter.createIntegerOnlyFormat("000000000");

        private SSNFormat() {
        }

        public static String format(Number num) {
            String result = df.format(num);
            return result.substring(0, 3) + '-' + result.substring(3, 5) + '-' + result.substring(5, 9);
        }

        @Override // java.text.Format
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            return toAppendTo.append(format((Number) obj));
        }

        @Override // java.text.Format
        public Object parseObject(String source, ParsePosition pos) {
            return df.parseObject(source, pos);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class ZipPlusFourFormat extends Format {
        public static final Format instance = new ZipPlusFourFormat();
        private static final DecimalFormat df = DataFormatter.createIntegerOnlyFormat("000000000");

        private ZipPlusFourFormat() {
        }

        public static String format(Number num) {
            String result = df.format(num);
            return result.substring(0, 5) + '-' + result.substring(5, 9);
        }

        @Override // java.text.Format
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            return toAppendTo.append(format((Number) obj));
        }

        @Override // java.text.Format
        public Object parseObject(String source, ParsePosition pos) {
            return df.parseObject(source, pos);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class PhoneFormat extends Format {
        public static final Format instance = new PhoneFormat();
        private static final DecimalFormat df = DataFormatter.createIntegerOnlyFormat("##########");

        private PhoneFormat() {
        }

        public static String format(Number num) {
            String result = df.format(num);
            StringBuilder sb = new StringBuilder();
            int len = result.length();
            if (len <= 4) {
                return result;
            }
            String seg3 = result.substring(len - 4, len);
            String seg2 = result.substring(Math.max(0, len - 7), len - 4);
            String seg1 = result.substring(Math.max(0, len - 10), Math.max(0, len - 7));
            if (StringUtil.isNotBlank(seg1)) {
                sb.append('(').append(seg1).append(") ");
            }
            if (StringUtil.isNotBlank(seg2)) {
                sb.append(seg2).append('-');
            }
            sb.append(seg3);
            return sb.toString();
        }

        @Override // java.text.Format
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            return toAppendTo.append(format((Number) obj));
        }

        @Override // java.text.Format
        public Object parseObject(String source, ParsePosition pos) {
            return df.parseObject(source, pos);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class ConstantStringFormat extends Format {
        private static final DecimalFormat df = DataFormatter.createIntegerOnlyFormat("##########");
        private final String str;

        public ConstantStringFormat(String s) {
            this.str = s;
        }

        @Override // java.text.Format
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            return toAppendTo.append(this.str);
        }

        @Override // java.text.Format
        public Object parseObject(String source, ParsePosition pos) {
            return df.parseObject(source, pos);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public final class CellFormatResultWrapper extends Format {
        private final CellFormatResult result;

        private CellFormatResultWrapper(CellFormatResult result) {
            this.result = result;
        }

        @Override // java.text.Format
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            if (DataFormatter.this.emulateCSV) {
                return toAppendTo.append(this.result.text);
            }
            return toAppendTo.append(this.result.text.trim());
        }

        @Override // java.text.Format
        public Object parseObject(String source, ParsePosition pos) {
            return null;
        }
    }
}
