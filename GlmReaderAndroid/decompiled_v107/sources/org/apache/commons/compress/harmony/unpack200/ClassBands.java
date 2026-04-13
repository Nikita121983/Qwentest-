package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPNameAndType;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.ConstantValueAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.DeprecatedAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.EnclosingMethodAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.ExceptionsAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LineNumberTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LocalVariableTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LocalVariableTypeTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SignatureAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SourceFileAttribute;

/* loaded from: classes9.dex */
public class ClassBands extends BandSet {
    private final AttributeLayoutMap attrMap;
    private long[] classAccessFlags;
    private ArrayList<Attribute>[] classAttributes;
    private final int classCount;
    private int[] classFieldCount;
    private long[] classFlags;
    private int[][] classInterfacesInts;
    private int[] classMethodCount;
    private int[] classSuperInts;
    private String[] classThis;
    private int[] classThisInts;
    private int[] classVersionMajor;
    private int[] classVersionMinor;
    private List<Attribute>[] codeAttributes;
    private int[][] codeHandlerCatchPO;
    private int[][] codeHandlerClassRCN;
    private int[] codeHandlerCount;
    private int[][] codeHandlerEndPO;
    private int[][] codeHandlerStartP;
    private boolean[] codeHasAttributes;
    private int[] codeMaxNALocals;
    private int[] codeMaxStack;
    private final CpBands cpBands;
    private long[][] fieldAccessFlags;
    private ArrayList<Attribute>[][] fieldAttributes;
    private String[][] fieldDescr;
    private int[][] fieldDescrInts;
    private long[][] fieldFlags;
    private IcTuple[][] icLocal;
    private long[][] methodAccessFlags;
    private int[] methodAttrCalls;
    private ArrayList<Attribute>[][] methodAttributes;
    private String[][] methodDescr;
    private int[][] methodDescrInts;
    private long[][] methodFlags;
    private final SegmentOptions options;

    /* renamed from: $r8$lambda$ZpmJ_VNnAu-jaI1Md1zDJiayPjU, reason: not valid java name */
    public static /* synthetic */ ArrayList m2076$r8$lambda$ZpmJ_VNnAujaI1Md1zDJiayPjU(Collection collection) {
        return new ArrayList(collection);
    }

    /* renamed from: $r8$lambda$wBZ5N9SAmxJLRX-vx8hIUeo_fgc, reason: not valid java name */
    public static /* synthetic */ ArrayList m2077$r8$lambda$wBZ5N9SAmxJLRXvx8hIUeo_fgc() {
        return new ArrayList();
    }

    public ClassBands(Segment segment) {
        super(segment);
        this.attrMap = segment.getAttrDefinitionBands().getAttributeDefinitionMap();
        this.cpBands = segment.getCpBands();
        this.classCount = this.header.getClassCount();
        this.options = this.header.getOptions();
    }

    private int getCallCount(int[][] methodAttrIndexes, long[][] flags, int context) {
        int callCount = 0;
        for (int[] element : methodAttrIndexes) {
            for (int index : element) {
                AttributeLayout layout = this.attrMap.getAttributeLayout(index, context);
                callCount += layout.numBackwardsCallables();
            }
        }
        int layoutsUsed = 0;
        for (long[] flag : flags) {
            for (long j : flag) {
                layoutsUsed = (int) (layoutsUsed | j);
            }
        }
        for (int i = 0; i < 26; i++) {
            if (((1 << i) & layoutsUsed) != 0) {
                AttributeLayout layout2 = this.attrMap.getAttributeLayout(i, context);
                callCount += layout2.numBackwardsCallables();
            }
        }
        return callCount;
    }

    public ArrayList<Attribute>[] getClassAttributes() {
        return this.classAttributes;
    }

    public int[] getClassFieldCount() {
        return this.classFieldCount;
    }

    public long[] getClassFlags() {
        if (this.classAccessFlags == null) {
            long mask = 32767;
            for (int i = 0; i < 16; i++) {
                AttributeLayout layout = this.attrMap.getAttributeLayout(i, 0);
                if (layout != null && !layout.isDefaultLayout()) {
                    mask &= ~(1 << i);
                }
            }
            this.classAccessFlags = new long[this.classFlags.length];
            for (int i2 = 0; i2 < this.classFlags.length; i2++) {
                this.classAccessFlags[i2] = this.classFlags[i2] & mask;
            }
        }
        return this.classAccessFlags;
    }

    public int[][] getClassInterfacesInts() {
        return this.classInterfacesInts;
    }

    public int[] getClassMethodCount() {
        return this.classMethodCount;
    }

    public int[] getClassSuperInts() {
        return this.classSuperInts;
    }

    public int[] getClassThisInts() {
        return this.classThisInts;
    }

    public int[] getClassVersionMajor() {
        return this.classVersionMajor;
    }

    public int[] getClassVersionMinor() {
        return this.classVersionMinor;
    }

    public int[][] getCodeHandlerCatchPO() {
        return this.codeHandlerCatchPO;
    }

    public int[][] getCodeHandlerClassRCN() {
        return this.codeHandlerClassRCN;
    }

    public int[] getCodeHandlerCount() {
        return this.codeHandlerCount;
    }

    public int[][] getCodeHandlerEndPO() {
        return this.codeHandlerEndPO;
    }

    public int[][] getCodeHandlerStartP() {
        return this.codeHandlerStartP;
    }

    public boolean[] getCodeHasAttributes() {
        return this.codeHasAttributes;
    }

    public int[] getCodeMaxNALocals() {
        return this.codeMaxNALocals;
    }

    public int[] getCodeMaxStack() {
        return this.codeMaxStack;
    }

    public ArrayList<Attribute>[][] getFieldAttributes() {
        return this.fieldAttributes;
    }

    public int[][] getFieldDescrInts() {
        return this.fieldDescrInts;
    }

    public long[][] getFieldFlags() {
        if (this.fieldAccessFlags == null) {
            long mask = 32767;
            for (int i = 0; i < 16; i++) {
                AttributeLayout layout = this.attrMap.getAttributeLayout(i, 1);
                if (layout != null && !layout.isDefaultLayout()) {
                    mask &= ~(1 << i);
                }
            }
            this.fieldAccessFlags = new long[this.fieldFlags.length];
            for (int i2 = 0; i2 < this.fieldFlags.length; i2++) {
                this.fieldAccessFlags[i2] = new long[this.fieldFlags[i2].length];
                for (int j = 0; j < this.fieldFlags[i2].length; j++) {
                    this.fieldAccessFlags[i2][j] = this.fieldFlags[i2][j] & mask;
                }
            }
        }
        return this.fieldAccessFlags;
    }

    public IcTuple[][] getIcLocal() {
        return this.icLocal;
    }

    public ArrayList<Attribute>[][] getMethodAttributes() {
        return this.methodAttributes;
    }

    public String[][] getMethodDescr() {
        return this.methodDescr;
    }

    public int[][] getMethodDescrInts() {
        return this.methodDescrInts;
    }

    public long[][] getMethodFlags() {
        if (this.methodAccessFlags == null) {
            long mask = 32767;
            for (int i = 0; i < 16; i++) {
                AttributeLayout layout = this.attrMap.getAttributeLayout(i, 2);
                if (layout != null && !layout.isDefaultLayout()) {
                    mask &= ~(1 << i);
                }
            }
            this.methodAccessFlags = new long[this.methodFlags.length];
            for (int i2 = 0; i2 < this.methodFlags.length; i2++) {
                this.methodAccessFlags[i2] = new long[this.methodFlags[i2].length];
                for (int j = 0; j < this.methodFlags[i2].length; j++) {
                    this.methodAccessFlags[i2][j] = this.methodFlags[i2][j] & mask;
                }
            }
        }
        return this.methodAccessFlags;
    }

