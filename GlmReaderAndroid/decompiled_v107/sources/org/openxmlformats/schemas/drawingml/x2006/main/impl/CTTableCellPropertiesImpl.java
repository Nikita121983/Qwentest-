package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCell3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHeaders;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate32;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextHorzOverflowType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVerticalType;

/* loaded from: classes11.dex */
public class CTTableCellPropertiesImpl extends XmlComplexContentImpl implements CTTableCellProperties {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "lnL"), new QName(XSSFRelation.NS_DRAWINGML, "lnR"), new QName(XSSFRelation.NS_DRAWINGML, "lnT"), new QName(XSSFRelation.NS_DRAWINGML, "lnB"), new QName(XSSFRelation.NS_DRAWINGML, "lnTlToBr"), new QName(XSSFRelation.NS_DRAWINGML, "lnBlToTr"), new QName(XSSFRelation.NS_DRAWINGML, "cell3D"), new QName(XSSFRelation.NS_DRAWINGML, "noFill"), new QName(XSSFRelation.NS_DRAWINGML, "solidFill"), new QName(XSSFRelation.NS_DRAWINGML, "gradFill"), new QName(XSSFRelation.NS_DRAWINGML, "blipFill"), new QName(XSSFRelation.NS_DRAWINGML, "pattFill"), new QName(XSSFRelation.NS_DRAWINGML, "grpFill"), new QName(XSSFRelation.NS_DRAWINGML, "headers"), new QName(XSSFRelation.NS_DRAWINGML, "extLst"), new QName("", "marL"), new QName("", "marR"), new QName("", "marT"), new QName("", "marB"), new QName("", "vert"), new QName("", "anchor"), new QName("", "anchorCtr"), new QName("", "horzOverflow")};
    private static final long serialVersionUID = 1;

    public CTTableCellPropertiesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTLineProperties getLnL() {
        CTLineProperties cTLineProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTLineProperties target = (CTLineProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTLineProperties = target == null ? null : target;
        }
        return cTLineProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetLnL() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setLnL(CTLineProperties lnL) {
        generatedSetterHelperImpl(lnL, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTLineProperties addNewLnL() {
        CTLineProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetLnL() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTLineProperties getLnR() {
        CTLineProperties cTLineProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTLineProperties target = (CTLineProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTLineProperties = target == null ? null : target;
        }
        return cTLineProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetLnR() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setLnR(CTLineProperties lnR) {
        generatedSetterHelperImpl(lnR, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTLineProperties addNewLnR() {
        CTLineProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetLnR() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTLineProperties getLnT() {
        CTLineProperties cTLineProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTLineProperties target = (CTLineProperties) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTLineProperties = target == null ? null : target;
        }
        return cTLineProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetLnT() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setLnT(CTLineProperties lnT) {
        generatedSetterHelperImpl(lnT, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTLineProperties addNewLnT() {
        CTLineProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineProperties) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetLnT() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTLineProperties getLnB() {
        CTLineProperties cTLineProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTLineProperties target = (CTLineProperties) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTLineProperties = target == null ? null : target;
        }
        return cTLineProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetLnB() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setLnB(CTLineProperties lnB) {
        generatedSetterHelperImpl(lnB, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTLineProperties addNewLnB() {
        CTLineProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineProperties) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetLnB() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTLineProperties getLnTlToBr() {
        CTLineProperties cTLineProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTLineProperties target = (CTLineProperties) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTLineProperties = target == null ? null : target;
        }
        return cTLineProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetLnTlToBr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setLnTlToBr(CTLineProperties lnTlToBr) {
        generatedSetterHelperImpl(lnTlToBr, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTLineProperties addNewLnTlToBr() {
        CTLineProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineProperties) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetLnTlToBr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTLineProperties getLnBlToTr() {
        CTLineProperties cTLineProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTLineProperties target = (CTLineProperties) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTLineProperties = target == null ? null : target;
        }
        return cTLineProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetLnBlToTr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setLnBlToTr(CTLineProperties lnBlToTr) {
        generatedSetterHelperImpl(lnBlToTr, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTLineProperties addNewLnBlToTr() {
        CTLineProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineProperties) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetLnBlToTr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTCell3D getCell3D() {
        CTCell3D cTCell3D;
        synchronized (monitor()) {
            check_orphaned();
            CTCell3D target = get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTCell3D = target == null ? null : target;
        }
        return cTCell3D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetCell3D() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setCell3D(CTCell3D cell3D) {
        generatedSetterHelperImpl(cell3D, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTCell3D addNewCell3D() {
        CTCell3D target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetCell3D() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTNoFillProperties getNoFill() {
        CTNoFillProperties cTNoFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTNoFillProperties target = (CTNoFillProperties) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTNoFillProperties = target == null ? null : target;
        }
        return cTNoFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetNoFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setNoFill(CTNoFillProperties noFill) {
        generatedSetterHelperImpl(noFill, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTNoFillProperties addNewNoFill() {
        CTNoFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNoFillProperties) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetNoFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTSolidColorFillProperties getSolidFill() {
        CTSolidColorFillProperties cTSolidColorFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTSolidColorFillProperties target = (CTSolidColorFillProperties) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTSolidColorFillProperties = target == null ? null : target;
        }
        return cTSolidColorFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetSolidFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setSolidFill(CTSolidColorFillProperties solidFill) {
        generatedSetterHelperImpl(solidFill, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTSolidColorFillProperties addNewSolidFill() {
        CTSolidColorFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSolidColorFillProperties) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetSolidFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTGradientFillProperties getGradFill() {
        CTGradientFillProperties cTGradientFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTGradientFillProperties target = (CTGradientFillProperties) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTGradientFillProperties = target == null ? null : target;
        }
        return cTGradientFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetGradFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setGradFill(CTGradientFillProperties gradFill) {
        generatedSetterHelperImpl(gradFill, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTGradientFillProperties addNewGradFill() {
        CTGradientFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGradientFillProperties) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetGradFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTBlipFillProperties getBlipFill() {
        CTBlipFillProperties cTBlipFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTBlipFillProperties target = (CTBlipFillProperties) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTBlipFillProperties = target == null ? null : target;
        }
        return cTBlipFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetBlipFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setBlipFill(CTBlipFillProperties blipFill) {
        generatedSetterHelperImpl(blipFill, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTBlipFillProperties addNewBlipFill() {
        CTBlipFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBlipFillProperties) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetBlipFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTPatternFillProperties getPattFill() {
        CTPatternFillProperties cTPatternFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTPatternFillProperties target = (CTPatternFillProperties) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTPatternFillProperties = target == null ? null : target;
        }
        return cTPatternFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetPattFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setPattFill(CTPatternFillProperties pattFill) {
        generatedSetterHelperImpl(pattFill, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTPatternFillProperties addNewPattFill() {
        CTPatternFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPatternFillProperties) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetPattFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTGroupFillProperties getGrpFill() {
        CTGroupFillProperties cTGroupFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTGroupFillProperties target = (CTGroupFillProperties) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTGroupFillProperties = target == null ? null : target;
        }
        return cTGroupFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetGrpFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setGrpFill(CTGroupFillProperties grpFill) {
        generatedSetterHelperImpl(grpFill, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTGroupFillProperties addNewGrpFill() {
        CTGroupFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupFillProperties) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetGrpFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTHeaders getHeaders() {
        CTHeaders cTHeaders;
        synchronized (monitor()) {
            check_orphaned();
            CTHeaders target = get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTHeaders = target == null ? null : target;
        }
        return cTHeaders;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetHeaders() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setHeaders(CTHeaders headers) {
        generatedSetterHelperImpl(headers, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTHeaders addNewHeaders() {
        CTHeaders target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetHeaders() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[14], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public Object getMarL() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[15]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public STCoordinate32 xgetMarL() {
        STCoordinate32 target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (STCoordinate32) get_default_attribute_value(PROPERTY_QNAME[15]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetMarL() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setMarL(Object marL) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.setObjectValue(marL);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void xsetMarL(STCoordinate32 marL) {
        synchronized (monitor()) {
            check_orphaned();
            STCoordinate32 target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (STCoordinate32) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.set(marL);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetMarL() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[15]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public Object getMarR() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[16]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public STCoordinate32 xgetMarR() {
        STCoordinate32 target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (STCoordinate32) get_default_attribute_value(PROPERTY_QNAME[16]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetMarR() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[16]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setMarR(Object marR) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.setObjectValue(marR);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void xsetMarR(STCoordinate32 marR) {
        synchronized (monitor()) {
            check_orphaned();
            STCoordinate32 target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (STCoordinate32) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.set(marR);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetMarR() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[16]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public Object getMarT() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[17]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public STCoordinate32 xgetMarT() {
        STCoordinate32 target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (STCoordinate32) get_default_attribute_value(PROPERTY_QNAME[17]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetMarT() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[17]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setMarT(Object marT) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.setObjectValue(marT);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void xsetMarT(STCoordinate32 marT) {
        synchronized (monitor()) {
            check_orphaned();
            STCoordinate32 target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (STCoordinate32) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.set(marT);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetMarT() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[17]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public Object getMarB() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[18]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public STCoordinate32 xgetMarB() {
        STCoordinate32 target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (STCoordinate32) get_default_attribute_value(PROPERTY_QNAME[18]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetMarB() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[18]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setMarB(Object marB) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.setObjectValue(marB);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void xsetMarB(STCoordinate32 marB) {
        synchronized (monitor()) {
            check_orphaned();
            STCoordinate32 target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (STCoordinate32) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.set(marB);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetMarB() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[18]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public STTextVerticalType.Enum getVert() {
        STTextVerticalType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[19]);
            }
            r1 = target == null ? null : (STTextVerticalType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public STTextVerticalType xgetVert() {
        STTextVerticalType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextVerticalType) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (STTextVerticalType) get_default_attribute_value(PROPERTY_QNAME[19]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetVert() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[19]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setVert(STTextVerticalType.Enum vert) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.setEnumValue(vert);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void xsetVert(STTextVerticalType vert) {
        synchronized (monitor()) {
            check_orphaned();
            STTextVerticalType target = (STTextVerticalType) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (STTextVerticalType) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.set(vert);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetVert() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[19]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public STTextAnchoringType.Enum getAnchor() {
        STTextAnchoringType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[20]);
            }
            r1 = target == null ? null : (STTextAnchoringType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public STTextAnchoringType xgetAnchor() {
        STTextAnchoringType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextAnchoringType) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (STTextAnchoringType) get_default_attribute_value(PROPERTY_QNAME[20]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetAnchor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[20]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setAnchor(STTextAnchoringType.Enum anchor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.setEnumValue(anchor);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void xsetAnchor(STTextAnchoringType anchor) {
        synchronized (monitor()) {
            check_orphaned();
            STTextAnchoringType target = (STTextAnchoringType) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (STTextAnchoringType) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.set(anchor);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetAnchor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[20]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean getAnchorCtr() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[21]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public XmlBoolean xgetAnchorCtr() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[21]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetAnchorCtr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[21]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setAnchorCtr(boolean anchorCtr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.setBooleanValue(anchorCtr);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void xsetAnchorCtr(XmlBoolean anchorCtr) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.set(anchorCtr);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetAnchorCtr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[21]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public STTextHorzOverflowType.Enum getHorzOverflow() {
        STTextHorzOverflowType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[22]);
            }
            r1 = target == null ? null : (STTextHorzOverflowType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public STTextHorzOverflowType xgetHorzOverflow() {
        STTextHorzOverflowType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextHorzOverflowType) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (STTextHorzOverflowType) get_default_attribute_value(PROPERTY_QNAME[22]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public boolean isSetHorzOverflow() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[22]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void setHorzOverflow(STTextHorzOverflowType.Enum horzOverflow) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.setEnumValue(horzOverflow);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void xsetHorzOverflow(STTextHorzOverflowType horzOverflow) {
        synchronized (monitor()) {
            check_orphaned();
            STTextHorzOverflowType target = (STTextHorzOverflowType) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (STTextHorzOverflowType) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.set(horzOverflow);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties
    public void unsetHorzOverflow() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[22]);
        }
    }
}
