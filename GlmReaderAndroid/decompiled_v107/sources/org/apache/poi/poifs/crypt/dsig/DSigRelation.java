package org.apache.poi.poifs.crypt.dsig;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;

/* loaded from: classes10.dex */
public class DSigRelation extends POIXMLRelation {
    private static final Map<String, DSigRelation> _table = new HashMap();
    public static final DSigRelation ORIGIN_SIGS = new DSigRelation(ContentTypes.DIGITAL_SIGNATURE_ORIGIN_PART, PackageRelationshipTypes.DIGITAL_SIGNATURE_ORIGIN, "/_xmlsignatures/origin.sigs");
    public static final DSigRelation SIG = new DSigRelation(ContentTypes.DIGITAL_SIGNATURE_XML_SIGNATURE_PART, PackageRelationshipTypes.DIGITAL_SIGNATURE, "/_xmlsignatures/sig#.xml");

    private DSigRelation(String type, String rel, String defaultName) {
        super(type, rel, defaultName);
        _table.put(rel, this);
    }

    public static DSigRelation getInstance(String rel) {
        return _table.get(rel);
    }
}
