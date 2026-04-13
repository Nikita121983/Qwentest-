package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.MasterShortcutType;
import com.microsoft.schemas.office.visio.x2012.main.MasterType;
import com.microsoft.schemas.office.visio.x2012.main.MastersType;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class MastersTypeImpl extends XmlComplexContentImpl implements MastersType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "Master"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "MasterShortcut")};
    private static final long serialVersionUID = 1;

    public MastersTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public List<MasterType> getMasterList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.MastersTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return MastersTypeImpl.this.getMasterArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.MastersTypeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    MastersTypeImpl.this.setMasterArray(((Integer) obj).intValue(), (MasterType) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.MastersTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return MastersTypeImpl.this.insertNewMaster(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.MastersTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    MastersTypeImpl.this.removeMaster(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.MastersTypeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(MastersTypeImpl.this.sizeOfMasterArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public MasterType[] getMasterArray() {
        return (MasterType[]) getXmlObjectArray(PROPERTY_QNAME[0], new MasterType[0]);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public MasterType getMasterArray(int i) {
        MasterType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (MasterType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public int sizeOfMasterArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public void setMasterArray(MasterType[] masterArray) {
        check_orphaned();
        arraySetterHelper(masterArray, PROPERTY_QNAME[0]);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public void setMasterArray(int i, MasterType master) {
        generatedSetterHelperImpl(master, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public MasterType insertNewMaster(int i) {
        MasterType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (MasterType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public MasterType addNewMaster() {
        MasterType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (MasterType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public void removeMaster(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public List<MasterShortcutType> getMasterShortcutList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.MastersTypeImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return MastersTypeImpl.this.getMasterShortcutArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.MastersTypeImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    MastersTypeImpl.this.setMasterShortcutArray(((Integer) obj).intValue(), (MasterShortcutType) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.MastersTypeImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return MastersTypeImpl.this.insertNewMasterShortcut(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.MastersTypeImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    MastersTypeImpl.this.removeMasterShortcut(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.MastersTypeImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(MastersTypeImpl.this.sizeOfMasterShortcutArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public MasterShortcutType[] getMasterShortcutArray() {
        return getXmlObjectArray(PROPERTY_QNAME[1], (XmlObject[]) new MasterShortcutType[0]);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public MasterShortcutType getMasterShortcutArray(int i) {
        MasterShortcutType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public int sizeOfMasterShortcutArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public void setMasterShortcutArray(MasterShortcutType[] masterShortcutArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) masterShortcutArray, PROPERTY_QNAME[1]);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public void setMasterShortcutArray(int i, MasterShortcutType masterShortcut) {
        generatedSetterHelperImpl(masterShortcut, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public MasterShortcutType insertNewMasterShortcut(int i) {
        MasterShortcutType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public MasterShortcutType addNewMasterShortcut() {
        MasterShortcutType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersType
    public void removeMasterShortcut(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }
}
