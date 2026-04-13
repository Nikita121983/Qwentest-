package org.apache.poi.xwpf.usermodel;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;

/* loaded from: classes10.dex */
public abstract class XWPFAbstractFootnotesEndnotes extends POIXMLDocumentPart {
    protected XWPFDocument document;
    private FootnoteEndnoteIdManager idManager;
    protected List<XWPFAbstractFootnoteEndnote> listFootnote;

    public XWPFAbstractFootnotesEndnotes(OPCPackage pkg) {
        super(pkg);
        this.listFootnote = new ArrayList();
    }

    public XWPFAbstractFootnotesEndnotes(OPCPackage pkg, String coreDocumentRel) {
        super(pkg, coreDocumentRel);
        this.listFootnote = new ArrayList();
    }

    public XWPFAbstractFootnotesEndnotes() {
        this.listFootnote = new ArrayList();
    }

    public XWPFAbstractFootnotesEndnotes(PackagePart part) {
        super(part);
        this.listFootnote = new ArrayList();
    }

    public XWPFAbstractFootnotesEndnotes(POIXMLDocumentPart parent, PackagePart part) {
        super(parent, part);
        this.listFootnote = new ArrayList();
    }

    public XWPFAbstractFootnoteEndnote getFootnoteById(int id) {
        for (XWPFAbstractFootnoteEndnote note : this.listFootnote) {
            if (note.getCTFtnEdn().getId() != null && note.getCTFtnEdn().getId().intValue() == id) {
                return note;
            }
        }
        return null;
    }

    public XWPFDocument getXWPFDocument() {
        if (this.document != null) {
            return this.document;
        }
        return (XWPFDocument) getParent();
    }

    public void setXWPFDocument(XWPFDocument doc) {
        this.document = doc;
    }

    public void setIdManager(FootnoteEndnoteIdManager footnoteIdManager) {
        this.idManager = footnoteIdManager;
    }

    public FootnoteEndnoteIdManager getIdManager() {
        return this.idManager;
    }
}
