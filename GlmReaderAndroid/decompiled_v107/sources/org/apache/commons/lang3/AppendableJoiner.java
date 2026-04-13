package org.apache.commons.lang3;

import java.io.IOException;
import java.util.Iterator;
import java.util.function.Supplier;
import org.apache.commons.lang3.exception.UncheckedException;
import org.apache.commons.lang3.function.FailableBiConsumer;

/* loaded from: classes9.dex */
public final class AppendableJoiner<T> {
    private final FailableBiConsumer<Appendable, T, IOException> appender;
    private final CharSequence delimiter;
    private final CharSequence prefix;
    private final CharSequence suffix;

    /* loaded from: classes9.dex */
    public static final class Builder<T> implements Supplier<AppendableJoiner<T>> {
        private FailableBiConsumer<Appendable, T, IOException> appender;
        private CharSequence delimiter;
        private CharSequence prefix;
        private CharSequence suffix;

        Builder() {
        }

        @Override // java.util.function.Supplier
        public AppendableJoiner<T> get() {
            return new AppendableJoiner<>(this.prefix, this.suffix, this.delimiter, this.appender);
        }

        public Builder<T> setDelimiter(CharSequence delimiter) {
            this.delimiter = delimiter;
            return this;
        }

        public Builder<T> setElementAppender(FailableBiConsumer<Appendable, T, IOException> appender) {
            this.appender = appender;
            return this;
        }

        public Builder<T> setPrefix(CharSequence prefix) {
            this.prefix = prefix;
            return this;
        }

        public Builder<T> setSuffix(CharSequence suffix) {
            this.suffix = suffix;
            return this;
        }
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    @SafeVarargs
    static <A extends Appendable, T> A joinA(A a, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, FailableBiConsumer<Appendable, T, IOException> failableBiConsumer, T... tArr) throws IOException {
        return (A) joinArray(a, charSequence, charSequence2, charSequence3, failableBiConsumer, tArr);
    }

    private static <A extends Appendable, T> A joinArray(A appendable, CharSequence prefix, CharSequence suffix, CharSequence delimiter, FailableBiConsumer<Appendable, T, IOException> appender, T[] elements) throws IOException {
        appendable.append(prefix);
        if (elements != null) {
            if (elements.length > 0) {
                appender.accept(appendable, elements[0]);
            }
            for (int i = 1; i < elements.length; i++) {
                appendable.append(delimiter);
                appender.accept(appendable, elements[i]);
            }
        }
        appendable.append(suffix);
        return appendable;
    }

    static <T> StringBuilder joinI(StringBuilder stringBuilder, CharSequence prefix, CharSequence suffix, CharSequence delimiter, FailableBiConsumer<Appendable, T, IOException> appender, Iterable<T> elements) {
        try {
            return (StringBuilder) joinIterable(stringBuilder, prefix, suffix, delimiter, appender, elements);
        } catch (IOException e) {
            throw new UncheckedException(e);
        }
    }

    private static <A extends Appendable, T> A joinIterable(A appendable, CharSequence prefix, CharSequence suffix, CharSequence delimiter, FailableBiConsumer<Appendable, T, IOException> appender, Iterable<T> elements) throws IOException {
        appendable.append(prefix);
        if (elements != null) {
            Iterator<T> iterator = elements.iterator();
            if (iterator.hasNext()) {
                appender.accept(appendable, iterator.next());
            }
            while (iterator.hasNext()) {
                appendable.append(delimiter);
                appender.accept(appendable, iterator.next());
            }
        }
        appendable.append(suffix);
        return appendable;
    }

    @SafeVarargs
    static <T> StringBuilder joinSB(StringBuilder stringBuilder, CharSequence prefix, CharSequence suffix, CharSequence delimiter, FailableBiConsumer<Appendable, T, IOException> appender, T... elements) {
        try {
            return (StringBuilder) joinArray(stringBuilder, prefix, suffix, delimiter, appender, elements);
        } catch (IOException e) {
            throw new UncheckedException(e);
        }
    }

    private static CharSequence nonNull(CharSequence value) {
        return value != null ? value : "";
    }

    private AppendableJoiner(CharSequence prefix, CharSequence suffix, CharSequence delimiter, FailableBiConsumer<Appendable, T, IOException> appender) {
        this.prefix = nonNull(prefix);
        this.suffix = nonNull(suffix);
        this.delimiter = nonNull(delimiter);
        this.appender = appender != null ? appender : new FailableBiConsumer() { // from class: org.apache.commons.lang3.AppendableJoiner$$ExternalSyntheticLambda0
            @Override // org.apache.commons.lang3.function.FailableBiConsumer
            public final void accept(Object obj, Object obj2) {
                ((Appendable) obj).append(String.valueOf(obj2));
            }
        };
    }

    public StringBuilder join(StringBuilder stringBuilder, Iterable<T> elements) {
        return joinI(stringBuilder, this.prefix, this.suffix, this.delimiter, this.appender, elements);
    }

    public StringBuilder join(StringBuilder stringBuilder, T... elements) {
        return joinSB(stringBuilder, this.prefix, this.suffix, this.delimiter, this.appender, elements);
    }

    public <A extends Appendable> A joinA(A a, Iterable<T> iterable) throws IOException {
        return (A) joinIterable(a, this.prefix, this.suffix, this.delimiter, this.appender, iterable);
    }

    public <A extends Appendable> A joinA(A a, T... tArr) throws IOException {
        return (A) joinA(a, this.prefix, this.suffix, this.delimiter, this.appender, tArr);
    }
}
