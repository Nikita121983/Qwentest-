package org.apache.commons.lang3.builder;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.function.Supplier;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.Strings;
import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes9.dex */
public abstract class ToStringStyle implements Serializable {
    private static final long serialVersionUID = -2587890625525655916L;
    private boolean fieldSeparatorAtEnd;
    private boolean fieldSeparatorAtStart;
    private boolean useShortClassName;
    public static final ToStringStyle DEFAULT_STYLE = new DefaultToStringStyle();
    public static final ToStringStyle MULTI_LINE_STYLE = new MultiLineToStringStyle();
    public static final ToStringStyle NO_FIELD_NAMES_STYLE = new NoFieldNameToStringStyle();
    public static final ToStringStyle SHORT_PREFIX_STYLE = new ShortPrefixToStringStyle();
    public static final ToStringStyle SIMPLE_STYLE = new SimpleToStringStyle();
    public static final ToStringStyle NO_CLASS_NAME_STYLE = new NoClassNameToStringStyle();
    public static final ToStringStyle JSON_STYLE = new JsonToStringStyle();
    private static final ThreadLocal<WeakHashMap<Object, Object>> REGISTRY = ThreadLocal.withInitial(new Supplier() { // from class: org.apache.commons.lang3.builder.ToStringStyle$$ExternalSyntheticLambda0
        @Override // java.util.function.Supplier
        public final Object get() {
            return ToStringStyle.m2187$r8$lambda$MSbZIt9Svlzq1LgR6lqIQHlf6s();
        }
    });
    private boolean useFieldNames = true;
    private boolean useClassName = true;
    private boolean useIdentityHashCode = true;
    private String contentStart = CollectionUtils.DEFAULT_TOSTRING_PREFIX;
    private String contentEnd = CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    private String fieldNameValueSeparator = "=";
    private String fieldSeparator = CollectionUtils.COMMA;
    private String arrayStart = VectorFormat.DEFAULT_PREFIX;
    private String arraySeparator = CollectionUtils.COMMA;
    private boolean arrayContentDetail = true;
    private String arrayEnd = VectorFormat.DEFAULT_SUFFIX;
    private boolean defaultFullDetail = true;
    private String nullText = "<null>";
    private String sizeStartText = "<size=";
    private String sizeEndText = ">";
    private String summaryObjectStartText = "<";
    private String summaryObjectEndText = ">";

    /* renamed from: $r8$lambda$MSb-ZIt9Svlzq1LgR6lqIQHlf6s, reason: not valid java name */
    public static /* synthetic */ WeakHashMap m2187$r8$lambda$MSbZIt9Svlzq1LgR6lqIQHlf6s() {
        return new WeakHashMap();
    }

    /* loaded from: classes9.dex */
    private static final class DefaultToStringStyle extends ToStringStyle {
        private static final long serialVersionUID = 1;

        DefaultToStringStyle() {
        }

        private Object readResolve() {
            return DEFAULT_STYLE;
        }
    }

    /* loaded from: classes9.dex */
    private static final class JsonToStringStyle extends ToStringStyle {
        private static final String FIELD_NAME_QUOTE = "\"";
        private static final long serialVersionUID = 1;

