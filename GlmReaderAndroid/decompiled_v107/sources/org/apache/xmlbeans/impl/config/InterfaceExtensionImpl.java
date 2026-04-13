package org.apache.xmlbeans.impl.config;

import androidx.core.os.EnvironmentCompat;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.types.ResolvedType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.InterfaceExtension;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.config.InterfaceExtensionImpl;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;

/* loaded from: classes11.dex */
public class InterfaceExtensionImpl implements InterfaceExtension {
    private String _delegateToClassName;
    private String _interfaceClassName;
    private MethodSignatureImpl[] _methods;
    private NameSet _xbeanSet;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InterfaceExtensionImpl newInstance(Parser loader, NameSet xbeanSet, Extensionconfig.Interface intfXO) {
        InterfaceExtensionImpl result = new InterfaceExtensionImpl();
        result._xbeanSet = xbeanSet;
        ClassOrInterfaceDeclaration interfaceJClass = validateInterface(loader, intfXO.getName(), intfXO);
        if (interfaceJClass == null) {
            BindingConfigImpl.error("Interface '" + intfXO.getStaticHandler() + "' not found.", intfXO);
            return null;
        }
        result._interfaceClassName = (String) interfaceJClass.getFullyQualifiedName().get();
        result._delegateToClassName = intfXO.getStaticHandler();
        ClassOrInterfaceDeclaration delegateJClass = validateClass(loader, result._delegateToClassName, intfXO);
        if (delegateJClass == null) {
            BindingConfigImpl.warning("Handler class '" + intfXO.getStaticHandler() + "' not found on classpath, skip validation.", intfXO);
            return result;
        }
        if (!result.validateMethods(loader, interfaceJClass, delegateJClass, intfXO)) {
            return null;
        }
        return result;
    }

