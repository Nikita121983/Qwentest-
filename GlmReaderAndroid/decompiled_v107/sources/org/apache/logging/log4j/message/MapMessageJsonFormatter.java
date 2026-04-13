package org.apache.logging.log4j.message;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.apache.logging.log4j.util.IndexedStringMap;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.apache.logging.log4j.util.StringBuilders;

/* loaded from: classes10.dex */
enum MapMessageJsonFormatter {
    ;

    private static final char COLON = ':';
    private static final char COMMA = ',';
    private static final char DQUOTE = '\"';
    private static final char LBRACE = '[';
    private static final char LCURLY = '{';
    public static final int MAX_DEPTH = readMaxDepth();
    private static final char RBRACE = ']';
    private static final char RCURLY = '}';

    private static int readMaxDepth() {
        int maxDepth = PropertiesUtil.getProperties().getIntegerProperty("log4j2.mapMessage.jsonFormatter.maxDepth", 8);
        if (maxDepth < 0) {
            throw new IllegalArgumentException("was expecting a positive maxDepth, found: " + maxDepth);
        }
        return maxDepth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void format(final StringBuilder sb, final Object object) {
        format(sb, object, 0);
    }

    private static void format(final StringBuilder sb, final Object object, final int depth) {
        if (depth >= MAX_DEPTH) {
            throw new IllegalArgumentException("maxDepth has been exceeded");
        }
        if (object == null) {
            sb.append("null");
            return;
        }
        if (object instanceof IndexedStringMap) {
            IndexedStringMap map = (IndexedStringMap) object;
            formatIndexedStringMap(sb, map, depth);
            return;
        }
        if (object instanceof Map) {
            Map<Object, Object> map2 = (Map) object;
            formatMap(sb, map2, depth);
            return;
        }
        if (object instanceof List) {
            List<Object> list = (List) object;
            formatList(sb, list, depth);
            return;
        }
        if (object instanceof Collection) {
            Collection<Object> collection = (Collection) object;
            formatCollection(sb, collection, depth);
            return;
        }
        if (object instanceof Number) {
            Number number = (Number) object;
            formatNumber(sb, number);
            return;
        }
        if (object instanceof Boolean) {
            boolean booleanValue = ((Boolean) object).booleanValue();
            formatBoolean(sb, booleanValue);
            return;
        }
        if (object instanceof StringBuilderFormattable) {
            StringBuilderFormattable formattable = (StringBuilderFormattable) object;
            formatFormattable(sb, formattable);
            return;
        }
        if (object instanceof char[]) {
            char[] charValues = (char[]) object;
            formatCharArray(sb, charValues);
            return;
        }
        if (object instanceof boolean[]) {
            boolean[] booleanValues = (boolean[]) object;
            formatBooleanArray(sb, booleanValues);
            return;
        }
        if (object instanceof byte[]) {
            byte[] byteValues = (byte[]) object;
            formatByteArray(sb, byteValues);
            return;
        }
        if (object instanceof short[]) {
            short[] shortValues = (short[]) object;
            formatShortArray(sb, shortValues);
            return;
        }
        if (object instanceof int[]) {
            int[] intValues = (int[]) object;
            formatIntArray(sb, intValues);
            return;
        }
        if (object instanceof long[]) {
            long[] longValues = (long[]) object;
            formatLongArray(sb, longValues);
            return;
        }
        if (object instanceof float[]) {
            float[] floatValues = (float[]) object;
            formatFloatArray(sb, floatValues);
        } else if (object instanceof double[]) {
            double[] doubleValues = (double[]) object;
            formatDoubleArray(sb, doubleValues);
        } else if (object instanceof Object[]) {
            Object[] objectValues = (Object[]) object;
            formatObjectArray(sb, objectValues, depth);
        } else {
            formatString(sb, object);
        }
    }

    private static void formatIndexedStringMap(final StringBuilder sb, final IndexedStringMap map, final int depth) {
        sb.append(LCURLY);
        int nextDepth = depth + 1;
        for (int entryIndex = 0; entryIndex < map.size(); entryIndex++) {
            String key = map.getKeyAt(entryIndex);
            Object value = map.getValueAt(entryIndex);
            if (entryIndex > 0) {
                sb.append(COMMA);
            }
            sb.append('\"');
            int keyStartIndex = sb.length();
            sb.append(key);
            StringBuilders.escapeJson(sb, keyStartIndex);
            sb.append('\"').append(':');
            format(sb, value, nextDepth);
        }
        sb.append(RCURLY);
    }

    private static void formatMap(final StringBuilder sb, final Map<Object, Object> map, final int depth) {
        sb.append(LCURLY);
        final int nextDepth = depth + 1;
        final boolean[] firstEntry = {true};
        map.forEach(new BiConsumer() { // from class: org.apache.logging.log4j.message.MapMessageJsonFormatter$$ExternalSyntheticLambda1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                MapMessageJsonFormatter.lambda$formatMap$0(firstEntry, sb, nextDepth, obj, obj2);
            }
        });
        sb.append(RCURLY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$formatMap$0(boolean[] firstEntry, StringBuilder sb, int nextDepth, Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("null keys are not allowed");
        }
        if (firstEntry[0]) {
            firstEntry[0] = false;
        } else {
            sb.append(COMMA);
        }
        sb.append('\"');
        String keyString = String.valueOf(key);
        int keyStartIndex = sb.length();
        sb.append(keyString);
        StringBuilders.escapeJson(sb, keyStartIndex);
        sb.append('\"').append(':');
        format(sb, value, nextDepth);
    }

    private static void formatList(final StringBuilder sb, final List<Object> items, final int depth) {
        sb.append(LBRACE);
        int nextDepth = depth + 1;
        for (int itemIndex = 0; itemIndex < items.size(); itemIndex++) {
            if (itemIndex > 0) {
                sb.append(COMMA);
            }
            Object item = items.get(itemIndex);
            format(sb, item, nextDepth);
        }
        sb.append(RBRACE);
    }

    private static void formatCollection(final StringBuilder sb, final Collection<Object> items, final int depth) {
        sb.append(LBRACE);
        final int nextDepth = depth + 1;
        final boolean[] firstItem = {true};
        items.forEach(new Consumer() { // from class: org.apache.logging.log4j.message.MapMessageJsonFormatter$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MapMessageJsonFormatter.lambda$formatCollection$1(firstItem, sb, nextDepth, obj);
            }
        });
        sb.append(RBRACE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$formatCollection$1(boolean[] firstItem, StringBuilder sb, int nextDepth, Object item) {
        if (firstItem[0]) {
            firstItem[0] = false;
        } else {
            sb.append(COMMA);
        }
        format(sb, item, nextDepth);
    }

    private static void formatNumber(final StringBuilder sb, final Number number) {
        if (number instanceof BigDecimal) {
            BigDecimal decimalNumber = (BigDecimal) number;
            sb.append(decimalNumber.toString());
            return;
        }
        if (number instanceof Double) {
            double doubleNumber = ((Double) number).doubleValue();
            sb.append(doubleNumber);
            return;
        }
        if (number instanceof Float) {
            float floatNumber = ((Float) number).floatValue();
            sb.append(floatNumber);
            return;
        }
        if ((number instanceof Byte) || (number instanceof Short) || (number instanceof Integer) || (number instanceof Long)) {
            sb.append(number.longValue());
            return;
        }
        long longNumber = number.longValue();
        double doubleValue = number.doubleValue();
        if (Double.compare(longNumber, doubleValue) == 0) {
            sb.append(longNumber);
        } else {
            sb.append(doubleValue);
        }
    }

    private static void formatBoolean(final StringBuilder sb, final boolean booleanValue) {
        sb.append(booleanValue);
    }

    private static void formatFormattable(final StringBuilder sb, final StringBuilderFormattable formattable) {
        sb.append('\"');
        int startIndex = sb.length();
        formattable.formatTo(sb);
        StringBuilders.escapeJson(sb, startIndex);
        sb.append('\"');
    }

    private static void formatCharArray(final StringBuilder sb, final char[] items) {
        sb.append(LBRACE);
        for (int itemIndex = 0; itemIndex < items.length; itemIndex++) {
            if (itemIndex > 0) {
                sb.append(COMMA);
            }
            char item = items[itemIndex];
            sb.append('\"');
            int startIndex = sb.length();
            sb.append(item);
            StringBuilders.escapeJson(sb, startIndex);
            sb.append('\"');
        }
        sb.append(RBRACE);
    }

    private static void formatBooleanArray(final StringBuilder sb, final boolean[] items) {
        sb.append(LBRACE);
        for (int itemIndex = 0; itemIndex < items.length; itemIndex++) {
            if (itemIndex > 0) {
                sb.append(COMMA);
            }
            boolean item = items[itemIndex];
            sb.append(item);
        }
        sb.append(RBRACE);
    }

    private static void formatByteArray(final StringBuilder sb, final byte[] items) {
        sb.append(LBRACE);
        for (int itemIndex = 0; itemIndex < items.length; itemIndex++) {
            if (itemIndex > 0) {
                sb.append(COMMA);
            }
            byte item = items[itemIndex];
            sb.append((int) item);
        }
        sb.append(RBRACE);
    }

    private static void formatShortArray(final StringBuilder sb, final short[] items) {
        sb.append(LBRACE);
        for (int itemIndex = 0; itemIndex < items.length; itemIndex++) {
            if (itemIndex > 0) {
                sb.append(COMMA);
            }
            short item = items[itemIndex];
            sb.append((int) item);
        }
        sb.append(RBRACE);
    }

    private static void formatIntArray(final StringBuilder sb, final int[] items) {
        sb.append(LBRACE);
        for (int itemIndex = 0; itemIndex < items.length; itemIndex++) {
            if (itemIndex > 0) {
                sb.append(COMMA);
            }
            int item = items[itemIndex];
            sb.append(item);
        }
        sb.append(RBRACE);
    }

    private static void formatLongArray(final StringBuilder sb, final long[] items) {
        sb.append(LBRACE);
        for (int itemIndex = 0; itemIndex < items.length; itemIndex++) {
            if (itemIndex > 0) {
                sb.append(COMMA);
            }
            long item = items[itemIndex];
            sb.append(item);
        }
        sb.append(RBRACE);
    }

    private static void formatFloatArray(final StringBuilder sb, final float[] items) {
        sb.append(LBRACE);
        for (int itemIndex = 0; itemIndex < items.length; itemIndex++) {
            if (itemIndex > 0) {
                sb.append(COMMA);
            }
            float item = items[itemIndex];
            sb.append(item);
        }
        sb.append(RBRACE);
    }

    private static void formatDoubleArray(final StringBuilder sb, final double[] items) {
        sb.append(LBRACE);
        for (int itemIndex = 0; itemIndex < items.length; itemIndex++) {
            if (itemIndex > 0) {
                sb.append(COMMA);
            }
            double item = items[itemIndex];
            sb.append(item);
        }
        sb.append(RBRACE);
    }

    private static void formatObjectArray(final StringBuilder sb, final Object[] items, final int depth) {
        sb.append(LBRACE);
        int nextDepth = depth + 1;
        for (int itemIndex = 0; itemIndex < items.length; itemIndex++) {
            if (itemIndex > 0) {
                sb.append(COMMA);
            }
            Object item = items[itemIndex];
            format(sb, item, nextDepth);
        }
        sb.append(RBRACE);
    }

    private static void formatString(final StringBuilder sb, final Object value) {
        sb.append('\"');
        int startIndex = sb.length();
        String valueString = String.valueOf(value);
        sb.append(valueString);
        StringBuilders.escapeJson(sb, startIndex);
        sb.append('\"');
    }
}
