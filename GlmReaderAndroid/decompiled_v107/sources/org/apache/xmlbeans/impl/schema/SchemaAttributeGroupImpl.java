package org.apache.xmlbeans.impl.schema;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlObject;

/* loaded from: classes11.dex */
public class SchemaAttributeGroupImpl implements SchemaAttributeGroup {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private SchemaAnnotation _annotation;
    private boolean _chameleon;
    private SchemaContainer _container;
    private String _filename;
    private String _formDefault;
    private QName _name;
    private XmlObject _parseObject;
    private String _parseTNS;
    private boolean _redefinition;
    private SchemaAttributeGroup.Ref _selfref = new SchemaAttributeGroup.Ref(this);
    private Object _userData;

    public SchemaAttributeGroupImpl(SchemaContainer container) {
        this._container = container;
    }

    public SchemaAttributeGroupImpl(SchemaContainer container, QName name) {
        this._container = container;
        this._name = name;
    }

    public void init(QName name, String targetNamespace, boolean chameleon, String formDefault, boolean redefinition, XmlObject x, SchemaAnnotation a, Object userData) {
        if (this._name != null && !name.equals(this._name)) {
            throw new AssertionError();
        }
        this._name = name;
        this._parseTNS = targetNamespace;
        this._chameleon = chameleon;
        this._formDefault = formDefault;
        this._redefinition = redefinition;
        this._parseObject = x;
        this._annotation = a;
        this._userData = userData;
    }

    @Override // org.apache.xmlbeans.SchemaComponent
    public SchemaTypeSystem getTypeSystem() {
        return this._container.getTypeSystem();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaContainer getContainer() {
        return this._container;
    }

    @Override // org.apache.xmlbeans.SchemaAttributeGroup, org.apache.xmlbeans.SchemaComponent
    public int getComponentType() {
        return 4;
    }

    public void setFilename(String filename) {
        this._filename = filename;
    }

    @Override // org.apache.xmlbeans.SchemaComponent
    public String getSourceName() {
        return this._filename;
    }

    @Override // org.apache.xmlbeans.SchemaAttributeGroup, org.apache.xmlbeans.SchemaComponent
    public QName getName() {
        return this._name;
    }

    public XmlObject getParseObject() {
        return this._parseObject;
    }

    public String getTargetNamespace() {
        return this._parseTNS;
    }

    public String getChameleonNamespace() {
        if (this._chameleon) {
            return this._parseTNS;
        }
        return null;
    }

    public String getFormDefault() {
        return this._formDefault;
    }

    @Override // org.apache.xmlbeans.SchemaAnnotated
    public SchemaAnnotation getAnnotation() {
        return this._annotation;
    }

    public SchemaAttributeGroup.Ref getRef() {
        return this._selfref;
    }

    @Override // org.apache.xmlbeans.SchemaComponent
    public SchemaComponent.Ref getComponentRef() {
        return getRef();
    }

    public boolean isRedefinition() {
        return this._redefinition;
    }

    @Override // org.apache.xmlbeans.SchemaAttributeGroup
    public Object getUserData() {
        return this._userData;
    }
}
