package org.apache.poi.ddf;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.Removal;

/* loaded from: classes10.dex */
public final class EscherArrayProperty extends EscherComplexProperty implements Iterable<byte[]> {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000;
    private static final int FIXED_SIZE = 6;
    private static int MAX_RECORD_LENGTH = 100000;
    private final boolean emptyComplexPart;
    private boolean sizeIncludesHeaderSize;

    public static void setMaxRecordLength(int length) {
        MAX_RECORD_LENGTH = length;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    @Internal
    public EscherArrayProperty(short id, int complexSize) {
        super(id, complexSize);
        this.sizeIncludesHeaderSize = true;
        this.emptyComplexPart = complexSize == 0;
    }

    @Removal(version = "5.0.0")
    @Deprecated
    public EscherArrayProperty(short propertyNumber, boolean isBlipId, byte[] complexData) {
        this((short) ((isBlipId ? (short) 16384 : (short) 0) | propertyNumber), safeSize(complexData != null ? complexData.length : 0));
        setComplexData(complexData);
    }

    public EscherArrayProperty(EscherPropertyTypes type, boolean isBlipId, int complexSize) {
        this((short) (type.propNumber | (isBlipId ? (short) 16384 : (short) 0)), safeSize(complexSize));
    }

    private static int safeSize(int complexSize) {
        if (complexSize == 0) {
            return 6;
        }
        return complexSize;
    }

    public int getNumberOfElementsInArray() {
        if (this.emptyComplexPart) {
            return 0;
        }
        return LittleEndian.getUShort(getComplexData(), 0);
    }

    public void setNumberOfElementsInArray(int numberOfElements) {
        if (this.emptyComplexPart) {
            return;
        }
        rewriteArray(numberOfElements, false);
        LittleEndian.putShort(getComplexData(), 0, (short) numberOfElements);
    }

    private void rewriteArray(int numberOfElements, boolean copyToNewLen) {
        int expectedArraySize = (getActualSizeOfElements(getSizeOfElements()) * numberOfElements) + 6;
        resizeComplexData(expectedArraySize, copyToNewLen ? expectedArraySize : getComplexData().length);
    }

    public int getNumberOfElementsInMemory() {
        if (this.emptyComplexPart) {
            return 0;
        }
        return LittleEndian.getUShort(getComplexData(), 2);
    }

    public void setNumberOfElementsInMemory(int numberOfElements) {
        if (this.emptyComplexPart) {
            return;
        }
        rewriteArray(numberOfElements, true);
        LittleEndian.putShort(getComplexData(), 2, (short) numberOfElements);
    }

    public short getSizeOfElements() {
        if (this.emptyComplexPart) {
            return (short) 0;
        }
        return LittleEndian.getShort(getComplexData(), 4);
    }

    public void setSizeOfElements(int sizeOfElements) {
        if (this.emptyComplexPart) {
            return;
        }
        LittleEndian.putShort(getComplexData(), 4, (short) sizeOfElements);
        int expectedArraySize = (getNumberOfElementsInArray() * getActualSizeOfElements(getSizeOfElements())) + 6;
        resizeComplexData(expectedArraySize, 6);
    }

    public byte[] getElement(int index) {
        int actualSize = getActualSizeOfElements(getSizeOfElements());
        return IOUtils.safelyClone(getComplexData(), (index * actualSize) + 6, actualSize, MAX_RECORD_LENGTH);
    }

    public void setElement(int index, byte[] element) {
        if (this.emptyComplexPart) {
            return;
        }
        int actualSize = getActualSizeOfElements(getSizeOfElements());
        System.arraycopy(element, 0, getComplexData(), (index * actualSize) + 6, actualSize);
    }

    public int setArrayData(byte[] data, int offset) {
        if (this.emptyComplexPart) {
            resizeComplexData(0);
        } else {
            short numElements = LittleEndian.getShort(data, offset);
            short sizeOfElements = LittleEndian.getShort(data, offset + 4);
            int cdLen = getComplexData().length;
            int arraySize = getActualSizeOfElements(sizeOfElements) * numElements;
            if (arraySize == cdLen) {
                resizeComplexData(arraySize + 6, 0);
                this.sizeIncludesHeaderSize = false;
            }
            setComplexData(data, offset);
        }
        return getComplexData().length;
    }

    @Override // org.apache.poi.ddf.EscherComplexProperty, org.apache.poi.ddf.EscherProperty
    public int serializeSimplePart(byte[] data, int pos) {
        LittleEndian.putShort(data, pos, getId());
        int recordSize = getComplexData().length;
        if (!this.sizeIncludesHeaderSize) {
            recordSize -= 6;
        }
        LittleEndian.putInt(data, pos + 2, recordSize);
        return 6;
    }

    private static int getActualSizeOfElements(short sizeOfElements) {
        if (sizeOfElements < 0) {
            return (short) ((-sizeOfElements) >> 2);
        }
        return sizeOfElements;
    }

    @Override // java.lang.Iterable
    public Iterator<byte[]> iterator() {
        return new Iterator<byte[]>() { // from class: org.apache.poi.ddf.EscherArrayProperty.1
            int idx;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.idx < EscherArrayProperty.this.getNumberOfElementsInArray();
            }

            @Override // java.util.Iterator
            public byte[] next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                EscherArrayProperty escherArrayProperty = EscherArrayProperty.this;
                int i = this.idx;
                this.idx = i + 1;
                return escherArrayProperty.getElement(i);
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("not yet implemented");
            }
        };
    }

    @Override // java.lang.Iterable
    public Spliterator<byte[]> spliterator() {
        return Spliterators.spliterator(iterator(), getNumberOfElementsInArray(), 0);
    }

    @Override // org.apache.poi.ddf.EscherComplexProperty, org.apache.poi.ddf.EscherProperty, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ddf.EscherArrayProperty$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherArrayProperty.this.m2232x364979f5();
            }
        }, "numElements", new Supplier() { // from class: org.apache.poi.ddf.EscherArrayProperty$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherArrayProperty.this.getNumberOfElementsInArray());
            }
        }, "numElementsInMemory", new Supplier() { // from class: org.apache.poi.ddf.EscherArrayProperty$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherArrayProperty.this.getNumberOfElementsInMemory());
            }
        }, "sizeOfElements", new Supplier() { // from class: org.apache.poi.ddf.EscherArrayProperty$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(EscherArrayProperty.this.getSizeOfElements());
            }
        }, "elements", new Supplier() { // from class: org.apache.poi.ddf.EscherArrayProperty$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherArrayProperty.this.m2233x8d676ad4();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherArrayProperty, reason: not valid java name */
    public /* synthetic */ Object m2232x364979f5() {
        return super.getGenericProperties();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-ddf-EscherArrayProperty, reason: not valid java name */
    public /* synthetic */ Object m2233x8d676ad4() {
        return (List) StreamSupport.stream(spliterator(), false).collect(Collectors.toList());
    }
}
