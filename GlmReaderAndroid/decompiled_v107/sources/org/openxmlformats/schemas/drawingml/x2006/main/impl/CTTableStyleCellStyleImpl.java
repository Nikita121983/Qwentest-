package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCell3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellBorderStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle;

/* loaded from: classes11.dex */
public class CTTableStyleCellStyleImpl extends XmlComplexContentImpl implements CTTableStyleCellStyle {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "tcBdr"), new QName(XSSFRelation.NS_DRAWINGML, "fill"), new QName(XSSFRelation.NS_DRAWINGML, "fillRef"), new QName(XSSFRelation.NS_DRAWINGML, "cell3D")};
    private static final long serialVersionUID = 1;

    public CTTableStyleCellStyleImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public CTTableCellBorderStyle getTcBdr() {
        CTTableCellBorderStyle cTTableCellBorderStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTableCellBorderStyle target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTableCellBorderStyle = target == null ? null : target;
        }
        return cTTableCellBorderStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public boolean isSetTcBdr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public void setTcBdr(CTTableCellBorderStyle tcBdr) {
        generatedSetterHelperImpl(tcBdr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public CTTableCellBorderStyle addNewTcBdr() {
        CTTableCellBorderStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public void unsetTcBdr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public CTFillProperties getFill() {
        CTFillProperties cTFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTFillProperties target = (CTFillProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTFillProperties = target == null ? null : target;
        }
        return cTFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public boolean isSetFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public void setFill(CTFillProperties fill) {
        generatedSetterHelperImpl(fill, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public CTFillProperties addNewFill() {
        CTFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFillProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public void unsetFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public CTStyleMatrixReference getFillRef() {
        CTStyleMatrixReference cTStyleMatrixReference;
        synchronized (monitor()) {
            check_orphaned();
            CTStyleMatrixReference target = (CTStyleMatrixReference) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTStyleMatrixReference = target == null ? null : target;
        }
        return cTStyleMatrixReference;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public boolean isSetFillRef() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public void setFillRef(CTStyleMatrixReference fillRef) {
        generatedSetterHelperImpl(fillRef, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public CTStyleMatrixReference addNewFillRef() {
        CTStyleMatrixReference target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStyleMatrixReference) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public void unsetFillRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public CTCell3D getCell3D() {
        CTCell3D cTCell3D;
        synchronized (monitor()) {
            check_orphaned();
            CTCell3D target = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTCell3D = target == null ? null : target;
        }
        return cTCell3D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public boolean isSetCell3D() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public void setCell3D(CTCell3D cell3D) {
        generatedSetterHelperImpl(cell3D, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public CTCell3D addNewCell3D() {
        CTCell3D target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
    public void unsetCell3D() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
