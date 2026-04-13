package org.apache.poi.xddf.usermodel.text;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.XDDFExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop;

/* loaded from: classes10.dex */
public class XDDFParagraphProperties {
    private XDDFParagraphBulletProperties bullet;
    private CTTextParagraphProperties props;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFParagraphProperties(CTTextParagraphProperties properties) {
        this.props = properties;
        this.bullet = new XDDFParagraphBulletProperties(properties);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTTextParagraphProperties getXmlObject() {
        return this.props;
    }

    public XDDFParagraphBulletProperties getBulletProperties() {
        return this.bullet;
    }

    public int getLevel() {
        if (this.props.isSetLvl()) {
            return this.props.getLvl() + 1;
        }
        return 0;
    }

    public void setLevel(Integer level) {
        if (level == null) {
            if (this.props.isSetLvl()) {
                this.props.unsetLvl();
            }
        } else {
            if (level.intValue() < 1 || 9 < level.intValue()) {
                throw new IllegalArgumentException("Minimum inclusive: 1. Maximum inclusive: 9.");
            }
            this.props.setLvl(level.intValue() - 1);
        }
    }

    public XDDFRunProperties addDefaultRunProperties() {
        if (!this.props.isSetDefRPr()) {
            this.props.addNewDefRPr();
        }
        return getDefaultRunProperties();
    }

    public XDDFRunProperties getDefaultRunProperties() {
        if (this.props.isSetDefRPr()) {
            return new XDDFRunProperties(this.props.getDefRPr());
        }
        return null;
    }

    public void setDefaultRunProperties(XDDFRunProperties properties) {
        if (properties == null) {
            if (this.props.isSetDefRPr()) {
                this.props.unsetDefRPr();
                return;
            }
            return;
        }
        this.props.setDefRPr(properties.getXmlObject());
    }

    public void setEastAsianLineBreak(Boolean value) {
        if (value == null) {
            if (this.props.isSetEaLnBrk()) {
                this.props.unsetEaLnBrk();
                return;
            }
            return;
        }
        this.props.setEaLnBrk(value.booleanValue());
    }

    public void setLatinLineBreak(Boolean value) {
        if (value == null) {
            if (this.props.isSetLatinLnBrk()) {
                this.props.unsetLatinLnBrk();
                return;
            }
            return;
        }
        this.props.setLatinLnBrk(value.booleanValue());
    }

    public void setHangingPunctuation(Boolean value) {
        if (value == null) {
            if (this.props.isSetHangingPunct()) {
                this.props.unsetHangingPunct();
                return;
            }
            return;
        }
        this.props.setHangingPunct(value.booleanValue());
    }

    public void setRightToLeft(Boolean value) {
        if (value == null) {
            if (this.props.isSetRtl()) {
                this.props.unsetRtl();
                return;
            }
            return;
        }
        this.props.setRtl(value.booleanValue());
    }

    public void setFontAlignment(FontAlignment align) {
        if (align == null) {
            if (this.props.isSetFontAlgn()) {
                this.props.unsetFontAlgn();
                return;
            }
            return;
        }
        this.props.setFontAlgn(align.underlying);
    }

    public void setTextAlignment(TextAlignment align) {
        if (align == null) {
            if (this.props.isSetAlgn()) {
                this.props.unsetAlgn();
                return;
            }
            return;
        }
        this.props.setAlgn(align.underlying);
    }

    public void setDefaultTabSize(Double points) {
        if (points == null) {
            if (this.props.isSetDefTabSz()) {
                this.props.unsetDefTabSz();
                return;
            }
            return;
        }
        this.props.setDefTabSz(Integer.valueOf(Units.toEMU(points.doubleValue())));
    }

    public void setIndentation(Double points) {
        if (points == null) {
            if (this.props.isSetIndent()) {
                this.props.unsetIndent();
            }
        } else {
            if (points.doubleValue() < -4032.0d || 4032.0d < points.doubleValue()) {
                throw new IllegalArgumentException("Minimum inclusive = -4032. Maximum inclusive = 4032.");
            }
            this.props.setIndent(Units.toEMU(points.doubleValue()));
        }
    }

    public void setMarginLeft(Double points) {
        if (points == null) {
            if (this.props.isSetMarL()) {
                this.props.unsetMarL();
            }
        } else {
            if (points.doubleValue() < 0.0d || 4032.0d < points.doubleValue()) {
                throw new IllegalArgumentException("Minimum inclusive = 0. Maximum inclusive = 4032.");
            }
            this.props.setMarL(Units.toEMU(points.doubleValue()));
        }
    }

    public void setMarginRight(Double points) {
        if (points == null) {
            if (this.props.isSetMarR()) {
                this.props.unsetMarR();
            }
        } else {
            if (points.doubleValue() < 0.0d || 4032.0d < points.doubleValue()) {
                throw new IllegalArgumentException("Minimum inclusive = 0. Maximum inclusive = 4032.");
            }
            this.props.setMarR(Units.toEMU(points.doubleValue()));
        }
    }

    public void setLineSpacing(XDDFSpacing spacing) {
        if (spacing == null) {
            if (this.props.isSetLnSpc()) {
                this.props.unsetLnSpc();
                return;
            }
            return;
        }
        this.props.setLnSpc(spacing.getXmlObject());
    }

    public void setSpaceAfter(XDDFSpacing spacing) {
        if (spacing == null) {
            if (this.props.isSetSpcAft()) {
                this.props.unsetSpcAft();
                return;
            }
            return;
        }
        this.props.setSpcAft(spacing.getXmlObject());
    }

    public void setSpaceBefore(XDDFSpacing spacing) {
        if (spacing == null) {
            if (this.props.isSetSpcBef()) {
                this.props.unsetSpcBef();
                return;
            }
            return;
        }
        this.props.setSpcBef(spacing.getXmlObject());
    }

    public XDDFTabStop addTabStop() {
        if (!this.props.isSetTabLst()) {
            this.props.addNewTabLst();
        }
        return new XDDFTabStop(this.props.getTabLst().addNewTab());
    }

    public XDDFTabStop insertTabStop(int index) {
        if (!this.props.isSetTabLst()) {
            this.props.addNewTabLst();
        }
        return new XDDFTabStop(this.props.getTabLst().insertNewTab(index));
    }

    public void removeTabStop(int index) {
        if (this.props.isSetTabLst()) {
            this.props.getTabLst().removeTab(index);
        }
    }

    public XDDFTabStop getTabStop(int index) {
        if (this.props.isSetTabLst()) {
            return new XDDFTabStop(this.props.getTabLst().getTabArray(index));
        }
        return null;
    }

    public List<XDDFTabStop> getTabStops() {
        if (this.props.isSetTabLst()) {
            return Collections.unmodifiableList((List) this.props.getTabLst().getTabList().stream().map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFParagraphProperties$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return XDDFParagraphProperties.lambda$getTabStops$0((CTTextTabStop) obj);
                }
            }).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XDDFTabStop lambda$getTabStops$0(CTTextTabStop gs) {
        return new XDDFTabStop(gs);
    }

    public int countTabStops() {
        if (this.props.isSetTabLst()) {
            return this.props.getTabLst().sizeOfTabArray();
        }
        return 0;
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
}
