package org.apache.poi.xslf.usermodel;

import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;

/* loaded from: classes10.dex */
public final class XSLFFactory extends POIXMLFactory {
    private static final XSLFFactory inst = new XSLFFactory();

    public static XSLFFactory getInstance() {
        return inst;
    }

    private XSLFFactory() {
    }

    @Override // org.apache.poi.ooxml.POIXMLFactory
    protected POIXMLRelation getDescriptor(String relationshipType) {
        return XSLFRelation.getInstance(relationshipType);
    }
}
