package org.apache.xmlbeans.impl.schema;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.xml.namespace.QName;
import org.apache.commons.lang3.SystemProperties;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.ss.formula.ptg.ParenthesisPtg;
import org.apache.xmlbeans.Filer;
import org.apache.xmlbeans.ResourceLoader;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SchemaTypeLoaderException;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.impl.common.DefaultClassLoaderResourceLoader;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.apache.xmlbeans.impl.common.XBeanDebug;
import org.apache.xmlbeans.impl.util.ExceptionUtil;
import org.apache.xmlbeans.impl.util.FilerImpl;
import org.apache.xmlbeans.impl.util.HexBin;
import org.apache.xmlbeans.impl.util.LongUTFDataInputStream;
import org.apache.xmlbeans.impl.util.LongUTFDataOutputStream;

/* loaded from: classes11.dex */
public class SchemaTypeSystemImpl extends SchemaTypeLoaderBase implements SchemaTypeSystem {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DATA_BABE = -629491010;
    public static final int FIELD_GLOBAL = 1;
    public static final int FIELD_LOCALATTR = 2;
    public static final int FIELD_LOCALELT = 3;
    public static final int FIELD_NONE = 0;
    public static final int FILETYPE_SCHEMAATTRIBUTE = 4;
    public static final int FILETYPE_SCHEMAATTRIBUTEGROUP = 7;
    public static final int FILETYPE_SCHEMAELEMENT = 3;
    public static final int FILETYPE_SCHEMAIDENTITYCONSTRAINT = 8;
    public static final int FILETYPE_SCHEMAINDEX = 1;
    public static final int FILETYPE_SCHEMAMODELGROUP = 6;
    public static final int FILETYPE_SCHEMAPOINTER = 5;
    public static final int FILETYPE_SCHEMATYPE = 2;
    static final int FLAG_ABSTRACT = 262144;
    static final int FLAG_ATTRIBUTE_TYPE = 524288;
    static final int FLAG_BLOCK_EXT = 4096;
    static final int FLAG_BLOCK_REST = 8192;
    static final int FLAG_BOUNDED = 8;
    static final int FLAG_COMPILED = 2048;
    static final int FLAG_DOCUMENT_TYPE = 2;
    static final int FLAG_FINAL_EXT = 16384;
    static final int FLAG_FINAL_LIST = 131072;
    static final int FLAG_FINAL_REST = 32768;
    static final int FLAG_FINAL_UNION = 65536;
    static final int FLAG_FINITE = 16;
    static final int FLAG_HAS_PATTERN = 256;
    static final int FLAG_NUMERIC = 32;
    static final int FLAG_ORDERED = 4;
    static final int FLAG_ORDER_SENSITIVE = 512;
    public static final int FLAG_PART_ABSTRACT = 128;
    public static final int FLAG_PART_BLOCKEXT = 16;
    public static final int FLAG_PART_BLOCKREST = 32;
    public static final int FLAG_PART_BLOCKSUBST = 64;
    public static final int FLAG_PART_FINALEXT = 256;
    public static final int FLAG_PART_FINALREST = 512;
    public static final int FLAG_PART_FIXED = 4;
    public static final int FLAG_PART_NILLABLE = 8;
    public static final int FLAG_PART_SKIPPABLE = 1;
    public static final int FLAG_PROP_ISATTR = 1;
    public static final int FLAG_PROP_JAVAARRAY = 8;
    public static final int FLAG_PROP_JAVAOPTIONAL = 4;
    public static final int FLAG_PROP_JAVASINGLETON = 2;
    static final int FLAG_SIMPLE_TYPE = 1;
    static final int FLAG_STRINGENUM = 64;
    static final int FLAG_TOTAL_ORDER = 1024;
    static final int FLAG_UNION_OF_LISTS = 128;
    public static final int MAJOR_VERSION = 2;
    public static final int MINOR_VERSION = 24;
    public static final int RELEASE_NUMBER = 0;
    private static Random _random;
    private List<SchemaAnnotation> _annotations;
    private Map<QName, SchemaComponent.Ref> _attributeGroups;
    private Map<QName, SchemaComponent.Ref> _attributeTypes;
    private ClassLoader _classloader;
    private SchemaDependencies _deps;
    private Map<QName, SchemaComponent.Ref> _documentTypes;
    private Filer _filer;
    private Map<QName, SchemaComponent.Ref> _globalAttributes;
    private Map<QName, SchemaComponent.Ref> _globalElements;
    private Map<QName, SchemaComponent.Ref> _globalTypes;
    SchemaTypeLoader _linker;
    private SchemaTypePool _localHandles;
    private Map<QName, SchemaComponent.Ref> _modelGroups;
    private final String _name;
    private Set<String> _namespaces;
    private List<SchemaComponent.Ref> _redefinedAttributeGroups;
    private List<SchemaComponent.Ref> _redefinedGlobalTypes;
    private List<SchemaComponent.Ref> _redefinedModelGroups;
    private ResourceLoader _resourceLoader;
    private String _sourceCodeEncoding;
    private static final Pattern packPat = Pattern.compile("^(.+)(\\.[^.]+){2}$");
    public static String METADATA_PACKAGE_GEN = "org/apache/xmlbeans/metadata";
    private static final SchemaType[] EMPTY_ST_ARRAY = new SchemaType[0];
    private static final SchemaGlobalElement[] EMPTY_GE_ARRAY = new SchemaGlobalElement[0];
    private static final SchemaGlobalAttribute[] EMPTY_GA_ARRAY = new SchemaGlobalAttribute[0];
    private static final SchemaModelGroup[] EMPTY_MG_ARRAY = new SchemaModelGroup[0];
    private static final SchemaAttributeGroup[] EMPTY_AG_ARRAY = new SchemaAttributeGroup[0];
    private static final SchemaIdentityConstraint[] EMPTY_IC_ARRAY = new SchemaIdentityConstraint[0];
    private static final SchemaAnnotation[] EMPTY_ANN_ARRAY = new SchemaAnnotation[0];
    private static final byte[] _mask = new byte[16];
    static final byte[] SINGLE_ZERO_BYTE = {0};
    private boolean _incomplete = false;
    private Map<String, SchemaContainer> _containers = new HashMap();
    private Map<QName, SchemaComponent.Ref> _identityConstraints = Collections.emptyMap();
    private Map<String, SchemaComponent.Ref> _typeRefsByClassname = new HashMap();
    private final Map<String, SchemaComponent> _resolvedHandles = new HashMap();
    private boolean _allNonGroupHandlesResolved = false;

