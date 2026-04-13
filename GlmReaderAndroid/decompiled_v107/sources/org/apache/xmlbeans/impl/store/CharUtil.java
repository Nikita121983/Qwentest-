package org.apache.xmlbeans.impl.store;

import java.io.PrintStream;
import java.lang.ref.SoftReference;
import java.util.function.Supplier;

/* loaded from: classes11.dex */
public final class CharUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int CHARUTIL_INITIAL_BUFSIZE = 32768;
    private static final int MAX_COPY = 64;
    private static final ThreadLocal<SoftReference<CharUtil>> tl_charUtil = ThreadLocal.withInitial(new Supplier() { // from class: org.apache.xmlbeans.impl.store.CharUtil$$ExternalSyntheticLambda0
        @Override // java.util.function.Supplier
        public final Object get() {
            return CharUtil.lambda$static$0();
        }
    });
    public int _cchSrc;
    private final int _charBufSize;
    private final CharIterator _charIter = new CharIterator();
    private char[] _currentBuffer;
    private int _currentOffset;
    public int _offSrc;

    public CharUtil(int charBufSize) {
        this._charBufSize = charBufSize;
    }

    public CharIterator getCharIterator(Object src, int off, int cch) {
        this._charIter.init(src, off, cch);
        return this._charIter;
    }

    public CharIterator getCharIterator(Object src, int off, int cch, int start) {
        this._charIter.init(src, off, cch, start);
        return this._charIter;
    }

    public static CharUtil getThreadLocalCharUtil() {
        SoftReference<CharUtil> softRef = tl_charUtil.get();
        CharUtil charUtil = softRef.get();
        if (charUtil == null) {
            CharUtil charUtil2 = new CharUtil(32768);
            tl_charUtil.set(new SoftReference<>(charUtil2));
            return charUtil2;
        }
        return charUtil;
    }

    public static void getString(StringBuffer sb, Object src, int off, int cch) {
        if (!isValid(src, off, cch)) {
            throw new AssertionError();
        }
        if (cch == 0) {
            return;
        }
        if (src instanceof char[]) {
            sb.append((char[]) src, off, cch);
            return;
        }
        if (src instanceof String) {
            String s = (String) src;
            if (off == 0 && cch == s.length()) {
                sb.append(s);
                return;
            } else {
                sb.append((CharSequence) s, off, off + cch);
                return;
            }
        }
        ((CharJoin) src).getString(sb, off, cch);
    }

    public static void getChars(char[] chars, int start, Object src, int off, int cch) {
        if (!isValid(src, off, cch)) {
            throw new AssertionError();
        }
        if (chars == null || start < 0 || start > chars.length) {
            throw new AssertionError();
        }
        if (cch == 0) {
            return;
        }
        if (src instanceof char[]) {
            char[] cs = (char[]) src;
            System.arraycopy(cs, off, chars, start, cch);
        } else if (src instanceof String) {
            ((String) src).getChars(off, off + cch, chars, start);
        } else {
            ((CharJoin) src).getChars(chars, start, off, cch);
        }
    }

    public static String getString(Object src, int off, int cch) {
        if (!isValid(src, off, cch)) {
            throw new AssertionError();
        }
        if (cch == 0) {
            return "";
        }
        if (src instanceof char[]) {
            return new String((char[]) src, off, cch);
        }
        if (src instanceof String) {
            String s = (String) src;
            if (off == 0 && cch == s.length()) {
                return s;
            }
            return s.substring(off, off + cch);
        }
        StringBuffer sb = new StringBuffer();
        ((CharJoin) src).getString(sb, off, cch);
        return sb.toString();
    }

    public static boolean isWhiteSpace(char ch) {
        switch (ch) {
            case '\t':
            case '\n':
            case '\r':
            case ' ':
                return true;
            default:
                return false;
        }
    }

    public final boolean isWhiteSpace(Object src, int off, int cch) {
        if (!isValid(src, off, cch)) {
            throw new AssertionError();
        }
        if (cch <= 0) {
            return true;
        }
        if (src instanceof char[]) {
            char[] chars = (char[]) src;
            while (cch > 0) {
                int off2 = off + 1;
                if (!isWhiteSpace(chars[off])) {
                    return false;
                }
                cch--;
                off = off2;
            }
            return true;
        }
        if (src instanceof String) {
            String s = (String) src;
            while (cch > 0) {
                int off3 = off + 1;
                if (!isWhiteSpace(s.charAt(off))) {
                    return false;
                }
                cch--;
                off = off3;
            }
            return true;
        }
        boolean isWhite = true;
        this._charIter.init(src, off, cch);
        while (true) {
            if (!this._charIter.hasNext()) {
                break;
            }
            if (!isWhiteSpace(this._charIter.next())) {
                isWhite = false;
                break;
            }
        }
        this._charIter.release();
        return isWhite;
    }

    public Object stripLeft(Object src, int off, int cch) {
        if (!isValid(src, off, cch)) {
            throw new AssertionError();
        }
        if (cch > 0) {
            if (src instanceof char[]) {
                char[] chars = (char[]) src;
                while (cch > 0 && isWhiteSpace(chars[off])) {
                    cch--;
                    off++;
                }
            } else if (src instanceof String) {
                String s = (String) src;
                while (cch > 0 && isWhiteSpace(s.charAt(off))) {
                    cch--;
                    off++;
                }
            } else {
                int count = 0;
                this._charIter.init(src, off, cch);
                while (this._charIter.hasNext() && isWhiteSpace(this._charIter.next())) {
                    count++;
                }
                this._charIter.release();
                off += count;
            }
        }
        if (cch == 0) {
            this._offSrc = 0;
            this._cchSrc = 0;
            return null;
        }
        this._offSrc = off;
        this._cchSrc = cch;
        return src;
    }

    public Object stripRight(Object src, int off, int cch) {
        if (!isValid(src, off, cch)) {
            throw new AssertionError();
        }
        if (cch > 0) {
            this._charIter.init(src, off, cch, cch);
            while (this._charIter.hasPrev() && isWhiteSpace(this._charIter.prev())) {
                cch--;
            }
            this._charIter.release();
        }
        if (cch == 0) {
            this._offSrc = 0;
            this._cchSrc = 0;
            return null;
        }
        this._offSrc = off;
        this._cchSrc = cch;
        return src;
    }

    public Object insertChars(int i, Object obj, int i2, int i3, Object obj2, int i4, int i5) {
        CharJoin charJoin;
        Object obj3;
        if (!isValid(obj, i2, i3)) {
            throw new AssertionError();
        }
        if (!isValid(obj2, i4, i5)) {
            throw new AssertionError();
        }
        if (i < 0 || i > i3) {
            throw new AssertionError();
        }
        if (i5 == 0) {
            this._cchSrc = i3;
            this._offSrc = i2;
            return obj;
        }
        if (i3 == 0) {
            this._cchSrc = i5;
            this._offSrc = i4;
            return obj2;
        }
        this._cchSrc = i3 + i5;
        if (this._cchSrc <= 64 && canAllocate(this._cchSrc)) {
            char[] allocate = allocate(this._cchSrc);
            getChars(allocate, this._offSrc, obj, i2, i);
            getChars(allocate, this._offSrc + i, obj2, i4, i5);
            getChars(allocate, this._offSrc + i + i5, obj, i2 + i, i3 - i);
            obj3 = allocate;
        } else {
            this._offSrc = 0;
            if (i == 0) {
                charJoin = new CharJoin(obj2, i4, i5, obj, i2);
            } else if (i == i3) {
                charJoin = new CharJoin(obj, i2, i3, obj2, i4);
            } else {
                charJoin = new CharJoin(new CharJoin(obj, i2, i, obj2, i4), 0, i + i5, obj, i2 + i);
            }
            if (charJoin._depth > 64) {
                obj3 = saveChars(charJoin, this._offSrc, this._cchSrc);
            } else {
                obj3 = charJoin;
            }
        }
        if (!isValid(obj3, this._offSrc, this._cchSrc)) {
            throw new AssertionError();
        }
        return obj3;
    }

    public Object removeChars(int posRemove, int cchRemove, Object src, int off, int cch) {
        Object newSrc;
        if (!isValid(src, off, cch)) {
            throw new AssertionError();
        }
        if (posRemove < 0 || posRemove > cch) {
            throw new AssertionError();
        }
        if (cchRemove < 0 || posRemove + cchRemove > cch) {
            throw new AssertionError();
        }
        this._cchSrc = cch - cchRemove;
        if (this._cchSrc == 0) {
            newSrc = null;
            this._offSrc = 0;
        } else if (posRemove == 0) {
            newSrc = src;
            this._offSrc = off + cchRemove;
        } else if (posRemove + cchRemove == cch) {
            newSrc = src;
            this._offSrc = off;
        } else {
            int cchAfter = cch - cchRemove;
            if (cchAfter <= 64 && canAllocate(cchAfter)) {
                char[] chars = allocate(cchAfter);
                getChars(chars, this._offSrc, src, off, posRemove);
                getChars(chars, this._offSrc + posRemove, src, off + posRemove + cchRemove, (cch - posRemove) - cchRemove);
                newSrc = chars;
            } else {
                CharJoin j = new CharJoin(src, off, posRemove, src, off + posRemove + cchRemove);
                if (j._depth > 64) {
                    newSrc = saveChars(j, 0, this._cchSrc);
                } else {
                    this._offSrc = 0;
                    newSrc = j;
                }
            }
        }
        if (!isValid(newSrc, this._offSrc, this._cchSrc)) {
            throw new AssertionError();
        }
        return newSrc;
    }

    private boolean canAllocate(int cch) {
        return this._currentBuffer == null || this._currentBuffer.length - this._currentOffset >= cch;
    }

    private char[] allocate(int cch) {
        if (this._currentBuffer != null && this._currentBuffer.length - this._currentOffset <= 0) {
            throw new AssertionError();
        }
        if (this._currentBuffer == null) {
            this._currentBuffer = new char[Math.max(cch, this._charBufSize)];
            this._currentOffset = 0;
        }
        this._offSrc = this._currentOffset;
        this._cchSrc = Math.min(this._currentBuffer.length - this._currentOffset, cch);
        char[] retBuf = this._currentBuffer;
        if (this._currentOffset + this._cchSrc > this._currentBuffer.length) {
            throw new AssertionError();
        }
        int i = this._currentOffset + this._cchSrc;
        this._currentOffset = i;
        if (i == this._currentBuffer.length) {
            this._currentBuffer = null;
            this._currentOffset = 0;
        }
        return retBuf;
    }

    public Object saveChars(Object srcSave, int offSave, int cchSave) {
        return saveChars(srcSave, offSave, cchSave, null, 0, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object saveChars(Object srcSave, int offSave, int cchSave, Object srcPrev, int offPrev, int cchPrev) {
        Object srcNew;
        int offNew;
        if (!isValid(srcSave, offSave, cchSave)) {
            throw new AssertionError();
        }
        if (!isValid(srcPrev, offPrev, cchPrev)) {
            throw new AssertionError();
        }
        char[] allocate = allocate(cchSave);
        int offAlloc = this._offSrc;
        int cchAlloc = this._cchSrc;
        if (cchAlloc > cchSave) {
            throw new AssertionError();
        }
        getChars(allocate, offAlloc, srcSave, offSave, cchAlloc);
        int cchNew = cchAlloc + cchPrev;
        if (cchPrev == 0) {
            srcNew = allocate;
            offNew = offAlloc;
        } else if (srcPrev == allocate && offPrev + cchPrev == offAlloc) {
            if (!(srcPrev instanceof char[])) {
                throw new AssertionError();
            }
            srcNew = srcPrev;
            offNew = offPrev;
        } else {
            if (srcPrev instanceof CharJoin) {
                CharJoin j = (CharJoin) srcPrev;
                if (j._srcRight == allocate && ((offPrev + cchPrev) - j._cchLeft) + j._offRight == offAlloc) {
                    if (!(j._srcRight instanceof char[])) {
                        throw new AssertionError();
                    }
                    srcNew = srcPrev;
                    offNew = offPrev;
                }
            }
            CharJoin j2 = new CharJoin(srcPrev, offPrev, cchPrev, allocate, offAlloc);
            srcNew = j2._depth > 64 ? saveChars(j2, 0, cchNew) : j2;
            offNew = 0;
        }
        int cchMore = cchSave - cchAlloc;
        if (cchMore > 0) {
            char[] srcAlloc = allocate(cchMore);
            int offAlloc2 = this._offSrc;
            if (this._cchSrc != cchMore) {
                throw new AssertionError();
            }
            if (offAlloc2 != 0) {
                throw new AssertionError();
            }
            getChars(srcAlloc, offAlloc2, srcSave, (cchSave - cchMore) + offSave, cchMore);
            CharJoin j3 = new CharJoin(srcNew, offNew, cchNew, srcAlloc, offAlloc2);
            offNew = 0;
            cchNew += cchMore;
            srcNew = j3._depth > 64 ? saveChars(j3, 0, cchNew) : j3;
        }
        this._offSrc = offNew;
        this._cchSrc = cchNew;
        if (!isValid(srcNew, this._offSrc, this._cchSrc)) {
            throw new AssertionError();
        }
        return srcNew;
    }

    private static void dumpText(PrintStream o, String s) {
        o.print("\"");
        int i = 0;
        while (true) {
            if (i < s.length()) {
                char ch = s.charAt(i);
                if (i == 36) {
                    o.print("...");
                } else {
                    switch (ch) {
                        case '\t':
                            o.print("\\t");
                            break;
                        case '\n':
                            o.print("\\n");
                            break;
                        case '\f':
                            o.print("\\f");
                            break;
                        case '\r':
                            o.print("\\r");
                            break;
                        case '\"':
                            o.print("\\\"");
                            break;
                        default:
                            o.print(ch);
                            break;
                    }
                    i++;
                }
            }
        }
        o.print("\"");
    }

    public static void dump(Object src, int off, int cch) {
        dumpChars(System.out, src, off, cch);
        System.out.println();
    }

    public static void dumpChars(PrintStream p, Object src, int off, int cch) {
        p.print("off=" + off + ", cch=" + cch + ", ");
        if (src == null) {
            p.print("<null-src>");
            return;
        }
        if (src instanceof String) {
            String s = (String) src;
            p.print("String");
            if ((off != 0 || cch != s.length()) && (off < 0 || off > s.length() || off + cch < 0 || off + cch > s.length())) {
                p.print(" (Error)");
                return;
            } else {
                dumpText(p, s.substring(off, off + cch));
                return;
            }
        }
        if (src instanceof char[]) {
            char[] chars = (char[]) src;
            p.print("char[]");
            if ((off != 0 || cch != chars.length) && (off < 0 || off > chars.length || off + cch < 0 || off + cch > chars.length)) {
                p.print(" (Error)");
                return;
            } else {
                dumpText(p, new String(chars, off, cch));
                return;
            }
        }
        if (src instanceof CharJoin) {
            p.print("CharJoin");
            ((CharJoin) src).dumpChars(p, off, cch);
        } else {
            p.print("Unknown text source");
        }
    }

    public static boolean isValid(Object src, int off, int cch) {
        if (cch < 0 || off < 0) {
            return false;
        }
        if (src == null) {
            if (off != 0 || cch != 0) {
                return false;
            }
            return true;
        }
        if (src instanceof char[]) {
            char[] c = (char[]) src;
            if (off > c.length || off + cch > c.length) {
                return false;
            }
            return true;
        }
        if (src instanceof String) {
            String s = (String) src;
            if (off > s.length() || off + cch > s.length()) {
                return false;
            }
            return true;
        }
        if (!(src instanceof CharJoin)) {
            return false;
        }
        return ((CharJoin) src).isValid(off, cch);
    }

    /* loaded from: classes11.dex */
    public static final class CharJoin {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static final int MAX_DEPTH = 64;
        public final int _cchLeft;
        public final int _depth;
        public final int _offLeft;
        public final int _offRight;
        public final Object _srcLeft;
        public final Object _srcRight;

        public CharJoin(Object srcLeft, int offLeft, int cchLeft, Object srcRight, int offRight) {
            int rightDepth;
            this._srcLeft = srcLeft;
            this._offLeft = offLeft;
            this._cchLeft = cchLeft;
            this._srcRight = srcRight;
            this._offRight = offRight;
            int depth = srcLeft instanceof CharJoin ? ((CharJoin) srcLeft)._depth : 0;
            if ((srcRight instanceof CharJoin) && (rightDepth = ((CharJoin) srcRight)._depth) > depth) {
                depth = rightDepth;
            }
            this._depth = depth + 1;
            if (this._depth > 66) {
                throw new AssertionError();
            }
        }

        private int cchRight(int off, int cch) {
            return Math.max(0, (cch - this._cchLeft) - off);
        }

        public int depth() {
            int depth = 0;
            if (this._srcLeft instanceof CharJoin) {
                depth = ((CharJoin) this._srcLeft).depth();
            }
            if (this._srcRight instanceof CharJoin) {
                depth = Math.max(((CharJoin) this._srcRight).depth(), depth);
            }
            return depth + 1;
        }

        public boolean isValid(int off, int cch) {
            if (this._depth > 2) {
                return true;
            }
            if (this._depth != depth()) {
                throw new AssertionError();
            }
            if (off < 0 || cch < 0 || !CharUtil.isValid(this._srcLeft, this._offLeft, this._cchLeft)) {
                return false;
            }
            return CharUtil.isValid(this._srcRight, this._offRight, cchRight(off, cch));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void getString(StringBuffer sb, int off, int cch) {
            if (cch <= 0) {
                throw new AssertionError();
            }
            if (off < this._cchLeft) {
                int cchL = Math.min(this._cchLeft - off, cch);
                CharUtil.getString(sb, this._srcLeft, this._offLeft + off, cchL);
                if (cch > cchL) {
                    CharUtil.getString(sb, this._srcRight, this._offRight, cch - cchL);
                    return;
                }
                return;
            }
            CharUtil.getString(sb, this._srcRight, (this._offRight + off) - this._cchLeft, cch);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void getChars(char[] chars, int start, int off, int cch) {
            if (cch <= 0) {
                throw new AssertionError();
            }
            if (off < this._cchLeft) {
                int cchL = Math.min(this._cchLeft - off, cch);
                CharUtil.getChars(chars, start, this._srcLeft, this._offLeft + off, cchL);
                if (cch > cchL) {
                    CharUtil.getChars(chars, start + cchL, this._srcRight, this._offRight, cch - cchL);
                    return;
                }
                return;
            }
            CharUtil.getChars(chars, start, this._srcRight, (this._offRight + off) - this._cchLeft, cch);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dumpChars(PrintStream p, int off, int cch) {
            p.print("( ");
            CharUtil.dumpChars(p, this._srcLeft, this._offLeft, this._cchLeft);
            p.print(", ");
            CharUtil.dumpChars(p, this._srcRight, this._offRight, cchRight(off, cch));
            p.print(" )");
        }
    }

    /* loaded from: classes11.dex */
    public static final class CharIterator {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private int _cchRoot;
        private int _maxPos;
        private int _minPos;
        private int _offLeaf;
        private int _offRoot;
        private int _pos;
        private char[] _srcLeafChars;
        private String _srcLeafString;
        private Object _srcRoot;

        public void init(Object src, int off, int cch) {
            init(src, off, cch, 0);
        }

        public void init(Object src, int off, int cch, int startPos) {
            if (!CharUtil.isValid(src, off, cch)) {
                throw new AssertionError();
            }
            release();
            this._srcRoot = src;
            this._offRoot = off;
            this._cchRoot = cch;
            this._maxPos = -1;
            this._minPos = -1;
            movePos(startPos);
        }

        public void release() {
            this._srcRoot = null;
            this._srcLeafString = null;
            this._srcLeafChars = null;
        }

        public boolean hasNext() {
            return this._pos < this._cchRoot;
        }

        public boolean hasPrev() {
            return this._pos > 0;
        }

        public char next() {
            if (!hasNext()) {
                throw new AssertionError();
            }
            char ch = currentChar();
            movePos(this._pos + 1);
            return ch;
        }

        public char prev() {
            if (!hasPrev()) {
                throw new AssertionError();
            }
            movePos(this._pos - 1);
            return currentChar();
        }

        public void movePos(int newPos) {
            if (newPos < 0 || newPos > this._cchRoot) {
                throw new AssertionError();
            }
            if (newPos < this._minPos || newPos > this._maxPos) {
                Object src = this._srcRoot;
                int off = this._offRoot + newPos;
                int cch = this._cchRoot;
                this._offLeaf = this._offRoot;
                while (src instanceof CharJoin) {
                    CharJoin j = (CharJoin) src;
                    if (off < j._cchLeft) {
                        src = j._srcLeft;
                        this._offLeaf = j._offLeft;
                        off += j._offLeft;
                        cch = j._cchLeft;
                    } else {
                        src = j._srcRight;
                        this._offLeaf = j._offRight;
                        off -= j._cchLeft - j._offRight;
                        cch -= j._cchLeft;
                    }
                }
                this._minPos = newPos - (off - this._offLeaf);
                this._maxPos = this._minPos + cch;
                if (newPos < this._cchRoot) {
                    this._maxPos--;
                }
                this._srcLeafChars = null;
                this._srcLeafString = null;
                if (src instanceof char[]) {
                    this._srcLeafChars = (char[]) src;
                } else {
                    this._srcLeafString = (String) src;
                }
                if (newPos < this._minPos || newPos > this._maxPos) {
                    throw new AssertionError();
                }
            }
            this._pos = newPos;
        }

        private char currentChar() {
            int i = (this._offLeaf + this._pos) - this._minPos;
            return this._srcLeafChars == null ? this._srcLeafString.charAt(i) : this._srcLeafChars[i];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SoftReference lambda$static$0() {
        return new SoftReference(new CharUtil(32768));
    }

    public static void clearThreadLocals() {
        tl_charUtil.remove();
    }
}
