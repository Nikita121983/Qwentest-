package org.apache.poi.ss.formula;

import java.util.Stack;
import org.apache.poi.ss.formula.ptg.ArrayInitialPtg;
import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.apache.poi.ss.formula.ptg.MemAreaPtg;
import org.apache.poi.ss.formula.ptg.MemErrPtg;
import org.apache.poi.ss.formula.ptg.MemFuncPtg;
import org.apache.poi.ss.formula.ptg.OperationPtg;
import org.apache.poi.ss.formula.ptg.ParenthesisPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public class FormulaRenderer {
    /* JADX WARN: Multi-variable type inference failed */
    public static String toFormulaString(FormulaRenderingWorkbook book, Ptg[] ptgArr) {
        if (ptgArr == 0 || ptgArr.length == 0) {
            throw new IllegalArgumentException("ptgs must not be null");
        }
        Stack<String> stack = new Stack<>();
        for (ArrayInitialPtg arrayInitialPtg : ptgArr) {
            if (!(arrayInitialPtg instanceof MemAreaPtg) && !(arrayInitialPtg instanceof MemFuncPtg) && !(arrayInitialPtg instanceof MemErrPtg)) {
                if (arrayInitialPtg instanceof ParenthesisPtg) {
                    String contents = stack.pop();
                    stack.push("(" + contents + ")");
                } else if (arrayInitialPtg instanceof AttrPtg) {
                    AttrPtg attrPtg = (AttrPtg) arrayInitialPtg;
                    if (!attrPtg.isOptimizedIf() && !attrPtg.isOptimizedChoose() && !attrPtg.isSkip() && !attrPtg.isSpace() && !attrPtg.isSemiVolatile()) {
                        if (attrPtg.isSum()) {
                            String[] operands = getOperands(stack, attrPtg.getNumberOfOperands());
                            stack.push(attrPtg.toFormulaString(operands));
                        } else {
                            throw new IllegalStateException("Unexpected tAttr: " + attrPtg);
                        }
                    }
                } else if (arrayInitialPtg instanceof WorkbookDependentFormula) {
                    WorkbookDependentFormula optg = (WorkbookDependentFormula) arrayInitialPtg;
                    stack.push(optg.toFormulaString(book));
                } else if (!(arrayInitialPtg instanceof OperationPtg)) {
                    stack.push(arrayInitialPtg.toFormulaString());
                } else {
                    OperationPtg o = (OperationPtg) arrayInitialPtg;
                    String[] operands2 = getOperands(stack, o.getNumberOfOperands());
                    stack.push(o.toFormulaString(operands2));
                }
            }
        }
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack underflow");
        }
        String result = stack.pop();
        if (!stack.isEmpty()) {
            throw new IllegalStateException("too much stuff left on the stack");
        }
        return result;
    }

    private static String[] getOperands(Stack<String> stack, int nOperands) {
        String[] operands = new String[nOperands];
        for (int j = nOperands - 1; j >= 0; j--) {
            if (stack.isEmpty()) {
                String msg = "Too few arguments supplied to operation. Expected (" + nOperands + ") operands but got (" + ((nOperands - j) - 1) + ")";
                throw new IllegalStateException(msg);
            }
            operands[j] = stack.pop();
        }
        return operands;
    }
}
