package org.apache.poi.poifs.filesystem;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* loaded from: classes10.dex */
public class POIFSDocumentPath {
    private final String[] components;
    private int hashcode;

    public POIFSDocumentPath() {
        this.components = new String[0];
    }

    public POIFSDocumentPath(String[] components) throws IllegalArgumentException {
        this(null, components);
    }

    public POIFSDocumentPath(POIFSDocumentPath path, String[] components) throws IllegalArgumentException {
        String[] s1 = path == null ? new String[0] : path.components;
        String[] s2 = components == null ? new String[0] : components;
        Predicate<String> p = path != null ? new Predicate() { // from class: org.apache.poi.poifs.filesystem.POIFSDocumentPath$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean isNull;
                isNull = Objects.isNull((String) obj);
                return isNull;
            }
        } : new Predicate() { // from class: org.apache.poi.poifs.filesystem.POIFSDocumentPath$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return POIFSDocumentPath.lambda$new$0((String) obj);
            }
        };
        if (Stream.of((Object[]) s2).anyMatch(p)) {
            throw new IllegalArgumentException("components cannot contain null or empty strings");
        }
        this.components = (String[]) Stream.concat(Stream.of((Object[]) s1), Stream.of((Object[]) s2)).toArray(new IntFunction() { // from class: org.apache.poi.poifs.filesystem.POIFSDocumentPath$$ExternalSyntheticLambda2
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return POIFSDocumentPath.lambda$new$1(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$new$0(String s) {
        return s == null || s.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$new$1(int x$0) {
        return new String[x$0];
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && o.getClass() == getClass()) {
            POIFSDocumentPath path = (POIFSDocumentPath) o;
            return Arrays.equals(this.components, path.components);
        }
        return false;
    }

    public int hashCode() {
        if (this.hashcode != 0) {
            return this.hashcode;
        }
        int hashCode = Arrays.hashCode(this.components);
        this.hashcode = hashCode;
        return hashCode;
    }

    public int length() {
        return this.components.length;
    }

    public String getComponent(int n) throws ArrayIndexOutOfBoundsException {
        return this.components[n];
    }

    public POIFSDocumentPath getParent() {
        if (this.components.length == 0) {
            return null;
        }
        return new POIFSDocumentPath((String[]) Arrays.copyOf(this.components, this.components.length - 1));
    }

    public String getName() {
        return this.components.length == 0 ? "" : this.components[this.components.length - 1];
    }

    public String toString() {
        return File.separatorChar + String.join(String.valueOf(File.separatorChar), this.components);
    }
}
