package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.collections4.CollectionUtils;

/* loaded from: classes9.dex */
public class IcBands extends BandSet {
    private int bit16Count;
    private final CpBands cpBands;
    private final Set<IcTuple> innerClasses;
    private final Map<String, List<IcTuple>> outerToInner;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class IcTuple implements Comparable<IcTuple> {
        protected CPClass C;
        protected CPClass C2;
        protected int F;
        protected CPUTF8 N;

        IcTuple(CPClass C, int F, CPClass C2, CPUTF8 N) {
            this.C = C;
            this.F = F;
            this.C2 = C2;
            this.N = N;
        }

        @Override // java.lang.Comparable
        public int compareTo(IcTuple arg0) {
            return this.C.compareTo(arg0.C);
        }

        public boolean equals(Object o) {
            if (!(o instanceof IcTuple)) {
                return false;
            }
            IcTuple icT = (IcTuple) o;
            return this.C.equals(icT.C) && this.F == icT.F && Objects.equals(this.C2, icT.C2) && Objects.equals(this.N, icT.N);
        }

        public boolean isAnonymous() {
            String className = this.C.toString();
            String innerName = className.substring(className.lastIndexOf(36) + 1);
            return Character.isDigit(innerName.charAt(0));
        }

        public String toString() {
            return this.C.toString();
        }
    }

    public IcBands(SegmentHeader segmentHeader, CpBands cpBands, int effort) {
        super(effort, segmentHeader);
        this.innerClasses = new TreeSet();
        this.outerToInner = new HashMap();
        this.cpBands = cpBands;
    }

    public void addInnerClass(String name, String outerName, String innerName, int flags) {
        if (outerName != null || innerName != null) {
            if (namesArePredictable(name, outerName, innerName)) {
                IcTuple innerClass = new IcTuple(this.cpBands.getCPClass(name), flags, null, null);
                addToMap(outerName, innerClass);
                this.innerClasses.add(innerClass);
                return;
            }
            IcTuple icTuple = new IcTuple(this.cpBands.getCPClass(name), flags | 65536, this.cpBands.getCPClass(outerName), this.cpBands.getCPUtf8(innerName));
            boolean added = this.innerClasses.add(icTuple);
            if (added) {
                this.bit16Count++;
                addToMap(outerName, icTuple);
                return;
            }
            return;
        }
        IcTuple innerClass2 = new IcTuple(this.cpBands.getCPClass(name), flags, null, null);
        addToMap(getOuter(name), innerClass2);
        this.innerClasses.add(innerClass2);
    }

    private void addToMap(String outerName, IcTuple icTuple) {
        List<IcTuple> tuples = this.outerToInner.get(outerName);
        if (tuples == null) {
            tuples = new ArrayList();
            this.outerToInner.put(outerName, tuples);
        } else {
            for (IcTuple tuple : tuples) {
                if (icTuple.equals(tuple)) {
                    return;
                }
            }
        }
        tuples.add(icTuple);
    }

    public void finaliseBands() {
        this.segmentHeader.setIc_count(this.innerClasses.size());
    }

    public IcTuple getIcTuple(CPClass inner) {
        for (IcTuple icTuple : this.innerClasses) {
            if (icTuple.C.equals(inner)) {
                return icTuple;
            }
        }
        return null;
    }

    public List<IcTuple> getInnerClassesForOuter(String outerClassName) {
        return this.outerToInner.get(outerClassName);
    }

    private String getOuter(String name) {
        return name.substring(0, name.lastIndexOf(36));
    }

    private boolean namesArePredictable(String name, String outerName, String innerName) {
        return name.equals(new StringBuilder().append(outerName).append('$').append(innerName).toString()) && innerName.indexOf(36) == -1;
    }

    @Override // org.apache.commons.compress.harmony.pack200.BandSet
    public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing internal class bands...");
        int[] ic_this_class = new int[this.innerClasses.size()];
        int[] ic_flags = new int[this.innerClasses.size()];
        int[] ic_outer_class = new int[this.bit16Count];
        int[] ic_name = new int[this.bit16Count];
        int index2 = 0;
        List<IcTuple> innerClassesList = new ArrayList<>(this.innerClasses);
        for (int i = 0; i < ic_this_class.length; i++) {
            IcTuple icTuple = innerClassesList.get(i);
            ic_this_class[i] = icTuple.C.getIndex();
            ic_flags[i] = icTuple.F;
            if ((icTuple.F & 65536) != 0) {
                ic_outer_class[index2] = icTuple.C2 == null ? 0 : icTuple.C2.getIndex() + 1;
                ic_name[index2] = icTuple.N != null ? icTuple.N.getIndex() + 1 : 0;
                index2++;
            }
        }
        byte[] encodedBand = encodeBandInt("ic_this_class", ic_this_class, Codec.UDELTA5);
        outputStream.write(encodedBand);
        PackingUtils.log("Wrote " + encodedBand.length + " bytes from ic_this_class[" + ic_this_class.length + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand2 = encodeBandInt("ic_flags", ic_flags, Codec.UNSIGNED5);
        outputStream.write(encodedBand2);
        PackingUtils.log("Wrote " + encodedBand2.length + " bytes from ic_flags[" + ic_flags.length + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand3 = encodeBandInt("ic_outer_class", ic_outer_class, Codec.DELTA5);
        outputStream.write(encodedBand3);
        PackingUtils.log("Wrote " + encodedBand3.length + " bytes from ic_outer_class[" + ic_outer_class.length + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand4 = encodeBandInt("ic_name", ic_name, Codec.DELTA5);
        outputStream.write(encodedBand4);
        PackingUtils.log("Wrote " + encodedBand4.length + " bytes from ic_name[" + ic_name.length + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
    }
}
