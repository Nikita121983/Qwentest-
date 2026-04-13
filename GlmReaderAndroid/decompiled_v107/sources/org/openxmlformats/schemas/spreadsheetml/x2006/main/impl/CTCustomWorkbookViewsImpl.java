package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookView;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookViews;

/* loaded from: classes12.dex */
public class CTCustomWorkbookViewsImpl extends XmlComplexContentImpl implements CTCustomWorkbookViews {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "customWorkbookView")};
    private static final long serialVersionUID = 1;

    public CTCustomWorkbookViewsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookViews
    public List<CTCustomWorkbookView> getCustomWorkbookViewList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCustomWorkbookViewsImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTCustomWorkbookViewsImpl.this.getCustomWorkbookViewArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCustomWorkbookViewsImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTCustomWorkbookViewsImpl.this.setCustomWorkbookViewArray(((Integer) obj).intValue(), (CTCustomWorkbookView) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCustomWorkbookViewsImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTCustomWorkbookViewsImpl.this.insertNewCustomWorkbookView(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCustomWorkbookViewsImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTCustomWorkbookViewsImpl.this.removeCustomWorkbookView(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCustomWorkbookViewsImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTCustomWorkbookViewsImpl.this.sizeOfCustomWorkbookViewArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookViews
    public CTCustomWorkbookView[] getCustomWorkbookViewArray() {
        return (CTCustomWorkbookView[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTCustomWorkbookView[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookViews
    public CTCustomWorkbookView getCustomWorkbookViewArray(int i) {
        CTCustomWorkbookView target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomWorkbookView) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookViews
    public int sizeOfCustomWorkbookViewArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookViews
    public void setCustomWorkbookViewArray(CTCustomWorkbookView[] customWorkbookViewArray) {
        check_orphaned();
        arraySetterHelper(customWorkbookViewArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookViews
    public void setCustomWorkbookViewArray(int i, CTCustomWorkbookView customWorkbookView) {
        generatedSetterHelperImpl(customWorkbookView, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookViews
    public CTCustomWorkbookView insertNewCustomWorkbookView(int i) {
        CTCustomWorkbookView target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomWorkbookView) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookViews
    public CTCustomWorkbookView addNewCustomWorkbookView() {
        CTCustomWorkbookView target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomWorkbookView) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookViews
    public void removeCustomWorkbookView(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
