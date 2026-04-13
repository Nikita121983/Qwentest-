package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBackgroundFillStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix;

/* loaded from: classes11.dex */
public class CTStyleMatrixImpl extends XmlComplexContentImpl implements CTStyleMatrix {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "fillStyleLst"), new QName(XSSFRelation.NS_DRAWINGML, "lnStyleLst"), new QName(XSSFRelation.NS_DRAWINGML, "effectStyleLst"), new QName(XSSFRelation.NS_DRAWINGML, "bgFillStyleLst"), new QName("", "name")};
    private static final long serialVersionUID = 1;

    public CTStyleMatrixImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public CTFillStyleList getFillStyleLst() {
        CTFillStyleList cTFillStyleList;
        synchronized (monitor()) {
            check_orphaned();
            CTFillStyleList target = (CTFillStyleList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTFillStyleList = target == null ? null : target;
        }
        return cTFillStyleList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public void setFillStyleLst(CTFillStyleList fillStyleLst) {
        generatedSetterHelperImpl(fillStyleLst, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public CTFillStyleList addNewFillStyleLst() {
        CTFillStyleList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFillStyleList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public CTLineStyleList getLnStyleLst() {
        CTLineStyleList cTLineStyleList;
        synchronized (monitor()) {
            check_orphaned();
            CTLineStyleList target = (CTLineStyleList) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTLineStyleList = target == null ? null : target;
        }
        return cTLineStyleList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public void setLnStyleLst(CTLineStyleList lnStyleLst) {
        generatedSetterHelperImpl(lnStyleLst, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public CTLineStyleList addNewLnStyleLst() {
        CTLineStyleList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineStyleList) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public CTEffectStyleList getEffectStyleLst() {
        CTEffectStyleList cTEffectStyleList;
        synchronized (monitor()) {
            check_orphaned();
            CTEffectStyleList target = (CTEffectStyleList) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTEffectStyleList = target == null ? null : target;
        }
        return cTEffectStyleList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public void setEffectStyleLst(CTEffectStyleList effectStyleLst) {
        generatedSetterHelperImpl(effectStyleLst, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public CTEffectStyleList addNewEffectStyleLst() {
        CTEffectStyleList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEffectStyleList) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public CTBackgroundFillStyleList getBgFillStyleLst() {
        CTBackgroundFillStyleList cTBackgroundFillStyleList;
        synchronized (monitor()) {
            check_orphaned();
            CTBackgroundFillStyleList target = (CTBackgroundFillStyleList) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTBackgroundFillStyleList = target == null ? null : target;
        }
        return cTBackgroundFillStyleList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public void setBgFillStyleLst(CTBackgroundFillStyleList bgFillStyleLst) {
        generatedSetterHelperImpl(bgFillStyleLst, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public CTBackgroundFillStyleList addNewBgFillStyleLst() {
        CTBackgroundFillStyleList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBackgroundFillStyleList) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public XmlString xgetName() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlString) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public void xsetName(XmlString name) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(name);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
