package org.apache.poi.xwpf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr;

/* loaded from: classes10.dex */
public abstract class XWPFAbstractSDT implements ISDTContents {
    private final IBody part;
    private final String tag;
    private final String title;
    private final XWPFDocument xwpfDocument;

    public abstract ISDTContent getContent();

    public XWPFAbstractSDT(CTSdtPr pr, IBody part) {
        String str = "";
        this.title = (pr == null || !pr.isSetAlias()) ? "" : pr.getAlias().getVal();
        if (pr != null && pr.isSetTag()) {
            str = pr.getTag().getVal();
        }
        this.tag = str;
        this.part = part;
        this.xwpfDocument = part.getXWPFDocument();
    }

    public String getTitle() {
        return this.title;
    }

    public String getTag() {
        return this.tag;
    }

    public IBody getBody() {
        return null;
    }

    public POIXMLDocumentPart getPart() {
        return this.part.getPart();
    }

    public BodyType getPartType() {
        return BodyType.CONTENTCONTROL;
    }

    public BodyElementType getElementType() {
        return BodyElementType.CONTENTCONTROL;
    }

    public XWPFDocument getDocument() {
        return this.xwpfDocument;
    }
}
