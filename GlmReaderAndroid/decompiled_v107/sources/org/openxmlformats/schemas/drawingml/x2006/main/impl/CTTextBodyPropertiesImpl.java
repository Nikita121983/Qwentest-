package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFlatText;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetTextShape;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNoAutofit;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextShapeAutofit;
import org.openxmlformats.schemas.drawingml.x2006.main.STAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate32;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate32;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextColumnCount;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextHorzOverflowType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVertOverflowType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVerticalType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextWrappingType;

/* loaded from: classes11.dex */
public class CTTextBodyPropertiesImpl extends XmlComplexContentImpl implements CTTextBodyProperties {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "prstTxWarp"), new QName(XSSFRelation.NS_DRAWINGML, "noAutofit"), new QName(XSSFRelation.NS_DRAWINGML, "normAutofit"), new QName(XSSFRelation.NS_DRAWINGML, "spAutoFit"), new QName(XSSFRelation.NS_DRAWINGML, "scene3d"), new QName(XSSFRelation.NS_DRAWINGML, "sp3d"), new QName(XSSFRelation.NS_DRAWINGML, "flatTx"), new QName(XSSFRelation.NS_DRAWINGML, "extLst"), new QName("", "rot"), new QName("", "spcFirstLastPara"), new QName("", "vertOverflow"), new QName("", "horzOverflow"), new QName("", "vert"), new QName("", "wrap"), new QName("", "lIns"), new QName("", "tIns"), new QName("", "rIns"), new QName("", "bIns"), new QName("", "numCol"), new QName("", "spcCol"), new QName("", "rtlCol"), new QName("", "fromWordArt"), new QName("", "anchor"), new QName("", "anchorCtr"), new QName("", "forceAA"), new QName("", "upright"), new QName("", "compatLnSpc")};
    private static final long serialVersionUID = 1;

    public CTTextBodyPropertiesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public CTPresetTextShape getPrstTxWarp() {
        CTPresetTextShape cTPresetTextShape;
        synchronized (monitor()) {
            check_orphaned();
            CTPresetTextShape target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTPresetTextShape = target == null ? null : target;
        }
        return cTPresetTextShape;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetPrstTxWarp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setPrstTxWarp(CTPresetTextShape prstTxWarp) {
        generatedSetterHelperImpl(prstTxWarp, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public CTPresetTextShape addNewPrstTxWarp() {
        CTPresetTextShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetPrstTxWarp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public CTTextNoAutofit getNoAutofit() {
        CTTextNoAutofit cTTextNoAutofit;
        synchronized (monitor()) {
            check_orphaned();
            CTTextNoAutofit target = (CTTextNoAutofit) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTextNoAutofit = target == null ? null : target;
        }
        return cTTextNoAutofit;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetNoAutofit() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setNoAutofit(CTTextNoAutofit noAutofit) {
        generatedSetterHelperImpl(noAutofit, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public CTTextNoAutofit addNewNoAutofit() {
        CTTextNoAutofit target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextNoAutofit) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetNoAutofit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public CTTextNormalAutofit getNormAutofit() {
        CTTextNormalAutofit cTTextNormalAutofit;
        synchronized (monitor()) {
            check_orphaned();
            CTTextNormalAutofit target = (CTTextNormalAutofit) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTTextNormalAutofit = target == null ? null : target;
        }
        return cTTextNormalAutofit;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetNormAutofit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setNormAutofit(CTTextNormalAutofit normAutofit) {
        generatedSetterHelperImpl(normAutofit, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public CTTextNormalAutofit addNewNormAutofit() {
        CTTextNormalAutofit target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextNormalAutofit) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetNormAutofit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public CTTextShapeAutofit getSpAutoFit() {
        CTTextShapeAutofit cTTextShapeAutofit;
        synchronized (monitor()) {
            check_orphaned();
            CTTextShapeAutofit target = (CTTextShapeAutofit) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTTextShapeAutofit = target == null ? null : target;
        }
        return cTTextShapeAutofit;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetSpAutoFit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setSpAutoFit(CTTextShapeAutofit spAutoFit) {
        generatedSetterHelperImpl(spAutoFit, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public CTTextShapeAutofit addNewSpAutoFit() {
        CTTextShapeAutofit target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextShapeAutofit) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetSpAutoFit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public CTScene3D getScene3D() {
        CTScene3D cTScene3D;
        synchronized (monitor()) {
            check_orphaned();
            CTScene3D target = (CTScene3D) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTScene3D = target == null ? null : target;
        }
        return cTScene3D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetScene3D() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setScene3D(CTScene3D scene3D) {
        generatedSetterHelperImpl(scene3D, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public CTScene3D addNewScene3D() {
        CTScene3D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTScene3D) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetScene3D() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public CTShape3D getSp3D() {
        CTShape3D cTShape3D;
        synchronized (monitor()) {
            check_orphaned();
            CTShape3D target = (CTShape3D) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTShape3D = target == null ? null : target;
        }
        return cTShape3D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetSp3D() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setSp3D(CTShape3D sp3D) {
        generatedSetterHelperImpl(sp3D, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public CTShape3D addNewSp3D() {
        CTShape3D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShape3D) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetSp3D() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public CTFlatText getFlatTx() {
        CTFlatText cTFlatText;
        synchronized (monitor()) {
            check_orphaned();
            CTFlatText target = get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTFlatText = target == null ? null : target;
        }
        return cTFlatText;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetFlatTx() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setFlatTx(CTFlatText flatTx) {
        generatedSetterHelperImpl(flatTx, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public CTFlatText addNewFlatTx() {
        CTFlatText target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetFlatTx() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public int getRot() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public STAngle xgetRot() {
        STAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAngle) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetRot() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setRot(int rot) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setIntValue(rot);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetRot(STAngle rot) {
        synchronized (monitor()) {
            check_orphaned();
            STAngle target = (STAngle) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STAngle) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(rot);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetRot() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean getSpcFirstLastPara() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public XmlBoolean xgetSpcFirstLastPara() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetSpcFirstLastPara() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setSpcFirstLastPara(boolean spcFirstLastPara) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setBooleanValue(spcFirstLastPara);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetSpcFirstLastPara(XmlBoolean spcFirstLastPara) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(spcFirstLastPara);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetSpcFirstLastPara() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public STTextVertOverflowType.Enum getVertOverflow() {
        STTextVertOverflowType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            r1 = target == null ? null : (STTextVertOverflowType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public STTextVertOverflowType xgetVertOverflow() {
        STTextVertOverflowType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextVertOverflowType) get_store().find_attribute_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetVertOverflow() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setVertOverflow(STTextVertOverflowType.Enum vertOverflow) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setEnumValue(vertOverflow);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetVertOverflow(STTextVertOverflowType vertOverflow) {
        synchronized (monitor()) {
            check_orphaned();
            STTextVertOverflowType target = (STTextVertOverflowType) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (STTextVertOverflowType) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(vertOverflow);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetVertOverflow() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public STTextHorzOverflowType.Enum getHorzOverflow() {
        STTextHorzOverflowType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            r1 = target == null ? null : (STTextHorzOverflowType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public STTextHorzOverflowType xgetHorzOverflow() {
        STTextHorzOverflowType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextHorzOverflowType) get_store().find_attribute_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetHorzOverflow() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setHorzOverflow(STTextHorzOverflowType.Enum horzOverflow) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setEnumValue(horzOverflow);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetHorzOverflow(STTextHorzOverflowType horzOverflow) {
        synchronized (monitor()) {
            check_orphaned();
            STTextHorzOverflowType target = (STTextHorzOverflowType) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (STTextHorzOverflowType) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(horzOverflow);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetHorzOverflow() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public STTextVerticalType.Enum getVert() {
        STTextVerticalType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            r1 = target == null ? null : (STTextVerticalType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public STTextVerticalType xgetVert() {
        STTextVerticalType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextVerticalType) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetVert() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setVert(STTextVerticalType.Enum vert) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setEnumValue(vert);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetVert(STTextVerticalType vert) {
        synchronized (monitor()) {
            check_orphaned();
            STTextVerticalType target = (STTextVerticalType) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (STTextVerticalType) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(vert);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetVert() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public STTextWrappingType.Enum getWrap() {
        STTextWrappingType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            r1 = target == null ? null : (STTextWrappingType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public STTextWrappingType xgetWrap() {
        STTextWrappingType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextWrappingType) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetWrap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setWrap(STTextWrappingType.Enum wrap) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setEnumValue(wrap);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetWrap(STTextWrappingType wrap) {
        synchronized (monitor()) {
            check_orphaned();
            STTextWrappingType target = (STTextWrappingType) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (STTextWrappingType) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(wrap);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetWrap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public Object getLIns() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public STCoordinate32 xgetLIns() {
        STCoordinate32 target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetLIns() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setLIns(Object lIns) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.setObjectValue(lIns);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetLIns(STCoordinate32 lIns) {
        synchronized (monitor()) {
            check_orphaned();
            STCoordinate32 target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (STCoordinate32) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.set(lIns);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetLIns() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[14]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public Object getTIns() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public STCoordinate32 xgetTIns() {
        STCoordinate32 target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetTIns() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setTIns(Object tIns) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.setObjectValue(tIns);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetTIns(STCoordinate32 tIns) {
        synchronized (monitor()) {
            check_orphaned();
            STCoordinate32 target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (STCoordinate32) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.set(tIns);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetTIns() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[15]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public Object getRIns() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public STCoordinate32 xgetRIns() {
        STCoordinate32 target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetRIns() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[16]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setRIns(Object rIns) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.setObjectValue(rIns);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetRIns(STCoordinate32 rIns) {
        synchronized (monitor()) {
            check_orphaned();
            STCoordinate32 target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (STCoordinate32) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.set(rIns);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetRIns() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[16]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public Object getBIns() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public STCoordinate32 xgetBIns() {
        STCoordinate32 target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetBIns() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[17]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setBIns(Object bIns) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.setObjectValue(bIns);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetBIns(STCoordinate32 bIns) {
        synchronized (monitor()) {
            check_orphaned();
            STCoordinate32 target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (STCoordinate32) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.set(bIns);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetBIns() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[17]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public int getNumCol() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public STTextColumnCount xgetNumCol() {
        STTextColumnCount target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetNumCol() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[18]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setNumCol(int numCol) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.setIntValue(numCol);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetNumCol(STTextColumnCount numCol) {
        synchronized (monitor()) {
            check_orphaned();
            STTextColumnCount target = get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (STTextColumnCount) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.set(numCol);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetNumCol() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[18]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public int getSpcCol() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public STPositiveCoordinate32 xgetSpcCol() {
        STPositiveCoordinate32 target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositiveCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetSpcCol() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[19]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setSpcCol(int spcCol) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.setIntValue(spcCol);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetSpcCol(STPositiveCoordinate32 spcCol) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveCoordinate32 target = (STPositiveCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (STPositiveCoordinate32) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.set(spcCol);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetSpcCol() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[19]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean getRtlCol() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public XmlBoolean xgetRtlCol() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetRtlCol() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[20]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setRtlCol(boolean rtlCol) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.setBooleanValue(rtlCol);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetRtlCol(XmlBoolean rtlCol) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.set(rtlCol);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetRtlCol() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[20]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean getFromWordArt() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public XmlBoolean xgetFromWordArt() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetFromWordArt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[21]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setFromWordArt(boolean fromWordArt) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.setBooleanValue(fromWordArt);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetFromWordArt(XmlBoolean fromWordArt) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.set(fromWordArt);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetFromWordArt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[21]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public STTextAnchoringType.Enum getAnchor() {
        STTextAnchoringType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            r1 = target == null ? null : (STTextAnchoringType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public STTextAnchoringType xgetAnchor() {
        STTextAnchoringType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextAnchoringType) get_store().find_attribute_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetAnchor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[22]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setAnchor(STTextAnchoringType.Enum anchor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.setEnumValue(anchor);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetAnchor(STTextAnchoringType anchor) {
        synchronized (monitor()) {
            check_orphaned();
            STTextAnchoringType target = (STTextAnchoringType) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (STTextAnchoringType) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.set(anchor);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetAnchor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[22]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean getAnchorCtr() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public XmlBoolean xgetAnchorCtr() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetAnchorCtr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[23]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setAnchorCtr(boolean anchorCtr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.setBooleanValue(anchorCtr);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetAnchorCtr(XmlBoolean anchorCtr) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.set(anchorCtr);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetAnchorCtr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[23]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean getForceAA() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public XmlBoolean xgetForceAA() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetForceAA() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[24]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setForceAA(boolean forceAA) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.setBooleanValue(forceAA);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetForceAA(XmlBoolean forceAA) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.set(forceAA);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetForceAA() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[24]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean getUpright() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[25]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public XmlBoolean xgetUpright() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[25]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetUpright() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[25]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setUpright(boolean upright) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.setBooleanValue(upright);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetUpright(XmlBoolean upright) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.set(upright);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetUpright() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[25]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean getCompatLnSpc() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public XmlBoolean xgetCompatLnSpc() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public boolean isSetCompatLnSpc() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[26]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void setCompatLnSpc(boolean compatLnSpc) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.setBooleanValue(compatLnSpc);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void xsetCompatLnSpc(XmlBoolean compatLnSpc) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.set(compatLnSpc);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties
    public void unsetCompatLnSpc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[26]);
        }
    }
}
