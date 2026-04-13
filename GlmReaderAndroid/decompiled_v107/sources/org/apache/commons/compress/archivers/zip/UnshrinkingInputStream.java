package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import org.apache.commons.compress.compressors.lzw.LZWInputStream;

/* loaded from: classes9.dex */
final class UnshrinkingInputStream extends LZWInputStream {
    private static final int MAX_CODE_SIZE = 13;
    private static final int MAX_TABLE_SIZE = 8192;
    private final boolean[] isUsed;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UnshrinkingInputStream(InputStream inputStream) {
        super(inputStream, ByteOrder.LITTLE_ENDIAN);
        setClearCode(9);
        initializeTables(13);
        this.isUsed = new boolean[getPrefixesLength()];
        for (int i = 0; i < 256; i++) {
            this.isUsed[i] = true;
        }
        int i2 = getClearCode();
        setTableSize(i2 + 1);
    }

    @Override // org.apache.commons.compress.compressors.lzw.LZWInputStream
    protected int addEntry(int previousCode, byte character) throws IOException {
        int tableSize = getTableSize();
        while (tableSize < 8192 && this.isUsed[tableSize]) {
            tableSize++;
        }
        setTableSize(tableSize);
        int idx = addEntry(previousCode, character, 8192);
        if (idx >= 0) {
            this.isUsed[idx] = true;
        }
        return idx;
    }

    @Override // org.apache.commons.compress.compressors.lzw.LZWInputStream
    protected int decompressNextSymbol() throws IOException {
        int code = readNextCode();
        if (code < 0) {
            return -1;
        }
        if (code != getClearCode()) {
            boolean addedUnfinishedEntry = false;
            int effectiveCode = code;
            if (!this.isUsed[code]) {
                effectiveCode = addRepeatOfPreviousCode();
                addedUnfinishedEntry = true;
            }
            return expandCodeToOutputStack(effectiveCode, addedUnfinishedEntry);
        }
        int subCode = readNextCode();
        if (subCode < 0) {
            throw new IOException("Unexpected EOF;");
        }
        if (subCode == 1) {
            if (getCodeSize() >= 13) {
                throw new IOException("Attempt to increase code size beyond maximum");
            }
            incrementCodeSize();
            return 0;
        }
        if (subCode == 2) {
            partialClear();
            setTableSize(getClearCode() + 1);
            return 0;
        }
        throw new IOException("Invalid clear code subcode " + subCode);
    }

    private void partialClear() {
        boolean[] isParent = new boolean[8192];
        for (int i = 0; i < this.isUsed.length; i++) {
            if (this.isUsed[i] && getPrefix(i) != -1) {
                isParent[getPrefix(i)] = true;
            }
        }
        int i2 = getClearCode();
        for (int i3 = i2 + 1; i3 < isParent.length; i3++) {
            if (!isParent[i3]) {
                this.isUsed[i3] = false;
                setPrefix(i3, -1);
            }
        }
    }
}
