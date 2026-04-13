package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;
import org.openxmlformats.schemas.drawingml.x2006.main.STFixedAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveFixedAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.STRectAlignment;

/* loaded from: classes11.dex */
public class CTOuterShadowEffectImpl extends XmlComplexContentImpl implements CTOuterShadowEffect {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "scrgbClr"), new QName(XSSFRelation.NS_DRAWINGML, "srgbClr"), new QName(XSSFRelation.NS_DRAWINGML, "hslClr"), new QName(XSSFRelation.NS_DRAWINGML, "sysClr"), new QName(XSSFRelation.NS_DRAWINGML, "schemeClr"), new QName(XSSFRelation.NS_DRAWINGML, "prstClr"), new QName("", "blurRad"), new QName("", "dist"), new QName("", "dir"), new QName("", "sx"), new QName("", "sy"), new QName("", "kx"), new QName("", "ky"), new QName("", "algn"), new QName("", "rotWithShape")};
    private static final long serialVersionUID = 1;

    public CTOuterShadowEffectImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public CTScRgbColor getScrgbClr() {
        CTScRgbColor cTScRgbColor;
        synchronized (monitor()) {
            check_orphaned();
            CTScRgbColor target = (CTScRgbColor) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTScRgbColor = target == null ? null : target;
        }
        return cTScRgbColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public boolean isSetScrgbClr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void setScrgbClr(CTScRgbColor scrgbClr) {
        generatedSetterHelperImpl(scrgbClr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public CTScRgbColor addNewScrgbClr() {
        CTScRgbColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTScRgbColor) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void unsetScrgbClr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public CTSRgbColor getSrgbClr() {
        CTSRgbColor cTSRgbColor;
        synchronized (monitor()) {
            check_orphaned();
            CTSRgbColor target = (CTSRgbColor) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTSRgbColor = target == null ? null : target;
        }
        return cTSRgbColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public boolean isSetSrgbClr() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void setSrgbClr(CTSRgbColor srgbClr) {
        generatedSetterHelperImpl(srgbClr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public CTSRgbColor addNewSrgbClr() {
        CTSRgbColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSRgbColor) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void unsetSrgbClr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public CTHslColor getHslClr() {
        CTHslColor cTHslColor;
        synchronized (monitor()) {
            check_orphaned();
            CTHslColor target = (CTHslColor) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTHslColor = target == null ? null : target;
        }
        return cTHslColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public boolean isSetHslClr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void setHslClr(CTHslColor hslClr) {
        generatedSetterHelperImpl(hslClr, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public CTHslColor addNewHslClr() {
        CTHslColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHslColor) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void unsetHslClr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public CTSystemColor getSysClr() {
        CTSystemColor cTSystemColor;
        synchronized (monitor()) {
            check_orphaned();
            CTSystemColor target = (CTSystemColor) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTSystemColor = target == null ? null : target;
        }
        return cTSystemColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public boolean isSetSysClr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void setSysClr(CTSystemColor sysClr) {
        generatedSetterHelperImpl(sysClr, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public CTSystemColor addNewSysClr() {
        CTSystemColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSystemColor) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void unsetSysClr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public CTSchemeColor getSchemeClr() {
        CTSchemeColor cTSchemeColor;
        synchronized (monitor()) {
            check_orphaned();
            CTSchemeColor target = (CTSchemeColor) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTSchemeColor = target == null ? null : target;
        }
        return cTSchemeColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public boolean isSetSchemeClr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void setSchemeClr(CTSchemeColor schemeClr) {
        generatedSetterHelperImpl(schemeClr, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public CTSchemeColor addNewSchemeClr() {
        CTSchemeColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSchemeColor) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void unsetSchemeClr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public CTPresetColor getPrstClr() {
        CTPresetColor cTPresetColor;
        synchronized (monitor()) {
            check_orphaned();
            CTPresetColor target = (CTPresetColor) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTPresetColor = target == null ? null : target;
        }
        return cTPresetColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public boolean isSetPrstClr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void setPrstClr(CTPresetColor prstClr) {
        generatedSetterHelperImpl(prstClr, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public CTPresetColor addNewPrstClr() {
        CTPresetColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPresetColor) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void unsetPrstClr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public long getBlurRad() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public STPositiveCoordinate xgetBlurRad() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public boolean isSetBlurRad() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void setBlurRad(long blurRad) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setLongValue(blurRad);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void xsetBlurRad(STPositiveCoordinate blurRad) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveCoordinate target = (STPositiveCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STPositiveCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(blurRad);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void unsetBlurRad() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public long getDist() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public STPositiveCoordinate xgetDist() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public boolean isSetDist() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void setDist(long dist) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setLongValue(dist);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void xsetDist(STPositiveCoordinate dist) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveCoordinate target = (STPositiveCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STPositiveCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(dist);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void unsetDist() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public int getDir() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[8]);
            }
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public STPositiveFixedAngle xgetDir() {
        STPositiveFixedAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositiveFixedAngle) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STPositiveFixedAngle) get_default_attribute_value(PROPERTY_QNAME[8]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public boolean isSetDir() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void setDir(int dir) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setIntValue(dir);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void xsetDir(STPositiveFixedAngle dir) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveFixedAngle target = (STPositiveFixedAngle) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STPositiveFixedAngle) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(dir);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void unsetDir() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public Object getSx() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public STPercentage xgetSx() {
        STPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (STPercentage) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public boolean isSetSx() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void setSx(Object sx) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setObjectValue(sx);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void xsetSx(STPercentage sx) {
        synchronized (monitor()) {
            check_orphaned();
            STPercentage target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (STPercentage) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(sx);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void unsetSx() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public Object getSy() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[10]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public STPercentage xgetSy() {
        STPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (STPercentage) get_default_attribute_value(PROPERTY_QNAME[10]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public boolean isSetSy() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void setSy(Object sy) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setObjectValue(sy);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void xsetSy(STPercentage sy) {
        synchronized (monitor()) {
            check_orphaned();
            STPercentage target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (STPercentage) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(sy);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void unsetSy() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public int getKx() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[11]);
            }
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public STFixedAngle xgetKx() {
        STFixedAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (STFixedAngle) get_default_attribute_value(PROPERTY_QNAME[11]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public boolean isSetKx() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void setKx(int kx) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setIntValue(kx);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void xsetKx(STFixedAngle kx) {
        synchronized (monitor()) {
            check_orphaned();
            STFixedAngle target = get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (STFixedAngle) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(kx);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void unsetKx() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public int getKy() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[12]);
            }
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public STFixedAngle xgetKy() {
        STFixedAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (STFixedAngle) get_default_attribute_value(PROPERTY_QNAME[12]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public boolean isSetKy() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void setKy(int ky) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setIntValue(ky);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void xsetKy(STFixedAngle ky) {
        synchronized (monitor()) {
            check_orphaned();
            STFixedAngle target = get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (STFixedAngle) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(ky);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void unsetKy() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public STRectAlignment.Enum getAlgn() {
        STRectAlignment.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[13]);
            }
            r1 = target == null ? null : (STRectAlignment.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public STRectAlignment xgetAlgn() {
        STRectAlignment target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRectAlignment) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (STRectAlignment) get_default_attribute_value(PROPERTY_QNAME[13]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public boolean isSetAlgn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void setAlgn(STRectAlignment.Enum algn) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setEnumValue(algn);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void xsetAlgn(STRectAlignment algn) {
        synchronized (monitor()) {
            check_orphaned();
            STRectAlignment target = (STRectAlignment) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (STRectAlignment) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(algn);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void unsetAlgn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public boolean getRotWithShape() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[14]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public XmlBoolean xgetRotWithShape() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[14]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public boolean isSetRotWithShape() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void setRotWithShape(boolean rotWithShape) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.setBooleanValue(rotWithShape);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void xsetRotWithShape(XmlBoolean rotWithShape) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.set(rotWithShape);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect
    public void unsetRotWithShape() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[14]);
        }
    }
}
