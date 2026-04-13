package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.values.XmlListImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.UnionDocumentImpl;

/* loaded from: classes11.dex */
public class UnionDocumentImpl extends XmlComplexContentImpl implements UnionDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", XmlErrorCodes.UNION)};
    private static final long serialVersionUID = 1;

    public UnionDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument
    public UnionDocument.Union getUnion() {
        UnionDocument.Union union;
        synchronized (monitor()) {
            check_orphaned();
            UnionDocument.Union target = (UnionDocument.Union) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            union = target == null ? null : target;
        }
        return union;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument
    public void setUnion(UnionDocument.Union union) {
        generatedSetterHelperImpl(union, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument
    public UnionDocument.Union addNewUnion() {
        UnionDocument.Union target;
        synchronized (monitor()) {
            check_orphaned();
            target = (UnionDocument.Union) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class UnionImpl extends AnnotatedImpl implements UnionDocument.Union {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "simpleType"), new QName("", "memberTypes")};
        private static final long serialVersionUID = 1;

        public UnionImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union
        public List<LocalSimpleType> getSimpleTypeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.UnionDocumentImpl$UnionImpl$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return UnionDocumentImpl.UnionImpl.this.getSimpleTypeArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.UnionDocumentImpl$UnionImpl$$ExternalSyntheticLambda1
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        UnionDocumentImpl.UnionImpl.this.setSimpleTypeArray(((Integer) obj).intValue(), (LocalSimpleType) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.UnionDocumentImpl$UnionImpl$$ExternalSyntheticLambda2
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return UnionDocumentImpl.UnionImpl.this.insertNewSimpleType(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.UnionDocumentImpl$UnionImpl$$ExternalSyntheticLambda3
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        UnionDocumentImpl.UnionImpl.this.removeSimpleType(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.UnionDocumentImpl$UnionImpl$$ExternalSyntheticLambda4
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(UnionDocumentImpl.UnionImpl.this.sizeOfSimpleTypeArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union
        public LocalSimpleType[] getSimpleTypeArray() {
            return (LocalSimpleType[]) getXmlObjectArray(PROPERTY_QNAME[0], new LocalSimpleType[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union
        public LocalSimpleType getSimpleTypeArray(int i) {
            LocalSimpleType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (LocalSimpleType) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union
        public int sizeOfSimpleTypeArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union
        public void setSimpleTypeArray(LocalSimpleType[] simpleTypeArray) {
            check_orphaned();
            arraySetterHelper(simpleTypeArray, PROPERTY_QNAME[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union
        public void setSimpleTypeArray(int i, LocalSimpleType simpleType) {
            generatedSetterHelperImpl(simpleType, PROPERTY_QNAME[0], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union
        public LocalSimpleType insertNewSimpleType(int i) {
            LocalSimpleType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (LocalSimpleType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union
        public LocalSimpleType addNewSimpleType() {
            LocalSimpleType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (LocalSimpleType) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union
        public void removeSimpleType(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union
        public List getMemberTypes() {
            List<?> listValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                listValue = target == null ? null : target.getListValue();
            }
            return listValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union
        public UnionDocument.Union.MemberTypes xgetMemberTypes() {
            UnionDocument.Union.MemberTypes target;
            synchronized (monitor()) {
                check_orphaned();
                target = (UnionDocument.Union.MemberTypes) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union
        public boolean isSetMemberTypes() {
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

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union
        public void setMemberTypes(List memberTypes) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                }
                target.setListValue(memberTypes);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union
        public void xsetMemberTypes(UnionDocument.Union.MemberTypes memberTypes) {
            synchronized (monitor()) {
                check_orphaned();
                UnionDocument.Union.MemberTypes target = (UnionDocument.Union.MemberTypes) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (UnionDocument.Union.MemberTypes) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                }
                target.set(memberTypes);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union
        public void unsetMemberTypes() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[1]);
            }
        }

        /* loaded from: classes11.dex */
        public static class MemberTypesImpl extends XmlListImpl implements UnionDocument.Union.MemberTypes {
            private static final long serialVersionUID = 1;

            public MemberTypesImpl(SchemaType sType) {
                super(sType, false);
            }

            protected MemberTypesImpl(SchemaType sType, boolean b) {
                super(sType, b);
            }
        }
    }
}
