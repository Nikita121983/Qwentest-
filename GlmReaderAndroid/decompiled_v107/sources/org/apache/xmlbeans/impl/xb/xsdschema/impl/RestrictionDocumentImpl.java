package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet;
import org.apache.xmlbeans.impl.xb.xsdschema.NumFacet;
import org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl;

/* loaded from: classes11.dex */
public class RestrictionDocumentImpl extends XmlComplexContentImpl implements RestrictionDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "restriction")};
    private static final long serialVersionUID = 1;

    public RestrictionDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument
    public RestrictionDocument.Restriction getRestriction() {
        RestrictionDocument.Restriction restriction;
        synchronized (monitor()) {
            check_orphaned();
            RestrictionDocument.Restriction target = (RestrictionDocument.Restriction) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            restriction = target == null ? null : target;
        }
        return restriction;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument
    public void setRestriction(RestrictionDocument.Restriction restriction) {
        generatedSetterHelperImpl(restriction, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument
    public RestrictionDocument.Restriction addNewRestriction() {
        RestrictionDocument.Restriction target;
        synchronized (monitor()) {
            check_orphaned();
            target = (RestrictionDocument.Restriction) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class RestrictionImpl extends AnnotatedImpl implements RestrictionDocument.Restriction {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "simpleType"), new QName("http://www.w3.org/2001/XMLSchema", "minExclusive"), new QName("http://www.w3.org/2001/XMLSchema", "minInclusive"), new QName("http://www.w3.org/2001/XMLSchema", "maxExclusive"), new QName("http://www.w3.org/2001/XMLSchema", "maxInclusive"), new QName("http://www.w3.org/2001/XMLSchema", "totalDigits"), new QName("http://www.w3.org/2001/XMLSchema", "fractionDigits"), new QName("http://www.w3.org/2001/XMLSchema", "length"), new QName("http://www.w3.org/2001/XMLSchema", "minLength"), new QName("http://www.w3.org/2001/XMLSchema", "maxLength"), new QName("http://www.w3.org/2001/XMLSchema", "enumeration"), new QName("http://www.w3.org/2001/XMLSchema", "whiteSpace"), new QName("http://www.w3.org/2001/XMLSchema", "pattern"), new QName("", "base")};
        private static final long serialVersionUID = 1;

        public RestrictionImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public LocalSimpleType getSimpleType() {
            LocalSimpleType localSimpleType;
            synchronized (monitor()) {
                check_orphaned();
                LocalSimpleType target = (LocalSimpleType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
                localSimpleType = target == null ? null : target;
            }
            return localSimpleType;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public boolean isSetSimpleType() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setSimpleType(LocalSimpleType simpleType) {
            generatedSetterHelperImpl(simpleType, PROPERTY_QNAME[0], 0, (short) 1);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public LocalSimpleType addNewSimpleType() {
            LocalSimpleType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (LocalSimpleType) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void unsetSimpleType() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], 0);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public List<Facet> getMinExclusiveList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda34
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.getMinExclusiveArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda35
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RestrictionDocumentImpl.RestrictionImpl.this.setMinExclusiveArray(((Integer) obj).intValue(), (Facet) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda36
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.insertNewMinExclusive(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda37
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RestrictionDocumentImpl.RestrictionImpl.this.removeMinExclusive(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda38
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(RestrictionDocumentImpl.RestrictionImpl.this.sizeOfMinExclusiveArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public Facet[] getMinExclusiveArray() {
            return (Facet[]) getXmlObjectArray(PROPERTY_QNAME[1], new Facet[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public Facet getMinExclusiveArray(int i) {
            Facet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Facet) get_store().find_element_user(PROPERTY_QNAME[1], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public int sizeOfMinExclusiveArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setMinExclusiveArray(Facet[] minExclusiveArray) {
            check_orphaned();
            arraySetterHelper(minExclusiveArray, PROPERTY_QNAME[1]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setMinExclusiveArray(int i, Facet minExclusive) {
            generatedSetterHelperImpl(minExclusive, PROPERTY_QNAME[1], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public Facet insertNewMinExclusive(int i) {
            Facet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Facet) get_store().insert_element_user(PROPERTY_QNAME[1], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public Facet addNewMinExclusive() {
            Facet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Facet) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void removeMinExclusive(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public List<Facet> getMinInclusiveList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda23
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.getMinInclusiveArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda24
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RestrictionDocumentImpl.RestrictionImpl.this.setMinInclusiveArray(((Integer) obj).intValue(), (Facet) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda25
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.insertNewMinInclusive(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda26
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RestrictionDocumentImpl.RestrictionImpl.this.removeMinInclusive(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda27
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(RestrictionDocumentImpl.RestrictionImpl.this.sizeOfMinInclusiveArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public Facet[] getMinInclusiveArray() {
            return (Facet[]) getXmlObjectArray(PROPERTY_QNAME[2], new Facet[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public Facet getMinInclusiveArray(int i) {
            Facet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Facet) get_store().find_element_user(PROPERTY_QNAME[2], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public int sizeOfMinInclusiveArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setMinInclusiveArray(Facet[] minInclusiveArray) {
            check_orphaned();
            arraySetterHelper(minInclusiveArray, PROPERTY_QNAME[2]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setMinInclusiveArray(int i, Facet minInclusive) {
            generatedSetterHelperImpl(minInclusive, PROPERTY_QNAME[2], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public Facet insertNewMinInclusive(int i) {
            Facet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Facet) get_store().insert_element_user(PROPERTY_QNAME[2], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public Facet addNewMinInclusive() {
            Facet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Facet) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void removeMinInclusive(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[2], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public List<Facet> getMaxExclusiveList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda45
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.getMaxExclusiveArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda46
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RestrictionDocumentImpl.RestrictionImpl.this.setMaxExclusiveArray(((Integer) obj).intValue(), (Facet) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda47
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.insertNewMaxExclusive(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda48
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RestrictionDocumentImpl.RestrictionImpl.this.removeMaxExclusive(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda49
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(RestrictionDocumentImpl.RestrictionImpl.this.sizeOfMaxExclusiveArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public Facet[] getMaxExclusiveArray() {
            return (Facet[]) getXmlObjectArray(PROPERTY_QNAME[3], new Facet[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public Facet getMaxExclusiveArray(int i) {
            Facet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Facet) get_store().find_element_user(PROPERTY_QNAME[3], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public int sizeOfMaxExclusiveArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setMaxExclusiveArray(Facet[] maxExclusiveArray) {
            check_orphaned();
            arraySetterHelper(maxExclusiveArray, PROPERTY_QNAME[3]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setMaxExclusiveArray(int i, Facet maxExclusive) {
            generatedSetterHelperImpl(maxExclusive, PROPERTY_QNAME[3], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public Facet insertNewMaxExclusive(int i) {
            Facet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Facet) get_store().insert_element_user(PROPERTY_QNAME[3], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public Facet addNewMaxExclusive() {
            Facet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Facet) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void removeMaxExclusive(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[3], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public List<Facet> getMaxInclusiveList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda55
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.getMaxInclusiveArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda56
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RestrictionDocumentImpl.RestrictionImpl.this.setMaxInclusiveArray(((Integer) obj).intValue(), (Facet) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda57
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.insertNewMaxInclusive(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda58
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RestrictionDocumentImpl.RestrictionImpl.this.removeMaxInclusive(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda59
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(RestrictionDocumentImpl.RestrictionImpl.this.sizeOfMaxInclusiveArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public Facet[] getMaxInclusiveArray() {
            return (Facet[]) getXmlObjectArray(PROPERTY_QNAME[4], new Facet[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public Facet getMaxInclusiveArray(int i) {
            Facet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Facet) get_store().find_element_user(PROPERTY_QNAME[4], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public int sizeOfMaxInclusiveArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setMaxInclusiveArray(Facet[] maxInclusiveArray) {
            check_orphaned();
            arraySetterHelper(maxInclusiveArray, PROPERTY_QNAME[4]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setMaxInclusiveArray(int i, Facet maxInclusive) {
            generatedSetterHelperImpl(maxInclusive, PROPERTY_QNAME[4], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public Facet insertNewMaxInclusive(int i) {
            Facet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Facet) get_store().insert_element_user(PROPERTY_QNAME[4], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public Facet addNewMaxInclusive() {
            Facet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Facet) get_store().add_element_user(PROPERTY_QNAME[4]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void removeMaxInclusive(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[4], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public List<TotalDigitsDocument.TotalDigits> getTotalDigitsList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda6
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.getTotalDigitsArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda7
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RestrictionDocumentImpl.RestrictionImpl.this.setTotalDigitsArray(((Integer) obj).intValue(), (TotalDigitsDocument.TotalDigits) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda8
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.insertNewTotalDigits(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda9
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RestrictionDocumentImpl.RestrictionImpl.this.removeTotalDigits(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda10
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(RestrictionDocumentImpl.RestrictionImpl.this.sizeOfTotalDigitsArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public TotalDigitsDocument.TotalDigits[] getTotalDigitsArray() {
            return (TotalDigitsDocument.TotalDigits[]) getXmlObjectArray(PROPERTY_QNAME[5], new TotalDigitsDocument.TotalDigits[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public TotalDigitsDocument.TotalDigits getTotalDigitsArray(int i) {
            TotalDigitsDocument.TotalDigits target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TotalDigitsDocument.TotalDigits) get_store().find_element_user(PROPERTY_QNAME[5], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public int sizeOfTotalDigitsArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setTotalDigitsArray(TotalDigitsDocument.TotalDigits[] totalDigitsArray) {
            check_orphaned();
            arraySetterHelper(totalDigitsArray, PROPERTY_QNAME[5]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setTotalDigitsArray(int i, TotalDigitsDocument.TotalDigits totalDigits) {
            generatedSetterHelperImpl(totalDigits, PROPERTY_QNAME[5], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public TotalDigitsDocument.TotalDigits insertNewTotalDigits(int i) {
            TotalDigitsDocument.TotalDigits target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TotalDigitsDocument.TotalDigits) get_store().insert_element_user(PROPERTY_QNAME[5], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public TotalDigitsDocument.TotalDigits addNewTotalDigits() {
            TotalDigitsDocument.TotalDigits target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TotalDigitsDocument.TotalDigits) get_store().add_element_user(PROPERTY_QNAME[5]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void removeTotalDigits(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[5], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public List<NumFacet> getFractionDigitsList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda17
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.getFractionDigitsArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda18
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RestrictionDocumentImpl.RestrictionImpl.this.setFractionDigitsArray(((Integer) obj).intValue(), (NumFacet) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda19
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.insertNewFractionDigits(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda20
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RestrictionDocumentImpl.RestrictionImpl.this.removeFractionDigits(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda21
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(RestrictionDocumentImpl.RestrictionImpl.this.sizeOfFractionDigitsArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NumFacet[] getFractionDigitsArray() {
            return (NumFacet[]) getXmlObjectArray(PROPERTY_QNAME[6], new NumFacet[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NumFacet getFractionDigitsArray(int i) {
            NumFacet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[6], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public int sizeOfFractionDigitsArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setFractionDigitsArray(NumFacet[] fractionDigitsArray) {
            check_orphaned();
            arraySetterHelper(fractionDigitsArray, PROPERTY_QNAME[6]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setFractionDigitsArray(int i, NumFacet fractionDigits) {
            generatedSetterHelperImpl(fractionDigits, PROPERTY_QNAME[6], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NumFacet insertNewFractionDigits(int i) {
            NumFacet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NumFacet) get_store().insert_element_user(PROPERTY_QNAME[6], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NumFacet addNewFractionDigits() {
            NumFacet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[6]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void removeFractionDigits(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[6], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public List<NumFacet> getLengthList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda12
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.getLengthArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda13
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RestrictionDocumentImpl.RestrictionImpl.this.setLengthArray(((Integer) obj).intValue(), (NumFacet) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda14
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.insertNewLength(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda15
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RestrictionDocumentImpl.RestrictionImpl.this.removeLength(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda16
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(RestrictionDocumentImpl.RestrictionImpl.this.sizeOfLengthArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NumFacet[] getLengthArray() {
            return (NumFacet[]) getXmlObjectArray(PROPERTY_QNAME[7], new NumFacet[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NumFacet getLengthArray(int i) {
            NumFacet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[7], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public int sizeOfLengthArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setLengthArray(NumFacet[] lengthArray) {
            check_orphaned();
            arraySetterHelper(lengthArray, PROPERTY_QNAME[7]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setLengthArray(int i, NumFacet length) {
            generatedSetterHelperImpl(length, PROPERTY_QNAME[7], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NumFacet insertNewLength(int i) {
            NumFacet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NumFacet) get_store().insert_element_user(PROPERTY_QNAME[7], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NumFacet addNewLength() {
            NumFacet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[7]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void removeLength(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[7], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public List<NumFacet> getMinLengthList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda50
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.getMinLengthArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda51
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RestrictionDocumentImpl.RestrictionImpl.this.setMinLengthArray(((Integer) obj).intValue(), (NumFacet) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda52
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.insertNewMinLength(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda53
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RestrictionDocumentImpl.RestrictionImpl.this.removeMinLength(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda54
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(RestrictionDocumentImpl.RestrictionImpl.this.sizeOfMinLengthArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NumFacet[] getMinLengthArray() {
            return (NumFacet[]) getXmlObjectArray(PROPERTY_QNAME[8], new NumFacet[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NumFacet getMinLengthArray(int i) {
            NumFacet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[8], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public int sizeOfMinLengthArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setMinLengthArray(NumFacet[] minLengthArray) {
            check_orphaned();
            arraySetterHelper(minLengthArray, PROPERTY_QNAME[8]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setMinLengthArray(int i, NumFacet minLength) {
            generatedSetterHelperImpl(minLength, PROPERTY_QNAME[8], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NumFacet insertNewMinLength(int i) {
            NumFacet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NumFacet) get_store().insert_element_user(PROPERTY_QNAME[8], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NumFacet addNewMinLength() {
            NumFacet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[8]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void removeMinLength(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[8], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public List<NumFacet> getMaxLengthList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda28
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.getMaxLengthArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda29
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RestrictionDocumentImpl.RestrictionImpl.this.setMaxLengthArray(((Integer) obj).intValue(), (NumFacet) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda30
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.insertNewMaxLength(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda31
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RestrictionDocumentImpl.RestrictionImpl.this.removeMaxLength(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda32
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(RestrictionDocumentImpl.RestrictionImpl.this.sizeOfMaxLengthArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NumFacet[] getMaxLengthArray() {
            return (NumFacet[]) getXmlObjectArray(PROPERTY_QNAME[9], new NumFacet[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NumFacet getMaxLengthArray(int i) {
            NumFacet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[9], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public int sizeOfMaxLengthArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setMaxLengthArray(NumFacet[] maxLengthArray) {
            check_orphaned();
            arraySetterHelper(maxLengthArray, PROPERTY_QNAME[9]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setMaxLengthArray(int i, NumFacet maxLength) {
            generatedSetterHelperImpl(maxLength, PROPERTY_QNAME[9], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NumFacet insertNewMaxLength(int i) {
            NumFacet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NumFacet) get_store().insert_element_user(PROPERTY_QNAME[9], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NumFacet addNewMaxLength() {
            NumFacet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[9]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void removeMaxLength(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[9], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public List<NoFixedFacet> getEnumerationList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda39
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.getEnumerationArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda40
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RestrictionDocumentImpl.RestrictionImpl.this.setEnumerationArray(((Integer) obj).intValue(), (NoFixedFacet) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda41
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.insertNewEnumeration(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda42
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RestrictionDocumentImpl.RestrictionImpl.this.removeEnumeration(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda43
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(RestrictionDocumentImpl.RestrictionImpl.this.sizeOfEnumerationArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NoFixedFacet[] getEnumerationArray() {
            return (NoFixedFacet[]) getXmlObjectArray(PROPERTY_QNAME[10], new NoFixedFacet[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NoFixedFacet getEnumerationArray(int i) {
            NoFixedFacet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NoFixedFacet) get_store().find_element_user(PROPERTY_QNAME[10], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public int sizeOfEnumerationArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setEnumerationArray(NoFixedFacet[] enumerationArray) {
            check_orphaned();
            arraySetterHelper(enumerationArray, PROPERTY_QNAME[10]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setEnumerationArray(int i, NoFixedFacet enumeration) {
            generatedSetterHelperImpl(enumeration, PROPERTY_QNAME[10], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NoFixedFacet insertNewEnumeration(int i) {
            NoFixedFacet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NoFixedFacet) get_store().insert_element_user(PROPERTY_QNAME[10], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public NoFixedFacet addNewEnumeration() {
            NoFixedFacet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NoFixedFacet) get_store().add_element_user(PROPERTY_QNAME[10]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void removeEnumeration(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[10], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public List<WhiteSpaceDocument.WhiteSpace> getWhiteSpaceList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.getWhiteSpaceArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda11
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RestrictionDocumentImpl.RestrictionImpl.this.setWhiteSpaceArray(((Integer) obj).intValue(), (WhiteSpaceDocument.WhiteSpace) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda22
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.insertNewWhiteSpace(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda33
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RestrictionDocumentImpl.RestrictionImpl.this.removeWhiteSpace(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda44
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(RestrictionDocumentImpl.RestrictionImpl.this.sizeOfWhiteSpaceArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public WhiteSpaceDocument.WhiteSpace[] getWhiteSpaceArray() {
            return (WhiteSpaceDocument.WhiteSpace[]) getXmlObjectArray(PROPERTY_QNAME[11], new WhiteSpaceDocument.WhiteSpace[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public WhiteSpaceDocument.WhiteSpace getWhiteSpaceArray(int i) {
            WhiteSpaceDocument.WhiteSpace target;
            synchronized (monitor()) {
                check_orphaned();
                target = (WhiteSpaceDocument.WhiteSpace) get_store().find_element_user(PROPERTY_QNAME[11], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public int sizeOfWhiteSpaceArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setWhiteSpaceArray(WhiteSpaceDocument.WhiteSpace[] whiteSpaceArray) {
            check_orphaned();
            arraySetterHelper(whiteSpaceArray, PROPERTY_QNAME[11]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setWhiteSpaceArray(int i, WhiteSpaceDocument.WhiteSpace whiteSpace) {
            generatedSetterHelperImpl(whiteSpace, PROPERTY_QNAME[11], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public WhiteSpaceDocument.WhiteSpace insertNewWhiteSpace(int i) {
            WhiteSpaceDocument.WhiteSpace target;
            synchronized (monitor()) {
                check_orphaned();
                target = (WhiteSpaceDocument.WhiteSpace) get_store().insert_element_user(PROPERTY_QNAME[11], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public WhiteSpaceDocument.WhiteSpace addNewWhiteSpace() {
            WhiteSpaceDocument.WhiteSpace target;
            synchronized (monitor()) {
                check_orphaned();
                target = (WhiteSpaceDocument.WhiteSpace) get_store().add_element_user(PROPERTY_QNAME[11]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void removeWhiteSpace(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[11], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public List<PatternDocument.Pattern> getPatternList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda1
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.getPatternArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda2
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RestrictionDocumentImpl.RestrictionImpl.this.setPatternArray(((Integer) obj).intValue(), (PatternDocument.Pattern) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda3
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RestrictionDocumentImpl.RestrictionImpl.this.insertNewPattern(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda4
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RestrictionDocumentImpl.RestrictionImpl.this.removePattern(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda5
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(RestrictionDocumentImpl.RestrictionImpl.this.sizeOfPatternArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public PatternDocument.Pattern[] getPatternArray() {
            return (PatternDocument.Pattern[]) getXmlObjectArray(PROPERTY_QNAME[12], new PatternDocument.Pattern[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public PatternDocument.Pattern getPatternArray(int i) {
            PatternDocument.Pattern target;
            synchronized (monitor()) {
                check_orphaned();
                target = (PatternDocument.Pattern) get_store().find_element_user(PROPERTY_QNAME[12], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public int sizeOfPatternArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setPatternArray(PatternDocument.Pattern[] patternArray) {
            check_orphaned();
            arraySetterHelper(patternArray, PROPERTY_QNAME[12]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setPatternArray(int i, PatternDocument.Pattern pattern) {
            generatedSetterHelperImpl(pattern, PROPERTY_QNAME[12], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public PatternDocument.Pattern insertNewPattern(int i) {
            PatternDocument.Pattern target;
            synchronized (monitor()) {
                check_orphaned();
                target = (PatternDocument.Pattern) get_store().insert_element_user(PROPERTY_QNAME[12], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public PatternDocument.Pattern addNewPattern() {
            PatternDocument.Pattern target;
            synchronized (monitor()) {
                check_orphaned();
                target = (PatternDocument.Pattern) get_store().add_element_user(PROPERTY_QNAME[12]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void removePattern(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[12], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public QName getBase() {
            QName qNameValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
                qNameValue = target == null ? null : target.getQNameValue();
            }
            return qNameValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public XmlQName xgetBase() {
            XmlQName target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public boolean isSetBase() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void setBase(QName base) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
                }
                target.setQNameValue(base);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void xsetBase(XmlQName base) {
            synchronized (monitor()) {
                check_orphaned();
                XmlQName target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[13]);
                if (target == null) {
                    target = (XmlQName) get_store().add_attribute_user(PROPERTY_QNAME[13]);
                }
                target.set(base);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction
        public void unsetBase() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[13]);
            }
        }
    }
}
