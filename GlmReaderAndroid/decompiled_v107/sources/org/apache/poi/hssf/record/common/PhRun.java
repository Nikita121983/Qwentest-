package org.apache.poi.hssf.record.common;

import java.util.Objects;
import org.apache.poi.hssf.record.cont.ContinuableRecordOutput;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianInput;

@Internal
/* loaded from: classes10.dex */
public class PhRun {
    final int phoneticTextFirstCharacterOffset;
    final int realTextFirstCharacterOffset;
    final int realTextLength;

    public PhRun(PhRun other) {
        this.phoneticTextFirstCharacterOffset = other.phoneticTextFirstCharacterOffset;
        this.realTextFirstCharacterOffset = other.realTextFirstCharacterOffset;
        this.realTextLength = other.realTextLength;
    }

    public PhRun(int phoneticTextFirstCharacterOffset, int realTextFirstCharacterOffset, int realTextLength) {
        this.phoneticTextFirstCharacterOffset = phoneticTextFirstCharacterOffset;
        this.realTextFirstCharacterOffset = realTextFirstCharacterOffset;
        this.realTextLength = realTextLength;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PhRun(LittleEndianInput in) {
        this.phoneticTextFirstCharacterOffset = in.readUShort();
        this.realTextFirstCharacterOffset = in.readUShort();
        this.realTextLength = in.readUShort();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void serialize(ContinuableRecordOutput out) {
        out.writeContinueIfRequired(6);
        out.writeShort(this.phoneticTextFirstCharacterOffset);
        out.writeShort(this.realTextFirstCharacterOffset);
        out.writeShort(this.realTextLength);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.phoneticTextFirstCharacterOffset), Integer.valueOf(this.realTextFirstCharacterOffset), Integer.valueOf(this.realTextLength));
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PhRun phRun = (PhRun) o;
        if (this.phoneticTextFirstCharacterOffset == phRun.phoneticTextFirstCharacterOffset && this.realTextFirstCharacterOffset == phRun.realTextFirstCharacterOffset && this.realTextLength == phRun.realTextLength) {
            return true;
        }
        return false;
    }
}
