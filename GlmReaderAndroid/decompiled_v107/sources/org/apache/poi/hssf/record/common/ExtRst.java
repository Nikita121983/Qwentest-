package org.apache.poi.hssf.record.common;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.cont.ContinuableRecordOutput;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.StringUtil;

@Internal
/* loaded from: classes10.dex */
public class ExtRst implements Comparable<ExtRst>, GenericRecord {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) ExtRst.class);
    private byte[] extraData;
    private short formattingFontIndex;
    private short formattingOptions;
    private int numberOfRuns;
    private PhRun[] phRuns;
    private String phoneticText;
    private short reserved;

    protected ExtRst() {
        populateEmpty();
    }

    protected ExtRst(ExtRst other) {
        this();
        this.reserved = other.reserved;
        this.formattingFontIndex = other.formattingFontIndex;
        this.formattingOptions = other.formattingOptions;
        this.numberOfRuns = other.numberOfRuns;
        this.phoneticText = other.phoneticText;
        this.phRuns = other.phRuns == null ? null : (PhRun[]) Stream.of((Object[]) other.phRuns).map(new Function() { // from class: org.apache.poi.hssf.record.common.ExtRst$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new PhRun((PhRun) obj);
            }
        }).toArray(new IntFunction() { // from class: org.apache.poi.hssf.record.common.ExtRst$$ExternalSyntheticLambda1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return ExtRst.lambda$new$0(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ PhRun[] lambda$new$0(int x$0) {
        return new PhRun[x$0];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ExtRst(LittleEndianInput in, int expectedLength) {
        this.reserved = in.readShort();
        if (this.reserved == -1) {
            populateEmpty();
            return;
        }
        if (this.reserved != 1) {
            LOG.atWarn().log("ExtRst has wrong magic marker, expecting 1 but found {} - ignoring", Unbox.box(this.reserved));
            for (int i = 0; i < expectedLength - 2; i++) {
                in.readByte();
            }
            populateEmpty();
            return;
        }
        short stringDataSize = in.readShort();
        this.formattingFontIndex = in.readShort();
        this.formattingOptions = in.readShort();
        this.numberOfRuns = in.readUShort();
        short length1 = in.readShort();
        short length2 = in.readShort();
        if (length1 == 0 && length2 > 0) {
            length2 = 0;
        }
        if (length1 != length2) {
            throw new IllegalStateException("The two length fields of the Phonetic Text don't agree! " + ((int) length1) + " vs " + ((int) length2));
        }
        this.phoneticText = StringUtil.readUnicodeLE(in, length1);
        int runData = ((stringDataSize - 4) - 6) - (this.phoneticText.length() * 2);
        int numRuns = runData / 6;
        this.phRuns = new PhRun[numRuns];
        for (int i2 = 0; i2 < this.phRuns.length; i2++) {
            this.phRuns[i2] = new PhRun(in);
        }
        int i3 = numRuns * 6;
        int extraDataLength = runData - i3;
        if (extraDataLength < 0) {
            LOG.atWarn().log("ExtRst overran by {} bytes", Unbox.box(-extraDataLength));
            extraDataLength = 0;
        }
        this.extraData = IOUtils.safelyAllocate(extraDataLength, HSSFWorkbook.getMaxRecordLength());
        for (int i4 = 0; i4 < this.extraData.length; i4++) {
            this.extraData[i4] = in.readByte();
        }
    }

    private void populateEmpty() {
        this.reserved = (short) 1;
        this.phoneticText = "";
        this.phRuns = new PhRun[0];
        this.extraData = new byte[0];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getDataSize() {
        return (this.phoneticText.length() * 2) + 10 + (this.phRuns.length * 6) + this.extraData.length;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void serialize(ContinuableRecordOutput out) {
        int dataSize = getDataSize();
        out.writeContinueIfRequired(8);
        out.writeShort(this.reserved);
        out.writeShort(dataSize);
        out.writeShort(this.formattingFontIndex);
        out.writeShort(this.formattingOptions);
        out.writeContinueIfRequired(6);
        out.writeShort(this.numberOfRuns);
        out.writeShort(this.phoneticText.length());
        out.writeShort(this.phoneticText.length());
        out.writeContinueIfRequired(this.phoneticText.length() * 2);
        StringUtil.putUnicodeLE(this.phoneticText, out);
        for (PhRun phRun : this.phRuns) {
            phRun.serialize(out);
        }
        out.write(this.extraData);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ExtRst)) {
            return false;
        }
        ExtRst other = (ExtRst) obj;
        return compareTo(other) == 0;
    }

    @Override // java.lang.Comparable
    public int compareTo(ExtRst o) {
        int result = this.reserved - o.reserved;
        if (result != 0) {
            return result;
        }
        int result2 = this.formattingFontIndex - o.formattingFontIndex;
        if (result2 != 0) {
            return result2;
        }
        int result3 = this.formattingOptions - o.formattingOptions;
        if (result3 != 0) {
            return result3;
        }
        int result4 = this.numberOfRuns - o.numberOfRuns;
        if (result4 != 0) {
            return result4;
        }
        int result5 = this.phoneticText.compareTo(o.phoneticText);
        if (result5 != 0) {
            return result5;
        }
        int result6 = this.phRuns.length - o.phRuns.length;
        if (result6 != 0) {
            return result6;
        }
        for (int i = 0; i < this.phRuns.length; i++) {
            int result7 = this.phRuns[i].phoneticTextFirstCharacterOffset - o.phRuns[i].phoneticTextFirstCharacterOffset;
            if (result7 != 0) {
                return result7;
            }
            int result8 = this.phRuns[i].realTextFirstCharacterOffset - o.phRuns[i].realTextFirstCharacterOffset;
            if (result8 != 0) {
                return result8;
            }
            int result9 = this.phRuns[i].realTextLength - o.phRuns[i].realTextLength;
            if (result9 != 0) {
                return result9;
            }
        }
        return Arrays.hashCode(this.extraData) - Arrays.hashCode(o.extraData);
    }

    public int hashCode() {
        return Arrays.deepHashCode(new Object[]{Short.valueOf(this.reserved), Short.valueOf(this.formattingFontIndex), Short.valueOf(this.formattingOptions), Integer.valueOf(this.numberOfRuns), this.phoneticText, this.phRuns});
    }

    public ExtRst copy() {
        return new ExtRst(this);
    }

    public short getFormattingFontIndex() {
        return this.formattingFontIndex;
    }

    public short getFormattingOptions() {
        return this.formattingOptions;
    }

    public int getNumberOfRuns() {
        return this.numberOfRuns;
    }

    public String getPhoneticText() {
        return this.phoneticText;
    }

    public PhRun[] getPhRuns() {
        return this.phRuns;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved", new Supplier() { // from class: org.apache.poi.hssf.record.common.ExtRst$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExtRst.this.m2444x8c255a74();
            }
        }, "formattingFontIndex", new Supplier() { // from class: org.apache.poi.hssf.record.common.ExtRst$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtRst.this.getFormattingFontIndex());
            }
        }, "formattingOptions", new Supplier() { // from class: org.apache.poi.hssf.record.common.ExtRst$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtRst.this.getFormattingOptions());
            }
        }, "numberOfRuns", new Supplier() { // from class: org.apache.poi.hssf.record.common.ExtRst$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ExtRst.this.getNumberOfRuns());
            }
        }, "phoneticText", new Supplier() { // from class: org.apache.poi.hssf.record.common.ExtRst$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExtRst.this.getPhoneticText();
            }
        }, "phRuns", new Supplier() { // from class: org.apache.poi.hssf.record.common.ExtRst$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExtRst.this.getPhRuns();
            }
        }, "extraData", new Supplier() { // from class: org.apache.poi.hssf.record.common.ExtRst$$ExternalSyntheticLambda8
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExtRst.this.m2445x938a8f93();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-common-ExtRst, reason: not valid java name */
    public /* synthetic */ Object m2444x8c255a74() {
        return Short.valueOf(this.reserved);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-common-ExtRst, reason: not valid java name */
    public /* synthetic */ Object m2445x938a8f93() {
        return this.extraData;
    }
}