    public static /* synthetic */ LinkedHashMap $r8$lambda$9jZrLfmN7qos7GZMvkzgNgs9WjU() {
        return new LinkedHashMap();
    }

    static String nameToPathString(String nameForSystem) {
        String nameForSystem2 = nameForSystem.replace('.', '/');
        if (!nameForSystem2.endsWith(PackagingURIHelper.FORWARD_SLASH_STRING) && nameForSystem2.length() > 0) {
            return nameForSystem2 + PackagingURIHelper.FORWARD_SLASH_STRING;
        }
        return nameForSystem2;
    }

    protected SchemaTypeSystemImpl() {
        String fullname = getClass().getName();
        this._name = fullname.substring(0, fullname.lastIndexOf(46));
        XBeanDebug.LOG.atTrace().log("Loading type system {}", this._name);
        this._classloader = getClass().getClassLoader();
        this._linker = this;
        this._resourceLoader = new ClassLoaderResourceLoader(this._classloader);
        try {
            initFromHeader();
            XBeanDebug.LOG.atTrace().log("Finished loading type system {}", this._name);
        } catch (Error | RuntimeException e) {
            XBeanDebug.LOG.atDebug().withThrowable(e).log(e.getMessage());
            throw e;
        }
    }

    public SchemaTypeSystemImpl(Class<?> indexclass) {
        String fullname = indexclass.getName();
        this._name = fullname.substring(0, fullname.lastIndexOf(46));
        XBeanDebug.LOG.atTrace().log("Loading type system {}", this._name);
        this._classloader = indexclass.getClassLoader();
        this._linker = SchemaTypeLoaderImpl.build(null, new DefaultClassLoaderResourceLoader(), this._classloader, getMetadataPath());
        this._resourceLoader = new ClassLoaderResourceLoader(this._classloader);
        try {
            initFromHeader();
            XBeanDebug.LOG.atTrace().log("Finished loading type system {}", this._name);
        } catch (Error | RuntimeException e) {
            XBeanDebug.LOG.atDebug().withThrowable(e).log(e.getMessage());
            throw e;
        }
    }

    public static SchemaTypeSystemImpl forName(String name, ClassLoader loader) {
        try {
            Class<?> c = Class.forName(name + ".TypeSystemHolder", true, loader);
            return (SchemaTypeSystemImpl) c.getField("typeSystem").get(null);
        } catch (Throwable e) {
            if (ExceptionUtil.isFatal(e)) {
                ExceptionUtil.rethrow(e);
            }
            return null;
        }
    }

    public SchemaTypeSystemImpl(ResourceLoader resourceLoader, String name, SchemaTypeLoader linker) {
        this._name = name;
        this._linker = linker;
        this._resourceLoader = resourceLoader;
        try {
            initFromHeader();
        } catch (Error | RuntimeException e) {
            XBeanDebug.LOG.atDebug().withThrowable(e).log(e.getMessage());
            throw e;
        }
    }

    private void initFromHeader() {
        XBeanDebug.LOG.atTrace().log("Reading unresolved handles for type system {}", this._name);
        XsbReader reader = null;
        try {
            reader = new XsbReader(getTypeSystem(), "index", 1);
            this._localHandles = new SchemaTypePool(getTypeSystem());
            this._localHandles.readHandlePool(reader);
            this._globalElements = reader.readQNameRefMap();
            this._globalAttributes = reader.readQNameRefMap();
            this._modelGroups = reader.readQNameRefMap();
            this._attributeGroups = reader.readQNameRefMap();
            this._identityConstraints = reader.readQNameRefMap();
            this._globalTypes = reader.readQNameRefMap();
            this._documentTypes = reader.readQNameRefMap();
            this._attributeTypes = reader.readQNameRefMap();
            this._typeRefsByClassname = reader.readClassnameRefMap();
            this._namespaces = reader.readNamespaces();
            List<QName> typeNames = new ArrayList<>();
            List<QName> modelGroupNames = new ArrayList<>();
            List<QName> attributeGroupNames = new ArrayList<>();
            if (reader.atLeast(2, 15, 0)) {
                this._redefinedGlobalTypes = reader.readQNameRefMapAsList(typeNames);
                this._redefinedModelGroups = reader.readQNameRefMapAsList(modelGroupNames);
                this._redefinedAttributeGroups = reader.readQNameRefMapAsList(attributeGroupNames);
            }
            if (reader.atLeast(2, 19, 0)) {
                this._annotations = reader.readAnnotations();
            }
            buildContainers(typeNames, modelGroupNames, attributeGroupNames);
            reader.readEnd();
        } catch (Throwable th) {
            if (reader != null) {
                reader.readEnd();
            }
            throw th;
        }
    }

    void saveIndex() {
        XsbReader saver = new XsbReader(getTypeSystem(), "index");
        saver.writeIndexData();
        saver.writeRealHeader("index", 1);
        saver.writeIndexData();
        saver.writeEnd();
    }

    void savePointers() {
        savePointersForComponents(globalElements(), getMetadataPath() + "/element/");
        savePointersForComponents(globalAttributes(), getMetadataPath() + "/attribute/");
        savePointersForComponents(modelGroups(), getMetadataPath() + "/modelgroup/");
        savePointersForComponents(attributeGroups(), getMetadataPath() + "/attributegroup/");
        savePointersForComponents(globalTypes(), getMetadataPath() + "/type/");
        savePointersForComponents(identityConstraints(), getMetadataPath() + "/identityconstraint/");
        savePointersForNamespaces(this._namespaces, getMetadataPath() + "/namespace/");
        savePointersForClassnames(this._typeRefsByClassname.keySet(), getMetadataPath() + "/javaname/");
        savePointersForComponents(redefinedModelGroups(), getMetadataPath() + "/redefinedmodelgroup/");
        savePointersForComponents(redefinedAttributeGroups(), getMetadataPath() + "/redefinedattributegroup/");
        savePointersForComponents(redefinedGlobalTypes(), getMetadataPath() + "/redefinedtype/");
    }

