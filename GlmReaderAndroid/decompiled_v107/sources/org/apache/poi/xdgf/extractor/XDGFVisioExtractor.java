package org.apache.poi.xdgf.extractor;

import java.io.IOException;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xdgf.usermodel.XDGFPage;
import org.apache.poi.xdgf.usermodel.XmlVisioDocument;
import org.apache.poi.xdgf.usermodel.shape.ShapeTextVisitor;

/* loaded from: classes10.dex */
public class XDGFVisioExtractor implements POIXMLTextExtractor {
    private boolean doCloseFilesystem;
    protected final XmlVisioDocument document;

    public XDGFVisioExtractor(XmlVisioDocument document) {
        this.doCloseFilesystem = true;
        this.document = document;
    }

    public XDGFVisioExtractor(OPCPackage openPackage) throws IOException {
        this(new XmlVisioDocument(openPackage));
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public String getText() {
        ShapeTextVisitor visitor = new ShapeTextVisitor();
        for (XDGFPage page : this.document.getPages()) {
            page.getContent().visitShapes(visitor);
        }
        return visitor.getText();
    }

    @Override // org.apache.poi.ooxml.extractor.POIXMLTextExtractor, org.apache.poi.extractor.POITextExtractor
    public XmlVisioDocument getDocument() {
        return this.document;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public void setCloseFilesystem(boolean doCloseFilesystem) {
        this.doCloseFilesystem = doCloseFilesystem;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public boolean isCloseFilesystem() {
        return this.doCloseFilesystem;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public XmlVisioDocument getFilesystem() {
        return this.document;
    }
}
