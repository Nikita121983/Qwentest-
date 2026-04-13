package org.apache.poi.xssf.usermodel;

import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;

/* loaded from: classes10.dex */
public class XSSFFactory extends POIXMLFactory {
    private static final XSSFFactory inst = new XSSFFactory();

    public static XSSFFactory getInstance() {
        return inst;
    }

    protected XSSFFactory() {
    }

    @Override // org.apache.poi.ooxml.POIXMLFactory
    protected POIXMLRelation getDescriptor(String relationshipType) {
        return XSSFRelation.getInstance(relationshipType);
    }
}
