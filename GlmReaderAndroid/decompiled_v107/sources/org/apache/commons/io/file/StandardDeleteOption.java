package org.apache.commons.io.file;

import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;

/* loaded from: classes9.dex */
public enum StandardDeleteOption implements DeleteOption {
    OVERRIDE_READ_ONLY;

    public static boolean overrideReadOnly(DeleteOption[] options) {
        if (IOUtils.length(options) == 0) {
            return false;
        }
        return Stream.of((Object[]) options).anyMatch(new Predicate() { // from class: org.apache.commons.io.file.StandardDeleteOption$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return StandardDeleteOption.lambda$overrideReadOnly$0((DeleteOption) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$overrideReadOnly$0(DeleteOption e) {
        return OVERRIDE_READ_ONLY == e;
    }
}
