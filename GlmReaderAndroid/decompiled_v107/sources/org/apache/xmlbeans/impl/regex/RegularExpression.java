package org.apache.xmlbeans.impl.regex;

import java.io.Serializable;
import java.text.CharacterIterator;
import java.util.Locale;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.impl.regex.Op;
import org.apache.xmlbeans.impl.regex.Token;

/* loaded from: classes11.dex */
public class RegularExpression implements Serializable {
    static final int CARRIAGE_RETURN = 13;
    static final boolean DEBUG = false;
    static final int EXTENDED_COMMENT = 16;
    static final int IGNORE_CASE = 2;
    static final int LINE_FEED = 10;
    static final int LINE_SEPARATOR = 8232;
    static final int MULTIPLE_LINES = 8;
    static final int PARAGRAPH_SEPARATOR = 8233;
    static final int PROHIBIT_FIXED_STRING_OPTIMIZATION = 256;
    static final int PROHIBIT_HEAD_CHARACTER_OPTIMIZATION = 128;
    static final int SINGLE_LINE = 4;
    static final int SPECIAL_COMMA = 1024;
    static final int UNICODE_WORD_BOUNDARY = 64;
    static final int USE_UNICODE_CATEGORY = 32;
    private static final int WT_IGNORE = 0;
    private static final int WT_LETTER = 1;
    private static final int WT_OTHER = 2;
    static final int XMLSCHEMA_MODE = 512;
    private static final long serialVersionUID = 6242499334195006401L;
    transient Context context;
    transient RangeToken firstChar;
    transient String fixedString;
    transient boolean fixedStringOnly;
    transient int fixedStringOptions;
    transient BMPattern fixedStringTable;
    boolean hasBackReferences;
    transient int minlength;
    int nofparen;
    transient int numberOfClosures;
    transient Op operations;
    int options;
    String regex;
    Token tokentree;

    private synchronized void compile(Token tok) {
        if (this.operations != null) {
            return;
        }
        this.numberOfClosures = 0;
        this.operations = compile(tok, null, false);
    }

    private Op compile(Token tok, Op next, boolean reverse) {
        Op.ChildOp op;
        Op ret;
        switch (tok.type) {
            case 0:
                Op ret2 = Op.createChar(tok.getChar());
                ret2.next = next;
                return ret2;
            case 1:
                if (!reverse) {
                    Op ret3 = next;
                    for (int i = tok.size() - 1; i >= 0; i--) {
                        ret3 = compile(tok.getChild(i), ret3, false);
                    }
                    return ret3;
                }
                Op ret4 = next;
                for (int i2 = 0; i2 < tok.size(); i2++) {
                    ret4 = compile(tok.getChild(i2), ret4, true);
                }
                return ret4;
            case 2:
                Op.UnionOp uni = Op.createUnion(tok.size());
                for (int i3 = 0; i3 < tok.size(); i3++) {
                    uni.addElement(compile(tok.getChild(i3), next, reverse));
                }
                return uni;
            case 3:
            case 9:
                Token child = tok.getChild(0);
                int min = tok.getMin();
                int max = tok.getMax();
                if (min >= 0 && min == max) {
                    Op ret5 = next;
                    for (int i4 = 0; i4 < min; i4++) {
                        ret5 = compile(child, ret5, reverse);
                    }
                    return ret5;
                }
                if (min > 0 && max > 0) {
                    max -= min;
                }
                if (max <= 0) {
                    if (tok.type == 9) {
                        op = Op.createNonGreedyClosure();
                    } else {
                        int i5 = this.numberOfClosures;
                        this.numberOfClosures = i5 + 1;
                        op = Op.createClosure(i5);
                    }
                    op.next = next;
                    op.setChild(compile(child, op, reverse));
                    ret = op;
                } else {
                    Op ret6 = next;
                    for (int i6 = 0; i6 < max; i6++) {
                        Op.ChildOp q = Op.createQuestion(tok.type == 9);
                        q.next = next;
                        q.setChild(compile(child, ret6, reverse));
                        ret6 = q;
                    }
                    ret = ret6;
                }
                if (min > 0) {
                    for (int i7 = 0; i7 < min; i7++) {
                        ret = compile(child, ret, reverse);
                    }
                    return ret;
                }
                return ret;
            case 4:
            case 5:
                Op ret7 = Op.createRange(tok);
                ret7.next = next;
                return ret7;
            case 6:
                if (tok.getParenNumber() == 0) {
                    return compile(tok.getChild(0), next, reverse);
                }
                if (reverse) {
                    return Op.createCapture(-tok.getParenNumber(), compile(tok.getChild(0), Op.createCapture(tok.getParenNumber(), next), reverse));
                }
                return Op.createCapture(tok.getParenNumber(), compile(tok.getChild(0), Op.createCapture(-tok.getParenNumber(), next), reverse));
            case 7:
                return next;
            case 8:
                Op ret8 = Op.createAnchor(tok.getChar());
                ret8.next = next;
                return ret8;
            case 10:
                Op ret9 = Op.createString(tok.getString());
                ret9.next = next;
                return ret9;
            case 11:
                Op ret10 = Op.createDot();
                ret10.next = next;
                return ret10;
            case 12:
                Op ret11 = Op.createBackReference(tok.getReferenceNumber());
                ret11.next = next;
                return ret11;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            default:
                throw new RuntimeException("Unknown token type: " + tok.type);
            case 20:
                return Op.createLook(20, next, compile(tok.getChild(0), null, false));
            case 21:
                return Op.createLook(21, next, compile(tok.getChild(0), null, false));
            case 22:
                return Op.createLook(22, next, compile(tok.getChild(0), null, true));
            case 23:
                return Op.createLook(23, next, compile(tok.getChild(0), null, true));
            case 24:
                return Op.createIndependent(next, compile(tok.getChild(0), null, reverse));
            case 25:
                return Op.createModifier(next, compile(tok.getChild(0), null, reverse), ((Token.ModifierToken) tok).getOptions(), ((Token.ModifierToken) tok).getOptionsMask());
            case 26:
                Token.ConditionToken ctok = (Token.ConditionToken) tok;
                int ref = ctok.refNumber;
                Op condition = ctok.condition == null ? null : compile(ctok.condition, null, reverse);
                Op yes = compile(ctok.yes, next, reverse);
                Op no = ctok.no != null ? compile(ctok.no, next, reverse) : null;
                return Op.createCondition(next, ref, condition, yes, no);
        }
    }

