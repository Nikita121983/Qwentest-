package org.apache.poi.xssf.util;

import java.util.Comparator;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCol;

/* loaded from: classes10.dex */
public class CTColComparator {
    public static final Comparator<CTCol> BY_MAX = new Comparator() { // from class: org.apache.poi.xssf.util.CTColComparator$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return CTColComparator.lambda$static$0((CTCol) obj, (CTCol) obj2);
        }
    };
    public static final Comparator<CTCol> BY_MIN_MAX = new Comparator() { // from class: org.apache.poi.xssf.util.CTColComparator$$ExternalSyntheticLambda1
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return CTColComparator.lambda$static$1((CTCol) obj, (CTCol) obj2);
        }
    };

    private CTColComparator() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$static$0(CTCol col1, CTCol col2) {
        long col1max = col1.getMax();
        long col2max = col2.getMax();
        return Long.compare(col1max, col2max);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$static$1(CTCol col1, CTCol col2) {
        long col11min = col1.getMin();
        long col2min = col2.getMin();
        if (col11min < col2min) {
            return -1;
        }
        if (col11min > col2min) {
            return 1;
        }
        return BY_MAX.compare(col1, col2);
    }
}
