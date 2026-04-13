package org.apache.xmlbeans.impl.schema;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import javax.xml.namespace.QName;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.BindingConfig;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SystemProperties;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ResolverUtil;
import org.apache.xmlbeans.impl.util.HexBin;
import org.apache.xmlbeans.impl.values.XmlStringImpl;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.xml.sax.EntityResolver;

/* loaded from: classes11.dex */
public class StscState {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String PROJECT_URL_PREFIX = "project://local";
    private boolean _allowPartial;
    private final List<SchemaAnnotation> _annotations;
    private final Map<QName, SchemaAttributeGroup> _attributeGroups;
    private final Map<QName, SchemaType> _attributeTypes;
    private URI _baseURI;
    private Map<QName, QName> _compatMap;
    private BindingConfig _config;
    private final Map<String, SchemaContainer> _containers;
    private SchemaDependencies _dependencies;
    private byte[] _digest;
    private final Map<QName, SchemaType> _documentTypes;
    private boolean _doingDownloads;
    private EntityResolver _entityResolver;
    private Collection<XmlError> _errorListener;
    private String _givenStsName;
    private final Map<QName, SchemaGlobalAttribute> _globalAttributes;
    private final Map<QName, SchemaGlobalElement> _globalElements;
    private final Map<QName, SchemaType> _globalTypes;
    private final Map<QName, SchemaIdentityConstraint> _idConstraints;
    private SchemaTypeLoader _importingLoader;
    private boolean _mdefAll;
    private final Set<String> _mdefNamespaces;
    private final Map<String, SchemaComponent> _misspelledNames;
    private final Map<QName, SchemaModelGroup> _modelGroups;
    private final Set<String> _namespaces;
    private boolean _noAnn;
    private boolean _noDigest;
    private boolean _noPvr;
    private boolean _noUpa;
    private final Set<SchemaComponent> _processingGroups;
    private int _recoveredErrors;
    private final Map<SchemaAttributeGroupImpl, SchemaAttributeGroupImpl> _redefinedAttributeGroups;
    private final Map<SchemaTypeImpl, SchemaTypeImpl> _redefinedGlobalTypes;
    private final Map<SchemaModelGroupImpl, SchemaModelGroupImpl> _redefinedModelGroups;
    private final SchemaTypeLoader _s4sloader;
    private File _schemasDir;
    private String _sourceCodeEncoding;
    private final Map<String, String> _sourceForUri;
    private SchemaTypeSystemImpl _target;
    private final Map<String, SchemaType> _typesByClassname;
    private static final XmlValueRef XMLSTR_PRESERVE = buildString("preserve");
    private static final XmlValueRef XMLSTR_REPLACE = buildString("preserve");
    private static final XmlValueRef XMLSTR_COLLAPSE = buildString("preserve");
    static final SchemaType[] EMPTY_ST_ARRAY = new SchemaType[0];
    private static final XmlValueRef[] FACETS_NONE = new XmlValueRef[12];
    private static final boolean[] FIXED_FACETS_NONE = new boolean[12];
    private static final boolean[] FIXED_FACETS_WS = new boolean[12];
    private static final XmlValueRef[] FACETS_WS_COLLAPSE = {null, null, null, null, null, null, null, null, null, build_wsstring(3), null, null};
    static final XmlValueRef[] FACETS_UNION = FACETS_NONE;
    static final boolean[] FIXED_FACETS_UNION = FIXED_FACETS_NONE;
    static final XmlValueRef[] FACETS_LIST = FACETS_WS_COLLAPSE;
    static final boolean[] FIXED_FACETS_LIST = FIXED_FACETS_WS;
    private static final ThreadLocal<StscStack> tl_stscStack = new ThreadLocal<>();

    private static Set<String> buildDefaultMdefNamespaces() {
        return new HashSet(Collections.singletonList("http://www.openuri.org/2002/04/soap/conversation/"));
    }

    private StscState() {
        this._digest = null;
        this._noDigest = false;
        this._allowPartial = false;
        this._recoveredErrors = 0;
        this._containers = new LinkedHashMap();
        this._redefinedGlobalTypes = new LinkedHashMap();
        this._redefinedModelGroups = new LinkedHashMap();
        this._redefinedAttributeGroups = new LinkedHashMap();
        this._globalTypes = new LinkedHashMap();
        this._globalElements = new LinkedHashMap();
        this._globalAttributes = new LinkedHashMap();
        this._modelGroups = new LinkedHashMap();
        this._attributeGroups = new LinkedHashMap();
        this._documentTypes = new LinkedHashMap();
        this._attributeTypes = new LinkedHashMap();
        this._typesByClassname = new LinkedHashMap();
        this._misspelledNames = new HashMap();
        this._processingGroups = new LinkedHashSet();
        this._idConstraints = new LinkedHashMap();
        this._namespaces = new HashSet();
        this._annotations = new ArrayList();
        this._mdefNamespaces = buildDefaultMdefNamespaces();
        this._sourceForUri = new HashMap();
        this._baseURI = URI.create("project://local/");
        this._s4sloader = XmlBeans.typeLoaderForClassLoader(SchemaDocument.class.getClassLoader());
    }

