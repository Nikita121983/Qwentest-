package org.apache.poi.xslf.usermodel;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.sl.usermodel.TabStop;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextTabAlignType;

/* loaded from: classes10.dex */
public class XSLFTabStop implements TabStop {
    final CTTextTabStop tabStop;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFTabStop(CTTextTabStop tabStop) {
        this.tabStop = tabStop;
    }

    public int getPosition() {
        return (int) POIXMLUnits.parseLength(this.tabStop.xgetPos());
    }

    public void setPosition(int position) {
        this.tabStop.setPos(Integer.valueOf(position));
    }

    @Override // org.apache.poi.sl.usermodel.TabStop
    public double getPositionInPoints() {
        return Units.toPoints(getPosition());
    }

    @Override // org.apache.poi.sl.usermodel.TabStop
    public void setPositionInPoints(double points) {
        setPosition(Units.toEMU(points));
    }

    @Override // org.apache.poi.sl.usermodel.TabStop
    public TabStop.TabStopType getType() {
        return TabStop.TabStopType.fromOoxmlId(this.tabStop.getAlgn().intValue());
    }

    @Override // org.apache.poi.sl.usermodel.TabStop
    public void setType(TabStop.TabStopType tabStopType) {
        this.tabStop.setAlgn(STTextTabAlignType.Enum.forInt(tabStopType.ooxmlId));
    }
}
