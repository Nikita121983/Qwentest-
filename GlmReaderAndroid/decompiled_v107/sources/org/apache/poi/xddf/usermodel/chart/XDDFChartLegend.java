package org.apache.poi.xddf.usermodel.chart;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegend;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegendEntry;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

/* loaded from: classes10.dex */
public final class XDDFChartLegend implements TextContainer {
    private CTLegend legend;

    public XDDFChartLegend(CTChart ctChart) {
        this.legend = ctChart.isSetLegend() ? ctChart.getLegend() : ctChart.addNewLegend();
        setDefaults();
    }

    private void setDefaults() {
        if (!this.legend.isSetOverlay()) {
            this.legend.addNewOverlay();
        }
        this.legend.getOverlay().setVal(false);
    }

    @Internal
    protected CTLegend getXmlObject() {
        return this.legend;
    }

    @Internal
    public CTShapeProperties getShapeProperties() {
        if (this.legend.isSetSpPr()) {
            return this.legend.getSpPr();
        }
        return null;
    }

    @Internal
    public void setShapeProperties(CTShapeProperties properties) {
        if (properties == null) {
            if (this.legend.isSetSpPr()) {
                this.legend.unsetSpPr();
                return;
            }
            return;
        }
        this.legend.setSpPr(properties);
    }

    public XDDFTextBody getTextBody() {
        if (this.legend.isSetTxPr()) {
            return new XDDFTextBody(this, this.legend.getTxPr());
        }
        return null;
    }

    public void setTextBody(XDDFTextBody body) {
        if (body == null) {
            if (this.legend.isSetTxPr()) {
                this.legend.unsetTxPr();
                return;
            }
            return;
        }
        this.legend.setTxPr(body.getXmlObject());
    }

    public XDDFLegendEntry addEntry() {
        return new XDDFLegendEntry(this.legend.addNewLegendEntry());
    }

    public XDDFLegendEntry getEntry(int index) {
        return new XDDFLegendEntry(this.legend.getLegendEntryArray(index));
    }

    public List<XDDFLegendEntry> getEntries() {
        return (List) this.legend.getLegendEntryList().stream().map(new Function() { // from class: org.apache.poi.xddf.usermodel.chart.XDDFChartLegend$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XDDFChartLegend.lambda$getEntries$0((CTLegendEntry) obj);
            }
        }).collect(Collectors.toList());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XDDFLegendEntry lambda$getEntries$0(CTLegendEntry entry) {
        return new XDDFLegendEntry(entry);
    }

    public void setExtensionList(XDDFChartExtensionList list) {
        if (list == null) {
            if (this.legend.isSetExtLst()) {
                this.legend.unsetExtLst();
                return;
            }
            return;
        }
        this.legend.setExtLst(list.getXmlObject());
    }

    public XDDFChartExtensionList getExtensionList() {
        if (this.legend.isSetExtLst()) {
            return new XDDFChartExtensionList(this.legend.getExtLst());
        }
        return null;
    }

    public void setLayout(XDDFLayout layout) {
        if (layout == null) {
            if (this.legend.isSetLayout()) {
                this.legend.unsetLayout();
                return;
            }
            return;
        }
        this.legend.setLayout(layout.getXmlObject());
    }

    public XDDFLayout getLayout() {
        if (this.legend.isSetLayout()) {
            return new XDDFLayout(this.legend.getLayout());
        }
        return null;
    }

    public void setPosition(LegendPosition position) {
        if (!this.legend.isSetLegendPos()) {
            this.legend.addNewLegendPos();
        }
        this.legend.getLegendPos().setVal(position.underlying);
    }

    public LegendPosition getPosition() {
        if (this.legend.isSetLegendPos()) {
            return LegendPosition.valueOf(this.legend.getLegendPos().getVal());
        }
        return LegendPosition.RIGHT;
    }

    public XDDFManualLayout getOrAddManualLayout() {
        if (!this.legend.isSetLayout()) {
            this.legend.addNewLayout();
        }
        return new XDDFManualLayout(this.legend.getLayout());
    }

    public boolean isOverlay() {
        return this.legend.getOverlay().getVal();
    }

    public void setOverlay(boolean value) {
        this.legend.getOverlay().setVal(value);
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
