package org.apache.commons.lang3.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

@Deprecated
/* loaded from: classes9.dex */
public class StrTokenizer implements ListIterator<String>, Cloneable {
    private static final StrTokenizer CSV_TOKENIZER_PROTOTYPE = new StrTokenizer().setDelimiterMatcher(StrMatcher.commaMatcher()).setQuoteMatcher(StrMatcher.doubleQuoteMatcher()).setIgnoredMatcher(StrMatcher.noneMatcher()).setTrimmerMatcher(StrMatcher.trimMatcher()).setEmptyTokenAsNull(false).setIgnoreEmptyTokens(false);
    private static final StrTokenizer TSV_TOKENIZER_PROTOTYPE = new StrTokenizer().setDelimiterMatcher(StrMatcher.tabMatcher()).setQuoteMatcher(StrMatcher.doubleQuoteMatcher()).setIgnoredMatcher(StrMatcher.noneMatcher()).setTrimmerMatcher(StrMatcher.trimMatcher()).setEmptyTokenAsNull(false).setIgnoreEmptyTokens(false);
    private char[] chars;
    private StrMatcher delimMatcher;
    private boolean emptyAsNull;
    private boolean ignoreEmptyTokens;
    private StrMatcher ignoredMatcher;
    private StrMatcher quoteMatcher;
    private int tokenPos;
    private String[] tokens;
    private StrMatcher trimmerMatcher;

    private static StrTokenizer getCSVClone() {
        return (StrTokenizer) CSV_TOKENIZER_PROTOTYPE.clone();
    }

    public static StrTokenizer getCSVInstance() {
        return getCSVClone();
    }

    public static StrTokenizer getCSVInstance(char[] input) {
        StrTokenizer tok = getCSVClone();
        tok.reset(input);
        return tok;
    }

    public static StrTokenizer getCSVInstance(String input) {
        StrTokenizer tok = getCSVClone();
        tok.reset(input);
        return tok;
    }

    private static StrTokenizer getTSVClone() {
        return (StrTokenizer) TSV_TOKENIZER_PROTOTYPE.clone();
    }

    public static StrTokenizer getTSVInstance() {
        return getTSVClone();
    }

    public static StrTokenizer getTSVInstance(char[] input) {
        StrTokenizer tok = getTSVClone();
        tok.reset(input);
        return tok;
    }

    public static StrTokenizer getTSVInstance(String input) {
        StrTokenizer tok = getTSVClone();
        tok.reset(input);
        return tok;
    }

    public StrTokenizer() {
        this.delimMatcher = StrMatcher.splitMatcher();
        this.quoteMatcher = StrMatcher.noneMatcher();
        this.ignoredMatcher = StrMatcher.noneMatcher();
        this.trimmerMatcher = StrMatcher.noneMatcher();
        this.ignoreEmptyTokens = true;
        this.chars = null;
    }

    public StrTokenizer(char[] input) {
        this.delimMatcher = StrMatcher.splitMatcher();
        this.quoteMatcher = StrMatcher.noneMatcher();
        this.ignoredMatcher = StrMatcher.noneMatcher();
        this.trimmerMatcher = StrMatcher.noneMatcher();
        this.ignoreEmptyTokens = true;
        this.chars = ArrayUtils.clone(input);
    }

    public StrTokenizer(char[] input, char delim) {
        this(input);
        setDelimiterChar(delim);
    }

    public StrTokenizer(char[] input, char delim, char quote) {
        this(input, delim);
        setQuoteChar(quote);
    }

    public StrTokenizer(char[] input, String delim) {
        this(input);
        setDelimiterString(delim);
    }

    public StrTokenizer(char[] input, StrMatcher delim) {
        this(input);
        setDelimiterMatcher(delim);
    }

    public StrTokenizer(char[] input, StrMatcher delim, StrMatcher quote) {
        this(input, delim);
        setQuoteMatcher(quote);
    }

    public StrTokenizer(String input) {
        this.delimMatcher = StrMatcher.splitMatcher();
        this.quoteMatcher = StrMatcher.noneMatcher();
        this.ignoredMatcher = StrMatcher.noneMatcher();
        this.trimmerMatcher = StrMatcher.noneMatcher();
        this.ignoreEmptyTokens = true;
        if (input != null) {
            this.chars = input.toCharArray();
        } else {
            this.chars = null;
        }
    }

    public StrTokenizer(String input, char delim) {
        this(input);
        setDelimiterChar(delim);
    }

    public StrTokenizer(String input, char delim, char quote) {
        this(input, delim);
        setQuoteChar(quote);
    }

    public StrTokenizer(String input, String delim) {
        this(input);
        setDelimiterString(delim);
    }

    public StrTokenizer(String input, StrMatcher delim) {
        this(input);
        setDelimiterMatcher(delim);
    }

    public StrTokenizer(String input, StrMatcher delim, StrMatcher quote) {
        this(input, delim);
        setQuoteMatcher(quote);
    }

    @Override // java.util.ListIterator
    public void add(String obj) {
        throw new UnsupportedOperationException("add() is unsupported");
    }

