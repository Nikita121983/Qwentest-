package org.apache.xmlbeans.impl.regex;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class RegexParser {
    private static final String BUNDLE_PKG = "org.apache.xmlbeans.impl.regex.message";
    protected static final int S_INBRACKETS = 1;
    protected static final int S_INXBRACKETS = 2;
    protected static final int S_NORMAL = 0;
    static final int T_BACKSOLIDUS = 10;
    static final int T_CARET = 11;
    static final int T_CHAR = 0;
    static final int T_COMMENT = 21;
    static final int T_CONDITION = 23;
    static final int T_DOLLAR = 12;
    static final int T_DOT = 8;
    static final int T_EOF = 1;
    static final int T_INDEPENDENT = 18;
    static final int T_LBRACKET = 9;
    static final int T_LOOKAHEAD = 14;
    static final int T_LOOKBEHIND = 16;
    static final int T_LPAREN = 6;
    static final int T_LPAREN2 = 13;
    static final int T_MODIFIERS = 22;
    static final int T_NEGATIVELOOKAHEAD = 15;
    static final int T_NEGATIVELOOKBEHIND = 17;
    static final int T_OR = 2;
    static final int T_PLUS = 4;
    static final int T_POSIX_CHARCLASS_START = 20;
    static final int T_QUESTION = 5;
    static final int T_RPAREN = 7;
    static final int T_SET_OPERATIONS = 19;
    static final int T_STAR = 3;
    static final int T_XMLSCHEMA_CC_SUBTRACTION = 24;
    int chardata;
    boolean hasBackReferences;
    int nexttoken;
    int offset;
    int options;
    String regex;
    int regexlen;
    ResourceBundle resources;
    int context = 0;
    int parennumber = 1;
    Vector references = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ReferencePosition {
        int position;
        int refNumber;

        ReferencePosition(int n, int pos) {
            this.refNumber = n;
            this.position = pos;
        }
    }

    public RegexParser() {
        setLocale(Locale.getDefault());
    }

    public RegexParser(Locale locale) {
        setLocale(locale);
    }

    public void setLocale(Locale locale) {
        this.resources = ResourceBundle.getBundle(BUNDLE_PKG, locale, RegexParser.class.getClassLoader());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ParseException ex(String key, int loc) {
        return new ParseException(this.resources.getString(key), loc);
    }

    private boolean isSet(int flag) {
        return (this.options & flag) == flag;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Token parse(String regex, int options) throws ParseException {
        Token ret;
        this.options = options;
        this.offset = 0;
        setContext(0);
        this.parennumber = 1;
        this.hasBackReferences = false;
        this.regex = regex;
        if (isSet(16)) {
            this.regex = REUtil.stripExtendedComment(this.regex);
        }
        this.regexlen = this.regex.length();
        next();
        ret = parseRegex();
        if (this.offset != this.regexlen) {
            throw ex("parser.parse.1", this.offset);
        }
        if (this.references != null) {
            for (int i = 0; i < this.references.size(); i++) {
                ReferencePosition position = (ReferencePosition) this.references.elementAt(i);
                if (this.parennumber <= position.refNumber) {
                    throw ex("parser.parse.2", position.position);
                }
            }
            this.references.removeAllElements();
        }
        return ret;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setContext(int con) {
        this.context = con;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int read() {
        return this.nexttoken;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:64:0x0121. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0023. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:103:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void next() {
        /*
            Method dump skipped, instructions count: 550
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.RegexParser.next():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Token parseRegex() throws ParseException {
        Token tok = parseTerm();
        Token parent = null;
        while (read() == 2) {
            next();
            if (parent == null) {
                parent = Token.createUnion();
                parent.addChild(tok);
                tok = parent;
            }
            tok.addChild(parseTerm());
        }
        return tok;
    }

    Token parseTerm() throws ParseException {
        int ch = read();
        if (ch == 2 || ch == 7 || ch == 1) {
            return Token.createEmpty();
        }
        Token tok = parseFactor();
        Token concat = null;
        while (true) {
            int ch2 = read();
            if (ch2 == 2 || ch2 == 7 || ch2 == 1) {
                break;
            }
            if (concat == null) {
                concat = Token.createConcat();
                concat.addChild(tok);
                tok = concat;
            }
            concat.addChild(parseFactor());
        }
        return tok;
    }

    Token processCaret() throws ParseException {
        next();
        return Token.token_linebeginning;
    }

    Token processDollar() throws ParseException {
        next();
        return Token.token_lineend;
    }

    Token processLookahead() throws ParseException {
        next();
        Token tok = Token.createLook(20, parseRegex());
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processNegativelookahead() throws ParseException {
        next();
        Token tok = Token.createLook(21, parseRegex());
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processLookbehind() throws ParseException {
        next();
        Token tok = Token.createLook(22, parseRegex());
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processNegativelookbehind() throws ParseException {
        next();
        Token tok = Token.createLook(23, parseRegex());
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processBacksolidus_A() throws ParseException {
        next();
        return Token.token_stringbeginning;
    }

    Token processBacksolidus_Z() throws ParseException {
        next();
        return Token.token_stringend2;
    }

    Token processBacksolidus_z() throws ParseException {
        next();
        return Token.token_stringend;
    }

    Token processBacksolidus_b() throws ParseException {
        next();
        return Token.token_wordedge;
    }

    Token processBacksolidus_B() throws ParseException {
        next();
        return Token.token_not_wordedge;
    }

    Token processBacksolidus_lt() throws ParseException {
        next();
        return Token.token_wordbeginning;
    }

    Token processBacksolidus_gt() throws ParseException {
        next();
        return Token.token_wordend;
    }

    Token processStar(Token tok) throws ParseException {
        next();
        if (read() == 5) {
            next();
            return Token.createNGClosure(tok);
        }
        return Token.createClosure(tok);
    }

    Token processPlus(Token tok) throws ParseException {
        next();
        if (read() == 5) {
            next();
            return Token.createConcat(tok, Token.createNGClosure(tok));
        }
        return Token.createConcat(tok, Token.createClosure(tok));
    }

    Token processQuestion(Token tok) throws ParseException {
        next();
        Token par = Token.createUnion();
        if (read() == 5) {
            next();
            par.addChild(Token.createEmpty());
            par.addChild(tok);
        } else {
            par.addChild(tok);
            par.addChild(Token.createEmpty());
        }
        return par;
    }

    boolean checkQuestion(int off) {
        return off < this.regexlen && this.regex.charAt(off) == '?';
    }

    Token processParen() throws ParseException {
        next();
        int p = this.parennumber;
        this.parennumber = p + 1;
        Token tok = Token.createParen(parseRegex(), p);
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processParen2() throws ParseException {
        next();
        Token tok = Token.createParen(parseRegex(), 0);
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processCondition() throws ParseException {
        if (this.offset + 1 >= this.regexlen) {
            throw ex("parser.factor.4", this.offset);
        }
        int refno = -1;
        Token condition = null;
        int ch = this.regex.charAt(this.offset);
        if (49 <= ch && ch <= 57) {
            refno = ch - 48;
            this.hasBackReferences = true;
            if (this.references == null) {
                this.references = new Vector();
            }
            this.references.addElement(new ReferencePosition(refno, this.offset));
            this.offset++;
            if (this.regex.charAt(this.offset) != ')') {
                throw ex("parser.factor.1", this.offset);
            }
            this.offset++;
        } else {
            if (ch == 63) {
                this.offset--;
            }
            next();
            condition = parseFactor();
            switch (condition.type) {
                case 8:
                    if (read() != 7) {
                        throw ex("parser.factor.1", this.offset - 1);
                    }
                    break;
                case 20:
                case 21:
                case 22:
                case 23:
                    break;
                default:
                    throw ex("parser.factor.5", this.offset);
            }
        }
        next();
        Token yesPattern = parseRegex();
        Token noPattern = null;
        if (yesPattern.type == 2) {
            if (yesPattern.size() != 2) {
                throw ex("parser.factor.6", this.offset);
            }
            noPattern = yesPattern.getChild(1);
            yesPattern = yesPattern.getChild(0);
        }
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return Token.createCondition(refno, condition, yesPattern, noPattern);
    }

    Token processModifiers() throws ParseException {
        int v;
        int v2;
        int add = 0;
        int mask = 0;
        int ch = -1;
        while (this.offset < this.regexlen && (v2 = REUtil.getOptionValue((ch = this.regex.charAt(this.offset)))) != 0) {
            add |= v2;
            this.offset++;
        }
        if (this.offset >= this.regexlen) {
            throw ex("parser.factor.2", this.offset - 1);
        }
        if (ch == 45) {
            this.offset++;
            while (this.offset < this.regexlen && (v = REUtil.getOptionValue((ch = this.regex.charAt(this.offset)))) != 0) {
                mask |= v;
                this.offset++;
            }
            if (this.offset >= this.regexlen) {
                throw ex("parser.factor.2", this.offset - 1);
            }
        }
        if (ch == 58) {
            this.offset++;
            next();
            Token tok = Token.createModifierGroup(parseRegex(), add, mask);
            if (read() != 7) {
                throw ex("parser.factor.1", this.offset - 1);
            }
            next();
            return tok;
        }
        if (ch == 41) {
            this.offset++;
            next();
            Token tok2 = Token.createModifierGroup(parseRegex(), add, mask);
            return tok2;
        }
        throw ex("parser.factor.3", this.offset);
    }

    Token processIndependent() throws ParseException {
        next();
        Token tok = Token.createLook(24, parseRegex());
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processBacksolidus_c() throws ParseException {
        if (this.offset < this.regexlen) {
            String str = this.regex;
            int i = this.offset;
            this.offset = i + 1;
            int ch2 = str.charAt(i);
            if ((ch2 & 65504) == 64) {
                next();
                return Token.createChar(ch2 - 64);
            }
        }
        throw ex("parser.atom.1", this.offset - 1);
    }

    Token processBacksolidus_C() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processBacksolidus_i() throws ParseException {
        Token tok = Token.createChar(105);
        next();
        return tok;
    }

    Token processBacksolidus_I() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processBacksolidus_g() throws ParseException {
        next();
        return Token.getGraphemePattern();
    }

    Token processBacksolidus_X() throws ParseException {
        next();
        return Token.getCombiningCharacterSequence();
    }

    Token processBackreference() throws ParseException {
        int refnum = this.chardata - 48;
        Token tok = Token.createBackReference(refnum);
        this.hasBackReferences = true;
        if (this.references == null) {
            this.references = new Vector();
        }
        this.references.addElement(new ReferencePosition(refnum, this.offset - 2));
        next();
        return tok;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0112  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    org.apache.xmlbeans.impl.regex.Token parseFactor() throws org.apache.xmlbeans.impl.regex.ParseException {
        /*
            Method dump skipped, instructions count: 398
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.RegexParser.parseFactor():org.apache.xmlbeans.impl.regex.Token");
    }

    Token parseAtom() throws ParseException {
        Token tok;
        int ch = read();
        switch (ch) {
            case 0:
                if (this.chardata == 93 || this.chardata == 123 || this.chardata == 125) {
                    throw ex("parser.atom.4", this.offset - 1);
                }
                Token tok2 = Token.createChar(this.chardata);
                int high = this.chardata;
                next();
                if (REUtil.isHighSurrogate(high) && read() == 0 && REUtil.isLowSurrogate(this.chardata)) {
                    char[] sur = {(char) high, (char) this.chardata};
                    Token tok3 = Token.createParen(Token.createString(new String(sur)), 0);
                    next();
                    return tok3;
                }
                return tok2;
            case 6:
                return processParen();
            case 8:
                next();
                return Token.token_dot;
            case 9:
                return parseCharacterClass(true);
            case 10:
                switch (this.chardata) {
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                        return processBackreference();
                    case 67:
                        return processBacksolidus_C();
                    case 68:
                    case 83:
                    case 87:
                    case 100:
                    case 115:
                    case 119:
                        Token tok4 = getTokenForShorthand(this.chardata);
                        next();
                        return tok4;
                    case 73:
                        return processBacksolidus_I();
                    case 80:
                    case 112:
                        int pstart = this.offset;
                        tok = processBacksolidus_pP(this.chardata);
                        if (tok == null) {
                            throw ex("parser.atom.5", pstart);
                        }
                        break;
                    case 88:
                        return processBacksolidus_X();
                    case 99:
                        return processBacksolidus_c();
                    case 101:
                    case 102:
                    case 110:
                    case 114:
                    case 116:
                    case 117:
                    case 118:
                    case 120:
                        int ch2 = decodeEscaped();
                        if (ch2 < 65536) {
                            tok = Token.createChar(ch2);
                            break;
                        } else {
                            tok = Token.createString(REUtil.decomposeToSurrogates(ch2));
                            break;
                        }
                    case 103:
                        return processBacksolidus_g();
                    case 105:
                        return processBacksolidus_i();
                    default:
                        tok = Token.createChar(this.chardata);
                        break;
                }
                next();
                return tok;
            case 13:
                return processParen2();
            case 18:
                return processIndependent();
            case 19:
                return parseSetOperations();
            case 22:
                return processModifiers();
            case 23:
                return processCondition();
            default:
                throw ex("parser.atom.4", this.offset - 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RangeToken processBacksolidus_pP(int c) throws ParseException {
        next();
        if (read() != 0 || this.chardata != 123) {
            throw ex("parser.atom.2", this.offset - 1);
        }
        boolean positive = c == 112;
        int namestart = this.offset;
        int nameend = this.regex.indexOf(125, namestart);
        if (nameend < 0) {
            throw ex("parser.atom.3", this.offset);
        }
        String pname = this.regex.substring(namestart, nameend);
        this.offset = nameend + 1;
        return Token.getRange(pname, positive, isSet(512));
    }

    int processCIinCharacterClass(RangeToken tok, int c) {
        return decodeEscaped();
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0163, code lost:
    
        if (read() == 1) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0165, code lost:
    
        if (r18 != false) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0167, code lost:
    
        if (r2 == false) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0169, code lost:
    
        r3.subtractRanges(r4);
        r4 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x016d, code lost:
    
        r4.sortRanges();
        r4.compactRanges();
        setContext(0);
        next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x017a, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0181, code lost:
    
        throw ex("parser.cc.2", r17.offset);
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x00e6, code lost:
    
        throw ex("parser.cc.1", r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected org.apache.xmlbeans.impl.regex.RangeToken parseCharacterClass(boolean r18) throws org.apache.xmlbeans.impl.regex.ParseException {
        /*
            Method dump skipped, instructions count: 436
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.RegexParser.parseCharacterClass(boolean):org.apache.xmlbeans.impl.regex.RangeToken");
    }

    protected RangeToken parseSetOperations() throws ParseException {
        RangeToken tok = parseCharacterClass(false);
        while (true) {
            int type = read();
            if (type != 7) {
                int ch = this.chardata;
                if ((type == 0 && (ch == 45 || ch == 38)) || type == 4) {
                    next();
                    if (read() != 9) {
                        throw ex("parser.ope.1", this.offset - 1);
                    }
                    RangeToken t2 = parseCharacterClass(false);
                    if (type == 4) {
                        tok.mergeRanges(t2);
                    } else if (ch == 45) {
                        tok.subtractRanges(t2);
                    } else if (ch == 38) {
                        tok.intersectRanges(t2);
                    } else {
                        throw new RuntimeException("ASSERT");
                    }
                } else {
                    throw ex("parser.ope.2", this.offset - 1);
                }
            } else {
                next();
                return tok;
            }
        }
    }

    Token getTokenForShorthand(int ch) {
        switch (ch) {
            case 68:
                if (isSet(32)) {
                    Token tok = Token.getRange("Nd", false);
                    return tok;
                }
                Token tok2 = Token.token_not_0to9;
                return tok2;
            case 83:
                if (isSet(32)) {
                    Token tok3 = Token.getRange("IsSpace", false);
                    return tok3;
                }
                Token tok4 = Token.token_not_spaces;
                return tok4;
            case 87:
                if (isSet(32)) {
                    Token tok5 = Token.getRange("IsWord", false);
                    return tok5;
                }
                Token tok6 = Token.token_not_wordchars;
                return tok6;
            case 100:
                if (isSet(32)) {
                    Token tok7 = Token.getRange("Nd", true);
                    return tok7;
                }
                Token tok8 = Token.token_0to9;
                return tok8;
            case 115:
                if (isSet(32)) {
                    Token tok9 = Token.getRange("IsSpace", true);
                    return tok9;
                }
                Token tok10 = Token.token_spaces;
                return tok10;
            case 119:
                if (isSet(32)) {
                    Token tok11 = Token.getRange("IsWord", true);
                    return tok11;
                }
                Token tok12 = Token.token_wordchars;
                return tok12;
            default:
                throw new RuntimeException("Internal Error: shorthands: \\u" + Integer.toString(ch, 16));
        }
    }

    int decodeEscaped() throws ParseException {
        int v1;
        int v12;
        int v13;
        int v14;
        int v15;
        int v16;
        int v17;
        int v18;
        int v19;
        int v110;
        int v111;
        int v112;
        if (read() != 10) {
            throw ex("parser.next.1", this.offset - 1);
        }
        int c = this.chardata;
        switch (c) {
            case 65:
            case 90:
            case 122:
                throw ex("parser.descape.5", this.offset - 2);
            case 101:
                return 27;
            case 102:
                return 12;
            case 110:
                return 10;
            case 114:
                return 13;
            case 116:
                return 9;
            case 117:
                next();
                if (read() != 0 || (v1 = hexChar(this.chardata)) < 0) {
                    int uv = this.offset;
                    throw ex("parser.descape.1", uv - 1);
                }
                next();
                if (read() != 0 || (v12 = hexChar(this.chardata)) < 0) {
                    int uv2 = this.offset;
                    throw ex("parser.descape.1", uv2 - 1);
                }
                int uv3 = (v1 * 16) + v12;
                next();
                if (read() != 0 || (v13 = hexChar(this.chardata)) < 0) {
                    int uv4 = this.offset;
                    throw ex("parser.descape.1", uv4 - 1);
                }
                int uv5 = (uv3 * 16) + v13;
                next();
                if (read() != 0 || (v14 = hexChar(this.chardata)) < 0) {
                    throw ex("parser.descape.1", this.offset - 1);
                }
                return (uv5 * 16) + v14;
            case 118:
                next();
                if (read() != 0 || (v15 = hexChar(this.chardata)) < 0) {
                    throw ex("parser.descape.1", this.offset - 1);
                }
                next();
                if (read() != 0 || (v16 = hexChar(this.chardata)) < 0) {
                    throw ex("parser.descape.1", this.offset - 1);
                }
                int uv6 = (v15 * 16) + v16;
                next();
                if (read() != 0 || (v17 = hexChar(this.chardata)) < 0) {
                    throw ex("parser.descape.1", this.offset - 1);
                }
                int uv7 = (uv6 * 16) + v17;
                next();
                if (read() != 0 || (v18 = hexChar(this.chardata)) < 0) {
                    throw ex("parser.descape.1", this.offset - 1);
                }
                int uv8 = (uv7 * 16) + v18;
                next();
                if (read() != 0 || (v19 = hexChar(this.chardata)) < 0) {
                    throw ex("parser.descape.1", this.offset - 1);
                }
                int uv9 = (uv8 * 16) + v19;
                next();
                if (read() != 0 || (v110 = hexChar(this.chardata)) < 0) {
                    throw ex("parser.descape.1", this.offset - 1);
                }
                int uv10 = (uv9 * 16) + v110;
                if (uv10 > 1114111) {
                    throw ex("parser.descappe.4", this.offset - 1);
                }
                return uv10;
            case 120:
                next();
                if (read() != 0) {
                    throw ex("parser.descape.1", this.offset - 1);
                }
                if (this.chardata == 123) {
                    int uv11 = 0;
                    while (true) {
                        next();
                        if (read() != 0) {
                            throw ex("parser.descape.1", this.offset - 1);
                        }
                        int v113 = hexChar(this.chardata);
                        if (v113 >= 0) {
                            if (uv11 > uv11 * 16) {
                                throw ex("parser.descape.2", this.offset - 1);
                            }
                            uv11 = (uv11 * 16) + v113;
                        } else {
                            if (this.chardata != 125) {
                                throw ex("parser.descape.3", this.offset - 1);
                            }
                            if (uv11 > 1114111) {
                                throw ex("parser.descape.4", this.offset - 1);
                            }
                            return uv11;
                        }
                    }
                } else {
                    if (read() != 0 || (v111 = hexChar(this.chardata)) < 0) {
                        int uv12 = this.offset;
                        throw ex("parser.descape.1", uv12 - 1);
                    }
                    next();
                    if (read() != 0 || (v112 = hexChar(this.chardata)) < 0) {
                        throw ex("parser.descape.1", this.offset - 1);
                    }
                    int uv13 = (v111 * 16) + v112;
                    return uv13;
                }
                break;
            default:
                return c;
        }
    }

    private static final int hexChar(int ch) {
        if (ch < 48 || ch > 102) {
            return -1;
        }
        if (ch <= 57) {
            return ch - 48;
        }
        if (ch < 65) {
            return -1;
        }
        if (ch <= 70) {
            return (ch - 65) + 10;
        }
        if (ch < 97) {
            return -1;
        }
        return (ch - 97) + 10;
    }
}
