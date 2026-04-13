package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontScalePercent;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontScalePercentOrPercentString;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STPercentage;

/* loaded from: classes11.dex */
public class STTextFontScalePercentOrPercentStringImpl extends XmlUnionImpl implements STTextFontScalePercentOrPercentString, STTextFontScalePercent, STPercentage {
    private static final long serialVersionUID = 1;

    public STTextFontScalePercentOrPercentStringImpl(SchemaType sType) {
        super(sType, false);
    }

    protected STTextFontScalePercentOrPercentStringImpl(SchemaType sType, boolean b) {
        super(sType, b);
    }
}
