package org.apache.poi.xddf.usermodel.chart;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegendEntry;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

/* loaded from: classes10.dex */
public class XDDFLegendEntry implements TextContainer {
    private CTLegendEntry entry;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFLegendEntry(CTLegendEntry entry) {
        this.entry = entry;
        if (entry.getIdx() == null) {
            entry.addNewIdx().setVal(0L);
        }
    }

    @Internal
    protected CTLegendEntry getXmlObject() {
        return this.entry;
    }

    public XDDFTextBody getTextBody() {
        if (this.entry.isSetTxPr()) {
            return new XDDFTextBody(this, this.entry.getTxPr());
        }
        return null;
    }

    public void setTextBody(XDDFTextBody body) {
        if (body == null) {
            if (this.entry.isSetTxPr()) {
                this.entry.unsetTxPr();
                return;
            }
            return;
        }
        this.entry.setTxPr(body.getXmlObject());
    }

    public boolean getDelete() {
        if (this.entry.isSetDelete()) {
            return this.entry.getDelete().getVal();
        }
        return false;
    }

    public void setDelete(Boolean delete) {
        if (delete == null) {
            if (this.entry.isSetDelete()) {
                this.entry.unsetDelete();
            }
        } else if (this.entry.isSetDelete()) {
            this.entry.getDelete().setVal(delete.booleanValue());
        } else {
            this.entry.addNewDelete().setVal(delete.booleanValue());
        }
    }

    public long getIndex() {
        return this.entry.getIdx().getVal();
    }

    public void setIndex(long index) {
        this.entry.getIdx().setVal(index);
    }

    public void setExtensionList(XDDFChartExtensionList list) {
        if (list == null) {
            if (this.entry.isSetExtLst()) {
                this.entry.unsetExtLst();
                return;
            }
            return;
        }
        this.entry.setExtLst(list.getXmlObject());
    }

    public XDDFChartExtensionList getExtensionList() {
        if (this.entry.isSetExtLst()) {
            return new XDDFChartExtensionList(this.entry.getExtLst());
        }
        return null;
    }

    @Override // org.apache.poi.xddf.usermodel.text.TextContainer
    public <R> Optional<R> findDefinedParagraphProperty(Predicate<CTTextParagraphProperties> isSet, Function<CTTextParagraphProperties, R> getter) {
        return Optional.empty();
    }

    @Override // org.apache.poi.xddf.usermodel.text.TextContainer
    public <R> Optional<R> findDefinedRunProperty(Predicate<CTTextCharacterProperties> isSet, Function<CTTextCharacterProperties, R> getter) {
        return Optional.empty();
    }
}
