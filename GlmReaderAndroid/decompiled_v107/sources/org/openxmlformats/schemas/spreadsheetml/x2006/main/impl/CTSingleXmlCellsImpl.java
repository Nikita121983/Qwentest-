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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCells;

/* loaded from: classes12.dex */
public class CTSingleXmlCellsImpl extends XmlComplexContentImpl implements CTSingleXmlCells {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "singleXmlCell")};
    private static final long serialVersionUID = 1;

    public CTSingleXmlCellsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCells
    public List<CTSingleXmlCell> getSingleXmlCellList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSingleXmlCellsImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSingleXmlCellsImpl.this.getSingleXmlCellArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSingleXmlCellsImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSingleXmlCellsImpl.this.setSingleXmlCellArray(((Integer) obj).intValue(), (CTSingleXmlCell) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSingleXmlCellsImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSingleXmlCellsImpl.this.insertNewSingleXmlCell(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSingleXmlCellsImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSingleXmlCellsImpl.this.removeSingleXmlCell(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSingleXmlCellsImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSingleXmlCellsImpl.this.sizeOfSingleXmlCellArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCells
    public CTSingleXmlCell[] getSingleXmlCellArray() {
        return (CTSingleXmlCell[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTSingleXmlCell[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCells
    public CTSingleXmlCell getSingleXmlCellArray(int i) {
        CTSingleXmlCell target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSingleXmlCell) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCells
    public int sizeOfSingleXmlCellArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCells
    public void setSingleXmlCellArray(CTSingleXmlCell[] singleXmlCellArray) {
        check_orphaned();
        arraySetterHelper(singleXmlCellArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCells
    public void setSingleXmlCellArray(int i, CTSingleXmlCell singleXmlCell) {
        generatedSetterHelperImpl(singleXmlCell, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCells
    public CTSingleXmlCell insertNewSingleXmlCell(int i) {
        CTSingleXmlCell target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSingleXmlCell) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCells
    public CTSingleXmlCell addNewSingleXmlCell() {
        CTSingleXmlCell target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSingleXmlCell) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCells
    public void removeSingleXmlCell(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
