package org.apache.poi.ss.format;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.LocaleUtil;

/* loaded from: classes10.dex */
public class CellNumberFormatter extends CellFormatter {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) CellNumberFormatter.class);
    private final CellFormatter SIMPLE_NUMBER;
    private final Special afterFractional;
    private final Special afterInteger;
    private final DecimalFormat decimalFmt;
    private final Special decimalPoint;
    private final String denominatorFmt;
    private final List<Special> denominatorSpecials;
    private final String desc;
    private final Special exponent;
    private final List<Special> exponentDigitSpecials;
    private final List<Special> exponentSpecials;
    private final List<Special> fractionalSpecials;
    private final boolean improperFraction;
    private final List<Special> integerSpecials;
    private final int maxDenominator;
    private final Special numerator;
    private final String numeratorFmt;
    private final List<Special> numeratorSpecials;
    private final String printfFmt;
    private final double scale;
    private final boolean showGroupingSeparator;
    private final Special slash;
    private final List<Special> specials;

    /* loaded from: classes10.dex */
    private static class GeneralNumberFormatter extends CellFormatter {
        private GeneralNumberFormatter(Locale locale) {
            super(locale, "General");
        }

        @Override // org.apache.poi.ss.format.CellFormatter
        public void formatValue(StringBuffer toAppendTo, Object value) {
            CellFormatter cf;
            if (value == null) {
                return;
            }
            if (value instanceof Number) {
                Number num = (Number) value;
                cf = num.doubleValue() % 1.0d == 0.0d ? new CellNumberFormatter(this.locale, "#") : new CellNumberFormatter(this.locale, "#.#");
            } else {
                cf = CellTextFormatter.SIMPLE_TEXT;
            }
            cf.formatValue(toAppendTo, value);
        }

        @Override // org.apache.poi.ss.format.CellFormatter
        public void simpleValue(StringBuffer toAppendTo, Object value) {
            formatValue(toAppendTo, value);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class Special {
        final char ch;
        int pos;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Special(char ch, int pos) {
            this.ch = ch;
            this.pos = pos;
        }

        public String toString() {
            return "'" + this.ch + "' @ " + this.pos;
        }
    }

    public CellNumberFormatter(String format) {
        this(LocaleUtil.getUserLocale(), format);
    }

    public CellNumberFormatter(Locale locale, String format) {
        super(locale, format);
        int fractionPartWidth;
        this.specials = new ArrayList();
        this.integerSpecials = new ArrayList();
        this.fractionalSpecials = new ArrayList();
        this.numeratorSpecials = new ArrayList();
        this.denominatorSpecials = new ArrayList();
        this.exponentSpecials = new ArrayList();
        this.exponentDigitSpecials = new ArrayList();
        this.SIMPLE_NUMBER = new GeneralNumberFormatter(this.locale);
        CellNumberPartHandler ph = new CellNumberPartHandler();
        StringBuffer descBuf = CellFormatPart.parseFormat(format, CellFormatType.NUMBER, ph);
        this.exponent = ph.getExponent();
        this.specials.addAll(ph.getSpecials());
        this.improperFraction = ph.isImproperFraction();
        if ((ph.getDecimalPoint() == null && ph.getExponent() == null) || ph.getSlash() == null) {
            this.slash = ph.getSlash();
            this.numerator = ph.getNumerator();
        } else {
            this.slash = null;
            this.numerator = null;
        }
        int precision = interpretPrecision(ph.getDecimalPoint(), this.specials);
        if (ph.getDecimalPoint() != null) {
            int fractionPartWidth2 = precision + 1;
            if (precision != 0) {
                this.decimalPoint = ph.getDecimalPoint();
            } else {
                this.specials.remove(ph.getDecimalPoint());
                this.decimalPoint = null;
            }
            fractionPartWidth = fractionPartWidth2;
        } else {
            this.decimalPoint = null;
            fractionPartWidth = 0;
        }
        if (this.decimalPoint != null) {
            this.afterInteger = this.decimalPoint;
        } else if (this.exponent != null) {
            this.afterInteger = this.exponent;
        } else if (this.numerator != null) {
            this.afterInteger = this.numerator;
        } else {
            this.afterInteger = null;
        }
        if (this.exponent != null) {
            this.afterFractional = this.exponent;
        } else if (this.numerator != null) {
            this.afterFractional = this.numerator;
        } else {
            this.afterFractional = null;
        }
        double[] scaleByRef = {ph.getScale()};
        this.showGroupingSeparator = interpretIntegerCommas(descBuf, this.specials, this.decimalPoint, integerEnd(), fractionalEnd(), scaleByRef);
        if (this.exponent == null) {
            this.scale = scaleByRef[0];
        } else {
            this.scale = 1.0d;
        }
        if (precision != 0) {
            this.fractionalSpecials.addAll(this.specials.subList(this.specials.indexOf(this.decimalPoint) + 1, fractionalEnd()));
        }
        if (this.exponent != null) {
            int exponentPos = this.specials.indexOf(this.exponent);
            this.exponentSpecials.addAll(specialsFor(exponentPos, 2));
            this.exponentDigitSpecials.addAll(specialsFor(exponentPos + 2));
        }
        if (this.slash != null) {
            if (this.numerator != null) {
                this.numeratorSpecials.addAll(specialsFor(this.specials.indexOf(this.numerator)));
            }
            this.denominatorSpecials.addAll(specialsFor(this.specials.indexOf(this.slash) + 1));
            if (!this.denominatorSpecials.isEmpty()) {
                this.maxDenominator = maxValue(this.denominatorSpecials);
                this.numeratorFmt = singleNumberFormat(this.numeratorSpecials);
                this.denominatorFmt = singleNumberFormat(this.denominatorSpecials);
            } else {
                this.numeratorSpecials.clear();
                this.maxDenominator = 1;
                this.numeratorFmt = null;
                this.denominatorFmt = null;
            }
        } else {
            this.maxDenominator = 1;
            this.numeratorFmt = null;
            this.denominatorFmt = null;
        }
        this.integerSpecials.addAll(this.specials.subList(0, integerEnd()));
        if (this.exponent == null) {
            int integerPartWidth = calculateIntegerPartWidth();
            int totalWidth = integerPartWidth + fractionPartWidth;
            if (totalWidth == 0) {
                this.printfFmt = "";
            } else {
                this.printfFmt = "%0" + totalWidth + '.' + precision + "f";
            }
            this.decimalFmt = null;
        } else {
            StringBuffer fmtBuf = new StringBuffer();
            boolean first = true;
            if (this.integerSpecials.size() == 1) {
                fmtBuf.append("0");
                first = false;
            } else {
                for (Special s : this.integerSpecials) {
                    if (isDigitFmt(s)) {
                        fmtBuf.append(first ? '#' : '0');
                        first = false;
                    }
                }
            }
            if (!this.fractionalSpecials.isEmpty()) {
                fmtBuf.append('.');
                for (Special s2 : this.fractionalSpecials) {
                    if (isDigitFmt(s2)) {
                        if (!first) {
                            fmtBuf.append('0');
                        }
                        first = false;
                    }
                }
            }
            fmtBuf.append('E');
            placeZeros(fmtBuf, this.exponentSpecials.subList(2, this.exponentSpecials.size()));
            this.decimalFmt = new DecimalFormat(fmtBuf.toString(), getDecimalFormatSymbols());
            this.printfFmt = null;
        }
        this.desc = descBuf.toString();
    }

    private DecimalFormatSymbols getDecimalFormatSymbols() {
        return DecimalFormatSymbols.getInstance(this.locale);
    }

    private static void placeZeros(StringBuffer sb, List<Special> specials) {
        for (Special s : specials) {
            if (isDigitFmt(s)) {
                sb.append('0');
            }
        }
    }

    private static CellNumberStringMod insertMod(Special special, CharSequence toAdd, int where) {
        return new CellNumberStringMod(special, toAdd, where);
    }

    private static CellNumberStringMod deleteMod(Special start, boolean startInclusive, Special end, boolean endInclusive) {
        return new CellNumberStringMod(start, startInclusive, end, endInclusive);
    }

    private static CellNumberStringMod replaceMod(Special start, boolean startInclusive, Special end, boolean endInclusive, char withChar) {
        return new CellNumberStringMod(start, startInclusive, end, endInclusive, withChar);
    }

    private static String singleNumberFormat(List<Special> numSpecials) {
        return "%0" + numSpecials.size() + "d";
    }

    private static int maxValue(List<Special> s) {
        return Math.toIntExact(Math.round(Math.pow(10.0d, s.size()) - 1.0d));
    }

    private List<Special> specialsFor(int pos, int takeFirst) {
        if (pos >= this.specials.size()) {
            return Collections.emptyList();
        }
        ListIterator<Special> it = this.specials.listIterator(pos + takeFirst);
        Special last = it.next();
        int end = pos + takeFirst;
        while (it.hasNext()) {
            Special s = it.next();
            if (!isDigitFmt(s) || s.pos - last.pos > 1) {
                break;
            }
            end++;
            last = s;
        }
        return this.specials.subList(pos, end + 1);
    }

    private List<Special> specialsFor(int pos) {
        return specialsFor(pos, 0);
    }

    private static boolean isDigitFmt(Special s) {
        return s.ch == '0' || s.ch == '?' || s.ch == '#';
    }

    private int calculateIntegerPartWidth() {
        Special s;
        int digitCount = 0;
        Iterator<Special> it = this.specials.iterator();
        while (it.hasNext() && (s = it.next()) != this.afterInteger) {
            if (isDigitFmt(s)) {
                digitCount++;
            }
        }
        return digitCount;
    }

    private static int interpretPrecision(Special decimalPoint, List<Special> specials) {
        int idx = specials.indexOf(decimalPoint);
        int precision = 0;
        if (idx != -1) {
            ListIterator<Special> it = specials.listIterator(idx + 1);
            while (it.hasNext()) {
                Special s = it.next();
                if (!isDigitFmt(s)) {
                    break;
                }
                precision++;
            }
        }
        return precision;
    }

    private static boolean interpretIntegerCommas(StringBuffer sb, List<Special> specials, Special decimalPoint, int integerEnd, int fractionalEnd, double[] scale) {
        ListIterator<Special> it = specials.listIterator(integerEnd);
        boolean stillScaling = true;
        boolean integerCommas = false;
        while (it.hasPrevious()) {
            if (it.previous().ch != ',') {
                stillScaling = false;
            } else if (stillScaling) {
                scale[0] = scale[0] / 1000.0d;
            } else {
                integerCommas = true;
            }
        }
        if (decimalPoint != null) {
            ListIterator<Special> it2 = specials.listIterator(fractionalEnd);
            while (it2.hasPrevious() && it2.previous().ch == ',') {
                scale[0] = scale[0] / 1000.0d;
            }
        }
        ListIterator<Special> it3 = specials.listIterator();
        int removed = 0;
        while (it3.hasNext()) {
            Special s = it3.next();
            s.pos -= removed;
            if (s.ch == ',') {
                removed++;
                it3.remove();
                sb.deleteCharAt(s.pos);
            }
        }
        return integerCommas;
    }

    private int integerEnd() {
        return this.afterInteger == null ? this.specials.size() : this.specials.indexOf(this.afterInteger);
    }

    private int fractionalEnd() {
        return this.afterFractional == null ? this.specials.size() : this.specials.indexOf(this.afterFractional);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:31:0x011f. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01f2  */
    @Override // org.apache.poi.ss.format.CellFormatter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void formatValue(java.lang.StringBuffer r28, java.lang.Object r29) {
        /*
            Method dump skipped, instructions count: 610
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.format.CellNumberFormatter.formatValue(java.lang.StringBuffer, java.lang.Object):void");
    }

    private void writeScientific(double value, StringBuffer output, Set<CellNumberStringMod> mods) {
        char expSignRes;
        StringBuffer result = new StringBuffer();
        FieldPosition fractionPos = new FieldPosition(1);
        this.decimalFmt.format(value, result, fractionPos);
        writeInteger(result, output, this.integerSpecials, mods, this.showGroupingSeparator);
        writeFractional(result, output);
        int ePos = fractionPos.getEndIndex();
        int signPos = ePos + 1;
        char expSignRes2 = result.charAt(signPos);
        if (expSignRes2 == '-') {
            expSignRes = expSignRes2;
        } else {
            result.insert(signPos, '+');
            expSignRes = '+';
        }
        ListIterator<Special> it = this.exponentSpecials.listIterator(1);
        Special expSign = it.next();
        char expSignFmt = expSign.ch;
        if (expSignRes == '-' || expSignFmt == '+') {
            mods.add(replaceMod(expSign, true, expSign, true, expSignRes));
        } else {
            mods.add(deleteMod(expSign, true, expSign, true));
        }
        StringBuffer exponentNum = new StringBuffer(result.substring(signPos + 1));
        writeInteger(exponentNum, output, this.exponentDigitSpecials, mods, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00fb A[Catch: RuntimeException -> 0x0124, TryCatch #0 {RuntimeException -> 0x0124, blocks: (B:57:0x00d3, B:59:0x00d7, B:62:0x00e0, B:46:0x00f7, B:48:0x00fb, B:49:0x010b, B:45:0x00f0), top: B:56:0x00d3 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x010a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void writeFraction(double r20, java.lang.StringBuffer r22, double r23, java.lang.StringBuffer r25, java.util.Set<org.apache.poi.ss.format.CellNumberStringMod> r26) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.format.CellNumberFormatter.writeFraction(double, java.lang.StringBuffer, double, java.lang.StringBuffer, java.util.Set):void");
    }

    private String localiseFormat(String format) {
        DecimalFormatSymbols dfs = getDecimalFormatSymbols();
        if (format.contains(CollectionUtils.COMMA) && dfs.getGroupingSeparator() != ',') {
            if (format.contains(".") && dfs.getDecimalSeparator() != '.') {
                return replaceLast(format, "\\.", "[DECIMAL_SEPARATOR]").replace(',', dfs.getGroupingSeparator()).replace("[DECIMAL_SEPARATOR]", Character.toString(dfs.getDecimalSeparator()));
            }
            return format.replace(',', dfs.getGroupingSeparator());
        }
        if (format.contains(".") && dfs.getDecimalSeparator() != '.') {
            return format.replace('.', dfs.getDecimalSeparator());
        }
        return format;
    }

    private static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }

    private static boolean hasChar(char ch, List<Special> numSpecials) {
        for (Special s : numSpecials) {
            if (s.ch == ch) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasChar(char ch, List<Special> numSpecials1, List<Special> numSpecials2) {
        return hasChar(ch, numSpecials1) || hasChar(ch, numSpecials2);
    }

    private static boolean hasChar(char ch, List<Special> numSpecials1, List<Special> numSpecials2, List<Special> numSpecials3) {
        return hasChar(ch, numSpecials1) || hasChar(ch, numSpecials2) || hasChar(ch, numSpecials3);
    }

    private void writeSingleInteger(String fmt, int num, StringBuffer output, List<Special> numSpecials, Set<CellNumberStringMod> mods) {
        StringBuffer sb = new StringBuffer();
        Formatter formatter = new Formatter(sb, this.locale);
        try {
            formatter.format(this.locale, fmt, Integer.valueOf(num));
            formatter.close();
            writeInteger(sb, output, numSpecials, mods, false);
        } finally {
        }
    }

    private void writeInteger(StringBuffer result, StringBuffer output, List<Special> numSpecials, Set<CellNumberStringMod> mods, boolean showGroupingSeparator) {
        char c;
        char resultCh;
        char resultCh2;
        DecimalFormatSymbols dfs = getDecimalFormatSymbols();
        String decimalSeparator = Character.toString(dfs.getDecimalSeparator());
        String groupingSeparator = Character.toString(dfs.getGroupingSeparator());
        int pos = result.indexOf(decimalSeparator) - 1;
        if (pos < 0) {
            if (this.exponent != null && numSpecials == this.integerSpecials) {
                pos = result.indexOf("E") - 1;
            } else {
                pos = result.length() - 1;
            }
        }
        int strip = 0;
        while (true) {
            c = '0';
            if (strip >= pos || !((resultCh2 = result.charAt(strip)) == '0' || resultCh2 == dfs.getGroupingSeparator())) {
                break;
            } else {
                strip++;
            }
        }
        ListIterator<Special> it = numSpecials.listIterator(numSpecials.size());
        Special lastOutputIntegerDigit = null;
        int digit = 0;
        while (it.hasPrevious()) {
            if (pos >= 0) {
                resultCh = result.charAt(pos);
            } else {
                resultCh = '0';
            }
            Special s = it.previous();
            boolean followWithGroupingSeparator = showGroupingSeparator && digit > 0 && digit % 3 == 0;
            boolean zeroStrip = false;
            if (resultCh != c || s.ch == c || s.ch == '?' || pos >= strip) {
                zeroStrip = s.ch == '?' && pos < strip;
                output.setCharAt(s.pos, zeroStrip ? Chars.SPACE : resultCh);
                lastOutputIntegerDigit = s;
            }
            if (followWithGroupingSeparator) {
                mods.add(insertMod(s, zeroStrip ? StringUtils.SPACE : groupingSeparator, 2));
            }
            digit++;
            pos--;
            c = '0';
        }
        if (pos >= 0) {
            int pos2 = pos + 1;
            StringBuffer extraLeadingDigits = new StringBuffer(result.substring(0, pos2));
            if (showGroupingSeparator) {
                while (pos2 > 0) {
                    if (digit > 0 && digit % 3 == 0) {
                        extraLeadingDigits.insert(pos2, groupingSeparator);
                    }
                    digit++;
                    pos2--;
                }
            }
            mods.add(insertMod(lastOutputIntegerDigit, extraLeadingDigits, 1));
        }
    }

    private void writeFractional(StringBuffer result, StringBuffer output) {
        int strip;
        if (!this.fractionalSpecials.isEmpty()) {
            String decimalSeparator = Character.toString(getDecimalFormatSymbols().getDecimalSeparator());
            int digit = result.indexOf(decimalSeparator) + 1;
            if (this.exponent != null) {
                strip = result.indexOf("e");
            } else {
                strip = result.length();
            }
            do {
                strip--;
                if (strip <= digit) {
                    break;
                }
            } while (result.charAt(strip) == '0');
            for (Special s : this.fractionalSpecials) {
                char resultCh = result.charAt(digit);
                if (resultCh != '0' || s.ch == '0' || digit < strip) {
                    output.setCharAt(s.pos, resultCh);
                } else if (s.ch == '?') {
                    output.setCharAt(s.pos, Chars.SPACE);
                }
                digit++;
            }
        }
    }

    @Override // org.apache.poi.ss.format.CellFormatter
    public void simpleValue(StringBuffer toAppendTo, Object value) {
        this.SIMPLE_NUMBER.formatValue(toAppendTo, value);
    }

    private static Special lastSpecial(List<Special> s) {
        return s.get(s.size() - 1);
    }
}
