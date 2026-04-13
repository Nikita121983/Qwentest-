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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets;

/* loaded from: classes12.dex */
public class CTSheetsImpl extends XmlComplexContentImpl implements CTSheets {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "sheet")};
    private static final long serialVersionUID = 1;

    public CTSheetsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets
    public List<CTSheet> getSheetList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetsImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSheetsImpl.this.getSheetArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetsImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSheetsImpl.this.setSheetArray(((Integer) obj).intValue(), (CTSheet) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetsImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSheetsImpl.this.insertNewSheet(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetsImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSheetsImpl.this.removeSheet(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetsImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSheetsImpl.this.sizeOfSheetArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets
    public CTSheet[] getSheetArray() {
        return (CTSheet[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTSheet[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets
    public CTSheet getSheetArray(int i) {
        CTSheet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSheet) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets
    public int sizeOfSheetArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets
    public void setSheetArray(CTSheet[] sheetArray) {
        check_orphaned();
        arraySetterHelper(sheetArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets
    public void setSheetArray(int i, CTSheet sheet) {
        generatedSetterHelperImpl(sheet, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets
    public CTSheet insertNewSheet(int i) {
        CTSheet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSheet) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets
    public CTSheet addNewSheet() {
        CTSheet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSheet) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets
    public void removeSheet(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