        JsonToStringStyle() {
            setUseClassName(false);
            setUseIdentityHashCode(false);
            setContentStart(VectorFormat.DEFAULT_PREFIX);
            setContentEnd(VectorFormat.DEFAULT_SUFFIX);
            setArrayStart(CollectionUtils.DEFAULT_TOSTRING_PREFIX);
            setArrayEnd(CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            setFieldSeparator(CollectionUtils.COMMA);
            setFieldNameValueSeparator(":");
            setNullText("null");
            setSummaryObjectStartText("\"<");
            setSummaryObjectEndText(">\"");
            setSizeStartText("\"<size=");
            setSizeEndText(">\"");
        }

        @Override // org.apache.commons.lang3.builder.ToStringStyle
        public void append(StringBuffer buffer, String fieldName, boolean[] array, Boolean fullDetail) {
            checkAppendInput(fieldName, fullDetail);
            super.append(buffer, fieldName, array, fullDetail);
        }

        @Override // org.apache.commons.lang3.builder.ToStringStyle
        public void append(StringBuffer buffer, String fieldName, byte[] array, Boolean fullDetail) {
            checkAppendInput(fieldName, fullDetail);
            super.append(buffer, fieldName, array, fullDetail);
        }

        @Override // org.apache.commons.lang3.builder.ToStringStyle
        public void append(StringBuffer buffer, String fieldName, char[] array, Boolean fullDetail) {
            checkAppendInput(fieldName, fullDetail);
            super.append(buffer, fieldName, array, fullDetail);
        }

        @Override // org.apache.commons.lang3.builder.ToStringStyle
        public void append(StringBuffer buffer, String fieldName, double[] array, Boolean fullDetail) {
            checkAppendInput(fieldName, fullDetail);
            super.append(buffer, fieldName, array, fullDetail);
        }

        @Override // org.apache.commons.lang3.builder.ToStringStyle
        public void append(StringBuffer buffer, String fieldName, float[] array, Boolean fullDetail) {
            checkAppendInput(fieldName, fullDetail);
            super.append(buffer, fieldName, array, fullDetail);
        }

        @Override // org.apache.commons.lang3.builder.ToStringStyle
        public void append(StringBuffer buffer, String fieldName, int[] array, Boolean fullDetail) {
            checkAppendInput(fieldName, fullDetail);
            super.append(buffer, fieldName, array, fullDetail);
        }

        @Override // org.apache.commons.lang3.builder.ToStringStyle
        public void append(StringBuffer buffer, String fieldName, long[] array, Boolean fullDetail) {
            checkAppendInput(fieldName, fullDetail);
            super.append(buffer, fieldName, array, fullDetail);
        }

        @Override // org.apache.commons.lang3.builder.ToStringStyle
        public void append(StringBuffer buffer, String fieldName, Object value, Boolean fullDetail) {
            checkAppendInput(fieldName, fullDetail);
            super.append(buffer, fieldName, value, fullDetail);
        }

        @Override // org.apache.commons.lang3.builder.ToStringStyle
        public void append(StringBuffer buffer, String fieldName, Object[] array, Boolean fullDetail) {
            checkAppendInput(fieldName, fullDetail);
            super.append(buffer, fieldName, array, fullDetail);
        }

        @Override // org.apache.commons.lang3.builder.ToStringStyle
        public void append(StringBuffer buffer, String fieldName, short[] array, Boolean fullDetail) {
            checkAppendInput(fieldName, fullDetail);
            super.append(buffer, fieldName, array, fullDetail);
        }

        @Override // org.apache.commons.lang3.builder.ToStringStyle
        protected void appendDetail(StringBuffer buffer, String fieldName, char value) {
            appendValueAsString(buffer, String.valueOf(value));
        }

        @Override // org.apache.commons.lang3.builder.ToStringStyle
        protected void appendDetail(StringBuffer buffer, String fieldName, Collection<?> coll) {
            if (coll != null && !coll.isEmpty()) {
                buffer.append(getArrayStart());
                int i = 0;
                for (Object item : coll) {
                    appendDetail(buffer, fieldName, i, item);
                    i++;
                }
                buffer.append(getArrayEnd());
                return;
            }
            buffer.append(coll);
        }

        @Override // org.apache.commons.lang3.builder.ToStringStyle
        protected void appendDetail(StringBuffer buffer, String fieldName, Map<?, ?> map) {
            if (map != null && !map.isEmpty()) {
                buffer.append(getContentStart());
                boolean firstItem = true;
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    String keyStr = Objects.toString(entry.getKey(), null);
                    if (keyStr != null) {
                        if (firstItem) {
                            firstItem = false;
                        } else {
                            appendFieldEnd(buffer, keyStr);
                        }
                        appendFieldStart(buffer, keyStr);
                        Object value = entry.getValue();
                        if (value == null) {
                            appendNullText(buffer, keyStr);
                        } else {
                            appendInternal(buffer, keyStr, value, true);
                        }
                    }
                }
                buffer.append(getContentEnd());
                return;
            }
            buffer.append(map);
        }

        @Override // org.apache.commons.lang3.builder.ToStringStyle
        protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
            if (value == null) {
                appendNullText(buffer, fieldName);
                return;
            }
            if ((value instanceof String) || (value instanceof Character)) {
                appendValueAsString(buffer, value.toString());
                return;
            }
            if ((value instanceof Number) || (value instanceof Boolean)) {
                buffer.append(value);
                return;
            }
            String valueAsString = value.toString();
            if (isJsonObject(valueAsString) || isJsonArray(valueAsString)) {
                buffer.append(value);
            } else {
                appendDetail(buffer, fieldName, valueAsString);
            }
        }

