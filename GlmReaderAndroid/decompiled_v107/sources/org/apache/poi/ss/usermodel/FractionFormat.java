package org.apache.poi.ss.usermodel;

import java.math.BigDecimal;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.ss.format.SimpleFraction;
import org.apache.poi.ss.formula.eval.NotImplementedException;

/* loaded from: classes10.dex */
public class FractionFormat extends Format {
    private static final int MAX_DENOM_POW = 4;
    private final int exactDenom;
    private final int maxDenom;
    private final String wholePartFormatString;
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) FractionFormat.class);
    private static final Pattern DENOM_FORMAT_PATTERN = Pattern.compile("(#+)|(\\d+)");

    public FractionFormat(String wholePartFormatString, String denomFormatString) {
        this.wholePartFormatString = wholePartFormatString;
        Matcher m = DENOM_FORMAT_PATTERN.matcher(denomFormatString);
        int tmpExact = -1;
        int tmpMax = -1;
        if (m.find()) {
            if (m.group(2) != null) {
                try {
                    int tmpExact2 = Integer.parseInt(m.group(2));
                    tmpExact = tmpExact2 == 0 ? -1 : tmpExact2;
                } catch (NumberFormatException e) {
                    throw new IllegalStateException(e);
                }
            } else if (m.group(1) != null) {
                int len = m.group(1).length();
                tmpMax = (int) Math.pow(10.0d, Math.min(len, 4));
            } else {
                tmpExact = 100;
            }
        }
        if (tmpExact <= 0 && tmpMax <= 0) {
            tmpExact = 100;
        }
        this.exactDenom = tmpExact;
        this.maxDenom = tmpMax;
    }

    public String format(Number num) {
        double d = num.doubleValue();
        return format(new BigDecimal(d));
    }

    private String format(BigDecimal decimal) {
        SimpleFraction fract;
        boolean isNeg = decimal.compareTo(BigDecimal.ZERO) < 0;
        BigDecimal absValue = decimal.abs();
        BigDecimal wholePart = new BigDecimal(absValue.toBigInteger());
        BigDecimal decPart = absValue.remainder(BigDecimal.ONE);
        if (wholePart.add(decPart).compareTo(BigDecimal.ZERO) == 0) {
            return "0";
        }
        if (decPart.compareTo(BigDecimal.ZERO) == 0) {
            StringBuilder sb = new StringBuilder();
            if (isNeg) {
                sb.append('-');
            }
            sb.append(wholePart);
            return sb.toString();
        }
        try {
            if (this.exactDenom > 0) {
                fract = SimpleFraction.buildFractionExactDenominator(decPart.doubleValue(), this.exactDenom);
            } else {
                fract = SimpleFraction.buildFractionMaxDenominator(decPart.doubleValue(), this.maxDenom);
            }
            StringBuilder sb2 = new StringBuilder();
            if (isNeg) {
                sb2.append('-');
            }
            if (this.wholePartFormatString == null || this.wholePartFormatString.isEmpty()) {
                int fden = fract.getDenominator();
                int fnum = fract.getNumerator();
                BigDecimal trueNum = wholePart.multiply(BigDecimal.valueOf(fden)).add(BigDecimal.valueOf(fnum));
                sb2.append(trueNum.toBigInteger()).append(PackagingURIHelper.FORWARD_SLASH_STRING).append(fden);
                return sb2.toString();
            }
            if (fract.getNumerator() == 0) {
                sb2.append(wholePart);
                return sb2.toString();
            }
            if (fract.getNumerator() == fract.getDenominator()) {
                sb2.append(wholePart.add(BigDecimal.ONE));
                return sb2.toString();
            }
            if (wholePart.compareTo(BigDecimal.ZERO) > 0) {
                sb2.append(wholePart).append(StringUtils.SPACE);
            }
            sb2.append(fract.getNumerator()).append(PackagingURIHelper.FORWARD_SLASH_STRING).append(fract.getDenominator());
            return sb2.toString();
        } catch (RuntimeException e) {
            LOGGER.atWarn().withThrowable(e).log("Can't format fraction");
            return Double.toString(decimal.doubleValue());
        }
    }

    @Override // java.text.Format
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        if (!(obj instanceof Number)) {
            throw new IllegalArgumentException("Cannot format object of " + obj.getClass() + " to number: " + obj);
        }
        return toAppendTo.append(format((Number) obj));
    }

    @Override // java.text.Format
    public Object parseObject(String source, ParsePosition pos) {
        throw new NotImplementedException("Reverse parsing not supported");
    }
}
