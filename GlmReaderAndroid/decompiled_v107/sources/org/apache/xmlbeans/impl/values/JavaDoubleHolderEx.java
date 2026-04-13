package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;

/* loaded from: classes11.dex */
public abstract class JavaDoubleHolderEx extends JavaDoubleHolder {
    private final SchemaType _schemaType;

    public JavaDoubleHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        initComplexType(complex, false);
    }

    @Override // org.apache.xmlbeans.impl.values.JavaDoubleHolder, org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.XmlObject
    public SchemaType schemaType() {
        return this._schemaType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.values.JavaDoubleHolder, org.apache.xmlbeans.impl.values.XmlObjectBase
    public void set_double(double v) {
        if (_validateOnSet()) {
            validateValue(v, this._schemaType, _voorVc);
        }
        super.set_double(v);
    }

    public static double validateLexical(String v, SchemaType sType, ValidationContext context) {
        double d = JavaDoubleHolder.validateLexical(v, context);
        if (!sType.matchPatternFacet(v)) {
            context.invalid(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{XmlErrorCodes.DOUBLE, v, QNameHelper.readable(sType)});
        }
        return d;
    }

    public static void validateValue(double v, SchemaType sType, ValidationContext context) {
        XmlObject x = sType.getFacet(3);
        if (x != null) {
            double d = ((XmlObjectBase) x).getDoubleValue();
            if (compare(v, d) <= 0) {
                context.invalid(XmlErrorCodes.DATATYPE_MIN_EXCLUSIVE_VALID, new Object[]{XmlErrorCodes.DOUBLE, Double.valueOf(v), Double.valueOf(d), QNameHelper.readable(sType)});
            }
        }
        XmlObject x2 = sType.getFacet(4);
        if (x2 != null) {
            double d2 = ((XmlObjectBase) x2).getDoubleValue();
            if (compare(v, d2) < 0) {
                context.invalid(XmlErrorCodes.DATATYPE_MIN_INCLUSIVE_VALID, new Object[]{XmlErrorCodes.DOUBLE, Double.valueOf(v), Double.valueOf(d2), QNameHelper.readable(sType)});
            }
        }
        XmlObject x3 = sType.getFacet(5);
        if (x3 != null) {
            double d3 = ((XmlObjectBase) x3).getDoubleValue();
            if (compare(v, d3) > 0) {
                context.invalid(XmlErrorCodes.DATATYPE_MAX_INCLUSIVE_VALID, new Object[]{XmlErrorCodes.DOUBLE, Double.valueOf(v), Double.valueOf(d3), QNameHelper.readable(sType)});
            }
        }
        XmlObject x4 = sType.getFacet(6);
        if (x4 != null) {
            double d4 = ((XmlObjectBase) x4).getDoubleValue();
            if (compare(v, d4) >= 0) {
                context.invalid(XmlErrorCodes.DATATYPE_MAX_EXCLUSIVE_VALID, new Object[]{XmlErrorCodes.DOUBLE, Double.valueOf(v), Double.valueOf(d4), QNameHelper.readable(sType)});
            }
        }
        XmlObject[] vals = sType.getEnumerationValues();
        if (vals != null) {
            for (XmlObject val : vals) {
                if (compare(v, ((XmlObjectBase) val).getDoubleValue()) == 0) {
                    return;
                }
            }
            context.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{XmlErrorCodes.DOUBLE, Double.valueOf(v), QNameHelper.readable(sType)});
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    public void validate_simpleval(String lexical, ValidationContext ctx) {
        validateLexical(lexical, schemaType(), ctx);
        validateValue(getDoubleValue(), schemaType(), ctx);
    }
}
