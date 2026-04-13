package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.office.office.STBWMode;
import com.microsoft.schemas.office.office.STScreenSize;
import com.microsoft.schemas.office.office.STScreenSize$Enum;
import com.microsoft.schemas.vml.CTBackground;
import com.microsoft.schemas.vml.CTFill;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STColorType;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;

/* loaded from: classes9.dex */
public class CTBackgroundImpl extends XmlComplexContentImpl implements CTBackground {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-microsoft-com:vml", "fill"), new QName("", "id"), new QName("", "filled"), new QName("", "fillcolor"), new QName("urn:schemas-microsoft-com:office:office", "bwmode"), new QName("urn:schemas-microsoft-com:office:office", "bwpure"), new QName("urn:schemas-microsoft-com:office:office", "bwnormal"), new QName("urn:schemas-microsoft-com:office:office", "targetscreensize")};
    private static final long serialVersionUID = 1;

    public CTBackgroundImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public CTFill getFill() {
        CTFill cTFill;
        synchronized (monitor()) {
            check_orphaned();
            CTFill target = (CTFill) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTFill = target == null ? null : target;
        }
        return cTFill;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public boolean isSetFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void setFill(CTFill fill) {
        generatedSetterHelperImpl(fill, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public CTFill addNewFill() {
        CTFill target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFill) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void unsetFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public XmlString xgetId() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public boolean isSetId() {
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

    @Override // com.microsoft.schemas.vml.CTBackground
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(id);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void xsetId(XmlString id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(id);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public STTrueFalse.Enum getFilled() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public STTrueFalse xgetFilled() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public boolean isSetFilled() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void setFilled(STTrueFalse.Enum filled) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setEnumValue(filled);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void xsetFilled(STTrueFalse filled) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(filled);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void unsetFilled() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public String getFillcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public STColorType xgetFillcolor() {
        STColorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public boolean isSetFillcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void setFillcolor(String fillcolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(fillcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void xsetFillcolor(STColorType fillcolor) {
        synchronized (monitor()) {
            check_orphaned();
            STColorType target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STColorType) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(fillcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void unsetFillcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public STBWMode.Enum getBwmode() {
        STBWMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            r1 = target == null ? null : (STBWMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public STBWMode xgetBwmode() {
        STBWMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public boolean isSetBwmode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void setBwmode(STBWMode.Enum bwmode) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setEnumValue(bwmode);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void xsetBwmode(STBWMode bwmode) {
        synchronized (monitor()) {
            check_orphaned();
            STBWMode target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STBWMode) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(bwmode);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void unsetBwmode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public STBWMode.Enum getBwpure() {
        STBWMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            r1 = target == null ? null : (STBWMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public STBWMode xgetBwpure() {
        STBWMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public boolean isSetBwpure() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void setBwpure(STBWMode.Enum bwpure) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setEnumValue(bwpure);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void xsetBwpure(STBWMode bwpure) {
        synchronized (monitor()) {
            check_orphaned();
            STBWMode target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STBWMode) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(bwpure);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void unsetBwpure() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public STBWMode.Enum getBwnormal() {
        STBWMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            r1 = target == null ? null : (STBWMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public STBWMode xgetBwnormal() {
        STBWMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public boolean isSetBwnormal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void setBwnormal(STBWMode.Enum bwnormal) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setEnumValue(bwnormal);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void xsetBwnormal(STBWMode bwnormal) {
        synchronized (monitor()) {
            check_orphaned();
            STBWMode target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STBWMode) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(bwnormal);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void unsetBwnormal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public STScreenSize$Enum getTargetscreensize() {
        STScreenSize$Enum sTScreenSize$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            sTScreenSize$Enum = target == null ? null : (STScreenSize$Enum) target.getEnumValue();
        }
        return sTScreenSize$Enum;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public STScreenSize xgetTargetscreensize() {
        STScreenSize target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public boolean isSetTargetscreensize() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void setTargetscreensize(STScreenSize$Enum targetscreensize) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setEnumValue(targetscreensize);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void xsetTargetscreensize(STScreenSize targetscreensize) {
        synchronized (monitor()) {
            check_orphaned();
            STScreenSize target = get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STScreenSize) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(targetscreensize);
        }
    }

    @Override // com.microsoft.schemas.vml.CTBackground
    public void unsetTargetscreensize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }
}
