package org.apache.commons.lang3.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.Validate;

/* loaded from: classes9.dex */
public class MethodUtils {
    private static final Comparator<Method> METHOD_BY_SIGNATURE = Comparator.comparing(new MethodUtils$$ExternalSyntheticLambda2());

    private static int distance(Class<?>[] fromClassArray, Class<?>[] toClassArray) {
        int answer = 0;
        if (!ClassUtils.isAssignable(fromClassArray, toClassArray, true)) {
            return -1;
        }
        for (int offset = 0; offset < fromClassArray.length; offset++) {
            Class<?> aClass = fromClassArray[offset];
            Class<?> toClass = toClassArray[offset];
            if (aClass != null && !aClass.equals(toClass)) {
                if (ClassUtils.isAssignable(aClass, toClass, true) && !ClassUtils.isAssignable(aClass, toClass, false)) {
                    answer++;
                } else {
                    answer += 2;
                }
            }
        }
        return answer;
    }

    public static Method getAccessibleMethod(Class<?> cls, String methodName, Class<?>... parameterTypes) {
        return getAccessibleMethod(getMethodObject(cls, methodName, parameterTypes));
    }

    public static Method getAccessibleMethod(Method method) {
        if (!MemberUtils.isAccessible(method)) {
            return null;
        }
        Class<?> cls = method.getDeclaringClass();
        if (ClassUtils.isPublic(cls)) {
            return method;
        }
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        Method method2 = getAccessibleMethodFromInterfaceNest(cls, methodName, parameterTypes);
        if (method2 == null) {
            return getAccessibleMethodFromSuperclass(cls, methodName, parameterTypes);
        }
        return method2;
    }

