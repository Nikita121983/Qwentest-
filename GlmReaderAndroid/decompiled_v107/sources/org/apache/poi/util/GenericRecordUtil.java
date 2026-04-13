package org.apache.poi.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda4;
import org.apache.poi.util.GenericRecordUtil;

@Internal
/* loaded from: classes10.dex */
public final class GenericRecordUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    private GenericRecordUtil() {
    }

    public static Map<String, Supplier<?>> getGenericProperties(String val1, Supplier<?> sup1) {
        return Collections.singletonMap(val1, sup1);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String val1, Supplier<?> sup1, String val2, Supplier<?> sup2) {
        return getGenericProperties(val1, sup1, val2, sup2, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String val1, Supplier<?> sup1, String val2, Supplier<?> sup2, String val3, Supplier<?> sup3) {
        return getGenericProperties(val1, sup1, val2, sup2, val3, sup3, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String val1, Supplier<?> sup1, String val2, Supplier<?> sup2, String val3, Supplier<?> sup3, String val4, Supplier<?> sup4) {
        return getGenericProperties(val1, sup1, val2, sup2, val3, sup3, val4, sup4, null, null, null, null, null, null, null, null, null, null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String val1, Supplier<?> sup1, String val2, Supplier<?> sup2, String val3, Supplier<?> sup3, String val4, Supplier<?> sup4, String val5, Supplier<?> sup5) {
        return getGenericProperties(val1, sup1, val2, sup2, val3, sup3, val4, sup4, val5, sup5, null, null, null, null, null, null, null, null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String val1, Supplier<?> sup1, String val2, Supplier<?> sup2, String val3, Supplier<?> sup3, String val4, Supplier<?> sup4, String val5, Supplier<?> sup5, String val6, Supplier<?> sup6) {
        return getGenericProperties(val1, sup1, val2, sup2, val3, sup3, val4, sup4, val5, sup5, val6, sup6, null, null, null, null, null, null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String val1, Supplier<?> sup1, String val2, Supplier<?> sup2, String val3, Supplier<?> sup3, String val4, Supplier<?> sup4, String val5, Supplier<?> sup5, String val6, Supplier<?> sup6, String val7, Supplier<?> sup7) {
        return getGenericProperties(val1, sup1, val2, sup2, val3, sup3, val4, sup4, val5, sup5, val6, sup6, val7, sup7, null, null, null, null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String val1, Supplier<?> sup1, String val2, Supplier<?> sup2, String val3, Supplier<?> sup3, String val4, Supplier<?> sup4, String val5, Supplier<?> sup5, String val6, Supplier<?> sup6, String val7, Supplier<?> sup7, String val8, Supplier<?> sup8) {
        return getGenericProperties(val1, sup1, val2, sup2, val3, sup3, val4, sup4, val5, sup5, val6, sup6, val7, sup7, val8, sup8, null, null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String val1, Supplier<?> sup1, String val2, Supplier<?> sup2, String val3, Supplier<?> sup3, String val4, Supplier<?> sup4, String val5, Supplier<?> sup5, String val6, Supplier<?> sup6, String val7, Supplier<?> sup7, String val8, Supplier<?> sup8, String val9, Supplier<?> sup9) {
        Map<String, Supplier<?>> m = new LinkedHashMap<>();
        String[] vals = {val1, val2, val3, val4, val5, val6, val7, val8, val9};
        Supplier<?>[] sups = {sup1, sup2, sup3, sup4, sup5, sup6, sup7, sup8, sup9};
        for (int i = 0; i < vals.length && vals[i] != null; i++) {
            if (sups[i] == null) {
                throw new AssertionError();
            }
            if ("base".equals(vals[i])) {
                Object baseMap = sups[i].get();
                if (!(baseMap instanceof Map)) {
                    throw new AssertionError();
                }
                m.putAll((Map) baseMap);
            } else {
                m.put(vals[i], sups[i]);
            }
        }
        return Collections.unmodifiableMap(m);
    }

    public static <T extends Enum<?>> Supplier<T> safeEnum(T[] values, Supplier<Number> ordinal) {
        return safeEnum(values, ordinal, null);
    }

    public static <T extends Enum<?>> Supplier<T> safeEnum(final T[] values, Supplier<Number> ordinal, final T defaultVal) {
        final int ord = ordinal.get().intValue();
        return new Supplier() { // from class: org.apache.poi.util.GenericRecordUtil$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return GenericRecordUtil.lambda$safeEnum$0(ord, values, defaultVal);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Enum lambda$safeEnum$0(int ord, Enum[] values, Enum defaultVal) {
        return (ord < 0 || ord >= values.length) ? defaultVal : values[ord];
    }

    public static Supplier<AnnotatedFlag> getBitsAsString(final Supplier<Number> flags, BitField[] masks, final String[] names) {
        final int[] iMasks = Arrays.stream(masks).mapToInt(new ToIntFunction() { // from class: org.apache.poi.util.GenericRecordUtil$$ExternalSyntheticLambda1
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((BitField) obj).getMask();
            }
        }).toArray();
        return new Supplier() { // from class: org.apache.poi.util.GenericRecordUtil$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return GenericRecordUtil.lambda$getBitsAsString$1(flags, iMasks, names);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ AnnotatedFlag lambda$getBitsAsString$1(Supplier flags, int[] iMasks, String[] names) {
        return new AnnotatedFlag(flags, iMasks, names, false);
    }

    public static Supplier<AnnotatedFlag> getBitsAsString(final Supplier<Number> flags, final int[] masks, final String[] names) {
        return new Supplier() { // from class: org.apache.poi.util.GenericRecordUtil$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return GenericRecordUtil.lambda$getBitsAsString$2(flags, masks, names);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ AnnotatedFlag lambda$getBitsAsString$2(Supplier flags, int[] masks, String[] names) {
        return new AnnotatedFlag(flags, masks, names, false);
    }

    public static Supplier<AnnotatedFlag> getEnumBitsAsString(final Supplier<Number> flags, final int[] masks, final String[] names) {
        return new Supplier() { // from class: org.apache.poi.util.GenericRecordUtil$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return GenericRecordUtil.lambda$getEnumBitsAsString$3(flags, masks, names);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ AnnotatedFlag lambda$getEnumBitsAsString$3(Supplier flags, int[] masks, String[] names) {
        return new AnnotatedFlag(flags, masks, names, true);
    }

    /* loaded from: classes10.dex */
    public static class AnnotatedFlag {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final boolean exactMatch;
        private final Map<Integer, String> masks = new LinkedHashMap();
        private final Supplier<Number> value;

        AnnotatedFlag(Supplier<Number> value, int[] masks, String[] names, boolean exactMatch) {
            if (masks.length != names.length) {
                throw new AssertionError();
            }
            this.value = value;
            this.exactMatch = exactMatch;
            for (int i = 0; i < masks.length; i++) {
                this.masks.put(Integer.valueOf(masks[i]), names[i]);
            }
        }

        public Supplier<Number> getValue() {
            return this.value;
        }

        public String getDescription() {
            final int val = this.value.get().intValue();
            return (String) this.masks.entrySet().stream().filter(new Predicate() { // from class: org.apache.poi.util.GenericRecordUtil$AnnotatedFlag$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return GenericRecordUtil.AnnotatedFlag.this.m2560x8f920b63(val, (Map.Entry) obj);
                }
            }).map(new ClassUtils$$ExternalSyntheticLambda4()).collect(Collectors.joining(" | "));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getDescription$0$org-apache-poi-util-GenericRecordUtil$AnnotatedFlag, reason: not valid java name */
        public /* synthetic */ boolean m2560x8f920b63(int val, Map.Entry e) {
            return match(val, ((Integer) e.getKey()).intValue());
        }

        private boolean match(int val, int mask) {
            if (this.exactMatch) {
                if (val == mask) {
                    return true;
                }
            } else if ((val & mask) == mask) {
                return true;
            }
            return false;
        }
    }
}
