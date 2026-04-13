package org.apache.poi.xddf.usermodel;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDashStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;

/* loaded from: classes10.dex */
public class XDDFLineProperties {
    private CTLineProperties props;

    public XDDFLineProperties() {
        this(CTLineProperties.Factory.newInstance());
    }

    public XDDFLineProperties(XDDFFillProperties fill) {
        this();
        setFillProperties(fill);
    }

    @Internal
    public XDDFLineProperties(CTLineProperties properties) {
        this.props = properties;
    }

    @Internal
    public CTLineProperties getXmlObject() {
        return this.props;
    }

    public PenAlignment getPenAlignment() {
        if (this.props.isSetAlgn()) {
            return PenAlignment.valueOf(this.props.getAlgn());
        }
        return null;
    }

    public void setPenAlignment(PenAlignment alignment) {
        if (alignment == null) {
            if (this.props.isSetAlgn()) {
                this.props.unsetAlgn();
                return;
            }
            return;
        }
        this.props.setAlgn(alignment.underlying);
    }

    public LineCap getLineCap() {
        if (this.props.isSetCap()) {
            return LineCap.valueOf(this.props.getCap());
        }
        return null;
    }

    public void setLineCap(LineCap cap) {
        if (cap == null) {
            if (this.props.isSetCap()) {
                this.props.unsetCap();
                return;
            }
            return;
        }
        this.props.setCap(cap.underlying);
    }

    public CompoundLine getCompoundLine() {
        if (this.props.isSetCmpd()) {
            return CompoundLine.valueOf(this.props.getCmpd());
        }
        return null;
    }

    public void setCompoundLine(CompoundLine compound) {
        if (compound == null) {
            if (this.props.isSetCmpd()) {
                this.props.unsetCmpd();
                return;
            }
            return;
        }
        this.props.setCmpd(compound.underlying);
    }

    public XDDFDashStop addDashStop() {
        if (!this.props.isSetCustDash()) {
            this.props.addNewCustDash();
        }
        return new XDDFDashStop(this.props.getCustDash().addNewDs());
    }

    public XDDFDashStop insertDashStop(int index) {
        if (!this.props.isSetCustDash()) {
            this.props.addNewCustDash();
        }
        return new XDDFDashStop(this.props.getCustDash().insertNewDs(index));
    }

    public void removeDashStop(int index) {
        if (this.props.isSetCustDash()) {
            this.props.getCustDash().removeDs(index);
        }
    }

    public XDDFDashStop getDashStop(int index) {
        if (this.props.isSetCustDash()) {
            return new XDDFDashStop(this.props.getCustDash().getDsArray(index));
        }
        return null;
    }

