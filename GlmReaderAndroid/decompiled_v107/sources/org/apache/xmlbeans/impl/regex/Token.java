package org.apache.xmlbeans.impl.regex;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.commons.codec.language.bm.Rule;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.math3.geometry.VectorFormat;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class Token implements Serializable {
    static final int ANCHOR = 8;
    static final int BACKREFERENCE = 12;
    static final int CHAR = 0;
    static final int CHAR_FINAL_QUOTE = 30;
    static final int CHAR_INIT_QUOTE = 29;
    static final int CHAR_LETTER = 31;
    static final int CHAR_MARK = 32;
    static final int CHAR_NUMBER = 33;
    static final int CHAR_OTHER = 35;
    static final int CHAR_PUNCTUATION = 36;
    static final int CHAR_SEPARATOR = 34;
    static final int CHAR_SYMBOL = 37;
    static final int CLOSURE = 3;
    static final int CONCAT = 1;
    static final int CONDITION = 26;
    static final boolean COUNTTOKENS = true;
    static final int DOT = 11;
    static final int EMPTY = 7;
    static final int FC_ANY = 2;
    static final int FC_CONTINUE = 0;
    static final int FC_TERMINAL = 1;
    static final int INDEPENDENT = 24;
    static final int LOOKAHEAD = 20;
    static final int LOOKBEHIND = 22;
    static final int MODIFIERGROUP = 25;
    static final int NEGATIVELOOKAHEAD = 21;
    static final int NEGATIVELOOKBEHIND = 23;
    private static final int NONBMP_BLOCK_START = 84;
    static final int NONGREEDYCLOSURE = 9;
    static final int NRANGE = 5;
    static final int PAREN = 6;
    static final int RANGE = 4;
    static final int STRING = 10;
    static final int UNION = 2;
    static final int UTF16_MAX = 1114111;
    private static final String[] blockNames;
    static final String blockRanges = "\u0000\u007f\u0080ÿĀſƀɏɐʯʰ˿̀ͯͰϿЀӿ\u0530֏\u0590\u05ff\u0600ۿ܀ݏހ\u07bfऀॿঀ\u09ff\u0a00\u0a7f\u0a80૿\u0b00\u0b7f\u0b80\u0bffఀ౿ಀ\u0cffഀൿ\u0d80\u0dff\u0e00\u0e7f\u0e80\u0effༀ\u0fffက႟Ⴀჿᄀᇿሀ\u137fᎠ\u13ff᐀ᙿ\u1680\u169fᚠ\u16ffក\u17ff᠀\u18afḀỿἀ\u1fff\u2000\u206f⁰\u209f₠\u20cf⃐\u20ff℀⅏⅐\u218f←⇿∀⋿⌀⏿␀\u243f⑀\u245f①⓿─╿▀▟■◿☀⛿✀➿⠀⣿⺀\u2eff⼀\u2fdf⿰\u2fff\u3000〿\u3040ゟ゠ヿ\u3100ㄯ\u3130\u318f㆐㆟ㆠㆿ㈀㋿㌀㏿㐀䶵一\u9fffꀀ\ua48f꒐\ua4cf가힣\ue000\uf8ff豈\ufaffﬀﭏﭐ\ufdff︠︯︰﹏﹐\ufe6fﹰ\ufefe\ufeff\ufeff\uff00\uffef";
    private static final Hashtable categories;
    private static final Hashtable categories2;
    private static final String[] categoryNames;
    static final int[] nonBMPBlockRanges;
    static Hashtable nonxs = null;
    private static Token token_ccs = null;
    private static Token token_grapheme = null;
    static Token token_not_0to9 = null;
    static Token token_not_spaces = null;
    static Token token_not_wordchars = null;
    static Token token_spaces = null;
    static Token token_wordchars = null;
    static final String viramaString = "्্੍્୍்్್്ฺ྄";
    int type;
    static int tokens = 0;
    static Token token_empty = new Token(7);
    static Token token_linebeginning = createAnchor(94);
    static Token token_linebeginning2 = createAnchor(64);
    static Token token_lineend = createAnchor(36);
    static Token token_stringbeginning = createAnchor(65);
    static Token token_stringend = createAnchor(122);
    static Token token_stringend2 = createAnchor(90);
    static Token token_wordedge = createAnchor(98);
    static Token token_not_wordedge = createAnchor(66);
    static Token token_wordbeginning = createAnchor(60);
    static Token token_wordend = createAnchor(62);
    static Token token_dot = new Token(11);
    static Token token_0to9 = createRange();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class FixedStringContainer {
        Token token = null;
        int options = 0;
    }

    static {
        token_0to9.addRange(48, 57);
        token_wordchars = createRange();
        token_wordchars.addRange(48, 57);
        token_wordchars.addRange(65, 90);
        token_wordchars.addRange(95, 95);
        token_wordchars.addRange(97, 122);
        token_spaces = createRange();
        token_spaces.addRange(9, 9);
        token_spaces.addRange(10, 10);
        token_spaces.addRange(12, 12);
        token_spaces.addRange(13, 13);
        token_spaces.addRange(32, 32);
        token_not_0to9 = complementRanges(token_0to9);
        token_not_wordchars = complementRanges(token_wordchars);
        token_not_spaces = complementRanges(token_spaces);
        categories = new Hashtable();
        categories2 = new Hashtable();
        categoryNames = new String[]{"Cn", "Lu", "Ll", "Lt", "Lm", "Lo", "Mn", "Me", "Mc", "Nd", "Nl", "No", "Zs", "Zl", "Zp", "Cc", "Cf", null, "Co", "Cs", "Pd", "Ps", "Pe", "Pc", "Po", "Sm", "Sc", "Sk", "So", "Pi", "Pf", "L", "M", "N", "Z", "C", "P", "S"};
        blockNames = new String[]{"Basic Latin", "Latin-1 Supplement", "Latin Extended-A", "Latin Extended-B", "IPA Extensions", "Spacing Modifier Letters", "Combining Diacritical Marks", "Greek", "Cyrillic", "Armenian", "Hebrew", "Arabic", "Syriac", "Thaana", "Devanagari", "Bengali", "Gurmukhi", "Gujarati", "Oriya", "Tamil", "Telugu", "Kannada", "Malayalam", "Sinhala", "Thai", "Lao", "Tibetan", "Myanmar", "Georgian", "Hangul Jamo", "Ethiopic", "Cherokee", "Unified Canadian Aboriginal Syllabics", "Ogham", "Runic", "Khmer", "Mongolian", "Latin Extended Additional", "Greek Extended", "General Punctuation", "Superscripts and Subscripts", "Currency Symbols", "Combining Marks for Symbols", "Letterlike Symbols", "Number Forms", "Arrows", "Mathematical Operators", "Miscellaneous Technical", "Control Pictures", "Optical Character Recognition", "Enclosed Alphanumerics", "Box Drawing", "Block Elements", "Geometric Shapes", "Miscellaneous Symbols", "Dingbats", "Braille Patterns", "CJK Radicals Supplement", "Kangxi Radicals", "Ideographic Description Characters", "CJK Symbols and Punctuation", "Hiragana", "Katakana", "Bopomofo", "Hangul Compatibility Jamo", "Kanbun", "Bopomofo Extended", "Enclosed CJK Letters and Months", "CJK Compatibility", "CJK Unified Ideographs Extension A", "CJK Unified Ideographs", "Yi Syllables", "Yi Radicals", "Hangul Syllables", "Private Use", "CJK Compatibility Ideographs", "Alphabetic Presentation Forms", "Arabic Presentation Forms-A", "Combining Half Marks", "CJK Compatibility Forms", "Small Form Variants", "Arabic Presentation Forms-B", "Specials", "Halfwidth and Fullwidth Forms", "Old Italic", "Gothic", "Deseret", "Byzantine Musical Symbols", "Musical Symbols", "Mathematical Alphanumeric Symbols", "CJK Unified Ideographs Extension B", "CJK Compatibility Ideographs Supplement", "Tags"};
        nonBMPBlockRanges = new int[]{66304, 66351, 66352, 66383, 66560, 66639, 118784, 119039, 119040, 119295, 119808, 120831, 131072, 173782, 194560, 195103, 917504, 917631};
        nonxs = null;
        token_grapheme = null;
        token_ccs = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ParenToken createLook(int type, Token child) {
        tokens++;
        return new ParenToken(type, child, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ParenToken createParen(Token child, int pnumber) {
        tokens++;
        return new ParenToken(6, child, pnumber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClosureToken createClosure(Token tok) {
        tokens++;
        return new ClosureToken(3, tok);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClosureToken createNGClosure(Token tok) {
        tokens++;
        return new ClosureToken(9, tok);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConcatToken createConcat(Token tok1, Token tok2) {
        tokens++;
        return new ConcatToken(tok1, tok2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static UnionToken createConcat() {
        tokens++;
        return new UnionToken(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static UnionToken createUnion() {
        tokens++;
        return new UnionToken(2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Token createEmpty() {
        return token_empty;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RangeToken createRange() {
        tokens++;
        return new RangeToken(4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RangeToken createNRange() {
        tokens++;
        return new RangeToken(5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CharToken createChar(int ch) {
        tokens++;
        return new CharToken(0, ch);
    }

    private static CharToken createAnchor(int ch) {
        tokens++;
        return new CharToken(8, ch);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static StringToken createBackReference(int refno) {
        tokens++;
        return new StringToken(12, null, refno);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static StringToken createString(String str) {
        tokens++;
        return new StringToken(10, str, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ModifierToken createModifierGroup(Token child, int add, int mask) {
        tokens++;
        return new ModifierToken(child, add, mask);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConditionToken createCondition(int refno, Token condition, Token yespat, Token nopat) {
        tokens++;
        return new ConditionToken(refno, condition, yespat, nopat);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Token(int type) {
        this.type = type;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int size() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Token getChild(int index) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addChild(Token tok) {
        throw new RuntimeException("Not supported.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addRange(int start, int end) {
        throw new RuntimeException("Not supported.");
    }

    protected void sortRanges() {
        throw new RuntimeException("Not supported.");
    }

    protected void compactRanges() {
        throw new RuntimeException("Not supported.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void mergeRanges(Token tok) {
        throw new RuntimeException("Not supported.");
    }

    protected void subtractRanges(Token tok) {
        throw new RuntimeException("Not supported.");
    }

    protected void intersectRanges(Token tok) {
        throw new RuntimeException("Not supported.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Token complementRanges(Token tok) {
        return RangeToken.complementRanges(tok);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMin(int min) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMax(int max) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMin() {
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMax() {
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getReferenceNumber() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getString() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getParenNumber() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getChar() {
        return -1;
    }

    public String toString() {
        return toString(0);
    }

    public String toString(int options) {
        return this.type == 11 ? "." : "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getMinLength() {
        switch (this.type) {
            case 0:
            case 4:
            case 5:
            case 11:
                return 1;
            case 1:
                int sum = 0;
                for (int i = 0; i < size(); i++) {
                    sum += getChild(i).getMinLength();
                }
                return sum;
            case 2:
            case 26:
                if (size() == 0) {
                    return 0;
                }
                int ret = getChild(0).getMinLength();
                for (int i2 = 1; i2 < size(); i2++) {
                    int min = getChild(i2).getMinLength();
                    if (min < ret) {
                        ret = min;
                    }
                }
                return ret;
            case 3:
            case 9:
                if (getMin() >= 0) {
                    return getMin() * getChild(0).getMinLength();
                }
                return 0;
            case 6:
            case 24:
            case 25:
                return getChild(0).getMinLength();
            case 7:
            case 8:
                return 0;
            case 10:
                return getString().length();
            case 12:
                return 0;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            default:
                throw new RuntimeException("Token#getMinLength(): Invalid Type: " + this.type);
            case 20:
            case 21:
            case 22:
            case 23:
                return 0;
        }
    }

    final int getMaxLength() {
        switch (this.type) {
            case 0:
                return 1;
            case 1:
                int sum = 0;
                for (int i = 0; i < size(); i++) {
                    int d = getChild(i).getMaxLength();
                    if (d < 0) {
                        return -1;
                    }
                    sum += d;
                }
                return sum;
            case 2:
            case 26:
                if (size() == 0) {
                    return 0;
                }
                int ret = getChild(0).getMaxLength();
                for (int i2 = 1; ret >= 0 && i2 < size(); i2++) {
                    int max = getChild(i2).getMaxLength();
                    if (max < 0) {
                        return -1;
                    }
                    if (max > ret) {
                        ret = max;
                    }
                }
                return ret;
            case 3:
            case 9:
                if (getMax() >= 0) {
                    return getMax() * getChild(0).getMaxLength();
                }
                return -1;
            case 4:
            case 5:
            case 11:
                return 2;
            case 6:
            case 24:
            case 25:
                return getChild(0).getMaxLength();
            case 7:
            case 8:
                return 0;
            case 10:
                return getString().length();
            case 12:
                return -1;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            default:
                throw new RuntimeException("Token#getMaxLength(): Invalid Type: " + this.type);
            case 20:
            case 21:
            case 22:
            case 23:
                return 0;
        }
    }

    private static final boolean isSet(int options, int flag) {
        return (options & flag) == flag;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int analyzeFirstCharacter(RangeToken result, int options) {
        switch (this.type) {
            case 0:
                int ch = getChar();
                result.addRange(ch, ch);
                if (ch < 65536 && isSet(options, 2)) {
                    int ch2 = Character.toUpperCase((char) ch);
                    result.addRange(ch2, ch2);
                    int ch3 = Character.toLowerCase((char) ch2);
                    result.addRange(ch3, ch3);
                }
                return 1;
            case 1:
                int ret = 0;
                for (int i = 0; i < size(); i++) {
                    int analyzeFirstCharacter = getChild(i).analyzeFirstCharacter(result, options);
                    ret = analyzeFirstCharacter;
                    if (analyzeFirstCharacter != 0) {
                        return ret;
                    }
                }
                return ret;
            case 2:
                if (size() == 0) {
                    return 0;
                }
                int ret2 = 0;
                boolean hasEmpty = false;
                for (int i2 = 0; i2 < size() && (ret2 = getChild(i2).analyzeFirstCharacter(result, options)) != 2; i2++) {
                    if (ret2 == 0) {
                        hasEmpty = true;
                    }
                }
                if (hasEmpty) {
                    return 0;
                }
                return ret2;
            case 3:
            case 9:
                getChild(0).analyzeFirstCharacter(result, options);
                return 0;
            case 4:
                if (isSet(options, 2)) {
                    result.mergeRanges(((RangeToken) this).getCaseInsensitiveToken());
                } else {
                    result.mergeRanges(this);
                }
                return 1;
            case 5:
                if (isSet(options, 2)) {
                    result.mergeRanges(complementRanges(((RangeToken) this).getCaseInsensitiveToken()));
                } else {
                    result.mergeRanges(complementRanges(this));
                }
                return 1;
            case 6:
            case 24:
                return getChild(0).analyzeFirstCharacter(result, options);
            case 7:
            case 8:
                return 0;
            case 10:
                int cha = getString().charAt(0);
                if (REUtil.isHighSurrogate(cha) && getString().length() >= 2) {
                    int ch22 = getString().charAt(1);
                    if (REUtil.isLowSurrogate(ch22)) {
                        cha = REUtil.composeFromSurrogates(cha, ch22);
                    }
                }
                result.addRange(cha, cha);
                if (cha < 65536 && isSet(options, 2)) {
                    int cha2 = Character.toUpperCase((char) cha);
                    result.addRange(cha2, cha2);
                    int cha3 = Character.toLowerCase((char) cha2);
                    result.addRange(cha3, cha3);
                }
                return 1;
            case 11:
                return isSet(options, 4) ? 0 : 0;
            case 12:
                result.addRange(0, UTF16_MAX);
                return 2;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            default:
                throw new RuntimeException("Token#analyzeHeadCharacter(): Invalid Type: " + this.type);
            case 20:
            case 21:
            case 22:
            case 23:
                return 0;
            case 25:
                return getChild(0).analyzeFirstCharacter(result, (options | ((ModifierToken) this).getOptions()) & (~((ModifierToken) this).getOptionsMask()));
            case 26:
                int ret3 = getChild(0).analyzeFirstCharacter(result, options);
                if (size() == 1) {
                    return 0;
                }
                if (ret3 == 2) {
                    return ret3;
                }
                int ret4 = getChild(1).analyzeFirstCharacter(result, options);
                return ret4 == 2 ? ret4 : (ret3 == 0 || ret4 == 0) ? 0 : 1;
        }
    }

    private final boolean isShorterThan(Token tok) {
        if (tok == null) {
            return false;
        }
        if (this.type != 10) {
            throw new RuntimeException("Internal Error: Illegal type: " + this.type);
        }
        int mylength = getString().length();
        if (tok.type != 10) {
            throw new RuntimeException("Internal Error: Illegal type: " + tok.type);
        }
        int otherlength = tok.getString().length();
        return mylength < otherlength;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void findFixedString(FixedStringContainer container, int options) {
        switch (this.type) {
            case 0:
                container.token = null;
                return;
            case 1:
                Token prevToken = null;
                int prevOptions = 0;
                for (int i = 0; i < size(); i++) {
                    getChild(i).findFixedString(container, options);
                    if (prevToken == null || prevToken.isShorterThan(container.token)) {
                        prevToken = container.token;
                        prevOptions = container.options;
                    }
                }
                container.token = prevToken;
                container.options = prevOptions;
                return;
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
            case 9:
            case 11:
            case 12:
            case 20:
            case 21:
            case 22:
            case 23:
            case 26:
                container.token = null;
                return;
            case 6:
            case 24:
                getChild(0).findFixedString(container, options);
                return;
            case 10:
                container.token = this;
                container.options = options;
                return;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            default:
                throw new RuntimeException("Token#findFixedString(): Invalid Type: " + this.type);
            case 25:
                getChild(0).findFixedString(container, (options | ((ModifierToken) this).getOptions()) & (~((ModifierToken) this).getOptionsMask()));
                return;
        }
    }

    boolean match(int ch) {
        throw new RuntimeException("NFAArrow#match(): Internal error: " + this.type);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static RangeToken getRange(String name, boolean positive) {
        int type;
        if (categories.isEmpty()) {
            synchronized (categories) {
                Token[] ranges = new Token[categoryNames.length];
                for (int i = 0; i < ranges.length; i++) {
                    ranges[i] = createRange();
                }
                for (int i2 = 0; i2 < 65536; i2++) {
                    int type2 = Character.getType((char) i2);
                    if (type2 == 21 || type2 == 22) {
                        if (i2 == 171 || i2 == 8216 || i2 == 8219 || i2 == 8220 || i2 == 8223 || i2 == 8249) {
                            type2 = 29;
                        }
                        if (i2 == 187 || i2 == 8217 || i2 == 8221 || i2 == 8250) {
                            type2 = 30;
                        }
                    }
                    ranges[type2].addRange(i2, i2);
                    switch (type2) {
                        case 0:
                        case 15:
                        case 16:
                        case 18:
                        case 19:
                            type = 35;
                            break;
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            type = 31;
                            break;
                        case 6:
                        case 7:
                        case 8:
                            type = 32;
                            break;
                        case 9:
                        case 10:
                        case 11:
                            type = 33;
                            break;
                        case 12:
                        case 13:
                        case 14:
                            type = 34;
                            break;
                        case 17:
                        default:
                            throw new RuntimeException("org.apache.xerces.utils.regex.Token#getRange(): Unknown Unicode category: " + type2);
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 29:
                        case 30:
                            type = 36;
                            break;
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                            type = 37;
                            break;
                    }
                    ranges[type].addRange(i2, i2);
                }
                ranges[0].addRange(65536, UTF16_MAX);
                for (int i3 = 0; i3 < ranges.length; i3++) {
                    if (categoryNames[i3] != null) {
                        if (i3 == 0) {
                            ranges[i3].addRange(65536, UTF16_MAX);
                        }
                        categories.put(categoryNames[i3], ranges[i3]);
                        categories2.put(categoryNames[i3], complementRanges(ranges[i3]));
                    }
                }
                StringBuilder buffer = new StringBuilder(50);
                for (int i4 = 0; i4 < blockNames.length; i4++) {
                    RangeToken createRange = createRange();
                    if (i4 < 84) {
                        int location = i4 * 2;
                        int rstart = blockRanges.charAt(location);
                        int rend = blockRanges.charAt(location + 1);
                        createRange.addRange(rstart, rend);
                    } else {
                        int location2 = 2 * (i4 - 84);
                        createRange.addRange(nonBMPBlockRanges[location2], nonBMPBlockRanges[location2 + 1]);
                    }
                    String n = blockNames[i4];
                    if (n.equals("Specials")) {
                        createRange.addRange(65520, 65533);
                    }
                    if (n.equals("Private Use")) {
                        createRange.addRange(983040, 1048573);
                        createRange.addRange(1048576, 1114109);
                    }
                    categories.put(n, createRange);
                    categories2.put(n, complementRanges(createRange));
                    buffer.setLength(0);
                    buffer.append("Is");
                    if (n.indexOf(32) >= 0) {
                        for (int ci = 0; ci < n.length(); ci++) {
                            if (n.charAt(ci) != ' ') {
                                buffer.append(n.charAt(ci));
                            }
                        }
                    } else {
                        buffer.append(n);
                    }
                    setAlias(buffer.toString(), n, true);
                }
                setAlias("ASSIGNED", "Cn", false);
                setAlias("UNASSIGNED", "Cn", true);
                RangeToken createRange2 = createRange();
                createRange2.addRange(0, UTF16_MAX);
                categories.put(Rule.ALL, createRange2);
                categories2.put(Rule.ALL, complementRanges(createRange2));
                registerNonXS("ASSIGNED");
                registerNonXS("UNASSIGNED");
                registerNonXS(Rule.ALL);
                RangeToken createRange3 = createRange();
                createRange3.mergeRanges(ranges[1]);
                createRange3.mergeRanges(ranges[2]);
                createRange3.mergeRanges(ranges[5]);
                categories.put("IsAlpha", createRange3);
                categories2.put("IsAlpha", complementRanges(createRange3));
                registerNonXS("IsAlpha");
                RangeToken createRange4 = createRange();
                createRange4.mergeRanges(createRange3);
                createRange4.mergeRanges(ranges[9]);
                categories.put("IsAlnum", createRange4);
                categories2.put("IsAlnum", complementRanges(createRange4));
                registerNonXS("IsAlnum");
                RangeToken createRange5 = createRange();
                createRange5.mergeRanges(token_spaces);
                createRange5.mergeRanges(ranges[34]);
                categories.put("IsSpace", createRange5);
                categories2.put("IsSpace", complementRanges(createRange5));
                registerNonXS("IsSpace");
                RangeToken createRange6 = createRange();
                createRange6.mergeRanges(createRange4);
                createRange6.addRange(95, 95);
                categories.put("IsWord", createRange6);
                categories2.put("IsWord", complementRanges(createRange6));
                registerNonXS("IsWord");
                RangeToken createRange7 = createRange();
                createRange7.addRange(0, 127);
                categories.put("IsASCII", createRange7);
                categories2.put("IsASCII", complementRanges(createRange7));
                registerNonXS("IsASCII");
                RangeToken createRange8 = createRange();
                createRange8.mergeRanges(ranges[35]);
                createRange8.addRange(32, 32);
                categories.put("IsGraph", complementRanges(createRange8));
                categories2.put("IsGraph", createRange8);
                registerNonXS("IsGraph");
                RangeToken createRange9 = createRange();
                createRange9.addRange(48, 57);
                createRange9.addRange(65, 70);
                createRange9.addRange(97, 102);
                categories.put("IsXDigit", complementRanges(createRange9));
                categories2.put("IsXDigit", createRange9);
                registerNonXS("IsXDigit");
                setAlias("IsDigit", "Nd", true);
                setAlias("IsUpper", "Lu", true);
                setAlias("IsLower", "Ll", true);
                setAlias("IsCntrl", "C", true);
                setAlias("IsPrint", "C", false);
                setAlias("IsPunct", "P", true);
                registerNonXS("IsDigit");
                registerNonXS("IsUpper");
                registerNonXS("IsLower");
                registerNonXS("IsCntrl");
                registerNonXS("IsPrint");
                registerNonXS("IsPunct");
                setAlias("alpha", "IsAlpha", true);
                setAlias("alnum", "IsAlnum", true);
                setAlias("ascii", "IsASCII", true);
                setAlias("cntrl", "IsCntrl", true);
                setAlias("digit", "IsDigit", true);
                setAlias("graph", "IsGraph", true);
                setAlias("lower", "IsLower", true);
                setAlias("print", "IsPrint", true);
                setAlias("punct", "IsPunct", true);
                setAlias("space", "IsSpace", true);
                setAlias("upper", "IsUpper", true);
                setAlias("word", "IsWord", true);
                setAlias("xdigit", "IsXDigit", true);
                registerNonXS("alpha");
                registerNonXS("alnum");
                registerNonXS("ascii");
                registerNonXS("cntrl");
                registerNonXS("digit");
                registerNonXS("graph");
                registerNonXS("lower");
                registerNonXS("print");
                registerNonXS("punct");
                registerNonXS("space");
                registerNonXS("upper");
                registerNonXS("word");
                registerNonXS("xdigit");
            }
        }
        if (positive) {
            RangeToken tok = (RangeToken) categories.get(name);
            return tok;
        }
        RangeToken tok2 = (RangeToken) categories2.get(name);
        return tok2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static RangeToken getRange(String name, boolean positive, boolean xs) {
        RangeToken range = getRange(name, positive);
        if (xs && range != null && isRegisterNonXS(name)) {
            return null;
        }
        return range;
    }

    protected static void registerNonXS(String name) {
        if (nonxs == null) {
            nonxs = new Hashtable();
        }
        nonxs.put(name, name);
    }

    protected static boolean isRegisterNonXS(String name) {
        if (nonxs == null) {
            return false;
        }
        return nonxs.containsKey(name);
    }

    private static void setAlias(String newName, String name, boolean positive) {
        Token t1 = (Token) categories.get(name);
        Token t2 = (Token) categories2.get(name);
        if (positive) {
            categories.put(newName, t1);
            categories2.put(newName, t2);
        } else {
            categories2.put(newName, t1);
            categories.put(newName, t2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized Token getGraphemePattern() {
        synchronized (Token.class) {
            if (token_grapheme != null) {
                return token_grapheme;
            }
            Token base_char = createRange();
            base_char.mergeRanges(getRange("ASSIGNED", true));
            base_char.subtractRanges(getRange("M", true));
            base_char.subtractRanges(getRange("C", true));
            Token virama = createRange();
            for (int i = 0; i < viramaString.length(); i++) {
                viramaString.charAt(i);
                virama.addRange(i, i);
            }
            Token combiner_wo_virama = createRange();
            combiner_wo_virama.mergeRanges(getRange("M", true));
            combiner_wo_virama.addRange(4448, 4607);
            combiner_wo_virama.addRange(65438, 65439);
            Token left = createUnion();
            left.addChild(base_char);
            left.addChild(token_empty);
            Token foo = createUnion();
            foo.addChild(createConcat(virama, getRange("L", true)));
            foo.addChild(combiner_wo_virama);
            token_grapheme = createConcat(left, createClosure(foo));
            return token_grapheme;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized Token getCombiningCharacterSequence() {
        synchronized (Token.class) {
            if (token_ccs != null) {
                return token_ccs;
            }
            Token foo = createClosure(getRange("M", true));
            token_ccs = createConcat(getRange("M", false), foo);
            return token_ccs;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class StringToken extends Token implements Serializable {
        int refNumber;
        String string;

        StringToken(int type, String str, int n) {
            super(type);
            this.string = str;
            this.refNumber = n;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        int getReferenceNumber() {
            return this.refNumber;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        String getString() {
            return this.string;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        public String toString(int options) {
            if (this.type == 12) {
                return "\\" + this.refNumber;
            }
            return REUtil.quoteMeta(this.string);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ConcatToken extends Token implements Serializable {
        Token child;
        Token child2;

        ConcatToken(Token t1, Token t2) {
            super(1);
            this.child = t1;
            this.child2 = t2;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        int size() {
            return 2;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        Token getChild(int index) {
            return index == 0 ? this.child : this.child2;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        public String toString(int options) {
            if (this.child2.type == 3 && this.child2.getChild(0) == this.child) {
                String ret = this.child.toString(options) + "+";
                return ret;
            }
            if (this.child2.type == 9 && this.child2.getChild(0) == this.child) {
                String ret2 = this.child.toString(options) + "+?";
                return ret2;
            }
            String ret3 = this.child.toString(options) + this.child2.toString(options);
            return ret3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class CharToken extends Token implements Serializable {
        int chardata;

        CharToken(int type, int ch) {
            super(type);
            this.chardata = ch;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        int getChar() {
            return this.chardata;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        public String toString(int options) {
            switch (this.type) {
                case 0:
                    switch (this.chardata) {
                        case 9:
                            return "\\t";
                        case 10:
                            return "\\n";
                        case 12:
                            return "\\f";
                        case 13:
                            return "\\r";
                        case 27:
                            return "\\e";
                        case 40:
                        case 41:
                        case 42:
                        case 43:
                        case 46:
                        case 63:
                        case 91:
                        case 92:
                        case 123:
                        case 124:
                            String ret = "\\" + ((char) this.chardata);
                            return ret;
                        default:
                            if (this.chardata >= 65536) {
                                String pre = "0" + Integer.toHexString(this.chardata);
                                return "\\v" + pre.substring(pre.length() - 6, pre.length());
                            }
                            String ret2 = "" + ((char) this.chardata);
                            return ret2;
                    }
                case 8:
                    if (this == Token.token_linebeginning || this == Token.token_lineend) {
                        String ret3 = "" + ((char) this.chardata);
                        return ret3;
                    }
                    String ret4 = "\\" + ((char) this.chardata);
                    return ret4;
                default:
                    return null;
            }
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        boolean match(int ch) {
            if (this.type == 0) {
                return ch == this.chardata;
            }
            throw new RuntimeException("NFAArrow#match(): Internal error: " + this.type);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ClosureToken extends Token implements Serializable {
        Token child;
        int max;
        int min;

        ClosureToken(int type, Token tok) {
            super(type);
            this.child = tok;
            setMin(-1);
            setMax(-1);
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        int size() {
            return 1;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        Token getChild(int index) {
            return this.child;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        final void setMin(int min) {
            this.min = min;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        final void setMax(int max) {
            this.max = max;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        final int getMin() {
            return this.min;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        final int getMax() {
            return this.max;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        public String toString(int options) {
            if (this.type == 3) {
                if (getMin() < 0 && getMax() < 0) {
                    String ret = this.child.toString(options) + "*";
                    return ret;
                }
                if (getMin() == getMax()) {
                    String ret2 = this.child.toString(options) + VectorFormat.DEFAULT_PREFIX + getMin() + VectorFormat.DEFAULT_SUFFIX;
                    return ret2;
                }
                if (getMin() >= 0 && getMax() >= 0) {
                    String ret3 = this.child.toString(options) + VectorFormat.DEFAULT_PREFIX + getMin() + CollectionUtils.COMMA + getMax() + VectorFormat.DEFAULT_SUFFIX;
                    return ret3;
                }
                if (getMin() >= 0 && getMax() < 0) {
                    String ret4 = this.child.toString(options) + VectorFormat.DEFAULT_PREFIX + getMin() + ",}";
                    return ret4;
                }
                throw new RuntimeException("Token#toString(): CLOSURE " + getMin() + ", " + getMax());
            }
            if (getMin() < 0 && getMax() < 0) {
                String ret5 = this.child.toString(options) + "*?";
                return ret5;
            }
            if (getMin() == getMax()) {
                String ret6 = this.child.toString(options) + VectorFormat.DEFAULT_PREFIX + getMin() + "}?";
                return ret6;
            }
            if (getMin() >= 0 && getMax() >= 0) {
                String ret7 = this.child.toString(options) + VectorFormat.DEFAULT_PREFIX + getMin() + CollectionUtils.COMMA + getMax() + "}?";
                return ret7;
            }
            if (getMin() >= 0 && getMax() < 0) {
                String ret8 = this.child.toString(options) + VectorFormat.DEFAULT_PREFIX + getMin() + ",}?";
                return ret8;
            }
            throw new RuntimeException("Token#toString(): NONGREEDYCLOSURE " + getMin() + ", " + getMax());
        }
    }

    /* loaded from: classes11.dex */
    static class ParenToken extends Token implements Serializable {
        Token child;
        int parennumber;

        ParenToken(int type, Token tok, int paren) {
            super(type);
            this.child = tok;
            this.parennumber = paren;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        int size() {
            return 1;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        Token getChild(int index) {
            return this.child;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        int getParenNumber() {
            return this.parennumber;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        public String toString(int options) {
            switch (this.type) {
                case 6:
                    if (this.parennumber == 0) {
                        String ret = "(?:" + this.child.toString(options) + ")";
                        return ret;
                    }
                    String ret2 = "(" + this.child.toString(options) + ")";
                    return ret2;
                case 20:
                    String ret3 = "(?=" + this.child.toString(options) + ")";
                    return ret3;
                case 21:
                    String ret4 = "(?!" + this.child.toString(options) + ")";
                    return ret4;
                case 22:
                    String ret5 = "(?<=" + this.child.toString(options) + ")";
                    return ret5;
                case 23:
                    String ret6 = "(?<!" + this.child.toString(options) + ")";
                    return ret6;
                case 24:
                    String ret7 = "(?>" + this.child.toString(options) + ")";
                    return ret7;
                default:
                    return null;
            }
        }
    }

    /* loaded from: classes11.dex */
    static class ConditionToken extends Token implements Serializable {
        Token condition;
        Token no;
        int refNumber;
        Token yes;

        ConditionToken(int refno, Token cond, Token yespat, Token nopat) {
            super(26);
            this.refNumber = refno;
            this.condition = cond;
            this.yes = yespat;
            this.no = nopat;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        int size() {
            return this.no == null ? 1 : 2;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        Token getChild(int index) {
            if (index == 0) {
                return this.yes;
            }
            if (index == 1) {
                return this.no;
            }
            throw new RuntimeException("Internal Error: " + index);
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        public String toString(int options) {
            String ret;
            if (this.refNumber > 0) {
                ret = "(?(" + this.refNumber + ")";
            } else if (this.condition.type == 8) {
                ret = "(?(" + this.condition + ")";
            } else {
                ret = "(?" + this.condition;
            }
            if (this.no == null) {
                return ret + this.yes + ")";
            }
            return ret + this.yes + "|" + this.no + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ModifierToken extends Token implements Serializable {
        int add;
        Token child;
        int mask;

        ModifierToken(Token tok, int add, int mask) {
            super(25);
            this.child = tok;
            this.add = add;
            this.mask = mask;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        int size() {
            return 1;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        Token getChild(int index) {
            return this.child;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int getOptions() {
            return this.add;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int getOptionsMask() {
            return this.mask;
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        public String toString(int options) {
            return "(?" + (this.add == 0 ? "" : REUtil.createOptionString(this.add)) + (this.mask != 0 ? REUtil.createOptionString(this.mask) : "") + ":" + this.child.toString(options) + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class UnionToken extends Token implements Serializable {
        Vector children;

        UnionToken(int type) {
            super(type);
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        void addChild(Token tok) {
            StringBuilder buffer;
            if (tok == null) {
                return;
            }
            if (this.children == null) {
                this.children = new Vector();
            }
            if (this.type == 2) {
                this.children.addElement(tok);
                return;
            }
            if (tok.type == 1) {
                for (int i = 0; i < tok.size(); i++) {
                    addChild(tok.getChild(i));
                }
                return;
            }
            int size = this.children.size();
            if (size == 0) {
                this.children.addElement(tok);
                return;
            }
            Token previous = (Token) this.children.elementAt(size - 1);
            if ((previous.type != 0 && previous.type != 10) || (tok.type != 0 && tok.type != 10)) {
                this.children.addElement(tok);
                return;
            }
            int nextMaxLength = tok.type != 0 ? tok.getString().length() : 2;
            if (previous.type == 0) {
                buffer = new StringBuilder(nextMaxLength + 2);
                int ch = previous.getChar();
                if (ch >= 65536) {
                    buffer.append(REUtil.decomposeToSurrogates(ch));
                } else {
                    buffer.append((char) ch);
                }
                previous = Token.createString(null);
                this.children.setElementAt(previous, size - 1);
            } else {
                buffer = new StringBuilder(previous.getString().length() + nextMaxLength);
                buffer.append(previous.getString());
            }
            if (tok.type == 0) {
                int ch2 = tok.getChar();
                if (ch2 >= 65536) {
                    buffer.append(REUtil.decomposeToSurrogates(ch2));
                } else {
                    buffer.append((char) ch2);
                }
            } else {
                buffer.append(tok.getString());
            }
            ((StringToken) previous).string = new String(buffer);
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        int size() {
            if (this.children == null) {
                return 0;
            }
            return this.children.size();
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        Token getChild(int index) {
            return (Token) this.children.elementAt(index);
        }

        @Override // org.apache.xmlbeans.impl.regex.Token
        public String toString(int options) {
            if (this.type == 1) {
                if (this.children.size() == 2) {
                    Token ch = getChild(0);
                    Token ch2 = getChild(1);
                    if (ch2.type == 3 && ch2.getChild(0) == ch) {
                        String ret = ch.toString(options) + "+";
                        return ret;
                    }
                    if (ch2.type == 9 && ch2.getChild(0) == ch) {
                        String ret2 = ch.toString(options) + "+?";
                        return ret2;
                    }
                    String ret3 = ch.toString(options) + ch2.toString(options);
                    return ret3;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < this.children.size(); i++) {
                    sb.append(((Token) this.children.elementAt(i)).toString(options));
                }
                String ret4 = new String(sb);
                return ret4;
            }
            if (this.children.size() == 2 && getChild(1).type == 7) {
                String ret5 = getChild(0).toString(options) + "?";
                return ret5;
            }
            if (this.children.size() == 2 && getChild(0).type == 7) {
                String ret6 = getChild(1).toString(options) + "??";
                return ret6;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(((Token) this.children.elementAt(0)).toString(options));
            for (int i2 = 1; i2 < this.children.size(); i2++) {
                sb2.append('|');
                sb2.append(((Token) this.children.elementAt(i2)).toString(options));
            }
            String ret7 = new String(sb2);
            return ret7;
        }
    }
}
