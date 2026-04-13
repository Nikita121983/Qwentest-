package org.apache.poi.xdgf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xdgf.xml.XDGFXMLDocumentPart;

/* loaded from: classes10.dex */
public class XDGFFactory extends POIXMLFactory {
    private final XDGFDocument document;

    public XDGFFactory(XDGFDocument document) {
        this.document = document;
    }

    @Override // org.apache.poi.ooxml.POIXMLFactory
    protected POIXMLRelation getDescriptor(String relationshipType) {
        return XDGFRelation.getInstance(relationshipType);
    }

    @Override // org.apache.poi.ooxml.POIXMLFactory
    public POIXMLDocumentPart createDocumentPart(POIXMLDocumentPart parent, PackagePart part) {
        POIXMLDocumentPart newPart = super.createDocumentPart(parent, part);
        if (newPart instanceof XDGFXMLDocumentPart) {
            ((XDGFXMLDocumentPart) newPart).setDocument(this.document);
        }
        return newPart;
    }

    @Override // org.apache.poi.ooxml.POIXMLFactory
    public POIXMLDocumentPart newDocumentPart(POIXMLRelation descriptor) {
        POIXMLDocumentPart newPart = super.newDocumentPart(descriptor);
        if (newPart instanceof XDGFXMLDocumentPart) {
            ((XDGFXMLDocumentPart) newPart).setDocument(this.document);
        }
        return newPart;
    }
}
