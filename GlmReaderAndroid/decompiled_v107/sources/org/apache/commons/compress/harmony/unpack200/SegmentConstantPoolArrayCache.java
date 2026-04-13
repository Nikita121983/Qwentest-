package org.apache.commons.compress.harmony.unpack200;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.function.Function;
import org.apache.commons.compress.harmony.unpack200.SegmentConstantPoolArrayCache;

/* loaded from: classes9.dex */
public class SegmentConstantPoolArrayCache {
    protected IdentityHashMap<String[], CachedArray> knownArrays = new IdentityHashMap<>(1000);
    protected String[] lastArray;
    protected List<Integer> lastIndexes;
    protected String lastKey;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public class CachedArray {
        int lastKnownSize;
        String[] primaryArray;
        HashMap<String, List<Integer>> primaryTable;

        public CachedArray(String[] array) {
            this.primaryArray = array;
            this.lastKnownSize = array.length;
            this.primaryTable = new HashMap<>(this.lastKnownSize);
            cacheIndexes();
        }

        protected void cacheIndexes() {
            for (int index = 0; index < this.primaryArray.length; index++) {
                String key = this.primaryArray[index];
                this.primaryTable.computeIfAbsent(key, new Function() { // from class: org.apache.commons.compress.harmony.unpack200.SegmentConstantPoolArrayCache$CachedArray$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SegmentConstantPoolArrayCache.CachedArray.lambda$cacheIndexes$0((String) obj);
                    }
                }).add(Integer.valueOf(index));
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ List lambda$cacheIndexes$0(String k) {
            return new ArrayList();
        }

        public List<Integer> indexesForKey(String key) {
            List<Integer> list = this.primaryTable.get(key);
            return list != null ? list : Collections.emptyList();
        }

        public int lastKnownSize() {
            return this.lastKnownSize;
        }
    }

    protected boolean arrayIsCached(String[] array) {
        CachedArray cachedArray = this.knownArrays.get(array);
        return cachedArray != null && cachedArray.lastKnownSize() == array.length;
    }

    protected void cacheArray(String[] array) {
        if (arrayIsCached(array)) {
            throw new IllegalArgumentException("Trying to cache an array that already exists");
        }
        this.knownArrays.put(array, new CachedArray(array));
        this.lastArray = null;
    }

    public List<Integer> indexesForArrayKey(String[] array, String key) {
        if (!arrayIsCached(array)) {
            cacheArray(array);
        }
        if (this.lastArray == array && this.lastKey == key) {
            return this.lastIndexes;
        }
        this.lastArray = array;
        this.lastKey = key;
        this.lastIndexes = this.knownArrays.get(array).indexesForKey(key);
        return this.lastIndexes;
    }
}
