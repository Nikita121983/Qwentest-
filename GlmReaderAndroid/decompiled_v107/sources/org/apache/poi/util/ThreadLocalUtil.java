package org.apache.poi.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes10.dex */
public class ThreadLocalUtil {
    private static final List<Runnable> registeredCleaners = new ArrayList();

    private ThreadLocalUtil() {
    }

    public static void clearAllThreadLocals() {
        registeredCleaners.forEach(new Consumer() { // from class: org.apache.poi.util.ThreadLocalUtil$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Runnable) obj).run();
            }
        });
    }

    @Internal
    public static void registerCleaner(Runnable cleaner) {
        registeredCleaners.add(cleaner);
    }
}
