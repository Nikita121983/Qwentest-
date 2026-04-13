package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBevel;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STPresetMaterialType;
import org.openxmlformats.schemas.drawingml.x2006.main.STPresetMaterialType$Enum;

/* loaded from: classes11.dex */
public class CTShape3DImpl extends XmlComplexContentImpl implements CTShape3D {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "bevelT"), new QName(XSSFRelation.NS_DRAWINGML, "bevelB"), new QName(XSSFRelation.NS_DRAWINGML, "extrusionClr"), new QName(XSSFRelation.NS_DRAWINGML, "contourClr"), new QName(XSSFRelation.NS_DRAWINGML, "extLst"), new QName("", CompressorStreamFactory.Z), new QName("", "extrusionH"), new QName("", "contourW"), new QName("", "prstMaterial")};
    private static final long serialVersionUID = 1;

    public CTShape3DImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public CTBevel getBevelT() {
        CTBevel cTBevel;
        synchronized (monitor()) {
            check_orphaned();
            CTBevel target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTBevel = target == null ? null : target;
        }
        return cTBevel;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public boolean isSetBevelT() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void setBevelT(CTBevel bevelT) {
        generatedSetterHelperImpl(bevelT, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public CTBevel addNewBevelT() {
        CTBevel target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void unsetBevelT() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public CTBevel getBevelB() {
        CTBevel cTBevel;
        synchronized (monitor()) {
            check_orphaned();
            CTBevel target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTBevel = target == null ? null : target;
        }
        return cTBevel;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public boolean isSetBevelB() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void setBevelB(CTBevel bevelB) {
        generatedSetterHelperImpl(bevelB, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public CTBevel addNewBevelB() {
        CTBevel target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void unsetBevelB() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public CTColor getExtrusionClr() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public boolean isSetExtrusionClr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void setExtrusionClr(CTColor extrusionClr) {
        generatedSetterHelperImpl(extrusionClr, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public CTColor addNewExtrusionClr() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void unsetExtrusionClr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public CTColor getContourClr() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public boolean isSetContourClr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void setContourClr(CTColor contourClr) {
        generatedSetterHelperImpl(contourClr, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public CTColor addNewContourClr() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void unsetContourClr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public Object getZ() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[5]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public STCoordinate xgetZ() {
        STCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STCoordinate) get_default_attribute_value(PROPERTY_QNAME[5]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public boolean isSetZ() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void setZ(Object z) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setObjectValue(z);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void xsetZ(STCoordinate z) {
        synchronized (monitor()) {
            check_orphaned();
            STCoordinate target = (STCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(z);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void unsetZ() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public long getExtrusionH() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[6]);
            }
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public STPositiveCoordinate xgetExtrusionH() {
        STPositiveCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositiveCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STPositiveCoordinate) get_default_attribute_value(PROPERTY_QNAME[6]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public boolean isSetExtrusionH() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void setExtrusionH(long extrusionH) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setLongValue(extrusionH);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void xsetExtrusionH(STPositiveCoordinate extrusionH) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveCoordinate target = (STPositiveCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STPositiveCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(extrusionH);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void unsetExtrusionH() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public long getContourW() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[7]);
            }
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public STPositiveCoordinate xgetContourW() {
        STPositiveCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositiveCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STPositiveCoordinate) get_default_attribute_value(PROPERTY_QNAME[7]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public boolean isSetContourW() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void setContourW(long contourW) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setLongValue(contourW);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void xsetContourW(STPositiveCoordinate contourW) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveCoordinate target = (STPositiveCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STPositiveCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(contourW);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void unsetContourW() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public STPresetMaterialType$Enum getPrstMaterial() {
        STPresetMaterialType$Enum sTPresetMaterialType$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[8]);
            }
            sTPresetMaterialType$Enum = target == null ? null : (STPresetMaterialType$Enum) target.getEnumValue();
        }
        return sTPresetMaterialType$Enum;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public STPresetMaterialType xgetPrstMaterial() {
        STPresetMaterialType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STPresetMaterialType) get_default_attribute_value(PROPERTY_QNAME[8]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public boolean isSetPrstMaterial() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void setPrstMaterial(STPresetMaterialType$Enum prstMaterial) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setEnumValue(prstMaterial);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void xsetPrstMaterial(STPresetMaterialType prstMaterial) {
        synchronized (monitor()) {
            check_orphaned();
            STPresetMaterialType target = get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STPresetMaterialType) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(prstMaterial);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D
    public void unsetPrstMaterial() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }
}
