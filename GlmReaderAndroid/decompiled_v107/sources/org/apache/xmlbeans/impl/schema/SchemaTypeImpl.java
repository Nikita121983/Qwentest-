package org.apache.xmlbeans.impl.schema;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.InterfaceExtension;
import org.apache.xmlbeans.PrePostExtension;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.QNameSetBuilder;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaLocalAttribute;
import org.apache.xmlbeans.SchemaLocalElement;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaStringEnumEntry;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeElementSequencer;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.XBeanDebug;
import org.apache.xmlbeans.impl.regex.RegularExpression;
import org.apache.xmlbeans.impl.util.ExceptionUtil;
import org.apache.xmlbeans.impl.values.StringEnumValue;
import org.apache.xmlbeans.impl.values.TypeStoreUser;
import org.apache.xmlbeans.impl.values.TypeStoreUserFactory;
import org.apache.xmlbeans.impl.values.XmlAnySimpleTypeImpl;
import org.apache.xmlbeans.impl.values.XmlAnySimpleTypeRestriction;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;
import org.apache.xmlbeans.impl.values.XmlAnyUriImpl;
import org.apache.xmlbeans.impl.values.XmlAnyUriRestriction;
import org.apache.xmlbeans.impl.values.XmlBase64BinaryImpl;
import org.apache.xmlbeans.impl.values.XmlBase64BinaryRestriction;
import org.apache.xmlbeans.impl.values.XmlBooleanImpl;
import org.apache.xmlbeans.impl.values.XmlBooleanRestriction;
import org.apache.xmlbeans.impl.values.XmlByteImpl;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.values.XmlDateImpl;
import org.apache.xmlbeans.impl.values.XmlDateTimeImpl;
import org.apache.xmlbeans.impl.values.XmlDecimalImpl;
import org.apache.xmlbeans.impl.values.XmlDecimalRestriction;
import org.apache.xmlbeans.impl.values.XmlDoubleImpl;
import org.apache.xmlbeans.impl.values.XmlDoubleRestriction;
import org.apache.xmlbeans.impl.values.XmlDurationImpl;
import org.apache.xmlbeans.impl.values.XmlEntitiesImpl;
import org.apache.xmlbeans.impl.values.XmlEntityImpl;
import org.apache.xmlbeans.impl.values.XmlFloatImpl;
import org.apache.xmlbeans.impl.values.XmlFloatRestriction;
import org.apache.xmlbeans.impl.values.XmlGDayImpl;
import org.apache.xmlbeans.impl.values.XmlGMonthDayImpl;
import org.apache.xmlbeans.impl.values.XmlGMonthImpl;
import org.apache.xmlbeans.impl.values.XmlGYearImpl;
import org.apache.xmlbeans.impl.values.XmlGYearMonthImpl;
import org.apache.xmlbeans.impl.values.XmlHexBinaryImpl;
import org.apache.xmlbeans.impl.values.XmlHexBinaryRestriction;
import org.apache.xmlbeans.impl.values.XmlIdImpl;
import org.apache.xmlbeans.impl.values.XmlIdRefImpl;
import org.apache.xmlbeans.impl.values.XmlIdRefsImpl;
import org.apache.xmlbeans.impl.values.XmlIntImpl;
import org.apache.xmlbeans.impl.values.XmlIntRestriction;
import org.apache.xmlbeans.impl.values.XmlIntegerImpl;
import org.apache.xmlbeans.impl.values.XmlIntegerRestriction;
import org.apache.xmlbeans.impl.values.XmlLanguageImpl;
import org.apache.xmlbeans.impl.values.XmlListImpl;
import org.apache.xmlbeans.impl.values.XmlLongImpl;
import org.apache.xmlbeans.impl.values.XmlLongRestriction;
import org.apache.xmlbeans.impl.values.XmlNCNameImpl;
import org.apache.xmlbeans.impl.values.XmlNameImpl;
import org.apache.xmlbeans.impl.values.XmlNegativeIntegerImpl;
import org.apache.xmlbeans.impl.values.XmlNmTokenImpl;
import org.apache.xmlbeans.impl.values.XmlNmTokensImpl;
import org.apache.xmlbeans.impl.values.XmlNonNegativeIntegerImpl;
import org.apache.xmlbeans.impl.values.XmlNonPositiveIntegerImpl;
import org.apache.xmlbeans.impl.values.XmlNormalizedStringImpl;
import org.apache.xmlbeans.impl.values.XmlNotationImpl;
import org.apache.xmlbeans.impl.values.XmlNotationRestriction;
import org.apache.xmlbeans.impl.values.XmlObjectBase;
import org.apache.xmlbeans.impl.values.XmlPositiveIntegerImpl;
import org.apache.xmlbeans.impl.values.XmlQNameImpl;
import org.apache.xmlbeans.impl.values.XmlQNameRestriction;
import org.apache.xmlbeans.impl.values.XmlShortImpl;
import org.apache.xmlbeans.impl.values.XmlStringEnumeration;
import org.apache.xmlbeans.impl.values.XmlStringImpl;
import org.apache.xmlbeans.impl.values.XmlStringRestriction;
import org.apache.xmlbeans.impl.values.XmlTimeImpl;
import org.apache.xmlbeans.impl.values.XmlTokenImpl;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.apache.xmlbeans.impl.values.XmlUnsignedByteImpl;
import org.apache.xmlbeans.impl.values.XmlUnsignedIntImpl;
import org.apache.xmlbeans.impl.values.XmlUnsignedLongImpl;
import org.apache.xmlbeans.impl.values.XmlUnsignedShortImpl;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Element;

/* loaded from: classes11.dex */
public final class SchemaTypeImpl implements SchemaType, TypeStoreUserFactory {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int JAVAIZED = 6;
    private static final int JAVAIZING = 5;
    private static final SchemaProperty[] NO_PROPERTIES = new SchemaProperty[0];
    private static final int RESOLVED = 4;
    private static final int RESOLVED_SGS = 2;
    private static final int RESOLVING = 3;
    private static final int RESOLVING_SGS = 1;
    private static final int UNRESOLVED = 0;
    private boolean _abs;
    private SchemaAnnotation _annotation;
    private SchemaType.Ref[] _anonymousTyperefs;
    private int _anonymousUnionMemberOrdinal;
    private String _attFormDefault;
    private volatile Map<SchemaLocalAttribute, Integer> _attrToIndexMap;
    private SchemaAttributeModel _attributeModel;
    private int _baseDepth;
    private SchemaType.Ref _baseEnumTyperef;
    private SchemaType.Ref _baseTyperef;
    private boolean _blockExt;
    private boolean _blockRest;
    private int _builtinTypeCode;
    private boolean _chameleon;
    private int _complexTypeVariety;
    private SchemaContainer _container;
    private volatile SchemaField _containerField;
    private volatile int _containerFieldCode;
    private volatile int _containerFieldIndex;
    private volatile SchemaComponent.Ref _containerFieldRef;
    private SchemaType.Ref _contentBasedOnTyperef;
    private SchemaParticle _contentModel;
    private int _decimalSize;
    private int _derivationType;
    private String _documentation;
    private String _elemFormDefault;
    private volatile Map<SchemaLocalElement, Integer> _eltToIndexMap;
    private XmlValueRef[] _enumerationValues;
    private XmlValueRef[] _facetArray;
    private String _filename;
    private boolean _finalExt;
    private boolean _finalList;
    private boolean _finalRest;
    private boolean _finalUnion;
    private boolean[] _fixedFacetArray;
    private String _fullJavaImplName;
    private String _fullJavaName;
    private volatile QName[] _groupReferenceContext;
    private boolean _hasAllContent;
    private boolean _hasPatterns;
    private boolean _hasWildcardAttributes;
    private boolean _hasWildcardElements;
    private volatile boolean _implNotAvailable;
    private InterfaceExtension[] _interfaces;
    private boolean _isAttributeType;
    private boolean _isBounded;
    private boolean _isCompiled;
    private boolean _isDocumentType;
    private boolean _isFinite;
    private boolean _isNumeric;
    private boolean _isSimpleType;
    private boolean _isUnionOfLists;
    private volatile Class<? extends XmlObject> _javaClass;
    private volatile Class<? extends StringEnumAbstractBase> _javaEnumClass;
    private volatile Class<? extends XmlObjectBase> _javaImplClass;
    private volatile Constructor<? extends XmlObjectBase> _javaImplConstructor;
    private volatile Constructor<? extends XmlObjectBase> _javaImplConstructor2;
    private SchemaType.Ref _listItemTyperef;
    private volatile List<StringEnumAbstractBase> _listOfStringEnum;
    private volatile SchemaLocalElement[] _localElts;
    private volatile Map<String, StringEnumAbstractBase> _lookupStringEnum;
    private volatile Map<String, SchemaStringEnumEntry> _lookupStringEnumEntry;
    private QName _name;
    private boolean _orderSensitive;
    private int _ordered;
    private SchemaType.Ref _outerSchemaTypeRef;
    private XmlObject _parseObject;
    private String _parseTNS;
    private RegularExpression[] _patterns;
    private PrePostExtension _prepost;
    private SchemaType.Ref _primitiveTypeRef;
    private Map<QName, SchemaProperty> _propertyModelByAttributeName;
    private Map<QName, SchemaProperty> _propertyModelByElementName;
    private boolean _redefinition;
    private int _resolvePhase;
    private QName _sg;
    private String _shortJavaImplName;
    private String _shortJavaName;
    private int _simpleTypeVariety;
    private boolean _stringEnumEnsured;
    private SchemaStringEnumEntry[] _stringEnumEntries;
    private QNameSet _typedWildcardAttributes;
    private QNameSet _typedWildcardElements;
    private volatile SchemaType _unionCommonBaseType;
    private volatile SchemaType[] _unionConstituentTypes;
    private SchemaType.Ref[] _unionMemberTyperefs;
    private volatile SchemaType[] _unionSubTypes;
    private volatile boolean _unloaded;
    private volatile Object _userData;
    private String _userTypeHandler;
    private String _userTypeName;
    private int _whiteSpaceRule;
    private final Object[] _ctrArgs = {this};
    private Set<QName> _validSubstitutions = Collections.emptySet();
    private final List<QName> _sgMembers = new ArrayList();
    private final SchemaType.Ref _selfref = new SchemaType.Ref(this);

