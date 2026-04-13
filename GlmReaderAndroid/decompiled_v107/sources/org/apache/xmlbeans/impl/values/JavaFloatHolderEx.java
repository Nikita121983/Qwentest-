package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;

/* loaded from: classes11.dex */
public abstract class JavaFloatHolderEx extends JavaFloatHolder {
    private final SchemaType _schemaType;

    public JavaFloatHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        initComplexType(complex, false);
    }

    @Override // org.apache.xmlbeans.impl.values.JavaFloatHolder, org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.XmlObject
    public SchemaType schemaType() {
        return this._schemaType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.values.JavaFloatHolder, org.apache.xmlbeans.impl.values.XmlObjectBase
    public void set_float(float v) {
        if (_validateOnSet()) {
            validateValue(v, this._schemaType, _voorVc);
        }
        super.set_float(v);
    }

    public static float validateLexical(String v, SchemaType sType, ValidationContext context) {
        float f = JavaFloatHolder.validateLexical(v, context);
        if (!sType.matchPatternFacet(v)) {
            context.invalid(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{"float", v, QNameHelper.readable(sType)});
        }
        return f;
    }

    public static void validateValue(float v, SchemaType sType, ValidationContext context) {
        XmlObject x = sType.getFacet(3);
        if (x != null) {
            float f = ((XmlObjectBase) x).getFloatValue();
            if (compare(v, f) <= 0) {
                context.invalid(XmlErrorCodes.DATATYPE_MIN_EXCLUSIVE_VALID, new Object[]{"float", Float.valueOf(v), Float.valueOf(f), QNameHelper.readable(sType)});
            }
        }
        XmlObject x2 = sType.getFacet(4);
        if (x2 != null) {
            float f2 = ((XmlObjectBase) x2).getFloatValue();
            if (compare(v, f2) < 0) {
                context.invalid(XmlErrorCodes.DATATYPE_MIN_INCLUSIVE_VALID, new Object[]{"float", Float.valueOf(v), Float.valueOf(f2), QNameHelper.readable(sType)});
            }
        }
        XmlObject x3 = sType.getFacet(5);
        if (x3 != null) {
            float f3 = ((XmlObjectBase) x3).getFloatValue();
            if (compare(v, f3) > 0) {
                context.invalid(XmlErrorCodes.DATATYPE_MAX_INCLUSIVE_VALID, new Object[]{"float", Float.valueOf(v), Float.valueOf(f3), QNameHelper.readable(sType)});
            }
        }
        XmlObject x4 = sType.getFacet(6);
        if (x4 != null) {
            float f4 = ((XmlObjectBase) x4).getFloatValue();
            if (compare(v, f4) >= 0) {
                context.invalid(XmlErrorCodes.DATATYPE_MAX_EXCLUSIVE_VALID, new Object[]{"float", Float.valueOf(v), Float.valueOf(f4), QNameHelper.readable(sType)});
            }
        }
        XmlObject[] vals = sType.getEnumerationValues();
        if (vals != null) {
            for (XmlObject val : vals) {
                if (compare(v, ((XmlObjectBase) val).getFloatValue()) == 0) {
                    return;
                }
            }
            context.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{"float", Float.valueOf(v), QNameHelper.readable(sType)});
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    public void validate_simpleval(String lexical, ValidationContext ctx) {
        validateLexical(lexical, schemaType(), ctx);
        validateValue(getFloatValue(), schemaType(), ctx);
    }
}
