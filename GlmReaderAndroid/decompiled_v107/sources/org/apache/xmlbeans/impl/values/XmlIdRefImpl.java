package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlIDREF;

/* loaded from: classes11.dex */
public class XmlIdRefImpl extends JavaStringHolderEx implements XmlIDREF {
    public XmlIdRefImpl() {
        super(XmlIDREF.type, false);
    }

    public XmlIdRefImpl(SchemaType type, boolean complex) {
        super(type, complex);
    }
}
