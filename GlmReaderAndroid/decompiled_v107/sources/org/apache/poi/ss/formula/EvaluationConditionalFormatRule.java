package org.apache.poi.ss.formula;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.AggregateFunction;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ConditionFilterData;
import org.apache.poi.ss.usermodel.ConditionFilterType;
import org.apache.poi.ss.usermodel.ConditionType;
import org.apache.poi.ss.usermodel.ConditionalFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.ExcelNumberFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public class EvaluationConditionalFormatRule implements Comparable<EvaluationConditionalFormatRule> {
    private final DecimalFormat decimalTextFormat;
    private final ConditionalFormatting formatting;
    private final int formattingIndex;
    private final String formula1;
    private final String formula2;
    private final String lowerText;
    private final Map<CellRangeAddress, Set<ValueAndFormat>> meaningfulRegionValues = new HashMap();
    private final ExcelNumberFormat numberFormat;
    private final OperatorEnum operator;
    private final int priority;
    private final CellRangeAddress[] regions;
    private final ConditionalFormattingRule rule;
    private final int ruleIndex;
    private final Sheet sheet;
    private final String text;
    private CellRangeAddress topLeftRegion;
    private final ConditionType type;
    private final WorkbookEvaluator workbookEvaluator;

    public EvaluationConditionalFormatRule(WorkbookEvaluator workbookEvaluator, Sheet sheet, ConditionalFormatting formatting, int formattingIndex, ConditionalFormattingRule rule, int ruleIndex, CellRangeAddress[] regions) {
        this.workbookEvaluator = workbookEvaluator;
        this.sheet = sheet;
        this.formatting = formatting;
        this.rule = rule;
        this.formattingIndex = formattingIndex;
        this.ruleIndex = ruleIndex;
        this.priority = rule.getPriority();
        this.regions = regions;
        for (CellRangeAddress region : regions) {
            if (this.topLeftRegion == null) {
                this.topLeftRegion = region;
            } else if (region.getFirstColumn() < this.topLeftRegion.getFirstColumn() || region.getFirstRow() < this.topLeftRegion.getFirstRow()) {
                this.topLeftRegion = region;
            }
        }
        this.formula1 = rule.getFormula1();
        this.formula2 = rule.getFormula2();
        this.text = rule.getText();
        this.lowerText = this.text == null ? null : this.text.toLowerCase(LocaleUtil.getUserLocale());
        this.numberFormat = rule.getNumberFormat();
        this.operator = OperatorEnum.values()[rule.getComparisonOperation()];
        this.type = rule.getConditionType();
        this.decimalTextFormat = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        this.decimalTextFormat.setMaximumFractionDigits(340);
    }

    public Sheet getSheet() {
        return this.sheet;
    }

    public ConditionalFormatting getFormatting() {
        return this.formatting;
    }

    public int getFormattingIndex() {
        return this.formattingIndex;
    }

    public ExcelNumberFormat getNumberFormat() {
        return this.numberFormat;
    }

    public ConditionalFormattingRule getRule() {
        return this.rule;
    }

    public int getRuleIndex() {
        return this.ruleIndex;
    }

    public CellRangeAddress[] getRegions() {
        return this.regions;
    }

    public int getPriority() {
        return this.priority;
    }

    public String getFormula1() {
        return this.formula1;
    }

    public String getFormula2() {
        return this.formula2;
    }

    public String getText() {
        return this.text;
    }

    public OperatorEnum getOperator() {
        return this.operator;
    }

    public ConditionType getType() {
        return this.type;
    }

    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        EvaluationConditionalFormatRule r = (EvaluationConditionalFormatRule) obj;
        return getSheet().getSheetName().equalsIgnoreCase(r.getSheet().getSheetName()) && getFormattingIndex() == r.getFormattingIndex() && getRuleIndex() == r.getRuleIndex();
    }

    @Override // java.lang.Comparable
    public int compareTo(EvaluationConditionalFormatRule o) {
        int cmp = getSheet().getSheetName().compareToIgnoreCase(o.getSheet().getSheetName());
        if (cmp != 0) {
            return cmp;
        }
        int x = getPriority();
        int y = o.getPriority();
        int cmp2 = Integer.compare(x, y);
        if (cmp2 != 0) {
            return cmp2;
        }
        int cmp3 = Integer.compare(getFormattingIndex(), o.getFormattingIndex());
        if (cmp3 != 0) {
            return cmp3;
        }
        return Integer.compare(getRuleIndex(), o.getRuleIndex());
    }

    public int hashCode() {
        return Objects.hash(this.sheet.getSheetName(), Integer.valueOf(this.formattingIndex), Integer.valueOf(this.ruleIndex));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matches(CellReference ref) {
        CellRangeAddress region = null;
        CellRangeAddress[] cellRangeAddressArr = this.regions;
        int length = cellRangeAddressArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            CellRangeAddress r = cellRangeAddressArr[i];
            if (!r.isInRange(ref)) {
                i++;
            } else {
                region = r;
                break;
            }
        }
        if (region == null) {
            return false;
        }
        ConditionType ruleType = getRule().getConditionType();
        if (ruleType.equals(ConditionType.COLOR_SCALE) || ruleType.equals(ConditionType.DATA_BAR) || ruleType.equals(ConditionType.ICON_SET)) {
            return true;
        }
        Cell cell = null;
        Row row = this.sheet.getRow(ref.getRow());
        if (row != null) {
            cell = row.getCell(ref.getCol());
        }
        if (ruleType.equals(ConditionType.CELL_VALUE_IS)) {
            if (cell == null) {
                return false;
            }
            return checkValue(cell, this.topLeftRegion);
        }
        if (ruleType.equals(ConditionType.FORMULA)) {
            return checkFormula(ref, this.topLeftRegion);
        }
        if (ruleType.equals(ConditionType.FILTER)) {
            return checkFilter(cell, ref, this.topLeftRegion);
        }
        return false;
    }

    private boolean checkValue(Cell cell, CellRangeAddress region) {
        if (cell != null && !DataValidationEvaluator.isType(cell, CellType.BLANK) && !DataValidationEvaluator.isType(cell, CellType.ERROR)) {
            if (DataValidationEvaluator.isType(cell, CellType.STRING) && (cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                return false;
            }
            ValueEval eval = unwrapEval(this.workbookEvaluator.evaluate(this.rule.getFormula1(), ConditionalFormattingEvaluator.getRef(cell), region));
            String f2 = this.rule.getFormula2();
            ValueEval eval2 = BlankEval.instance;
            if (f2 != null && !f2.isEmpty()) {
                eval2 = unwrapEval(this.workbookEvaluator.evaluate(f2, ConditionalFormattingEvaluator.getRef(cell), region));
            }
            if (DataValidationEvaluator.isType(cell, CellType.BOOLEAN) && ((eval == BlankEval.instance || (eval instanceof BoolEval)) && (eval2 == BlankEval.instance || (eval2 instanceof BoolEval)))) {
                return this.operator.isValid(Boolean.valueOf(cell.getBooleanCellValue()), eval == BlankEval.instance ? null : Boolean.valueOf(((BoolEval) eval).getBooleanValue()), eval2 != BlankEval.instance ? Boolean.valueOf(((BoolEval) eval2).getBooleanValue()) : null);
            }
            if (DataValidationEvaluator.isType(cell, CellType.NUMERIC) && ((eval == BlankEval.instance || (eval instanceof NumberEval)) && (eval2 == BlankEval.instance || (eval2 instanceof NumberEval)))) {
                return this.operator.isValid(Double.valueOf(cell.getNumericCellValue()), eval == BlankEval.instance ? null : Double.valueOf(((NumberEval) eval).getNumberValue()), eval2 != BlankEval.instance ? Double.valueOf(((NumberEval) eval2).getNumberValue()) : null);
            }
            if (DataValidationEvaluator.isType(cell, CellType.STRING) && ((eval == BlankEval.instance || (eval instanceof StringEval)) && (eval2 == BlankEval.instance || (eval2 instanceof StringEval)))) {
                return this.operator.isValid(cell.getStringCellValue(), eval == BlankEval.instance ? null : ((StringEval) eval).getStringValue(), eval2 != BlankEval.instance ? ((StringEval) eval2).getStringValue() : null);
            }
            return this.operator.isValidForIncompatibleTypes();
        }
        return false;
    }

    private ValueEval unwrapEval(ValueEval eval) {
        ValueEval comp = eval;
        while (comp instanceof RefEval) {
            RefEval ref = (RefEval) comp;
            comp = ref.getInnerValueEval(ref.getFirstSheetIndex());
        }
        return comp;
    }

    private boolean checkFormula(CellReference ref, CellRangeAddress region) {
        ValueEval comp = unwrapEval(this.workbookEvaluator.evaluate(this.rule.getFormula1(), ref, region));
        if (comp instanceof BlankEval) {
            return true;
        }
        if (comp instanceof ErrorEval) {
            return false;
        }
        if (comp instanceof BoolEval) {
            return ((BoolEval) comp).getBooleanValue();
        }
        return (comp instanceof NumberEval) && ((NumberEval) comp).getNumberValue() != 0.0d;
    }

    private boolean checkFilter(Cell cell, CellReference ref, CellRangeAddress region) {
        double d;
        OperatorEnum op;
        ConditionFilterType filterType = this.rule.getConditionFilterType();
        if (filterType == null) {
            return false;
        }
        ValueAndFormat cv = getCellValue(cell);
        switch (filterType) {
            case FILTER:
                return false;
            case TOP_10:
                if (cv.isNumber()) {
                    return getMeaningfulValues(region, false, new Function() { // from class: org.apache.poi.ss.formula.EvaluationConditionalFormatRule$$ExternalSyntheticLambda0
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            Set evaluateTop10;
                            evaluateTop10 = EvaluationConditionalFormatRule.this.evaluateTop10((List) obj);
                            return evaluateTop10;
                        }
                    }).contains(cv);
                }
                return false;
            case UNIQUE_VALUES:
                return getMeaningfulValues(region, true, new Function() { // from class: org.apache.poi.ss.formula.EvaluationConditionalFormatRule$$ExternalSyntheticLambda1
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        Set evaluateUniqueValues;
                        evaluateUniqueValues = EvaluationConditionalFormatRule.this.evaluateUniqueValues((List) obj);
                        return evaluateUniqueValues;
                    }
                }).contains(cv);
            case DUPLICATE_VALUES:
                return getMeaningfulValues(region, true, new Function() { // from class: org.apache.poi.ss.formula.EvaluationConditionalFormatRule$$ExternalSyntheticLambda2
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return EvaluationConditionalFormatRule.this.evaluateDuplicateValues((List) obj);
                    }
                }).contains(cv);
            case ABOVE_AVERAGE:
                ConditionFilterData conf = this.rule.getFilterConfiguration();
                List<ValueAndFormat> values = new ArrayList<>(getMeaningfulValues(region, false, new Function() { // from class: org.apache.poi.ss.formula.EvaluationConditionalFormatRule$$ExternalSyntheticLambda3
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        Set evaluateAboveAverage;
                        evaluateAboveAverage = EvaluationConditionalFormatRule.this.evaluateAboveAverage((List) obj);
                        return evaluateAboveAverage;
                    }
                }));
                Double val = cv.isNumber() ? cv.getValue() : null;
                if (val == null) {
                    return false;
                }
                double avg = values.get(0).value.doubleValue();
                double stdDev = values.get(1).value.doubleValue();
                if (conf.getStdDev() > 0) {
                    d = avg + ((conf.getAboveAverage() ? 1 : -1) * stdDev * conf.getStdDev());
                } else {
                    d = avg;
                }
                Double comp = Double.valueOf(d);
                if (conf.getAboveAverage()) {
                    if (conf.getEqualAverage()) {
                        op = OperatorEnum.GREATER_OR_EQUAL;
                    } else {
                        op = OperatorEnum.GREATER_THAN;
                    }
                } else if (conf.getEqualAverage()) {
                    op = OperatorEnum.LESS_OR_EQUAL;
                } else {
                    op = OperatorEnum.LESS_THAN;
                }
                return op.isValid(val, comp, null);
            case CONTAINS_TEXT:
                return this.text != null && cv.toString().toLowerCase(LocaleUtil.getUserLocale()).contains(this.lowerText);
            case NOT_CONTAINS_TEXT:
                return this.text == null || !cv.toString().toLowerCase(LocaleUtil.getUserLocale()).contains(this.lowerText);
            case BEGINS_WITH:
                return cv.toString().toLowerCase(LocaleUtil.getUserLocale()).startsWith(this.lowerText);
            case ENDS_WITH:
                return cv.toString().toLowerCase(LocaleUtil.getUserLocale()).endsWith(this.lowerText);
            case CONTAINS_BLANKS:
                try {
                    String v = cv.getString();
                    return StringUtil.isBlank(v);
                } catch (Exception e) {
                    return false;
                }
            case NOT_CONTAINS_BLANKS:
                try {
                    String v2 = cv.getString();
                    return StringUtil.isNotBlank(v2);
                } catch (Exception e2) {
                    return true;
                }
            case CONTAINS_ERRORS:
                return cell != null && DataValidationEvaluator.isType(cell, CellType.ERROR);
            case NOT_CONTAINS_ERRORS:
                return cell == null || !DataValidationEvaluator.isType(cell, CellType.ERROR);
            case TIME_PERIOD:
                return checkFormula(ref, region);
            default:
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Set<ValueAndFormat> evaluateTop10(List<ValueAndFormat> allValues) {
        ConditionFilterData conf = this.rule.getFilterConfiguration();
        if (!conf.getBottom()) {
            allValues.sort(Collections.reverseOrder());
        } else {
            Collections.sort(allValues);
        }
        int limit = Math.toIntExact(conf.getRank());
        if (conf.getPercent()) {
            limit = (allValues.size() * limit) / 100;
        }
        if (allValues.size() <= limit) {
            return new HashSet(allValues);
        }
        return new HashSet(allValues.subList(0, limit));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Set<ValueAndFormat> evaluateUniqueValues(List<ValueAndFormat> allValues) {
        Collections.sort(allValues);
        Set<ValueAndFormat> unique = new HashSet<>();
        int i = 0;
        while (i < allValues.size()) {
            ValueAndFormat v = allValues.get(i);
            if ((i < allValues.size() - 1 && v.equals(allValues.get(i + 1))) || (i > 0 && i == allValues.size() - 1 && v.equals(allValues.get(i - 1)))) {
                i++;
            } else {
                unique.add(v);
            }
            i++;
        }
        return unique;
    }

    public Set<ValueAndFormat> evaluateDuplicateValues(List<ValueAndFormat> allValues) {
        Collections.sort(allValues);
        Set<ValueAndFormat> dup = new HashSet<>();
        int i = 0;
        while (i < allValues.size()) {
            ValueAndFormat v = allValues.get(i);
            if ((i < allValues.size() - 1 && v.equals(allValues.get(i + 1))) || (i > 0 && i == allValues.size() - 1 && v.equals(allValues.get(i - 1)))) {
                dup.add(v);
                i++;
            }
            i++;
        }
        return dup;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Set<ValueAndFormat> evaluateAboveAverage(List<ValueAndFormat> allValues) {
        double total = 0.0d;
        ValueEval[] pop = new ValueEval[allValues.size()];
        for (int i = 0; i < allValues.size(); i++) {
            ValueAndFormat v = allValues.get(i);
            total += v.value.doubleValue();
            pop[i] = new NumberEval(v.value.doubleValue());
        }
        Set<ValueAndFormat> avgSet = new LinkedHashSet<>(1);
        avgSet.add(new ValueAndFormat(Double.valueOf(allValues.isEmpty() ? 0.0d : total / allValues.size()), null, this.decimalTextFormat));
        double stdDev = allValues.size() > 1 ? ((NumberEval) AggregateFunction.STDEV.evaluate(pop, 0, 0)).getNumberValue() : 0.0d;
        avgSet.add(new ValueAndFormat(Double.valueOf(stdDev), null, this.decimalTextFormat));
        return avgSet;
    }

    private Set<ValueAndFormat> getMeaningfulValues(CellRangeAddress region, boolean withText, Function<List<ValueAndFormat>, Set<ValueAndFormat>> func) {
        Set<ValueAndFormat> values = this.meaningfulRegionValues.get(region);
        if (values != null) {
            return values;
        }
        List<ValueAndFormat> allValues = new ArrayList<>(((region.getLastColumn() - region.getFirstColumn()) + 1) * ((region.getLastRow() - region.getFirstRow()) + 1));
        for (int r = region.getFirstRow(); r <= region.getLastRow(); r++) {
            Row row = this.sheet.getRow(r);
            if (row != null) {
                for (int c = region.getFirstColumn(); c <= region.getLastColumn(); c++) {
                    Cell cell = row.getCell(c);
                    ValueAndFormat cv = getCellValue(cell);
                    if (withText || cv.isNumber()) {
                        allValues.add(cv);
                    }
                }
            }
        }
        Set<ValueAndFormat> values2 = func.apply(allValues);
        this.meaningfulRegionValues.put(region, values2);
        return values2;
    }

    private ValueAndFormat getCellValue(Cell cell) {
        if (cell != null) {
            String format = cell.getCellStyle().getDataFormatString();
            CellType type = cell.getCellType();
            if (type == CellType.FORMULA) {
                type = cell.getCachedFormulaResultType();
            }
            switch (type) {
                case NUMERIC:
                    return new ValueAndFormat(Double.valueOf(cell.getNumericCellValue()), format, this.decimalTextFormat);
                case STRING:
                case BOOLEAN:
                    return new ValueAndFormat(cell.getStringCellValue(), format);
            }
        }
        return new ValueAndFormat("", "");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes10.dex */
    public static class ValueAndFormat implements Comparable<ValueAndFormat> {
        private final DecimalFormat decimalTextFormat;
        private final String format;
        private final String string;
        private final Double value;

        public ValueAndFormat(Double value, String format, DecimalFormat df) {
            this.value = value;
            this.format = format;
            this.string = null;
            this.decimalTextFormat = df;
        }

        public ValueAndFormat(String value, String format) {
            this.value = null;
            this.format = format;
            this.string = value;
            this.decimalTextFormat = null;
        }

        public boolean isNumber() {
            return this.value != null;
        }

        public Double getValue() {
            return this.value;
        }

        public String getString() {
            return this.string;
        }

        public String toString() {
            if (isNumber()) {
                return this.decimalTextFormat.format(getValue().doubleValue());
            }
            return getString();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ValueAndFormat)) {
                return false;
            }
            ValueAndFormat o = (ValueAndFormat) obj;
            return Objects.equals(this.value, o.value) && Objects.equals(this.format, o.format) && Objects.equals(this.string, o.string);
        }

        @Override // java.lang.Comparable
        public int compareTo(ValueAndFormat o) {
            if (this.value == null && o.value != null) {
                return 1;
            }
            if (o.value == null && this.value != null) {
                return -1;
            }
            int cmp = this.value == null ? 0 : this.value.compareTo(o.value);
            if (cmp != 0) {
                return cmp;
            }
            if (this.string == null && o.string != null) {
                return 1;
            }
            if (o.string == null && this.string != null) {
                return -1;
            }
            if (this.string == null) {
                return 0;
            }
            return this.string.compareTo(o.string);
        }

        public int hashCode() {
            return Objects.hash(this.string, this.value, this.format);
        }
    }
}
