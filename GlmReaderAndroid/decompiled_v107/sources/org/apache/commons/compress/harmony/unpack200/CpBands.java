package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPDouble;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFieldRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFloat;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInteger;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInterfaceMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPLong;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPNameAndType;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPString;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;

/* loaded from: classes9.dex */
public class CpBands extends BandSet {
    private static final String EMPTY_STRING = "";
    private int classOffset;
    private String[] cpClass;
    private int[] cpClassInts;
    private String[] cpDescriptor;
    private int[] cpDescriptorNameInts;
    private int[] cpDescriptorTypeInts;
    private double[] cpDouble;
    private String[] cpFieldClass;
    private int[] cpFieldClassInts;
    private String[] cpFieldDescriptor;
    private int[] cpFieldDescriptorInts;
    private float[] cpFloat;
    private String[] cpIMethodClass;
    private int[] cpIMethodClassInts;
    private String[] cpIMethodDescriptor;
    private int[] cpIMethodDescriptorInts;
    private int[] cpInt;
    private long[] cpLong;
    private String[] cpMethodClass;
    private int[] cpMethodClassInts;
    private String[] cpMethodDescriptor;
    private int[] cpMethodDescriptorInts;
    private String[] cpSignature;
    private int[] cpSignatureInts;
    private String[] cpString;
    private int[] cpStringInts;
    private String[] cpUTF8;
    private int descrOffset;
    private final Map<String, CPNameAndType> descriptorsToCPNameAndTypes;
    private int doubleOffset;
    private final Map<Double, CPDouble> doublesToCPDoubles;
    private int fieldOffset;
    private int floatOffset;
    private final Map<Float, CPFloat> floatsToCPFloats;
    private int imethodOffset;
    private int intOffset;
    private final Map<Integer, CPInteger> integersToCPIntegers;
    private int longOffset;
    private final Map<Long, CPLong> longsToCPLongs;
    private Map<String, Integer> mapClass;
    private Map<String, Integer> mapDescriptor;
    private Map<String, Integer> mapSignature;
    private Map<String, Integer> mapUTF8;
    private int methodOffset;
    private final SegmentConstantPool pool;
    private int signatureOffset;
    private int stringOffset;
    private final Map<String, CPClass> stringsToCPClass;
    private final Map<String, CPString> stringsToCPStrings;
    private final Map<String, CPUTF8> stringsToCPUTF8;

    public CpBands(Segment segment) {
        super(segment);
        this.pool = new SegmentConstantPool(this);
        this.stringsToCPUTF8 = new HashMap();
        this.stringsToCPStrings = new HashMap();
        this.longsToCPLongs = new HashMap();
        this.integersToCPIntegers = new HashMap();
        this.floatsToCPFloats = new HashMap();
        this.stringsToCPClass = new HashMap();
        this.doublesToCPDoubles = new HashMap();
        this.descriptorsToCPNameAndTypes = new HashMap();
    }

