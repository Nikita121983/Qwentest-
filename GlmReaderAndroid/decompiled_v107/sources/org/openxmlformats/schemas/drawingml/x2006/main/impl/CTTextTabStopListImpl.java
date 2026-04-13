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
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList;

/* loaded from: classes11.dex */
public class CTTextTabStopListImpl extends XmlComplexContentImpl implements CTTextTabStopList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "tab")};
    private static final long serialVersionUID = 1;

    public CTTextTabStopListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList
    public List<CTTextTabStop> getTabList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextTabStopListImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTextTabStopListImpl.this.getTabArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextTabStopListImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTextTabStopListImpl.this.setTabArray(((Integer) obj).intValue(), (CTTextTabStop) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextTabStopListImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTextTabStopListImpl.this.insertNewTab(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextTabStopListImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTextTabStopListImpl.this.removeTab(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextTabStopListImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTextTabStopListImpl.this.sizeOfTabArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList
    public CTTextTabStop[] getTabArray() {
        return (CTTextTabStop[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTTextTabStop[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList
    public CTTextTabStop getTabArray(int i) {
        CTTextTabStop target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextTabStop) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList
    public int sizeOfTabArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList
    public void setTabArray(CTTextTabStop[] tabArray) {
        check_orphaned();
        arraySetterHelper(tabArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList
    public void setTabArray(int i, CTTextTabStop tab) {
        generatedSetterHelperImpl(tab, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList
    public CTTextTabStop insertNewTab(int i) {
        CTTextTabStop target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextTabStop) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList
    public CTTextTabStop addNewTab() {
        CTTextTabStop target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextTabStop) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList
    public void removeTab(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
