package org.apache.commons.lang3.builder;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes9.dex */
public class DiffResult<T> implements Iterable<Diff<?>> {
    public static final String OBJECTS_SAME_STRING = "";
    private final List<Diff<?>> diffList;
    private final T lhs;
    private final T rhs;
    private final ToStringStyle style;
    private final String toStringFormat;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DiffResult(T t, T t2, List<Diff<?>> list, ToStringStyle toStringStyle, String str) {
        this.diffList = (List) Objects.requireNonNull(list, "diffList");
        this.lhs = (T) Objects.requireNonNull(t, "lhs");
        this.rhs = (T) Objects.requireNonNull(t2, "rhs");
        this.style = (ToStringStyle) Objects.requireNonNull(toStringStyle, "style");
        this.toStringFormat = (String) Objects.requireNonNull(str, "toStringFormat");
    }

    public List<Diff<?>> getDiffs() {
        return Collections.unmodifiableList(this.diffList);
    }

    public T getLeft() {
        return this.lhs;
    }

    public int getNumberOfDiffs() {
        return this.diffList.size();
    }

    public T getRight() {
        return this.rhs;
    }

    public ToStringStyle getToStringStyle() {
        return this.style;
    }

    @Override // java.lang.Iterable
    public Iterator<Diff<?>> iterator() {
        return this.diffList.iterator();
    }

    public String toString() {
        return toString(this.style);
    }

    public String toString(ToStringStyle style) {
        if (this.diffList.isEmpty()) {
            return "";
        }
        final ToStringBuilder lhsBuilder = new ToStringBuilder(this.lhs, style);
        final ToStringBuilder rhsBuilder = new ToStringBuilder(this.rhs, style);
        this.diffList.forEach(new Consumer() { // from class: org.apache.commons.lang3.builder.DiffResult$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DiffResult.lambda$toString$0(ToStringBuilder.this, rhsBuilder, (Diff) obj);
            }
        });
        return String.format(this.toStringFormat, lhsBuilder.build(), rhsBuilder.build());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$toString$0(ToStringBuilder lhsBuilder, ToStringBuilder rhsBuilder, Diff diff) {
        lhsBuilder.append(diff.getFieldName(), diff.getLeft());
        rhsBuilder.append(diff.getFieldName(), diff.getRight());
    }
}
