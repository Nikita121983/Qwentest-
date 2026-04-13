package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.util.XsTypeConverter;

/* loaded from: classes11.dex */
public abstract class JavaLongHolderEx extends JavaLongHolder {
    private final SchemaType _schemaType;

    public JavaLongHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        initComplexType(complex, false);
    }

    @Override // org.apache.xmlbeans.impl.values.JavaLongHolder, org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.XmlObject
    public SchemaType schemaType() {
        return this._schemaType;
    }

    @Override // org.apache.xmlbeans.impl.values.JavaLongHolder, org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_text(String s) {
        try {
            long v = XsTypeConverter.lexLong(s);
            if (_validateOnSet()) {
                validateValue(v, this._schemaType, _voorVc);
                validateLexical(s, this._schemaType, _voorVc);
            }
            super.set_long(v);
        } catch (Exception e) {
            throw new XmlValueOutOfRangeException(XmlErrorCodes.LONG, new Object[]{s});
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.values.JavaLongHolder, org.apache.xmlbeans.impl.values.XmlObjectBase
    public void set_long(long v) {
        if (_validateOnSet()) {
            validateValue(v, this._schemaType, _voorVc);
        }
        super.set_long(v);
    }

    public static void validateLexical(String v, SchemaType sType, ValidationContext context) {
        JavaDecimalHolder.validateLexical(v, context);
        if (sType.hasPatternFacet() && !sType.matchPatternFacet(v)) {
            context.invalid(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{XmlErrorCodes.LONG, v, QNameHelper.readable(sType)});
        }
    }

    private static void validateValue(long v, SchemaType sType, ValidationContext context) {
        XmlObject td = sType.getFacet(7);
        if (td != null) {
            long m = getLongValue(td);
            String temp = Long.toString(v);
            int len = temp.length();
            if (len > 0 && temp.charAt(0) == '-') {
                len--;
            }
            if (len > m) {
                context.invalid(XmlErrorCodes.DATATYPE_TOTAL_DIGITS_VALID, new Object[]{Integer.valueOf(len), temp, Long.valueOf(m), QNameHelper.readable(sType)});
                return;
            }
        }
        XmlObject mine = sType.getFacet(3);
        if (mine != null) {
            long m2 = getLongValue(mine);
            if (v <= m2) {
                context.invalid(XmlErrorCodes.DATATYPE_MIN_EXCLUSIVE_VALID, new Object[]{XmlErrorCodes.LONG, Long.valueOf(v), Long.valueOf(m2), QNameHelper.readable(sType)});
                return;
            }
        }
        XmlObject mini = sType.getFacet(4);
        if (mini != null) {
            long m3 = getLongValue(mini);
            if (v < m3) {
                context.invalid(XmlErrorCodes.DATATYPE_MIN_INCLUSIVE_VALID, new Object[]{XmlErrorCodes.LONG, Long.valueOf(v), Long.valueOf(m3), QNameHelper.readable(sType)});
                return;
            }
        }
        XmlObject maxi = sType.getFacet(5);
        if (maxi != null) {
            long m4 = getLongValue(maxi);
            if (v > m4) {
                context.invalid(XmlErrorCodes.DATATYPE_MAX_INCLUSIVE_VALID, new Object[]{XmlErrorCodes.LONG, Long.valueOf(v), Long.valueOf(m4), QNameHelper.readable(sType)});
                return;
            }
        }
        XmlObject maxe = sType.getFacet(6);
        if (maxe != null) {
            long m5 = getLongValue(maxe);
            if (v >= m5) {
                context.invalid(XmlErrorCodes.DATATYPE_MAX_EXCLUSIVE_VALID, new Object[]{XmlErrorCodes.LONG, Long.valueOf(v), Long.valueOf(m5), QNameHelper.readable(sType)});
                return;
            }
        }
        XmlObject[] vals = sType.getEnumerationValues();
        if (vals != null) {
            for (XmlObject val : vals) {
                if (v == getLongValue(val)) {
                    return;
                }
            }
            context.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{XmlErrorCodes.LONG, Long.valueOf(v), QNameHelper.readable(sType)});
        }
    }

    private static long getLongValue(XmlObject o) {
        SchemaType s = o.schemaType();
        switch (s.getDecimalSize()) {
            case 64:
                return ((XmlObjectBase) o).getLongValue();
            case 1000000:
                return ((XmlObjectBase) o).getBigIntegerValue().longValue();
            case SchemaType.SIZE_BIG_DECIMAL /* 1000001 */:
                return ((XmlObjectBase) o).getBigDecimalValue().longValue();
            default:
                throw new IllegalStateException("Bad facet type: " + s);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    public void validate_simpleval(String lexical, ValidationContext ctx) {
        validateLexical(lexical, schemaType(), ctx);
        validateValue(getLongValue(), schemaType(), ctx);
    }
}
