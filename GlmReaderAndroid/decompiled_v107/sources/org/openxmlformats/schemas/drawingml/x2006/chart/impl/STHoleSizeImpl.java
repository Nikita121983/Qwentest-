package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.STHoleSize;
import org.openxmlformats.schemas.drawingml.x2006.chart.STHoleSizePercent;
import org.openxmlformats.schemas.drawingml.x2006.chart.STHoleSizeUByte;

/* loaded from: classes11.dex */
public class STHoleSizeImpl extends XmlUnionImpl implements STHoleSize, STHoleSizePercent, STHoleSizeUByte {
    private static final long serialVersionUID = 1;

    public STHoleSizeImpl(SchemaType sType) {
        super(sType, false);
    }

    protected STHoleSizeImpl(SchemaType sType, boolean b) {
        super(sType, b);
    }
}
