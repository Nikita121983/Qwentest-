package org.apache.poi;

/* loaded from: classes10.dex */
public class EmptyFileException extends IllegalArgumentException {
    private static final long serialVersionUID = 1536449292174360166L;

    public EmptyFileException() {
        super("The supplied file was empty (zero bytes long)");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public EmptyFileException(java.io.File r3) {
        /*
            r2 = this;
            boolean r0 = r3.exists()
            if (r0 == 0) goto L24
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "The supplied file '"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = r3.getAbsolutePath()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = "' was empty (zero bytes long)"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            goto L41
        L24:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "The file '"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = r3.getAbsolutePath()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = "' does not exist"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
        L41:
            r2.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.EmptyFileException.<init>(java.io.File):void");
    }
}
