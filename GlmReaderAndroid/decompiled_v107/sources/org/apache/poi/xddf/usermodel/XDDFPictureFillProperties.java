package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;

/* loaded from: classes10.dex */
public class XDDFPictureFillProperties implements XDDFFillProperties {
    private CTBlipFillProperties props;

    public XDDFPictureFillProperties() {
        this(CTBlipFillProperties.Factory.newInstance());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFPictureFillProperties(CTBlipFillProperties properties) {
        this.props = properties;
    }

    @Internal
    public CTBlipFillProperties getXmlObject() {
        return this.props;
    }

    public XDDFPicture getPicture() {
        if (this.props.isSetBlip()) {
            return new XDDFPicture(this.props.getBlip());
        }
        return null;
    }

    public void setPicture(XDDFPicture picture) {
        if (picture == null) {
            this.props.unsetBlip();
        } else {
            this.props.setBlip(picture.getXmlObject());
        }
    }

    public Boolean isRotatingWithShape() {
        if (this.props.isSetRotWithShape()) {
            return Boolean.valueOf(this.props.getRotWithShape());
        }
        return false;
    }

    public void setRotatingWithShape(Boolean rotating) {
        if (rotating == null) {
            if (this.props.isSetRotWithShape()) {
                this.props.unsetRotWithShape();
                return;
            }
            return;
        }
        this.props.setRotWithShape(rotating.booleanValue());
    }

    public Long getDpi() {
        if (this.props.isSetDpi()) {
            return Long.valueOf(this.props.getDpi());
        }
        return null;
    }

    public void setDpi(Long dpi) {
        if (dpi == null) {
            if (this.props.isSetDpi()) {
                this.props.unsetDpi();
                return;
            }
            return;
        }
        this.props.setDpi(dpi.longValue());
    }

    public XDDFRelativeRectangle getSourceRectangle() {
        if (this.props.isSetSrcRect()) {
            return new XDDFRelativeRectangle(this.props.getSrcRect());
        }
        return null;
    }

    public void setSourceRectangle(XDDFRelativeRectangle rectangle) {
        if (rectangle == null) {
            if (this.props.isSetSrcRect()) {
                this.props.unsetSrcRect();
                return;
            }
            return;
        }
        this.props.setSrcRect(rectangle.getXmlObject());
    }

    public XDDFStretchInfoProperties getStetchInfoProperties() {
        if (this.props.isSetStretch()) {
            return new XDDFStretchInfoProperties(this.props.getStretch());
        }
        return null;
    }

    public void setStretchInfoProperties(XDDFStretchInfoProperties properties) {
        if (properties == null) {
            if (this.props.isSetStretch()) {
                this.props.unsetStretch();
                return;
            }
            return;
        }
        this.props.setStretch(properties.getXmlObject());
    }

    public XDDFTileInfoProperties getTileInfoProperties() {
        if (this.props.isSetTile()) {
            return new XDDFTileInfoProperties(this.props.getTile());
        }
        return null;
    }

    public void setTileInfoProperties(XDDFTileInfoProperties properties) {
        if (properties == null) {
            if (this.props.isSetTile()) {
                this.props.unsetTile();
                return;
            }
            return;
        }
        this.props.setTile(properties.getXmlObject());
    }
}
