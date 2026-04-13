package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEmpty;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPlaceholder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtComboBox;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDocPart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnsignedDecimalNumber;

/* loaded from: classes12.dex */
public class CTSdtPrImpl extends XmlComplexContentImpl implements CTSdtPr {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "rPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "alias"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tag"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "id"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "lock"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "placeholder"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "temporary"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "showingPlcHdr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "dataBinding"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "label"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tabIndex"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "equation"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "comboBox"), new QName(XSSFRelation.NS_WORDPROCESSINGML, XmlErrorCodes.DATE), new QName(XSSFRelation.NS_WORDPROCESSINGML, "docPartObj"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "docPartList"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "dropDownList"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "picture"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "richText"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "text"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "citation"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "group"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bibliography")};
    private static final long serialVersionUID = 1;

    public CTSdtPrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTRPr getRPr() {
        CTRPr cTRPr;
        synchronized (monitor()) {
            check_orphaned();
            CTRPr target = (CTRPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTRPr = target == null ? null : target;
        }
        return cTRPr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setRPr(CTRPr rPr) {
        generatedSetterHelperImpl(rPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTRPr addNewRPr() {
        CTRPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTString getAlias() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetAlias() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setAlias(CTString alias) {
        generatedSetterHelperImpl(alias, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTString addNewAlias() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetAlias() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTString getTag() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetTag() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setTag(CTString tag) {
        generatedSetterHelperImpl(tag, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTString addNewTag() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetTag() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTDecimalNumber getId() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumber target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTDecimalNumber = target == null ? null : target;
        }
        return cTDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setId(CTDecimalNumber id) {
        generatedSetterHelperImpl(id, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTDecimalNumber addNewId() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTLock getLock() {
        CTLock cTLock;
        synchronized (monitor()) {
            check_orphaned();
            CTLock target = (CTLock) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTLock = target == null ? null : target;
        }
        return cTLock;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetLock() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setLock(CTLock lock) {
        generatedSetterHelperImpl(lock, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTLock addNewLock() {
        CTLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLock) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetLock() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTPlaceholder getPlaceholder() {
        CTPlaceholder cTPlaceholder;
        synchronized (monitor()) {
            check_orphaned();
            CTPlaceholder target = (CTPlaceholder) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTPlaceholder = target == null ? null : target;
        }
        return cTPlaceholder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetPlaceholder() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setPlaceholder(CTPlaceholder placeholder) {
        generatedSetterHelperImpl(placeholder, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTPlaceholder addNewPlaceholder() {
        CTPlaceholder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPlaceholder) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetPlaceholder() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTOnOff getTemporary() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetTemporary() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setTemporary(CTOnOff temporary) {
        generatedSetterHelperImpl(temporary, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTOnOff addNewTemporary() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetTemporary() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTOnOff getShowingPlcHdr() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetShowingPlcHdr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setShowingPlcHdr(CTOnOff showingPlcHdr) {
        generatedSetterHelperImpl(showingPlcHdr, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTOnOff addNewShowingPlcHdr() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetShowingPlcHdr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTDataBinding getDataBinding() {
        CTDataBinding cTDataBinding;
        synchronized (monitor()) {
            check_orphaned();
            CTDataBinding target = (CTDataBinding) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTDataBinding = target == null ? null : target;
        }
        return cTDataBinding;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetDataBinding() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setDataBinding(CTDataBinding dataBinding) {
        generatedSetterHelperImpl(dataBinding, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTDataBinding addNewDataBinding() {
        CTDataBinding target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDataBinding) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetDataBinding() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTDecimalNumber getLabel() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumber target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTDecimalNumber = target == null ? null : target;
        }
        return cTDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetLabel() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setLabel(CTDecimalNumber label) {
        generatedSetterHelperImpl(label, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTDecimalNumber addNewLabel() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetLabel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTUnsignedDecimalNumber getTabIndex() {
        CTUnsignedDecimalNumber cTUnsignedDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTUnsignedDecimalNumber target = get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTUnsignedDecimalNumber = target == null ? null : target;
        }
        return cTUnsignedDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetTabIndex() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setTabIndex(CTUnsignedDecimalNumber tabIndex) {
        generatedSetterHelperImpl(tabIndex, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTUnsignedDecimalNumber addNewTabIndex() {
        CTUnsignedDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetTabIndex() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTEmpty getEquation() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            CTEmpty target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTEmpty = target == null ? null : target;
        }
        return cTEmpty;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetEquation() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setEquation(CTEmpty equation) {
        generatedSetterHelperImpl(equation, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTEmpty addNewEquation() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetEquation() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTSdtComboBox getComboBox() {
        CTSdtComboBox cTSdtComboBox;
        synchronized (monitor()) {
            check_orphaned();
            CTSdtComboBox target = (CTSdtComboBox) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTSdtComboBox = target == null ? null : target;
        }
        return cTSdtComboBox;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetComboBox() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setComboBox(CTSdtComboBox comboBox) {
        generatedSetterHelperImpl(comboBox, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTSdtComboBox addNewComboBox() {
        CTSdtComboBox target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtComboBox) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetComboBox() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTSdtDate getDate() {
        CTSdtDate cTSdtDate;
        synchronized (monitor()) {
            check_orphaned();
            CTSdtDate target = (CTSdtDate) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTSdtDate = target == null ? null : target;
        }
        return cTSdtDate;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetDate() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setDate(CTSdtDate date) {
        generatedSetterHelperImpl(date, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTSdtDate addNewDate() {
        CTSdtDate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtDate) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetDate() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTSdtDocPart getDocPartObj() {
        CTSdtDocPart cTSdtDocPart;
        synchronized (monitor()) {
            check_orphaned();
            CTSdtDocPart target = (CTSdtDocPart) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            cTSdtDocPart = target == null ? null : target;
        }
        return cTSdtDocPart;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetDocPartObj() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setDocPartObj(CTSdtDocPart docPartObj) {
        generatedSetterHelperImpl(docPartObj, PROPERTY_QNAME[14], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTSdtDocPart addNewDocPartObj() {
        CTSdtDocPart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtDocPart) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetDocPartObj() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTSdtDocPart getDocPartList() {
        CTSdtDocPart cTSdtDocPart;
        synchronized (monitor()) {
            check_orphaned();
            CTSdtDocPart target = (CTSdtDocPart) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            cTSdtDocPart = target == null ? null : target;
        }
        return cTSdtDocPart;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetDocPartList() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setDocPartList(CTSdtDocPart docPartList) {
        generatedSetterHelperImpl(docPartList, PROPERTY_QNAME[15], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTSdtDocPart addNewDocPartList() {
        CTSdtDocPart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtDocPart) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetDocPartList() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTSdtDropDownList getDropDownList() {
        CTSdtDropDownList cTSdtDropDownList;
        synchronized (monitor()) {
            check_orphaned();
            CTSdtDropDownList target = (CTSdtDropDownList) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            cTSdtDropDownList = target == null ? null : target;
        }
        return cTSdtDropDownList;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetDropDownList() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setDropDownList(CTSdtDropDownList dropDownList) {
        generatedSetterHelperImpl(dropDownList, PROPERTY_QNAME[16], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTSdtDropDownList addNewDropDownList() {
        CTSdtDropDownList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtDropDownList) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetDropDownList() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTEmpty getPicture() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            CTEmpty target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            cTEmpty = target == null ? null : target;
        }
        return cTEmpty;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetPicture() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setPicture(CTEmpty picture) {
        generatedSetterHelperImpl(picture, PROPERTY_QNAME[17], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTEmpty addNewPicture() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetPicture() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTEmpty getRichText() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            CTEmpty target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            cTEmpty = target == null ? null : target;
        }
        return cTEmpty;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetRichText() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setRichText(CTEmpty richText) {
        generatedSetterHelperImpl(richText, PROPERTY_QNAME[18], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTEmpty addNewRichText() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetRichText() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTSdtText getText() {
        CTSdtText cTSdtText;
        synchronized (monitor()) {
            check_orphaned();
            CTSdtText target = (CTSdtText) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            cTSdtText = target == null ? null : target;
        }
        return cTSdtText;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetText() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[19]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setText(CTSdtText text) {
        generatedSetterHelperImpl(text, PROPERTY_QNAME[19], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTSdtText addNewText() {
        CTSdtText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtText) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetText() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTEmpty getCitation() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            CTEmpty target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[20], 0);
            cTEmpty = target == null ? null : target;
        }
        return cTEmpty;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetCitation() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[20]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setCitation(CTEmpty citation) {
        generatedSetterHelperImpl(citation, PROPERTY_QNAME[20], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTEmpty addNewCitation() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetCitation() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTEmpty getGroup() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            CTEmpty target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[21], 0);
            cTEmpty = target == null ? null : target;
        }
        return cTEmpty;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetGroup() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[21]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setGroup(CTEmpty group) {
        generatedSetterHelperImpl(group, PROPERTY_QNAME[21], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTEmpty addNewGroup() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetGroup() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTEmpty getBibliography() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            CTEmpty target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[22], 0);
            cTEmpty = target == null ? null : target;
        }
        return cTEmpty;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public boolean isSetBibliography() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[22]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void setBibliography(CTEmpty bibliography) {
        generatedSetterHelperImpl(bibliography, PROPERTY_QNAME[22], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public CTEmpty addNewBibliography() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr
    public void unsetBibliography() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], 0);
        }
    }
}
