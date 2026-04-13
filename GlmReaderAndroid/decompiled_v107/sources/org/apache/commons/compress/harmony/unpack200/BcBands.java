package org.apache.commons.compress.harmony.unpack200;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.UByte;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CodeAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.ExceptionTableEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.NewAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;
import org.apache.poi.hssf.record.UnknownRecord;
import org.apache.poi.hssf.usermodel.HSSFShapeTypes;

/* loaded from: classes9.dex */
public class BcBands extends BandSet {
    private int[] bcByte;
    private int[] bcCaseCount;
    private int[] bcCaseValue;
    private int[] bcClassRef;
    private int[] bcDoubleRef;
    private int[][] bcEscByte;
    private int[] bcEscRef;
    private int[] bcEscRefSize;
    private int[] bcEscSize;
    private int[] bcFieldRef;
    private int[] bcFloatRef;
    private int[] bcIMethodRef;
    private int[] bcInitRef;
    private int[] bcIntRef;
    private int[] bcLabel;
    private int[] bcLocal;
    private int[] bcLongRef;
    private int[] bcMethodRef;
    private int[] bcShort;
    private int[] bcStringRef;
    private int[] bcSuperField;
    private int[] bcSuperMethod;
    private int[] bcThisField;
    private int[] bcThisMethod;
    private byte[][][] methodByteCodePacked;
    private List<Integer> wideByteCodes;

    public BcBands(Segment segment) {
        super(segment);
    }

    private boolean endsWithLoad(int codePacked) {
        return codePacked >= 21 && codePacked <= 25;
    }

    private boolean endsWithStore(int codePacked) {
        return codePacked >= 54 && codePacked <= 58;
    }

    public int[] getBcByte() {
        return this.bcByte;
    }

    public int[] getBcCaseCount() {
        return this.bcCaseCount;
    }

    public int[] getBcCaseValue() {
        return this.bcCaseValue;
    }

    public int[] getBcClassRef() {
        return this.bcClassRef;
    }

    public int[] getBcDoubleRef() {
        return this.bcDoubleRef;
    }

    public int[] getBcFieldRef() {
        return this.bcFieldRef;
    }

    public int[] getBcFloatRef() {
        return this.bcFloatRef;
    }

    public int[] getBcIMethodRef() {
        return this.bcIMethodRef;
    }

    public int[] getBcInitRef() {
        return this.bcInitRef;
    }

    public int[] getBcIntRef() {
        return this.bcIntRef;
    }

    public int[] getBcLabel() {
        return this.bcLabel;
    }

    public int[] getBcLocal() {
        return this.bcLocal;
    }

    public int[] getBcLongRef() {
        return this.bcLongRef;
    }

    public int[] getBcMethodRef() {
        return this.bcMethodRef;
    }

    public int[] getBcShort() {
        return this.bcShort;
    }

    public int[] getBcStringRef() {
        return this.bcStringRef;
    }

    public int[] getBcSuperField() {
        return this.bcSuperField;
    }

    public int[] getBcSuperMethod() {
        return this.bcSuperMethod;
    }

    public int[] getBcThisField() {
        return this.bcThisField;
    }

    public int[] getBcThisMethod() {
        return this.bcThisMethod;
    }

