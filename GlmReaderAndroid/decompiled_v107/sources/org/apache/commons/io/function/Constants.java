package org.apache.commons.io.function;

import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class Constants {
    static final IOBiConsumer IO_BI_CONSUMER = new IOBiConsumer() { // from class: org.apache.commons.io.function.Constants$$ExternalSyntheticLambda0
        @Override // org.apache.commons.io.function.IOBiConsumer
        public final void accept(Object obj, Object obj2) {
            Constants.lambda$static$0(obj, obj2);
        }
    };
    static final IORunnable IO_RUNNABLE = new IORunnable() { // from class: org.apache.commons.io.function.Constants$$ExternalSyntheticLambda1
        @Override // org.apache.commons.io.function.IORunnable
        public final void run() {
            Constants.lambda$static$1();
        }
    };
    static final IOBiFunction IO_BI_FUNCTION = new IOBiFunction() { // from class: org.apache.commons.io.function.Constants$$ExternalSyntheticLambda2
        @Override // org.apache.commons.io.function.IOBiFunction
        public final Object apply(Object obj, Object obj2) {
            return Constants.lambda$static$2(obj, obj2);
        }
    };
    static final IOFunction IO_FUNCTION_ID = new IOFunction() { // from class: org.apache.commons.io.function.Constants$$ExternalSyntheticLambda3
        @Override // org.apache.commons.io.function.IOFunction
        public final Object apply(Object obj) {
            return Constants.lambda$static$3(obj);
        }
    };
    static final IOPredicate<Object> IO_PREDICATE_FALSE = new IOPredicate() { // from class: org.apache.commons.io.function.Constants$$ExternalSyntheticLambda4
        @Override // org.apache.commons.io.function.IOPredicate
        public final boolean test(Object obj) {
            return Constants.lambda$static$4(obj);
        }
    };
    static final IOPredicate<Object> IO_PREDICATE_TRUE = new IOPredicate() { // from class: org.apache.commons.io.function.Constants$$ExternalSyntheticLambda5
        @Override // org.apache.commons.io.function.IOPredicate
        public final boolean test(Object obj) {
            return Constants.lambda$static$5(obj);
        }
    };
    static final IOTriConsumer IO_TRI_CONSUMER = new IOTriConsumer() { // from class: org.apache.commons.io.function.Constants$$ExternalSyntheticLambda6
        @Override // org.apache.commons.io.function.IOTriConsumer
        public final void accept(Object obj, Object obj2, Object obj3) {
            Constants.lambda$static$6(obj, obj2, obj3);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$static$0(Object t, Object u) throws IOException {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$static$1() throws IOException {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$static$2(Object t, Object u) throws IOException {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$static$3(Object t) throws IOException {
        return t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$4(Object t) throws IOException {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$5(Object t) throws IOException {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$static$6(Object t, Object u, Object v) throws IOException {
    }

    private Constants() {
    }
}
