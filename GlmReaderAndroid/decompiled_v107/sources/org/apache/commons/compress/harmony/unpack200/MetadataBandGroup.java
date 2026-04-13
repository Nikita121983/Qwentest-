package org.apache.commons.compress.harmony.unpack200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.IntFunction;
import org.apache.commons.compress.harmony.unpack200.bytecode.AnnotationDefaultAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.AnnotationsAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPDouble;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFloat;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInteger;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPLong;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.RuntimeVisibleorInvisibleAnnotationsAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.RuntimeVisibleorInvisibleParameterAnnotationsAttribute;

/* loaded from: classes9.dex */
public class MetadataBandGroup {
    private static CPUTF8 riaUTF8;
    private static CPUTF8 ripaUTF8;
    private static CPUTF8 rvaUTF8;
    private static CPUTF8 rvpaUTF8;
    public int[] T;
    private int T_index;
    public int[] anno_N;
    private int anno_N_Index;
    private List<Attribute> attributes;
    public CPDouble[] caseD_KD;
    private int caseD_KD_Index;
    public CPFloat[] caseF_KF;
    private int caseF_KF_Index;
    public CPInteger[] caseI_KI;
    private int caseI_KI_Index;
    public CPLong[] caseJ_KJ;
    private int caseJ_KJ_Index;
    public int[] casearray_N;
    private int casearray_N_Index;
    public CPUTF8[] casec_RS;
    private int casec_RS_Index;
    public String[] caseec_RU;
    private int caseec_RU_Index;
    public String[] caseet_RS;
    private int caseet_RS_Index;
    public CPUTF8[] cases_RU;
    private int cases_RU_Index;
    private final CpBands cpBands;
    public CPUTF8[] name_RU;
    public CPUTF8[] nestname_RU;
    private Iterator<CPUTF8> nestname_RU_Iterator;
    public int[] nestpair_N;
    private int nestpair_N_Index;
    public CPUTF8[] nesttype_RS;
    private int nesttype_RS_Index;
    public int[][] pair_N;
    private int pair_N_Index;
    public int[] param_NB;
    private final String type;
    public CPUTF8[][] type_RS;

    public static void setRiaAttributeName(CPUTF8 cpUTF8Value) {
        riaUTF8 = cpUTF8Value;
    }

    public static void setRipaAttributeName(CPUTF8 cpUTF8Value) {
        ripaUTF8 = cpUTF8Value;
    }

    public static void setRvaAttributeName(CPUTF8 cpUTF8Value) {
        rvaUTF8 = cpUTF8Value;
    }

    public static void setRvpaAttributeName(CPUTF8 cpUTF8Value) {
        rvpaUTF8 = cpUTF8Value;
    }

    public MetadataBandGroup(String type, CpBands cpBands) {
        this.type = type;
        this.cpBands = cpBands;
    }

    private AnnotationsAttribute.Annotation getAnnotation(CPUTF8 type, int pairCount, Iterator<CPUTF8> namesIterator) {
        CPUTF8[] elementNames = new CPUTF8[pairCount];
        AnnotationsAttribute.ElementValue[] elementValues = new AnnotationsAttribute.ElementValue[pairCount];
        for (int j = 0; j < elementNames.length; j++) {
            elementNames[j] = namesIterator.next();
            int[] iArr = this.T;
            int i = this.T_index;
            this.T_index = i + 1;
            int t = iArr[i];
            elementValues[j] = new AnnotationsAttribute.ElementValue(t, getNextValue(t));
        }
        return new AnnotationsAttribute.Annotation(pairCount, type, elementNames, elementValues);
    }

