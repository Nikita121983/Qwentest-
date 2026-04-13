package org.apache.poi.xdgf.util;

/* loaded from: classes10.dex */
public class Util {
    public static int countLines(String str) {
        int lines = 1;
        int pos = 0;
        while (true) {
            int indexOf = str.indexOf(10, pos) + 1;
            pos = indexOf;
            if (indexOf != 0) {
                lines++;
            } else {
                return lines;
            }
        }
    }

    public static String sanitizeFilename(String name) {
        return name.replaceAll("[:\\\\/*\"?|<>]", "_");
    }
}
