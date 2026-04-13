package org.apache.poi.hssf.record.common;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class FeatProtection implements SharedFeature {
    public static final long HAS_SELF_RELATIVE_SECURITY_FEATURE = 1;
    public static final long NO_SELF_RELATIVE_SECURITY_FEATURE = 0;
    private int fSD;
    private int passwordVerifier;
    private byte[] securityDescriptor;
    private String title;

    public FeatProtection() {
        this.securityDescriptor = new byte[0];
    }

    public FeatProtection(FeatProtection other) {
        this.fSD = other.fSD;
        this.passwordVerifier = other.passwordVerifier;
        this.title = other.title;
        this.securityDescriptor = other.securityDescriptor == null ? null : (byte[]) other.securityDescriptor.clone();
    }

    public FeatProtection(RecordInputStream in) {
        this.fSD = in.readInt();
        this.passwordVerifier = in.readInt();
        this.title = StringUtil.readUnicodeString(in);
        this.securityDescriptor = in.readRemainder();
    }

    @Override // org.apache.poi.hssf.record.common.SharedFeature
    public void serialize(LittleEndianOutput out) {
        out.writeInt(this.fSD);
        out.writeInt(this.passwordVerifier);
        StringUtil.writeUnicodeString(out, this.title);
        out.write(this.securityDescriptor);
    }

    @Override // org.apache.poi.hssf.record.common.SharedFeature
    public int getDataSize() {
        return StringUtil.getEncodedSize(this.title) + 8 + this.securityDescriptor.length;
    }

    public int getPasswordVerifier() {
        return this.passwordVerifier;
    }

    public void setPasswordVerifier(int passwordVerifier) {
        this.passwordVerifier = passwordVerifier;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFSD() {
        return this.fSD;
    }

    @Override // org.apache.poi.hssf.record.common.SharedFeature
    public FeatProtection copy() {
        return new FeatProtection(this);
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("FSD", new Supplier() { // from class: org.apache.poi.hssf.record.common.FeatProtection$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(FeatProtection.this.getFSD());
            }
        }, "passwordVerifier", new Supplier() { // from class: org.apache.poi.hssf.record.common.FeatProtection$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(FeatProtection.this.getPasswordVerifier());
            }
        }, "title", new Supplier() { // from class: org.apache.poi.hssf.record.common.FeatProtection$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return FeatProtection.this.getTitle();
            }
        }, "securityDescriptor", new Supplier() { // from class: org.apache.poi.hssf.record.common.FeatProtection$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return FeatProtection.this.m2446x6c3d9f8e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-common-FeatProtection, reason: not valid java name */
    public /* synthetic */ Object m2446x6c3d9f8e() {
        return this.securityDescriptor;
    }
}
