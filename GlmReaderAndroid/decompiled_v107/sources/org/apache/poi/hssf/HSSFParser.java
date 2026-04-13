package org.apache.poi.hssf;

/* loaded from: classes10.dex */
public final class HSSFParser {
    private HSSFParser() {
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.apache.poi.hssf.usermodel.HSSFWorkbook parse(java.io.InputStream r3) throws org.apache.poi.hssf.HSSFReadException, java.io.IOException {
        /*
            java.lang.String r0 = "Exception reading HSSFWorkbook"
            org.apache.poi.hssf.usermodel.HSSFWorkbook r1 = new org.apache.poi.hssf.usermodel.HSSFWorkbook     // Catch: java.lang.Exception -> L8 java.lang.RuntimeException -> Lf java.lang.Error -> L11 java.io.IOException -> L1f
            r1.<init>(r3)     // Catch: java.lang.Exception -> L8 java.lang.RuntimeException -> Lf java.lang.Error -> L11 java.io.IOException -> L1f
            return r1
        L8:
            r1 = move-exception
            org.apache.poi.hssf.HSSFReadException r2 = new org.apache.poi.hssf.HSSFReadException
            r2.<init>(r0, r1)
            throw r2
        Lf:
            r1 = move-exception
            goto L12
        L11:
            r1 = move-exception
        L12:
            boolean r2 = org.apache.poi.util.ExceptionUtil.isFatal(r1)
            if (r2 == 0) goto L19
            throw r1
        L19:
            org.apache.poi.hssf.HSSFReadException r2 = new org.apache.poi.hssf.HSSFReadException
            r2.<init>(r0, r1)
            throw r2
        L1f:
            r0 = move-exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.HSSFParser.parse(java.io.InputStream):org.apache.poi.hssf.usermodel.HSSFWorkbook");
    }
}
