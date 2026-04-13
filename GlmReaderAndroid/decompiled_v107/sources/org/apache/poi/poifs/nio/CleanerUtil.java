package org.apache.poi.poifs.nio;

import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Objects;
import org.apache.poi.util.ExceptionUtil;

/* loaded from: classes10.dex */
public final class CleanerUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final BufferCleaner CLEANER;
    public static final String UNMAP_NOT_SUPPORTED_REASON;
    public static final boolean UNMAP_SUPPORTED;

    @FunctionalInterface
    /* loaded from: classes10.dex */
    public interface BufferCleaner {
        void freeBuffer(ByteBuffer byteBuffer) throws IOException;
    }

    static {
        Object hack = AccessController.doPrivileged((PrivilegedAction<Object>) new PrivilegedAction() { // from class: org.apache.poi.poifs.nio.CleanerUtil$$ExternalSyntheticLambda0
            @Override // java.security.PrivilegedAction
            public final Object run() {
                Object unmapHackImpl;
                unmapHackImpl = CleanerUtil.unmapHackImpl();
                return unmapHackImpl;
            }
        });
        if (hack instanceof BufferCleaner) {
            CLEANER = (BufferCleaner) hack;
            UNMAP_SUPPORTED = true;
            UNMAP_NOT_SUPPORTED_REASON = null;
        } else {
            CLEANER = null;
            UNMAP_SUPPORTED = false;
            UNMAP_NOT_SUPPORTED_REASON = hack.toString();
        }
    }

    private CleanerUtil() {
    }

    public static BufferCleaner getCleaner() {
        return CLEANER;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object unmapHackImpl() {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try {
            try {
                Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
                MethodHandle unmapper = lookup.findVirtual(unsafeClass, "invokeCleaner", MethodType.methodType((Class<?>) Void.TYPE, (Class<?>) ByteBuffer.class));
                Field f = unsafeClass.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                Object theUnsafe = f.get(null);
                return newBufferCleaner(ByteBuffer.class, unmapper.bindTo(theUnsafe));
            } catch (ReflectiveOperationException e) {
                Class<?> directBufferClass = Class.forName("java.nio.DirectByteBuffer");
                Method m = directBufferClass.getMethod("cleaner", new Class[0]);
                m.setAccessible(true);
                MethodHandle directBufferCleanerMethod = lookup.unreflect(m);
                Class<?> cleanerClass = directBufferCleanerMethod.type().returnType();
                MethodHandle cleanMethod = lookup.findVirtual(cleanerClass, "clean", MethodType.methodType(Void.TYPE));
                MethodHandle nonNullTest = lookup.findStatic(Objects.class, "nonNull", MethodType.methodType((Class<?>) Boolean.TYPE, (Class<?>) Object.class)).asType(MethodType.methodType((Class<?>) Boolean.TYPE, cleanerClass));
                MethodHandle noop = MethodHandles.dropArguments(MethodHandles.constant(Void.class, null).asType(MethodType.methodType(Void.TYPE)), 0, (Class<?>[]) new Class[]{cleanerClass});
                MethodHandle unmapper2 = MethodHandles.filterReturnValue(directBufferCleanerMethod, MethodHandles.guardWithTest(nonNullTest, cleanMethod, noop)).asType(MethodType.methodType((Class<?>) Void.TYPE, (Class<?>) ByteBuffer.class));
                return newBufferCleaner(directBufferClass, unmapper2);
            } catch (SecurityException se) {
                throw se;
            } catch (RuntimeException e2) {
                Class<?> directBufferClass2 = Class.forName("java.nio.DirectByteBuffer");
                Method m2 = directBufferClass2.getMethod("cleaner", new Class[0]);
                m2.setAccessible(true);
                MethodHandle directBufferCleanerMethod2 = lookup.unreflect(m2);
                Class<?> cleanerClass2 = directBufferCleanerMethod2.type().returnType();
                MethodHandle cleanMethod2 = lookup.findVirtual(cleanerClass2, "clean", MethodType.methodType(Void.TYPE));
                MethodHandle nonNullTest2 = lookup.findStatic(Objects.class, "nonNull", MethodType.methodType((Class<?>) Boolean.TYPE, (Class<?>) Object.class)).asType(MethodType.methodType((Class<?>) Boolean.TYPE, cleanerClass2));
                MethodHandle noop2 = MethodHandles.dropArguments(MethodHandles.constant(Void.class, null).asType(MethodType.methodType(Void.TYPE)), 0, (Class<?>[]) new Class[]{cleanerClass2});
                MethodHandle unmapper22 = MethodHandles.filterReturnValue(directBufferCleanerMethod2, MethodHandles.guardWithTest(nonNullTest2, cleanMethod2, noop2)).asType(MethodType.methodType((Class<?>) Void.TYPE, (Class<?>) ByteBuffer.class));
                return newBufferCleaner(directBufferClass2, unmapper22);
            }
        } catch (ReflectiveOperationException e3) {
            e = e3;
            return "Unmapping is not supported on this platform, because internal Java APIs are not compatible with this Hadoop version: " + e;
        } catch (SecurityException se2) {
            return "Unmapping is not supported, because not all required permissions are given to the Hadoop JAR file: " + se2 + " [Please grant at least the following permissions: RuntimePermission(\"accessClassInPackage.sun.misc\")  and ReflectPermission(\"suppressAccessChecks\")]";
        } catch (RuntimeException e4) {
            e = e4;
            return "Unmapping is not supported on this platform, because internal Java APIs are not compatible with this Hadoop version: " + e;
        }
    }

    private static BufferCleaner newBufferCleaner(final Class<?> unmappableBufferClass, final MethodHandle unmapper) {
        if (!Objects.equals(MethodType.methodType((Class<?>) Void.TYPE, (Class<?>) ByteBuffer.class), unmapper.type())) {
            throw new AssertionError();
        }
        return new BufferCleaner() { // from class: org.apache.poi.poifs.nio.CleanerUtil$$ExternalSyntheticLambda2
            @Override // org.apache.poi.poifs.nio.CleanerUtil.BufferCleaner
            public final void freeBuffer(ByteBuffer byteBuffer) {
                CleanerUtil.lambda$newBufferCleaner$1(unmappableBufferClass, unmapper, byteBuffer);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$newBufferCleaner$1(Class unmappableBufferClass, final MethodHandle unmapper, final ByteBuffer buffer) throws IOException {
        if (!buffer.isDirect()) {
            throw new IllegalArgumentException("unmapping only works with direct buffers");
        }
        if (!unmappableBufferClass.isInstance(buffer)) {
            throw new IllegalArgumentException("buffer is not an instance of " + unmappableBufferClass.getName());
        }
        Throwable error = (Throwable) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.poi.poifs.nio.CleanerUtil$$ExternalSyntheticLambda1
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return CleanerUtil.lambda$null$0(unmapper, buffer);
            }
        });
        if (error != null) {
            if (ExceptionUtil.isFatal(error)) {
                ExceptionUtil.rethrow(error);
            }
            throw new IOException("Unable to unmap the mapped buffer", error);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Throwable lambda$null$0(MethodHandle unmapper, ByteBuffer buffer) {
        try {
            (void) unmapper.invokeExact(buffer);
            return null;
        } catch (Throwable t) {
            if (ExceptionUtil.isFatal(t)) {
                ExceptionUtil.rethrow(t);
                return null;
            }
            return null;
        }
    }
}
