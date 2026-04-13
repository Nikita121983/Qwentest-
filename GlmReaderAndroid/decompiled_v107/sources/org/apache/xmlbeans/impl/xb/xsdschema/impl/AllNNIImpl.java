package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AllNNI;

/* loaded from: classes11.dex */
public class AllNNIImpl extends XmlUnionImpl implements AllNNI, XmlNonNegativeInteger, AllNNI.Member {
    private static final long serialVersionUID = 1;

    public AllNNIImpl(SchemaType sType) {
        super(sType, false);
    }

    protected AllNNIImpl(SchemaType sType, boolean b) {
        super(sType, b);
    }

    /* loaded from: classes11.dex */
    public static class MemberImpl extends JavaStringEnumerationHolderEx implements AllNNI.Member {
        private static final long serialVersionUID = 1;

        public MemberImpl(SchemaType sType) {
            super(sType, false);
        }

        protected MemberImpl(SchemaType sType, boolean b) {
            super(sType, b);
        }
    }
}
