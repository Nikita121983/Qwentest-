package org.apache.commons.lang3.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntFunction;
import org.apache.commons.lang3.ArraySorter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.stream.Streams;

/* loaded from: classes9.dex */
public class ReflectionToStringBuilder extends ToStringBuilder {
    private boolean appendStatics;
    private boolean appendTransients;
    protected String[] excludeFieldNames;
    private boolean excludeNullValues;
    protected String[] includeFieldNames;
    private Class<?> upToClass;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] toNoNullStringArray(Collection<String> collection) {
        if (collection == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        return toNoNullStringArray(collection.toArray());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$toNoNullStringArray$0(int x$0) {
        return new String[x$0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] toNoNullStringArray(Object[] array) {
        return (String[]) Streams.nonNull(array).map(new Function() { // from class: org.apache.commons.lang3.builder.ReflectionToStringBuilder$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String objects;
                objects = Objects.toString(obj);
                return objects;
            }
        }).toArray(new IntFunction() { // from class: org.apache.commons.lang3.builder.ReflectionToStringBuilder$$ExternalSyntheticLambda1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return ReflectionToStringBuilder.lambda$toNoNullStringArray$0(i);
            }
        });
    }

    public static String toString(Object object) {
        return toString(object, null, false, false, null);
    }

    public static String toString(Object object, ToStringStyle style) {
        return toString(object, style, false, false, null);
    }

    public static String toString(Object object, ToStringStyle style, boolean outputTransients) {
        return toString(object, style, outputTransients, false, null);
    }

    public static String toString(Object object, ToStringStyle style, boolean outputTransients, boolean outputStatics) {
        return toString(object, style, outputTransients, outputStatics, null);
    }

    public static <T> String toString(T object, ToStringStyle style, boolean outputTransients, boolean outputStatics, boolean excludeNullValues, Class<? super T> reflectUpToClass) {
        return new ReflectionToStringBuilder(object, style, null, reflectUpToClass, outputTransients, outputStatics, excludeNullValues).toString();
    }

    public static <T> String toString(T object, ToStringStyle style, boolean outputTransients, boolean outputStatics, Class<? super T> reflectUpToClass) {
        return new ReflectionToStringBuilder(object, style, null, reflectUpToClass, outputTransients, outputStatics).toString();
    }

    public static String toStringExclude(Object object, Collection<String> excludeFieldNames) {
        return toStringExclude(object, toNoNullStringArray(excludeFieldNames));
    }

    public static String toStringExclude(Object object, String... excludeFieldNames) {
        return new ReflectionToStringBuilder(object).setExcludeFieldNames(excludeFieldNames).toString();
    }

    public static String toStringInclude(Object object, Collection<String> includeFieldNames) {
        return toStringInclude(object, toNoNullStringArray(includeFieldNames));
    }

    public static String toStringInclude(Object object, String... includeFieldNames) {
        return new ReflectionToStringBuilder(object).setIncludeFieldNames(includeFieldNames).toString();
    }

    public ReflectionToStringBuilder(Object object) {
        super(object);
    }

    public ReflectionToStringBuilder(Object object, ToStringStyle style) {
        super(object, style);
    }

    public ReflectionToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer) {
        super(object, style, buffer);
    }

    public <T> ReflectionToStringBuilder(T object, ToStringStyle style, StringBuffer buffer, Class<? super T> reflectUpToClass, boolean outputTransients, boolean outputStatics) {
        super(object, style, buffer);
        setUpToClass(reflectUpToClass);
        setAppendTransients(outputTransients);
        setAppendStatics(outputStatics);
    }

    public <T> ReflectionToStringBuilder(T object, ToStringStyle style, StringBuffer buffer, Class<? super T> reflectUpToClass, boolean outputTransients, boolean outputStatics, boolean excludeNullValues) {
        super(object, style, buffer);
        setUpToClass(reflectUpToClass);
        setAppendTransients(outputTransients);
        setAppendStatics(outputStatics);
        setExcludeNullValues(excludeNullValues);
    }

    protected boolean accept(Field field) {
        if (field.getName().indexOf(36) != -1) {
            return false;
        }
        if (Modifier.isTransient(field.getModifiers()) && !isAppendTransients()) {
            return false;
        }
        if (Modifier.isStatic(field.getModifiers()) && !isAppendStatics()) {
            return false;
        }
        if (this.excludeFieldNames != null && Arrays.binarySearch(this.excludeFieldNames, field.getName()) >= 0) {
            return false;
        }
        if (ArrayUtils.isNotEmpty(this.includeFieldNames)) {
            return Arrays.binarySearch(this.includeFieldNames, field.getName()) >= 0;
        }
        return !field.isAnnotationPresent(ToStringExclude.class);
    }

    protected void appendFieldsIn(Class<?> clazz) {
        if (clazz.isArray()) {
            reflectionAppendArray(getObject());
            return;
        }
        Field[] fields = (Field[]) ArraySorter.sort(clazz.getDeclaredFields(), Comparator.comparing(new Function() { // from class: org.apache.commons.lang3.builder.ReflectionToStringBuilder$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String name;
                name = ((Field) obj).getName();
                return name;
            }
        }));
        AccessibleObject.setAccessible(fields, true);
        for (Field field : fields) {
            String fieldName = field.getName();
            if (accept(field)) {
                try {
                    Object fieldValue = getValue(field);
                    if (!this.excludeNullValues || fieldValue != null) {
                        append(fieldName, fieldValue, !field.isAnnotationPresent(ToStringSummary.class));
                    }
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

    public String[] getExcludeFieldNames() {
        return (String[]) this.excludeFieldNames.clone();
    }

    public String[] getIncludeFieldNames() {
        return (String[]) this.includeFieldNames.clone();
    }

    public Class<?> getUpToClass() {
        return this.upToClass;
    }

    protected Object getValue(Field field) throws IllegalAccessException {
        return field.get(getObject());
    }

    public boolean isAppendStatics() {
        return this.appendStatics;
    }

    public boolean isAppendTransients() {
        return this.appendTransients;
    }

    public boolean isExcludeNullValues() {
        return this.excludeNullValues;
    }

    public ReflectionToStringBuilder reflectionAppendArray(Object array) {
        getStyle().reflectionAppendArrayDetail(getStringBuffer(), null, array);
        return this;
    }

    public void setAppendStatics(boolean appendStatics) {
        this.appendStatics = appendStatics;
    }

    public void setAppendTransients(boolean appendTransients) {
        this.appendTransients = appendTransients;
    }

    public ReflectionToStringBuilder setExcludeFieldNames(String... excludeFieldNamesParam) {
        if (excludeFieldNamesParam == null) {
            this.excludeFieldNames = null;
        } else {
            this.excludeFieldNames = (String[]) ArraySorter.sort(toNoNullStringArray(excludeFieldNamesParam));
        }
        return this;
    }

    public void setExcludeNullValues(boolean excludeNullValues) {
        this.excludeNullValues = excludeNullValues;
    }

    public ReflectionToStringBuilder setIncludeFieldNames(String... includeFieldNamesParam) {
        if (includeFieldNamesParam == null) {
            this.includeFieldNames = null;
        } else {
            this.includeFieldNames = (String[]) ArraySorter.sort(toNoNullStringArray(includeFieldNamesParam));
        }
        return this;
    }

    public void setUpToClass(Class<?> clazz) {
        Object object;
        if (clazz != null && (object = getObject()) != null && !clazz.isInstance(object)) {
            throw new IllegalArgumentException("Specified class is not a superclass of the object");
        }
        this.upToClass = clazz;
    }

    @Override // org.apache.commons.lang3.builder.ToStringBuilder
    public String toString() {
        if (getObject() == null) {
            return getStyle().getNullText();
        }
        validate();
        Class<?> clazz = getObject().getClass();
        appendFieldsIn(clazz);
        while (clazz.getSuperclass() != null && clazz != getUpToClass()) {
            clazz = clazz.getSuperclass();
            appendFieldsIn(clazz);
        }
        return super.toString();
    }

    private void validate() {
        if (ArrayUtils.containsAny(this.excludeFieldNames, this.includeFieldNames)) {
            ToStringStyle.unregister(getObject());
            throw new IllegalStateException("includeFieldNames and excludeFieldNames must not intersect");
        }
    }
}
