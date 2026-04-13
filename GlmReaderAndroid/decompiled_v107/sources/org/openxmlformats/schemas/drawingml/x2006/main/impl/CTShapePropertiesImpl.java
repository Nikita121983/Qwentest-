package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode;

/* loaded from: classes11.dex */
public class CTShapePropertiesImpl extends XmlComplexContentImpl implements CTShapeProperties {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "xfrm"), new QName(XSSFRelation.NS_DRAWINGML, "custGeom"), new QName(XSSFRelation.NS_DRAWINGML, "prstGeom"), new QName(XSSFRelation.NS_DRAWINGML, "noFill"), new QName(XSSFRelation.NS_DRAWINGML, "solidFill"), new QName(XSSFRelation.NS_DRAWINGML, "gradFill"), new QName(XSSFRelation.NS_DRAWINGML, "blipFill"), new QName(XSSFRelation.NS_DRAWINGML, "pattFill"), new QName(XSSFRelation.NS_DRAWINGML, "grpFill"), new QName(XSSFRelation.NS_DRAWINGML, "ln"), new QName(XSSFRelation.NS_DRAWINGML, "effectLst"), new QName(XSSFRelation.NS_DRAWINGML, "effectDag"), new QName(XSSFRelation.NS_DRAWINGML, "scene3d"), new QName(XSSFRelation.NS_DRAWINGML, "sp3d"), new QName(XSSFRelation.NS_DRAWINGML, "extLst"), new QName("", "bwMode")};
    private static final long serialVersionUID = 1;

    public CTShapePropertiesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTTransform2D getXfrm() {
        CTTransform2D cTTransform2D;
        synchronized (monitor()) {
            check_orphaned();
            CTTransform2D target = (CTTransform2D) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTransform2D = target == null ? null : target;
        }
        return cTTransform2D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public boolean isSetXfrm() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void setXfrm(CTTransform2D xfrm) {
        generatedSetterHelperImpl(xfrm, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTTransform2D addNewXfrm() {
        CTTransform2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTransform2D) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void unsetXfrm() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTCustomGeometry2D getCustGeom() {
        CTCustomGeometry2D cTCustomGeometry2D;
        synchronized (monitor()) {
            check_orphaned();
            CTCustomGeometry2D target = (CTCustomGeometry2D) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTCustomGeometry2D = target == null ? null : target;
        }
        return cTCustomGeometry2D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public boolean isSetCustGeom() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void setCustGeom(CTCustomGeometry2D custGeom) {
        generatedSetterHelperImpl(custGeom, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTCustomGeometry2D addNewCustGeom() {
        CTCustomGeometry2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomGeometry2D) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void unsetCustGeom() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTPresetGeometry2D getPrstGeom() {
        CTPresetGeometry2D cTPresetGeometry2D;
        synchronized (monitor()) {
            check_orphaned();
            CTPresetGeometry2D target = (CTPresetGeometry2D) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTPresetGeometry2D = target == null ? null : target;
        }
        return cTPresetGeometry2D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public boolean isSetPrstGeom() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void setPrstGeom(CTPresetGeometry2D prstGeom) {
        generatedSetterHelperImpl(prstGeom, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTPresetGeometry2D addNewPrstGeom() {
        CTPresetGeometry2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPresetGeometry2D) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void unsetPrstGeom() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTNoFillProperties getNoFill() {
        CTNoFillProperties cTNoFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTNoFillProperties target = (CTNoFillProperties) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTNoFillProperties = target == null ? null : target;
        }
        return cTNoFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public boolean isSetNoFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void setNoFill(CTNoFillProperties noFill) {
        generatedSetterHelperImpl(noFill, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTNoFillProperties addNewNoFill() {
        CTNoFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNoFillProperties) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void unsetNoFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTSolidColorFillProperties getSolidFill() {
        CTSolidColorFillProperties cTSolidColorFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTSolidColorFillProperties target = (CTSolidColorFillProperties) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTSolidColorFillProperties = target == null ? null : target;
        }
        return cTSolidColorFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public boolean isSetSolidFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void setSolidFill(CTSolidColorFillProperties solidFill) {
        generatedSetterHelperImpl(solidFill, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTSolidColorFillProperties addNewSolidFill() {
        CTSolidColorFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSolidColorFillProperties) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void unsetSolidFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTGradientFillProperties getGradFill() {
        CTGradientFillProperties cTGradientFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTGradientFillProperties target = (CTGradientFillProperties) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTGradientFillProperties = target == null ? null : target;
        }
        return cTGradientFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public boolean isSetGradFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void setGradFill(CTGradientFillProperties gradFill) {
        generatedSetterHelperImpl(gradFill, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTGradientFillProperties addNewGradFill() {
        CTGradientFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGradientFillProperties) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void unsetGradFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTBlipFillProperties getBlipFill() {
        CTBlipFillProperties cTBlipFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTBlipFillProperties target = (CTBlipFillProperties) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTBlipFillProperties = target == null ? null : target;
        }
        return cTBlipFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public boolean isSetBlipFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void setBlipFill(CTBlipFillProperties blipFill) {
        generatedSetterHelperImpl(blipFill, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTBlipFillProperties addNewBlipFill() {
        CTBlipFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBlipFillProperties) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void unsetBlipFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTPatternFillProperties getPattFill() {
        CTPatternFillProperties cTPatternFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTPatternFillProperties target = (CTPatternFillProperties) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTPatternFillProperties = target == null ? null : target;
        }
        return cTPatternFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public boolean isSetPattFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void setPattFill(CTPatternFillProperties pattFill) {
        generatedSetterHelperImpl(pattFill, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTPatternFillProperties addNewPattFill() {
        CTPatternFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPatternFillProperties) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void unsetPattFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTGroupFillProperties getGrpFill() {
        CTGroupFillProperties cTGroupFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTGroupFillProperties target = (CTGroupFillProperties) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTGroupFillProperties = target == null ? null : target;
        }
        return cTGroupFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public boolean isSetGrpFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void setGrpFill(CTGroupFillProperties grpFill) {
        generatedSetterHelperImpl(grpFill, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTGroupFillProperties addNewGrpFill() {
        CTGroupFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupFillProperties) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void unsetGrpFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTLineProperties getLn() {
        CTLineProperties cTLineProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTLineProperties target = (CTLineProperties) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTLineProperties = target == null ? null : target;
        }
        return cTLineProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public boolean isSetLn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void setLn(CTLineProperties ln) {
        generatedSetterHelperImpl(ln, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTLineProperties addNewLn() {
        CTLineProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineProperties) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void unsetLn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTEffectList getEffectLst() {
        CTEffectList cTEffectList;
        synchronized (monitor()) {
            check_orphaned();
            CTEffectList target = (CTEffectList) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTEffectList = target == null ? null : target;
        }
        return cTEffectList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public boolean isSetEffectLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void setEffectLst(CTEffectList effectLst) {
        generatedSetterHelperImpl(effectLst, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTEffectList addNewEffectLst() {
        CTEffectList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEffectList) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void unsetEffectLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTEffectContainer getEffectDag() {
        CTEffectContainer cTEffectContainer;
        synchronized (monitor()) {
            check_orphaned();
            CTEffectContainer target = (CTEffectContainer) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTEffectContainer = target == null ? null : target;
        }
        return cTEffectContainer;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public boolean isSetEffectDag() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void setEffectDag(CTEffectContainer effectDag) {
        generatedSetterHelperImpl(effectDag, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTEffectContainer addNewEffectDag() {
        CTEffectContainer target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEffectContainer) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void unsetEffectDag() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTScene3D getScene3D() {
        CTScene3D cTScene3D;
        synchronized (monitor()) {
            check_orphaned();
            CTScene3D target = (CTScene3D) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTScene3D = target == null ? null : target;
        }
        return cTScene3D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public boolean isSetScene3D() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void setScene3D(CTScene3D scene3D) {
        generatedSetterHelperImpl(scene3D, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTScene3D addNewScene3D() {
        CTScene3D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTScene3D) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void unsetScene3D() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTShape3D getSp3D() {
        CTShape3D cTShape3D;
        synchronized (monitor()) {
            check_orphaned();
            CTShape3D target = (CTShape3D) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTShape3D = target == null ? null : target;
        }
        return cTShape3D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public boolean isSetSp3D() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void setSp3D(CTShape3D sp3D) {
        generatedSetterHelperImpl(sp3D, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTShape3D addNewSp3D() {
        CTShape3D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShape3D) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void unsetSp3D() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[14], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public STBlackWhiteMode.Enum getBwMode() {
        STBlackWhiteMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            r1 = target == null ? null : (STBlackWhiteMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public STBlackWhiteMode xgetBwMode() {
        STBlackWhiteMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBlackWhiteMode) get_store().find_attribute_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public boolean isSetBwMode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void setBwMode(STBlackWhiteMode.Enum bwMode) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.setEnumValue(bwMode);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void xsetBwMode(STBlackWhiteMode bwMode) {
        synchronized (monitor()) {
            check_orphaned();
            STBlackWhiteMode target = (STBlackWhiteMode) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (STBlackWhiteMode) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.set(bwMode);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties
    public void unsetBwMode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[15]);
        }
    }
}
