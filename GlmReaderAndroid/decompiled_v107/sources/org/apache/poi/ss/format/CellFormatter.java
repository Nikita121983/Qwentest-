package org.apache.poi.ss.format;

import java.util.Locale;
import org.apache.poi.util.LocaleUtil;

/* loaded from: classes10.dex */
public abstract class CellFormatter {
    protected final String format;
    protected final Locale locale;

    public abstract void formatValue(StringBuffer stringBuffer, Object obj);

    public abstract void simpleValue(StringBuffer stringBuffer, Object obj);

    public CellFormatter(String format) {
        this(LocaleUtil.getUserLocale(), format);
    }

    public CellFormatter(Locale locale, String format) {
        this.locale = locale;
        this.format = format;
    }

    public String format(Object value) {
        StringBuffer sb = new StringBuffer();
        formatValue(sb, value);
        return sb.toString();
    }

    public String simpleFormat(Object value) {
        StringBuffer sb = new StringBuffer();
        simpleValue(sb, value);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String quote(String str) {
        return '\"' + str + '\"';
    }
}
