package org.apache.poi.ddf;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;

/* loaded from: classes10.dex */
public class EscherSimpleProperty extends EscherProperty {
    private final int propertyValue;

    public EscherSimpleProperty(short id, int propertyValue) {
        super(id);
        this.propertyValue = propertyValue;
    }

    public EscherSimpleProperty(EscherPropertyTypes type, int propertyValue) {
        this(type, false, false, propertyValue);
    }

    public EscherSimpleProperty(short propertyNumber, boolean isComplex, boolean isBlipId, int propertyValue) {
        super(propertyNumber, isComplex, isBlipId);
        this.propertyValue = propertyValue;
    }

    public EscherSimpleProperty(EscherPropertyTypes type, boolean isComplex, boolean isBlipId, int propertyValue) {
        super(type, isComplex, isBlipId);
        this.propertyValue = propertyValue;
    }

    @Override // org.apache.poi.ddf.EscherProperty
    public int serializeSimplePart(byte[] data, int offset) {
        LittleEndian.putShort(data, offset, getId());
        LittleEndian.putInt(data, offset + 2, this.propertyValue);
        return 6;
    }

    @Override // org.apache.poi.ddf.EscherProperty
    public int serializeComplexPart(byte[] data, int pos) {
        return 0;
    }

    public int getPropertyValue() {
        return this.propertyValue;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EscherSimpleProperty)) {
            return false;
        }
        EscherSimpleProperty escherSimpleProperty = (EscherSimpleProperty) o;
        return this.propertyValue == escherSimpleProperty.propertyValue && getId() == escherSimpleProperty.getId();
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.propertyValue), Short.valueOf(getId()));
    }

    @Override // org.apache.poi.ddf.EscherProperty, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ddf.EscherSimpleProperty$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherSimpleProperty.this.m2243x385e47e0();
            }
        }, "value", new Supplier() { // from class: org.apache.poi.ddf.EscherSimpleProperty$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherSimpleProperty.this.getPropertyValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherSimpleProperty, reason: not valid java name */
    public /* synthetic */ Object m2243x385e47e0() {
        return super.getGenericProperties();
    }
}
