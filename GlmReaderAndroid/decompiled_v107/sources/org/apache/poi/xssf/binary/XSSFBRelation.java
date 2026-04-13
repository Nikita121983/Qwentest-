package org.apache.poi.xssf.binary;

import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public class XSSFBRelation extends POIXMLRelation {
    static final XSSFBRelation SHARED_STRINGS_BINARY = new XSSFBRelation("application/vnd.ms-excel.sharedStrings", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/sharedStrings", "/xl/sharedStrings.bin");
    public static final XSSFBRelation STYLES_BINARY = new XSSFBRelation("application/vnd.ms-excel.styles", PackageRelationshipTypes.STYLE_PART, "/xl/styles.bin");

    private XSSFBRelation(String type, String rel, String defaultName) {
        super(type, rel, defaultName);
    }
}
