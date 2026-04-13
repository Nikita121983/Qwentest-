package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.XDDFExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties;

/* loaded from: classes10.dex */
public class XDDFBodyProperties {
    private CTTextBodyProperties props;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFBodyProperties(CTTextBodyProperties properties) {
        this.props = properties;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTTextBodyProperties getXmlObject() {
        return this.props;
    }

    public AnchorType getAnchoring() {
        if (this.props.isSetAnchor()) {
            return AnchorType.valueOf(this.props.getAnchor());
        }
        return null;
    }

    public void setAnchoring(AnchorType anchor) {
        if (anchor == null) {
            if (this.props.isSetAnchor()) {
                this.props.unsetAnchor();
                return;
            }
            return;
        }
        this.props.setAnchor(anchor.underlying);
    }

    public Boolean isAnchorCentered() {
        if (this.props.isSetAnchorCtr()) {
            return Boolean.valueOf(this.props.getAnchorCtr());
        }
        return false;
    }

    public void setAnchorCentered(Boolean centered) {
        if (centered == null) {
            if (this.props.isSetAnchorCtr()) {
                this.props.unsetAnchorCtr();
                return;
            }
            return;
        }
        this.props.setAnchorCtr(centered.booleanValue());
    }

    public XDDFAutoFit getAutoFit() {
        if (this.props.isSetNoAutofit()) {
            return new XDDFNoAutoFit(this.props.getNoAutofit());
        }
        if (this.props.isSetNormAutofit()) {
            return new XDDFNormalAutoFit(this.props.getNormAutofit());
        }
        if (this.props.isSetSpAutoFit()) {
            return new XDDFShapeAutoFit(this.props.getSpAutoFit());
        }
        return new XDDFNormalAutoFit();
    }

    public void setAutoFit(XDDFAutoFit autofit) {
        if (this.props.isSetNoAutofit()) {
            this.props.unsetNoAutofit();
        }
        if (this.props.isSetNormAutofit()) {
            this.props.unsetNormAutofit();
        }
        if (this.props.isSetSpAutoFit()) {
            this.props.unsetSpAutoFit();
        }
        if (autofit instanceof XDDFNoAutoFit) {
            this.props.setNoAutofit(((XDDFNoAutoFit) autofit).getXmlObject());
        } else if (autofit instanceof XDDFNormalAutoFit) {
            this.props.setNormAutofit(((XDDFNormalAutoFit) autofit).getXmlObject());
        } else if (autofit instanceof XDDFShapeAutoFit) {
            this.props.setSpAutoFit(((XDDFShapeAutoFit) autofit).getXmlObject());
        }
    }

    public XDDFExtensionList getExtensionList() {
        if (this.props.isSetExtLst()) {
            return new XDDFExtensionList(this.props.getExtLst());
        }
        return null;
    }

    public void setExtensionList(XDDFExtensionList list) {
        if (list == null) {
            if (this.props.isSetExtLst()) {
                this.props.unsetExtLst();
                return;
            }
            return;
        }
        this.props.setExtLst(list.getXmlObject());
    }

    public Double getBottomInset() {
        if (this.props.isSetBIns()) {
            return Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(this.props.xgetBIns())));
        }
        return null;
    }

    public void setBottomInset(Double points) {
        if (points == null || Double.isNaN(points.doubleValue())) {
            if (this.props.isSetBIns()) {
                this.props.unsetBIns();
                return;
            }
            return;
        }
        this.props.setBIns(Integer.valueOf(Units.toEMU(points.doubleValue())));
    }

    public Double getLeftInset() {
        if (this.props.isSetLIns()) {
            return Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(this.props.xgetLIns())));
        }
        return null;
    }

    public void setLeftInset(Double points) {
        if (points == null || Double.isNaN(points.doubleValue())) {
            if (this.props.isSetLIns()) {
                this.props.unsetLIns();
                return;
            }
            return;
        }
        this.props.setLIns(Integer.valueOf(Units.toEMU(points.doubleValue())));
    }

    public Double getRightInset() {
        if (this.props.isSetRIns()) {
            return Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(this.props.xgetRIns())));
        }
        return null;
    }

    public void setRightInset(Double points) {
        if (points == null || Double.isNaN(points.doubleValue())) {
            if (this.props.isSetRIns()) {
                this.props.unsetRIns();
                return;
            }
            return;
        }
        this.props.setRIns(Integer.valueOf(Units.toEMU(points.doubleValue())));
    }

    public Double getTopInset() {
        if (this.props.isSetTIns()) {
            return Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(this.props.xgetTIns())));
        }
        return null;
    }

    public void setTopInset(Double points) {
        if (points == null || Double.isNaN(points.doubleValue())) {
            if (this.props.isSetTIns()) {
                this.props.unsetTIns();
                return;
            }
            return;
        }
        this.props.setTIns(Integer.valueOf(Units.toEMU(points.doubleValue())));
    }

    public Boolean hasParagraphSpacing() {
        if (this.props.isSetSpcFirstLastPara()) {
            return Boolean.valueOf(this.props.getSpcFirstLastPara());
        }
        return null;
    }

    public void setParagraphSpacing(Boolean spacing) {
        if (spacing == null) {
            if (this.props.isSetSpcFirstLastPara()) {
                this.props.unsetSpcFirstLastPara();
                return;
            }
            return;
        }
        this.props.setSpcFirstLastPara(spacing.booleanValue());
    }

    public Boolean isRightToLeft() {
        if (this.props.isSetRtlCol()) {
            return Boolean.valueOf(this.props.getRtlCol());
        }
        return false;
    }

    public void setRightToLeft(Boolean rightToLeft) {
        if (rightToLeft == null) {
            if (this.props.isSetRtlCol()) {
                this.props.unsetRtlCol();
                return;
            }
            return;
        }
        this.props.setRtlCol(rightToLeft.booleanValue());
    }
}
