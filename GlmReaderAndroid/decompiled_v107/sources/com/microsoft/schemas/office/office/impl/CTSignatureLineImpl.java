package com.microsoft.schemas.office.office.impl;

import com.microsoft.schemas.office.office.CTSignatureLine;
import com.microsoft.schemas.vml.STExt;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;

/* loaded from: classes.dex */
public class CTSignatureLineImpl extends XmlComplexContentImpl implements CTSignatureLine {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-microsoft-com:vml", "ext"), new QName("", "issignatureline"), new QName("", "id"), new QName("", "provid"), new QName("", "signinginstructionsset"), new QName("", "allowcomments"), new QName("", "showsigndate"), new QName("urn:schemas-microsoft-com:office:office", "suggestedsigner"), new QName("urn:schemas-microsoft-com:office:office", "suggestedsigner2"), new QName("urn:schemas-microsoft-com:office:office", "suggestedsigneremail"), new QName("", "signinginstructions"), new QName("", "addlxml"), new QName("", "sigprovurl")};
    private static final long serialVersionUID = 1;

    public CTSignatureLineImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public STExt.Enum getExt() {
        STExt.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STExt.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public STExt xgetExt() {
        STExt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STExt) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public boolean isSetExt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void setExt(STExt.Enum ext) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(ext);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void xsetExt(STExt ext) {
        synchronized (monitor()) {
            check_orphaned();
            STExt target = (STExt) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STExt) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(ext);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void unsetExt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public STTrueFalse.Enum getIssignatureline() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public STTrueFalse xgetIssignatureline() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public boolean isSetIssignatureline() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().find_attribute_user(PROPERTY_QNAME[1]) == null) {
                z = false;
            }
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void setIssignatureline(STTrueFalse.Enum issignatureline) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(issignatureline);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void xsetIssignatureline(STTrueFalse issignatureline) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(issignatureline);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void unsetIssignatureline() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public STGuid xgetId() {
        STGuid target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STGuid) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(id);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void xsetId(STGuid id) {
        synchronized (monitor()) {
            check_orphaned();
            STGuid target = (STGuid) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STGuid) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(id);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public String getProvid() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public STGuid xgetProvid() {
        STGuid target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STGuid) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public boolean isSetProvid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void setProvid(String provid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(provid);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void xsetProvid(STGuid provid) {
        synchronized (monitor()) {
            check_orphaned();
            STGuid target = (STGuid) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STGuid) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(provid);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void unsetProvid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public STTrueFalse.Enum getSigninginstructionsset() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public STTrueFalse xgetSigninginstructionsset() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public boolean isSetSigninginstructionsset() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void setSigninginstructionsset(STTrueFalse.Enum signinginstructionsset) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setEnumValue(signinginstructionsset);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void xsetSigninginstructionsset(STTrueFalse signinginstructionsset) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(signinginstructionsset);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void unsetSigninginstructionsset() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public STTrueFalse.Enum getAllowcomments() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public STTrueFalse xgetAllowcomments() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public boolean isSetAllowcomments() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void setAllowcomments(STTrueFalse.Enum allowcomments) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setEnumValue(allowcomments);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void xsetAllowcomments(STTrueFalse allowcomments) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(allowcomments);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void unsetAllowcomments() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public STTrueFalse.Enum getShowsigndate() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public STTrueFalse xgetShowsigndate() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public boolean isSetShowsigndate() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void setShowsigndate(STTrueFalse.Enum showsigndate) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setEnumValue(showsigndate);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void xsetShowsigndate(STTrueFalse showsigndate) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(showsigndate);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void unsetShowsigndate() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public String getSuggestedsigner() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public XmlString xgetSuggestedsigner() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public boolean isSetSuggestedsigner() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void setSuggestedsigner(String suggestedsigner) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setStringValue(suggestedsigner);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void xsetSuggestedsigner(XmlString suggestedsigner) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(suggestedsigner);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void unsetSuggestedsigner() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public String getSuggestedsigner2() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public XmlString xgetSuggestedsigner2() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public boolean isSetSuggestedsigner2() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void setSuggestedsigner2(String suggestedsigner2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setStringValue(suggestedsigner2);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void xsetSuggestedsigner2(XmlString suggestedsigner2) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(suggestedsigner2);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void unsetSuggestedsigner2() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public String getSuggestedsigneremail() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public XmlString xgetSuggestedsigneremail() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public boolean isSetSuggestedsigneremail() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void setSuggestedsigneremail(String suggestedsigneremail) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setStringValue(suggestedsigneremail);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void xsetSuggestedsigneremail(XmlString suggestedsigneremail) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(suggestedsigneremail);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void unsetSuggestedsigneremail() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public String getSigninginstructions() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public XmlString xgetSigninginstructions() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public boolean isSetSigninginstructions() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void setSigninginstructions(String signinginstructions) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setStringValue(signinginstructions);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void xsetSigninginstructions(XmlString signinginstructions) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(signinginstructions);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void unsetSigninginstructions() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public String getAddlxml() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public XmlString xgetAddlxml() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public boolean isSetAddlxml() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void setAddlxml(String addlxml) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setStringValue(addlxml);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void xsetAddlxml(XmlString addlxml) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(addlxml);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void unsetAddlxml() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public String getSigprovurl() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public XmlString xgetSigprovurl() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public boolean isSetSigprovurl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void setSigprovurl(String sigprovurl) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setStringValue(sigprovurl);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void xsetSigprovurl(XmlString sigprovurl) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(sigprovurl);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTSignatureLine
    public void unsetSigprovurl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }
}
