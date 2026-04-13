package org.apache.xmlbeans.impl.common;

import java.lang.reflect.Array;

/* loaded from: classes11.dex */
public class Levenshtein {
    private static int minimum(int a, int b, int c) {
        int mi = a;
        if (b < mi) {
            mi = b;
        }
        if (c < mi) {
            return c;
        }
        return mi;
    }

    public static int distance(String s, String t) {
        int cost;
        int n = s.length();
        int m = t.length();
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        int[][] d = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, n + 1, m + 1);
        for (int i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            d[0][j] = j;
        }
        for (int i2 = 1; i2 <= n; i2++) {
            char s_i = s.charAt(i2 - 1);
            for (int j2 = 1; j2 <= m; j2++) {
                char t_j = t.charAt(j2 - 1);
                if (s_i == t_j) {
                    cost = 0;
                } else {
                    cost = 1;
                }
                d[i2][j2] = minimum(d[i2 - 1][j2] + 1, d[i2][j2 - 1] + 1, d[i2 - 1][j2 - 1] + cost);
            }
        }
        return d[n][m];
    }
}
