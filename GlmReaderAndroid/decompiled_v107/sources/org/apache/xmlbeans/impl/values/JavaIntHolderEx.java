package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.util.XsTypeConverter;

/* loaded from: classes11.dex */
public abstract class JavaIntHolderEx extends JavaIntHolder {
    private final SchemaType _schemaType;

    public JavaIntHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        initComplexType(complex, false);
    }

    @Override // org.apache.xmlbeans.impl.values.JavaIntHolder, org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.XmlObject
    public SchemaType schemaType() {
        return this._schemaType;
    }

    @Override // org.apache.xmlbeans.impl.values.JavaIntHolder, org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_text(String s) {
        try {
            int v = XsTypeConverter.lexInt(s);
            if (_validateOnSet()) {
                validateValue(v, this._schemaType, _voorVc);
                validateLexical(s, this._schemaType, _voorVc);
            }
            super.set_int(v);
        } catch (Exception e) {
            throw new XmlValueOutOfRangeException(XmlErrorCodes.INT, new Object[]{s});
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.values.JavaIntHolder, org.apache.xmlbeans.impl.values.XmlObjectBase
    public void set_int(int v) {
        if (_validateOnSet()) {
            validateValue(v, this._schemaType, _voorVc);
        }
        super.set_int(v);
    }

    public static void validateLexical(String v, SchemaType sType, ValidationContext context) {
        JavaDecimalHolder.validateLexical(v, context);
        if (sType.hasPatternFacet() && !sType.matchPatternFacet(v)) {
            context.invalid(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{XmlErrorCodes.INT, v, QNameHelper.readable(sType)});
        }
    }

    private static void validateValue(int v, SchemaType sType, ValidationContext context) {
        int m;
        int m2;
        int m3;
        int m4;
        XmlObject td = sType.getFacet(7);
        if (td != null) {
            String temp = Integer.toString(v);
            int len = temp.length();
            if (len > 0 && temp.charAt(0) == '-') {
                len--;
            }
            if (len > getIntValue(td)) {
                context.invalid(XmlErrorCodes.DATATYPE_TOTAL_DIGITS_VALID, new Object[]{Integer.valueOf(len), temp, Integer.valueOf(getIntValue(td)), QNameHelper.readable(sType)});
                return;
            }
        }
        XmlObject mine = sType.getFacet(3);
        if (mine != null && v <= (m4 = getIntValue(mine))) {
            context.invalid(XmlErrorCodes.DATATYPE_MIN_EXCLUSIVE_VALID, new Object[]{XmlErrorCodes.INT, Integer.valueOf(v), Integer.valueOf(m4), QNameHelper.readable(sType)});
            return;
        }
        XmlObject mini = sType.getFacet(4);
        if (mini != null && v < (m3 = getIntValue(mini))) {
            context.invalid(XmlErrorCodes.DATATYPE_MIN_INCLUSIVE_VALID, new Object[]{XmlErrorCodes.INT, Integer.valueOf(v), Integer.valueOf(m3), QNameHelper.readable(sType)});
            return;
        }
        XmlObject maxi = sType.getFacet(5);
        if (maxi != null && v > (m2 = getIntValue(maxi))) {
            context.invalid(XmlErrorCodes.DATATYPE_MAX_EXCLUSIVE_VALID, new Object[]{XmlErrorCodes.INT, Integer.valueOf(v), Integer.valueOf(m2), QNameHelper.readable(sType)});
            return;
        }
        XmlObject maxe = sType.getFacet(6);
        if (maxe != null && v >= (m = getIntValue(maxe))) {
            context.invalid(XmlErrorCodes.DATATYPE_MAX_EXCLUSIVE_VALID, new Object[]{XmlErrorCodes.INT, Integer.valueOf(v), Integer.valueOf(m), QNameHelper.readable(sType)});
            return;
        }
        XmlObject[] vals = sType.getEnumerationValues();
        if (vals != null) {
            for (XmlObject val : vals) {
                if (v == getIntValue(val)) {
                    return;
                }
            }
            context.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{XmlErrorCodes.INT, Integer.valueOf(v), QNameHelper.readable(sType)});
        }
    }

    private static int getIntValue(XmlObject o) {
        SchemaType s = o.schemaType();
        switch (s.getDecimalSize()) {
            case 64:
                return (int) ((XmlObjectBase) o).getLongValue();
            case 1000000:
                return ((XmlObjectBase) o).getBigIntegerValue().intValue();
            case SchemaType.SIZE_BIG_DECIMAL /* 1000001 */:
                return ((XmlObjectBase) o).getBigDecimalValue().intValue();
            default:
                return ((XmlObjectBase) o).getIntValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    public void validate_simpleval(String lexical, ValidationContext ctx) {
        validateLexical(lexical, schemaType(), ctx);
        validateValue(getIntValue(), schemaType(), ctx);
    }
}
