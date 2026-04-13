package org.apache.commons.lang3;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.AnnotationUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.exception.UncheckedException;

/* loaded from: classes9.dex */
public class AnnotationUtils {
    private static final ToStringStyle TO_STRING_STYLE = new AnonymousClass1();

    /* renamed from: org.apache.commons.lang3.AnnotationUtils$1, reason: invalid class name */
    /* loaded from: classes9.dex */
    class AnonymousClass1 extends ToStringStyle {
        private static final long serialVersionUID = 1;

        AnonymousClass1() {
            setDefaultFullDetail(true);
            setArrayContentDetail(true);
            setUseClassName(true);
            setUseShortClassName(true);
            setUseIdentityHashCode(false);
            setContentStart("(");
            setContentEnd(")");
            setFieldSeparator(", ");
            setArrayStart(CollectionUtils.DEFAULT_TOSTRING_PREFIX);
            setArrayEnd(CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.commons.lang3.builder.ToStringStyle
        public void appendDetail(StringBuffer buffer, String fieldName, Object value) {
            if (value instanceof Annotation) {
                value = AnnotationUtils.toString((Annotation) value);
            }
            super.appendDetail(buffer, fieldName, value);
        }

        @Override // org.apache.commons.lang3.builder.ToStringStyle
        protected String getShortClassName(Class<?> cls) {
            Stream<Class<?>> stream = ClassUtils.getAllInterfaces(cls).stream();
            final Class<Annotation> cls2 = Annotation.class;
            Objects.requireNonNull(Annotation.class);
            return (String) stream.filter(new Predicate() { // from class: org.apache.commons.lang3.AnnotationUtils$1$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean isAssignableFrom;
                    isAssignableFrom = cls2.isAssignableFrom((Class) obj);
                    return isAssignableFrom;
                }
            }).findFirst().map(new Function() { // from class: org.apache.commons.lang3.AnnotationUtils$1$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return AnnotationUtils.AnonymousClass1.lambda$getShortClassName$0((Class) obj);
                }
            }).orElse("");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ String lambda$getShortClassName$0(Class iface) {
            return "@" + iface.getName();
        }
    }

