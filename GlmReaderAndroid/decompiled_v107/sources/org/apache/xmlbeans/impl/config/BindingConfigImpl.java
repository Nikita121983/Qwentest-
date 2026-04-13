package org.apache.xmlbeans.impl.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.BindingConfig;
import org.apache.xmlbeans.InterfaceExtension;
import org.apache.xmlbeans.PrePostExtension;
import org.apache.xmlbeans.UserType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.config.InterfaceExtensionImpl;
import org.apache.xmlbeans.impl.schema.StscState;
import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Qnametargetenum;
import org.apache.xmlbeans.impl.xb.xmlconfig.Usertypeconfig;

/* loaded from: classes11.dex */
public class BindingConfigImpl extends BindingConfig {
    private final File[] _classpath;
    private final File[] _javaFiles;
    private Parser _parser;
    private final Map<Object, String> _packageMap = new LinkedHashMap();
    private final Map<Object, String> _prefixMap = new LinkedHashMap();
    private final Map<Object, String> _suffixMap = new LinkedHashMap();
    private final Map<Object, String> _packageMapByUriPrefix = new LinkedHashMap();
    private final Map<Object, String> _prefixMapByUriPrefix = new LinkedHashMap();
    private final Map<Object, String> _suffixMapByUriPrefix = new LinkedHashMap();
    private final Map<QName, String> _qnameTypeMap = new LinkedHashMap();
    private final Map<QName, String> _qnameDocTypeMap = new LinkedHashMap();
    private final Map<QName, String> _qnameElemMap = new LinkedHashMap();
    private final Map<QName, String> _qnameAttMap = new LinkedHashMap();
    private final List<InterfaceExtensionImpl> _interfaceExtensions = new ArrayList();
    private final List<PrePostExtensionImpl> _prePostExtensions = new ArrayList();
    private final Map<QName, UserTypeImpl> _userTypes = new LinkedHashMap();

    public static BindingConfig forConfigDocuments(ConfigDocument.Config[] configs, File[] javaFiles, File[] classpath) {
        return new BindingConfigImpl(configs, javaFiles, classpath);
    }

    private BindingConfigImpl(ConfigDocument.Config[] configs, File[] javaFiles, File[] classpath) {
        ConfigDocument.Config[] configArr = configs;
        int i = 0;
        this._javaFiles = javaFiles != null ? (File[]) javaFiles.clone() : new File[0];
        this._classpath = classpath != null ? (File[]) classpath.clone() : new File[0];
        int length = configArr.length;
        int i2 = 0;
        while (i2 < length) {
            ConfigDocument.Config config = configArr[i2];
            Nsconfig[] nsa = config.getNamespaceArray();
            int length2 = nsa.length;
            for (int i3 = i; i3 < length2; i3++) {
                Nsconfig nsconfig = nsa[i3];
                recordNamespaceSetting(nsconfig.getUri(), nsconfig.getPackage(), this._packageMap);
                recordNamespaceSetting(nsconfig.getUri(), nsconfig.getPrefix(), this._prefixMap);
                recordNamespaceSetting(nsconfig.getUri(), nsconfig.getSuffix(), this._suffixMap);
                recordNamespacePrefixSetting(nsconfig.getUriprefix(), nsconfig.getPackage(), this._packageMapByUriPrefix);
                recordNamespacePrefixSetting(nsconfig.getUriprefix(), nsconfig.getPrefix(), this._prefixMapByUriPrefix);
                recordNamespacePrefixSetting(nsconfig.getUriprefix(), nsconfig.getSuffix(), this._suffixMapByUriPrefix);
            }
            Qnameconfig[] qnc = config.getQnameArray();
            int length3 = qnc.length;
            for (int i4 = i; i4 < length3; i4++) {
                Qnameconfig qnameconfig = qnc[i4];
                List<? extends XmlAnySimpleType> applyto = qnameconfig.xgetTarget().xgetListValue();
                QName name = qnameconfig.getName();
                String javaname = qnameconfig.getJavaname();
                for (XmlAnySimpleType xmlAnySimpleType : applyto) {
                    if (xmlAnySimpleType instanceof Qnametargetenum) {
                        Qnametargetenum a = (Qnametargetenum) xmlAnySimpleType;
                        switch (a.getEnumValue().intValue()) {
                            case 1:
                                this._qnameTypeMap.put(name, javaname);
                                break;
                            case 2:
                                this._qnameDocTypeMap.put(name, javaname);
                                break;
                            case 3:
                                this._qnameElemMap.put(name, javaname);
                                break;
                            case 4:
                                this._qnameAttMap.put(name, javaname);
                                break;
                        }
                    }
                }
            }
            Extensionconfig[] ext = config.getExtensionArray();
            for (Extensionconfig extensionconfig : ext) {
                recordExtensionSetting(extensionconfig);
            }
            Usertypeconfig[] utypes = config.getUsertypeArray();
            for (Usertypeconfig utype : utypes) {
                recordUserTypeSetting(utype);
            }
            i2++;
            configArr = configs;
            i = 0;
        }
        secondPhaseValidation();
    }