    public boolean isUnloaded() {
        return this._unloaded;
    }

    public void finishLoading() {
        this._unloaded = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaTypeImpl(SchemaContainer container) {
        this._container = container;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaTypeImpl(SchemaContainer container, boolean unloaded) {
        this._container = container;
        this._unloaded = unloaded;
        if (unloaded) {
            finishQuick();
        }
    }

    public boolean isSGResolved() {
        return this._resolvePhase >= 2;
    }

    public boolean isSGResolving() {
        return this._resolvePhase >= 1;
    }

    public boolean isResolved() {
        return this._resolvePhase >= 4;
    }

    public boolean isResolving() {
        return this._resolvePhase == 3;
    }

    public boolean isUnjavaized() {
        return this._resolvePhase < 6;
    }

    public boolean isJavaized() {
        return this._resolvePhase == 6;
    }

    public void startResolvingSGs() {
        if (this._resolvePhase != 0) {
            throw new IllegalStateException();
        }
        this._resolvePhase = 1;
    }

    public void finishResolvingSGs() {
        if (this._resolvePhase != 1) {
            throw new IllegalStateException();
        }
        this._resolvePhase = 2;
    }

    public void startResolving() {
        if ((this._isDocumentType && this._resolvePhase != 2) || (!this._isDocumentType && this._resolvePhase != 0)) {
            throw new IllegalStateException();
        }
        this._resolvePhase = 3;
    }

    public void finishResolving() {
        if (this._resolvePhase != 3) {
            throw new IllegalStateException();
        }
        this._resolvePhase = 4;
    }

    public void startJavaizing() {
        if (this._resolvePhase != 4) {
            throw new IllegalStateException();
        }
        this._resolvePhase = 5;
    }

    public void finishJavaizing() {
        if (this._resolvePhase != 5) {
            throw new IllegalStateException();
        }
        this._resolvePhase = 6;
    }

    private void finishQuick() {
        this._resolvePhase = 6;
    }

    private void assertUnresolved() {
        if (this._resolvePhase != 0 && !this._unloaded) {
            throw new IllegalStateException();
        }
    }

    private void assertSGResolving() {
        if (this._resolvePhase != 1 && !this._unloaded) {
            throw new IllegalStateException();
        }
    }

    private void assertSGResolved() {
        if (this._resolvePhase != 2 && !this._unloaded) {
            throw new IllegalStateException();
        }
    }

    private void assertResolving() {
        if (this._resolvePhase != 3 && !this._unloaded) {
            throw new IllegalStateException();
        }
    }

    private void assertResolved() {
        if (this._resolvePhase != 4 && !this._unloaded) {
            throw new IllegalStateException();
        }
    }

    private void assertJavaizing() {
        if (this._resolvePhase != 5 && !this._unloaded) {
            throw new IllegalStateException();
        }
    }

    @Override // org.apache.xmlbeans.SchemaType, org.apache.xmlbeans.SchemaComponent
    public QName getName() {
        return this._name;
    }

    public void setName(QName name) {
        assertUnresolved();
        this._name = name;
    }

    @Override // org.apache.xmlbeans.SchemaComponent
    public String getSourceName() {
        if (this._filename != null) {
            return this._filename;
        }
        if (getOuterType() != null) {
            return getOuterType().getSourceName();
        }
        SchemaField field = getContainerField();
        if (field != null) {
            if (field instanceof SchemaGlobalElement) {
                return ((SchemaGlobalElement) field).getSourceName();
            }
            if (field instanceof SchemaGlobalAttribute) {
                return ((SchemaGlobalAttribute) field).getSourceName();
            }
            return null;
        }
        return null;
    }

    public void setFilename(String filename) {
        assertUnresolved();
        this._filename = filename;
    }

    @Override // org.apache.xmlbeans.SchemaComponent
    public int getComponentType() {
        return 0;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isAnonymousType() {
        return this._name == null;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isDocumentType() {
        return this._isDocumentType;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isAttributeType() {
        return this._isAttributeType;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public QName getDocumentElementName() {
        SchemaParticle sp;
        if (this._isDocumentType && (sp = getContentModel()) != null) {
            return sp.getName();
        }
        return null;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public QName getAttributeTypeAttributeName() {
        SchemaAttributeModel sam;
        SchemaLocalAttribute[] slaArray;
        if (this._isAttributeType && (sam = getAttributeModel()) != null && (slaArray = sam.getAttributes()) != null && slaArray.length > 0) {
            SchemaLocalAttribute sla = slaArray[0];
            return sla.getName();
        }
        return null;
    }

    public void setAnnotation(SchemaAnnotation ann) {
        assertUnresolved();
        this._annotation = ann;
    }

    @Override // org.apache.xmlbeans.SchemaAnnotated
    public SchemaAnnotation getAnnotation() {
        return this._annotation;
    }

    public void setDocumentType(boolean isDocument) {
        assertUnresolved();
        this._isDocumentType = isDocument;
    }

    public void setAttributeType(boolean isAttribute) {
        assertUnresolved();
        this._isAttributeType = isAttribute;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public int getContentType() {
        return this._complexTypeVariety;
    }

    public void setComplexTypeVariety(int complexTypeVariety) {
        assertResolving();
        this._complexTypeVariety = complexTypeVariety;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.xmlbeans.SchemaType
    public SchemaTypeElementSequencer getElementSequencer() {
        SchemaTypeVisitorImpl schemaTypeVisitorImpl = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        if (this._complexTypeVariety == 0) {
            return new SequencerImpl(schemaTypeVisitorImpl);
        }
        return new SequencerImpl(new SchemaTypeVisitorImpl(this._contentModel));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAbstractFinal(boolean abs, boolean finalExt, boolean finalRest, boolean finalList, boolean finalUnion) {
        assertResolving();
        this._abs = abs;
        this._finalExt = finalExt;
        this._finalRest = finalRest;
        this._finalList = finalList;
        this._finalUnion = finalUnion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSimpleFinal(boolean finalRest, boolean finalList, boolean finalUnion) {
        assertResolving();
        this._finalRest = finalRest;
        this._finalList = finalList;
        this._finalUnion = finalUnion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBlock(boolean blockExt, boolean blockRest) {
        assertResolving();
        this._blockExt = blockExt;
        this._blockRest = blockRest;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean blockRestriction() {
        return this._blockRest;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean blockExtension() {
        return this._blockExt;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isAbstract() {
        return this._abs;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean finalExtension() {
        return this._finalExt;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean finalRestriction() {
        return this._finalRest;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean finalList() {
        return this._finalList;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean finalUnion() {
        return this._finalUnion;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public synchronized SchemaField getContainerField() {
        if (this._containerFieldCode != -1) {
            SchemaType outer = getOuterType();
            if (this._containerFieldCode == 0) {
                this._containerField = this._containerFieldRef == null ? null : (SchemaField) this._containerFieldRef.getComponent();
            } else if (this._containerFieldCode == 1) {
                if (outer == null) {
                    throw new AssertionError();
                }
                this._containerField = outer.getAttributeModel().getAttributes()[this._containerFieldIndex];
            } else {
                if (outer == null) {
                    throw new AssertionError();
                }
                this._containerField = ((SchemaTypeImpl) outer).getLocalElementByIndex(this._containerFieldIndex);
            }
            this._containerFieldCode = -1;
        }
        return this._containerField;
    }

    public void setContainerField(SchemaField field) {
        assertUnresolved();
        this._containerField = field;
        this._containerFieldCode = -1;
    }

    public void setContainerFieldRef(SchemaComponent.Ref ref) {
        assertUnresolved();
        this._containerFieldRef = ref;
        this._containerFieldCode = 0;
    }

    public void setContainerFieldIndex(short code, int index) {
        assertUnresolved();
        this._containerFieldCode = code;
        this._containerFieldIndex = index;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setGroupReferenceContext(QName[] groupNames) {
        assertUnresolved();
        this._groupReferenceContext = groupNames;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public QName[] getGroupReferenceContext() {
        return this._groupReferenceContext;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaType getOuterType() {
        if (this._outerSchemaTypeRef == null) {
            return null;
        }
        return this._outerSchemaTypeRef.get();
    }

    public void setOuterSchemaTypeRef(SchemaType.Ref typeref) {
        assertUnresolved();
        this._outerSchemaTypeRef = typeref;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isCompiled() {
        return this._isCompiled;
    }

    public void setCompiled(boolean f) {
        assertJavaizing();
        this._isCompiled = f;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isSkippedAnonymousType() {
        SchemaType outerType = getOuterType();
        return outerType != null && (outerType.getBaseType() == this || outerType.getContentBasedOnType() == this);
    }

    @Override // org.apache.xmlbeans.SchemaType
    public String getShortJavaName() {
        return this._shortJavaName;
    }

    public void setShortJavaName(String name) {
        assertResolved();
        this._shortJavaName = name;
        SchemaType outer = this._outerSchemaTypeRef.get();
        while (outer.getFullJavaName() == null) {
            outer = outer.getOuterType();
        }
        this._fullJavaName = outer.getFullJavaName() + "$" + this._shortJavaName;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public String getFullJavaName() {
        return this._fullJavaName;
    }

    public void setFullJavaName(String name) {
        assertResolved();
        this._fullJavaName = name;
        int index = Math.max(this._fullJavaName.lastIndexOf(36), this._fullJavaName.lastIndexOf(46)) + 1;
        this._shortJavaName = this._fullJavaName.substring(index);
    }

    public void setShortJavaImplName(String name) {
        assertResolved();
        this._shortJavaImplName = name;
        SchemaType outer = this._outerSchemaTypeRef.get();
        while (outer.getFullJavaImplName() == null) {
            outer = outer.getOuterType();
        }
        this._fullJavaImplName = outer.getFullJavaImplName() + "$" + this._shortJavaImplName;
    }

    public void setFullJavaImplName(String name) {
        assertResolved();
        this._fullJavaImplName = name;
        int index = Math.max(this._fullJavaImplName.lastIndexOf(36), this._fullJavaImplName.lastIndexOf(46)) + 1;
        this._shortJavaImplName = this._fullJavaImplName.substring(index);
    }

    @Override // org.apache.xmlbeans.SchemaType
    public String getFullJavaImplName() {
        return this._fullJavaImplName;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public String getShortJavaImplName() {
        return this._shortJavaImplName;
    }

    public String getUserTypeName() {
        return this._userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this._userTypeName = userTypeName;
    }

    public String getUserTypeHandlerName() {
        return this._userTypeHandler;
    }

    public void setUserTypeHandlerName(String typeHandler) {
        this._userTypeHandler = typeHandler;
    }

    public void setInterfaceExtensions(InterfaceExtension[] interfaces) {
        assertResolved();
        this._interfaces = interfaces == null ? null : (InterfaceExtension[]) interfaces.clone();
    }

    public InterfaceExtension[] getInterfaceExtensions() {
        return this._interfaces;
    }

    public void setPrePostExtension(PrePostExtension prepost) {
        assertResolved();
        this._prepost = prepost;
    }

    public PrePostExtension getPrePostExtension() {
        return this._prepost;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public Object getUserData() {
        return this._userData;
    }

    public void setUserData(Object data) {
        this._userData = data;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaContainer getContainer() {
        return this._container;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setContainer(SchemaContainer container) {
        this._container = container;
    }

    @Override // org.apache.xmlbeans.SchemaType, org.apache.xmlbeans.SchemaComponent
    public SchemaTypeSystem getTypeSystem() {
        return this._container.getTypeSystem();
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaParticle getContentModel() {
        return this._contentModel;
    }

    private static void buildEltList(List<SchemaLocalElement> eltList, SchemaParticle contentModel) {
        if (contentModel == null) {
            return;
        }
        switch (contentModel.getParticleType()) {
            case 1:
            case 2:
            case 3:
                for (int i = 0; i < contentModel.countOfParticleChild(); i++) {
                    buildEltList(eltList, contentModel.getParticleChild(i));
                }
                return;
            case 4:
                eltList.add((SchemaLocalElement) contentModel);
                return;
            default:
                return;
        }
    }

    private void buildLocalElts() {
        List<SchemaLocalElement> eltList = new ArrayList<>();
        buildEltList(eltList, this._contentModel);
        this._localElts = (SchemaLocalElement[]) eltList.toArray(new SchemaLocalElement[0]);
    }

    public SchemaLocalElement getLocalElementByIndex(int i) {
        SchemaLocalElement[] elts = this._localElts;
        if (elts == null) {
            buildLocalElts();
            elts = this._localElts;
        }
        return elts[i];
    }

    public int getIndexForLocalElement(SchemaLocalElement elt) {
        Map<SchemaLocalElement, Integer> localEltMap = this._eltToIndexMap;
        if (localEltMap == null) {
            if (this._localElts == null) {
                buildLocalElts();
            }
            localEltMap = new HashMap();
            for (int i = 0; i < this._localElts.length; i++) {
                localEltMap.put(this._localElts[i], Integer.valueOf(i));
            }
            this._eltToIndexMap = localEltMap;
        }
        return localEltMap.get(elt).intValue();
    }

    public int getIndexForLocalAttribute(SchemaLocalAttribute attr) {
        Map<SchemaLocalAttribute, Integer> localAttrMap = this._attrToIndexMap;
        if (localAttrMap == null) {
            localAttrMap = new HashMap();
            SchemaLocalAttribute[] attrs = this._attributeModel.getAttributes();
            for (int i = 0; i < attrs.length; i++) {
                localAttrMap.put(attrs[i], Integer.valueOf(i));
            }
            this._attrToIndexMap = localAttrMap;
        }
        return localAttrMap.get(attr).intValue();
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaAttributeModel getAttributeModel() {
        return this._attributeModel;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaProperty[] getProperties() {
        if (this._propertyModelByElementName == null) {
            return getAttributeProperties();
        }
        if (this._propertyModelByAttributeName == null) {
            return getElementProperties();
        }
        List<SchemaProperty> list = new ArrayList<>();
        list.addAll(this._propertyModelByElementName.values());
        list.addAll(this._propertyModelByAttributeName.values());
        return (SchemaProperty[]) list.toArray(new SchemaProperty[0]);
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaProperty[] getDerivedProperties() {
        SchemaProperty baseProp;
        SchemaType baseType = getBaseType();
        if (baseType == null) {
            return getProperties();
        }
        List<SchemaProperty> results = new ArrayList<>();
        if (this._propertyModelByElementName != null) {
            results.addAll(this._propertyModelByElementName.values());
        }
        if (this._propertyModelByAttributeName != null) {
            results.addAll(this._propertyModelByAttributeName.values());
        }
        Iterator<SchemaProperty> it = results.iterator();
        while (it.hasNext()) {
            SchemaProperty prop = it.next();
            if (prop.isAttribute()) {
                baseProp = baseType.getAttributeProperty(prop.getName());
            } else {
                baseProp = baseType.getElementProperty(prop.getName());
            }
            if (baseProp != null && eq(prop.getMinOccurs(), baseProp.getMinOccurs()) && eq(prop.getMaxOccurs(), baseProp.getMaxOccurs()) && prop.hasNillable() == baseProp.hasNillable() && eq(prop.getDefaultText(), baseProp.getDefaultText())) {
                it.remove();
            }
        }
        return (SchemaProperty[]) results.toArray(new SchemaProperty[0]);
    }

    private static boolean eq(BigInteger a, BigInteger b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.equals(b);
    }

    private static boolean eq(String a, String b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.equals(b);
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaProperty[] getElementProperties() {
        if (this._propertyModelByElementName == null) {
            return NO_PROPERTIES;
        }
        return (SchemaProperty[]) this._propertyModelByElementName.values().toArray(new SchemaProperty[0]);
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaProperty[] getAttributeProperties() {
        if (this._propertyModelByAttributeName == null) {
            return NO_PROPERTIES;
        }
        return (SchemaProperty[]) this._propertyModelByAttributeName.values().toArray(new SchemaProperty[0]);
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaProperty getElementProperty(QName eltName) {
        if (this._propertyModelByElementName == null) {
            return null;
        }
        return this._propertyModelByElementName.get(eltName);
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaProperty getAttributeProperty(QName attrName) {
        if (this._propertyModelByAttributeName == null) {
            return null;
        }
        return this._propertyModelByAttributeName.get(attrName);
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean hasAllContent() {
        return this._hasAllContent;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isOrderSensitive() {
        return this._orderSensitive;
    }

    public void setOrderSensitive(boolean sensitive) {
        assertJavaizing();
        this._orderSensitive = sensitive;
    }

    public void setContentModel(SchemaParticle contentModel, SchemaAttributeModel attrModel, Map<QName, SchemaProperty> propertyModelByElementName, Map<QName, SchemaProperty> propertyModelByAttributeName, boolean isAll) {
        assertResolving();
        this._contentModel = contentModel;
        this._attributeModel = attrModel;
        this._propertyModelByElementName = propertyModelByElementName;
        this._propertyModelByAttributeName = propertyModelByAttributeName;
        this._hasAllContent = isAll;
        if (this._propertyModelByElementName != null) {
            this._validSubstitutions = new LinkedHashSet();
            Collection<SchemaProperty> eltProps = this._propertyModelByElementName.values();
            for (SchemaProperty prop : eltProps) {
                QName[] names = prop.acceptedNames();
                for (QName name : names) {
                    if (!this._propertyModelByElementName.containsKey(name)) {
                        this._validSubstitutions.add(name);
                    }
                }
            }
        }
    }

    private boolean noElements() {
        return (getContentType() == 3 || getContentType() == 4) ? false : true;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean hasAttributeWildcards() {
        return this._hasWildcardAttributes;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean hasElementWildcards() {
        return this._hasWildcardElements;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isValidSubstitution(QName name) {
        return this._validSubstitutions.contains(name);
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaType getElementType(QName eltName, QName xsiType, SchemaTypeLoader wildcardTypeLoader) {
        SchemaType type;
        SchemaType itype;
        if (isSimpleType() || noElements() || isNoType()) {
            return BuiltinSchemaTypeSystem.ST_NO_TYPE;
        }
        SchemaProperty prop = this._propertyModelByElementName.get(eltName);
        if (prop != null) {
            type = prop.getType();
        } else {
            if (wildcardTypeLoader == null) {
                return BuiltinSchemaTypeSystem.ST_NO_TYPE;
            }
            if (this._typedWildcardElements.contains(eltName) || this._validSubstitutions.contains(eltName)) {
                SchemaGlobalElement elt = wildcardTypeLoader.findElement(eltName);
                if (elt == null) {
                    return BuiltinSchemaTypeSystem.ST_NO_TYPE;
                }
                type = elt.getType();
            } else {
                return BuiltinSchemaTypeSystem.ST_NO_TYPE;
            }
        }
        if (xsiType != null && wildcardTypeLoader != null && (itype = wildcardTypeLoader.findType(xsiType)) != null && type.isAssignableFrom(itype)) {
            return itype;
        }
        return type;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaType getAttributeType(QName attrName, SchemaTypeLoader wildcardTypeLoader) {
        if (isSimpleType() || isNoType()) {
            return BuiltinSchemaTypeSystem.ST_NO_TYPE;
        }
        if (isURType()) {
            return BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        }
        SchemaProperty prop = this._propertyModelByAttributeName.get(attrName);
        if (prop != null) {
            return prop.getType();
        }
        if (!this._typedWildcardAttributes.contains(attrName) || wildcardTypeLoader == null) {
            return BuiltinSchemaTypeSystem.ST_NO_TYPE;
        }
        SchemaGlobalAttribute attr = wildcardTypeLoader.findAttribute(attrName);
        if (attr == null) {
            return BuiltinSchemaTypeSystem.ST_NO_TYPE;
        }
        return attr.getType();
    }

    public XmlObject createElementType(QName eltName, QName xsiType, SchemaTypeLoader wildcardTypeLoader) {
        SchemaType type;
        SchemaType itype;
        SchemaProperty prop = null;
        if (isSimpleType() || noElements() || isNoType()) {
            type = BuiltinSchemaTypeSystem.ST_NO_TYPE;
        } else {
            SchemaProperty prop2 = this._propertyModelByElementName.get(eltName);
            prop = prop2;
            if (prop != null) {
                type = prop.getType();
            } else if (this._typedWildcardElements.contains(eltName) || this._validSubstitutions.contains(eltName)) {
                SchemaGlobalElement elt = wildcardTypeLoader.findElement(eltName);
                if (elt != null) {
                    SchemaType type2 = elt.getType();
                    SchemaType docType = wildcardTypeLoader.findDocumentType(eltName);
                    if (docType != null) {
                        prop = docType.getElementProperty(eltName);
                    }
                    type = type2;
                } else {
                    SchemaType type3 = BuiltinSchemaTypeSystem.ST_NO_TYPE;
                    type = type3;
                }
            } else {
                type = BuiltinSchemaTypeSystem.ST_NO_TYPE;
            }
            if (xsiType != null && (itype = wildcardTypeLoader.findType(xsiType)) != null && type.isAssignableFrom(itype)) {
                type = itype;
            }
        }
        if (type != null) {
            return ((SchemaTypeImpl) type).createUnattachedNode(prop);
        }
        return null;
    }

    public XmlObject createAttributeType(QName attrName, SchemaTypeLoader wildcardTypeLoader) {
        SchemaTypeImpl type;
        SchemaProperty prop = null;
        if (isSimpleType() || isNoType()) {
            type = BuiltinSchemaTypeSystem.ST_NO_TYPE;
        } else if (isURType()) {
            type = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        } else {
            prop = this._propertyModelByAttributeName.get(attrName);
            if (prop != null) {
                type = (SchemaTypeImpl) prop.getType();
            } else if (!this._typedWildcardAttributes.contains(attrName)) {
                type = BuiltinSchemaTypeSystem.ST_NO_TYPE;
            } else {
                SchemaGlobalAttribute attr = wildcardTypeLoader.findAttribute(attrName);
                if (attr != null) {
                    type = (SchemaTypeImpl) attr.getType();
                } else {
                    SchemaTypeImpl type2 = BuiltinSchemaTypeSystem.ST_NO_TYPE;
                    type = type2;
                }
            }
        }
        if (type != null) {
            return type.createUnattachedNode(prop);
        }
        return null;
    }

    public void setWildcardSummary(QNameSet elementSet, boolean haswcElt, QNameSet attributeSet, boolean haswcAtt) {
        assertResolving();
        this._typedWildcardElements = elementSet;
        this._hasWildcardElements = haswcElt;
        this._typedWildcardAttributes = attributeSet;
        this._hasWildcardAttributes = haswcAtt;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaType[] getAnonymousTypes() {
        SchemaType[] result = new SchemaType[this._anonymousTyperefs.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = this._anonymousTyperefs[i].get();
        }
        return result;
    }

    public void setAnonymousTypeRefs(SchemaType.Ref[] anonymousTyperefs) {
        this._anonymousTyperefs = anonymousTyperefs == null ? null : (SchemaType.Ref[]) anonymousTyperefs.clone();
    }

    private static SchemaType[] staCopy(SchemaType[] a) {
        if (a == null) {
            return null;
        }
        SchemaType[] result = new SchemaType[a.length];
        System.arraycopy(a, 0, result, 0, a.length);
        return result;
    }

    private static boolean[] boaCopy(boolean[] a) {
        if (a == null) {
            return null;
        }
        boolean[] result = new boolean[a.length];
        System.arraycopy(a, 0, result, 0, a.length);
        return result;
    }

    public void setSimpleTypeVariety(int variety) {
        assertResolving();
        this._simpleTypeVariety = variety;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public int getSimpleVariety() {
        return this._simpleTypeVariety;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isURType() {
        return this._builtinTypeCode == 1 || this._builtinTypeCode == 2;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isNoType() {
        return this == BuiltinSchemaTypeSystem.ST_NO_TYPE;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isSimpleType() {
        return this._isSimpleType;
    }

    public void setSimpleType(boolean f) {
        assertUnresolved();
        this._isSimpleType = f;
    }

    public boolean isUnionOfLists() {
        return this._isUnionOfLists;
    }

    public void setUnionOfLists(boolean f) {
        assertResolving();
        this._isUnionOfLists = f;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaType getPrimitiveType() {
        if (this._primitiveTypeRef == null) {
            return null;
        }
        return this._primitiveTypeRef.get();
    }

    public void setPrimitiveTypeRef(SchemaType.Ref typeref) {
        assertResolving();
        this._primitiveTypeRef = typeref;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public int getDecimalSize() {
        return this._decimalSize;
    }

    public void setDecimalSize(int bits) {
        assertResolving();
        this._decimalSize = bits;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaType getBaseType() {
        if (this._baseTyperef == null) {
            return null;
        }
        return this._baseTyperef.get();
    }

    public void setBaseTypeRef(SchemaType.Ref typeref) {
        assertResolving();
        this._baseTyperef = typeref;
    }

    public int getBaseDepth() {
        return this._baseDepth;
    }

    public void setBaseDepth(int depth) {
        assertResolving();
        this._baseDepth = depth;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaType getContentBasedOnType() {
        if (this._contentBasedOnTyperef == null) {
            return null;
        }
        return this._contentBasedOnTyperef.get();
    }

    public void setContentBasedOnTypeRef(SchemaType.Ref typeref) {
        assertResolving();
        this._contentBasedOnTyperef = typeref;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public int getDerivationType() {
        return this._derivationType;
    }

    public void setDerivationType(int type) {
        assertResolving();
        this._derivationType = type;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaType getListItemType() {
        if (this._listItemTyperef == null) {
            return null;
        }
        return this._listItemTyperef.get();
    }

    public void setListItemTypeRef(SchemaType.Ref typeref) {
        assertResolving();
        this._listItemTyperef = typeref;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaType[] getUnionMemberTypes() {
        SchemaType[] result = new SchemaType[this._unionMemberTyperefs == null ? 0 : this._unionMemberTyperefs.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = this._unionMemberTyperefs[i].get();
        }
        return result;
    }

    public void setUnionMemberTypeRefs(SchemaType.Ref[] typerefs) {
        assertResolving();
        this._unionMemberTyperefs = typerefs == null ? null : (SchemaType.Ref[]) typerefs.clone();
    }

    @Override // org.apache.xmlbeans.SchemaType
    public int getAnonymousUnionMemberOrdinal() {
        return this._anonymousUnionMemberOrdinal;
    }

    public void setAnonymousUnionMemberOrdinal(int i) {
        assertUnresolved();
        this._anonymousUnionMemberOrdinal = i;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public synchronized SchemaType[] getUnionConstituentTypes() {
        if (this._unionCommonBaseType == null) {
            computeFlatUnionModel();
        }
        return staCopy(this._unionConstituentTypes);
    }

    private void setUnionConstituentTypes(SchemaType[] types) {
        this._unionConstituentTypes = types;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public synchronized SchemaType[] getUnionSubTypes() {
        if (this._unionCommonBaseType == null) {
            computeFlatUnionModel();
        }
        return staCopy(this._unionSubTypes);
    }

    private void setUnionSubTypes(SchemaType[] types) {
        this._unionSubTypes = types;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public synchronized SchemaType getUnionCommonBaseType() {
        if (this._unionCommonBaseType == null) {
            computeFlatUnionModel();
        }
        return this._unionCommonBaseType;
    }

    private void setUnionCommonBaseType(SchemaType type) {
        this._unionCommonBaseType = type;
    }

    private void computeFlatUnionModel() {
        if (getSimpleVariety() != 2) {
            throw new IllegalStateException("Operation is only supported on union types");
        }
        Set<SchemaType> constituentMemberTypes = new LinkedHashSet<>();
        Set<SchemaType> allSubTypes = new LinkedHashSet<>();
        SchemaType commonBaseType = null;
        allSubTypes.add(this);
        for (SchemaType.Ref unionMemberTyperef : this._unionMemberTyperefs) {
            SchemaTypeImpl mImpl = (SchemaTypeImpl) unionMemberTyperef.get();
            switch (mImpl.getSimpleVariety()) {
                case 1:
                    constituentMemberTypes.add(mImpl);
                    allSubTypes.add(mImpl);
                    commonBaseType = mImpl.getCommonBaseType(commonBaseType);
                    break;
                case 2:
                    constituentMemberTypes.addAll(Arrays.asList(mImpl.getUnionConstituentTypes()));
                    allSubTypes.addAll(Arrays.asList(mImpl.getUnionSubTypes()));
                    SchemaType otherCommonBaseType = mImpl.getUnionCommonBaseType();
                    if (otherCommonBaseType != null) {
                        commonBaseType = otherCommonBaseType.getCommonBaseType(commonBaseType);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    constituentMemberTypes.add(mImpl);
                    allSubTypes.add(mImpl);
                    commonBaseType = mImpl.getCommonBaseType(commonBaseType);
                    break;
                default:
                    throw new AssertionError();
            }
        }
        setUnionConstituentTypes((SchemaType[]) constituentMemberTypes.toArray(StscState.EMPTY_ST_ARRAY));
        setUnionSubTypes((SchemaType[]) allSubTypes.toArray(StscState.EMPTY_ST_ARRAY));
        setUnionCommonBaseType(commonBaseType);
    }

    public QName getSubstitutionGroup() {
        return this._sg;
    }

    public void setSubstitutionGroup(QName sg) {
        assertSGResolving();
        this._sg = sg;
    }

    public void addSubstitutionGroupMember(QName member) {
        assertSGResolved();
        this._sgMembers.add(member);
    }

    public QName[] getSubstitutionGroupMembers() {
        return (QName[]) this._sgMembers.toArray(new QName[0]);
    }

    @Override // org.apache.xmlbeans.SchemaType
    public int getWhiteSpaceRule() {
        return this._whiteSpaceRule;
    }

    public void setWhiteSpaceRule(int ws) {
        assertResolving();
        this._whiteSpaceRule = ws;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public XmlAnySimpleType getFacet(int facetCode) {
        XmlValueRef ref;
        if (this._facetArray == null || (ref = this._facetArray[facetCode]) == null) {
            return null;
        }
        return ref.get();
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isFacetFixed(int facetCode) {
        return this._fixedFacetArray[facetCode];
    }

    public XmlAnySimpleType[] getBasicFacets() {
        XmlAnySimpleType[] result = new XmlAnySimpleType[12];
        for (int i = 0; i <= 11; i++) {
            result[i] = getFacet(i);
        }
        return result;
    }

    public boolean[] getFixedFacets() {
        return boaCopy(this._fixedFacetArray);
    }

    public void setBasicFacets(XmlValueRef[] values, boolean[] fixed) {
        assertResolving();
        this._facetArray = values == null ? null : (XmlValueRef[]) values.clone();
        this._fixedFacetArray = fixed != null ? (boolean[]) fixed.clone() : null;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public int ordered() {
        return this._ordered;
    }

    public void setOrdered(int ordering) {
        assertResolving();
        this._ordered = ordering;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isBounded() {
        return this._isBounded;
    }

    public void setBounded(boolean f) {
        assertResolving();
        this._isBounded = f;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isFinite() {
        return this._isFinite;
    }

    public void setFinite(boolean f) {
        assertResolving();
        this._isFinite = f;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isNumeric() {
        return this._isNumeric;
    }

    public void setNumeric(boolean f) {
        assertResolving();
        this._isNumeric = f;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean hasPatternFacet() {
        return this._hasPatterns;
    }

    public void setPatternFacet(boolean hasPatterns) {
        assertResolving();
        this._hasPatterns = hasPatterns;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean matchPatternFacet(String s) {
        if (!this._hasPatterns) {
            return true;
        }
        if (this._patterns != null && this._patterns.length > 0) {
            int i = 0;
            while (i < this._patterns.length && !this._patterns[i].matches(s)) {
                i++;
            }
            if (i >= this._patterns.length) {
                return false;
            }
        }
        return getBaseType().matchPatternFacet(s);
    }

    @Override // org.apache.xmlbeans.SchemaType
    public String[] getPatterns() {
        if (this._patterns == null) {
            return new String[0];
        }
        String[] patterns = new String[this._patterns.length];
        for (int i = 0; i < this._patterns.length; i++) {
            patterns[i] = this._patterns[i].getPattern();
        }
        return patterns;
    }

    public RegularExpression[] getPatternExpressions() {
        if (this._patterns == null) {
            return new RegularExpression[0];
        }
        RegularExpression[] result = new RegularExpression[this._patterns.length];
        System.arraycopy(this._patterns, 0, result, 0, this._patterns.length);
        return result;
    }

    public void setPatterns(RegularExpression[] list) {
        assertResolving();
        this._patterns = list == null ? null : (RegularExpression[]) list.clone();
    }

    @Override // org.apache.xmlbeans.SchemaType
    public XmlAnySimpleType[] getEnumerationValues() {
        if (this._enumerationValues == null) {
            return null;
        }
        XmlAnySimpleType[] result = new XmlAnySimpleType[this._enumerationValues.length];
        for (int i = 0; i < result.length; i++) {
            XmlValueRef ref = this._enumerationValues[i];
            result[i] = ref == null ? null : ref.get();
        }
        return result;
    }

    public void setEnumerationValues(XmlValueRef[] a) {
        assertResolving();
        this._enumerationValues = a == null ? null : (XmlValueRef[]) a.clone();
    }

    @Override // org.apache.xmlbeans.SchemaType
    public StringEnumAbstractBase enumForString(String s) {
        ensureStringEnumInfo();
        if (this._lookupStringEnum == null) {
            return null;
        }
        return this._lookupStringEnum.get(s);
    }

    @Override // org.apache.xmlbeans.SchemaType
    public StringEnumAbstractBase enumForInt(int i) {
        ensureStringEnumInfo();
        if (this._listOfStringEnum == null || i < 0 || i >= this._listOfStringEnum.size()) {
            return null;
        }
        return this._listOfStringEnum.get(i);
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaStringEnumEntry enumEntryForString(String s) {
        ensureStringEnumInfo();
        if (this._lookupStringEnumEntry == null) {
            return null;
        }
        return this._lookupStringEnumEntry.get(s);
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaType getBaseEnumType() {
        if (this._baseEnumTyperef == null) {
            return null;
        }
        return this._baseEnumTyperef.get();
    }

    public void setBaseEnumTypeRef(SchemaType.Ref baseEnumTyperef) {
        this._baseEnumTyperef = baseEnumTyperef;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaStringEnumEntry[] getStringEnumEntries() {
        if (this._stringEnumEntries == null) {
            return null;
        }
        SchemaStringEnumEntry[] result = new SchemaStringEnumEntry[this._stringEnumEntries.length];
        System.arraycopy(this._stringEnumEntries, 0, result, 0, result.length);
        return result;
    }

    public void setStringEnumEntries(SchemaStringEnumEntry[] sEnums) {
        assertJavaizing();
        this._stringEnumEntries = sEnums == null ? null : (SchemaStringEnumEntry[]) sEnums.clone();
    }

    private void ensureStringEnumInfo() {
        if (this._stringEnumEnsured) {
            return;
        }
        SchemaStringEnumEntry[] sEnums = this._stringEnumEntries;
        if (sEnums == null) {
            this._stringEnumEnsured = true;
            return;
        }
        Map<String, StringEnumAbstractBase> lookupStringEnum = new HashMap<>(sEnums.length);
        List<StringEnumAbstractBase> listOfStringEnum = new ArrayList<>(sEnums.length + 1);
        Map<String, SchemaStringEnumEntry> lookupStringEnumEntry = new HashMap<>(sEnums.length);
        for (SchemaStringEnumEntry sEnum : sEnums) {
            lookupStringEnumEntry.put(sEnum.getString(), sEnum);
        }
        Class<? extends StringEnumAbstractBase> jc = this._baseEnumTyperef.get().getEnumJavaClass();
        if (jc != null) {
            try {
                StringEnumAbstractBase.Table table = (StringEnumAbstractBase.Table) jc.getField("table").get(null);
                for (SchemaStringEnumEntry sEnum2 : sEnums) {
                    int j = sEnum2.getIntValue();
                    StringEnumAbstractBase enumVal = table.forInt(j);
                    lookupStringEnum.put(sEnum2.getString(), enumVal);
                    while (listOfStringEnum.size() <= j) {
                        listOfStringEnum.add(null);
                    }
                    listOfStringEnum.set(j, enumVal);
                }
            } catch (Exception e) {
                System.err.println("Something wrong: could not locate enum table for " + jc);
                jc = null;
                lookupStringEnum.clear();
                listOfStringEnum.clear();
            }
        }
        if (jc == null) {
            for (SchemaStringEnumEntry sEnum3 : sEnums) {
                int j2 = sEnum3.getIntValue();
                String s = sEnum3.getString();
                StringEnumAbstractBase enumVal2 = new StringEnumValue(s, j2);
                lookupStringEnum.put(s, enumVal2);
                while (listOfStringEnum.size() <= j2) {
                    listOfStringEnum.add(null);
                }
                listOfStringEnum.set(j2, enumVal2);
            }
        }
        synchronized (this) {
            if (!this._stringEnumEnsured) {
                this._lookupStringEnum = lookupStringEnum;
                this._listOfStringEnum = listOfStringEnum;
                this._lookupStringEnumEntry = lookupStringEnumEntry;
            }
        }
        synchronized (this) {
            this._stringEnumEnsured = true;
        }
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean hasStringEnumValues() {
        return this._stringEnumEntries != null;
    }

    public void copyEnumerationValues(SchemaTypeImpl baseImpl) {
        assertResolving();
        this._enumerationValues = baseImpl._enumerationValues;
        this._baseEnumTyperef = baseImpl._baseEnumTyperef;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public int getBuiltinTypeCode() {
        return this._builtinTypeCode;
    }

    public void setBuiltinTypeCode(int builtinTypeCode) {
        assertResolving();
        this._builtinTypeCode = builtinTypeCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void assignJavaElementSetterModel() {
        SchemaProperty[] eltProps = getElementProperties();
        SchemaParticle contentModel = getContentModel();
        Map<SchemaParticle, QNameSet> state = new HashMap<>();
        QNameSet allContents = computeAllContainedElements(contentModel, state);
        for (SchemaProperty eltProp : eltProps) {
            SchemaPropertyImpl sImpl = (SchemaPropertyImpl) eltProp;
            QNameSet nde = computeNondelimitingElements(sImpl.getName(), contentModel, state);
            QNameSetBuilder builder = new QNameSetBuilder(allContents);
            builder.removeAll(nde);
            sImpl.setJavaSetterDelimiter(builder.toQNameSet());
        }
    }

    private static QNameSet computeNondelimitingElements(QName target, SchemaParticle contentModel, Map<SchemaParticle, QNameSet> state) {
        QNameSet allContents = computeAllContainedElements(contentModel, state);
        if (!allContents.contains(target)) {
            return QNameSet.EMPTY;
        }
        if (contentModel.getMaxOccurs() == null || contentModel.getMaxOccurs().compareTo(BigInteger.ONE) > 0) {
            return allContents;
        }
        switch (contentModel.getParticleType()) {
            case 2:
                QNameSetBuilder builder = new QNameSetBuilder();
                for (int i = 0; i < contentModel.countOfParticleChild(); i++) {
                    if (computeAllContainedElements(contentModel.getParticleChild(i), state).contains(target)) {
                        builder.addAll(computeNondelimitingElements(target, contentModel.getParticleChild(i), state));
                    }
                }
                return builder.toQNameSet();
            case 3:
                QNameSetBuilder builder2 = new QNameSetBuilder();
                boolean seenTarget = false;
                int i2 = contentModel.countOfParticleChild();
                while (i2 > 0) {
                    i2--;
                    QNameSet childContents = computeAllContainedElements(contentModel.getParticleChild(i2), state);
                    if (seenTarget) {
                        builder2.addAll(childContents);
                    } else if (childContents.contains(target)) {
                        builder2.addAll(computeNondelimitingElements(target, contentModel.getParticleChild(i2), state));
                        seenTarget = true;
                    }
                }
                return builder2.toQNameSet();
            case 4:
            default:
                return allContents;
            case 5:
                return QNameSet.singleton(target);
        }
    }

    private static QNameSet computeAllContainedElements(SchemaParticle contentModel, Map<SchemaParticle, QNameSet> state) {
        QNameSet result;
        QNameSet result2 = state.get(contentModel);
        if (result2 != null) {
            return result2;
        }
        switch (contentModel.getParticleType()) {
            case 4:
                result = contentModel.acceptedStartNames();
                break;
            case 5:
                result = contentModel.getWildcardSet();
                break;
            default:
                QNameSetBuilder builder = new QNameSetBuilder();
                for (int i = 0; i < contentModel.countOfParticleChild(); i++) {
                    builder.addAll(computeAllContainedElements(contentModel.getParticleChild(i), state));
                }
                result = builder.toQNameSet();
                break;
        }
        state.put(contentModel, result);
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public Class<? extends XmlObject> getJavaClass() {
        if (this._javaClass == null && getFullJavaName() != null) {
            try {
                this._javaClass = Class.forName(getFullJavaName(), false, getTypeSystem().getClassLoader());
            } catch (ClassNotFoundException e) {
                this._javaClass = null;
            }
        }
        return this._javaClass;
    }

    public Class<? extends XmlObjectBase> getJavaImplClass() {
        if (this._implNotAvailable) {
            return null;
        }
        if (this._javaImplClass == null) {
            try {
                if (getFullJavaImplName() != null) {
                    this._javaImplClass = Class.forName(getFullJavaImplName(), false, getTypeSystem().getClassLoader());
                } else {
                    this._implNotAvailable = true;
                }
            } catch (ClassNotFoundException e) {
                this._implNotAvailable = true;
            }
        }
        return this._javaImplClass;
    }

    public Constructor<? extends XmlObjectBase> getJavaImplConstructor() {
        if (this._javaImplConstructor == null && !this._implNotAvailable) {
            Class<? extends XmlObjectBase> impl = getJavaImplClass();
            if (impl == null) {
                return null;
            }
            try {
                this._javaImplConstructor = impl.getConstructor(SchemaType.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return this._javaImplConstructor;
    }

    public Constructor<? extends XmlObjectBase> getJavaImplConstructor2() {
        if (this._javaImplConstructor2 == null && !this._implNotAvailable) {
            Class<? extends XmlObjectBase> impl = getJavaImplClass();
            if (impl == null) {
                return null;
            }
            try {
                this._javaImplConstructor2 = impl.getDeclaredConstructor(SchemaType.class, Boolean.TYPE);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return this._javaImplConstructor2;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public Class<? extends StringEnumAbstractBase> getEnumJavaClass() {
        if (this._javaEnumClass == null && getBaseEnumType() != null) {
            try {
                this._javaEnumClass = Class.forName(getBaseEnumType().getFullJavaName() + "$Enum", false, getTypeSystem().getClassLoader());
            } catch (ClassNotFoundException e) {
                this._javaEnumClass = null;
            }
        }
        return this._javaEnumClass;
    }

    public void setJavaClass(Class<? extends XmlObject> javaClass) {
        assertResolved();
        this._javaClass = javaClass;
        setFullJavaName(javaClass.getName());
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isPrimitiveType() {
        return getBuiltinTypeCode() >= 2 && getBuiltinTypeCode() <= 21;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isBuiltinType() {
        return getBuiltinTypeCode() != 0;
    }

    public XmlObject createUnwrappedNode() {
        return createUnattachedNode(null);
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUserFactory
    public TypeStoreUser createTypeStoreUser() {
        return (TypeStoreUser) createUnattachedNode(null);
    }

    public XmlAnySimpleType newValidatingValue(Object obj) {
        return newValue(obj, true);
    }

    @Override // org.apache.xmlbeans.SchemaType
    public XmlAnySimpleType newValue(Object obj) {
        return newValue(obj, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public XmlAnySimpleType newValue(Object obj, boolean validateOnSet) {
        if (!isSimpleType() && getContentType() != 2) {
            throw new XmlValueOutOfRangeException();
        }
        XmlObjectBase xmlObjectBase = (XmlObjectBase) createUnattachedNode(null);
        if (validateOnSet) {
            xmlObjectBase.setValidateOnSet();
        }
        if (obj instanceof XmlObject) {
            xmlObjectBase.set_newValue((XmlObject) obj);
        } else {
            xmlObjectBase.setObjectValue(obj);
        }
        xmlObjectBase.check_dated();
        xmlObjectBase.setImmutable();
        return (XmlAnySimpleType) xmlObjectBase;
    }

    private XmlObject createUnattachedNode(SchemaProperty prop) {
        XmlObject result = null;
        if (!isBuiltinType() && !isNoType()) {
            Constructor<? extends XmlObjectBase> ctr = getJavaImplConstructor();
            if (ctr != null) {
                try {
                    return ctr.newInstance(this._ctrArgs);
                } catch (Exception e) {
                    System.out.println("Exception trying to instantiate impl class.");
                    e.printStackTrace();
                }
            }
        } else {
            result = createBuiltinInstance();
        }
        SchemaType sType = this;
        while (result == null) {
            result = ((SchemaTypeImpl) sType).createUnattachedSubclass(this);
            sType = sType.getBaseType();
        }
        ((XmlObjectBase) result).init_flags(prop);
        return result;
    }

    private XmlObject createUnattachedSubclass(SchemaType sType) {
        if (!isBuiltinType() && !isNoType()) {
            Constructor<? extends XmlObjectBase> ctr = getJavaImplConstructor2();
            if (ctr == null) {
                return null;
            }
            try {
                return ctr.newInstance(sType, Boolean.valueOf(!sType.isSimpleType()));
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                XBeanDebug.LOG.atDebug().withThrowable(e).log(e.getMessage());
                return null;
            }
        }
        return createBuiltinSubclass(sType);
    }

    private XmlObject createBuiltinInstance() {
        switch (getBuiltinTypeCode()) {
            case 0:
                return new XmlAnyTypeImpl(BuiltinSchemaTypeSystem.ST_NO_TYPE);
            case 1:
                return new XmlAnyTypeImpl();
            case 2:
                return new XmlAnySimpleTypeImpl();
            case 3:
                return new XmlBooleanImpl();
            case 4:
                return new XmlBase64BinaryImpl();
            case 5:
                return new XmlHexBinaryImpl();
            case 6:
                return new XmlAnyUriImpl();
            case 7:
                return new XmlQNameImpl();
            case 8:
                return new XmlNotationImpl();
            case 9:
                return new XmlFloatImpl();
            case 10:
                return new XmlDoubleImpl();
            case 11:
                return new XmlDecimalImpl();
            case 12:
                return new XmlStringImpl();
            case 13:
                return new XmlDurationImpl();
            case 14:
                return new XmlDateTimeImpl();
            case 15:
                return new XmlTimeImpl();
            case 16:
                return new XmlDateImpl();
            case 17:
                return new XmlGYearMonthImpl();
            case 18:
                return new XmlGYearImpl();
            case 19:
                return new XmlGMonthDayImpl();
            case 20:
                return new XmlGDayImpl();
            case 21:
                return new XmlGMonthImpl();
            case 22:
                return new XmlIntegerImpl();
            case 23:
                return new XmlLongImpl();
            case 24:
                return new XmlIntImpl();
            case 25:
                return new XmlShortImpl();
            case 26:
                return new XmlByteImpl();
            case 27:
                return new XmlNonPositiveIntegerImpl();
            case 28:
                return new XmlNegativeIntegerImpl();
            case 29:
                return new XmlNonNegativeIntegerImpl();
            case 30:
                return new XmlPositiveIntegerImpl();
            case 31:
                return new XmlUnsignedLongImpl();
            case 32:
                return new XmlUnsignedIntImpl();
            case 33:
                return new XmlUnsignedShortImpl();
            case 34:
                return new XmlUnsignedByteImpl();
            case 35:
                return new XmlNormalizedStringImpl();
            case 36:
                return new XmlTokenImpl();
            case 37:
                return new XmlNameImpl();
            case 38:
                return new XmlNCNameImpl();
            case 39:
                return new XmlLanguageImpl();
            case 40:
                return new XmlIdImpl();
            case 41:
                return new XmlIdRefImpl();
            case 42:
                return new XmlIdRefsImpl();
            case 43:
                return new XmlEntityImpl();
            case 44:
                return new XmlEntitiesImpl();
            case 45:
                return new XmlNmTokenImpl();
            case 46:
                return new XmlNmTokensImpl();
            default:
                throw new IllegalStateException("Unrecognized builtin type: " + getBuiltinTypeCode());
        }
    }

    private XmlObject createBuiltinSubclass(SchemaType sType) {
        boolean complex = !sType.isSimpleType();
        switch (getBuiltinTypeCode()) {
            case 0:
                return new XmlAnyTypeImpl(BuiltinSchemaTypeSystem.ST_NO_TYPE);
            case 1:
            case 2:
                switch (sType.getSimpleVariety()) {
                    case 0:
                        return new XmlComplexContentImpl(sType);
                    case 1:
                        return new XmlAnySimpleTypeRestriction(sType, complex);
                    case 2:
                        return new XmlUnionImpl(sType, complex);
                    case 3:
                        return new XmlListImpl(sType, complex);
                    default:
                        throw new IllegalStateException();
                }
            case 3:
                return new XmlBooleanRestriction(sType, complex);
            case 4:
                return new XmlBase64BinaryRestriction(sType, complex);
            case 5:
                return new XmlHexBinaryRestriction(sType, complex);
            case 6:
                return new XmlAnyUriRestriction(sType, complex);
            case 7:
                return new XmlQNameRestriction(sType, complex);
            case 8:
                return new XmlNotationRestriction(sType, complex);
            case 9:
                return new XmlFloatRestriction(sType, complex);
            case 10:
                return new XmlDoubleRestriction(sType, complex);
            case 11:
                return new XmlDecimalRestriction(sType, complex);
            case 12:
                if (sType.hasStringEnumValues()) {
                    return new XmlStringEnumeration(sType, complex);
                }
                return new XmlStringRestriction(sType, complex);
            case 13:
                return new XmlDurationImpl(sType, complex);
            case 14:
                return new XmlDateTimeImpl(sType, complex);
            case 15:
                return new XmlTimeImpl(sType, complex);
            case 16:
                return new XmlDateImpl(sType, complex);
            case 17:
                return new XmlGYearMonthImpl(sType, complex);
            case 18:
                return new XmlGYearImpl(sType, complex);
            case 19:
                return new XmlGMonthDayImpl(sType, complex);
            case 20:
                return new XmlGDayImpl(sType, complex);
            case 21:
                return new XmlGMonthImpl(sType, complex);
            case 22:
                return new XmlIntegerRestriction(sType, complex);
            case 23:
                return new XmlLongRestriction(sType, complex);
            case 24:
                return new XmlIntRestriction(sType, complex);
            case 25:
                return new XmlShortImpl(sType, complex);
            case 26:
                return new XmlByteImpl(sType, complex);
            case 27:
                return new XmlNonPositiveIntegerImpl(sType, complex);
            case 28:
                return new XmlNegativeIntegerImpl(sType, complex);
            case 29:
                return new XmlNonNegativeIntegerImpl(sType, complex);
            case 30:
                return new XmlPositiveIntegerImpl(sType, complex);
            case 31:
                return new XmlUnsignedLongImpl(sType, complex);
            case 32:
                return new XmlUnsignedIntImpl(sType, complex);
            case 33:
                return new XmlUnsignedShortImpl(sType, complex);
            case 34:
                return new XmlUnsignedByteImpl(sType, complex);
            case 35:
                return new XmlNormalizedStringImpl(sType, complex);
            case 36:
                return new XmlTokenImpl(sType, complex);
            case 37:
                return new XmlNameImpl(sType, complex);
            case 38:
                return new XmlNCNameImpl(sType, complex);
            case 39:
                return new XmlLanguageImpl(sType, complex);
            case 40:
                return new XmlIdImpl(sType, complex);
            case 41:
                return new XmlIdRefImpl(sType, complex);
            case 42:
                return new XmlIdRefsImpl(sType, complex);
            case 43:
                return new XmlEntityImpl(sType, complex);
            case 44:
                return new XmlEntitiesImpl(sType, complex);
            case 45:
                return new XmlNmTokenImpl(sType, complex);
            case 46:
                return new XmlNmTokensImpl(sType, complex);
            default:
                throw new IllegalStateException("Unrecognized builtin type: " + getBuiltinTypeCode());
        }
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaType getCommonBaseType(SchemaType type) {
        SchemaTypeImpl sImpl1;
        if (this == BuiltinSchemaTypeSystem.ST_ANY_TYPE || type == null || type.isNoType()) {
            return this;
        }
        if (type == BuiltinSchemaTypeSystem.ST_ANY_TYPE || isNoType()) {
            return type;
        }
        SchemaType schemaType = type;
        while (true) {
            sImpl1 = (SchemaTypeImpl) schemaType;
            if (sImpl1.getBaseDepth() <= getBaseDepth()) {
                break;
            }
            schemaType = sImpl1.getBaseType();
        }
        SchemaTypeImpl sImpl2 = this;
        while (sImpl2.getBaseDepth() > sImpl1.getBaseDepth()) {
            sImpl2 = (SchemaTypeImpl) sImpl2.getBaseType();
        }
        while (!sImpl1.equals(sImpl2)) {
            sImpl1 = (SchemaTypeImpl) sImpl1.getBaseType();
            sImpl2 = (SchemaTypeImpl) sImpl2.getBaseType();
            if (sImpl1 == null || sImpl2 == null) {
                throw new AssertionError();
            }
        }
        return sImpl1;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public boolean isAssignableFrom(SchemaType type) {
        if (type == null || type.isNoType()) {
            return true;
        }
        if (isNoType()) {
            return false;
        }
        if (getSimpleVariety() == 2) {
            SchemaType[] members = getUnionMemberTypes();
            for (SchemaType member : members) {
                if (member.isAssignableFrom(type)) {
                    return true;
                }
            }
        }
        int depth = ((SchemaTypeImpl) type).getBaseDepth() - getBaseDepth();
        if (depth < 0) {
            return false;
        }
        while (depth > 0) {
            type = type.getBaseType();
            depth--;
        }
        return type != null && type.equals(this);
    }

    public String toString() {
        String prefix;
        String str;
        if (getName() != null) {
            return "T=" + QNameHelper.pretty(getName());
        }
        if (isDocumentType()) {
            return "D=" + QNameHelper.pretty(getDocumentElementName());
        }
        if (isAttributeType()) {
            return "R=" + QNameHelper.pretty(getAttributeTypeAttributeName());
        }
        if (getContainerField() != null) {
            StringBuilder sb = new StringBuilder();
            if (getContainerField().getName().getNamespaceURI().length() > 0) {
                str = getContainerField().isAttribute() ? "Q=" : "E=";
            } else {
                str = getContainerField().isAttribute() ? "A=" : "U=";
            }
            prefix = sb.append(str).append(getContainerField().getName().getLocalPart()).toString();
            if (getOuterType() == null) {
                return prefix + "@" + getContainerField().getName().getNamespaceURI();
            }
        } else {
            if (isNoType()) {
                return "N=";
            }
            if (getOuterType() == null) {
                return "noouter";
            }
            if (getOuterType().getBaseType() == this) {
                prefix = "B=";
            } else if (getOuterType().getContentBasedOnType() == this) {
                prefix = "S=";
            } else if (getOuterType().getSimpleVariety() == 3) {
                prefix = "I=";
            } else if (getOuterType().getSimpleVariety() == 2) {
                prefix = "M=" + getAnonymousUnionMemberOrdinal();
            } else {
                prefix = "strange=";
            }
        }
        return prefix + "|" + getOuterType().toString();
    }

    public void setParseContext(XmlObject parseObject, String targetNamespace, boolean chameleon, String elemFormDefault, String attFormDefault, boolean redefinition) {
        this._parseObject = parseObject;
        this._parseTNS = targetNamespace;
        this._chameleon = chameleon;
        this._elemFormDefault = elemFormDefault;
        this._attFormDefault = attFormDefault;
        this._redefinition = redefinition;
    }

    public XmlObject getParseObject() {
        return this._parseObject;
    }

    public String getTargetNamespace() {
        return this._parseTNS;
    }

    public boolean isChameleon() {
        return this._chameleon;
    }

    public String getElemFormDefault() {
        return this._elemFormDefault;
    }

    public String getAttFormDefault() {
        return this._attFormDefault;
    }

    public String getChameleonNamespace() {
        if (this._chameleon) {
            return this._parseTNS;
        }
        return null;
    }

    public boolean isRedefinition() {
        return this._redefinition;
    }

    @Override // org.apache.xmlbeans.SchemaType
    public SchemaType.Ref getRef() {
        return this._selfref;
    }

    @Override // org.apache.xmlbeans.SchemaComponent
    public SchemaComponent.Ref getComponentRef() {
        return getRef();
    }

    /* loaded from: classes11.dex */
    private static class SequencerImpl implements SchemaTypeElementSequencer {
        private final SchemaTypeVisitorImpl _visitor;

        private SequencerImpl(SchemaTypeVisitorImpl visitor) {
            this._visitor = visitor;
        }

        @Override // org.apache.xmlbeans.SchemaTypeElementSequencer
        public boolean next(QName elementName) {
            if (this._visitor == null) {
                return false;
            }
            return this._visitor.visit(elementName);
        }

        @Override // org.apache.xmlbeans.SchemaTypeElementSequencer
        public boolean peek(QName elementName) {
            if (this._visitor == null) {
                return false;
            }
            return this._visitor.testValid(elementName);
        }
    }

    @Override // org.apache.xmlbeans.SchemaType
    public QNameSet qnameSetForWildcardElements() {
        SchemaParticle model = getContentModel();
        QNameSetBuilder wildcardSet = new QNameSetBuilder();
        computeWildcardSet(model, wildcardSet);
        QNameSetBuilder qnsb = new QNameSetBuilder(wildcardSet);
        SchemaProperty[] props = getElementProperties();
        for (SchemaProperty prop : props) {
            qnsb.remove(prop.getName());
        }
        return qnsb.toQNameSet();
    }

    private static void computeWildcardSet(SchemaParticle model, QNameSetBuilder result) {
        if (model.getParticleType() == 5) {
            QNameSet cws = model.getWildcardSet();
            result.addAll(cws);
            return;
        }
        for (int i = 0; i < model.countOfParticleChild(); i++) {
            SchemaParticle child = model.getParticleChild(i);
            computeWildcardSet(child, result);
        }
    }

    @Override // org.apache.xmlbeans.SchemaType
    public QNameSet qnameSetForWildcardAttributes() {
        SchemaAttributeModel model = getAttributeModel();
        QNameSet wildcardSet = model.getWildcardSet();
        if (wildcardSet == null) {
            return QNameSet.EMPTY;
        }
        QNameSetBuilder qnsb = new QNameSetBuilder(wildcardSet);
        SchemaProperty[] props = getAttributeProperties();
        for (SchemaProperty prop : props) {
            qnsb.remove(prop.getName());
        }
        return qnsb.toQNameSet();
    }

    @Override // org.apache.xmlbeans.SchemaType
    public String getDocumentation() {
        if (this._documentation == null) {
            this._documentation = parseDocumentation(this._parseObject);
        }
        return this._documentation;
    }

    private static String parseDocumentation(XmlObject lcti) {
        String str = lcti.toString();
        try {
            Element el = Element.Factory.parse(str);
            AnnotationDocument.Annotation ann = el.getAnnotation();
            if (ann == null || ann.sizeOfDocumentationArray() == 0) {
                return "";
            }
            StringBuilder docBody = new StringBuilder();
            for (DocumentationDocument.Documentation documentation : ann.getDocumentationArray()) {
                XmlCursor c = documentation.newCursor();
                try {
                    if (c.getChars() != null) {
                        docBody.append(c.getTextValue());
                    }
                    if (c != null) {
                        c.close();
                    }
                } finally {
                }
            }
            return docBody.toString();
        } catch (Throwable e) {
            if (ExceptionUtil.isFatal(e)) {
                ExceptionUtil.rethrow(e);
            }
            return "";
        }
    }
}
