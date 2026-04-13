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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews;

/* loaded from: classes12.dex */
public class CTCustomSheetViewsImpl extends XmlComplexContentImpl implements CTCustomSheetViews {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "customSheetView")};
    private static final long serialVersionUID = 1;

    public CTCustomSheetViewsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews
    public List<CTCustomSheetView> getCustomSheetViewList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCustomSheetViewsImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTCustomSheetViewsImpl.this.getCustomSheetViewArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCustomSheetViewsImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTCustomSheetViewsImpl.this.setCustomSheetViewArray(((Integer) obj).intValue(), (CTCustomSheetView) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCustomSheetViewsImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTCustomSheetViewsImpl.this.insertNewCustomSheetView(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCustomSheetViewsImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTCustomSheetViewsImpl.this.removeCustomSheetView(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCustomSheetViewsImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTCustomSheetViewsImpl.this.sizeOfCustomSheetViewArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews
    public CTCustomSheetView[] getCustomSheetViewArray() {
        return (CTCustomSheetView[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTCustomSheetView[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews
    public CTCustomSheetView getCustomSheetViewArray(int i) {
        CTCustomSheetView target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomSheetView) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews
    public int sizeOfCustomSheetViewArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews
    public void setCustomSheetViewArray(CTCustomSheetView[] customSheetViewArray) {
        check_orphaned();
        arraySetterHelper(customSheetViewArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews
    public void setCustomSheetViewArray(int i, CTCustomSheetView customSheetView) {
        generatedSetterHelperImpl(customSheetView, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews
    public CTCustomSheetView insertNewCustomSheetView(int i) {
        CTCustomSheetView target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomSheetView) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews
    public CTCustomSheetView addNewCustomSheetView() {
        CTCustomSheetView target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomSheetView) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews
    public void removeCustomSheetView(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
