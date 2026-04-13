package org.apache.poi.hssf.usermodel;

import java.util.HashSet;
import java.util.Iterator;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.FontRecord;
import org.apache.poi.hssf.record.StyleRecord;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

/* loaded from: classes10.dex */
public class HSSFOptimiser {
    public static void optimiseFonts(HSSFWorkbook workbook) {
        short[] newPos = new short[workbook.getWorkbook().getNumberOfFontRecords() + 1];
        boolean[] zapRecords = new boolean[newPos.length];
        for (int i = 0; i < newPos.length; i++) {
            newPos[i] = (short) i;
            zapRecords[i] = false;
        }
        int i2 = newPos.length;
        FontRecord[] frecs = new FontRecord[i2];
        for (int i3 = 0; i3 < newPos.length; i3++) {
            if (i3 != 4) {
                frecs[i3] = workbook.getWorkbook().getFontRecordAt(i3);
            }
        }
        for (int i4 = 5; i4 < newPos.length; i4++) {
            int earlierDuplicate = -1;
            for (int j = 0; j < i4 && earlierDuplicate == -1; j++) {
                if (j != 4) {
                    FontRecord frCheck = workbook.getWorkbook().getFontRecordAt(j);
                    if (frCheck.sameProperties(frecs[i4])) {
                        earlierDuplicate = j;
                    }
                }
            }
            if (earlierDuplicate != -1) {
                newPos[i4] = (short) earlierDuplicate;
                zapRecords[i4] = true;
            }
        }
        for (int i5 = 5; i5 < newPos.length; i5++) {
            short preDeletePos = newPos[i5];
            short newPosition = preDeletePos;
            for (int j2 = 0; j2 < preDeletePos; j2++) {
                if (zapRecords[j2]) {
                    newPosition = (short) (newPosition - 1);
                }
            }
            newPos[i5] = newPosition;
        }
        for (int i6 = 5; i6 < newPos.length; i6++) {
            if (zapRecords[i6]) {
                workbook.getWorkbook().removeFontRecord(frecs[i6]);
            }
        }
        workbook.resetFontCache();
        for (int i7 = 0; i7 < workbook.getWorkbook().getNumExFormats(); i7++) {
            ExtendedFormatRecord xfr = workbook.getWorkbook().getExFormatAt(i7);
            xfr.setFontIndex(newPos[xfr.getFontIndex()]);
        }
        HashSet<UnicodeString> doneUnicodeStrings = new HashSet<>();
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            HSSFSheet s = workbook.getSheetAt(sheetNum);
            Iterator<Row> it = s.iterator();
            while (it.hasNext()) {
                Row row = it.next();
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING) {
                        HSSFRichTextString rtr = (HSSFRichTextString) cell.getRichStringCellValue();
                        UnicodeString u = rtr.getRawUnicodeString();
                        if (!doneUnicodeStrings.contains(u)) {
                            for (short i8 = 5; i8 < newPos.length; i8 = (short) (i8 + 1)) {
                                if (i8 != newPos[i8]) {
                                    u.swapFontUse(i8, newPos[i8]);
                                }
                            }
                            doneUnicodeStrings.add(u);
                        }
                    }
                }
            }
        }
    }

    public static void optimiseCellStyles(HSSFWorkbook workbook) {
        short oldXf;
        short[] newPos = new short[workbook.getWorkbook().getNumExFormats()];
        boolean[] isUsed = new boolean[newPos.length];
        boolean[] zapRecords = new boolean[newPos.length];
        boolean[] userDefined = new boolean[newPos.length];
        ExtendedFormatRecord[] xfrs = new ExtendedFormatRecord[newPos.length];
        int i = 0;
        while (true) {
            oldXf = 0;
            if (i >= newPos.length) {
                break;
            }
            isUsed[i] = false;
            newPos[i] = (short) i;
            zapRecords[i] = false;
            userDefined[i] = isUserDefined(workbook, i);
            xfrs[i] = workbook.getWorkbook().getExFormatAt(i);
            i++;
        }
        for (int i2 = 21; i2 < newPos.length; i2++) {
            int earlierDuplicate = -1;
            int j = 0;
            while (true) {
                if (j >= i2) {
                    break;
                }
                ExtendedFormatRecord xfCheck = workbook.getWorkbook().getExFormatAt(j);
                if (!xfCheck.equals(xfrs[i2]) || userDefined[j]) {
                    j++;
                } else {
                    earlierDuplicate = j;
                    break;
                }
            }
            if (earlierDuplicate != -1) {
                newPos[i2] = (short) earlierDuplicate;
                zapRecords[i2] = true;
            }
        }
        int sheetNum = 0;
        while (sheetNum < workbook.getNumberOfSheets()) {
            HSSFSheet s = workbook.getSheetAt(sheetNum);
            Iterator<Row> it = s.iterator();
            while (it.hasNext()) {
                Row row = it.next();
                for (Cell cellI : row) {
                    short oldXf2 = ((HSSFCell) cellI).getCellValueRecord().getXFIndex();
                    short s2 = oldXf;
                    if (oldXf2 < newPos.length) {
                        isUsed[oldXf2] = true;
                    }
                    oldXf = s2;
                }
                short s3 = oldXf;
                short oldXf3 = ((HSSFRow) row).getRowRecord().getXFIndex();
                if (oldXf3 < newPos.length) {
                    isUsed[oldXf3] = true;
                }
                oldXf = s3;
            }
            short s4 = oldXf;
            for (int col = s.getSheet().getMinColumnIndex(); col <= s.getSheet().getMaxColumnIndex(); col++) {
                short oldXf4 = s.getSheet().getXFIndexForColAt((short) col);
                if (oldXf4 < newPos.length) {
                    isUsed[oldXf4] = true;
                }
            }
            sheetNum++;
            oldXf = s4;
        }
        short s5 = oldXf;
        for (int i3 = 21; i3 < isUsed.length; i3++) {
            if (isUserDefined(workbook, i3)) {
                isUsed[i3] = true;
            }
            if (newPos[i3] != i3 && isUsed[i3]) {
                isUsed[newPos[i3]] = true;
            }
        }
        for (int i4 = 21; i4 < isUsed.length; i4++) {
            if (!isUsed[i4]) {
                zapRecords[i4] = true;
                newPos[i4] = s5;
            }
        }
        for (int i5 = 21; i5 < newPos.length; i5++) {
            short preDeletePos = newPos[i5];
            short newPosition = preDeletePos;
            for (int j2 = 0; j2 < preDeletePos; j2++) {
                if (zapRecords[j2]) {
                    newPosition = (short) (newPosition - 1);
                }
            }
            newPos[i5] = newPosition;
            if (i5 != newPosition && newPosition != 0) {
                workbook.getWorkbook().updateStyleRecord(i5, newPosition);
                ExtendedFormatRecord exFormat = workbook.getWorkbook().getExFormatAt(i5);
                short oldParent = exFormat.getParentIndex();
                if (oldParent < newPos.length) {
                    short newParent = newPos[oldParent];
                    exFormat.setParentIndex(newParent);
                }
            }
        }
        int max = newPos.length;
        int removed = 0;
        int i6 = 21;
        while (i6 < max) {
            if (zapRecords[i6 + removed]) {
                workbook.getWorkbook().removeExFormatRecord(i6);
                i6--;
                max--;
                removed++;
            }
            i6++;
        }
        for (int sheetNum2 = 0; sheetNum2 < workbook.getNumberOfSheets(); sheetNum2++) {
            HSSFSheet s6 = workbook.getSheetAt(sheetNum2);
            Iterator<Row> it2 = s6.iterator();
            while (it2.hasNext()) {
                Row row2 = it2.next();
                for (Cell cell : row2) {
                    short oldXf5 = ((HSSFCell) cell).getCellValueRecord().getXFIndex();
                    if (oldXf5 < newPos.length) {
                        HSSFCellStyle newStyle = workbook.getCellStyleAt((int) newPos[oldXf5]);
                        cell.setCellStyle(newStyle);
                    }
                }
                short oldXf6 = ((HSSFRow) row2).getRowRecord().getXFIndex();
                if (oldXf6 < newPos.length) {
                    HSSFCellStyle newStyle2 = workbook.getCellStyleAt((int) newPos[oldXf6]);
                    row2.setRowStyle(newStyle2);
                }
            }
            for (int col2 = s6.getSheet().getMinColumnIndex(); col2 <= s6.getSheet().getMaxColumnIndex(); col2++) {
                short oldXf7 = s6.getSheet().getXFIndexForColAt((short) col2);
                if (oldXf7 < newPos.length) {
                    HSSFCellStyle newStyle3 = workbook.getCellStyleAt((int) newPos[oldXf7]);
                    s6.setDefaultColumnStyle(col2, newStyle3);
                }
            }
        }
    }

    private static boolean isUserDefined(HSSFWorkbook workbook, int index) {
        StyleRecord styleRecord = workbook.getWorkbook().getStyleRecord(index);
        return (styleRecord == null || styleRecord.isBuiltin() || styleRecord.getName() == null) ? false : true;
    }
}
