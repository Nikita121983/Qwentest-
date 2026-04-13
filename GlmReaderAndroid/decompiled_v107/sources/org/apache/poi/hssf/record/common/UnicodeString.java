package org.apache.poi.hssf.record.common;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.cont.ContinuableRecordInput;
import org.apache.poi.hssf.record.cont.ContinuableRecordOutput;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;

/* loaded from: classes10.dex */
public class UnicodeString implements Comparable<UnicodeString>, Duplicatable, GenericRecord {
    private short field_1_charCount;
    private byte field_2_optionflags;
    private String field_3_string;
    private List<FormatRun> field_4_format_runs;
    private ExtRst field_5_ext_rst;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) UnicodeString.class);
    private static final BitField highByte = BitFieldFactory.getInstance(1);
    private static final BitField extBit = BitFieldFactory.getInstance(4);
    private static final BitField richText = BitFieldFactory.getInstance(8);

    private UnicodeString(UnicodeString other) {
        this.field_1_charCount = other.field_1_charCount;
        this.field_2_optionflags = other.field_2_optionflags;
        this.field_3_string = other.field_3_string;
        this.field_4_format_runs = other.field_4_format_runs == null ? null : (List) other.field_4_format_runs.stream().map(new Function() { // from class: org.apache.poi.hssf.record.common.UnicodeString$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new FormatRun((FormatRun) obj);
            }
        }).collect(Collectors.toList());
        this.field_5_ext_rst = other.field_5_ext_rst != null ? other.field_5_ext_rst.copy() : null;
    }

    public UnicodeString(String str) {
        setString(str);
    }

    public UnicodeString(RecordInputStream in) {
        this.field_1_charCount = in.readShort();
        this.field_2_optionflags = in.readByte();
        int runCount = isRichText() ? in.readShort() : 0;
        int extensionLength = isExtendedText() ? in.readInt() : 0;
        boolean isCompressed = (this.field_2_optionflags & 1) == 0;
        int cc = getCharCount();
        this.field_3_string = isCompressed ? in.readCompressedUnicode(cc) : in.readUnicodeLEString(cc);
        if (isRichText() && runCount > 0) {
            this.field_4_format_runs = new ArrayList(runCount);
            for (int i = 0; i < runCount; i++) {
                this.field_4_format_runs.add(new FormatRun(in));
            }
        }
        if (isExtendedText() && extensionLength > 0) {
            this.field_5_ext_rst = new ExtRst(new ContinuableRecordInput(in), extensionLength);
            if (this.field_5_ext_rst.getDataSize() + 4 != extensionLength) {
                LOG.atWarn().log("ExtRst was supposed to be {} bytes long, but seems to actually be {}", Unbox.box(extensionLength), Unbox.box(this.field_5_ext_rst.getDataSize() + 4));
            }
        }
    }

    public int hashCode() {
        return Objects.hash(Short.valueOf(this.field_1_charCount), this.field_3_string);
    }

    public boolean equals(Object o) {
        int size;
        if (!(o instanceof UnicodeString)) {
            return false;
        }
        UnicodeString other = (UnicodeString) o;
        if (this.field_1_charCount != other.field_1_charCount || this.field_2_optionflags != other.field_2_optionflags || !this.field_3_string.equals(other.field_3_string)) {
            return false;
        }
        if (this.field_4_format_runs == null) {
            return other.field_4_format_runs == null;
        }
        if (other.field_4_format_runs == null || (size = this.field_4_format_runs.size()) != other.field_4_format_runs.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            FormatRun run1 = this.field_4_format_runs.get(i);
            FormatRun run2 = other.field_4_format_runs.get(i);
            if (!run1.equals(run2)) {
                return false;
            }
        }
        if (this.field_5_ext_rst == null) {
            return other.field_5_ext_rst == null;
        }
        if (other.field_5_ext_rst == null) {
            return false;
        }
        return this.field_5_ext_rst.equals(other.field_5_ext_rst);
    }

    public int getCharCount() {
        if (this.field_1_charCount < 0) {
            return this.field_1_charCount + 65536;
        }
        return this.field_1_charCount;
    }

    public short getCharCountShort() {
        return this.field_1_charCount;
    }

    public void setCharCount(short cc) {
        this.field_1_charCount = cc;
    }

    public byte getOptionFlags() {
        return this.field_2_optionflags;
    }

    public void setOptionFlags(byte of) {
        this.field_2_optionflags = of;
    }

    public String getString() {
        return this.field_3_string;
    }

    public void setString(String string) {
        this.field_3_string = string;
        setCharCount((short) this.field_3_string.length());
        boolean useUTF16 = false;
        int strlen = string.length();
        int j = 0;
        while (true) {
            if (j >= strlen) {
                break;
            }
            if (string.charAt(j) <= 255) {
                j++;
            } else {
                useUTF16 = true;
                break;
            }
        }
        if (useUTF16) {
            this.field_2_optionflags = highByte.setByte(this.field_2_optionflags);
        } else {
            this.field_2_optionflags = highByte.clearByte(this.field_2_optionflags);
        }
    }

    public int getFormatRunCount() {
        if (this.field_4_format_runs == null) {
            return 0;
        }
        return this.field_4_format_runs.size();
    }

    public FormatRun getFormatRun(int index) {
        if (this.field_4_format_runs != null && index >= 0 && index < this.field_4_format_runs.size()) {
            return this.field_4_format_runs.get(index);
        }
        return null;
    }

    private int findFormatRunAt(int characterPos) {
        int size = this.field_4_format_runs.size();
        for (int i = 0; i < size; i++) {
            FormatRun r = this.field_4_format_runs.get(i);
            if (r._character == characterPos) {
                return i;
            }
            if (r._character > characterPos) {
                return -1;
            }
        }
        return -1;
    }

    public void addFormatRun(FormatRun r) {
        if (this.field_4_format_runs == null) {
            this.field_4_format_runs = new ArrayList();
        }
        int index = findFormatRunAt(r._character);
        if (index != -1) {
            this.field_4_format_runs.remove(index);
        }
        this.field_4_format_runs.add(r);
        Collections.sort(this.field_4_format_runs);
        this.field_2_optionflags = richText.setByte(this.field_2_optionflags);
    }

    public Iterator<FormatRun> formatIterator() {
        if (this.field_4_format_runs != null) {
            return this.field_4_format_runs.iterator();
        }
        return null;
    }

    public Spliterator<FormatRun> formatSpliterator() {
        if (this.field_4_format_runs != null) {
            return this.field_4_format_runs.spliterator();
        }
        return null;
    }

    public void removeFormatRun(FormatRun r) {
        this.field_4_format_runs.remove(r);
        if (this.field_4_format_runs.isEmpty()) {
            this.field_4_format_runs = null;
            this.field_2_optionflags = richText.clearByte(this.field_2_optionflags);
        }
    }

    public void clearFormatting() {
        this.field_4_format_runs = null;
        this.field_2_optionflags = richText.clearByte(this.field_2_optionflags);
    }

    public ExtRst getExtendedRst() {
        return this.field_5_ext_rst;
    }

    void setExtendedRst(ExtRst ext_rst) {
        if (ext_rst != null) {
            this.field_2_optionflags = extBit.setByte(this.field_2_optionflags);
        } else {
            this.field_2_optionflags = extBit.clearByte(this.field_2_optionflags);
        }
        this.field_5_ext_rst = ext_rst;
    }

    public void swapFontUse(short oldFontIndex, short newFontIndex) {
        if (this.field_4_format_runs != null) {
            for (FormatRun run : this.field_4_format_runs) {
                if (run._fontIndex == oldFontIndex) {
                    run._fontIndex = newFontIndex;
                }
            }
        }
    }

    public String toString() {
        return getString();
    }

    public String getDebugInfo() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[UNICODESTRING]\n");
        buffer.append("    .charcount       = ").append(Integer.toHexString(getCharCount())).append(StringUtils.LF);
        buffer.append("    .optionflags     = ").append(Integer.toHexString(getOptionFlags())).append(StringUtils.LF);
        buffer.append("    .string          = ").append(getString()).append(StringUtils.LF);
        if (this.field_4_format_runs != null) {
            for (int i = 0; i < this.field_4_format_runs.size(); i++) {
                FormatRun r = this.field_4_format_runs.get(i);
                buffer.append("      .format_run").append(i).append("          = ").append(r).append(StringUtils.LF);
            }
        }
        if (this.field_5_ext_rst != null) {
            buffer.append("    .field_5_ext_rst          = ").append(StringUtils.LF);
            buffer.append(this.field_5_ext_rst).append(StringUtils.LF);
        }
        buffer.append("[/UNICODESTRING]\n");
        return buffer.toString();
    }

    public void serialize(ContinuableRecordOutput out) {
        int numberOfRichTextRuns = 0;
        int extendedDataSize = 0;
        if (isRichText() && this.field_4_format_runs != null) {
            numberOfRichTextRuns = this.field_4_format_runs.size();
        }
        if (isExtendedText() && this.field_5_ext_rst != null) {
            extendedDataSize = this.field_5_ext_rst.getDataSize() + 4;
        }
        out.writeString(this.field_3_string, numberOfRichTextRuns, extendedDataSize);
        if (numberOfRichTextRuns > 0) {
            for (int i = 0; i < numberOfRichTextRuns; i++) {
                if (out.getAvailableSpace() < 4) {
                    out.writeContinue();
                }
                FormatRun r = this.field_4_format_runs.get(i);
                r.serialize(out);
            }
        }
        if (extendedDataSize > 0 && this.field_5_ext_rst != null) {
            this.field_5_ext_rst.serialize(out);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(UnicodeString str) {
        int result = getString().compareTo(str.getString());
        if (result != 0) {
            return result;
        }
        if (this.field_4_format_runs == null) {
            return str.field_4_format_runs == null ? 0 : 1;
        }
        if (str.field_4_format_runs == null) {
            return -1;
        }
        int size = this.field_4_format_runs.size();
        if (size != str.field_4_format_runs.size()) {
            return size - str.field_4_format_runs.size();
        }
        for (int i = 0; i < size; i++) {
            FormatRun run1 = this.field_4_format_runs.get(i);
            FormatRun run2 = str.field_4_format_runs.get(i);
            int result2 = run1.compareTo(run2);
            if (result2 != 0) {
                return result2;
            }
        }
        if (this.field_5_ext_rst == null) {
            return str.field_5_ext_rst == null ? 0 : 1;
        }
        if (str.field_5_ext_rst == null) {
            return -1;
        }
        return this.field_5_ext_rst.compareTo(str.field_5_ext_rst);
    }

    private boolean isRichText() {
        return richText.isSet(getOptionFlags());
    }

    private boolean isExtendedText() {
        return extBit.isSet(getOptionFlags());
    }

    @Override // org.apache.poi.common.Duplicatable
    public UnicodeString copy() {
        return new UnicodeString(this);
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("charCount", new Supplier() { // from class: org.apache.poi.hssf.record.common.UnicodeString$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(UnicodeString.this.getCharCount());
            }
        }, "optionFlags", new Supplier() { // from class: org.apache.poi.hssf.record.common.UnicodeString$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(UnicodeString.this.getOptionFlags());
            }
        }, TypedValues.Custom.S_STRING, new Supplier() { // from class: org.apache.poi.hssf.record.common.UnicodeString$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return UnicodeString.this.getString();
            }
        }, "formatRuns", new Supplier() { // from class: org.apache.poi.hssf.record.common.UnicodeString$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return UnicodeString.this.m2448xbeb80b0b();
            }
        }, "extendedRst", new Supplier() { // from class: org.apache.poi.hssf.record.common.UnicodeString$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return UnicodeString.this.getExtendedRst();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-common-UnicodeString, reason: not valid java name */
    public /* synthetic */ Object m2448xbeb80b0b() {
        return this.field_4_format_runs;
    }
}
