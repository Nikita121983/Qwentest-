package org.apache.poi.util;

import java.util.Iterator;
import java.util.PrimitiveIterator;

@Internal
/* loaded from: classes10.dex */
public class CodepointsUtil {
    public static Iterator<String> iteratorFor(String text) {
        final PrimitiveIterator.OfInt iter = primitiveIterator(text);
        return new Iterator<String>() { // from class: org.apache.poi.util.CodepointsUtil.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override // java.util.Iterator
            public String next() {
                return new String(Character.toChars(iter.nextInt()));
            }
        };
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.PrimitiveIterator$OfInt] */
    public static PrimitiveIterator.OfInt primitiveIterator(String text) {
        return text.codePoints().iterator();
    }
}
