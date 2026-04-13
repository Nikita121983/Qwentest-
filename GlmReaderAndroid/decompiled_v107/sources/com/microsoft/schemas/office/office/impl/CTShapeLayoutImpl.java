package com.microsoft.schemas.office.office.impl;

import com.microsoft.schemas.office.office.CTIdMap;
import com.microsoft.schemas.office.office.CTRegroupTable;
import com.microsoft.schemas.office.office.CTRules;
import com.microsoft.schemas.office.office.CTShapeLayout;
import com.microsoft.schemas.vml.STExt;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class CTShapeLayoutImpl extends XmlComplexContentImpl implements CTShapeLayout {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-microsoft-com:office:office", "idmap"), new QName("urn:schemas-microsoft-com:office:office", "regrouptable"), new QName("urn:schemas-microsoft-com:office:office", "rules"), new QName("urn:schemas-microsoft-com:vml", "ext")};
    private static final long serialVersionUID = 1;

    public CTShapeLayoutImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public CTIdMap getIdmap() {
        CTIdMap cTIdMap;
        synchronized (monitor()) {
            check_orphaned();
            CTIdMap target = (CTIdMap) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTIdMap = target == null ? null : target;
        }
        return cTIdMap;
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public boolean isSetIdmap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public void setIdmap(CTIdMap idmap) {
        generatedSetterHelperImpl(idmap, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public CTIdMap addNewIdmap() {
        CTIdMap target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTIdMap) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public void unsetIdmap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public CTRegroupTable getRegrouptable() {
        CTRegroupTable cTRegroupTable;
        synchronized (monitor()) {
            check_orphaned();
            CTRegroupTable target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTRegroupTable = target == null ? null : target;
        }
        return cTRegroupTable;
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public boolean isSetRegrouptable() {
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

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public void setRegrouptable(CTRegroupTable regrouptable) {
        generatedSetterHelperImpl(regrouptable, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public CTRegroupTable addNewRegrouptable() {
        CTRegroupTable target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public void unsetRegrouptable() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public CTRules getRules() {
        CTRules cTRules;
        synchronized (monitor()) {
            check_orphaned();
            CTRules target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTRules = target == null ? null : target;
        }
        return cTRules;
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public boolean isSetRules() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public void setRules(CTRules rules) {
        generatedSetterHelperImpl(rules, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public CTRules addNewRules() {
        CTRules target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public void unsetRules() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public STExt.Enum getExt() {
        STExt.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            r1 = target == null ? null : (STExt.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public STExt xgetExt() {
        STExt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STExt) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public boolean isSetExt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public void setExt(STExt.Enum ext) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setEnumValue(ext);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public void xsetExt(STExt ext) {
        synchronized (monitor()) {
            check_orphaned();
            STExt target = (STExt) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STExt) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(ext);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTShapeLayout
    public void unsetExt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }
}
