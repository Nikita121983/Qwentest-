package org.apache.commons.io.input;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/* loaded from: classes9.dex */
final class ByteBufferCleaner {
    private static final Cleaner INSTANCE = getCleaner();

    /* loaded from: classes9.dex */
    private interface Cleaner {
        void clean(ByteBuffer byteBuffer) throws ReflectiveOperationException;
    }

    ByteBufferCleaner() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class Java8Cleaner implements Cleaner {
        private final Method cleanMethod;
        private final Method cleanerMethod;

        private Java8Cleaner() throws ReflectiveOperationException, SecurityException {
            this.cleanMethod = Class.forName("sun.misc.Cleaner").getMethod("clean", new Class[0]);
            this.cleanerMethod = Class.forName("sun.nio.ch.DirectBuffer").getMethod("cleaner", new Class[0]);
        }

        @Override // org.apache.commons.io.input.ByteBufferCleaner.Cleaner
        public void clean(ByteBuffer buffer) throws ReflectiveOperationException {
            Object cleaner = this.cleanerMethod.invoke(buffer, new Object[0]);
            if (cleaner != null) {
                this.cleanMethod.invoke(cleaner, new Object[0]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class Java9Cleaner implements Cleaner {
        private final Method invokeCleaner;
        private final Object theUnsafe;

        private Java9Cleaner() throws ReflectiveOperationException, SecurityException {
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            Field field = unsafeClass.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            this.theUnsafe = field.get(null);
            this.invokeCleaner = unsafeClass.getMethod("invokeCleaner", ByteBuffer.class);
        }

        @Override // org.apache.commons.io.input.ByteBufferCleaner.Cleaner
        public void clean(ByteBuffer buffer) throws ReflectiveOperationException {
            this.invokeCleaner.invoke(this.theUnsafe, buffer);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clean(ByteBuffer buffer) {
        try {
            INSTANCE.clean(buffer);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to clean direct buffer.", e);
        }
    }

    private static Cleaner getCleaner() {
        try {
            return new Java8Cleaner();
        } catch (Exception e) {
            try {
                return new Java9Cleaner();
            } catch (Exception e2) {
                throw new IllegalStateException("Failed to initialize a Cleaner.", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSupported() {
        return INSTANCE != null;
    }
}
