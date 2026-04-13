package org.apache.poi.hssf.record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.PageBreakRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public abstract class PageBreakRecord extends StandardRecord {
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private final ArrayList<Break> _breaks = new ArrayList<>();
    private final Map<Integer, Break> _breakMap = new HashMap();

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public abstract PageBreakRecord copy();

    /* loaded from: classes10.dex */
    public static final class Break implements GenericRecord {
        public static final int ENCODED_SIZE = 6;
        private int main;
        private int subFrom;
        private int subTo;

        public Break(Break other) {
            this.main = other.main;
            this.subFrom = other.subFrom;
            this.subTo = other.subTo;
        }

        public Break(int main, int subFrom, int subTo) {
            this.main = main;
            this.subFrom = subFrom;
            this.subTo = subTo;
        }

        public Break(RecordInputStream in) {
            this.main = in.readUShort() - 1;
            this.subFrom = in.readUShort();
            this.subTo = in.readUShort();
        }

        public int getMain() {
            return this.main;
        }

        public int getSubFrom() {
            return this.subFrom;
        }

        public int getSubTo() {
            return this.subTo;
        }

        public void serialize(LittleEndianOutput out) {
            out.writeShort(this.main + 1);
            out.writeShort(this.subFrom);
            out.writeShort(this.subTo);
        }

        @Override // org.apache.poi.common.usermodel.GenericRecord
        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("main", new Supplier() { // from class: org.apache.poi.hssf.record.PageBreakRecord$Break$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return PageBreakRecord.Break.this.m2352xf5b72c1d();
                }
            }, "subFrom", new Supplier() { // from class: org.apache.poi.hssf.record.PageBreakRecord$Break$$ExternalSyntheticLambda1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return PageBreakRecord.Break.this.m2353xe760d23c();
                }
            }, "subTo", new Supplier() { // from class: org.apache.poi.hssf.record.PageBreakRecord$Break$$ExternalSyntheticLambda2
                @Override // java.util.function.Supplier
                public final Object get() {
                    return PageBreakRecord.Break.this.m2354xd90a785b();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-PageBreakRecord$Break, reason: not valid java name */
        public /* synthetic */ Object m2352xf5b72c1d() {
            return Integer.valueOf(this.main);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-PageBreakRecord$Break, reason: not valid java name */
        public /* synthetic */ Object m2353xe760d23c() {
            return Integer.valueOf(this.subFrom);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-PageBreakRecord$Break, reason: not valid java name */
        public /* synthetic */ Object m2354xd90a785b() {
            return Integer.valueOf(this.subTo);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PageBreakRecord() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PageBreakRecord(PageBreakRecord other) {
        this._breaks.addAll(other._breaks);
        initMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PageBreakRecord(RecordInputStream in) {
        int nBreaks = in.readShort();
        this._breaks.ensureCapacity(nBreaks + 2);
        for (int k = 0; k < nBreaks; k++) {
            this._breaks.add(new Break(in));
        }
        initMap();
    }

    private void initMap() {
        this._breaks.forEach(new Consumer() { // from class: org.apache.poi.hssf.record.PageBreakRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                PageBreakRecord.this.m2351lambda$initMap$0$orgapachepoihssfrecordPageBreakRecord((PageBreakRecord.Break) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$initMap$0$org-apache-poi-hssf-record-PageBreakRecord, reason: not valid java name */
    public /* synthetic */ void m2351lambda$initMap$0$orgapachepoihssfrecordPageBreakRecord(Break br) {
        this._breakMap.put(Integer.valueOf(br.main), br);
    }

    public boolean isEmpty() {
        return this._breaks.isEmpty();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (this._breaks.size() * 6) + 2;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public final void serialize(LittleEndianOutput out) {
        int nBreaks = this._breaks.size();
        out.writeShort(nBreaks);
        Iterator<Break> it = this._breaks.iterator();
        while (it.hasNext()) {
            Break aBreak = it.next();
            aBreak.serialize(out);
        }
    }

    public int getNumBreaks() {
        return this._breaks.size();
    }

    public final Iterator<Break> getBreaksIterator() {
        return this._breaks.iterator();
    }

    public final Spliterator<Break> getBreaksSpliterator() {
        return this._breaks.spliterator();
    }

    public void addBreak(int main, int subFrom, int subTo) {
        Integer key = Integer.valueOf(main);
        Break region = this._breakMap.get(key);
        if (region != null) {
            region.main = main;
            region.subFrom = subFrom;
            region.subTo = subTo;
        } else {
            Break region2 = new Break(main, subFrom, subTo);
            this._breakMap.put(key, region2);
            this._breaks.add(region2);
        }
    }

    public final void removeBreak(int main) {
        Integer rowKey = Integer.valueOf(main);
        Break region = this._breakMap.get(rowKey);
        this._breaks.remove(region);
        this._breakMap.remove(rowKey);
    }

    public final Break getBreak(int main) {
        Integer rowKey = Integer.valueOf(main);
        return this._breakMap.get(rowKey);
    }

    public final int[] getBreaks() {
        int count = getNumBreaks();
        if (count < 1) {
            return EMPTY_INT_ARRAY;
        }
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            Break breakItem = this._breaks.get(i);
            result[i] = breakItem.main;
        }
        return result;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("numBreaks", new Supplier() { // from class: org.apache.poi.hssf.record.PageBreakRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(PageBreakRecord.this.getNumBreaks());
            }
        }, "breaks", new Supplier() { // from class: org.apache.poi.hssf.record.PageBreakRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return PageBreakRecord.this.m2350xc60987c1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-PageBreakRecord, reason: not valid java name */
    public /* synthetic */ Object m2350xc60987c1() {
        return this._breaks;
    }
}