    private void addToken(List<String> list, String tok) {
        if (StringUtils.isEmpty(tok)) {
            if (isIgnoreEmptyTokens()) {
                return;
            }
            if (isEmptyTokenAsNull()) {
                tok = null;
            }
        }
        list.add(tok);
    }

    private void checkTokenized() {
        if (this.tokens == null) {
            if (this.chars == null) {
                List<String> split = tokenize(null, 0, 0);
                this.tokens = (String[]) split.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
            } else {
                List<String> split2 = tokenize(this.chars, 0, this.chars.length);
                this.tokens = (String[]) split2.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
            }
        }
    }

    public Object clone() {
        try {
            return cloneReset();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    Object cloneReset() throws CloneNotSupportedException {
        StrTokenizer cloned = (StrTokenizer) super.clone();
        if (cloned.chars != null) {
            cloned.chars = (char[]) cloned.chars.clone();
        }
        cloned.reset();
        return cloned;
    }

    public String getContent() {
        if (this.chars == null) {
            return null;
        }
        return new String(this.chars);
    }

    public StrMatcher getDelimiterMatcher() {
        return this.delimMatcher;
    }

    public StrMatcher getIgnoredMatcher() {
        return this.ignoredMatcher;
    }

    public StrMatcher getQuoteMatcher() {
        return this.quoteMatcher;
    }

    public String[] getTokenArray() {
        checkTokenized();
        return (String[]) this.tokens.clone();
    }

    public List<String> getTokenList() {
        checkTokenized();
        List<String> list = new ArrayList<>(this.tokens.length);
        list.addAll(Arrays.asList(this.tokens));
        return list;
    }

    public StrMatcher getTrimmerMatcher() {
        return this.trimmerMatcher;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public boolean hasNext() {
        checkTokenized();
        return this.tokenPos < this.tokens.length;
    }

    @Override // java.util.ListIterator
    public boolean hasPrevious() {
        checkTokenized();
        return this.tokenPos > 0;
    }

    public boolean isEmptyTokenAsNull() {
        return this.emptyAsNull;
    }

    public boolean isIgnoreEmptyTokens() {
        return this.ignoreEmptyTokens;
    }

    private boolean isQuote(char[] srcChars, int pos, int len, int quoteStart, int quoteLen) {
        for (int i = 0; i < quoteLen; i++) {
            if (pos + i >= len || srcChars[pos + i] != srcChars[quoteStart + i]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public String next() {
        if (hasNext()) {
            String[] strArr = this.tokens;
            int i = this.tokenPos;
            this.tokenPos = i + 1;
            return strArr[i];
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        return this.tokenPos;
    }

    public String nextToken() {
        if (hasNext()) {
            String[] strArr = this.tokens;
            int i = this.tokenPos;
            this.tokenPos = i + 1;
            return strArr[i];
        }
        return null;
    }

    @Override // java.util.ListIterator
    public String previous() {
        if (hasPrevious()) {
            String[] strArr = this.tokens;
            int i = this.tokenPos - 1;
            this.tokenPos = i;
            return strArr[i];
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        return this.tokenPos - 1;
    }

    public String previousToken() {
        if (hasPrevious()) {
            String[] strArr = this.tokens;
            int i = this.tokenPos - 1;
            this.tokenPos = i;
            return strArr[i];
        }
        return null;
    }

    private int readNextToken(char[] srcChars, int start, int len, StrBuilder workArea, List<String> tokenList) {
        int start2 = start;
        while (start2 < len) {
            int removeLen = Math.max(getIgnoredMatcher().isMatch(srcChars, start2, start2, len), getTrimmerMatcher().isMatch(srcChars, start2, start2, len));
            if (removeLen == 0 || getDelimiterMatcher().isMatch(srcChars, start2, start2, len) > 0 || getQuoteMatcher().isMatch(srcChars, start2, start2, len) > 0) {
                break;
            }
            start2 += removeLen;
        }
        if (start2 >= len) {
            addToken(tokenList, "");
            return -1;
        }
        int delimLen = getDelimiterMatcher().isMatch(srcChars, start2, start2, len);
        if (delimLen > 0) {
            addToken(tokenList, "");
            return start2 + delimLen;
        }
        int quoteLen = getQuoteMatcher().isMatch(srcChars, start2, start2, len);
        if (quoteLen > 0) {
            int start3 = start2;
            return readWithQuotes(srcChars, start3 + quoteLen, len, workArea, tokenList, start3, quoteLen);
        }
        return readWithQuotes(srcChars, start2, len, workArea, tokenList, 0, 0);
    }

    private int readWithQuotes(char[] srcChars, int start, int len, StrBuilder workArea, List<String> tokenList, int quoteStart, int quoteLen) {
        int pos;
        int i = len;
        workArea.clear();
        boolean quoting = quoteLen > 0;
        boolean quoting2 = quoting;
        int trimStart = 0;
        int pos2 = start;
        while (pos2 < i) {
            if (!quoting2) {
                int pos3 = pos2;
                int delimLen = getDelimiterMatcher().isMatch(srcChars, pos3, start, i);
                if (delimLen > 0) {
                    addToken(tokenList, workArea.substring(0, trimStart));
                    return pos3 + delimLen;
                }
                if (quoteLen > 0) {
                    pos = pos3;
                    if (isQuote(srcChars, pos, i, quoteStart, quoteLen)) {
                        quoting2 = true;
                        pos2 = pos + quoteLen;
                    }
                } else {
                    pos = pos3;
                }
                int ignoredLen = getIgnoredMatcher().isMatch(srcChars, pos, start, i);
                if (ignoredLen <= 0) {
                    int trimmedLen = getTrimmerMatcher().isMatch(srcChars, pos, start, i);
                    if (trimmedLen > 0) {
                        workArea.append(srcChars, pos, trimmedLen);
                        pos2 = pos + trimmedLen;
                    } else {
                        workArea.append(srcChars[pos]);
                        trimStart = workArea.size();
                        pos2 = pos + 1;
                    }
                } else {
                    pos2 = pos + ignoredLen;
                }
            } else {
                int pos4 = pos2;
                if (!isQuote(srcChars, pos2, i, quoteStart, quoteLen)) {
                    i = len;
                    pos = pos4;
                    workArea.append(srcChars[pos]);
                    trimStart = workArea.size();
                    pos2 = pos + 1;
                } else {
                    i = len;
                    if (isQuote(srcChars, pos4 + quoteLen, i, quoteStart, quoteLen)) {
                        workArea.append(srcChars, pos4, quoteLen);
                        pos2 = (quoteLen * 2) + pos4;
                        trimStart = workArea.size();
                    } else {
                        quoting2 = false;
                        pos2 = pos4 + quoteLen;
                    }
                }
            }
        }
        addToken(tokenList, workArea.substring(0, trimStart));
        return -1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("remove() is unsupported");
    }

    public StrTokenizer reset() {
        this.tokenPos = 0;
        this.tokens = null;
        return this;
    }

    public StrTokenizer reset(char[] input) {
        reset();
        this.chars = ArrayUtils.clone(input);
        return this;
    }

    public StrTokenizer reset(String input) {
        reset();
        if (input != null) {
            this.chars = input.toCharArray();
        } else {
            this.chars = null;
        }
        return this;
    }

    @Override // java.util.ListIterator
    public void set(String obj) {
        throw new UnsupportedOperationException("set() is unsupported");
    }

    public StrTokenizer setDelimiterChar(char delim) {
        return setDelimiterMatcher(StrMatcher.charMatcher(delim));
    }

    public StrTokenizer setDelimiterMatcher(StrMatcher delim) {
        if (delim == null) {
            this.delimMatcher = StrMatcher.noneMatcher();
        } else {
            this.delimMatcher = delim;
        }
        return this;
    }

    public StrTokenizer setDelimiterString(String delim) {
        return setDelimiterMatcher(StrMatcher.stringMatcher(delim));
    }

    public StrTokenizer setEmptyTokenAsNull(boolean emptyAsNull) {
        this.emptyAsNull = emptyAsNull;
        return this;
    }

    public StrTokenizer setIgnoredChar(char ignored) {
        return setIgnoredMatcher(StrMatcher.charMatcher(ignored));
    }

    public StrTokenizer setIgnoredMatcher(StrMatcher ignored) {
        if (ignored != null) {
            this.ignoredMatcher = ignored;
        }
        return this;
    }

    public StrTokenizer setIgnoreEmptyTokens(boolean ignoreEmptyTokens) {
        this.ignoreEmptyTokens = ignoreEmptyTokens;
        return this;
    }

    public StrTokenizer setQuoteChar(char quote) {
        return setQuoteMatcher(StrMatcher.charMatcher(quote));
    }

    public StrTokenizer setQuoteMatcher(StrMatcher quote) {
        if (quote != null) {
            this.quoteMatcher = quote;
        }
        return this;
    }

    public StrTokenizer setTrimmerMatcher(StrMatcher trimmer) {
        if (trimmer != null) {
            this.trimmerMatcher = trimmer;
        }
        return this;
    }

    public int size() {
        checkTokenized();
        return this.tokens.length;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<String> tokenize(char[] srcChars, int offset, int count) {
        if (ArrayUtils.isEmpty(srcChars)) {
            return Collections.emptyList();
        }
        StrBuilder buf = new StrBuilder();
        List<String> tokenList = new ArrayList<>();
        int pos = offset;
        while (pos >= 0 && pos < count) {
            char[] srcChars2 = srcChars;
            int count2 = count;
            pos = readNextToken(srcChars2, pos, count2, buf, tokenList);
            if (pos >= count2) {
                addToken(tokenList, "");
            }
            srcChars = srcChars2;
            count = count2;
        }
        return tokenList;
    }

    public String toString() {
        if (this.tokens == null) {
            return "StrTokenizer[not tokenized yet]";
        }
        return "StrTokenizer" + getTokenList();
    }
}
