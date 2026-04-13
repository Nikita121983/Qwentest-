package org.apache.poi.ddf;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.GenericRecordXmlWriter;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;

/* loaded from: classes10.dex */
public abstract class EscherRecord implements Duplicatable, GenericRecord {
    private static final int DEFAULT_MAX_NUMBER_OF_CHILDREN = 100000;
    private short _options;
    private short _recordId;
    private static final BitField fInstance = BitFieldFactory.getInstance(65520);
    private static final BitField fVersion = BitFieldFactory.getInstance(15);
    protected static int MAX_NUMBER_OF_CHILDREN = 100000;

    @Override // org.apache.poi.common.Duplicatable
    public abstract EscherRecord copy();

    public abstract int fillFields(byte[] bArr, int i, EscherRecordFactory escherRecordFactory);

    public abstract String getRecordName();

    public abstract int getRecordSize();

    public abstract int serialize(int i, byte[] bArr, EscherSerializationListener escherSerializationListener);

    public EscherRecord() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EscherRecord(EscherRecord other) {
        this._options = other._options;
        this._recordId = other._recordId;
    }

    protected int fillFields(byte[] data, EscherRecordFactory f) {
        return fillFields(data, 0, f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory, int nesting) {
        return fillFields(data, offset, recordFactory);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int readHeader(byte[] data, int offset) {
        this._options = LittleEndian.getShort(data, offset);
        this._recordId = LittleEndian.getShort(data, offset + 2);
        return LittleEndian.getInt(data, offset + 4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static short readInstance(byte[] data, int offset) {
        short options = LittleEndian.getShort(data, offset);
        return fInstance.getShortValue(options);
    }

    public boolean isContainerRecord() {
        return getVersion() == 15;
    }

    @Internal
    public short getOptions() {
        return this._options;
    }

    @Internal
    public void setOptions(short options) {
        setVersion(fVersion.getShortValue(options));
        setInstance(fInstance.getShortValue(options));
        this._options = options;
    }

    public byte[] serialize() {
        byte[] retval = new byte[getRecordSize()];
        serialize(0, retval);
        return retval;
    }

    public int serialize(int offset, byte[] data) {
        return serialize(offset, data, new NullEscherSerializationListener());
    }

    public short getRecordId() {
        return this._recordId;
    }

    public void setRecordId(short recordId) {
        this._recordId = recordId;
    }

    public List<EscherRecord> getChildRecords() {
        return Collections.emptyList();
    }

    public void setChildRecords(List<EscherRecord> childRecords) {
        throw new UnsupportedOperationException("This record does not support child records.");
    }

    public EscherRecord getChild(int index) {
        return getChildRecords().get(index);
    }

    public void display(PrintWriter w, int indent) {
        for (int i = 0; i < indent * 4; i++) {
            w.print(Chars.SPACE);
        }
        w.println(getRecordName());
    }

    public short getInstance() {
        return fInstance.getShortValue(this._options);
    }

    public void setInstance(short value) {
        this._options = fInstance.setShortValue(this._options, value);
    }

    public short getVersion() {
        return fVersion.getShortValue(this._options);
    }

    public void setVersion(short value) {
        this._options = fVersion.setShortValue(this._options, value);
    }

    public String toXml() {
        return toXml("");
    }

    public final String toXml(String tab) {
        return GenericRecordXmlWriter.marshal(this);
    }

    public final String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public List<? extends GenericRecord> getGenericChildren() {
        return getChildRecords();
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("recordId", new Supplier() { // from class: org.apache.poi.ddf.EscherRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(EscherRecord.this.getRecordId());
            }
        }, "version", new Supplier() { // from class: org.apache.poi.ddf.EscherRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(EscherRecord.this.getVersion());
            }
        }, "instance", new Supplier() { // from class: org.apache.poi.ddf.EscherRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(EscherRecord.this.getInstance());
            }
        }, "options", new Supplier() { // from class: org.apache.poi.ddf.EscherRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(EscherRecord.this.getOptions());
            }
        }, "recordSize", new Supplier() { // from class: org.apache.poi.ddf.EscherRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherRecord.this.getRecordSize());
            }
        });
    }

    public static void setMaxNumberOfChildren(int length) {
        MAX_NUMBER_OF_CHILDREN = length;
    }

    public static int getMaxNumberOfChildren() {
        return MAX_NUMBER_OF_CHILDREN;
    }
}
