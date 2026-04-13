package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlENTITY;

/* loaded from: classes11.dex */
public class XmlEntityImpl extends JavaStringHolderEx implements XmlENTITY {
    public XmlEntityImpl() {
        super(XmlENTITY.type, false);
    }

    public XmlEntityImpl(SchemaType type, boolean complex) {
        super(type, complex);
    }
}
