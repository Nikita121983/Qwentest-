package org.apache.commons.compress.archivers.sevenz;

import java.util.Objects;

/* loaded from: classes9.dex */
public class SevenZMethodConfiguration {
    private final SevenZMethod method;
    private final Object options;

    public SevenZMethodConfiguration(SevenZMethod method) {
        this(method, null);
    }

    public SevenZMethodConfiguration(SevenZMethod method, Object options) {
        this.method = method;
        this.options = options;
        if (options != null && !Coders.findByMethod(method).isOptionInstance(options)) {
            throw new IllegalArgumentException("The " + method + " method doesn't support options of type " + options.getClass());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SevenZMethodConfiguration other = (SevenZMethodConfiguration) obj;
        if (Objects.equals(this.method, other.method) && Objects.equals(this.options, other.options)) {
            return true;
        }
        return false;
    }

    public SevenZMethod getMethod() {
        return this.method;
    }

    public Object getOptions() {
        return this.options;
    }

    public int hashCode() {
        if (this.method == null) {
            return 0;
        }
        return this.method.hashCode();
    }
}
