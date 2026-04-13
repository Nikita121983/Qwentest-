package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;

/* loaded from: classes11.dex */
public abstract class JavaHexBinaryHolderEx extends JavaHexBinaryHolder {
    private final SchemaType _schemaType;

    @Override // org.apache.xmlbeans.impl.values.JavaHexBinaryHolder, org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.XmlObject
    public SchemaType schemaType() {
        return this._schemaType;
    }

    public JavaHexBinaryHolderEx(SchemaType type, boolean complex) {
        this._schemaType = type;
        initComplexType(complex, false);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected int get_wscanon_rule() {
        return schemaType().getWhiteSpaceRule();
    }

    @Override // org.apache.xmlbeans.impl.values.JavaHexBinaryHolder, org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_text(String s) {
        byte[] v;
        if (_validateOnSet()) {
            v = validateLexical(s, schemaType(), _voorVc);
        } else {
            v = lex(s, _voorVc);
        }
        if (_validateOnSet() && v != null) {
            validateValue(v, schemaType(), XmlObjectBase._voorVc);
        }
        if (v != null) {
            super.set_ByteArray(v);
        }
        this._value = v;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.values.JavaHexBinaryHolder, org.apache.xmlbeans.impl.values.XmlObjectBase
    public void set_ByteArray(byte[] v) {
        if (_validateOnSet()) {
            validateValue(v, schemaType(), _voorVc);
        }
        super.set_ByteArray(v);
    }

    public static void validateValue(byte[] v, SchemaType sType, ValidationContext context) {
        int i;
        int i2;
        int i3;
        XmlObject o = sType.getFacet(0);
        if (o != null && (i3 = ((XmlObjectBase) o).getBigIntegerValue().intValue()) != v.length) {
            context.invalid(XmlErrorCodes.DATATYPE_LENGTH_VALID$BINARY, new Object[]{XmlErrorCodes.HEXBINARY, Integer.valueOf(v.length), Integer.valueOf(i3), QNameHelper.readable(sType)});
        }
        XmlObject o2 = sType.getFacet(1);
        if (o2 != null && (i2 = ((XmlObjectBase) o2).getBigIntegerValue().intValue()) > v.length) {
            context.invalid(XmlErrorCodes.DATATYPE_MIN_LENGTH_VALID$BINARY, new Object[]{XmlErrorCodes.HEXBINARY, Integer.valueOf(v.length), Integer.valueOf(i2), QNameHelper.readable(sType)});
        }
        XmlObject o3 = sType.getFacet(2);
        if (o3 != null && (i = ((XmlObjectBase) o3).getBigIntegerValue().intValue()) < v.length) {
            context.invalid(XmlErrorCodes.DATATYPE_MAX_LENGTH_VALID$BINARY, new Object[]{XmlErrorCodes.HEXBINARY, Integer.valueOf(v.length), Integer.valueOf(i), QNameHelper.readable(sType)});
        }
        XmlObject[] vals = sType.getEnumerationValues();
        if (vals != null) {
            int i4 = 0;
            loop0: while (i4 < vals.length) {
                byte[] enumBytes = ((XmlObjectBase) vals[i4]).getByteArrayValue();
                if (enumBytes.length == v.length) {
                    for (int j = 0; j < enumBytes.length; j++) {
                        if (enumBytes[j] != v[j]) {
                            break;
                        }
                    }
                    break loop0;
                }
                i4++;
            }
            if (i4 >= vals.length) {
                context.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID$NO_VALUE, new Object[]{XmlErrorCodes.HEXBINARY, QNameHelper.readable(sType)});
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    public void validate_simpleval(String lexical, ValidationContext ctx) {
        validateLexical(lexical, schemaType(), ctx);
        validateValue(getByteArrayValue(), schemaType(), ctx);
    }
}
