package org.apache.xmlbeans.impl.schema;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaLocalAttribute;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.NamespaceContext;
import org.apache.xmlbeans.soap.SOAPArrayType;
import org.apache.xmlbeans.soap.SchemaWSDLArrayType;

/* loaded from: classes11.dex */
public class SchemaLocalAttributeImpl implements SchemaLocalAttribute, SchemaWSDLArrayType {
    private SchemaAnnotation _annotation;
    private String _defaultText;
    XmlValueRef _defaultValue;
    private boolean _isDefault;
    private boolean _isFixed;
    protected XmlObject _parseObject;
    private SchemaType.Ref _typeref;
    private int _use;
    private Object _userData;
    private SOAPArrayType _wsdlArrayType;
    private QName _xmlName;

    public void init(QName name, SchemaType.Ref typeref, int use, String deftext, XmlObject parseObject, XmlValueRef defvalue, boolean isFixed, SOAPArrayType wsdlArray, SchemaAnnotation ann, Object userData) {
        if (this._xmlName != null || this._typeref != null) {
            throw new IllegalStateException("Already initialized");
        }
        this._use = use;
        this._typeref = typeref;
        this._defaultText = deftext;
        this._parseObject = parseObject;
        this._defaultValue = defvalue;
        this._isDefault = deftext != null;
        this._isFixed = isFixed;
        this._xmlName = name;
        this._wsdlArrayType = wsdlArray;
        this._annotation = ann;
        this._userData = userData;
    }

    public boolean isTypeResolved() {
        return this._typeref != null;
    }

    public void resolveTypeRef(SchemaType.Ref typeref) {
        if (this._typeref != null) {
            throw new IllegalStateException();
        }
        this._typeref = typeref;
    }

    @Override // org.apache.xmlbeans.SchemaLocalAttribute
    public int getUse() {
        return this._use;
    }

    @Override // org.apache.xmlbeans.SchemaField
    public QName getName() {
        return this._xmlName;
    }

    @Override // org.apache.xmlbeans.SchemaField
    public String getDefaultText() {
        return this._defaultText;
    }

    @Override // org.apache.xmlbeans.SchemaField
    public boolean isDefault() {
        return this._isDefault;
    }

    @Override // org.apache.xmlbeans.SchemaField
    public boolean isFixed() {
        return this._isFixed;
    }

    @Override // org.apache.xmlbeans.SchemaField
    public boolean isAttribute() {
        return true;
    }

    @Override // org.apache.xmlbeans.SchemaAnnotated
    public SchemaAnnotation getAnnotation() {
        return this._annotation;
    }

    @Override // org.apache.xmlbeans.SchemaField
    public SchemaType getType() {
        return this._typeref.get();
    }

    public SchemaType.Ref getTypeRef() {
        return this._typeref;
    }

    @Override // org.apache.xmlbeans.SchemaField
    public BigInteger getMinOccurs() {
        return this._use == 3 ? BigInteger.ONE : BigInteger.ZERO;
    }

    @Override // org.apache.xmlbeans.SchemaField
    public BigInteger getMaxOccurs() {
        return this._use == 1 ? BigInteger.ZERO : BigInteger.ONE;
    }

    @Override // org.apache.xmlbeans.SchemaField
    public boolean isNillable() {
        return false;
    }

    @Override // org.apache.xmlbeans.soap.SchemaWSDLArrayType
    public SOAPArrayType getWSDLArrayType() {
        return this._wsdlArrayType;
    }

    @Override // org.apache.xmlbeans.SchemaField
    public XmlAnySimpleType getDefaultValue() {
        if (this._defaultValue != null) {
            return this._defaultValue.get();
        }
        if (this._defaultText != null && XmlAnySimpleType.type.isAssignableFrom(getType())) {
            if (this._parseObject != null) {
                try {
                    NamespaceContext.push(new NamespaceContext(this._parseObject));
                    return getType().newValue(this._defaultText);
                } finally {
                    NamespaceContext.pop();
                }
            }
            return getType().newValue(this._defaultText);
        }
        return null;
    }

    public void setDefaultValue(XmlValueRef defaultRef) {
        this._defaultValue = defaultRef;
    }

    @Override // org.apache.xmlbeans.SchemaField
    public Object getUserData() {
        return this._userData;
    }
}
