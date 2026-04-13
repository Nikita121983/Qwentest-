package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSiteList;

/* loaded from: classes11.dex */
public class CTConnectionSiteListImpl extends XmlComplexContentImpl implements CTConnectionSiteList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "cxn")};
    private static final long serialVersionUID = 1;

    public CTConnectionSiteListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSiteList
    public List<CTConnectionSite> getCxnList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTConnectionSiteListImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTConnectionSiteListImpl.this.getCxnArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTConnectionSiteListImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTConnectionSiteListImpl.this.setCxnArray(((Integer) obj).intValue(), (CTConnectionSite) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTConnectionSiteListImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTConnectionSiteListImpl.this.insertNewCxn(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTConnectionSiteListImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTConnectionSiteListImpl.this.removeCxn(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTConnectionSiteListImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTConnectionSiteListImpl.this.sizeOfCxnArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSiteList
    public CTConnectionSite[] getCxnArray() {
        return (CTConnectionSite[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTConnectionSite[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSiteList
    public CTConnectionSite getCxnArray(int i) {
        CTConnectionSite target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTConnectionSite) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSiteList
    public int sizeOfCxnArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSiteList
    public void setCxnArray(CTConnectionSite[] cxnArray) {
        check_orphaned();
        arraySetterHelper(cxnArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSiteList
    public void setCxnArray(int i, CTConnectionSite cxn) {
        generatedSetterHelperImpl(cxn, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSiteList
    public CTConnectionSite insertNewCxn(int i) {
        CTConnectionSite target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTConnectionSite) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSiteList
    public CTConnectionSite addNewCxn() {
        CTConnectionSite target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTConnectionSite) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSiteList
    public void removeCxn(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
