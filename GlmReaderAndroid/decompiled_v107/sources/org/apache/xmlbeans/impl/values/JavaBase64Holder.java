package org.apache.xmlbeans.impl.values;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;

/* loaded from: classes11.dex */
public abstract class JavaBase64Holder extends XmlObjectBase {
    protected static final MessageDigest md5;
    protected byte[] _value;
    protected boolean _hashcached = false;
    protected int hashcode = 0;

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.XmlObject
    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_BASE_64_BINARY;
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected String compute_text(NamespaceManager nsm) {
        return Base64.getEncoder().encodeToString(this._value);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_text(String s) {
        this._hashcached = false;
        if (_validateOnSet()) {
            this._value = validateLexical(s, schemaType(), _voorVc);
        } else {
            this._value = lex(s, _voorVc);
        }
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_nil() {
        this._hashcached = false;
        this._value = null;
    }

    public static byte[] lex(String v, ValidationContext c) {
        try {
            return Base64.getMimeDecoder().decode(v);
        } catch (IllegalArgumentException e) {
            c.invalid(XmlErrorCodes.BASE64BINARY, new Object[]{"not encoded properly"});
            return null;
        }
    }

    public static byte[] validateLexical(String v, SchemaType sType, ValidationContext context) {
        byte[] bytes = lex(v, context);
        if (bytes == null) {
            return null;
        }
        if (!sType.matchPatternFacet(v)) {
            context.invalid(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID$NO_VALUE, new Object[]{"base 64", QNameHelper.readable(sType)});
            return null;
        }
        return bytes;
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public byte[] getByteArrayValue() {
        check_dated();
        if (this._value == null) {
            return null;
        }
        byte[] result = new byte[this._value.length];
        System.arraycopy(this._value, 0, result, 0, this._value.length);
        return result;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    public void set_ByteArray(byte[] ba) {
        this._hashcached = false;
        this._value = new byte[ba.length];
        System.arraycopy(ba, 0, this._value, 0, ba.length);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected boolean equal_to(XmlObject i) {
        byte[] ival = ((XmlBase64Binary) i).getByteArrayValue();
        return Arrays.equals(this._value, ival);
    }

    static {
        try {
            md5 = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Cannot find MD5 hash Algorithm");
        }
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected int value_hash_code() {
        if (this._hashcached) {
            return this.hashcode;
        }
        this._hashcached = true;
        if (this._value == null) {
            this.hashcode = 0;
            return 0;
        }
        byte[] res = md5.digest(this._value);
        int i = (res[1] << UnionPtg.sid) | (res[0] << 24) | (res[2] << 8) | res[3];
        this.hashcode = i;
        return i;
    }
}
