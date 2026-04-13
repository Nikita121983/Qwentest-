package org.apache.poi.ss.format;

import java.text.AttributedCharacterIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.regex.Matcher;
import org.apache.poi.ss.format.CellFormatPart;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public class CellDateFormatter extends CellFormatter {
    private static final Calendar EXCEL_EPOCH_CAL = LocaleUtil.getLocaleCalendar(1904, 0, 1);
    private static final int NUM_MILLISECONDS_IN_DAY = 86400000;
    private static CellDateFormatter SIMPLE_DATE_FORMATTER;
    private boolean amPmUpper;
    private final DateFormat dateFmt;
    private String sFmt;
    private boolean showAmPm;
    private boolean showM;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class DatePartHandler implements CellFormatPart.PartHandler {
        private int hLen;
        private int mLen;
        private int mStart = -1;
        private int hStart = -1;

        DatePartHandler() {
        }

        @Override // org.apache.poi.ss.format.CellFormatPart.PartHandler
        public String handlePart(Matcher m, String part, CellFormatType type, StringBuffer desc) {
            int pos = desc.length();
            boolean z = false;
            char firstCh = part.charAt(0);
            switch (firstCh) {
                case '0':
                    this.mStart = -1;
                    int sLen = part.length();
                    CellDateFormatter.this.sFmt = "%0" + (sLen + 2) + "." + sLen + "f";
                    return part.replace('0', 'S');
                case 'A':
                case 'P':
                case 'a':
                case 'p':
                    if (part.length() > 1) {
                        this.mStart = -1;
                        CellDateFormatter.this.showAmPm = true;
                        CellDateFormatter.this.showM = StringUtil.toLowerCase(part.charAt(1)).equals("m");
                        CellDateFormatter cellDateFormatter = CellDateFormatter.this;
                        if (CellDateFormatter.this.showM || StringUtil.isUpperCase(part.charAt(0))) {
                            z = true;
                        }
                        cellDateFormatter.amPmUpper = z;
                        return "a";
                    }
                    return null;
                case 'D':
                case 'd':
                    this.mStart = -1;
                    if (part.length() <= 2) {
                        return part.toLowerCase(Locale.ROOT);
                    }
                    return part.toLowerCase(Locale.ROOT).replace('d', 'E');
                case 'H':
                case 'h':
                    this.mStart = -1;
                    this.hStart = pos;
                    this.hLen = part.length();
                    return part.toLowerCase(Locale.ROOT);
                case 'M':
                case 'm':
                    this.mStart = pos;
                    this.mLen = part.length();
                    if (this.hStart >= 0) {
                        return part.toLowerCase(Locale.ROOT);
                    }
                    return part.toUpperCase(Locale.ROOT);
                case 'S':
                case 's':
                    if (this.mStart >= 0) {
                        for (int i = 0; i < this.mLen; i++) {
                            desc.setCharAt(this.mStart + i, 'm');
                        }
                        this.mStart = -1;
                    }
                    return part.toLowerCase(Locale.ROOT);
                case 'Y':
                case 'y':
                    this.mStart = -1;
                    if (part.length() == 1) {
                        part = "yy";
                    } else if (part.length() == 3) {
                        part = "yyyy";
                    }
                    return part.toLowerCase(Locale.ROOT);
                default:
                    return null;
            }
        }

        public void updatePositions(int pos, int offset) {
            if (pos < this.hStart) {
                this.hStart += offset;
            }
            if (pos < this.mStart) {
                this.mStart += offset;
            }
        }

        public void finish(StringBuffer toAppendTo) {
            if (this.hStart >= 0 && !CellDateFormatter.this.showAmPm) {
                for (int i = 0; i < this.hLen; i++) {
                    toAppendTo.setCharAt(this.hStart + i, 'H');
                }
            }
        }
    }

    public CellDateFormatter(String format) {
        this(LocaleUtil.getUserLocale(), format);
    }

    public CellDateFormatter(Locale locale, String format) {
        super(format);
        DatePartHandler partHandler = new DatePartHandler();
        StringBuffer descBuf = CellFormatPart.parseFormat(format, CellFormatType.DATE, partHandler);
        partHandler.finish(descBuf);
        this.dateFmt = new SimpleDateFormat(descBuf.toString(), locale);
        this.dateFmt.setTimeZone(LocaleUtil.getUserTimeZone());
    }

    @Override // org.apache.poi.ss.format.CellFormatter
    public synchronized void formatValue(StringBuffer toAppendTo, Object value) {
        Object value2;
        Object value3;
        double d;
        if (value != null) {
            value2 = value;
        } else {
            value2 = Double.valueOf(0.0d);
        }
        double d2 = 1000.0d;
        if (!(value2 instanceof Number)) {
            value3 = value2;
        } else {
            Number num = (Number) value2;
            double v = Math.round(num.doubleValue() * 8.64E7d);
            if (v == 0.0d) {
                value3 = EXCEL_EPOCH_CAL.getTime();
            } else {
                Calendar c = (Calendar) EXCEL_EPOCH_CAL.clone();
                int seconds = (int) (this.sFmt == null ? Math.round(v / 1000.0d) : v / 1000.0d);
                c.add(13, seconds);
                c.add(14, (int) (v % 1000.0d));
                value3 = c.getTime();
            }
        }
        AttributedCharacterIterator it = this.dateFmt.formatToCharacterIterator(value3);
        char ch = it.first();
        boolean doneMillis = false;
        boolean doneMillis2 = false;
        while (ch != 65535) {
            if (it.getAttribute(DateFormat.Field.MILLISECOND) != null) {
                if (doneMillis) {
                    d = d2;
                } else {
                    Date dateObj = (Date) value3;
                    int pos = toAppendTo.length();
                    Formatter formatter = new Formatter(toAppendTo, Locale.ROOT);
                    try {
                        long msecs = dateObj.getTime() % 1000;
                        if (msecs < 0) {
                            msecs += 1000;
                        }
                        d = d2;
                        formatter.format(this.locale, this.sFmt, Double.valueOf(msecs / d));
                        formatter.close();
                        toAppendTo.delete(pos, pos + 2);
                        doneMillis = true;
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            try {
                                formatter.close();
                                throw th2;
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                                throw th2;
                            }
                        }
                    }
                }
            } else {
                d = d2;
                if (it.getAttribute(DateFormat.Field.AM_PM) != null) {
                    if (!doneMillis2) {
                        if (this.showAmPm) {
                            if (this.amPmUpper) {
                                toAppendTo.append(StringUtil.toUpperCase(ch));
                                if (this.showM) {
                                    toAppendTo.append('M');
                                }
                            } else {
                                toAppendTo.append(StringUtil.toLowerCase(ch));
                                if (this.showM) {
                                    toAppendTo.append('m');
                                }
                            }
                        }
                        doneMillis2 = true;
                    }
                } else {
                    toAppendTo.append(ch);
                }
            }
            ch = it.next();
            d2 = d;
        }
    }

    @Override // org.apache.poi.ss.format.CellFormatter
    public void simpleValue(StringBuffer toAppendTo, Object value) {
        CellDateFormatter cellDateFormatter = SIMPLE_DATE_FORMATTER;
        if (cellDateFormatter == null) {
            synchronized (CellDateFormatter.class) {
                cellDateFormatter = SIMPLE_DATE_FORMATTER;
                if (cellDateFormatter == null) {
                    CellDateFormatter cellDateFormatter2 = new CellDateFormatter("mm/d/y");
                    cellDateFormatter = cellDateFormatter2;
                    SIMPLE_DATE_FORMATTER = cellDateFormatter2;
                }
            }
        }
        cellDateFormatter.formatValue(toAppendTo, value);
    }
}