    void addInterfaceExtension(InterfaceExtensionImpl ext) {
        if (ext == null) {
            return;
        }
        this._interfaceExtensions.add(ext);
    }

    void addPrePostExtension(PrePostExtensionImpl ext) {
        if (ext == null) {
            return;
        }
        this._prePostExtensions.add(ext);
    }

    void secondPhaseValidation() {
        Map<InterfaceExtension.MethodSignature, InterfaceExtension.MethodSignature> methodSignatures = new HashMap<>();
        for (InterfaceExtensionImpl extension : this._interfaceExtensions) {
            InterfaceExtensionImpl.MethodSignatureImpl[] methods = (InterfaceExtensionImpl.MethodSignatureImpl[]) extension.getMethods();
            for (InterfaceExtensionImpl.MethodSignatureImpl ms : methods) {
                if (methodSignatures.containsKey(ms)) {
                    InterfaceExtensionImpl.MethodSignatureImpl ms2 = (InterfaceExtensionImpl.MethodSignatureImpl) methodSignatures.get(ms);
                    if (!ms.getReturnType().equals(ms2.getReturnType())) {
                        error("Colliding methods '" + ms.getSignature() + "' in interfaces " + ms.getInterfaceName() + " and " + ms2.getInterfaceName() + ".", null);
                        return;
                    }
                    return;
                }
                methodSignatures.put(ms, ms);
            }
        }
        for (int i = 0; i < this._prePostExtensions.size() - 1; i++) {
            PrePostExtensionImpl a = this._prePostExtensions.get(i);
            for (int j = 1; j < this._prePostExtensions.size(); j++) {
                PrePostExtensionImpl b = this._prePostExtensions.get(j);
                if (a.hasNameSetIntersection(b)) {
                    error("The applicable domain for handler '" + a.getHandlerNameForJavaSource() + "' intersects with the one for '" + b.getHandlerNameForJavaSource() + "'.", null);
                }
            }
        }
    }

