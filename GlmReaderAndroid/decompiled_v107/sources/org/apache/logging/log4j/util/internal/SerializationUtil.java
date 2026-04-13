package org.apache.logging.log4j.util.internal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.FilteredObjectInputStream;
import org.apache.xmlbeans.XmlErrorCodes;

/* loaded from: classes10.dex */
public final class SerializationUtil {
    private static final String DEFAULT_FILTER_CLASS = "org.apache.logging.log4j.util.internal.DefaultObjectInputFilter";
    public static final List<String> REQUIRED_JAVA_CLASSES;
    public static final List<String> REQUIRED_JAVA_PACKAGES;
    private static final Method getObjectInputFilter;
    private static final Method newObjectInputFilter;
    private static final Method setObjectInputFilter;

    static {
        Method[] methods = ObjectInputStream.class.getMethods();
        Method setMethod = null;
        Method getMethod = null;
        for (Method method : methods) {
            if (method.getName().equals("setObjectInputFilter")) {
                setMethod = method;
            } else if (method.getName().equals("getObjectInputFilter")) {
                getMethod = method;
            }
        }
        Method newMethod = null;
        if (setMethod != null) {
            try {
                Class<?> clazz = Class.forName(DEFAULT_FILTER_CLASS);
                Method[] methods2 = clazz.getMethods();
                int length = methods2.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Method method2 = methods2[i];
                    if (!method2.getName().equals("newInstance") || !Modifier.isStatic(method2.getModifiers())) {
                        i++;
                    } else {
                        newMethod = method2;
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
            }
        }
        newObjectInputFilter = newMethod;
        setObjectInputFilter = setMethod;
        getObjectInputFilter = getMethod;
        REQUIRED_JAVA_CLASSES = Arrays.asList("java.math.BigDecimal", "java.math.BigInteger", "java.rmi.MarshalledObject", "boolean", "byte", "char", XmlErrorCodes.DOUBLE, "float", XmlErrorCodes.INT, XmlErrorCodes.LONG, "short");
        REQUIRED_JAVA_PACKAGES = Arrays.asList("java.lang.", "java.time.", "java.util.", "org.apache.logging.log4j.");
    }

    public static void writeWrappedObject(final Serializable obj, final ObjectOutputStream out) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        try {
            oos.writeObject(obj);
            oos.flush();
            out.writeObject(bout.toByteArray());
            oos.close();
        } catch (Throwable th) {
            try {
                oos.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static Object readWrappedObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream ois;
        assertFiltered(in);
        byte[] data = (byte[]) in.readObject();
        ByteArrayInputStream bin = new ByteArrayInputStream(data);
        if (in instanceof FilteredObjectInputStream) {
            ois = new FilteredObjectInputStream(bin, ((FilteredObjectInputStream) in).getAllowedClasses());
        } else {
            try {
                Object obj = getObjectInputFilter.invoke(in, new Object[0]);
                Object filter = newObjectInputFilter.invoke(null, obj);
                ObjectInputStream ois2 = new ObjectInputStream(bin);
                setObjectInputFilter.invoke(ois2, filter);
                ois = ois2;
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new StreamCorruptedException("Unable to set ObjectInputFilter on stream");
            }
        }
        try {
            try {
                return ois.readObject();
            } finally {
                ois.close();
            }
        } catch (Exception | LinkageError e2) {
            StatusLogger.getLogger().warn("Ignoring {} during deserialization", e2.getMessage());
            return null;
        }
    }

    public static void assertFiltered(final ObjectInputStream stream) {
        if (!(stream instanceof FilteredObjectInputStream) && setObjectInputFilter == null) {
            throw new IllegalArgumentException("readObject requires a FilteredObjectInputStream or an ObjectInputStream that accepts an ObjectInputFilter");
        }
    }

    public static String stripArray(final Class<?> clazz) {
        Class<?> currentClazz = clazz;
        while (currentClazz.isArray()) {
            currentClazz = currentClazz.getComponentType();
        }
        return currentClazz.getName();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0077, code lost:
    
        if (r2.equals("B") != false) goto L37;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String stripArray(final java.lang.String r4) {
        /*
            r0 = 91
            int r0 = r4.lastIndexOf(r0)
            r1 = 1
            int r0 = r0 + r1
            if (r0 != 0) goto Lb
            return r4
        Lb:
            char r2 = r4.charAt(r0)
            r3 = 76
            if (r2 != r3) goto L1f
            int r2 = r0 + 1
            int r3 = r4.length()
            int r3 = r3 - r1
            java.lang.String r1 = r4.substring(r2, r3)
            return r1
        L1f:
            java.lang.String r2 = r4.substring(r0)
            int r3 = r2.hashCode()
            switch(r3) {
                case 66: goto L71;
                case 67: goto L67;
                case 68: goto L5d;
                case 70: goto L53;
                case 73: goto L49;
                case 74: goto L3f;
                case 83: goto L35;
                case 90: goto L2b;
                default: goto L2a;
            }
        L2a:
            goto L7a
        L2b:
            java.lang.String r1 = "Z"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L2a
            r1 = 0
            goto L7b
        L35:
            java.lang.String r1 = "S"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L2a
            r1 = 7
            goto L7b
        L3f:
            java.lang.String r1 = "J"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L2a
            r1 = 6
            goto L7b
        L49:
            java.lang.String r1 = "I"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L2a
            r1 = 5
            goto L7b
        L53:
            java.lang.String r1 = "F"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L2a
            r1 = 4
            goto L7b
        L5d:
            java.lang.String r1 = "D"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L2a
            r1 = 3
            goto L7b
        L67:
            java.lang.String r1 = "C"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L2a
            r1 = 2
            goto L7b
        L71:
            java.lang.String r3 = "B"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L2a
            goto L7b
        L7a:
            r1 = -1
        L7b:
            switch(r1) {
                case 0: goto Lb2;
                case 1: goto Laf;
                case 2: goto Lac;
                case 3: goto La9;
                case 4: goto La6;
                case 5: goto La3;
                case 6: goto La0;
                case 7: goto L9d;
                default: goto L7e;
            }
        L7e:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unsupported array class signature '"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r3 = "'"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L9d:
            java.lang.String r1 = "short"
            return r1
        La0:
            java.lang.String r1 = "long"
            return r1
        La3:
            java.lang.String r1 = "int"
            return r1
        La6:
            java.lang.String r1 = "float"
            return r1
        La9:
            java.lang.String r1 = "double"
            return r1
        Lac:
            java.lang.String r1 = "char"
            return r1
        Laf:
            java.lang.String r1 = "byte"
            return r1
        Lb2:
            java.lang.String r1 = "boolean"
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.logging.log4j.util.internal.SerializationUtil.stripArray(java.lang.String):java.lang.String");
    }

    private SerializationUtil() {
    }
}
