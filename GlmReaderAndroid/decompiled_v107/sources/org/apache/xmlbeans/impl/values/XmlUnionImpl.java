package org.apache.xmlbeans.impl.values;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDateSpecification;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.GDurationSpecification;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;

/* loaded from: classes11.dex */
public class XmlUnionImpl extends XmlObjectBase implements XmlAnySimpleType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int JAVA_BYTEARRAY = 50;
    private static final int JAVA_CALENDAR = 49;
    private static final int JAVA_DATE = 48;
    private static final int JAVA_LIST = 51;
    private static final int JAVA_NUMBER = 47;
    private final SchemaType _schemaType;
    private String _textvalue = "";
    private XmlAnySimpleType _value;

    public XmlUnionImpl(SchemaType type, boolean complex) {
        this._schemaType = type;
        initComplexType(complex, false);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.XmlObject
    public SchemaType schemaType() {
        return this._schemaType;
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public SchemaType instanceType() {
        check_dated();
        if (this._value == null) {
            return null;
        }
        return ((SimpleValue) this._value).instanceType();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected String compute_text(NamespaceManager nsm) {
        return this._textvalue;
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected boolean is_defaultable_ws(String v) {
        try {
            XmlAnySimpleType savedValue = this._value;
            set_text(v);
            this._value = savedValue;
            return false;
        } catch (XmlValueOutOfRangeException e) {
            return true;
        }
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_text(String s) {
        XmlAnySimpleType newval;
        if (!this._schemaType.matchPatternFacet(s) && _validateOnSet()) {
            throw new XmlValueOutOfRangeException(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{TypedValues.Custom.S_STRING, s, QNameHelper.readable(this._schemaType)});
        }
        String original = this._textvalue;
        this._textvalue = s;
        SchemaType[] members = this._schemaType.getUnionConstituentTypes();
        if (members == null) {
            throw new AssertionError();
        }
        boolean pushed = false;
        if (has_store()) {
            NamespaceContext.push(new NamespaceContext(get_store()));
            pushed = true;
        }
        boolean validate = true;
        while (true) {
            if (!validate) {
                try {
                    if (_validateOnSet()) {
                        break;
                    }
                } finally {
                    if (pushed) {
                        NamespaceContext.pop();
                    }
                }
            }
            for (SchemaType member : members) {
                try {
                    newval = ((SchemaTypeImpl) member).newValue(s, validate);
                } catch (XmlValueOutOfRangeException e) {
                } catch (Exception e2) {
                    throw new RuntimeException("Troublesome union exception caused by unexpected " + e2, e2);
                }
                if (check(newval, this._schemaType)) {
                    this._value = newval;
                    if (pushed) {
                        return;
                    } else {
                        return;
                    }
                }
            }
            if (!validate) {
                break;
            } else {
                validate = false;
            }
        }
        if (pushed) {
            NamespaceContext.pop();
        }
        this._textvalue = original;
        throw new XmlValueOutOfRangeException(XmlErrorCodes.DATATYPE_VALID$UNION, new Object[]{s, QNameHelper.readable(this._schemaType)});
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_nil() {
        this._value = null;
        this._textvalue = null;
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected int get_wscanon_rule() {
        return 1;
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public float getFloatValue() {
        check_dated();
        if (this._value == null) {
            return 0.0f;
        }
        return ((SimpleValue) this._value).getFloatValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public double getDoubleValue() {
        check_dated();
        if (this._value == null) {
            return 0.0d;
        }
        return ((SimpleValue) this._value).getDoubleValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public BigDecimal getBigDecimalValue() {
        check_dated();
        if (this._value == null) {
            return null;
        }
        return ((SimpleValue) this._value).getBigDecimalValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public BigInteger getBigIntegerValue() {
        check_dated();
        if (this._value == null) {
            return null;
        }
        return ((SimpleValue) this._value).getBigIntegerValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public byte getByteValue() {
        check_dated();
        if (this._value == null) {
            return (byte) 0;
        }
        return ((SimpleValue) this._value).getByteValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public short getShortValue() {
        check_dated();
        if (this._value == null) {
            return (short) 0;
        }
        return ((SimpleValue) this._value).getShortValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public int getIntValue() {
        check_dated();
        if (this._value == null) {
            return 0;
        }
        return ((SimpleValue) this._value).getIntValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public long getLongValue() {
        check_dated();
        if (this._value == null) {
            return 0L;
        }
        return ((SimpleValue) this._value).getLongValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public byte[] getByteArrayValue() {
        check_dated();
        if (this._value == null) {
            return null;
        }
        return ((SimpleValue) this._value).getByteArrayValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public boolean getBooleanValue() {
        check_dated();
        return this._value != null && ((SimpleValue) this._value).getBooleanValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public Calendar getCalendarValue() {
        check_dated();
        if (this._value == null) {
            return null;
        }
        return ((SimpleValue) this._value).getCalendarValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public Date getDateValue() {
        check_dated();
        if (this._value == null) {
            return null;
        }
        return ((SimpleValue) this._value).getDateValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public GDate getGDateValue() {
        check_dated();
        if (this._value == null) {
            return null;
        }
        return ((SimpleValue) this._value).getGDateValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public GDuration getGDurationValue() {
        check_dated();
        if (this._value == null) {
            return null;
        }
        return ((SimpleValue) this._value).getGDurationValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public QName getQNameValue() {
        check_dated();
        if (this._value == null) {
            return null;
        }
        return ((SimpleValue) this._value).getQNameValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public List<?> getListValue() {
        check_dated();
        if (this._value == null) {
            return null;
        }
        return ((SimpleValue) this._value).getListValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public List<? extends XmlAnySimpleType> xgetListValue() {
        check_dated();
        if (this._value == null) {
            return null;
        }
        return ((SimpleValue) this._value).xgetListValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public StringEnumAbstractBase getEnumValue() {
        check_dated();
        if (this._value == null) {
            return null;
        }
        return ((SimpleValue) this._value).getEnumValue();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public String getStringValue() {
        check_dated();
        if (this._value == null) {
            return null;
        }
        return this._value.getStringValue();
    }

    private static boolean logical_overlap(SchemaType type, int javacode) {
        if (type.getSimpleVariety() == 2) {
            throw new AssertionError();
        }
        if (javacode <= 46) {
            return type.getSimpleVariety() == 1 && type.getPrimitiveType().getBuiltinTypeCode() == javacode;
        }
        switch (javacode) {
            case 47:
                if (type.getSimpleVariety() != 1) {
                    return false;
                }
                switch (type.getPrimitiveType().getBuiltinTypeCode()) {
                    case 9:
                    case 10:
                    case 11:
                    case 18:
                    case 20:
                    case 21:
                        return true;
                    default:
                        return false;
                }
            case 48:
                if (type.getSimpleVariety() != 1) {
                    return false;
                }
                switch (type.getPrimitiveType().getBuiltinTypeCode()) {
                    case 14:
                    case 16:
                        return true;
                    case 15:
                    default:
                        return false;
                }
            case 49:
                if (type.getSimpleVariety() != 1) {
                    return false;
                }
                switch (type.getPrimitiveType().getBuiltinTypeCode()) {
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                        return true;
                    default:
                        return false;
                }
            case 50:
                if (type.getSimpleVariety() != 1) {
                    return false;
                }
                switch (type.getPrimitiveType().getBuiltinTypeCode()) {
                    case 4:
                    case 5:
                        return true;
                    default:
                        return false;
                }
            case 51:
                return type.getSimpleVariety() == 3;
            default:
                throw new AssertionError("missing case");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x006b, code lost:
    
        if (r1 == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006d, code lost:
    
        org.apache.xmlbeans.impl.values.NamespaceContext.pop();
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0085, code lost:
    
        throw new org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException(org.apache.xmlbeans.XmlErrorCodes.DATATYPE_VALID$UNION, new java.lang.Object[]{r10.toString(), org.apache.xmlbeans.impl.common.QNameHelper.readable(r8._schemaType)});
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void set_primitive(int r9, java.lang.Object r10) {
        /*
            r8 = this;
            org.apache.xmlbeans.SchemaType r0 = r8._schemaType
            org.apache.xmlbeans.SchemaType[] r0 = r0.getUnionConstituentTypes()
            if (r0 == 0) goto L8f
            r1 = 0
            boolean r2 = r8.has_store()
            if (r2 == 0) goto L1c
            org.apache.xmlbeans.impl.values.NamespaceContext r2 = new org.apache.xmlbeans.impl.values.NamespaceContext
            org.apache.xmlbeans.impl.values.TypeStore r3 = r8.get_store()
            r2.<init>(r3)
            org.apache.xmlbeans.impl.values.NamespaceContext.push(r2)
            r1 = 1
        L1c:
            r2 = 1
        L1d:
            if (r2 != 0) goto L25
            boolean r3 = r8._validateOnSet()     // Catch: java.lang.Throwable -> L88
            if (r3 != 0) goto L6b
        L25:
            int r3 = r0.length     // Catch: java.lang.Throwable -> L88
            r4 = 0
        L27:
            if (r4 >= r3) goto L68
            r5 = r0[r4]     // Catch: java.lang.Throwable -> L88
            boolean r6 = logical_overlap(r5, r9)     // Catch: java.lang.Throwable -> L88
            if (r6 == 0) goto L65
            r6 = r5
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r6 = (org.apache.xmlbeans.impl.schema.SchemaTypeImpl) r6     // Catch: java.lang.Exception -> L49 org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException -> L63 java.lang.Throwable -> L88
            org.apache.xmlbeans.XmlAnySimpleType r3 = r6.newValue(r10, r2)     // Catch: java.lang.Exception -> L49 org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException -> L63 java.lang.Throwable -> L88
            r8._value = r3     // Catch: java.lang.Throwable -> L88
            org.apache.xmlbeans.XmlAnySimpleType r4 = r8._value     // Catch: java.lang.Throwable -> L88
            java.lang.String r4 = r4.getStringValue()     // Catch: java.lang.Throwable -> L88
            r8._textvalue = r4     // Catch: java.lang.Throwable -> L88
            if (r1 == 0) goto L48
            org.apache.xmlbeans.impl.values.NamespaceContext.pop()
        L48:
            return
        L49:
            r3 = move-exception
            java.lang.AssertionError r4 = new java.lang.AssertionError     // Catch: java.lang.Throwable -> L88
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L88
            r6.<init>()     // Catch: java.lang.Throwable -> L88
            java.lang.String r7 = "Unexpected "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch: java.lang.Throwable -> L88
            java.lang.StringBuilder r6 = r6.append(r3)     // Catch: java.lang.Throwable -> L88
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L88
            r4.<init>(r6)     // Catch: java.lang.Throwable -> L88
            throw r4     // Catch: java.lang.Throwable -> L88
        L63:
            r6 = move-exception
        L65:
            int r4 = r4 + 1
            goto L27
        L68:
            if (r2 != 0) goto L86
        L6b:
            if (r1 == 0) goto L70
            org.apache.xmlbeans.impl.values.NamespaceContext.pop()
        L70:
            org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException r2 = new org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException
            java.lang.String r3 = r10.toString()
            org.apache.xmlbeans.SchemaType r4 = r8._schemaType
            java.lang.String r4 = org.apache.xmlbeans.impl.common.QNameHelper.readable(r4)
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4}
            java.lang.String r4 = "cvc-datatype-valid.1.2.3"
            r2.<init>(r4, r3)
            throw r2
        L86:
            r2 = 0
            goto L1d
        L88:
            r2 = move-exception
            if (r1 == 0) goto L8e
            org.apache.xmlbeans.impl.values.NamespaceContext.pop()
        L8e:
            throw r2
        L8f:
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.values.XmlUnionImpl.set_primitive(int, java.lang.Object):void");
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_boolean(boolean v) {
        set_primitive(3, Boolean.valueOf(v));
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_byte(byte v) {
        set_primitive(47, Byte.valueOf(v));
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_short(short v) {
        set_primitive(47, Short.valueOf(v));
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_int(int v) {
        set_primitive(47, Integer.valueOf(v));
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_long(long v) {
        set_primitive(47, Long.valueOf(v));
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_float(float v) {
        set_primitive(47, Float.valueOf(v));
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_double(double v) {
        set_primitive(47, Double.valueOf(v));
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_ByteArray(byte[] b) {
        set_primitive(50, b);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_hex(byte[] b) {
        set_primitive(50, b);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_b64(byte[] b) {
        set_primitive(50, b);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_BigInteger(BigInteger v) {
        set_primitive(47, v);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_BigDecimal(BigDecimal v) {
        set_primitive(47, v);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_QName(QName v) {
        set_primitive(7, v);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_Calendar(Calendar c) {
        set_primitive(49, c);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_Date(Date d) {
        set_primitive(48, d);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_GDate(GDateSpecification d) {
        int btc = d.getBuiltinTypeCode();
        if (btc <= 0) {
            throw new XmlValueOutOfRangeException();
        }
        set_primitive(btc, d);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_GDuration(GDurationSpecification d) {
        set_primitive(13, d);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_enum(StringEnumAbstractBase e) {
        set_primitive(12, e);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_list(List<?> v) {
        set_primitive(51, v);
    }

    protected void set_xmlfloat(XmlObject v) {
        set_primitive(9, v);
    }

    protected void set_xmldouble(XmlObject v) {
        set_primitive(10, v);
    }

    protected void set_xmldecimal(XmlObject v) {
        set_primitive(11, v);
    }

    protected void set_xmlduration(XmlObject v) {
        set_primitive(13, v);
    }

    protected void set_xmldatetime(XmlObject v) {
        set_primitive(14, v);
    }

    protected void set_xmltime(XmlObject v) {
        set_primitive(15, v);
    }

    protected void set_xmldate(XmlObject v) {
        set_primitive(16, v);
    }

    protected void set_xmlgyearmonth(XmlObject v) {
        set_primitive(17, v);
    }

    protected void set_xmlgyear(XmlObject v) {
        set_primitive(18, v);
    }

    protected void set_xmlgmonthday(XmlObject v) {
        set_primitive(19, v);
    }

    protected void set_xmlgday(XmlObject v) {
        set_primitive(20, v);
    }

    protected void set_xmlgmonth(XmlObject v) {
        set_primitive(21, v);
    }

    private static boolean check(XmlObject v, SchemaType sType) {
        XmlObject[] vals = sType.getEnumerationValues();
        if (vals == null) {
            return true;
        }
        for (XmlObject val : vals) {
            if (val.valueEquals(v)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected boolean equal_to(XmlObject xmlobj) {
        return this._value.valueEquals(xmlobj);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected int value_hash_code() {
        return this._value.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    public void validate_simpleval(String lexical, ValidationContext ctx) {
        try {
            check_dated();
            if (this._value == null) {
                ctx.invalid(XmlErrorCodes.UNION, new Object[]{"'" + lexical + "' does not match any of the member types for " + QNameHelper.readable(schemaType())});
            } else {
                ((XmlObjectBase) this._value).validate_simpleval(lexical, ctx);
            }
        } catch (Exception e) {
            ctx.invalid(XmlErrorCodes.UNION, new Object[]{"'" + lexical + "' does not match any of the member types for " + QNameHelper.readable(schemaType())});
        }
    }
}