    public void initFromTypeSystem(SchemaTypeSystemImpl system, Set<String> newNamespaces) {
        SchemaContainer[] containers = system.containers();
        for (SchemaContainer container : containers) {
            if (!newNamespaces.contains(container.getNamespace())) {
                addContainer(container);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addNewContainer(String namespace) {
        if (this._containers.containsKey(namespace)) {
            return;
        }
        SchemaContainer container = new SchemaContainer(namespace);
        container.setTypeSystem(sts());
        addNamespace(namespace);
        this._containers.put(namespace, container);
    }

    private void addContainer(SchemaContainer container) {
        this._containers.put(container.getNamespace(), container);
        container.globalElements().forEach(new Consumer() { // from class: org.apache.xmlbeans.impl.schema.StscState$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                StscState.this.m2606lambda$addContainer$0$orgapachexmlbeansimplschemaStscState((SchemaGlobalElement) obj);
            }
        });
        container.globalAttributes().forEach(new Consumer() { // from class: org.apache.xmlbeans.impl.schema.StscState$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                StscState.this.m2607lambda$addContainer$1$orgapachexmlbeansimplschemaStscState((SchemaGlobalAttribute) obj);
            }
        });
        container.modelGroups().forEach(new Consumer() { // from class: org.apache.xmlbeans.impl.schema.StscState$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                StscState.this.m2608lambda$addContainer$2$orgapachexmlbeansimplschemaStscState((SchemaModelGroup) obj);
            }
        });
        container.attributeGroups().forEach(new Consumer() { // from class: org.apache.xmlbeans.impl.schema.StscState$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                StscState.this.m2609lambda$addContainer$3$orgapachexmlbeansimplschemaStscState((SchemaAttributeGroup) obj);
            }
        });
        container.globalTypes().forEach(mapTypes(this._globalTypes, false));
        container.documentTypes().forEach(mapTypes(this._documentTypes, true));
        container.attributeTypes().forEach(mapTypes(this._attributeTypes, true));
        container.identityConstraints().forEach(new Consumer() { // from class: org.apache.xmlbeans.impl.schema.StscState$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                StscState.this.m2610lambda$addContainer$4$orgapachexmlbeansimplschemaStscState((SchemaIdentityConstraint) obj);
            }
        });
        this._annotations.addAll(container.annotations());
        this._namespaces.add(container.getNamespace());
        container.unsetImmutable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$addContainer$0$org-apache-xmlbeans-impl-schema-StscState, reason: not valid java name */
    public /* synthetic */ void m2606lambda$addContainer$0$orgapachexmlbeansimplschemaStscState(SchemaGlobalElement g) {
        this._globalElements.put(g.getName(), g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$addContainer$1$org-apache-xmlbeans-impl-schema-StscState, reason: not valid java name */
    public /* synthetic */ void m2607lambda$addContainer$1$orgapachexmlbeansimplschemaStscState(SchemaGlobalAttribute g) {
        this._globalAttributes.put(g.getName(), g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$addContainer$2$org-apache-xmlbeans-impl-schema-StscState, reason: not valid java name */
    public /* synthetic */ void m2608lambda$addContainer$2$orgapachexmlbeansimplschemaStscState(SchemaModelGroup g) {
        this._modelGroups.put(g.getName(), g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$addContainer$3$org-apache-xmlbeans-impl-schema-StscState, reason: not valid java name */
    public /* synthetic */ void m2609lambda$addContainer$3$orgapachexmlbeansimplschemaStscState(SchemaAttributeGroup g) {
        this._attributeGroups.put(g.getName(), g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$addContainer$4$org-apache-xmlbeans-impl-schema-StscState, reason: not valid java name */
    public /* synthetic */ void m2610lambda$addContainer$4$orgapachexmlbeansimplschemaStscState(SchemaIdentityConstraint g) {
        this._idConstraints.put(g.getName(), g);
    }

    private Consumer<SchemaType> mapTypes(final Map<QName, SchemaType> map, final boolean useProperties) {
        return new Consumer() { // from class: org.apache.xmlbeans.impl.schema.StscState$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                StscState.this.m2611lambda$mapTypes$5$orgapachexmlbeansimplschemaStscState(useProperties, map, (SchemaType) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$mapTypes$5$org-apache-xmlbeans-impl-schema-StscState, reason: not valid java name */
    public /* synthetic */ void m2611lambda$mapTypes$5$orgapachexmlbeansimplschemaStscState(boolean useProperties, Map map, SchemaType t) {
        QName name = useProperties ? t.getProperties()[0].getName() : t.getName();
        map.put(name, t);
        if (t.getFullJavaName() != null) {
            addClassname(t.getFullJavaName(), t);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaContainer getContainer(String namespace) {
        return this._containers.get(namespace);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, SchemaContainer> getContainerMap() {
        return Collections.unmodifiableMap(this._containers);
    }

    void registerDependency(String sourceNs, String targetNs) {
        this._dependencies.registerDependency(sourceNs, targetNs);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerContribution(String ns, String fileUrl) {
        this._dependencies.registerContribution(ns, fileUrl);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaDependencies getDependencies() {
        return this._dependencies;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDependencies(SchemaDependencies deps) {
        this._dependencies = deps;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isFileProcessed(String url) {
        return this._dependencies.isFileRepresented(url);
    }

    public void setImportingTypeLoader(SchemaTypeLoader loader) {
        this._importingLoader = loader;
    }

    public void setErrorListener(Collection<XmlError> errorListener) {
        this._errorListener = errorListener;
    }

    public void error(String message, int code, XmlObject loc) {
        addError(this._errorListener, message, code, loc);
    }

    public void error(String code, Object[] args, XmlObject loc) {
        addError(this._errorListener, code, args, loc);
    }

    public void recover(String code, Object[] args, XmlObject loc) {
        addError(this._errorListener, code, args, loc);
        this._recoveredErrors++;
    }

    public void warning(String message, int code, XmlObject loc) {
        addWarning(this._errorListener, message, code, loc);
    }

    public void warning(String code, Object[] args, XmlObject loc) {
        if (XmlErrorCodes.RESERVED_TYPE_NAME.equals(code) && loc.documentProperties().getSourceName() != null && loc.documentProperties().getSourceName().indexOf("XMLSchema.xsd") > 0) {
            return;
        }
        addWarning(this._errorListener, code, args, loc);
    }

    public void info(String message) {
        addInfo(this._errorListener, message);
    }

    public void info(String code, Object[] args) {
        addInfo(this._errorListener, code, args);
    }

    public static void addError(Collection<XmlError> errorListener, String message, int code, XmlObject location) {
        XmlError err = XmlError.forObject(message, 0, location);
        errorListener.add(err);
    }

    public static void addError(Collection<XmlError> errorListener, String code, Object[] args, XmlObject location) {
        XmlError err = XmlError.forObject(code, args, 0, location);
        errorListener.add(err);
    }

    public static void addError(Collection<XmlError> errorListener, String code, Object[] args, File location) {
        XmlError err = XmlError.forLocation(code, args, 0, location.toURI().toString(), 0, 0, 0);
        errorListener.add(err);
    }

    public static void addError(Collection<XmlError> errorListener, String code, Object[] args, URL location) {
        XmlError err = XmlError.forLocation(code, args, 0, location.toString(), 0, 0, 0);
        errorListener.add(err);
    }

    public static void addWarning(Collection<XmlError> errorListener, String message, int code, XmlObject location) {
        XmlError err = XmlError.forObject(message, 1, location);
        errorListener.add(err);
    }

    public static void addWarning(Collection<XmlError> errorListener, String code, Object[] args, XmlObject location) {
        XmlError err = XmlError.forObject(code, args, 1, location);
        errorListener.add(err);
    }

    public static void addInfo(Collection<XmlError> errorListener, String message) {
        XmlError err = XmlError.forMessage(message, 2);
        errorListener.add(err);
    }

    public static void addInfo(Collection<XmlError> errorListener, String code, Object[] args) {
        XmlError err = XmlError.forMessage(code, args, 2);
        errorListener.add(err);
    }

    public void setGivenTypeSystemName(String name) {
        this._givenStsName = name;
    }

    public void setTargetSchemaTypeSystem(SchemaTypeSystemImpl target) {
        this._target = target;
    }

    public void addSchemaDigest(byte[] digest) {
        if (this._noDigest) {
            return;
        }
        if (digest == null) {
            this._noDigest = true;
            this._digest = null;
            return;
        }
        if (this._digest == null) {
            this._digest = new byte[16];
        }
        int len = this._digest.length;
        if (digest.length < len) {
            len = digest.length;
        }
        for (int i = 0; i < len; i++) {
            byte[] bArr = this._digest;
            bArr[i] = (byte) (bArr[i] ^ digest[i]);
        }
    }

    public SchemaTypeSystemImpl sts() {
        if (this._target != null) {
            return this._target;
        }
        String name = this._givenStsName;
        if (name == null && this._digest != null) {
            name = "s" + new String(HexBin.encode(this._digest), StandardCharsets.ISO_8859_1);
        }
        this._target = new SchemaTypeSystemImpl(name);
        return this._target;
    }

    public boolean shouldDownloadURI(String uriString) {
        if (this._doingDownloads) {
            return true;
        }
        if (uriString == null) {
            return false;
        }
        try {
            URI uri = new URI(uriString);
            if (!uri.getScheme().equalsIgnoreCase(ArchiveStreamFactory.JAR) && !uri.getScheme().equalsIgnoreCase(ArchiveStreamFactory.ZIP)) {
                return uri.getScheme().equalsIgnoreCase("file");
            }
            String s = uri.getSchemeSpecificPart();
            int i = s.lastIndexOf(33);
            return shouldDownloadURI(i > 0 ? s.substring(0, i) : s);
        } catch (Exception e) {
            return false;
        }
    }

    public void setOptions(XmlOptions options) {
        if (options == null) {
            return;
        }
        this._allowPartial = options.isCompilePartialTypesystem();
        this._compatMap = options.getCompileSubstituteNames();
        this._noUpa = options.isCompileNoUpaRule() || !"true".equals(SystemProperties.getProperty("xmlbean.uniqueparticleattribution", "true"));
        this._noPvr = options.isCompileNoPvrRule() || !"true".equals(SystemProperties.getProperty("xmlbean.particlerestriction", "true"));
        this._noAnn = options.isCompileNoAnnotations() || !"true".equals(SystemProperties.getProperty("xmlbean.schemaannotations", "true"));
        this._doingDownloads = options.isCompileDownloadUrls() || "true".equals(SystemProperties.getProperty("xmlbean.downloadurls", "false"));
        this._sourceCodeEncoding = options.getCharacterEncoding();
        if (this._sourceCodeEncoding == null || this._sourceCodeEncoding.isEmpty()) {
            this._sourceCodeEncoding = SystemProperties.getProperty("xmlbean.sourcecodeencoding");
        }
        this._entityResolver = options.getEntityResolver();
        if (this._entityResolver == null) {
            this._entityResolver = ResolverUtil.getGlobalEntityResolver();
        }
        if (this._entityResolver != null) {
            this._doingDownloads = true;
        }
        Set<String> mdef = options.getCompileMdefNamespaces();
        if (mdef != null) {
            this._mdefNamespaces.addAll(mdef);
            if (this._mdefNamespaces.contains("##local")) {
                this._mdefNamespaces.remove("##local");
                this._mdefNamespaces.add("");
            }
            if (this._mdefNamespaces.contains("##any")) {
                this._mdefNamespaces.remove("##any");
                this._mdefAll = true;
            }
        }
    }

    public EntityResolver getEntityResolver() {
        return this._entityResolver;
    }

    public boolean noUpa() {
        return this._noUpa;
    }

    public boolean noPvr() {
        return this._noPvr;
    }

    public boolean noAnn() {
        return this._noAnn;
    }

    public boolean allowPartial() {
        return this._allowPartial;
    }

    public String sourceCodeEncoding() {
        return this._sourceCodeEncoding;
    }

    public int getRecovered() {
        return this._recoveredErrors;
    }

    private QName compatName(QName name, String chameleonNamespace) {
        if (name.getNamespaceURI().isEmpty() && chameleonNamespace != null && chameleonNamespace.length() > 0) {
            name = new QName(chameleonNamespace, name.getLocalPart());
        }
        if (this._compatMap == null) {
            return name;
        }
        QName subst = this._compatMap.get(name);
        if (subst == null) {
            return name;
        }
        return subst;
    }

    public void setBindingConfig(BindingConfig config) throws IllegalArgumentException {
        this._config = config;
    }

    public BindingConfig getBindingConfig() throws IllegalArgumentException {
        return this._config;
    }

    public String getPackageOverride(String namespace) {
        if (this._config == null) {
            return null;
        }
        return this._config.lookupPackageForNamespace(namespace);
    }

    public String getJavaPrefix(String namespace) {
        if (this._config == null) {
            return null;
        }
        return this._config.lookupPrefixForNamespace(namespace);
    }

    public String getJavaSuffix(String namespace) {
        if (this._config == null) {
            return null;
        }
        return this._config.lookupSuffixForNamespace(namespace);
    }

    public String getJavaname(QName qname, int kind) {
        if (this._config == null) {
            return null;
        }
        return this._config.lookupJavanameForQName(qname, kind);
    }

    private static String crunchName(QName name) {
        return name.getLocalPart().toLowerCase(Locale.ROOT);
    }

    void addSpelling(QName name, SchemaComponent comp) {
        this._misspelledNames.put(crunchName(name), comp);
    }

    SchemaComponent findSpelling(QName name) {
        return this._misspelledNames.get(crunchName(name));
    }

    void addNamespace(String targetNamespace) {
        this._namespaces.add(targetNamespace);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] getNamespaces() {
        return (String[]) this._namespaces.toArray(new String[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean linkerDefinesNamespace(String namespace) {
        return this._importingLoader.isNamespaceDefined(namespace);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaTypeImpl findGlobalType(QName name, String chameleonNamespace, String sourceNamespace) {
        QName name2 = compatName(name, chameleonNamespace);
        SchemaTypeImpl result = (SchemaTypeImpl) this._globalTypes.get(name2);
        boolean foundOnLoader = false;
        if (result == null) {
            result = (SchemaTypeImpl) this._importingLoader.findType(name2);
            foundOnLoader = result != null;
        }
        if (!foundOnLoader && sourceNamespace != null) {
            registerDependency(sourceNamespace, name2.getNamespaceURI());
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaTypeImpl findRedefinedGlobalType(QName name, String chameleonNamespace, SchemaTypeImpl redefinedBy) {
        QName redefinedName = redefinedBy.getName();
        QName name2 = compatName(name, chameleonNamespace);
        if (name2.equals(redefinedName)) {
            return this._redefinedGlobalTypes.get(redefinedBy);
        }
        SchemaTypeImpl result = (SchemaTypeImpl) this._globalTypes.get(name2);
        if (result == null) {
            return (SchemaTypeImpl) this._importingLoader.findType(name2);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addGlobalType(SchemaTypeImpl type, SchemaTypeImpl redefined) {
        if (type != null) {
            QName name = type.getName();
            SchemaContainer container = getContainer(name.getNamespaceURI());
            if (container == null || container != type.getContainer()) {
                throw new AssertionError();
            }
            if (redefined != null) {
                if (this._redefinedGlobalTypes.containsKey(redefined)) {
                    if (!ignoreMdef(name)) {
                        if (this._mdefAll) {
                            warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"global type", QNameHelper.pretty(name), this._redefinedGlobalTypes.get(redefined).getSourceName()}, type.getParseObject());
                            return;
                        } else {
                            error(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"global type", QNameHelper.pretty(name), this._redefinedGlobalTypes.get(redefined).getSourceName()}, type.getParseObject());
                            return;
                        }
                    }
                    return;
                }
                this._redefinedGlobalTypes.put(redefined, type);
                container.addRedefinedType(type.getRef());
                return;
            }
            if (this._globalTypes.containsKey(name)) {
                if (!ignoreMdef(name)) {
                    if (this._mdefAll) {
                        warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"global type", QNameHelper.pretty(name), this._globalTypes.get(name).getSourceName()}, type.getParseObject());
                        return;
                    } else {
                        error(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"global type", QNameHelper.pretty(name), this._globalTypes.get(name).getSourceName()}, type.getParseObject());
                        return;
                    }
                }
                return;
            }
            this._globalTypes.put(name, type);
            container.addGlobalType(type.getRef());
            addSpelling(name, type);
        }
    }

    private boolean ignoreMdef(QName name) {
        return this._mdefNamespaces.contains(name.getNamespaceURI());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaType[] globalTypes() {
        return (SchemaType[]) this._globalTypes.values().toArray(new SchemaType[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaType[] redefinedGlobalTypes() {
        return (SchemaType[]) this._redefinedGlobalTypes.values().toArray(new SchemaType[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaTypeImpl findDocumentType(QName name, String chameleonNamespace, String sourceNamespace) {
        QName name2 = compatName(name, chameleonNamespace);
        SchemaTypeImpl result = (SchemaTypeImpl) this._documentTypes.get(name2);
        boolean foundOnLoader = false;
        if (result == null) {
            result = (SchemaTypeImpl) this._importingLoader.findDocumentType(name2);
            foundOnLoader = result != null;
        }
        if (!foundOnLoader && sourceNamespace != null) {
            registerDependency(sourceNamespace, name2.getNamespaceURI());
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addDocumentType(SchemaTypeImpl type, QName name) {
        if (this._documentTypes.containsKey(name)) {
            if (!ignoreMdef(name)) {
                if (this._mdefAll) {
                    warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"global element", QNameHelper.pretty(name), this._documentTypes.get(name).getSourceName()}, type.getParseObject());
                    return;
                } else {
                    error(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"global element", QNameHelper.pretty(name), this._documentTypes.get(name).getSourceName()}, type.getParseObject());
                    return;
                }
            }
            return;
        }
        this._documentTypes.put(name, type);
        SchemaContainer container = getContainer(name.getNamespaceURI());
        if (container == null || container != type.getContainer()) {
            throw new AssertionError();
        }
        container.addDocumentType(type.getRef());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaType[] documentTypes() {
        return (SchemaType[]) this._documentTypes.values().toArray(new SchemaType[0]);
    }

    SchemaTypeImpl findAttributeType(QName name, String chameleonNamespace, String sourceNamespace) {
        QName name2 = compatName(name, chameleonNamespace);
        SchemaTypeImpl result = (SchemaTypeImpl) this._attributeTypes.get(name2);
        boolean foundOnLoader = false;
        if (result == null) {
            result = (SchemaTypeImpl) this._importingLoader.findAttributeType(name2);
            foundOnLoader = result != null;
        }
        if (!foundOnLoader && sourceNamespace != null) {
            registerDependency(sourceNamespace, name2.getNamespaceURI());
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addAttributeType(SchemaTypeImpl type, QName name) {
        if (this._attributeTypes.containsKey(name)) {
            if (!ignoreMdef(name)) {
                if (this._mdefAll) {
                    warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"global attribute", QNameHelper.pretty(name), this._attributeTypes.get(name).getSourceName()}, type.getParseObject());
                    return;
                } else {
                    error(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"global attribute", QNameHelper.pretty(name), this._attributeTypes.get(name).getSourceName()}, type.getParseObject());
                    return;
                }
            }
            return;
        }
        this._attributeTypes.put(name, type);
        SchemaContainer container = getContainer(name.getNamespaceURI());
        if (container == null || container != type.getContainer()) {
            throw new AssertionError();
        }
        container.addAttributeType(type.getRef());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaType[] attributeTypes() {
        return (SchemaType[]) this._attributeTypes.values().toArray(new SchemaType[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaGlobalAttributeImpl findGlobalAttribute(QName name, String chameleonNamespace, String sourceNamespace) {
        QName name2 = compatName(name, chameleonNamespace);
        SchemaGlobalAttributeImpl result = (SchemaGlobalAttributeImpl) this._globalAttributes.get(name2);
        boolean foundOnLoader = false;
        if (result == null) {
            result = (SchemaGlobalAttributeImpl) this._importingLoader.findAttribute(name2);
            foundOnLoader = result != null;
        }
        if (!foundOnLoader && sourceNamespace != null) {
            registerDependency(sourceNamespace, name2.getNamespaceURI());
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addGlobalAttribute(SchemaGlobalAttributeImpl attribute) {
        if (attribute != null) {
            QName name = attribute.getName();
            this._globalAttributes.put(name, attribute);
            addSpelling(name, attribute);
            SchemaContainer container = getContainer(name.getNamespaceURI());
            if (container == null || container != attribute.getContainer()) {
                throw new AssertionError();
            }
            container.addGlobalAttribute(attribute.getRef());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaGlobalAttribute[] globalAttributes() {
        return (SchemaGlobalAttribute[]) this._globalAttributes.values().toArray(new SchemaGlobalAttribute[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaGlobalElementImpl findGlobalElement(QName name, String chameleonNamespace, String sourceNamespace) {
        QName name2 = compatName(name, chameleonNamespace);
        SchemaGlobalElementImpl result = (SchemaGlobalElementImpl) this._globalElements.get(name2);
        boolean foundOnLoader = false;
        if (result == null) {
            result = (SchemaGlobalElementImpl) this._importingLoader.findElement(name2);
            foundOnLoader = result != null;
        }
        if (!foundOnLoader && sourceNamespace != null) {
            registerDependency(sourceNamespace, name2.getNamespaceURI());
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addGlobalElement(SchemaGlobalElementImpl element) {
        if (element != null) {
            QName name = element.getName();
            this._globalElements.put(name, element);
            SchemaContainer container = getContainer(name.getNamespaceURI());
            if (container == null || container != element.getContainer()) {
                throw new AssertionError();
            }
            container.addGlobalElement(element.getRef());
            addSpelling(name, element);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaGlobalElement[] globalElements() {
        return (SchemaGlobalElement[]) this._globalElements.values().toArray(new SchemaGlobalElement[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaAttributeGroupImpl findAttributeGroup(QName name, String chameleonNamespace, String sourceNamespace) {
        QName name2 = compatName(name, chameleonNamespace);
        SchemaAttributeGroupImpl result = (SchemaAttributeGroupImpl) this._attributeGroups.get(name2);
        boolean foundOnLoader = false;
        if (result == null) {
            result = (SchemaAttributeGroupImpl) this._importingLoader.findAttributeGroup(name2);
            foundOnLoader = result != null;
        }
        if (!foundOnLoader && sourceNamespace != null) {
            registerDependency(sourceNamespace, name2.getNamespaceURI());
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaAttributeGroupImpl findRedefinedAttributeGroup(QName name, String chameleonNamespace, SchemaAttributeGroupImpl redefinedBy) {
        QName redefinitionFor = redefinedBy.getName();
        QName name2 = compatName(name, chameleonNamespace);
        if (name2.equals(redefinitionFor)) {
            return this._redefinedAttributeGroups.get(redefinedBy);
        }
        SchemaAttributeGroupImpl result = (SchemaAttributeGroupImpl) this._attributeGroups.get(name2);
        if (result == null) {
            return (SchemaAttributeGroupImpl) this._importingLoader.findAttributeGroup(name2);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addAttributeGroup(SchemaAttributeGroupImpl attributeGroup, SchemaAttributeGroupImpl redefined) {
        if (attributeGroup != null) {
            QName name = attributeGroup.getName();
            SchemaContainer container = getContainer(name.getNamespaceURI());
            if (container == null || container != attributeGroup.getContainer()) {
                throw new AssertionError();
            }
            if (redefined != null) {
                if (this._redefinedAttributeGroups.containsKey(redefined)) {
                    if (!ignoreMdef(name)) {
                        if (this._mdefAll) {
                            warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"attribute group", QNameHelper.pretty(name), this._redefinedAttributeGroups.get(redefined).getSourceName()}, attributeGroup.getParseObject());
                            return;
                        } else {
                            error(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"attribute group", QNameHelper.pretty(name), this._redefinedAttributeGroups.get(redefined).getSourceName()}, attributeGroup.getParseObject());
                            return;
                        }
                    }
                    return;
                }
                this._redefinedAttributeGroups.put(redefined, attributeGroup);
                container.addRedefinedAttributeGroup(attributeGroup.getRef());
                return;
            }
            if (this._attributeGroups.containsKey(name)) {
                if (!ignoreMdef(name)) {
                    if (this._mdefAll) {
                        warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"attribute group", QNameHelper.pretty(name), this._attributeGroups.get(name).getSourceName()}, attributeGroup.getParseObject());
                        return;
                    } else {
                        error(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"attribute group", QNameHelper.pretty(name), this._attributeGroups.get(name).getSourceName()}, attributeGroup.getParseObject());
                        return;
                    }
                }
                return;
            }
            this._attributeGroups.put(attributeGroup.getName(), attributeGroup);
            addSpelling(attributeGroup.getName(), attributeGroup);
            container.addAttributeGroup(attributeGroup.getRef());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaAttributeGroup[] attributeGroups() {
        return (SchemaAttributeGroup[]) this._attributeGroups.values().toArray(new SchemaAttributeGroup[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaAttributeGroup[] redefinedAttributeGroups() {
        return (SchemaAttributeGroup[]) this._redefinedAttributeGroups.values().toArray(new SchemaAttributeGroup[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaModelGroupImpl findModelGroup(QName name, String chameleonNamespace, String sourceNamespace) {
        QName name2 = compatName(name, chameleonNamespace);
        SchemaModelGroupImpl result = (SchemaModelGroupImpl) this._modelGroups.get(name2);
        boolean foundOnLoader = false;
        if (result == null) {
            result = (SchemaModelGroupImpl) this._importingLoader.findModelGroup(name2);
            foundOnLoader = result != null;
        }
        if (!foundOnLoader && sourceNamespace != null) {
            registerDependency(sourceNamespace, name2.getNamespaceURI());
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaModelGroupImpl findRedefinedModelGroup(QName name, String chameleonNamespace, SchemaModelGroupImpl redefinedBy) {
        QName redefinitionFor = redefinedBy.getName();
        QName name2 = compatName(name, chameleonNamespace);
        if (name2.equals(redefinitionFor)) {
            return this._redefinedModelGroups.get(redefinedBy);
        }
        SchemaModelGroupImpl result = (SchemaModelGroupImpl) this._modelGroups.get(name2);
        if (result == null) {
            return (SchemaModelGroupImpl) this._importingLoader.findModelGroup(name2);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addModelGroup(SchemaModelGroupImpl modelGroup, SchemaModelGroupImpl redefined) {
        if (modelGroup != null) {
            QName name = modelGroup.getName();
            SchemaContainer container = getContainer(name.getNamespaceURI());
            if (container == null || container != modelGroup.getContainer()) {
                throw new AssertionError();
            }
            if (redefined != null) {
                if (this._redefinedModelGroups.containsKey(redefined)) {
                    if (!ignoreMdef(name)) {
                        if (this._mdefAll) {
                            warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"model group", QNameHelper.pretty(name), this._redefinedModelGroups.get(redefined).getSourceName()}, modelGroup.getParseObject());
                            return;
                        } else {
                            error(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"model group", QNameHelper.pretty(name), this._redefinedModelGroups.get(redefined).getSourceName()}, modelGroup.getParseObject());
                            return;
                        }
                    }
                    return;
                }
                this._redefinedModelGroups.put(redefined, modelGroup);
                container.addRedefinedModelGroup(modelGroup.getRef());
                return;
            }
            if (this._modelGroups.containsKey(name)) {
                if (!ignoreMdef(name)) {
                    if (this._mdefAll) {
                        warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"model group", QNameHelper.pretty(name), this._modelGroups.get(name).getSourceName()}, modelGroup.getParseObject());
                        return;
                    } else {
                        error(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"model group", QNameHelper.pretty(name), this._modelGroups.get(name).getSourceName()}, modelGroup.getParseObject());
                        return;
                    }
                }
                return;
            }
            this._modelGroups.put(modelGroup.getName(), modelGroup);
            addSpelling(modelGroup.getName(), modelGroup);
            container.addModelGroup(modelGroup.getRef());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaModelGroup[] modelGroups() {
        return (SchemaModelGroup[]) this._modelGroups.values().toArray(new SchemaModelGroup[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaModelGroup[] redefinedModelGroups() {
        return (SchemaModelGroup[]) this._redefinedModelGroups.values().toArray(new SchemaModelGroup[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaIdentityConstraintImpl findIdConstraint(QName name, String chameleonNamespace, String sourceNamespace) {
        QName name2 = compatName(name, chameleonNamespace);
        if (sourceNamespace != null) {
            registerDependency(sourceNamespace, name2.getNamespaceURI());
        }
        return (SchemaIdentityConstraintImpl) this._idConstraints.get(name2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addIdConstraint(SchemaIdentityConstraintImpl idc) {
        if (idc != null) {
            QName name = idc.getName();
            SchemaContainer container = getContainer(name.getNamespaceURI());
            if (container == null || container != idc.getContainer()) {
                throw new AssertionError();
            }
            if (this._idConstraints.containsKey(name)) {
                if (!ignoreMdef(name)) {
                    warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"identity constraint", QNameHelper.pretty(name), this._idConstraints.get(name).getSourceName()}, idc.getParseObject());
                }
            } else {
                this._idConstraints.put(name, idc);
                addSpelling(idc.getName(), idc);
                container.addIdentityConstraint(idc.getRef());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaIdentityConstraintImpl[] idConstraints() {
        return (SchemaIdentityConstraintImpl[]) this._idConstraints.values().toArray(new SchemaIdentityConstraintImpl[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addAnnotation(SchemaAnnotationImpl ann, String targetNamespace) {
        if (ann != null) {
            SchemaContainer container = getContainer(targetNamespace);
            if (container == null || container != ann.getContainer()) {
                throw new AssertionError();
            }
            this._annotations.add(ann);
            container.addAnnotation(ann);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<SchemaAnnotation> annotations() {
        return this._annotations;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isProcessing(SchemaComponent obj) {
        return this._processingGroups.contains(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startProcessing(SchemaComponent obj) {
        if (this._processingGroups.contains(obj)) {
            throw new AssertionError();
        }
        this._processingGroups.add(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void finishProcessing(SchemaComponent obj) {
        if (!this._processingGroups.contains(obj)) {
            throw new AssertionError();
        }
        this._processingGroups.remove(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaComponent[] getCurrentProcessing() {
        return (SchemaComponent[]) this._processingGroups.toArray(new SchemaComponent[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, SchemaType> typesByClassname() {
        return Collections.unmodifiableMap(this._typesByClassname);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addClassname(String classname, SchemaType type) {
        this._typesByClassname.put(classname, type);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class StscStack {
        StscState current;
        List<StscState> stack;

        private StscStack() {
            this.stack = new ArrayList();
        }

        final StscState push() {
            this.stack.add(this.current);
            this.current = new StscState();
            return this.current;
        }

        final void pop() {
            this.current = this.stack.get(this.stack.size() - 1);
            this.stack.remove(this.stack.size() - 1);
        }
    }

    public static void clearThreadLocals() {
        tl_stscStack.remove();
    }

    public static StscState start() {
        StscStack stscStack = tl_stscStack.get();
        if (stscStack == null) {
            stscStack = new StscStack();
            tl_stscStack.set(stscStack);
        }
        return stscStack.push();
    }

    public static StscState get() {
        return tl_stscStack.get().current;
    }

    public static void end() {
        StscStack stscStack = tl_stscStack.get();
        stscStack.pop();
        if (stscStack.stack.isEmpty()) {
            tl_stscStack.remove();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static XmlValueRef build_wsstring(int wsr) {
        switch (wsr) {
            case 1:
                return XMLSTR_PRESERVE;
            case 2:
                return XMLSTR_REPLACE;
            case 3:
                return XMLSTR_COLLAPSE;
            default:
                return null;
        }
    }

    static XmlValueRef buildString(String str) {
        if (str == null) {
            return null;
        }
        try {
            XmlStringImpl i = new XmlStringImpl();
            i.setStringValue(str);
            i.setImmutable();
            return new XmlValueRef(i);
        } catch (XmlValueOutOfRangeException e) {
            return null;
        }
    }

    public void notFoundError(QName itemName, int code, XmlObject loc, boolean recovered) {
        String expected;
        String foundName;
        int i;
        String foundName2;
        String sourceName;
        QName name;
        String expectedName = QNameHelper.pretty(itemName);
        String found = null;
        String sourceName2 = null;
        if (recovered) {
            this._recoveredErrors++;
        }
        switch (code) {
            case 0:
                expected = ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY;
                break;
            case 1:
                expected = "element";
                break;
            case 2:
            default:
                throw new AssertionError();
            case 3:
                expected = "attribute";
                break;
            case 4:
                expected = "attribute group";
                break;
            case 5:
                expected = "identity constraint";
                break;
            case 6:
                expected = "model group";
                break;
        }
        SchemaComponent foundComponent = findSpelling(itemName);
        if (foundComponent == null || (name = foundComponent.getName()) == null) {
            foundName = null;
            i = 1;
            foundName2 = null;
            sourceName = null;
        } else {
            switch (foundComponent.getComponentType()) {
                case 0:
                    found = ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY;
                    sourceName2 = foundComponent.getSourceName();
                    break;
                case 1:
                    found = "element";
                    sourceName2 = foundComponent.getSourceName();
                    break;
                case 3:
                    found = "attribute";
                    sourceName2 = foundComponent.getSourceName();
                    break;
                case 4:
                    found = "attribute group";
                    break;
                case 6:
                    found = "model group";
                    break;
            }
            if (sourceName2 != null) {
                sourceName2 = sourceName2.substring(sourceName2.lastIndexOf(47) + 1);
            }
            if (name.equals(itemName)) {
                foundName = found;
                i = 1;
                foundName2 = null;
                sourceName = sourceName2;
            } else {
                String foundName3 = QNameHelper.pretty(name);
                foundName = found;
                i = 1;
                foundName2 = foundName3;
                sourceName = sourceName2;
            }
        }
        if (foundName == null) {
            error(XmlErrorCodes.SCHEMA_QNAME_RESOLVE, new Object[]{expected, expectedName}, loc);
            return;
        }
        Integer valueOf = Integer.valueOf(foundName2 == null ? 0 : i);
        if (sourceName == null) {
            i = 0;
        }
        error(XmlErrorCodes.SCHEMA_QNAME_RESOLVE$HELP, new Object[]{expected, expectedName, foundName, valueOf, foundName2, Integer.valueOf(i), sourceName}, loc);
    }

    public String sourceNameForUri(String uri) {
        return this._sourceForUri.get(uri);
    }

    public Map<String, String> sourceCopyMap() {
        return Collections.unmodifiableMap(this._sourceForUri);
    }

    public void setBaseUri(URI uri) {
        this._baseURI = uri;
    }

    public String relativize(String uri) {
        return relativize(uri, false);
    }

    public String computeSavedFilename(String uri) {
        return relativize(uri, true);
    }

    private String relativize(String uri, boolean forSavedFilename) {
        if (uri == null) {
            return null;
        }
        if (uri.startsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) {
            uri = PROJECT_URL_PREFIX + uri.replace(IOUtils.DIR_SEPARATOR_WINDOWS, '/');
        } else {
            int colon = uri.indexOf(58);
            if (colon <= 1 || !uri.substring(0, colon).matches("^\\w+$")) {
                uri = "project://local/" + uri.replace(IOUtils.DIR_SEPARATOR_WINDOWS, '/');
            }
        }
        if (this._baseURI != null) {
            try {
                URI relative = this._baseURI.relativize(new URI(uri));
                if (!relative.isAbsolute()) {
                    return relative.toString();
                }
                uri = relative.toString();
            } catch (URISyntaxException e) {
            }
        }
        if (!forSavedFilename) {
            return uri;
        }
        int lastslash = uri.lastIndexOf(47);
        String dir = QNameHelper.hexsafe(lastslash == -1 ? "" : uri.substring(0, lastslash));
        int question = uri.indexOf(63, lastslash + 1);
        if (question == -1) {
            return dir + PackagingURIHelper.FORWARD_SLASH_STRING + uri.substring(lastslash + 1);
        }
        String query = QNameHelper.hexsafe(uri.substring(question));
        return query.startsWith(QNameHelper.URI_SHA1_PREFIX) ? dir + PackagingURIHelper.FORWARD_SLASH_STRING + uri.substring(lastslash + 1, question) : dir + PackagingURIHelper.FORWARD_SLASH_STRING + uri.substring(lastslash + 1, question) + query;
    }

    public void addSourceUri(String uri, String nameToUse) {
        if (uri == null) {
            return;
        }
        if (nameToUse == null) {
            nameToUse = computeSavedFilename(uri);
        }
        this._sourceForUri.put(uri, nameToUse);
    }

    public Collection<XmlError> getErrorListener() {
        return this._errorListener;
    }

    public SchemaTypeLoader getS4SLoader() {
        return this._s4sloader;
    }

    public File getSchemasDir() {
        return this._schemasDir;
    }

    public void setSchemasDir(File _schemasDir) {
        this._schemasDir = _schemasDir;
    }
}
