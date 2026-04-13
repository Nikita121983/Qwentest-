package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXAlign;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STYAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDropCap;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDropCap$Enum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHAnchor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHeightRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVAnchor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STWrap;

/* loaded from: classes12.dex */
public class CTFramePrImpl extends XmlComplexContentImpl implements CTFramePr {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "dropCap"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "lines"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "w"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "h"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "vSpace"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hSpace"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "wrap"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hAnchor"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "vAnchor"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "x"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "xAlign"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "y"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "yAlign"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hRule"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "anchorLock")};
    private static final long serialVersionUID = 1;

    public CTFramePrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STDropCap$Enum getDropCap() {
        STDropCap$Enum sTDropCap$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            sTDropCap$Enum = target == null ? null : (STDropCap$Enum) target.getEnumValue();
        }
        return sTDropCap$Enum;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STDropCap xgetDropCap() {
        STDropCap target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public boolean isSetDropCap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void setDropCap(STDropCap$Enum dropCap) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(dropCap);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void xsetDropCap(STDropCap dropCap) {
        synchronized (monitor()) {
            check_orphaned();
            STDropCap target = get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STDropCap) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(dropCap);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void unsetDropCap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public BigInteger getLines() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STDecimalNumber xgetLines() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public boolean isSetLines() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void setLines(BigInteger lines) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setBigIntegerValue(lines);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void xsetLines(STDecimalNumber lines) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(lines);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void unsetLines() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public Object getW() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STTwipsMeasure xgetW() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public boolean isSetW() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void setW(Object w) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setObjectValue(w);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void xsetW(STTwipsMeasure w) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(w);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void unsetW() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public Object getH() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STTwipsMeasure xgetH() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public boolean isSetH() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void setH(Object h) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setObjectValue(h);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void xsetH(STTwipsMeasure h) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(h);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void unsetH() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public Object getVSpace() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STTwipsMeasure xgetVSpace() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public boolean isSetVSpace() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void setVSpace(Object vSpace) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setObjectValue(vSpace);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void xsetVSpace(STTwipsMeasure vSpace) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(vSpace);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void unsetVSpace() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public Object getHSpace() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STTwipsMeasure xgetHSpace() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public boolean isSetHSpace() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void setHSpace(Object hSpace) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setObjectValue(hSpace);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void xsetHSpace(STTwipsMeasure hSpace) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(hSpace);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void unsetHSpace() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STWrap.Enum getWrap() {
        STWrap.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            r1 = target == null ? null : (STWrap.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STWrap xgetWrap() {
        STWrap target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STWrap) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public boolean isSetWrap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void setWrap(STWrap.Enum wrap) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setEnumValue(wrap);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void xsetWrap(STWrap wrap) {
        synchronized (monitor()) {
            check_orphaned();
            STWrap target = (STWrap) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STWrap) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(wrap);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void unsetWrap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STHAnchor.Enum getHAnchor() {
        STHAnchor.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            r1 = target == null ? null : (STHAnchor.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STHAnchor xgetHAnchor() {
        STHAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STHAnchor) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public boolean isSetHAnchor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void setHAnchor(STHAnchor.Enum hAnchor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setEnumValue(hAnchor);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void xsetHAnchor(STHAnchor hAnchor) {
        synchronized (monitor()) {
            check_orphaned();
            STHAnchor target = (STHAnchor) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STHAnchor) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(hAnchor);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void unsetHAnchor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STVAnchor.Enum getVAnchor() {
        STVAnchor.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            r1 = target == null ? null : (STVAnchor.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STVAnchor xgetVAnchor() {
        STVAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STVAnchor) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public boolean isSetVAnchor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void setVAnchor(STVAnchor.Enum vAnchor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setEnumValue(vAnchor);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void xsetVAnchor(STVAnchor vAnchor) {
        synchronized (monitor()) {
            check_orphaned();
            STVAnchor target = (STVAnchor) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STVAnchor) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(vAnchor);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void unsetVAnchor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public Object getX() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STSignedTwipsMeasure xgetX() {
        STSignedTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public boolean isSetX() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void setX(Object x) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setObjectValue(x);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void xsetX(STSignedTwipsMeasure x) {
        synchronized (monitor()) {
            check_orphaned();
            STSignedTwipsMeasure target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (STSignedTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(x);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void unsetX() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STXAlign.Enum getXAlign() {
        STXAlign.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            r1 = target == null ? null : (STXAlign.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STXAlign xgetXAlign() {
        STXAlign target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXAlign) get_store().find_attribute_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public boolean isSetXAlign() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void setXAlign(STXAlign.Enum xAlign) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setEnumValue(xAlign);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void xsetXAlign(STXAlign xAlign) {
        synchronized (monitor()) {
            check_orphaned();
            STXAlign target = (STXAlign) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (STXAlign) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(xAlign);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void unsetXAlign() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public Object getY() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STSignedTwipsMeasure xgetY() {
        STSignedTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public boolean isSetY() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void setY(Object y) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setObjectValue(y);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void xsetY(STSignedTwipsMeasure y) {
        synchronized (monitor()) {
            check_orphaned();
            STSignedTwipsMeasure target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (STSignedTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(y);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void unsetY() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STYAlign.Enum getYAlign() {
        STYAlign.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            r1 = target == null ? null : (STYAlign.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STYAlign xgetYAlign() {
        STYAlign target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STYAlign) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public boolean isSetYAlign() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void setYAlign(STYAlign.Enum yAlign) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setEnumValue(yAlign);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void xsetYAlign(STYAlign yAlign) {
        synchronized (monitor()) {
            check_orphaned();
            STYAlign target = (STYAlign) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (STYAlign) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(yAlign);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void unsetYAlign() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STHeightRule.Enum getHRule() {
        STHeightRule.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            r1 = target == null ? null : (STHeightRule.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STHeightRule xgetHRule() {
        STHeightRule target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STHeightRule) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public boolean isSetHRule() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void setHRule(STHeightRule.Enum hRule) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setEnumValue(hRule);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void xsetHRule(STHeightRule hRule) {
        synchronized (monitor()) {
            check_orphaned();
            STHeightRule target = (STHeightRule) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (STHeightRule) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(hRule);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void unsetHRule() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public Object getAnchorLock() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public STOnOff xgetAnchorLock() {
        STOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public boolean isSetAnchorLock() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void setAnchorLock(Object anchorLock) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.setObjectValue(anchorLock);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void xsetAnchorLock(STOnOff anchorLock) {
        synchronized (monitor()) {
            check_orphaned();
            STOnOff target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (STOnOff) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.set(anchorLock);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
    public void unsetAnchorLock() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[14]);
        }
    }
}
