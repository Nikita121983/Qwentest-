package org.apache.xmlbeans.impl.tool;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.XmlError;

/* loaded from: classes11.dex */
public class StreamInstanceValidator {
    public static void printUsage() {
        System.out.println("Validates the specified instance against the specified schema.");
        System.out.println("A streaming validation useful for validating very large instance ");
        System.out.println("documents with less memory. Contrast with the validate tool.");
        System.out.println("Usage: svalidate [-dl] [-nopvr] [-noupa] [-license] schema.xsd instance.xml");
        System.out.println("Options:");
        System.out.println("    -dl - permit network downloads for imports and includes (default is off)");
        System.out.println("    -noupa - do not enforce the unique particle attribution rule");
        System.out.println("    -nopvr - do not enforce the particle valid (restriction) rule");
        System.out.println("    -license - prints license information");
    }

    /* JADX WARN: Code restructure failed: missing block: B:78:0x016f, code lost:
    
        if ((r0 instanceof org.apache.xmlbeans.XmlException) != false) goto L77;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void main(java.lang.String[] r19) {
        /*
            Method dump skipped, instructions count: 427
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.tool.StreamInstanceValidator.main(java.lang.String[]):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void validateFiles(java.io.File[] r16, org.apache.xmlbeans.SchemaTypeLoader r17, org.apache.xmlbeans.XmlOptions r18) {
        /*
            Method dump skipped, instructions count: 320
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.tool.StreamInstanceValidator.validateFiles(java.io.File[], org.apache.xmlbeans.SchemaTypeLoader, org.apache.xmlbeans.XmlOptions):void");
    }

    private static String stringFromError(XmlError err, String path) {
        String s = XmlError.severityAsString(err.getSeverity()) + ": " + path + ":" + err.getLine() + ":" + err.getColumn() + StringUtils.SPACE + err.getMessage() + StringUtils.SPACE;
        return s;
    }
}
