package org.apache.poi.ddf;

/* loaded from: classes10.dex */
public class EscherTertiaryOptRecord extends AbstractEscherOptRecord {
    public static final short RECORD_ID = EscherRecordTypes.USER_DEFINED.typeID;

    public EscherTertiaryOptRecord() {
    }

    public EscherTertiaryOptRecord(EscherTertiaryOptRecord other) {
        super(other);
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public String getRecordName() {
        return EscherRecordTypes.USER_DEFINED.recordName;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Enum getGenericRecordType() {
        return EscherRecordTypes.USER_DEFINED;
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.Duplicatable
    public EscherTertiaryOptRecord copy() {
        return new EscherTertiaryOptRecord(this);
    }
}
