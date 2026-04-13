package org.apache.xmlbeans;

import java.util.Map;
import java.util.Properties;

/* loaded from: classes.dex */
public class SystemProperties {
    private static Map<Object, Object> propertyH;

    public static String getProperty(String key) {
        if (propertyH == null) {
            try {
                propertyH = System.getProperties();
            } catch (SecurityException e) {
                propertyH = new Properties();
                return null;
            }
        }
        Object ret = propertyH.get(key);
        if (ret == null) {
            return null;
        }
        return ret.toString();
    }

    public static String getProperty(String key, String defaultValue) {
        String result = getProperty(key);
        if (result == null) {
            return defaultValue;
        }
        return result;
    }

    public static void setPropertyH(Map<Object, Object> aPropertyH) {
        propertyH = aPropertyH;
    }
}
