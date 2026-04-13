package org.apache.commons.io.output;

import java.util.Objects;
import org.apache.commons.io.function.IOFunction;
import org.apache.commons.io.function.IOTriFunction;
import org.apache.commons.io.function.Uncheck;

/* loaded from: classes9.dex */
final class UncheckedAppendableImpl implements UncheckedAppendable {
    private final Appendable appendable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UncheckedAppendableImpl(Appendable appendable) {
        this.appendable = (Appendable) Objects.requireNonNull(appendable, "appendable");
    }

    @Override // org.apache.commons.io.output.UncheckedAppendable, java.lang.Appendable
    public UncheckedAppendable append(char c) {
        final Appendable appendable = this.appendable;
        Objects.requireNonNull(appendable);
        Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.output.UncheckedAppendableImpl$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return appendable.append(((Character) obj).charValue());
            }
        }, Character.valueOf(c));
        return this;
    }

    @Override // org.apache.commons.io.output.UncheckedAppendable, java.lang.Appendable
    public UncheckedAppendable append(CharSequence csq) {
        final Appendable appendable = this.appendable;
        Objects.requireNonNull(appendable);
        Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.output.UncheckedAppendableImpl$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return appendable.append((CharSequence) obj);
            }
        }, csq);
        return this;
    }

    @Override // org.apache.commons.io.output.UncheckedAppendable, java.lang.Appendable
    public UncheckedAppendable append(CharSequence csq, int start, int end) {
        final Appendable appendable = this.appendable;
        Objects.requireNonNull(appendable);
        Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.output.UncheckedAppendableImpl$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                return appendable.append((CharSequence) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        }, csq, Integer.valueOf(start), Integer.valueOf(end));
        return this;
    }

    public String toString() {
        return this.appendable.toString();
    }
}
