package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff1;

/* loaded from: classes11.dex */
public class STOnOffImpl extends XmlUnionImpl implements STOnOff, XmlBoolean, STOnOff1 {
    private static final long serialVersionUID = 1;

    public STOnOffImpl(SchemaType sType) {
        super(sType, false);
    }

    protected STOnOffImpl(SchemaType sType, boolean b) {
        super(sType, b);
    }
}
