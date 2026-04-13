package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop;

/* loaded from: classes10.dex */
public class XDDFTabStop {
    private CTTextTabStop stop;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFTabStop(CTTextTabStop stop) {
        this.stop = stop;
    }

    @Internal
    protected CTTextTabStop getXmlObject() {
        return this.stop;
    }

    public TabAlignment getAlignment() {
        if (this.stop.isSetAlgn()) {
            return TabAlignment.valueOf(this.stop.getAlgn());
        }
        return null;
    }

    public void setAlignment(TabAlignment align) {
        if (align == null) {
            if (this.stop.isSetAlgn()) {
                this.stop.unsetAlgn();
                return;
            }
            return;
        }
        this.stop.setAlgn(align.underlying);
    }

    public Double getPosition() {
        if (this.stop.isSetPos()) {
            return Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(this.stop.xgetPos())));
        }
        return null;
    }

    public void setPosition(Double position) {
        if (position == null) {
            if (this.stop.isSetPos()) {
                this.stop.unsetPos();
                return;
            }
            return;
        }
        this.stop.setPos(Integer.valueOf(Units.toEMU(position.doubleValue())));
    }
}