    public byte[][][] getMethodByteCodePacked() {
        return this.methodByteCodePacked;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void read(InputStream in) throws IOException, Pack200Exception {
        AttributeLayout abstractModifier;
        int[] codes;
        AttributeLayout nativeModifier;
        int i;
        AttributeLayoutMap attributeDefinitionMap = this.segment.getAttrDefinitionBands().getAttributeDefinitionMap();
        int classCount = this.header.getClassCount();
        long[][] methodFlags = this.segment.getClassBands().getMethodFlags();
        AttributeLayout abstractModifier2 = attributeDefinitionMap.getAttributeLayout(AttributeLayout.ACC_ABSTRACT, 2);
        AttributeLayout nativeModifier2 = attributeDefinitionMap.getAttributeLayout(AttributeLayout.ACC_NATIVE, 2);
        this.methodByteCodePacked = new byte[classCount][];
        List<Boolean> switchIsTableSwitch = new ArrayList<>();
        this.wideByteCodes = new ArrayList();
        int numberOfMethods = 0;
        int bcClassRefCount = 0;
        int bcFieldRefCount = 0;
        int bcMethodRefCount = 0;
        int bcIMethodRefCount = 0;
        int bcThisFieldCount = 0;
        int bcSuperFieldCount = 0;
        int bcThisMethodCount = 0;
        int bcSuperMethodCount = 0;
        int bcInitRefCount = 0;
        int bcEscCount = 0;
        int bcEscRefCount = 0;
        int bcLabelCount = 0;
        int bcDoubleRefCount = 0;
        int bcShortCount = 0;
        int bcFloatRefCount = 0;
        int bcStringRefCount = 0;
        int bcCaseCountCount = 0;
        int bcLongRefCount = 0;
        int bcIntRefCount = 0;
        int bcLocalCount = 0;
        int bcByteCount = 0;
        while (numberOfMethods < classCount) {
            int c = numberOfMethods;
            int numberOfMethods2 = methodFlags[c].length;
            int classCount2 = classCount;
            this.methodByteCodePacked[c] = new byte[numberOfMethods2];
            int m = 0;
            while (m < numberOfMethods2) {
                int numberOfMethods3 = numberOfMethods2;
                int m2 = m;
                long methodFlag = methodFlags[c][m2];
                if (abstractModifier2.matches(methodFlag) || nativeModifier2.matches(methodFlag)) {
                    abstractModifier = abstractModifier2;
                } else {
                    ByteArrayOutputStream codeBytes = new ByteArrayOutputStream();
                    while (true) {
                        long methodFlag2 = methodFlag;
                        byte read = (byte) (in.read() & 255);
                        byte code = read;
                        abstractModifier = abstractModifier2;
                        if (read != -1) {
                            codeBytes.write(code);
                            abstractModifier2 = abstractModifier;
                            methodFlag = methodFlag2;
                        } else {
                            this.methodByteCodePacked[c][m2] = codeBytes.toByteArray();
                            int[] codes2 = new int[this.methodByteCodePacked[c][m2].length];
                            int i2 = 0;
                            while (true) {
                                byte code2 = code;
                                if (i2 < codes2.length) {
                                    codes2[i2] = this.methodByteCodePacked[c][m2][i2] & UByte.MAX_VALUE;
                                    i2++;
                                    code = code2;
                                } else {
                                    int i3 = 0;
                                    while (i3 < this.methodByteCodePacked[c][m2].length) {
                                        int codePacked = this.methodByteCodePacked[c][m2][i3] & UByte.MAX_VALUE;
                                        switch (codePacked) {
                                            case 16:
                                            case 188:
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                bcByteCount++;
                                                break;
                                            case 17:
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                bcShortCount++;
                                                break;
                                            case 18:
                                            case 19:
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                bcStringRefCount++;
                                                break;
                                            case 20:
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                bcLongRefCount++;
                                                break;
                                            case 132:
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                bcLocalCount++;
                                                bcByteCount++;
                                                break;
                                            case 167:
                                            case 168:
                                            case 200:
                                            case HSSFShapeTypes.HostControl /* 201 */:
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                bcLabelCount++;
                                                break;
                                            case 169:
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                bcLocalCount++;
                                                break;
                                            case 170:
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                switchIsTableSwitch.add(Boolean.TRUE);
                                                bcCaseCountCount++;
                                                bcLabelCount++;
                                                i3 = i3;
                                                break;
                                            case 171:
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                switchIsTableSwitch.add(Boolean.FALSE);
                                                bcCaseCountCount++;
                                                bcLabelCount++;
                                                i3 = i3;
                                                break;
                                            case 178:
                                            case 179:
                                            case 180:
                                            case 181:
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                bcFieldRefCount++;
                                                break;
                                            case 182:
                                            case 183:
                                            case 184:
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                bcMethodRefCount++;
                                                break;
                                            case 185:
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                bcIMethodRefCount++;
                                                break;
                                            case 187:
                                            case 189:
                                            case 192:
                                            case 193:
                                            case UnknownRecord.BITMAP_00E9 /* 233 */:
                                            case 236:
                                                i = i3;
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                break;
                                            case HSSFShapeTypes.ActionButtonBeginning /* 196 */:
                                                int i4 = i3;
                                                int nextInstruction = this.methodByteCodePacked[c][m2][i4 + 1] & UByte.MAX_VALUE;
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                this.wideByteCodes.add(Integer.valueOf(nextInstruction));
                                                if (nextInstruction == 132) {
                                                    bcLocalCount++;
                                                    bcShortCount++;
                                                } else {
                                                    if (!endsWithLoad(nextInstruction) && !endsWithStore(nextInstruction)) {
                                                        if (nextInstruction != 169) {
                                                            this.segment.log(2, "Found unhandled " + ByteCode.getByteCode(nextInstruction));
                                                        }
                                                    }
                                                    bcLocalCount++;
                                                }
                                                i3 = i4 + 1;
                                                break;
                                            case HSSFShapeTypes.ActionButtonReturn /* 197 */:
                                                bcByteCount++;
                                                i = i3;
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                break;
                                            case HSSFShapeTypes.TextBox /* 202 */:
                                            case 203:
                                            case 204:
                                            case 205:
                                            case 209:
                                            case 210:
                                            case 211:
                                            case 212:
                                                bcThisFieldCount++;
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                break;
                                            case 206:
                                            case 207:
                                            case 208:
                                            case 213:
                                            case 214:
                                            case 215:
                                                bcThisMethodCount++;
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                break;
                                            case 216:
                                            case 217:
                                            case 218:
                                            case 219:
                                            case 223:
                                            case 224:
                                            case 225:
                                            case 226:
                                                bcSuperFieldCount++;
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                break;
                                            case 220:
                                            case 221:
                                            case 222:
                                            case 227:
                                            case 228:
                                            case 229:
                                                bcSuperMethodCount++;
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                break;
                                            case 230:
                                            case 231:
                                            case 232:
                                                bcInitRefCount++;
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                break;
                                            case 234:
                                            case 237:
                                                bcIntRefCount++;
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                break;
                                            case 235:
                                            case 238:
                                                bcFloatRefCount++;
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                break;
                                            case UnknownRecord.PHONETICPR_00EF /* 239 */:
                                                bcDoubleRefCount++;
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                break;
                                            case 253:
                                                bcEscRefCount++;
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                break;
                                            case 254:
                                                bcEscCount++;
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                break;
                                            default:
                                                int i5 = i3;
                                                codes = codes2;
                                                nativeModifier = nativeModifier2;
                                                if (endsWithLoad(codePacked) || endsWithStore(codePacked)) {
                                                    bcLocalCount++;
                                                    i3 = i5;
                                                    break;
                                                } else if (!startsWithIf(codePacked)) {
                                                    i3 = i5;
                                                    break;
                                                } else {
                                                    bcLabelCount++;
                                                    i3 = i5;
                                                    break;
                                                }
                                                break;
                                        }
                                        bcClassRefCount++;
                                        i3 = i;
                                        i3++;
                                        codes2 = codes;
                                        nativeModifier2 = nativeModifier;
                                    }
                                }
                            }
                        }
                    }
                }
                m = m2 + 1;
                abstractModifier2 = abstractModifier;
                numberOfMethods2 = numberOfMethods3;
                nativeModifier2 = nativeModifier2;
            }
            numberOfMethods = c + 1;
            classCount = classCount2;
            nativeModifier2 = nativeModifier2;
        }
        this.bcCaseCount = decodeBandInt("bc_case_count", in, Codec.UNSIGNED5, bcCaseCountCount);
        int bcCaseValueCount = 0;
        for (int i6 = 0; i6 < this.bcCaseCount.length; i6++) {
            boolean isTableSwitch = switchIsTableSwitch.get(i6).booleanValue();
            if (isTableSwitch) {
                bcCaseValueCount++;
            } else {
                bcCaseValueCount += this.bcCaseCount[i6];
            }
        }
        this.bcCaseValue = decodeBandInt("bc_case_value", in, Codec.DELTA5, bcCaseValueCount);
        int bcLabelCount2 = bcLabelCount;
        for (int index = 0; index < bcCaseCountCount; index++) {
            bcLabelCount2 += this.bcCaseCount[index];
        }
        this.bcByte = decodeBandInt("bc_byte", in, Codec.BYTE1, bcByteCount);
        this.bcShort = decodeBandInt("bc_short", in, Codec.DELTA5, bcShortCount);
        this.bcLocal = decodeBandInt("bc_local", in, Codec.UNSIGNED5, bcLocalCount);
        this.bcLabel = decodeBandInt("bc_label", in, Codec.BRANCH5, bcLabelCount2);
        this.bcIntRef = decodeBandInt("bc_intref", in, Codec.DELTA5, bcIntRefCount);
        this.bcFloatRef = decodeBandInt("bc_floatref", in, Codec.DELTA5, bcFloatRefCount);
        this.bcLongRef = decodeBandInt("bc_longref", in, Codec.DELTA5, bcLongRefCount);
        this.bcDoubleRef = decodeBandInt("bc_doubleref", in, Codec.DELTA5, bcDoubleRefCount);
        this.bcStringRef = decodeBandInt("bc_stringref", in, Codec.DELTA5, bcStringRefCount);
        int bcCaseValueCount2 = bcClassRefCount;
        this.bcClassRef = decodeBandInt("bc_classref", in, Codec.UNSIGNED5, bcCaseValueCount2);
        int bcClassRefCount2 = bcFieldRefCount;
        this.bcFieldRef = decodeBandInt("bc_fieldref", in, Codec.DELTA5, bcClassRefCount2);
        int bcFieldRefCount2 = bcMethodRefCount;
        this.bcMethodRef = decodeBandInt("bc_methodref", in, Codec.UNSIGNED5, bcFieldRefCount2);
        int bcMethodRefCount2 = bcIMethodRefCount;
        this.bcIMethodRef = decodeBandInt("bc_imethodref", in, Codec.DELTA5, bcMethodRefCount2);
        int bcIMethodRefCount2 = bcThisFieldCount;
        this.bcThisField = decodeBandInt("bc_thisfield", in, Codec.UNSIGNED5, bcIMethodRefCount2);
        int bcThisFieldCount2 = bcSuperFieldCount;
        this.bcSuperField = decodeBandInt("bc_superfield", in, Codec.UNSIGNED5, bcThisFieldCount2);
        int bcSuperFieldCount2 = bcThisMethodCount;
        this.bcThisMethod = decodeBandInt("bc_thismethod", in, Codec.UNSIGNED5, bcSuperFieldCount2);
        int bcThisMethodCount2 = bcSuperMethodCount;
        this.bcSuperMethod = decodeBandInt("bc_supermethod", in, Codec.UNSIGNED5, bcThisMethodCount2);
        int bcSuperMethodCount2 = bcInitRefCount;
        this.bcInitRef = decodeBandInt("bc_initref", in, Codec.UNSIGNED5, bcSuperMethodCount2);
        int bcInitRefCount2 = bcEscRefCount;
        this.bcEscRef = decodeBandInt("bc_escref", in, Codec.UNSIGNED5, bcInitRefCount2);
        this.bcEscRefSize = decodeBandInt("bc_escrefsize", in, Codec.UNSIGNED5, bcInitRefCount2);
        int bcEscRefCount2 = bcEscCount;
        this.bcEscSize = decodeBandInt("bc_escsize", in, Codec.UNSIGNED5, bcEscRefCount2);
        this.bcEscByte = decodeBandInt("bc_escbyte", in, Codec.BYTE1, this.bcEscSize);
    }

    private boolean startsWithIf(int codePacked) {
        return (codePacked >= 153 && codePacked <= 166) || codePacked == 198 || codePacked == 199;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void unpack() throws Pack200Exception {
        int m;
        ArrayList<Attribute>[][] methodAttributes;
        String[][] methodDescr;
        AttributeLayoutMap attributeDefinitionMap;
        OperandManager operandManager;
        int codeAttributeIndex;
        int c;
        int numberOfMethods;
        List<Attribute> currentAttributes;
        List<Attribute> currentAttributes2;
        BcBands bcBands = this;
        int classCount = bcBands.header.getClassCount();
        long[][] methodFlags = bcBands.segment.getClassBands().getMethodFlags();
        int[] codeMaxNALocals = bcBands.segment.getClassBands().getCodeMaxNALocals();
        int[] codeMaxStack = bcBands.segment.getClassBands().getCodeMaxStack();
        ArrayList<Attribute>[][] methodAttributes2 = bcBands.segment.getClassBands().getMethodAttributes();
        String[][] methodDescr2 = bcBands.segment.getClassBands().getMethodDescr();
        AttributeLayoutMap attributeDefinitionMap2 = bcBands.segment.getAttrDefinitionBands().getAttributeDefinitionMap();
        AttributeLayout abstractModifier = attributeDefinitionMap2.getAttributeLayout(AttributeLayout.ACC_ABSTRACT, 2);
        AttributeLayout nativeModifier = attributeDefinitionMap2.getAttributeLayout(AttributeLayout.ACC_NATIVE, 2);
        AttributeLayout staticModifier = attributeDefinitionMap2.getAttributeLayout(AttributeLayout.ACC_STATIC, 2);
        int[] wideByteCodeArray = new int[bcBands.wideByteCodes.size()];
        for (int index = 0; index < wideByteCodeArray.length; index++) {
            wideByteCodeArray[index] = bcBands.wideByteCodes.get(index).intValue();
        }
        OperandManager operandManager2 = new OperandManager(bcBands.bcCaseCount, bcBands.bcCaseValue, bcBands.bcByte, bcBands.bcShort, bcBands.bcLocal, bcBands.bcLabel, bcBands.bcIntRef, bcBands.bcFloatRef, bcBands.bcLongRef, bcBands.bcDoubleRef, bcBands.bcStringRef, bcBands.bcClassRef, bcBands.bcFieldRef, bcBands.bcMethodRef, bcBands.bcIMethodRef, bcBands.bcThisField, bcBands.bcSuperField, bcBands.bcThisMethod, bcBands.bcSuperMethod, bcBands.bcInitRef, wideByteCodeArray);
        operandManager2.setSegment(bcBands.segment);
        int i = 0;
        ArrayList<List<Attribute>> orderedCodeAttributes = bcBands.segment.getClassBands().getOrderedCodeAttributes();
        int codeAttributeIndex2 = 0;
        int[] handlerCount = bcBands.segment.getClassBands().getCodeHandlerCount();
        int[][] handlerStartPCs = bcBands.segment.getClassBands().getCodeHandlerStartP();
        int[][] handlerEndPCs = bcBands.segment.getClassBands().getCodeHandlerEndPO();
        int[][] handlerCatchPCs = bcBands.segment.getClassBands().getCodeHandlerCatchPO();
        int[][] handlerClassTypes = bcBands.segment.getClassBands().getCodeHandlerClassRCN();
        boolean allCodeHasFlags = bcBands.segment.getSegmentHeader().getOptions().hasAllCodeFlags();
        boolean[] codeHasFlags = bcBands.segment.getClassBands().getCodeHasAttributes();
        int c2 = 0;
        while (c2 < classCount) {
            int numberOfMethods2 = methodFlags[c2].length;
            int classCount2 = classCount;
            int classCount3 = 0;
            while (classCount3 < numberOfMethods2) {
                int[] codeMaxNALocals2 = codeMaxNALocals;
                int[] codeMaxStack2 = codeMaxStack;
                long methodFlag = methodFlags[c2][classCount3];
                if (abstractModifier.matches(methodFlag) || nativeModifier.matches(methodFlag)) {
                    m = classCount3;
                    methodAttributes = methodAttributes2;
                    methodDescr = methodDescr2;
                    attributeDefinitionMap = attributeDefinitionMap2;
                    operandManager = operandManager2;
                    codeAttributeIndex = codeAttributeIndex2;
                    c = c2;
                    numberOfMethods = numberOfMethods2;
                } else {
                    int c3 = c2;
                    int c4 = codeMaxStack2[i];
                    int maxLocal = codeMaxNALocals2[i];
                    if (!staticModifier.matches(methodFlag)) {
                        maxLocal++;
                    }
                    int maxLocal2 = maxLocal + SegmentUtils.countInvokeInterfaceArgs(methodDescr2[c3][classCount3]);
                    m = classCount3;
                    String[] cpClass = bcBands.segment.getCpBands().getCpClass();
                    operandManager2.setCurrentClass(cpClass[bcBands.segment.getClassBands().getClassThisInts()[c3]]);
                    operandManager2.setSuperClass(cpClass[bcBands.segment.getClassBands().getClassSuperInts()[c3]]);
                    List<ExceptionTableEntry> exceptionTable = new ArrayList<>();
                    if (handlerCount != null) {
                        int j = 0;
                        while (j < handlerCount[i]) {
                            int handlerClass = handlerClassTypes[i][j] - 1;
                            CPClass cpHandlerClass = null;
                            int j2 = j;
                            if (handlerClass != -1) {
                                cpHandlerClass = bcBands.segment.getCpBands().cpClassValue(handlerClass);
                            }
                            ExceptionTableEntry entry = new ExceptionTableEntry(handlerStartPCs[i][j2], handlerEndPCs[i][j2], handlerCatchPCs[i][j2], cpHandlerClass);
                            exceptionTable.add(entry);
                            j = j2 + 1;
                            methodAttributes2 = methodAttributes2;
                            methodDescr2 = methodDescr2;
                            attributeDefinitionMap2 = attributeDefinitionMap2;
                        }
                        methodAttributes = methodAttributes2;
                        methodDescr = methodDescr2;
                        attributeDefinitionMap = attributeDefinitionMap2;
                    } else {
                        methodAttributes = methodAttributes2;
                        methodDescr = methodDescr2;
                        attributeDefinitionMap = attributeDefinitionMap2;
                    }
                    codeAttributeIndex = codeAttributeIndex2;
                    operandManager = operandManager2;
                    int c5 = c3;
                    numberOfMethods = numberOfMethods2;
                    CodeAttribute codeAttr = new CodeAttribute(c4, maxLocal2, bcBands.methodByteCodePacked[c3][m], bcBands.segment, operandManager, exceptionTable);
                    ArrayList<Attribute> methodAttributesList = methodAttributes[c5][m];
                    int indexForCodeAttr = 0;
                    Iterator<Attribute> it = methodAttributesList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            c = c5;
                            break;
                        }
                        Attribute attribute = it.next();
                        if (!(attribute instanceof NewAttribute)) {
                            c = c5;
                            break;
                        }
                        c = c5;
                        if (((NewAttribute) attribute).getLayoutIndex() >= 15) {
                            break;
                        }
                        indexForCodeAttr++;
                        c5 = c;
                    }
                    methodAttributesList.add(indexForCodeAttr, codeAttr);
                    codeAttr.renumber(codeAttr.byteCodeOffsets);
                    if (allCodeHasFlags) {
                        currentAttributes = orderedCodeAttributes.get(i);
                    } else if (codeHasFlags[i]) {
                        currentAttributes = orderedCodeAttributes.get(codeAttributeIndex);
                        codeAttributeIndex++;
                    } else {
                        currentAttributes = Collections.EMPTY_LIST;
                    }
                    for (Attribute currentAttribute : currentAttributes) {
                        codeAttr.addAttribute(currentAttribute);
                        if (!currentAttribute.hasBCIRenumbering()) {
                            currentAttributes2 = currentAttributes;
                        } else {
                            currentAttributes2 = currentAttributes;
                            ((BCIRenumberedAttribute) currentAttribute).renumber(codeAttr.byteCodeOffsets);
                        }
                        currentAttributes = currentAttributes2;
                    }
                    i++;
                }
                codeAttributeIndex2 = codeAttributeIndex;
                classCount3 = m + 1;
                bcBands = this;
                numberOfMethods2 = numberOfMethods;
                c2 = c;
                operandManager2 = operandManager;
                codeMaxNALocals = codeMaxNALocals2;
                codeMaxStack = codeMaxStack2;
                methodAttributes2 = methodAttributes;
                methodDescr2 = methodDescr;
                attributeDefinitionMap2 = attributeDefinitionMap;
            }
            c2++;
            bcBands = this;
            classCount = classCount2;
            codeMaxNALocals = codeMaxNALocals;
            codeMaxStack = codeMaxStack;
        }
    }
}
