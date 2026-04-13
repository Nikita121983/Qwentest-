package org.apache.poi.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;

/* loaded from: classes10.dex */
public final class DrawingDump {
    private DrawingDump() {
    }

    public static void main(String[] args) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(System.out, Charset.defaultCharset());
        try {
            PrintWriter pw = new PrintWriter(osw);
            try {
                POIFSFileSystem fs = new POIFSFileSystem(new File(args[0]));
                try {
                    HSSFWorkbook wb = new HSSFWorkbook(fs);
                    try {
                        pw.println("Drawing group:");
                        wb.dumpDrawingGroupRecords(true);
                        Iterator<Sheet> it = wb.iterator();
                        while (it.hasNext()) {
                            Sheet sheet = it.next();
                            pw.println("Sheet 1(" + sheet.getSheetName() + "):");
                            ((HSSFSheet) sheet).dumpDrawingRecords(true, pw);
                        }
                        wb.close();
                        fs.close();
                        pw.close();
                        osw.close();
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    osw.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }
}
