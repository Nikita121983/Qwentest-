package org.apache.poi.xssf.usermodel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationErrorStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationOperator;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationType;

/* loaded from: classes10.dex */
public class XSSFDataValidation implements DataValidation {
    private static final int MAX_TEXT_LENGTH = 255;
    static final Map<Integer, STDataValidationErrorStyle.Enum> errorStyleMappings;
    static final Map<Integer, STDataValidationOperator.Enum> operatorTypeMappings;
    static final Map<STDataValidationOperator.Enum, Integer> operatorTypeReverseMappings;
    static final Map<STDataValidationErrorStyle.Enum, Integer> reverseErrorStyleMappings;
    static final Map<Integer, STDataValidationType.Enum> validationTypeMappings;
    static final Map<STDataValidationType.Enum, Integer> validationTypeReverseMappings;
    private final CTDataValidation ctDataValidation;
    private final CellRangeAddressList regions;
    private final XSSFDataValidationConstraint validationConstraint;

    static {
        HashMap<Integer, STDataValidationErrorStyle.Enum> esMappings = new HashMap<>();
        esMappings.put(2, STDataValidationErrorStyle.INFORMATION);
        esMappings.put(0, STDataValidationErrorStyle.STOP);
        esMappings.put(1, STDataValidationErrorStyle.WARNING);
        errorStyleMappings = Collections.unmodifiableMap(esMappings);
        reverseErrorStyleMappings = Collections.unmodifiableMap(MapUtils.invertMap(esMappings));
        Map<Integer, STDataValidationOperator.Enum> otMappings = new HashMap<>();
        otMappings.put(0, STDataValidationOperator.BETWEEN);
        otMappings.put(1, STDataValidationOperator.NOT_BETWEEN);
        otMappings.put(2, STDataValidationOperator.EQUAL);
        otMappings.put(3, STDataValidationOperator.NOT_EQUAL);
        otMappings.put(4, STDataValidationOperator.GREATER_THAN);
        otMappings.put(6, STDataValidationOperator.GREATER_THAN_OR_EQUAL);
        otMappings.put(5, STDataValidationOperator.LESS_THAN);
        otMappings.put(7, STDataValidationOperator.LESS_THAN_OR_EQUAL);
        operatorTypeMappings = Collections.unmodifiableMap(otMappings);
        operatorTypeReverseMappings = Collections.unmodifiableMap(MapUtils.invertMap(otMappings));
        Map<Integer, STDataValidationType.Enum> vtMappings = new HashMap<>();
        vtMappings.put(7, STDataValidationType.CUSTOM);
        vtMappings.put(4, STDataValidationType.DATE);
        vtMappings.put(2, STDataValidationType.DECIMAL);
        vtMappings.put(3, STDataValidationType.LIST);
        vtMappings.put(0, STDataValidationType.NONE);
        vtMappings.put(6, STDataValidationType.TEXT_LENGTH);
        vtMappings.put(5, STDataValidationType.TIME);
        vtMappings.put(1, STDataValidationType.WHOLE);
        validationTypeMappings = Collections.unmodifiableMap(vtMappings);
        validationTypeReverseMappings = Collections.unmodifiableMap(MapUtils.invertMap(validationTypeMappings));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSSFDataValidation(CellRangeAddressList regions, CTDataValidation ctDataValidation) {
        this(getConstraint(ctDataValidation), regions, ctDataValidation);
    }

    public XSSFDataValidation(XSSFDataValidationConstraint constraint, CellRangeAddressList regions, CTDataValidation ctDataValidation) {
        this.validationConstraint = constraint;
        this.ctDataValidation = ctDataValidation;
        this.regions = regions;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CTDataValidation getCtDataValidation() {
        return this.ctDataValidation;
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public void createErrorBox(String title, String text) {
        if (title != null && title.length() > 255) {
            throw new IllegalStateException("Error-title cannot be longer than 32 characters, but had: " + title);
        }
        if (text != null && text.length() > 255) {
            throw new IllegalStateException("Error-text cannot be longer than 255 characters, but had: " + text);
        }
        this.ctDataValidation.setErrorTitle(encodeUtf(title));
        this.ctDataValidation.setError(encodeUtf(text));
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public void createPromptBox(String title, String text) {
        if (title != null && title.length() > 255) {
            throw new IllegalStateException("Error-title cannot be longer than 32 characters, but had: " + title);
        }
        if (text != null && text.length() > 255) {
            throw new IllegalStateException("Error-text cannot be longer than 255 characters, but had: " + text);
        }
        this.ctDataValidation.setPromptTitle(encodeUtf(title));
        this.ctDataValidation.setPrompt(encodeUtf(text));
    }

    private String encodeUtf(String text) {
        if (text == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        char[] charArray = text.toCharArray();
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            char c = charArray[i];
            if (c < ' ') {
                builder.append("_x").append(c < 16 ? "000" : TarConstants.VERSION_POSIX).append(Integer.toHexString(c)).append("_");
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public boolean getEmptyCellAllowed() {
        return this.ctDataValidation.getAllowBlank();
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public String getErrorBoxText() {
        return this.ctDataValidation.getError();
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public String getErrorBoxTitle() {
        return this.ctDataValidation.getErrorTitle();
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public int getErrorStyle() {
        return reverseErrorStyleMappings.get(this.ctDataValidation.getErrorStyle()).intValue();
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public String getPromptBoxText() {
        return this.ctDataValidation.getPrompt();
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public String getPromptBoxTitle() {
        return this.ctDataValidation.getPromptTitle();
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public boolean getShowErrorBox() {
        return this.ctDataValidation.getShowErrorMessage();
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public boolean getShowPromptBox() {
        return this.ctDataValidation.getShowInputMessage();
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public boolean getSuppressDropDownArrow() {
        return !this.ctDataValidation.getShowDropDown();
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public DataValidationConstraint getValidationConstraint() {
        return this.validationConstraint;
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public void setEmptyCellAllowed(boolean allowed) {
        this.ctDataValidation.setAllowBlank(allowed);
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public void setErrorStyle(int errorStyle) {
        this.ctDataValidation.setErrorStyle(errorStyleMappings.get(Integer.valueOf(errorStyle)));
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public void setShowErrorBox(boolean show) {
        this.ctDataValidation.setShowErrorMessage(show);
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public void setShowPromptBox(boolean show) {
        this.ctDataValidation.setShowInputMessage(show);
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public void setSuppressDropDownArrow(boolean suppress) {
        if (this.validationConstraint.getValidationType() == 3) {
            this.ctDataValidation.setShowDropDown(!suppress);
        }
    }

    @Override // org.apache.poi.ss.usermodel.DataValidation
    public CellRangeAddressList getRegions() {
        return this.regions;
    }

    public String prettyPrint() {
        StringBuilder builder = new StringBuilder();
        for (CellRangeAddress address : this.regions.getCellRangeAddresses()) {
            builder.append(address.formatAsString());
        }
        builder.append(" => ");
        builder.append(this.validationConstraint.prettyPrint());
        return builder.toString();
    }

    private static XSSFDataValidationConstraint getConstraint(CTDataValidation ctDataValidation) {
        String formula1 = ctDataValidation.getFormula1();
        String formula2 = ctDataValidation.getFormula2();
        STDataValidationOperator.Enum operator = ctDataValidation.getOperator();
        STDataValidationType.Enum type = ctDataValidation.getType();
        Integer validationType = validationTypeReverseMappings.get(type);
        Integer operatorType = operatorTypeReverseMappings.get(operator);
        return new XSSFDataValidationConstraint(validationType.intValue(), operatorType.intValue(), formula1, formula2);
    }
}
