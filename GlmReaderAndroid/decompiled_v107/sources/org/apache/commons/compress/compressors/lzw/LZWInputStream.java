package org.apache.commons.compress.compressors.lzw;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import kotlin.UByte;
import org.apache.commons.compress.MemoryLimitException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.BitInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;

/* loaded from: classes9.dex */
public abstract class LZWInputStream extends CompressorInputStream implements InputStreamStatistics {
    protected static final int DEFAULT_CODE_SIZE = 9;
    private static final int MAX_CODE_SIZE = 31;
    protected static final int UNUSED_PREFIX = -1;
    private byte[] characters;
    protected final BitInputStream in;
    private byte[] outputStack;
    private int outputStackLocation;
    private int[] prefixes;
    private byte previousCodeFirstChar;
    private int tableSize;
    private final byte[] oneByte = new byte[1];
    private int clearCode = -1;
    private int codeSize = 9;
    private int previousCode = -1;

    protected abstract int addEntry(int i, byte b) throws IOException;

    protected abstract int decompressNextSymbol() throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public LZWInputStream(InputStream inputStream, ByteOrder byteOrder) {
        this.in = new BitInputStream(inputStream, byteOrder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int addEntry(int previousCode, byte character, int maxTableSize) {
        if (this.tableSize < maxTableSize) {
            this.prefixes[this.tableSize] = previousCode;
            this.characters[this.tableSize] = character;
            int i = this.tableSize;
            this.tableSize = i + 1;
            return i;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int addRepeatOfPreviousCode() throws IOException {
        if (this.previousCode == -1) {
            throw new IOException("The first code can't be a reference to its preceding code");
        }
        return addEntry(this.previousCode, this.previousCodeFirstChar);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int expandCodeToOutputStack(int code, boolean addedUnfinishedEntry) throws IOException {
        int entry = code;
        while (entry >= 0) {
            byte[] bArr = this.outputStack;
            int i = this.outputStackLocation - 1;
            this.outputStackLocation = i;
            bArr[i] = this.characters[entry];
            entry = this.prefixes[entry];
        }
        int entry2 = this.previousCode;
        if (entry2 != -1 && !addedUnfinishedEntry) {
            addEntry(this.previousCode, this.outputStack[this.outputStackLocation]);
        }
        this.previousCode = code;
        this.previousCodeFirstChar = this.outputStack[this.outputStackLocation];
        return this.outputStackLocation;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getClearCode() {
        return this.clearCode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getCodeSize() {
        return this.codeSize;
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.in.getBytesRead();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getPrefix(int offset) {
        return this.prefixes[offset];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getPrefixesLength() {
        return this.prefixes.length;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getTableSize() {
        return this.tableSize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void incrementCodeSize() {
        this.codeSize++;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initializeTables(int maxCodeSize) {
        if ((1 << maxCodeSize) < 256 || getCodeSize() > maxCodeSize) {
            throw new IllegalArgumentException("maxCodeSize " + maxCodeSize + " is out of bounds.");
        }
        int maxTableSize = 1 << maxCodeSize;
        this.prefixes = new int[maxTableSize];
        this.characters = new byte[maxTableSize];
        this.outputStack = new byte[maxTableSize];
        this.outputStackLocation = maxTableSize;
        for (int i = 0; i < 256; i++) {
            this.prefixes[i] = -1;
            this.characters[i] = (byte) i;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initializeTables(int maxCodeSize, int memoryLimiKiB) throws MemoryLimitException {
        if (maxCodeSize <= 0) {
            throw new IllegalArgumentException("maxCodeSize is " + maxCodeSize + ", must be bigger than 0");
        }
        if (memoryLimiKiB > -1) {
            int maxTableSize = 1 << maxCodeSize;
            long memoryUsageBytes = maxTableSize * 6;
            long memoryUsageKiB = memoryUsageBytes >> 10;
            if (memoryUsageKiB > memoryLimiKiB) {
                throw new MemoryLimitException(memoryUsageKiB, memoryLimiKiB);
            }
        }
        initializeTables(maxCodeSize);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int ret = read(this.oneByte);
        if (ret < 0) {
            return ret;
        }
        return this.oneByte[0] & UByte.MAX_VALUE;
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        int bytesRead = readFromStack(b, off, len);
        while (len - bytesRead > 0) {
            int result = decompressNextSymbol();
            if (result < 0) {
                if (bytesRead > 0) {
                    count(bytesRead);
                    return bytesRead;
                }
                return result;
            }
            bytesRead += readFromStack(b, off + bytesRead, len - bytesRead);
        }
        count(bytesRead);
        return bytesRead;
    }

    private int readFromStack(byte[] b, int off, int len) {
        int remainingInStack = this.outputStack.length - this.outputStackLocation;
        if (remainingInStack > 0) {
            int maxLength = Math.min(remainingInStack, len);
            System.arraycopy(this.outputStack, this.outputStackLocation, b, off, maxLength);
            this.outputStackLocation += maxLength;
            return maxLength;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int readNextCode() throws IOException {
        if (this.codeSize > 31) {
            throw new IllegalArgumentException("Code size must not be bigger than 31");
        }
        return (int) this.in.readBits(this.codeSize);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resetCodeSize() {
        setCodeSize(9);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resetPreviousCode() {
        this.previousCode = -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setClearCode(int codeSize) {
        this.clearCode = 1 << (codeSize - 1);
    }

    protected void setCodeSize(int codeSize) {
        this.codeSize = codeSize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPrefix(int offset, int value) {
        this.prefixes[offset] = value;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }
}
