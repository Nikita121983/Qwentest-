package org.apache.poi.ss.formula.functions;

import java.util.function.Supplier;
import java.util.regex.Pattern;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.StringValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Countif;
import org.apache.poi.ss.util.NumberComparer;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LocaleUtil;

@Internal
/* loaded from: classes10.dex */
public final class DStarRunner implements Function3Arg {
    private final DStarAlgorithmEnum algoType;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public enum operator {
        largerThan,
        largerEqualThan,
        smallerThan,
        smallerEqualThan,
        equal,
        notEqual
    }

    /* loaded from: classes10.dex */
    public enum DStarAlgorithmEnum {
        DGET(new Supplier() { // from class: org.apache.poi.ss.formula.functions.DStarRunner$DStarAlgorithmEnum$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return new DGet();
            }
        }),
        DMIN(new Supplier() { // from class: org.apache.poi.ss.formula.functions.DStarRunner$DStarAlgorithmEnum$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return new DMin();
            }
        }),
        DMAX(new Supplier() { // from class: org.apache.poi.ss.formula.functions.DStarRunner$DStarAlgorithmEnum$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return new DMax();
            }
        }),
        DSUM(new Supplier() { // from class: org.apache.poi.ss.formula.functions.DStarRunner$DStarAlgorithmEnum$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return new DSum();
            }
        }),
        DCOUNT(new Supplier() { // from class: org.apache.poi.ss.formula.functions.DStarRunner$DStarAlgorithmEnum$$ExternalSyntheticLambda8
            @Override // java.util.function.Supplier
            public final Object get() {
                return new DCount();
            }
        }),
        DCOUNTA(new Supplier() { // from class: org.apache.poi.ss.formula.functions.DStarRunner$DStarAlgorithmEnum$$ExternalSyntheticLambda9
            @Override // java.util.function.Supplier
            public final Object get() {
                return new DCountA();
            }
        }),
        DAVERAGE(new Supplier() { // from class: org.apache.poi.ss.formula.functions.DStarRunner$DStarAlgorithmEnum$$ExternalSyntheticLambda10
            @Override // java.util.function.Supplier
            public final Object get() {
                return new DAverage();
            }
        }),
        DSTDEV(new Supplier() { // from class: org.apache.poi.ss.formula.functions.DStarRunner$DStarAlgorithmEnum$$ExternalSyntheticLambda11
            @Override // java.util.function.Supplier
            public final Object get() {
                return new DStdev();
            }
        }),
        DSTDEVP(new Supplier() { // from class: org.apache.poi.ss.formula.functions.DStarRunner$DStarAlgorithmEnum$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return new DStdevp();
            }
        }),
        DVAR(new Supplier() { // from class: org.apache.poi.ss.formula.functions.DStarRunner$DStarAlgorithmEnum$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return new DVar();
            }
        }),
        DVARP(new Supplier() { // from class: org.apache.poi.ss.formula.functions.DStarRunner$DStarAlgorithmEnum$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return new DVarp();
            }
        }),
        DPRODUCT(new Supplier() { // from class: org.apache.poi.ss.formula.functions.DStarRunner$DStarAlgorithmEnum$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return new DProduct();
            }
        });

        private final Supplier<IDStarAlgorithm> implSupplier;

        DStarAlgorithmEnum(Supplier supplier) {
            this.implSupplier = supplier;
        }

        public IDStarAlgorithm newInstance() {
            return this.implSupplier.get();
        }
    }

    public DStarRunner(DStarAlgorithmEnum algorithm) {
        this.algoType = algorithm;
    }

    @Override // org.apache.poi.ss.formula.functions.Function
    public ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        return args.length == 3 ? evaluate(srcRowIndex, srcColumnIndex, args[0], args[1], args[2]) : ErrorEval.VALUE_INVALID;
    }

    @Override // org.apache.poi.ss.formula.functions.Function3Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval database, ValueEval filterColumn, ValueEval conditionDatabase) {
        if (!(database instanceof AreaEval) || !(conditionDatabase instanceof AreaEval)) {
            return ErrorEval.VALUE_INVALID;
        }
        AreaEval db = (AreaEval) database;
        AreaEval cdb = (AreaEval) conditionDatabase;
        IDStarAlgorithm algorithm = this.algoType.newInstance();
        int fc = -1;
        try {
            ValueEval filterColumn2 = OperandResolver.getSingleValue(filterColumn, srcRowIndex, srcColumnIndex);
            fc = filterColumn2 instanceof NumericValueEval ? ((int) Math.round(((NumericValueEval) filterColumn2).getNumberValue())) - 1 : getColumnForName(filterColumn2, db);
            if (fc == -1 && !algorithm.allowEmptyMatchField()) {
                return ErrorEval.VALUE_INVALID;
            }
        } catch (EvaluationException e) {
            if (!algorithm.allowEmptyMatchField()) {
                return e.getErrorEval();
            }
        } catch (Exception e2) {
            if (!algorithm.allowEmptyMatchField()) {
                return ErrorEval.VALUE_INVALID;
            }
        }
        int height = db.getHeight();
        for (int row = 1; row < height; row++) {
            try {
                boolean matches = fulfillsConditions(db, row, cdb);
                if (matches) {
                    ValueEval currentValueEval = resolveReference(db, row, fc);
                    if (fc < 0 && algorithm.allowEmptyMatchField() && !(currentValueEval instanceof NumericValueEval)) {
                        currentValueEval = NumberEval.ZERO;
                    }
                    boolean shouldContinue = algorithm.processMatch(currentValueEval);
                    if (!shouldContinue) {
                        break;
                    }
                }
            } catch (EvaluationException e3) {
                return ErrorEval.VALUE_INVALID;
            }
        }
        return algorithm.getResult();
    }

    private static int getColumnForName(ValueEval nameValueEval, AreaEval db) throws EvaluationException {
        if (nameValueEval instanceof NumericValueEval) {
            int columnNo = OperandResolver.coerceValueToInt(nameValueEval) - 1;
            if (columnNo < 0 || columnNo >= db.getWidth()) {
                return -1;
            }
            return columnNo;
        }
        String name = OperandResolver.coerceValueToString(nameValueEval);
        return getColumnForString(db, name);
    }

    private static int getColumnForString(AreaEval db, String name) {
        int width = db.getWidth();
        for (int column = 0; column < width; column++) {
            ValueEval columnNameValueEval = resolveReference(db, 0, column);
            if (!(columnNameValueEval instanceof BlankEval) && !(columnNameValueEval instanceof ErrorEval)) {
                String columnName = OperandResolver.coerceValueToString(columnNameValueEval);
                if (name.equalsIgnoreCase(columnName)) {
                    int resultColumn = column;
                    return resultColumn;
                }
            }
        }
        return -1;
    }

    private static boolean fulfillsConditions(AreaEval db, int row, AreaEval cdb) throws EvaluationException {
        int height = cdb.getHeight();
        for (int conditionRow = 1; conditionRow < height; conditionRow++) {
            boolean matches = true;
            int width = cdb.getWidth();
            int column = 0;
            while (true) {
                if (column >= width) {
                    break;
                }
                boolean columnCondition = true;
                ValueEval condition = resolveReference(cdb, conditionRow, column);
                if (!(condition instanceof BlankEval)) {
                    ValueEval targetHeader = resolveReference(cdb, 0, column);
                    if (!(targetHeader instanceof StringValueEval)) {
                        throw new EvaluationException(ErrorEval.VALUE_INVALID);
                    }
                    if (getColumnForName(targetHeader, db) == -1) {
                        columnCondition = false;
                    }
                    if (columnCondition) {
                        ValueEval value = resolveReference(db, row, getColumnForName(targetHeader, db));
                        if (!testNormalCondition(value, condition)) {
                            matches = false;
                            break;
                        }
                    } else {
                        if (OperandResolver.coerceValueToString(condition).isEmpty()) {
                            throw new EvaluationException(ErrorEval.VALUE_INVALID);
                        }
                        throw new NotImplementedException("D* function with formula conditions");
                    }
                }
                column++;
            }
            if (matches) {
                return true;
            }
        }
        return false;
    }

    private static boolean testNormalCondition(ValueEval value, ValueEval condition) throws EvaluationException {
        if (condition instanceof StringEval) {
            String conditionString = ((StringEval) condition).getStringValue();
            if (conditionString.startsWith("<")) {
                String number = conditionString.substring(1);
                if (number.startsWith("=")) {
                    return testNumericCondition(value, operator.smallerEqualThan, number.substring(1));
                }
                if (number.startsWith(">")) {
                    String number2 = number.substring(1);
                    boolean itsANumber = isNumber(number2);
                    if (itsANumber) {
                        return testNumericCondition(value, operator.notEqual, number2);
                    }
                    return testStringCondition(value, operator.notEqual, number2);
                }
                return testNumericCondition(value, operator.smallerThan, number);
            }
            if (conditionString.startsWith(">")) {
                String number3 = conditionString.substring(1);
                if (number3.startsWith("=")) {
                    return testNumericCondition(value, operator.largerEqualThan, number3.substring(1));
                }
                return testNumericCondition(value, operator.largerThan, number3);
            }
            if (conditionString.startsWith("=")) {
                String stringOrNumber = conditionString.substring(1);
                if (stringOrNumber.isEmpty()) {
                    return value instanceof BlankEval;
                }
                boolean itsANumber2 = isNumber(stringOrNumber);
                if (itsANumber2) {
                    return testNumericCondition(value, operator.equal, stringOrNumber);
                }
                return testStringCondition(value, operator.equal, stringOrNumber);
            }
            if (conditionString.isEmpty()) {
                return value instanceof StringEval;
            }
            String valueString = value instanceof BlankEval ? "" : OperandResolver.coerceValueToString(value);
            String lowerValue = valueString.toLowerCase(LocaleUtil.getUserLocale());
            String lowerCondition = conditionString.toLowerCase(LocaleUtil.getUserLocale());
            Pattern pattern = Countif.StringMatcher.getWildCardPattern(lowerCondition);
            if (pattern == null) {
                return lowerValue.startsWith(lowerCondition);
            }
            return pattern.matcher(lowerValue).matches();
        }
        if (!(condition instanceof NumericValueEval)) {
            return (condition instanceof ErrorEval) && (value instanceof ErrorEval) && ((ErrorEval) condition).getErrorCode() == ((ErrorEval) value).getErrorCode();
        }
        double conditionNumber = ((NumericValueEval) condition).getNumberValue();
        Double valueNumber = getNumberFromValueEval(value);
        return valueNumber != null && conditionNumber == valueNumber.doubleValue();
    }

    private static boolean testNumericCondition(ValueEval valueEval, operator op, String condition) throws EvaluationException {
        double conditionValue;
        if (!(valueEval instanceof NumericValueEval)) {
            return false;
        }
        double value = ((NumericValueEval) valueEval).getNumberValue();
        try {
            conditionValue = Integer.parseInt(condition);
        } catch (NumberFormatException e) {
            try {
                conditionValue = Double.parseDouble(condition);
            } catch (NumberFormatException e2) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
        }
        int result = NumberComparer.compare(value, conditionValue);
        switch (op) {
            case largerThan:
                return result > 0;
            case largerEqualThan:
                return result >= 0;
            case smallerThan:
                return result < 0;
            case smallerEqualThan:
                return result <= 0;
            case equal:
                return result == 0;
            case notEqual:
                return result != 0;
            default:
                return false;
        }
    }

    private static boolean testStringCondition(ValueEval valueEval, operator op, String condition) {
        String valueString = valueEval instanceof BlankEval ? "" : OperandResolver.coerceValueToString(valueEval);
        switch (op) {
            case equal:
                return valueString.equalsIgnoreCase(condition);
            case notEqual:
                return !valueString.equalsIgnoreCase(condition);
            default:
                return false;
        }
    }

    private static Double getNumberFromValueEval(ValueEval value) {
        if (value instanceof NumericValueEval) {
            return Double.valueOf(((NumericValueEval) value).getNumberValue());
        }
        if (!(value instanceof StringValueEval)) {
            return null;
        }
        String stringValue = ((StringValueEval) value).getStringValue();
        try {
            return Double.valueOf(Double.parseDouble(stringValue));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static ValueEval resolveReference(AreaEval db, int dbRow, int dbCol) {
        try {
            return OperandResolver.getSingleValue(db.getValue(dbRow, dbCol), db.getFirstRow() + dbRow, db.getFirstColumn() + dbCol);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static boolean isNumber(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            try {
                Double.parseDouble(value);
                return true;
            } catch (NumberFormatException e2) {
                return false;
            }
        }
    }
}
