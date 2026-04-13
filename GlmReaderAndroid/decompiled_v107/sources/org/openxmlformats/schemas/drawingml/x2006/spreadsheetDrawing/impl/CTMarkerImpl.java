package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STColID;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STRowID;

/* loaded from: classes11.dex */
public class CTMarkerImpl extends XmlComplexContentImpl implements CTMarker {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "col"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "colOff"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "row"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "rowOff")};
    private static final long serialVersionUID = 1;

    public CTMarkerImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker
    public int getCol() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker
    public STColID xgetCol() {
        STColID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColID) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker
    public void setCol(int col) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.setIntValue(col);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker
    public void xsetCol(STColID col) {
        synchronized (monitor()) {
            check_orphaned();
            STColID target = (STColID) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (STColID) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.set(col);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker
    public Object getColOff() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker
    public STCoordinate xgetColOff() {
        STCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCoordinate) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker
    public void setColOff(Object colOff) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setObjectValue(colOff);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker
    public void xsetColOff(STCoordinate colOff) {
        synchronized (monitor()) {
            check_orphaned();
            STCoordinate target = (STCoordinate) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (STCoordinate) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(colOff);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker
    public int getRow() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker
    public STRowID xgetRow() {
        STRowID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRowID) get_store().find_element_user(PROPERTY_QNAME[2], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker
    public void setRow(int row) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.setIntValue(row);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker
    public void xsetRow(STRowID row) {
        synchronized (monitor()) {
            check_orphaned();
            STRowID target = (STRowID) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (STRowID) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.set(row);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker
    public Object getRowOff() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker
    public STCoordinate xgetRowOff() {
        STCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCoordinate) get_store().find_element_user(PROPERTY_QNAME[3], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker
    public void setRowOff(Object rowOff) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            target.setObjectValue(rowOff);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker
    public void xsetRowOff(STCoordinate rowOff) {
        synchronized (monitor()) {
            check_orphaned();
            STCoordinate target = (STCoordinate) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (target == null) {
                target = (STCoordinate) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            target.set(rowOff);
        }
    }
}
