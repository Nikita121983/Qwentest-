package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLongHexNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrGeneral;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblStylePr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;

/* loaded from: classes12.dex */
public class CTStyleImpl extends XmlComplexContentImpl implements CTStyle {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "name"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "aliases"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "basedOn"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "next"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "link"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "autoRedefine"), new QName(XSSFRelation.NS_WORDPROCESSINGML, CellUtil.HIDDEN), new QName(XSSFRelation.NS_WORDPROCESSINGML, "uiPriority"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "semiHidden"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "unhideWhenUsed"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "qFormat"), new QName(XSSFRelation.NS_WORDPROCESSINGML, CellUtil.LOCKED), new QName(XSSFRelation.NS_WORDPROCESSINGML, "personal"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "personalCompose"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "personalReply"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rsid"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "pPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "trPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tcPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblStylePr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY), new QName(XSSFRelation.NS_WORDPROCESSINGML, "styleId"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "default"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customStyle")};
    private static final long serialVersionUID = 1;

    public CTStyleImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTString getName() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setName(CTString name) {
        generatedSetterHelperImpl(name, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTString addNewName() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTString getAliases() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetAliases() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setAliases(CTString aliases) {
        generatedSetterHelperImpl(aliases, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTString addNewAliases() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetAliases() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTString getBasedOn() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetBasedOn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setBasedOn(CTString basedOn) {
        generatedSetterHelperImpl(basedOn, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTString addNewBasedOn() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetBasedOn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTString getNext() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetNext() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setNext(CTString next) {
        generatedSetterHelperImpl(next, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTString addNewNext() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetNext() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTString getLink() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetLink() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setLink(CTString link) {
        generatedSetterHelperImpl(link, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTString addNewLink() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetLink() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff getAutoRedefine() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetAutoRedefine() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setAutoRedefine(CTOnOff autoRedefine) {
        generatedSetterHelperImpl(autoRedefine, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff addNewAutoRedefine() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetAutoRedefine() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff getHidden() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetHidden() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setHidden(CTOnOff hidden) {
        generatedSetterHelperImpl(hidden, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff addNewHidden() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetHidden() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTDecimalNumber getUiPriority() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumber target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTDecimalNumber = target == null ? null : target;
        }
        return cTDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetUiPriority() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setUiPriority(CTDecimalNumber uiPriority) {
        generatedSetterHelperImpl(uiPriority, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTDecimalNumber addNewUiPriority() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetUiPriority() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff getSemiHidden() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetSemiHidden() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setSemiHidden(CTOnOff semiHidden) {
        generatedSetterHelperImpl(semiHidden, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff addNewSemiHidden() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetSemiHidden() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff getUnhideWhenUsed() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetUnhideWhenUsed() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setUnhideWhenUsed(CTOnOff unhideWhenUsed) {
        generatedSetterHelperImpl(unhideWhenUsed, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff addNewUnhideWhenUsed() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetUnhideWhenUsed() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff getQFormat() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetQFormat() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setQFormat(CTOnOff qFormat) {
        generatedSetterHelperImpl(qFormat, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff addNewQFormat() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetQFormat() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff getLocked() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetLocked() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setLocked(CTOnOff locked) {
        generatedSetterHelperImpl(locked, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff addNewLocked() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetLocked() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff getPersonal() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetPersonal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setPersonal(CTOnOff personal) {
        generatedSetterHelperImpl(personal, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff addNewPersonal() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetPersonal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff getPersonalCompose() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetPersonalCompose() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setPersonalCompose(CTOnOff personalCompose) {
        generatedSetterHelperImpl(personalCompose, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff addNewPersonalCompose() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetPersonalCompose() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff getPersonalReply() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetPersonalReply() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setPersonalReply(CTOnOff personalReply) {
        generatedSetterHelperImpl(personalReply, PROPERTY_QNAME[14], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTOnOff addNewPersonalReply() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetPersonalReply() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTLongHexNumber getRsid() {
        CTLongHexNumber cTLongHexNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTLongHexNumber target = get_store().find_element_user(PROPERTY_QNAME[15], 0);
            cTLongHexNumber = target == null ? null : target;
        }
        return cTLongHexNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetRsid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setRsid(CTLongHexNumber rsid) {
        generatedSetterHelperImpl(rsid, PROPERTY_QNAME[15], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTLongHexNumber addNewRsid() {
        CTLongHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetRsid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTPPrGeneral getPPr() {
        CTPPrGeneral cTPPrGeneral;
        synchronized (monitor()) {
            check_orphaned();
            CTPPrGeneral target = (CTPPrGeneral) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            cTPPrGeneral = target == null ? null : target;
        }
        return cTPPrGeneral;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetPPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setPPr(CTPPrGeneral pPr) {
        generatedSetterHelperImpl(pPr, PROPERTY_QNAME[16], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTPPrGeneral addNewPPr() {
        CTPPrGeneral target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPPrGeneral) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetPPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTRPr getRPr() {
        CTRPr cTRPr;
        synchronized (monitor()) {
            check_orphaned();
            CTRPr target = (CTRPr) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            cTRPr = target == null ? null : target;
        }
        return cTRPr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setRPr(CTRPr rPr) {
        generatedSetterHelperImpl(rPr, PROPERTY_QNAME[17], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTRPr addNewRPr() {
        CTRPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRPr) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTTblPrBase getTblPr() {
        CTTblPrBase cTTblPrBase;
        synchronized (monitor()) {
            check_orphaned();
            CTTblPrBase target = (CTTblPrBase) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            cTTblPrBase = target == null ? null : target;
        }
        return cTTblPrBase;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetTblPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setTblPr(CTTblPrBase tblPr) {
        generatedSetterHelperImpl(tblPr, PROPERTY_QNAME[18], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTTblPrBase addNewTblPr() {
        CTTblPrBase target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblPrBase) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetTblPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTTrPr getTrPr() {
        CTTrPr cTTrPr;
        synchronized (monitor()) {
            check_orphaned();
            CTTrPr target = (CTTrPr) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            cTTrPr = target == null ? null : target;
        }
        return cTTrPr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetTrPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[19]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setTrPr(CTTrPr trPr) {
        generatedSetterHelperImpl(trPr, PROPERTY_QNAME[19], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTTrPr addNewTrPr() {
        CTTrPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrPr) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetTrPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTTcPr getTcPr() {
        CTTcPr cTTcPr;
        synchronized (monitor()) {
            check_orphaned();
            CTTcPr target = (CTTcPr) get_store().find_element_user(PROPERTY_QNAME[20], 0);
            cTTcPr = target == null ? null : target;
        }
        return cTTcPr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetTcPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[20]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setTcPr(CTTcPr tcPr) {
        generatedSetterHelperImpl(tcPr, PROPERTY_QNAME[20], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTTcPr addNewTcPr() {
        CTTcPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTcPr) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetTcPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public List<CTTblStylePr> getTblStylePrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTStyleImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTStyleImpl.this.getTblStylePrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTStyleImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTStyleImpl.this.setTblStylePrArray(((Integer) obj).intValue(), (CTTblStylePr) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTStyleImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTStyleImpl.this.insertNewTblStylePr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTStyleImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTStyleImpl.this.removeTblStylePr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTStyleImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTStyleImpl.this.sizeOfTblStylePrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTTblStylePr[] getTblStylePrArray() {
        return getXmlObjectArray(PROPERTY_QNAME[21], (XmlObject[]) new CTTblStylePr[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTTblStylePr getTblStylePrArray(int i) {
        CTTblStylePr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public int sizeOfTblStylePrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setTblStylePrArray(CTTblStylePr[] tblStylePrArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) tblStylePrArray, PROPERTY_QNAME[21]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setTblStylePrArray(int i, CTTblStylePr tblStylePr) {
        generatedSetterHelperImpl(tblStylePr, PROPERTY_QNAME[21], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTTblStylePr insertNewTblStylePr(int i) {
        CTTblStylePr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public CTTblStylePr addNewTblStylePr() {
        CTTblStylePr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void removeTblStylePr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public STStyleType.Enum getType() {
        STStyleType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            r1 = target == null ? null : (STStyleType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public STStyleType xgetType() {
        STStyleType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STStyleType) get_store().find_attribute_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[22]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setType(STStyleType.Enum type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.setEnumValue(type);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void xsetType(STStyleType type) {
        synchronized (monitor()) {
            check_orphaned();
            STStyleType target = (STStyleType) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (STStyleType) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.set(type);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[22]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public String getStyleId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public STString xgetStyleId() {
        STString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetStyleId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[23]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setStyleId(String styleId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.setStringValue(styleId);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void xsetStyleId(STString styleId) {
        synchronized (monitor()) {
            check_orphaned();
            STString target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (STString) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.set(styleId);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetStyleId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[23]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public Object getDefault() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public STOnOff xgetDefault() {
        STOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetDefault() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[24]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setDefault(Object xdefault) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.setObjectValue(xdefault);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void xsetDefault(STOnOff xdefault) {
        synchronized (monitor()) {
            check_orphaned();
            STOnOff target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (STOnOff) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.set(xdefault);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetDefault() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[24]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public Object getCustomStyle() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public STOnOff xgetCustomStyle() {
        STOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public boolean isSetCustomStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[25]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void setCustomStyle(Object customStyle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.setObjectValue(customStyle);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void xsetCustomStyle(STOnOff customStyle) {
        synchronized (monitor()) {
            check_orphaned();
            STOnOff target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (STOnOff) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.set(customStyle);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle
    public void unsetCustomStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[25]);
        }
    }
}
