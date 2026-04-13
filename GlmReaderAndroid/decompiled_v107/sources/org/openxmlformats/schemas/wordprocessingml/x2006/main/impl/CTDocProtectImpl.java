package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STAlgClass;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STAlgType;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STCryptProv;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber;

/* loaded from: classes12.dex */
public class CTDocProtectImpl extends XmlComplexContentImpl implements CTDocProtect {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "edit"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "formatting"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "enforcement"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "algorithmName"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hashValue"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "saltValue"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "spinCount"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "cryptProviderType"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "cryptAlgorithmClass"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "cryptAlgorithmType"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "cryptAlgorithmSid"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "cryptSpinCount"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "cryptProvider"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "algIdExt"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "algIdExtSource"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "cryptProviderTypeExt"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "cryptProviderTypeExtSource"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hash"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "salt")};
    private static final long serialVersionUID = 1;

    public CTDocProtectImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STDocProtect.Enum getEdit() {
        STDocProtect.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STDocProtect.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STDocProtect xgetEdit() {
        STDocProtect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDocProtect) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetEdit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setEdit(STDocProtect.Enum edit) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(edit);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetEdit(STDocProtect edit) {
        synchronized (monitor()) {
            check_orphaned();
            STDocProtect target = (STDocProtect) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STDocProtect) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(edit);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetEdit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public Object getFormatting() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STOnOff xgetFormatting() {
        STOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetFormatting() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setFormatting(Object formatting) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setObjectValue(formatting);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetFormatting(STOnOff formatting) {
        synchronized (monitor()) {
            check_orphaned();
            STOnOff target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STOnOff) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(formatting);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetFormatting() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public Object getEnforcement() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STOnOff xgetEnforcement() {
        STOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetEnforcement() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setEnforcement(Object enforcement) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setObjectValue(enforcement);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetEnforcement(STOnOff enforcement) {
        synchronized (monitor()) {
            check_orphaned();
            STOnOff target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STOnOff) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(enforcement);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetEnforcement() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public String getAlgorithmName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STString xgetAlgorithmName() {
        STString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetAlgorithmName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setAlgorithmName(String algorithmName) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(algorithmName);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetAlgorithmName(STString algorithmName) {
        synchronized (monitor()) {
            check_orphaned();
            STString target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STString) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(algorithmName);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetAlgorithmName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public byte[] getHashValue() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public XmlBase64Binary xgetHashValue() {
        XmlBase64Binary target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBase64Binary) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetHashValue() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setHashValue(byte[] hashValue) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setByteArrayValue(hashValue);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetHashValue(XmlBase64Binary hashValue) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBase64Binary target = (XmlBase64Binary) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlBase64Binary) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(hashValue);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetHashValue() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public byte[] getSaltValue() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public XmlBase64Binary xgetSaltValue() {
        XmlBase64Binary target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBase64Binary) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetSaltValue() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setSaltValue(byte[] saltValue) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setByteArrayValue(saltValue);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetSaltValue(XmlBase64Binary saltValue) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBase64Binary target = (XmlBase64Binary) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlBase64Binary) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(saltValue);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetSaltValue() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public BigInteger getSpinCount() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STDecimalNumber xgetSpinCount() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetSpinCount() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setSpinCount(BigInteger spinCount) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setBigIntegerValue(spinCount);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetSpinCount(STDecimalNumber spinCount) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(spinCount);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetSpinCount() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STCryptProv.Enum getCryptProviderType() {
        STCryptProv.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            r1 = target == null ? null : (STCryptProv.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STCryptProv xgetCryptProviderType() {
        STCryptProv target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCryptProv) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetCryptProviderType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setCryptProviderType(STCryptProv.Enum cryptProviderType) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setEnumValue(cryptProviderType);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetCryptProviderType(STCryptProv cryptProviderType) {
        synchronized (monitor()) {
            check_orphaned();
            STCryptProv target = (STCryptProv) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STCryptProv) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(cryptProviderType);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetCryptProviderType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STAlgClass.Enum getCryptAlgorithmClass() {
        STAlgClass.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            r1 = target == null ? null : (STAlgClass.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STAlgClass xgetCryptAlgorithmClass() {
        STAlgClass target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAlgClass) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetCryptAlgorithmClass() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setCryptAlgorithmClass(STAlgClass.Enum cryptAlgorithmClass) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setEnumValue(cryptAlgorithmClass);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetCryptAlgorithmClass(STAlgClass cryptAlgorithmClass) {
        synchronized (monitor()) {
            check_orphaned();
            STAlgClass target = (STAlgClass) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STAlgClass) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(cryptAlgorithmClass);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetCryptAlgorithmClass() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STAlgType.Enum getCryptAlgorithmType() {
        STAlgType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            r1 = target == null ? null : (STAlgType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STAlgType xgetCryptAlgorithmType() {
        STAlgType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAlgType) get_store().find_attribute_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetCryptAlgorithmType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setCryptAlgorithmType(STAlgType.Enum cryptAlgorithmType) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setEnumValue(cryptAlgorithmType);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetCryptAlgorithmType(STAlgType cryptAlgorithmType) {
        synchronized (monitor()) {
            check_orphaned();
            STAlgType target = (STAlgType) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (STAlgType) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(cryptAlgorithmType);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetCryptAlgorithmType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public BigInteger getCryptAlgorithmSid() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STDecimalNumber xgetCryptAlgorithmSid() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetCryptAlgorithmSid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setCryptAlgorithmSid(BigInteger cryptAlgorithmSid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setBigIntegerValue(cryptAlgorithmSid);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetCryptAlgorithmSid(STDecimalNumber cryptAlgorithmSid) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(cryptAlgorithmSid);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetCryptAlgorithmSid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public BigInteger getCryptSpinCount() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STDecimalNumber xgetCryptSpinCount() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetCryptSpinCount() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setCryptSpinCount(BigInteger cryptSpinCount) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setBigIntegerValue(cryptSpinCount);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetCryptSpinCount(STDecimalNumber cryptSpinCount) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(cryptSpinCount);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetCryptSpinCount() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public String getCryptProvider() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STString xgetCryptProvider() {
        STString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetCryptProvider() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setCryptProvider(String cryptProvider) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setStringValue(cryptProvider);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetCryptProvider(STString cryptProvider) {
        synchronized (monitor()) {
            check_orphaned();
            STString target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (STString) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(cryptProvider);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetCryptProvider() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public byte[] getAlgIdExt() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STLongHexNumber xgetAlgIdExt() {
        STLongHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetAlgIdExt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setAlgIdExt(byte[] algIdExt) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setByteArrayValue(algIdExt);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetAlgIdExt(STLongHexNumber algIdExt) {
        synchronized (monitor()) {
            check_orphaned();
            STLongHexNumber target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (STLongHexNumber) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(algIdExt);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetAlgIdExt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public String getAlgIdExtSource() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STString xgetAlgIdExtSource() {
        STString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetAlgIdExtSource() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setAlgIdExtSource(String algIdExtSource) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.setStringValue(algIdExtSource);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetAlgIdExtSource(STString algIdExtSource) {
        synchronized (monitor()) {
            check_orphaned();
            STString target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (STString) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.set(algIdExtSource);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetAlgIdExtSource() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[14]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public byte[] getCryptProviderTypeExt() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STLongHexNumber xgetCryptProviderTypeExt() {
        STLongHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetCryptProviderTypeExt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setCryptProviderTypeExt(byte[] cryptProviderTypeExt) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.setByteArrayValue(cryptProviderTypeExt);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetCryptProviderTypeExt(STLongHexNumber cryptProviderTypeExt) {
        synchronized (monitor()) {
            check_orphaned();
            STLongHexNumber target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (STLongHexNumber) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.set(cryptProviderTypeExt);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetCryptProviderTypeExt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[15]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public String getCryptProviderTypeExtSource() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public STString xgetCryptProviderTypeExtSource() {
        STString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetCryptProviderTypeExtSource() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[16]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setCryptProviderTypeExtSource(String cryptProviderTypeExtSource) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.setStringValue(cryptProviderTypeExtSource);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetCryptProviderTypeExtSource(STString cryptProviderTypeExtSource) {
        synchronized (monitor()) {
            check_orphaned();
            STString target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (STString) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.set(cryptProviderTypeExtSource);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetCryptProviderTypeExtSource() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[16]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public byte[] getHash() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public XmlBase64Binary xgetHash() {
        XmlBase64Binary target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBase64Binary) get_store().find_attribute_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetHash() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[17]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setHash(byte[] hash) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.setByteArrayValue(hash);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetHash(XmlBase64Binary hash) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBase64Binary target = (XmlBase64Binary) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (XmlBase64Binary) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.set(hash);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetHash() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[17]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public byte[] getSalt() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public XmlBase64Binary xgetSalt() {
        XmlBase64Binary target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBase64Binary) get_store().find_attribute_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public boolean isSetSalt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[18]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void setSalt(byte[] salt) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.setByteArrayValue(salt);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void xsetSalt(XmlBase64Binary salt) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBase64Binary target = (XmlBase64Binary) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (XmlBase64Binary) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.set(salt);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect
    public void unsetSalt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[18]);
        }
    }
}
