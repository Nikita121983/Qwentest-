package org.apache.xmlbeans.impl.regex;

import java.io.Serializable;
import org.apache.commons.collections4.CollectionUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class RangeToken extends Token implements Serializable {
    private static final int MAPSIZE = 256;
    boolean compacted;
    RangeToken icaseCache;
    int[] map;
    int nonMapIndex;
    int[] ranges;
    boolean sorted;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RangeToken(int type) {
        super(type);
        this.icaseCache = null;
        this.map = null;
        setSorted(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.regex.Token
    public void addRange(int start, int end) {
        int r1;
        int r2;
        this.icaseCache = null;
        if (start <= end) {
            r1 = start;
            r2 = end;
        } else {
            r1 = end;
            r2 = start;
        }
        if (this.ranges == null) {
            this.ranges = new int[2];
            this.ranges[0] = r1;
            this.ranges[1] = r2;
            setSorted(true);
            return;
        }
        int pos = this.ranges.length;
        if (this.ranges[pos - 1] + 1 == r1) {
            this.ranges[pos - 1] = r2;
            return;
        }
        int[] temp = new int[pos + 2];
        System.arraycopy(this.ranges, 0, temp, 0, pos);
        this.ranges = temp;
        if (this.ranges[pos - 1] >= r1) {
            setSorted(false);
        }
        this.ranges[pos] = r1;
        this.ranges[pos + 1] = r2;
        if (!this.sorted) {
            sortRanges();
        }
    }

    private boolean isSorted() {
        return this.sorted;
    }

    private void setSorted(boolean sort) {
        this.sorted = sort;
        if (!sort) {
            this.compacted = false;
        }
    }

    private boolean isCompacted() {
        return this.compacted;
    }

    private void setCompacted() {
        this.compacted = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.regex.Token
    public void sortRanges() {
        if (isSorted() || this.ranges == null) {
            return;
        }
        for (int i = this.ranges.length - 4; i >= 0; i -= 2) {
            for (int j = 0; j <= i; j += 2) {
                if (this.ranges[j] > this.ranges[j + 2] || (this.ranges[j] == this.ranges[j + 2] && this.ranges[j + 1] > this.ranges[j + 3])) {
                    int tmp = this.ranges[j + 2];
                    this.ranges[j + 2] = this.ranges[j];
                    this.ranges[j] = tmp;
                    int tmp2 = this.ranges[j + 3];
                    this.ranges[j + 3] = this.ranges[j + 1];
                    this.ranges[j + 1] = tmp2;
                }
            }
        }
        setSorted(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.regex.Token
    public void compactRanges() {
        int target;
        if (this.ranges == null || this.ranges.length <= 2 || isCompacted()) {
            return;
        }
        int base = 0;
        int target2 = 0;
        while (target2 < this.ranges.length) {
            if (base != target2) {
                int target3 = target2 + 1;
                this.ranges[base] = this.ranges[target2];
                target = target3 + 1;
                this.ranges[base + 1] = this.ranges[target3];
            } else {
                target = target2 + 2;
            }
            int baseend = this.ranges[base + 1];
            target2 = target;
            while (target2 < this.ranges.length && baseend + 1 >= this.ranges[target2]) {
                if (baseend + 1 == this.ranges[target2]) {
                    if (0 != 0) {
                        System.err.println("Token#compactRanges(): Compaction: [" + this.ranges[base] + ", " + this.ranges[base + 1] + "], [" + this.ranges[target2] + ", " + this.ranges[target2 + 1] + "] -> [" + this.ranges[base] + ", " + this.ranges[target2 + 1] + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
                    }
                    this.ranges[base + 1] = this.ranges[target2 + 1];
                    baseend = this.ranges[base + 1];
                    target2 += 2;
                } else if (baseend >= this.ranges[target2 + 1]) {
                    if (0 != 0) {
                        System.err.println("Token#compactRanges(): Compaction: [" + this.ranges[base] + ", " + this.ranges[base + 1] + "], [" + this.ranges[target2] + ", " + this.ranges[target2 + 1] + "] -> [" + this.ranges[base] + ", " + this.ranges[base + 1] + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
                    }
                    target2 += 2;
                } else if (baseend < this.ranges[target2 + 1]) {
                    if (0 != 0) {
                        System.err.println("Token#compactRanges(): Compaction: [" + this.ranges[base] + ", " + this.ranges[base + 1] + "], [" + this.ranges[target2] + ", " + this.ranges[target2 + 1] + "] -> [" + this.ranges[base] + ", " + this.ranges[target2 + 1] + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
                    }
                    this.ranges[base + 1] = this.ranges[target2 + 1];
                    baseend = this.ranges[base + 1];
                    target2 += 2;
                } else {
                    throw new RuntimeException("Token#compactRanges(): Internel Error: [" + this.ranges[base] + CollectionUtils.COMMA + this.ranges[base + 1] + "] [" + this.ranges[target2] + CollectionUtils.COMMA + this.ranges[target2 + 1] + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
                }
            }
            base += 2;
        }
        if (base != this.ranges.length) {
            int[] result = new int[base];
            System.arraycopy(this.ranges, 0, result, 0, base);
            this.ranges = result;
        }
        setCompacted();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.regex.Token
    public void mergeRanges(Token token) {
        RangeToken tok = (RangeToken) token;
        sortRanges();
        tok.sortRanges();
        if (tok.ranges == null) {
            return;
        }
        this.icaseCache = null;
        setSorted(true);
        if (this.ranges == null) {
            this.ranges = new int[tok.ranges.length];
            System.arraycopy(tok.ranges, 0, this.ranges, 0, tok.ranges.length);
            return;
        }
        int[] result = new int[this.ranges.length + tok.ranges.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (true) {
            if (i < this.ranges.length || j < tok.ranges.length) {
                if (i >= this.ranges.length) {
                    int k2 = k + 1;
                    int j2 = j + 1;
                    result[k] = tok.ranges[j];
                    k = k2 + 1;
                    result[k2] = tok.ranges[j2];
                    j = j2 + 1;
                } else if (j >= tok.ranges.length) {
                    int k3 = k + 1;
                    int i2 = i + 1;
                    result[k] = this.ranges[i];
                    k = k3 + 1;
                    result[k3] = this.ranges[i2];
                    i = i2 + 1;
                } else if (tok.ranges[j] < this.ranges[i] || (tok.ranges[j] == this.ranges[i] && tok.ranges[j + 1] < this.ranges[i + 1])) {
                    int k4 = k + 1;
                    int j3 = j + 1;
                    result[k] = tok.ranges[j];
                    k = k4 + 1;
                    result[k4] = tok.ranges[j3];
                    j = j3 + 1;
                } else {
                    int k5 = k + 1;
                    int i3 = i + 1;
                    result[k] = this.ranges[i];
                    k = k5 + 1;
                    result[k5] = this.ranges[i3];
                    i = i3 + 1;
                }
            } else {
                this.ranges = result;
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.regex.Token
    public void subtractRanges(Token token) {
        if (token.type == 5) {
            intersectRanges(token);
            return;
        }
        RangeToken tok = (RangeToken) token;
        if (tok.ranges == null || this.ranges == null) {
            return;
        }
        this.icaseCache = null;
        sortRanges();
        compactRanges();
        tok.sortRanges();
        tok.compactRanges();
        int[] result = new int[this.ranges.length + tok.ranges.length];
        int wp = 0;
        int src = 0;
        int sub = 0;
        while (src < this.ranges.length && sub < tok.ranges.length) {
            int srcbegin = this.ranges[src];
            int srcend = this.ranges[src + 1];
            int subbegin = tok.ranges[sub];
            int subend = tok.ranges[sub + 1];
            if (srcend < subbegin) {
                int wp2 = wp + 1;
                int src2 = src + 1;
                result[wp] = this.ranges[src];
                wp = wp2 + 1;
                result[wp2] = this.ranges[src2];
                src = src2 + 1;
            } else if (srcend >= subbegin && srcbegin <= subend) {
                if (subbegin <= srcbegin && srcend <= subend) {
                    src += 2;
                } else if (subbegin <= srcbegin) {
                    this.ranges[src] = subend + 1;
                    sub += 2;
                } else if (srcend <= subend) {
                    int wp3 = wp + 1;
                    result[wp] = srcbegin;
                    wp = wp3 + 1;
                    result[wp3] = subbegin - 1;
                    src += 2;
                } else {
                    int wp4 = wp + 1;
                    result[wp] = srcbegin;
                    wp = wp4 + 1;
                    result[wp4] = subbegin - 1;
                    this.ranges[src] = subend + 1;
                    sub += 2;
                }
            } else if (subend < srcbegin) {
                sub += 2;
            } else {
                throw new RuntimeException("Token#subtractRanges(): Internal Error: [" + this.ranges[src] + CollectionUtils.COMMA + this.ranges[src + 1] + "] - [" + tok.ranges[sub] + CollectionUtils.COMMA + tok.ranges[sub + 1] + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            }
        }
        while (src < this.ranges.length) {
            int wp5 = wp + 1;
            int src3 = src + 1;
            result[wp] = this.ranges[src];
            wp = wp5 + 1;
            result[wp5] = this.ranges[src3];
            src = src3 + 1;
        }
        this.ranges = new int[wp];
        System.arraycopy(result, 0, this.ranges, 0, wp);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.regex.Token
    public void intersectRanges(Token token) {
        RangeToken tok = (RangeToken) token;
        if (tok.ranges == null || this.ranges == null) {
            return;
        }
        this.icaseCache = null;
        sortRanges();
        compactRanges();
        tok.sortRanges();
        tok.compactRanges();
        int[] result = new int[this.ranges.length + tok.ranges.length];
        int wp = 0;
        int src1 = 0;
        int src2 = 0;
        while (src1 < this.ranges.length && src2 < tok.ranges.length) {
            int src1begin = this.ranges[src1];
            int src1end = this.ranges[src1 + 1];
            int src2begin = tok.ranges[src2];
            int src2end = tok.ranges[src2 + 1];
            if (src1end < src2begin) {
                src1 += 2;
            } else if (src1end >= src2begin && src1begin <= src2end) {
                if (src2begin <= src1begin && src1end <= src2end) {
                    int wp2 = wp + 1;
                    result[wp] = src1begin;
                    wp = wp2 + 1;
                    result[wp2] = src1end;
                    src1 += 2;
                } else if (src2begin <= src1begin) {
                    int wp3 = wp + 1;
                    result[wp] = src1begin;
                    wp = wp3 + 1;
                    result[wp3] = src2end;
                    this.ranges[src1] = src2end + 1;
                    src2 += 2;
                } else if (src1end <= src2end) {
                    int wp4 = wp + 1;
                    result[wp] = src2begin;
                    wp = wp4 + 1;
                    result[wp4] = src1end;
                    src1 += 2;
                } else {
                    int wp5 = wp + 1;
                    result[wp] = src2begin;
                    wp = wp5 + 1;
                    result[wp5] = src2end;
                    this.ranges[src1] = src2end + 1;
                }
            } else if (src2end < src1begin) {
                src2 += 2;
            } else {
                throw new RuntimeException("Token#intersectRanges(): Internal Error: [" + this.ranges[src1] + CollectionUtils.COMMA + this.ranges[src1 + 1] + "] & [" + tok.ranges[src2] + CollectionUtils.COMMA + tok.ranges[src2 + 1] + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            }
        }
        while (src1 < this.ranges.length) {
            int wp6 = wp + 1;
            int src12 = src1 + 1;
            result[wp] = this.ranges[src1];
            wp = wp6 + 1;
            result[wp6] = this.ranges[src12];
            src1 = src12 + 1;
        }
        this.ranges = new int[wp];
        System.arraycopy(result, 0, this.ranges, 0, wp);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Token complementRanges(Token token) {
        if (token.type != 4 && token.type != 5) {
            throw new IllegalArgumentException("Token#complementRanges(): must be RANGE: " + token.type);
        }
        RangeToken tok = (RangeToken) token;
        tok.sortRanges();
        tok.compactRanges();
        int len = tok.ranges.length + 2;
        if (tok.ranges[0] == 0) {
            len -= 2;
        }
        int last = tok.ranges[tok.ranges.length - 1];
        if (last == 1114111) {
            len -= 2;
        }
        RangeToken ret = Token.createRange();
        ret.ranges = new int[len];
        int wp = 0;
        if (tok.ranges[0] > 0) {
            int wp2 = 0 + 1;
            ret.ranges[0] = 0;
            ret.ranges[wp2] = tok.ranges[0] - 1;
            wp = wp2 + 1;
        }
        int i = 1;
        while (i < tok.ranges.length - 2) {
            int wp3 = wp + 1;
            ret.ranges[wp] = tok.ranges[i] + 1;
            ret.ranges[wp3] = tok.ranges[i + 1] - 1;
            i += 2;
            wp = wp3 + 1;
        }
        if (last != 1114111) {
            ret.ranges[wp] = last + 1;
            ret.ranges[wp + 1] = 1114111;
        }
        ret.setCompacted();
        return ret;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized RangeToken getCaseInsensitiveToken() {
        if (this.icaseCache != null) {
            return this.icaseCache;
        }
        RangeToken uppers = this.type == 4 ? Token.createRange() : Token.createNRange();
        for (int i = 0; i < this.ranges.length; i += 2) {
            for (int ch = this.ranges[i]; ch <= this.ranges[i + 1]; ch++) {
                if (ch > 65535) {
                    uppers.addRange(ch, ch);
                } else {
                    char uch = Character.toUpperCase((char) ch);
                    uppers.addRange(uch, uch);
                }
            }
        }
        int i2 = this.type;
        RangeToken lowers = i2 == 4 ? Token.createRange() : Token.createNRange();
        for (int i3 = 0; i3 < uppers.ranges.length; i3 += 2) {
            for (int ch2 = uppers.ranges[i3]; ch2 <= uppers.ranges[i3 + 1]; ch2++) {
                if (ch2 > 65535) {
                    lowers.addRange(ch2, ch2);
                } else {
                    char uch2 = Character.toUpperCase((char) ch2);
                    lowers.addRange(uch2, uch2);
                }
            }
        }
        lowers.mergeRanges(uppers);
        lowers.mergeRanges(this);
        lowers.compactRanges();
        this.icaseCache = lowers;
        return lowers;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.xmlbeans.impl.regex.Token
    public boolean match(int ch) {
        boolean ret;
        if (this.map == null) {
            createMap();
        }
        if (this.type == 4) {
            if (ch < 256) {
                return (this.map[ch / 32] & (1 << (ch & 31))) != 0;
            }
            ret = false;
            for (int i = this.nonMapIndex; i < this.ranges.length; i += 2) {
                if (this.ranges[i] <= ch && ch <= this.ranges[i + 1]) {
                    return true;
                }
            }
        } else {
            if (ch < 256) {
                return (this.map[ch / 32] & (1 << (ch & 31))) == 0;
            }
            ret = true;
            for (int i2 = this.nonMapIndex; i2 < this.ranges.length; i2 += 2) {
                if (this.ranges[i2] <= ch && ch <= this.ranges[i2 + 1]) {
                    return false;
                }
            }
        }
        return ret;
    }

    private void createMap() {
        int[] localmap = new int[8];
        int localnonMapIndex = this.ranges.length;
        for (int i = 0; i < 8; i++) {
            localmap[i] = 0;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= this.ranges.length) {
                break;
            }
            int s = this.ranges[i2];
            int e = this.ranges[i2 + 1];
            if (s < 256) {
                for (int j = s; j <= e && j < 256; j++) {
                    int i3 = j / 32;
                    localmap[i3] = localmap[i3] | (1 << (j & 31));
                }
                if (e < 256) {
                    i2 += 2;
                } else {
                    localnonMapIndex = i2;
                    break;
                }
            } else {
                localnonMapIndex = i2;
                break;
            }
        }
        this.nonMapIndex = localnonMapIndex;
        this.map = localmap;
    }

    @Override // org.apache.xmlbeans.impl.regex.Token
    public String toString(int options) {
        if (this.type == 4) {
            if (this == Token.token_dot) {
                return ".";
            }
            if (this == Token.token_0to9) {
                return "\\d";
            }
            if (this == Token.token_wordchars) {
                return "\\w";
            }
            if (this == Token.token_spaces) {
                return "\\s";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(CollectionUtils.DEFAULT_TOSTRING_PREFIX);
            for (int i = 0; i < this.ranges.length; i += 2) {
                if ((options & 1024) != 0 && i > 0) {
                    sb.append(CollectionUtils.COMMA);
                }
                if (this.ranges[i] == this.ranges[i + 1]) {
                    sb.append(escapeCharInCharClass(this.ranges[i]));
                } else {
                    sb.append(escapeCharInCharClass(this.ranges[i]));
                    sb.append('-');
                    sb.append(escapeCharInCharClass(this.ranges[i + 1]));
                }
            }
            sb.append(CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            String ret = sb.toString();
            return ret;
        }
        if (this == Token.token_not_0to9) {
            return "\\D";
        }
        if (this == Token.token_not_wordchars) {
            return "\\W";
        }
        if (this == Token.token_not_spaces) {
            return "\\S";
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("[^");
        for (int i2 = 0; i2 < this.ranges.length; i2 += 2) {
            if ((options & 1024) != 0 && i2 > 0) {
                sb2.append(CollectionUtils.COMMA);
            }
            if (this.ranges[i2] == this.ranges[i2 + 1]) {
                sb2.append(escapeCharInCharClass(this.ranges[i2]));
            } else {
                sb2.append(escapeCharInCharClass(this.ranges[i2]));
                sb2.append('-');
                sb2.append(escapeCharInCharClass(this.ranges[i2 + 1]));
            }
        }
        sb2.append(CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        String ret2 = sb2.toString();
        return ret2;
    }

    private static String escapeCharInCharClass(int ch) {
        switch (ch) {
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
            case 44:
            case 45:
            case 91:
            case 92:
            case 93:
            case 94:
                String ret = "\\" + ((char) ch);
                return ret;
            default:
                if (ch < 32) {
                    String pre = "0" + Integer.toHexString(ch);
                    return "\\x" + pre.substring(pre.length() - 2);
                }
                if (ch >= 65536) {
                    String pre2 = "0" + Integer.toHexString(ch);
                    return "\\v" + pre2.substring(pre2.length() - 6);
                }
                String ret2 = "" + ((char) ch);
                return ret2;
        }
    }
}