    private static Method getAccessibleMethodFromInterfaceNest(Class<?> cls, String methodName, Class<?>... parameterTypes) {
        while (cls != null) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                Class<?> anInterface = interfaces[i];
                if (ClassUtils.isPublic(anInterface)) {
                    try {
                        return anInterface.getDeclaredMethod(methodName, parameterTypes);
                    } catch (NoSuchMethodException e) {
                        Method method = getAccessibleMethodFromInterfaceNest(anInterface, methodName, parameterTypes);
                        if (method != null) {
                            return method;
                        }
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    private static Method getAccessibleMethodFromSuperclass(Class<?> cls, String methodName, Class<?>... parameterTypes) {
        for (Class<?> parentClass = cls.getSuperclass(); parentClass != null; parentClass = parentClass.getSuperclass()) {
            if (ClassUtils.isPublic(parentClass)) {
                return getMethodObject(parentClass, methodName, parameterTypes);
            }
        }
        return null;
    }

    private static List<Class<?>> getAllSuperclassesAndInterfaces(Class<?> cls) {
        Class<?> acls;
        int superClassIndex;
        if (cls == null) {
            return null;
        }
        List<Class<?>> allSuperClassesAndInterfaces = new ArrayList<>();
        List<Class<?>> allSuperclasses = ClassUtils.getAllSuperclasses(cls);
        int superClassIndex2 = 0;
        List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(cls);
        int interfaceIndex = 0;
        while (true) {
            if (interfaceIndex < allInterfaces.size() || superClassIndex2 < allSuperclasses.size()) {
                if (interfaceIndex >= allInterfaces.size() || (superClassIndex2 < allSuperclasses.size() && superClassIndex2 < interfaceIndex)) {
                    int interfaceIndex2 = superClassIndex2 + 1;
                    int i = interfaceIndex;
                    acls = allSuperclasses.get(superClassIndex2);
                    superClassIndex2 = interfaceIndex2;
                    superClassIndex = i;
                } else {
                    superClassIndex = interfaceIndex + 1;
                    acls = allInterfaces.get(interfaceIndex);
                }
                allSuperClassesAndInterfaces.add(acls);
                interfaceIndex = superClassIndex;
            } else {
                return allSuperClassesAndInterfaces;
            }
        }
    }

    public static <A extends Annotation> A getAnnotation(Method method, Class<A> cls, boolean z, boolean z2) {
        Objects.requireNonNull(method, "method");
        Objects.requireNonNull(cls, "annotationCls");
        if (!z2 && !MemberUtils.isAccessible(method)) {
            return null;
        }
        A a = (A) method.getAnnotation(cls);
        if (a == null && z) {
            for (Class<?> cls2 : getAllSuperclassesAndInterfaces(method.getDeclaringClass())) {
                Method matchingMethod = z2 ? getMatchingMethod(cls2, method.getName(), method.getParameterTypes()) : getMatchingAccessibleMethod(cls2, method.getName(), method.getParameterTypes());
                if (matchingMethod != null && (a = (A) matchingMethod.getAnnotation(cls)) != null) {
                    break;
                }
            }
        }
        return a;
    }

    public static Method getMatchingAccessibleMethod(Class<?> cls, final String methodName, final Class<?>... parameterTypes) {
        String parameterTypeSuperClassName;
        Method candidate = getMethodObject(cls, methodName, parameterTypes);
        if (candidate != null) {
            return (Method) MemberUtils.setAccessibleWorkaround(candidate);
        }
        Method[] methods = cls.getMethods();
        List<Method> matchingMethods = (List) Stream.of((Object[]) methods).filter(new Predicate() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return MethodUtils.lambda$getMatchingAccessibleMethod$0(methodName, parameterTypes, (Method) obj);
            }
        }).collect(Collectors.toList());
        matchingMethods.sort(METHOD_BY_SIGNATURE);
        Method bestMatch = null;
        for (Method method : matchingMethods) {
            Method accessibleMethod = getAccessibleMethod(method);
            if (accessibleMethod != null && (bestMatch == null || MemberUtils.compareMethodFit(accessibleMethod, bestMatch, parameterTypes) < 0)) {
                bestMatch = accessibleMethod;
            }
        }
        if (bestMatch != null) {
            MemberUtils.setAccessibleWorkaround(bestMatch);
        }
        if (bestMatch != null && bestMatch.isVarArgs() && bestMatch.getParameterTypes().length > 0 && parameterTypes.length > 0) {
            Class<?>[] methodParameterTypes = bestMatch.getParameterTypes();
            Class<?> methodParameterComponentType = methodParameterTypes[methodParameterTypes.length - 1].getComponentType();
            String methodParameterComponentTypeName = ClassUtils.primitiveToWrapper(methodParameterComponentType).getName();
            Class<?> lastParameterType = parameterTypes[parameterTypes.length - 1];
            String parameterTypeName = lastParameterType == null ? null : lastParameterType.getName();
            if (lastParameterType == null) {
                parameterTypeSuperClassName = null;
            } else {
                parameterTypeSuperClassName = lastParameterType.getSuperclass() != null ? lastParameterType.getSuperclass().getName() : null;
            }
            if (parameterTypeName != null && parameterTypeSuperClassName != null && !methodParameterComponentTypeName.equals(parameterTypeName) && !methodParameterComponentTypeName.equals(parameterTypeSuperClassName)) {
                return null;
            }
        }
        return bestMatch;
    }

    public static /* synthetic */ boolean lambda$getMatchingAccessibleMethod$0(String methodName, Class[] parameterTypes, Method method) {
        return method.getName().equals(methodName) && MemberUtils.isMatchingMethod(method, parameterTypes);
    }

    public static Method getMatchingMethod(Class<?> cls, final String methodName, final Class<?>... parameterTypes) {
        Objects.requireNonNull(cls, "cls");
        Validate.notEmpty(methodName, "methodName", new Object[0]);
        List<Method> methods = (List) Stream.of((Object[]) cls.getDeclaredMethods()).filter(new Predicate() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = ((Method) obj).getName().equals(methodName);
                return equals;
            }
        }).collect(Collectors.toList());
        Stream filter = getAllSuperclassesAndInterfaces(cls).stream().map(new Function() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Method[] declaredMethods;
                declaredMethods = ((Class) obj).getDeclaredMethods();
                return declaredMethods;
            }
        }).flatMap(new Function() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda10
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Stream of;
                of = Stream.of((Object[]) obj);
                return of;
            }
        }).filter(new Predicate() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda11
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = ((Method) obj).getName().equals(methodName);
                return equals;
            }
        });
        Objects.requireNonNull(methods);
        filter.forEach(new MethodUtils$$ExternalSyntheticLambda12(methods));
        for (Method method : methods) {
            if (Arrays.deepEquals(method.getParameterTypes(), parameterTypes)) {
                return method;
            }
        }
        final TreeMap<Integer, List<Method>> candidates = new TreeMap<>();
        methods.stream().filter(new Predicate() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda13
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean isAssignable;
                isAssignable = ClassUtils.isAssignable((Class<?>[]) parameterTypes, ((Method) obj).getParameterTypes(), true);
                return isAssignable;
            }
        }).forEach(new Consumer() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda14
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MethodUtils.lambda$getMatchingMethod$5(parameterTypes, candidates, (Method) obj);
            }
        });
        if (candidates.isEmpty()) {
            return null;
        }
        List<Method> bestCandidates = candidates.values().iterator().next();
        if (bestCandidates.size() == 1 || !Objects.equals(bestCandidates.get(0).getDeclaringClass(), bestCandidates.get(1).getDeclaringClass())) {
            return bestCandidates.get(0);
        }
        throw new IllegalStateException(String.format("Found multiple candidates for method %s on class %s : %s", methodName + ((String) Stream.of((Object[]) parameterTypes).map(new Function() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String valueOf;
                valueOf = String.valueOf((Class) obj);
                return valueOf;
            }
        }).collect(Collectors.joining(CollectionUtils.COMMA, "(", ")"))), cls.getName(), bestCandidates.stream().map(new MethodUtils$$ExternalSyntheticLambda2()).collect(Collectors.joining(CollectionUtils.COMMA, CollectionUtils.DEFAULT_TOSTRING_PREFIX, CollectionUtils.DEFAULT_TOSTRING_SUFFIX))));
    }

    public static /* synthetic */ void lambda$getMatchingMethod$5(Class[] parameterTypes, TreeMap candidates, Method method) {
        int distance = distance(parameterTypes, method.getParameterTypes());
        List<Method> candidatesAtDistance = (List) candidates.computeIfAbsent(Integer.valueOf(distance), new Function() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MethodUtils.lambda$getMatchingMethod$4((Integer) obj);
            }
        });
        candidatesAtDistance.add(method);
    }

    public static /* synthetic */ List lambda$getMatchingMethod$4(Integer k) {
        return new ArrayList();
    }

    public static Method getMethodObject(Class<?> cls, String name, Class<?>... parameterTypes) {
        try {
            return cls.getMethod(name, parameterTypes);
        } catch (NoSuchMethodException | SecurityException e) {
            return null;
        }
    }

    public static List<Method> getMethodsListWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls) {
        return getMethodsListWithAnnotation(cls, annotationCls, false, false);
    }

    public static List<Method> getMethodsListWithAnnotation(Class<?> cls, final Class<? extends Annotation> annotationCls, boolean searchSupers, final boolean ignoreAccess) {
        Objects.requireNonNull(cls, "cls");
        Objects.requireNonNull(annotationCls, "annotationCls");
        List<Class<?>> classes = searchSupers ? getAllSuperclassesAndInterfaces(cls) : new ArrayList<>();
        classes.add(0, cls);
        final List<Method> annotatedMethods = new ArrayList<>();
        classes.forEach(new Consumer() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda7
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MethodUtils.lambda$getMethodsListWithAnnotation$7(ignoreAccess, annotationCls, annotatedMethods, (Class) obj);
            }
        });
        return annotatedMethods;
    }

    public static /* synthetic */ void lambda$getMethodsListWithAnnotation$7(boolean ignoreAccess, final Class annotationCls, List annotatedMethods, Class acls) {
        Method[] methods = ignoreAccess ? acls.getDeclaredMethods() : acls.getMethods();
        Stream filter = Stream.of((Object[]) methods).filter(new Predicate() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean isAnnotationPresent;
                isAnnotationPresent = ((Method) obj).isAnnotationPresent(annotationCls);
                return isAnnotationPresent;
            }
        });
        Objects.requireNonNull(annotatedMethods);
        filter.forEachOrdered(new MethodUtils$$ExternalSyntheticLambda12(annotatedMethods));
    }

    public static Method[] getMethodsWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls) {
        return getMethodsWithAnnotation(cls, annotationCls, false, false);
    }

    public static Method[] getMethodsWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls, boolean searchSupers, boolean ignoreAccess) {
        return (Method[]) getMethodsListWithAnnotation(cls, annotationCls, searchSupers, ignoreAccess).toArray(ArrayUtils.EMPTY_METHOD_ARRAY);
    }

    public static Set<Method> getOverrideHierarchy(Method method, ClassUtils.Interfaces interfacesBehavior) {
        Objects.requireNonNull(method, "method");
        Set<Method> result = new LinkedHashSet<>();
        result.add(method);
        Class<?>[] parameterTypes = method.getParameterTypes();
        Class<?> declaringClass = method.getDeclaringClass();
        Iterator<Class<?>> hierarchy = ClassUtils.hierarchy(declaringClass, interfacesBehavior).iterator();
        hierarchy.next();
        while (hierarchy.hasNext()) {
            Class<?> c = hierarchy.next();
            Method m = getMatchingAccessibleMethod(c, method.getName(), parameterTypes);
            if (m != null) {
                if (Arrays.equals(m.getParameterTypes(), parameterTypes)) {
                    result.add(m);
                } else {
                    Map<TypeVariable<?>, Type> typeArguments = TypeUtils.getTypeArguments(declaringClass, m.getDeclaringClass());
                    int i = 0;
                    while (true) {
                        if (i < parameterTypes.length) {
                            Type childType = TypeUtils.unrollVariables(typeArguments, method.getGenericParameterTypes()[i]);
                            Type parentType = TypeUtils.unrollVariables(typeArguments, m.getGenericParameterTypes()[i]);
                            if (!TypeUtils.equals(childType, parentType)) {
                                break;
                            }
                            i++;
                        } else {
                            result.add(m);
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static Object[] getVarArgs(Object[] args, final Class<?>[] methodParameterTypes) {
        if (args.length == methodParameterTypes.length && (args[args.length - 1] == null || args[args.length - 1].getClass().equals(methodParameterTypes[methodParameterTypes.length - 1]))) {
            return args;
        }
        Object[] newArgs = (Object[]) ArrayUtils.arraycopy(args, 0, 0, methodParameterTypes.length - 1, (Supplier<Object[]>) new Supplier() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return MethodUtils.lambda$getVarArgs$8(methodParameterTypes);
            }
        });
        final Class<?> varArgComponentType = methodParameterTypes[methodParameterTypes.length - 1].getComponentType();
        final int varArgLength = (args.length - methodParameterTypes.length) + 1;
        Object varArgsArray = ArrayUtils.arraycopy(args, methodParameterTypes.length - 1, 0, varArgLength, (Function<Integer, Object[]>) new Function() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Object newInstance;
                newInstance = Array.newInstance(ClassUtils.primitiveToWrapper(varArgComponentType), varArgLength);
                return newInstance;
            }
        });
        if (varArgComponentType.isPrimitive()) {
            varArgsArray = ArrayUtils.toPrimitive(varArgsArray);
        }
        newArgs[methodParameterTypes.length - 1] = varArgsArray;
        return newArgs;
    }

    public static /* synthetic */ Object[] lambda$getVarArgs$8(Class[] methodParameterTypes) {
        return new Object[methodParameterTypes.length];
    }

    public static Object invokeExactMethod(Object object, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return invokeExactMethod(object, methodName, ArrayUtils.EMPTY_OBJECT_ARRAY, null);
    }

    public static Object invokeExactMethod(Object object, String methodName, Object... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        return invokeExactMethod(object, methodName, args2, ClassUtils.toClass(args2));
    }

    public static Object invokeExactMethod(Object object, String methodName, Object[] args, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Objects.requireNonNull(object, "object");
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        Class<?>[] parameterTypes2 = ArrayUtils.nullToEmpty(parameterTypes);
        Class<?> cls = object.getClass();
        Method method = getAccessibleMethod(cls, methodName, parameterTypes2);
        if (method == null) {
            throw new NoSuchMethodException("No such accessible method: " + methodName + "() on object: " + cls.getName());
        }
        return method.invoke(object, args2);
    }

    public static Object invokeExactStaticMethod(Class<?> cls, String methodName, Object... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        return invokeExactStaticMethod(cls, methodName, args2, ClassUtils.toClass(args2));
    }

    public static Object invokeExactStaticMethod(Class<?> cls, String methodName, Object[] args, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        Method method = getAccessibleMethod(cls, methodName, ArrayUtils.nullToEmpty(parameterTypes));
        if (method == null) {
            throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + cls.getName());
        }
        return method.invoke(null, args2);
    }

    public static Object invokeMethod(Object object, boolean forceAccess, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return invokeMethod(object, forceAccess, methodName, ArrayUtils.EMPTY_OBJECT_ARRAY, null);
    }

    public static Object invokeMethod(Object object, boolean forceAccess, String methodName, Object... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        return invokeMethod(object, forceAccess, methodName, args2, ClassUtils.toClass(args2));
    }

    public static Object invokeMethod(Object object, boolean forceAccess, String methodName, Object[] args, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String messagePrefix;
        Method method;
        Objects.requireNonNull(object, "object");
        Class<?>[] parameterTypes2 = ArrayUtils.nullToEmpty(parameterTypes);
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        Class<?> cls = object.getClass();
        if (forceAccess) {
            messagePrefix = "No such method: ";
            method = getMatchingMethod(cls, methodName, parameterTypes2);
            if (method != null && !method.isAccessible()) {
                method.setAccessible(true);
            }
        } else {
            messagePrefix = "No such accessible method: ";
            method = getMatchingAccessibleMethod(cls, methodName, parameterTypes2);
        }
        if (method == null) {
            throw new NoSuchMethodException(messagePrefix + methodName + "() on object: " + cls.getName());
        }
        return method.invoke(object, toVarArgs(method, args2));
    }

    public static Object invokeMethod(Object object, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return invokeMethod(object, methodName, ArrayUtils.EMPTY_OBJECT_ARRAY, (Class<?>[]) null);
    }

    public static Object invokeMethod(Object object, String methodName, Object... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        return invokeMethod(object, methodName, args2, ClassUtils.toClass(args2));
    }

    public static Object invokeMethod(Object object, String methodName, Object[] args, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return invokeMethod(object, false, methodName, args, parameterTypes);
    }

    public static Object invokeStaticMethod(Class<?> cls, String methodName, Object... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        return invokeStaticMethod(cls, methodName, args2, ClassUtils.toClass(args2));
    }

    public static Object invokeStaticMethod(Class<?> cls, String methodName, Object[] args, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        Method method = getMatchingAccessibleMethod(cls, methodName, ArrayUtils.nullToEmpty(parameterTypes));
        if (method == null) {
            throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + cls.getName());
        }
        return method.invoke(null, toVarArgs(method, args2));
    }

    private static Object[] toVarArgs(Method method, Object[] args) {
        if (method.isVarArgs()) {
            Class<?>[] methodParameterTypes = method.getParameterTypes();
            return getVarArgs(args, methodParameterTypes);
        }
        return args;
    }

    @Deprecated
    public MethodUtils() {
    }
}