    public CPClass cpClassValue(int index) {
        String string = this.cpClass[index];
        final int utf8Index = this.cpClassInts[index];
        final int globalIndex = this.classOffset + index;
        return this.stringsToCPClass.computeIfAbsent(string, new Function() { // from class: org.apache.commons.compress.harmony.unpack200.CpBands$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CpBands.this.m2079xd8e60d18(utf8Index, globalIndex, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$cpClassValue$0$org-apache-commons-compress-harmony-unpack200-CpBands, reason: not valid java name */
    public /* synthetic */ CPClass m2079xd8e60d18(int utf8Index, int globalIndex, String k) {
        return new CPClass(cpUTF8Value(utf8Index), globalIndex);
    }

    public CPClass cpClassValue(String string) {
        CPClass cpString = this.stringsToCPClass.get(string);
        if (cpString == null) {
            Integer index = this.mapClass.get(string);
            if (index != null) {
                return cpClassValue(index.intValue());
            }
            CPClass cpString2 = new CPClass(cpUTF8Value(string, false), -1);
            this.stringsToCPClass.put(string, cpString2);
            return cpString2;
        }
        return cpString;
    }

    public CPDouble cpDoubleValue(final int index) {
        return this.doublesToCPDoubles.computeIfAbsent(Double.valueOf(this.cpDouble[index]), new Function() { // from class: org.apache.commons.compress.harmony.unpack200.CpBands$$ExternalSyntheticLambda9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CpBands.this.m2080x6e417fcc(index, (Double) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$cpDoubleValue$1$org-apache-commons-compress-harmony-unpack200-CpBands, reason: not valid java name */
    public /* synthetic */ CPDouble m2080x6e417fcc(int index, Double k) {
        return new CPDouble(k, this.doubleOffset + index);
    }

    public CPFieldRef cpFieldValue(int index) {
        return new CPFieldRef(cpClassValue(this.cpFieldClassInts[index]), cpNameAndTypeValue(this.cpFieldDescriptorInts[index]), this.fieldOffset + index);
    }

    public CPFloat cpFloatValue(final int index) {
        return this.floatsToCPFloats.computeIfAbsent(Float.valueOf(this.cpFloat[index]), new Function() { // from class: org.apache.commons.compress.harmony.unpack200.CpBands$$ExternalSyntheticLambda7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CpBands.this.m2081xc792a936(index, (Float) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$cpFloatValue$2$org-apache-commons-compress-harmony-unpack200-CpBands, reason: not valid java name */
    public /* synthetic */ CPFloat m2081xc792a936(int index, Float k) {
        return new CPFloat(Float.valueOf(this.cpFloat[index]), this.floatOffset + index);
    }

    public CPInterfaceMethodRef cpIMethodValue(int index) {
        return new CPInterfaceMethodRef(cpClassValue(this.cpIMethodClassInts[index]), cpNameAndTypeValue(this.cpIMethodDescriptorInts[index]), this.imethodOffset + index);
    }

    public CPInteger cpIntegerValue(final int index) {
        return this.integersToCPIntegers.computeIfAbsent(Integer.valueOf(this.cpInt[index]), new Function() { // from class: org.apache.commons.compress.harmony.unpack200.CpBands$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CpBands.this.m2082x76991055(index, (Integer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$cpIntegerValue$3$org-apache-commons-compress-harmony-unpack200-CpBands, reason: not valid java name */
    public /* synthetic */ CPInteger m2082x76991055(int index, Integer k) {
        return new CPInteger(k, this.intOffset + index);
    }

    public CPLong cpLongValue(final int index) {
        return this.longsToCPLongs.computeIfAbsent(Long.valueOf(this.cpLong[index]), new Function() { // from class: org.apache.commons.compress.harmony.unpack200.CpBands$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CpBands.this.m2083xfc12da44(index, (Long) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$cpLongValue$4$org-apache-commons-compress-harmony-unpack200-CpBands, reason: not valid java name */
    public /* synthetic */ CPLong m2083xfc12da44(int index, Long k) {
        return new CPLong(k, this.longOffset + index);
    }

    public CPMethodRef cpMethodValue(int index) {
        return new CPMethodRef(cpClassValue(this.cpMethodClassInts[index]), cpNameAndTypeValue(this.cpMethodDescriptorInts[index]), this.methodOffset + index);
    }

    public CPNameAndType cpNameAndTypeValue(final int index) {
        String descriptor = this.cpDescriptor[index];
        return this.descriptorsToCPNameAndTypes.computeIfAbsent(descriptor, new Function() { // from class: org.apache.commons.compress.harmony.unpack200.CpBands$$ExternalSyntheticLambda6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CpBands.this.m2084x5349ab6f(index, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$cpNameAndTypeValue$5$org-apache-commons-compress-harmony-unpack200-CpBands, reason: not valid java name */
    public /* synthetic */ CPNameAndType m2084x5349ab6f(int index, String k) {
        int nameIndex = this.cpDescriptorNameInts[index];
        int descriptorIndex = this.cpDescriptorTypeInts[index];
        CPUTF8 name = cpUTF8Value(nameIndex);
        CPUTF8 descriptorU = cpSignatureValue(descriptorIndex);
        return new CPNameAndType(name, descriptorU, this.descrOffset + index);
    }

    public CPNameAndType cpNameAndTypeValue(String descriptor) {
        CPNameAndType cpNameAndType = this.descriptorsToCPNameAndTypes.get(descriptor);
        if (cpNameAndType == null) {
            Integer index = this.mapDescriptor.get(descriptor);
            if (index != null) {
                return cpNameAndTypeValue(index.intValue());
            }
            int colon = descriptor.indexOf(58);
            String nameString = descriptor.substring(0, colon);
            String descriptorString = descriptor.substring(colon + 1);
            CPUTF8 name = cpUTF8Value(nameString, true);
            CPUTF8 descriptorU = cpUTF8Value(descriptorString, true);
            CPNameAndType cpNameAndType2 = new CPNameAndType(name, descriptorU, this.descrOffset - 1);
            this.descriptorsToCPNameAndTypes.put(descriptor, cpNameAndType2);
            return cpNameAndType2;
        }
        return cpNameAndType;
    }

    public CPUTF8 cpSignatureValue(int index) {
        final int globalIndex;
        if (this.cpSignatureInts[index] != -1) {
            globalIndex = this.cpSignatureInts[index];
        } else {
            int globalIndex2 = this.signatureOffset;
            globalIndex = globalIndex2 + index;
        }
        return this.stringsToCPUTF8.computeIfAbsent(this.cpSignature[index], new Function() { // from class: org.apache.commons.compress.harmony.unpack200.CpBands$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CpBands.lambda$cpSignatureValue$6(globalIndex, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CPUTF8 lambda$cpSignatureValue$6(int globalIndex, String k) {
        return new CPUTF8(k, globalIndex);
    }

    public CPString cpStringValue(int index) {
        String string = this.cpString[index];
        final int utf8Index = this.cpStringInts[index];
        final int globalIndex = this.stringOffset + index;
        return this.stringsToCPStrings.computeIfAbsent(string, new Function() { // from class: org.apache.commons.compress.harmony.unpack200.CpBands$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CpBands.this.m2085xf4840312(utf8Index, globalIndex, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$cpStringValue$7$org-apache-commons-compress-harmony-unpack200-CpBands, reason: not valid java name */
    public /* synthetic */ CPString m2085xf4840312(int utf8Index, int globalIndex, String k) {
        return new CPString(cpUTF8Value(utf8Index), globalIndex);
    }

    public CPUTF8 cpUTF8Value(int index) {
        String string = this.cpUTF8[index];
        CPUTF8 cputf8 = this.stringsToCPUTF8.get(string);
        if (cputf8 == null) {
            CPUTF8 cputf82 = new CPUTF8(string, index);
            this.stringsToCPUTF8.put(string, cputf82);
            return cputf82;
        }
        if (cputf8.getGlobalIndex() > index) {
            cputf8.setGlobalIndex(index);
            return cputf8;
        }
        return cputf8;
    }

    public CPUTF8 cpUTF8Value(String string) {
        return cpUTF8Value(string, true);
    }

    public CPUTF8 cpUTF8Value(String string, boolean searchForIndex) {
        CPUTF8 cputf8 = this.stringsToCPUTF8.get(string);
        if (cputf8 == null) {
            Integer index = null;
            if (searchForIndex) {
                Integer index2 = this.mapUTF8.get(string);
                index = index2;
            }
            if (index != null) {
                return cpUTF8Value(index.intValue());
            }
            if (searchForIndex) {
                Integer index3 = this.mapSignature.get(string);
                index = index3;
            }
            if (index != null) {
                return cpSignatureValue(index.intValue());
            }
            CPUTF8 cputf82 = new CPUTF8(string, -1);
            this.stringsToCPUTF8.put(string, cputf82);
            return cputf82;
        }
        return cputf8;
    }

    public SegmentConstantPool getConstantPool() {
        return this.pool;
    }

    public String[] getCpClass() {
        return this.cpClass;
    }

    public String[] getCpDescriptor() {
        return this.cpDescriptor;
    }

    public int[] getCpDescriptorNameInts() {
        return this.cpDescriptorNameInts;
    }

    public int[] getCpDescriptorTypeInts() {
        return this.cpDescriptorTypeInts;
    }

    public String[] getCpFieldClass() {
        return this.cpFieldClass;
    }

    public String[] getCpIMethodClass() {
        return this.cpIMethodClass;
    }

    public int[] getCpInt() {
        return this.cpInt;
    }

    public long[] getCpLong() {
        return this.cpLong;
    }

    public String[] getCpMethodClass() {
        return this.cpMethodClass;
    }

    public String[] getCpMethodDescriptor() {
        return this.cpMethodDescriptor;
    }

    public String[] getCpSignature() {
        return this.cpSignature;
    }

    public String[] getCpUTF8() {
        return this.cpUTF8;
    }

    private void parseCpClass(InputStream in) throws IOException, Pack200Exception {
        int cpClassCount = this.header.getCpClassCount();
        this.cpClassInts = decodeBandInt("cp_Class", in, Codec.UDELTA5, cpClassCount);
        this.cpClass = new String[cpClassCount];
        this.mapClass = new HashMap(cpClassCount);
        for (int i = 0; i < cpClassCount; i++) {
            this.cpClass[i] = this.cpUTF8[this.cpClassInts[i]];
            this.mapClass.put(this.cpClass[i], Integer.valueOf(i));
        }
    }

    private void parseCpDescriptor(InputStream in) throws IOException, Pack200Exception {
        int cpDescriptorCount = this.header.getCpDescriptorCount();
        this.cpDescriptorNameInts = decodeBandInt("cp_Descr_name", in, Codec.DELTA5, cpDescriptorCount);
        this.cpDescriptorTypeInts = decodeBandInt("cp_Descr_type", in, Codec.UDELTA5, cpDescriptorCount);
        String[] cpDescriptorNames = getReferences(this.cpDescriptorNameInts, this.cpUTF8);
        String[] cpDescriptorTypes = getReferences(this.cpDescriptorTypeInts, this.cpSignature);
        this.cpDescriptor = new String[cpDescriptorCount];
        this.mapDescriptor = new HashMap(cpDescriptorCount);
        for (int i = 0; i < cpDescriptorCount; i++) {
            this.cpDescriptor[i] = cpDescriptorNames[i] + ":" + cpDescriptorTypes[i];
            this.mapDescriptor.put(this.cpDescriptor[i], Integer.valueOf(i));
        }
    }

    private void parseCpDouble(InputStream in) throws IOException, Pack200Exception {
        int cpDoubleCount = this.header.getCpDoubleCount();
        final long[] band = parseFlags("cp_Double", in, cpDoubleCount, Codec.UDELTA5, Codec.DELTA5);
        this.cpDouble = new double[band.length];
        Arrays.setAll(this.cpDouble, new IntToDoubleFunction() { // from class: org.apache.commons.compress.harmony.unpack200.CpBands$$ExternalSyntheticLambda8
            @Override // java.util.function.IntToDoubleFunction
            public final double applyAsDouble(int i) {
                double longBitsToDouble;
                longBitsToDouble = Double.longBitsToDouble(band[i]);
                return longBitsToDouble;
            }
        });
    }

    private void parseCpField(InputStream in) throws IOException, Pack200Exception {
        int cpFieldCount = this.header.getCpFieldCount();
        this.cpFieldClassInts = decodeBandInt("cp_Field_class", in, Codec.DELTA5, cpFieldCount);
        this.cpFieldDescriptorInts = decodeBandInt("cp_Field_desc", in, Codec.UDELTA5, cpFieldCount);
        this.cpFieldClass = new String[cpFieldCount];
        this.cpFieldDescriptor = new String[cpFieldCount];
        for (int i = 0; i < cpFieldCount; i++) {
            this.cpFieldClass[i] = this.cpClass[this.cpFieldClassInts[i]];
            this.cpFieldDescriptor[i] = this.cpDescriptor[this.cpFieldDescriptorInts[i]];
        }
    }

    private void parseCpFloat(InputStream in) throws IOException, Pack200Exception {
        int cpFloatCount = this.header.getCpFloatCount();
        int[] floatBits = decodeBandInt("cp_Float", in, Codec.UDELTA5, cpFloatCount);
        this.cpFloat = new float[cpFloatCount];
        for (int i = 0; i < cpFloatCount; i++) {
            this.cpFloat[i] = Float.intBitsToFloat(floatBits[i]);
        }
    }

    private void parseCpIMethod(InputStream in) throws IOException, Pack200Exception {
        int cpIMethodCount = this.header.getCpIMethodCount();
        this.cpIMethodClassInts = decodeBandInt("cp_Imethod_class", in, Codec.DELTA5, cpIMethodCount);
        this.cpIMethodDescriptorInts = decodeBandInt("cp_Imethod_desc", in, Codec.UDELTA5, cpIMethodCount);
        this.cpIMethodClass = new String[cpIMethodCount];
        this.cpIMethodDescriptor = new String[cpIMethodCount];
        for (int i = 0; i < cpIMethodCount; i++) {
            this.cpIMethodClass[i] = this.cpClass[this.cpIMethodClassInts[i]];
            this.cpIMethodDescriptor[i] = this.cpDescriptor[this.cpIMethodDescriptorInts[i]];
        }
    }

    private void parseCpInt(InputStream in) throws IOException, Pack200Exception {
        int cpIntCount = this.header.getCpIntCount();
        this.cpInt = decodeBandInt("cpInt", in, Codec.UDELTA5, cpIntCount);
    }

    private void parseCpLong(InputStream in) throws IOException, Pack200Exception {
        int cpLongCount = this.header.getCpLongCount();
        this.cpLong = parseFlags("cp_Long", in, cpLongCount, Codec.UDELTA5, Codec.DELTA5);
    }

    private void parseCpMethod(InputStream in) throws IOException, Pack200Exception {
        int cpMethodCount = this.header.getCpMethodCount();
        this.cpMethodClassInts = decodeBandInt("cp_Method_class", in, Codec.DELTA5, cpMethodCount);
        this.cpMethodDescriptorInts = decodeBandInt("cp_Method_desc", in, Codec.UDELTA5, cpMethodCount);
        this.cpMethodClass = new String[cpMethodCount];
        this.cpMethodDescriptor = new String[cpMethodCount];
        for (int i = 0; i < cpMethodCount; i++) {
            this.cpMethodClass[i] = this.cpClass[this.cpMethodClassInts[i]];
            this.cpMethodDescriptor[i] = this.cpDescriptor[this.cpMethodDescriptorInts[i]];
        }
    }

    private void parseCpSignature(InputStream in) throws IOException, Pack200Exception {
        int cpSignatureCount = this.header.getCpSignatureCount();
        this.cpSignatureInts = decodeBandInt("cp_Signature_form", in, Codec.DELTA5, cpSignatureCount);
        String[] cpSignatureForm = getReferences(this.cpSignatureInts, this.cpUTF8);
        this.cpSignature = new String[cpSignatureCount];
        this.mapSignature = new HashMap();
        int lCount = 0;
        for (int i = 0; i < cpSignatureCount; i++) {
            char[] chars = cpSignatureForm[i].toCharArray();
            for (char element : chars) {
                if (element == 'L') {
                    this.cpSignatureInts[i] = -1;
                    lCount++;
                }
            }
        }
        String[] cpSignatureClasses = parseReferences("cp_Signature_classes", in, Codec.UDELTA5, lCount, this.cpClass);
        int index = 0;
        for (int i2 = 0; i2 < cpSignatureCount; i2++) {
            String form = cpSignatureForm[i2];
            int len = form.length();
            StringBuilder signature = new StringBuilder(64);
            for (int j = 0; j < len; j++) {
                char c = form.charAt(j);
                signature.append(c);
                if (c == 'L') {
                    String className = cpSignatureClasses[index];
                    signature.append(className);
                    index++;
                }
            }
            this.cpSignature[i2] = signature.toString();
            this.mapSignature.put(signature.toString(), Integer.valueOf(i2));
        }
    }

    private void parseCpString(InputStream in) throws IOException, Pack200Exception {
        int cpStringCount = this.header.getCpStringCount();
        this.cpStringInts = decodeBandInt("cp_String", in, Codec.UDELTA5, cpStringCount);
        this.cpString = new String[cpStringCount];
        Arrays.setAll(this.cpString, new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.CpBands$$ExternalSyntheticLambda1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CpBands.this.m2086xabd0a112(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$parseCpString$9$org-apache-commons-compress-harmony-unpack200-CpBands, reason: not valid java name */
    public /* synthetic */ String m2086xabd0a112(int i) {
        return this.cpUTF8[this.cpStringInts[i]];
    }

    private void parseCpUtf8(InputStream in) throws IOException, Pack200Exception {
        int cpUTF8Count;
        int cpUTF8Count2 = this.header.getCpUTF8Count();
        if (cpUTF8Count2 <= 0) {
            throw new IOException("cpUTF8Count value must be greater than 0");
        }
        int[] prefix = decodeBandInt("cpUTF8Prefix", in, Codec.DELTA5, cpUTF8Count2 - 2);
        int charCount = 0;
        int bigSuffixCount = 0;
        int[] suffix = decodeBandInt("cpUTF8Suffix", in, Codec.UNSIGNED5, cpUTF8Count2 - 1);
        for (int element : suffix) {
            if (element == 0) {
                bigSuffixCount++;
            } else {
                charCount += element;
            }
        }
        int[] dataBand = decodeBandInt("cp_Utf8_chars", in, Codec.CHAR3, charCount);
        char[] data = new char[charCount];
        for (int i = 0; i < data.length; i++) {
            data[i] = (char) dataBand[i];
        }
        int[] bigSuffixCounts = decodeBandInt("cp_Utf8_big_suffix", in, Codec.DELTA5, bigSuffixCount);
        int[][] bigSuffixDataBand = new int[bigSuffixCount];
        for (int i2 = 0; i2 < bigSuffixDataBand.length; i2++) {
            bigSuffixDataBand[i2] = decodeBandInt("cp_Utf8_big_chars " + i2, in, Codec.DELTA5, bigSuffixCounts[i2]);
        }
        char[][] bigSuffixData = new char[bigSuffixCount];
        for (int i3 = 0; i3 < bigSuffixDataBand.length; i3++) {
            bigSuffixData[i3] = new char[bigSuffixDataBand[i3].length];
            for (int j = 0; j < bigSuffixDataBand[i3].length; j++) {
                bigSuffixData[i3][j] = (char) bigSuffixDataBand[i3][j];
            }
        }
        int cpUTF8Count3 = 0;
        this.mapUTF8 = new HashMap(cpUTF8Count2 + 1);
        this.cpUTF8 = new String[cpUTF8Count2];
        this.cpUTF8[0] = "";
        this.mapUTF8.put("", 0);
        int charCount2 = 0;
        int bigSuffixCount2 = 0;
        int i4 = 1;
        while (i4 < cpUTF8Count2) {
            String lastString = this.cpUTF8[i4 - 1];
            if (suffix[i4 - 1] == 0) {
                cpUTF8Count = cpUTF8Count2;
                this.cpUTF8[i4] = lastString.substring(cpUTF8Count3, i4 > 1 ? prefix[i4 - 2] : cpUTF8Count3) + new String(bigSuffixData[bigSuffixCount2]);
                bigSuffixCount2++;
            } else {
                cpUTF8Count = cpUTF8Count2;
                this.cpUTF8[i4] = lastString.substring(0, i4 > 1 ? prefix[i4 - 2] : 0) + new String(data, charCount2, suffix[i4 - 1]);
                charCount2 += suffix[i4 - 1];
            }
            this.mapUTF8.put(this.cpUTF8[i4], Integer.valueOf(i4));
            i4++;
            cpUTF8Count2 = cpUTF8Count;
            cpUTF8Count3 = 0;
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void read(InputStream in) throws IOException, Pack200Exception {
        parseCpUtf8(in);
        parseCpInt(in);
        parseCpFloat(in);
        parseCpLong(in);
        parseCpDouble(in);
        parseCpString(in);
        parseCpClass(in);
        parseCpSignature(in);
        parseCpDescriptor(in);
        parseCpField(in);
        parseCpMethod(in);
        parseCpIMethod(in);
        this.intOffset = this.cpUTF8.length;
        this.floatOffset = this.intOffset + this.cpInt.length;
        this.longOffset = this.floatOffset + this.cpFloat.length;
        this.doubleOffset = this.longOffset + this.cpLong.length;
        this.stringOffset = this.doubleOffset + this.cpDouble.length;
        this.classOffset = this.stringOffset + this.cpString.length;
        this.signatureOffset = this.classOffset + this.cpClass.length;
        this.descrOffset = this.signatureOffset + this.cpSignature.length;
        this.fieldOffset = this.descrOffset + this.cpDescriptor.length;
        this.methodOffset = this.fieldOffset + this.cpFieldClass.length;
        this.imethodOffset = this.methodOffset + this.cpMethodClass.length;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void unpack() {
    }
}
