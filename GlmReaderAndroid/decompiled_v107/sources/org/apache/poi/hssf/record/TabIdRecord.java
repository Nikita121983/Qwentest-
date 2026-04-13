package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class TabIdRecord extends StandardRecord {
    private static final short[] EMPTY_SHORT_ARRAY = new short[0];
    public static final short sid = 317;
    private short[] _tabids;

    public TabIdRecord() {
        this._tabids = EMPTY_SHORT_ARRAY;
    }

    public TabIdRecord(TabIdRecord other) {
        super(other);
        this._tabids = other._tabids == null ? null : (short[]) other._tabids.clone();
    }

    public TabIdRecord(RecordInputStream in) {
        int nTabs = in.remaining() / 2;
        this._tabids = new short[nTabs];
        for (int i = 0; i < this._tabids.length; i++) {
            this._tabids[i] = in.readShort();
        }
    }

    public void setTabIdArray(short[] array) {
        this._tabids = (short[]) array.clone();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        for (short tabid : this._tabids) {
            out.writeShort(tabid);
        }
    }

    public int getTabIdSize() {
        return this._tabids.length;
    }

    public short getTabIdAt(int index) {
        return this._tabids[index];
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return this._tabids.length * 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public TabIdRecord copy() {
        return new TabIdRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.TAB_ID;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("elements", new Supplier() { // from class: org.apache.poi.hssf.record.TabIdRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return TabIdRecord.this.m2378xfd71eec2();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-TabIdRecord, reason: not valid java name */
    public /* synthetic */ Object m2378xfd71eec2() {
        return this._tabids;
    }
}
