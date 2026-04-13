package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D;

/* loaded from: classes11.dex */
public class CTEffectStyleItemImpl extends XmlComplexContentImpl implements CTEffectStyleItem {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "effectLst"), new QName(XSSFRelation.NS_DRAWINGML, "effectDag"), new QName(XSSFRelation.NS_DRAWINGML, "scene3d"), new QName(XSSFRelation.NS_DRAWINGML, "sp3d")};
    private static final long serialVersionUID = 1;

    public CTEffectStyleItemImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public CTEffectList getEffectLst() {
        CTEffectList cTEffectList;
        synchronized (monitor()) {
            check_orphaned();
            CTEffectList target = (CTEffectList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTEffectList = target == null ? null : target;
        }
        return cTEffectList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public boolean isSetEffectLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public void setEffectLst(CTEffectList effectLst) {
        generatedSetterHelperImpl(effectLst, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public CTEffectList addNewEffectLst() {
        CTEffectList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEffectList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public void unsetEffectLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public CTEffectContainer getEffectDag() {
        CTEffectContainer cTEffectContainer;
        synchronized (monitor()) {
            check_orphaned();
            CTEffectContainer target = (CTEffectContainer) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTEffectContainer = target == null ? null : target;
        }
        return cTEffectContainer;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public boolean isSetEffectDag() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public void setEffectDag(CTEffectContainer effectDag) {
        generatedSetterHelperImpl(effectDag, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public CTEffectContainer addNewEffectDag() {
        CTEffectContainer target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEffectContainer) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public void unsetEffectDag() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public CTScene3D getScene3D() {
        CTScene3D cTScene3D;
        synchronized (monitor()) {
            check_orphaned();
            CTScene3D target = (CTScene3D) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTScene3D = target == null ? null : target;
        }
        return cTScene3D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public boolean isSetScene3D() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public void setScene3D(CTScene3D scene3D) {
        generatedSetterHelperImpl(scene3D, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public CTScene3D addNewScene3D() {
        CTScene3D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTScene3D) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public void unsetScene3D() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public CTShape3D getSp3D() {
        CTShape3D cTShape3D;
        synchronized (monitor()) {
            check_orphaned();
            CTShape3D target = (CTShape3D) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTShape3D = target == null ? null : target;
        }
        return cTShape3D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public boolean isSetSp3D() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public void setSp3D(CTShape3D sp3D) {
        generatedSetterHelperImpl(sp3D, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public CTShape3D addNewSp3D() {
        CTShape3D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShape3D) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem
    public void unsetSp3D() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
