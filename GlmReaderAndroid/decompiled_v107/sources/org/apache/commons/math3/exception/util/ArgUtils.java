package org.apache.commons.math3.exception.util;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes10.dex */
public class ArgUtils {
    private ArgUtils() {
    }

    public static Object[] flatten(Object[] array) {
        List<Object> list = new ArrayList<>();
        if (array != null) {
            for (Object o : array) {
                if (o instanceof Object[]) {
                    Object[] arr$ = flatten((Object[]) o);
                    for (Object oR : arr$) {
                        list.add(oR);
                    }
                } else {
                    list.add(o);
                }
            }
        }
        return list.toArray();
    }
}
