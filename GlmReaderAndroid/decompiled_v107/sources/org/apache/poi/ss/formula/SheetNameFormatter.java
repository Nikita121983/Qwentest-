package org.apache.poi.ss.formula;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.util.CellReference;

/* loaded from: classes10.dex */
public final class SheetNameFormatter {
    private static final Pattern CELL_REF_PATTERN = Pattern.compile("([A-Za-z]+)([0-9]+)");
    private static final char DELIMITER = '\'';

    private SheetNameFormatter() {
    }

    public static String format(String rawSheetName) {
        StringBuilder sb = new StringBuilder((rawSheetName == null ? 0 : rawSheetName.length()) + 2);
        appendFormat(sb, rawSheetName);
        return sb.toString();
    }

    public static void appendFormat(Appendable out, String rawSheetName) {
        try {
            boolean needsQuotes = needsDelimiting(rawSheetName);
            if (needsQuotes) {
                out.append('\'');
                appendAndEscape(out, rawSheetName);
                out.append('\'');
                return;
            }
            appendAndEscape(out, rawSheetName);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0016 A[Catch: Exception -> 0x0045, TryCatch #0 {Exception -> 0x0045, blocks: (B:2:0x0000, B:4:0x0006, B:10:0x0016, B:13:0x0037), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0037 A[Catch: Exception -> 0x0045, TRY_LEAVE, TryCatch #0 {Exception -> 0x0045, blocks: (B:2:0x0000, B:4:0x0006, B:10:0x0016, B:13:0x0037), top: B:1:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void appendFormat(java.lang.Appendable r5, java.lang.String r6, java.lang.String r7) {
        /*
            boolean r0 = needsDelimiting(r6)     // Catch: java.lang.Exception -> L45
            if (r0 != 0) goto Lf
            boolean r0 = needsDelimiting(r7)     // Catch: java.lang.Exception -> L45
            if (r0 == 0) goto Ld
            goto Lf
        Ld:
            r0 = 0
            goto L10
        Lf:
            r0 = 1
        L10:
            r1 = 93
            r2 = 91
            if (r0 == 0) goto L37
            r3 = 39
            r5.append(r3)     // Catch: java.lang.Exception -> L45
            r5.append(r2)     // Catch: java.lang.Exception -> L45
            r4 = 40
            java.lang.String r2 = r6.replace(r2, r4)     // Catch: java.lang.Exception -> L45
            r4 = 41
            java.lang.String r2 = r2.replace(r1, r4)     // Catch: java.lang.Exception -> L45
            appendAndEscape(r5, r2)     // Catch: java.lang.Exception -> L45
            r5.append(r1)     // Catch: java.lang.Exception -> L45
            appendAndEscape(r5, r7)     // Catch: java.lang.Exception -> L45
            r5.append(r3)     // Catch: java.lang.Exception -> L45
            goto L43
        L37:
            r5.append(r2)     // Catch: java.lang.Exception -> L45
            appendOrREF(r5, r6)     // Catch: java.lang.Exception -> L45
            r5.append(r1)     // Catch: java.lang.Exception -> L45
            appendOrREF(r5, r7)     // Catch: java.lang.Exception -> L45
        L43:
            return
        L45:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.SheetNameFormatter.appendFormat(java.lang.Appendable, java.lang.String, java.lang.String):void");
    }

    private static void appendOrREF(Appendable out, String name) throws IOException {
        if (name == null) {
            out.append("#REF");
        } else {
            out.append(name);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void appendAndEscape(Appendable sb, String rawSheetName) {
        try {
            if (rawSheetName == null) {
                sb.append("#REF");
                return;
            }
            int len = rawSheetName.length();
            for (int i = 0; i < len; i++) {
                char ch = rawSheetName.charAt(i);
                if (ch == '\'') {
                    sb.append('\'');
                }
                sb.append(ch);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean needsDelimiting(String rawSheetName) {
        int len;
        if (rawSheetName == null || (len = rawSheetName.length()) < 1) {
            return false;
        }
        if (Character.isDigit(rawSheetName.charAt(0))) {
            return true;
        }
        for (int i = 0; i < len; i++) {
            char ch = rawSheetName.charAt(i);
            if (isSpecialChar(ch)) {
                return true;
            }
        }
        if ((!Character.isLetter(rawSheetName.charAt(0)) || !Character.isDigit(rawSheetName.charAt(len - 1)) || !nameLooksLikePlainCellReference(rawSheetName)) && !nameLooksLikeBooleanLiteral(rawSheetName) && !nameStartsWithR1C1CellReference(rawSheetName)) {
            return false;
        }
        return true;
    }

    private static boolean nameLooksLikeBooleanLiteral(String rawSheetName) {
        switch (rawSheetName.charAt(0)) {
            case 'F':
            case 'f':
                return "FALSE".equalsIgnoreCase(rawSheetName);
            case 'T':
            case 't':
                return "TRUE".equalsIgnoreCase(rawSheetName);
            default:
                return false;
        }
    }

    static boolean isSpecialChar(char ch) {
        if (Character.isLetterOrDigit(ch)) {
            return false;
        }
        switch (ch) {
            case '\t':
            case '\n':
            case '\r':
                throw new IllegalStateException("Illegal character (0x" + Integer.toHexString(ch) + ") found in sheet name");
            case '.':
            case '_':
                return false;
            default:
                return true;
        }
    }

    static boolean cellReferenceIsWithinRange(String lettersPrefix, String numbersSuffix) {
        return CellReference.cellReferenceIsWithinRange(lettersPrefix, numbersSuffix, SpreadsheetVersion.EXCEL97);
    }

    static boolean nameLooksLikePlainCellReference(String rawSheetName) {
        Matcher matcher = CELL_REF_PATTERN.matcher(rawSheetName);
        if (!matcher.matches()) {
            return false;
        }
        String lettersPrefix = matcher.group(1);
        String numbersSuffix = matcher.group(2);
        return cellReferenceIsWithinRange(lettersPrefix, numbersSuffix);
    }

    static boolean nameStartsWithR1C1CellReference(String rawSheetName) {
        int len = rawSheetName.length();
        char firstChar = rawSheetName.charAt(0);
        if (firstChar == 'R' || firstChar == 'r') {
            if (len <= 1) {
                return true;
            }
            char secondChar = rawSheetName.charAt(1);
            if (secondChar == 'C' || secondChar == 'c') {
                if (len <= 2) {
                    return true;
                }
                char thirdChar = rawSheetName.charAt(2);
                return Character.isDigit(thirdChar);
            }
            return Character.isDigit(secondChar);
        }
        if (firstChar != 'C' && firstChar != 'c') {
            return false;
        }
        if (len > 1) {
            return Character.isDigit(rawSheetName.charAt(1));
        }
        return true;
    }
}