    void savePointersForComponents(SchemaComponent[] components, String dir) {
        for (SchemaComponent component : components) {
            savePointerFile(dir + QNameHelper.hexsafedir(component.getName()), this._name);
        }
    }

    void savePointersForClassnames(Set<String> classnames, String dir) {
        for (String classname : classnames) {
            savePointerFile(dir + classname.replace('.', '/'), this._name);
        }
    }

    void savePointersForNamespaces(Set<String> namespaces, String dir) {
        for (String ns : namespaces) {
            savePointerFile(dir + QNameHelper.hexsafedir(new QName(ns, Sax2Dom.XMLNS_PREFIX)), this._name);
        }
    }

    void savePointerFile(String filename, String name) {
        XsbReader saver = new XsbReader(getTypeSystem(), filename);
        saver.writeString(name);
        saver.writeRealHeader(filename, 5);
        saver.writeString(name);
        saver.writeEnd();
    }

    private Map<String, SchemaComponent.Ref> buildTypeRefsByClassname(Map<String, SchemaType> typesByClassname) {
        Map<String, SchemaComponent.Ref> result = new LinkedHashMap<>();
        for (String className : typesByClassname.keySet()) {
            result.put(className, typesByClassname.get(className).getRef());
        }
        return result;
    }

    private static Map<QName, SchemaComponent.Ref> buildComponentRefMap(SchemaComponent[] components) {
        return buildComponentRefMap((List<? extends SchemaComponent>) Arrays.asList(components));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<QName, SchemaComponent.Ref> buildComponentRefMap(List<? extends SchemaComponent> components) {
        return (Map) components.stream().collect(Collectors.toMap(new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaComponent) obj).getName();
            }
        }, new SchemaTypeSystemImpl$$ExternalSyntheticLambda9(), new BinaryOperator() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda20
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return SchemaTypeSystemImpl.lambda$buildComponentRefMap$0((SchemaComponent.Ref) obj, (SchemaComponent.Ref) obj2);
            }
        }, new Supplier() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda30
            @Override // java.util.function.Supplier
            public final Object get() {
                return SchemaTypeSystemImpl.$r8$lambda$9jZrLfmN7qos7GZMvkzgNgs9WjU();
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SchemaComponent.Ref lambda$buildComponentRefMap$0(SchemaComponent.Ref u, SchemaComponent.Ref v) {
        return v;
    }

    private static List<SchemaComponent.Ref> buildComponentRefList(SchemaComponent[] components) {
        return buildComponentRefList((List<? extends SchemaComponent>) Arrays.asList(components));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<SchemaComponent.Ref> buildComponentRefList(List<? extends SchemaComponent> components) {
        return (List) components.stream().map(new SchemaTypeSystemImpl$$ExternalSyntheticLambda9()).collect(Collectors.toList());
    }

    private static Map<QName, SchemaComponent.Ref> buildDocumentMap(SchemaType[] types) {
        return buildDocumentMap((List<? extends SchemaComponent>) Arrays.asList(types));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<QName, SchemaComponent.Ref> buildDocumentMap(List<? extends SchemaComponent> types) {
        Map<QName, SchemaComponent.Ref> result = new LinkedHashMap<>();
        for (SchemaComponent comp : types) {
            SchemaType type = (SchemaType) comp;
            result.put(type.getDocumentElementName(), type.getRef());
        }
        return result;
    }

    private static Map<QName, SchemaComponent.Ref> buildAttributeTypeMap(SchemaType[] types) {
        Map<QName, SchemaComponent.Ref> result = new LinkedHashMap<>();
        for (SchemaType type : types) {
            result.put(type.getAttributeTypeAttributeName(), type.getRef());
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<QName, SchemaComponent.Ref> buildAttributeTypeMap(List<? extends SchemaComponent> types) {
        Map<QName, SchemaComponent.Ref> result = new LinkedHashMap<>();
        for (SchemaComponent comp : types) {
            SchemaType type = (SchemaType) comp;
            result.put(type.getAttributeTypeAttributeName(), type.getRef());
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaContainer getContainer(String namespace) {
        return this._containers.get(namespace);
    }

    private void addContainer(String namespace) {
        SchemaContainer c = new SchemaContainer(namespace);
        c.setTypeSystem(this);
        this._containers.put(namespace, c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaContainer getContainerNonNull(String namespace) {
        SchemaContainer result = getContainer(namespace);
        if (result == null) {
            addContainer(namespace);
            return getContainer(namespace);
        }
        return result;
    }

    String getSourceCodeEncoding() {
        return this._sourceCodeEncoding;
    }

    private <T extends SchemaComponent.Ref> void buildContainersHelper(Map<QName, SchemaComponent.Ref> elements, final BiConsumer<SchemaContainer, T> adder) {
        elements.forEach(new BiConsumer() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda26
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                SchemaTypeSystemImpl.this.m2605x7fa81dda(adder, (QName) obj, (SchemaComponent.Ref) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$buildContainersHelper$1$org-apache-xmlbeans-impl-schema-SchemaTypeSystemImpl, reason: not valid java name */
    public /* synthetic */ void m2605x7fa81dda(BiConsumer adder, QName k, SchemaComponent.Ref v) {
        adder.accept(getContainerNonNull(k.getNamespaceURI()), v);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T extends SchemaComponent.Ref> void buildContainersHelper(List<SchemaComponent.Ref> refs, List<QName> names, BiConsumer<SchemaContainer, T> biConsumer) {
        Iterator<SchemaComponent.Ref> it = refs.iterator();
        Iterator<QName> itname = names.iterator();
        while (it.hasNext()) {
            String ns = itname.next().getNamespaceURI();
            SchemaContainer sc = getContainerNonNull(ns);
            biConsumer.accept(sc, it.next());
        }
    }

    private void buildContainers(List<QName> redefTypeNames, List<QName> redefModelGroupNames, List<QName> redefAttributeGroupNames) {
        buildContainersHelper(this._globalElements, new BiConsumer() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda11
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((SchemaContainer) obj).addGlobalElement((SchemaGlobalElement.Ref) obj2);
            }
        });
        buildContainersHelper(this._globalAttributes, new BiConsumer() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda14
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((SchemaContainer) obj).addGlobalAttribute((SchemaGlobalAttribute.Ref) obj2);
            }
        });
        buildContainersHelper(this._modelGroups, new BiConsumer() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda15
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((SchemaContainer) obj).addModelGroup((SchemaModelGroup.Ref) obj2);
            }
        });
        buildContainersHelper(this._attributeGroups, new BiConsumer() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda16
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((SchemaContainer) obj).addAttributeGroup((SchemaAttributeGroup.Ref) obj2);
            }
        });
        buildContainersHelper(this._identityConstraints, new BiConsumer() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda17
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((SchemaContainer) obj).addIdentityConstraint((SchemaIdentityConstraint.Ref) obj2);
            }
        });
        buildContainersHelper(this._globalTypes, new BiConsumer() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda18
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((SchemaContainer) obj).addGlobalType((SchemaType.Ref) obj2);
            }
        });
        buildContainersHelper(this._attributeTypes, new BiConsumer() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda19
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((SchemaContainer) obj).addAttributeType((SchemaType.Ref) obj2);
            }
        });
        if (this._redefinedGlobalTypes != null && this._redefinedModelGroups != null && this._redefinedAttributeGroups != null) {
            if (this._redefinedGlobalTypes.size() != redefTypeNames.size()) {
                throw new AssertionError();
            }
            buildContainersHelper(this._redefinedGlobalTypes, redefTypeNames, new BiConsumer() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((SchemaContainer) obj).addRedefinedType((SchemaType.Ref) obj2);
                }
            });
            buildContainersHelper(this._redefinedModelGroups, redefModelGroupNames, new BiConsumer() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((SchemaContainer) obj).addRedefinedModelGroup((SchemaModelGroup.Ref) obj2);
                }
            });
            buildContainersHelper(this._redefinedAttributeGroups, redefAttributeGroupNames, new BiConsumer() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((SchemaContainer) obj).addRedefinedAttributeGroup((SchemaAttributeGroup.Ref) obj2);
                }
            });
        }
        if (this._annotations != null && !this._annotations.isEmpty()) {
            List<SchemaAnnotation> list = this._annotations;
            final SchemaContainer containerNonNull = getContainerNonNull("");
            containerNonNull.getClass();
            list.forEach(new Consumer() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    SchemaContainer.this.addAnnotation((SchemaAnnotation) obj);
                }
            });
        }
        this._containers.values().forEach(new Consumer() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda13
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((SchemaContainer) obj).setImmutable();
            }
        });
    }

    private void fixupContainers() {
        for (SchemaContainer container : this._containers.values()) {
            container.setTypeSystem(this);
            container.setImmutable();
        }
    }

    private void assertContainersHelper(Map<QName, SchemaComponent.Ref> comp, Function<SchemaContainer, List<? extends SchemaComponent>> fun, Function<List<? extends SchemaComponent>, ? extends Map<QName, SchemaComponent.Ref>> fun2) {
        Map<QName, SchemaComponent.Ref> temp = (Map) this._containers.values().stream().map(fun).map(fun2 == null ? new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Map buildComponentRefMap;
                buildComponentRefMap = SchemaTypeSystemImpl.buildComponentRefMap((List<? extends SchemaComponent>) obj);
                return buildComponentRefMap;
            }
        } : fun2).map(new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Map) obj).entrySet();
            }
        }).flatMap(new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Set) obj).stream();
            }
        }).collect(Collectors.toMap(new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (QName) ((Map.Entry) obj).getKey();
            }
        }, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (SchemaComponent.Ref) ((Map.Entry) obj).getValue();
            }
        }));
        if (!comp.equals(temp)) {
            throw new AssertionError();
        }
    }

    private void assertContainersHelper(List<? extends SchemaComponent.Ref> comp, Function<SchemaContainer, List<? extends SchemaComponent>> fun) {
        Set<SchemaComponent.Ref> temp = (Set) this._containers.values().stream().map(fun).map(new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda27
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                List buildComponentRefList;
                buildComponentRefList = SchemaTypeSystemImpl.buildComponentRefList((List<? extends SchemaComponent>) obj);
                return buildComponentRefList;
            }
        }).flatMap(new SchemaDependencies$$ExternalSyntheticLambda3()).collect(Collectors.toSet());
        if (!new HashSet(comp).equals(temp)) {
            throw new AssertionError();
        }
    }

    private void assertContainersSynchronized() {
        if (1 == 0) {
            return;
        }
        assertContainersHelper(this._globalElements, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda28
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaContainer) obj).globalElements();
            }
        }, null);
        assertContainersHelper(this._globalAttributes, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda36
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaContainer) obj).globalAttributes();
            }
        }, null);
        assertContainersHelper(this._modelGroups, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda37
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaContainer) obj).modelGroups();
            }
        }, null);
        assertContainersHelper(this._modelGroups, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda37
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaContainer) obj).modelGroups();
            }
        }, null);
        assertContainersHelper(this._redefinedModelGroups, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda38
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaContainer) obj).redefinedModelGroups();
            }
        });
        assertContainersHelper(this._attributeGroups, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda39
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaContainer) obj).attributeGroups();
            }
        }, null);
        assertContainersHelper(this._redefinedAttributeGroups, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda40
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaContainer) obj).redefinedAttributeGroups();
            }
        });
        assertContainersHelper(this._globalTypes, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda41
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaContainer) obj).globalTypes();
            }
        }, null);
        assertContainersHelper(this._redefinedGlobalTypes, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda42
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaContainer) obj).redefinedGlobalTypes();
            }
        });
        assertContainersHelper(this._documentTypes, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda43
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaContainer) obj).documentTypes();
            }
        }, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda29
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Map buildDocumentMap;
                buildDocumentMap = SchemaTypeSystemImpl.buildDocumentMap((List<? extends SchemaComponent>) obj);
                return buildDocumentMap;
            }
        });
        assertContainersHelper(this._attributeTypes, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda31
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaContainer) obj).attributeTypes();
            }
        }, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda32
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Map buildAttributeTypeMap;
                buildAttributeTypeMap = SchemaTypeSystemImpl.buildAttributeTypeMap((List<? extends SchemaComponent>) obj);
                return buildAttributeTypeMap;
            }
        });
        assertContainersHelper(this._identityConstraints, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda33
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaContainer) obj).identityConstraints();
            }
        }, null);
        Set<SchemaAnnotation> temp3 = (Set) this._containers.values().stream().map(new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda34
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaContainer) obj).annotations();
            }
        }).flatMap(new SchemaDependencies$$ExternalSyntheticLambda3()).collect(Collectors.toSet());
        if (!new HashSet(this._annotations).equals(temp3)) {
            throw new AssertionError();
        }
        Set<String> temp4 = (Set) this._containers.values().stream().map(new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda35
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaContainer) obj).getNamespace();
            }
        }).collect(Collectors.toSet());
        if (!this._namespaces.equals(temp4)) {
            throw new AssertionError();
        }
    }

    private static synchronized void nextBytes(byte[] result) {
        synchronized (SchemaTypeSystemImpl.class) {
            if (_random == null) {
                try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    try {
                        LongUTFDataOutputStream daos = new LongUTFDataOutputStream(baos);
                        try {
                            daos.writeInt(System.identityHashCode(SchemaTypeSystemImpl.class));
                            String[] props = {"user.name", "user.dir", SystemProperties.USER_TIMEZONE, SystemProperties.USER_COUNTRY, SystemProperties.JAVA_CLASS_PATH, "java.home", SystemProperties.JAVA_VENDOR, SystemProperties.JAVA_VERSION, SystemProperties.OS_VERSION};
                            for (String s : props) {
                                String prop = org.apache.xmlbeans.SystemProperties.getProperty(s);
                                if (prop != null) {
                                    daos.writeUTF(prop);
                                    daos.writeInt(System.identityHashCode(prop));
                                }
                            }
                            daos.writeLong(Runtime.getRuntime().freeMemory());
                            daos.close();
                            byte[] bytes = baos.toByteArray();
                            for (int i = 0; i < bytes.length; i++) {
                                int j = i % _mask.length;
                                byte[] bArr = _mask;
                                bArr[j] = (byte) (bArr[j] * ParenthesisPtg.sid);
                                byte[] bArr2 = _mask;
                                bArr2[j] = (byte) (bArr2[j] + ((byte) i));
                            }
                            baos.close();
                        } finally {
                        }
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            try {
                                baos.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                            throw th2;
                        }
                    }
                } catch (IOException e) {
                    XBeanDebug.LOG.atDebug().withThrowable(e).log(e.getMessage());
                }
                _random = new Random(System.currentTimeMillis());
            }
            _random.nextBytes(result);
            for (int i2 = 0; i2 < result.length; i2++) {
                result[i2] = (byte) (result[i2] ^ _mask[_mask.length & i2]);
            }
        }
    }

    public SchemaTypeSystemImpl(String nameForSystem) {
        if (nameForSystem == null) {
            byte[] bytes = new byte[16];
            nextBytes(bytes);
            nameForSystem = "s" + new String(HexBin.encode(bytes), StandardCharsets.ISO_8859_1);
        }
        this._name = METADATA_PACKAGE_GEN.replace('/', '.') + ".system." + nameForSystem;
        this._classloader = null;
    }

    public void loadFromStscState(StscState state) {
        if (this._classloader != null) {
            throw new AssertionError();
        }
        this._localHandles = new SchemaTypePool(getTypeSystem());
        this._globalElements = buildComponentRefMap(state.globalElements());
        this._globalAttributes = buildComponentRefMap(state.globalAttributes());
        this._modelGroups = buildComponentRefMap(state.modelGroups());
        this._redefinedModelGroups = buildComponentRefList(state.redefinedModelGroups());
        this._attributeGroups = buildComponentRefMap(state.attributeGroups());
        this._redefinedAttributeGroups = buildComponentRefList(state.redefinedAttributeGroups());
        this._globalTypes = buildComponentRefMap(state.globalTypes());
        this._redefinedGlobalTypes = buildComponentRefList(state.redefinedGlobalTypes());
        this._documentTypes = buildDocumentMap(state.documentTypes());
        this._attributeTypes = buildAttributeTypeMap(state.attributeTypes());
        this._typeRefsByClassname = buildTypeRefsByClassname(state.typesByClassname());
        this._identityConstraints = buildComponentRefMap(state.idConstraints());
        this._annotations = state.annotations();
        this._namespaces = new HashSet(Arrays.asList(state.getNamespaces()));
        this._containers = state.getContainerMap();
        this._sourceCodeEncoding = state.sourceCodeEncoding();
        fixupContainers();
        assertContainersSynchronized();
        setDependencies(state.getDependencies());
    }

    final SchemaTypeSystemImpl getTypeSystem() {
        return this;
    }

    void setDependencies(SchemaDependencies deps) {
        this._deps = deps;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaDependencies getDependencies() {
        return this._deps;
    }

    public boolean isIncomplete() {
        return this._incomplete;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setIncomplete(boolean incomplete) {
        this._incomplete = incomplete;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class StringPool {
        private final String _handle;
        private final String _name;
        private final List<String> intsToStrings = new ArrayList();
        private final Map<String, Integer> stringsToInts = new HashMap();

        /* JADX INFO: Access modifiers changed from: package-private */
        public StringPool(String handle, String name) {
            this._handle = handle;
            this._name = name;
            this.intsToStrings.add(null);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int codeForString(String str) {
            if (str == null) {
                return 0;
            }
            Integer result = this.stringsToInts.get(str);
            if (result == null) {
                result = Integer.valueOf(this.intsToStrings.size());
                this.intsToStrings.add(str);
                this.stringsToInts.put(str, result);
            }
            return result.intValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String stringForCode(int code) {
            if (code == 0) {
                return null;
            }
            return this.intsToStrings.get(code);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void writeTo(LongUTFDataOutputStream output) {
            try {
                int cnt = this.intsToStrings.size();
                output.writeShortOrInt(cnt);
                boolean isNext = false;
                for (String str : this.intsToStrings) {
                    if (isNext) {
                        output.writeLongUTF(str);
                    }
                    isNext = true;
                }
            } catch (IOException e) {
                throw new SchemaTypeLoaderException(e.getMessage(), this._name, this._handle, 9, e);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void readFrom(LongUTFDataInputStream input) {
            if (this.intsToStrings.size() != 1 || !this.stringsToInts.isEmpty()) {
                throw new IllegalStateException();
            }
            try {
                int size = input.readUnsignedShortOrInt();
                for (int i = 1; i < size; i++) {
                    String str = input.readLongUTF().intern();
                    int code = codeForString(str);
                    if (code != i) {
                        throw new IllegalStateException();
                    }
                }
            } catch (IOException e) {
                throw new SchemaTypeLoaderException(e.getMessage() == null ? e.getMessage() : "IO Exception", this._name, this._handle, 9, e);
            }
        }
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public void saveToDirectory(File classDir) {
        save(new FilerImpl(classDir, null, null, false, false));
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public void save(Filer filer) {
        if (this._incomplete) {
            throw new IllegalStateException("Incomplete SchemaTypeSystems cannot be saved.");
        }
        if (filer == null) {
            throw new IllegalArgumentException("filer must not be null");
        }
        this._filer = filer;
        this._localHandles.startWriteMode();
        saveTypesRecursively(globalTypes());
        saveTypesRecursively(documentTypes());
        saveTypesRecursively(attributeTypes());
        saveGlobalElements(globalElements());
        saveGlobalAttributes(globalAttributes());
        saveModelGroups(modelGroups());
        saveAttributeGroups(attributeGroups());
        saveIdentityConstraints(identityConstraints());
        saveTypesRecursively(redefinedGlobalTypes());
        saveModelGroups(redefinedModelGroups());
        saveAttributeGroups(redefinedAttributeGroups());
        saveIndex();
        savePointers();
    }

    void saveTypesRecursively(SchemaType[] types) {
        for (SchemaType type : types) {
            if (type.getTypeSystem() == getTypeSystem()) {
                saveType(type);
                saveTypesRecursively(type.getAnonymousTypes());
            }
        }
    }

    public void saveGlobalElements(SchemaGlobalElement[] elts) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        for (SchemaGlobalElement elt : elts) {
            saveGlobalElement(elt);
        }
    }

    public void saveGlobalAttributes(SchemaGlobalAttribute[] attrs) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        for (SchemaGlobalAttribute attr : attrs) {
            saveGlobalAttribute(attr);
        }
    }

    public void saveModelGroups(SchemaModelGroup[] groups) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        for (SchemaModelGroup group : groups) {
            saveModelGroup(group);
        }
    }

    public void saveAttributeGroups(SchemaAttributeGroup[] groups) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        for (SchemaAttributeGroup group : groups) {
            saveAttributeGroup(group);
        }
    }

    public void saveIdentityConstraints(SchemaIdentityConstraint[] idcs) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        for (SchemaIdentityConstraint idc : idcs) {
            saveIdentityConstraint(idc);
        }
    }

    public void saveGlobalElement(SchemaGlobalElement elt) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        String handle = this._localHandles.handleForElement(elt);
        XsbReader saver = new XsbReader(getTypeSystem(), handle);
        saver.writeParticleData((SchemaParticle) elt);
        saver.writeString(elt.getSourceName());
        saver.writeRealHeader(handle, 3);
        saver.writeParticleData((SchemaParticle) elt);
        saver.writeString(elt.getSourceName());
        saver.writeEnd();
    }

    public void saveGlobalAttribute(SchemaGlobalAttribute attr) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        String handle = this._localHandles.handleForAttribute(attr);
        XsbReader saver = new XsbReader(getTypeSystem(), handle);
        saver.writeAttributeData(attr);
        saver.writeString(attr.getSourceName());
        saver.writeRealHeader(handle, 4);
        saver.writeAttributeData(attr);
        saver.writeString(attr.getSourceName());
        saver.writeEnd();
    }

    public void saveModelGroup(SchemaModelGroup grp) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        String handle = this._localHandles.handleForModelGroup(grp);
        XsbReader saver = new XsbReader(getTypeSystem(), handle);
        saver.writeModelGroupData(grp);
        saver.writeRealHeader(handle, 6);
        saver.writeModelGroupData(grp);
        saver.writeEnd();
    }

    public void saveAttributeGroup(SchemaAttributeGroup grp) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        String handle = this._localHandles.handleForAttributeGroup(grp);
        XsbReader saver = new XsbReader(getTypeSystem(), handle);
        saver.writeAttributeGroupData(grp);
        saver.writeRealHeader(handle, 7);
        saver.writeAttributeGroupData(grp);
        saver.writeEnd();
    }

    public void saveIdentityConstraint(SchemaIdentityConstraint idc) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        String handle = this._localHandles.handleForIdentityConstraint(idc);
        XsbReader saver = new XsbReader(getTypeSystem(), handle);
        saver.writeIdConstraintData(idc);
        saver.writeRealHeader(handle, 8);
        saver.writeIdConstraintData(idc);
        saver.writeEnd();
    }

    void saveType(SchemaType type) {
        String handle = this._localHandles.handleForType(type);
        XsbReader saver = new XsbReader(getTypeSystem(), handle);
        saver.writeTypeData(type);
        saver.writeRealHeader(handle, 2);
        saver.writeTypeData(type);
        saver.writeEnd();
    }

    public static String crackPointer(InputStream stream) {
        try {
            LongUTFDataInputStream input = new LongUTFDataInputStream(stream);
            try {
                int magic = input.readInt();
                if (magic != -629491010) {
                    input.close();
                    return null;
                }
                int majorver = input.readShort();
                int minorver = input.readShort();
                if (majorver != 2) {
                    input.close();
                    return null;
                }
                if (minorver > 24) {
                    input.close();
                    return null;
                }
                if (minorver >= 18) {
                    input.readShort();
                }
                int actualfiletype = input.readShort();
                if (actualfiletype != 5) {
                    input.close();
                    return null;
                }
                StringPool stringPool = new StringPool("pointer", "unk");
                stringPool.readFrom(input);
                String stringForCode = stringPool.stringForCode(input.readShort());
                input.close();
                return stringForCode;
            } finally {
            }
        } catch (IOException e) {
            return null;
        }
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaType typeForHandle(String handle) {
        SchemaType schemaType;
        synchronized (this._resolvedHandles) {
            schemaType = (SchemaType) this._resolvedHandles.get(handle);
        }
        return schemaType;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType typeForClassname(String classname) {
        SchemaType.Ref ref = (SchemaType.Ref) this._typeRefsByClassname.get(classname);
        if (ref != null) {
            return ref.get();
        }
        return null;
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaComponent resolveHandle(String handle) {
        SchemaComponent result;
        synchronized (this._resolvedHandles) {
            result = this._resolvedHandles.get(handle);
        }
        if (result == null) {
            XsbReader reader = new XsbReader(getTypeSystem(), handle, 65535);
            int filetype = reader.getActualFiletype();
            switch (filetype) {
                case 2:
                    XBeanDebug.LOG.atTrace().log("Resolving type for handle {}", handle);
                    result = reader.finishLoadingType();
                    break;
                case 3:
                    XBeanDebug.LOG.atTrace().log("Resolving element for handle {}", handle);
                    result = reader.finishLoadingElement();
                    break;
                case 4:
                    XBeanDebug.LOG.atTrace().log("Resolving attribute for handle {}", handle);
                    result = reader.finishLoadingAttribute();
                    break;
                case 5:
                default:
                    throw new IllegalStateException("Illegal handle type");
                case 6:
                    XBeanDebug.LOG.atTrace().log("Resolving model group for handle {}", handle);
                    result = reader.finishLoadingModelGroup();
                    break;
                case 7:
                    XBeanDebug.LOG.atTrace().log("Resolving attribute group for handle {}", handle);
                    result = reader.finishLoadingAttributeGroup();
                    break;
                case 8:
                    XBeanDebug.LOG.atTrace().log("Resolving id constraint for handle {}", handle);
                    result = reader.finishLoadingIdentityConstraint();
                    break;
            }
            synchronized (this._resolvedHandles) {
                if (!this._resolvedHandles.containsKey(handle)) {
                    this._resolvedHandles.put(handle, result);
                } else {
                    result = this._resolvedHandles.get(handle);
                }
            }
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public void resolve() {
        XBeanDebug.LOG.atTrace().log("Resolve called type system {}", this._name);
        if (this._allNonGroupHandlesResolved) {
            return;
        }
        XBeanDebug.LOG.atTrace().log("Resolving all handles for type system {}", this._name);
        List<SchemaComponent.Ref> refs = new ArrayList<>();
        refs.addAll(this._globalElements.values());
        refs.addAll(this._globalAttributes.values());
        refs.addAll(this._globalTypes.values());
        refs.addAll(this._documentTypes.values());
        refs.addAll(this._attributeTypes.values());
        refs.addAll(this._identityConstraints.values());
        for (SchemaComponent.Ref ref : refs) {
            ref.getComponent();
        }
        XBeanDebug.LOG.atTrace().log("Finished resolving type system {}", this._name);
        this._allNonGroupHandlesResolved = true;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public boolean isNamespaceDefined(String namespace) {
        return this._namespaces.contains(namespace);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType.Ref findTypeRef(QName name) {
        return (SchemaType.Ref) this._globalTypes.get(name);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType.Ref findDocumentTypeRef(QName name) {
        return (SchemaType.Ref) this._documentTypes.get(name);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType.Ref findAttributeTypeRef(QName name) {
        return (SchemaType.Ref) this._attributeTypes.get(name);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaGlobalElement.Ref findElementRef(QName name) {
        return (SchemaGlobalElement.Ref) this._globalElements.get(name);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaGlobalAttribute.Ref findAttributeRef(QName name) {
        return (SchemaGlobalAttribute.Ref) this._globalAttributes.get(name);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaModelGroup.Ref findModelGroupRef(QName name) {
        return (SchemaModelGroup.Ref) this._modelGroups.get(name);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaAttributeGroup.Ref findAttributeGroupRef(QName name) {
        return (SchemaAttributeGroup.Ref) this._attributeGroups.get(name);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaIdentityConstraint.Ref findIdentityConstraintRef(QName name) {
        return (SchemaIdentityConstraint.Ref) this._identityConstraints.get(name);
    }

    private static <T, U> U[] refHelper(Map<QName, SchemaComponent.Ref> map, Function<T, U> function, IntFunction<U[]> intFunction, U[] uArr) {
        return (U[]) refHelper(map == null ? null : map.values(), function, intFunction, uArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$refHelper$2(SchemaComponent.Ref e) {
        return e;
    }

    private static <T, U> U[] refHelper(Collection<SchemaComponent.Ref> collection, Function<T, U> function, IntFunction<U[]> intFunction, U[] uArr) {
        return (collection == null || collection.isEmpty()) ? uArr : (U[]) collection.stream().map(new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda25
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SchemaTypeSystemImpl.lambda$refHelper$2((SchemaComponent.Ref) obj);
            }
        }).map(function).toArray(intFunction);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SchemaType[] lambda$globalTypes$3(int x$0) {
        return new SchemaType[x$0];
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaType[] globalTypes() {
        return (SchemaType[]) refHelper(this._globalTypes, new SchemaContainer$$ExternalSyntheticLambda0(), new IntFunction() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda47
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return SchemaTypeSystemImpl.lambda$globalTypes$3(i);
            }
        }, EMPTY_ST_ARRAY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SchemaType[] lambda$redefinedGlobalTypes$4(int x$0) {
        return new SchemaType[x$0];
    }

    public SchemaType[] redefinedGlobalTypes() {
        return (SchemaType[]) refHelper(this._redefinedGlobalTypes, new SchemaContainer$$ExternalSyntheticLambda0(), new IntFunction() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return SchemaTypeSystemImpl.lambda$redefinedGlobalTypes$4(i);
            }
        }, EMPTY_ST_ARRAY);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public InputStream getSourceAsStream(String sourceName) {
        if (!sourceName.startsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) {
            sourceName = PackagingURIHelper.FORWARD_SLASH_STRING + sourceName;
        }
        return this._resourceLoader.getResourceAsStream(getMetadataPath() + "/src" + sourceName);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaContainer[] containers() {
        return (SchemaContainer[]) this._containers.values().toArray(new SchemaContainer[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SchemaType[] lambda$documentTypes$5(int x$0) {
        return new SchemaType[x$0];
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaType[] documentTypes() {
        return (SchemaType[]) refHelper(this._documentTypes, new SchemaContainer$$ExternalSyntheticLambda0(), new IntFunction() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda44
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return SchemaTypeSystemImpl.lambda$documentTypes$5(i);
            }
        }, EMPTY_ST_ARRAY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SchemaType[] lambda$attributeTypes$6(int x$0) {
        return new SchemaType[x$0];
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaType[] attributeTypes() {
        return (SchemaType[]) refHelper(this._attributeTypes, new SchemaContainer$$ExternalSyntheticLambda0(), new IntFunction() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda45
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return SchemaTypeSystemImpl.lambda$attributeTypes$6(i);
            }
        }, EMPTY_ST_ARRAY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SchemaGlobalElement[] lambda$globalElements$7(int x$0) {
        return new SchemaGlobalElement[x$0];
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaGlobalElement[] globalElements() {
        return (SchemaGlobalElement[]) refHelper(this._globalElements, new SchemaContainer$$ExternalSyntheticLambda5(), new IntFunction() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda46
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return SchemaTypeSystemImpl.lambda$globalElements$7(i);
            }
        }, EMPTY_GE_ARRAY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SchemaGlobalAttribute[] lambda$globalAttributes$8(int x$0) {
        return new SchemaGlobalAttribute[x$0];
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaGlobalAttribute[] globalAttributes() {
        return (SchemaGlobalAttribute[]) refHelper(this._globalAttributes, new SchemaContainer$$ExternalSyntheticLambda4(), new IntFunction() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda8
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return SchemaTypeSystemImpl.lambda$globalAttributes$8(i);
            }
        }, EMPTY_GA_ARRAY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SchemaModelGroup[] lambda$modelGroups$9(int x$0) {
        return new SchemaModelGroup[x$0];
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaModelGroup[] modelGroups() {
        return (SchemaModelGroup[]) refHelper(this._modelGroups, new SchemaContainer$$ExternalSyntheticLambda1(), new IntFunction() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda24
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return SchemaTypeSystemImpl.lambda$modelGroups$9(i);
            }
        }, EMPTY_MG_ARRAY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SchemaModelGroup[] lambda$redefinedModelGroups$10(int x$0) {
        return new SchemaModelGroup[x$0];
    }

    public SchemaModelGroup[] redefinedModelGroups() {
        return (SchemaModelGroup[]) refHelper(this._redefinedModelGroups, new SchemaContainer$$ExternalSyntheticLambda1(), new IntFunction() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda2
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return SchemaTypeSystemImpl.lambda$redefinedModelGroups$10(i);
            }
        }, EMPTY_MG_ARRAY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SchemaAttributeGroup[] lambda$attributeGroups$11(int x$0) {
        return new SchemaAttributeGroup[x$0];
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaAttributeGroup[] attributeGroups() {
        return (SchemaAttributeGroup[]) refHelper(this._attributeGroups, new SchemaContainer$$ExternalSyntheticLambda2(), new IntFunction() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda48
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return SchemaTypeSystemImpl.lambda$attributeGroups$11(i);
            }
        }, EMPTY_AG_ARRAY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SchemaAttributeGroup[] lambda$redefinedAttributeGroups$12(int x$0) {
        return new SchemaAttributeGroup[x$0];
    }

    public SchemaAttributeGroup[] redefinedAttributeGroups() {
        return (SchemaAttributeGroup[]) refHelper(this._redefinedAttributeGroups, new SchemaContainer$$ExternalSyntheticLambda2(), new IntFunction() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda10
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return SchemaTypeSystemImpl.lambda$redefinedAttributeGroups$12(i);
            }
        }, EMPTY_AG_ARRAY);
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaAnnotation[] annotations() {
        return (this._annotations == null || this._annotations.isEmpty()) ? EMPTY_ANN_ARRAY : (SchemaAnnotation[]) this._annotations.toArray(EMPTY_ANN_ARRAY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SchemaIdentityConstraint[] lambda$identityConstraints$13(int x$0) {
        return new SchemaIdentityConstraint[x$0];
    }

    public SchemaIdentityConstraint[] identityConstraints() {
        return (SchemaIdentityConstraint[]) refHelper(this._identityConstraints, new SchemaContainer$$ExternalSyntheticLambda3(), new IntFunction() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda49
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return SchemaTypeSystemImpl.lambda$identityConstraints$13(i);
            }
        }, EMPTY_IC_ARRAY);
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public ClassLoader getClassLoader() {
        return this._classloader;
    }

    public String handleForType(SchemaType type) {
        return this._localHandles.handleForType(type);
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public String getName() {
        return this._name;
    }

    public String getMetadataPath() {
        Matcher m = packPat.matcher(this._name);
        String n = m.find() ? m.group(1) : this._name;
        return n.replace('.', '/');
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getBasePackage() {
        return nameToPathString(this._name);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaTypeLoader getLinker() {
        return this._linker;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaTypePool getTypePool() {
        return this._localHandles;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<String> getNamespaces() {
        return this._namespaces;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, SchemaComponent.Ref> getTypeRefsByClassname() {
        return this._typeRefsByClassname;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OutputStream getSaverStream(String name, String handle) {
        try {
            return this._filer.createBinaryFile(name);
        } catch (IOException e) {
            throw new SchemaTypeLoaderException(e.getMessage(), getName(), handle, 9, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InputStream getLoaderStream(String resourcename) {
        return this._resourceLoader.getResourceAsStream(resourcename);
    }
}
