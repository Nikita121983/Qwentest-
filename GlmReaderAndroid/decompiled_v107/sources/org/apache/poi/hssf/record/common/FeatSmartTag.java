package org.apache.poi.hssf.record.common;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class FeatSmartTag implements SharedFeature {
    private byte[] data;

    public FeatSmartTag() {
        this.data = new byte[0];
    }

    public FeatSmartTag(FeatSmartTag other) {
        this.data = other.data == null ? null : (byte[]) other.data.clone();
    }

    public FeatSmartTag(RecordInputStream in) {
        this.data = in.readRemainder();
    }

    @Override // org.apache.poi.hssf.record.common.SharedFeature
    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    @Override // org.apache.poi.hssf.record.common.SharedFeature
    public int getDataSize() {
        return this.data.length;
    }

    @Override // org.apache.poi.hssf.record.common.SharedFeature
    public void serialize(LittleEndianOutput out) {
        out.write(this.data);
    }

    @Override // org.apache.poi.hssf.record.common.SharedFeature
    public FeatSmartTag copy() {
        return new FeatSmartTag(this);
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("data", new Supplier() { // from class: org.apache.poi.hssf.record.common.FeatSmartTag$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return FeatSmartTag.this.m2447x93af1f06();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-common-FeatSmartTag, reason: not valid java name */
    public /* synthetic */ Object m2447x93af1f06() {
        return this.data;
    }
}
