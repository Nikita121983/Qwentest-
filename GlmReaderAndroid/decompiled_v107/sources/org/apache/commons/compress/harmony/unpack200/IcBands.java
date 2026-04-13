package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassConstantPool;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry;

/* loaded from: classes9.dex */
public class IcBands extends BandSet {
    private final String[] cpClass;
    private final String[] cpUTF8;
    private IcTuple[] icAll;
    private Map<String, List<IcTuple>> outerClassToTuples;
    private Map<String, IcTuple> thisClassToTuple;

    public IcBands(Segment segment) {
        super(segment);
        this.cpClass = segment.getCpBands().getCpClass();
        this.cpUTF8 = segment.getCpBands().getCpUTF8();
    }

    public IcTuple[] getIcTuples() {
        return this.icAll;
    }

    public IcTuple[] getRelevantIcTuples(String className, ClassConstantPool cp) {
        Set<IcTuple> relevantTuplesContains = new HashSet<>();
        List<IcTuple> relevantTuples = new ArrayList<>();
        List<IcTuple> relevantCandidates = this.outerClassToTuples.get(className);
        if (relevantCandidates != null) {
            for (int index = 0; index < relevantCandidates.size(); index++) {
                IcTuple tuple = relevantCandidates.get(index);
                relevantTuplesContains.add(tuple);
                relevantTuples.add(tuple);
            }
        }
        List<ClassFileEntry> entries = cp.entries();
        for (int eIndex = 0; eIndex < entries.size(); eIndex++) {
            ConstantPoolEntry entry = (ConstantPoolEntry) entries.get(eIndex);
            if (entry instanceof CPClass) {
                CPClass clazz = (CPClass) entry;
                IcTuple relevant = this.thisClassToTuple.get(clazz.name);
                if (relevant != null && relevantTuplesContains.add(relevant)) {
                    relevantTuples.add(relevant);
                }
            }
        }
        List<IcTuple> tuplesToScan = new ArrayList<>(relevantTuples);
        List<IcTuple> tuplesToAdd = new ArrayList<>();
        while (tuplesToScan.size() > 0) {
            tuplesToAdd.clear();
            for (int index2 = 0; index2 < tuplesToScan.size(); index2++) {
                IcTuple aRelevantTuple = tuplesToScan.get(index2);
                IcTuple relevant2 = this.thisClassToTuple.get(aRelevantTuple.outerClassString());
                if (relevant2 != null && !aRelevantTuple.outerIsAnonymous()) {
                    tuplesToAdd.add(relevant2);
                }
            }
            tuplesToScan.clear();
            for (int index3 = 0; index3 < tuplesToAdd.size(); index3++) {
                IcTuple tuple2 = tuplesToAdd.get(index3);
                if (relevantTuplesContains.add(tuple2)) {
                    relevantTuples.add(tuple2);
                    tuplesToScan.add(tuple2);
                }
            }
        }
        relevantTuples.sort(new Comparator() { // from class: org.apache.commons.compress.harmony.unpack200.IcBands$$ExternalSyntheticLambda1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return IcBands.lambda$getRelevantIcTuples$0((IcTuple) obj, (IcTuple) obj2);
            }
        });
        return (IcTuple[]) relevantTuples.toArray(IcTuple.EMPTY_ARRAY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$getRelevantIcTuples$0(IcTuple arg0, IcTuple arg1) {
        int index1 = arg0.getTupleIndex();
        Integer index2 = Integer.valueOf(arg1.getTupleIndex());
        return Integer.compare(index1, index2.intValue());
    }

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void read(InputStream in) throws IOException, Pack200Exception {
        int innerClassCount = this.header.getInnerClassCount();
        int[] icThisClassInts = decodeBandInt("ic_this_class", in, Codec.UDELTA5, innerClassCount);
        String[] icThisClass = getReferences(icThisClassInts, this.cpClass);
        int[] icFlags = decodeBandInt("ic_flags", in, Codec.UNSIGNED5, innerClassCount);
        int outerClasses = SegmentUtils.countBit16(icFlags);
        int[] icOuterClassInts = decodeBandInt("ic_outer_class", in, Codec.DELTA5, outerClasses);
        String[] icOuterClass = new String[outerClasses];
        for (int i = 0; i < icOuterClass.length; i++) {
            if (icOuterClassInts[i] == 0) {
                icOuterClass[i] = null;
            } else {
                icOuterClass[i] = this.cpClass[icOuterClassInts[i] - 1];
            }
        }
        int[] icNameInts = decodeBandInt("ic_name", in, Codec.DELTA5, outerClasses);
        String[] icName = new String[outerClasses];
        for (int i2 = 0; i2 < icName.length; i2++) {
            if (icNameInts[i2] == 0) {
                icName[i2] = null;
            } else {
                icName[i2] = this.cpUTF8[icNameInts[i2] - 1];
            }
        }
        this.icAll = new IcTuple[icThisClass.length];
        int index = 0;
        int i3 = 0;
        while (i3 < icThisClass.length) {
            String icTupleC = icThisClass[i3];
            int icTupleF = icFlags[i3];
            String icTupleC2 = null;
            String icTupleN = null;
            int cIndex = icThisClassInts[i3];
            int c2Index = -1;
            int nIndex = -1;
            if ((icFlags[i3] & 65536) != 0) {
                icTupleC2 = icOuterClass[index];
                icTupleN = icName[index];
                c2Index = icOuterClassInts[index] - 1;
                nIndex = icNameInts[index] - 1;
                index++;
            }
            int i4 = i3;
            this.icAll[i4] = new IcTuple(icTupleC, icTupleF, icTupleC2, icTupleN, cIndex, c2Index, nIndex, i4);
            i3 = i4 + 1;
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void unpack() throws IOException, Pack200Exception {
        IcTuple[] allTuples = getIcTuples();
        this.thisClassToTuple = new HashMap(allTuples.length);
        this.outerClassToTuples = new HashMap(allTuples.length);
        for (IcTuple tuple : allTuples) {
            Object result = this.thisClassToTuple.put(tuple.thisClassString(), tuple);
            if (result != null) {
                throw new Error("Collision detected in <thisClassString, IcTuple> mapping. There are at least two inner clases with the same name.");
            }
            if ((!tuple.isAnonymous() && !tuple.outerIsAnonymous()) || tuple.nestedExplicitFlagSet()) {
                String key = tuple.outerClassString();
                this.outerClassToTuples.computeIfAbsent(key, new Function() { // from class: org.apache.commons.compress.harmony.unpack200.IcBands$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return IcBands.lambda$unpack$1((String) obj);
                    }
                }).add(tuple);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$unpack$1(String k) {
        return new ArrayList();
    }
}
