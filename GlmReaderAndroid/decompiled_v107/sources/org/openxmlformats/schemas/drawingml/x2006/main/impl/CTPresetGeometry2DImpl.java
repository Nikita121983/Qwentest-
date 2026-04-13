package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;

/* loaded from: classes11.dex */
public class CTPresetGeometry2DImpl extends XmlComplexContentImpl implements CTPresetGeometry2D {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "avLst"), new QName("", "prst")};
    private static final long serialVersionUID = 1;

    public CTPresetGeometry2DImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D
    public CTGeomGuideList getAvLst() {
        CTGeomGuideList cTGeomGuideList;
        synchronized (monitor()) {
            check_orphaned();
            CTGeomGuideList target = (CTGeomGuideList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTGeomGuideList = target == null ? null : target;
        }
        return cTGeomGuideList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D
    public boolean isSetAvLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D
    public void setAvLst(CTGeomGuideList avLst) {
        generatedSetterHelperImpl(avLst, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D
    public CTGeomGuideList addNewAvLst() {
        CTGeomGuideList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGeomGuideList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D
    public void unsetAvLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D
    public STShapeType.Enum getPrst() {
        STShapeType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            r1 = target == null ? null : (STShapeType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D
    public STShapeType xgetPrst() {
        STShapeType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STShapeType) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D
    public void setPrst(STShapeType.Enum prst) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(prst);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D
    public void xsetPrst(STShapeType prst) {
        synchronized (monitor()) {
            check_orphaned();
            STShapeType target = (STShapeType) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STShapeType) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(prst);
        }
    }
}
