package org.apache.poi;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes10.dex */
public class Version {
    private static final String VERSION_STRING = "5.5.1";

    public static String getVersion() {
        return VERSION_STRING;
    }

    public static String getProduct() {
        return "POI";
    }

    public static String getImplementationLanguage() {
        return "Java";
    }

    public static void main(String[] args) {
        System.out.println("Apache " + getProduct() + StringUtils.SPACE + getVersion());
    }
}
