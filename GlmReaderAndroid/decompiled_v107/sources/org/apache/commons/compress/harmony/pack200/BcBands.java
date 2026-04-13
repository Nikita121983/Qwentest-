package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.record.UnknownRecord;
import org.objectweb.asm.Label;

/* loaded from: classes9.dex */
public class BcBands extends BandSet {
    private static final int ALOAD_0 = 42;
    private static final int IINC = 132;
    private static final int INVOKEINTERFACE = 185;
    private static final int LOOKUPSWITCH = 171;
    private static final int MULTIANEWARRAY = 197;
    private static final int TABLESWITCH = 170;
    private static final int WIDE = 196;
    private static final int endMarker = 255;
    private final IntList bcByte;
    private final IntList bcCaseCount;
    private final IntList bcCaseValue;
    private final List<CPClass> bcClassRef;
    private final IntList bcCodes;
    private final List<CPDouble> bcDoubleRef;
    private final List<CPMethodOrField> bcFieldRef;
    private final List<CPFloat> bcFloatRef;
    private final List<CPMethodOrField> bcIMethodRef;
    private List bcInitRef;
    private final List<CPInt> bcIntref;
    private final List bcLabel;
    private final IntList bcLabelRelativeOffsets;
    private final IntList bcLocal;
    private final List<CPLong> bcLongRef;
    private final List<CPMethodOrField> bcMethodRef;
    private final IntList bcShort;
    private final List<CPString> bcStringRef;
    private final List bcSuperField;
    private List bcSuperMethod;
    private List bcThisField;
    private List bcThisMethod;
    private final IntList bciRenumbering;
    private int byteCodeOffset;
    private final CpBands cpBands;
    private String currentClass;
    private String currentNewClass;
    private final Map<Label, Integer> labelsToOffsets;
    private int renumberedOffset;
    private final Segment segment;
    private String superClass;

    public BcBands(CpBands cpBands, Segment segment, int effort) {
        super(effort, segment.getSegmentHeader());
        this.bcCodes = new IntList();
        this.bcCaseCount = new IntList();
        this.bcCaseValue = new IntList();
        this.bcByte = new IntList();
        this.bcShort = new IntList();
        this.bcLocal = new IntList();
        this.bcLabel = new ArrayList();
        this.bcIntref = new ArrayList();
        this.bcFloatRef = new ArrayList();
        this.bcLongRef = new ArrayList();
        this.bcDoubleRef = new ArrayList();
        this.bcStringRef = new ArrayList();
        this.bcClassRef = new ArrayList();
        this.bcFieldRef = new ArrayList();
        this.bcMethodRef = new ArrayList();
        this.bcIMethodRef = new ArrayList();
        this.bcThisField = new ArrayList();
        this.bcSuperField = new ArrayList();
        this.bcThisMethod = new ArrayList();
        this.bcSuperMethod = new ArrayList();
        this.bcInitRef = new ArrayList();
        this.bciRenumbering = new IntList();
        this.labelsToOffsets = new HashMap();
        this.bcLabelRelativeOffsets = new IntList();
        this.cpBands = cpBands;
        this.segment = segment;
    }

    public void finaliseBands() {
        this.bcThisField = getIndexInClass(this.bcThisField);
        this.bcThisMethod = getIndexInClass(this.bcThisMethod);
        this.bcSuperMethod = getIndexInClass(this.bcSuperMethod);
        this.bcInitRef = getIndexInClassForConstructor(this.bcInitRef);
    }

