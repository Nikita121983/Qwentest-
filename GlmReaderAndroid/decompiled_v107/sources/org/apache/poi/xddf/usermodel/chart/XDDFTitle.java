package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTx;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

/* loaded from: classes10.dex */
public class XDDFTitle {
    private final TextContainer parent;
    private final CTTitle title;

    public XDDFTitle(TextContainer parent, CTTitle title) {
        this.parent = parent;
        this.title = title;
    }

    public XDDFTextBody getBody() {
        if (!this.title.isSetTx()) {
            this.title.addNewTx();
        }
        CTTx tx = this.title.getTx();
        if (tx.isSetStrRef()) {
            tx.unsetStrRef();
        }
        if (!tx.isSetRich()) {
            tx.addNewRich();
        }
        return new XDDFTextBody(this.parent, tx.getRich());
    }

    public void setText(String text) {
        if (text == null) {
            if (this.title.isSetTx()) {
                this.title.unsetTx();
            }
        } else {
            if (!this.title.isSetLayout()) {
                this.title.addNewLayout();
            }
            getBody().setText(text);
        }
    }

    public void setOverlay(Boolean overlay) {
        if (overlay == null) {
            if (this.title.isSetOverlay()) {
                this.title.unsetOverlay();
            }
        } else if (this.title.isSetOverlay()) {
            this.title.getOverlay().setVal(overlay.booleanValue());
        } else {
            this.title.addNewOverlay().setVal(overlay.booleanValue());
        }
    }

    public XDDFRunProperties getOrAddTextProperties() {
        CTTextBody text;
        if (this.title.isSetTxPr()) {
            text = this.title.getTxPr();
        } else {
            text = this.title.addNewTxPr();
        }
        return new XDDFRunProperties(getOrAddTextProperties(text));
    }

    private CTTextCharacterProperties getOrAddTextProperties(CTTextBody body) {
        CTTextParagraph paragraph;
        CTTextParagraphProperties paraprops;
        if (body.getBodyPr() == null) {
            body.addNewBodyPr();
        }
        if (body.sizeOfPArray() > 0) {
            paragraph = body.getPArray(0);
        } else {
            paragraph = body.addNewP();
        }
        if (paragraph.isSetPPr()) {
            paraprops = paragraph.getPPr();
        } else {
            paraprops = paragraph.addNewPPr();
        }
        if (paraprops.isSetDefRPr()) {
            CTTextCharacterProperties properties = paraprops.getDefRPr();
            return properties;
        }
        CTTextCharacterProperties properties2 = paraprops.addNewDefRPr();
        return properties2;
    }
}
