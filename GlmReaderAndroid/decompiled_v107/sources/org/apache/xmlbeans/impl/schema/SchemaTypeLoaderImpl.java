package org.apache.xmlbeans.impl.schema;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.ResourceLoader;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlRuntimeException;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.apache.xmlbeans.impl.common.SystemCache;
import org.apache.xmlbeans.impl.common.XBeanDebug;
import org.apache.xmlbeans.impl.schema.SchemaTypeLoaderImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

/* loaded from: classes11.dex */
public class SchemaTypeLoaderImpl extends SchemaTypeLoaderBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Map<QName, Object> _attributeCache;
    private Map<QName, Object> _attributeGroupCache;
    private Map<QName, Object> _attributeTypeCache;
    private final ClassLoader _classLoader;
    private Map<String, SchemaTypeSystemImpl> _classLoaderTypeSystems;
    private Map<String, Object> _classnameCache;
    private Map<String, SchemaTypeSystemImpl> _classpathTypeSystems;
    private Map<QName, Object> _documentCache;
    private Map<QName, Object> _elementCache;
    private Map<QName, Object> _idConstraintCache;
    private final String _metadataPath;
    private Map<QName, Object> _modelGroupCache;
    private final ResourceLoader _resourceLoader;
    private final SchemaTypeLoader[] _searchPath;
    private Map<QName, Object> _typeCache;
    public static String METADATA_PACKAGE_LOAD = SchemaTypeSystemImpl.METADATA_PACKAGE_GEN;
    private static final Object CACHED_NOT_FOUND = new Object();
    private static final String[] basePackage = {"org.apache.xmlbeans.metadata", "schemaorg_apache_xmlbeans"};
    private static final String[] baseSchemas = {"sXMLCONFIG", "sXMLLANG", "sXMLSCHEMA", "sXMLTOOLS"};
    private static final SchemaTypeLoader[] EMPTY_SCHEMATYPELOADER_ARRAY = new SchemaTypeLoader[0];

    static {
        SystemCache.set(new SchemaTypeLoaderCache());
    }

    /* loaded from: classes11.dex */
    private static class SchemaTypeLoaderCache extends SystemCache {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final ThreadLocal<List<SoftReference<SchemaTypeLoaderImpl>>> _cachedTypeSystems;

        public static /* synthetic */ ArrayList $r8$lambda$aw4WkQINtNlXlsGxYEbzatsgkDc() {
            return new ArrayList();
        }

        private SchemaTypeLoaderCache() {
            this._cachedTypeSystems = ThreadLocal.withInitial(new Supplier() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeLoaderImpl$SchemaTypeLoaderCache$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return SchemaTypeLoaderImpl.SchemaTypeLoaderCache.$r8$lambda$aw4WkQINtNlXlsGxYEbzatsgkDc();
                }
            });
        }

        @Override // org.apache.xmlbeans.impl.common.SystemCache
        public void clearThreadLocals() {
            this._cachedTypeSystems.remove();
            super.clearThreadLocals();
        }

        @Override // org.apache.xmlbeans.impl.common.SystemCache
        public SchemaTypeLoader getFromTypeLoaderCache(ClassLoader cl) {
            List<SoftReference<SchemaTypeLoaderImpl>> a = this._cachedTypeSystems.get();
            int candidate = -1;
            SchemaTypeLoaderImpl result = null;
            int i = 0;
            while (true) {
                if (i >= a.size()) {
                    break;
                }
                SchemaTypeLoaderImpl tl = a.get(i).get();
                if (tl != null) {
                    if (tl._classLoader == cl) {
                        candidate = i;
                        result = tl;
                        break;
                    }
                } else {
                    a.remove(i);
                    i--;
                }
                i++;
            }
            if (candidate > 0) {
                SoftReference<SchemaTypeLoaderImpl> t = a.get(0);
                a.set(0, a.get(candidate));
                a.set(candidate, t);
            }
            return result;
        }

        @Override // org.apache.xmlbeans.impl.common.SystemCache
        public void addToTypeLoaderCache(SchemaTypeLoader stl, ClassLoader cl) {
            if (!(stl instanceof SchemaTypeLoaderImpl) || ((SchemaTypeLoaderImpl) stl)._classLoader != cl) {
                throw new AssertionError();
            }
            List<SoftReference<SchemaTypeLoaderImpl>> a = this._cachedTypeSystems.get();
            if (!a.isEmpty()) {
                SoftReference<SchemaTypeLoaderImpl> t = a.get(0);
                a.set(0, new SoftReference<>((SchemaTypeLoaderImpl) stl));
                a.add(t);
                return;
            }
            a.add(new SoftReference<>((SchemaTypeLoaderImpl) stl));
        }
    }

    public static SchemaTypeLoaderImpl getContextTypeLoader() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        SchemaTypeLoaderImpl result = (SchemaTypeLoaderImpl) SystemCache.get().getFromTypeLoaderCache(cl);
        if (result == null) {
            SchemaTypeLoaderImpl result2 = new SchemaTypeLoaderImpl(new SchemaTypeLoader[]{BuiltinSchemaTypeSystem.get()}, null, cl, null);
            SystemCache.get().addToTypeLoaderCache(result2, cl);
            return result2;
        }
        return result;
    }

    public static SchemaTypeLoader build(SchemaTypeLoader[] searchPath, ResourceLoader resourceLoader, ClassLoader classLoader) {
        return build(searchPath, resourceLoader, classLoader, null);
    }

    public static SchemaTypeLoader build(SchemaTypeLoader[] searchPath, ResourceLoader resourceLoader, ClassLoader classLoader, String metadataPath) {
        SubLoaderList list = new SubLoaderList();
        list.add(searchPath);
        ClassLoader cl = classLoader == null ? SchemaDocument.class.getClassLoader() : classLoader;
        for (String prefix : basePackage) {
            for (String holder : baseSchemas) {
                String clName = prefix + ".system." + holder + ".TypeSystemHolder";
                try {
                    list.add((SchemaTypeLoader) Class.forName(clName, true, cl).getDeclaredField("typeSystem").get(null));
                } catch (ClassNotFoundException e) {
                } catch (Exception e2) {
                    throw new XmlRuntimeException(e2);
                }
            }
        }
        return new SchemaTypeLoaderImpl(list.toArray(), resourceLoader, classLoader, metadataPath);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class SubLoaderList {
        private final Map<SchemaTypeLoader, Object> seen;
        private final List<SchemaTypeLoader> theList;

        private SubLoaderList() {
            this.theList = new ArrayList();
            this.seen = new IdentityHashMap();
        }

        void add(SchemaTypeLoader[] searchPath) {
            if (searchPath == null) {
                return;
            }
            for (SchemaTypeLoader stl : searchPath) {
                if (stl instanceof SchemaTypeLoaderImpl) {
                    SchemaTypeLoaderImpl sub = (SchemaTypeLoaderImpl) stl;
                    if (sub._classLoader == null && sub._resourceLoader == null) {
                        add(sub._searchPath);
                    } else {
                        add(sub);
                    }
                } else {
                    add(stl);
                }
            }
        }

        void add(SchemaTypeLoader loader) {
            if (loader != null && !this.seen.containsKey(loader)) {
                this.theList.add(loader);
                this.seen.put(loader, null);
            }
        }

        SchemaTypeLoader[] toArray() {
            return (SchemaTypeLoader[]) this.theList.toArray(SchemaTypeLoaderImpl.EMPTY_SCHEMATYPELOADER_ARRAY);
        }
    }

    private SchemaTypeLoaderImpl(SchemaTypeLoader[] searchPath, ResourceLoader resourceLoader, ClassLoader classLoader, String metadataPath) {
        this._searchPath = searchPath == null ? EMPTY_SCHEMATYPELOADER_ARRAY : searchPath;
        this._resourceLoader = resourceLoader;
        this._classLoader = classLoader;
        if (metadataPath != null) {
            this._metadataPath = metadataPath;
        } else {
            String path26 = "schema" + METADATA_PACKAGE_LOAD.replace(PackagingURIHelper.FORWARD_SLASH_STRING, "_");
            this._metadataPath = isPath30(this._classLoader) ? METADATA_PACKAGE_LOAD : path26;
        }
        initCaches();
    }

    private static boolean isPath30(ClassLoader loader) {
        String path30 = METADATA_PACKAGE_LOAD + "/system";
        ClassLoader cl = loader != null ? loader : SchemaDocument.class.getClassLoader();
        return cl.getResource(path30) != null;
    }

    private void initCaches() {
        this._classpathTypeSystems = Collections.synchronizedMap(new HashMap());
        this._classLoaderTypeSystems = Collections.synchronizedMap(new HashMap());
        this._elementCache = Collections.synchronizedMap(new HashMap());
        this._attributeCache = Collections.synchronizedMap(new HashMap());
        this._modelGroupCache = Collections.synchronizedMap(new HashMap());
        this._attributeGroupCache = Collections.synchronizedMap(new HashMap());
        this._idConstraintCache = Collections.synchronizedMap(new HashMap());
        this._typeCache = Collections.synchronizedMap(new HashMap());
        this._documentCache = Collections.synchronizedMap(new HashMap());
        this._attributeTypeCache = Collections.synchronizedMap(new HashMap());
        this._classnameCache = Collections.synchronizedMap(new HashMap());
    }

    SchemaTypeSystemImpl typeSystemForComponent(String searchdir, QName name) {
        String searchfor = searchdir + QNameHelper.hexsafedir(name) + ".xsb";
        String tsname = null;
        if (this._resourceLoader != null) {
            tsname = crackEntry(this._resourceLoader, searchfor);
        }
        if (tsname == null && this._classLoader != null) {
            tsname = crackEntry(this._classLoader, searchfor);
        }
        if (tsname != null) {
            return (SchemaTypeSystemImpl) typeSystemForName(tsname);
        }
        return null;
    }

    public SchemaTypeSystem typeSystemForName(String name) {
        SchemaTypeSystem result;
        SchemaTypeSystem result2;
        if (this._resourceLoader != null && (result2 = getTypeSystemOnClasspath(name)) != null) {
            return result2;
        }
        if (this._classLoader != null && (result = getTypeSystemOnClassloader(name)) != null) {
            return result;
        }
        return null;
    }

    SchemaTypeSystemImpl typeSystemForClassname(String searchdir, String name) {
        String tsname;
        String tsname2;
        String searchfor = searchdir + name.replace('.', '/') + ".xsb";
        if (this._resourceLoader != null && (tsname2 = crackEntry(this._resourceLoader, searchfor)) != null) {
            return getTypeSystemOnClasspath(tsname2);
        }
        if (this._classLoader != null && (tsname = crackEntry(this._classLoader, searchfor)) != null) {
            return getTypeSystemOnClassloader(tsname);
        }
        return null;
    }

    SchemaTypeSystemImpl getTypeSystemOnClasspath(String name) {
        return this._classpathTypeSystems.computeIfAbsent(name, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypeLoaderImpl$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SchemaTypeLoaderImpl.this.m2601x46b5d54((String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getTypeSystemOnClasspath$0$org-apache-xmlbeans-impl-schema-SchemaTypeLoaderImpl, reason: not valid java name */
    public /* synthetic */ SchemaTypeSystemImpl m2601x46b5d54(String n) {
        return new SchemaTypeSystemImpl(this._resourceLoader, n, this);
    }

    SchemaTypeSystemImpl getTypeSystemOnClassloader(String name) {
        XBeanDebug.LOG.atTrace().log("Finding type system {} on classloader", name);
        SchemaTypeSystemImpl result = this._classLoaderTypeSystems.get(name);
        if (result == null) {
            XBeanDebug.LOG.atTrace().log("Type system {}} not cached - consulting field", name);
            SchemaTypeSystemImpl result2 = SchemaTypeSystemImpl.forName(name, this._classLoader);
            this._classLoaderTypeSystems.put(name, result2);
            return result2;
        }
        return result;
    }

    static String crackEntry(ResourceLoader loader, String searchfor) {
        InputStream is = loader.getResourceAsStream(searchfor);
        if (is == null) {
            return null;
        }
        return crackPointer(is);
    }

    static String crackEntry(ClassLoader loader, String searchfor) {
        InputStream stream = loader.getResourceAsStream(searchfor);
        if (stream == null) {
            return null;
        }
        return crackPointer(stream);
    }

    static String crackPointer(InputStream stream) {
        return SchemaTypeSystemImpl.crackPointer(stream);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public boolean isNamespaceDefined(String namespace) {
        for (SchemaTypeLoader schemaTypeLoader : this._searchPath) {
            if (schemaTypeLoader.isNamespaceDefined(namespace)) {
                return true;
            }
        }
        SchemaTypeSystem sts = typeSystemForComponent(this._metadataPath + "/namespace/", new QName(namespace, Sax2Dom.XMLNS_PREFIX));
        return sts != null;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType.Ref findTypeRef(QName name) {
        SchemaTypeSystem ts;
        Object cached = this._typeCache.get(name);
        if (cached == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaType.Ref result = (SchemaType.Ref) cached;
        if (result == null) {
            for (SchemaTypeLoader schemaTypeLoader : this._searchPath) {
                SchemaType.Ref findTypeRef = schemaTypeLoader.findTypeRef(name);
                result = findTypeRef;
                if (findTypeRef != null) {
                    break;
                }
            }
            if (result == null && (ts = typeSystemForComponent(this._metadataPath + "/type/", name)) != null && (result = ts.findTypeRef(name)) == null) {
                throw new AssertionError("Type system registered type " + QNameHelper.pretty(name) + " but does not return it");
            }
            this._typeCache.put(name, result == null ? CACHED_NOT_FOUND : result);
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType typeForClassname(String classname) {
        SchemaTypeSystem ts;
        String classname2 = classname.replace('$', '.');
        Object cached = this._classnameCache.get(classname2);
        if (cached == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaType result = (SchemaType) cached;
        if (result == null) {
            for (SchemaTypeLoader schemaTypeLoader : this._searchPath) {
                SchemaType typeForClassname = schemaTypeLoader.typeForClassname(classname2);
                result = typeForClassname;
                if (typeForClassname != null) {
                    break;
                }
            }
            if (result == null && (ts = typeSystemForClassname(this._metadataPath + "/javaname/", classname2)) != null && (result = ts.typeForClassname(classname2)) == null) {
                throw new AssertionError("Type system registered type " + classname2 + " but does not return it");
            }
            this._classnameCache.put(classname2, result == null ? CACHED_NOT_FOUND : result);
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType.Ref findDocumentTypeRef(QName name) {
        SchemaTypeSystem ts;
        Object cached = this._documentCache.get(name);
        if (cached == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaType.Ref result = (SchemaType.Ref) cached;
        if (result == null) {
            for (SchemaTypeLoader schemaTypeLoader : this._searchPath) {
                SchemaType.Ref findDocumentTypeRef = schemaTypeLoader.findDocumentTypeRef(name);
                result = findDocumentTypeRef;
                if (findDocumentTypeRef != null) {
                    break;
                }
            }
            if (result == null && (ts = typeSystemForComponent(this._metadataPath + "/element/", name)) != null && (result = ts.findDocumentTypeRef(name)) == null) {
                throw new AssertionError("Type system registered element " + QNameHelper.pretty(name) + " but does not contain document type");
            }
            this._documentCache.put(name, result == null ? CACHED_NOT_FOUND : result);
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType.Ref findAttributeTypeRef(QName name) {
        SchemaTypeSystem ts;
        Object cached = this._attributeTypeCache.get(name);
        if (cached == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaType.Ref result = (SchemaType.Ref) cached;
        if (result == null) {
            for (SchemaTypeLoader schemaTypeLoader : this._searchPath) {
                SchemaType.Ref findAttributeTypeRef = schemaTypeLoader.findAttributeTypeRef(name);
                result = findAttributeTypeRef;
                if (findAttributeTypeRef != null) {
                    break;
                }
            }
            if (result == null && (ts = typeSystemForComponent(this._metadataPath + "/attribute/", name)) != null && (result = ts.findAttributeTypeRef(name)) == null) {
                throw new AssertionError("Type system registered attribute " + QNameHelper.pretty(name) + " but does not contain attribute type");
            }
            this._attributeTypeCache.put(name, result == null ? CACHED_NOT_FOUND : result);
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaGlobalElement.Ref findElementRef(QName name) {
        SchemaTypeSystem ts;
        Object cached = this._elementCache.get(name);
        if (cached == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaGlobalElement.Ref result = (SchemaGlobalElement.Ref) cached;
        if (result == null) {
            for (SchemaTypeLoader schemaTypeLoader : this._searchPath) {
                SchemaGlobalElement.Ref findElementRef = schemaTypeLoader.findElementRef(name);
                result = findElementRef;
                if (findElementRef != null) {
                    break;
                }
            }
            if (result == null && (ts = typeSystemForComponent(this._metadataPath + "/element/", name)) != null && (result = ts.findElementRef(name)) == null) {
                throw new AssertionError("Type system registered element " + QNameHelper.pretty(name) + " but does not return it");
            }
            this._elementCache.put(name, result == null ? CACHED_NOT_FOUND : result);
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaGlobalAttribute.Ref findAttributeRef(QName name) {
        SchemaTypeSystem ts;
        Object cached = this._attributeCache.get(name);
        if (cached == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaGlobalAttribute.Ref result = (SchemaGlobalAttribute.Ref) cached;
        if (result == null) {
            for (SchemaTypeLoader schemaTypeLoader : this._searchPath) {
                SchemaGlobalAttribute.Ref findAttributeRef = schemaTypeLoader.findAttributeRef(name);
                result = findAttributeRef;
                if (findAttributeRef != null) {
                    break;
                }
            }
            if (result == null && (ts = typeSystemForComponent(this._metadataPath + "/attribute/", name)) != null && (result = ts.findAttributeRef(name)) == null) {
                throw new AssertionError("Type system registered attribute " + QNameHelper.pretty(name) + " but does not return it");
            }
            this._attributeCache.put(name, result == null ? CACHED_NOT_FOUND : result);
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaModelGroup.Ref findModelGroupRef(QName name) {
        SchemaTypeSystem ts;
        Object cached = this._modelGroupCache.get(name);
        if (cached == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaModelGroup.Ref result = (SchemaModelGroup.Ref) cached;
        if (result == null) {
            for (SchemaTypeLoader schemaTypeLoader : this._searchPath) {
                SchemaModelGroup.Ref findModelGroupRef = schemaTypeLoader.findModelGroupRef(name);
                result = findModelGroupRef;
                if (findModelGroupRef != null) {
                    break;
                }
            }
            if (result == null && (ts = typeSystemForComponent(this._metadataPath + "/modelgroup/", name)) != null && (result = ts.findModelGroupRef(name)) == null) {
                throw new AssertionError("Type system registered model group " + QNameHelper.pretty(name) + " but does not return it");
            }
            this._modelGroupCache.put(name, result == null ? CACHED_NOT_FOUND : result);
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaAttributeGroup.Ref findAttributeGroupRef(QName name) {
        SchemaTypeSystem ts;
        Object cached = this._attributeGroupCache.get(name);
        if (cached == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaAttributeGroup.Ref result = (SchemaAttributeGroup.Ref) cached;
        if (result == null) {
            for (SchemaTypeLoader schemaTypeLoader : this._searchPath) {
                SchemaAttributeGroup.Ref findAttributeGroupRef = schemaTypeLoader.findAttributeGroupRef(name);
                result = findAttributeGroupRef;
                if (findAttributeGroupRef != null) {
                    break;
                }
            }
            if (result == null && (ts = typeSystemForComponent(this._metadataPath + "/attributegroup/", name)) != null && (result = ts.findAttributeGroupRef(name)) == null) {
                throw new AssertionError("Type system registered attribute group " + QNameHelper.pretty(name) + " but does not return it");
            }
            this._attributeGroupCache.put(name, result == null ? CACHED_NOT_FOUND : result);
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaIdentityConstraint.Ref findIdentityConstraintRef(QName name) {
        SchemaTypeSystem ts;
        Object cached = this._idConstraintCache.get(name);
        if (cached == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaIdentityConstraint.Ref result = (SchemaIdentityConstraint.Ref) cached;
        if (result == null) {
            for (SchemaTypeLoader schemaTypeLoader : this._searchPath) {
                SchemaIdentityConstraint.Ref findIdentityConstraintRef = schemaTypeLoader.findIdentityConstraintRef(name);
                result = findIdentityConstraintRef;
                if (findIdentityConstraintRef != null) {
                    break;
                }
            }
            if (result == null && (ts = typeSystemForComponent(this._metadataPath + "/identityconstraint/", name)) != null && (result = ts.findIdentityConstraintRef(name)) == null) {
                throw new AssertionError("Type system registered identity constraint " + QNameHelper.pretty(name) + " but does not return it");
            }
            this._idConstraintCache.put(name, result == null ? CACHED_NOT_FOUND : result);
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public InputStream getSourceAsStream(String sourceName) {
        InputStream result = null;
        if (!sourceName.startsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) {
            sourceName = PackagingURIHelper.FORWARD_SLASH_STRING + sourceName;
        }
        if (this._resourceLoader != null) {
            result = this._resourceLoader.getResourceAsStream(this._metadataPath + "/src" + sourceName);
        }
        if (result == null && this._classLoader != null) {
            return this._classLoader.getResourceAsStream(this._metadataPath + "/src" + sourceName);
        }
        return result;
    }
}
