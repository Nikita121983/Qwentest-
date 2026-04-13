package org.apache.poi.ooxml;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.openxml4j.opc.PackageNamespaces;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlOptions;

/* loaded from: classes10.dex */
public class POIXMLTypeLoader {
    public static final XmlOptions DEFAULT_XML_OPTIONS = new XmlOptions();
    private static final String MS_EXCEL_URN = "urn:schemas-microsoft-com:office:excel";
    private static final String MS_OFFICE_URN = "urn:schemas-microsoft-com:office:office";
    private static final String MS_VML_URN = "urn:schemas-microsoft-com:vml";
    private static final String MS_WORD_URN = "urn:schemas-microsoft-com:office:word";

    static {
        DEFAULT_XML_OPTIONS.setSaveOuter();
        DEFAULT_XML_OPTIONS.setUseDefaultNamespace();
        DEFAULT_XML_OPTIONS.setSaveAggressiveNamespaces();
        DEFAULT_XML_OPTIONS.setCharacterEncoding("UTF-8");
        DEFAULT_XML_OPTIONS.setDisallowDocTypeDeclaration(true);
        DEFAULT_XML_OPTIONS.setEntityExpansionLimit(1);
        DEFAULT_XML_OPTIONS.setLoadStripProcinsts(true);
        DEFAULT_XML_OPTIONS.setLoadStripComments(true);
        Map<String, String> map = new HashMap<>();
        map.put(XSSFRelation.NS_DRAWINGML, "a");
        map.put(XSSFRelation.NS_CHART, "c");
        map.put("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "wp");
        map.put(PackageNamespaces.MARKUP_COMPATIBILITY, "ve");
        map.put("http://schemas.openxmlformats.org/officeDocument/2006/math", "m");
        map.put(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "r");
        map.put("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "vt");
        map.put(XSSFRelation.NS_PRESENTATIONML, "p");
        map.put(XSSFRelation.NS_WORDPROCESSINGML, "w");
        map.put("http://schemas.microsoft.com/office/word/2006/wordml", "wne");
        map.put(MS_OFFICE_URN, "o");
        map.put(MS_EXCEL_URN, "x");
        map.put(MS_WORD_URN, "w10");
        map.put(MS_VML_URN, "v");
        map.put("http://schemas.microsoft.com/office/word/2010/wordml", "w14");
        map.put("http://schemas.microsoft.com/office/word/2012/wordml", "w15");
        map.put("http://schemas.microsoft.com/office/drawing/2012/chart", "c15");
        DEFAULT_XML_OPTIONS.setSaveSuggestedPrefixes(Collections.unmodifiableMap(map));
    }
}
