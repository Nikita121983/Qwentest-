package org.apache.poi.ss.format;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PrimitiveIterator;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.util.CodepointsUtil;
import org.apache.poi.util.LocaleUtil;
import org.apache.xmlbeans.impl.common.NameUtil;

/* loaded from: classes10.dex */
public class CellFormatPart {
    public static final int COLOR_GROUP;
    public static final Pattern COLOR_PAT;
    public static final int CONDITION_OPERATOR_GROUP;
    public static final Pattern CONDITION_PAT;
    public static final int CONDITION_VALUE_GROUP;
    public static final Pattern CURRENCY_PAT;
    public static final Pattern FORMAT_PAT;
    static final List<Color> INDEXED_COLORS;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) CellFormatPart.class);
    static final Map<String, Color> NAMED_COLORS;
    public static final int SPECIFICATION_GROUP;
    public static final Pattern SPECIFICATION_PAT;
    private final Color color;
    private final CellFormatCondition condition;
    private final CellFormatter format;
    private final CellFormatType type;

    /* loaded from: classes10.dex */
    interface PartHandler {
        String handlePart(Matcher matcher, String str, CellFormatType cellFormatType, StringBuffer stringBuffer);
    }

    public static /* synthetic */ Color $r8$lambda$4xc_1npPoPoVjOtQ7vMOOEZe_rg(int i) {
        return new Color(i);
    }

    static {
        Integer[] indexedColors = {0, Integer.valueOf(ViewCompat.MEASURED_SIZE_MASK), 16711680, Integer.valueOf(MotionEventCompat.ACTION_POINTER_INDEX_MASK), 255, 16776960, 16711935, 65535, 8388608, 32768, 128, 8421376, 8388736, 32896, 12632256, 8421504, 10066431, 10040166, 16777164, 13434879, 6684774, 16744576, 26316, 13421823, 128, 16711935, 16776960, 65535, 8388736, 8388608, 32896, 255, 52479, 13434879, 13434828, 16777113, 10079487, 16751052, 13408767, 16764057, 3368703, 3394764, 10079232, 16763904, 16750848, 16737792, 6710937, 9868950, 13158, 3381606, 13056, 3355392, 10040064, 10040166, 3355545, 3355443};
        INDEXED_COLORS = Collections.unmodifiableList((List) Arrays.asList(indexedColors).stream().map(new Function() { // from class: org.apache.poi.ss.format.CellFormatPart$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CellFormatPart.$r8$lambda$4xc_1npPoPoVjOtQ7vMOOEZe_rg(((Integer) obj).intValue());
            }
        }).collect(Collectors.toList()));
        Map<String, Color> namedColors = new TreeMap<>((Comparator<? super String>) String.CASE_INSENSITIVE_ORDER);
        for (HSSFColor.HSSFColorPredefined color : HSSFColor.HSSFColorPredefined.values()) {
            String name = color.name();
            short[] rgb = color.getTriplet();
            Color c = new Color(rgb[0], rgb[1], rgb[2]);
            namedColors.put(name, c);
            if (name.indexOf(95) > 0) {
                namedColors.put(name.replace(NameUtil.USCORE, Chars.SPACE), c);
            }
            if (name.indexOf("_PERCENT") > 0) {
                namedColors.put(name.replace("_PERCENT", "%").replace(NameUtil.USCORE, Chars.SPACE), c);
            }
        }
        namedColors.put("black", INDEXED_COLORS.get(0));
        namedColors.put("white", INDEXED_COLORS.get(1));
        namedColors.put("red", INDEXED_COLORS.get(2));
        namedColors.put("green", INDEXED_COLORS.get(3));
        namedColors.put("blue", INDEXED_COLORS.get(4));
        namedColors.put("yellow", INDEXED_COLORS.get(5));
        namedColors.put("magenta", INDEXED_COLORS.get(6));
        namedColors.put("cyan", INDEXED_COLORS.get(7));
        String color2 = "\\[(";
        for (String key : namedColors.keySet()) {
            color2 = color2 + key.replaceAll("([^a-zA-Z0-9])", "\\\\$1") + "|";
        }
        String color3 = color2 + "color\\s*[0-9]+)\\]";
        String part = "\\\\.                     # Quoted single character\n|\"([^\\\\\"]|\\\\.)*\"         # Quoted string of characters (handles escaped quotes like \\\") \n|(\\[\\$.{0,3}(-[0-9a-f]{3,4})?])                   # Currency symbol in a given locale\n|_.                             # Space as wide as a given character\n|\\*.                           # Repeating fill character\n|@                              # Text: cell text\n|([0?\\#][0?\\#,]*)             # Number: digit + other digits and commas\n|e[-+]                          # Number: Scientific: Exponent\n|m{1,5}                         # Date: month or minute spec\n|d{1,4}                         # Date: day/date spec\n|y{2,4}                         # Date: year spec\n|h{1,2}                         # Date: hour spec\n|s{1,2}                         # Date: second spec\n|am?/pm?                        # Date: am/pm spec\n|\\[h{1,2}]                     # Elapsed time: hour spec\n|\\[m{1,2}]                     # Elapsed time: minute spec\n|\\[s{1,2}]                     # Elapsed time: second spec\n|[^;]                           # A character\n";
        String format = "(?:" + color3 + ")?                 # Text color\n(?:\\[([<>=]=?|!=|<>)    # The operator\n  \\s*(-?([0-9]+(?:\\.[0-9]*)?)|(\\.[0-9]*))\\s*  # The constant to test against\n])?               # Condition\n(?:\\[\\$-[0-9a-fA-F]+])?                # Optional locale id, ignored currently\n((?:" + part + ")+)                        # Format spec\n";
        COLOR_PAT = Pattern.compile(color3, 6);
        CONDITION_PAT = Pattern.compile("([<>=]=?|!=|<>)    # The operator\n  \\s*(-?([0-9]+(?:\\.[0-9]*)?)|(\\.[0-9]*))\\s*  # The constant to test against\n", 6);
        SPECIFICATION_PAT = Pattern.compile(part, 6);
        CURRENCY_PAT = Pattern.compile("(\\[\\$.{0,3}(-[0-9a-f]{3,4})?])", 6);
        FORMAT_PAT = Pattern.compile(format, 6);
        COLOR_GROUP = findGroup(FORMAT_PAT, "[Blue]@", "Blue");
        CONDITION_OPERATOR_GROUP = findGroup(FORMAT_PAT, "[>=1]@", ">=");
        CONDITION_VALUE_GROUP = findGroup(FORMAT_PAT, "[>=1]@", "1");
        SPECIFICATION_GROUP = findGroup(FORMAT_PAT, "[Blue][>1]\\a ?", "\\a ?");
        for (int i = 0; i < INDEXED_COLORS.size(); i++) {
            namedColors.put(TypedValues.Custom.S_COLOR + (i + 1), INDEXED_COLORS.get(i));
            namedColors.put("color " + (i + 1), INDEXED_COLORS.get(i));
        }
        NAMED_COLORS = Collections.unmodifiableMap(namedColors);
    }

    public CellFormatPart(String desc) {
        this(LocaleUtil.getUserLocale(), desc);
    }

    public CellFormatPart(Locale locale, String desc) {
        Matcher m = FORMAT_PAT.matcher(desc);
        if (!m.matches()) {
            throw new IllegalArgumentException("Unrecognized format: " + CellFormatter.quote(desc));
        }
        this.color = getColor(m);
        this.condition = getCondition(m);
        this.type = getCellFormatType(m);
        this.format = getFormatter(locale, m);
    }

    public boolean applies(Object valueObject) {
        if (this.condition == null || !(valueObject instanceof Number)) {
            if (valueObject == null) {
                throw new NullPointerException("valueObject");
            }
            return true;
        }
        Number num = (Number) valueObject;
        return this.condition.pass(num.doubleValue());
    }

    private static int findGroup(Pattern pat, String str, String marker) {
        Matcher m = pat.matcher(str);
        if (!m.find()) {
            throw new IllegalArgumentException("Pattern \"" + pat.pattern() + "\" doesn't match \"" + str + "\"");
        }
        for (int i = 1; i <= m.groupCount(); i++) {
            String grp = m.group(i);
            if (grp != null && grp.equals(marker)) {
                return i;
            }
        }
        throw new IllegalArgumentException("\"" + marker + "\" not found in \"" + pat.pattern() + "\"");
    }

    private static Color getColor(Matcher m) {
        return getColor(m.group(COLOR_GROUP));
    }

    static Color getColor(String cname) {
        if (cname == null || cname.isEmpty()) {
            return null;
        }
        Color c = NAMED_COLORS.get(cname);
        if (c == null) {
            LOG.warn("Unknown color: {}", CellFormatter.quote(cname));
        }
        return c;
    }

    private CellFormatCondition getCondition(Matcher m) {
        String mdesc = m.group(CONDITION_OPERATOR_GROUP);
        if (mdesc == null || mdesc.isEmpty()) {
            return null;
        }
        return CellFormatCondition.getInstance(m.group(CONDITION_OPERATOR_GROUP), m.group(CONDITION_VALUE_GROUP));
    }

    private CellFormatType getCellFormatType(Matcher matcher) {
        String fdesc = matcher.group(SPECIFICATION_GROUP);
        return formatType(fdesc);
    }

    private CellFormatter getFormatter(Locale locale, Matcher matcher) {
        String currencyRepl;
        String fdesc = matcher.group(SPECIFICATION_GROUP);
        Matcher currencyM = CURRENCY_PAT.matcher(fdesc);
        if (currencyM.find()) {
            String currencyPart = currencyM.group(1);
            if (currencyPart.startsWith("[$-")) {
                currencyRepl = "$";
            } else if (!currencyPart.contains(ProcessIdUtil.DEFAULT_PROCESSID)) {
                currencyRepl = currencyPart.substring(2, currencyPart.indexOf(CollectionUtils.DEFAULT_TOSTRING_SUFFIX));
            } else {
                currencyRepl = currencyPart.substring(2, currencyPart.lastIndexOf(45));
            }
            fdesc = fdesc.replace(currencyPart, currencyRepl);
        }
        return this.type.formatter(locale, fdesc);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x00bd, code lost:
    
        if (r6.equals("@") != false) goto L61;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:12:0x0041. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.apache.poi.ss.format.CellFormatType formatType(java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 454
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.format.CellFormatPart.formatType(java.lang.String):org.apache.poi.ss.format.CellFormatType");
    }

    static String quoteSpecial(String repl, CellFormatType type) {
        StringBuilder sb = new StringBuilder();
        PrimitiveIterator.OfInt codePoints = CodepointsUtil.primitiveIterator(repl);
        while (codePoints.hasNext()) {
            int codepoint = codePoints.nextInt();
            if (codepoint == 39 && type.isSpecial(Chars.QUOTE)) {
                sb.append((char) 0);
            } else {
                char[] chars = Character.toChars(codepoint);
                boolean special = type.isSpecial(chars[0]);
                if (special) {
                    sb.append(Chars.QUOTE);
                }
                sb.append(chars);
                if (special) {
                    sb.append(Chars.QUOTE);
                }
            }
        }
        return sb.toString();
    }

    public CellFormatResult apply(Object value) {
        String text;
        Color textColor;
        boolean applies = applies(value);
        if (applies) {
            text = this.format.format(value);
            textColor = this.color;
        } else {
            text = this.format.simpleFormat(value);
            textColor = null;
        }
        return new CellFormatResult(applies, text, textColor);
    }

    public CellFormatResult apply(JLabel label, Object value) {
        CellFormatResult result = apply(value);
        label.setText(result.text);
        if (result.textColor != null) {
            label.setForeground(result.textColor);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CellFormatType getCellFormatType() {
        return this.type;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasCondition() {
        return this.condition != null;
    }

    public static StringBuffer parseFormat(String fdesc, CellFormatType type, PartHandler partHandler) {
        Matcher m = SPECIFICATION_PAT.matcher(fdesc);
        StringBuffer fmt = new StringBuffer();
        while (m.find()) {
            String part = group(m, 0);
            if (!part.isEmpty()) {
                String repl = partHandler.handlePart(m, part, type, fmt);
                if (repl == null) {
                    switch (part.charAt(0)) {
                        case '\"':
                            repl = quoteSpecial(part.substring(1, part.length() - 1), type);
                            break;
                        case '*':
                            repl = expandChar(part);
                            break;
                        case '\\':
                            repl = quoteSpecial(part.substring(1), type);
                            break;
                        case '_':
                            repl = StringUtils.SPACE;
                            break;
                        default:
                            repl = part;
                            break;
                    }
                }
                m.appendReplacement(fmt, Matcher.quoteReplacement(repl));
            }
        }
        m.appendTail(fmt);
        if (type.isSpecial(Chars.QUOTE)) {
            int pos = 0;
            while (true) {
                int indexOf = fmt.indexOf("''", pos);
                pos = indexOf;
                if (indexOf >= 0) {
                    fmt.delete(pos, pos + 2);
                    if (partHandler instanceof CellDateFormatter.DatePartHandler) {
                        CellDateFormatter.DatePartHandler datePartHandler = (CellDateFormatter.DatePartHandler) partHandler;
                        datePartHandler.updatePositions(pos, -2);
                    }
                } else {
                    int pos2 = 0;
                    while (true) {
                        int indexOf2 = fmt.indexOf("\u0000", pos2);
                        pos2 = indexOf2;
                        if (indexOf2 >= 0) {
                            fmt.replace(pos2, pos2 + 1, "''");
                            if (partHandler instanceof CellDateFormatter.DatePartHandler) {
                                CellDateFormatter.DatePartHandler datePartHandler2 = (CellDateFormatter.DatePartHandler) partHandler;
                                datePartHandler2.updatePositions(pos2, 1);
                            }
                        }
                    }
                }
            }
        }
        return fmt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String expandChar(String part) {
        PrimitiveIterator.OfInt iterator = CodepointsUtil.primitiveIterator(part);
        Integer c0 = iterator.hasNext() ? iterator.next() : null;
        Integer c1 = iterator.hasNext() ? iterator.next() : null;
        if (c0 == null || c1 == null) {
            throw new IllegalArgumentException("Expected part string to have at least 2 chars");
        }
        char[] ch = Character.toChars(c1.intValue());
        StringBuilder sb = new StringBuilder(ch.length * 3);
        sb.append(ch);
        sb.append(ch);
        sb.append(ch);
        return sb.toString();
    }

    public static String group(Matcher m, int g) {
        String str = m.group(g);
        return str == null ? "" : str;
    }

    public String toString() {
        return this.format.format;
    }
}
