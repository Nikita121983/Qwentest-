package org.apache.poi.ddf;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;

/* loaded from: classes10.dex */
public class EscherComplexProperty extends EscherProperty {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000000;
    private static int MAX_RECORD_LENGTH = DEFAULT_MAX_RECORD_LENGTH;
    private byte[] complexData;

    public static void setMaxRecordLength(int length) {
        MAX_RECORD_LENGTH = length;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public EscherComplexProperty(short id, int complexSize) {
        super((short) (32768 | id));
        this.complexData = IOUtils.safelyAllocate(complexSize, MAX_RECORD_LENGTH);
    }

    public EscherComplexProperty(short propertyNumber, boolean isBlipId, int complexSize) {
        this((short) ((isBlipId ? (short) 16384 : (short) 0) | propertyNumber), complexSize);
    }

    public EscherComplexProperty(EscherPropertyTypes type, boolean isBlipId, int complexSize) {
        this((short) (type.propNumber | (isBlipId ? (short) 16384 : (short) 0)), complexSize);
    }

    @Override // org.apache.poi.ddf.EscherProperty
    public int serializeSimplePart(byte[] data, int pos) {
        LittleEndian.putShort(data, pos, getId());
        LittleEndian.putInt(data, pos + 2, this.complexData.length);
        return 6;
    }

    @Override // org.apache.poi.ddf.EscherProperty
    public int serializeComplexPart(byte[] data, int pos) {
        System.arraycopy(this.complexData, 0, data, pos, this.complexData.length);
        return this.complexData.length;
    }

    public byte[] getComplexData() {
        return this.complexData;
    }

    public int setComplexData(byte[] complexData) {
        return setComplexData(complexData, 0);
    }

    public int setComplexData(byte[] complexData, int offset) {
        if (complexData == null) {
            return 0;
        }
        int copySize = Math.max(0, Math.min(this.complexData.length, complexData.length - offset));
        System.arraycopy(complexData, offset, this.complexData, 0, copySize);
        return copySize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resizeComplexData(int newSize) {
        resizeComplexData(newSize, Integer.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resizeComplexData(int newSize, int copyLen) {
        if (newSize == this.complexData.length) {
            return;
        }
        byte[] newArray = IOUtils.safelyAllocate(newSize, MAX_RECORD_LENGTH);
        System.arraycopy(this.complexData, 0, newArray, 0, Math.min(Math.min(this.complexData.length, copyLen), newSize));
        this.complexData = newArray;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EscherComplexProperty)) {
            return false;
        }
        EscherComplexProperty escherComplexProperty = (EscherComplexProperty) o;
        return Arrays.equals(this.complexData, escherComplexProperty.complexData);
    }

    @Override // org.apache.poi.ddf.EscherProperty
    public int getPropertySize() {
        return this.complexData.length + 6;
    }

    public int hashCode() {
        return Arrays.deepHashCode(new Object[]{this.complexData, Short.valueOf(getId())});
    }

    @Override // org.apache.poi.ddf.EscherProperty, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ddf.EscherComplexProperty$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherComplexProperty.this.m2238x7d43178c();
            }
        }, "data", new Supplier() { // from class: org.apache.poi.ddf.EscherComplexProperty$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherComplexProperty.this.getComplexData();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherComplexProperty, reason: not valid java name */
    public /* synthetic */ Object m2238x7d43178c() {
        return super.getGenericProperties();
    }
}
