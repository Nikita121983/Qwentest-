package org.apache.poi.xwpf;

/* loaded from: classes10.dex */
public final class XWPFParser {
    private XWPFParser() {
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.apache.poi.xwpf.usermodel.XWPFDocument parse(java.io.InputStream r3) throws org.apache.poi.xwpf.XWPFReadException, java.io.IOException {
        /*
            java.lang.String r0 = "Exception reading XWPFDocument"
            org.apache.poi.xwpf.usermodel.XWPFDocument r1 = new org.apache.poi.xwpf.usermodel.XWPFDocument     // Catch: java.lang.Exception -> L8 java.lang.RuntimeException -> Lf java.lang.Error -> L11 java.io.IOException -> L1f
            r1.<init>(r3)     // Catch: java.lang.Exception -> L8 java.lang.RuntimeException -> Lf java.lang.Error -> L11 java.io.IOException -> L1f
            return r1
        L8:
            r1 = move-exception
            org.apache.poi.xwpf.XWPFReadException r2 = new org.apache.poi.xwpf.XWPFReadException
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
            org.apache.poi.xwpf.XWPFReadException r2 = new org.apache.poi.xwpf.XWPFReadException
            r2.<init>(r0, r1)
            throw r2
        L1f:
            r0 = move-exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.XWPFParser.parse(java.io.InputStream):org.apache.poi.xwpf.usermodel.XWPFDocument");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.apache.poi.xwpf.usermodel.XWPFDocument parse(java.io.InputStream r3, boolean r4) throws org.apache.poi.xwpf.XWPFReadException, java.io.IOException {
        /*
            java.lang.String r0 = "Exception reading XWPFDocument"
            org.apache.poi.xwpf.usermodel.XWPFDocument r1 = new org.apache.poi.xwpf.usermodel.XWPFDocument     // Catch: java.lang.Exception -> L8 java.lang.RuntimeException -> Lf java.lang.Error -> L11 java.io.IOException -> L1f
            r1.<init>(r3, r4)     // Catch: java.lang.Exception -> L8 java.lang.RuntimeException -> Lf java.lang.Error -> L11 java.io.IOException -> L1f
            return r1
        L8:
            r1 = move-exception
            org.apache.poi.xwpf.XWPFReadException r2 = new org.apache.poi.xwpf.XWPFReadException
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
            org.apache.poi.xwpf.XWPFReadException r2 = new org.apache.poi.xwpf.XWPFReadException
            r2.<init>(r0, r1)
            throw r2
        L1f:
            r0 = move-exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.XWPFParser.parse(java.io.InputStream, boolean):org.apache.poi.xwpf.usermodel.XWPFDocument");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001f  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.apache.poi.xwpf.usermodel.XWPFDocument parse(java.io.File r4) throws org.apache.poi.xwpf.XWPFReadException, java.io.IOException {
        /*
            java.lang.String r0 = "Exception reading XWPFDocument"
            r1 = 0
            org.apache.poi.openxml4j.opc.OPCPackage r2 = org.apache.poi.openxml4j.opc.OPCPackage.open(r4)     // Catch: java.lang.Exception -> Le java.lang.RuntimeException -> L1a java.lang.Error -> L1c java.io.IOException -> L2f
            r1 = r2
            org.apache.poi.xwpf.usermodel.XWPFDocument r2 = new org.apache.poi.xwpf.usermodel.XWPFDocument     // Catch: java.lang.Exception -> Le java.lang.RuntimeException -> L1a java.lang.Error -> L1c java.io.IOException -> L2f
            r2.<init>(r1)     // Catch: java.lang.Exception -> Le java.lang.RuntimeException -> L1a java.lang.Error -> L1c java.io.IOException -> L2f
            return r2
        Le:
            r2 = move-exception
            if (r1 == 0) goto L14
            org.apache.poi.util.IOUtils.closeQuietly(r1)
        L14:
            org.apache.poi.xwpf.XWPFReadException r3 = new org.apache.poi.xwpf.XWPFReadException
            r3.<init>(r0, r2)
            throw r3
        L1a:
            r2 = move-exception
            goto L1d
        L1c:
            r2 = move-exception
        L1d:
            if (r1 == 0) goto L22
            org.apache.poi.util.IOUtils.closeQuietly(r1)
        L22:
            boolean r3 = org.apache.poi.util.ExceptionUtil.isFatal(r2)
            if (r3 == 0) goto L29
            throw r2
        L29:
            org.apache.poi.xwpf.XWPFReadException r3 = new org.apache.poi.xwpf.XWPFReadException
            r3.<init>(r0, r2)
            throw r3
        L2f:
            r0 = move-exception
            if (r1 == 0) goto L35
            org.apache.poi.util.IOUtils.closeQuietly(r1)
        L35:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.XWPFParser.parse(java.io.File):org.apache.poi.xwpf.usermodel.XWPFDocument");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.apache.poi.xwpf.usermodel.XWPFDocument parse(org.apache.poi.openxml4j.opc.OPCPackage r3) throws org.apache.poi.xwpf.XWPFReadException, java.io.IOException {
        /*
            java.lang.String r0 = "Exception reading XWPFDocument"
            org.apache.poi.xwpf.usermodel.XWPFDocument r1 = new org.apache.poi.xwpf.usermodel.XWPFDocument     // Catch: java.lang.Exception -> L8 java.lang.RuntimeException -> Lf java.lang.Error -> L11 java.io.IOException -> L1f
            r1.<init>(r3)     // Catch: java.lang.Exception -> L8 java.lang.RuntimeException -> Lf java.lang.Error -> L11 java.io.IOException -> L1f
            return r1
        L8:
            r1 = move-exception
            org.apache.poi.xwpf.XWPFReadException r2 = new org.apache.poi.xwpf.XWPFReadException
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
            org.apache.poi.xwpf.XWPFReadException r2 = new org.apache.poi.xwpf.XWPFReadException
            r2.<init>(r0, r1)
            throw r2
        L1f:
            r0 = move-exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.XWPFParser.parse(org.apache.poi.openxml4j.opc.OPCPackage):org.apache.poi.xwpf.usermodel.XWPFDocument");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.apache.poi.xwpf.usermodel.XWPFDocument parse(org.apache.poi.openxml4j.opc.PackagePart r5) throws org.apache.poi.xwpf.XWPFReadException, java.io.IOException {
        /*
            java.lang.String r0 = "Exception reading XWPFDocument"
            java.io.InputStream r1 = r5.getInputStream()     // Catch: java.lang.Exception -> L1f java.lang.RuntimeException -> L26 java.lang.Error -> L28 java.io.IOException -> L36
            org.apache.poi.xwpf.usermodel.XWPFDocument r2 = new org.apache.poi.xwpf.usermodel.XWPFDocument     // Catch: java.lang.Throwable -> L11
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L11
            if (r1 == 0) goto L10
            r1.close()     // Catch: java.lang.Exception -> L1f java.lang.RuntimeException -> L26 java.lang.Error -> L28 java.io.IOException -> L36
        L10:
            return r2
        L11:
            r2 = move-exception
            throw r2     // Catch: java.lang.Throwable -> L13
        L13:
            r3 = move-exception
            if (r1 == 0) goto L1e
            r1.close()     // Catch: java.lang.Throwable -> L1a
            goto L1e
        L1a:
            r4 = move-exception
            r2.addSuppressed(r4)     // Catch: java.lang.Exception -> L1f java.lang.RuntimeException -> L26 java.lang.Error -> L28 java.io.IOException -> L36
        L1e:
            throw r3     // Catch: java.lang.Exception -> L1f java.lang.RuntimeException -> L26 java.lang.Error -> L28 java.io.IOException -> L36
        L1f:
            r1 = move-exception
            org.apache.poi.xwpf.XWPFReadException r2 = new org.apache.poi.xwpf.XWPFReadException
            r2.<init>(r0, r1)
            throw r2
        L26:
            r1 = move-exception
            goto L29
        L28:
            r1 = move-exception
        L29:
            boolean r2 = org.apache.poi.util.ExceptionUtil.isFatal(r1)
            if (r2 == 0) goto L30
            throw r1
        L30:
            org.apache.poi.xwpf.XWPFReadException r2 = new org.apache.poi.xwpf.XWPFReadException
            r2.<init>(r0, r1)
            throw r2
        L36:
            r0 = move-exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.XWPFParser.parse(org.apache.poi.openxml4j.opc.PackagePart):org.apache.poi.xwpf.usermodel.XWPFDocument");
    }
}
