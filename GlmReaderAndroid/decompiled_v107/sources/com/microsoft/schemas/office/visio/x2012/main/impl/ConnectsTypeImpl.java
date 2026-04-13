package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.ConnectType;
import com.microsoft.schemas.office.visio.x2012.main.ConnectsType;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class ConnectsTypeImpl extends XmlComplexContentImpl implements ConnectsType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "Connect")};
    private static final long serialVersionUID = 1;

    public ConnectsTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ConnectsType
    public List<ConnectType> getConnectList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.ConnectsTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ConnectsTypeImpl.this.getConnectArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.ConnectsTypeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ConnectsTypeImpl.this.setConnectArray(((Integer) obj).intValue(), (ConnectType) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.ConnectsTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ConnectsTypeImpl.this.insertNewConnect(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.ConnectsTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ConnectsTypeImpl.this.removeConnect(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.ConnectsTypeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(ConnectsTypeImpl.this.sizeOfConnectArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ConnectsType
    public ConnectType[] getConnectArray() {
        return (ConnectType[]) getXmlObjectArray(PROPERTY_QNAME[0], new ConnectType[0]);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ConnectsType
    public ConnectType getConnectArray(int i) {
        ConnectType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ConnectType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ConnectsType
    public int sizeOfConnectArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ConnectsType
    public void setConnectArray(ConnectType[] connectArray) {
        check_orphaned();
        arraySetterHelper(connectArray, PROPERTY_QNAME[0]);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ConnectsType
    public void setConnectArray(int i, ConnectType connect) {
        generatedSetterHelperImpl(connect, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ConnectsType
    public ConnectType insertNewConnect(int i) {
        ConnectType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ConnectType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ConnectsType
    public ConnectType addNewConnect() {
        ConnectType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ConnectType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ConnectsType
    public void removeConnect(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
