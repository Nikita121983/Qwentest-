package org.apache.commons.io.input;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.util.function.IntPredicate;

/* loaded from: classes9.dex */
public abstract class AbstractCharacterFilterReader extends FilterReader {
    protected static final IntPredicate SKIP_NONE = new IntPredicate() { // from class: org.apache.commons.io.input.AbstractCharacterFilterReader$$ExternalSyntheticLambda0
        @Override // java.util.function.IntPredicate
        public final boolean test(int i) {
            return AbstractCharacterFilterReader.lambda$static$0(i);
        }
    };
    private final IntPredicate skip;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$0(int ch) {
        return false;
    }

    protected AbstractCharacterFilterReader(Reader reader) {
        this(reader, SKIP_NONE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractCharacterFilterReader(Reader reader, IntPredicate skip) {
        super(reader);
        this.skip = skip == null ? SKIP_NONE : skip;
    }

    protected boolean filter(int ch) {
        return this.skip.test(ch);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        int ch;
        do {
            ch = this.in.read();
            if (ch == -1) {
                break;
            }
        } while (filter(ch));
        return ch;
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read(char[] cbuf, int off, int len) throws IOException {
        int read = super.read(cbuf, off, len);
        if (read == -1) {
            return -1;
        }
        int pos = off - 1;
        for (int readPos = off; readPos < off + read; readPos++) {
            if (!filter(cbuf[readPos]) && (pos = pos + 1) < readPos) {
                cbuf[pos] = cbuf[readPos];
            }
        }
        int readPos2 = pos - off;
        return readPos2 + 1;
    }
}
