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
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCol;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableGrid;

/* loaded from: classes11.dex */
public class CTTableGridImpl extends XmlComplexContentImpl implements CTTableGrid {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "gridCol")};
    private static final long serialVersionUID = 1;

    public CTTableGridImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableGrid
    public List<CTTableCol> getGridColList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTableGridImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTableGridImpl.this.getGridColArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTableGridImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTableGridImpl.this.setGridColArray(((Integer) obj).intValue(), (CTTableCol) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTableGridImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTableGridImpl.this.insertNewGridCol(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTableGridImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTableGridImpl.this.removeGridCol(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTableGridImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTableGridImpl.this.sizeOfGridColArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableGrid
    public CTTableCol[] getGridColArray() {
        return (CTTableCol[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTTableCol[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableGrid
    public CTTableCol getGridColArray(int i) {
        CTTableCol target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTableCol) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableGrid
    public int sizeOfGridColArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableGrid
    public void setGridColArray(CTTableCol[] gridColArray) {
        check_orphaned();
        arraySetterHelper(gridColArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableGrid
    public void setGridColArray(int i, CTTableCol gridCol) {
        generatedSetterHelperImpl(gridCol, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableGrid
    public CTTableCol insertNewGridCol(int i) {
        CTTableCol target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTableCol) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableGrid
    public CTTableCol addNewGridCol() {
        CTTableCol target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTableCol) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableGrid
    public void removeGridCol(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
