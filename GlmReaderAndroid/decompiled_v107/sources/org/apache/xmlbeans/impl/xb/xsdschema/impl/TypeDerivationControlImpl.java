package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.xb.xsdschema.TypeDerivationControl;

/* loaded from: classes11.dex */
public class TypeDerivationControlImpl extends JavaStringEnumerationHolderEx implements TypeDerivationControl {
    private static final long serialVersionUID = 1;

    public TypeDerivationControlImpl(SchemaType sType) {
        super(sType, false);
    }

    protected TypeDerivationControlImpl(SchemaType sType, boolean b) {
        super(sType, b);
    }
}
