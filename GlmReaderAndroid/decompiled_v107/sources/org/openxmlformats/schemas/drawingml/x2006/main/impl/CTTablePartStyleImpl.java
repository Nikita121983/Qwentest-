package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle;

/* loaded from: classes11.dex */
public class CTTablePartStyleImpl extends XmlComplexContentImpl implements CTTablePartStyle {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "tcTxStyle"), new QName(XSSFRelation.NS_DRAWINGML, "tcStyle")};
    private static final long serialVersionUID = 1;

    public CTTablePartStyleImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle
    public CTTableStyleTextStyle getTcTxStyle() {
        CTTableStyleTextStyle cTTableStyleTextStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTableStyleTextStyle target = (CTTableStyleTextStyle) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTableStyleTextStyle = target == null ? null : target;
        }
        return cTTableStyleTextStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle
    public boolean isSetTcTxStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle
    public void setTcTxStyle(CTTableStyleTextStyle tcTxStyle) {
        generatedSetterHelperImpl(tcTxStyle, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle
    public CTTableStyleTextStyle addNewTcTxStyle() {
        CTTableStyleTextStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTableStyleTextStyle) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle
    public void unsetTcTxStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle
    public CTTableStyleCellStyle getTcStyle() {
        CTTableStyleCellStyle cTTableStyleCellStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTableStyleCellStyle target = (CTTableStyleCellStyle) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTableStyleCellStyle = target == null ? null : target;
        }
        return cTTableStyleCellStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle
    public boolean isSetTcStyle() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle
    public void setTcStyle(CTTableStyleCellStyle tcStyle) {
        generatedSetterHelperImpl(tcStyle, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle
    public CTTableStyleCellStyle addNewTcStyle() {
        CTTableStyleCellStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTableStyleCellStyle) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle
    public void unsetTcStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
