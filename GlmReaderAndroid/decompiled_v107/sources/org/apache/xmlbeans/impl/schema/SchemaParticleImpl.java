package org.apache.xmlbeans.impl.schema;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.values.NamespaceContext;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Element;

/* loaded from: classes11.dex */
public class SchemaParticleImpl implements SchemaParticle {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final BigInteger _maxint = BigInteger.valueOf(2147483647L);
    private String _defaultText;
    private XmlValueRef _defaultValue;
    private String _documentation;
    private QNameSet _excludeNextSet;
    private int _intMaxOccurs;
    private int _intMinOccurs;
    private boolean _isDefault;
    private boolean _isDeterministic;
    private boolean _isFixed;
    private boolean _isImmutable;
    private boolean _isNillable;
    private boolean _isSkippable;
    private BigInteger _maxOccurs;
    private BigInteger _minOccurs;
    protected XmlObject _parseObject;
    private SchemaParticle[] _particleChildren;
    private int _particleType;
    private QName _qName;
    private QNameSet _startSet;
    private SchemaType.Ref _typeref;
    private Object _userData;
    private int _wildcardProcess;
    private QNameSet _wildcardSet;

    /* JADX INFO: Access modifiers changed from: protected */
    public void mutate() {
        if (this._isImmutable) {
            throw new IllegalStateException();
        }
    }

    public void setImmutable() {
        mutate();
        this._isImmutable = true;
    }

    public boolean hasTransitionRules() {
        return this._startSet != null;
    }

    public boolean hasTransitionNotes() {
        return this._excludeNextSet != null;
    }

    public void setTransitionRules(QNameSet start, boolean isSkippable) {
        this._startSet = start;
        this._isSkippable = isSkippable;
    }

    public void setTransitionNotes(QNameSet excludeNext, boolean isDeterministic) {
        this._excludeNextSet = excludeNext;
        this._isDeterministic = isDeterministic;
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public boolean canStartWithElement(QName name) {
        return name != null && this._startSet.contains(name);
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public QNameSet acceptedStartNames() {
        return this._startSet;
    }

    public QNameSet getExcludeNextSet() {
        return this._excludeNextSet;
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public boolean isSkippable() {
        return this._isSkippable;
    }

    public boolean isDeterministic() {
        return this._isDeterministic;
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public int getParticleType() {
        return this._particleType;
    }

    public void setParticleType(int pType) {
        mutate();
        this._particleType = pType;
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public boolean isSingleton() {
        return this._maxOccurs != null && this._maxOccurs.compareTo(BigInteger.ONE) == 0 && this._minOccurs.compareTo(BigInteger.ONE) == 0;
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public BigInteger getMinOccurs() {
        return this._minOccurs;
    }

    public void setMinOccurs(BigInteger min) {
        mutate();
        this._minOccurs = min;
        this._intMinOccurs = pegBigInteger(min);
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public int getIntMinOccurs() {
        return this._intMinOccurs;
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public BigInteger getMaxOccurs() {
        return this._maxOccurs;
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public int getIntMaxOccurs() {
        return this._intMaxOccurs;
    }

    public void setMaxOccurs(BigInteger max) {
        mutate();
        this._maxOccurs = max;
        this._intMaxOccurs = pegBigInteger(max);
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public SchemaParticle[] getParticleChildren() {
        if (this._particleChildren == null) {
            if (this._particleType == 1 || this._particleType == 3 || this._particleType == 2) {
                throw new AssertionError();
            }
            return null;
        }
        SchemaParticle[] result = new SchemaParticle[this._particleChildren.length];
        System.arraycopy(this._particleChildren, 0, result, 0, this._particleChildren.length);
        return result;
    }

    public void setParticleChildren(SchemaParticle[] children) {
        mutate();
        this._particleChildren = children == null ? null : (SchemaParticle[]) children.clone();
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public SchemaParticle getParticleChild(int i) {
        return this._particleChildren[i];
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public int countOfParticleChild() {
        if (this._particleChildren == null) {
            return 0;
        }
        return this._particleChildren.length;
    }

    public void setWildcardSet(QNameSet set) {
        mutate();
        this._wildcardSet = set;
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public QNameSet getWildcardSet() {
        return this._wildcardSet;
    }

    public void setWildcardProcess(int process) {
        mutate();
        this._wildcardProcess = process;
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public int getWildcardProcess() {
        return this._wildcardProcess;
    }

    private static int pegBigInteger(BigInteger bi) {
        if (bi == null) {
            return Integer.MAX_VALUE;
        }
        if (bi.signum() <= 0) {
            return 0;
        }
        if (bi.compareTo(_maxint) >= 0) {
            return Integer.MAX_VALUE;
        }
        return bi.intValue();
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public QName getName() {
        return this._qName;
    }

    public void setNameAndTypeRef(QName formname, SchemaType.Ref typeref) {
        mutate();
        this._qName = formname;
        this._typeref = typeref;
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

    public boolean isAttribute() {
        return false;
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public SchemaType getType() {
        if (this._typeref == null) {
            return null;
        }
        return this._typeref.get();
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public String getDefaultText() {
        return this._defaultText;
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public boolean isDefault() {
        return this._isDefault;
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public boolean isFixed() {
        return this._isFixed;
    }

    public void setDefault(String deftext, boolean isFixed, XmlObject parseObject) {
        mutate();
        this._defaultText = deftext;
        this._isDefault = deftext != null;
        this._isFixed = isFixed;
        this._parseObject = parseObject;
        this._documentation = parseDocumentation(this._parseObject);
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public boolean isNillable() {
        return this._isNillable;
    }

    public void setNillable(boolean nillable) {
        mutate();
        this._isNillable = nillable;
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public XmlAnySimpleType getDefaultValue() {
        if (this._defaultValue != null) {
            return this._defaultValue.get();
        }
        if (this._defaultText != null && XmlAnySimpleType.type.isAssignableFrom(getType())) {
            if (this._parseObject != null && XmlQName.type.isAssignableFrom(getType())) {
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
        mutate();
        this._defaultValue = defaultRef;
    }

    public Object getUserData() {
        return this._userData;
    }

    public void setUserData(Object data) {
        this._userData = data;
    }

    @Override // org.apache.xmlbeans.SchemaParticle
    public String getDocumentation() {
        return this._documentation;
    }

    private static String parseDocumentation(XmlObject parseObject) {
        try {
            if (parseObject instanceof Element) {
                Element e = (Element) parseObject;
                if (e.getAnnotation() != null) {
                    AnnotationDocument.Annotation a = e.getAnnotation();
                    if (a.getDocumentationArray() != null) {
                        DocumentationDocument.Documentation[] docArray = a.getDocumentationArray();
                        StringBuilder sb = new StringBuilder();
                        for (DocumentationDocument.Documentation documentation : docArray) {
                            XmlCursor c = documentation.newCursor();
                            try {
                                sb.append(c.getTextValue());
                                if (c != null) {
                                    c.close();
                                }
                            } finally {
                            }
                        }
                        return sb.toString();
                    }
                }
            }
            return "";
        } catch (Exception e2) {
            return "";
        }
    }
}
