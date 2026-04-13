package org.apache.poi.hssf.record.crypto;

import org.apache.poi.util.ThreadLocalUtil;

/* loaded from: classes10.dex */
public final class Biff8EncryptionKey {
    private static final ThreadLocal<String> _userPasswordTLS = new ThreadLocal<>();

    static {
        final ThreadLocal<String> threadLocal = _userPasswordTLS;
        threadLocal.getClass();
        ThreadLocalUtil.registerCleaner(new Runnable() { // from class: org.apache.poi.hssf.record.crypto.Biff8EncryptionKey$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                threadLocal.remove();
            }
        });
    }

    public static void setCurrentUserPassword(String password) {
        if (password == null) {
            _userPasswordTLS.remove();
        } else {
            _userPasswordTLS.set(password);
        }
    }

    public static String getCurrentUserPassword() {
        return _userPasswordTLS.get();
    }
}
