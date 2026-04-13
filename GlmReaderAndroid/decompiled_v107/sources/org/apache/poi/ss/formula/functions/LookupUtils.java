package org.apache.poi.ss.formula.functions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Countif;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public final class LookupUtils {
    private static final Map<Integer, MatchMode> matchModeMap;
    private static final Map<Integer, SearchMode> searchModeMap;

    /* loaded from: classes10.dex */
    public interface LookupValueComparer {
        CompareResult compareTo(ValueEval valueEval);
    }

    /* loaded from: classes10.dex */
    public enum MatchMode {
        ExactMatch(0),
        ExactMatchFallbackToSmallerValue(-1),
        ExactMatchFallbackToLargerValue(1),
        WildcardMatch(2);

        private final int intValue;

        MatchMode(int intValue) {
            this.intValue = intValue;
        }

        public int getIntValue() {
            return this.intValue;
        }
    }

    /* loaded from: classes10.dex */
    public enum SearchMode {
        IterateForward(1),
        IterateBackward(-1),
        BinarySearchForward(2),
        BinarySearchBackward(-2);

        private final int intValue;

        SearchMode(int intValue) {
            this.intValue = intValue;
        }

        public int getIntValue() {
            return this.intValue;
        }
    }

    static {
        Map<Integer, MatchMode> mmMap = new HashMap<>();
        for (MatchMode mode : MatchMode.values()) {
            mmMap.put(Integer.valueOf(mode.getIntValue()), mode);
        }
        matchModeMap = Collections.unmodifiableMap(mmMap);
        Map<Integer, SearchMode> smMap = new HashMap<>();
        for (SearchMode mode2 : SearchMode.values()) {
            smMap.put(Integer.valueOf(mode2.getIntValue()), mode2);
        }
        searchModeMap = Collections.unmodifiableMap(smMap);
    }

    public static MatchMode matchMode(int m) {
        MatchMode mode = matchModeMap.get(Integer.valueOf(m));
        if (mode == null) {
            throw new IllegalArgumentException("unknown match mode " + m);
        }
        return mode;
    }

    public static SearchMode searchMode(int s) {
        SearchMode mode = searchModeMap.get(Integer.valueOf(s));
        if (mode == null) {
            throw new IllegalArgumentException("unknown search mode " + s);
        }
        return mode;
    }

    /* loaded from: classes10.dex */
    public interface ValueVector {
        ValueEval getItem(int i);

        int getSize();

        default Iterator<Integer> indexIterator() {
            return new Iterator<Integer>() { // from class: org.apache.poi.ss.formula.functions.LookupUtils.ValueVector.1
                private int pos = 0;

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.pos < ValueVector.this.getSize();
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.Iterator
                public Integer next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    int i = this.pos;
                    this.pos = i + 1;
                    return Integer.valueOf(i);
                }
            };
        }

        default Iterator<Integer> reverseIndexIterator() {
            return new Iterator<Integer>() { // from class: org.apache.poi.ss.formula.functions.LookupUtils.ValueVector.2
                private int pos;

                {
                    this.pos = ValueVector.this.getSize() - 1;
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.pos > 0;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.Iterator
                public Integer next() {
                    this.pos--;
                    if (this.pos < 0) {
                        throw new NoSuchElementException();
                    }
                    return Integer.valueOf(this.pos);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class RowVector implements ValueVector {
        private final int _rowIndex;
        private final int _size;
        private final TwoDEval _tableArray;

        public RowVector(TwoDEval tableArray, int rowIndex) {
            this._rowIndex = rowIndex;
            int lastRowIx = tableArray.getHeight() - 1;
            if (rowIndex < 0 || rowIndex > lastRowIx) {
                throw new IllegalArgumentException("Specified row index (" + rowIndex + ") is outside the allowed range (0.." + lastRowIx + ")");
            }
            this._tableArray = tableArray;
            this._size = tableArray.getWidth();
        }

        @Override // org.apache.poi.ss.formula.functions.LookupUtils.ValueVector
        public ValueEval getItem(int index) {
            if (index > this._size) {
                throw new ArrayIndexOutOfBoundsException("Specified index (" + index + ") is outside the allowed range (0.." + (this._size - 1) + ")");
            }
            return this._tableArray.getValue(this._rowIndex, index);
        }

        @Override // org.apache.poi.ss.formula.functions.LookupUtils.ValueVector
        public int getSize() {
            return this._size;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class ColumnVector implements ValueVector {
        private final int _columnIndex;
        private final int _size;
        private final TwoDEval _tableArray;

        public ColumnVector(TwoDEval tableArray, int columnIndex) {
            this._columnIndex = columnIndex;
            int lastColIx = tableArray.getWidth() - 1;
            if (columnIndex < 0 || columnIndex > lastColIx) {
                throw new IllegalArgumentException("Specified column index (" + columnIndex + ") is outside the allowed range (0.." + lastColIx + ")");
            }
            this._tableArray = tableArray;
            this._size = this._tableArray.getHeight();
        }

        @Override // org.apache.poi.ss.formula.functions.LookupUtils.ValueVector
        public ValueEval getItem(int index) {
            if (index > this._size) {
                throw new ArrayIndexOutOfBoundsException("Specified index (" + index + ") is outside the allowed range (0.." + (this._size - 1) + ")");
            }
            return this._tableArray.getValue(index, this._columnIndex);
        }

        @Override // org.apache.poi.ss.formula.functions.LookupUtils.ValueVector
        public int getSize() {
            return this._size;
        }
    }

    /* loaded from: classes10.dex */
    private static final class SheetVector implements ValueVector {
        private final RefEval _re;
        private final int _size;

        public SheetVector(RefEval re) {
            this._size = re.getNumberOfSheets();
            this._re = re;
        }

        @Override // org.apache.poi.ss.formula.functions.LookupUtils.ValueVector
        public ValueEval getItem(int index) {
            if (index >= this._size) {
                throw new ArrayIndexOutOfBoundsException("Specified index (" + index + ") is outside the allowed range (0.." + (this._size - 1) + ")");
            }
            int sheetIndex = this._re.getFirstSheetIndex() + index;
            return this._re.getInnerValueEval(sheetIndex);
        }

        @Override // org.apache.poi.ss.formula.functions.LookupUtils.ValueVector
        public int getSize() {
            return this._size;
        }
    }

    public static ValueVector createRowVector(TwoDEval tableArray, int relativeRowIndex) {
        return new RowVector(tableArray, relativeRowIndex);
    }

    public static ValueVector createColumnVector(TwoDEval tableArray, int relativeColumnIndex) {
        return new ColumnVector(tableArray, relativeColumnIndex);
    }

    public static ValueVector createVector(TwoDEval ae) {
        if (ae.isColumn()) {
            return createColumnVector(ae, 0);
        }
        if (ae.isRow()) {
            return createRowVector(ae, 0);
        }
        return null;
    }

    public static ValueVector createVector(RefEval re) {
        return new SheetVector(re);
    }

    /* loaded from: classes10.dex */
    public static final class CompareResult {
        private final boolean _isEqual;
        private final boolean _isGreaterThan;
        private final boolean _isLessThan;
        private final boolean _isTypeMismatch;
        public static final CompareResult TYPE_MISMATCH = new CompareResult(true, 0);
        public static final CompareResult LESS_THAN = new CompareResult(false, -1);
        public static final CompareResult EQUAL = new CompareResult(false, 0);
        public static final CompareResult GREATER_THAN = new CompareResult(false, 1);

        private CompareResult(boolean isTypeMismatch, int simpleCompareResult) {
            if (isTypeMismatch) {
                this._isTypeMismatch = true;
                this._isLessThan = false;
                this._isEqual = false;
                this._isGreaterThan = false;
                return;
            }
            this._isTypeMismatch = false;
            this._isLessThan = simpleCompareResult < 0;
            this._isEqual = simpleCompareResult == 0;
            this._isGreaterThan = simpleCompareResult > 0;
        }

        public static CompareResult valueOf(int simpleCompareResult) {
            if (simpleCompareResult < 0) {
                return LESS_THAN;
            }
            if (simpleCompareResult > 0) {
                return GREATER_THAN;
            }
            return EQUAL;
        }

        public static CompareResult valueOf(boolean matches) {
            if (matches) {
                return EQUAL;
            }
            return LESS_THAN;
        }

        public boolean isTypeMismatch() {
            return this._isTypeMismatch;
        }

        public boolean isLessThan() {
            return this._isLessThan;
        }

        public boolean isEqual() {
            return this._isEqual;
        }

        public boolean isGreaterThan() {
            return this._isGreaterThan;
        }

        public String toString() {
            return getClass().getName() + " [" + formatAsString() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
        }

        private String formatAsString() {
            if (this._isTypeMismatch) {
                return "TYPE_MISMATCH";
            }
            if (this._isLessThan) {
                return "LESS_THAN";
            }
            if (this._isEqual) {
                return "EQUAL";
            }
            if (this._isGreaterThan) {
                return "GREATER_THAN";
            }
            return "??error??";
        }
    }

    /* loaded from: classes10.dex */
    private static abstract class LookupValueComparerBase implements LookupValueComparer {
        private final Class<? extends ValueEval> _targetClass;

        protected abstract CompareResult compareSameType(ValueEval valueEval);

        protected abstract String getValueAsString();

        protected LookupValueComparerBase(ValueEval targetValue) {
            if (targetValue == null) {
                throw new IllegalStateException("targetValue cannot be null");
            }
            this._targetClass = targetValue.getClass();
        }

        @Override // org.apache.poi.ss.formula.functions.LookupUtils.LookupValueComparer
        public final CompareResult compareTo(ValueEval other) {
            if (other == null) {
                throw new IllegalStateException("compare to value cannot be null");
            }
            if (this._targetClass != other.getClass()) {
                return CompareResult.TYPE_MISMATCH;
            }
            return compareSameType(other);
        }

        public String toString() {
            return getClass().getName() + " [" + getValueAsString() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class StringLookupComparer extends LookupValueComparerBase {
        protected final boolean _isMatchFunction;
        protected final boolean _matchExact;
        protected final String _value;
        protected final Pattern _wildCardPattern;

        protected StringLookupComparer(StringEval se, boolean matchExact, boolean isMatchFunction) {
            super(se);
            this._value = se.getStringValue();
            this._wildCardPattern = Countif.StringMatcher.getWildCardPattern(this._value);
            this._matchExact = matchExact;
            this._isMatchFunction = isMatchFunction;
        }

        protected String convertToString(ValueEval other) {
            StringEval se = (StringEval) other;
            return se.getStringValue();
        }

        @Override // org.apache.poi.ss.formula.functions.LookupUtils.LookupValueComparerBase
        protected CompareResult compareSameType(ValueEval other) {
            String stringValue = convertToString(other);
            if (this._wildCardPattern != null && (this._isMatchFunction || !this._matchExact)) {
                Matcher matcher = this._wildCardPattern.matcher(stringValue);
                boolean matches = matcher.matches();
                return CompareResult.valueOf(matches);
            }
            return CompareResult.valueOf(this._value.compareToIgnoreCase(stringValue));
        }

        @Override // org.apache.poi.ss.formula.functions.LookupUtils.LookupValueComparerBase
        protected String getValueAsString() {
            return this._value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class TolerantStringLookupComparer extends StringLookupComparer {
        static StringEval convertToStringEval(ValueEval eval) {
            if (eval instanceof StringEval) {
                return (StringEval) eval;
            }
            String sv = OperandResolver.coerceValueToString(eval);
            return new StringEval(sv);
        }

        protected TolerantStringLookupComparer(ValueEval eval, boolean matchExact, boolean isMatchFunction) {
            super(convertToStringEval(eval), matchExact, isMatchFunction);
        }

        @Override // org.apache.poi.ss.formula.functions.LookupUtils.StringLookupComparer
        protected String convertToString(ValueEval other) {
            return OperandResolver.coerceValueToString(other);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class NumberLookupComparer extends LookupValueComparerBase {
        private final double _value;

        protected NumberLookupComparer(NumberEval ne) {
            super(ne);
            this._value = ne.getNumberValue();
        }

        @Override // org.apache.poi.ss.formula.functions.LookupUtils.LookupValueComparerBase
        protected CompareResult compareSameType(ValueEval other) {
            NumberEval ne = (NumberEval) other;
            return CompareResult.valueOf(Double.compare(this._value, ne.getNumberValue()));
        }

        @Override // org.apache.poi.ss.formula.functions.LookupUtils.LookupValueComparerBase
        protected String getValueAsString() {
            return String.valueOf(this._value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class BooleanLookupComparer extends LookupValueComparerBase {
        private final boolean _value;

        protected BooleanLookupComparer(BoolEval be) {
            super(be);
            this._value = be.getBooleanValue();
        }

        @Override // org.apache.poi.ss.formula.functions.LookupUtils.LookupValueComparerBase
        protected CompareResult compareSameType(ValueEval other) {
            BoolEval be = (BoolEval) other;
            boolean otherVal = be.getBooleanValue();
            if (this._value == otherVal) {
                return CompareResult.EQUAL;
            }
            if (this._value) {
                return CompareResult.GREATER_THAN;
            }
            return CompareResult.LESS_THAN;
        }

        @Override // org.apache.poi.ss.formula.functions.LookupUtils.LookupValueComparerBase
        protected String getValueAsString() {
            return String.valueOf(this._value);
        }
    }

    public static int resolveRowOrColIndexArg(ValueEval rowColIndexArg, int srcCellRow, int srcCellCol) throws EvaluationException {
        if (rowColIndexArg == null) {
            throw new IllegalArgumentException("argument must not be null");
        }
        try {
            ValueEval veRowColIndexArg = OperandResolver.getSingleValue(rowColIndexArg, srcCellRow, (short) srcCellCol);
            if (veRowColIndexArg instanceof StringEval) {
                StringEval se = (StringEval) veRowColIndexArg;
                String strVal = se.getStringValue();
                Double dVal = OperandResolver.parseDouble(strVal);
                if (dVal == null) {
                    throw EvaluationException.invalidRef();
                }
            }
            int oneBasedIndex = OperandResolver.coerceValueToInt(veRowColIndexArg);
            if (oneBasedIndex < 1) {
                throw EvaluationException.invalidValue();
            }
            return oneBasedIndex - 1;
        } catch (EvaluationException e) {
            throw EvaluationException.invalidRef();
        }
    }

    public static TwoDEval resolveTableArrayArg(ValueEval eval) throws EvaluationException {
        if (eval instanceof TwoDEval) {
            return (TwoDEval) eval;
        }
        if (eval instanceof RefEval) {
            RefEval refEval = (RefEval) eval;
            return refEval.offset(0, 0, 0, 0);
        }
        throw EvaluationException.invalidValue();
    }

    public static boolean resolveRangeLookupArg(ValueEval rangeLookupArg, int srcCellRow, int srcCellCol) throws EvaluationException {
        ValueEval valEval = OperandResolver.getSingleValue(rangeLookupArg, srcCellRow, srcCellCol);
        if (valEval == MissingArgEval.instance || (valEval instanceof BlankEval)) {
            return false;
        }
        if (valEval instanceof BoolEval) {
            BoolEval boolEval = (BoolEval) valEval;
            return boolEval.getBooleanValue();
        }
        if (valEval instanceof StringEval) {
            String stringValue = ((StringEval) valEval).getStringValue();
            if (stringValue.isEmpty()) {
                throw EvaluationException.invalidValue();
            }
            Boolean b = Countif.parseBoolean(stringValue);
            if (b != null) {
                return b.booleanValue();
            }
            throw EvaluationException.invalidValue();
        }
        if (valEval instanceof NumericValueEval) {
            NumericValueEval nve = (NumericValueEval) valEval;
            return 0.0d != nve.getNumberValue();
        }
        throw new IllegalStateException("Unexpected eval type (" + valEval + ")");
    }

    public static int lookupFirstIndexOfValue(ValueEval lookupValue, ValueVector vector, boolean isRangeLookup) throws EvaluationException {
        int result;
        LookupValueComparer lookupComparer = createLookupComparer(lookupValue, isRangeLookup, false);
        if (isRangeLookup) {
            result = performBinarySearch(vector, lookupComparer);
        } else {
            result = lookupFirstIndexOfValue(lookupComparer, vector, MatchMode.ExactMatch);
        }
        if (result < 0) {
            throw new EvaluationException(ErrorEval.NA);
        }
        return result;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int xlookupIndexOfValue(org.apache.poi.ss.formula.eval.ValueEval r9, org.apache.poi.ss.formula.functions.LookupUtils.ValueVector r10, org.apache.poi.ss.formula.functions.LookupUtils.MatchMode r11, org.apache.poi.ss.formula.functions.LookupUtils.SearchMode r12) throws org.apache.poi.ss.formula.eval.EvaluationException {
        /*
            r0 = r9
            boolean r1 = r9 instanceof org.apache.poi.ss.formula.eval.StringEval
            r2 = 0
            if (r1 == 0) goto L44
            org.apache.poi.ss.formula.functions.LookupUtils$MatchMode r1 = org.apache.poi.ss.formula.functions.LookupUtils.MatchMode.ExactMatchFallbackToLargerValue
            if (r11 == r1) goto Le
            org.apache.poi.ss.formula.functions.LookupUtils$MatchMode r1 = org.apache.poi.ss.formula.functions.LookupUtils.MatchMode.ExactMatchFallbackToSmallerValue
            if (r11 != r1) goto L44
        Le:
            r1 = r9
            org.apache.poi.ss.formula.eval.StringEval r1 = (org.apache.poi.ss.formula.eval.StringEval) r1
            java.lang.String r1 = r1.getStringValue()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            int r4 = r1.length()
            r3.<init>(r4)
            r4 = 0
            char[] r5 = r1.toCharArray()
            int r6 = r5.length
            r7 = r2
        L25:
            if (r7 >= r6) goto L38
            char r8 = r5[r7]
            switch(r8) {
                case 42: goto L30;
                case 63: goto L30;
                case 126: goto L30;
                default: goto L2c;
            }
        L2c:
            r3.append(r8)
            goto L32
        L30:
            r4 = 1
        L32:
            if (r4 == 0) goto L35
            goto L38
        L35:
            int r7 = r7 + 1
            goto L25
        L38:
            if (r4 == 0) goto L44
            org.apache.poi.ss.formula.eval.StringEval r5 = new org.apache.poi.ss.formula.eval.StringEval
            java.lang.String r6 = r3.toString()
            r5.<init>(r6)
            r0 = r5
        L44:
            org.apache.poi.ss.formula.functions.LookupUtils$MatchMode r1 = org.apache.poi.ss.formula.functions.LookupUtils.MatchMode.WildcardMatch
            r3 = 1
            if (r11 == r1) goto L4b
            r1 = r3
            goto L4c
        L4b:
            r1 = r2
        L4c:
            org.apache.poi.ss.formula.functions.LookupUtils$LookupValueComparer r1 = createTolerantLookupComparer(r0, r1, r3)
            org.apache.poi.ss.formula.functions.LookupUtils$SearchMode r4 = org.apache.poi.ss.formula.functions.LookupUtils.SearchMode.BinarySearchForward
            if (r12 != r4) goto L59
            int r2 = binarySearchIndexOfValue(r1, r10, r11, r2)
            goto L6f
        L59:
            org.apache.poi.ss.formula.functions.LookupUtils$SearchMode r2 = org.apache.poi.ss.formula.functions.LookupUtils.SearchMode.BinarySearchBackward
            if (r12 != r2) goto L62
            int r2 = binarySearchIndexOfValue(r1, r10, r11, r3)
            goto L6f
        L62:
            org.apache.poi.ss.formula.functions.LookupUtils$SearchMode r2 = org.apache.poi.ss.formula.functions.LookupUtils.SearchMode.IterateBackward
            if (r12 != r2) goto L6b
            int r2 = lookupLastIndexOfValue(r1, r10, r11)
            goto L6f
        L6b:
            int r2 = lookupFirstIndexOfValue(r1, r10, r11)
        L6f:
            if (r2 < 0) goto L72
            return r2
        L72:
            org.apache.poi.ss.formula.eval.EvaluationException r3 = new org.apache.poi.ss.formula.eval.EvaluationException
            org.apache.poi.ss.formula.eval.ErrorEval r4 = org.apache.poi.ss.formula.eval.ErrorEval.NA
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.LookupUtils.xlookupIndexOfValue(org.apache.poi.ss.formula.eval.ValueEval, org.apache.poi.ss.formula.functions.LookupUtils$ValueVector, org.apache.poi.ss.formula.functions.LookupUtils$MatchMode, org.apache.poi.ss.formula.functions.LookupUtils$SearchMode):int");
    }

    private static int lookupFirstIndexOfValue(LookupValueComparer lookupComparer, ValueVector vector, MatchMode matchMode) {
        return lookupIndexOfValue(lookupComparer, vector, matchMode, false);
    }

    private static int lookupLastIndexOfValue(LookupValueComparer lookupComparer, ValueVector vector, MatchMode matchMode) {
        return lookupIndexOfValue(lookupComparer, vector, matchMode, true);
    }

    private static int lookupIndexOfValue(LookupValueComparer lookupComparer, ValueVector vector, MatchMode matchMode, boolean reverse) {
        int bestMatchIdx = -1;
        ValueEval bestMatchEval = null;
        Iterator<Integer> idxIter = reverse ? vector.reverseIndexIterator() : vector.indexIterator();
        while (idxIter.hasNext()) {
            int i = idxIter.next().intValue();
            ValueEval valueEval = vector.getItem(i);
            CompareResult result = lookupComparer.compareTo(valueEval);
            if (result.isEqual()) {
                return i;
            }
            switch (matchMode) {
                case ExactMatchFallbackToLargerValue:
                    if (!result.isLessThan()) {
                        break;
                    } else if (bestMatchEval == null) {
                        bestMatchIdx = i;
                        bestMatchEval = valueEval;
                        break;
                    } else {
                        LookupValueComparer matchComparer = createTolerantLookupComparer(valueEval, true, true);
                        if (!matchComparer.compareTo(bestMatchEval).isLessThan()) {
                            break;
                        } else {
                            bestMatchIdx = i;
                            bestMatchEval = valueEval;
                            break;
                        }
                    }
                case ExactMatchFallbackToSmallerValue:
                    if (!result.isGreaterThan()) {
                        break;
                    } else if (bestMatchEval == null) {
                        bestMatchIdx = i;
                        bestMatchEval = valueEval;
                        break;
                    } else {
                        LookupValueComparer matchComparer2 = createTolerantLookupComparer(valueEval, true, true);
                        if (!matchComparer2.compareTo(bestMatchEval).isGreaterThan()) {
                            break;
                        } else {
                            bestMatchIdx = i;
                            bestMatchEval = valueEval;
                            break;
                        }
                    }
            }
        }
        return bestMatchIdx;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x009e, code lost:
    
        return r0;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0041. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int binarySearchIndexOfValue(org.apache.poi.ss.formula.functions.LookupUtils.LookupValueComparer r9, org.apache.poi.ss.formula.functions.LookupUtils.ValueVector r10, org.apache.poi.ss.formula.functions.LookupUtils.MatchMode r11, boolean r12) {
        /*
            r0 = -1
            r1 = 0
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            org.apache.poi.ss.formula.functions.LookupUtils$BinarySearchIndexes r3 = new org.apache.poi.ss.formula.functions.LookupUtils$BinarySearchIndexes
            int r4 = r10.getSize()
            r3.<init>(r4)
        L10:
            int r4 = r3.getMidIx()
            if (r4 < 0) goto L9e
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)
            boolean r5 = r2.contains(r5)
            if (r5 == 0) goto L22
            goto L9e
        L22:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)
            r2.add(r5)
            org.apache.poi.ss.formula.eval.ValueEval r5 = r10.getItem(r4)
            org.apache.poi.ss.formula.functions.LookupUtils$CompareResult r6 = r9.compareTo(r5)
            boolean r7 = r6.isEqual()
            if (r7 == 0) goto L38
            return r4
        L38:
            int[] r7 = org.apache.poi.ss.formula.functions.LookupUtils.AnonymousClass1.$SwitchMap$org$apache$poi$ss$formula$functions$LookupUtils$MatchMode
            int r8 = r11.ordinal()
            r7 = r7[r8]
            r8 = 1
            switch(r7) {
                case 1: goto L61;
                case 2: goto L45;
                default: goto L44;
            }
        L44:
            goto L7d
        L45:
            boolean r7 = r6.isGreaterThan()
            if (r7 == 0) goto L7d
            if (r1 != 0) goto L50
            r0 = r4
            r1 = r5
            goto L7d
        L50:
            org.apache.poi.ss.formula.functions.LookupUtils$LookupValueComparer r7 = createTolerantLookupComparer(r5, r8, r8)
            org.apache.poi.ss.formula.functions.LookupUtils$CompareResult r8 = r7.compareTo(r1)
            boolean r8 = r8.isGreaterThan()
            if (r8 == 0) goto L7d
            r0 = r4
            r1 = r5
            goto L7d
        L61:
            boolean r7 = r6.isLessThan()
            if (r7 == 0) goto L7d
            if (r1 != 0) goto L6c
            r0 = r4
            r1 = r5
            goto L7d
        L6c:
            org.apache.poi.ss.formula.functions.LookupUtils$LookupValueComparer r7 = createTolerantLookupComparer(r5, r8, r8)
            org.apache.poi.ss.formula.functions.LookupUtils$CompareResult r8 = r7.compareTo(r1)
            boolean r8 = r8.isLessThan()
            if (r8 == 0) goto L7c
            r0 = r4
            r1 = r5
        L7c:
        L7d:
            boolean r7 = r6.isTypeMismatch()
            if (r7 == 0) goto L8b
            int r7 = handleMidValueTypeMismatch(r9, r10, r3, r4, r12)
            if (r7 < 0) goto L8a
            return r7
        L8a:
            goto L9c
        L8b:
            if (r12 == 0) goto L95
            boolean r7 = r6.isGreaterThan()
            r3.narrowSearch(r4, r7)
            goto L9c
        L95:
            boolean r7 = r6.isLessThan()
            r3.narrowSearch(r4, r7)
        L9c:
            goto L10
        L9e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.LookupUtils.binarySearchIndexOfValue(org.apache.poi.ss.formula.functions.LookupUtils$LookupValueComparer, org.apache.poi.ss.formula.functions.LookupUtils$ValueVector, org.apache.poi.ss.formula.functions.LookupUtils$MatchMode, boolean):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class BinarySearchIndexes {
        private int _highIx;
        private int _lowIx = -1;

        public BinarySearchIndexes(int highIx) {
            this._highIx = highIx;
        }

        public int getMidIx() {
            int ixDiff = this._highIx - this._lowIx;
            if (ixDiff < 2) {
                return -1;
            }
            return this._lowIx + (ixDiff / 2);
        }

        public int getLowIx() {
            return this._lowIx;
        }

        public int getHighIx() {
            return this._highIx;
        }

        public void narrowSearch(int midIx, boolean isLessThan) {
            if (isLessThan) {
                this._highIx = midIx;
            } else {
                this._lowIx = midIx;
            }
        }
    }

    private static int performBinarySearch(ValueVector vector, LookupValueComparer lookupComparer) {
        BinarySearchIndexes bsi = new BinarySearchIndexes(vector.getSize());
        while (true) {
            int midIx = bsi.getMidIx();
            if (midIx < 0) {
                return bsi.getLowIx();
            }
            CompareResult cr = lookupComparer.compareTo(vector.getItem(midIx));
            if (cr.isTypeMismatch()) {
                int newMidIx = handleMidValueTypeMismatch(lookupComparer, vector, bsi, midIx, false);
                if (newMidIx < 0) {
                    continue;
                } else {
                    midIx = newMidIx;
                    cr = lookupComparer.compareTo(vector.getItem(midIx));
                }
            }
            if (cr.isEqual()) {
                return findLastIndexInRunOfEqualValues(lookupComparer, vector, midIx, bsi.getHighIx());
            }
            bsi.narrowSearch(midIx, cr.isLessThan());
        }
    }

    private static int handleMidValueTypeMismatch(LookupValueComparer lookupComparer, ValueVector vector, BinarySearchIndexes bsi, int midIx, boolean reverse) {
        CompareResult cr;
        int newMid = midIx;
        int highIx = bsi.getHighIx();
        do {
            newMid++;
            if (newMid == highIx) {
                bsi.narrowSearch(midIx, true);
                return -1;
            }
            cr = lookupComparer.compareTo(vector.getItem(newMid));
            if (cr.isLessThan() && !reverse && newMid == highIx - 1) {
                bsi.narrowSearch(midIx, true);
                return -1;
            }
            if (cr.isGreaterThan() && reverse && newMid == highIx - 1) {
                bsi.narrowSearch(midIx, true);
                return -1;
            }
        } while (cr.isTypeMismatch());
        if (cr.isEqual()) {
            return newMid;
        }
        if (reverse) {
            bsi.narrowSearch(newMid, cr.isGreaterThan());
        } else {
            bsi.narrowSearch(newMid, cr.isLessThan());
        }
        return -1;
    }

    private static int findLastIndexInRunOfEqualValues(LookupValueComparer lookupComparer, ValueVector vector, int firstFoundIndex, int maxIx) {
        for (int i = firstFoundIndex + 1; i < maxIx; i++) {
            if (!lookupComparer.compareTo(vector.getItem(i)).isEqual()) {
                return i - 1;
            }
        }
        int i2 = maxIx - 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LookupValueComparer createLookupComparer(ValueEval lookupValue, boolean matchExact, boolean isMatchFunction) {
        if (lookupValue == BlankEval.instance) {
            return new NumberLookupComparer(NumberEval.ZERO);
        }
        if (lookupValue instanceof StringEval) {
            return new StringLookupComparer((StringEval) lookupValue, matchExact, isMatchFunction);
        }
        if (lookupValue instanceof NumberEval) {
            return new NumberLookupComparer((NumberEval) lookupValue);
        }
        if (lookupValue instanceof BoolEval) {
            return new BooleanLookupComparer((BoolEval) lookupValue);
        }
        throw new IllegalArgumentException("Bad lookup value type (" + lookupValue.getClass().getName() + ")");
    }

    private static LookupValueComparer createTolerantLookupComparer(ValueEval lookupValue, boolean matchExact, boolean isMatchFunction) {
        if (lookupValue == BlankEval.instance) {
            return new TolerantStringLookupComparer(new StringEval(""), matchExact, isMatchFunction);
        }
        if (lookupValue instanceof BoolEval) {
            return new BooleanLookupComparer((BoolEval) lookupValue);
        }
        if (matchExact && (lookupValue instanceof NumberEval)) {
            return new NumberLookupComparer((NumberEval) lookupValue);
        }
        return new TolerantStringLookupComparer(lookupValue, matchExact, isMatchFunction);
    }
}
