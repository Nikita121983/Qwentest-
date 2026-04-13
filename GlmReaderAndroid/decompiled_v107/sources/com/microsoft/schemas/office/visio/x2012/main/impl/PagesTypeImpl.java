package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.PageType;
import com.microsoft.schemas.office.visio.x2012.main.PagesType;
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
public class PagesTypeImpl extends XmlComplexContentImpl implements PagesType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "Page")};
    private static final long serialVersionUID = 1;

    public PagesTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PagesType
    public List<PageType> getPageList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.PagesTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return PagesTypeImpl.this.getPageArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.PagesTypeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    PagesTypeImpl.this.setPageArray(((Integer) obj).intValue(), (PageType) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.PagesTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return PagesTypeImpl.this.insertNewPage(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.PagesTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PagesTypeImpl.this.removePage(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.PagesTypeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(PagesTypeImpl.this.sizeOfPageArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PagesType
    public PageType[] getPageArray() {
        return (PageType[]) getXmlObjectArray(PROPERTY_QNAME[0], new PageType[0]);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PagesType
    public PageType getPageArray(int i) {
        PageType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (PageType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PagesType
    public int sizeOfPageArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PagesType
    public void setPageArray(PageType[] pageArray) {
        check_orphaned();
        arraySetterHelper(pageArray, PROPERTY_QNAME[0]);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PagesType
    public void setPageArray(int i, PageType page) {
        generatedSetterHelperImpl(page, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PagesType
    public PageType insertNewPage(int i) {
        PageType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (PageType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PagesType
    public PageType addNewPage() {
        PageType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (PageType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PagesType
    public void removePage(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
