package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXAlign;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STYAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHAnchor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVAnchor;

/* loaded from: classes12.dex */
public class CTTblPPrImpl extends XmlComplexContentImpl implements CTTblPPr {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "leftFromText"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rightFromText"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "topFromText"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bottomFromText"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "vertAnchor"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "horzAnchor"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblpXSpec"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblpX"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblpYSpec"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblpY")};
    private static final long serialVersionUID = 1;

    public CTTblPPrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public Object getLeftFromText() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public STTwipsMeasure xgetLeftFromText() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public boolean isSetLeftFromText() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void setLeftFromText(Object leftFromText) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setObjectValue(leftFromText);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void xsetLeftFromText(STTwipsMeasure leftFromText) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(leftFromText);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void unsetLeftFromText() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public Object getRightFromText() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public STTwipsMeasure xgetRightFromText() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public boolean isSetRightFromText() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void setRightFromText(Object rightFromText) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setObjectValue(rightFromText);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void xsetRightFromText(STTwipsMeasure rightFromText) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(rightFromText);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void unsetRightFromText() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public Object getTopFromText() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public STTwipsMeasure xgetTopFromText() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public boolean isSetTopFromText() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void setTopFromText(Object topFromText) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setObjectValue(topFromText);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void xsetTopFromText(STTwipsMeasure topFromText) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(topFromText);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void unsetTopFromText() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public Object getBottomFromText() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public STTwipsMeasure xgetBottomFromText() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public boolean isSetBottomFromText() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void setBottomFromText(Object bottomFromText) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setObjectValue(bottomFromText);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void xsetBottomFromText(STTwipsMeasure bottomFromText) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(bottomFromText);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void unsetBottomFromText() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public STVAnchor.Enum getVertAnchor() {
        STVAnchor.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            r1 = target == null ? null : (STVAnchor.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public STVAnchor xgetVertAnchor() {
        STVAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STVAnchor) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public boolean isSetVertAnchor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void setVertAnchor(STVAnchor.Enum vertAnchor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setEnumValue(vertAnchor);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void xsetVertAnchor(STVAnchor vertAnchor) {
        synchronized (monitor()) {
            check_orphaned();
            STVAnchor target = (STVAnchor) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STVAnchor) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(vertAnchor);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void unsetVertAnchor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public STHAnchor.Enum getHorzAnchor() {
        STHAnchor.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            r1 = target == null ? null : (STHAnchor.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public STHAnchor xgetHorzAnchor() {
        STHAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STHAnchor) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public boolean isSetHorzAnchor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void setHorzAnchor(STHAnchor.Enum horzAnchor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setEnumValue(horzAnchor);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void xsetHorzAnchor(STHAnchor horzAnchor) {
        synchronized (monitor()) {
            check_orphaned();
            STHAnchor target = (STHAnchor) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STHAnchor) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(horzAnchor);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void unsetHorzAnchor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public STXAlign.Enum getTblpXSpec() {
        STXAlign.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            r1 = target == null ? null : (STXAlign.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public STXAlign xgetTblpXSpec() {
        STXAlign target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXAlign) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public boolean isSetTblpXSpec() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void setTblpXSpec(STXAlign.Enum tblpXSpec) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setEnumValue(tblpXSpec);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void xsetTblpXSpec(STXAlign tblpXSpec) {
        synchronized (monitor()) {
            check_orphaned();
            STXAlign target = (STXAlign) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STXAlign) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(tblpXSpec);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void unsetTblpXSpec() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public Object getTblpX() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public STSignedTwipsMeasure xgetTblpX() {
        STSignedTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public boolean isSetTblpX() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void setTblpX(Object tblpX) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setObjectValue(tblpX);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void xsetTblpX(STSignedTwipsMeasure tblpX) {
        synchronized (monitor()) {
            check_orphaned();
            STSignedTwipsMeasure target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STSignedTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(tblpX);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void unsetTblpX() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public STYAlign.Enum getTblpYSpec() {
        STYAlign.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            r1 = target == null ? null : (STYAlign.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public STYAlign xgetTblpYSpec() {
        STYAlign target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STYAlign) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public boolean isSetTblpYSpec() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void setTblpYSpec(STYAlign.Enum tblpYSpec) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setEnumValue(tblpYSpec);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void xsetTblpYSpec(STYAlign tblpYSpec) {
        synchronized (monitor()) {
            check_orphaned();
            STYAlign target = (STYAlign) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STYAlign) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(tblpYSpec);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void unsetTblpYSpec() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public Object getTblpY() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public STSignedTwipsMeasure xgetTblpY() {
        STSignedTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public boolean isSetTblpY() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void setTblpY(Object tblpY) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setObjectValue(tblpY);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void xsetTblpY(STSignedTwipsMeasure tblpY) {
        synchronized (monitor()) {
            check_orphaned();
            STSignedTwipsMeasure target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (STSignedTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(tblpY);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
    public void unsetTblpY() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }
}
