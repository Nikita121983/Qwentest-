package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.RealGroup;

/* loaded from: classes11.dex */
public class RealGroupImpl extends GroupImpl implements RealGroup {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "all"), new QName("http://www.w3.org/2001/XMLSchema", "choice"), new QName("http://www.w3.org/2001/XMLSchema", "sequence")};
    private static final long serialVersionUID = 1;

    public RealGroupImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public List<All> getAllList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RealGroupImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RealGroupImpl.this.getAllArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RealGroupImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RealGroupImpl.this.setAllArray(((Integer) obj).intValue(), (All) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RealGroupImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RealGroupImpl.this.insertNewAll(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RealGroupImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RealGroupImpl.this.removeAll(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RealGroupImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RealGroupImpl.this.sizeOfAllArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public All[] getAllArray() {
        return (All[]) getXmlObjectArray(PROPERTY_QNAME[0], new All[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public All getAllArray(int i) {
        All target;
        synchronized (monitor()) {
            check_orphaned();
            target = (All) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public int sizeOfAllArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setAllArray(All[] allArray) {
        check_orphaned();
        arraySetterHelper(allArray, PROPERTY_QNAME[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setAllArray(int i, All all) {
        generatedSetterHelperImpl(all, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public All insertNewAll(int i) {
        All target;
        synchronized (monitor()) {
            check_orphaned();
            target = (All) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public All addNewAll() {
        All target;
        synchronized (monitor()) {
            check_orphaned();
            target = (All) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void removeAll(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public List<ExplicitGroup> getChoiceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RealGroupImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RealGroupImpl.this.getChoiceArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RealGroupImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RealGroupImpl.this.setChoiceArray(((Integer) obj).intValue(), (ExplicitGroup) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RealGroupImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RealGroupImpl.this.insertNewChoice(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RealGroupImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RealGroupImpl.this.removeChoice(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RealGroupImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RealGroupImpl.this.sizeOfChoiceArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public ExplicitGroup[] getChoiceArray() {
        return (ExplicitGroup[]) getXmlObjectArray(PROPERTY_QNAME[1], new ExplicitGroup[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public ExplicitGroup getChoiceArray(int i) {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public int sizeOfChoiceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setChoiceArray(ExplicitGroup[] choiceArray) {
        check_orphaned();
        arraySetterHelper(choiceArray, PROPERTY_QNAME[1]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setChoiceArray(int i, ExplicitGroup choice) {
        generatedSetterHelperImpl(choice, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public ExplicitGroup insertNewChoice(int i) {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public ExplicitGroup addNewChoice() {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void removeChoice(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public List<ExplicitGroup> getSequenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RealGroupImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RealGroupImpl.this.getSequenceArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RealGroupImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RealGroupImpl.this.setSequenceArray(((Integer) obj).intValue(), (ExplicitGroup) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RealGroupImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RealGroupImpl.this.insertNewSequence(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RealGroupImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RealGroupImpl.this.removeSequence(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RealGroupImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RealGroupImpl.this.sizeOfSequenceArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public ExplicitGroup[] getSequenceArray() {
        return (ExplicitGroup[]) getXmlObjectArray(PROPERTY_QNAME[2], new ExplicitGroup[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public ExplicitGroup getSequenceArray(int i) {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public int sizeOfSequenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setSequenceArray(ExplicitGroup[] sequenceArray) {
        check_orphaned();
        arraySetterHelper(sequenceArray, PROPERTY_QNAME[2]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setSequenceArray(int i, ExplicitGroup sequence) {
        generatedSetterHelperImpl(sequence, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public ExplicitGroup insertNewSequence(int i) {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public ExplicitGroup addNewSequence() {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void removeSequence(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }
}
