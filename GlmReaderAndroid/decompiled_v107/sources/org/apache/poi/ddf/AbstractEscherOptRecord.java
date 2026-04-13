package org.apache.poi.ddf;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;

/* loaded from: classes10.dex */
public abstract class AbstractEscherOptRecord extends EscherRecord {
    private final List<EscherProperty> properties;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractEscherOptRecord() {
        this.properties = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractEscherOptRecord(AbstractEscherOptRecord other) {
        super(other);
        this.properties = new ArrayList();
        this.properties.addAll(other.properties);
    }

    public void addEscherProperty(EscherProperty prop) {
        this.properties.add(prop);
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesRemaining = readHeader(data, offset);
        if (bytesRemaining < 0) {
            throw new IllegalStateException("Invalid value for bytesRemaining: " + bytesRemaining);
        }
        short propertiesCount = readInstance(data, offset);
        int pos = offset + 8;
        EscherPropertyFactory f = new EscherPropertyFactory();
        this.properties.clear();
        this.properties.addAll(f.createProperties(data, pos, propertiesCount));
        return bytesRemaining + 8;
    }

    public List<EscherProperty> getEscherProperties() {
        return this.properties;
    }

    public EscherProperty getEscherProperty(int index) {
        return this.properties.get(index);
    }

    private int getPropertiesSize() {
        int totalSize = 0;
        for (EscherProperty property : this.properties) {
            totalSize += property.getPropertySize();
        }
        return totalSize;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int getRecordSize() {
        return getPropertiesSize() + 8;
    }

    public <T extends EscherProperty> T lookup(EscherPropertyTypes escherPropertyTypes) {
        return (T) lookup(escherPropertyTypes.propNumber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$lookup$0(int propId, EscherProperty p) {
        return p.getPropertyNumber() == propId;
    }

    public <T extends EscherProperty> T lookup(final int propId) {
        return (T) this.properties.stream().filter(new Predicate() { // from class: org.apache.poi.ddf.AbstractEscherOptRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AbstractEscherOptRecord.lambda$lookup$0(propId, (EscherProperty) obj);
            }
        }).findFirst().orElse(null);
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, getRecordId(), this);
        LittleEndian.putShort(data, offset, getOptions());
        LittleEndian.putShort(data, offset + 2, getRecordId());
        LittleEndian.putInt(data, offset + 4, getPropertiesSize());
        int pos = offset + 8;
        for (EscherProperty property : this.properties) {
            pos += property.serializeSimplePart(data, pos);
        }
        for (EscherProperty property2 : this.properties) {
            pos += property2.serializeComplexPart(data, pos);
        }
        listener.afterRecordSerialize(pos, getRecordId(), pos - offset, this);
        return pos - offset;
    }

    public void sortProperties() {
        this.properties.sort(Comparator.comparingInt(new ToIntFunction() { // from class: org.apache.poi.ddf.AbstractEscherOptRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((EscherProperty) obj).getPropertyNumber();
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$setEscherProperty$1(EscherProperty value, EscherProperty prop) {
        return prop.getId() == value.getId();
    }

    public void setEscherProperty(final EscherProperty value) {
        this.properties.removeIf(new Predicate() { // from class: org.apache.poi.ddf.AbstractEscherOptRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AbstractEscherOptRecord.lambda$setEscherProperty$1(EscherProperty.this, (EscherProperty) obj);
            }
        });
        this.properties.add(value);
        sortProperties();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$removeEscherProperty$2(EscherPropertyTypes type, EscherProperty prop) {
        return prop.getPropertyNumber() == type.propNumber;
    }

    public void removeEscherProperty(final EscherPropertyTypes type) {
        this.properties.removeIf(new Predicate() { // from class: org.apache.poi.ddf.AbstractEscherOptRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AbstractEscherOptRecord.lambda$removeEscherProperty$2(EscherPropertyTypes.this, (EscherProperty) obj);
            }
        });
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ddf.AbstractEscherOptRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return AbstractEscherOptRecord.this.m2231x79b053a6();
            }
        }, "isContainer", new Supplier() { // from class: org.apache.poi.ddf.AbstractEscherOptRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(AbstractEscherOptRecord.this.isContainerRecord());
            }
        }, "properties", new Supplier() { // from class: org.apache.poi.ddf.AbstractEscherOptRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return AbstractEscherOptRecord.this.getEscherProperties();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-ddf-AbstractEscherOptRecord, reason: not valid java name */
    public /* synthetic */ Object m2231x79b053a6() {
        return super.getGenericProperties();
    }
}
