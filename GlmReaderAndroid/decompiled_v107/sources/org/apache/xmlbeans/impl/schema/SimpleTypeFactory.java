package org.apache.xmlbeans.impl.schema;

import org.apache.xmlbeans.SchemaTypeSystem;

/* loaded from: classes11.dex */
public class SimpleTypeFactory<T> extends ElementFactory<T> {
    public SimpleTypeFactory(SchemaTypeSystem typeSystem, String typeHandle) {
        super(typeSystem, typeHandle);
    }

    public T newValue(Object obj) {
        return (T) getType().newValue(obj);
    }
}
