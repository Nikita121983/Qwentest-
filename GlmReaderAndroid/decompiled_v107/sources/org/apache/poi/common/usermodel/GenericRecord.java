package org.apache.poi.common.usermodel;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/* loaded from: classes10.dex */
public interface GenericRecord {
    Map<String, Supplier<?>> getGenericProperties();

    default Enum<?> getGenericRecordType() {
        return null;
    }

    default List<? extends GenericRecord> getGenericChildren() {
        return null;
    }
}