    private static boolean annotationArrayMemberEquals(Annotation[] a1, Annotation[] a2) {
        if (a1.length != a2.length) {
            return false;
        }
        for (int i = 0; i < a1.length; i++) {
            if (!equals(a1[i], a2[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean arrayMemberEquals(Class<?> componentType, Object o1, Object o2) {
        if (componentType.isAnnotation()) {
            return annotationArrayMemberEquals((Annotation[]) o1, (Annotation[]) o2);
        }
        if (componentType.equals(Byte.TYPE)) {
            return Arrays.equals((byte[]) o1, (byte[]) o2);
        }
        if (componentType.equals(Short.TYPE)) {
            return Arrays.equals((short[]) o1, (short[]) o2);
        }
        if (componentType.equals(Integer.TYPE)) {
            return Arrays.equals((int[]) o1, (int[]) o2);
        }
        if (componentType.equals(Character.TYPE)) {
            return Arrays.equals((char[]) o1, (char[]) o2);
        }
        if (componentType.equals(Long.TYPE)) {
            return Arrays.equals((long[]) o1, (long[]) o2);
        }
        if (componentType.equals(Float.TYPE)) {
            return Arrays.equals((float[]) o1, (float[]) o2);
        }
        if (componentType.equals(Double.TYPE)) {
            return Arrays.equals((double[]) o1, (double[]) o2);
        }
        if (componentType.equals(Boolean.TYPE)) {
            return Arrays.equals((boolean[]) o1, (boolean[]) o2);
        }
        return Arrays.equals((Object[]) o1, (Object[]) o2);
    }

    private static int arrayMemberHash(Class<?> componentType, Object o) {
        if (componentType.equals(Byte.TYPE)) {
            return Arrays.hashCode((byte[]) o);
        }
        if (componentType.equals(Short.TYPE)) {
            return Arrays.hashCode((short[]) o);
        }
        if (componentType.equals(Integer.TYPE)) {
            return Arrays.hashCode((int[]) o);
        }
        if (componentType.equals(Character.TYPE)) {
            return Arrays.hashCode((char[]) o);
        }
        if (componentType.equals(Long.TYPE)) {
            return Arrays.hashCode((long[]) o);
        }
        if (componentType.equals(Float.TYPE)) {
            return Arrays.hashCode((float[]) o);
        }
        if (componentType.equals(Double.TYPE)) {
            return Arrays.hashCode((double[]) o);
        }
        if (componentType.equals(Boolean.TYPE)) {
            return Arrays.hashCode((boolean[]) o);
        }
        return Arrays.hashCode((Object[]) o);
    }

    public static boolean equals(Annotation a1, Annotation a2) {
        if (a1 == a2) {
            return true;
        }
        if (a1 == null || a2 == null) {
            return false;
        }
        Class<? extends Annotation> type1 = a1.annotationType();
        Class<? extends Annotation> type2 = a2.annotationType();
        Validate.notNull(type1, "Annotation %s with null annotationType()", a1);
        Validate.notNull(type2, "Annotation %s with null annotationType()", a2);
        if (!type1.equals(type2)) {
            return false;
        }
        try {
            for (Method m : type1.getDeclaredMethods()) {
                if (m.getParameterTypes().length == 0 && isValidAnnotationMemberType(m.getReturnType())) {
                    Object v1 = m.invoke(a1, new Object[0]);
                    Object v2 = m.invoke(a2, new Object[0]);
                    if (!memberEquals(m.getReturnType(), v1, v2)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (ReflectiveOperationException e) {
            return false;
        }
    }

    public static int hashCode(Annotation a) {
        int result = 0;
        Class<? extends Annotation> type = a.annotationType();
        for (Method m : type.getDeclaredMethods()) {
            try {
                Object value = m.invoke(a, new Object[0]);
                if (value == null) {
                    throw new IllegalStateException(String.format("Annotation method %s returned null", m));
                }
                result += hashMember(m.getName(), value);
            } catch (ReflectiveOperationException ex) {
                throw new UncheckedException(ex);
            }
        }
        return result;
    }

    private static int hashMember(String name, Object value) {
        int part1 = name.hashCode() * 127;
        if (ObjectUtils.isArray(value)) {
            return arrayMemberHash(value.getClass().getComponentType(), value) ^ part1;
        }
        if (value instanceof Annotation) {
            return hashCode((Annotation) value) ^ part1;
        }
        return value.hashCode() ^ part1;
    }

    public static boolean isValidAnnotationMemberType(Class<?> type) {
        if (type == null) {
            return false;
        }
        if (type.isArray()) {
            type = type.getComponentType();
        }
        return type.isPrimitive() || type.isEnum() || type.isAnnotation() || String.class.equals(type) || Class.class.equals(type);
    }

    private static boolean memberEquals(Class<?> type, Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        if (type.isArray()) {
            return arrayMemberEquals(type.getComponentType(), o1, o2);
        }
        if (type.isAnnotation()) {
            return equals((Annotation) o1, (Annotation) o2);
        }
        return o1.equals(o2);
    }

    public static String toString(Annotation a) {
        ToStringBuilder builder = new ToStringBuilder(a, TO_STRING_STYLE);
        for (Method m : a.annotationType().getDeclaredMethods()) {
            if (m.getParameterTypes().length <= 0) {
                try {
                    builder.append(m.getName(), m.invoke(a, new Object[0]));
                } catch (ReflectiveOperationException ex) {
                    throw new UncheckedException(ex);
                }
            }
        }
        return builder.build();
    }

    @Deprecated
    public AnnotationUtils() {
    }
}
