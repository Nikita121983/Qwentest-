package org.apache.commons.io.input;

import java.io.IOException;

/* loaded from: classes9.dex */
class Input {
    Input() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkOpen(boolean isOpen) throws IOException {
        if (!isOpen) {
            throw new IOException("Closed");
        }
    }
}
