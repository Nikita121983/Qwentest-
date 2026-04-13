package org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import javax.xml.namespace.QName;
import org.apache.commons.math3.linear.ConjugateGradient;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlByte;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlDecimal;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlFloat;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlLong;
import org.apache.xmlbeans.XmlShort;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.XmlUnsignedLong;
import org.apache.xmlbeans.XmlUnsignedShort;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTArray;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTEmpty;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTNull;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVstream;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STCy;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STError;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid;

/* loaded from: classes11.dex */
public class CTPropertyImpl extends XmlComplexContentImpl implements CTProperty {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", ConjugateGradient.VECTOR), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "array"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "blob"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "oblob"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "empty"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "null"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "i1"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "i2"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "i4"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "i8"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", XmlErrorCodes.INT), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ui1"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ui2"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ui4"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ui8"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "uint"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "r4"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "r8"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", XmlErrorCodes.DECIMAL), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "lpstr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "lpwstr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "bstr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", XmlErrorCodes.DATE), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "filetime"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "bool"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "cy"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "error"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "stream"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ostream"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "storage"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ostorage"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "vstream"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "clsid"), new QName("", "fmtid"), new QName("", "pid"), new QName("", "name"), new QName("", "linkTarget")};
    private static final long serialVersionUID = 1;

    public CTPropertyImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public CTVector getVector() {
        CTVector cTVector;
        synchronized (monitor()) {
            check_orphaned();
            CTVector target = (CTVector) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTVector = target == null ? null : target;
        }
        return cTVector;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetVector() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setVector(CTVector vector) {
        generatedSetterHelperImpl(vector, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public CTVector addNewVector() {
        CTVector target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTVector) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetVector() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public CTArray getArray() {
        CTArray cTArray;
        synchronized (monitor()) {
            check_orphaned();
            CTArray target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTArray = target == null ? null : target;
        }
        return cTArray;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetArray() {
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

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setArray(CTArray array) {
        generatedSetterHelperImpl(array, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public CTArray addNewArray() {
        CTArray target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetArray() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public byte[] getBlob() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlBase64Binary xgetBlob() {
        XmlBase64Binary target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBase64Binary) get_store().find_element_user(PROPERTY_QNAME[2], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetBlob() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setBlob(byte[] blob) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.setByteArrayValue(blob);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetBlob(XmlBase64Binary blob) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBase64Binary target = (XmlBase64Binary) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (XmlBase64Binary) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.set(blob);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetBlob() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public byte[] getOblob() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlBase64Binary xgetOblob() {
        XmlBase64Binary target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBase64Binary) get_store().find_element_user(PROPERTY_QNAME[3], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetOblob() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setOblob(byte[] oblob) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            target.setByteArrayValue(oblob);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetOblob(XmlBase64Binary oblob) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBase64Binary target = (XmlBase64Binary) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (target == null) {
                target = (XmlBase64Binary) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            target.set(oblob);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetOblob() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public CTEmpty getEmpty() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            CTEmpty target = get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTEmpty = target == null ? null : target;
        }
        return cTEmpty;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetEmpty() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setEmpty(CTEmpty empty) {
        generatedSetterHelperImpl(empty, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public CTEmpty addNewEmpty() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetEmpty() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public CTNull getNull() {
        CTNull cTNull;
        synchronized (monitor()) {
            check_orphaned();
            CTNull target = get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTNull = target == null ? null : target;
        }
        return cTNull;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetNull() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setNull(CTNull xnull) {
        generatedSetterHelperImpl(xnull, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public CTNull addNewNull() {
        CTNull target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetNull() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public byte getI1() {
        byte b;
        synchronized (monitor()) {
            check_orphaned();
            b = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (target != null) {
                b = target.getByteValue();
            }
        }
        return b;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlByte xgetI1() {
        XmlByte target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlByte) get_store().find_element_user(PROPERTY_QNAME[6], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetI1() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setI1(byte i1) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[6]);
            }
            target.setByteValue(i1);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetI1(XmlByte i1) {
        synchronized (monitor()) {
            check_orphaned();
            XmlByte target = (XmlByte) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (target == null) {
                target = (XmlByte) get_store().add_element_user(PROPERTY_QNAME[6]);
            }
            target.set(i1);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetI1() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public short getI2() {
        short s;
        synchronized (monitor()) {
            check_orphaned();
            s = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (target != null) {
                s = target.getShortValue();
            }
        }
        return s;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlShort xgetI2() {
        XmlShort target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlShort) get_store().find_element_user(PROPERTY_QNAME[7], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetI2() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setI2(short i2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[7]);
            }
            target.setShortValue(i2);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetI2(XmlShort i2) {
        synchronized (monitor()) {
            check_orphaned();
            XmlShort target = (XmlShort) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (target == null) {
                target = (XmlShort) get_store().add_element_user(PROPERTY_QNAME[7]);
            }
            target.set(i2);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetI2() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public int getI4() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlInt xgetI4() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[8], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetI4() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setI4(int i4) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[8]);
            }
            target.setIntValue(i4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetI4(XmlInt i4) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (target == null) {
                target = (XmlInt) get_store().add_element_user(PROPERTY_QNAME[8]);
            }
            target.set(i4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetI4() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public long getI8() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlLong xgetI8() {
        XmlLong target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlLong) get_store().find_element_user(PROPERTY_QNAME[9], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetI8() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setI8(long i8) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[9]);
            }
            target.setLongValue(i8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetI8(XmlLong i8) {
        synchronized (monitor()) {
            check_orphaned();
            XmlLong target = (XmlLong) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (target == null) {
                target = (XmlLong) get_store().add_element_user(PROPERTY_QNAME[9]);
            }
            target.set(i8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetI8() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public int getInt() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlInt xgetInt() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[10], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetInt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setInt(int xint) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[10]);
            }
            target.setIntValue(xint);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetInt(XmlInt xint) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (target == null) {
                target = (XmlInt) get_store().add_element_user(PROPERTY_QNAME[10]);
            }
            target.set(xint);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetInt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public short getUi1() {
        short s;
        synchronized (monitor()) {
            check_orphaned();
            s = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (target != null) {
                s = target.getShortValue();
            }
        }
        return s;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlUnsignedByte xgetUi1() {
        XmlUnsignedByte target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedByte) get_store().find_element_user(PROPERTY_QNAME[11], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetUi1() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setUi1(short ui1) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[11]);
            }
            target.setShortValue(ui1);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetUi1(XmlUnsignedByte ui1) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedByte target = (XmlUnsignedByte) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (target == null) {
                target = (XmlUnsignedByte) get_store().add_element_user(PROPERTY_QNAME[11]);
            }
            target.set(ui1);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetUi1() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public int getUi2() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlUnsignedShort xgetUi2() {
        XmlUnsignedShort target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedShort) get_store().find_element_user(PROPERTY_QNAME[12], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetUi2() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setUi2(int ui2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[12]);
            }
            target.setIntValue(ui2);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetUi2(XmlUnsignedShort ui2) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedShort target = (XmlUnsignedShort) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (target == null) {
                target = (XmlUnsignedShort) get_store().add_element_user(PROPERTY_QNAME[12]);
            }
            target.set(ui2);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetUi2() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public long getUi4() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlUnsignedInt xgetUi4() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[13], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetUi4() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setUi4(long ui4) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[13]);
            }
            target.setLongValue(ui4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetUi4(XmlUnsignedInt ui4) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[13]);
            }
            target.set(ui4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetUi4() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public BigInteger getUi8() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlUnsignedLong xgetUi8() {
        XmlUnsignedLong target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedLong) get_store().find_element_user(PROPERTY_QNAME[14], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetUi8() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setUi8(BigInteger ui8) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[14]);
            }
            target.setBigIntegerValue(ui8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetUi8(XmlUnsignedLong ui8) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedLong target = (XmlUnsignedLong) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            if (target == null) {
                target = (XmlUnsignedLong) get_store().add_element_user(PROPERTY_QNAME[14]);
            }
            target.set(ui8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetUi8() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public long getUint() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlUnsignedInt xgetUint() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[15], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetUint() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setUint(long uint) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[15]);
            }
            target.setLongValue(uint);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetUint(XmlUnsignedInt uint) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[15]);
            }
            target.set(uint);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetUint() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public float getR4() {
        float floatValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            floatValue = target == null ? 0.0f : target.getFloatValue();
        }
        return floatValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlFloat xgetR4() {
        XmlFloat target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlFloat) get_store().find_element_user(PROPERTY_QNAME[16], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetR4() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setR4(float r4) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[16]);
            }
            target.setFloatValue(r4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetR4(XmlFloat r4) {
        synchronized (monitor()) {
            check_orphaned();
            XmlFloat target = (XmlFloat) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            if (target == null) {
                target = (XmlFloat) get_store().add_element_user(PROPERTY_QNAME[16]);
            }
            target.set(r4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetR4() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public double getR8() {
        double doubleValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            doubleValue = target == null ? 0.0d : target.getDoubleValue();
        }
        return doubleValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlDouble xgetR8() {
        XmlDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDouble) get_store().find_element_user(PROPERTY_QNAME[17], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetR8() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setR8(double r8) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[17]);
            }
            target.setDoubleValue(r8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetR8(XmlDouble r8) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDouble target = (XmlDouble) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            if (target == null) {
                target = (XmlDouble) get_store().add_element_user(PROPERTY_QNAME[17]);
            }
            target.set(r8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetR8() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public BigDecimal getDecimal() {
        BigDecimal bigDecimalValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            bigDecimalValue = target == null ? null : target.getBigDecimalValue();
        }
        return bigDecimalValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlDecimal xgetDecimal() {
        XmlDecimal target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDecimal) get_store().find_element_user(PROPERTY_QNAME[18], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetDecimal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setDecimal(BigDecimal decimal) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[18]);
            }
            target.setBigDecimalValue(decimal);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetDecimal(XmlDecimal decimal) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDecimal target = (XmlDecimal) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            if (target == null) {
                target = (XmlDecimal) get_store().add_element_user(PROPERTY_QNAME[18]);
            }
            target.set(decimal);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetDecimal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public String getLpstr() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlString xgetLpstr() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[19], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetLpstr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[19]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setLpstr(String lpstr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[19]);
            }
            target.setStringValue(lpstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetLpstr(XmlString lpstr) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[19]);
            }
            target.set(lpstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetLpstr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public String getLpwstr() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[20], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlString xgetLpwstr() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[20], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetLpwstr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[20]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setLpwstr(String lpwstr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[20], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[20]);
            }
            target.setStringValue(lpwstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetLpwstr(XmlString lpwstr) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[20], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[20]);
            }
            target.set(lpwstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetLpwstr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public String getBstr() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[21], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlString xgetBstr() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[21], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetBstr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[21]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setBstr(String bstr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[21], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[21]);
            }
            target.setStringValue(bstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetBstr(XmlString bstr) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[21], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[21]);
            }
            target.set(bstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetBstr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public Calendar getDate() {
        Calendar calendarValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[22], 0);
            calendarValue = target == null ? null : target.getCalendarValue();
        }
        return calendarValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlDateTime xgetDate() {
        XmlDateTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDateTime) get_store().find_element_user(PROPERTY_QNAME[22], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetDate() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[22]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setDate(Calendar date) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[22], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[22]);
            }
            target.setCalendarValue(date);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetDate(XmlDateTime date) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDateTime target = (XmlDateTime) get_store().find_element_user(PROPERTY_QNAME[22], 0);
            if (target == null) {
                target = (XmlDateTime) get_store().add_element_user(PROPERTY_QNAME[22]);
            }
            target.set(date);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetDate() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public Calendar getFiletime() {
        Calendar calendarValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[23], 0);
            calendarValue = target == null ? null : target.getCalendarValue();
        }
        return calendarValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlDateTime xgetFiletime() {
        XmlDateTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDateTime) get_store().find_element_user(PROPERTY_QNAME[23], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetFiletime() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[23]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setFiletime(Calendar filetime) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[23], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[23]);
            }
            target.setCalendarValue(filetime);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetFiletime(XmlDateTime filetime) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDateTime target = (XmlDateTime) get_store().find_element_user(PROPERTY_QNAME[23], 0);
            if (target == null) {
                target = (XmlDateTime) get_store().add_element_user(PROPERTY_QNAME[23]);
            }
            target.set(filetime);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetFiletime() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean getBool() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[24], 0);
            if (target != null) {
                z = target.getBooleanValue();
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlBoolean xgetBool() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_element_user(PROPERTY_QNAME[24], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetBool() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[24]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setBool(boolean bool) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[24], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[24]);
            }
            target.setBooleanValue(bool);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetBool(XmlBoolean bool) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_element_user(PROPERTY_QNAME[24], 0);
            if (target == null) {
                target = (XmlBoolean) get_store().add_element_user(PROPERTY_QNAME[24]);
            }
            target.set(bool);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetBool() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public String getCy() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[25], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public STCy xgetCy() {
        STCy target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[25], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetCy() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[25]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setCy(String cy) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[25], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[25]);
            }
            target.setStringValue(cy);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetCy(STCy cy) {
        synchronized (monitor()) {
            check_orphaned();
            STCy target = get_store().find_element_user(PROPERTY_QNAME[25], 0);
            if (target == null) {
                target = (STCy) get_store().add_element_user(PROPERTY_QNAME[25]);
            }
            target.set(cy);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetCy() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public String getError() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[26], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public STError xgetError() {
        STError target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[26], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetError() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[26]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setError(String error) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[26], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[26]);
            }
            target.setStringValue(error);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetError(STError error) {
        synchronized (monitor()) {
            check_orphaned();
            STError target = get_store().find_element_user(PROPERTY_QNAME[26], 0);
            if (target == null) {
                target = (STError) get_store().add_element_user(PROPERTY_QNAME[26]);
            }
            target.set(error);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetError() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public byte[] getStream() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[27], 0);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlBase64Binary xgetStream() {
        XmlBase64Binary target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBase64Binary) get_store().find_element_user(PROPERTY_QNAME[27], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetStream() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[27]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setStream(byte[] stream) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[27], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[27]);
            }
            target.setByteArrayValue(stream);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetStream(XmlBase64Binary stream) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBase64Binary target = (XmlBase64Binary) get_store().find_element_user(PROPERTY_QNAME[27], 0);
            if (target == null) {
                target = (XmlBase64Binary) get_store().add_element_user(PROPERTY_QNAME[27]);
            }
            target.set(stream);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetStream() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public byte[] getOstream() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[28], 0);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlBase64Binary xgetOstream() {
        XmlBase64Binary target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBase64Binary) get_store().find_element_user(PROPERTY_QNAME[28], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetOstream() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[28]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setOstream(byte[] ostream) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[28], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[28]);
            }
            target.setByteArrayValue(ostream);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetOstream(XmlBase64Binary ostream) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBase64Binary target = (XmlBase64Binary) get_store().find_element_user(PROPERTY_QNAME[28], 0);
            if (target == null) {
                target = (XmlBase64Binary) get_store().add_element_user(PROPERTY_QNAME[28]);
            }
            target.set(ostream);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetOstream() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public byte[] getStorage() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[29], 0);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlBase64Binary xgetStorage() {
        XmlBase64Binary target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBase64Binary) get_store().find_element_user(PROPERTY_QNAME[29], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetStorage() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[29]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setStorage(byte[] storage) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[29], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[29]);
            }
            target.setByteArrayValue(storage);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetStorage(XmlBase64Binary storage) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBase64Binary target = (XmlBase64Binary) get_store().find_element_user(PROPERTY_QNAME[29], 0);
            if (target == null) {
                target = (XmlBase64Binary) get_store().add_element_user(PROPERTY_QNAME[29]);
            }
            target.set(storage);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetStorage() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public byte[] getOstorage() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[30], 0);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlBase64Binary xgetOstorage() {
        XmlBase64Binary target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBase64Binary) get_store().find_element_user(PROPERTY_QNAME[30], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetOstorage() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[30]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setOstorage(byte[] ostorage) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[30], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[30]);
            }
            target.setByteArrayValue(ostorage);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetOstorage(XmlBase64Binary ostorage) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBase64Binary target = (XmlBase64Binary) get_store().find_element_user(PROPERTY_QNAME[30], 0);
            if (target == null) {
                target = (XmlBase64Binary) get_store().add_element_user(PROPERTY_QNAME[30]);
            }
            target.set(ostorage);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetOstorage() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[30], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public CTVstream getVstream() {
        CTVstream cTVstream;
        synchronized (monitor()) {
            check_orphaned();
            CTVstream target = get_store().find_element_user(PROPERTY_QNAME[31], 0);
            cTVstream = target == null ? null : target;
        }
        return cTVstream;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetVstream() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[31]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setVstream(CTVstream vstream) {
        generatedSetterHelperImpl(vstream, PROPERTY_QNAME[31], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public CTVstream addNewVstream() {
        CTVstream target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[31]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetVstream() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[31], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public String getClsid() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[32], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public STGuid xgetClsid() {
        STGuid target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STGuid) get_store().find_element_user(PROPERTY_QNAME[32], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetClsid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[32]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setClsid(String clsid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[32], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[32]);
            }
            target.setStringValue(clsid);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetClsid(STGuid clsid) {
        synchronized (monitor()) {
            check_orphaned();
            STGuid target = (STGuid) get_store().find_element_user(PROPERTY_QNAME[32], 0);
            if (target == null) {
                target = (STGuid) get_store().add_element_user(PROPERTY_QNAME[32]);
            }
            target.set(clsid);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetClsid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[32], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public String getFmtid() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public STGuid xgetFmtid() {
        STGuid target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STGuid) get_store().find_attribute_user(PROPERTY_QNAME[33]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setFmtid(String fmtid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[33]);
            }
            target.setStringValue(fmtid);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetFmtid(STGuid fmtid) {
        synchronized (monitor()) {
            check_orphaned();
            STGuid target = (STGuid) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            if (target == null) {
                target = (STGuid) get_store().add_attribute_user(PROPERTY_QNAME[33]);
            }
            target.set(fmtid);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public int getPid() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlInt xgetPid() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_attribute_user(PROPERTY_QNAME[34]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setPid(int pid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[34]);
            }
            target.setIntValue(pid);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetPid(XmlInt pid) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            if (target == null) {
                target = (XmlInt) get_store().add_attribute_user(PROPERTY_QNAME[34]);
            }
            target.set(pid);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlString xgetName() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[35]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[35]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[35]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetName(XmlString name) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[35]);
            }
            target.set(name);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[35]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public String getLinkTarget() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public XmlString xgetLinkTarget() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[36]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public boolean isSetLinkTarget() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[36]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void setLinkTarget(String linkTarget) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[36]);
            }
            target.setStringValue(linkTarget);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void xsetLinkTarget(XmlString linkTarget) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[36]);
            }
            target.set(linkTarget);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty
    public void unsetLinkTarget() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[36]);
        }
    }
}
