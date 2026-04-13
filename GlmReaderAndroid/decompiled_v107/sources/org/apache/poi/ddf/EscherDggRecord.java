package org.apache.poi.ddf;

import com.zaxxer.sparsebits.SparseBitSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.ddf.EscherDggRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public final class EscherDggRecord extends EscherRecord {
    public static final short RECORD_ID = EscherRecordTypes.DGG.typeID;
    private int field_1_shapeIdMax;
    private int field_3_numShapesSaved;
    private int field_4_drawingsSaved;
    private final List<FileIdCluster> field_5_fileIdClusters;
    private int maxDgId;

    /* loaded from: classes10.dex */
    public static class FileIdCluster implements GenericRecord {
        private int field_1_drawingGroupId;
        private int field_2_numShapeIdsUsed;

        public FileIdCluster(FileIdCluster other) {
            this.field_1_drawingGroupId = other.field_1_drawingGroupId;
            this.field_2_numShapeIdsUsed = other.field_2_numShapeIdsUsed;
        }

        public FileIdCluster(int drawingGroupId, int numShapeIdsUsed) {
            this.field_1_drawingGroupId = drawingGroupId;
            this.field_2_numShapeIdsUsed = numShapeIdsUsed;
        }

        public int getDrawingGroupId() {
            return this.field_1_drawingGroupId;
        }

        public int getNumShapeIdsUsed() {
            return this.field_2_numShapeIdsUsed;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void incrementUsedShapeId() {
            this.field_2_numShapeIdsUsed++;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int compareFileIdCluster(FileIdCluster f1, FileIdCluster f2) {
            int dgDif = f1.getDrawingGroupId() - f2.getDrawingGroupId();
            int cntDif = f2.getNumShapeIdsUsed() - f1.getNumShapeIdsUsed();
            return dgDif != 0 ? dgDif : cntDif;
        }

        @Override // org.apache.poi.common.usermodel.GenericRecord
        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("drawingGroupId", new Supplier() { // from class: org.apache.poi.ddf.EscherDggRecord$FileIdCluster$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(EscherDggRecord.FileIdCluster.this.getDrawingGroupId());
                }
            }, "numShapeIdUsed", new Supplier() { // from class: org.apache.poi.ddf.EscherDggRecord$FileIdCluster$$ExternalSyntheticLambda1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(EscherDggRecord.FileIdCluster.this.getNumShapeIdsUsed());
                }
            });
        }
    }

    public EscherDggRecord() {
        this.field_5_fileIdClusters = new ArrayList();
    }

    public EscherDggRecord(EscherDggRecord other) {
        super(other);
        this.field_5_fileIdClusters = new ArrayList();
        this.field_1_shapeIdMax = other.field_1_shapeIdMax;
        this.field_3_numShapesSaved = other.field_3_numShapesSaved;
        this.field_4_drawingsSaved = other.field_4_drawingsSaved;
        Stream<R> map = other.field_5_fileIdClusters.stream().map(new Function() { // from class: org.apache.poi.ddf.EscherDggRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new EscherDggRecord.FileIdCluster((EscherDggRecord.FileIdCluster) obj);
            }
        });
        final List<FileIdCluster> list = this.field_5_fileIdClusters;
        list.getClass();
        map.forEach(new Consumer() { // from class: org.apache.poi.ddf.EscherDggRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                list.add((EscherDggRecord.FileIdCluster) obj);
            }
        });
        this.maxDgId = other.maxDgId;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesRemaining = readHeader(data, offset);
        int pos = offset + 8;
        this.field_1_shapeIdMax = LittleEndian.getInt(data, pos + 0);
        int size = 0 + 4 + 4;
        this.field_3_numShapesSaved = LittleEndian.getInt(data, pos + size);
        int size2 = size + 4;
        this.field_4_drawingsSaved = LittleEndian.getInt(data, pos + size2);
        int size3 = size2 + 4;
        this.field_5_fileIdClusters.clear();
        int numIdClusters = (bytesRemaining - size3) / 8;
        for (int i = 0; i < numIdClusters; i++) {
            int drawingGroupId = LittleEndian.getInt(data, pos + size3);
            int numShapeIdsUsed = LittleEndian.getInt(data, pos + size3 + 4);
            FileIdCluster fic = new FileIdCluster(drawingGroupId, numShapeIdsUsed);
            this.field_5_fileIdClusters.add(fic);
            this.maxDgId = Math.max(this.maxDgId, drawingGroupId);
            size3 += 8;
        }
        int bytesRemaining2 = bytesRemaining - size3;
        if (bytesRemaining2 != 0) {
            throw new RecordFormatException("Expecting no remaining data but got " + bytesRemaining2 + " byte(s).");
        }
        return size3 + 8;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, getRecordId(), this);
        LittleEndian.putShort(data, offset, getOptions());
        int pos = offset + 2;
        LittleEndian.putShort(data, pos, getRecordId());
        int pos2 = pos + 2;
        int remainingBytes = getRecordSize() - 8;
        LittleEndian.putInt(data, pos2, remainingBytes);
        int pos3 = pos2 + 4;
        LittleEndian.putInt(data, pos3, this.field_1_shapeIdMax);
        int pos4 = pos3 + 4;
        LittleEndian.putInt(data, pos4, getNumIdClusters());
        int pos5 = pos4 + 4;
        LittleEndian.putInt(data, pos5, this.field_3_numShapesSaved);
        int pos6 = pos5 + 4;
        LittleEndian.putInt(data, pos6, this.field_4_drawingsSaved);
        int pos7 = pos6 + 4;
        for (FileIdCluster fic : this.field_5_fileIdClusters) {
            LittleEndian.putInt(data, pos7, fic.getDrawingGroupId());
            int pos8 = pos7 + 4;
            LittleEndian.putInt(data, pos8, fic.getNumShapeIdsUsed());
            pos7 = pos8 + 4;
        }
        listener.afterRecordSerialize(pos7, getRecordId(), getRecordSize(), this);
        return getRecordSize();
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int getRecordSize() {
        return (this.field_5_fileIdClusters.size() * 8) + 24;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public short getRecordId() {
        return RECORD_ID;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public String getRecordName() {
        return EscherRecordTypes.DGG.recordName;
    }

    public int getShapeIdMax() {
        return this.field_1_shapeIdMax;
    }

    public void setShapeIdMax(int shapeIdMax) {
        this.field_1_shapeIdMax = shapeIdMax;
    }

    public int getNumIdClusters() {
        if (this.field_5_fileIdClusters.isEmpty()) {
            return 0;
        }
        return this.field_5_fileIdClusters.size() + 1;
    }

    public int getNumShapesSaved() {
        return this.field_3_numShapesSaved;
    }

    public void setNumShapesSaved(int numShapesSaved) {
        this.field_3_numShapesSaved = numShapesSaved;
    }

    public int getDrawingsSaved() {
        return this.field_4_drawingsSaved;
    }

    public void setDrawingsSaved(int drawingsSaved) {
        this.field_4_drawingsSaved = drawingsSaved;
    }

    public int getMaxDrawingGroupId() {
        return this.maxDgId;
    }

    public FileIdCluster[] getFileIdClusters() {
        return (FileIdCluster[]) this.field_5_fileIdClusters.toArray(new FileIdCluster[0]);
    }

    public void setFileIdClusters(FileIdCluster[] fileIdClusters) {
        this.field_5_fileIdClusters.clear();
        if (fileIdClusters != null) {
            this.field_5_fileIdClusters.addAll(Arrays.asList(fileIdClusters));
        }
    }

    public FileIdCluster addCluster(int dgId, int numShapedUsed) {
        return addCluster(dgId, numShapedUsed, true);
    }

    public FileIdCluster addCluster(int dgId, int numShapedUsed, boolean sort) {
        FileIdCluster ficNew = new FileIdCluster(dgId, numShapedUsed);
        this.field_5_fileIdClusters.add(ficNew);
        this.maxDgId = Math.min(this.maxDgId, dgId);
        if (sort) {
            sortCluster();
        }
        return ficNew;
    }

    private void sortCluster() {
        this.field_5_fileIdClusters.sort(new Comparator() { // from class: org.apache.poi.ddf.EscherDggRecord$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compareFileIdCluster;
                compareFileIdCluster = EscherDggRecord.FileIdCluster.compareFileIdCluster((EscherDggRecord.FileIdCluster) obj, (EscherDggRecord.FileIdCluster) obj2);
                return compareFileIdCluster;
            }
        });
    }

    public short findNewDrawingGroupId() {
        SparseBitSet bs = new SparseBitSet();
        bs.set(0);
        for (FileIdCluster fic : this.field_5_fileIdClusters) {
            bs.set(fic.getDrawingGroupId());
        }
        return (short) bs.nextClearBit(0);
    }

    public int allocateShapeId(EscherDgRecord dg, boolean sort) {
        short drawingGroupId = dg.getDrawingGroupId();
        this.field_3_numShapesSaved++;
        FileIdCluster ficAdd = null;
        int index = 1;
        Iterator<FileIdCluster> it = this.field_5_fileIdClusters.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            FileIdCluster fic = it.next();
            if (fic.getDrawingGroupId() == drawingGroupId && fic.getNumShapeIdsUsed() < 1024) {
                ficAdd = fic;
                break;
            }
            index++;
        }
        if (ficAdd == null) {
            ficAdd = addCluster(drawingGroupId, 0, sort);
            this.maxDgId = Math.max(this.maxDgId, (int) drawingGroupId);
        }
        int shapeId = (index * 1024) + ficAdd.getNumShapeIdsUsed();
        ficAdd.incrementUsedShapeId();
        dg.setNumShapes(dg.getNumShapes() + 1);
        dg.setLastMSOSPID(shapeId);
        this.field_1_shapeIdMax = Math.max(this.field_1_shapeIdMax, shapeId + 1);
        return shapeId;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Enum getGenericRecordType() {
        return EscherRecordTypes.DGG;
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ddf.EscherDggRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherDggRecord.this.m2241lambda$getGenericProperties$1$orgapachepoiddfEscherDggRecord();
            }
        }, "fileIdClusters", new Supplier() { // from class: org.apache.poi.ddf.EscherDggRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherDggRecord.this.m2242lambda$getGenericProperties$2$orgapachepoiddfEscherDggRecord();
            }
        }, "shapeIdMax", new Supplier() { // from class: org.apache.poi.ddf.EscherDggRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherDggRecord.this.getShapeIdMax());
            }
        }, "numIdClusters", new Supplier() { // from class: org.apache.poi.ddf.EscherDggRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherDggRecord.this.getNumIdClusters());
            }
        }, "numShapesSaved", new Supplier() { // from class: org.apache.poi.ddf.EscherDggRecord$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherDggRecord.this.getNumShapesSaved());
            }
        }, "drawingsSaved", new Supplier() { // from class: org.apache.poi.ddf.EscherDggRecord$$ExternalSyntheticLambda8
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherDggRecord.this.getDrawingsSaved());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-ddf-EscherDggRecord, reason: not valid java name */
    public /* synthetic */ Object m2241lambda$getGenericProperties$1$orgapachepoiddfEscherDggRecord() {
        return super.getGenericProperties();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-ddf-EscherDggRecord, reason: not valid java name */
    public /* synthetic */ Object m2242lambda$getGenericProperties$2$orgapachepoiddfEscherDggRecord() {
        return this.field_5_fileIdClusters;
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.Duplicatable
    public EscherDggRecord copy() {
        return new EscherDggRecord(this);
    }
}
