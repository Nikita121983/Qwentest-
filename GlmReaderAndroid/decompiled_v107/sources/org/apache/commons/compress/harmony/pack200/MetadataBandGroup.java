package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import org.apache.commons.collections4.CollectionUtils;

/* loaded from: classes9.dex */
public class MetadataBandGroup extends BandSet {
    public static final int CONTEXT_CLASS = 0;
    public static final int CONTEXT_FIELD = 1;
    public static final int CONTEXT_METHOD = 2;
    public List<String> T;
    public IntList anno_N;
    public List<CPConstant<?>> caseD_KD;
    public List<CPConstant<?>> caseF_KF;
    public List<CPConstant<?>> caseI_KI;
    public List<CPConstant<?>> caseJ_KJ;
    public IntList casearray_N;
    public List<CPSignature> casec_RS;
    public List<CPUTF8> caseec_RU;
    public List<CPSignature> caseet_RS;
    public List<CPUTF8> cases_RU;
    private final int context;
    private final CpBands cpBands;
    public List<CPUTF8> name_RU;
    public List<CPUTF8> nestname_RU;
    public IntList nestpair_N;
    public List<CPSignature> nesttype_RS;
    private int numBackwardsCalls;
    public IntList pair_N;
    public IntList param_NB;
    private final String type;
    public List<CPSignature> type_RS;

    public MetadataBandGroup(String type, int context, CpBands cpBands, SegmentHeader segmentHeader, int effort) {
        super(effort, segmentHeader);
        this.param_NB = new IntList();
        this.anno_N = new IntList();
        this.type_RS = new ArrayList();
        this.pair_N = new IntList();
        this.name_RU = new ArrayList();
        this.T = new ArrayList();
        this.caseI_KI = new ArrayList();
        this.caseD_KD = new ArrayList();
        this.caseF_KF = new ArrayList();
        this.caseJ_KJ = new ArrayList();
        this.casec_RS = new ArrayList();
        this.caseet_RS = new ArrayList();
        this.caseec_RU = new ArrayList();
        this.cases_RU = new ArrayList();
        this.casearray_N = new IntList();
        this.nesttype_RS = new ArrayList();
        this.nestpair_N = new IntList();
        this.nestname_RU = new ArrayList();
        this.type = type;
        this.cpBands = cpBands;
        this.context = context;
    }