    public boolean matches(char[] target) {
        return matches(target, 0, target.length, (Match) null);
    }

    public boolean matches(char[] target, int start, int end) {
        return matches(target, start, end, (Match) null);
    }

    public boolean matches(char[] target, Match match) {
        return matches(target, 0, target.length, match);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean matches(char[] r18, int r19, int r20, org.apache.xmlbeans.impl.regex.Match r21) {
        /*
            Method dump skipped, instructions count: 446
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.RegularExpression.matches(char[], int, int, org.apache.xmlbeans.impl.regex.Match):boolean");
    }

    public boolean matches(String target) {
        return matches(target, 0, target.length(), (Match) null);
    }

    public boolean matches(String target, int start, int end) {
        return matches(target, start, end, (Match) null);
    }

    public boolean matches(String target, Match match) {
        return matches(target, 0, target.length(), match);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean matches(java.lang.String r18, int r19, int r20, org.apache.xmlbeans.impl.regex.Match r21) {
        /*
            Method dump skipped, instructions count: 452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.RegularExpression.matches(java.lang.String, int, int, org.apache.xmlbeans.impl.regex.Match):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:158:0x0197, code lost:
    
        throw new java.lang.RuntimeException("Internal Error: Reference number must be more than zero: " + r15);
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:101:0x003d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:17:0x03a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int match(org.apache.xmlbeans.impl.regex.RegularExpression.Context r19, org.apache.xmlbeans.impl.regex.Op r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instructions count: 1194
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.RegularExpression.match(org.apache.xmlbeans.impl.regex.RegularExpression$Context, org.apache.xmlbeans.impl.regex.Op, int, int, int):int");
    }

    private boolean matchChar(int ch, int other, boolean ignoreCase) {
        return ignoreCase ? matchIgnoreCase(ch, other) : ch == other;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:107:0x013e A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean matchAnchor(org.apache.xmlbeans.impl.regex.RegularExpression.ExpressionTarget r9, org.apache.xmlbeans.impl.regex.Op r10, org.apache.xmlbeans.impl.regex.RegularExpression.Context r11, int r12, int r13) {
        /*
            Method dump skipped, instructions count: 362
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.RegularExpression.matchAnchor(org.apache.xmlbeans.impl.regex.RegularExpression$ExpressionTarget, org.apache.xmlbeans.impl.regex.Op, org.apache.xmlbeans.impl.regex.RegularExpression$Context, int, int):boolean");
    }

    private static final int getPreviousWordType(ExpressionTarget target, int begin, int end, int offset, int opts) {
        int offset2 = offset - 1;
        int ret = getWordType(target, begin, end, offset2, opts);
        while (ret == 0) {
            offset2--;
            ret = getWordType(target, begin, end, offset2, opts);
        }
        return ret;
    }

    private static final int getWordType(ExpressionTarget target, int begin, int end, int offset, int opts) {
        if (offset < begin || offset >= end) {
            return 2;
        }
        return getWordType0(target.charAt(offset), opts);
    }

    public boolean matches(CharacterIterator target) {
        return matches(target, (Match) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean matches(java.text.CharacterIterator r18, org.apache.xmlbeans.impl.regex.Match r19) {
        /*
            Method dump skipped, instructions count: 438
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.RegularExpression.matches(java.text.CharacterIterator, org.apache.xmlbeans.impl.regex.Match):boolean");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static abstract class ExpressionTarget {
        abstract char charAt(int i);

        abstract boolean regionMatches(boolean z, int i, int i2, int i3, int i4);

        abstract boolean regionMatches(boolean z, int i, int i2, String str, int i3);

        ExpressionTarget() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class StringTarget extends ExpressionTarget {
        private String target;

        StringTarget(String target) {
            this.target = target;
        }

        void resetTarget(String target) {
            this.target = target;
        }

        @Override // org.apache.xmlbeans.impl.regex.RegularExpression.ExpressionTarget
        char charAt(int index) {
            return this.target.charAt(index);
        }

        @Override // org.apache.xmlbeans.impl.regex.RegularExpression.ExpressionTarget
        boolean regionMatches(boolean ignoreCase, int offset, int limit, String part, int partlen) {
            if (limit - offset < partlen) {
                return false;
            }
            return ignoreCase ? this.target.regionMatches(true, offset, part, 0, partlen) : this.target.regionMatches(offset, part, 0, partlen);
        }

        @Override // org.apache.xmlbeans.impl.regex.RegularExpression.ExpressionTarget
        boolean regionMatches(boolean ignoreCase, int offset, int limit, int offset2, int partlen) {
            if (limit - offset < partlen) {
                return false;
            }
            return ignoreCase ? this.target.regionMatches(true, offset, this.target, offset2, partlen) : this.target.regionMatches(offset, this.target, offset2, partlen);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class CharArrayTarget extends ExpressionTarget {
        char[] target;

        CharArrayTarget(char[] target) {
            this.target = target;
        }

        void resetTarget(char[] target) {
            this.target = target;
        }

        @Override // org.apache.xmlbeans.impl.regex.RegularExpression.ExpressionTarget
        char charAt(int index) {
            return this.target[index];
        }

        @Override // org.apache.xmlbeans.impl.regex.RegularExpression.ExpressionTarget
        boolean regionMatches(boolean ignoreCase, int offset, int limit, String part, int partlen) {
            if (offset < 0 || limit - offset < partlen) {
                return false;
            }
            return ignoreCase ? regionMatchesIgnoreCase(offset, limit, part, partlen) : regionMatches(offset, limit, part, partlen);
        }

        private boolean regionMatches(int offset, int limit, String part, int i) {
            int i2 = 0;
            while (true) {
                int partlen = i - 1;
                if (i > 0) {
                    int offset2 = offset + 1;
                    int i3 = i2 + 1;
                    if (this.target[offset] == part.charAt(i2)) {
                        i2 = i3;
                        i = partlen;
                        offset = offset2;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }
        }

        private boolean regionMatchesIgnoreCase(int offset, int limit, String part, int i) {
            char uch1;
            char uch2;
            int i2 = 0;
            while (true) {
                int partlen = i - 1;
                if (i > 0) {
                    int offset2 = offset + 1;
                    char ch1 = this.target[offset];
                    int i3 = i2 + 1;
                    char ch2 = part.charAt(i2);
                    if (ch1 == ch2 || (uch1 = Character.toUpperCase(ch1)) == (uch2 = Character.toUpperCase(ch2)) || Character.toLowerCase(uch1) == Character.toLowerCase(uch2)) {
                        i2 = i3;
                        i = partlen;
                        offset = offset2;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }
        }

        @Override // org.apache.xmlbeans.impl.regex.RegularExpression.ExpressionTarget
        boolean regionMatches(boolean ignoreCase, int offset, int limit, int offset2, int partlen) {
            if (offset < 0 || limit - offset < partlen) {
                return false;
            }
            return ignoreCase ? regionMatchesIgnoreCase(offset, limit, offset2, partlen) : regionMatches(offset, limit, offset2, partlen);
        }

        private boolean regionMatches(int offset, int limit, int offset2, int partlen) {
            int i = offset2;
            while (true) {
                int partlen2 = partlen - 1;
                if (partlen > 0) {
                    int offset3 = offset + 1;
                    int i2 = i + 1;
                    if (this.target[offset] == this.target[i]) {
                        partlen = partlen2;
                        offset = offset3;
                        i = i2;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }
        }

        private boolean regionMatchesIgnoreCase(int offset, int limit, int offset2, int partlen) {
            char uch1;
            char uch2;
            int i = offset2;
            while (true) {
                int partlen2 = partlen - 1;
                if (partlen > 0) {
                    int offset3 = offset + 1;
                    char ch1 = this.target[offset];
                    int i2 = i + 1;
                    char ch2 = this.target[i];
                    if (ch1 == ch2 || (uch1 = Character.toUpperCase(ch1)) == (uch2 = Character.toUpperCase(ch2)) || Character.toLowerCase(uch1) == Character.toLowerCase(uch2)) {
                        partlen = partlen2;
                        offset = offset3;
                        i = i2;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class CharacterIteratorTarget extends ExpressionTarget {
        CharacterIterator target;

        CharacterIteratorTarget(CharacterIterator target) {
            this.target = target;
        }

        void resetTarget(CharacterIterator target) {
            this.target = target;
        }

        @Override // org.apache.xmlbeans.impl.regex.RegularExpression.ExpressionTarget
        char charAt(int index) {
            return this.target.setIndex(index);
        }

        @Override // org.apache.xmlbeans.impl.regex.RegularExpression.ExpressionTarget
        boolean regionMatches(boolean ignoreCase, int offset, int limit, String part, int partlen) {
            if (offset < 0 || limit - offset < partlen) {
                return false;
            }
            return ignoreCase ? regionMatchesIgnoreCase(offset, limit, part, partlen) : regionMatches(offset, limit, part, partlen);
        }

        private boolean regionMatches(int offset, int limit, String part, int i) {
            int i2 = 0;
            while (true) {
                int partlen = i - 1;
                if (i > 0) {
                    int offset2 = offset + 1;
                    int i3 = i2 + 1;
                    if (this.target.setIndex(offset) == part.charAt(i2)) {
                        i2 = i3;
                        i = partlen;
                        offset = offset2;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }
        }

        private boolean regionMatchesIgnoreCase(int offset, int limit, String part, int i) {
            char uch1;
            char uch2;
            int i2 = 0;
            while (true) {
                int partlen = i - 1;
                if (i > 0) {
                    int offset2 = offset + 1;
                    char ch1 = this.target.setIndex(offset);
                    int i3 = i2 + 1;
                    char ch2 = part.charAt(i2);
                    if (ch1 == ch2 || (uch1 = Character.toUpperCase(ch1)) == (uch2 = Character.toUpperCase(ch2)) || Character.toLowerCase(uch1) == Character.toLowerCase(uch2)) {
                        i2 = i3;
                        i = partlen;
                        offset = offset2;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }
        }

        @Override // org.apache.xmlbeans.impl.regex.RegularExpression.ExpressionTarget
        boolean regionMatches(boolean ignoreCase, int offset, int limit, int offset2, int partlen) {
            if (offset < 0 || limit - offset < partlen) {
                return false;
            }
            return ignoreCase ? regionMatchesIgnoreCase(offset, limit, offset2, partlen) : regionMatches(offset, limit, offset2, partlen);
        }

        private boolean regionMatches(int offset, int limit, int offset2, int partlen) {
            int i = offset2;
            while (true) {
                int partlen2 = partlen - 1;
                if (partlen > 0) {
                    int offset3 = offset + 1;
                    int i2 = i + 1;
                    if (this.target.setIndex(offset) == this.target.setIndex(i)) {
                        partlen = partlen2;
                        offset = offset3;
                        i = i2;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }
        }

        private boolean regionMatchesIgnoreCase(int offset, int limit, int offset2, int partlen) {
            char uch1;
            char uch2;
            int i = offset2;
            while (true) {
                int partlen2 = partlen - 1;
                if (partlen > 0) {
                    int offset3 = offset + 1;
                    char ch1 = this.target.setIndex(offset);
                    int i2 = i + 1;
                    char ch2 = this.target.setIndex(i);
                    if (ch1 == ch2 || (uch1 = Character.toUpperCase(ch1)) == (uch2 = Character.toUpperCase(ch2)) || Character.toLowerCase(uch1) == Character.toLowerCase(uch2)) {
                        partlen = partlen2;
                        offset = offset3;
                        i = i2;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ClosureContext {
        int[] offsets = new int[4];
        int currentIndex = 0;

        ClosureContext() {
        }

        boolean contains(int offset) {
            for (int i = 0; i < this.currentIndex; i++) {
                if (this.offsets[i] == offset) {
                    return true;
                }
            }
            return false;
        }

        void reset() {
            this.currentIndex = 0;
        }

        void addOffset(int offset) {
            if (this.currentIndex == this.offsets.length) {
                this.offsets = expandOffsets();
            }
            int[] iArr = this.offsets;
            int i = this.currentIndex;
            this.currentIndex = i + 1;
            iArr[i] = offset;
        }

        private int[] expandOffsets() {
            int len = this.offsets.length;
            int newLen = len << 1;
            int[] newOffsets = new int[newLen];
            System.arraycopy(this.offsets, 0, newOffsets, 0, this.currentIndex);
            return newOffsets;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class Context {
        private CharArrayTarget charArrayTarget;
        private CharacterIteratorTarget characterIteratorTarget;
        ClosureContext[] closureContexts;
        boolean inuse = false;
        int length;
        int limit;
        Match match;
        int start;
        private StringTarget stringTarget;
        ExpressionTarget target;

        Context() {
        }

        private void resetCommon(int nofclosures) {
            this.length = this.limit - this.start;
            setInUse(true);
            this.match = null;
            if (this.closureContexts == null || this.closureContexts.length != nofclosures) {
                this.closureContexts = new ClosureContext[nofclosures];
            }
            for (int i = 0; i < nofclosures; i++) {
                if (this.closureContexts[i] == null) {
                    this.closureContexts[i] = new ClosureContext();
                } else {
                    this.closureContexts[i].reset();
                }
            }
        }

        void reset(CharacterIterator target, int start, int limit, int nofclosures) {
            if (this.characterIteratorTarget == null) {
                this.characterIteratorTarget = new CharacterIteratorTarget(target);
            } else {
                this.characterIteratorTarget.resetTarget(target);
            }
            this.target = this.characterIteratorTarget;
            this.start = start;
            this.limit = limit;
            resetCommon(nofclosures);
        }

        void reset(String target, int start, int limit, int nofclosures) {
            if (this.stringTarget == null) {
                this.stringTarget = new StringTarget(target);
            } else {
                this.stringTarget.resetTarget(target);
            }
            this.target = this.stringTarget;
            this.start = start;
            this.limit = limit;
            resetCommon(nofclosures);
        }

        void reset(char[] target, int start, int limit, int nofclosures) {
            if (this.charArrayTarget == null) {
                this.charArrayTarget = new CharArrayTarget(target);
            } else {
                this.charArrayTarget.resetTarget(target);
            }
            this.target = this.charArrayTarget;
            this.start = start;
            this.limit = limit;
            resetCommon(nofclosures);
        }

        synchronized void setInUse(boolean inUse) {
            this.inuse = inUse;
        }
    }

    void prepare() {
        compile(this.tokentree);
        this.minlength = this.tokentree.getMinLength();
        this.firstChar = null;
        if (!isSet(this.options, 128) && !isSet(this.options, 512)) {
            RangeToken firstChar = Token.createRange();
            int fresult = this.tokentree.analyzeFirstCharacter(firstChar, this.options);
            if (fresult == 1) {
                firstChar.compactRanges();
                this.firstChar = firstChar;
            }
        }
        if (this.operations != null && ((this.operations.type == 6 || this.operations.type == 1) && this.operations.next == null)) {
            this.fixedStringOnly = true;
            if (this.operations.type == 6) {
                this.fixedString = this.operations.getString();
            } else if (this.operations.getData() >= 65536) {
                this.fixedString = REUtil.decomposeToSurrogates(this.operations.getData());
            } else {
                char[] ac = {(char) this.operations.getData()};
                this.fixedString = new String(ac);
            }
            this.fixedStringOptions = this.options;
            this.fixedStringTable = new BMPattern(this.fixedString, 256, isSet(this.fixedStringOptions, 2));
            return;
        }
        if (!isSet(this.options, 256) && !isSet(this.options, 512)) {
            Token.FixedStringContainer container = new Token.FixedStringContainer();
            this.tokentree.findFixedString(container, this.options);
            this.fixedString = container.token == null ? null : container.token.getString();
            this.fixedStringOptions = container.options;
            if (this.fixedString != null && this.fixedString.length() < 2) {
                this.fixedString = null;
            }
            if (this.fixedString != null) {
                this.fixedStringTable = new BMPattern(this.fixedString, 256, isSet(this.fixedStringOptions, 2));
            }
        }
    }

    private static boolean isSet(int options, int flag) {
        return (options & flag) == flag;
    }

    public RegularExpression(String regex) throws ParseException {
        this(regex, null);
    }

    public RegularExpression(String regex, String options) throws ParseException {
        this.hasBackReferences = false;
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = false;
        setPattern(regex, options);
    }

    public RegularExpression(String regex, String options, Locale locale) throws ParseException {
        this.hasBackReferences = false;
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = false;
        setPattern(regex, options, locale);
    }

    RegularExpression(String regex, Token tok, int parens, boolean hasBackReferences, int options) {
        this.hasBackReferences = false;
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = false;
        this.regex = regex;
        this.tokentree = tok;
        this.nofparen = parens;
        this.options = options;
        this.hasBackReferences = hasBackReferences;
    }

    public void setPattern(String newPattern) throws ParseException {
        setPattern(newPattern, Locale.getDefault());
    }

    public void setPattern(String newPattern, Locale locale) throws ParseException {
        setPattern(newPattern, this.options, locale);
    }

    private void setPattern(String newPattern, int options, Locale locale) throws ParseException {
        this.regex = newPattern;
        this.options = options;
        RegexParser rp = isSet(this.options, 512) ? new ParserForXMLSchema(locale) : new RegexParser(locale);
        this.tokentree = rp.parse(this.regex, this.options);
        this.nofparen = rp.parennumber;
        this.hasBackReferences = rp.hasBackReferences;
        this.operations = null;
        this.context = null;
    }

    public void setPattern(String newPattern, String options) throws ParseException {
        setPattern(newPattern, options, Locale.getDefault());
    }

    public void setPattern(String newPattern, String options, Locale locale) throws ParseException {
        setPattern(newPattern, REUtil.parseOptions(options), locale);
    }

    public String getPattern() {
        return this.regex;
    }

    public String toString() {
        return this.tokentree.toString(this.options);
    }

    public String getOptions() {
        return REUtil.createOptionString(this.options);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof RegularExpression)) {
            return false;
        }
        RegularExpression r = (RegularExpression) obj;
        return this.regex.equals(r.regex) && this.options == r.options;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean equals(String pattern, int options) {
        return this.regex.equals(pattern) && this.options == options;
    }

    public int hashCode() {
        return (this.regex + PackagingURIHelper.FORWARD_SLASH_STRING + getOptions()).hashCode();
    }

    public int getNumberOfGroups() {
        return this.nofparen;
    }

    private static int getWordType0(char ch, int opts) {
        if (!isSet(opts, 64)) {
            return isSet(opts, 32) ? Token.getRange("IsWord", true).match(ch) ? 1 : 2 : isWordChar(ch) ? 1 : 2;
        }
        switch (Character.getType(ch)) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
            case 11:
                return 1;
            case 6:
            case 7:
            case 16:
                return 0;
            case 12:
            case 13:
            case 14:
            default:
                return 2;
            case 15:
                switch (ch) {
                    case '\t':
                    case '\n':
                    case 11:
                    case '\f':
                    case '\r':
                        return 2;
                    default:
                        return 0;
                }
        }
    }

    private static boolean isEOLChar(int ch) {
        return ch == 10 || ch == 13 || ch == LINE_SEPARATOR || ch == PARAGRAPH_SEPARATOR;
    }

    private static boolean isWordChar(int ch) {
        if (ch == 95) {
            return true;
        }
        if (ch < 48 || ch > 122) {
            return false;
        }
        if (ch <= 57) {
            return true;
        }
        if (ch < 65) {
            return false;
        }
        return ch <= 90 || ch >= 97;
    }

    private static boolean matchIgnoreCase(int chardata, int ch) {
        if (chardata == ch) {
            return true;
        }
        if (chardata > 65535 || ch > 65535) {
            return false;
        }
        char uch1 = Character.toUpperCase((char) chardata);
        char uch2 = Character.toUpperCase((char) ch);
        if (uch1 == uch2 || Character.toLowerCase(uch1) == Character.toLowerCase(uch2)) {
            return true;
        }
        return false;
    }
}
