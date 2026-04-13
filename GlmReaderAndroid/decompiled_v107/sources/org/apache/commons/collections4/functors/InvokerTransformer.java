package org.apache.commons.collections4.functors;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.Transformer;

/* loaded from: classes9.dex */
public class InvokerTransformer<T, R> implements Transformer<T, R> {
    private final Object[] iArgs;
    private final String iMethodName;
    private final Class<?>[] iParamTypes;

    public static <I, O> Transformer<I, O> invokerTransformer(String methodName) {
        return new InvokerTransformer((String) Objects.requireNonNull(methodName, "methodName"));
    }

    public static <I, O> Transformer<I, O> invokerTransformer(String methodName, Class<?>[] paramTypes, Object[] args) {
        Objects.requireNonNull(methodName, "methodName");
        if ((paramTypes == null && args != null) || ((paramTypes != null && args == null) || (paramTypes != null && args != null && paramTypes.length != args.length))) {
            throw new IllegalArgumentException("The parameter types must match the arguments");
        }
        if (paramTypes == null || paramTypes.length == 0) {
            return new InvokerTransformer(methodName);
        }
        return new InvokerTransformer(methodName, paramTypes, args);
    }

    private InvokerTransformer(String methodName) {
        this.iMethodName = methodName;
        this.iParamTypes = null;
        this.iArgs = null;
    }

    public InvokerTransformer(String methodName, Class<?>[] paramTypes, Object[] args) {
        this.iMethodName = methodName;
        this.iParamTypes = paramTypes != null ? (Class[]) paramTypes.clone() : null;
        this.iArgs = args != null ? (Object[]) args.clone() : null;
    }

    @Override // org.apache.commons.collections4.Transformer
    public R transform(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return (R) obj.getClass().getMethod(this.iMethodName, this.iParamTypes).invoke(obj, this.iArgs);
        } catch (IllegalAccessException e) {
            throw new FunctorException("InvokerTransformer: The method '" + this.iMethodName + "' on '" + obj.getClass() + "' cannot be accessed");
        } catch (NoSuchMethodException e2) {
            throw new FunctorException("InvokerTransformer: The method '" + this.iMethodName + "' on '" + obj.getClass() + "' does not exist");
        } catch (InvocationTargetException e3) {
            throw new FunctorException("InvokerTransformer: The method '" + this.iMethodName + "' on '" + obj.getClass() + "' threw an exception", e3);
        }
    }
}