    public void addAnnotation(String desc, List<String> nameRU, List<String> tags, List<Object> values, List<Integer> caseArrayN, List<String> nestTypeRS, List<String> nestNameRU, List<Integer> nestPairN) {
        char c;
        this.type_RS.add(this.cpBands.getCPSignature(desc));
        this.pair_N.add(nameRU.size());
        nameRU.forEach(new Consumer() { // from class: org.apache.commons.compress.harmony.pack200.MetadataBandGroup$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MetadataBandGroup.this.m2069x38f307eb((String) obj);
            }
        });
        Iterator<Object> valuesIterator = values.iterator();
        for (String tag : tags) {
            this.T.add(tag);
            switch (tag.hashCode()) {
                case 66:
                    if (tag.equals("B")) {
                        c = 0;
                        break;
                    }
                    break;
                case 67:
                    if (tag.equals("C")) {
                        c = 1;
                        break;
                    }
                    break;
                case 68:
                    if (tag.equals("D")) {
                        c = 5;
                        break;
                    }
                    break;
                case 70:
                    if (tag.equals("F")) {
                        c = 6;
                        break;
                    }
                    break;
                case 73:
                    if (tag.equals("I")) {
                        c = 2;
                        break;
                    }
                    break;
                case 74:
                    if (tag.equals("J")) {
                        c = 7;
                        break;
                    }
                    break;
                case 83:
                    if (tag.equals("S")) {
                        c = 3;
                        break;
                    }
                    break;
                case 90:
                    if (tag.equals("Z")) {
                        c = 4;
                        break;
                    }
                    break;
                case 99:
                    if (tag.equals("c")) {
                        c = '\b';
                        break;
                    }
                    break;
                case 101:
                    if (tag.equals("e")) {
                        c = '\t';
                        break;
                    }
                    break;
                case 115:
                    if (tag.equals("s")) {
                        c = '\n';
                        break;
                    }
                    break;
            }
            c = 65535;
            switch (c) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    this.caseI_KI.add(this.cpBands.getConstant(valuesIterator.next()));
                    break;
                case 5:
                    this.caseD_KD.add(this.cpBands.getConstant(valuesIterator.next()));
                    break;
                case 6:
                    this.caseF_KF.add(this.cpBands.getConstant(valuesIterator.next()));
                    break;
                case 7:
                    this.caseJ_KJ.add(this.cpBands.getConstant(valuesIterator.next()));
                    break;
                case '\b':
                    this.casec_RS.add(this.cpBands.getCPSignature(nextString(valuesIterator)));
                    break;
                case '\t':
                    this.caseet_RS.add(this.cpBands.getCPSignature(nextString(valuesIterator)));
                    this.caseec_RU.add(this.cpBands.getCPUtf8(nextString(valuesIterator)));
                    break;
                case '\n':
                    this.cases_RU.add(this.cpBands.getCPUtf8(nextString(valuesIterator)));
                    break;
            }
        }
        for (Integer element : caseArrayN) {
            int arraySize = element.intValue();
            this.casearray_N.add(arraySize);
            this.numBackwardsCalls += arraySize;
        }
        nestTypeRS.forEach(new Consumer() { // from class: org.apache.commons.compress.harmony.pack200.MetadataBandGroup$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MetadataBandGroup.this.m2070x2c828c2c((String) obj);
            }
        });
        nestNameRU.forEach(new Consumer() { // from class: org.apache.commons.compress.harmony.pack200.MetadataBandGroup$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MetadataBandGroup.this.m2071x2012106d((String) obj);
            }
        });
        for (Integer numPairs : nestPairN) {
            this.nestpair_N.add(numPairs.intValue());
            this.numBackwardsCalls += numPairs.intValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$addAnnotation$0$org-apache-commons-compress-harmony-pack200-MetadataBandGroup, reason: not valid java name */
    public /* synthetic */ void m2069x38f307eb(String name) {
        this.name_RU.add(this.cpBands.getCPUtf8(name));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$addAnnotation$1$org-apache-commons-compress-harmony-pack200-MetadataBandGroup, reason: not valid java name */
    public /* synthetic */ void m2070x2c828c2c(String element) {
        this.nesttype_RS.add(this.cpBands.getCPSignature(element));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$addAnnotation$2$org-apache-commons-compress-harmony-pack200-MetadataBandGroup, reason: not valid java name */
    public /* synthetic */ void m2071x2012106d(String element) {
        this.nestname_RU.add(this.cpBands.getCPUtf8(element));
    }

    public void addParameterAnnotation(int numParams, int[] annoN, IntList pairN, List<String> typeRS, List<String> nameRU, List<String> tags, List<Object> values, List<Integer> caseArrayN, List<String> nestTypeRS, List<String> nestNameRU, List<Integer> nestPairN) {
        char c;
        this.param_NB.add(numParams);
        for (int element : annoN) {
            this.anno_N.add(element);
        }
        this.pair_N.addAll(pairN);
        typeRS.forEach(new Consumer() { // from class: org.apache.commons.compress.harmony.pack200.MetadataBandGroup$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MetadataBandGroup.this.m2072x334022ef((String) obj);
            }
        });
        nameRU.forEach(new Consumer() { // from class: org.apache.commons.compress.harmony.pack200.MetadataBandGroup$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MetadataBandGroup.this.m2073x26cfa730((String) obj);
            }
        });
        Iterator<Object> valuesIterator = values.iterator();
        for (String tag : tags) {
            this.T.add(tag);
            switch (tag.hashCode()) {
                case 66:
                    if (tag.equals("B")) {
                        c = 0;
                        break;
                    }
                    break;
                case 67:
                    if (tag.equals("C")) {
                        c = 1;
                        break;
                    }
                    break;
                case 68:
                    if (tag.equals("D")) {
                        c = 5;
                        break;
                    }
                    break;
                case 70:
                    if (tag.equals("F")) {
                        c = 6;
                        break;
                    }
                    break;
                case 73:
                    if (tag.equals("I")) {
                        c = 2;
                        break;
                    }
                    break;
                case 74:
                    if (tag.equals("J")) {
                        c = 7;
                        break;
                    }
                    break;
                case 83:
                    if (tag.equals("S")) {
                        c = 3;
                        break;
                    }
                    break;
                case 90:
                    if (tag.equals("Z")) {
                        c = 4;
                        break;
                    }
                    break;
                case 99:
                    if (tag.equals("c")) {
                        c = '\b';
                        break;
                    }
                    break;
                case 101:
                    if (tag.equals("e")) {
                        c = '\t';
                        break;
                    }
                    break;
                case 115:
                    if (tag.equals("s")) {
                        c = '\n';
                        break;
                    }
                    break;
            }
            c = 65535;
            switch (c) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    this.caseI_KI.add(this.cpBands.getConstant(valuesIterator.next()));
                    break;
                case 5:
                    this.caseD_KD.add(this.cpBands.getConstant(valuesIterator.next()));
                    break;
                case 6:
                    this.caseF_KF.add(this.cpBands.getConstant(valuesIterator.next()));
                    break;
                case 7:
                    this.caseJ_KJ.add(this.cpBands.getConstant(valuesIterator.next()));
                    break;
                case '\b':
                    this.casec_RS.add(this.cpBands.getCPSignature(nextString(valuesIterator)));
                    break;
                case '\t':
                    this.caseet_RS.add(this.cpBands.getCPSignature(nextString(valuesIterator)));
                    this.caseec_RU.add(this.cpBands.getCPUtf8(nextString(valuesIterator)));
                    break;
                case '\n':
                    this.cases_RU.add(this.cpBands.getCPUtf8(nextString(valuesIterator)));
                    break;
            }
        }
        for (Integer element2 : caseArrayN) {
            int arraySize = element2.intValue();
            this.casearray_N.add(arraySize);
            this.numBackwardsCalls += arraySize;
        }
        nestTypeRS.forEach(new Consumer() { // from class: org.apache.commons.compress.harmony.pack200.MetadataBandGroup$$ExternalSyntheticLambda6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MetadataBandGroup.this.m2074x1a5f2b71((String) obj);
            }
        });
        nestNameRU.forEach(new Consumer() { // from class: org.apache.commons.compress.harmony.pack200.MetadataBandGroup$$ExternalSyntheticLambda7
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MetadataBandGroup.this.m2075xdeeafb2((String) obj);
            }
        });
        for (Integer numPairs : nestPairN) {
            this.nestpair_N.add(numPairs.intValue());
            this.numBackwardsCalls += numPairs.intValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$addParameterAnnotation$3$org-apache-commons-compress-harmony-pack200-MetadataBandGroup, reason: not valid java name */
    public /* synthetic */ void m2072x334022ef(String desc) {
        this.type_RS.add(this.cpBands.getCPSignature(desc));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$addParameterAnnotation$4$org-apache-commons-compress-harmony-pack200-MetadataBandGroup, reason: not valid java name */
    public /* synthetic */ void m2073x26cfa730(String name) {
        this.name_RU.add(this.cpBands.getCPUtf8(name));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$addParameterAnnotation$5$org-apache-commons-compress-harmony-pack200-MetadataBandGroup, reason: not valid java name */
    public /* synthetic */ void m2074x1a5f2b71(String type) {
        this.nesttype_RS.add(this.cpBands.getCPSignature(type));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$addParameterAnnotation$6$org-apache-commons-compress-harmony-pack200-MetadataBandGroup, reason: not valid java name */
    public /* synthetic */ void m2075xdeeafb2(String name) {
        this.nestname_RU.add(this.cpBands.getCPUtf8(name));
    }

    public boolean hasContent() {
        return this.type_RS.size() > 0;
    }

    public void incrementAnnoN() {
        this.anno_N.increment(this.anno_N.size() - 1);
    }

    public void newEntryInAnnoN() {
        this.anno_N.add(1);
    }

    private String nextString(Iterator<Object> valuesIterator) {
        return (String) valuesIterator.next();
    }

    public int numBackwardsCalls() {
        return this.numBackwardsCalls;
    }

    @Override // org.apache.commons.compress.harmony.pack200.BandSet
    public void pack(OutputStream out) throws IOException, Pack200Exception {
        String contextStr;
        PackingUtils.log("Writing metadata band group...");
        if (hasContent()) {
            if (this.context == 0) {
                contextStr = "Class";
            } else if (this.context == 1) {
                contextStr = "Field";
            } else {
                contextStr = "Method";
            }
            if (!this.type.equals("AD")) {
                if (this.type.indexOf(80) != -1) {
                    byte[] encodedBand = encodeBandInt(contextStr + "_" + this.type + " param_NB", this.param_NB.toArray(), Codec.BYTE1);
                    out.write(encodedBand);
                    PackingUtils.log("Wrote " + encodedBand.length + " bytes from " + contextStr + "_" + this.type + " anno_N[" + this.param_NB.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
                }
                byte[] encodedBand2 = encodeBandInt(contextStr + "_" + this.type + " anno_N", this.anno_N.toArray(), Codec.UNSIGNED5);
                out.write(encodedBand2);
                PackingUtils.log("Wrote " + encodedBand2.length + " bytes from " + contextStr + "_" + this.type + " anno_N[" + this.anno_N.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
                byte[] encodedBand3 = encodeBandInt(contextStr + "_" + this.type + " type_RS", cpEntryListToArray(this.type_RS), Codec.UNSIGNED5);
                out.write(encodedBand3);
                PackingUtils.log("Wrote " + encodedBand3.length + " bytes from " + contextStr + "_" + this.type + " type_RS[" + this.type_RS.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
                byte[] encodedBand4 = encodeBandInt(contextStr + "_" + this.type + " pair_N", this.pair_N.toArray(), Codec.UNSIGNED5);
                out.write(encodedBand4);
                PackingUtils.log("Wrote " + encodedBand4.length + " bytes from " + contextStr + "_" + this.type + " pair_N[" + this.pair_N.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
                byte[] encodedBand5 = encodeBandInt(contextStr + "_" + this.type + " name_RU", cpEntryListToArray(this.name_RU), Codec.UNSIGNED5);
                out.write(encodedBand5);
                PackingUtils.log("Wrote " + encodedBand5.length + " bytes from " + contextStr + "_" + this.type + " name_RU[" + this.name_RU.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            }
            byte[] encodedBand6 = encodeBandInt(contextStr + "_" + this.type + " T", tagListToArray(this.T), Codec.BYTE1);
            out.write(encodedBand6);
            PackingUtils.log("Wrote " + encodedBand6.length + " bytes from " + contextStr + "_" + this.type + " T[" + this.T.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            byte[] encodedBand7 = encodeBandInt(contextStr + "_" + this.type + " caseI_KI", cpEntryListToArray(this.caseI_KI), Codec.UNSIGNED5);
            out.write(encodedBand7);
            PackingUtils.log("Wrote " + encodedBand7.length + " bytes from " + contextStr + "_" + this.type + " caseI_KI[" + this.caseI_KI.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            byte[] encodedBand8 = encodeBandInt(contextStr + "_" + this.type + " caseD_KD", cpEntryListToArray(this.caseD_KD), Codec.UNSIGNED5);
            out.write(encodedBand8);
            PackingUtils.log("Wrote " + encodedBand8.length + " bytes from " + contextStr + "_" + this.type + " caseD_KD[" + this.caseD_KD.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            byte[] encodedBand9 = encodeBandInt(contextStr + "_" + this.type + " caseF_KF", cpEntryListToArray(this.caseF_KF), Codec.UNSIGNED5);
            out.write(encodedBand9);
            PackingUtils.log("Wrote " + encodedBand9.length + " bytes from " + contextStr + "_" + this.type + " caseF_KF[" + this.caseF_KF.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            byte[] encodedBand10 = encodeBandInt(contextStr + "_" + this.type + " caseJ_KJ", cpEntryListToArray(this.caseJ_KJ), Codec.UNSIGNED5);
            out.write(encodedBand10);
            PackingUtils.log("Wrote " + encodedBand10.length + " bytes from " + contextStr + "_" + this.type + " caseJ_KJ[" + this.caseJ_KJ.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            byte[] encodedBand11 = encodeBandInt(contextStr + "_" + this.type + " casec_RS", cpEntryListToArray(this.casec_RS), Codec.UNSIGNED5);
            out.write(encodedBand11);
            PackingUtils.log("Wrote " + encodedBand11.length + " bytes from " + contextStr + "_" + this.type + " casec_RS[" + this.casec_RS.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            byte[] encodedBand12 = encodeBandInt(contextStr + "_" + this.type + " caseet_RS", cpEntryListToArray(this.caseet_RS), Codec.UNSIGNED5);
            out.write(encodedBand12);
            PackingUtils.log("Wrote " + encodedBand12.length + " bytes from " + contextStr + "_" + this.type + " caseet_RS[" + this.caseet_RS.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            byte[] encodedBand13 = encodeBandInt(contextStr + "_" + this.type + " caseec_RU", cpEntryListToArray(this.caseec_RU), Codec.UNSIGNED5);
            out.write(encodedBand13);
            PackingUtils.log("Wrote " + encodedBand13.length + " bytes from " + contextStr + "_" + this.type + " caseec_RU[" + this.caseec_RU.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            byte[] encodedBand14 = encodeBandInt(contextStr + "_" + this.type + " cases_RU", cpEntryListToArray(this.cases_RU), Codec.UNSIGNED5);
            out.write(encodedBand14);
            PackingUtils.log("Wrote " + encodedBand14.length + " bytes from " + contextStr + "_" + this.type + " cases_RU[" + this.cases_RU.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            byte[] encodedBand15 = encodeBandInt(contextStr + "_" + this.type + " casearray_N", this.casearray_N.toArray(), Codec.UNSIGNED5);
            out.write(encodedBand15);
            PackingUtils.log("Wrote " + encodedBand15.length + " bytes from " + contextStr + "_" + this.type + " casearray_N[" + this.casearray_N.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            byte[] encodedBand16 = encodeBandInt(contextStr + "_" + this.type + " nesttype_RS", cpEntryListToArray(this.nesttype_RS), Codec.UNSIGNED5);
            out.write(encodedBand16);
            PackingUtils.log("Wrote " + encodedBand16.length + " bytes from " + contextStr + "_" + this.type + " nesttype_RS[" + this.nesttype_RS.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            byte[] encodedBand17 = encodeBandInt(contextStr + "_" + this.type + " nestpair_N", this.nestpair_N.toArray(), Codec.UNSIGNED5);
            out.write(encodedBand17);
            PackingUtils.log("Wrote " + encodedBand17.length + " bytes from " + contextStr + "_" + this.type + " nestpair_N[" + this.nestpair_N.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            byte[] encodedBand18 = encodeBandInt(contextStr + "_" + this.type + " nestname_RU", cpEntryListToArray(this.nestname_RU), Codec.UNSIGNED5);
            out.write(encodedBand18);
            PackingUtils.log("Wrote " + encodedBand18.length + " bytes from " + contextStr + "_" + this.type + " nestname_RU[" + this.nestname_RU.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        }
    }

    public void removeLatest() {
        int latest = this.anno_N.remove(this.anno_N.size() - 1);
        for (int i = 0; i < latest; i++) {
            this.type_RS.remove(this.type_RS.size() - 1);
            int pairs = this.pair_N.remove(this.pair_N.size() - 1);
            for (int j = 0; j < pairs; j++) {
                removeOnePair();
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void removeOnePair() {
        char c;
        String tag = this.T.remove(this.T.size() - 1);
        switch (tag.hashCode()) {
            case 64:
                if (tag.equals("@")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 66:
                if (tag.equals("B")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 67:
                if (tag.equals("C")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 68:
                if (tag.equals("D")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 70:
                if (tag.equals("F")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 73:
                if (tag.equals("I")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 74:
                if (tag.equals("J")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 83:
                if (tag.equals("S")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 90:
                if (tag.equals("Z")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 91:
                if (tag.equals(CollectionUtils.DEFAULT_TOSTRING_PREFIX)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 101:
                if (tag.equals("e")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 115:
                if (tag.equals("s")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                this.caseI_KI.remove(this.caseI_KI.size() - 1);
                return;
            case 5:
                this.caseD_KD.remove(this.caseD_KD.size() - 1);
                return;
            case 6:
                this.caseF_KF.remove(this.caseF_KF.size() - 1);
                return;
            case 7:
                this.caseJ_KJ.remove(this.caseJ_KJ.size() - 1);
                return;
            case '\b':
                this.caseet_RS.remove(this.caseet_RS.size() - 1);
                this.caseec_RU.remove(this.caseet_RS.size() - 1);
                return;
            case '\t':
                this.cases_RU.remove(this.cases_RU.size() - 1);
                return;
            case '\n':
                int arraySize = this.casearray_N.remove(this.casearray_N.size() - 1);
                this.numBackwardsCalls -= arraySize;
                for (int k = 0; k < arraySize; k++) {
                    removeOnePair();
                }
                return;
            case 11:
                this.nesttype_RS.remove(this.nesttype_RS.size() - 1);
                int numPairs = this.nestpair_N.remove(this.nestpair_N.size() - 1);
                this.numBackwardsCalls -= numPairs;
                for (int i = 0; i < numPairs; i++) {
                    removeOnePair();
                }
                return;
            default:
                return;
        }
    }

    private int[] tagListToArray(List<String> list) {
        return list.stream().mapToInt(new ToIntFunction() { // from class: org.apache.commons.compress.harmony.pack200.MetadataBandGroup$$ExternalSyntheticLambda0
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                int charAt;
                charAt = ((String) obj).charAt(0);
                return charAt;
            }
        }).toArray();
    }
}
