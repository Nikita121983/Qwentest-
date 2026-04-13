package org.apache.logging.log4j.util;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LoggingException;
import org.apache.logging.log4j.status.StatusLogger;

/* loaded from: classes10.dex */
public final class Base64Util {
    private static final Logger LOGGER = StatusLogger.getLogger();
    private static Method encodeMethod;
    private static Object encoder;

    static {
        encodeMethod = null;
        encoder = null;
        try {
            Class<?> clazz = LoaderUtil.loadClass("java.util.Base64");
            Class<?> encoderClazz = LoaderUtil.loadClass("java.util.Base64$Encoder");
            Method method = clazz.getMethod("getEncoder", new Class[0]);
            encoder = method.invoke(null, new Object[0]);
            encodeMethod = encoderClazz.getMethod("encodeToString", byte[].class);
        } catch (Exception e) {
            try {
                Class<?> clazz2 = LoaderUtil.loadClass("javax.xml.bind.DataTypeConverter");
                encodeMethod = clazz2.getMethod("printBase64Binary", new Class[0]);
            } catch (Exception ex2) {
                LOGGER.error("Unable to create a Base64 Encoder", (Throwable) ex2);
            }
        }
    }

    private Base64Util() {
    }

    @Deprecated
    public static String encode(final String str) {
        if (str == null) {
            return null;
        }
        byte[] data = str.getBytes(Charset.defaultCharset());
        if (encodeMethod != null) {
            try {
                return (String) encodeMethod.invoke(encoder, data);
            } catch (Exception ex) {
                throw new LoggingException("Unable to encode String", ex);
            }
        }
        throw new LoggingException("No Encoder, unable to encode string");
    }
}