    private static void recordNamespaceSetting(Object key, final String value, final Map<Object, String> result) {
        if (value == null) {
            return;
        }
        if (key == null) {
            result.put("", value);
            return;
        }
        if ((key instanceof String) && "##any".equals(key)) {
            result.put(key, value);
        } else if (key instanceof List) {
            ((List) key).forEach(new Consumer() { // from class: org.apache.xmlbeans.impl.config.BindingConfigImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    BindingConfigImpl.lambda$recordNamespaceSetting$0(result, value, obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$recordNamespaceSetting$0(Map result, String value, Object o) {
    }

    private static void recordNamespacePrefixSetting(List list, final String value, final Map<Object, String> result) {
        if (value == null || list == null) {
            return;
        }
        list.forEach(new Consumer() { // from class: org.apache.xmlbeans.impl.config.BindingConfigImpl$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BindingConfigImpl.lambda$recordNamespacePrefixSetting$1(result, value, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$recordNamespacePrefixSetting$1(Map result, String value, Object o) {
    }

    private void recordExtensionSetting(Extensionconfig ext) {
        NameSet xbeanSet = null;
        Object key = ext.getFor();
        if ((key instanceof String) && "*".equals(key)) {
            xbeanSet = NameSet.EVERYTHING;
        } else if (key instanceof List) {
            NameSetBuilder xbeanSetBuilder = new NameSetBuilder();
            for (Object o : (List) key) {
                String xbeanName = (String) o;
                xbeanSetBuilder.add(xbeanName);
            }
            xbeanSet = xbeanSetBuilder.toNameSet();
        }
        if (xbeanSet == null) {
            error("Invalid value of attribute 'for' : '" + key + "'.", ext);
        }
        Extensionconfig.Interface[] intfXO = ext.getInterfaceArray();
        Extensionconfig.PrePostSet ppXO = ext.getPrePostSet();
        Parser loader = parserInstance();
        if (intfXO.length > 0 || ppXO != null) {
            for (Extensionconfig.Interface anInterface : intfXO) {
                addInterfaceExtension(InterfaceExtensionImpl.newInstance(loader, xbeanSet, anInterface));
            }
            addPrePostExtension(PrePostExtensionImpl.newInstance(loader, xbeanSet, ppXO));
        }
    }

    private void recordUserTypeSetting(Usertypeconfig usertypeconfig) {
        Parser loader = parserInstance();
        UserTypeImpl userType = UserTypeImpl.newInstance(loader, usertypeconfig);
        this._userTypes.put(userType.getName(), userType);
    }

    private Parser parserInstance() {
        if (this._parser == null) {
            this._parser = new Parser(this._javaFiles, this._classpath);
        }
        return this._parser;
    }

    private String lookup(Map<Object, String> map, Map<Object, String> mapByUriPrefix, String uri) {
        String result;
        if (uri == null) {
            uri = "";
        }
        String result2 = map.get(uri);
        if (result2 != null) {
            return result2;
        }
        if (mapByUriPrefix != null && (result = lookupByUriPrefix(mapByUriPrefix, uri)) != null) {
            return result;
        }
        return map.get("##any");
    }

    private String lookupByUriPrefix(Map<Object, String> mapByUriPrefix, String uri) {
        if (uri != null && !mapByUriPrefix.isEmpty()) {
            String uriprefix = null;
            for (Object o : mapByUriPrefix.keySet()) {
                if (o instanceof String) {
                    String nextprefix = (String) o;
                    if (uriprefix == null || nextprefix.length() >= uriprefix.length()) {
                        if (uri.startsWith(nextprefix)) {
                            uriprefix = nextprefix;
                        }
                    }
                }
            }
            if (uriprefix != null) {
                return mapByUriPrefix.get(uriprefix);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void warning(String s, XmlObject xo) {
        StscState.get().error(s, 1, xo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void error(String s, XmlObject xo) {
        StscState.get().error(s, 0, xo);
    }

    @Override // org.apache.xmlbeans.BindingConfig
    public String lookupPackageForNamespace(String uri) {
        return lookup(this._packageMap, this._packageMapByUriPrefix, uri);
    }

    @Override // org.apache.xmlbeans.BindingConfig
    public String lookupPrefixForNamespace(String uri) {
        return lookup(this._prefixMap, this._prefixMapByUriPrefix, uri);
    }

    @Override // org.apache.xmlbeans.BindingConfig
    public String lookupSuffixForNamespace(String uri) {
        return lookup(this._suffixMap, this._suffixMapByUriPrefix, uri);
    }

    public String lookupJavanameForQName(QName qname) {
        String result = this._qnameTypeMap.get(qname);
        return result != null ? result : this._qnameDocTypeMap.get(qname);
    }

    @Override // org.apache.xmlbeans.BindingConfig
    public String lookupJavanameForQName(QName qname, int kind) {
        switch (kind) {
            case 1:
                return this._qnameTypeMap.get(qname);
            case 2:
                return this._qnameDocTypeMap.get(qname);
            case 3:
                return this._qnameElemMap.get(qname);
            case 4:
                return this._qnameAttMap.get(qname);
            default:
                return null;
        }
    }

    @Override // org.apache.xmlbeans.BindingConfig
    public UserType lookupUserTypeForQName(QName qname) {
        if (qname == null) {
            return null;
        }
        return this._userTypes.get(qname);
    }

    @Override // org.apache.xmlbeans.BindingConfig
    public UserType[] getUserTypes() {
        return (UserType[]) this._userTypes.values().toArray(new UserType[0]);
    }

    @Override // org.apache.xmlbeans.BindingConfig
    public InterfaceExtension[] getInterfaceExtensions() {
        return (InterfaceExtension[]) this._interfaceExtensions.toArray(new InterfaceExtension[0]);
    }

    @Override // org.apache.xmlbeans.BindingConfig
    public InterfaceExtension[] getInterfaceExtensions(final String fullJavaName) {
        return (InterfaceExtension[]) this._interfaceExtensions.stream().filter(new Predicate() { // from class: org.apache.xmlbeans.impl.config.BindingConfigImpl$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean contains;
                contains = ((InterfaceExtensionImpl) obj).contains(fullJavaName);
                return contains;
            }
        }).toArray(new IntFunction() { // from class: org.apache.xmlbeans.impl.config.BindingConfigImpl$$ExternalSyntheticLambda1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return BindingConfigImpl.lambda$getInterfaceExtensions$3(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ InterfaceExtension[] lambda$getInterfaceExtensions$3(int x$0) {
        return new InterfaceExtension[x$0];
    }

    @Override // org.apache.xmlbeans.BindingConfig
    public PrePostExtension[] getPrePostExtensions() {
        return (PrePostExtension[]) this._prePostExtensions.toArray(new PrePostExtension[0]);
    }

    @Override // org.apache.xmlbeans.BindingConfig
    public PrePostExtension getPrePostExtension(final String fullJavaName) {
        return this._prePostExtensions.stream().filter(new Predicate() { // from class: org.apache.xmlbeans.impl.config.BindingConfigImpl$$ExternalSyntheticLambda4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean contains;
                contains = ((PrePostExtensionImpl) obj).contains(fullJavaName);
                return contains;
            }
        }).findFirst().orElse(null);
    }
}
