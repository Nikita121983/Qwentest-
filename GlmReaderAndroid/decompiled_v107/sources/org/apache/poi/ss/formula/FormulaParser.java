package org.apache.poi.ss.formula;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.constant.ErrorConstant;
import org.apache.poi.ss.formula.function.FunctionMetadata;
import org.apache.poi.ss.formula.function.FunctionMetadataRegistry;
import org.apache.poi.ss.formula.ptg.AbstractFunctionPtg;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.AreaPtg;
import org.apache.poi.ss.formula.ptg.ArrayPtg;
import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.apache.poi.ss.formula.ptg.BoolPtg;
import org.apache.poi.ss.formula.ptg.ConcatPtg;
import org.apache.poi.ss.formula.ptg.EqualPtg;
import org.apache.poi.ss.formula.ptg.ErrPtg;
import org.apache.poi.ss.formula.ptg.FuncPtg;
import org.apache.poi.ss.formula.ptg.FuncVarPtg;
import org.apache.poi.ss.formula.ptg.GreaterEqualPtg;
import org.apache.poi.ss.formula.ptg.GreaterThanPtg;
import org.apache.poi.ss.formula.ptg.IntPtg;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.poi.ss.formula.ptg.LessEqualPtg;
import org.apache.poi.ss.formula.ptg.LessThanPtg;
import org.apache.poi.ss.formula.ptg.MemAreaPtg;
import org.apache.poi.ss.formula.ptg.MemFuncPtg;
import org.apache.poi.ss.formula.ptg.MissingArgPtg;
import org.apache.poi.ss.formula.ptg.NamePtg;
import org.apache.poi.ss.formula.ptg.NameXPtg;
import org.apache.poi.ss.formula.ptg.NameXPxg;
import org.apache.poi.ss.formula.ptg.NotEqualPtg;
import org.apache.poi.ss.formula.ptg.NumberPtg;
import org.apache.poi.ss.formula.ptg.OperandPtg;
import org.apache.poi.ss.formula.ptg.OperationPtg;
import org.apache.poi.ss.formula.ptg.ParenthesisPtg;
import org.apache.poi.ss.formula.ptg.PercentPtg;
import org.apache.poi.ss.formula.ptg.PowerPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.RangePtg;
import org.apache.poi.ss.formula.ptg.RefPtg;
import org.apache.poi.ss.formula.ptg.StringPtg;
import org.apache.poi.ss.formula.ptg.UnaryMinusPtg;
import org.apache.poi.ss.formula.ptg.UnaryPlusPtg;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.util.StringUtil;

