package org.apache.commons.lang3;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

/* loaded from: classes9.dex */
public class ClassUtils {
    private static final Map<String, String> ABBREVIATION_MAP;
    public static final char INNER_CLASS_SEPARATOR_CHAR = '$';
    private static final int MAX_DIMENSIONS = 255;
    public static final char PACKAGE_SEPARATOR_CHAR = '.';
    private static final Map<Class<?>, Class<?>> PRIMITIVE_WRAPPER_MAP;
    private static final Map<String, String> REVERSE_ABBREVIATION_MAP;
    private static final Map<Class<?>, Class<?>> WRAPPER_PRIMITIVE_MAP;
    private static final Comparator<Class<?>> COMPARATOR = new Comparator() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            int compare;
            compare = Objects.compare(ClassUtils.getName((Class<?>) obj), ClassUtils.getName((Class<?>) obj2), new Comparator() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda9
                @Override // java.util.Comparator
                public final int compare(Object obj3, Object obj4) {
                    int compareTo;
                    compareTo = ((String) obj3).compareTo((String) obj4);
                    return compareTo;
                }
            });
            return compare;
        }
    };
    public static final String PACKAGE_SEPARATOR = String.valueOf('.');
    public static final String INNER_CLASS_SEPARATOR = String.valueOf('$');
    private static final Map<String, Class<?>> NAME_PRIMITIVE_MAP = new HashMap();

    /* loaded from: classes9.dex */
    public enum Interfaces {
        INCLUDE,
        EXCLUDE
    }

    static {
        NAME_PRIMITIVE_MAP.put(Boolean.TYPE.getName(), Boolean.TYPE);
        NAME_PRIMITIVE_MAP.put(Byte.TYPE.getName(), Byte.TYPE);
        NAME_PRIMITIVE_MAP.put(Character.TYPE.getName(), Character.TYPE);
        NAME_PRIMITIVE_MAP.put(Double.TYPE.getName(), Double.TYPE);
        NAME_PRIMITIVE_MAP.put(Float.TYPE.getName(), Float.TYPE);
        NAME_PRIMITIVE_MAP.put(Integer.TYPE.getName(), Integer.TYPE);
        NAME_PRIMITIVE_MAP.put(Long.TYPE.getName(), Long.TYPE);
        NAME_PRIMITIVE_MAP.put(Short.TYPE.getName(), Short.TYPE);
        NAME_PRIMITIVE_MAP.put(Void.TYPE.getName(), Void.TYPE);
        PRIMITIVE_WRAPPER_MAP = new HashMap();
        PRIMITIVE_WRAPPER_MAP.put(Boolean.TYPE, Boolean.class);
        PRIMITIVE_WRAPPER_MAP.put(Byte.TYPE, Byte.class);
        PRIMITIVE_WRAPPER_MAP.put(Character.TYPE, Character.class);
        PRIMITIVE_WRAPPER_MAP.put(Short.TYPE, Short.class);
        PRIMITIVE_WRAPPER_MAP.put(Integer.TYPE, Integer.class);
        PRIMITIVE_WRAPPER_MAP.put(Long.TYPE, Long.class);
        PRIMITIVE_WRAPPER_MAP.put(Double.TYPE, Double.class);
        PRIMITIVE_WRAPPER_MAP.put(Float.TYPE, Float.class);
        PRIMITIVE_WRAPPER_MAP.put(Void.TYPE, Void.TYPE);
        WRAPPER_PRIMITIVE_MAP = new HashMap();
        PRIMITIVE_WRAPPER_MAP.forEach(new BiConsumer() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda3
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ClassUtils.lambda$static$1((Class) obj, (Class) obj2);
            }
        });
        Map<String, String> map = new HashMap<>();
        map.put(Integer.TYPE.getName(), "I");
        map.put(Boolean.TYPE.getName(), "Z");
        map.put(Float.TYPE.getName(), "F");
        map.put(Long.TYPE.getName(), "J");
        map.put(Short.TYPE.getName(), "S");
        map.put(Byte.TYPE.getName(), "B");
        map.put(Double.TYPE.getName(), "D");
        map.put(Character.TYPE.getName(), "C");
        ABBREVIATION_MAP = Collections.unmodifiableMap(map);
        REVERSE_ABBREVIATION_MAP = Collections.unmodifiableMap((Map) map.entrySet().stream().collect(Collectors.toMap(new ClassUtils$$ExternalSyntheticLambda4(), new ClassUtils$$ExternalSyntheticLambda5())));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$static$1(Class primitiveClass, Class wrapperClass) {
        if (!primitiveClass.equals(wrapperClass)) {
            WRAPPER_PRIMITIVE_MAP.put(wrapperClass, primitiveClass);
        }
    }

    public static Comparator<Class<?>> comparator() {
        return COMPARATOR;
    }

    public static List<String> convertClassesToClassNames(List<Class<?>> classes) {
        if (classes == null) {
            return null;
        }
        return (List) classes.stream().map(new Function() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda10
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String name;
                name = ClassUtils.getName((Class<?>) obj, (String) null);
                return name;
            }
        }).collect(Collectors.toList());
    }

    public static List<Class<?>> convertClassNamesToClasses(List<String> classNames) {
        if (classNames == null) {
            return null;
        }
        final List<Class<?>> classes = new ArrayList<>(classNames.size());
        classNames.forEach(new Consumer() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ClassUtils.lambda$convertClassNamesToClasses$3(classes, (String) obj);
            }
        });
        return classes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$convertClassNamesToClasses$3(List classes, String className) {
        try {
            classes.add(Class.forName(className));
        } catch (Exception e) {
            classes.add(null);
        }
    }

    public static String getAbbreviatedName(Class<?> cls, int lengthHint) {
        if (cls == null) {
            return "";
        }
        return getAbbreviatedName(cls.getName(), lengthHint);
    }

    public static String getAbbreviatedName(String className, int lengthHint) {
        if (lengthHint <= 0) {
            throw new IllegalArgumentException("len must be > 0");
        }
        if (className == null) {
            return "";
        }
        if (className.length() <= lengthHint) {
            return className;
        }
        char[] abbreviated = className.toCharArray();
        int target = 0;
        int source = 0;
        while (source < abbreviated.length) {
            int runAheadTarget = target;
            while (source < abbreviated.length && abbreviated[source] != '.') {
                abbreviated[runAheadTarget] = abbreviated[source];
                runAheadTarget++;
                source++;
            }
            target++;
            if (useFull(runAheadTarget, source, abbreviated.length, lengthHint) || target > runAheadTarget) {
                target = runAheadTarget;
            }
            if (source < abbreviated.length) {
                abbreviated[target] = abbreviated[source];
                target++;
                source++;
            }
        }
        return new String(abbreviated, 0, target);
    }

    public static List<Class<?>> getAllInterfaces(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        LinkedHashSet<Class<?>> interfacesFound = new LinkedHashSet<>();
        getAllInterfaces(cls, interfacesFound);
        return new ArrayList(interfacesFound);
    }

    private static void getAllInterfaces(Class<?> cls, HashSet<Class<?>> interfacesFound) {
        while (cls != null) {
            Class<?>[] interfaces = cls.getInterfaces();
            for (Class<?> i : interfaces) {
                if (interfacesFound.add(i)) {
                    getAllInterfaces(i, interfacesFound);
                }
            }
            cls = cls.getSuperclass();
        }
    }

    public static List<Class<?>> getAllSuperclasses(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        List<Class<?>> classes = new ArrayList<>();
        for (Class<?> superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            classes.add(superclass);
        }
        return classes;
    }

    public static String getCanonicalName(Class<?> cls) {
        return getCanonicalName(cls, "");
    }

    public static String getCanonicalName(Class<?> cls, String valueIfNull) {
        if (cls == null) {
            return valueIfNull;
        }
        String canonicalName = cls.getCanonicalName();
        return canonicalName == null ? valueIfNull : canonicalName;
    }

    public static String getCanonicalName(Object object) {
        return getCanonicalName(object, "");
    }

    public static String getCanonicalName(Object object, String valueIfNull) {
        if (object == null) {
            return valueIfNull;
        }
        String canonicalName = object.getClass().getCanonicalName();
        return canonicalName == null ? valueIfNull : canonicalName;
    }

    private static String getCanonicalName(String name) {
        String className = StringUtils.deleteWhitespace(name);
        if (className == null) {
            return null;
        }
        int dim = 0;
        while (className.charAt(dim) == '[') {
            dim++;
            if (dim > 255) {
                throw new IllegalArgumentException(String.format("Maximum array dimension %d exceeded", 255));
            }
        }
        if (dim < 1) {
            return className;
        }
        String className2 = className.substring(dim);
        if (className2.startsWith("L")) {
            className2 = className2.substring(1, className2.endsWith(";") ? className2.length() - 1 : className2.length());
        } else if (!className2.isEmpty()) {
            className2 = REVERSE_ABBREVIATION_MAP.get(className2.substring(0, 1));
        }
        StringBuilder canonicalClassNameBuffer = new StringBuilder(className2.length() + (dim * 2));
        canonicalClassNameBuffer.append(className2);
        for (int i = 0; i < dim; i++) {
            canonicalClassNameBuffer.append("[]");
        }
        return canonicalClassNameBuffer.toString();
    }

    public static Class<?> getClass(ClassLoader classLoader, String className) throws ClassNotFoundException {
        return getClass(classLoader, className, true);
    }

    public static Class<?> getClass(ClassLoader classLoader, String className, boolean initialize) throws ClassNotFoundException {
        int lastDotIndex;
        String next = className;
        do {
            try {
                Class<?> clazz = getPrimitiveClass(next);
                return clazz != null ? clazz : Class.forName(toCanonicalName(next), initialize, classLoader);
            } catch (ClassNotFoundException e) {
                lastDotIndex = next.lastIndexOf(46);
                if (lastDotIndex != -1) {
                    next = next.substring(0, lastDotIndex) + '$' + next.substring(lastDotIndex + 1);
                }
            }
        } while (lastDotIndex != -1);
        throw new ClassNotFoundException(next);
    }

    public static Class<?> getClass(String className) throws ClassNotFoundException {
        return getClass(className, true);
    }

    public static Class<?> getClass(String className, boolean initialize) throws ClassNotFoundException {
        ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
        ClassLoader loader = contextCL == null ? ClassUtils.class.getClassLoader() : contextCL;
        return getClass(loader, className, initialize);
    }

    public static <T> Class<T> getComponentType(Class<T[]> cls) {
        if (cls == null) {
            return null;
        }
        return (Class<T>) cls.getComponentType();
    }

    public static String getName(Class<?> cls) {
        return getName(cls, "");
    }

    public static String getName(Class<?> cls, String valueIfNull) {
        return cls == null ? valueIfNull : cls.getName();
    }

    public static String getName(Object object) {
        return getName(object, "");
    }

    public static String getName(Object object, String valueIfNull) {
        return object == null ? valueIfNull : object.getClass().getName();
    }

    public static String getPackageCanonicalName(Class<?> cls) {
        if (cls == null) {
            return "";
        }
        return getPackageCanonicalName(cls.getName());
    }

    public static String getPackageCanonicalName(Object object, String valueIfNull) {
        if (object == null) {
            return valueIfNull;
        }
        return getPackageCanonicalName(object.getClass().getName());
    }

    public static String getPackageCanonicalName(String name) {
        return getPackageName(getCanonicalName(name));
    }

    public static String getPackageName(Class<?> cls) {
        if (cls == null) {
            return "";
        }
        return getPackageName(cls.getName());
    }

    public static String getPackageName(Object object, String valueIfNull) {
        if (object == null) {
            return valueIfNull;
        }
        return getPackageName(object.getClass());
    }

    public static String getPackageName(String className) {
        if (StringUtils.isEmpty(className)) {
            return "";
        }
        while (className.charAt(0) == '[') {
            className = className.substring(1);
        }
        if (className.charAt(0) == 'L' && className.charAt(className.length() - 1) == ';') {
            className = className.substring(1);
        }
        int i = className.lastIndexOf(46);
        return i == -1 ? "" : className.substring(0, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Class<?> getPrimitiveClass(String className) {
        return NAME_PRIMITIVE_MAP.get(className);
    }

    public static Method getPublicMethod(Class<?> cls, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method declaredMethod = cls.getMethod(methodName, parameterTypes);
        if (isPublic(declaredMethod.getDeclaringClass())) {
            return declaredMethod;
        }
        List<Class<?>> candidateClasses = new ArrayList<>(getAllInterfaces(cls));
        candidateClasses.addAll(getAllSuperclasses(cls));
        for (Class<?> candidateClass : candidateClasses) {
            if (isPublic(candidateClass)) {
                try {
                    Method candidateMethod = candidateClass.getMethod(methodName, parameterTypes);
                    if (Modifier.isPublic(candidateMethod.getDeclaringClass().getModifiers())) {
                        return candidateMethod;
                    }
                } catch (NoSuchMethodException e) {
                }
            }
        }
        throw new NoSuchMethodException("Can't find a public method for " + methodName + StringUtils.SPACE + ArrayUtils.toString(parameterTypes));
    }

    public static String getShortCanonicalName(Class<?> cls) {
        return cls == null ? "" : getShortCanonicalName(cls.getCanonicalName());
    }

    public static String getShortCanonicalName(Object object, String valueIfNull) {
        return object == null ? valueIfNull : getShortCanonicalName(object.getClass().getCanonicalName());
    }

    public static String getShortCanonicalName(String canonicalName) {
        return getShortClassName(getCanonicalName(canonicalName));
    }

    public static String getShortClassName(Class<?> cls) {
        if (cls == null) {
            return "";
        }
        return getShortClassName(cls.getName());
    }

    public static String getShortClassName(Object object, String valueIfNull) {
        if (object == null) {
            return valueIfNull;
        }
        return getShortClassName(object.getClass());
    }

    public static String getShortClassName(String className) {
        if (StringUtils.isEmpty(className)) {
            return "";
        }
        StringBuilder arrayPrefix = new StringBuilder();
        if (className.startsWith(CollectionUtils.DEFAULT_TOSTRING_PREFIX)) {
            while (className.charAt(0) == '[') {
                className = className.substring(1);
                arrayPrefix.append("[]");
            }
            if (className.charAt(0) == 'L' && className.charAt(className.length() - 1) == ';') {
                className = className.substring(1, className.length() - 1);
            }
            if (REVERSE_ABBREVIATION_MAP.containsKey(className)) {
                className = REVERSE_ABBREVIATION_MAP.get(className);
            }
        }
        int lastDotIdx = className.lastIndexOf(46);
        int innerIdx = className.indexOf(36, lastDotIdx != -1 ? lastDotIdx + 1 : 0);
        String out = className.substring(lastDotIdx + 1);
        if (innerIdx != -1) {
            out = out.replace('$', '.');
        }
        return out + ((Object) arrayPrefix);
    }

    public static String getSimpleName(Class<?> cls) {
        return getSimpleName(cls, "");
    }

    public static String getSimpleName(Class<?> cls, String valueIfNull) {
        return cls == null ? valueIfNull : cls.getSimpleName();
    }

    public static String getSimpleName(Object object) {
        return getSimpleName(object, "");
    }

    public static String getSimpleName(Object object, String valueIfNull) {
        return object == null ? valueIfNull : object.getClass().getSimpleName();
    }

    public static Iterable<Class<?>> hierarchy(Class<?> type) {
        return hierarchy(type, Interfaces.EXCLUDE);
    }

    public static Iterable<Class<?>> hierarchy(final Class<?> type, Interfaces interfacesBehavior) {
        final Iterable<Class<?>> classes = new Iterable() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda7
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return ClassUtils.lambda$hierarchy$4(type);
            }
        };
        if (interfacesBehavior != Interfaces.INCLUDE) {
            return classes;
        }
        return new Iterable() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda8
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return ClassUtils.lambda$hierarchy$5(classes);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.apache.commons.lang3.ClassUtils$1, reason: invalid class name */
    /* loaded from: classes9.dex */
    public class AnonymousClass1 implements Iterator<Class<?>> {
        final /* synthetic */ AtomicReference val$next;

        AnonymousClass1(AtomicReference atomicReference) {
            this.val$next = atomicReference;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.val$next.get() != null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Class<?> next() {
            return (Class) this.val$next.getAndUpdate(new UnaryOperator() { // from class: org.apache.commons.lang3.ClassUtils$1$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    Class superclass;
                    superclass = ((Class) obj).getSuperclass();
                    return superclass;
                }
            });
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Iterator lambda$hierarchy$4(Class type) {
        AtomicReference<Class<?>> next = new AtomicReference<>(type);
        return new AnonymousClass1(next);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Iterator lambda$hierarchy$5(Iterable classes) {
        final Set<Class<?>> seenInterfaces = new HashSet<>();
        final Iterator<Class<?>> wrapped = classes.iterator();
        return new Iterator<Class<?>>() { // from class: org.apache.commons.lang3.ClassUtils.2
            Iterator<Class<?>> interfaces = Collections.emptyIterator();

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.interfaces.hasNext() || wrapped.hasNext();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Class<?> next() {
                if (this.interfaces.hasNext()) {
                    Class<?> nextInterface = this.interfaces.next();
                    seenInterfaces.add(nextInterface);
                    return nextInterface;
                }
                Class<?> nextSuperclass = (Class) wrapped.next();
                Set<Class<?>> currentInterfaces = new LinkedHashSet<>();
                walkInterfaces(currentInterfaces, nextSuperclass);
                this.interfaces = currentInterfaces.iterator();
                return nextSuperclass;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }

            private void walkInterfaces(Set<Class<?>> addTo, Class<?> c) {
                for (Class<?> iface : c.getInterfaces()) {
                    if (!seenInterfaces.contains(iface)) {
                        addTo.add(iface);
                    }
                    walkInterfaces(addTo, iface);
                }
            }
        };
    }

    public static boolean isAssignable(Class<?> cls, Class<?> toClass) {
        return isAssignable(cls, toClass, true);
    }

    public static boolean isAssignable(Class<?> cls, Class<?> toClass, boolean autoboxing) {
        if (toClass == null) {
            return false;
        }
        if (cls == null) {
            return !toClass.isPrimitive();
        }
        if (autoboxing) {
            if (cls.isPrimitive() && !toClass.isPrimitive() && (cls = primitiveToWrapper(cls)) == null) {
                return false;
            }
            if (toClass.isPrimitive() && !cls.isPrimitive() && (cls = wrapperToPrimitive(cls)) == null) {
                return false;
            }
        }
        if (cls.equals(toClass)) {
            return true;
        }
        if (cls.isPrimitive()) {
            if (!toClass.isPrimitive()) {
                return false;
            }
            if (Integer.TYPE.equals(cls)) {
                if (!Long.TYPE.equals(toClass) && !Float.TYPE.equals(toClass) && !Double.TYPE.equals(toClass)) {
                    return false;
                }
                return true;
            }
            if (Long.TYPE.equals(cls)) {
                if (!Float.TYPE.equals(toClass) && !Double.TYPE.equals(toClass)) {
                    return false;
                }
                return true;
            }
            if (Boolean.TYPE.equals(cls) || Double.TYPE.equals(cls)) {
                return false;
            }
            if (Float.TYPE.equals(cls)) {
                return Double.TYPE.equals(toClass);
            }
            if (Character.TYPE.equals(cls) || Short.TYPE.equals(cls)) {
                if (!Integer.TYPE.equals(toClass) && !Long.TYPE.equals(toClass) && !Float.TYPE.equals(toClass) && !Double.TYPE.equals(toClass)) {
                    return false;
                }
                return true;
            }
            if (!Byte.TYPE.equals(cls)) {
                return false;
            }
            if (!Short.TYPE.equals(toClass) && !Integer.TYPE.equals(toClass) && !Long.TYPE.equals(toClass) && !Float.TYPE.equals(toClass) && !Double.TYPE.equals(toClass)) {
                return false;
            }
            return true;
        }
        return toClass.isAssignableFrom(cls);
    }

    public static boolean isAssignable(Class<?>[] classArray, Class<?>... toClassArray) {
        return isAssignable(classArray, toClassArray, true);
    }

    public static boolean isAssignable(Class<?>[] classArray, Class<?>[] toClassArray, boolean autoboxing) {
        if (!ArrayUtils.isSameLength((Object[]) classArray, (Object[]) toClassArray)) {
            return false;
        }
        Class<?>[] classArray2 = ArrayUtils.nullToEmpty(classArray);
        Class<?>[] toClassArray2 = ArrayUtils.nullToEmpty(toClassArray);
        for (int i = 0; i < classArray2.length; i++) {
            if (!isAssignable(classArray2[i], toClassArray2[i], autoboxing)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isInnerClass(Class<?> cls) {
        return (cls == null || cls.getEnclosingClass() == null) ? false : true;
    }

    public static boolean isPrimitiveOrWrapper(Class<?> type) {
        if (type == null) {
            return false;
        }
        return type.isPrimitive() || isPrimitiveWrapper(type);
    }

    public static boolean isPrimitiveWrapper(Class<?> type) {
        return WRAPPER_PRIMITIVE_MAP.containsKey(type);
    }

    public static boolean isPublic(Class<?> cls) {
        return Modifier.isPublic(cls.getModifiers());
    }

    public static Class<?>[] primitivesToWrappers(final Class<?>... classes) {
        if (classes == null) {
            return null;
        }
        if (classes.length == 0) {
            return classes;
        }
        Class<?>[] convertedClasses = new Class[classes.length];
        Arrays.setAll(convertedClasses, new IntFunction() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda2
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                Class primitiveToWrapper;
                primitiveToWrapper = ClassUtils.primitiveToWrapper(classes[i]);
                return primitiveToWrapper;
            }
        });
        return convertedClasses;
    }

    public static Class<?> primitiveToWrapper(Class<?> cls) {
        if (cls == null || !cls.isPrimitive()) {
            return cls;
        }
        Class<?> convertedClass = PRIMITIVE_WRAPPER_MAP.get(cls);
        return convertedClass;
    }

    private static String toCanonicalName(String className) {
        String canonicalName = StringUtils.deleteWhitespace(className);
        Objects.requireNonNull(canonicalName, "className");
        if (canonicalName.endsWith("[]")) {
            StringBuilder classNameBuffer = new StringBuilder();
            while (canonicalName.endsWith("[]")) {
                canonicalName = canonicalName.substring(0, canonicalName.length() - 2);
                classNameBuffer.append(CollectionUtils.DEFAULT_TOSTRING_PREFIX);
            }
            String abbreviation = ABBREVIATION_MAP.get(canonicalName);
            if (abbreviation != null) {
                classNameBuffer.append(abbreviation);
            } else {
                classNameBuffer.append("L").append(canonicalName).append(";");
            }
            return classNameBuffer.toString();
        }
        return canonicalName;
    }

    public static Class<?>[] toClass(final Object... array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        Class<?>[] classes = new Class[array.length];
        Arrays.setAll(classes, new IntFunction() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda11
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return ClassUtils.lambda$toClass$7(array, i);
            }
        });
        return classes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Class lambda$toClass$7(Object[] array, int i) {
        if (array[i] == null) {
            return null;
        }
        return array[i].getClass();
    }

    private static boolean useFull(int runAheadTarget, int source, int originalLength, int desiredLength) {
        return source >= originalLength || (runAheadTarget + originalLength) - source <= desiredLength;
    }

    public static Class<?>[] wrappersToPrimitives(final Class<?>... classes) {
        if (classes == null) {
            return null;
        }
        if (classes.length == 0) {
            return classes;
        }
        Class<?>[] convertedClasses = new Class[classes.length];
        Arrays.setAll(convertedClasses, new IntFunction() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                Class wrapperToPrimitive;
                wrapperToPrimitive = ClassUtils.wrapperToPrimitive(classes[i]);
                return wrapperToPrimitive;
            }
        });
        return convertedClasses;
    }

    public static Class<?> wrapperToPrimitive(Class<?> cls) {
        return WRAPPER_PRIMITIVE_MAP.get(cls);
    }

    @Deprecated
    public ClassUtils() {
    }
}