    private static ClassOrInterfaceDeclaration validateInterface(Parser loader, String intfStr, XmlObject loc) {
        return validateJava(loader, intfStr, true, loc);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClassOrInterfaceDeclaration validateClass(Parser loader, String clsStr, XmlObject loc) {
        return validateJava(loader, clsStr, false, loc);
    }

    static ClassOrInterfaceDeclaration validateJava(Parser loader, String clsStr, boolean isInterface, XmlObject loc) {
        if (loader == null) {
            return null;
        }
        String ent = isInterface ? "Interface" : "Class";
        ClassOrInterfaceDeclaration cls = loader.loadSource(clsStr);
        if (cls == null) {
            BindingConfigImpl.error(ent + " '" + clsStr + "' not found.", loc);
            return null;
        }
        if (isInterface != cls.isInterface()) {
            BindingConfigImpl.error("'" + clsStr + "' must be " + (isInterface ? "an interface" : "a class") + ".", loc);
        }
        if (!cls.isPublic()) {
            BindingConfigImpl.error(ent + " '" + clsStr + "' is not public.", loc);
        }
        return cls;
    }

    private boolean validateMethods(Parser loader, final ClassOrInterfaceDeclaration interfaceJClass, final ClassOrInterfaceDeclaration delegateJClass, final XmlObject loc) {
        this._methods = (MethodSignatureImpl[]) interfaceJClass.resolve().getAllMethods().stream().filter(new Predicate() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$$ExternalSyntheticLambda6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InterfaceExtensionImpl.lambda$validateMethods$0((MethodUsage) obj);
            }
        }).map(new Function() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$$ExternalSyntheticLambda7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return InterfaceExtensionImpl.this.m2593x7c8fc247(interfaceJClass, delegateJClass, loc, (MethodUsage) obj);
            }
        }).map(new Function() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$$ExternalSyntheticLambda8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return InterfaceExtensionImpl.this.m2594x7dc61526((MethodDeclaration) obj);
            }
        }).toArray(new IntFunction() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$$ExternalSyntheticLambda9
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return InterfaceExtensionImpl.lambda$validateMethods$3(i);
            }
        });
        return Stream.of((Object[]) this._methods).allMatch(new Predicate() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$$ExternalSyntheticLambda10
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean nonNull;
                nonNull = Objects.nonNull((InterfaceExtensionImpl.MethodSignatureImpl) obj);
                return nonNull;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$validateMethods$0(MethodUsage m) {
        return !Object.class.getName().equals(m.declaringType().getQualifiedName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$validateMethods$2$org-apache-xmlbeans-impl-config-InterfaceExtensionImpl, reason: not valid java name */
    public /* synthetic */ MethodSignatureImpl m2594x7dc61526(MethodDeclaration m) {
        if (m == null) {
            return null;
        }
        return new MethodSignatureImpl(getStaticHandler(), m);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MethodSignatureImpl[] lambda$validateMethods$3(int x$0) {
        return new MethodSignatureImpl[x$0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: validateMethod, reason: merged with bridge method [inline-methods] */
    public MethodDeclaration m2593x7c8fc247(ClassOrInterfaceDeclaration interfaceJClass, ClassOrInterfaceDeclaration delegateJClass, final MethodUsage ifMethod, XmlObject loc) {
        String methodName = ifMethod.getName();
        MethodDeclaration delMethod = (MethodDeclaration) delegateJClass.getMethodsByName(methodName).stream().filter(new Predicate() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$$ExternalSyntheticLambda11
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean matchParams;
                matchParams = InterfaceExtensionImpl.matchParams(ifMethod, (MethodDeclaration) obj);
                return matchParams;
            }
        }).findFirst().orElse(null);
        String delegateFQN = (String) delegateJClass.getFullyQualifiedName().orElse("");
        String methodFQN = methodName + "(" + ifMethod.getParamTypes().toString() + ")";
        String interfaceFQN = (String) interfaceJClass.getFullyQualifiedName().orElse("");
        if (delMethod == null) {
            BindingConfigImpl.error("Handler class '" + delegateFQN + "' does not contain method " + methodFQN, loc);
            return null;
        }
        String[] ifEx = (String[]) ifMethod.getDeclaration().getSpecifiedExceptions().stream().map(new Function() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$$ExternalSyntheticLambda12
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ResolvedType) obj).describe();
            }
        }).sorted().toArray(new IntFunction() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$$ExternalSyntheticLambda1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return InterfaceExtensionImpl.lambda$validateMethod$5(i);
            }
        });
        String[] delEx = (String[]) delMethod.getThrownExceptions().stream().map(new Function() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ResolvedType resolve;
                resolve = ((ReferenceType) obj).resolve();
                return resolve;
            }
        }).map(new Function() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$$ExternalSyntheticLambda12
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ResolvedType) obj).describe();
            }
        }).sorted().toArray(new IntFunction() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$$ExternalSyntheticLambda3
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return InterfaceExtensionImpl.lambda$validateMethod$6(i);
            }
        });
        if (!Arrays.equals(ifEx, delEx)) {
            BindingConfigImpl.error("Handler method '" + delegateFQN + "." + methodName + "' must declare the same exceptions as the interface method '" + interfaceFQN + "." + methodFQN, loc);
            return null;
        }
        if (!delMethod.isPublic() || !delMethod.isStatic()) {
            BindingConfigImpl.error("Method '" + delegateFQN + "." + methodFQN + "' must be declared public and static.", loc);
            return null;
        }
        if (!ifMethod.getDeclaration().getReturnType().equals(delMethod.resolve().getReturnType())) {
            String returnType = ifMethod.getDeclaration().getReturnType().describe();
            BindingConfigImpl.error("Return type for method '" + returnType + StringUtils.SPACE + delegateFQN + "." + methodName + "(...)' does not match the return type of the interface method :'" + returnType + "'.", loc);
            return null;
        }
        return delMethod;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$validateMethod$5(int x$0) {
        return new String[x$0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$validateMethod$6(int x$0) {
        return new String[x$0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MethodDeclaration getMethod(ClassOrInterfaceDeclaration cls, String name, final String[] paramTypes) {
        return (MethodDeclaration) cls.getMethodsByName(name).stream().filter(new Predicate() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean parameterMatches;
                parameterMatches = InterfaceExtensionImpl.parameterMatches(InterfaceExtensionImpl.paramStrings(((MethodDeclaration) obj).getParameters()), paramTypes);
                return parameterMatches;
            }
        }).findFirst().orElse(null);
    }

    private static String[] paramStrings(NodeList<?> params) {
        return (String[]) params.stream().map(new Function() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return InterfaceExtensionImpl.lambda$paramStrings$8((Node) obj);
            }
        }).toArray(new IntFunction() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$$ExternalSyntheticLambda5
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return InterfaceExtensionImpl.lambda$paramStrings$9(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$paramStrings$8(Node p) {
        if (p instanceof Parameter) {
            return ((Parameter) p).getType().resolve().describe();
        }
        if (p instanceof TypeParameter) {
            return ((TypeParameter) p).getNameAsString();
        }
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$paramStrings$9(int x$0) {
        return new String[x$0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean matchParams(MethodUsage mIf, MethodDeclaration mDel) {
        List<ResolvedType> pIf = mIf.getParamTypes();
        NodeList<Parameter> pDel = mDel.getParameters();
        if (pDel.size() != pIf.size() + 1 || !XmlObject.class.getName().equals(pDel.get(0).resolve().describeType())) {
            return false;
        }
        int idx = 1;
        for (ResolvedType rt : pIf) {
            int idx2 = idx + 1;
            if (!rt.describe().equals(pDel.get(idx).resolve().describeType())) {
                return false;
            }
            idx = idx2;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean parameterMatches(String[] params1, String[] params2) {
        if (params1.length != params2.length) {
            return false;
        }
        for (int i = 0; i < params1.length; i++) {
            String p1 = params1[i];
            String p2 = params2[i];
            if (p1.contains(".")) {
                p1 = p2;
                p2 = p1;
            }
            if (!p2.endsWith(p1)) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(String fullJavaName) {
        return this._xbeanSet.contains(fullJavaName);
    }

    @Override // org.apache.xmlbeans.InterfaceExtension
    public String getStaticHandler() {
        return this._delegateToClassName;
    }

    @Override // org.apache.xmlbeans.InterfaceExtension
    public String getInterface() {
        return this._interfaceClassName;
    }

    @Override // org.apache.xmlbeans.InterfaceExtension
    public InterfaceExtension.MethodSignature[] getMethods() {
        return this._methods;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("  static handler: ").append(this._delegateToClassName).append(StringUtils.LF);
        buf.append("  interface: ").append(this._interfaceClassName).append(StringUtils.LF);
        buf.append("  name set: ").append(this._xbeanSet).append(StringUtils.LF);
        for (int i = 0; i < this._methods.length; i++) {
            buf.append("  method[").append(i).append("]=").append(this._methods[i]).append(StringUtils.LF);
        }
        return buf.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class MethodSignatureImpl implements InterfaceExtension.MethodSignature {
        private final String[] _exceptions;
        private final String _intfName;
        private final String _name;
        private final String[] _paramNames;
        private final String[] _params;
        private final String _return;
        private String _signature;
        private final int NOTINITIALIZED = -1;
        private int _hashCode = -1;

        MethodSignatureImpl(String intfName, MethodDeclaration method) {
            if (intfName == null || method == null) {
                throw new IllegalArgumentException("Interface: " + intfName + " method: " + method);
            }
            this._intfName = intfName;
            this._signature = null;
            this._name = method.getName().asString();
            String typeParams = (String) method.getTypeParameters().stream().map(new Function() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$MethodSignatureImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    String node;
                    node = ((TypeParameter) obj).toString();
                    return node;
                }
            }).collect(Collectors.joining(", "));
            this._return = (typeParams.isEmpty() ? "" : " <" + typeParams + "> ") + replaceInner(method.getType().resolve().describe());
            this._params = (String[]) method.getParameters().stream().map(new Function() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$MethodSignatureImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    String describe;
                    describe = ((Parameter) obj).getType().resolve().describe();
                    return describe;
                }
            }).map(new Function() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$MethodSignatureImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    String replaceInner;
                    replaceInner = InterfaceExtensionImpl.MethodSignatureImpl.replaceInner((String) obj);
                    return replaceInner;
                }
            }).toArray(new IntFunction() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$MethodSignatureImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return InterfaceExtensionImpl.MethodSignatureImpl.lambda$new$1(i);
                }
            });
            this._exceptions = (String[]) method.getThrownExceptions().stream().map(new Function() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$MethodSignatureImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    String describe;
                    describe = ((ReferenceType) obj).resolve().describe();
                    return describe;
                }
            }).map(new Function() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$MethodSignatureImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    String replaceInner;
                    replaceInner = InterfaceExtensionImpl.MethodSignatureImpl.replaceInner((String) obj);
                    return replaceInner;
                }
            }).toArray(new IntFunction() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$MethodSignatureImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return InterfaceExtensionImpl.MethodSignatureImpl.lambda$new$3(i);
                }
            });
            this._paramNames = (String[]) method.getParameters().stream().map(new Function() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$MethodSignatureImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    String nameAsString;
                    nameAsString = ((Parameter) obj).getNameAsString();
                    return nameAsString;
                }
            }).toArray(new IntFunction() { // from class: org.apache.xmlbeans.impl.config.InterfaceExtensionImpl$MethodSignatureImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return InterfaceExtensionImpl.MethodSignatureImpl.lambda$new$5(i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ String[] lambda$new$1(int x$0) {
            return new String[x$0];
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ String[] lambda$new$3(int x$0) {
            return new String[x$0];
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ String[] lambda$new$5(int x$0) {
            return new String[x$0];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String replaceInner(String classname) {
            return classname.replace('$', '.');
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getInterfaceName() {
            return this._intfName;
        }

        @Override // org.apache.xmlbeans.InterfaceExtension.MethodSignature
        public String getName() {
            return this._name;
        }

        @Override // org.apache.xmlbeans.InterfaceExtension.MethodSignature
        public String getReturnType() {
            return this._return;
        }

        @Override // org.apache.xmlbeans.InterfaceExtension.MethodSignature
        public String[] getParameterTypes() {
            return this._params;
        }

        @Override // org.apache.xmlbeans.InterfaceExtension.MethodSignature
        public String[] getParameterNames() {
            return this._paramNames;
        }

        @Override // org.apache.xmlbeans.InterfaceExtension.MethodSignature
        public String[] getExceptionTypes() {
            return this._exceptions;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof MethodSignatureImpl)) {
                return false;
            }
            MethodSignatureImpl ms = (MethodSignatureImpl) o;
            return ms.getName().equals(getName()) && this._intfName.equals(ms._intfName) && Arrays.equals(getParameterTypes(), ms.getParameterTypes());
        }

        public int hashCode() {
            if (this._hashCode != -1) {
                return this._hashCode;
            }
            int hash = Objects.hash(getName(), Integer.valueOf(Arrays.hashCode(getParameterTypes())), this._intfName);
            this._hashCode = hash;
            return hash;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getSignature() {
            if (this._signature != null) {
                return this._signature;
            }
            String str = this._name + "(" + String.join(" ,", this._params) + ")";
            this._signature = str;
            return str;
        }

        public String toString() {
            return getReturnType() + StringUtils.SPACE + getSignature();
        }
    }
}
