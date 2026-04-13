package org.apache.poi.ddf;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import kotlin.jvm.internal.ShortCompanionObject;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.GenericRecordXmlWriter;

/* loaded from: classes10.dex */
public abstract class EscherProperty implements GenericRecord {
    private static final int[] FLAG_MASK = {16384, 32768};
    private static final String[] FLAG_NAMES = {"IS_BLIP", "IS_COMPLEX"};
    static final int IS_BLIP = 16384;
    static final int IS_COMPLEX = 32768;
    private final short id;

    public abstract int serializeComplexPart(byte[] bArr, int i);

    public abstract int serializeSimplePart(byte[] bArr, int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public EscherProperty(short id) {
        this.id = id;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EscherProperty(short propertyNumber, boolean isComplex, boolean isBlipId) {
        this((short) ((isBlipId ? 16384 : 0) | (isComplex ? ShortCompanionObject.MIN_VALUE : (short) 0) | propertyNumber));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EscherProperty(EscherPropertyTypes type, boolean isComplex, boolean isBlipId) {
        this((short) (type.propNumber | (isComplex ? ShortCompanionObject.MIN_VALUE : (short) 0) | (isBlipId ? 16384 : 0)));
    }

    public short getId() {
        return this.id;
    }

    public short getPropertyNumber() {
        return (short) (this.id & 16383);
    }

    public boolean isComplex() {
        return (this.id & ShortCompanionObject.MIN_VALUE) != 0;
    }

    public boolean isBlipId() {
        return (this.id & 16384) != 0;
    }

    public String getName() {
        return EscherPropertyTypes.forPropertyID(getPropertyNumber()).propName;
    }

    public int getPropertySize() {
        return 6;
    }

    public final String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public final String toXml(String tab) {
        return GenericRecordXmlWriter.marshal(this);
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("id", new Supplier() { // from class: org.apache.poi.ddf.EscherProperty$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(EscherProperty.this.getId());
            }
        }, "name", new Supplier() { // from class: org.apache.poi.ddf.EscherProperty$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherProperty.this.getName();
            }
        }, "propertyNumber", new Supplier() { // from class: org.apache.poi.ddf.EscherProperty$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(EscherProperty.this.getPropertyNumber());
            }
        }, "propertySize", new Supplier() { // from class: org.apache.poi.ddf.EscherProperty$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherProperty.this.getPropertySize());
            }
        }, "flags", GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.ddf.EscherProperty$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(EscherProperty.this.getId());
            }
        }, FLAG_MASK, FLAG_NAMES));
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public List<? extends GenericRecord> getGenericChildren() {
        return null;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public EscherPropertyTypes getGenericRecordType() {
        return EscherPropertyTypes.forPropertyID(this.id);
    }
}
