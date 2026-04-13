package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedTwipsMeasure;

/* loaded from: classes12.dex */
public class CTSpacingImpl extends XmlComplexContentImpl implements CTSpacing {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "before"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "beforeLines"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "beforeAutospacing"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "after"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "afterLines"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "afterAutospacing"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "line"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "lineRule")};
    private static final long serialVersionUID = 1;

    public CTSpacingImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public Object getBefore() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public STTwipsMeasure xgetBefore() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STTwipsMeasure) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public boolean isSetBefore() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void setBefore(Object before) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setObjectValue(before);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void xsetBefore(STTwipsMeasure before) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(before);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void unsetBefore() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public BigInteger getBeforeLines() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public STDecimalNumber xgetBeforeLines() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STDecimalNumber) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public boolean isSetBeforeLines() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void setBeforeLines(BigInteger beforeLines) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setBigIntegerValue(beforeLines);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void xsetBeforeLines(STDecimalNumber beforeLines) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(beforeLines);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void unsetBeforeLines() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public Object getBeforeAutospacing() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public STOnOff xgetBeforeAutospacing() {
        STOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STOnOff) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public boolean isSetBeforeAutospacing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void setBeforeAutospacing(Object beforeAutospacing) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setObjectValue(beforeAutospacing);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void xsetBeforeAutospacing(STOnOff beforeAutospacing) {
        synchronized (monitor()) {
            check_orphaned();
            STOnOff target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STOnOff) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(beforeAutospacing);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void unsetBeforeAutospacing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public Object getAfter() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public STTwipsMeasure xgetAfter() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STTwipsMeasure) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public boolean isSetAfter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void setAfter(Object after) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setObjectValue(after);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void xsetAfter(STTwipsMeasure after) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(after);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void unsetAfter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public BigInteger getAfterLines() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public STDecimalNumber xgetAfterLines() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STDecimalNumber) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public boolean isSetAfterLines() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void setAfterLines(BigInteger afterLines) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setBigIntegerValue(afterLines);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void xsetAfterLines(STDecimalNumber afterLines) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(afterLines);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void unsetAfterLines() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public Object getAfterAutospacing() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[5]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public STOnOff xgetAfterAutospacing() {
        STOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STOnOff) get_default_attribute_value(PROPERTY_QNAME[5]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public boolean isSetAfterAutospacing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void setAfterAutospacing(Object afterAutospacing) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setObjectValue(afterAutospacing);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void xsetAfterAutospacing(STOnOff afterAutospacing) {
        synchronized (monitor()) {
            check_orphaned();
            STOnOff target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STOnOff) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(afterAutospacing);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void unsetAfterAutospacing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public Object getLine() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[6]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public STSignedTwipsMeasure xgetLine() {
        STSignedTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STSignedTwipsMeasure) get_default_attribute_value(PROPERTY_QNAME[6]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public boolean isSetLine() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void setLine(Object line) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setObjectValue(line);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void xsetLine(STSignedTwipsMeasure line) {
        synchronized (monitor()) {
            check_orphaned();
            STSignedTwipsMeasure target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STSignedTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(line);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void unsetLine() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public STLineSpacingRule.Enum getLineRule() {
        STLineSpacingRule.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[7]);
            }
            r1 = target == null ? null : (STLineSpacingRule.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public STLineSpacingRule xgetLineRule() {
        STLineSpacingRule target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLineSpacingRule) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STLineSpacingRule) get_default_attribute_value(PROPERTY_QNAME[7]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public boolean isSetLineRule() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void setLineRule(STLineSpacingRule.Enum lineRule) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setEnumValue(lineRule);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void xsetLineRule(STLineSpacingRule lineRule) {
        synchronized (monitor()) {
            check_orphaned();
            STLineSpacingRule target = (STLineSpacingRule) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STLineSpacingRule) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(lineRule);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing
    public void unsetLineRule() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }
}