@Internal
/* loaded from: classes10.dex */
public final class FormulaParser {
    private static final char CR = '\r';
    private static final char LF = '\n';
    private static final char TAB = '\t';
    private static final String specAll = "All";
    private static final String specData = "Data";
    private static final String specHeaders = "Headers";
    private static final String specThisRow = "This Row";
    private static final String specTotals = "Totals";
    private final FormulaParsingWorkbook _book;
    private final int _formulaLength;
    private final String _formulaString;
    private boolean _inIntersection;
    private int _pointer = 0;
    private ParseNode _rootNode;
    private final int _rowIndex;
    private final int _sheetIndex;
    private final SpreadsheetVersion _ssVersion;
    private int look;
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) FormulaParser.class);
    private static final Pattern CELL_REF_PATTERN = Pattern.compile("(\\$?[A-Za-z]+)?(\\$?[0-9]+)?");

    private FormulaParser(String formula, FormulaParsingWorkbook book, int sheetIndex, int rowIndex) {
        this._formulaString = formula;
        this._book = book;
        this._ssVersion = book == null ? SpreadsheetVersion.EXCEL97 : book.getSpreadsheetVersion();
        this._formulaLength = this._formulaString.length();
        this._sheetIndex = sheetIndex;
        this._rowIndex = rowIndex;
    }

    public static Ptg[] parse(String formula, FormulaParsingWorkbook workbook, FormulaType formulaType, int sheetIndex, int rowIndex) {
        FormulaParser fp = new FormulaParser(formula, workbook, sheetIndex, rowIndex);
        fp.parse();
        return fp.getRPNPtg(formulaType);
    }

    public static Ptg[] parse(String formula, FormulaParsingWorkbook workbook, FormulaType formulaType, int sheetIndex) {
        return parse(formula, workbook, formulaType, sheetIndex, -1);
    }

    public static Area3DPxg parseStructuredReference(String tableText, FormulaParsingWorkbook workbook, int rowIndex) {
        Ptg[] arr = parse(tableText, workbook, FormulaType.CELL, -1, rowIndex);
        if (arr.length != 1 || !(arr[0] instanceof Area3DPxg)) {
            throw new IllegalStateException("Illegal structured reference, had length: " + arr.length);
        }
        return (Area3DPxg) arr[0];
    }

    private void nextChar() {
        if (isWhite(this.look)) {
            if (this.look == 32) {
                this._inIntersection = true;
            }
        } else {
            this._inIntersection = false;
        }
        if (this._pointer > this._formulaLength) {
            throw new IllegalStateException("Parsed past the end of the formula, pos: " + this._pointer + ", length: " + this._formulaLength + ", formula: " + this._formulaString);
        }
        if (this._pointer < this._formulaLength) {
            this.look = this._formulaString.codePointAt(this._pointer);
        } else {
            this.look = 0;
            this._inIntersection = false;
        }
        this._pointer += Character.charCount(this.look);
    }

    private void resetPointer(int ptr) {
        this._pointer = ptr;
        if (this._pointer <= this._formulaLength) {
            this.look = this._formulaString.codePointAt(this._pointer - Character.charCount(this.look));
        } else {
            this.look = 0;
        }
    }

    private RuntimeException expected(String s) {
        String msg;
        if (this.look == 61 && StringUtil.isBlank(this._formulaString.substring(0, this._pointer - 1))) {
            msg = "The specified formula '" + this._formulaString + "' starts with an equals sign which is not allowed.";
        } else {
            msg = new StringBuilder("Parse error near char ").append(this._pointer - 1).append(" '").appendCodePoint(this.look).append("'").append(" in specified formula '").append(this._formulaString).append("'. Expected ").append(s).toString();
        }
        return new FormulaParseException(msg);
    }

    private static boolean isAlpha(int c) {
        return Character.isLetter(c) || c == 36 || c == 95;
    }

    private static boolean isDigit(int c) {
        return Character.isDigit(c);
    }

    private static boolean isWhite(int c) {
        return c == 32 || c == 9 || c == 13 || c == 10;
    }

    private void skipWhite() {
        while (isWhite(this.look)) {
            nextChar();
        }
    }

    private void match(int x) {
        if (this.look != x) {
            throw expected(new StringBuilder().append("'").appendCodePoint(x).append("'").toString());
        }
        nextChar();
    }

    private String nextNum() {
        StringBuilder value = new StringBuilder();
        while (isDigit(this.look)) {
            value.appendCodePoint(this.look);
            nextChar();
        }
        if (value.length() == 0) {
            return null;
        }
        return value.toString();
    }

    private ParseNode parseRangeExpression() {
        ParseNode result = parseRangeable();
        boolean hasRange = false;
        while (this.look == 58) {
            int pos = this._pointer;
            nextChar();
            ParseNode nextPart = parseRangeable();
            checkValidRangeOperand("LHS", pos, result);
            checkValidRangeOperand("RHS", pos, nextPart);
            ParseNode[] children = {result, nextPart};
            result = new ParseNode(RangePtg.instance, children);
            hasRange = true;
        }
        if (hasRange) {
            return augmentWithMemPtg(result);
        }
        return result;
    }

    private static ParseNode augmentWithMemPtg(ParseNode root) {
        Ptg memPtg;
        if (needsMemFunc(root)) {
            memPtg = new MemFuncPtg(root.getEncodedSize());
        } else {
            memPtg = new MemAreaPtg(root.getEncodedSize());
        }
        return new ParseNode(memPtg, root);
    }

    private static boolean needsMemFunc(ParseNode root) {
        Ptg token = root.getToken();
        if ((token instanceof AbstractFunctionPtg) || (token instanceof ExternSheetReferenceToken) || (token instanceof NamePtg) || (token instanceof NameXPtg)) {
            return true;
        }
        if (!(token instanceof OperationPtg) && !(token instanceof ParenthesisPtg)) {
            return false;
        }
        for (ParseNode child : root.getChildren()) {
            if (needsMemFunc(child)) {
                return true;
            }
        }
        return false;
    }

    private static void checkValidRangeOperand(String sideName, int currentParsePosition, ParseNode pn) {
        if (!isValidRangeOperand(pn)) {
            throw new FormulaParseException("The " + sideName + " of the range operator ':' at position " + currentParsePosition + " is not a proper reference.");
        }
    }

    private static boolean isValidRangeOperand(ParseNode a) {
        Ptg tkn = a.getToken();
        if (tkn instanceof OperandPtg) {
            return true;
        }
        if (tkn instanceof AbstractFunctionPtg) {
            AbstractFunctionPtg afp = (AbstractFunctionPtg) tkn;
            byte returnClass = afp.getDefaultOperandClass();
            return returnClass == 0 || 32 == returnClass;
        }
        if (tkn instanceof ValueOperatorPtg) {
            return false;
        }
        if (tkn instanceof OperationPtg) {
            return true;
        }
        if (tkn instanceof ParenthesisPtg) {
            return isValidRangeOperand(a.getChildren()[0]);
        }
        return tkn == ErrPtg.REF_INVALID;
    }

    private ParseNode parseRangeable() {
        skipWhite();
        int savePointer = this._pointer;
        SheetIdentifier sheetIden = parseSheetName(false);
        if (sheetIden == null) {
            resetPointer(savePointer);
        } else {
            skipWhite();
            savePointer = this._pointer;
        }
        SimpleRangePart part1 = parseSimpleRangePart();
        if (part1 == null) {
            if (sheetIden != null) {
                if (this.look == 35) {
                    return new ParseNode(ErrPtg.valueOf(parseErrorLiteral()));
                }
                String name = parseAsName();
                if (name.isEmpty()) {
                    throw new FormulaParseException("Cell reference or Named Range expected after sheet name at index " + this._pointer + ".");
                }
                Ptg nameXPtg = this._book.getNameXPtg(name, sheetIden);
                if (nameXPtg == null) {
                    throw new FormulaParseException("Specified name '" + name + "' for sheet " + sheetIden.asFormulaString() + " not found");
                }
                return new ParseNode(nameXPtg);
            }
            return parseNonRange(savePointer);
        }
        boolean whiteAfterPart1 = isWhite(this.look);
        if (whiteAfterPart1) {
            skipWhite();
        }
        if (this.look == 58) {
            int colonPos = this._pointer;
            nextChar();
            skipWhite();
            SimpleRangePart part2 = parseSimpleRangePart();
            if (part2 != null && !part1.isCompatibleForArea(part2)) {
                part2 = null;
            }
            if (part2 == null) {
                resetPointer(colonPos);
                if (!part1.isCell()) {
                    String prefix = "";
                    if (sheetIden != null) {
                        prefix = "'" + sheetIden.getSheetIdentifier().getName() + '!';
                    }
                    throw new FormulaParseException(prefix + part1.getRep() + "' is not a proper reference.");
                }
            }
            return createAreaRefParseNode(sheetIden, part1, part2);
        }
        if (this.look == 46) {
            nextChar();
            int dotCount = 1;
            while (this.look == 46) {
                dotCount++;
                nextChar();
            }
            boolean whiteBeforePart2 = isWhite(this.look);
            skipWhite();
            SimpleRangePart part22 = parseSimpleRangePart();
            String part1And2 = this._formulaString.substring(savePointer - 1, this._pointer - 1);
            if (part22 == null) {
                if (sheetIden != null) {
                    throw new FormulaParseException("Complete area reference expected after sheet name at index " + this._pointer + ".");
                }
                return parseNonRange(savePointer);
            }
            if (whiteAfterPart1 || whiteBeforePart2) {
                if (part1.isRowOrColumn() || part22.isRowOrColumn()) {
                    throw new FormulaParseException("Dotted range (full row or column) expression '" + part1And2 + "' must not contain whitespace.");
                }
                return createAreaRefParseNode(sheetIden, part1, part22);
            }
            if (dotCount == 1 && part1.isRow() && part22.isRow()) {
                return parseNonRange(savePointer);
            }
            if ((part1.isRowOrColumn() || part22.isRowOrColumn()) && dotCount != 2) {
                throw new FormulaParseException("Dotted range (full row or column) expression '" + part1And2 + "' must have exactly 2 dots.");
            }
            return createAreaRefParseNode(sheetIden, part1, part22);
        }
        if (part1.isCell() && isValidCellReference(part1.getRep())) {
            return createAreaRefParseNode(sheetIden, part1, null);
        }
        if (sheetIden != null) {
            throw new FormulaParseException("Second part of cell reference expected after sheet name at index " + this._pointer + ".");
        }
        return parseNonRange(savePointer);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x01cf, code lost:
    
        if (r8.equals(org.apache.poi.ss.formula.FormulaParser.specThisRow) != false) goto L95;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:20:0x00af. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.apache.poi.ss.formula.ParseNode parseStructuredReference(java.lang.String r37) {
        /*
            Method dump skipped, instructions count: 1236
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.FormulaParser.parseStructuredReference(java.lang.String):org.apache.poi.ss.formula.ParseNode");
    }

    private String parseAsColumnQuantifier() {
        if (this.look != 91) {
            return null;
        }
        nextChar();
        if (this.look == 35) {
            return null;
        }
        if (this.look == 64) {
            nextChar();
        }
        StringBuilder name = new StringBuilder();
        while (this.look != 93) {
            name.appendCodePoint(this.look);
            nextChar();
        }
        match(93);
        return name.toString();
    }

    private String parseAsSpecialQuantifier() {
        if (this.look != 91) {
            return null;
        }
        nextChar();
        if (this.look != 35) {
            return null;
        }
        nextChar();
        String name = parseAsName();
        if (name.equals("This")) {
            name = name + Chars.SPACE + parseAsName();
        }
        match(93);
        return name;
    }

    private ParseNode parseNonRange(int savePointer) {
        resetPointer(savePointer);
        if (Character.isDigit(this.look)) {
            return new ParseNode(parseNumber());
        }
        if (this.look == 34) {
            return new ParseNode(new StringPtg(parseStringLiteral()));
        }
        String name = parseAsName();
        if (this.look == 40) {
            return function(name);
        }
        if (this.look == 91) {
            return parseStructuredReference(name);
        }
        if (name.equalsIgnoreCase("TRUE") || name.equalsIgnoreCase("FALSE")) {
            return new ParseNode(BoolPtg.valueOf(name.equalsIgnoreCase("TRUE")));
        }
        if (this._book == null) {
            throw new IllegalStateException("Need book to evaluate name '" + name + "'");
        }
        EvaluationName evalName = this._book.getName(name, this._sheetIndex);
        if (evalName == null) {
            throw new FormulaParseException("Specified named range '" + name + "' does not exist in the current workbook.");
        }
        if (evalName.isRange()) {
            return new ParseNode(evalName.createPtg());
        }
        throw new FormulaParseException("Specified name '" + name + "' is not a range as expected.");
    }

    private String parseAsName() {
        StringBuilder sb = new StringBuilder();
        if (!Character.isLetter(this.look) && this.look != 95 && this.look != 92) {
            throw expected("number, string, defined name, or data table");
        }
        while (isValidDefinedNameChar(this.look)) {
            sb.appendCodePoint(this.look);
            nextChar();
        }
        skipWhite();
        return sb.toString();
    }

    private static boolean isValidDefinedNameChar(int ch) {
        if (Character.isLetterOrDigit(ch) || ch > 128) {
            return true;
        }
        switch (ch) {
            case 46:
            case 63:
            case 92:
            case 95:
                return true;
            default:
                return false;
        }
    }

    private ParseNode createAreaRefParseNode(SheetIdentifier sheetIden, SimpleRangePart part1, SimpleRangePart part2) throws FormulaParseException {
        Ptg ptg;
        if (part2 == null) {
            CellReference cr = part1.getCellReference();
            if (sheetIden == null) {
                ptg = new RefPtg(cr);
            } else {
                ptg = this._book.get3DReferencePtg(cr, sheetIden);
            }
        } else {
            AreaReference areaRef = createAreaRef(part1, part2);
            if (sheetIden == null) {
                ptg = new AreaPtg(areaRef);
            } else {
                ptg = this._book.get3DReferencePtg(areaRef, sheetIden);
            }
        }
        return new ParseNode(ptg);
    }

    private AreaReference createAreaRef(SimpleRangePart part1, SimpleRangePart part2) {
        if (!part1.isCompatibleForArea(part2)) {
            throw new FormulaParseException("has incompatible parts: '" + part1.getRep() + "' and '" + part2.getRep() + "'.");
        }
        if (part1.isRow()) {
            return AreaReference.getWholeRow(this._ssVersion, part1.getRep(), part2.getRep());
        }
        if (part1.isColumn()) {
            return AreaReference.getWholeColumn(this._ssVersion, part1.getRep(), part2.getRep());
        }
        return new AreaReference(part1.getCellReference(), part2.getCellReference(), this._ssVersion);
    }

    private SimpleRangePart parseSimpleRangePart() {
        int ptr = this._pointer - 1;
        boolean hasDigits = false;
        boolean hasLetters = false;
        while (ptr < this._formulaLength) {
            char ch = this._formulaString.charAt(ptr);
            if (Character.isDigit(ch)) {
                hasDigits = true;
            } else if (Character.isLetter(ch)) {
                hasLetters = true;
            } else if (ch != '$' && ch != '_') {
                break;
            }
            ptr++;
        }
        if (ptr <= this._pointer - 1) {
            return null;
        }
        String rep = this._formulaString.substring(this._pointer - 1, ptr);
        if (!CELL_REF_PATTERN.matcher(rep).matches()) {
            return null;
        }
        if (hasLetters && hasDigits) {
            if (!isValidCellReference(rep)) {
                return null;
            }
        } else if (hasLetters) {
            if (!CellReference.isColumnWithinRange(rep.replace("$", ""), this._ssVersion)) {
                return null;
            }
        } else {
            if (!hasDigits) {
                return null;
            }
            try {
                int i = Integer.parseInt(rep.replace("$", ""));
                if (i < 1 || i > this._ssVersion.getMaxRows()) {
                    return null;
                }
            } catch (NumberFormatException e) {
                return null;
            }
        }
        resetPointer(ptr + 1);
        return new SimpleRangePart(rep, hasLetters, hasDigits);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class SimpleRangePart {
        private final String _rep;
        private final Type _type;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes10.dex */
        public enum Type {
            CELL,
            ROW,
            COLUMN;

            public static Type get(boolean hasLetters, boolean hasDigits) {
                if (hasLetters) {
                    return hasDigits ? CELL : COLUMN;
                }
                if (!hasDigits) {
                    throw new IllegalArgumentException("must have either letters or numbers");
                }
                return ROW;
            }
        }

        public SimpleRangePart(String rep, boolean hasLetters, boolean hasNumbers) {
            this._rep = rep;
            this._type = Type.get(hasLetters, hasNumbers);
        }

        public boolean isCell() {
            return this._type == Type.CELL;
        }

        public boolean isRowOrColumn() {
            return this._type != Type.CELL;
        }

        public CellReference getCellReference() {
            if (this._type != Type.CELL) {
                throw new IllegalStateException("Not applicable to this reference-type, expected CELL, but had " + this._type);
            }
            return new CellReference(this._rep);
        }

        public boolean isColumn() {
            return this._type == Type.COLUMN;
        }

        public boolean isRow() {
            return this._type == Type.ROW;
        }

        public String getRep() {
            return this._rep;
        }

        public boolean isCompatibleForArea(SimpleRangePart part2) {
            return this._type == part2._type;
        }

        public String toString() {
            return getClass().getName() + " [" + this._rep + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
        }
    }

    private String getBookName() {
        StringBuilder sb = new StringBuilder();
        nextChar();
        while (this.look != 93) {
            sb.appendCodePoint(this.look);
            nextChar();
        }
        nextChar();
        return sb.toString();
    }

    private SheetIdentifier parseSheetName(boolean isSndPartOfQuotedRange) {
        String bookName;
        if (this.look == 91) {
            bookName = getBookName();
        } else {
            bookName = null;
        }
        if (this.look == 39 || isSndPartOfQuotedRange) {
            if (!isSndPartOfQuotedRange) {
                match(39);
            }
            if (this.look == 91) {
                bookName = getBookName();
            }
            StringBuilder sb = new StringBuilder();
            boolean done = this.look == 39;
            while (!done) {
                sb.appendCodePoint(this.look);
                nextChar();
                switch (this.look) {
                    case 39:
                        nextChar();
                        if (this.look != 39) {
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        break;
                }
                done = true;
            }
            NameIdentifier iden = new NameIdentifier(sb.toString(), true);
            skipWhite();
            if (this.look == 33) {
                nextChar();
                return new SheetIdentifier(bookName, iden);
            }
            if (this.look == 58) {
                return parseSheetRange(bookName, iden, true);
            }
            return null;
        }
        if (this.look == 95 || Character.isLetter(this.look)) {
            StringBuilder sb2 = new StringBuilder();
            while (isUnquotedSheetNameChar(this.look)) {
                sb2.appendCodePoint(this.look);
                nextChar();
            }
            if (this.look == 39) {
                nextChar();
            }
            NameIdentifier iden2 = new NameIdentifier(sb2.toString(), false);
            skipWhite();
            if (this.look == 33) {
                nextChar();
                return new SheetIdentifier(bookName, iden2);
            }
            if (this.look == 58) {
                return parseSheetRange(bookName, iden2, false);
            }
            return null;
        }
        if (this.look != 33 || bookName == null) {
            return null;
        }
        nextChar();
        return new SheetIdentifier(bookName, null);
    }

    private SheetIdentifier parseSheetRange(String bookname, NameIdentifier sheet1Name, boolean isSndPartOfQuotedRange) {
        nextChar();
        SheetIdentifier sheet2 = parseSheetName(isSndPartOfQuotedRange);
        if (sheet2 != null) {
            return new SheetRangeIdentifier(bookname, sheet1Name, sheet2.getSheetIdentifier());
        }
        return null;
    }

    private static boolean isUnquotedSheetNameChar(int ch) {
        if (Character.isLetterOrDigit(ch) || ch > 128) {
            return true;
        }
        switch (ch) {
            case 32:
            case 46:
            case 95:
                return true;
            default:
                return false;
        }
    }

    private boolean isValidCellReference(String str) {
        boolean result = CellReference.classifyCellReference(str, this._ssVersion) == CellReference.NameType.CELL;
        if (result) {
            boolean isFunc = FunctionMetadataRegistry.getFunctionByName(str.toUpperCase(Locale.ROOT)) != null;
            if (isFunc) {
                int savePointer = this._pointer;
                resetPointer(this._pointer + str.length());
                skipWhite();
                boolean result2 = this.look != 40;
                resetPointer(savePointer);
                return result2;
            }
            return result;
        }
        return result;
    }

    private ParseNode function(String name) {
        Ptg nameToken = null;
        if (!AbstractFunctionPtg.isBuiltInFunctionName(name)) {
            if (this._book == null) {
                throw new IllegalStateException("Need book to evaluate name '" + name + "'");
            }
            EvaluationName hName = this._book.getName(name, this._sheetIndex);
            if (hName != null) {
                if (!hName.isFunctionName()) {
                    throw new FormulaParseException("Attempt to use name '" + name + "' as a function, but defined name in workbook does not refer to a function");
                }
                nameToken = hName.createPtg();
            } else {
                nameToken = this._book.getNameXPtg(name, null);
                if (nameToken == null) {
                    LOGGER.atWarn().log("FormulaParser.function: Name '{}' is completely unknown in the current workbook.", name);
                    switch (this._book.getSpreadsheetVersion()) {
                        case EXCEL97:
                            addName(name);
                            nameToken = this._book.getName(name, this._sheetIndex).createPtg();
                            break;
                        case EXCEL2007:
                            nameToken = new NameXPxg(name);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected spreadsheet version: " + this._book.getSpreadsheetVersion().name());
                    }
                }
            }
        }
        match(40);
        ParseNode[] args = Arguments();
        match(41);
        return getFunction(name, nameToken, args);
    }

    private void addName(String functionName) {
        Name name = this._book.createName();
        name.setFunction(true);
        name.setNameName(functionName);
        name.setSheetIndex(this._sheetIndex);
    }

    private ParseNode getFunction(String name, Ptg namePtg, ParseNode[] args) {
        AbstractFunctionPtg retval;
        FunctionMetadata fm = FunctionMetadataRegistry.getFunctionByName(name.toUpperCase(Locale.ROOT));
        int numArgs = args.length;
        if (fm == null) {
            if (namePtg == null) {
                throw new IllegalStateException("NamePtg must be supplied for external functions");
            }
            ParseNode[] allArgs = new ParseNode[numArgs + 1];
            allArgs[0] = new ParseNode(namePtg);
            System.arraycopy(args, 0, allArgs, 1, numArgs);
            return new ParseNode(FuncVarPtg.create(name, numArgs + 1), allArgs);
        }
        if (namePtg == null) {
            boolean isVarArgs = !fm.hasFixedArgsLength();
            int funcIx = fm.getIndex();
            if (funcIx == 4 && args.length == 1) {
                return new ParseNode(AttrPtg.getSumSingle(), args);
            }
            validateNumArgs(args.length, fm);
            if (isVarArgs) {
                retval = FuncVarPtg.create(name, numArgs);
            } else {
                retval = FuncPtg.create(funcIx);
            }
            return new ParseNode(retval, args);
        }
        throw new IllegalStateException("NamePtg no applicable to internal functions");
    }

    private void validateNumArgs(int numArgs, FunctionMetadata fm) {
        int maxArgs;
        String msg;
        String msg2;
        if (numArgs < fm.getMinParams()) {
            String msg3 = "Too few arguments to function '" + fm.getName() + "'. ";
            if (fm.hasFixedArgsLength()) {
                msg2 = msg3 + "Expected " + fm.getMinParams();
            } else {
                msg2 = msg3 + "At least " + fm.getMinParams() + " were expected";
            }
            throw new FormulaParseException(msg2 + " but got " + numArgs + ".");
        }
        if (fm.hasUnlimitedVarags()) {
            if (this._book != null) {
                maxArgs = this._book.getSpreadsheetVersion().getMaxFunctionArgs();
            } else {
                maxArgs = fm.getMaxParams();
            }
        } else {
            maxArgs = fm.getMaxParams();
        }
        if (numArgs > maxArgs) {
            String msg4 = "Too many arguments to function '" + fm.getName() + "'. ";
            if (fm.hasFixedArgsLength()) {
                msg = msg4 + "Expected " + maxArgs;
            } else {
                msg = msg4 + "At most " + maxArgs + " were expected";
            }
            throw new FormulaParseException(msg + " but got " + numArgs + ".");
        }
    }

    private static boolean isArgumentDelimiter(int ch) {
        return ch == 44 || ch == 41;
    }

    private ParseNode[] Arguments() {
        List<ParseNode> temp = new ArrayList<>(2);
        skipWhite();
        if (this.look == 41) {
            return ParseNode.EMPTY_ARRAY;
        }
        boolean missedPrevArg = true;
        while (true) {
            skipWhite();
            if (isArgumentDelimiter(this.look)) {
                if (missedPrevArg) {
                    temp.add(new ParseNode(MissingArgPtg.instance));
                }
                if (this.look != 41) {
                    match(44);
                    missedPrevArg = true;
                } else {
                    ParseNode[] result = new ParseNode[temp.size()];
                    temp.toArray(result);
                    return result;
                }
            } else {
                temp.add(intersectionExpression());
                missedPrevArg = false;
                skipWhite();
                if (!isArgumentDelimiter(this.look)) {
                    throw expected("',' or ')'");
                }
            }
        }
    }

    private ParseNode powerFactor() {
        ParseNode result = percentFactor();
        while (true) {
            skipWhite();
            if (this.look != 94) {
                return result;
            }
            match(94);
            ParseNode other = percentFactor();
            result = new ParseNode(PowerPtg.instance, result, other);
        }
    }

    private ParseNode percentFactor() {
        ParseNode result = parseSimpleFactor();
        while (true) {
            skipWhite();
            if (this.look != 37) {
                return result;
            }
            match(37);
            result = new ParseNode(PercentPtg.instance, result);
        }
    }

    private ParseNode parseSimpleFactor() {
        skipWhite();
        switch (this.look) {
            case 34:
                return new ParseNode(new StringPtg(parseStringLiteral()));
            case 35:
                return new ParseNode(ErrPtg.valueOf(parseErrorLiteral()));
            case 40:
                match(40);
                ParseNode inside = unionExpression();
                match(41);
                return new ParseNode(ParenthesisPtg.instance, inside);
            case 43:
                match(43);
                return parseUnary(true);
            case 45:
                match(45);
                return parseUnary(false);
            case 123:
                match(123);
                ParseNode arrayNode = parseArray();
                match(125);
                return arrayNode;
            default:
                if (isAlpha(this.look) || Character.isDigit(this.look) || this.look == 39 || this.look == 91 || this.look == 95 || this.look == 92) {
                    return parseRangeExpression();
                }
                if (this.look == 46) {
                    return new ParseNode(parseNumber());
                }
                throw expected("cell ref or constant literal");
        }
    }

    private ParseNode parseUnary(boolean isPlus) {
        boolean numberFollows = isDigit(this.look) || this.look == 46;
        ParseNode factor = powerFactor();
        if (numberFollows) {
            Ptg token = factor.getToken();
            if (token instanceof NumberPtg) {
                if (isPlus) {
                    return factor;
                }
                return new ParseNode(new NumberPtg(-((NumberPtg) token).getValue()));
            }
            if (token instanceof IntPtg) {
                if (isPlus) {
                    return factor;
                }
                int intVal = ((IntPtg) token).getValue();
                return new ParseNode(new NumberPtg(-intVal));
            }
        }
        return new ParseNode(isPlus ? UnaryPlusPtg.instance : UnaryMinusPtg.instance, factor);
    }

    private ParseNode parseArray() {
        List<Object[]> rowsData = new ArrayList<>();
        while (true) {
            Object[] singleRowData = parseArrayRow();
            rowsData.add(singleRowData);
            if (this.look != 125) {
                if (this.look != 59) {
                    throw expected("'}' or ';'");
                }
                match(59);
            } else {
                int nRows = rowsData.size();
                Object[][] values2d = new Object[nRows];
                rowsData.toArray(values2d);
                int nColumns = values2d[0].length;
                checkRowLengths(values2d, nColumns);
                return new ParseNode(new ArrayPtg(values2d));
            }
        }
    }

    private void checkRowLengths(Object[][] values2d, int nColumns) {
        for (int i = 0; i < values2d.length; i++) {
            int rowLen = values2d[i].length;
            if (rowLen != nColumns) {
                throw new FormulaParseException("Array row " + i + " has length " + rowLen + " but row 0 has length " + nColumns);
            }
        }
    }

    private Object[] parseArrayRow() {
        List<Object> temp = new ArrayList<>();
        while (true) {
            temp.add(parseArrayItem());
            skipWhite();
            switch (this.look) {
                case 44:
                    match(44);
                case 59:
                case 125:
                    Object[] result = new Object[temp.size()];
                    temp.toArray(result);
                    return result;
                default:
                    throw expected("'}' or ','");
            }
        }
    }

    private Object parseArrayItem() {
        skipWhite();
        switch (this.look) {
            case 34:
                return parseStringLiteral();
            case 35:
                return ErrorConstant.valueOf(parseErrorLiteral());
            case 45:
                match(45);
                skipWhite();
                return convertArrayNumber(parseNumber(), false);
            case 70:
            case 84:
            case 102:
            case 116:
                return parseBooleanLiteral();
            default:
                return convertArrayNumber(parseNumber(), true);
        }
    }

    private Boolean parseBooleanLiteral() {
        String iden = parseUnquotedIdentifier();
        if ("TRUE".equalsIgnoreCase(iden)) {
            return Boolean.TRUE;
        }
        if ("FALSE".equalsIgnoreCase(iden)) {
            return Boolean.FALSE;
        }
        throw expected("'TRUE' or 'FALSE'");
    }

    private static Double convertArrayNumber(Ptg ptg, boolean isPositive) {
        double value;
        if (ptg instanceof IntPtg) {
            value = ((IntPtg) ptg).getValue();
        } else if (ptg instanceof NumberPtg) {
            value = ((NumberPtg) ptg).getValue();
        } else {
            throw new IllegalStateException("Unexpected ptg (" + ptg.getClass().getName() + ")");
        }
        if (!isPositive) {
            value = -value;
        }
        return Double.valueOf(value);
    }

    private Ptg parseNumber() {
        String number2 = null;
        String exponent = null;
        String number1 = nextNum();
        if (this.look == 46) {
            nextChar();
            number2 = nextNum();
        }
        if (this.look == 69) {
            nextChar();
            String sign = "";
            if (this.look == 43) {
                nextChar();
            } else if (this.look == 45) {
                nextChar();
                sign = ProcessIdUtil.DEFAULT_PROCESSID;
            }
            String number = nextNum();
            if (number == null) {
                throw expected("Integer");
            }
            exponent = sign + number;
        }
        if (number1 == null && number2 == null) {
            throw expected("Integer");
        }
        return getNumberPtgFromString(number1, number2, exponent);
    }

    private int parseErrorLiteral() {
        match(35);
        String part1 = parseUnquotedIdentifier();
        if (part1 == null) {
            throw expected("remainder of error constant literal");
        }
        String part12 = part1.toUpperCase(Locale.ROOT);
        switch (part12.charAt(0)) {
            case 'D':
                FormulaError fe = FormulaError.DIV0;
                if (part12.equals("DIV")) {
                    match(47);
                    match(48);
                    match(33);
                    return fe.getCode();
                }
                throw expected(fe.getString());
            case 'N':
                FormulaError fe2 = FormulaError.NAME;
                if (part12.equals(fe2.name())) {
                    match(63);
                    return fe2.getCode();
                }
                FormulaError fe3 = FormulaError.NUM;
                if (part12.equals(fe3.name())) {
                    match(33);
                    return fe3.getCode();
                }
                FormulaError fe4 = FormulaError.NULL;
                if (part12.equals(fe4.name())) {
                    match(33);
                    return fe4.getCode();
                }
                FormulaError fe5 = FormulaError.NA;
                if (part12.equals("N")) {
                    match(47);
                    if (this.look != 65 && this.look != 97) {
                        throw expected(fe5.getString());
                    }
                    match(this.look);
                    return fe5.getCode();
                }
                throw expected("#NAME?, #NUM!, #NULL! or #N/A");
            case 'R':
                FormulaError fe6 = FormulaError.REF;
                if (part12.equals(fe6.name())) {
                    match(33);
                    return fe6.getCode();
                }
                throw expected(fe6.getString());
            case 'V':
                FormulaError fe7 = FormulaError.VALUE;
                if (part12.equals(fe7.name())) {
                    match(33);
                    return fe7.getCode();
                }
                throw expected(fe7.getString());
            default:
                throw expected("#VALUE!, #REF!, #DIV/0!, #NAME?, #NUM!, #NULL! or #N/A");
        }
    }

    private String parseUnquotedIdentifier() {
        if (this.look == 39) {
            throw expected("unquoted identifier");
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!Character.isLetterOrDigit(this.look) && this.look != 46) {
                break;
            }
            sb.appendCodePoint(this.look);
            nextChar();
        }
        if (sb.length() < 1) {
            return null;
        }
        return sb.toString();
    }

    private static Ptg getNumberPtgFromString(String number1, String number2, String exponent) {
        StringBuilder number = new StringBuilder();
        if (number2 == null) {
            number.append(number1);
            if (exponent != null) {
                number.append('E');
                number.append(exponent);
            }
            String numberStr = number.toString();
            try {
                int intVal = Integer.parseInt(numberStr);
                if (IntPtg.isInRange(intVal)) {
                    return new IntPtg(intVal);
                }
                return new NumberPtg(numberStr);
            } catch (NumberFormatException e) {
                return new NumberPtg(numberStr);
            }
        }
        if (number1 != null) {
            number.append(number1);
        }
        number.append('.');
        number.append(number2);
        if (exponent != null) {
            number.append('E');
            number.append(exponent);
        }
        return new NumberPtg(number.toString());
    }

    private String parseStringLiteral() {
        match(34);
        StringBuilder token = new StringBuilder();
        while (true) {
            if (this.look == 34) {
                nextChar();
                if (this.look != 34) {
                    return token.toString();
                }
            }
            token.appendCodePoint(this.look);
            nextChar();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x000c, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.apache.poi.ss.formula.ParseNode Term() {
        /*
            r4 = this;
            org.apache.poi.ss.formula.ParseNode r0 = r4.powerFactor()
        L4:
            r4.skipWhite()
            int r1 = r4.look
            switch(r1) {
                case 42: goto L15;
                case 47: goto Ld;
                default: goto Lc;
            }
        Lc:
            return r0
        Ld:
            r1 = 47
            r4.match(r1)
            org.apache.poi.ss.formula.ptg.DividePtg r1 = org.apache.poi.ss.formula.ptg.DividePtg.instance
            goto L1d
        L15:
            r1 = 42
            r4.match(r1)
            org.apache.poi.ss.formula.ptg.MultiplyPtg r1 = org.apache.poi.ss.formula.ptg.MultiplyPtg.instance
        L1d:
            org.apache.poi.ss.formula.ParseNode r2 = r4.powerFactor()
            org.apache.poi.ss.formula.ParseNode r3 = new org.apache.poi.ss.formula.ParseNode
            r3.<init>(r1, r0, r2)
            r0 = r3
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.FormulaParser.Term():org.apache.poi.ss.formula.ParseNode");
    }

    private ParseNode unionExpression() {
        ParseNode result = intersectionExpression();
        boolean hasUnions = false;
        while (true) {
            skipWhite();
            if (this.look != 44) {
                break;
            }
            nextChar();
            hasUnions = true;
            ParseNode other = intersectionExpression();
            result = new ParseNode(UnionPtg.instance, result, other);
        }
        if (hasUnions) {
            return augmentWithMemPtg(result);
        }
        return result;
    }

    private ParseNode intersectionExpression() {
        ParseNode result = comparisonExpression();
        boolean hasIntersections = false;
        while (true) {
            skipWhite();
            if (!this._inIntersection) {
                break;
            }
            int savePointer = this._pointer;
            try {
                ParseNode other = comparisonExpression();
                result = new ParseNode(IntersectionPtg.instance, result, other);
                hasIntersections = true;
            } catch (FormulaParseException e) {
                resetPointer(savePointer);
            }
        }
        if (hasIntersections) {
            return augmentWithMemPtg(result);
        }
        return result;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:923)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:201)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    private org.apache.poi.ss.formula.ParseNode comparisonExpression() {
        /*
            r4 = this;
            org.apache.poi.ss.formula.ParseNode r0 = r4.concatExpression()
        L4:
            r4.skipWhite()
            int r1 = r4.look
            switch(r1) {
                case 60: goto Ld;
                case 61: goto Ld;
                case 62: goto Ld;
                default: goto Lc;
            }
        Lc:
            return r0
        Ld:
            org.apache.poi.ss.formula.ptg.Ptg r1 = r4.getComparisonToken()
            org.apache.poi.ss.formula.ParseNode r2 = r4.concatExpression()
            org.apache.poi.ss.formula.ParseNode r3 = new org.apache.poi.ss.formula.ParseNode
            r3.<init>(r1, r0, r2)
            r0 = r3
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.FormulaParser.comparisonExpression():org.apache.poi.ss.formula.ParseNode");
    }

    private Ptg getComparisonToken() {
        if (this.look == 61) {
            match(this.look);
            return EqualPtg.instance;
        }
        boolean isGreater = this.look == 62;
        match(this.look);
        if (isGreater) {
            if (this.look == 61) {
                match(61);
                return GreaterEqualPtg.instance;
            }
            return GreaterThanPtg.instance;
        }
        switch (this.look) {
            case 61:
                match(61);
                return LessEqualPtg.instance;
            case 62:
                match(62);
                return NotEqualPtg.instance;
            default:
                return LessThanPtg.instance;
        }
    }

    private ParseNode concatExpression() {
        ParseNode result = additiveExpression();
        while (true) {
            skipWhite();
            if (this.look == 38) {
                match(38);
                ParseNode other = additiveExpression();
                result = new ParseNode(ConcatPtg.instance, result, other);
            } else {
                return result;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x000c, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.apache.poi.ss.formula.ParseNode additiveExpression() {
        /*
            r4 = this;
            org.apache.poi.ss.formula.ParseNode r0 = r4.Term()
        L4:
            r4.skipWhite()
            int r1 = r4.look
            switch(r1) {
                case 43: goto L15;
                case 44: goto Lc;
                case 45: goto Ld;
                default: goto Lc;
            }
        Lc:
            return r0
        Ld:
            r1 = 45
            r4.match(r1)
            org.apache.poi.ss.formula.ptg.SubtractPtg r1 = org.apache.poi.ss.formula.ptg.SubtractPtg.instance
            goto L1d
        L15:
            r1 = 43
            r4.match(r1)
            org.apache.poi.ss.formula.ptg.AddPtg r1 = org.apache.poi.ss.formula.ptg.AddPtg.instance
        L1d:
            org.apache.poi.ss.formula.ParseNode r2 = r4.Term()
            org.apache.poi.ss.formula.ParseNode r3 = new org.apache.poi.ss.formula.ParseNode
            r3.<init>(r1, r0, r2)
            r0 = r3
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.FormulaParser.additiveExpression():org.apache.poi.ss.formula.ParseNode");
    }

    private void parse() {
        this._pointer = 0;
        nextChar();
        this._rootNode = unionExpression();
        if (this._pointer <= this._formulaLength) {
            String msg = "Unused input [" + this._formulaString.substring(this._pointer - 1) + "] after attempting to parse the formula [" + this._formulaString + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
            throw new FormulaParseException(msg);
        }
    }

    private Ptg[] getRPNPtg(FormulaType formulaType) {
        OperandClassTransformer oct = new OperandClassTransformer(formulaType);
        oct.transformFormula(this._rootNode);
        return ParseNode.toTokenArray(this._rootNode);
    }
}
