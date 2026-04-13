package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.eval.FunctionNameEval;
import org.apache.poi.ss.formula.eval.NotImplementedFunctionException;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class UserDefinedFunction implements FreeRefFunction {
    public static final FreeRefFunction instance = new UserDefinedFunction();

    private UserDefinedFunction() {
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        int nIncomingArgs = args.length;
        if (nIncomingArgs < 1) {
            throw new IllegalStateException("function name argument missing");
        }
        ValueEval nameArg = args[0];
        if (nameArg instanceof FunctionNameEval) {
            String functionName = ((FunctionNameEval) nameArg).getFunctionName();
            FreeRefFunction targetFunc = ec.findUserDefinedFunction(functionName);
            if (targetFunc == null) {
                throw new NotImplementedFunctionException(functionName);
            }
            int nOutGoingArgs = nIncomingArgs - 1;
            ValueEval[] outGoingArgs = new ValueEval[nOutGoingArgs];
            System.arraycopy(args, 1, outGoingArgs, 0, nOutGoingArgs);
            if (targetFunc instanceof ArrayFunction) {
                ArrayFunction func = (ArrayFunction) targetFunc;
                ValueEval eval = OperationEvaluatorFactory.evaluateArrayFunction(func, outGoingArgs, ec);
                if (eval != null) {
                    return eval;
                }
            }
            return targetFunc.evaluate(outGoingArgs, ec);
        }
        throw new IllegalStateException("First argument should be a NameEval, but got (" + nameArg.getClass().getName() + ")");
    }
}
