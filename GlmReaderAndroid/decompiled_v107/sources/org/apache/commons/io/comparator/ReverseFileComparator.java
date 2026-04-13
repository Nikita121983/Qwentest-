package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;

/* loaded from: classes9.dex */
final class ReverseFileComparator extends AbstractFileComparator implements Serializable {
    private static final long serialVersionUID = -4808255005272229056L;
    private final Comparator<File> delegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReverseFileComparator(Comparator<File> delegate) {
        this.delegate = (Comparator) Objects.requireNonNull(delegate, "delegate");
    }

    @Override // java.util.Comparator
    public int compare(File file1, File file2) {
        return this.delegate.compare(file2, file1);
    }

    @Override // org.apache.commons.io.comparator.AbstractFileComparator
    public String toString() {
        return super.toString() + CollectionUtils.DEFAULT_TOSTRING_PREFIX + this.delegate.toString() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
