package org.apache.commons.math3.fraction;

import java.io.Serializable;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes10.dex */
public abstract class AbstractFormat extends NumberFormat implements Serializable {
    private static final long serialVersionUID = -6981118387974191891L;
    private NumberFormat denominatorFormat;
    private NumberFormat numeratorFormat;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractFormat() {
        this(getDefaultNumberFormat());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractFormat(NumberFormat format) {
        this(format, (NumberFormat) format.clone());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractFormat(NumberFormat numeratorFormat, NumberFormat denominatorFormat) {
        this.numeratorFormat = numeratorFormat;
        this.denominatorFormat = denominatorFormat;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static NumberFormat getDefaultNumberFormat() {
        return getDefaultNumberFormat(Locale.getDefault());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static NumberFormat getDefaultNumberFormat(Locale locale) {
        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        nf.setMaximumFractionDigits(0);
        nf.setParseIntegerOnly(true);
        return nf;
    }

    public NumberFormat getDenominatorFormat() {
        return this.denominatorFormat;
    }

    public NumberFormat getNumeratorFormat() {
        return this.numeratorFormat;
    }

    public void setDenominatorFormat(NumberFormat format) {
        if (format == null) {
            throw new NullArgumentException(LocalizedFormats.DENOMINATOR_FORMAT, new Object[0]);
        }
        this.denominatorFormat = format;
    }

    public void setNumeratorFormat(NumberFormat format) {
        if (format == null) {
            throw new NullArgumentException(LocalizedFormats.NUMERATOR_FORMAT, new Object[0]);
        }
        this.numeratorFormat = format;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void parseAndIgnoreWhitespace(String source, ParsePosition pos) {
        parseNextCharacter(source, pos);
        pos.setIndex(pos.getIndex() - 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static char parseNextCharacter(String source, ParsePosition pos) {
        int index;
        char c;
        int index2 = pos.getIndex();
        int n = source.length();
        if (index2 >= n) {
            return (char) 0;
        }
        while (true) {
            index = index2 + 1;
            c = source.charAt(index2);
            if (!Character.isWhitespace(c) || index >= n) {
                break;
            }
            index2 = index;
        }
        pos.setIndex(index);
        if (index >= n) {
            return (char) 0;
        }
        return c;
    }

    @Override // java.text.NumberFormat
    public StringBuffer format(double value, StringBuffer buffer, FieldPosition position) {
        return format(Double.valueOf(value), buffer, position);
    }

    @Override // java.text.NumberFormat
    public StringBuffer format(long value, StringBuffer buffer, FieldPosition position) {
        return format(Long.valueOf(value), buffer, position);
    }
}
