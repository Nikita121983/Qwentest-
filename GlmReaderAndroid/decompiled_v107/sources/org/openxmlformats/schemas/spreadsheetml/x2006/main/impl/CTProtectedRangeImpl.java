package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda66;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.JavaListObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSqref;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnsignedShortHex;

/* loaded from: classes12.dex */
public class CTProtectedRangeImpl extends XmlComplexContentImpl implements CTProtectedRange {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "securityDescriptor"), new QName("", "password"), new QName("", "sqref"), new QName("", "name"), new QName("", "securityDescriptor"), new QName("", "algorithmName"), new QName("", "hashValue"), new QName("", "saltValue"), new QName("", "spinCount")};
    private static final long serialVersionUID = 1;

    public CTProtectedRangeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public List<String> getSecurityDescriptorList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTProtectedRangeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTProtectedRangeImpl.this.getSecurityDescriptorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTProtectedRangeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTProtectedRangeImpl.this.setSecurityDescriptorArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTProtectedRangeImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTProtectedRangeImpl.this.insertSecurityDescriptor(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTProtectedRangeImpl$$ExternalSyntheticLambda6(this), new CTProtectedRangeImpl$$ExternalSyntheticLambda7(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getSecurityDescriptorArray$0(int x$0) {
        return new String[x$0];
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public String[] getSecurityDescriptorArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[0], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTProtectedRangeImpl$$ExternalSyntheticLambda1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTProtectedRangeImpl.lambda$getSecurityDescriptorArray$0(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public String getSecurityDescriptorArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public List<XmlString> xgetSecurityDescriptorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTProtectedRangeImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTProtectedRangeImpl.this.xgetSecurityDescriptorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTProtectedRangeImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTProtectedRangeImpl.this.xsetSecurityDescriptorArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTProtectedRangeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTProtectedRangeImpl.this.insertNewSecurityDescriptor(((Integer) obj).intValue());
                }
            }, new CTProtectedRangeImpl$$ExternalSyntheticLambda6(this), new CTProtectedRangeImpl$$ExternalSyntheticLambda7(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetSecurityDescriptorArray$1(int x$0) {
        return new XmlString[x$0];
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public XmlString[] xgetSecurityDescriptorArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[0], new IntFunction() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTProtectedRangeImpl$$ExternalSyntheticLambda2
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTProtectedRangeImpl.lambda$xgetSecurityDescriptorArray$1(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public XmlString xgetSecurityDescriptorArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public int sizeOfSecurityDescriptorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void setSecurityDescriptorArray(String[] securityDescriptorArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(securityDescriptorArray, PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void setSecurityDescriptorArray(int i, String securityDescriptor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(securityDescriptor);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void xsetSecurityDescriptorArray(XmlString[] securityDescriptorArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(securityDescriptorArray, PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void xsetSecurityDescriptorArray(int i, XmlString securityDescriptor) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(securityDescriptor);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void insertSecurityDescriptor(int i, String securityDescriptor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            target.setStringValue(securityDescriptor);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void addSecurityDescriptor(String securityDescriptor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            target.setStringValue(securityDescriptor);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public XmlString insertNewSecurityDescriptor(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public XmlString addNewSecurityDescriptor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void removeSecurityDescriptor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public byte[] getPassword() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public STUnsignedShortHex xgetPassword() {
        STUnsignedShortHex target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STUnsignedShortHex) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public boolean isSetPassword() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void setPassword(byte[] password) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setByteArrayValue(password);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void xsetPassword(STUnsignedShortHex password) {
        synchronized (monitor()) {
            check_orphaned();
            STUnsignedShortHex target = (STUnsignedShortHex) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STUnsignedShortHex) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(password);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void unsetPassword() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public List getSqref() {
        List<?> listValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            listValue = target == null ? null : target.getListValue();
        }
        return listValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public STSqref xgetSqref() {
        STSqref target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSqref) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void setSqref(List sqref) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setListValue(sqref);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void xsetSqref(STSqref sqref) {
        synchronized (monitor()) {
            check_orphaned();
            STSqref target = (STSqref) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STSqref) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(sqref);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public STXstring xgetName() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void xsetName(STXstring name) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(name);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public String getSecurityDescriptor2() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public XmlString xgetSecurityDescriptor2() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public boolean isSetSecurityDescriptor2() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void setSecurityDescriptor2(String securityDescriptor2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(securityDescriptor2);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void xsetSecurityDescriptor2(XmlString securityDescriptor2) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(securityDescriptor2);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void unsetSecurityDescriptor2() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public String getAlgorithmName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public STXstring xgetAlgorithmName() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public boolean isSetAlgorithmName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void setAlgorithmName(String algorithmName) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setStringValue(algorithmName);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void xsetAlgorithmName(STXstring algorithmName) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(algorithmName);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void unsetAlgorithmName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public byte[] getHashValue() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public XmlBase64Binary xgetHashValue() {
        XmlBase64Binary target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBase64Binary) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public boolean isSetHashValue() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void setHashValue(byte[] hashValue) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setByteArrayValue(hashValue);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void xsetHashValue(XmlBase64Binary hashValue) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBase64Binary target = (XmlBase64Binary) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (XmlBase64Binary) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(hashValue);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void unsetHashValue() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public byte[] getSaltValue() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public XmlBase64Binary xgetSaltValue() {
        XmlBase64Binary target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBase64Binary) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public boolean isSetSaltValue() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void setSaltValue(byte[] saltValue) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setByteArrayValue(saltValue);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void xsetSaltValue(XmlBase64Binary saltValue) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBase64Binary target = (XmlBase64Binary) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlBase64Binary) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(saltValue);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void unsetSaltValue() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public long getSpinCount() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public XmlUnsignedInt xgetSpinCount() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public boolean isSetSpinCount() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void setSpinCount(long spinCount) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setLongValue(spinCount);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void xsetSpinCount(XmlUnsignedInt spinCount) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(spinCount);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange
    public void unsetSpinCount() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }
}
