package org.apache.xmlbeans.impl.values;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;

/* loaded from: classes11.dex */
public abstract class JavaStringHolderEx extends JavaStringHolder {
    private final SchemaType _schemaType;

    @Override // org.apache.xmlbeans.impl.values.JavaStringHolder, org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.XmlObject
    public SchemaType schemaType() {
        return this._schemaType;
    }

    public JavaStringHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        initComplexType(complex, false);
    }

    @Override // org.apache.xmlbeans.impl.values.JavaStringHolder, org.apache.xmlbeans.impl.values.XmlObjectBase
    protected int get_wscanon_rule() {
        return schemaType().getWhiteSpaceRule();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.values.JavaStringHolder, org.apache.xmlbeans.impl.values.XmlObjectBase
    public void set_text(String s) {
        if (_validateOnSet()) {
            validateLexical(s, this._schemaType, _voorVc);
        }
        super.set_text(s);
    }

    @Override // org.apache.xmlbeans.impl.values.JavaStringHolder, org.apache.xmlbeans.impl.values.XmlObjectBase
    protected boolean is_defaultable_ws(String v) {
        try {
            validateLexical(v, this._schemaType, _voorVc);
            return false;
        } catch (XmlValueOutOfRangeException e) {
            return true;
        }
    }

    public static void validateLexical(String v, SchemaType sType, ValidationContext context) {
        int m;
        int m2;
        int m3;
        if (!sType.matchPatternFacet(v)) {
            context.invalid(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{TypedValues.Custom.S_STRING, v, QNameHelper.readable(sType)});
            return;
        }
        XmlObject len = sType.getFacet(0);
        if (len != null && v.length() != (m3 = ((XmlObjectBase) len).getBigIntegerValue().intValue())) {
            context.invalid(XmlErrorCodes.DATATYPE_LENGTH_VALID$STRING, new Object[]{TypedValues.Custom.S_STRING, Integer.valueOf(v.length()), Integer.valueOf(m3), QNameHelper.readable(sType)});
            return;
        }
        XmlObject min = sType.getFacet(1);
        if (min != null && v.length() < (m2 = ((XmlObjectBase) min).getBigIntegerValue().intValue())) {
            context.invalid(XmlErrorCodes.DATATYPE_MIN_LENGTH_VALID$STRING, new Object[]{TypedValues.Custom.S_STRING, Integer.valueOf(v.length()), Integer.valueOf(m2), QNameHelper.readable(sType)});
            return;
        }
        XmlObject max = sType.getFacet(2);
        if (max != null && v.length() > (m = ((XmlObjectBase) max).getBigIntegerValue().intValue())) {
            context.invalid(XmlErrorCodes.DATATYPE_MAX_LENGTH_VALID$STRING, new Object[]{TypedValues.Custom.S_STRING, Integer.valueOf(v.length()), Integer.valueOf(m), QNameHelper.readable(sType)});
            return;
        }
        XmlAnySimpleType[] vals = sType.getEnumerationValues();
        if (vals != null) {
            for (XmlAnySimpleType val : vals) {
                if (v.equals(val.getStringValue())) {
                    return;
                }
            }
            context.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{TypedValues.Custom.S_STRING, v, QNameHelper.readable(sType)});
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    public void validate_simpleval(String lexical, ValidationContext ctx) {
        validateLexical(getStringValue(), schemaType(), ctx);
    }
}