    public ArrayList<List<Attribute>> getOrderedCodeAttributes() {
        return (ArrayList) Stream.of((Object[]) this.codeAttributes).map(new Function() { // from class: org.apache.commons.compress.harmony.unpack200.ClassBands$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ClassBands.m2076$r8$lambda$ZpmJ_VNnAujaI1Md1zDJiayPjU((List) obj);
            }
        }).collect(Collectors.toCollection(new Supplier() { // from class: org.apache.commons.compress.harmony.unpack200.ClassBands$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return ClassBands.m2077$r8$lambda$wBZ5N9SAmxJLRXvx8hIUeo_fgc();
            }
        }));
    }

    public long[] getRawClassFlags() {
        return this.classFlags;
    }

    private void parseClassAttrBands(InputStream in) throws IOException, Pack200Exception {
        int i;
        int[] classAttrCalls;
        AttributeLayout deprecatedLayout;
        AttributeLayout signatureLayout;
        AttributeLayout signatureLayout2;
        int j;
        int i2;
        int[] counts;
        String[] cpUTF8 = this.cpBands.getCpUTF8();
        String[] cpClass = this.cpBands.getCpClass();
        this.classAttributes = new ArrayList[this.classCount];
        Arrays.setAll(this.classAttributes, new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.ClassBands$$ExternalSyntheticLambda4
            @Override // java.util.function.IntFunction
            public final Object apply(int i3) {
                return ClassBands.lambda$parseClassAttrBands$0(i3);
            }
        });
        InputStream inputStream = in;
        this.classFlags = parseFlags("class_flags", inputStream, this.classCount, Codec.UNSIGNED5, this.options.hasClassFlagsHi());
        int classAttrCount = SegmentUtils.countBit16(this.classFlags);
        int[] classAttrCounts = decodeBandInt("class_attr_count", inputStream, Codec.UNSIGNED5, classAttrCount);
        int[][] classAttrIndexes = decodeBandInt("class_attr_indexes", inputStream, Codec.UNSIGNED5, classAttrCounts);
        int callCount = getCallCount(classAttrIndexes, new long[][]{this.classFlags}, 0);
        int[] classAttrCalls2 = decodeBandInt("class_attr_calls", inputStream, Codec.UNSIGNED5, callCount);
        AttributeLayout deprecatedLayout2 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 0);
        AttributeLayout sourceFileLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_SOURCE_FILE, 0);
        int sourceFileCount = SegmentUtils.countMatches(this.classFlags, sourceFileLayout);
        int[] classSourceFile = decodeBandInt("class_SourceFile_RUN", inputStream, Codec.UNSIGNED5, sourceFileCount);
        AttributeLayout enclosingMethodLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_ENCLOSING_METHOD, 0);
        int enclosingMethodCount = SegmentUtils.countMatches(this.classFlags, enclosingMethodLayout);
        int[] enclosingMethodRC = decodeBandInt("class_EnclosingMethod_RC", inputStream, Codec.UNSIGNED5, enclosingMethodCount);
        int[] enclosingMethodRDN = decodeBandInt("class_EnclosingMethod_RDN", inputStream, Codec.UNSIGNED5, enclosingMethodCount);
        AttributeLayout signatureLayout3 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE, 0);
        int signatureCount = SegmentUtils.countMatches(this.classFlags, signatureLayout3);
        int[] classSignature = decodeBandInt("class_Signature_RS", inputStream, Codec.UNSIGNED5, signatureCount);
        int backwardsCallsUsed = parseClassMetadataBands(inputStream, classAttrCalls2);
        AttributeLayout innerClassLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_INNER_CLASSES, 0);
        int innerClassCount = SegmentUtils.countMatches(this.classFlags, innerClassLayout);
        int[] classInnerClassesN = decodeBandInt("class_InnerClasses_N", inputStream, Codec.UNSIGNED5, innerClassCount);
        int[][] classInnerClassesRC = decodeBandInt("class_InnerClasses_RC", inputStream, Codec.UNSIGNED5, classInnerClassesN);
        int[][] classInnerClassesF = decodeBandInt("class_InnerClasses_F", inputStream, Codec.UNSIGNED5, classInnerClassesN);
        int flagsCount = 0;
        int length = classInnerClassesF.length;
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3;
            int[] element = classInnerClassesF[i4];
            int i5 = length;
            for (int element2 : element) {
                if (element2 != 0) {
                    flagsCount++;
                }
            }
            i3 = i4 + 1;
            length = i5;
        }
        int[] classInnerClassesOuterRCN = decodeBandInt("class_InnerClasses_outer_RCN", inputStream, Codec.UNSIGNED5, flagsCount);
        int[] classInnerClassesNameRUN = decodeBandInt("class_InnerClasses_name_RUN", inputStream, Codec.UNSIGNED5, flagsCount);
        AttributeLayout versionLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_CLASS_FILE_VERSION, 0);
        int versionCount = SegmentUtils.countMatches(this.classFlags, versionLayout);
        int[] classFileVersionMinorH = decodeBandInt("class_file_version_minor_H", inputStream, Codec.UNSIGNED5, versionCount);
        int[] classFileVersionMajorH = decodeBandInt("class_file_version_major_H", inputStream, Codec.UNSIGNED5, versionCount);
        if (versionCount > 0) {
            this.classVersionMajor = new int[this.classCount];
            this.classVersionMinor = new int[this.classCount];
        }
        int defaultVersionMajor = this.header.getDefaultClassMajorVersion();
        int defaultVersionMinor = this.header.getDefaultClassMinorVersion();
        int limit = this.options.hasClassFlagsHi() ? 62 : 31;
        AttributeLayout[] otherLayouts = new AttributeLayout[limit + 1];
        int defaultVersionMajor2 = limit + 1;
        int[] counts2 = new int[defaultVersionMajor2];
        int backwardsCallsUsed2 = limit + 1;
        List<Attribute>[] otherAttributes = new List[backwardsCallsUsed2];
        int i6 = 0;
        while (i6 < limit) {
            int limit2 = limit;
            int sourceFileCount2 = sourceFileCount;
            AttributeLayout layout = this.attrMap.getAttributeLayout(i6, 0);
            if (layout != null && !layout.isDefaultLayout()) {
                otherLayouts[i6] = layout;
                counts2[i6] = SegmentUtils.countMatches(this.classFlags, layout);
            }
            i6++;
            limit = limit2;
            sourceFileCount = sourceFileCount2;
        }
        int i7 = 0;
        int backwardsCallIndex = backwardsCallsUsed;
        while (i7 < counts2.length) {
            if (counts2[i7] <= 0) {
                i2 = i7;
                counts = counts2;
            } else {
                i2 = i7;
                NewAttributeBands bands = this.attrMap.getAttributeBands(otherLayouts[i2]);
                otherAttributes[i2] = bands.parseAttributes(inputStream, counts2[i2]);
                int numBackwardsCallables = otherLayouts[i2].numBackwardsCallables();
                if (numBackwardsCallables <= 0) {
                    counts = counts2;
                } else {
                    int[] backwardsCalls = new int[numBackwardsCallables];
                    counts = counts2;
                    System.arraycopy(classAttrCalls2, backwardsCallIndex, backwardsCalls, 0, numBackwardsCallables);
                    bands.setBackwardsCalls(backwardsCalls);
                    backwardsCallIndex += numBackwardsCallables;
                }
            }
            i7 = i2 + 1;
            inputStream = in;
            counts2 = counts;
        }
        int enclosingMethodIndex = 0;
        int signatureIndex = 0;
        int innerClassIndex = 0;
        int innerClassC2NIndex = 0;
        int versionIndex = 0;
        int sourceFileIndex = 0;
        int sourceFileIndex2 = this.classCount;
        this.icLocal = new IcTuple[sourceFileIndex2];
        int i8 = 0;
        while (true) {
            int enclosingMethodIndex2 = enclosingMethodIndex;
            int enclosingMethodIndex3 = this.classCount;
            if (i8 < enclosingMethodIndex3) {
                int signatureIndex2 = signatureIndex;
                int backwardsCallIndex2 = backwardsCallIndex;
                long flag = this.classFlags[i8];
                int innerClassIndex2 = innerClassIndex;
                int[] classSourceFile2 = classSourceFile;
                if (deprecatedLayout2.matches(this.classFlags[i8])) {
                    this.classAttributes[i8].add(new DeprecatedAttribute());
                }
                if (!sourceFileLayout.matches(flag)) {
                    i = i8;
                    classAttrCalls = classAttrCalls2;
                    deprecatedLayout = deprecatedLayout2;
                } else {
                    long result = classSourceFile2[sourceFileIndex];
                    ClassFileEntry value = sourceFileLayout.getValue(result, this.cpBands.getConstantPool());
                    if (value != null) {
                        i = i8;
                        classAttrCalls = classAttrCalls2;
                        deprecatedLayout = deprecatedLayout2;
                    } else {
                        i = i8;
                        classAttrCalls = classAttrCalls2;
                        String className = this.classThis[i].substring(this.classThis[i].lastIndexOf(47) + 1);
                        String className2 = className.substring(className.lastIndexOf(46) + 1);
                        char[] chars = className2.toCharArray();
                        int index = 0;
                        while (true) {
                            deprecatedLayout = deprecatedLayout2;
                            if (index >= chars.length) {
                                index = -1;
                                break;
                            }
                            char[] chars2 = chars;
                            if (chars[index] <= '-') {
                                break;
                            }
                            index++;
                            deprecatedLayout2 = deprecatedLayout;
                            chars = chars2;
                        }
                        if (index > -1) {
                            className2 = className2.substring(0, index);
                        }
                        value = this.cpBands.cpUTF8Value(className2 + ".java", true);
                    }
                    this.classAttributes[i].add(new SourceFileAttribute((CPUTF8) value));
                    sourceFileIndex++;
                }
                if (!enclosingMethodLayout.matches(flag)) {
                    enclosingMethodIndex = enclosingMethodIndex2;
                } else {
                    CPClass theClass = this.cpBands.cpClassValue(enclosingMethodRC[enclosingMethodIndex2]);
                    CPNameAndType theMethod = null;
                    if (enclosingMethodRDN[enclosingMethodIndex2] != 0) {
                        theMethod = this.cpBands.cpNameAndTypeValue(enclosingMethodRDN[enclosingMethodIndex2] - 1);
                    }
                    this.classAttributes[i].add(new EnclosingMethodAttribute(theClass, theMethod));
                    enclosingMethodIndex = enclosingMethodIndex2 + 1;
                }
                if (signatureLayout3.matches(flag)) {
                    long result2 = classSignature[signatureIndex2];
                    this.classAttributes[i].add(new SignatureAttribute((CPUTF8) signatureLayout3.getValue(result2, this.cpBands.getConstantPool())));
                    signatureIndex2++;
                }
                if (innerClassLayout.matches(flag)) {
                    this.icLocal[i] = new IcTuple[classInnerClassesN[innerClassIndex2]];
                    int j2 = 0;
                    while (j2 < this.icLocal[i].length) {
                        int icTupleCIndex = classInnerClassesRC[innerClassIndex2][j2];
                        int icTupleC2Index = -1;
                        int icTupleNIndex = -1;
                        String icTupleC = cpClass[icTupleCIndex];
                        int icTupleF = classInnerClassesF[innerClassIndex2][j2];
                        String icTupleC2 = null;
                        String icTupleN = null;
                        if (icTupleF != 0) {
                            icTupleC2Index = classInnerClassesOuterRCN[innerClassC2NIndex];
                            icTupleNIndex = classInnerClassesNameRUN[innerClassC2NIndex];
                            icTupleC2 = cpClass[icTupleC2Index];
                            icTupleN = cpUTF8[icTupleNIndex];
                            innerClassC2NIndex++;
                            signatureLayout2 = signatureLayout3;
                            j = j2;
                        } else {
                            signatureLayout2 = signatureLayout3;
                            IcBands icBands = this.segment.getIcBands();
                            IcTuple[] icAll = icBands.getIcTuples();
                            j = j2;
                            int j3 = icAll.length;
                            int i9 = 0;
                            while (true) {
                                if (i9 < j3) {
                                    IcTuple element3 = icAll[i9];
                                    int i10 = i9;
                                    if (!element3.getC().equals(icTupleC)) {
                                        i9 = i10 + 1;
                                    } else {
                                        icTupleF = element3.getF();
                                        icTupleC2 = element3.getC2();
                                        icTupleN = element3.getN();
                                        break;
                                    }
                                }
                            }
                        }
                        IcTuple icTuple = new IcTuple(icTupleC, icTupleF, icTupleC2, icTupleN, icTupleCIndex, icTupleC2Index, icTupleNIndex, j);
                        this.icLocal[i][j] = icTuple;
                        j2 = j + 1;
                        signatureLayout3 = signatureLayout2;
                    }
                    signatureLayout = signatureLayout3;
                    innerClassIndex = innerClassIndex2 + 1;
                } else {
                    signatureLayout = signatureLayout3;
                    innerClassIndex = innerClassIndex2;
                }
                if (versionLayout.matches(flag)) {
                    this.classVersionMajor[i] = classFileVersionMajorH[versionIndex];
                    this.classVersionMinor[i] = classFileVersionMinorH[versionIndex];
                    versionIndex++;
                } else if (this.classVersionMajor != null) {
                    this.classVersionMajor[i] = defaultVersionMajor;
                    this.classVersionMinor[i] = defaultVersionMinor;
                }
                for (int j4 = 0; j4 < otherLayouts.length; j4++) {
                    if (otherLayouts[j4] != null && otherLayouts[j4].matches(flag)) {
                        this.classAttributes[i].add(otherAttributes[j4].get(0));
                        otherAttributes[j4].remove(0);
                    }
                }
                i8 = i + 1;
                backwardsCallIndex = backwardsCallIndex2;
                signatureIndex = signatureIndex2;
                classSourceFile = classSourceFile2;
                signatureLayout3 = signatureLayout;
                classAttrCalls2 = classAttrCalls;
                deprecatedLayout2 = deprecatedLayout;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ArrayList lambda$parseClassAttrBands$0(int i) {
        return new ArrayList();
    }

    private int parseClassMetadataBands(InputStream in, int[] classAttrCalls) throws Pack200Exception, IOException {
        int numBackwardsCalls;
        String[] RxA = {"RVA", "RIA"};
        AttributeLayout rvaLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS, 0);
        AttributeLayout riaLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS, 0);
        int rvaCount = SegmentUtils.countMatches(this.classFlags, rvaLayout);
        int riaCount = SegmentUtils.countMatches(this.classFlags, riaLayout);
        int[] RxACount = {rvaCount, riaCount};
        int[] backwardsCalls = {0, 0};
        if (rvaCount > 0) {
            int numBackwardsCalls2 = 0 + 1;
            backwardsCalls[0] = classAttrCalls[0];
            if (riaCount <= 0) {
                numBackwardsCalls = numBackwardsCalls2;
            } else {
                backwardsCalls[1] = classAttrCalls[1];
                numBackwardsCalls = numBackwardsCalls2 + 1;
            }
        } else if (riaCount <= 0) {
            numBackwardsCalls = 0;
        } else {
            int numBackwardsCalls3 = 0 + 1;
            backwardsCalls[1] = classAttrCalls[0];
            numBackwardsCalls = numBackwardsCalls3;
        }
        MetadataBandGroup[] mbgs = parseMetadata(in, RxA, RxACount, backwardsCalls, "class");
        List<Attribute> rvaAttributes = mbgs[0].getAttributes();
        List<Attribute> riaAttributes = mbgs[1].getAttributes();
        int rvaAttributesIndex = 0;
        int riaAttributesIndex = 0;
        int i = 0;
        while (i < this.classFlags.length) {
            String[] RxA2 = RxA;
            int[] RxACount2 = RxACount;
            if (rvaLayout.matches(this.classFlags[i])) {
                this.classAttributes[i].add(rvaAttributes.get(rvaAttributesIndex));
                rvaAttributesIndex++;
            }
            List<Attribute> rvaAttributes2 = rvaAttributes;
            if (riaLayout.matches(this.classFlags[i])) {
                this.classAttributes[i].add(riaAttributes.get(riaAttributesIndex));
                riaAttributesIndex++;
            }
            i++;
            rvaAttributes = rvaAttributes2;
            RxA = RxA2;
            RxACount = RxACount2;
        }
        return numBackwardsCalls;
    }

    private void parseCodeAttrBands(InputStream in, int codeFlagsCount) throws IOException, Pack200Exception {
        int lvttIndex;
        int[] counts;
        int lineNumberIndex;
        int lvtIndex;
        int lvttIndex2;
        AttributeLayout localVariableTypeTableLayout;
        int lineNumberIndex2;
        long[] codeFlags;
        int i;
        InputStream inputStream = in;
        long[] codeFlags2 = parseFlags("code_flags", inputStream, codeFlagsCount, Codec.UNSIGNED5, this.segment.getSegmentHeader().getOptions().hasCodeFlagsHi());
        int codeAttrCount = SegmentUtils.countBit16(codeFlags2);
        int[] codeAttrCounts = decodeBandInt("code_attr_count", inputStream, Codec.UNSIGNED5, codeAttrCount);
        int[][] codeAttrIndexes = decodeBandInt("code_attr_indexes", inputStream, Codec.UNSIGNED5, codeAttrCounts);
        int callCount = 0;
        for (int[] element : codeAttrIndexes) {
            for (int index : element) {
                callCount += this.attrMap.getAttributeLayout(index, 3).numBackwardsCallables();
            }
        }
        int[] codeAttrCalls = decodeBandInt("code_attr_calls", inputStream, Codec.UNSIGNED5, callCount);
        AttributeLayout lineNumberTableLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_LINE_NUMBER_TABLE, 3);
        int lineNumberTableCount = SegmentUtils.countMatches(codeFlags2, lineNumberTableLayout);
        int[] lineNumberTableN = decodeBandInt("code_LineNumberTable_N", inputStream, Codec.UNSIGNED5, lineNumberTableCount);
        int[][] lineNumberTableBciP = decodeBandInt("code_LineNumberTable_bci_P", inputStream, Codec.BCI5, lineNumberTableN);
        int[][] lineNumberTableLine = decodeBandInt("code_LineNumberTable_line", inputStream, Codec.UNSIGNED5, lineNumberTableN);
        AttributeLayout localVariableTableLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TABLE, 3);
        AttributeLayout localVariableTypeTableLayout2 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TYPE_TABLE, 3);
        int lengthLocalVariableNBand = SegmentUtils.countMatches(codeFlags2, localVariableTableLayout);
        int[] localVariableTableN = decodeBandInt("code_LocalVariableTable_N", inputStream, Codec.UNSIGNED5, lengthLocalVariableNBand);
        int[][] localVariableTableBciP = decodeBandInt("code_LocalVariableTable_bci_P", inputStream, Codec.BCI5, localVariableTableN);
        int[][] localVariableTableSpanO = decodeBandInt("code_LocalVariableTable_span_O", inputStream, Codec.BRANCH5, localVariableTableN);
        CPUTF8[][] localVariableTableNameRU = parseCPUTF8References("code_LocalVariableTable_name_RU", inputStream, Codec.UNSIGNED5, localVariableTableN);
        CPUTF8[][] localVariableTableTypeRS = parseCPSignatureReferences("code_LocalVariableTable_type_RS", inputStream, Codec.UNSIGNED5, localVariableTableN);
        int[][] localVariableTableSlot = decodeBandInt("code_LocalVariableTable_slot", inputStream, Codec.UNSIGNED5, localVariableTableN);
        int lengthLocalVariableTypeTableNBand = SegmentUtils.countMatches(codeFlags2, localVariableTypeTableLayout2);
        int[] localVariableTypeTableN = decodeBandInt("code_LocalVariableTypeTable_N", inputStream, Codec.UNSIGNED5, lengthLocalVariableTypeTableNBand);
        int[][] localVariableTypeTableBciP = decodeBandInt("code_LocalVariableTypeTable_bci_P", inputStream, Codec.BCI5, localVariableTypeTableN);
        int[][] localVariableTypeTableSpanO = decodeBandInt("code_LocalVariableTypeTable_span_O", inputStream, Codec.BRANCH5, localVariableTypeTableN);
        CPUTF8[][] localVariableTypeTableNameRU = parseCPUTF8References("code_LocalVariableTypeTable_name_RU", inputStream, Codec.UNSIGNED5, localVariableTypeTableN);
        CPUTF8[][] localVariableTypeTableTypeRS = parseCPSignatureReferences("code_LocalVariableTypeTable_type_RS", inputStream, Codec.UNSIGNED5, localVariableTypeTableN);
        int[][] localVariableTypeTableSlot = decodeBandInt("code_LocalVariableTypeTable_slot", inputStream, Codec.UNSIGNED5, localVariableTypeTableN);
        int limit = this.options.hasCodeFlagsHi() ? 62 : 31;
        AttributeLayout[] otherLayouts = new AttributeLayout[limit + 1];
        int backwardsCallIndex = limit + 1;
        int[] counts2 = new int[backwardsCallIndex];
        int lengthLocalVariableTypeTableNBand2 = limit + 1;
        List<Attribute>[] otherAttributes = new List[lengthLocalVariableTypeTableNBand2];
        int i2 = 0;
        while (i2 < limit) {
            int limit2 = limit;
            int lineNumberTableCount2 = lineNumberTableCount;
            AttributeLayout layout = this.attrMap.getAttributeLayout(i2, 3);
            if (layout != null && !layout.isDefaultLayout()) {
                otherLayouts[i2] = layout;
                counts2[i2] = SegmentUtils.countMatches(codeFlags2, layout);
            }
            i2++;
            limit = limit2;
            lineNumberTableCount = lineNumberTableCount2;
        }
        int i3 = 0;
        int backwardsCallIndex2 = 0;
        while (i3 < counts2.length) {
            if (counts2[i3] <= 0) {
                codeFlags = codeFlags2;
                i = i3;
            } else {
                codeFlags = codeFlags2;
                NewAttributeBands bands = this.attrMap.getAttributeBands(otherLayouts[i3]);
                otherAttributes[i3] = bands.parseAttributes(inputStream, counts2[i3]);
                int numBackwardsCallables = otherLayouts[i3].numBackwardsCallables();
                if (numBackwardsCallables <= 0) {
                    i = i3;
                } else {
                    int[] backwardsCalls = new int[numBackwardsCallables];
                    i = i3;
                    System.arraycopy(codeAttrCalls, backwardsCallIndex2, backwardsCalls, 0, numBackwardsCallables);
                    bands.setBackwardsCalls(backwardsCalls);
                    backwardsCallIndex2 += numBackwardsCallables;
                }
            }
            i3 = i + 1;
            inputStream = in;
            codeFlags2 = codeFlags;
        }
        long[] codeFlags3 = codeFlags2;
        int lineNumberIndex3 = 0;
        int lvtIndex2 = 0;
        int lvttIndex3 = 0;
        int i4 = 0;
        while (true) {
            int lineNumberIndex4 = lineNumberIndex3;
            if (i4 < codeFlagsCount) {
                int lvtIndex3 = lvtIndex2;
                if (!lineNumberTableLayout.matches(codeFlags3[i4])) {
                    lvttIndex = lvttIndex3;
                    counts = counts2;
                    lineNumberIndex = lineNumberIndex4;
                } else {
                    lvttIndex = lvttIndex3;
                    counts = counts2;
                    LineNumberTableAttribute lnta = new LineNumberTableAttribute(lineNumberTableN[lineNumberIndex4], lineNumberTableBciP[lineNumberIndex4], lineNumberTableLine[lineNumberIndex4]);
                    this.codeAttributes[i4].add(lnta);
                    lineNumberIndex = lineNumberIndex4 + 1;
                }
                int lineNumberIndex5 = lineNumberIndex;
                if (!localVariableTableLayout.matches(codeFlags3[i4])) {
                    lvtIndex = lvtIndex3;
                } else {
                    LocalVariableTableAttribute lvta = new LocalVariableTableAttribute(localVariableTableN[lvtIndex3], localVariableTableBciP[lvtIndex3], localVariableTableSpanO[lvtIndex3], localVariableTableNameRU[lvtIndex3], localVariableTableTypeRS[lvtIndex3], localVariableTableSlot[lvtIndex3]);
                    lvtIndex = lvtIndex3 + 1;
                    this.codeAttributes[i4].add(lvta);
                }
                int lvtIndex4 = lvtIndex;
                if (!localVariableTypeTableLayout2.matches(codeFlags3[i4])) {
                    lvttIndex2 = lvttIndex;
                } else {
                    LocalVariableTypeTableAttribute lvtta = new LocalVariableTypeTableAttribute(localVariableTypeTableN[lvttIndex], localVariableTypeTableBciP[lvttIndex], localVariableTypeTableSpanO[lvttIndex], localVariableTypeTableNameRU[lvttIndex], localVariableTypeTableTypeRS[lvttIndex], localVariableTypeTableSlot[lvttIndex]);
                    lvttIndex2 = lvttIndex + 1;
                    this.codeAttributes[i4].add(lvtta);
                }
                int j = 0;
                while (j < otherLayouts.length) {
                    if (otherLayouts[j] != null) {
                        localVariableTypeTableLayout = localVariableTypeTableLayout2;
                        lineNumberIndex2 = lineNumberIndex5;
                        if (otherLayouts[j].matches(codeFlags3[i4])) {
                            this.codeAttributes[i4].add(otherAttributes[j].get(0));
                            otherAttributes[j].remove(0);
                        }
                    } else {
                        localVariableTypeTableLayout = localVariableTypeTableLayout2;
                        lineNumberIndex2 = lineNumberIndex5;
                    }
                    j++;
                    localVariableTypeTableLayout2 = localVariableTypeTableLayout;
                    lineNumberIndex5 = lineNumberIndex2;
                }
                int lineNumberIndex6 = lineNumberIndex5;
                i4++;
                lvtIndex2 = lvtIndex4;
                lvttIndex3 = lvttIndex2;
                counts2 = counts;
                lineNumberIndex3 = lineNumberIndex6;
            } else {
                return;
            }
        }
    }

    private void parseCodeBands(InputStream in) throws Pack200Exception, IOException {
        int i;
        int i2 = 2;
        AttributeLayout layout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_CODE, 2);
        int codeCount = SegmentUtils.countMatches(this.methodFlags, layout);
        int[] codeHeaders = decodeBandInt("code_headers", in, Codec.BYTE1, codeCount);
        boolean allCodeHasFlags = this.segment.getSegmentHeader().getOptions().hasAllCodeFlags();
        if (!allCodeHasFlags) {
            this.codeHasAttributes = new boolean[codeCount];
        }
        int codeSpecialHeader = 0;
        for (int i3 = 0; i3 < codeCount; i3++) {
            if (codeHeaders[i3] == 0) {
                codeSpecialHeader++;
                if (!allCodeHasFlags) {
                    this.codeHasAttributes[i3] = true;
                }
            }
        }
        int[] codeMaxStackSpecials = decodeBandInt("code_max_stack", in, Codec.UNSIGNED5, codeSpecialHeader);
        int[] codeMaxNALocalsSpecials = decodeBandInt("code_max_na_locals", in, Codec.UNSIGNED5, codeSpecialHeader);
        int[] codeHandlerCountSpecials = decodeBandInt("code_handler_count", in, Codec.UNSIGNED5, codeSpecialHeader);
        this.codeMaxStack = new int[codeCount];
        this.codeMaxNALocals = new int[codeCount];
        this.codeHandlerCount = new int[codeCount];
        int special = 0;
        int i4 = 0;
        while (i4 < codeCount) {
            int header = codeHeaders[i4] & 255;
            if (header < 0) {
                throw new IllegalStateException("Shouldn't get here");
            }
            if (header == 0) {
                this.codeMaxStack[i4] = codeMaxStackSpecials[special];
                this.codeMaxNALocals[i4] = codeMaxNALocalsSpecials[special];
                this.codeHandlerCount[i4] = codeHandlerCountSpecials[special];
                special++;
                i = i2;
            } else {
                i = i2;
                if (header <= 144) {
                    this.codeMaxStack[i4] = (header - 1) % 12;
                    this.codeMaxNALocals[i4] = (header - 1) / 12;
                    this.codeHandlerCount[i4] = 0;
                } else if (header <= 208) {
                    this.codeMaxStack[i4] = (header - 145) % 8;
                    this.codeMaxNALocals[i4] = (header - 145) / 8;
                    this.codeHandlerCount[i4] = 1;
                } else if (header <= 255) {
                    this.codeMaxStack[i4] = (header - 209) % 7;
                    this.codeMaxNALocals[i4] = (header - 209) / 7;
                    this.codeHandlerCount[i4] = i;
                } else {
                    throw new IllegalStateException("Shouldn't get here either");
                }
            }
            i4++;
            i2 = i;
        }
        this.codeHandlerStartP = decodeBandInt("code_handler_start_P", in, Codec.BCI5, this.codeHandlerCount);
        this.codeHandlerEndPO = decodeBandInt("code_handler_end_PO", in, Codec.BRANCH5, this.codeHandlerCount);
        this.codeHandlerCatchPO = decodeBandInt("code_handler_catch_PO", in, Codec.BRANCH5, this.codeHandlerCount);
        this.codeHandlerClassRCN = decodeBandInt("code_handler_class_RCN", in, Codec.UNSIGNED5, this.codeHandlerCount);
        int codeFlagsCount = allCodeHasFlags ? codeCount : codeSpecialHeader;
        this.codeAttributes = new List[codeFlagsCount];
        Arrays.setAll(this.codeAttributes, new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.ClassBands$$ExternalSyntheticLambda0
            @Override // java.util.function.IntFunction
            public final Object apply(int i5) {
                return ClassBands.lambda$parseCodeBands$1(i5);
            }
        });
        parseCodeAttrBands(in, codeFlagsCount);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$parseCodeBands$1(int i) {
        return new ArrayList();
    }

    private void parseFieldAttrBands(InputStream in) throws IOException, Pack200Exception {
        int i;
        long flag;
        int othersAddedAtStart;
        int i2;
        int i3;
        AttributeLayout deprecatedLayout;
        long flag2;
        InputStream inputStream = in;
        this.fieldFlags = parseFlags("field_flags", inputStream, this.classFieldCount, Codec.UNSIGNED5, this.options.hasFieldFlagsHi());
        int fieldAttrCount = SegmentUtils.countBit16(this.fieldFlags);
        int[] fieldAttrCounts = decodeBandInt("field_attr_count", inputStream, Codec.UNSIGNED5, fieldAttrCount);
        int[][] fieldAttrIndexes = decodeBandInt("field_attr_indexes", inputStream, Codec.UNSIGNED5, fieldAttrCounts);
        int callCount = getCallCount(fieldAttrIndexes, this.fieldFlags, 1);
        int[] fieldAttrCalls = decodeBandInt("field_attr_calls", inputStream, Codec.UNSIGNED5, callCount);
        this.fieldAttributes = new ArrayList[this.classCount];
        for (int i4 = 0; i4 < this.classCount; i4++) {
            this.fieldAttributes[i4] = new ArrayList[this.fieldFlags[i4].length];
            for (int j = 0; j < this.fieldFlags[i4].length; j++) {
                this.fieldAttributes[i4][j] = new ArrayList<>();
            }
        }
        AttributeLayout constantValueLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_CONSTANT_VALUE, 1);
        int constantCount = SegmentUtils.countMatches(this.fieldFlags, constantValueLayout);
        int[] field_constantValue_KQ = decodeBandInt("field_ConstantValue_KQ", inputStream, Codec.UNSIGNED5, constantCount);
        int constantValueIndex = 0;
        AttributeLayout signatureLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE, 1);
        int signatureCount = SegmentUtils.countMatches(this.fieldFlags, signatureLayout);
        int[] fieldSignatureRS = decodeBandInt("field_Signature_RS", inputStream, Codec.UNSIGNED5, signatureCount);
        int signatureIndex = 0;
        AttributeLayout deprecatedLayout2 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 1);
        int i5 = 0;
        while (i5 < this.classCount) {
            int j2 = 0;
            while (true) {
                i3 = i5;
                if (j2 < this.fieldFlags[i3].length) {
                    int[][] fieldAttrIndexes2 = fieldAttrIndexes;
                    int callCount2 = callCount;
                    long flag3 = this.fieldFlags[i3][j2];
                    if (!deprecatedLayout2.matches(flag3)) {
                        deprecatedLayout = deprecatedLayout2;
                    } else {
                        deprecatedLayout = deprecatedLayout2;
                        this.fieldAttributes[i3][j2].add(new DeprecatedAttribute());
                    }
                    if (!constantValueLayout.matches(flag3)) {
                        flag2 = flag3;
                    } else {
                        flag2 = flag3;
                        long result = field_constantValue_KQ[constantValueIndex];
                        String desc = this.fieldDescr[i3][j2];
                        int colon = desc.indexOf(58);
                        String type = desc.substring(colon + 1);
                        if (type.equals("B") || type.equals("S") || type.equals("C") || type.equals("Z")) {
                            type = "I";
                        }
                        ClassFileEntry value = constantValueLayout.getValue(result, type, this.cpBands.getConstantPool());
                        this.fieldAttributes[i3][j2].add(new ConstantValueAttribute(value));
                        constantValueIndex++;
                    }
                    if (signatureLayout.matches(flag2)) {
                        long flag4 = fieldSignatureRS[signatureIndex];
                        String desc2 = this.fieldDescr[i3][j2];
                        int colon2 = desc2.indexOf(58);
                        CPUTF8 value2 = (CPUTF8) signatureLayout.getValue(flag4, desc2.substring(colon2 + 1), this.cpBands.getConstantPool());
                        this.fieldAttributes[i3][j2].add(new SignatureAttribute(value2));
                        signatureIndex++;
                    }
                    j2++;
                    i5 = i3;
                    fieldAttrIndexes = fieldAttrIndexes2;
                    callCount = callCount2;
                    deprecatedLayout2 = deprecatedLayout;
                }
            }
            i5 = i3 + 1;
        }
        int backwardsCallIndex = parseFieldMetadataBands(inputStream, fieldAttrCalls);
        int limit = this.options.hasFieldFlagsHi() ? 62 : 31;
        AttributeLayout[] otherLayouts = new AttributeLayout[limit + 1];
        int[] counts = new int[limit + 1];
        List<Attribute>[] otherAttributes = new List[limit + 1];
        int backwardsCallIndex2 = 0;
        while (backwardsCallIndex2 < limit) {
            int limit2 = limit;
            List<Attribute>[] otherAttributes2 = otherAttributes;
            AttributeLayout layout = this.attrMap.getAttributeLayout(backwardsCallIndex2, 1);
            if (layout != null && !layout.isDefaultLayout()) {
                otherLayouts[backwardsCallIndex2] = layout;
                counts[backwardsCallIndex2] = SegmentUtils.countMatches(this.fieldFlags, layout);
            }
            backwardsCallIndex2++;
            limit = limit2;
            otherAttributes = otherAttributes2;
        }
        List<Attribute>[] otherAttributes3 = otherAttributes;
        int i6 = 0;
        int backwardsCallIndex3 = backwardsCallIndex;
        while (true) {
            int[] counts2 = counts;
            if (i6 >= counts.length) {
                break;
            }
            if (counts2[i6] <= 0) {
                i2 = i6;
            } else {
                NewAttributeBands bands = this.attrMap.getAttributeBands(otherLayouts[i6]);
                otherAttributes3[i6] = bands.parseAttributes(inputStream, counts2[i6]);
                int numBackwardsCallables = otherLayouts[i6].numBackwardsCallables();
                if (numBackwardsCallables <= 0) {
                    i2 = i6;
                } else {
                    i2 = i6;
                    int[] backwardsCalls = new int[numBackwardsCallables];
                    System.arraycopy(fieldAttrCalls, backwardsCallIndex3, backwardsCalls, 0, numBackwardsCallables);
                    bands.setBackwardsCalls(backwardsCalls);
                    backwardsCallIndex3 += numBackwardsCallables;
                }
            }
            i6 = i2 + 1;
            inputStream = in;
            counts = counts2;
        }
        int i7 = 0;
        while (i7 < this.classCount) {
            int j3 = 0;
            while (j3 < this.fieldFlags[i7].length) {
                int i8 = i7;
                int j4 = j3;
                long flag5 = this.fieldFlags[i7][j4];
                int othersAddedAtStart2 = 0;
                int backwardsCallIndex4 = backwardsCallIndex3;
                int backwardsCallIndex5 = 0;
                while (true) {
                    i = i8;
                    int i9 = otherLayouts.length;
                    if (backwardsCallIndex5 < i9) {
                        if (otherLayouts[backwardsCallIndex5] == null || !otherLayouts[backwardsCallIndex5].matches(flag5)) {
                            flag = flag5;
                        } else {
                            flag = flag5;
                            if (otherLayouts[backwardsCallIndex5].getIndex() < 15) {
                                othersAddedAtStart = 0;
                                this.fieldAttributes[i][j4].add(othersAddedAtStart2, otherAttributes3[backwardsCallIndex5].get(0));
                                othersAddedAtStart2++;
                            } else {
                                othersAddedAtStart = 0;
                                this.fieldAttributes[i][j4].add(otherAttributes3[backwardsCallIndex5].get(0));
                            }
                            otherAttributes3[backwardsCallIndex5].remove(othersAddedAtStart);
                        }
                        backwardsCallIndex5++;
                        i8 = i;
                        flag5 = flag;
                    }
                }
                j3 = j4 + 1;
                backwardsCallIndex3 = backwardsCallIndex4;
                i7 = i;
            }
            i7++;
        }
    }

    private void parseFieldBands(InputStream in) throws IOException, Pack200Exception {
        this.fieldDescrInts = decodeBandInt("field_descr", in, Codec.DELTA5, this.classFieldCount);
        this.fieldDescr = getReferences(this.fieldDescrInts, this.cpBands.getCpDescriptor());
        parseFieldAttrBands(in);
    }

    private int parseFieldMetadataBands(InputStream in, int[] fieldAttrCalls) throws Pack200Exception, IOException {
        int backwardsCallsUsed;
        String[] RxA;
        String[] RxA2 = {"RVA", "RIA"};
        AttributeLayout rvaLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS, 1);
        AttributeLayout riaLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS, 1);
        int rvaCount = SegmentUtils.countMatches(this.fieldFlags, rvaLayout);
        int riaCount = SegmentUtils.countMatches(this.fieldFlags, riaLayout);
        int[] RxACount = {rvaCount, riaCount};
        int[] backwardsCalls = {0, 0};
        if (rvaCount > 0) {
            backwardsCalls[0] = fieldAttrCalls[0];
            int backwardsCallsUsed2 = 0 + 1;
            if (riaCount <= 0) {
                backwardsCallsUsed = backwardsCallsUsed2;
            } else {
                backwardsCalls[1] = fieldAttrCalls[1];
                backwardsCallsUsed = backwardsCallsUsed2 + 1;
            }
        } else if (riaCount <= 0) {
            backwardsCallsUsed = 0;
        } else {
            backwardsCalls[1] = fieldAttrCalls[0];
            int backwardsCallsUsed3 = 0 + 1;
            backwardsCallsUsed = backwardsCallsUsed3;
        }
        MetadataBandGroup[] mb = parseMetadata(in, RxA2, RxACount, backwardsCalls, "field");
        List<Attribute> rvaAttributes = mb[0].getAttributes();
        List<Attribute> riaAttributes = mb[1].getAttributes();
        int rvaAttributesIndex = 0;
        int riaAttributesIndex = 0;
        int i = 0;
        while (i < this.fieldFlags.length) {
            int j = 0;
            while (true) {
                RxA = RxA2;
                if (j < this.fieldFlags[i].length) {
                    int[] RxACount2 = RxACount;
                    int[] backwardsCalls2 = backwardsCalls;
                    if (rvaLayout.matches(this.fieldFlags[i][j])) {
                        this.fieldAttributes[i][j].add(rvaAttributes.get(rvaAttributesIndex));
                        rvaAttributesIndex++;
                    }
                    if (riaLayout.matches(this.fieldFlags[i][j])) {
                        this.fieldAttributes[i][j].add(riaAttributes.get(riaAttributesIndex));
                        riaAttributesIndex++;
                    }
                    j++;
                    RxA2 = RxA;
                    RxACount = RxACount2;
                    backwardsCalls = backwardsCalls2;
                }
            }
            i++;
            RxA2 = RxA;
        }
        return backwardsCallsUsed;
    }

    private MetadataBandGroup[] parseMetadata(InputStream in, String[] RxA, int[] RxACount, int[] backwardsCallCounts, String contextName) throws IOException, Pack200Exception {
        int pairCount;
        InputStream inputStream = in;
        String[] strArr = RxA;
        MetadataBandGroup[] mbg = new MetadataBandGroup[strArr.length];
        int atCount = 0;
        while (atCount < strArr.length) {
            mbg[atCount] = new MetadataBandGroup(strArr[atCount], this.cpBands);
            String rxa = strArr[atCount];
            if (rxa.indexOf(80) >= 0) {
                mbg[atCount].param_NB = decodeBandInt(contextName + "_" + rxa + "_param_NB", inputStream, Codec.BYTE1, RxACount[atCount]);
            }
            int pairCount2 = 0;
            if (!rxa.equals("AD")) {
                mbg[atCount].anno_N = decodeBandInt(contextName + "_" + rxa + "_anno_N", inputStream, Codec.UNSIGNED5, RxACount[atCount]);
                mbg[atCount].type_RS = parseCPSignatureReferences(contextName + "_" + rxa + "_type_RS", inputStream, Codec.UNSIGNED5, mbg[atCount].anno_N);
                mbg[atCount].pair_N = decodeBandInt(contextName + "_" + rxa + "_pair_N", inputStream, Codec.UNSIGNED5, mbg[atCount].anno_N);
                for (int[] element : mbg[atCount].pair_N) {
                    for (int element2 : element) {
                        pairCount2 += element2;
                    }
                }
                mbg[atCount].name_RU = parseCPUTF8References(contextName + "_" + rxa + "_name_RU", inputStream, Codec.UNSIGNED5, pairCount2);
                pairCount = pairCount2;
            } else {
                int pairCount3 = RxACount[atCount];
                pairCount = pairCount3;
            }
            mbg[atCount].T = decodeBandInt(contextName + "_" + rxa + "_T", inputStream, Codec.BYTE1, backwardsCallCounts[atCount] + pairCount);
            int[] iArr = mbg[atCount].T;
            int ICount = iArr.length;
            MetadataBandGroup[] mbg2 = mbg;
            int arrayCount = 0;
            int atCount2 = atCount;
            int i = 0;
            int sCount = 0;
            int sCount2 = 0;
            int eCount = 0;
            int DCount = 0;
            int FCount = 0;
            int JCount = 0;
            int cCount = 0;
            int i2 = 0;
            while (i2 < ICount) {
                int i3 = ICount;
                int element3 = iArr[i2];
                int i4 = i2;
                char c = (char) element3;
                switch (c) {
                    case '@':
                        i++;
                        break;
                    case 'B':
                    case 'C':
                    case 'I':
                    case 'S':
                    case 'Z':
                        cCount++;
                        break;
                    case 'D':
                        eCount++;
                        break;
                    case 'F':
                        DCount++;
                        break;
                    case 'J':
                        FCount++;
                        break;
                    case '[':
                        arrayCount++;
                        break;
                    case 'c':
                        JCount++;
                        break;
                    case 'e':
                        sCount2++;
                        break;
                    case 's':
                        sCount++;
                        break;
                }
                i2 = i4 + 1;
                ICount = i3;
            }
            mbg2[atCount2].caseI_KI = parseCPIntReferences(contextName + "_" + rxa + "_caseI_KI", inputStream, Codec.UNSIGNED5, cCount);
            mbg2[atCount2].caseD_KD = parseCPDoubleReferences(contextName + "_" + rxa + "_caseD_KD", inputStream, Codec.UNSIGNED5, eCount);
            mbg2[atCount2].caseF_KF = parseCPFloatReferences(contextName + "_" + rxa + "_caseF_KF", inputStream, Codec.UNSIGNED5, DCount);
            mbg2[atCount2].caseJ_KJ = parseCPLongReferences(contextName + "_" + rxa + "_caseJ_KJ", inputStream, Codec.UNSIGNED5, FCount);
            mbg2[atCount2].casec_RS = parseCPSignatureReferences(contextName + "_" + rxa + "_casec_RS", inputStream, Codec.UNSIGNED5, JCount);
            int eCount2 = sCount2;
            mbg2[atCount2].caseet_RS = parseReferences(contextName + "_" + rxa + "_caseet_RS", inputStream, Codec.UNSIGNED5, eCount2, this.cpBands.getCpSignature());
            inputStream = in;
            mbg2[atCount2].caseec_RU = parseReferences(contextName + "_" + rxa + "_caseec_RU", inputStream, Codec.UNSIGNED5, eCount2, this.cpBands.getCpUTF8());
            mbg2[atCount2].cases_RU = parseCPUTF8References(contextName + "_" + rxa + "_cases_RU", inputStream, Codec.UNSIGNED5, sCount);
            mbg2[atCount2].casearray_N = decodeBandInt(contextName + "_" + rxa + "_casearray_N", inputStream, Codec.UNSIGNED5, arrayCount);
            mbg2[atCount2].nesttype_RS = parseCPUTF8References(contextName + "_" + rxa + "_nesttype_RS", inputStream, Codec.UNSIGNED5, i);
            mbg2[atCount2].nestpair_N = decodeBandInt(contextName + "_" + rxa + "_nestpair_N", inputStream, Codec.UNSIGNED5, i);
            int nestPairCount = 0;
            for (int element4 : mbg2[atCount2].nestpair_N) {
                nestPairCount += element4;
            }
            mbg2[atCount2].nestname_RU = parseCPUTF8References(contextName + "_" + rxa + "_nestname_RU", inputStream, Codec.UNSIGNED5, nestPairCount);
            atCount = atCount2 + 1;
            strArr = RxA;
            mbg = mbg2;
        }
        return mbg;
    }

    private void parseMethodAttrBands(InputStream in) throws IOException, Pack200Exception {
        List<Attribute>[] otherAttributes;
        int backwardsCallIndex;
        long flag;
        int othersAddedAtStart;
        int[] counts;
        int i;
        int[] methodAttrCounts;
        int j;
        int i2;
        AttributeLayout methodExceptionsLayout;
        InputStream inputStream = in;
        this.methodFlags = parseFlags("method_flags", inputStream, this.classMethodCount, Codec.UNSIGNED5, this.options.hasMethodFlagsHi());
        int methodAttrCount = SegmentUtils.countBit16(this.methodFlags);
        int[] methodAttrCounts2 = decodeBandInt("method_attr_count", inputStream, Codec.UNSIGNED5, methodAttrCount);
        int[][] methodAttrIndexes = decodeBandInt("method_attr_indexes", inputStream, Codec.UNSIGNED5, methodAttrCounts2);
        int callCount = getCallCount(methodAttrIndexes, this.methodFlags, 2);
        this.methodAttrCalls = decodeBandInt("method_attr_calls", inputStream, Codec.UNSIGNED5, callCount);
        this.methodAttributes = new ArrayList[this.classCount];
        for (int i3 = 0; i3 < this.classCount; i3++) {
            this.methodAttributes[i3] = new ArrayList[this.methodFlags[i3].length];
            for (int j2 = 0; j2 < this.methodFlags[i3].length; j2++) {
                this.methodAttributes[i3][j2] = new ArrayList<>();
            }
        }
        AttributeLayout methodExceptionsLayout2 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_EXCEPTIONS, 2);
        int count = SegmentUtils.countMatches(this.methodFlags, methodExceptionsLayout2);
        int[] numExceptions = decodeBandInt("method_Exceptions_n", inputStream, Codec.UNSIGNED5, count);
        int[][] methodExceptionsRS = decodeBandInt("method_Exceptions_RC", inputStream, Codec.UNSIGNED5, numExceptions);
        AttributeLayout methodSignatureLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE, 2);
        int count1 = SegmentUtils.countMatches(this.methodFlags, methodSignatureLayout);
        int[] methodSignatureRS = decodeBandInt("method_signature_RS", inputStream, Codec.UNSIGNED5, count1);
        AttributeLayout deprecatedLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 2);
        int methodExceptionsIndex = 0;
        int methodSignatureIndex = 0;
        int i4 = 0;
        while (true) {
            int methodAttrCount2 = methodAttrCount;
            if (i4 >= this.methodAttributes.length) {
                break;
            }
            int j3 = 0;
            while (true) {
                methodAttrCounts = methodAttrCounts2;
                if (j3 < this.methodAttributes[i4].length) {
                    int[][] methodAttrIndexes2 = methodAttrIndexes;
                    int callCount2 = callCount;
                    long flag2 = this.methodFlags[i4][j3];
                    if (!methodExceptionsLayout2.matches(flag2)) {
                        j = j3;
                        i2 = i4;
                    } else {
                        int n = numExceptions[methodExceptionsIndex];
                        int[] exceptions = methodExceptionsRS[methodExceptionsIndex];
                        j = j3;
                        CPClass[] exceptionClasses = new CPClass[n];
                        i2 = i4;
                        int i5 = 0;
                        while (i5 < n) {
                            int k = i5;
                            exceptionClasses[k] = this.cpBands.cpClassValue(exceptions[k]);
                            i5 = k + 1;
                            n = n;
                        }
                        this.methodAttributes[i2][j].add(new ExceptionsAttribute(exceptionClasses));
                        methodExceptionsIndex++;
                    }
                    if (!methodSignatureLayout.matches(flag2)) {
                        methodExceptionsLayout = methodExceptionsLayout2;
                    } else {
                        long result = methodSignatureRS[methodSignatureIndex];
                        String desc = this.methodDescr[i2][j];
                        methodExceptionsLayout = methodExceptionsLayout2;
                        int colon = desc.indexOf(58);
                        String type = desc.substring(colon + 1);
                        if (type.equals("B") || type.equals("H")) {
                            type = "I";
                        }
                        CPUTF8 value = (CPUTF8) methodSignatureLayout.getValue(result, type, this.cpBands.getConstantPool());
                        this.methodAttributes[i2][j].add(new SignatureAttribute(value));
                        methodSignatureIndex++;
                    }
                    if (deprecatedLayout.matches(flag2)) {
                        this.methodAttributes[i2][j].add(new DeprecatedAttribute());
                    }
                    j3 = j + 1;
                    methodAttrCounts2 = methodAttrCounts;
                    methodAttrIndexes = methodAttrIndexes2;
                    callCount = callCount2;
                    methodExceptionsLayout2 = methodExceptionsLayout;
                    i4 = i2;
                }
            }
            i4++;
            methodAttrCount = methodAttrCount2;
            methodAttrCounts2 = methodAttrCounts;
        }
        int backwardsCallIndex2 = parseMethodMetadataBands(inputStream, this.methodAttrCalls);
        int limit = this.options.hasMethodFlagsHi() ? 62 : 31;
        AttributeLayout[] otherLayouts = new AttributeLayout[limit + 1];
        int[] counts2 = new int[limit + 1];
        int i6 = 0;
        while (i6 < limit) {
            int backwardsCallIndex3 = backwardsCallIndex2;
            AttributeLayout layout = this.attrMap.getAttributeLayout(i6, 2);
            if (layout != null && !layout.isDefaultLayout()) {
                otherLayouts[i6] = layout;
                counts2[i6] = SegmentUtils.countMatches(this.methodFlags, layout);
            }
            i6++;
            backwardsCallIndex2 = backwardsCallIndex3;
        }
        int backwardsCallIndex4 = backwardsCallIndex2;
        int backwardsCallIndex5 = limit + 1;
        List<Attribute>[] otherAttributes2 = new List[backwardsCallIndex5];
        int i7 = 0;
        int backwardsCallIndex6 = backwardsCallIndex4;
        while (true) {
            otherAttributes = otherAttributes2;
            int limit2 = limit;
            if (i7 >= counts2.length) {
                break;
            }
            if (counts2[i7] <= 0) {
                counts = counts2;
                i = i7;
            } else {
                NewAttributeBands bands = this.attrMap.getAttributeBands(otherLayouts[i7]);
                otherAttributes[i7] = bands.parseAttributes(inputStream, counts2[i7]);
                int numBackwardsCallables = otherLayouts[i7].numBackwardsCallables();
                if (numBackwardsCallables <= 0) {
                    counts = counts2;
                    i = i7;
                } else {
                    int[] backwardsCalls = new int[numBackwardsCallables];
                    counts = counts2;
                    i = i7;
                    System.arraycopy(this.methodAttrCalls, backwardsCallIndex6, backwardsCalls, 0, numBackwardsCallables);
                    bands.setBackwardsCalls(backwardsCalls);
                    backwardsCallIndex6 += numBackwardsCallables;
                }
            }
            i7 = i + 1;
            inputStream = in;
            otherAttributes2 = otherAttributes;
            limit = limit2;
            counts2 = counts;
        }
        int i8 = 0;
        while (i8 < this.methodAttributes.length) {
            int j4 = 0;
            while (j4 < this.methodAttributes[i8].length) {
                int i9 = i8;
                int j5 = j4;
                long flag3 = this.methodFlags[i8][j5];
                int othersAddedAtStart2 = 0;
                int i10 = 0;
                while (true) {
                    backwardsCallIndex = backwardsCallIndex6;
                    int backwardsCallIndex7 = otherLayouts.length;
                    if (i10 < backwardsCallIndex7) {
                        if (otherLayouts[i10] == null || !otherLayouts[i10].matches(flag3)) {
                            flag = flag3;
                        } else {
                            flag = flag3;
                            if (otherLayouts[i10].getIndex() < 15) {
                                othersAddedAtStart = 0;
                                this.methodAttributes[i9][j5].add(othersAddedAtStart2, otherAttributes[i10].get(0));
                                othersAddedAtStart2++;
                            } else {
                                othersAddedAtStart = 0;
                                this.methodAttributes[i9][j5].add(otherAttributes[i10].get(0));
                            }
                            otherAttributes[i10].remove(othersAddedAtStart);
                        }
                        i10++;
                        backwardsCallIndex6 = backwardsCallIndex;
                        flag3 = flag;
                    }
                }
                j4 = j5 + 1;
                i8 = i9;
                backwardsCallIndex6 = backwardsCallIndex;
            }
            i8++;
        }
    }

    private void parseMethodBands(InputStream in) throws IOException, Pack200Exception {
        this.methodDescrInts = decodeBandInt("method_descr", in, Codec.MDELTA5, this.classMethodCount);
        this.methodDescr = getReferences(this.methodDescrInts, this.cpBands.getCpDescriptor());
        parseMethodAttrBands(in);
    }

    /* JADX WARN: Incorrect condition in loop: B:3:0x0068 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int parseMethodMetadataBands(java.io.InputStream r23, int[] r24) throws org.apache.commons.compress.harmony.pack200.Pack200Exception, java.io.IOException {
        /*
            Method dump skipped, instructions count: 265
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.unpack200.ClassBands.parseMethodMetadataBands(java.io.InputStream, int[]):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$parseMethodMetadataBands$2$org-apache-commons-compress-harmony-unpack200-ClassBands, reason: not valid java name */
    public /* synthetic */ int m2078xf85b1228(AttributeLayout[] rxaLayouts, int i) {
        return SegmentUtils.countMatches(this.methodFlags, rxaLayouts[i]);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void read(InputStream in) throws IOException, Pack200Exception {
        int classCount = this.header.getClassCount();
        this.classThisInts = decodeBandInt("class_this", in, Codec.DELTA5, classCount);
        this.classThis = getReferences(this.classThisInts, this.cpBands.getCpClass());
        this.classSuperInts = decodeBandInt("class_super", in, Codec.DELTA5, classCount);
        int[] classInterfaceLengths = decodeBandInt("class_interface_count", in, Codec.DELTA5, classCount);
        this.classInterfacesInts = decodeBandInt("class_interface", in, Codec.DELTA5, classInterfaceLengths);
        this.classFieldCount = decodeBandInt("class_field_count", in, Codec.DELTA5, classCount);
        this.classMethodCount = decodeBandInt("class_method_count", in, Codec.DELTA5, classCount);
        parseFieldBands(in);
        parseMethodBands(in);
        parseClassAttrBands(in);
        parseCodeBands(in);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void unpack() {
    }
}