    private List<Integer> getIndexInClass(List<CPMethodOrField> cPMethodOrFieldList) {
        return (List) cPMethodOrFieldList.stream().collect(Collectors.mapping(new Function() { // from class: org.apache.commons.compress.harmony.pack200.BcBands$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((CPMethodOrField) obj).getIndexInClass());
            }
        }, Collectors.toList()));
    }

    private List<Integer> getIndexInClassForConstructor(List<CPMethodOrField> cPMethodList) {
        return (List) cPMethodList.stream().collect(Collectors.mapping(new Function() { // from class: org.apache.commons.compress.harmony.pack200.BcBands$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((CPMethodOrField) obj).getIndexInClassForConstructor());
            }
        }, Collectors.toList()));
    }

    @Override // org.apache.commons.compress.harmony.pack200.BandSet
    public void pack(OutputStream out) throws IOException, Pack200Exception {
        PackingUtils.log("Writing byte code bands...");
        byte[] encodedBand = encodeBandInt("bcCodes", this.bcCodes.toArray(), Codec.BYTE1);
        out.write(encodedBand);
        PackingUtils.log("Wrote " + encodedBand.length + " bytes from bcCodes[" + this.bcCodes.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand2 = encodeBandInt("bcCaseCount", this.bcCaseCount.toArray(), Codec.UNSIGNED5);
        out.write(encodedBand2);
        PackingUtils.log("Wrote " + encodedBand2.length + " bytes from bcCaseCount[" + this.bcCaseCount.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand3 = encodeBandInt("bcCaseValue", this.bcCaseValue.toArray(), Codec.DELTA5);
        out.write(encodedBand3);
        PackingUtils.log("Wrote " + encodedBand3.length + " bytes from bcCaseValue[" + this.bcCaseValue.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand4 = encodeBandInt("bcByte", this.bcByte.toArray(), Codec.BYTE1);
        out.write(encodedBand4);
        PackingUtils.log("Wrote " + encodedBand4.length + " bytes from bcByte[" + this.bcByte.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand5 = encodeBandInt("bcShort", this.bcShort.toArray(), Codec.DELTA5);
        out.write(encodedBand5);
        PackingUtils.log("Wrote " + encodedBand5.length + " bytes from bcShort[" + this.bcShort.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand6 = encodeBandInt("bcLocal", this.bcLocal.toArray(), Codec.UNSIGNED5);
        out.write(encodedBand6);
        PackingUtils.log("Wrote " + encodedBand6.length + " bytes from bcLocal[" + this.bcLocal.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand7 = encodeBandInt("bcLabel", integerListToArray(this.bcLabel), Codec.BRANCH5);
        out.write(encodedBand7);
        PackingUtils.log("Wrote " + encodedBand7.length + " bytes from bcLabel[" + this.bcLabel.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand8 = encodeBandInt("bcIntref", cpEntryListToArray(this.bcIntref), Codec.DELTA5);
        out.write(encodedBand8);
        PackingUtils.log("Wrote " + encodedBand8.length + " bytes from bcIntref[" + this.bcIntref.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand9 = encodeBandInt("bcFloatRef", cpEntryListToArray(this.bcFloatRef), Codec.DELTA5);
        out.write(encodedBand9);
        PackingUtils.log("Wrote " + encodedBand9.length + " bytes from bcFloatRef[" + this.bcFloatRef.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand10 = encodeBandInt("bcLongRef", cpEntryListToArray(this.bcLongRef), Codec.DELTA5);
        out.write(encodedBand10);
        PackingUtils.log("Wrote " + encodedBand10.length + " bytes from bcLongRef[" + this.bcLongRef.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand11 = encodeBandInt("bcDoubleRef", cpEntryListToArray(this.bcDoubleRef), Codec.DELTA5);
        out.write(encodedBand11);
        PackingUtils.log("Wrote " + encodedBand11.length + " bytes from bcDoubleRef[" + this.bcDoubleRef.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand12 = encodeBandInt("bcStringRef", cpEntryListToArray(this.bcStringRef), Codec.DELTA5);
        out.write(encodedBand12);
        PackingUtils.log("Wrote " + encodedBand12.length + " bytes from bcStringRef[" + this.bcStringRef.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand13 = encodeBandInt("bcClassRef", cpEntryOrNullListToArray(this.bcClassRef), Codec.UNSIGNED5);
        out.write(encodedBand13);
        PackingUtils.log("Wrote " + encodedBand13.length + " bytes from bcClassRef[" + this.bcClassRef.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand14 = encodeBandInt("bcFieldRef", cpEntryListToArray(this.bcFieldRef), Codec.DELTA5);
        out.write(encodedBand14);
        PackingUtils.log("Wrote " + encodedBand14.length + " bytes from bcFieldRef[" + this.bcFieldRef.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand15 = encodeBandInt("bcMethodRef", cpEntryListToArray(this.bcMethodRef), Codec.UNSIGNED5);
        out.write(encodedBand15);
        PackingUtils.log("Wrote " + encodedBand15.length + " bytes from bcMethodRef[" + this.bcMethodRef.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand16 = encodeBandInt("bcIMethodRef", cpEntryListToArray(this.bcIMethodRef), Codec.DELTA5);
        out.write(encodedBand16);
        PackingUtils.log("Wrote " + encodedBand16.length + " bytes from bcIMethodRef[" + this.bcIMethodRef.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand17 = encodeBandInt("bcThisField", integerListToArray(this.bcThisField), Codec.UNSIGNED5);
        out.write(encodedBand17);
        PackingUtils.log("Wrote " + encodedBand17.length + " bytes from bcThisField[" + this.bcThisField.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand18 = encodeBandInt("bcSuperField", integerListToArray(this.bcSuperField), Codec.UNSIGNED5);
        out.write(encodedBand18);
        PackingUtils.log("Wrote " + encodedBand18.length + " bytes from bcSuperField[" + this.bcSuperField.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand19 = encodeBandInt("bcThisMethod", integerListToArray(this.bcThisMethod), Codec.UNSIGNED5);
        out.write(encodedBand19);
        PackingUtils.log("Wrote " + encodedBand19.length + " bytes from bcThisMethod[" + this.bcThisMethod.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand20 = encodeBandInt("bcSuperMethod", integerListToArray(this.bcSuperMethod), Codec.UNSIGNED5);
        out.write(encodedBand20);
        PackingUtils.log("Wrote " + encodedBand20.length + " bytes from bcSuperMethod[" + this.bcSuperMethod.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand21 = encodeBandInt("bcInitRef", integerListToArray(this.bcInitRef), Codec.UNSIGNED5);
        out.write(encodedBand21);
        PackingUtils.log("Wrote " + encodedBand21.length + " bytes from bcInitRef[" + this.bcInitRef.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
    }

    public void setCurrentClass(String name, String superName) {
        this.currentClass = name;
        this.superClass = superName;
    }

    private void updateRenumbering() {
        if (this.bciRenumbering.isEmpty()) {
            this.bciRenumbering.add(0);
        }
        this.renumberedOffset++;
        for (int i = this.bciRenumbering.size(); i < this.byteCodeOffset; i++) {
            this.bciRenumbering.add(-1);
        }
        this.bciRenumbering.add(this.renumberedOffset);
    }

    public void visitEnd() {
        for (int i = 0; i < this.bciRenumbering.size(); i++) {
            if (this.bciRenumbering.get(i) == -1) {
                this.bciRenumbering.remove(i);
                IntList intList = this.bciRenumbering;
                int i2 = this.renumberedOffset + 1;
                this.renumberedOffset = i2;
                intList.add(i, i2);
            }
        }
        int i3 = this.renumberedOffset;
        if (i3 != 0) {
            if (this.renumberedOffset + 1 != this.bciRenumbering.size()) {
                throw new IllegalStateException("Mistake made with renumbering");
            }
            for (int i4 = this.bcLabel.size() - 1; i4 >= 0; i4--) {
                Object label = this.bcLabel.get(i4);
                if (label instanceof Integer) {
                    break;
                }
                if (label instanceof Label) {
                    this.bcLabel.remove(i4);
                    Integer offset = this.labelsToOffsets.get(label);
                    int relativeOffset = this.bcLabelRelativeOffsets.get(i4);
                    this.bcLabel.add(i4, Integer.valueOf(this.bciRenumbering.get(offset.intValue()) - this.bciRenumbering.get(relativeOffset)));
                }
            }
            this.bcCodes.add(255);
            this.segment.getClassBands().doBciRenumbering(this.bciRenumbering, this.labelsToOffsets);
            this.bciRenumbering.clear();
            this.labelsToOffsets.clear();
            this.byteCodeOffset = 0;
            this.renumberedOffset = 0;
        }
    }

    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        this.byteCodeOffset += 3;
        updateRenumbering();
        boolean aload_0 = false;
        if (this.bcCodes.size() > 0 && this.bcCodes.get(this.bcCodes.size() - 1) == 42) {
            this.bcCodes.remove(this.bcCodes.size() - 1);
            aload_0 = true;
        }
        CPMethodOrField cpField = this.cpBands.getCPField(owner, name, desc);
        if (aload_0) {
            opcode += 7;
        }
        if (owner.equals(this.currentClass)) {
            opcode += 24;
            this.bcThisField.add(cpField);
        } else {
            if (aload_0) {
                opcode -= 7;
                this.bcCodes.add(42);
            }
            this.bcFieldRef.add(cpField);
        }
        this.bcCodes.add(opcode);
    }

    public void visitIincInsn(int var, int increment) {
        if (var > 255 || increment > 255) {
            this.byteCodeOffset += 6;
            this.bcCodes.add(196);
            this.bcCodes.add(132);
            this.bcLocal.add(var);
            this.bcShort.add(increment);
        } else {
            this.byteCodeOffset += 3;
            this.bcCodes.add(132);
            this.bcLocal.add(var);
            this.bcByte.add(increment & 255);
        }
        updateRenumbering();
    }

    public void visitInsn(int opcode) {
        if (opcode >= 202) {
            throw new IllegalArgumentException("Non-standard bytecode instructions not supported");
        }
        this.bcCodes.add(opcode);
        this.byteCodeOffset++;
        updateRenumbering();
    }

    public void visitIntInsn(int opcode, int operand) {
        switch (opcode) {
            case 16:
            case 188:
                this.bcCodes.add(opcode);
                this.bcByte.add(operand & 255);
                this.byteCodeOffset += 2;
                break;
            case 17:
                this.bcCodes.add(opcode);
                this.bcShort.add(operand);
                this.byteCodeOffset += 3;
                break;
        }
        updateRenumbering();
    }

    public void visitJumpInsn(int opcode, Label label) {
        this.bcCodes.add(opcode);
        this.bcLabel.add(label);
        this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        this.byteCodeOffset += 3;
        updateRenumbering();
    }

    public void visitLabel(Label label) {
        this.labelsToOffsets.put(label, Integer.valueOf(this.byteCodeOffset));
    }

    public void visitLdcInsn(Object cst) {
        CPConstant<?> constant = this.cpBands.getConstant(cst);
        if (this.segment.lastConstantHadWideIndex() || (constant instanceof CPLong) || (constant instanceof CPDouble)) {
            this.byteCodeOffset += 3;
            if (constant instanceof CPInt) {
                this.bcCodes.add(237);
                this.bcIntref.add((CPInt) constant);
            } else if (constant instanceof CPFloat) {
                this.bcCodes.add(238);
                this.bcFloatRef.add((CPFloat) constant);
            } else if (constant instanceof CPLong) {
                this.bcCodes.add(20);
                this.bcLongRef.add((CPLong) constant);
            } else if (constant instanceof CPDouble) {
                this.bcCodes.add(UnknownRecord.PHONETICPR_00EF);
                this.bcDoubleRef.add((CPDouble) constant);
            } else if (constant instanceof CPString) {
                this.bcCodes.add(19);
                this.bcStringRef.add((CPString) constant);
            } else if (constant instanceof CPClass) {
                this.bcCodes.add(236);
                this.bcClassRef.add((CPClass) constant);
            } else {
                throw new IllegalArgumentException("Constant should not be null");
            }
        } else {
            this.byteCodeOffset += 2;
            if (constant instanceof CPInt) {
                this.bcCodes.add(234);
                this.bcIntref.add((CPInt) constant);
            } else if (constant instanceof CPFloat) {
                this.bcCodes.add(235);
                this.bcFloatRef.add((CPFloat) constant);
            } else if (constant instanceof CPString) {
                this.bcCodes.add(18);
                this.bcStringRef.add((CPString) constant);
            } else if (constant instanceof CPClass) {
                this.bcCodes.add(UnknownRecord.BITMAP_00E9);
                this.bcClassRef.add((CPClass) constant);
            }
        }
        updateRenumbering();
    }

    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        this.bcCodes.add(171);
        this.bcLabel.add(dflt);
        this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        this.bcCaseCount.add(keys.length);
        for (int i = 0; i < labels.length; i++) {
            this.bcCaseValue.add(keys[i]);
            this.bcLabel.add(labels[i]);
            this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        }
        int i2 = this.byteCodeOffset;
        int padding = (i2 + 1) % 4 == 0 ? 0 : 4 - ((this.byteCodeOffset + 1) % 4);
        this.byteCodeOffset += padding + 1 + 8 + (keys.length * 8);
        updateRenumbering();
    }

    public void visitMethodInsn(int opcode, String owner, String name, String desc) {
        this.byteCodeOffset += 3;
        switch (opcode) {
            case 182:
            case 183:
            case 184:
                boolean aload_0 = false;
                if (this.bcCodes.size() > 0 && this.bcCodes.get(this.bcCodes.size() - 1) == 42) {
                    this.bcCodes.remove(this.bcCodes.size() - 1);
                    aload_0 = true;
                    opcode += 7;
                }
                if (owner.equals(this.currentClass)) {
                    opcode += 24;
                    if (name.equals("<init>") && opcode == 207) {
                        opcode = 230;
                        this.bcInitRef.add(this.cpBands.getCPMethod(owner, name, desc));
                    } else {
                        this.bcThisMethod.add(this.cpBands.getCPMethod(owner, name, desc));
                    }
                } else if (owner.equals(this.superClass)) {
                    opcode += 38;
                    if (name.equals("<init>") && opcode == 221) {
                        opcode = 231;
                        this.bcInitRef.add(this.cpBands.getCPMethod(owner, name, desc));
                    } else {
                        this.bcSuperMethod.add(this.cpBands.getCPMethod(owner, name, desc));
                    }
                } else {
                    if (aload_0) {
                        opcode -= 7;
                        this.bcCodes.add(42);
                    }
                    if (name.equals("<init>") && opcode == 183 && owner.equals(this.currentNewClass)) {
                        opcode = 232;
                        this.bcInitRef.add(this.cpBands.getCPMethod(owner, name, desc));
                    } else {
                        this.bcMethodRef.add(this.cpBands.getCPMethod(owner, name, desc));
                    }
                }
                this.bcCodes.add(opcode);
                break;
            case 185:
                this.byteCodeOffset += 2;
                CPMethodOrField cpIMethod = this.cpBands.getCPIMethod(owner, name, desc);
                this.bcIMethodRef.add(cpIMethod);
                this.bcCodes.add(185);
                break;
        }
        updateRenumbering();
    }

    public void visitMultiANewArrayInsn(String desc, int dimensions) {
        this.byteCodeOffset += 4;
        updateRenumbering();
        this.bcCodes.add(197);
        this.bcClassRef.add(this.cpBands.getCPClass(desc));
        this.bcByte.add(dimensions & 255);
    }

    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        this.bcCodes.add(170);
        this.bcLabel.add(dflt);
        this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        this.bcCaseValue.add(min);
        int count = labels.length;
        this.bcCaseCount.add(count);
        for (Label label : labels) {
            this.bcLabel.add(label);
            this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        }
        int i = this.byteCodeOffset;
        int padding = i % 4 == 0 ? 0 : 4 - (this.byteCodeOffset % 4);
        this.byteCodeOffset += padding + 12 + (labels.length * 4);
        updateRenumbering();
    }

    public void visitTypeInsn(int opcode, String type) {
        this.byteCodeOffset += 3;
        updateRenumbering();
        this.bcCodes.add(opcode);
        this.bcClassRef.add(this.cpBands.getCPClass(type));
        if (opcode == 187) {
            this.currentNewClass = type;
        }
    }

    public void visitVarInsn(int opcode, int var) {
        if (var > 255) {
            this.byteCodeOffset += 4;
            this.bcCodes.add(196);
            this.bcCodes.add(opcode);
            this.bcLocal.add(var);
        } else if (var > 3 || opcode == 169) {
            this.byteCodeOffset += 2;
            this.bcCodes.add(opcode);
            this.bcLocal.add(var);
        } else {
            this.byteCodeOffset++;
            switch (opcode) {
                case 21:
                case 54:
                    this.bcCodes.add(opcode + 5 + var);
                    break;
                case 22:
                case 55:
                    this.bcCodes.add(opcode + 8 + var);
                    break;
                case 23:
                case 56:
                    this.bcCodes.add(opcode + 11 + var);
                    break;
                case 24:
                case 57:
                    this.bcCodes.add(opcode + 14 + var);
                    break;
                case 25:
                case 58:
                    this.bcCodes.add(opcode + 17 + var);
                    break;
            }
        }
        updateRenumbering();
    }
}
