package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.ptg.AbstractFunctionPtg;
import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.apache.poi.ss.formula.ptg.ControlPtg;
import org.apache.poi.ss.formula.ptg.FuncVarPtg;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.poi.ss.formula.ptg.MemAreaPtg;
import org.apache.poi.ss.formula.ptg.MemFuncPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.RangePtg;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class OperandClassTransformer {
    private final FormulaType _formulaType;

    public OperandClassTransformer(FormulaType formulaType) {
        this._formulaType = formulaType;
    }

    public void transformFormula(ParseNode rootNode) {
        byte rootNodeOperandClass;
        switch (this._formulaType) {
            case CELL:
                rootNodeOperandClass = 32;
                break;
            case ARRAY:
                rootNodeOperandClass = Ptg.CLASS_ARRAY;
                break;
            case NAMEDRANGE:
            case DATAVALIDATION_LIST:
                rootNodeOperandClass = 0;
                break;
            default:
                throw new IllegalStateException("Incomplete code - formula type (" + this._formulaType + ") not supported yet");
        }
        transformNode(rootNode, rootNodeOperandClass, false);
    }

    private void transformNode(ParseNode node, byte desiredOperandClass, boolean callerForceArrayFlag) {
        Ptg token = node.getToken();
        ParseNode[] children = node.getChildren();
        boolean isSimpleValueFunc = isSimpleValueFunction(token);
        int i = 0;
        if (isSimpleValueFunc) {
            boolean localForceArray = desiredOperandClass == 64;
            int length = children.length;
            while (i < length) {
                ParseNode child = children[i];
                transformNode(child, desiredOperandClass, localForceArray);
                i++;
            }
            setSimpleValueFuncClass((AbstractFunctionPtg) token, desiredOperandClass, callerForceArrayFlag);
            return;
        }
        boolean localForceArray2 = isSingleArgSum(token);
        if (localForceArray2) {
            token = FuncVarPtg.SUM;
        }
        if ((token instanceof ValueOperatorPtg) || (token instanceof ControlPtg) || (token instanceof MemFuncPtg) || (token instanceof MemAreaPtg) || (token instanceof UnionPtg) || (token instanceof IntersectionPtg)) {
            byte localDesiredOperandClass = desiredOperandClass == 0 ? (byte) 32 : desiredOperandClass;
            int length2 = children.length;
            while (i < length2) {
                ParseNode child2 = children[i];
                transformNode(child2, localDesiredOperandClass, callerForceArrayFlag);
                i++;
            }
            return;
        }
        if (token instanceof AbstractFunctionPtg) {
            transformFunctionNode((AbstractFunctionPtg) token, children, desiredOperandClass, callerForceArrayFlag);
            return;
        }
        if (children.length > 0) {
            if (token == RangePtg.instance) {
            } else {
                throw new IllegalStateException("Node should not have any children");
            }
        } else {
            if (token.isBaseToken()) {
                return;
            }
            token.setClass(transformClass(token.getPtgClass(), desiredOperandClass, callerForceArrayFlag));
        }
    }

    private static boolean isSingleArgSum(Ptg token) {
        if (token instanceof AttrPtg) {
            AttrPtg attrPtg = (AttrPtg) token;
            return attrPtg.isSum();
        }
        return false;
    }

    private static boolean isSimpleValueFunction(Ptg token) {
        if (!(token instanceof AbstractFunctionPtg)) {
            return false;
        }
        AbstractFunctionPtg aptg = (AbstractFunctionPtg) token;
        if (aptg.getDefaultOperandClass() != 32) {
            return false;
        }
        int numberOfOperands = aptg.getNumberOfOperands();
        for (int i = numberOfOperands - 1; i >= 0; i--) {
            if (aptg.getParameterClass(i) != 32) {
                return false;
            }
        }
        return true;
    }

    private byte transformClass(byte currentOperandClass, byte desiredOperandClass, boolean callerForceArrayFlag) {
        switch (desiredOperandClass) {
            case 0:
                if (!callerForceArrayFlag) {
                    return currentOperandClass;
                }
                return (byte) 0;
            case 32:
                if (!callerForceArrayFlag) {
                    return (byte) 32;
                }
                return Ptg.CLASS_ARRAY;
            case 64:
                return Ptg.CLASS_ARRAY;
            default:
                throw new IllegalStateException("Unexpected operand class (" + ((int) desiredOperandClass) + ")");
        }
    }

    private void transformFunctionNode(AbstractFunctionPtg afp, ParseNode[] children, byte desiredOperandClass, boolean callerForceArrayFlag) {
        byte defaultReturnOperandClass = afp.getDefaultOperandClass();
        boolean localForceArrayFlag = false;
        if (callerForceArrayFlag) {
            switch (defaultReturnOperandClass) {
                case 0:
                    if (desiredOperandClass == 0) {
                        afp.setClass((byte) 0);
                    } else {
                        afp.setClass(Ptg.CLASS_ARRAY);
                    }
                    localForceArrayFlag = false;
                    break;
                case 32:
                    afp.setClass(Ptg.CLASS_ARRAY);
                    localForceArrayFlag = true;
                    break;
                case 64:
                    afp.setClass(Ptg.CLASS_ARRAY);
                    localForceArrayFlag = false;
                    break;
                default:
                    throw new IllegalStateException("Unexpected operand class (" + ((int) defaultReturnOperandClass) + ")");
            }
        } else if (defaultReturnOperandClass == desiredOperandClass) {
            localForceArrayFlag = false;
            afp.setClass(defaultReturnOperandClass);
        } else {
            switch (desiredOperandClass) {
                case 0:
                    switch (defaultReturnOperandClass) {
                        case 32:
                            afp.setClass((byte) 32);
                            break;
                        case 64:
                            afp.setClass(Ptg.CLASS_ARRAY);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected operand class (" + ((int) defaultReturnOperandClass) + ")");
                    }
                    localForceArrayFlag = false;
                    break;
                case 32:
                    afp.setClass((byte) 32);
                    localForceArrayFlag = false;
                    break;
                case 64:
                    switch (defaultReturnOperandClass) {
                        case 0:
                            afp.setClass((byte) 0);
                            break;
                        case 32:
                            afp.setClass(Ptg.CLASS_ARRAY);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected operand class (" + ((int) defaultReturnOperandClass) + ")");
                    }
                    if (defaultReturnOperandClass == 32) {
                        localForceArrayFlag = true;
                        break;
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected operand class (" + ((int) desiredOperandClass) + ")");
            }
        }
        for (int i = 0; i < children.length; i++) {
            ParseNode child = children[i];
            byte paramOperandClass = afp.getParameterClass(i);
            transformNode(child, paramOperandClass, localForceArrayFlag);
        }
    }

    private void setSimpleValueFuncClass(AbstractFunctionPtg afp, byte desiredOperandClass, boolean callerForceArrayFlag) {
        if (callerForceArrayFlag || desiredOperandClass == 64) {
            afp.setClass(Ptg.CLASS_ARRAY);
        } else {
            afp.setClass((byte) 32);
        }
    }
}
