package org.apache.poi.xddf.usermodel;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties;

/* loaded from: classes10.dex */
public class XDDFTileInfoProperties {
    private CTTileInfoProperties props;

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFTileInfoProperties(CTTileInfoProperties properties) {
        this.props = properties;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTTileInfoProperties getXmlObject() {
        return this.props;
    }

    public void setAlignment(RectangleAlignment alignment) {
        if (alignment == null) {
            if (this.props.isSetAlgn()) {
                this.props.unsetAlgn();
                return;
            }
            return;
        }
        this.props.setAlgn(alignment.underlying);
    }

    public TileFlipMode getFlipMode() {
        if (this.props.isSetFlip()) {
            return TileFlipMode.valueOf(this.props.getFlip());
        }
        return null;
    }

    public void setFlipMode(TileFlipMode mode) {
        if (mode == null) {
            if (this.props.isSetFlip()) {
                this.props.unsetFlip();
                return;
            }
            return;
        }
        this.props.setFlip(mode.underlying);
    }

    public Integer getSx() {
        if (this.props.isSetSx()) {
            return Integer.valueOf(POIXMLUnits.parsePercent(this.props.xgetSx()));
        }
        return null;
    }

    public void setSx(Integer value) {
        if (value == null) {
            if (this.props.isSetSx()) {
                this.props.unsetSx();
                return;
            }
            return;
        }
        this.props.setSx(value);
    }

    public Integer getSy() {
        if (this.props.isSetSy()) {
            return Integer.valueOf(POIXMLUnits.parsePercent(this.props.xgetSy()));
        }
        return null;
    }

    public void setSy(Integer value) {
        if (value == null) {
            if (this.props.isSetSy()) {
                this.props.unsetSy();
                return;
            }
            return;
        }
        this.props.setSy(value);
    }

    public Long getTx() {
        if (this.props.isSetTx()) {
            return Long.valueOf(POIXMLUnits.parseLength(this.props.xgetTx()));
        }
        return null;
    }

    public void setTx(Long value) {
        if (value == null) {
            if (this.props.isSetTx()) {
                this.props.unsetTx();
                return;
            }
            return;
        }
        this.props.setTx(value);
    }

    public Long getTy() {
        if (this.props.isSetTy()) {
            return Long.valueOf(POIXMLUnits.parseLength(this.props.xgetTy()));
        }
        return null;
    }

    public void setTy(Long value) {
        if (value == null) {
            if (this.props.isSetTy()) {
                this.props.unsetTy();
                return;
            }
            return;
        }
        this.props.setTy(value);
    }
}
