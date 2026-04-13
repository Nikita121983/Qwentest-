package org.apache.poi.sl.draw;

import java.util.Collections;
import java.util.function.Supplier;

/* loaded from: classes10.dex */
public interface EmbeddedExtractor {

    /* loaded from: classes10.dex */
    public static class EmbeddedPart {
        private Supplier<byte[]> data;
        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Supplier<byte[]> getData() {
            return this.data;
        }

        public void setData(Supplier<byte[]> data) {
            this.data = data;
        }
    }

    default Iterable<EmbeddedPart> getEmbeddings() {
        return Collections.emptyList();
    }
}
