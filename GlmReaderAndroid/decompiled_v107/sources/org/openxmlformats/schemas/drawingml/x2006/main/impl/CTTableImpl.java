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
import org.openxmlformats.schemas.drawingml.x2006.main.CTTable;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableGrid;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableRow;

/* loaded from: classes11.dex */
public class CTTableImpl extends XmlComplexContentImpl implements CTTable {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "tblPr"), new QName(XSSFRelation.NS_DRAWINGML, "tblGrid"), new QName(XSSFRelation.NS_DRAWINGML, "tr")};
    private static final long serialVersionUID = 1;

    public CTTableImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTable
    public CTTableProperties getTblPr() {
        CTTableProperties cTTableProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTableProperties target = (CTTableProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTableProperties = target == null ? null : target;
        }
        return cTTableProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTable
    public boolean isSetTblPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTable
    public void setTblPr(CTTableProperties tblPr) {
        generatedSetterHelperImpl(tblPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTable
    public CTTableProperties addNewTblPr() {
        CTTableProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTableProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTable
    public void unsetTblPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTable
    public CTTableGrid getTblGrid() {
        CTTableGrid cTTableGrid;
        synchronized (monitor()) {
            check_orphaned();
            CTTableGrid target = (CTTableGrid) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTableGrid = target == null ? null : target;
        }
        return cTTableGrid;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTable
    public void setTblGrid(CTTableGrid tblGrid) {
        generatedSetterHelperImpl(tblGrid, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTable
    public CTTableGrid addNewTblGrid() {
        CTTableGrid target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTableGrid) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTable
    public List<CTTableRow> getTrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTableImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTableImpl.this.getTrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTableImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTableImpl.this.setTrArray(((Integer) obj).intValue(), (CTTableRow) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTableImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTableImpl.this.insertNewTr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTableImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTableImpl.this.removeTr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTableImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTableImpl.this.sizeOfTrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTable
    public CTTableRow[] getTrArray() {
        return (CTTableRow[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTTableRow[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTable
    public CTTableRow getTrArray(int i) {
        CTTableRow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTableRow) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTable
    public int sizeOfTrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTable
    public void setTrArray(CTTableRow[] trArray) {
        check_orphaned();
        arraySetterHelper(trArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTable
    public void setTrArray(int i, CTTableRow tr) {
        generatedSetterHelperImpl(tr, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTable
    public CTTableRow insertNewTr(int i) {
        CTTableRow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTableRow) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTable
    public CTTableRow addNewTr() {
        CTTableRow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTableRow) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTable
    public void removeTr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }
}
