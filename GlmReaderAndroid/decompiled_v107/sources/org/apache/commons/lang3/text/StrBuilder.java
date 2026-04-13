package org.apache.commons.lang3.text;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.apache.commons.lang3.builder.Builder;

@Deprecated
/* loaded from: classes9.dex */
public class StrBuilder implements CharSequence, Appendable, Serializable, Builder<String> {
    static final int CAPACITY = 32;
    private static final long serialVersionUID = 7628716375283629643L;
    protected char[] buffer;
    private String newLine;
    private String nullText;
    protected int size;

    /* loaded from: classes9.dex */
    final class StrBuilderReader extends Reader {
        private int mark;
        private int pos;

        StrBuilderReader() {
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Reader
        public void mark(int readAheadLimit) {
            this.mark = this.pos;
        }

        @Override // java.io.Reader
        public boolean markSupported() {
            return true;
        }

        @Override // java.io.Reader
        public int read() {
            if (!ready()) {
                return -1;
            }
            StrBuilder strBuilder = StrBuilder.this;
            int i = this.pos;
            this.pos = i + 1;
            return strBuilder.charAt(i);
        }

        @Override // java.io.Reader
        public int read(char[] b, int off, int len) {
            if (off < 0 || len < 0 || off > b.length || off + len > b.length || off + len < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (len == 0) {
                return 0;
            }
            if (this.pos >= StrBuilder.this.size()) {
                return -1;
            }
            if (this.pos + len > StrBuilder.this.size()) {
                len = StrBuilder.this.size() - this.pos;
            }
            StrBuilder.this.getChars(this.pos, this.pos + len, b, off);
            this.pos += len;
            return len;
        }

        @Override // java.io.Reader
        public boolean ready() {
            return this.pos < StrBuilder.this.size();
        }

        @Override // java.io.Reader
        public void reset() {
            this.pos = this.mark;
        }

        @Override // java.io.Reader
        public long skip(long n) {
            if (this.pos + n > StrBuilder.this.size()) {
                n = StrBuilder.this.size() - this.pos;
            }
            if (n < 0) {
                return 0L;
            }
            this.pos = Math.addExact(this.pos, Math.toIntExact(n));
            return n;
        }
    }

    /* loaded from: classes9.dex */
    final class StrBuilderTokenizer extends StrTokenizer {
        StrBuilderTokenizer() {
        }

        @Override // org.apache.commons.lang3.text.StrTokenizer
        public String getContent() {
            String str = super.getContent();
            if (str == null) {
                return StrBuilder.this.toString();
            }
            return str;
        }

        @Override // org.apache.commons.lang3.text.StrTokenizer
        protected List<String> tokenize(char[] chars, int offset, int count) {
            if (chars == null) {
                return super.tokenize(StrBuilder.this.buffer, 0, StrBuilder.this.size());
            }
            return super.tokenize(chars, offset, count);
        }
    }

    /* loaded from: classes9.dex */
    final class StrBuilderWriter extends Writer {
        StrBuilderWriter() {
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(char[] cbuf) {
            StrBuilder.this.append(cbuf);
        }

        @Override // java.io.Writer
        public void write(char[] cbuf, int off, int len) {
            StrBuilder.this.append(cbuf, off, len);
        }

        @Override // java.io.Writer
        public void write(int c) {
            StrBuilder.this.append((char) c);
        }

        @Override // java.io.Writer
        public void write(String str) {
            StrBuilder.this.append(str);
        }

        @Override // java.io.Writer
        public void write(String str, int off, int len) {
            StrBuilder.this.append(str, off, len);
        }
    }

    public StrBuilder() {
        this(32);
    }

    public StrBuilder(int initialCapacity) {
        this.buffer = new char[initialCapacity <= 0 ? 32 : initialCapacity];
    }

    public StrBuilder(String str) {
        if (str != null) {
            this.buffer = new char[str.length() + 32];
            append(str);
        } else {
            this.buffer = new char[32];
        }
    }

    public StrBuilder append(boolean value) {
        if (value) {
            ensureCapacity(this.size + 4);
            char[] cArr = this.buffer;
            int i = this.size;
            this.size = i + 1;
            cArr[i] = 't';
            char[] cArr2 = this.buffer;
            int i2 = this.size;
            this.size = i2 + 1;
            cArr2[i2] = 'r';
            char[] cArr3 = this.buffer;
            int i3 = this.size;
            this.size = i3 + 1;
            cArr3[i3] = 'u';
        } else {
            ensureCapacity(this.size + 5);
            char[] cArr4 = this.buffer;
            int i4 = this.size;
            this.size = i4 + 1;
            cArr4[i4] = 'f';
            char[] cArr5 = this.buffer;
            int i5 = this.size;
            this.size = i5 + 1;
            cArr5[i5] = 'a';
            char[] cArr6 = this.buffer;
            int i6 = this.size;
            this.size = i6 + 1;
            cArr6[i6] = 'l';
            char[] cArr7 = this.buffer;
            int i7 = this.size;
            this.size = i7 + 1;
            cArr7[i7] = 's';
        }
        char[] cArr8 = this.buffer;
        int i8 = this.size;
        this.size = i8 + 1;
        cArr8[i8] = 'e';
        return this;
    }

    @Override // java.lang.Appendable
    public StrBuilder append(char ch) {
        int len = length();
        ensureCapacity(len + 1);
        char[] cArr = this.buffer;
        int i = this.size;
        this.size = i + 1;
        cArr[i] = ch;
        return this;
    }

    public StrBuilder append(char[] chars) {
        if (chars == null) {
            return appendNull();
        }
        int strLen = chars.length;
        if (strLen > 0) {
            int len = length();
            ensureCapacity(len + strLen);
            System.arraycopy(chars, 0, this.buffer, len, strLen);
            this.size += strLen;
        }
        return this;
    }

    public StrBuilder append(char[] chars, int startIndex, int length) {
        if (chars == null) {
            return appendNull();
        }
        if (startIndex < 0 || startIndex > chars.length) {
            throw new StringIndexOutOfBoundsException("Invalid startIndex: " + length);
        }
        if (length < 0 || startIndex + length > chars.length) {
            throw new StringIndexOutOfBoundsException("Invalid length: " + length);
        }
        if (length > 0) {
            int len = length();
            ensureCapacity(len + length);
            System.arraycopy(chars, startIndex, this.buffer, len, length);
            this.size += length;
        }
        return this;
    }

    public StrBuilder append(CharBuffer buf) {
        if (buf == null) {
            return appendNull();
        }
        if (buf.hasArray()) {
            int length = buf.remaining();
            int len = length();
            ensureCapacity(len + length);
            System.arraycopy(buf.array(), buf.arrayOffset() + buf.position(), this.buffer, len, length);
            this.size += length;
        } else {
            append(buf.toString());
        }
        return this;
    }

    public StrBuilder append(CharBuffer buf, int startIndex, int length) {
        if (buf == null) {
            return appendNull();
        }
        if (buf.hasArray()) {
            int totalLength = buf.remaining();
            if (startIndex < 0 || startIndex > totalLength) {
                throw new StringIndexOutOfBoundsException("startIndex must be valid");
            }
            if (length < 0 || startIndex + length > totalLength) {
                throw new StringIndexOutOfBoundsException("length must be valid");
            }
            int len = length();
            ensureCapacity(len + length);
            System.arraycopy(buf.array(), buf.arrayOffset() + buf.position() + startIndex, this.buffer, len, length);
            this.size += length;
        } else {
            append(buf.toString(), startIndex, length);
        }
        return this;
    }

    @Override // java.lang.Appendable
    public StrBuilder append(CharSequence seq) {
        if (seq == null) {
            return appendNull();
        }
        if (seq instanceof StrBuilder) {
            return append((StrBuilder) seq);
        }
        if (seq instanceof StringBuilder) {
            return append((StringBuilder) seq);
        }
        if (seq instanceof StringBuffer) {
            return append((StringBuffer) seq);
        }
        if (seq instanceof CharBuffer) {
            return append((CharBuffer) seq);
        }
        return append(seq.toString());
    }

    @Override // java.lang.Appendable
    public StrBuilder append(CharSequence seq, int startIndex, int length) {
        if (seq == null) {
            return appendNull();
        }
        return append(seq.toString(), startIndex, length);
    }

    public StrBuilder append(double value) {
        return append(String.valueOf(value));
    }

    public StrBuilder append(float value) {
        return append(String.valueOf(value));
    }

    public StrBuilder append(int value) {
        return append(String.valueOf(value));
    }

    public StrBuilder append(long value) {
        return append(String.valueOf(value));
    }

    public StrBuilder append(Object obj) {
        if (obj == null) {
            return appendNull();
        }
        if (obj instanceof CharSequence) {
            return append((CharSequence) obj);
        }
        return append(obj.toString());
    }

    public StrBuilder append(StrBuilder str) {
        if (str == null) {
            return appendNull();
        }
        int strLen = str.length();
        if (strLen > 0) {
            int len = length();
            ensureCapacity(len + strLen);
            System.arraycopy(str.buffer, 0, this.buffer, len, strLen);
            this.size += strLen;
        }
        return this;
    }

    public StrBuilder append(StrBuilder str, int startIndex, int length) {
        if (str == null) {
            return appendNull();
        }
        if (startIndex < 0 || startIndex > str.length()) {
            throw new StringIndexOutOfBoundsException("startIndex must be valid");
        }
        if (length < 0 || startIndex + length > str.length()) {
            throw new StringIndexOutOfBoundsException("length must be valid");
        }
        if (length > 0) {
            int len = length();
            ensureCapacity(len + length);
            str.getChars(startIndex, startIndex + length, this.buffer, len);
            this.size += length;
        }
        return this;
    }

    public StrBuilder append(String str) {
        if (str == null) {
            return appendNull();
        }
        int strLen = str.length();
        if (strLen > 0) {
            int len = length();
            ensureCapacity(len + strLen);
            str.getChars(0, strLen, this.buffer, len);
            this.size += strLen;
        }
        return this;
    }

    public StrBuilder append(String str, int startIndex, int length) {
        if (str == null) {
            return appendNull();
        }
        if (startIndex < 0 || startIndex > str.length()) {
            throw new StringIndexOutOfBoundsException("startIndex must be valid");
        }
        if (length < 0 || startIndex + length > str.length()) {
            throw new StringIndexOutOfBoundsException("length must be valid");
        }
        if (length > 0) {
            int len = length();
            ensureCapacity(len + length);
            str.getChars(startIndex, startIndex + length, this.buffer, len);
            this.size += length;
        }
        return this;
    }

    public StrBuilder append(String format, Object... objs) {
        return append(String.format(format, objs));
    }

    public StrBuilder append(StringBuffer str) {
        if (str == null) {
            return appendNull();
        }
        int strLen = str.length();
        if (strLen > 0) {
            int len = length();
            ensureCapacity(len + strLen);
            str.getChars(0, strLen, this.buffer, len);
            this.size += strLen;
        }
        return this;
    }

    public StrBuilder append(StringBuffer str, int startIndex, int length) {
        if (str == null) {
            return appendNull();
        }
        if (startIndex < 0 || startIndex > str.length()) {
            throw new StringIndexOutOfBoundsException("startIndex must be valid");
        }
        if (length < 0 || startIndex + length > str.length()) {
            throw new StringIndexOutOfBoundsException("length must be valid");
        }
        if (length > 0) {
            int len = length();
            ensureCapacity(len + length);
            str.getChars(startIndex, startIndex + length, this.buffer, len);
            this.size += length;
        }
        return this;
    }

    public StrBuilder append(StringBuilder str) {
        if (str == null) {
            return appendNull();
        }
        int strLen = str.length();
        if (strLen > 0) {
            int len = length();
            ensureCapacity(len + strLen);
            str.getChars(0, strLen, this.buffer, len);
            this.size += strLen;
        }
        return this;
    }

    public StrBuilder append(StringBuilder str, int startIndex, int length) {
        if (str == null) {
            return appendNull();
        }
        if (startIndex < 0 || startIndex > str.length()) {
            throw new StringIndexOutOfBoundsException("startIndex must be valid");
        }
        if (length < 0 || startIndex + length > str.length()) {
            throw new StringIndexOutOfBoundsException("length must be valid");
        }
        if (length > 0) {
            int len = length();
            ensureCapacity(len + length);
            str.getChars(startIndex, startIndex + length, this.buffer, len);
            this.size += length;
        }
        return this;
    }

    public StrBuilder appendAll(Iterable<?> iterable) {
        if (iterable != null) {
            iterable.forEach(new StrBuilder$$ExternalSyntheticLambda3(this));
        }
        return this;
    }

    public StrBuilder appendAll(Iterator<?> it) {
        if (it != null) {
            it.forEachRemaining(new StrBuilder$$ExternalSyntheticLambda3(this));
        }
        return this;
    }

    public <T> StrBuilder appendAll(T... array) {
        if (ArrayUtils.isNotEmpty(array)) {
            for (T t : array) {
                append(t);
            }
        }
        return this;
    }

    public StrBuilder appendFixedWidthPadLeft(int value, int width, char padChar) {
        return appendFixedWidthPadLeft(String.valueOf(value), width, padChar);
    }

    public StrBuilder appendFixedWidthPadLeft(Object obj, int width, char padChar) {
        if (width > 0) {
            ensureCapacity(this.size + width);
            String str = ObjectUtils.toString(obj, new StrBuilder$$ExternalSyntheticLambda2(this));
            if (str == null) {
                str = "";
            }
            int strLen = str.length();
            if (strLen >= width) {
                str.getChars(strLen - width, strLen, this.buffer, this.size);
            } else {
                int padLen = width - strLen;
                int toIndex = this.size + padLen;
                Arrays.fill(this.buffer, this.size, toIndex, padChar);
                str.getChars(0, strLen, this.buffer, toIndex);
            }
            int padLen2 = this.size;
            this.size = padLen2 + width;
        }
        return this;
    }

    public StrBuilder appendFixedWidthPadRight(int value, int width, char padChar) {
        return appendFixedWidthPadRight(String.valueOf(value), width, padChar);
    }

    public StrBuilder appendFixedWidthPadRight(Object obj, int width, char padChar) {
        if (width > 0) {
            ensureCapacity(this.size + width);
            String str = ObjectUtils.toString(obj, new StrBuilder$$ExternalSyntheticLambda2(this));
            if (str == null) {
                str = "";
            }
            int strLen = str.length();
            if (strLen >= width) {
                str.getChars(0, width, this.buffer, this.size);
            } else {
                str.getChars(0, strLen, this.buffer, this.size);
                int fromIndex = this.size + strLen;
                Arrays.fill(this.buffer, fromIndex, (fromIndex + width) - strLen, padChar);
            }
            this.size += width;
        }
        return this;
    }

    public StrBuilder appendln(boolean value) {
        return append(value).appendNewLine();
    }

    public StrBuilder appendln(char ch) {
        return append(ch).appendNewLine();
    }

    public StrBuilder appendln(char[] chars) {
        return append(chars).appendNewLine();
    }

    public StrBuilder appendln(char[] chars, int startIndex, int length) {
        return append(chars, startIndex, length).appendNewLine();
    }

    public StrBuilder appendln(double value) {
        return append(value).appendNewLine();
    }

    public StrBuilder appendln(float value) {
        return append(value).appendNewLine();
    }

    public StrBuilder appendln(int value) {
        return append(value).appendNewLine();
    }

    public StrBuilder appendln(long value) {
        return append(value).appendNewLine();
    }

    public StrBuilder appendln(Object obj) {
        return append(obj).appendNewLine();
    }

    public StrBuilder appendln(StrBuilder str) {
        return append(str).appendNewLine();
    }

    public StrBuilder appendln(StrBuilder str, int startIndex, int length) {
        return append(str, startIndex, length).appendNewLine();
    }

    public StrBuilder appendln(String str) {
        return append(str).appendNewLine();
    }

    public StrBuilder appendln(String str, int startIndex, int length) {
        return append(str, startIndex, length).appendNewLine();
    }

    public StrBuilder appendln(String format, Object... objs) {
        return append(format, objs).appendNewLine();
    }

    public StrBuilder appendln(StringBuffer str) {
        return append(str).appendNewLine();
    }

    public StrBuilder appendln(StringBuffer str, int startIndex, int length) {
        return append(str, startIndex, length).appendNewLine();
    }

    public StrBuilder appendln(StringBuilder str) {
        return append(str).appendNewLine();
    }

    public StrBuilder appendln(StringBuilder str, int startIndex, int length) {
        return append(str, startIndex, length).appendNewLine();
    }

    public StrBuilder appendNewLine() {
        if (this.newLine == null) {
            append(System.lineSeparator());
            return this;
        }
        return append(this.newLine);
    }

    public StrBuilder appendNull() {
        if (this.nullText == null) {
            return this;
        }
        return append(this.nullText);
    }

    public StrBuilder appendPadding(int length, char padChar) {
        if (length >= 0) {
            ensureCapacity(this.size + length);
            for (int i = 0; i < length; i++) {
                char[] cArr = this.buffer;
                int i2 = this.size;
                this.size = i2 + 1;
                cArr[i2] = padChar;
            }
        }
        return this;
    }

    public StrBuilder appendSeparator(char separator) {
        if (isNotEmpty()) {
            append(separator);
        }
        return this;
    }

    public StrBuilder appendSeparator(char standard, char defaultIfEmpty) {
        if (isNotEmpty()) {
            append(standard);
        } else {
            append(defaultIfEmpty);
        }
        return this;
    }

    public StrBuilder appendSeparator(char separator, int loopIndex) {
        if (loopIndex > 0) {
            append(separator);
        }
        return this;
    }

    public StrBuilder appendSeparator(String separator) {
        return appendSeparator(separator, (String) null);
    }

    public StrBuilder appendSeparator(String separator, int loopIndex) {
        if (separator != null && loopIndex > 0) {
            append(separator);
        }
        return this;
    }

    public StrBuilder appendSeparator(String standard, String defaultIfEmpty) {
        String str = isEmpty() ? defaultIfEmpty : standard;
        if (str != null) {
            append(str);
        }
        return this;
    }

    public void appendTo(Appendable appendable) throws IOException {
        if (appendable instanceof Writer) {
            ((Writer) appendable).write(this.buffer, 0, this.size);
            return;
        }
        if (appendable instanceof StringBuilder) {
            ((StringBuilder) appendable).append(this.buffer, 0, this.size);
            return;
        }
        if (appendable instanceof StringBuffer) {
            ((StringBuffer) appendable).append(this.buffer, 0, this.size);
        } else if (appendable instanceof CharBuffer) {
            ((CharBuffer) appendable).put(this.buffer, 0, this.size);
        } else {
            appendable.append(this);
        }
    }

    public StrBuilder appendWithSeparators(Iterable<?> iterable, String separator) {
        if (iterable != null) {
            String sep = Objects.toString(separator, "");
            Iterator<?> it = iterable.iterator();
            while (it.hasNext()) {
                append(it.next());
                if (it.hasNext()) {
                    append(sep);
                }
            }
        }
        return this;
    }

    public StrBuilder appendWithSeparators(Iterator<?> it, String separator) {
        if (it != null) {
            String sep = Objects.toString(separator, "");
            while (it.hasNext()) {
                append(it.next());
                if (it.hasNext()) {
                    append(sep);
                }
            }
        }
        return this;
    }

    public StrBuilder appendWithSeparators(Object[] array, String separator) {
        if (array != null && array.length > 0) {
            String sep = Objects.toString(separator, "");
            append(array[0]);
            for (int i = 1; i < array.length; i++) {
                append(sep);
                append(array[i]);
            }
        }
        return this;
    }

    public Reader asReader() {
        return new StrBuilderReader();
    }

    public StrTokenizer asTokenizer() {
        return new StrBuilderTokenizer();
    }

    public Writer asWriter() {
        return new StrBuilderWriter();
    }

    @Override // org.apache.commons.lang3.builder.Builder
    public String build() {
        return toString();
    }

    public int capacity() {
        return this.buffer.length;
    }

    @Override // java.lang.CharSequence
    public char charAt(int index) {
        if (index < 0 || index >= length()) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return this.buffer[index];
    }

    public StrBuilder clear() {
        this.size = 0;
        return this;
    }

    public boolean contains(char ch) {
        char[] thisBuf = this.buffer;
        for (int i = 0; i < this.size; i++) {
            if (thisBuf[i] == ch) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(String str) {
        return indexOf(str, 0) >= 0;
    }

    public boolean contains(StrMatcher matcher) {
        return indexOf(matcher, 0) >= 0;
    }

    public StrBuilder delete(int startIndex, int endIndex) {
        int endIndex2 = validateRange(startIndex, endIndex);
        int len = endIndex2 - startIndex;
        if (len > 0) {
            deleteImpl(startIndex, endIndex2, len);
        }
        return this;
    }

    public StrBuilder deleteAll(char ch) {
        int i = 0;
        while (i < this.size) {
            if (this.buffer[i] == ch) {
                int start = i;
                do {
                    i++;
                    if (i >= this.size) {
                        break;
                    }
                } while (this.buffer[i] == ch);
                int len = i - start;
                deleteImpl(start, i, len);
                i -= len;
            }
            i++;
        }
        return this;
    }

    public StrBuilder deleteAll(String str) {
        int len = StringUtils.length(str);
        if (len > 0) {
            int index = indexOf(str, 0);
            while (index >= 0) {
                deleteImpl(index, index + len, len);
                index = indexOf(str, index);
            }
        }
        return this;
    }

    public StrBuilder deleteAll(StrMatcher matcher) {
        return replace(matcher, null, 0, this.size, -1);
    }

    public StrBuilder deleteCharAt(int index) {
        if (index < 0 || index >= this.size) {
            throw new StringIndexOutOfBoundsException(index);
        }
        deleteImpl(index, index + 1, 1);
        return this;
    }

    public StrBuilder deleteFirst(char ch) {
        int i = 0;
        while (true) {
            if (i >= this.size) {
                break;
            }
            if (this.buffer[i] != ch) {
                i++;
            } else {
                deleteImpl(i, i + 1, 1);
                break;
            }
        }
        return this;
    }

    public StrBuilder deleteFirst(String str) {
        int index;
        int len = StringUtils.length(str);
        if (len > 0 && (index = indexOf(str, 0)) >= 0) {
            deleteImpl(index, index + len, len);
        }
        return this;
    }

    public StrBuilder deleteFirst(StrMatcher matcher) {
        return replace(matcher, null, 0, this.size, 1);
    }

    private void deleteImpl(int startIndex, int endIndex, int len) {
        System.arraycopy(this.buffer, endIndex, this.buffer, startIndex, this.size - endIndex);
        this.size -= len;
    }

    public boolean endsWith(String str) {
        if (str == null) {
            return false;
        }
        int len = str.length();
        if (len == 0) {
            return true;
        }
        if (len > this.size) {
            return false;
        }
        int pos = this.size - len;
        int i = 0;
        while (i < len) {
            if (this.buffer[pos] != str.charAt(i)) {
                return false;
            }
            i++;
            pos++;
        }
        return true;
    }

    public StrBuilder ensureCapacity(final int capacity) {
        if (capacity > this.buffer.length) {
            this.buffer = (char[]) ArrayUtils.arraycopy(this.buffer, 0, 0, this.size, (Supplier<char[]>) new Supplier() { // from class: org.apache.commons.lang3.text.StrBuilder$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return StrBuilder.lambda$ensureCapacity$0(capacity);
                }
            });
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ char[] lambda$ensureCapacity$0(int capacity) {
        return new char[capacity * 2];
    }

    public boolean equals(Object obj) {
        return (obj instanceof StrBuilder) && equals((StrBuilder) obj);
    }

    public boolean equals(StrBuilder other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.size != other.size) {
            return false;
        }
        char[] thisBuf = this.buffer;
        char[] otherBuf = other.buffer;
        for (int i = this.size - 1; i >= 0; i--) {
            if (thisBuf[i] != otherBuf[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean equalsIgnoreCase(StrBuilder other) {
        if (this == other) {
            return true;
        }
        if (this.size != other.size) {
            return false;
        }
        char[] thisBuf = this.buffer;
        char[] otherBuf = other.buffer;
        for (int i = this.size - 1; i >= 0; i--) {
            char c1 = thisBuf[i];
            char c2 = otherBuf[i];
            if (c1 != c2 && Character.toUpperCase(c1) != Character.toUpperCase(c2)) {
                return false;
            }
        }
        return true;
    }

    public char[] getChars(char[] destination) {
        int len = length();
        if (destination == null || destination.length < len) {
            destination = new char[len];
        }
        return (char[]) ArrayUtils.arraycopy(this.buffer, 0, destination, 0, len);
    }

    public void getChars(int startIndex, int endIndex, char[] destination, int destinationIndex) {
        if (startIndex < 0) {
            throw new StringIndexOutOfBoundsException(startIndex);
        }
        if (endIndex < 0 || endIndex > length()) {
            throw new StringIndexOutOfBoundsException(endIndex);
        }
        if (startIndex > endIndex) {
            throw new StringIndexOutOfBoundsException("end < start");
        }
        System.arraycopy(this.buffer, startIndex, destination, destinationIndex, endIndex - startIndex);
    }

    public String getNewLineText() {
        return this.newLine;
    }

    public String getNullText() {
        return this.nullText;
    }

    public int hashCode() {
        char[] buf = this.buffer;
        int hash = 0;
        for (int i = this.size - 1; i >= 0; i--) {
            hash = (hash * 31) + buf[i];
        }
        return hash;
    }

    public int indexOf(char ch) {
        return indexOf(ch, 0);
    }

    public int indexOf(char ch, int startIndex) {
        int startIndex2 = Math.max(startIndex, 0);
        if (startIndex2 >= this.size) {
            return -1;
        }
        char[] thisBuf = this.buffer;
        for (int i = startIndex2; i < this.size; i++) {
            if (thisBuf[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    public int indexOf(String str) {
        return indexOf(str, 0);
    }

    public int indexOf(String str, int startIndex) {
        return Strings.CS.indexOf(this, str, startIndex);
    }

    public int indexOf(StrMatcher matcher) {
        return indexOf(matcher, 0);
    }

    public int indexOf(StrMatcher matcher, int startIndex) {
        int startIndex2 = Math.max(startIndex, 0);
        if (matcher == null || startIndex2 >= this.size) {
            return -1;
        }
        int len = this.size;
        char[] buf = this.buffer;
        for (int i = startIndex2; i < len; i++) {
            if (matcher.isMatch(buf, i, startIndex2, len) > 0) {
                return i;
            }
        }
        return -1;
    }

    public StrBuilder insert(int index, boolean value) {
        validateIndex(index);
        if (value) {
            ensureCapacity(this.size + 4);
            System.arraycopy(this.buffer, index, this.buffer, index + 4, this.size - index);
            int index2 = index + 1;
            this.buffer[index] = 't';
            int index3 = index2 + 1;
            this.buffer[index2] = 'r';
            this.buffer[index3] = 'u';
            this.buffer[index3 + 1] = 'e';
            this.size += 4;
        } else {
            ensureCapacity(this.size + 5);
            System.arraycopy(this.buffer, index, this.buffer, index + 5, this.size - index);
            int index4 = index + 1;
            this.buffer[index] = 'f';
            int index5 = index4 + 1;
            this.buffer[index4] = 'a';
            int index6 = index5 + 1;
            this.buffer[index5] = 'l';
            this.buffer[index6] = 's';
            this.buffer[index6 + 1] = 'e';
            this.size += 5;
        }
        return this;
    }

    public StrBuilder insert(int index, char value) {
        validateIndex(index);
        ensureCapacity(this.size + 1);
        System.arraycopy(this.buffer, index, this.buffer, index + 1, this.size - index);
        this.buffer[index] = value;
        this.size++;
        return this;
    }

    public StrBuilder insert(int index, char[] chars) {
        validateIndex(index);
        if (chars == null) {
            return insert(index, this.nullText);
        }
        int len = chars.length;
        if (len > 0) {
            ensureCapacity(this.size + len);
            System.arraycopy(this.buffer, index, this.buffer, index + len, this.size - index);
            System.arraycopy(chars, 0, this.buffer, index, len);
            this.size += len;
        }
        return this;
    }

    public StrBuilder insert(int index, char[] chars, int offset, int length) {
        validateIndex(index);
        if (chars == null) {
            return insert(index, this.nullText);
        }
        if (offset < 0 || offset > chars.length) {
            throw new StringIndexOutOfBoundsException("Invalid offset: " + offset);
        }
        if (length < 0 || offset + length > chars.length) {
            throw new StringIndexOutOfBoundsException("Invalid length: " + length);
        }
        if (length > 0) {
            ensureCapacity(this.size + length);
            System.arraycopy(this.buffer, index, this.buffer, index + length, this.size - index);
            System.arraycopy(chars, offset, this.buffer, index, length);
            this.size += length;
        }
        return this;
    }

    public StrBuilder insert(int index, double value) {
        return insert(index, String.valueOf(value));
    }

    public StrBuilder insert(int index, float value) {
        return insert(index, String.valueOf(value));
    }

    public StrBuilder insert(int index, int value) {
        return insert(index, String.valueOf(value));
    }

    public StrBuilder insert(int index, long value) {
        return insert(index, String.valueOf(value));
    }

    public StrBuilder insert(int index, Object obj) {
        if (obj == null) {
            return insert(index, this.nullText);
        }
        return insert(index, obj.toString());
    }

    public StrBuilder insert(int index, String str) {
        int strLen;
        validateIndex(index);
        if (str == null) {
            str = this.nullText;
        }
        if (str != null && (strLen = str.length()) > 0) {
            int newSize = this.size + strLen;
            ensureCapacity(newSize);
            System.arraycopy(this.buffer, index, this.buffer, index + strLen, this.size - index);
            this.size = newSize;
            str.getChars(0, strLen, this.buffer, index);
        }
        return this;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isNotEmpty() {
        return this.size > 0;
    }

    public int lastIndexOf(char ch) {
        return lastIndexOf(ch, this.size - 1);
    }

    public int lastIndexOf(char ch, int startIndex) {
        int startIndex2 = startIndex >= this.size ? this.size - 1 : startIndex;
        if (startIndex2 < 0) {
            return -1;
        }
        for (int i = startIndex2; i >= 0; i--) {
            if (this.buffer[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(String str) {
        return lastIndexOf(str, this.size - 1);
    }

    public int lastIndexOf(String str, int startIndex) {
        return Strings.CS.lastIndexOf(this, str, startIndex);
    }

    public int lastIndexOf(StrMatcher matcher) {
        return lastIndexOf(matcher, this.size);
    }

    public int lastIndexOf(StrMatcher matcher, int startIndex) {
        int startIndex2 = startIndex >= this.size ? this.size - 1 : startIndex;
        if (matcher == null || startIndex2 < 0) {
            return -1;
        }
        char[] buf = this.buffer;
        int endIndex = startIndex2 + 1;
        for (int i = startIndex2; i >= 0; i--) {
            if (matcher.isMatch(buf, i, 0, endIndex) > 0) {
                return i;
            }
        }
        return -1;
    }

    public String leftString(int length) {
        if (length <= 0) {
            return "";
        }
        if (length >= this.size) {
            return new String(this.buffer, 0, this.size);
        }
        return new String(this.buffer, 0, length);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.size;
    }

    public String midString(int index, int length) {
        if (index < 0) {
            index = 0;
        }
        if (length <= 0 || index >= this.size) {
            return "";
        }
        if (this.size <= index + length) {
            return new String(this.buffer, index, this.size - index);
        }
        return new String(this.buffer, index, length);
    }

    public StrBuilder minimizeCapacity() {
        if (this.buffer.length > length()) {
            this.buffer = (char[]) ArrayUtils.arraycopy(this.buffer, 0, 0, this.size, (Supplier<char[]>) new Supplier() { // from class: org.apache.commons.lang3.text.StrBuilder$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return StrBuilder.this.m2199x4aa5ba69();
                }
            });
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$minimizeCapacity$1$org-apache-commons-lang3-text-StrBuilder, reason: not valid java name */
    public /* synthetic */ char[] m2199x4aa5ba69() {
        return new char[length()];
    }

    public int readFrom(Readable readable) throws IOException {
        int oldSize = this.size;
        if (readable instanceof Reader) {
            Reader r = (Reader) readable;
            ensureCapacity(this.size + 1);
            while (true) {
                int read = r.read(this.buffer, this.size, this.buffer.length - this.size);
                if (read == -1) {
                    break;
                }
                this.size += read;
                ensureCapacity(this.size + 1);
            }
        } else if (readable instanceof CharBuffer) {
            CharBuffer cb = (CharBuffer) readable;
            int remaining = cb.remaining();
            ensureCapacity(this.size + remaining);
            cb.get(this.buffer, this.size, remaining);
            this.size += remaining;
        } else {
            while (true) {
                ensureCapacity(this.size + 1);
                CharBuffer buf = CharBuffer.wrap(this.buffer, this.size, this.buffer.length - this.size);
                int read2 = readable.read(buf);
                if (read2 == -1) {
                    break;
                }
                this.size += read2;
            }
        }
        return this.size - oldSize;
    }

    public StrBuilder replace(int startIndex, int endIndex, String replaceStr) {
        int endIndex2 = validateRange(startIndex, endIndex);
        int insertLen = StringUtils.length(replaceStr);
        replaceImpl(startIndex, endIndex2, endIndex2 - startIndex, replaceStr, insertLen);
        return this;
    }

    public StrBuilder replace(StrMatcher matcher, String replaceStr, int startIndex, int endIndex, int replaceCount) {
        return replaceImpl(matcher, replaceStr, startIndex, validateRange(startIndex, endIndex), replaceCount);
    }

    public StrBuilder replaceAll(char search, char replace) {
        if (search != replace) {
            for (int i = 0; i < this.size; i++) {
                if (this.buffer[i] == search) {
                    this.buffer[i] = replace;
                }
            }
        }
        return this;
    }

    public StrBuilder replaceAll(String searchStr, String replaceStr) {
        int searchLen = StringUtils.length(searchStr);
        if (searchLen <= 0) {
            return this;
        }
        int replaceLen = StringUtils.length(replaceStr);
        int index = indexOf(searchStr, 0);
        while (index >= 0) {
            String replaceStr2 = replaceStr;
            replaceImpl(index, index + searchLen, searchLen, replaceStr2, replaceLen);
            index = indexOf(searchStr, index + replaceLen);
            replaceStr = replaceStr2;
        }
        return this;
    }

    public StrBuilder replaceAll(StrMatcher matcher, String replaceStr) {
        return replace(matcher, replaceStr, 0, this.size, -1);
    }

    public StrBuilder replaceFirst(char search, char replace) {
        if (search != replace) {
            int i = 0;
            while (true) {
                if (i >= this.size) {
                    break;
                }
                if (this.buffer[i] != search) {
                    i++;
                } else {
                    this.buffer[i] = replace;
                    break;
                }
            }
        }
        return this;
    }

    public StrBuilder replaceFirst(String searchStr, String replaceStr) {
        int searchLen = StringUtils.length(searchStr);
        if (searchLen <= 0) {
            return this;
        }
        int index = indexOf(searchStr, 0);
        if (index < 0) {
            return this;
        }
        int replaceLen = StringUtils.length(replaceStr);
        replaceImpl(index, index + searchLen, searchLen, replaceStr, replaceLen);
        return this;
    }

    public StrBuilder replaceFirst(StrMatcher matcher, String replaceStr) {
        return replace(matcher, replaceStr, 0, this.size, 1);
    }

    private void replaceImpl(int startIndex, int endIndex, int removeLen, String insertStr, int insertLen) {
        int newSize = (this.size - removeLen) + insertLen;
        if (insertLen != removeLen) {
            ensureCapacity(newSize);
            System.arraycopy(this.buffer, endIndex, this.buffer, startIndex + insertLen, this.size - endIndex);
            this.size = newSize;
        }
        if (insertLen > 0) {
            insertStr.getChars(0, insertLen, this.buffer, startIndex);
        }
    }

    private StrBuilder replaceImpl(StrMatcher matcher, String replaceStr, int from, int to, int replaceCount) {
        String replaceStr2;
        if (matcher == null) {
            return this;
        }
        if (this.size == 0) {
            return this;
        }
        int replaceLen = StringUtils.length(replaceStr);
        int i = from;
        while (i < to && replaceCount != 0) {
            char[] buf = this.buffer;
            int removeLen = matcher.isMatch(buf, i, from, to);
            if (removeLen > 0) {
                replaceStr2 = replaceStr;
                replaceImpl(i, i + removeLen, removeLen, replaceStr2, replaceLen);
                int to2 = (to - removeLen) + replaceLen;
                int to3 = i + replaceLen;
                i = to3 - 1;
                if (replaceCount <= 0) {
                    to = to2;
                } else {
                    replaceCount--;
                    to = to2;
                }
            } else {
                replaceStr2 = replaceStr;
            }
            i++;
            replaceStr = replaceStr2;
        }
        return this;
    }

    public StrBuilder reverse() {
        if (this.size == 0) {
            return this;
        }
        int half = this.size / 2;
        char[] buf = this.buffer;
        int leftIdx = 0;
        int rightIdx = this.size - 1;
        while (leftIdx < half) {
            char swap = buf[leftIdx];
            buf[leftIdx] = buf[rightIdx];
            buf[rightIdx] = swap;
            leftIdx++;
            rightIdx--;
        }
        return this;
    }

    public String rightString(int length) {
        if (length <= 0) {
            return "";
        }
        if (length >= this.size) {
            return new String(this.buffer, 0, this.size);
        }
        return new String(this.buffer, this.size - length, length);
    }

    public StrBuilder setCharAt(int index, char ch) {
        if (index < 0 || index >= length()) {
            throw new StringIndexOutOfBoundsException(index);
        }
        this.buffer[index] = ch;
        return this;
    }

    public StrBuilder setLength(int length) {
        if (length < 0) {
            throw new StringIndexOutOfBoundsException(length);
        }
        if (length < this.size) {
            this.size = length;
        } else if (length > this.size) {
            ensureCapacity(length);
            Arrays.fill(this.buffer, this.size, length, (char) 0);
            this.size = length;
        }
        return this;
    }

    public StrBuilder setNewLineText(String newLine) {
        this.newLine = newLine;
        return this;
    }

    public StrBuilder setNullText(String nullText) {
        if (StringUtils.isEmpty(nullText)) {
            nullText = null;
        }
        this.nullText = nullText;
        return this;
    }

    public int size() {
        return this.size;
    }

    public boolean startsWith(String str) {
        if (str == null) {
            return false;
        }
        int len = str.length();
        if (len == 0) {
            return true;
        }
        if (len > this.size) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (this.buffer[i] != str.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int startIndex, int endIndex) {
        if (startIndex < 0) {
            throw new StringIndexOutOfBoundsException(startIndex);
        }
        if (endIndex > this.size) {
            throw new StringIndexOutOfBoundsException(endIndex);
        }
        if (startIndex > endIndex) {
            throw new StringIndexOutOfBoundsException(endIndex - startIndex);
        }
        return substring(startIndex, endIndex);
    }

    public String substring(int start) {
        return substring(start, this.size);
    }

    public String substring(int startIndex, int endIndex) {
        return new String(this.buffer, startIndex, validateRange(startIndex, endIndex) - startIndex);
    }

    public char[] toCharArray() {
        if (this.size == 0) {
            return ArrayUtils.EMPTY_CHAR_ARRAY;
        }
        return (char[]) ArrayUtils.arraycopy(this.buffer, 0, 0, this.size, (Function<Integer, char[]>) new Function() { // from class: org.apache.commons.lang3.text.StrBuilder$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return StrBuilder.lambda$toCharArray$2(((Integer) obj).intValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ char[] lambda$toCharArray$2(int x$0) {
        return new char[x$0];
    }

    public char[] toCharArray(int startIndex, int endIndex) {
        int len = validateRange(startIndex, endIndex) - startIndex;
        if (len == 0) {
            return ArrayUtils.EMPTY_CHAR_ARRAY;
        }
        return (char[]) ArrayUtils.arraycopy(this.buffer, startIndex, 0, len, (Function<Integer, char[]>) new Function() { // from class: org.apache.commons.lang3.text.StrBuilder$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return StrBuilder.lambda$toCharArray$3(((Integer) obj).intValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ char[] lambda$toCharArray$3(int x$0) {
        return new char[x$0];
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return new String(this.buffer, 0, this.size);
    }

    public StringBuffer toStringBuffer() {
        return new StringBuffer(this.size).append(this.buffer, 0, this.size);
    }

    public StringBuilder toStringBuilder() {
        return new StringBuilder(this.size).append(this.buffer, 0, this.size);
    }

    public StrBuilder trim() {
        if (this.size == 0) {
            return this;
        }
        int len = this.size;
        char[] buf = this.buffer;
        int pos = 0;
        while (pos < len && buf[pos] <= ' ') {
            pos++;
        }
        while (pos < len && buf[len - 1] <= ' ') {
            len--;
        }
        if (len < this.size) {
            delete(len, this.size);
        }
        if (pos > 0) {
            delete(0, pos);
        }
        return this;
    }

    protected void validateIndex(int index) {
        if (index < 0 || index > this.size) {
            throw new StringIndexOutOfBoundsException(index);
        }
    }

    protected int validateRange(int startIndex, int endIndex) {
        if (startIndex < 0) {
            throw new StringIndexOutOfBoundsException(startIndex);
        }
        if (endIndex > this.size) {
            endIndex = this.size;
        }
        if (startIndex > endIndex) {
            throw new StringIndexOutOfBoundsException("end < start");
        }
        return endIndex;
    }
}
