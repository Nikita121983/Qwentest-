package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt;

/* loaded from: classes12.dex */
public class CTDxfImpl extends XmlComplexContentImpl implements CTDxf {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, CellUtil.FONT), new QName(XSSFRelation.NS_SPREADSHEETML, "numFmt"), new QName(XSSFRelation.NS_SPREADSHEETML, "fill"), new QName(XSSFRelation.NS_SPREADSHEETML, CellUtil.ALIGNMENT), new QName(XSSFRelation.NS_SPREADSHEETML, "border"), new QName(XSSFRelation.NS_SPREADSHEETML, "protection"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTDxfImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public CTFont getFont() {
        CTFont cTFont;
        synchronized (monitor()) {
            check_orphaned();
            CTFont target = (CTFont) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTFont = target == null ? null : target;
        }
        return cTFont;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public boolean isSetFont() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public void setFont(CTFont font) {
        generatedSetterHelperImpl(font, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public CTFont addNewFont() {
        CTFont target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFont) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public void unsetFont() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public CTNumFmt getNumFmt() {
        CTNumFmt cTNumFmt;
        synchronized (monitor()) {
            check_orphaned();
            CTNumFmt target = (CTNumFmt) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTNumFmt = target == null ? null : target;
        }
        return cTNumFmt;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public boolean isSetNumFmt() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public void setNumFmt(CTNumFmt numFmt) {
        generatedSetterHelperImpl(numFmt, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public CTNumFmt addNewNumFmt() {
        CTNumFmt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNumFmt) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public void unsetNumFmt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public CTFill getFill() {
        CTFill cTFill;
        synchronized (monitor()) {
            check_orphaned();
            CTFill target = (CTFill) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTFill = target == null ? null : target;
        }
        return cTFill;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public boolean isSetFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public void setFill(CTFill fill) {
        generatedSetterHelperImpl(fill, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public CTFill addNewFill() {
        CTFill target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFill) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public void unsetFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public CTCellAlignment getAlignment() {
        CTCellAlignment cTCellAlignment;
        synchronized (monitor()) {
            check_orphaned();
            CTCellAlignment target = (CTCellAlignment) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTCellAlignment = target == null ? null : target;
        }
        return cTCellAlignment;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public boolean isSetAlignment() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public void setAlignment(CTCellAlignment alignment) {
        generatedSetterHelperImpl(alignment, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public CTCellAlignment addNewAlignment() {
        CTCellAlignment target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCellAlignment) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public void unsetAlignment() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public CTBorder getBorder() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBorder target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTBorder = target == null ? null : target;
        }
        return cTBorder;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public boolean isSetBorder() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public void setBorder(CTBorder border) {
        generatedSetterHelperImpl(border, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public CTBorder addNewBorder() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public void unsetBorder() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public CTCellProtection getProtection() {
        CTCellProtection cTCellProtection;
        synchronized (monitor()) {
            check_orphaned();
            CTCellProtection target = (CTCellProtection) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTCellProtection = target == null ? null : target;
        }
        return cTCellProtection;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public boolean isSetProtection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public void setProtection(CTCellProtection protection) {
        generatedSetterHelperImpl(protection, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public CTCellProtection addNewProtection() {
        CTCellProtection target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCellProtection) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public void unsetProtection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }
}
