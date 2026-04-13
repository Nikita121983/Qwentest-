package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.ThreeDEval;
import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
final class CountUtils {

    /* loaded from: classes10.dex */
    public interface I_MatchAreaPredicate extends I_MatchPredicate {
        boolean matches(TwoDEval twoDEval, int i, int i2);
    }

    /* loaded from: classes10.dex */
    public interface I_MatchPredicate {
        boolean matches(ValueEval valueEval);
    }

    private CountUtils() {
    }

    public static int countMatchingCellsInArea(ThreeDEval areaEval, I_MatchPredicate criteriaPredicate) {
        int rcIx;
        int result = 0;
        int firstSheetIndex = areaEval.getFirstSheetIndex();
        int lastSheetIndex = areaEval.getLastSheetIndex();
        for (int sIx = firstSheetIndex; sIx <= lastSheetIndex; sIx++) {
            int height = areaEval.getHeight();
            int width = areaEval.getWidth();
            for (int rrIx = 0; rrIx < height; rrIx++) {
                while (rcIx < width) {
                    ValueEval ve = areaEval.getValue(sIx, rrIx, rcIx);
                    if (criteriaPredicate instanceof I_MatchAreaPredicate) {
                        I_MatchAreaPredicate areaPredicate = (I_MatchAreaPredicate) criteriaPredicate;
                        rcIx = areaPredicate.matches(areaEval, rrIx, rcIx) ? 0 : rcIx + 1;
                    }
                    if (criteriaPredicate.matches(ve)) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    public static int countMatchingCellsInRef(RefEval refEval, I_MatchPredicate criteriaPredicate) {
        int result = 0;
        int firstSheetIndex = refEval.getFirstSheetIndex();
        int lastSheetIndex = refEval.getLastSheetIndex();
        for (int sIx = firstSheetIndex; sIx <= lastSheetIndex; sIx++) {
            ValueEval ve = refEval.getInnerValueEval(sIx);
            if (criteriaPredicate.matches(ve)) {
                result++;
            }
        }
        return result;
    }

    public static int countArg(ValueEval valueEval, I_MatchPredicate i_MatchPredicate) {
        if (valueEval == null) {
            throw new IllegalArgumentException("eval must not be null");
        }
        if (valueEval instanceof ThreeDEval) {
            return countMatchingCellsInArea((ThreeDEval) valueEval, i_MatchPredicate);
        }
        if (valueEval instanceof TwoDEval) {
            throw new IllegalArgumentException("Count requires 3D Evals, 2D ones aren't supported");
        }
        if (valueEval instanceof RefEval) {
            return countMatchingCellsInRef((RefEval) valueEval, i_MatchPredicate);
        }
        return i_MatchPredicate.matches(valueEval) ? 1 : 0;
    }
}
