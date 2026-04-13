package org.apache.poi.xwpf.usermodel;

import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;

/* loaded from: classes10.dex */
public final class XWPFFactory extends POIXMLFactory {
    private static final XWPFFactory inst = new XWPFFactory();

    public static XWPFFactory getInstance() {
        return inst;
    }

    private XWPFFactory() {
    }

    @Override // org.apache.poi.ooxml.POIXMLFactory
    protected POIXMLRelation getDescriptor(String relationshipType) {
        return XWPFRelation.getInstance(relationshipType);
    }
}
