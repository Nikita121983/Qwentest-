package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableBackgroundStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid;

/* loaded from: classes11.dex */
public class CTTableStyleImpl extends XmlComplexContentImpl implements CTTableStyle {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "tblBg"), new QName(XSSFRelation.NS_DRAWINGML, "wholeTbl"), new QName(XSSFRelation.NS_DRAWINGML, "band1H"), new QName(XSSFRelation.NS_DRAWINGML, "band2H"), new QName(XSSFRelation.NS_DRAWINGML, "band1V"), new QName(XSSFRelation.NS_DRAWINGML, "band2V"), new QName(XSSFRelation.NS_DRAWINGML, "lastCol"), new QName(XSSFRelation.NS_DRAWINGML, "firstCol"), new QName(XSSFRelation.NS_DRAWINGML, "lastRow"), new QName(XSSFRelation.NS_DRAWINGML, "seCell"), new QName(XSSFRelation.NS_DRAWINGML, "swCell"), new QName(XSSFRelation.NS_DRAWINGML, "firstRow"), new QName(XSSFRelation.NS_DRAWINGML, "neCell"), new QName(XSSFRelation.NS_DRAWINGML, "nwCell"), new QName(XSSFRelation.NS_DRAWINGML, "extLst"), new QName("", "styleId"), new QName("", "styleName")};
    private static final long serialVersionUID = 1;

    public CTTableStyleImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTableBackgroundStyle getTblBg() {
        CTTableBackgroundStyle cTTableBackgroundStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTableBackgroundStyle target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTableBackgroundStyle = target == null ? null : target;
        }
        return cTTableBackgroundStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public boolean isSetTblBg() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void setTblBg(CTTableBackgroundStyle tblBg) {
        generatedSetterHelperImpl(tblBg, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTableBackgroundStyle addNewTblBg() {
        CTTableBackgroundStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void unsetTblBg() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle getWholeTbl() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTablePartStyle target = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTablePartStyle = target == null ? null : target;
        }
        return cTTablePartStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public boolean isSetWholeTbl() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void setWholeTbl(CTTablePartStyle wholeTbl) {
        generatedSetterHelperImpl(wholeTbl, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle addNewWholeTbl() {
        CTTablePartStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void unsetWholeTbl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle getBand1H() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTablePartStyle target = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTTablePartStyle = target == null ? null : target;
        }
        return cTTablePartStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public boolean isSetBand1H() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void setBand1H(CTTablePartStyle band1H) {
        generatedSetterHelperImpl(band1H, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle addNewBand1H() {
        CTTablePartStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void unsetBand1H() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle getBand2H() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTablePartStyle target = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTTablePartStyle = target == null ? null : target;
        }
        return cTTablePartStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public boolean isSetBand2H() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void setBand2H(CTTablePartStyle band2H) {
        generatedSetterHelperImpl(band2H, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle addNewBand2H() {
        CTTablePartStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void unsetBand2H() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle getBand1V() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTablePartStyle target = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTTablePartStyle = target == null ? null : target;
        }
        return cTTablePartStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public boolean isSetBand1V() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void setBand1V(CTTablePartStyle band1V) {
        generatedSetterHelperImpl(band1V, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle addNewBand1V() {
        CTTablePartStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void unsetBand1V() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle getBand2V() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTablePartStyle target = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTTablePartStyle = target == null ? null : target;
        }
        return cTTablePartStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public boolean isSetBand2V() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void setBand2V(CTTablePartStyle band2V) {
        generatedSetterHelperImpl(band2V, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle addNewBand2V() {
        CTTablePartStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void unsetBand2V() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle getLastCol() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTablePartStyle target = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTTablePartStyle = target == null ? null : target;
        }
        return cTTablePartStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public boolean isSetLastCol() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void setLastCol(CTTablePartStyle lastCol) {
        generatedSetterHelperImpl(lastCol, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle addNewLastCol() {
        CTTablePartStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void unsetLastCol() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle getFirstCol() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTablePartStyle target = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTTablePartStyle = target == null ? null : target;
        }
        return cTTablePartStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public boolean isSetFirstCol() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void setFirstCol(CTTablePartStyle firstCol) {
        generatedSetterHelperImpl(firstCol, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle addNewFirstCol() {
        CTTablePartStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void unsetFirstCol() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle getLastRow() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTablePartStyle target = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTTablePartStyle = target == null ? null : target;
        }
        return cTTablePartStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public boolean isSetLastRow() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void setLastRow(CTTablePartStyle lastRow) {
        generatedSetterHelperImpl(lastRow, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle addNewLastRow() {
        CTTablePartStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void unsetLastRow() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle getSeCell() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTablePartStyle target = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTTablePartStyle = target == null ? null : target;
        }
        return cTTablePartStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public boolean isSetSeCell() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void setSeCell(CTTablePartStyle seCell) {
        generatedSetterHelperImpl(seCell, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle addNewSeCell() {
        CTTablePartStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void unsetSeCell() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle getSwCell() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTablePartStyle target = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTTablePartStyle = target == null ? null : target;
        }
        return cTTablePartStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public boolean isSetSwCell() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void setSwCell(CTTablePartStyle swCell) {
        generatedSetterHelperImpl(swCell, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle addNewSwCell() {
        CTTablePartStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void unsetSwCell() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle getFirstRow() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTablePartStyle target = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTTablePartStyle = target == null ? null : target;
        }
        return cTTablePartStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public boolean isSetFirstRow() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void setFirstRow(CTTablePartStyle firstRow) {
        generatedSetterHelperImpl(firstRow, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle addNewFirstRow() {
        CTTablePartStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void unsetFirstRow() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle getNeCell() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTablePartStyle target = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTTablePartStyle = target == null ? null : target;
        }
        return cTTablePartStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public boolean isSetNeCell() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void setNeCell(CTTablePartStyle neCell) {
        generatedSetterHelperImpl(neCell, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle addNewNeCell() {
        CTTablePartStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void unsetNeCell() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle getNwCell() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTablePartStyle target = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTTablePartStyle = target == null ? null : target;
        }
        return cTTablePartStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public boolean isSetNwCell() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void setNwCell(CTTablePartStyle nwCell) {
        generatedSetterHelperImpl(nwCell, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTTablePartStyle addNewNwCell() {
        CTTablePartStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void unsetNwCell() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[14], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public String getStyleId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public STGuid xgetStyleId() {
        STGuid target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STGuid) get_store().find_attribute_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void setStyleId(String styleId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.setStringValue(styleId);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void xsetStyleId(STGuid styleId) {
        synchronized (monitor()) {
            check_orphaned();
            STGuid target = (STGuid) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (STGuid) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.set(styleId);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public String getStyleName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public XmlString xgetStyleName() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void setStyleName(String styleName) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.setStringValue(styleName);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle
    public void xsetStyleName(XmlString styleName) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.set(styleName);
        }
    }
}
