package org.apache.commons.lang3.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes9.dex */
public class IterableStringTokenizer extends StringTokenizer implements Iterable<String> {
    public IterableStringTokenizer(String str) {
        super(str);
    }

    public IterableStringTokenizer(String str, String delim) {
        super(str, delim);
    }

    public IterableStringTokenizer(String str, String delim, boolean returnDelims) {
        super(str, delim, returnDelims);
    }

    @Override // java.lang.Iterable
    public Iterator<String> iterator() {
        return new Iterator<String>() { // from class: org.apache.commons.lang3.util.IterableStringTokenizer.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return IterableStringTokenizer.this.hasMoreElements();
            }

            @Override // java.util.Iterator
            public String next() {
                return Objects.toString(IterableStringTokenizer.this.nextElement(), null);
            }
        };
    }

    public String[] toArray() {
        return (String[]) toList().toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public List<String> toList() {
        List<String> list = new ArrayList<>();
        Objects.requireNonNull(list);
        forEach(new IterableStringTokenizer$$ExternalSyntheticLambda0(list));
        return list;
    }

    public Stream<String> toStream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