        @Override // org.apache.commons.lang3.builder.ToStringStyle
        protected void appendFieldStart(StringBuffer buffer, String fieldName) {
            checkFieldName(fieldName);
            super.appendFieldStart(buffer, FIELD_NAME_QUOTE + StringEscapeUtils.escapeJson(fieldName) + FIELD_NAME_QUOTE);
        }

        private void appendValueAsString(StringBuffer buffer, String value) {
            buffer.append('\"').append(StringEscapeUtils.escapeJson(value)).append('\"');
        }

        private void checkAppendInput(String fieldName, Boolean fullDetail) {
            checkFieldName(fieldName);
            checkIsFullDetail(fullDetail);
        }

        private void checkFieldName(String fieldName) {
            if (fieldName == null) {
                throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
            }
        }

        private void checkIsFullDetail(Boolean fullDetail) {
            if (!isFullDetail(fullDetail)) {
                throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
            }
        }

        private boolean isJsonArray(String valueAsString) {
            return valueAsString.startsWith(getArrayStart()) && valueAsString.endsWith(getArrayEnd());
        }

        private boolean isJsonObject(String valueAsString) {
            return valueAsString.startsWith(getContentStart()) && valueAsString.endsWith(getContentEnd());
        }

        private Object readResolve() {
            return JSON_STYLE;
        }
    }

    /* loaded from: classes9.dex */
    private static final class MultiLineToStringStyle extends ToStringStyle {
        private static final long serialVersionUID = 1;

        MultiLineToStringStyle() {
            setContentStart(CollectionUtils.DEFAULT_TOSTRING_PREFIX);
            setFieldSeparator(System.lineSeparator() + "  ");
            setFieldSeparatorAtStart(true);
            setContentEnd(System.lineSeparator() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        }

        private Object readResolve() {
            return MULTI_LINE_STYLE;
        }
    }

    /* loaded from: classes9.dex */
    private static final class NoClassNameToStringStyle extends ToStringStyle {
        private static final long serialVersionUID = 1;

        NoClassNameToStringStyle() {
            setUseClassName(false);
            setUseIdentityHashCode(false);
        }

        private Object readResolve() {
            return NO_CLASS_NAME_STYLE;
        }
    }

    /* loaded from: classes9.dex */
    private static final class NoFieldNameToStringStyle extends ToStringStyle {
        private static final long serialVersionUID = 1;

        NoFieldNameToStringStyle() {
            setUseFieldNames(false);
        }

        private Object readResolve() {
            return NO_FIELD_NAMES_STYLE;
        }
    }

    /* loaded from: classes9.dex */
    private static final class ShortPrefixToStringStyle extends ToStringStyle {
        private static final long serialVersionUID = 1;

        ShortPrefixToStringStyle() {
            setUseShortClassName(true);
            setUseIdentityHashCode(false);
        }

        private Object readResolve() {
            return SHORT_PREFIX_STYLE;
        }
    }

    /* loaded from: classes9.dex */
    private static final class SimpleToStringStyle extends ToStringStyle {
        private static final long serialVersionUID = 1;

        SimpleToStringStyle() {
            setUseClassName(false);
            setUseIdentityHashCode(false);
            setUseFieldNames(false);
            setContentStart("");
            setContentEnd("");
        }

        private Object readResolve() {
            return SIMPLE_STYLE;
        }
    }

    public static Map<Object, Object> getRegistry() {
        return REGISTRY.get();
    }

    static boolean isRegistered(Object value) {
        return getRegistry().containsKey(value);
    }

    static void register(Object value) {
        if (value != null) {
            getRegistry().put(value, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void unregister(Object value) {
        if (value != null) {
            Map<Object, Object> m = getRegistry();
            m.remove(value);
            if (m.isEmpty()) {
                REGISTRY.remove();
            }
        }
    }

    public void append(StringBuffer buffer, String fieldName, boolean value) {
        appendFieldStart(buffer, fieldName);
        appendDetail(buffer, fieldName, value);
        appendFieldEnd(buffer, fieldName);
    }

    public void append(StringBuffer buffer, String fieldName, boolean[] array, Boolean fullDetail) {
        appendFieldStart(buffer, fieldName);
        if (array == null) {
            appendNullText(buffer, fieldName);
        } else if (isFullDetail(fullDetail)) {
            appendDetail(buffer, fieldName, array);
        } else {
            appendSummary(buffer, fieldName, array);
        }
        appendFieldEnd(buffer, fieldName);
    }

    public void append(StringBuffer buffer, String fieldName, byte value) {
        appendFieldStart(buffer, fieldName);
        appendDetail(buffer, fieldName, value);
        appendFieldEnd(buffer, fieldName);
    }

    public void append(StringBuffer buffer, String fieldName, byte[] array, Boolean fullDetail) {
        appendFieldStart(buffer, fieldName);
        if (array == null) {
            appendNullText(buffer, fieldName);
        } else if (isFullDetail(fullDetail)) {
            appendDetail(buffer, fieldName, array);
        } else {
            appendSummary(buffer, fieldName, array);
        }
        appendFieldEnd(buffer, fieldName);
    }

    public void append(StringBuffer buffer, String fieldName, char value) {
        appendFieldStart(buffer, fieldName);
        appendDetail(buffer, fieldName, value);
        appendFieldEnd(buffer, fieldName);
    }

    public void append(StringBuffer buffer, String fieldName, char[] array, Boolean fullDetail) {
        appendFieldStart(buffer, fieldName);
        if (array == null) {
            appendNullText(buffer, fieldName);
        } else if (isFullDetail(fullDetail)) {
            appendDetail(buffer, fieldName, array);
        } else {
            appendSummary(buffer, fieldName, array);
        }
        appendFieldEnd(buffer, fieldName);
    }

    public void append(StringBuffer buffer, String fieldName, double value) {
        appendFieldStart(buffer, fieldName);
        appendDetail(buffer, fieldName, value);
        appendFieldEnd(buffer, fieldName);
    }

    public void append(StringBuffer buffer, String fieldName, double[] array, Boolean fullDetail) {
        appendFieldStart(buffer, fieldName);
        if (array == null) {
            appendNullText(buffer, fieldName);
        } else if (isFullDetail(fullDetail)) {
            appendDetail(buffer, fieldName, array);
        } else {
            appendSummary(buffer, fieldName, array);
        }
        appendFieldEnd(buffer, fieldName);
    }

    public void append(StringBuffer buffer, String fieldName, float value) {
        appendFieldStart(buffer, fieldName);
        appendDetail(buffer, fieldName, value);
        appendFieldEnd(buffer, fieldName);
    }

    public void append(StringBuffer buffer, String fieldName, float[] array, Boolean fullDetail) {
        appendFieldStart(buffer, fieldName);
        if (array == null) {
            appendNullText(buffer, fieldName);
        } else if (isFullDetail(fullDetail)) {
            appendDetail(buffer, fieldName, array);
        } else {
            appendSummary(buffer, fieldName, array);
        }
        appendFieldEnd(buffer, fieldName);
    }

    public void append(StringBuffer buffer, String fieldName, int value) {
        appendFieldStart(buffer, fieldName);
        appendDetail(buffer, fieldName, value);
        appendFieldEnd(buffer, fieldName);
    }

    public void append(StringBuffer buffer, String fieldName, int[] array, Boolean fullDetail) {
        appendFieldStart(buffer, fieldName);
        if (array == null) {
            appendNullText(buffer, fieldName);
        } else if (isFullDetail(fullDetail)) {
            appendDetail(buffer, fieldName, array);
        } else {
            appendSummary(buffer, fieldName, array);
        }
        appendFieldEnd(buffer, fieldName);
    }

    public void append(StringBuffer buffer, String fieldName, long value) {
        appendFieldStart(buffer, fieldName);
        appendDetail(buffer, fieldName, value);
        appendFieldEnd(buffer, fieldName);
    }

    public void append(StringBuffer buffer, String fieldName, long[] array, Boolean fullDetail) {
        appendFieldStart(buffer, fieldName);
        if (array == null) {
            appendNullText(buffer, fieldName);
        } else if (isFullDetail(fullDetail)) {
            appendDetail(buffer, fieldName, array);
        } else {
            appendSummary(buffer, fieldName, array);
        }
        appendFieldEnd(buffer, fieldName);
    }

    public void append(StringBuffer buffer, String fieldName, Object value, Boolean fullDetail) {
        appendFieldStart(buffer, fieldName);
        if (value == null) {
            appendNullText(buffer, fieldName);
        } else {
            appendInternal(buffer, fieldName, value, isFullDetail(fullDetail));
        }
        appendFieldEnd(buffer, fieldName);
    }

    public void append(StringBuffer buffer, String fieldName, Object[] array, Boolean fullDetail) {
        appendFieldStart(buffer, fieldName);
        if (array == null) {
            appendNullText(buffer, fieldName);
        } else if (isFullDetail(fullDetail)) {
            appendDetail(buffer, fieldName, array);
        } else {
            appendSummary(buffer, fieldName, array);
        }
        appendFieldEnd(buffer, fieldName);
    }

    public void append(StringBuffer buffer, String fieldName, short value) {
        appendFieldStart(buffer, fieldName);
        appendDetail(buffer, fieldName, value);
        appendFieldEnd(buffer, fieldName);
    }

    public void append(StringBuffer buffer, String fieldName, short[] array, Boolean fullDetail) {
        appendFieldStart(buffer, fieldName);
        if (array == null) {
            appendNullText(buffer, fieldName);
        } else if (isFullDetail(fullDetail)) {
            appendDetail(buffer, fieldName, array);
        } else {
            appendSummary(buffer, fieldName, array);
        }
        appendFieldEnd(buffer, fieldName);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendClassName(StringBuffer buffer, Object object) {
        if (this.useClassName && object != null) {
            register(object);
            if (this.useShortClassName) {
                buffer.append(getShortClassName(object.getClass()));
            } else {
                buffer.append(object.getClass().getName());
            }
        }
    }

    protected void appendContentEnd(StringBuffer buffer) {
        buffer.append(this.contentEnd);
    }

    protected void appendContentStart(StringBuffer buffer) {
        buffer.append(this.contentStart);
    }

    protected void appendCyclicObject(StringBuffer buffer, String fieldName, Object value) {
        ObjectUtils.identityToString(buffer, value);
    }

    protected void appendDetail(StringBuffer buffer, String fieldName, boolean value) {
        buffer.append(value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendDetail(StringBuffer buffer, String fieldName, boolean[] array) {
        buffer.append(this.arrayStart);
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buffer.append(this.arraySeparator);
            }
            appendDetail(buffer, fieldName, array[i]);
        }
        buffer.append(this.arrayEnd);
    }

    protected void appendDetail(StringBuffer buffer, String fieldName, byte value) {
        buffer.append((int) value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendDetail(StringBuffer buffer, String fieldName, byte[] array) {
        buffer.append(this.arrayStart);
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buffer.append(this.arraySeparator);
            }
            appendDetail(buffer, fieldName, array[i]);
        }
        buffer.append(this.arrayEnd);
    }

    protected void appendDetail(StringBuffer buffer, String fieldName, char value) {
        buffer.append(value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendDetail(StringBuffer buffer, String fieldName, char[] array) {
        buffer.append(this.arrayStart);
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buffer.append(this.arraySeparator);
            }
            appendDetail(buffer, fieldName, array[i]);
        }
        buffer.append(this.arrayEnd);
    }

    protected void appendDetail(StringBuffer buffer, String fieldName, Collection<?> coll) {
        buffer.append(coll);
    }

    protected void appendDetail(StringBuffer buffer, String fieldName, double value) {
        buffer.append(value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendDetail(StringBuffer buffer, String fieldName, double[] array) {
        buffer.append(this.arrayStart);
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buffer.append(this.arraySeparator);
            }
            appendDetail(buffer, fieldName, array[i]);
        }
        buffer.append(this.arrayEnd);
    }

    protected void appendDetail(StringBuffer buffer, String fieldName, float value) {
        buffer.append(value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendDetail(StringBuffer buffer, String fieldName, float[] array) {
        buffer.append(this.arrayStart);
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buffer.append(this.arraySeparator);
            }
            appendDetail(buffer, fieldName, array[i]);
        }
        buffer.append(this.arrayEnd);
    }

    protected void appendDetail(StringBuffer buffer, String fieldName, int value) {
        buffer.append(value);
    }

    protected void appendDetail(StringBuffer buffer, String fieldName, int i, Object item) {
        if (i > 0) {
            buffer.append(this.arraySeparator);
        }
        if (item == null) {
            appendNullText(buffer, fieldName);
        } else {
            appendInternal(buffer, fieldName, item, this.arrayContentDetail);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendDetail(StringBuffer buffer, String fieldName, int[] array) {
        buffer.append(this.arrayStart);
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buffer.append(this.arraySeparator);
            }
            appendDetail(buffer, fieldName, array[i]);
        }
        buffer.append(this.arrayEnd);
    }

    protected void appendDetail(StringBuffer buffer, String fieldName, long value) {
        buffer.append(value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendDetail(StringBuffer buffer, String fieldName, long[] array) {
        buffer.append(this.arrayStart);
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buffer.append(this.arraySeparator);
            }
            appendDetail(buffer, fieldName, array[i]);
        }
        buffer.append(this.arrayEnd);
    }

    protected void appendDetail(StringBuffer buffer, String fieldName, Map<?, ?> map) {
        buffer.append(map);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendDetail(StringBuffer buffer, String fieldName, Object value) {
        buffer.append(value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendDetail(StringBuffer buffer, String fieldName, Object[] array) {
        buffer.append(this.arrayStart);
        for (int i = 0; i < array.length; i++) {
            appendDetail(buffer, fieldName, i, array[i]);
        }
        buffer.append(this.arrayEnd);
    }

    protected void appendDetail(StringBuffer buffer, String fieldName, short value) {
        buffer.append((int) value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendDetail(StringBuffer buffer, String fieldName, short[] array) {
        buffer.append(this.arrayStart);
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buffer.append(this.arraySeparator);
            }
            appendDetail(buffer, fieldName, array[i]);
        }
        buffer.append(this.arrayEnd);
    }

    public void appendEnd(StringBuffer buffer, Object object) {
        if (!this.fieldSeparatorAtEnd) {
            removeLastFieldSeparator(buffer);
        }
        appendContentEnd(buffer);
        unregister(object);
    }

    protected void appendFieldEnd(StringBuffer buffer, String fieldName) {
        appendFieldSeparator(buffer);
    }

    protected void appendFieldSeparator(StringBuffer buffer) {
        buffer.append(this.fieldSeparator);
    }

    protected void appendFieldStart(StringBuffer buffer, String fieldName) {
        if (this.useFieldNames && fieldName != null) {
            buffer.append(fieldName);
            buffer.append(this.fieldNameValueSeparator);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendIdentityHashCode(StringBuffer buffer, Object object) {
        if (isUseIdentityHashCode() && object != null) {
            register(object);
            buffer.append('@');
            buffer.append(ObjectUtils.identityHashCodeHex(object));
        }
    }

    protected void appendInternal(StringBuffer buffer, String fieldName, Object value, boolean detail) {
        if (isRegistered(value) && !(value instanceof Number) && !(value instanceof Boolean) && !(value instanceof Character)) {
            appendCyclicObject(buffer, fieldName, value);
            return;
        }
        register(value);
        try {
            if (value instanceof Collection) {
                if (detail) {
                    appendDetail(buffer, fieldName, (Collection<?>) value);
                } else {
                    appendSummarySize(buffer, fieldName, ((Collection) value).size());
                }
            } else if (value instanceof Map) {
                if (detail) {
                    appendDetail(buffer, fieldName, (Map<?, ?>) value);
                } else {
                    appendSummarySize(buffer, fieldName, ((Map) value).size());
                }
            } else if (value instanceof long[]) {
                if (detail) {
                    appendDetail(buffer, fieldName, (long[]) value);
                } else {
                    appendSummary(buffer, fieldName, (long[]) value);
                }
            } else if (value instanceof int[]) {
                if (detail) {
                    appendDetail(buffer, fieldName, (int[]) value);
                } else {
                    appendSummary(buffer, fieldName, (int[]) value);
                }
            } else if (value instanceof short[]) {
                if (detail) {
                    appendDetail(buffer, fieldName, (short[]) value);
                } else {
                    appendSummary(buffer, fieldName, (short[]) value);
                }
            } else if (value instanceof byte[]) {
                if (detail) {
                    appendDetail(buffer, fieldName, (byte[]) value);
                } else {
                    appendSummary(buffer, fieldName, (byte[]) value);
                }
            } else if (value instanceof char[]) {
                if (detail) {
                    appendDetail(buffer, fieldName, (char[]) value);
                } else {
                    appendSummary(buffer, fieldName, (char[]) value);
                }
            } else if (value instanceof double[]) {
                if (detail) {
                    appendDetail(buffer, fieldName, (double[]) value);
                } else {
                    appendSummary(buffer, fieldName, (double[]) value);
                }
            } else if (value instanceof float[]) {
                if (detail) {
                    appendDetail(buffer, fieldName, (float[]) value);
                } else {
                    appendSummary(buffer, fieldName, (float[]) value);
                }
            } else if (value instanceof boolean[]) {
                if (detail) {
                    appendDetail(buffer, fieldName, (boolean[]) value);
                } else {
                    appendSummary(buffer, fieldName, (boolean[]) value);
                }
            } else if (ObjectUtils.isArray(value)) {
                if (detail) {
                    appendDetail(buffer, fieldName, (Object[]) value);
                } else {
                    appendSummary(buffer, fieldName, (Object[]) value);
                }
            } else if (detail) {
                appendDetail(buffer, fieldName, value);
            } else {
                appendSummary(buffer, fieldName, value);
            }
        } finally {
            unregister(value);
        }
    }

    protected void appendNullText(StringBuffer buffer, String fieldName) {
        buffer.append(this.nullText);
    }

    public void appendStart(StringBuffer buffer, Object object) {
        if (object != null) {
            appendClassName(buffer, object);
            appendIdentityHashCode(buffer, object);
            appendContentStart(buffer);
            if (this.fieldSeparatorAtStart) {
                appendFieldSeparator(buffer);
            }
        }
    }

    protected void appendSummary(StringBuffer buffer, String fieldName, boolean[] array) {
        appendSummarySize(buffer, fieldName, array.length);
    }

    protected void appendSummary(StringBuffer buffer, String fieldName, byte[] array) {
        appendSummarySize(buffer, fieldName, array.length);
    }

    protected void appendSummary(StringBuffer buffer, String fieldName, char[] array) {
        appendSummarySize(buffer, fieldName, array.length);
    }

    protected void appendSummary(StringBuffer buffer, String fieldName, double[] array) {
        appendSummarySize(buffer, fieldName, array.length);
    }

    protected void appendSummary(StringBuffer buffer, String fieldName, float[] array) {
        appendSummarySize(buffer, fieldName, array.length);
    }

    protected void appendSummary(StringBuffer buffer, String fieldName, int[] array) {
        appendSummarySize(buffer, fieldName, array.length);
    }

    protected void appendSummary(StringBuffer buffer, String fieldName, long[] array) {
        appendSummarySize(buffer, fieldName, array.length);
    }

    protected void appendSummary(StringBuffer buffer, String fieldName, Object value) {
        buffer.append(this.summaryObjectStartText);
        buffer.append(getShortClassName(value.getClass()));
        buffer.append(this.summaryObjectEndText);
    }

    protected void appendSummary(StringBuffer buffer, String fieldName, Object[] array) {
        appendSummarySize(buffer, fieldName, array.length);
    }

    protected void appendSummary(StringBuffer buffer, String fieldName, short[] array) {
        appendSummarySize(buffer, fieldName, array.length);
    }

    protected void appendSummarySize(StringBuffer buffer, String fieldName, int size) {
        buffer.append(this.sizeStartText);
        buffer.append(size);
        buffer.append(this.sizeEndText);
    }

    public void appendSuper(StringBuffer buffer, String superToString) {
        appendToString(buffer, superToString);
    }

    public void appendToString(StringBuffer buffer, String toString) {
        int pos1;
        int pos2;
        if (toString != null && (pos1 = toString.indexOf(this.contentStart) + this.contentStart.length()) != (pos2 = toString.lastIndexOf(this.contentEnd)) && pos1 >= 0 && pos2 >= 0) {
            if (this.fieldSeparatorAtStart) {
                removeLastFieldSeparator(buffer);
            }
            buffer.append((CharSequence) toString, pos1, pos2);
            appendFieldSeparator(buffer);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getArrayEnd() {
        return this.arrayEnd;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getArraySeparator() {
        return this.arraySeparator;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getArrayStart() {
        return this.arrayStart;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getContentEnd() {
        return this.contentEnd;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getContentStart() {
        return this.contentStart;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getFieldNameValueSeparator() {
        return this.fieldNameValueSeparator;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getFieldSeparator() {
        return this.fieldSeparator;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getNullText() {
        return this.nullText;
    }

    protected String getShortClassName(Class<?> cls) {
        return ClassUtils.getShortClassName(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getSizeEndText() {
        return this.sizeEndText;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getSizeStartText() {
        return this.sizeStartText;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getSummaryObjectEndText() {
        return this.summaryObjectEndText;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getSummaryObjectStartText() {
        return this.summaryObjectStartText;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isArrayContentDetail() {
        return this.arrayContentDetail;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDefaultFullDetail() {
        return this.defaultFullDetail;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isFieldSeparatorAtEnd() {
        return this.fieldSeparatorAtEnd;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isFieldSeparatorAtStart() {
        return this.fieldSeparatorAtStart;
    }

    protected boolean isFullDetail(Boolean fullDetailRequest) {
        if (fullDetailRequest == null) {
            return this.defaultFullDetail;
        }
        return fullDetailRequest.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isUseClassName() {
        return this.useClassName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isUseFieldNames() {
        return this.useFieldNames;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isUseIdentityHashCode() {
        return this.useIdentityHashCode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isUseShortClassName() {
        return this.useShortClassName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reflectionAppendArrayDetail(StringBuffer buffer, String fieldName, Object array) {
        buffer.append(this.arrayStart);
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            appendDetail(buffer, fieldName, i, Array.get(array, i));
        }
        buffer.append(this.arrayEnd);
    }

    protected void removeLastFieldSeparator(StringBuffer buffer) {
        if (Strings.CS.endsWith(buffer, this.fieldSeparator)) {
            buffer.setLength(buffer.length() - this.fieldSeparator.length());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setArrayContentDetail(boolean arrayContentDetail) {
        this.arrayContentDetail = arrayContentDetail;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setArrayEnd(String arrayEnd) {
        if (arrayEnd == null) {
            arrayEnd = "";
        }
        this.arrayEnd = arrayEnd;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setArraySeparator(String arraySeparator) {
        if (arraySeparator == null) {
            arraySeparator = "";
        }
        this.arraySeparator = arraySeparator;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setArrayStart(String arrayStart) {
        if (arrayStart == null) {
            arrayStart = "";
        }
        this.arrayStart = arrayStart;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setContentEnd(String contentEnd) {
        if (contentEnd == null) {
            contentEnd = "";
        }
        this.contentEnd = contentEnd;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setContentStart(String contentStart) {
        if (contentStart == null) {
            contentStart = "";
        }
        this.contentStart = contentStart;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setDefaultFullDetail(boolean defaultFullDetail) {
        this.defaultFullDetail = defaultFullDetail;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setFieldNameValueSeparator(String fieldNameValueSeparator) {
        if (fieldNameValueSeparator == null) {
            fieldNameValueSeparator = "";
        }
        this.fieldNameValueSeparator = fieldNameValueSeparator;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setFieldSeparator(String fieldSeparator) {
        if (fieldSeparator == null) {
            fieldSeparator = "";
        }
        this.fieldSeparator = fieldSeparator;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setFieldSeparatorAtEnd(boolean fieldSeparatorAtEnd) {
        this.fieldSeparatorAtEnd = fieldSeparatorAtEnd;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setFieldSeparatorAtStart(boolean fieldSeparatorAtStart) {
        this.fieldSeparatorAtStart = fieldSeparatorAtStart;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setNullText(String nullText) {
        if (nullText == null) {
            nullText = "";
        }
        this.nullText = nullText;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSizeEndText(String sizeEndText) {
        if (sizeEndText == null) {
            sizeEndText = "";
        }
        this.sizeEndText = sizeEndText;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSizeStartText(String sizeStartText) {
        if (sizeStartText == null) {
            sizeStartText = "";
        }
        this.sizeStartText = sizeStartText;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSummaryObjectEndText(String summaryObjectEndText) {
        if (summaryObjectEndText == null) {
            summaryObjectEndText = "";
        }
        this.summaryObjectEndText = summaryObjectEndText;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSummaryObjectStartText(String summaryObjectStartText) {
        if (summaryObjectStartText == null) {
            summaryObjectStartText = "";
        }
        this.summaryObjectStartText = summaryObjectStartText;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setUseClassName(boolean useClassName) {
        this.useClassName = useClassName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setUseFieldNames(boolean useFieldNames) {
        this.useFieldNames = useFieldNames;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setUseIdentityHashCode(boolean useIdentityHashCode) {
        this.useIdentityHashCode = useIdentityHashCode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setUseShortClassName(boolean useShortClassName) {
        this.useShortClassName = useShortClassName;
    }
}
