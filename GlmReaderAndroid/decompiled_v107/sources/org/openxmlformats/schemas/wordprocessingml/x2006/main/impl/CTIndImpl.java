package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedTwipsMeasure;

/* loaded from: classes12.dex */
public class CTIndImpl extends XmlComplexContentImpl implements CTInd {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "start"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "startChars"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "end"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "endChars"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "left"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "leftChars"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "right"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rightChars"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hanging"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hangingChars"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "firstLine"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "firstLineChars")};
    private static final long serialVersionUID = 1;

    public CTIndImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public Object getStart() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public STSignedTwipsMeasure xgetStart() {
        STSignedTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public boolean isSetStart() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void setStart(Object start) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setObjectValue(start);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void xsetStart(STSignedTwipsMeasure start) {
        synchronized (monitor()) {
            check_orphaned();
            STSignedTwipsMeasure target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STSignedTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(start);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void unsetStart() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public BigInteger getStartChars() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public STDecimalNumber xgetStartChars() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public boolean isSetStartChars() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void setStartChars(BigInteger startChars) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setBigIntegerValue(startChars);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void xsetStartChars(STDecimalNumber startChars) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(startChars);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void unsetStartChars() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public Object getEnd() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public STSignedTwipsMeasure xgetEnd() {
        STSignedTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public boolean isSetEnd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void setEnd(Object end) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setObjectValue(end);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void xsetEnd(STSignedTwipsMeasure end) {
        synchronized (monitor()) {
            check_orphaned();
            STSignedTwipsMeasure target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STSignedTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(end);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void unsetEnd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public BigInteger getEndChars() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public STDecimalNumber xgetEndChars() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public boolean isSetEndChars() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void setEndChars(BigInteger endChars) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setBigIntegerValue(endChars);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void xsetEndChars(STDecimalNumber endChars) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(endChars);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void unsetEndChars() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public Object getLeft() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public STSignedTwipsMeasure xgetLeft() {
        STSignedTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public boolean isSetLeft() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void setLeft(Object left) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setObjectValue(left);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void xsetLeft(STSignedTwipsMeasure left) {
        synchronized (monitor()) {
            check_orphaned();
            STSignedTwipsMeasure target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STSignedTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(left);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void unsetLeft() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public BigInteger getLeftChars() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public STDecimalNumber xgetLeftChars() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public boolean isSetLeftChars() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void setLeftChars(BigInteger leftChars) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setBigIntegerValue(leftChars);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void xsetLeftChars(STDecimalNumber leftChars) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(leftChars);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void unsetLeftChars() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public Object getRight() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public STSignedTwipsMeasure xgetRight() {
        STSignedTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public boolean isSetRight() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void setRight(Object right) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setObjectValue(right);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void xsetRight(STSignedTwipsMeasure right) {
        synchronized (monitor()) {
            check_orphaned();
            STSignedTwipsMeasure target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STSignedTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(right);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void unsetRight() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public BigInteger getRightChars() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public STDecimalNumber xgetRightChars() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public boolean isSetRightChars() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void setRightChars(BigInteger rightChars) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setBigIntegerValue(rightChars);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void xsetRightChars(STDecimalNumber rightChars) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(rightChars);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void unsetRightChars() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public Object getHanging() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public STTwipsMeasure xgetHanging() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public boolean isSetHanging() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void setHanging(Object hanging) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setObjectValue(hanging);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void xsetHanging(STTwipsMeasure hanging) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(hanging);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void unsetHanging() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public BigInteger getHangingChars() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public STDecimalNumber xgetHangingChars() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public boolean isSetHangingChars() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void setHangingChars(BigInteger hangingChars) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setBigIntegerValue(hangingChars);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void xsetHangingChars(STDecimalNumber hangingChars) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(hangingChars);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void unsetHangingChars() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public Object getFirstLine() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public STTwipsMeasure xgetFirstLine() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public boolean isSetFirstLine() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void setFirstLine(Object firstLine) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setObjectValue(firstLine);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void xsetFirstLine(STTwipsMeasure firstLine) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(firstLine);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void unsetFirstLine() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public BigInteger getFirstLineChars() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public STDecimalNumber xgetFirstLineChars() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public boolean isSetFirstLineChars() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void setFirstLineChars(BigInteger firstLineChars) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setBigIntegerValue(firstLineChars);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void xsetFirstLineChars(STDecimalNumber firstLineChars) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(firstLineChars);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd
    public void unsetFirstLineChars() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }
}
