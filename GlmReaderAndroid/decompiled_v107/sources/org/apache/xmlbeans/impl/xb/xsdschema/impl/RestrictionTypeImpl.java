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
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.apache.xmlbeans.impl.xb.xsdschema.GroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet;
import org.apache.xmlbeans.impl.xb.xsdschema.NumFacet;
import org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType;
import org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;

/* loaded from: classes11.dex */
public class RestrictionTypeImpl extends AnnotatedImpl implements RestrictionType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "group"), new QName("http://www.w3.org/2001/XMLSchema", "all"), new QName("http://www.w3.org/2001/XMLSchema", "choice"), new QName("http://www.w3.org/2001/XMLSchema", "sequence"), new QName("http://www.w3.org/2001/XMLSchema", "simpleType"), new QName("http://www.w3.org/2001/XMLSchema", "minExclusive"), new QName("http://www.w3.org/2001/XMLSchema", "minInclusive"), new QName("http://www.w3.org/2001/XMLSchema", "maxExclusive"), new QName("http://www.w3.org/2001/XMLSchema", "maxInclusive"), new QName("http://www.w3.org/2001/XMLSchema", "totalDigits"), new QName("http://www.w3.org/2001/XMLSchema", "fractionDigits"), new QName("http://www.w3.org/2001/XMLSchema", "length"), new QName("http://www.w3.org/2001/XMLSchema", "minLength"), new QName("http://www.w3.org/2001/XMLSchema", "maxLength"), new QName("http://www.w3.org/2001/XMLSchema", "enumeration"), new QName("http://www.w3.org/2001/XMLSchema", "whiteSpace"), new QName("http://www.w3.org/2001/XMLSchema", "pattern"), new QName("http://www.w3.org/2001/XMLSchema", "attribute"), new QName("http://www.w3.org/2001/XMLSchema", "attributeGroup"), new QName("http://www.w3.org/2001/XMLSchema", "anyAttribute"), new QName("", "base")};
    private static final long serialVersionUID = 1;

    public RestrictionTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public GroupRef getGroup() {
        GroupRef groupRef;
        synchronized (monitor()) {
            check_orphaned();
            GroupRef target = (GroupRef) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            groupRef = target == null ? null : target;
        }
        return groupRef;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public boolean isSetGroup() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setGroup(GroupRef group) {
        generatedSetterHelperImpl(group, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public GroupRef addNewGroup() {
        GroupRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (GroupRef) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void unsetGroup() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public All getAll() {
        All all;
        synchronized (monitor()) {
            check_orphaned();
            All target = (All) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            all = target == null ? null : target;
        }
        return all;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public boolean isSetAll() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setAll(All all) {
        generatedSetterHelperImpl(all, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public All addNewAll() {
        All target;
        synchronized (monitor()) {
            check_orphaned();
            target = (All) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void unsetAll() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public ExplicitGroup getChoice() {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            ExplicitGroup target = (ExplicitGroup) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            explicitGroup = target == null ? null : target;
        }
        return explicitGroup;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public boolean isSetChoice() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setChoice(ExplicitGroup choice) {
        generatedSetterHelperImpl(choice, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public ExplicitGroup addNewChoice() {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void unsetChoice() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public ExplicitGroup getSequence() {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            ExplicitGroup target = (ExplicitGroup) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            explicitGroup = target == null ? null : target;
        }
        return explicitGroup;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public boolean isSetSequence() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setSequence(ExplicitGroup sequence) {
        generatedSetterHelperImpl(sequence, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public ExplicitGroup addNewSequence() {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void unsetSequence() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public LocalSimpleType getSimpleType() {
        LocalSimpleType localSimpleType;
        synchronized (monitor()) {
            check_orphaned();
            LocalSimpleType target = (LocalSimpleType) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            localSimpleType = target == null ? null : target;
        }
        return localSimpleType;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public boolean isSetSimpleType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setSimpleType(LocalSimpleType simpleType) {
        generatedSetterHelperImpl(simpleType, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public LocalSimpleType addNewSimpleType() {
        LocalSimpleType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (LocalSimpleType) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void unsetSimpleType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public List<Facet> getMinExclusiveList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.getMinExclusiveArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RestrictionTypeImpl.this.setMinExclusiveArray(((Integer) obj).intValue(), (Facet) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.insertNewMinExclusive(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RestrictionTypeImpl.this.removeMinExclusive(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RestrictionTypeImpl.this.sizeOfMinExclusiveArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Facet[] getMinExclusiveArray() {
        return (Facet[]) getXmlObjectArray(PROPERTY_QNAME[5], new Facet[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Facet getMinExclusiveArray(int i) {
        Facet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Facet) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public int sizeOfMinExclusiveArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setMinExclusiveArray(Facet[] minExclusiveArray) {
        check_orphaned();
        arraySetterHelper(minExclusiveArray, PROPERTY_QNAME[5]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setMinExclusiveArray(int i, Facet minExclusive) {
        generatedSetterHelperImpl(minExclusive, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Facet insertNewMinExclusive(int i) {
        Facet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Facet) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Facet addNewMinExclusive() {
        Facet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Facet) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void removeMinExclusive(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public List<Facet> getMinInclusiveList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.getMinInclusiveArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RestrictionTypeImpl.this.setMinInclusiveArray(((Integer) obj).intValue(), (Facet) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.insertNewMinInclusive(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RestrictionTypeImpl.this.removeMinInclusive(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RestrictionTypeImpl.this.sizeOfMinInclusiveArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Facet[] getMinInclusiveArray() {
        return (Facet[]) getXmlObjectArray(PROPERTY_QNAME[6], new Facet[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Facet getMinInclusiveArray(int i) {
        Facet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Facet) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public int sizeOfMinInclusiveArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setMinInclusiveArray(Facet[] minInclusiveArray) {
        check_orphaned();
        arraySetterHelper(minInclusiveArray, PROPERTY_QNAME[6]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setMinInclusiveArray(int i, Facet minInclusive) {
        generatedSetterHelperImpl(minInclusive, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Facet insertNewMinInclusive(int i) {
        Facet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Facet) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Facet addNewMinInclusive() {
        Facet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Facet) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void removeMinInclusive(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public List<Facet> getMaxExclusiveList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.getMaxExclusiveArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RestrictionTypeImpl.this.setMaxExclusiveArray(((Integer) obj).intValue(), (Facet) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.insertNewMaxExclusive(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RestrictionTypeImpl.this.removeMaxExclusive(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RestrictionTypeImpl.this.sizeOfMaxExclusiveArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Facet[] getMaxExclusiveArray() {
        return (Facet[]) getXmlObjectArray(PROPERTY_QNAME[7], new Facet[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Facet getMaxExclusiveArray(int i) {
        Facet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Facet) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public int sizeOfMaxExclusiveArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setMaxExclusiveArray(Facet[] maxExclusiveArray) {
        check_orphaned();
        arraySetterHelper(maxExclusiveArray, PROPERTY_QNAME[7]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setMaxExclusiveArray(int i, Facet maxExclusive) {
        generatedSetterHelperImpl(maxExclusive, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Facet insertNewMaxExclusive(int i) {
        Facet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Facet) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Facet addNewMaxExclusive() {
        Facet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Facet) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void removeMaxExclusive(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public List<Facet> getMaxInclusiveList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.getMaxInclusiveArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RestrictionTypeImpl.this.setMaxInclusiveArray(((Integer) obj).intValue(), (Facet) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.insertNewMaxInclusive(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RestrictionTypeImpl.this.removeMaxInclusive(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RestrictionTypeImpl.this.sizeOfMaxInclusiveArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Facet[] getMaxInclusiveArray() {
        return (Facet[]) getXmlObjectArray(PROPERTY_QNAME[8], new Facet[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Facet getMaxInclusiveArray(int i) {
        Facet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Facet) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public int sizeOfMaxInclusiveArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setMaxInclusiveArray(Facet[] maxInclusiveArray) {
        check_orphaned();
        arraySetterHelper(maxInclusiveArray, PROPERTY_QNAME[8]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setMaxInclusiveArray(int i, Facet maxInclusive) {
        generatedSetterHelperImpl(maxInclusive, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Facet insertNewMaxInclusive(int i) {
        Facet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Facet) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Facet addNewMaxInclusive() {
        Facet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Facet) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void removeMaxInclusive(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public List<TotalDigitsDocument.TotalDigits> getTotalDigitsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.getTotalDigitsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RestrictionTypeImpl.this.setTotalDigitsArray(((Integer) obj).intValue(), (TotalDigitsDocument.TotalDigits) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.insertNewTotalDigits(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RestrictionTypeImpl.this.removeTotalDigits(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RestrictionTypeImpl.this.sizeOfTotalDigitsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public TotalDigitsDocument.TotalDigits[] getTotalDigitsArray() {
        return (TotalDigitsDocument.TotalDigits[]) getXmlObjectArray(PROPERTY_QNAME[9], new TotalDigitsDocument.TotalDigits[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public TotalDigitsDocument.TotalDigits getTotalDigitsArray(int i) {
        TotalDigitsDocument.TotalDigits target;
        synchronized (monitor()) {
            check_orphaned();
            target = (TotalDigitsDocument.TotalDigits) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public int sizeOfTotalDigitsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setTotalDigitsArray(TotalDigitsDocument.TotalDigits[] totalDigitsArray) {
        check_orphaned();
        arraySetterHelper(totalDigitsArray, PROPERTY_QNAME[9]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setTotalDigitsArray(int i, TotalDigitsDocument.TotalDigits totalDigits) {
        generatedSetterHelperImpl(totalDigits, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public TotalDigitsDocument.TotalDigits insertNewTotalDigits(int i) {
        TotalDigitsDocument.TotalDigits target;
        synchronized (monitor()) {
            check_orphaned();
            target = (TotalDigitsDocument.TotalDigits) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public TotalDigitsDocument.TotalDigits addNewTotalDigits() {
        TotalDigitsDocument.TotalDigits target;
        synchronized (monitor()) {
            check_orphaned();
            target = (TotalDigitsDocument.TotalDigits) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void removeTotalDigits(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public List<NumFacet> getFractionDigitsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.getFractionDigitsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RestrictionTypeImpl.this.setFractionDigitsArray(((Integer) obj).intValue(), (NumFacet) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.insertNewFractionDigits(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RestrictionTypeImpl.this.removeFractionDigits(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RestrictionTypeImpl.this.sizeOfFractionDigitsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NumFacet[] getFractionDigitsArray() {
        return (NumFacet[]) getXmlObjectArray(PROPERTY_QNAME[10], new NumFacet[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NumFacet getFractionDigitsArray(int i) {
        NumFacet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public int sizeOfFractionDigitsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setFractionDigitsArray(NumFacet[] fractionDigitsArray) {
        check_orphaned();
        arraySetterHelper(fractionDigitsArray, PROPERTY_QNAME[10]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setFractionDigitsArray(int i, NumFacet fractionDigits) {
        generatedSetterHelperImpl(fractionDigits, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NumFacet insertNewFractionDigits(int i) {
        NumFacet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NumFacet) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NumFacet addNewFractionDigits() {
        NumFacet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void removeFractionDigits(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public List<NumFacet> getLengthList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.getLengthArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RestrictionTypeImpl.this.setLengthArray(((Integer) obj).intValue(), (NumFacet) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.insertNewLength(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RestrictionTypeImpl.this.removeLength(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RestrictionTypeImpl.this.sizeOfLengthArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NumFacet[] getLengthArray() {
        return (NumFacet[]) getXmlObjectArray(PROPERTY_QNAME[11], new NumFacet[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NumFacet getLengthArray(int i) {
        NumFacet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public int sizeOfLengthArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setLengthArray(NumFacet[] lengthArray) {
        check_orphaned();
        arraySetterHelper(lengthArray, PROPERTY_QNAME[11]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setLengthArray(int i, NumFacet length) {
        generatedSetterHelperImpl(length, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NumFacet insertNewLength(int i) {
        NumFacet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NumFacet) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NumFacet addNewLength() {
        NumFacet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void removeLength(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public List<NumFacet> getMinLengthList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.getMinLengthArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RestrictionTypeImpl.this.setMinLengthArray(((Integer) obj).intValue(), (NumFacet) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.insertNewMinLength(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RestrictionTypeImpl.this.removeMinLength(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RestrictionTypeImpl.this.sizeOfMinLengthArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NumFacet[] getMinLengthArray() {
        return (NumFacet[]) getXmlObjectArray(PROPERTY_QNAME[12], new NumFacet[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NumFacet getMinLengthArray(int i) {
        NumFacet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public int sizeOfMinLengthArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setMinLengthArray(NumFacet[] minLengthArray) {
        check_orphaned();
        arraySetterHelper(minLengthArray, PROPERTY_QNAME[12]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setMinLengthArray(int i, NumFacet minLength) {
        generatedSetterHelperImpl(minLength, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NumFacet insertNewMinLength(int i) {
        NumFacet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NumFacet) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NumFacet addNewMinLength() {
        NumFacet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void removeMinLength(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public List<NumFacet> getMaxLengthList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.getMaxLengthArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RestrictionTypeImpl.this.setMaxLengthArray(((Integer) obj).intValue(), (NumFacet) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.insertNewMaxLength(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RestrictionTypeImpl.this.removeMaxLength(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RestrictionTypeImpl.this.sizeOfMaxLengthArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NumFacet[] getMaxLengthArray() {
        return (NumFacet[]) getXmlObjectArray(PROPERTY_QNAME[13], new NumFacet[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NumFacet getMaxLengthArray(int i) {
        NumFacet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public int sizeOfMaxLengthArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setMaxLengthArray(NumFacet[] maxLengthArray) {
        check_orphaned();
        arraySetterHelper(maxLengthArray, PROPERTY_QNAME[13]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setMaxLengthArray(int i, NumFacet maxLength) {
        generatedSetterHelperImpl(maxLength, PROPERTY_QNAME[13], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NumFacet insertNewMaxLength(int i) {
        NumFacet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NumFacet) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NumFacet addNewMaxLength() {
        NumFacet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void removeMaxLength(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public List<NoFixedFacet> getEnumerationList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.getEnumerationArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RestrictionTypeImpl.this.setEnumerationArray(((Integer) obj).intValue(), (NoFixedFacet) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.insertNewEnumeration(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RestrictionTypeImpl.this.removeEnumeration(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RestrictionTypeImpl.this.sizeOfEnumerationArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NoFixedFacet[] getEnumerationArray() {
        return (NoFixedFacet[]) getXmlObjectArray(PROPERTY_QNAME[14], new NoFixedFacet[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NoFixedFacet getEnumerationArray(int i) {
        NoFixedFacet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NoFixedFacet) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public int sizeOfEnumerationArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setEnumerationArray(NoFixedFacet[] enumerationArray) {
        check_orphaned();
        arraySetterHelper(enumerationArray, PROPERTY_QNAME[14]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setEnumerationArray(int i, NoFixedFacet enumeration) {
        generatedSetterHelperImpl(enumeration, PROPERTY_QNAME[14], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NoFixedFacet insertNewEnumeration(int i) {
        NoFixedFacet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NoFixedFacet) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public NoFixedFacet addNewEnumeration() {
        NoFixedFacet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NoFixedFacet) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void removeEnumeration(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public List<WhiteSpaceDocument.WhiteSpace> getWhiteSpaceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.getWhiteSpaceArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RestrictionTypeImpl.this.setWhiteSpaceArray(((Integer) obj).intValue(), (WhiteSpaceDocument.WhiteSpace) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.insertNewWhiteSpace(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RestrictionTypeImpl.this.removeWhiteSpace(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RestrictionTypeImpl.this.sizeOfWhiteSpaceArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public WhiteSpaceDocument.WhiteSpace[] getWhiteSpaceArray() {
        return (WhiteSpaceDocument.WhiteSpace[]) getXmlObjectArray(PROPERTY_QNAME[15], new WhiteSpaceDocument.WhiteSpace[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public WhiteSpaceDocument.WhiteSpace getWhiteSpaceArray(int i) {
        WhiteSpaceDocument.WhiteSpace target;
        synchronized (monitor()) {
            check_orphaned();
            target = (WhiteSpaceDocument.WhiteSpace) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public int sizeOfWhiteSpaceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setWhiteSpaceArray(WhiteSpaceDocument.WhiteSpace[] whiteSpaceArray) {
        check_orphaned();
        arraySetterHelper(whiteSpaceArray, PROPERTY_QNAME[15]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setWhiteSpaceArray(int i, WhiteSpaceDocument.WhiteSpace whiteSpace) {
        generatedSetterHelperImpl(whiteSpace, PROPERTY_QNAME[15], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public WhiteSpaceDocument.WhiteSpace insertNewWhiteSpace(int i) {
        WhiteSpaceDocument.WhiteSpace target;
        synchronized (monitor()) {
            check_orphaned();
            target = (WhiteSpaceDocument.WhiteSpace) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public WhiteSpaceDocument.WhiteSpace addNewWhiteSpace() {
        WhiteSpaceDocument.WhiteSpace target;
        synchronized (monitor()) {
            check_orphaned();
            target = (WhiteSpaceDocument.WhiteSpace) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void removeWhiteSpace(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public List<PatternDocument.Pattern> getPatternList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.getPatternArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RestrictionTypeImpl.this.setPatternArray(((Integer) obj).intValue(), (PatternDocument.Pattern) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.insertNewPattern(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RestrictionTypeImpl.this.removePattern(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RestrictionTypeImpl.this.sizeOfPatternArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public PatternDocument.Pattern[] getPatternArray() {
        return (PatternDocument.Pattern[]) getXmlObjectArray(PROPERTY_QNAME[16], new PatternDocument.Pattern[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public PatternDocument.Pattern getPatternArray(int i) {
        PatternDocument.Pattern target;
        synchronized (monitor()) {
            check_orphaned();
            target = (PatternDocument.Pattern) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public int sizeOfPatternArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setPatternArray(PatternDocument.Pattern[] patternArray) {
        check_orphaned();
        arraySetterHelper(patternArray, PROPERTY_QNAME[16]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setPatternArray(int i, PatternDocument.Pattern pattern) {
        generatedSetterHelperImpl(pattern, PROPERTY_QNAME[16], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public PatternDocument.Pattern insertNewPattern(int i) {
        PatternDocument.Pattern target;
        synchronized (monitor()) {
            check_orphaned();
            target = (PatternDocument.Pattern) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public PatternDocument.Pattern addNewPattern() {
        PatternDocument.Pattern target;
        synchronized (monitor()) {
            check_orphaned();
            target = (PatternDocument.Pattern) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void removePattern(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public List<Attribute> getAttributeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.getAttributeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda66
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RestrictionTypeImpl.this.setAttributeArray(((Integer) obj).intValue(), (Attribute) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.insertNewAttribute(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RestrictionTypeImpl.this.removeAttribute(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda69
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RestrictionTypeImpl.this.sizeOfAttributeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Attribute[] getAttributeArray() {
        return (Attribute[]) getXmlObjectArray(PROPERTY_QNAME[17], new Attribute[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Attribute getAttributeArray(int i) {
        Attribute target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Attribute) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public int sizeOfAttributeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setAttributeArray(Attribute[] attributeArray) {
        check_orphaned();
        arraySetterHelper(attributeArray, PROPERTY_QNAME[17]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setAttributeArray(int i, Attribute attribute) {
        generatedSetterHelperImpl(attribute, PROPERTY_QNAME[17], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Attribute insertNewAttribute(int i) {
        Attribute target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Attribute) get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Attribute addNewAttribute() {
        Attribute target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Attribute) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void removeAttribute(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public List<AttributeGroupRef> getAttributeGroupList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.getAttributeGroupArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RestrictionTypeImpl.this.setAttributeGroupArray(((Integer) obj).intValue(), (AttributeGroupRef) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RestrictionTypeImpl.this.insertNewAttributeGroup(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RestrictionTypeImpl.this.removeAttributeGroup(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionTypeImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RestrictionTypeImpl.this.sizeOfAttributeGroupArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public AttributeGroupRef[] getAttributeGroupArray() {
        return (AttributeGroupRef[]) getXmlObjectArray(PROPERTY_QNAME[18], new AttributeGroupRef[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public AttributeGroupRef getAttributeGroupArray(int i) {
        AttributeGroupRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AttributeGroupRef) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public int sizeOfAttributeGroupArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setAttributeGroupArray(AttributeGroupRef[] attributeGroupArray) {
        check_orphaned();
        arraySetterHelper(attributeGroupArray, PROPERTY_QNAME[18]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setAttributeGroupArray(int i, AttributeGroupRef attributeGroup) {
        generatedSetterHelperImpl(attributeGroup, PROPERTY_QNAME[18], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public AttributeGroupRef insertNewAttributeGroup(int i) {
        AttributeGroupRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AttributeGroupRef) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public AttributeGroupRef addNewAttributeGroup() {
        AttributeGroupRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AttributeGroupRef) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void removeAttributeGroup(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Wildcard getAnyAttribute() {
        Wildcard wildcard;
        synchronized (monitor()) {
            check_orphaned();
            Wildcard target = (Wildcard) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            wildcard = target == null ? null : target;
        }
        return wildcard;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public boolean isSetAnyAttribute() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[19]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setAnyAttribute(Wildcard anyAttribute) {
        generatedSetterHelperImpl(anyAttribute, PROPERTY_QNAME[19], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public Wildcard addNewAnyAttribute() {
        Wildcard target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Wildcard) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void unsetAnyAttribute() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public QName getBase() {
        QName qNameValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            qNameValue = target == null ? null : target.getQNameValue();
        }
        return qNameValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public XmlQName xgetBase() {
        XmlQName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void setBase(QName base) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.setQNameValue(base);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType
    public void xsetBase(XmlQName base) {
        synchronized (monitor()) {
            check_orphaned();
            XmlQName target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (XmlQName) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.set(base);
        }
    }
}