    private Attribute getAttribute(int numAnnotations, final CPUTF8[] types, final int[] pairCounts, final Iterator<CPUTF8> namesIterator) {
        AnnotationsAttribute.Annotation[] annotations = new AnnotationsAttribute.Annotation[numAnnotations];
        Arrays.setAll(annotations, new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.MetadataBandGroup$$ExternalSyntheticLambda1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return MetadataBandGroup.this.m2087x45564f82(types, pairCounts, namesIterator, i);
            }
        });
        return new RuntimeVisibleorInvisibleAnnotationsAttribute(this.type.equals("RVA") ? rvaUTF8 : riaUTF8, annotations);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getAttribute$0$org-apache-commons-compress-harmony-unpack200-MetadataBandGroup, reason: not valid java name */
    public /* synthetic */ AnnotationsAttribute.Annotation m2087x45564f82(CPUTF8[] types, int[] pairCounts, Iterator namesIterator, int i) {
        return getAnnotation(types[i], pairCounts[i], namesIterator);
    }

    public List<Attribute> getAttributes() {
        if (this.attributes == null) {
            this.attributes = new ArrayList();
            int i = 0;
            if (this.name_RU != null) {
                Iterator<CPUTF8> name_RU_Iterator = Arrays.asList(this.name_RU).iterator();
                if (!this.type.equals("AD")) {
                    this.T_index = 0;
                }
                this.caseI_KI_Index = 0;
                this.caseD_KD_Index = 0;
                this.caseF_KF_Index = 0;
                this.caseJ_KJ_Index = 0;
                this.casec_RS_Index = 0;
                this.caseet_RS_Index = 0;
                this.caseec_RU_Index = 0;
                this.cases_RU_Index = 0;
                this.casearray_N_Index = 0;
                this.nesttype_RS_Index = 0;
                this.nestpair_N_Index = 0;
                this.nestname_RU_Iterator = Arrays.asList(this.nestname_RU).iterator();
                if (this.type.equals("RVA") || this.type.equals("RIA")) {
                    for (int i2 = 0; i2 < this.anno_N.length; i2++) {
                        this.attributes.add(getAttribute(this.anno_N[i2], this.type_RS[i2], this.pair_N[i2], name_RU_Iterator));
                    }
                } else if (this.type.equals("RVPA") || this.type.equals("RIPA")) {
                    this.anno_N_Index = 0;
                    this.pair_N_Index = 0;
                    int[] iArr = this.param_NB;
                    int length = iArr.length;
                    while (i < length) {
                        this.attributes.add(getParameterAttribute(iArr[i], name_RU_Iterator));
                        i++;
                    }
                }
            } else if (this.type.equals("AD")) {
                int[] iArr2 = this.T;
                int length2 = iArr2.length;
                while (i < length2) {
                    int element = iArr2[i];
                    this.attributes.add(new AnnotationDefaultAttribute(new AnnotationsAttribute.ElementValue(element, getNextValue(element))));
                    i++;
                }
            }
        }
        return this.attributes;
    }

    private Object getNextValue(int t) {
        switch (t) {
            case 64:
                CPUTF8[] cputf8Arr = this.nesttype_RS;
                int i = this.nesttype_RS_Index;
                this.nesttype_RS_Index = i + 1;
                CPUTF8 type = cputf8Arr[i];
                int[] iArr = this.nestpair_N;
                int i2 = this.nestpair_N_Index;
                this.nestpair_N_Index = i2 + 1;
                int numPairs = iArr[i2];
                return getAnnotation(type, numPairs, this.nestname_RU_Iterator);
            case 66:
            case 67:
            case 73:
            case 83:
            case 90:
                CPInteger[] cPIntegerArr = this.caseI_KI;
                int i3 = this.caseI_KI_Index;
                this.caseI_KI_Index = i3 + 1;
                return cPIntegerArr[i3];
            case 68:
                CPDouble[] cPDoubleArr = this.caseD_KD;
                int i4 = this.caseD_KD_Index;
                this.caseD_KD_Index = i4 + 1;
                return cPDoubleArr[i4];
            case 70:
                CPFloat[] cPFloatArr = this.caseF_KF;
                int i5 = this.caseF_KF_Index;
                this.caseF_KF_Index = i5 + 1;
                return cPFloatArr[i5];
            case 74:
                CPLong[] cPLongArr = this.caseJ_KJ;
                int i6 = this.caseJ_KJ_Index;
                this.caseJ_KJ_Index = i6 + 1;
                return cPLongArr[i6];
            case 91:
                int[] iArr2 = this.casearray_N;
                int i7 = this.casearray_N_Index;
                this.casearray_N_Index = i7 + 1;
                int arraySize = iArr2[i7];
                AnnotationsAttribute.ElementValue[] nestedArray = new AnnotationsAttribute.ElementValue[arraySize];
                for (int i8 = 0; i8 < arraySize; i8++) {
                    int[] iArr3 = this.T;
                    int i9 = this.T_index;
                    this.T_index = i9 + 1;
                    int nextT = iArr3[i9];
                    nestedArray[i8] = new AnnotationsAttribute.ElementValue(nextT, getNextValue(nextT));
                }
                return nestedArray;
            case 99:
                CPUTF8[] cputf8Arr2 = this.casec_RS;
                int i10 = this.casec_RS_Index;
                this.casec_RS_Index = i10 + 1;
                return cputf8Arr2[i10];
            case 101:
                StringBuilder sb = new StringBuilder();
                String[] strArr = this.caseet_RS;
                int i11 = this.caseet_RS_Index;
                this.caseet_RS_Index = i11 + 1;
                StringBuilder append = sb.append(strArr[i11]).append(":");
                String[] strArr2 = this.caseec_RU;
                int i12 = this.caseec_RU_Index;
                this.caseec_RU_Index = i12 + 1;
                String enumString = append.append(strArr2[i12]).toString();
                return this.cpBands.cpNameAndTypeValue(enumString);
            case 115:
                CPUTF8[] cputf8Arr3 = this.cases_RU;
                int i13 = this.cases_RU_Index;
                this.cases_RU_Index = i13 + 1;
                return cputf8Arr3[i13];
            default:
                return null;
        }
    }

    private Attribute getParameterAttribute(int numParameters, final Iterator<CPUTF8> namesIterator) {
        RuntimeVisibleorInvisibleParameterAnnotationsAttribute.ParameterAnnotation[] parameterAnnotations = new RuntimeVisibleorInvisibleParameterAnnotationsAttribute.ParameterAnnotation[numParameters];
        for (int i = 0; i < numParameters; i++) {
            int[] iArr = this.anno_N;
            int i2 = this.anno_N_Index;
            this.anno_N_Index = i2 + 1;
            int numAnnotations = iArr[i2];
            int[][] iArr2 = this.pair_N;
            int i3 = this.pair_N_Index;
            this.pair_N_Index = i3 + 1;
            final int[] pairCounts = iArr2[i3];
            AnnotationsAttribute.Annotation[] annotations = new AnnotationsAttribute.Annotation[numAnnotations];
            Arrays.setAll(annotations, new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.MetadataBandGroup$$ExternalSyntheticLambda0
                @Override // java.util.function.IntFunction
                public final Object apply(int i4) {
                    return MetadataBandGroup.this.m2088xa92456c(pairCounts, namesIterator, i4);
                }
            });
            parameterAnnotations[i] = new RuntimeVisibleorInvisibleParameterAnnotationsAttribute.ParameterAnnotation(annotations);
        }
        return new RuntimeVisibleorInvisibleParameterAnnotationsAttribute(this.type.equals("RVPA") ? rvpaUTF8 : ripaUTF8, parameterAnnotations);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getParameterAttribute$1$org-apache-commons-compress-harmony-unpack200-MetadataBandGroup, reason: not valid java name */
    public /* synthetic */ AnnotationsAttribute.Annotation m2088xa92456c(int[] pairCounts, Iterator namesIterator, int j) {
        return getAnnotation(this.type_RS[this.anno_N_Index - 1][j], pairCounts[j], namesIterator);
    }
}
