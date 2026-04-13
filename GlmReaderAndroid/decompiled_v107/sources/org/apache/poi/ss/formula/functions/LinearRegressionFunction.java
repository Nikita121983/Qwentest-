package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.LookupUtils;

/* loaded from: classes10.dex */
public final class LinearRegressionFunction extends Fixed2ArgFunction {
    private final FUNCTION function;

    /* loaded from: classes10.dex */
    public enum FUNCTION {
        INTERCEPT,
        SLOPE
    }

    /* loaded from: classes10.dex */
    private static abstract class ValueArray implements LookupUtils.ValueVector {
        private final int _size;

        protected abstract ValueEval getItemInternal(int i);

        protected ValueArray(int size) {
            this._size = size;
        }

        @Override // org.apache.poi.ss.formula.functions.LookupUtils.ValueVector
        public ValueEval getItem(int index) {
            if (index < 0 || index > this._size) {
                throw new IllegalArgumentException("Specified index " + index + " is outside range (0.." + (this._size - 1) + ")");
            }
            return getItemInternal(index);
        }

        @Override // org.apache.poi.ss.formula.functions.LookupUtils.ValueVector
        public final int getSize() {
            return this._size;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class SingleCellValueArray extends ValueArray {
        private final ValueEval _value;

        public SingleCellValueArray(ValueEval value) {
            super(1);
            this._value = value;
        }

        @Override // org.apache.poi.ss.formula.functions.LinearRegressionFunction.ValueArray
        protected ValueEval getItemInternal(int index) {
            return this._value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class RefValueArray extends ValueArray {
        private final RefEval _ref;
        private final int _width;

        public RefValueArray(RefEval ref) {
            super(ref.getNumberOfSheets());
            this._ref = ref;
            this._width = ref.getNumberOfSheets();
        }

        @Override // org.apache.poi.ss.formula.functions.LinearRegressionFunction.ValueArray
        protected ValueEval getItemInternal(int index) {
            int sIx = (index % this._width) + this._ref.getFirstSheetIndex();
            return this._ref.getInnerValueEval(sIx);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class AreaValueArray extends ValueArray {
        private final TwoDEval _ae;
        private final int _width;

        public AreaValueArray(TwoDEval ae) {
            super(ae.getWidth() * ae.getHeight());
            this._ae = ae;
            this._width = ae.getWidth();
        }

        @Override // org.apache.poi.ss.formula.functions.LinearRegressionFunction.ValueArray
        protected ValueEval getItemInternal(int index) {
            int rowIx = index / this._width;
            int colIx = index % this._width;
            return this._ae.getValue(rowIx, colIx);
        }
    }

    public LinearRegressionFunction(FUNCTION function) {
        this.function = function;
    }

    @Override // org.apache.poi.ss.formula.functions.Function2Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        try {
            LookupUtils.ValueVector vvY = createValueVector(arg0);
            LookupUtils.ValueVector vvX = createValueVector(arg1);
            int size = vvX.getSize();
            if (size != 0 && vvY.getSize() == size) {
                double result = evaluateInternal(vvX, vvY, size);
                if (Double.isNaN(result) || Double.isInfinite(result)) {
                    return ErrorEval.NUM_ERROR;
                }
                return new NumberEval(result);
            }
            return ErrorEval.NA;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private double evaluateInternal(LookupUtils.ValueVector x, LookupUtils.ValueVector y, int size) throws EvaluationException {
        LookupUtils.ValueVector valueVector = x;
        LookupUtils.ValueVector valueVector2 = y;
        int i = size;
        ErrorEval firstYerr = null;
        boolean accumlatedSome = false;
        double sumx = 0.0d;
        double sumy = 0.0d;
        for (int i2 = 0; i2 < i; i2++) {
            ValueEval vx = valueVector.getItem(i2);
            ValueEval vy = valueVector2.getItem(i2);
            if (vx instanceof ErrorEval) {
                throw new EvaluationException((ErrorEval) vx);
            }
            if ((vy instanceof ErrorEval) && firstYerr == null) {
                firstYerr = (ErrorEval) vy;
            } else if ((vx instanceof NumberEval) && (vy instanceof NumberEval)) {
                accumlatedSome = true;
                NumberEval ny = (NumberEval) vy;
                sumx += ((NumberEval) vx).getNumberValue();
                sumy += ny.getNumberValue();
            }
        }
        if (firstYerr != null) {
            throw new EvaluationException(firstYerr);
        }
        if (!accumlatedSome) {
            throw new EvaluationException(ErrorEval.DIV_ZERO);
        }
        double xbar = sumx / i;
        double ybar = sumy / i;
        double xxbar = 0.0d;
        double xybar = 0.0d;
        int i3 = 0;
        while (i3 < i) {
            ValueEval vx2 = valueVector.getItem(i3);
            ValueEval vy2 = valueVector2.getItem(i3);
            if ((vx2 instanceof NumberEval) && (vy2 instanceof NumberEval)) {
                NumberEval nx = (NumberEval) vx2;
                NumberEval ny2 = (NumberEval) vy2;
                xxbar += (nx.getNumberValue() - xbar) * (nx.getNumberValue() - xbar);
                xybar += (nx.getNumberValue() - xbar) * (ny2.getNumberValue() - ybar);
            }
            i3++;
            valueVector = x;
            valueVector2 = y;
            i = size;
        }
        if (xxbar == 0.0d) {
            throw new EvaluationException(ErrorEval.DIV_ZERO);
        }
        double beta1 = xybar / xxbar;
        double beta0 = ybar - (beta1 * xbar);
        return this.function == FUNCTION.INTERCEPT ? beta0 : beta1;
    }

    private static LookupUtils.ValueVector createValueVector(ValueEval arg) throws EvaluationException {
        if (arg instanceof ErrorEval) {
            throw new EvaluationException((ErrorEval) arg);
        }
        if (arg instanceof TwoDEval) {
            return new AreaValueArray((TwoDEval) arg);
        }
        if (arg instanceof RefEval) {
            return new RefValueArray((RefEval) arg);
        }
        return new SingleCellValueArray(arg);
    }
}
