package org.apache.poi.ss.formula;

import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
enum OperatorEnum {
    NO_COMPARISON(new CompareOp() { // from class: org.apache.poi.ss.formula.OperatorEnum$$ExternalSyntheticLambda0
        @Override // org.apache.poi.ss.formula.OperatorEnum.CompareOp
        public final boolean isValid(Comparable comparable, Comparable comparable2, Comparable comparable3) {
            boolean noComp;
            noComp = OperatorEnum.noComp(comparable, comparable2, comparable3);
            return noComp;
        }
    }, false),
    BETWEEN(new CompareOp() { // from class: org.apache.poi.ss.formula.OperatorEnum$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ss.formula.OperatorEnum.CompareOp
        public final boolean isValid(Comparable comparable, Comparable comparable2, Comparable comparable3) {
            boolean between;
            between = OperatorEnum.between(comparable, comparable2, comparable3);
            return between;
        }
    }, false),
    NOT_BETWEEN(new CompareOp() { // from class: org.apache.poi.ss.formula.OperatorEnum$$ExternalSyntheticLambda2
        @Override // org.apache.poi.ss.formula.OperatorEnum.CompareOp
        public final boolean isValid(Comparable comparable, Comparable comparable2, Comparable comparable3) {
            boolean notBetween;
            notBetween = OperatorEnum.notBetween(comparable, comparable2, comparable3);
            return notBetween;
        }
    }, true),
    EQUAL(new CompareOp() { // from class: org.apache.poi.ss.formula.OperatorEnum$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ss.formula.OperatorEnum.CompareOp
        public final boolean isValid(Comparable comparable, Comparable comparable2, Comparable comparable3) {
            boolean equalCheck;
            equalCheck = OperatorEnum.equalCheck(comparable, comparable2, comparable3);
            return equalCheck;
        }
    }, false),
    NOT_EQUAL(new CompareOp() { // from class: org.apache.poi.ss.formula.OperatorEnum$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ss.formula.OperatorEnum.CompareOp
        public final boolean isValid(Comparable comparable, Comparable comparable2, Comparable comparable3) {
            boolean notEqual;
            notEqual = OperatorEnum.notEqual(comparable, comparable2, comparable3);
            return notEqual;
        }
    }, true),
    GREATER_THAN(new CompareOp() { // from class: org.apache.poi.ss.formula.OperatorEnum$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ss.formula.OperatorEnum.CompareOp
        public final boolean isValid(Comparable comparable, Comparable comparable2, Comparable comparable3) {
            boolean greaterThan;
            greaterThan = OperatorEnum.greaterThan(comparable, comparable2, comparable3);
            return greaterThan;
        }
    }, false),
    LESS_THAN(new CompareOp() { // from class: org.apache.poi.ss.formula.OperatorEnum$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ss.formula.OperatorEnum.CompareOp
        public final boolean isValid(Comparable comparable, Comparable comparable2, Comparable comparable3) {
            boolean lessThan;
            lessThan = OperatorEnum.lessThan(comparable, comparable2, comparable3);
            return lessThan;
        }
    }, false),
    GREATER_OR_EQUAL(new CompareOp() { // from class: org.apache.poi.ss.formula.OperatorEnum$$ExternalSyntheticLambda7
        @Override // org.apache.poi.ss.formula.OperatorEnum.CompareOp
        public final boolean isValid(Comparable comparable, Comparable comparable2, Comparable comparable3) {
            boolean greaterOrEqual;
            greaterOrEqual = OperatorEnum.greaterOrEqual(comparable, comparable2, comparable3);
            return greaterOrEqual;
        }
    }, false),
    LESS_OR_EQUAL(new CompareOp() { // from class: org.apache.poi.ss.formula.OperatorEnum$$ExternalSyntheticLambda8
        @Override // org.apache.poi.ss.formula.OperatorEnum.CompareOp
        public final boolean isValid(Comparable comparable, Comparable comparable2, Comparable comparable3) {
            boolean lessOrEqual;
            lessOrEqual = OperatorEnum.lessOrEqual(comparable, comparable2, comparable3);
            return lessOrEqual;
        }
    }, false);

