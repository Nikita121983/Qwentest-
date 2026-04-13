package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AllNNI;
import org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument;

/* loaded from: classes11.dex */
public class AnyDocumentImpl extends XmlComplexContentImpl implements AnyDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", Languages.ANY)};
    private static final long serialVersionUID = 1;

    public AnyDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument
    public AnyDocument.Any getAny() {
        AnyDocument.Any any;
        synchronized (monitor()) {
            check_orphaned();
            AnyDocument.Any target = (AnyDocument.Any) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            any = target == null ? null : target;
        }
        return any;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument
    public void setAny(AnyDocument.Any any) {
        generatedSetterHelperImpl(any, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument
    public AnyDocument.Any addNewAny() {
        AnyDocument.Any target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AnyDocument.Any) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class AnyImpl extends WildcardImpl implements AnyDocument.Any {
        private static final QName[] PROPERTY_QNAME = {new QName("", "minOccurs"), new QName("", "maxOccurs")};
        private static final long serialVersionUID = 1;

        public AnyImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument.Any
        public BigInteger getMinOccurs() {
            BigInteger bigIntegerValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                if (target == null) {
                    target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[0]);
                }
                bigIntegerValue = target == null ? null : target.getBigIntegerValue();
            }
            return bigIntegerValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument.Any
        public XmlNonNegativeInteger xgetMinOccurs() {
            XmlNonNegativeInteger target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlNonNegativeInteger) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                if (target == null) {
                    target = (XmlNonNegativeInteger) get_default_attribute_value(PROPERTY_QNAME[0]);
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument.Any
        public boolean isSetMinOccurs() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument.Any
        public void setMinOccurs(BigInteger minOccurs) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
                }
                target.setBigIntegerValue(minOccurs);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument.Any
        public void xsetMinOccurs(XmlNonNegativeInteger minOccurs) {
            synchronized (monitor()) {
                check_orphaned();
                XmlNonNegativeInteger target = (XmlNonNegativeInteger) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                if (target == null) {
                    target = (XmlNonNegativeInteger) get_store().add_attribute_user(PROPERTY_QNAME[0]);
                }
                target.set(minOccurs);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument.Any
        public void unsetMinOccurs() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[0]);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument.Any
        public Object getMaxOccurs() {
            Object objectValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[1]);
                }
                objectValue = target == null ? null : target.getObjectValue();
            }
            return objectValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument.Any
        public AllNNI xgetMaxOccurs() {
            AllNNI target;
            synchronized (monitor()) {
                check_orphaned();
                target = (AllNNI) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (AllNNI) get_default_attribute_value(PROPERTY_QNAME[1]);
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument.Any
        public boolean isSetMaxOccurs() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = true;
                if (get_store().find_attribute_user(PROPERTY_QNAME[1]) == null) {
                    z = false;
                }
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument.Any
        public void setMaxOccurs(Object maxOccurs) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                }
                target.setObjectValue(maxOccurs);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument.Any
        public void xsetMaxOccurs(AllNNI maxOccurs) {
            synchronized (monitor()) {
                check_orphaned();
                AllNNI target = (AllNNI) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (AllNNI) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                }
                target.set(maxOccurs);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument.Any
        public void unsetMaxOccurs() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[1]);
            }
        }
    }
}
