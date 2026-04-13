package org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl;

import com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda188;
import com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda66;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlByte;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlFloat;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlLong;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlShort;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.XmlUnsignedLong;
import org.apache.xmlbeans.XmlUnsignedShort;
import org.apache.xmlbeans.impl.values.JavaListObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVariant;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STCy;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STError;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STVectorBaseType;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.STVectorBaseType$Enum;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid;

/* loaded from: classes11.dex */
public class CTVectorImpl extends XmlComplexContentImpl implements CTVector {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "variant"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "i1"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "i2"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "i4"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "i8"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ui1"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ui2"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ui4"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "ui8"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "r4"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "r8"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "lpstr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "lpwstr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "bstr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", XmlErrorCodes.DATE), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "filetime"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "bool"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "cy"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "error"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "clsid"), new QName("", "baseType"), new QName("", "size")};
    private static final long serialVersionUID = 1;

    public CTVectorImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<CTVariant> getVariantList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda145
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.getVariantArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda146
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setVariantArray(((Integer) obj).intValue(), (CTVariant) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda147
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewVariant(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda148
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTVectorImpl.this.removeVariant(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda149
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTVectorImpl.this.sizeOfVariantArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public CTVariant[] getVariantArray() {
        return (CTVariant[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTVariant[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public CTVariant getVariantArray(int i) {
        CTVariant target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTVariant) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfVariantArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setVariantArray(CTVariant[] variantArray) {
        check_orphaned();
        arraySetterHelper(variantArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setVariantArray(int i, CTVariant variant) {
        generatedSetterHelperImpl(variant, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public CTVariant insertNewVariant(int i) {
        CTVariant target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTVariant) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public CTVariant addNewVariant() {
        CTVariant target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTVariant) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeVariant(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<Byte> getI1List() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda157
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Byte.valueOf(CTVectorImpl.this.getI1Array(((Integer) obj).intValue()));
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda158
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setI1Array(((Integer) obj).intValue(), ((Byte) obj2).byteValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda159
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertI1(((Integer) obj).intValue(), ((Byte) obj2).byteValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda97(this), new CTVectorImpl$$ExternalSyntheticLambda99(this));
        }
        return javaListObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public byte[] getI1Array() {
        return getByteArray(PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public byte getI1Array(int i) {
        byte byteValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            byteValue = target.getByteValue();
        }
        return byteValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<XmlByte> xgetI1List() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda77
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetI1Array(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda88
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetI1Array(((Integer) obj).intValue(), (XmlByte) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda96
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewI1(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda97(this), new CTVectorImpl$$ExternalSyntheticLambda99(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlByte[] lambda$xgetI1Array$0(int x$0) {
        return new XmlByte[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlByte[] xgetI1Array() {
        return (XmlByte[]) xgetArray(PROPERTY_QNAME[1], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda4
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetI1Array$0(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlByte xgetI1Array(int i) {
        XmlByte target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlByte) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfI1Array() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setI1Array(byte[] i1Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(i1Array, PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setI1Array(int i, byte i1) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setByteValue(i1);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetI1Array(XmlByte[] i1Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(i1Array, PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetI1Array(int i, XmlByte i1) {
        synchronized (monitor()) {
            check_orphaned();
            XmlByte target = (XmlByte) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(i1);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertI1(int i, byte i1) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[1], i);
            target.setByteValue(i1);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addI1(byte i1) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            target.setByteValue(i1);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlByte insertNewI1(int i) {
        XmlByte target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlByte) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlByte addNewI1() {
        XmlByte target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlByte) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeI1(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<Short> getI2List() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Short.valueOf(CTVectorImpl.this.getI2Array(((Integer) obj).intValue()));
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setI2Array(((Integer) obj).intValue(), ((Short) obj2).shortValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertI2(((Integer) obj).intValue(), ((Short) obj2).shortValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda40(this), new CTVectorImpl$$ExternalSyntheticLambda41(this));
        }
        return javaListObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public short[] getI2Array() {
        return getShortArray(PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public short getI2Array(int i) {
        short shortValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            shortValue = target.getShortValue();
        }
        return shortValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<XmlShort> xgetI2List() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetI2Array(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetI2Array(((Integer) obj).intValue(), (XmlShort) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewI2(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda40(this), new CTVectorImpl$$ExternalSyntheticLambda41(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlShort[] lambda$xgetI2Array$1(int x$0) {
        return new XmlShort[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlShort[] xgetI2Array() {
        return (XmlShort[]) xgetArray(PROPERTY_QNAME[2], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda14
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetI2Array$1(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlShort xgetI2Array(int i) {
        XmlShort target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlShort) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfI2Array() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setI2Array(short[] i2Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(i2Array, PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setI2Array(int i, short i2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setShortValue(i2);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetI2Array(XmlShort[] i2Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(i2Array, PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetI2Array(int i, XmlShort i2) {
        synchronized (monitor()) {
            check_orphaned();
            XmlShort target = (XmlShort) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(i2);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertI2(int i, short i2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[2], i);
            target.setShortValue(i2);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addI2(short i2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[2]);
            target.setShortValue(i2);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlShort insertNewI2(int i) {
        XmlShort target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlShort) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlShort addNewI2() {
        XmlShort target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlShort) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeI2(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<Integer> getI4List() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda162
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Integer.valueOf(CTVectorImpl.this.getI4Array(((Integer) obj).intValue()));
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda164
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setI4Array(((Integer) obj).intValue(), ((Integer) obj2).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda165
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertI4(((Integer) obj).intValue(), ((Integer) obj2).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda123(this), new CTVectorImpl$$ExternalSyntheticLambda124(this));
        }
        return javaListObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int[] getI4Array() {
        return getIntArray(PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int getI4Array(int i) {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            intValue = target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<XmlInt> xgetI4List() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda120
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetI4Array(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda121
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetI4Array(((Integer) obj).intValue(), (XmlInt) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda122
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewI4(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda123(this), new CTVectorImpl$$ExternalSyntheticLambda124(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlInt[] lambda$xgetI4Array$2(int x$0) {
        return new XmlInt[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlInt[] xgetI4Array() {
        return (XmlInt[]) xgetArray(PROPERTY_QNAME[3], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda80
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetI4Array$2(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlInt xgetI4Array(int i) {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfI4Array() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setI4Array(int[] i4Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(i4Array, PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setI4Array(int i, int i4) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setIntValue(i4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetI4Array(XmlInt[] i4Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(i4Array, PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetI4Array(int i, XmlInt i4) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(i4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertI4(int i, int i4) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[3], i);
            target.setIntValue(i4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addI4(int i4) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[3]);
            target.setIntValue(i4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlInt insertNewI4(int i) {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlInt addNewI4() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeI4(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<Long> getI8List() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda151
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Long.valueOf(CTVectorImpl.this.getI8Array(((Integer) obj).intValue()));
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda153
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setI8Array(((Integer) obj).intValue(), ((Long) obj2).longValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda154
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertI8(((Integer) obj).intValue(), ((Long) obj2).longValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda155(this), new CTVectorImpl$$ExternalSyntheticLambda156(this));
        }
        return javaListObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public long[] getI8Array() {
        return getLongArray(PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public long getI8Array(int i) {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            longValue = target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<XmlLong> xgetI8List() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetI8Array(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetI8Array(((Integer) obj).intValue(), (XmlLong) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewI8(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda155(this), new CTVectorImpl$$ExternalSyntheticLambda156(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlLong[] lambda$xgetI8Array$3(int x$0) {
        return new XmlLong[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlLong[] xgetI8Array() {
        return (XmlLong[]) xgetArray(PROPERTY_QNAME[4], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda169
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetI8Array$3(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlLong xgetI8Array(int i) {
        XmlLong target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlLong) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfI8Array() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setI8Array(long[] i8Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(i8Array, PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setI8Array(int i, long i8) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setLongValue(i8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetI8Array(XmlLong[] i8Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(i8Array, PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetI8Array(int i, XmlLong i8) {
        synchronized (monitor()) {
            check_orphaned();
            XmlLong target = (XmlLong) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(i8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertI8(int i, long i8) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[4], i);
            target.setLongValue(i8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addI8(long i8) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[4]);
            target.setLongValue(i8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlLong insertNewI8(int i) {
        XmlLong target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlLong) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlLong addNewI8() {
        XmlLong target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlLong) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeI8(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<Short> getUi1List() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda176
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Short.valueOf(CTVectorImpl.this.getUi1Array(((Integer) obj).intValue()));
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda177
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setUi1Array(((Integer) obj).intValue(), ((Short) obj2).shortValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda178
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertUi1(((Integer) obj).intValue(), ((Short) obj2).shortValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda179(this), new CTVectorImpl$$ExternalSyntheticLambda180(this));
        }
        return javaListObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public short[] getUi1Array() {
        return getShortArray(PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public short getUi1Array(int i) {
        short shortValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            shortValue = target.getShortValue();
        }
        return shortValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<XmlUnsignedByte> xgetUi1List() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetUi1Array(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetUi1Array(((Integer) obj).intValue(), (XmlUnsignedByte) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewUi1(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda179(this), new CTVectorImpl$$ExternalSyntheticLambda180(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlUnsignedByte[] lambda$xgetUi1Array$4(int x$0) {
        return new XmlUnsignedByte[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlUnsignedByte[] xgetUi1Array() {
        return (XmlUnsignedByte[]) xgetArray(PROPERTY_QNAME[5], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda84
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetUi1Array$4(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlUnsignedByte xgetUi1Array(int i) {
        XmlUnsignedByte target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedByte) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfUi1Array() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setUi1Array(short[] ui1Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(ui1Array, PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setUi1Array(int i, short ui1) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setShortValue(ui1);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetUi1Array(XmlUnsignedByte[] ui1Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(ui1Array, PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetUi1Array(int i, XmlUnsignedByte ui1) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedByte target = (XmlUnsignedByte) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(ui1);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertUi1(int i, short ui1) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[5], i);
            target.setShortValue(ui1);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addUi1(short ui1) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[5]);
            target.setShortValue(ui1);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlUnsignedByte insertNewUi1(int i) {
        XmlUnsignedByte target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedByte) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlUnsignedByte addNewUi1() {
        XmlUnsignedByte target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedByte) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeUi1(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<Integer> getUi2List() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda86
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Integer.valueOf(CTVectorImpl.this.getUi2Array(((Integer) obj).intValue()));
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda87
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setUi2Array(((Integer) obj).intValue(), ((Integer) obj2).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda89
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertUi2(((Integer) obj).intValue(), ((Integer) obj2).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda8(this), new CTVectorImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int[] getUi2Array() {
        return getIntArray(PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int getUi2Array(int i) {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            intValue = target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<XmlUnsignedShort> xgetUi2List() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetUi2Array(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetUi2Array(((Integer) obj).intValue(), (XmlUnsignedShort) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewUi2(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda8(this), new CTVectorImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlUnsignedShort[] lambda$xgetUi2Array$5(int x$0) {
        return new XmlUnsignedShort[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlUnsignedShort[] xgetUi2Array() {
        return (XmlUnsignedShort[]) xgetArray(PROPERTY_QNAME[6], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda50
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetUi2Array$5(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlUnsignedShort xgetUi2Array(int i) {
        XmlUnsignedShort target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedShort) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfUi2Array() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setUi2Array(int[] ui2Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(ui2Array, PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setUi2Array(int i, int ui2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setIntValue(ui2);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetUi2Array(XmlUnsignedShort[] ui2Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(ui2Array, PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetUi2Array(int i, XmlUnsignedShort ui2) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedShort target = (XmlUnsignedShort) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(ui2);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertUi2(int i, int ui2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[6], i);
            target.setIntValue(ui2);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addUi2(int ui2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[6]);
            target.setIntValue(ui2);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlUnsignedShort insertNewUi2(int i) {
        XmlUnsignedShort target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedShort) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlUnsignedShort addNewUi2() {
        XmlUnsignedShort target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedShort) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeUi2(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<Long> getUi4List() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda90
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Long.valueOf(CTVectorImpl.this.getUi4Array(((Integer) obj).intValue()));
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda91
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setUi4Array(((Integer) obj).intValue(), ((Long) obj2).longValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda92
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertUi4(((Integer) obj).intValue(), ((Long) obj2).longValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda185(this), new CTVectorImpl$$ExternalSyntheticLambda11(this));
        }
        return javaListObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public long[] getUi4Array() {
        return getLongArray(PROPERTY_QNAME[7]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public long getUi4Array(int i) {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            longValue = target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<XmlUnsignedInt> xgetUi4List() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda152
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetUi4Array(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda163
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetUi4Array(((Integer) obj).intValue(), (XmlUnsignedInt) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda174
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewUi4(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda185(this), new CTVectorImpl$$ExternalSyntheticLambda11(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlUnsignedInt[] lambda$xgetUi4Array$6(int x$0) {
        return new XmlUnsignedInt[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlUnsignedInt[] xgetUi4Array() {
        return (XmlUnsignedInt[]) xgetArray(PROPERTY_QNAME[7], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda30
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetUi4Array$6(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlUnsignedInt xgetUi4Array(int i) {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfUi4Array() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setUi4Array(long[] ui4Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(ui4Array, PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setUi4Array(int i, long ui4) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setLongValue(ui4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetUi4Array(XmlUnsignedInt[] ui4Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(ui4Array, PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetUi4Array(int i, XmlUnsignedInt ui4) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(ui4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertUi4(int i, long ui4) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[7], i);
            target.setLongValue(ui4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addUi4(long ui4) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[7]);
            target.setLongValue(ui4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlUnsignedInt insertNewUi4(int i) {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlUnsignedInt addNewUi4() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeUi4(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<BigInteger> getUi8List() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda126
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.getUi8Array(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda127
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setUi8Array(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda128
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertUi8(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda129(this), new CTVectorImpl$$ExternalSyntheticLambda131(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getUi8Array$7(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public BigInteger[] getUi8Array() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[8], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda93
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$getUi8Array$7(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public BigInteger getUi8Array(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<XmlUnsignedLong> xgetUi8List() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda138
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetUi8Array(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda139
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetUi8Array(((Integer) obj).intValue(), (XmlUnsignedLong) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda140
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewUi8(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda129(this), new CTVectorImpl$$ExternalSyntheticLambda131(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlUnsignedLong[] lambda$xgetUi8Array$8(int x$0) {
        return new XmlUnsignedLong[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlUnsignedLong[] xgetUi8Array() {
        return (XmlUnsignedLong[]) xgetArray(PROPERTY_QNAME[8], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda141
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetUi8Array$8(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlUnsignedLong xgetUi8Array(int i) {
        XmlUnsignedLong target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedLong) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfUi8Array() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setUi8Array(BigInteger[] ui8Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(ui8Array, PROPERTY_QNAME[8]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setUi8Array(int i, BigInteger ui8) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(ui8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetUi8Array(XmlUnsignedLong[] ui8Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(ui8Array, PROPERTY_QNAME[8]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetUi8Array(int i, XmlUnsignedLong ui8) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedLong target = (XmlUnsignedLong) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(ui8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertUi8(int i, BigInteger ui8) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[8], i);
            target.setBigIntegerValue(ui8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addUi8(BigInteger ui8) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[8]);
            target.setBigIntegerValue(ui8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlUnsignedLong insertNewUi8(int i) {
        XmlUnsignedLong target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedLong) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlUnsignedLong addNewUi8() {
        XmlUnsignedLong target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedLong) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeUi8(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<Float> getR4List() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Float.valueOf(CTVectorImpl.this.getR4Array(((Integer) obj).intValue()));
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setR4Array(((Integer) obj).intValue(), ((Float) obj2).floatValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertR4(((Integer) obj).intValue(), ((Float) obj2).floatValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda55(this), new CTVectorImpl$$ExternalSyntheticLambda66(this));
        }
        return javaListObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public float[] getR4Array() {
        return getFloatArray(PROPERTY_QNAME[9]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public float getR4Array(int i) {
        float floatValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            floatValue = target.getFloatValue();
        }
        return floatValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<XmlFloat> xgetR4List() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda166
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetR4Array(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda167
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetR4Array(((Integer) obj).intValue(), (XmlFloat) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda168
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewR4(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda55(this), new CTVectorImpl$$ExternalSyntheticLambda66(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlFloat[] lambda$xgetR4Array$9(int x$0) {
        return new XmlFloat[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlFloat[] xgetR4Array() {
        return (XmlFloat[]) xgetArray(PROPERTY_QNAME[9], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda75
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetR4Array$9(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlFloat xgetR4Array(int i) {
        XmlFloat target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlFloat) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfR4Array() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setR4Array(float[] r4Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(r4Array, PROPERTY_QNAME[9]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setR4Array(int i, float r4) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setFloatValue(r4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetR4Array(XmlFloat[] r4Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(r4Array, PROPERTY_QNAME[9]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetR4Array(int i, XmlFloat r4) {
        synchronized (monitor()) {
            check_orphaned();
            XmlFloat target = (XmlFloat) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(r4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertR4(int i, float r4) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[9], i);
            target.setFloatValue(r4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addR4(float r4) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[9]);
            target.setFloatValue(r4);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlFloat insertNewR4(int i) {
        XmlFloat target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlFloat) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlFloat addNewR4() {
        XmlFloat target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlFloat) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeR4(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<Double> getR8List() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda113
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Double.valueOf(CTVectorImpl.this.getR8Array(((Integer) obj).intValue()));
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda114
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setR8Array(((Integer) obj).intValue(), ((Double) obj2).doubleValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda115
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertR8(((Integer) obj).intValue(), ((Double) obj2).doubleValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda116(this), new CTVectorImpl$$ExternalSyntheticLambda117(this));
        }
        return javaListObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public double[] getR8Array() {
        return getDoubleArray(PROPERTY_QNAME[10]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public double getR8Array(int i) {
        double doubleValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            doubleValue = target.getDoubleValue();
        }
        return doubleValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<XmlDouble> xgetR8List() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda142
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetR8Array(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda143
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetR8Array(((Integer) obj).intValue(), (XmlDouble) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda144
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewR8(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda116(this), new CTVectorImpl$$ExternalSyntheticLambda117(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlDouble[] lambda$xgetR8Array$10(int x$0) {
        return new XmlDouble[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlDouble[] xgetR8Array() {
        return (XmlDouble[]) xgetArray(PROPERTY_QNAME[10], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda29
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetR8Array$10(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlDouble xgetR8Array(int i) {
        XmlDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDouble) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfR8Array() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setR8Array(double[] r8Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(r8Array, PROPERTY_QNAME[10]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setR8Array(int i, double r8) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setDoubleValue(r8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetR8Array(XmlDouble[] r8Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(r8Array, PROPERTY_QNAME[10]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetR8Array(int i, XmlDouble r8) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDouble target = (XmlDouble) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(r8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertR8(int i, double r8) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[10], i);
            target.setDoubleValue(r8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addR8(double r8) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[10]);
            target.setDoubleValue(r8);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlDouble insertNewR8(int i) {
        XmlDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDouble) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlDouble addNewR8() {
        XmlDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDouble) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeR8(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<String> getLpstrList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda81
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.getLpstrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda82
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setLpstrArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda83
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertLpstr(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda111(this), new CTVectorImpl$$ExternalSyntheticLambda112(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getLpstrArray$11(int x$0) {
        return new String[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public String[] getLpstrArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[11], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda85
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$getLpstrArray$11(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public String getLpstrArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<XmlString> xgetLpstrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda107
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetLpstrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda109
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetLpstrArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda110
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewLpstr(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda111(this), new CTVectorImpl$$ExternalSyntheticLambda112(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetLpstrArray$12(int x$0) {
        return new XmlString[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlString[] xgetLpstrArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[11], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda150
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetLpstrArray$12(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlString xgetLpstrArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfLpstrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setLpstrArray(String[] lpstrArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(lpstrArray, PROPERTY_QNAME[11]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setLpstrArray(int i, String lpstr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(lpstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetLpstrArray(XmlString[] lpstrArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(lpstrArray, PROPERTY_QNAME[11]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetLpstrArray(int i, XmlString lpstr) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(lpstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertLpstr(int i, String lpstr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[11], i);
            target.setStringValue(lpstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addLpstr(String lpstr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[11]);
            target.setStringValue(lpstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlString insertNewLpstr(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlString addNewLpstr() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeLpstr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<String> getLpwstrList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.getLpwstrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setLpwstrArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertLpwstr(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda119(this), new CTVectorImpl$$ExternalSyntheticLambda130(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getLpwstrArray$13(int x$0) {
        return new String[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public String[] getLpwstrArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[12], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda106
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$getLpwstrArray$13(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public String getLpwstrArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<XmlString> xgetLpwstrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetLpwstrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda98
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetLpwstrArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda108
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewLpwstr(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda119(this), new CTVectorImpl$$ExternalSyntheticLambda130(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetLpwstrArray$14(int x$0) {
        return new XmlString[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlString[] xgetLpwstrArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[12], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda100
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetLpwstrArray$14(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlString xgetLpwstrArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfLpwstrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setLpwstrArray(String[] lpwstrArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(lpwstrArray, PROPERTY_QNAME[12]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setLpwstrArray(int i, String lpwstr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(lpwstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetLpwstrArray(XmlString[] lpwstrArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(lpwstrArray, PROPERTY_QNAME[12]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetLpwstrArray(int i, XmlString lpwstr) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(lpwstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertLpwstr(int i, String lpwstr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[12], i);
            target.setStringValue(lpwstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addLpwstr(String lpwstr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[12]);
            target.setStringValue(lpwstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlString insertNewLpwstr(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlString addNewLpwstr() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeLpwstr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<String> getBstrList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.getBstrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setBstrArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertBstr(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda1(this), new CTVectorImpl$$ExternalSyntheticLambda2(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getBstrArray$15(int x$0) {
        return new String[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public String[] getBstrArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[13], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda49
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$getBstrArray$15(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public String getBstrArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<XmlString> xgetBstrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda182
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetBstrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda183
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetBstrArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda184
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewBstr(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda1(this), new CTVectorImpl$$ExternalSyntheticLambda2(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetBstrArray$16(int x$0) {
        return new XmlString[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlString[] xgetBstrArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[13], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda95
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetBstrArray$16(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlString xgetBstrArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfBstrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setBstrArray(String[] bstrArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(bstrArray, PROPERTY_QNAME[13]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setBstrArray(int i, String bstr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(bstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetBstrArray(XmlString[] bstrArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(bstrArray, PROPERTY_QNAME[13]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetBstrArray(int i, XmlString bstr) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(bstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertBstr(int i, String bstr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[13], i);
            target.setStringValue(bstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addBstr(String bstr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[13]);
            target.setStringValue(bstr);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlString insertNewBstr(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlString addNewBstr() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeBstr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<Calendar> getDateList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda72
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.getDateArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda73
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setDateArray(((Integer) obj).intValue(), (Calendar) obj2);
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda74
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertDate(((Integer) obj).intValue(), (Calendar) obj2);
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda21(this), new CTVectorImpl$$ExternalSyntheticLambda23(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Calendar[] lambda$getDateArray$17(int x$0) {
        return new Calendar[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public Calendar[] getDateArray() {
        return (Calendar[]) getObjectArray(PROPERTY_QNAME[14], new CTVectorImpl$$ExternalSyntheticLambda160(), new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda54
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$getDateArray$17(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public Calendar getDateArray(int i) {
        Calendar calendarValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            calendarValue = target.getCalendarValue();
        }
        return calendarValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<XmlDateTime> xgetDateList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetDateArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetDateArray(((Integer) obj).intValue(), (XmlDateTime) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewDate(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda21(this), new CTVectorImpl$$ExternalSyntheticLambda23(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlDateTime[] lambda$xgetDateArray$18(int x$0) {
        return new XmlDateTime[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlDateTime[] xgetDateArray() {
        return (XmlDateTime[]) xgetArray(PROPERTY_QNAME[14], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda36
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetDateArray$18(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlDateTime xgetDateArray(int i) {
        XmlDateTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDateTime) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfDateArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setDateArray(Calendar[] dateArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(dateArray, PROPERTY_QNAME[14]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setDateArray(int i, Calendar date) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setCalendarValue(date);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetDateArray(XmlDateTime[] dateArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(dateArray, PROPERTY_QNAME[14]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetDateArray(int i, XmlDateTime date) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDateTime target = (XmlDateTime) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(date);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertDate(int i, Calendar date) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[14], i);
            target.setCalendarValue(date);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addDate(Calendar date) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[14]);
            target.setCalendarValue(date);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlDateTime insertNewDate(int i) {
        XmlDateTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDateTime) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlDateTime addNewDate() {
        XmlDateTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDateTime) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeDate(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<Calendar> getFiletimeList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.getFiletimeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setFiletimeArray(((Integer) obj).intValue(), (Calendar) obj2);
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertFiletime(((Integer) obj).intValue(), (Calendar) obj2);
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda104(this), new CTVectorImpl$$ExternalSyntheticLambda105(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Calendar[] lambda$getFiletimeArray$19(int x$0) {
        return new Calendar[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public Calendar[] getFiletimeArray() {
        return (Calendar[]) getObjectArray(PROPERTY_QNAME[15], new CTVectorImpl$$ExternalSyntheticLambda160(), new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda161
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$getFiletimeArray$19(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public Calendar getFiletimeArray(int i) {
        Calendar calendarValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            calendarValue = target.getCalendarValue();
        }
        return calendarValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<XmlDateTime> xgetFiletimeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda101
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetFiletimeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda102
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetFiletimeArray(((Integer) obj).intValue(), (XmlDateTime) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda103
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewFiletime(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda104(this), new CTVectorImpl$$ExternalSyntheticLambda105(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlDateTime[] lambda$xgetFiletimeArray$20(int x$0) {
        return new XmlDateTime[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlDateTime[] xgetFiletimeArray() {
        return (XmlDateTime[]) xgetArray(PROPERTY_QNAME[15], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda3
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetFiletimeArray$20(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlDateTime xgetFiletimeArray(int i) {
        XmlDateTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDateTime) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfFiletimeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setFiletimeArray(Calendar[] filetimeArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(filetimeArray, PROPERTY_QNAME[15]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setFiletimeArray(int i, Calendar filetime) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setCalendarValue(filetime);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetFiletimeArray(XmlDateTime[] filetimeArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(filetimeArray, PROPERTY_QNAME[15]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetFiletimeArray(int i, XmlDateTime filetime) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDateTime target = (XmlDateTime) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(filetime);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertFiletime(int i, Calendar filetime) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[15], i);
            target.setCalendarValue(filetime);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addFiletime(Calendar filetime) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[15]);
            target.setCalendarValue(filetime);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlDateTime insertNewFiletime(int i) {
        XmlDateTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDateTime) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlDateTime addNewFiletime() {
        XmlDateTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDateTime) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeFiletime(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<Boolean> getBoolList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Boolean.valueOf(CTVectorImpl.this.getBoolArray(((Integer) obj).intValue()));
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setBoolArray(((Integer) obj).intValue(), ((Boolean) obj2).booleanValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertBool(((Integer) obj).intValue(), ((Boolean) obj2).booleanValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda59(this), new CTVectorImpl$$ExternalSyntheticLambda60(this));
        }
        return javaListObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public boolean[] getBoolArray() {
        return getBooleanArray(PROPERTY_QNAME[16]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public boolean getBoolArray(int i) {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            booleanValue = target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<XmlBoolean> xgetBoolList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda76
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetBoolArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda78
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetBoolArray(((Integer) obj).intValue(), (XmlBoolean) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda79
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewBool(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda59(this), new CTVectorImpl$$ExternalSyntheticLambda60(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlBoolean[] lambda$xgetBoolArray$21(int x$0) {
        return new XmlBoolean[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlBoolean[] xgetBoolArray() {
        return (XmlBoolean[]) xgetArray(PROPERTY_QNAME[16], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda94
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetBoolArray$21(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlBoolean xgetBoolArray(int i) {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfBoolArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setBoolArray(boolean[] boolArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(boolArray, PROPERTY_QNAME[16]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setBoolArray(int i, boolean bool) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBooleanValue(bool);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetBoolArray(XmlBoolean[] boolArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(boolArray, PROPERTY_QNAME[16]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetBoolArray(int i, XmlBoolean bool) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(bool);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertBool(int i, boolean bool) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[16], i);
            target.setBooleanValue(bool);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addBool(boolean bool) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[16]);
            target.setBooleanValue(bool);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlBoolean insertNewBool(int i) {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlBoolean addNewBool() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeBool(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<String> getCyList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda170
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.getCyArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda171
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setCyArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda172
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertCy(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda173(this), new CTVectorImpl$$ExternalSyntheticLambda175(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getCyArray$22(int x$0) {
        return new String[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public String[] getCyArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[17], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda71
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$getCyArray$22(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public String getCyArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<STCy> xgetCyList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetCyArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda69
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetCyArray(((Integer) obj).intValue(), (STCy) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda70
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewCy(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda173(this), new CTVectorImpl$$ExternalSyntheticLambda175(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STCy[] lambda$xgetCyArray$23(int x$0) {
        return new STCy[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public STCy[] xgetCyArray() {
        return xgetArray(PROPERTY_QNAME[17], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda137
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetCyArray$23(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public STCy xgetCyArray(int i) {
        STCy target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfCyArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setCyArray(String[] cyArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(cyArray, PROPERTY_QNAME[17]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setCyArray(int i, String cy) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(cy);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetCyArray(STCy[] cyArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) cyArray, PROPERTY_QNAME[17]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetCyArray(int i, STCy cy) {
        synchronized (monitor()) {
            check_orphaned();
            STCy target = get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(cy);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertCy(int i, String cy) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[17], i);
            target.setStringValue(cy);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addCy(String cy) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[17]);
            target.setStringValue(cy);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public STCy insertNewCy(int i) {
        STCy target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public STCy addNewCy() {
        STCy target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeCy(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<String> getErrorList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.getErrorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setErrorArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertError(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda135(this), new CTVectorImpl$$ExternalSyntheticLambda136(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getErrorArray$24(int x$0) {
        return new String[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public String[] getErrorArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[18], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda181
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$getErrorArray$24(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public String getErrorArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<STError> xgetErrorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda132
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetErrorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda133
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetErrorArray(((Integer) obj).intValue(), (STError) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda134
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewError(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda135(this), new CTVectorImpl$$ExternalSyntheticLambda136(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STError[] lambda$xgetErrorArray$25(int x$0) {
        return new STError[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public STError[] xgetErrorArray() {
        return xgetArray(PROPERTY_QNAME[18], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda31
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetErrorArray$25(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public STError xgetErrorArray(int i) {
        STError target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfErrorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setErrorArray(String[] errorArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(errorArray, PROPERTY_QNAME[18]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setErrorArray(int i, String error) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(error);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetErrorArray(STError[] errorArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) errorArray, PROPERTY_QNAME[18]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetErrorArray(int i, STError error) {
        synchronized (monitor()) {
            check_orphaned();
            STError target = get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(error);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertError(int i, String error) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[18], i);
            target.setStringValue(error);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addError(String error) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[18]);
            target.setStringValue(error);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public STError insertNewError(int i) {
        STError target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public STError addNewError() {
        STError target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeError(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<String> getClsidList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.getClsidArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.setClsidArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.insertClsid(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda27(this), new CTVectorImpl$$ExternalSyntheticLambda28(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getClsidArray$26(int x$0) {
        return new String[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public String[] getClsidArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[19], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda118
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$getClsidArray$26(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public String getClsidArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public List<STGuid> xgetClsidList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.xgetClsidArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTVectorImpl.this.xsetClsidArray(((Integer) obj).intValue(), (STGuid) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTVectorImpl.this.insertNewClsid(((Integer) obj).intValue());
                }
            }, new CTVectorImpl$$ExternalSyntheticLambda27(this), new CTVectorImpl$$ExternalSyntheticLambda28(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STGuid[] lambda$xgetClsidArray$27(int x$0) {
        return new STGuid[x$0];
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public STGuid[] xgetClsidArray() {
        return (STGuid[]) xgetArray(PROPERTY_QNAME[19], new IntFunction() { // from class: org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.impl.CTVectorImpl$$ExternalSyntheticLambda125
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTVectorImpl.lambda$xgetClsidArray$27(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public STGuid xgetClsidArray(int i) {
        STGuid target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STGuid) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public int sizeOfClsidArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setClsidArray(String[] clsidArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(clsidArray, PROPERTY_QNAME[19]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setClsidArray(int i, String clsid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(clsid);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetClsidArray(STGuid[] clsidArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(clsidArray, PROPERTY_QNAME[19]);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetClsidArray(int i, STGuid clsid) {
        synchronized (monitor()) {
            check_orphaned();
            STGuid target = (STGuid) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(clsid);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void insertClsid(int i, String clsid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[19], i);
            target.setStringValue(clsid);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void addClsid(String clsid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[19]);
            target.setStringValue(clsid);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public STGuid insertNewClsid(int i) {
        STGuid target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STGuid) get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public STGuid addNewClsid() {
        STGuid target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STGuid) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void removeClsid(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public STVectorBaseType$Enum getBaseType() {
        STVectorBaseType$Enum sTVectorBaseType$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            sTVectorBaseType$Enum = target == null ? null : (STVectorBaseType$Enum) target.getEnumValue();
        }
        return sTVectorBaseType$Enum;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public STVectorBaseType xgetBaseType() {
        STVectorBaseType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setBaseType(STVectorBaseType$Enum baseType) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.setEnumValue(baseType);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetBaseType(STVectorBaseType baseType) {
        synchronized (monitor()) {
            check_orphaned();
            STVectorBaseType target = get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (STVectorBaseType) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.set(baseType);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public long getSize() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public XmlUnsignedInt xgetSize() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void setSize(long size) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.setLongValue(size);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector
    public void xsetSize(XmlUnsignedInt size) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.set(size);
        }
    }
}
