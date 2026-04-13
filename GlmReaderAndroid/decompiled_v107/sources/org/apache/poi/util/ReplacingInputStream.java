package org.apache.poi.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

@Internal
/* loaded from: classes10.dex */
public class ReplacingInputStream extends FilterInputStream {
    final int[] buf;
    private int matchedIndex;
    private final byte[] pattern;
    private int replacedIndex;
    private final byte[] replacement;
    private State state;
    private int unbufferIndex;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public enum State {
        NOT_MATCHED,
        MATCHING,
        REPLACING,
        UNBUFFER
    }

    public ReplacingInputStream(InputStream in, String pattern, String replacement) {
        this(in, pattern.getBytes(StandardCharsets.UTF_8), replacement == null ? null : replacement.getBytes(StandardCharsets.UTF_8));
    }

    public ReplacingInputStream(InputStream in, byte[] pattern, byte[] replacement) {
        super(in);
        this.state = State.NOT_MATCHED;
        if (pattern == null || pattern.length == 0) {
            throw new IllegalArgumentException("pattern length should be > 0");
        }
        this.pattern = pattern;
        this.replacement = replacement;
        this.buf = new int[pattern.length];
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return 0;
        }
        int c = read();
        if (c == -1) {
            return -1;
        }
        b[off] = (byte) c;
        int i = 1;
        while (i < len) {
            int c2 = read();
            if (c2 == -1) {
                break;
            }
            b[off + i] = (byte) c2;
            i++;
        }
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        switch (this.state) {
            case MATCHING:
                int next = super.read();
                if (this.pattern[this.matchedIndex] == next) {
                    int[] iArr = this.buf;
                    int i = this.matchedIndex;
                    this.matchedIndex = i + 1;
                    iArr[i] = next;
                    if (this.matchedIndex == this.pattern.length) {
                        if (this.replacement == null || this.replacement.length == 0) {
                            this.state = State.NOT_MATCHED;
                            this.matchedIndex = 0;
                        } else {
                            this.state = State.REPLACING;
                            this.replacedIndex = 0;
                        }
                    }
                } else {
                    int[] iArr2 = this.buf;
                    int i2 = this.matchedIndex;
                    this.matchedIndex = i2 + 1;
                    iArr2[i2] = next;
                    this.state = State.UNBUFFER;
                    this.unbufferIndex = 0;
                }
                return read();
            case REPLACING:
                byte[] bArr = this.replacement;
                int i3 = this.replacedIndex;
                this.replacedIndex = i3 + 1;
                int next2 = bArr[i3];
                if (this.replacedIndex == this.replacement.length) {
                    this.state = State.NOT_MATCHED;
                    this.replacedIndex = 0;
                }
                return next2;
            case UNBUFFER:
                int[] iArr3 = this.buf;
                int i4 = this.unbufferIndex;
                this.unbufferIndex = i4 + 1;
                int next3 = iArr3[i4];
                if (this.unbufferIndex == this.matchedIndex) {
                    this.state = State.NOT_MATCHED;
                    this.matchedIndex = 0;
                }
                return next3;
            default:
                int next4 = super.read();
                if (this.pattern[0] != next4) {
                    return next4;
                }
                Arrays.fill(this.buf, 0);
                this.matchedIndex = 0;
                int[] iArr4 = this.buf;
                int i5 = this.matchedIndex;
                this.matchedIndex = i5 + 1;
                iArr4[i5] = next4;
                if (this.pattern.length == 1) {
                    this.state = State.REPLACING;
                    this.replacedIndex = 0;
                } else {
                    this.state = State.MATCHING;
                }
                return read();
        }
    }

    public String toString() {
        return this.state.name() + StringUtils.SPACE + this.matchedIndex + StringUtils.SPACE + this.replacedIndex + StringUtils.SPACE + this.unbufferIndex;
    }
}