    private final CompareOp compareOp;
    private final boolean validForIncompatibleTypes;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public interface CompareOp {
        <C extends Comparable<C>> boolean isValid(C c, C c2, C c3);
    }

    OperatorEnum(CompareOp compareOp, boolean validForIncompatibleTypes) {
        this.compareOp = compareOp;
        this.validForIncompatibleTypes = validForIncompatibleTypes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <C extends Comparable<C>> boolean isValid(C cellValue, C v1, C v2) {
        return this.compareOp.isValid(cellValue, v1, v2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isValidForIncompatibleTypes() {
        return this.validForIncompatibleTypes;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <C extends Comparable<C>> boolean noComp(C cellValue, C v1, C v2) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <C extends Comparable<C>> boolean between(C c, C v1, C c2) {
        if (v1 != null) {
            return c.compareTo(v1) >= 0 && c.compareTo(c2) <= 0;
        }
        if (c instanceof Number) {
            double n2 = c2 == 0 ? 0.0d : ((Number) c2).doubleValue();
            return Double.compare(((Number) c).doubleValue(), 0.0d) >= 0 && Double.compare(((Number) c).doubleValue(), n2) <= 0;
        }
        if (!(c instanceof String)) {
            return c instanceof Boolean ? false : false;
        }
        String n22 = c2 == 0 ? "" : (String) c2;
        return ((String) c).compareToIgnoreCase("") >= 0 && ((String) c).compareToIgnoreCase(n22) <= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <C extends Comparable<C>> boolean notBetween(C c, C v1, C c2) {
        if (v1 != null) {
            return c.compareTo(v1) < 0 || c.compareTo(c2) > 0;
        }
        if (c instanceof Number) {
            double n2 = c2 == 0 ? 0.0d : ((Number) c2).doubleValue();
            return Double.compare(((Number) c).doubleValue(), 0.0d) < 0 || Double.compare(((Number) c).doubleValue(), n2) > 0;
        }
        if (c instanceof String) {
            String n22 = c2 == 0 ? "" : (String) c2;
            return ((String) c).compareToIgnoreCase("") < 0 || ((String) c).compareToIgnoreCase(n22) > 0;
        }
        return c instanceof Boolean;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <C extends Comparable<C>> boolean equalCheck(C c, C v1, C v2) {
        return v1 == null ? c instanceof Number ? Double.compare(((Number) c).doubleValue(), 0.0d) == 0 : (!(c instanceof String) && (c instanceof Boolean)) ? false : false : c instanceof String ? c.toString().compareToIgnoreCase(v1.toString()) == 0 : c.compareTo(v1) == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <C extends Comparable<C>> boolean notEqual(C cellValue, C v1, C v2) {
        if (v1 == null) {
            return true;
        }
        return cellValue instanceof String ? cellValue.toString().compareToIgnoreCase(v1.toString()) == 0 : cellValue.compareTo(v1) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <C extends Comparable<C>> boolean greaterThan(C c, C v1, C v2) {
        if (v1 != null) {
            return c.compareTo(v1) > 0;
        }
        if (c instanceof Number) {
            return Double.compare(((Number) c).doubleValue(), 0.0d) > 0;
        }
        if (c instanceof String) {
            return true;
        }
        return c instanceof Boolean;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <C extends Comparable<C>> boolean lessThan(C c, C v1, C v2) {
        return v1 == null ? c instanceof Number ? Double.compare(((Number) c).doubleValue(), 0.0d) < 0 : (!(c instanceof String) && (c instanceof Boolean)) ? false : false : c.compareTo(v1) < 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <C extends Comparable<C>> boolean greaterOrEqual(C c, C v1, C v2) {
        if (v1 != null) {
            return c.compareTo(v1) >= 0;
        }
        if (c instanceof Number) {
            return Double.compare(((Number) c).doubleValue(), 0.0d) >= 0;
        }
        if (c instanceof String) {
            return true;
        }
        return c instanceof Boolean;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <C extends Comparable<C>> boolean lessOrEqual(C c, C v1, C v2) {
        return v1 == null ? c instanceof Number ? Double.compare(((Number) c).doubleValue(), 0.0d) <= 0 : (!(c instanceof String) && (c instanceof Boolean)) ? false : false : c.compareTo(v1) <= 0;
    }
}