    public List<XDDFDashStop> getDashStops() {
        if (this.props.isSetCustDash()) {
            return Collections.unmodifiableList((List) this.props.getCustDash().getDsList().stream().map(new Function() { // from class: org.apache.poi.xddf.usermodel.XDDFLineProperties$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return XDDFLineProperties.lambda$getDashStops$0((CTDashStop) obj);
                }
            }).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XDDFDashStop lambda$getDashStops$0(CTDashStop ds) {
        return new XDDFDashStop(ds);
    }

    public int countDashStops() {
        if (this.props.isSetCustDash()) {
            return this.props.getCustDash().sizeOfDsArray();
        }
        return 0;
    }

    public XDDFPresetLineDash getPresetDash() {
        if (this.props.isSetPrstDash()) {
            return new XDDFPresetLineDash(this.props.getPrstDash());
        }
        return null;
    }

    public void setPresetDash(XDDFPresetLineDash properties) {
        if (properties == null) {
            if (this.props.isSetPrstDash()) {
                this.props.unsetPrstDash();
                return;
            }
            return;
        }
        this.props.setPrstDash(properties.getXmlObject());
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

    public XDDFFillProperties getFillProperties() {
        if (this.props.isSetGradFill()) {
            return new XDDFGradientFillProperties(this.props.getGradFill());
        }
        if (this.props.isSetNoFill()) {
            return new XDDFNoFillProperties(this.props.getNoFill());
        }
        if (this.props.isSetPattFill()) {
            return new XDDFPatternFillProperties(this.props.getPattFill());
        }
        if (this.props.isSetSolidFill()) {
            return new XDDFSolidFillProperties(this.props.getSolidFill());
        }
        return null;
    }

    public void setFillProperties(XDDFFillProperties properties) {
        if (this.props.isSetGradFill()) {
            this.props.unsetGradFill();
        }
        if (this.props.isSetNoFill()) {
            this.props.unsetNoFill();
        }
        if (this.props.isSetPattFill()) {
            this.props.unsetPattFill();
        }
        if (this.props.isSetSolidFill()) {
            this.props.unsetSolidFill();
        }
        if (properties == null) {
            return;
        }
        if (properties instanceof XDDFGradientFillProperties) {
            this.props.setGradFill(((XDDFGradientFillProperties) properties).getXmlObject());
            return;
        }
        if (properties instanceof XDDFNoFillProperties) {
            this.props.setNoFill(((XDDFNoFillProperties) properties).getXmlObject());
        } else if (properties instanceof XDDFPatternFillProperties) {
            this.props.setPattFill(((XDDFPatternFillProperties) properties).getXmlObject());
        } else if (properties instanceof XDDFSolidFillProperties) {
            this.props.setSolidFill(((XDDFSolidFillProperties) properties).getXmlObject());
        }
    }

    public XDDFLineJoinProperties getLineJoinProperties() {
        if (this.props.isSetBevel()) {
            return new XDDFLineJoinBevelProperties(this.props.getBevel());
        }
        if (this.props.isSetMiter()) {
            return new XDDFLineJoinMiterProperties(this.props.getMiter());
        }
        if (this.props.isSetRound()) {
            return new XDDFLineJoinRoundProperties(this.props.getRound());
        }
        return null;
    }

    public void setLineJoinProperties(XDDFLineJoinProperties properties) {
        if (this.props.isSetBevel()) {
            this.props.unsetBevel();
        }
        if (this.props.isSetMiter()) {
            this.props.unsetMiter();
        }
        if (this.props.isSetRound()) {
            this.props.unsetRound();
        }
        if (properties == null) {
            return;
        }
        if (properties instanceof XDDFLineJoinBevelProperties) {
            this.props.setBevel(((XDDFLineJoinBevelProperties) properties).getXmlObject());
        } else if (properties instanceof XDDFLineJoinMiterProperties) {
            this.props.setMiter(((XDDFLineJoinMiterProperties) properties).getXmlObject());
        } else if (properties instanceof XDDFLineJoinRoundProperties) {
            this.props.setRound(((XDDFLineJoinRoundProperties) properties).getXmlObject());
        }
    }

    public XDDFLineEndProperties getHeadEnd() {
        if (this.props.isSetHeadEnd()) {
            return new XDDFLineEndProperties(this.props.getHeadEnd());
        }
        return null;
    }

    public void setHeadEnd(XDDFLineEndProperties properties) {
        if (properties == null) {
            if (this.props.isSetHeadEnd()) {
                this.props.unsetHeadEnd();
                return;
            }
            return;
        }
        this.props.setHeadEnd(properties.getXmlObject());
    }

    public XDDFLineEndProperties getTailEnd() {
        if (this.props.isSetTailEnd()) {
            return new XDDFLineEndProperties(this.props.getTailEnd());
        }
        return null;
    }

    public void setTailEnd(XDDFLineEndProperties properties) {
        if (properties == null) {
            if (this.props.isSetTailEnd()) {
                this.props.unsetTailEnd();
                return;
            }
            return;
        }
        this.props.setTailEnd(properties.getXmlObject());
    }

    public Double getWidth() {
        if (this.props.isSetW()) {
            return Double.valueOf(Units.toPoints(this.props.getW()));
        }
        return null;
    }

    public void setWidth(Double width) {
        if (width == null) {
            if (this.props.isSetW()) {
                this.props.unsetW();
                return;
            }
            return;
        }
        this.props.setW(Units.toEMU(width.doubleValue()));
    }
}
