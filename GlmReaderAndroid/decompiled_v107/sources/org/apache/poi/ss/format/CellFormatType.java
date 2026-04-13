package org.apache.poi.ss.format;

import java.util.Locale;

/* loaded from: classes10.dex */
public enum CellFormatType {
    GENERAL { // from class: org.apache.poi.ss.format.CellFormatType.1
        @Override // org.apache.poi.ss.format.CellFormatType
        boolean isSpecial(char ch) {
            return false;
        }

        @Override // org.apache.poi.ss.format.CellFormatType
        CellFormatter formatter(String pattern) {
            return new CellGeneralFormatter();
        }

        @Override // org.apache.poi.ss.format.CellFormatType
        CellFormatter formatter(Locale locale, String pattern) {
            return new CellGeneralFormatter(locale);
        }
    },
    NUMBER { // from class: org.apache.poi.ss.format.CellFormatType.2
        @Override // org.apache.poi.ss.format.CellFormatType
        boolean isSpecial(char ch) {
            return false;
        }

        @Override // org.apache.poi.ss.format.CellFormatType
        CellFormatter formatter(String pattern) {
            return new CellNumberFormatter(pattern);
        }

        @Override // org.apache.poi.ss.format.CellFormatType
        CellFormatter formatter(Locale locale, String pattern) {
            return new CellNumberFormatter(locale, pattern);
        }
    },
    DATE { // from class: org.apache.poi.ss.format.CellFormatType.3
        @Override // org.apache.poi.ss.format.CellFormatType
        boolean isSpecial(char ch) {
            return ch == '\'' || (ch <= 127 && Character.isLetter(ch));
        }

        @Override // org.apache.poi.ss.format.CellFormatType
        CellFormatter formatter(String pattern) {
            return new CellDateFormatter(pattern);
        }

        @Override // org.apache.poi.ss.format.CellFormatType
        CellFormatter formatter(Locale locale, String pattern) {
            return new CellDateFormatter(locale, pattern);
        }
    },
    ELAPSED { // from class: org.apache.poi.ss.format.CellFormatType.4
        @Override // org.apache.poi.ss.format.CellFormatType
        boolean isSpecial(char ch) {
            return false;
        }

        @Override // org.apache.poi.ss.format.CellFormatType
        CellFormatter formatter(String pattern) {
            return new CellElapsedFormatter(pattern);
        }

        @Override // org.apache.poi.ss.format.CellFormatType
        CellFormatter formatter(Locale locale, String pattern) {
            return new CellElapsedFormatter(pattern);
        }
    },
    TEXT { // from class: org.apache.poi.ss.format.CellFormatType.5
        @Override // org.apache.poi.ss.format.CellFormatType
        boolean isSpecial(char ch) {
            return false;
        }

        @Override // org.apache.poi.ss.format.CellFormatType
        CellFormatter formatter(String pattern) {
            return new CellTextFormatter(pattern);
        }

        @Override // org.apache.poi.ss.format.CellFormatType
        CellFormatter formatter(Locale locale, String pattern) {
            return new CellTextFormatter(pattern);
        }
    };

    abstract CellFormatter formatter(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract CellFormatter formatter(Locale locale, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isSpecial(char c);
}
