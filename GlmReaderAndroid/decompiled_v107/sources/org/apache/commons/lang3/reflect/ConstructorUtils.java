package org.apache.commons.lang3.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes9.dex */
public class ConstructorUtils {
    public static <T> Constructor<T> getAccessibleConstructor(Class<T> cls, Class<?>... parameterTypes) {
        Objects.requireNonNull(cls, "cls");
        try {
            return getAccessibleConstructor(cls.getConstructor(parameterTypes));
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static <T> Constructor<T> getAccessibleConstructor(Constructor<T> ctor) {
        Objects.requireNonNull(ctor, "ctor");
        if (MemberUtils.isAccessible(ctor) && isAccessible(ctor.getDeclaringClass())) {
            return ctor;
        }
        return null;
    }

    public static <T> Constructor<T> getMatchingAccessibleConstructor(Class<T> cls, Class<?>... parameterTypes) {
        Constructor<T> accessibleConstructor;
        Objects.requireNonNull(cls, "cls");
        try {
            return (Constructor) MemberUtils.setAccessibleWorkaround(cls.getConstructor(parameterTypes));
        } catch (NoSuchMethodException e) {
            Constructor<T> result = null;
            Constructor<?>[] ctors = cls.getConstructors();
            for (Constructor<?> ctor : ctors) {
                if (MemberUtils.isMatchingConstructor(ctor, parameterTypes) && (accessibleConstructor = getAccessibleConstructor(ctor)) != null) {
                    MemberUtils.setAccessibleWorkaround(accessibleConstructor);
                    if (result == null || MemberUtils.compareConstructorFit(accessibleConstructor, result, parameterTypes) < 0) {
                        result = accessibleConstructor;
                    }
                }
            }
            return result;
        }
    }

    public static <T> T invokeConstructor(Class<T> cls, Object... objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object[] nullToEmpty = ArrayUtils.nullToEmpty(objArr);
        return (T) invokeConstructor(cls, nullToEmpty, ClassUtils.toClass(nullToEmpty));
    }

    public static <T> T invokeConstructor(Class<T> cls, Object[] args, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        Constructor<T> ctor = getMatchingAccessibleConstructor(cls, ArrayUtils.nullToEmpty(parameterTypes));
        if (ctor == null) {
            throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
        }
        if (ctor.isVarArgs()) {
            Class<?>[] methodParameterTypes = ctor.getParameterTypes();
            args2 = MethodUtils.getVarArgs(args2, methodParameterTypes);
        }
        return ctor.newInstance(args2);
    }

    public static <T> T invokeExactConstructor(Class<T> cls, Object... objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object[] nullToEmpty = ArrayUtils.nullToEmpty(objArr);
        return (T) invokeExactConstructor(cls, nullToEmpty, ClassUtils.toClass(nullToEmpty));
    }

    public static <T> T invokeExactConstructor(Class<T> cls, Object[] args, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        Constructor<T> ctor = getAccessibleConstructor(cls, ArrayUtils.nullToEmpty(parameterTypes));
        if (ctor == null) {
            throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
        }
        return ctor.newInstance(args2);
    }

    private static boolean isAccessible(Class<?> type) {
        for (Class<?> cls = type; cls != null; cls = cls.getEnclosingClass()) {
            if (!ClassUtils.isPublic(cls)) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    public ConstructorUtils() {
    }
}
