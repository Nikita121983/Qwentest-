package org.apache.poi.openxml4j.opc;

/* loaded from: classes10.dex */
public final class ContentTypes {
    public static final String CORE_PROPERTIES_PART = "application/vnd.openxmlformats-package.core-properties+xml";
    public static final String CUSTOM_XML_PART = "application/vnd.openxmlformats-officedocument.customXmlProperties+xml";
    public static final String DIGITAL_SIGNATURE_CERTIFICATE_PART = "application/vnd.openxmlformats-package.digital-signature-certificate";
    public static final String DIGITAL_SIGNATURE_ORIGIN_PART = "application/vnd.openxmlformats-package.digital-signature-origin";
    public static final String DIGITAL_SIGNATURE_XML_SIGNATURE_PART = "application/vnd.openxmlformats-package.digital-signature-xmlsignature+xml";
    public static final String EXTENSION_GIF = "gif";
    public static final String EXTENSION_JPG_1 = "jpg";
    public static final String EXTENSION_JPG_2 = "jpeg";
    public static final String EXTENSION_PICT = "pict";
    public static final String EXTENSION_PNG = "png";
    public static final String EXTENSION_TIFF = "tiff";
    public static final String EXTENSION_XML = "xml";
    public static final String IMAGE_GIF = "image/gif";
    public static final String IMAGE_JPEG = "image/jpeg";
    public static final String IMAGE_PICT = "image/x-pict";
    public static final String IMAGE_PNG = "image/png";
    public static final String IMAGE_TIFF = "image/tiff";
    public static final String PLAIN_OLD_XML = "application/xml";
    public static final String RELATIONSHIPS_PART = "application/vnd.openxmlformats-package.relationships+xml";
    public static final String XML = "text/xml";

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0034, code lost:
    
        if (r0.equals(org.apache.poi.openxml4j.opc.ContentTypes.EXTENSION_JPG_2) != false) goto L27;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getContentTypeFromFileExtension(java.lang.String r3) {
        /*
            r0 = 46
            int r0 = r3.lastIndexOf(r0)
            r1 = 1
            int r0 = r0 + r1
            java.lang.String r0 = r3.substring(r0)
            java.util.Locale r2 = java.util.Locale.ROOT
            java.lang.String r0 = r0.toLowerCase(r2)
            int r2 = r0.hashCode()
            switch(r2) {
                case 102340: goto L55;
                case 105441: goto L4b;
                case 111145: goto L41;
                case 118807: goto L37;
                case 3268712: goto L2e;
                case 3440682: goto L24;
                case 3559925: goto L1a;
                default: goto L19;
            }
        L19:
            goto L5f
        L1a:
            java.lang.String r1 = "tiff"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L19
            r1 = 5
            goto L60
        L24:
            java.lang.String r1 = "pict"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L19
            r1 = 3
            goto L60
        L2e:
            java.lang.String r2 = "jpeg"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L19
            goto L60
        L37:
            java.lang.String r1 = "xml"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L19
            r1 = 6
            goto L60
        L41:
            java.lang.String r1 = "png"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L19
            r1 = 4
            goto L60
        L4b:
            java.lang.String r1 = "jpg"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L19
            r1 = 0
            goto L60
        L55:
            java.lang.String r1 = "gif"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L19
            r1 = 2
            goto L60
        L5f:
            r1 = -1
        L60:
            switch(r1) {
                case 0: goto L74;
                case 1: goto L74;
                case 2: goto L71;
                case 3: goto L6e;
                case 4: goto L6b;
                case 5: goto L68;
                case 6: goto L65;
                default: goto L63;
            }
        L63:
            r1 = 0
            return r1
        L65:
            java.lang.String r1 = "text/xml"
            return r1
        L68:
            java.lang.String r1 = "image/tiff"
            return r1
        L6b:
            java.lang.String r1 = "image/png"
            return r1
        L6e:
            java.lang.String r1 = "image/x-pict"
            return r1
        L71:
            java.lang.String r1 = "image/gif"
            return r1
        L74:
            java.lang.String r1 = "image/jpeg"
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.ContentTypes.getContentTypeFromFileExtension(java.lang.String):java.lang.String");
    }
}
