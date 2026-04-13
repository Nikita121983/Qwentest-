package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.AllNNI;
import org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.Group;
import org.apache.xmlbeans.impl.xb.xsdschema.GroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalElement;

/* loaded from: classes11.dex */
public class GroupImpl extends AnnotatedImpl implements Group {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "element"), new QName("http://www.w3.org/2001/XMLSchema", "group"), new QName("http://www.w3.org/2001/XMLSchema", "all"), new QName("http://www.w3.org/2001/XMLSchema", "choice"), new QName("http://www.w3.org/2001/XMLSchema", "sequence"), new QName("http://www.w3.org/2001/XMLSchema", Languages.ANY), new QName("", "name"), new QName("", "ref"), new QName("", "minOccurs"), new QName("", "maxOccurs")};
    private static final long serialVersionUID = 1;

    public GroupImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public List<LocalElement> getElementList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GroupImpl.this.getElementArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    GroupImpl.this.setElementArray(((Integer) obj).intValue(), (LocalElement) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GroupImpl.this.insertNewElement(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GroupImpl.this.removeElement(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(GroupImpl.this.sizeOfElementArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public LocalElement[] getElementArray() {
        return (LocalElement[]) getXmlObjectArray(PROPERTY_QNAME[0], new LocalElement[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public LocalElement getElementArray(int i) {
        LocalElement target;
        synchronized (monitor()) {
            check_orphaned();
            target = (LocalElement) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public int sizeOfElementArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setElementArray(LocalElement[] elementArray) {
        check_orphaned();
        arraySetterHelper(elementArray, PROPERTY_QNAME[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setElementArray(int i, LocalElement element) {
        generatedSetterHelperImpl(element, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public LocalElement insertNewElement(int i) {
        LocalElement target;
        synchronized (monitor()) {
            check_orphaned();
            target = (LocalElement) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public LocalElement addNewElement() {
        LocalElement target;
        synchronized (monitor()) {
            check_orphaned();
            target = (LocalElement) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void removeElement(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public List<GroupRef> getGroupList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GroupImpl.this.getGroupArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    GroupImpl.this.setGroupArray(((Integer) obj).intValue(), (GroupRef) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GroupImpl.this.insertNewGroup(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GroupImpl.this.removeGroup(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(GroupImpl.this.sizeOfGroupArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public GroupRef[] getGroupArray() {
        return (GroupRef[]) getXmlObjectArray(PROPERTY_QNAME[1], new GroupRef[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public GroupRef getGroupArray(int i) {
        GroupRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (GroupRef) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public int sizeOfGroupArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setGroupArray(GroupRef[] groupArray) {
        check_orphaned();
        arraySetterHelper(groupArray, PROPERTY_QNAME[1]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setGroupArray(int i, GroupRef group) {
        generatedSetterHelperImpl(group, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public GroupRef insertNewGroup(int i) {
        GroupRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (GroupRef) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public GroupRef addNewGroup() {
        GroupRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (GroupRef) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void removeGroup(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public List<All> getAllList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GroupImpl.this.getAllArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    GroupImpl.this.setAllArray(((Integer) obj).intValue(), (All) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GroupImpl.this.insertNewAll(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GroupImpl.this.removeAll(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(GroupImpl.this.sizeOfAllArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public All[] getAllArray() {
        return (All[]) getXmlObjectArray(PROPERTY_QNAME[2], new All[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public All getAllArray(int i) {
        All target;
        synchronized (monitor()) {
            check_orphaned();
            target = (All) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public int sizeOfAllArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setAllArray(All[] allArray) {
        check_orphaned();
        arraySetterHelper(allArray, PROPERTY_QNAME[2]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setAllArray(int i, All all) {
        generatedSetterHelperImpl(all, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public All insertNewAll(int i) {
        All target;
        synchronized (monitor()) {
            check_orphaned();
            target = (All) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public All addNewAll() {
        All target;
        synchronized (monitor()) {
            check_orphaned();
            target = (All) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void removeAll(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public List<ExplicitGroup> getChoiceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GroupImpl.this.getChoiceArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    GroupImpl.this.setChoiceArray(((Integer) obj).intValue(), (ExplicitGroup) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GroupImpl.this.insertNewChoice(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GroupImpl.this.removeChoice(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(GroupImpl.this.sizeOfChoiceArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public ExplicitGroup[] getChoiceArray() {
        return (ExplicitGroup[]) getXmlObjectArray(PROPERTY_QNAME[3], new ExplicitGroup[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public ExplicitGroup getChoiceArray(int i) {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public int sizeOfChoiceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setChoiceArray(ExplicitGroup[] choiceArray) {
        check_orphaned();
        arraySetterHelper(choiceArray, PROPERTY_QNAME[3]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setChoiceArray(int i, ExplicitGroup choice) {
        generatedSetterHelperImpl(choice, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public ExplicitGroup insertNewChoice(int i) {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public ExplicitGroup addNewChoice() {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void removeChoice(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public List<ExplicitGroup> getSequenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GroupImpl.this.getSequenceArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    GroupImpl.this.setSequenceArray(((Integer) obj).intValue(), (ExplicitGroup) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GroupImpl.this.insertNewSequence(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GroupImpl.this.removeSequence(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(GroupImpl.this.sizeOfSequenceArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public ExplicitGroup[] getSequenceArray() {
        return (ExplicitGroup[]) getXmlObjectArray(PROPERTY_QNAME[4], new ExplicitGroup[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public ExplicitGroup getSequenceArray(int i) {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public int sizeOfSequenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setSequenceArray(ExplicitGroup[] sequenceArray) {
        check_orphaned();
        arraySetterHelper(sequenceArray, PROPERTY_QNAME[4]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setSequenceArray(int i, ExplicitGroup sequence) {
        generatedSetterHelperImpl(sequence, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public ExplicitGroup insertNewSequence(int i) {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public ExplicitGroup addNewSequence() {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void removeSequence(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public List<AnyDocument.Any> getAnyList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GroupImpl.this.getAnyArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    GroupImpl.this.setAnyArray(((Integer) obj).intValue(), (AnyDocument.Any) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GroupImpl.this.insertNewAny(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GroupImpl.this.removeAny(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(GroupImpl.this.sizeOfAnyArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public AnyDocument.Any[] getAnyArray() {
        return (AnyDocument.Any[]) getXmlObjectArray(PROPERTY_QNAME[5], new AnyDocument.Any[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public AnyDocument.Any getAnyArray(int i) {
        AnyDocument.Any target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AnyDocument.Any) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public int sizeOfAnyArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setAnyArray(AnyDocument.Any[] anyArray) {
        check_orphaned();
        arraySetterHelper(anyArray, PROPERTY_QNAME[5]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setAnyArray(int i, AnyDocument.Any any) {
        generatedSetterHelperImpl(any, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public AnyDocument.Any insertNewAny(int i) {
        AnyDocument.Any target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AnyDocument.Any) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public AnyDocument.Any addNewAny() {
        AnyDocument.Any target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AnyDocument.Any) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void removeAny(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public XmlNCName xgetName() {
        XmlNCName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlNCName) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void xsetName(XmlNCName name) {
        synchronized (monitor()) {
            check_orphaned();
            XmlNCName target = (XmlNCName) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (XmlNCName) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(name);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public QName getRef() {
        QName qNameValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            qNameValue = target == null ? null : target.getQNameValue();
        }
        return qNameValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public XmlQName xgetRef() {
        XmlQName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public boolean isSetRef() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setRef(QName ref) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setQNameValue(ref);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void xsetRef(XmlQName ref) {
        synchronized (monitor()) {
            check_orphaned();
            XmlQName target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlQName) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(ref);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void unsetRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public BigInteger getMinOccurs() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[8]);
            }
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public XmlNonNegativeInteger xgetMinOccurs() {
        XmlNonNegativeInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlNonNegativeInteger) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlNonNegativeInteger) get_default_attribute_value(PROPERTY_QNAME[8]);
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public boolean isSetMinOccurs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setMinOccurs(BigInteger minOccurs) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setBigIntegerValue(minOccurs);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void xsetMinOccurs(XmlNonNegativeInteger minOccurs) {
        synchronized (monitor()) {
            check_orphaned();
            XmlNonNegativeInteger target = (XmlNonNegativeInteger) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlNonNegativeInteger) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(minOccurs);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void unsetMinOccurs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public Object getMaxOccurs() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public AllNNI xgetMaxOccurs() {
        AllNNI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AllNNI) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (AllNNI) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public boolean isSetMaxOccurs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void setMaxOccurs(Object maxOccurs) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setObjectValue(maxOccurs);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void xsetMaxOccurs(AllNNI maxOccurs) {
        synchronized (monitor()) {
            check_orphaned();
            AllNNI target = (AllNNI) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (AllNNI) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(maxOccurs);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    public void unsetMaxOccurs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }
}
