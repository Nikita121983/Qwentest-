package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D;

/* loaded from: classes10.dex */
public class XDDFView3D {
    private final CTView3D view3D;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFView3D(CTView3D view3D) {
        this.view3D = view3D;
    }

    public Byte getXRotationAngle() {
        if (this.view3D.isSetRotX()) {
            return Byte.valueOf(this.view3D.getRotX().getVal());
        }
        return null;
    }

    public void setXRotationAngle(Byte rotation) {
        if (rotation == null) {
            if (this.view3D.isSetRotX()) {
                this.view3D.unsetRotX();
            }
        } else {
            if (rotation.byteValue() < -90 || 90 < rotation.byteValue()) {
                throw new IllegalArgumentException("rotation must be between -90 and 90");
            }
            if (this.view3D.isSetRotX()) {
                this.view3D.getRotX().setVal(rotation.byteValue());
            } else {
                this.view3D.addNewRotX().setVal(rotation.byteValue());
            }
        }
    }

    public Integer getYRotationAngle() {
        if (this.view3D.isSetRotY()) {
            return Integer.valueOf(this.view3D.getRotY().getVal());
        }
        return null;
    }

    public void setYRotationAngle(Integer rotation) {
        if (rotation == null) {
            if (this.view3D.isSetRotY()) {
                this.view3D.unsetRotY();
            }
        } else {
            if (rotation.intValue() < 0 || 360 < rotation.intValue()) {
                throw new IllegalArgumentException("rotation must be between 0 and 360");
            }
            if (this.view3D.isSetRotY()) {
                this.view3D.getRotY().setVal(rotation.intValue());
            } else {
                this.view3D.addNewRotY().setVal(rotation.intValue());
            }
        }
    }

    public Boolean hasRightAngleAxes() {
        if (this.view3D.isSetRAngAx()) {
            return Boolean.valueOf(this.view3D.getRAngAx().getVal());
        }
        return null;
    }

    public void setRightAngleAxes(Boolean rightAngles) {
        if (rightAngles == null) {
            if (this.view3D.isSetRAngAx()) {
                this.view3D.unsetRAngAx();
            }
        } else if (this.view3D.isSetRAngAx()) {
            this.view3D.getRAngAx().setVal(rightAngles.booleanValue());
        } else {
            this.view3D.addNewRAngAx().setVal(rightAngles.booleanValue());
        }
    }

    public Short getPerspectiveAngle() {
        if (this.view3D.isSetPerspective()) {
            return Short.valueOf(this.view3D.getPerspective().getVal());
        }
        return null;
    }

    public void setPerspectiveAngle(Short perspective) {
        if (perspective == null) {
            if (this.view3D.isSetPerspective()) {
                this.view3D.unsetPerspective();
            }
        } else {
            if (perspective.shortValue() < 0 || 240 < perspective.shortValue()) {
                throw new IllegalArgumentException("perspective must be between 0 and 240");
            }
            if (this.view3D.isSetPerspective()) {
                this.view3D.getPerspective().setVal(perspective.shortValue());
            } else {
                this.view3D.addNewPerspective().setVal(perspective.shortValue());
            }
        }
    }

    public Integer getDepthPercent() {
        if (this.view3D.isSetDepthPercent()) {
            return Integer.valueOf(POIXMLUnits.parsePercent(this.view3D.getDepthPercent().xgetVal()));
        }
        return null;
    }

    public void setDepthPercent(Integer percent) {
        if (percent == null) {
            if (this.view3D.isSetDepthPercent()) {
                this.view3D.unsetDepthPercent();
            }
        } else {
            if (percent.intValue() < 20 || 2000 < percent.intValue()) {
                throw new IllegalArgumentException("percent must be between 20 and 2000");
            }
            if (this.view3D.isSetDepthPercent()) {
                this.view3D.getDepthPercent().setVal(percent);
            } else {
                this.view3D.addNewDepthPercent().setVal(percent);
            }
        }
    }

    public Integer getHPercent() {
        if (this.view3D.isSetHPercent()) {
            return Integer.valueOf(POIXMLUnits.parsePercent(this.view3D.getHPercent().xgetVal()));
        }
        return null;
    }

    public void setHPercent(Integer percent) {
        if (percent == null) {
            if (this.view3D.isSetHPercent()) {
                this.view3D.unsetHPercent();
            }
        } else {
            if (percent.intValue() < 5 || 500 < percent.intValue()) {
                throw new IllegalArgumentException("percent must be between 5 and 500");
            }
            if (this.view3D.isSetHPercent()) {
                this.view3D.getHPercent().setVal(percent);
            } else {
                this.view3D.addNewHPercent().setVal(percent);
            }
        }
    }
}
