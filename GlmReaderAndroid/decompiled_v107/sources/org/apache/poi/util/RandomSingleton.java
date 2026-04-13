package org.apache.poi.util;

import java.security.SecureRandom;

/* loaded from: classes10.dex */
public class RandomSingleton {
    private static final SecureRandom INSTANCE = new SecureRandom();

    public static SecureRandom getInstance() {
        return INSTANCE;
    }
}
