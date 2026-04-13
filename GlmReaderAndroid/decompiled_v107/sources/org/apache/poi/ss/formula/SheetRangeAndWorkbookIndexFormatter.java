package org.apache.poi.ss.formula;

import org.apache.logging.log4j.util.Chars;
import org.apache.xmlbeans.impl.common.NameUtil;

/* loaded from: classes10.dex */
public class SheetRangeAndWorkbookIndexFormatter {
    private SheetRangeAndWorkbookIndexFormatter() {
    }

    public static String format(StringBuilder sb, int workbookIndex, String firstSheetName, String lastSheetName) {
        if (anySheetNameNeedsEscaping(firstSheetName, lastSheetName)) {
            return formatWithDelimiting(sb, workbookIndex, firstSheetName, lastSheetName);
        }
        return formatWithoutDelimiting(sb, workbookIndex, firstSheetName, lastSheetName);
    }

    private static String formatWithDelimiting(StringBuilder sb, int workbookIndex, String firstSheetName, String lastSheetName) {
        sb.append(Chars.QUOTE);
        if (workbookIndex >= 0) {
            sb.append('[');
            sb.append(workbookIndex);
            sb.append(']');
        }
        SheetNameFormatter.appendAndEscape(sb, firstSheetName);
        if (lastSheetName != null) {
            sb.append(NameUtil.COLON);
            SheetNameFormatter.appendAndEscape(sb, lastSheetName);
        }
        sb.append(Chars.QUOTE);
        return sb.toString();
    }

    private static String formatWithoutDelimiting(StringBuilder sb, int workbookIndex, String firstSheetName, String lastSheetName) {
        if (workbookIndex >= 0) {
            sb.append('[');
            sb.append(workbookIndex);
            sb.append(']');
        }
        sb.append(firstSheetName);
        if (lastSheetName != null) {
            sb.append(NameUtil.COLON);
            sb.append(lastSheetName);
        }
        return sb.toString();
    }

    private static boolean anySheetNameNeedsEscaping(String firstSheetName, String lastSheetName) {
        boolean anySheetNameNeedsDelimiting = SheetNameFormatter.needsDelimiting(firstSheetName);
        return anySheetNameNeedsDelimiting | SheetNameFormatter.needsDelimiting(lastSheetName);
    }
}
