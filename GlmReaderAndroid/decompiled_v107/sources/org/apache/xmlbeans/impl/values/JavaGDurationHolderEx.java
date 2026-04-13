package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.GDurationSpecification;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;

/* loaded from: classes11.dex */
public abstract class JavaGDurationHolderEx extends XmlObjectBase {
    private final SchemaType _schemaType;
    GDuration _value;

    public JavaGDurationHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        initComplexType(complex, false);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.XmlObject
    public SchemaType schemaType() {
        return this._schemaType;
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_text(String s) {
        GDuration newVal;
        if (_validateOnSet()) {
            newVal = validateLexical(s, this._schemaType, _voorVc);
        } else {
            newVal = lex(s, _voorVc);
        }
        if (_validateOnSet() && newVal != null) {
            validateValue(newVal, this._schemaType, _voorVc);
        }
        this._value = newVal;
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_GDuration(GDurationSpecification v) {
        if (_validateOnSet()) {
            validateValue(v, this._schemaType, _voorVc);
        }
        if (v.isImmutable() && (v instanceof GDuration)) {
            this._value = (GDuration) v;
        } else {
            this._value = new GDuration(v);
        }
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected String compute_text(NamespaceManager nsm) {
        return this._value == null ? "" : this._value.toString();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_nil() {
        this._value = null;
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public GDuration getGDurationValue() {
        check_dated();
        if (this._value == null) {
            return null;
        }
        return this._value;
    }

    public static GDuration lex(String v, ValidationContext context) {
        try {
            GDuration duration = new GDuration(v);
            return duration;
        } catch (Exception e) {
            context.invalid("duration", new Object[]{v});
            return null;
        }
    }

    public static GDuration validateLexical(String v, SchemaType sType, ValidationContext context) {
        GDuration duration = lex(v, context);
        if (duration != null && sType.hasPatternFacet() && !sType.matchPatternFacet(v)) {
            context.invalid(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{"duration", v, QNameHelper.readable(sType)});
        }
        return duration;
    }

    public static void validateValue(GDurationSpecification v, SchemaType sType, ValidationContext context) {
        XmlObject x = sType.getFacet(3);
        if (x != null) {
            GDuration g = ((XmlObjectBase) x).getGDurationValue();
            if (v.compareToGDuration(g) <= 0) {
                context.invalid(XmlErrorCodes.DATATYPE_MIN_EXCLUSIVE_VALID, new Object[]{"duration", v, g, QNameHelper.readable(sType)});
            }
        }
        XmlObject x2 = sType.getFacet(4);
        if (x2 != null) {
            GDuration g2 = ((XmlObjectBase) x2).getGDurationValue();
            if (v.compareToGDuration(g2) < 0) {
                context.invalid(XmlErrorCodes.DATATYPE_MIN_INCLUSIVE_VALID, new Object[]{"duration", v, g2, QNameHelper.readable(sType)});
            }
        }
        XmlObject x3 = sType.getFacet(6);
        if (x3 != null) {
            GDuration g3 = ((XmlObjectBase) x3).getGDurationValue();
            if (v.compareToGDuration(g3) >= 0) {
                context.invalid(XmlErrorCodes.DATATYPE_MAX_EXCLUSIVE_VALID, new Object[]{"duration", v, g3, QNameHelper.readable(sType)});
            }
        }
        XmlObject x4 = sType.getFacet(5);
        if (x4 != null) {
            GDuration g4 = ((XmlObjectBase) x4).getGDurationValue();
            if (v.compareToGDuration(g4) > 0) {
                context.invalid(XmlErrorCodes.DATATYPE_MAX_INCLUSIVE_VALID, new Object[]{"duration", v, g4, QNameHelper.readable(sType)});
            }
        }
        XmlObject[] vals = sType.getEnumerationValues();
        if (vals != null) {
            for (XmlObject val : vals) {
                if (v.compareToGDuration(((XmlObjectBase) val).getGDurationValue()) == 0) {
                    return;
                }
            }
            context.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{"duration", v, QNameHelper.readable(sType)});
        }
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected int compare_to(XmlObject d) {
        return this._value.compareToGDuration(((XmlObjectBase) d).getGDurationValue());
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected boolean equal_to(XmlObject d) {
        return this._value.equals(((XmlObjectBase) d).getGDurationValue());
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected int value_hash_code() {
        return this._value.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    public void validate_simpleval(String lexical, ValidationContext ctx) {
        validateLexical(lexical, schemaType(), ctx);
        validateValue(getGDurationValue(), schemaType(), ctx);
    }
}
