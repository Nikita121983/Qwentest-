package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface BooleanConsumer {
    public static final BooleanConsumer NOP = new BooleanConsumer() { // from class: org.apache.commons.lang3.function.BooleanConsumer$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.BooleanConsumer
        public final void accept(boolean z) {
            BooleanConsumer.lambda$static$0(z);
        }
    };

    void accept(boolean z);

    static /* synthetic */ void lambda$static$0(boolean t) {
    }

    static BooleanConsumer nop() {
        return NOP;
    }

    default BooleanConsumer andThen(final BooleanConsumer after) {
        Objects.requireNonNull(after);
        return new BooleanConsumer() { // from class: org.apache.commons.lang3.function.BooleanConsumer$$ExternalSyntheticLambda1
            @Override // org.apache.commons.lang3.function.BooleanConsumer
            public final void accept(boolean z) {
                BooleanConsumer.lambda$andThen$1(BooleanConsumer.this, after, z);
            }
        };
    }

    static /* synthetic */ void lambda$andThen$1(BooleanConsumer _this, BooleanConsumer after, boolean t) {
        _this.accept(t);
        after.accept(t);
    }
}
