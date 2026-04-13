package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference;
import org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackground;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties;

/* loaded from: classes11.dex */
public class CTBackgroundImpl extends XmlComplexContentImpl implements CTBackground {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "bgPr"), new QName(XSSFRelation.NS_PRESENTATIONML, "bgRef"), new QName("", "bwMode")};
    private static final long serialVersionUID = 1;

    public CTBackgroundImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackground
    public CTBackgroundProperties getBgPr() {
        CTBackgroundProperties cTBackgroundProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTBackgroundProperties target = (CTBackgroundProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTBackgroundProperties = target == null ? null : target;
        }
        return cTBackgroundProperties;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackground
    public boolean isSetBgPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackground
    public void setBgPr(CTBackgroundProperties bgPr) {
        generatedSetterHelperImpl(bgPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackground
    public CTBackgroundProperties addNewBgPr() {
        CTBackgroundProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBackgroundProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackground
    public void unsetBgPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackground
    public CTStyleMatrixReference getBgRef() {
        CTStyleMatrixReference cTStyleMatrixReference;
        synchronized (monitor()) {
            check_orphaned();
            CTStyleMatrixReference target = (CTStyleMatrixReference) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTStyleMatrixReference = target == null ? null : target;
        }
        return cTStyleMatrixReference;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackground
    public boolean isSetBgRef() {
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackground
    public void setBgRef(CTStyleMatrixReference bgRef) {
        generatedSetterHelperImpl(bgRef, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackground
    public CTStyleMatrixReference addNewBgRef() {
        CTStyleMatrixReference target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStyleMatrixReference) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackground
    public void unsetBgRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackground
    public STBlackWhiteMode.Enum getBwMode() {
        STBlackWhiteMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
            r1 = target == null ? null : (STBlackWhiteMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackground
    public STBlackWhiteMode xgetBwMode() {
        STBlackWhiteMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBlackWhiteMode) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STBlackWhiteMode) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackground
    public boolean isSetBwMode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackground
    public void setBwMode(STBlackWhiteMode.Enum bwMode) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setEnumValue(bwMode);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackground
    public void xsetBwMode(STBlackWhiteMode bwMode) {
        synchronized (monitor()) {
            check_orphaned();
            STBlackWhiteMode target = (STBlackWhiteMode) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STBlackWhiteMode) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(bwMode);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackground
    public void unsetBwMode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }
}
