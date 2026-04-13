package org.apache.commons.lang3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Objects;

/* loaded from: classes9.dex */
public class SerializationUtils {

    /* loaded from: classes9.dex */
    static final class ClassLoaderAwareObjectInputStream extends ObjectInputStream {
        private final ClassLoader classLoader;

        ClassLoaderAwareObjectInputStream(InputStream in, ClassLoader classLoader) throws IOException {
            super(in);
            this.classLoader = classLoader;
        }

        @Override // java.io.ObjectInputStream
        protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
            String name = desc.getName();
            try {
                return Class.forName(name, false, this.classLoader);
            } catch (ClassNotFoundException e) {
                try {
                    return Class.forName(name, false, Thread.currentThread().getContextClassLoader());
                } catch (ClassNotFoundException cnfe) {
                    Class<?> cls = ClassUtils.getPrimitiveClass(name);
                    if (cls != null) {
                        return cls;
                    }
                    throw cnfe;
                }
            }
        }
    }

    public static <T extends Serializable> T clone(T object) {
        if (object == null) {
            return null;
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(serialize(object));
        Class<T> cls = ObjectUtils.getClass(object);
        try {
            ClassLoaderAwareObjectInputStream in = new ClassLoaderAwareObjectInputStream(bais, cls.getClassLoader());
            try {
                T t = (T) in.readObject();
                in.close();
                return t;
            } catch (Throwable th) {
                try {
                    in.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException | ClassNotFoundException ex) {
            throw new SerializationException(String.format("%s while reading cloned object data", ex.getClass().getSimpleName()), ex);
        }
    }

    public static <T> T deserialize(byte[] bArr) {
        Objects.requireNonNull(bArr, "objectData");
        return (T) deserialize(new ByteArrayInputStream(bArr));
    }

    public static <T> T deserialize(InputStream inputStream) {
        Objects.requireNonNull(inputStream, "inputStream");
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            try {
                T t = (T) objectInputStream.readObject();
                objectInputStream.close();
                return t;
            } finally {
            }
        } catch (IOException | ClassNotFoundException | NegativeArraySizeException e) {
            throw new SerializationException(e);
        }
    }

    public static <T extends Serializable> T roundtrip(T obj) {
        return (T) deserialize(serialize(obj));
    }

    public static byte[] serialize(Serializable obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        serialize(obj, baos);
        return baos.toByteArray();
    }

    public static void serialize(Serializable obj, OutputStream outputStream) {
        Objects.requireNonNull(outputStream, "outputStream");
        try {
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            try {
                out.writeObject(obj);
                out.close();
            } finally {
            }
        } catch (IOException ex) {
            throw new SerializationException(ex);
        }
    }

    @Deprecated
    public SerializationUtils() {
    }
}
