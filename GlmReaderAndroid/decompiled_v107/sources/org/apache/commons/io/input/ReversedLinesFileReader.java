package org.apache.commons.io.input;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileSystem;
import org.apache.commons.io.StandardLineSeparator;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.function.IOIterable;
import org.apache.commons.io.function.IOIterator;

/* loaded from: classes9.dex */
public class ReversedLinesFileReader implements Closeable, IOIterable<String> {
    private static final int DEFAULT_BLOCK_SIZE = FileSystem.getCurrent().getBlockSize();
    private static final String EMPTY_STRING = "";
    private final int avoidNewlineSplitBufferSize;
    private final int blockSize;
    private final int byteDecrement;
    private final SeekableByteChannel channel;
    private final Charset charset;
    private FilePart currentFilePart;
    private final byte[][] newLineSequences;
    private final long totalBlockCount;
    private final long totalByteLength;
    private boolean trailingNewlineOfFileSkipped;

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<ReversedLinesFileReader, Builder> {
        public Builder() {
            setBufferSizeDefault(ReversedLinesFileReader.DEFAULT_BLOCK_SIZE);
            setBufferSize(ReversedLinesFileReader.DEFAULT_BLOCK_SIZE);
            setOpenOptions(StandardOpenOption.READ);
        }

        @Override // org.apache.commons.io.function.IOSupplier
        public ReversedLinesFileReader get() throws IOException {
            return new ReversedLinesFileReader(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public final class FilePart {
        private int currentLastBytePos;
        private final byte[] data;
        private byte[] leftOver;
        private final long partNumber;

        private FilePart(long partNumber, int length, byte[] leftOverOfLastFilePart) throws IOException {
            this.partNumber = partNumber;
            int dataLength = (leftOverOfLastFilePart != null ? leftOverOfLastFilePart.length : 0) + length;
            this.data = new byte[dataLength];
            long off = (partNumber - 1) * ReversedLinesFileReader.this.blockSize;
            if (partNumber > 0) {
                ReversedLinesFileReader.this.channel.position(off);
                int countRead = ReversedLinesFileReader.this.channel.read(ByteBuffer.wrap(this.data, 0, length));
                if (countRead != length) {
                    throw new IllegalStateException("Count of requested bytes and actually read bytes don't match");
                }
            }
            if (leftOverOfLastFilePart != null) {
                System.arraycopy(leftOverOfLastFilePart, 0, this.data, length, leftOverOfLastFilePart.length);
            }
            this.currentLastBytePos = this.data.length - 1;
            this.leftOver = null;
        }

        private void createLeftOver() {
            int lineLengthBytes = this.currentLastBytePos + 1;
            if (lineLengthBytes > 0) {
                this.leftOver = Arrays.copyOf(this.data, lineLengthBytes);
            } else {
                this.leftOver = null;
            }
            this.currentLastBytePos = -1;
        }

        private int getNewLineMatchByteCount(byte[] data, int i) {
            for (byte[] newLineSequence : ReversedLinesFileReader.this.newLineSequences) {
                boolean match = true;
                for (int j = newLineSequence.length - 1; j >= 0; j--) {
                    int k = (i + j) - (newLineSequence.length - 1);
                    match &= k >= 0 && data[k] == newLineSequence[j];
                }
                if (match) {
                    return newLineSequence.length;
                }
            }
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String readLine() {
            String line = null;
            boolean isLastFilePart = this.partNumber == 1;
            int i = this.currentLastBytePos;
            while (true) {
                if (i > -1) {
                    if (!isLastFilePart && i < ReversedLinesFileReader.this.avoidNewlineSplitBufferSize) {
                        createLeftOver();
                        break;
                    }
                    int newLineMatchByteCount = getNewLineMatchByteCount(this.data, i);
                    if (newLineMatchByteCount <= 0) {
                        i -= ReversedLinesFileReader.this.byteDecrement;
                        if (i < 0) {
                            createLeftOver();
                            break;
                        }
                    } else {
                        int lineStart = i + 1;
                        int lineLengthBytes = (this.currentLastBytePos - lineStart) + 1;
                        if (lineLengthBytes < 0) {
                            throw new IllegalStateException("Unexpected negative line length=" + lineLengthBytes);
                        }
                        byte[] lineData = Arrays.copyOfRange(this.data, lineStart, lineStart + lineLengthBytes);
                        line = new String(lineData, ReversedLinesFileReader.this.charset);
                        this.currentLastBytePos = i - newLineMatchByteCount;
                    }
                } else {
                    break;
                }
            }
            if (isLastFilePart && this.leftOver != null) {
                String line2 = new String(this.leftOver, ReversedLinesFileReader.this.charset);
                this.leftOver = null;
                return line2;
            }
            return line;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public FilePart rollOver() throws IOException {
            if (this.currentLastBytePos > -1) {
                throw new IllegalStateException("Current currentLastCharPos unexpectedly positive... last readLine() should have returned something! currentLastCharPos=" + this.currentLastBytePos);
            }
            if (this.partNumber > 1) {
                return new FilePart(this.partNumber - 1, ReversedLinesFileReader.this.blockSize, this.leftOver);
            }
            if (this.leftOver != null) {
                throw new IllegalStateException("Unexpected leftover of the last block: leftOverOfThisFilePart=" + new String(this.leftOver, ReversedLinesFileReader.this.charset));
            }
            return null;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private ReversedLinesFileReader(Builder builder) throws IOException {
        int lastBlockLength;
        this.blockSize = builder.getBufferSize();
        this.charset = Charsets.toCharset(builder.getCharset());
        CharsetEncoder charsetEncoder = this.charset.newEncoder();
        float maxBytesPerChar = charsetEncoder.maxBytesPerChar();
        if (maxBytesPerChar == 1.0f || this.charset == StandardCharsets.UTF_8) {
            this.byteDecrement = 1;
        } else if (this.charset == Charset.forName("Shift_JIS") || this.charset == Charset.forName("windows-31j") || this.charset == Charset.forName("x-windows-949") || this.charset == Charset.forName("gbk") || this.charset == Charset.forName("x-windows-950")) {
            this.byteDecrement = 1;
        } else if (this.charset == StandardCharsets.UTF_16BE || this.charset == StandardCharsets.UTF_16LE) {
            this.byteDecrement = 2;
        } else {
            if (this.charset == StandardCharsets.UTF_16) {
                throw new UnsupportedEncodingException("For UTF-16, you need to specify the byte order (use UTF-16BE or UTF-16LE)");
            }
            throw new UnsupportedEncodingException("Encoding " + this.charset + " is not supported yet (feel free to submit a patch)");
        }
        this.newLineSequences = new byte[][]{StandardLineSeparator.CRLF.getBytes(this.charset), StandardLineSeparator.LF.getBytes(this.charset), StandardLineSeparator.CR.getBytes(this.charset)};
        this.avoidNewlineSplitBufferSize = this.newLineSequences[0].length;
        this.channel = (SeekableByteChannel) builder.getChannel(SeekableByteChannel.class);
        this.totalByteLength = this.channel.size();
        int lastBlockLength2 = (int) (this.totalByteLength % this.blockSize);
        if (lastBlockLength2 > 0) {
            this.totalBlockCount = (this.totalByteLength / this.blockSize) + 1;
        } else {
            this.totalBlockCount = this.totalByteLength / this.blockSize;
            if (this.totalByteLength > 0) {
                lastBlockLength = this.blockSize;
                this.currentFilePart = new FilePart(this.totalBlockCount, lastBlockLength, null);
            }
        }
        lastBlockLength = lastBlockLength2;
        this.currentFilePart = new FilePart(this.totalBlockCount, lastBlockLength, null);
    }

    @Deprecated
    public ReversedLinesFileReader(File file) throws IOException {
        this(file, DEFAULT_BLOCK_SIZE, Charset.defaultCharset());
    }

    @Deprecated
    public ReversedLinesFileReader(File file, Charset charset) throws IOException {
        this(file.toPath(), charset);
    }

    @Deprecated
    public ReversedLinesFileReader(File file, int blockSize, Charset charset) throws IOException {
        this(file.toPath(), blockSize, charset);
    }

    @Deprecated
    public ReversedLinesFileReader(File file, int blockSize, String charsetName) throws IOException {
        this(file.toPath(), blockSize, charsetName);
    }

    @Deprecated
    public ReversedLinesFileReader(Path file, Charset charset) throws IOException {
        this(file, DEFAULT_BLOCK_SIZE, charset);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public ReversedLinesFileReader(Path file, int blockSize, Charset charset) throws IOException {
        this(((Builder) builder().setPath(file)).setBufferSize(blockSize).setCharset(charset));
    }

    @Deprecated
    public ReversedLinesFileReader(Path file, int blockSize, String charsetName) throws IOException {
        this(file, blockSize, Charsets.toCharset(charsetName));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.channel.close();
    }

    @Override // org.apache.commons.io.function.IOIterable
    public IOIterator<String> iterator() {
        return new IOIterator<String>() { // from class: org.apache.commons.io.input.ReversedLinesFileReader.1
            private String next;

            @Override // org.apache.commons.io.function.IOIterator
            public boolean hasNext() throws IOException {
                if (this.next == null) {
                    this.next = ReversedLinesFileReader.this.readLine();
                }
                return this.next != null;
            }

            @Override // org.apache.commons.io.function.IOIterator
            public String next() throws IOException {
                if (this.next == null) {
                    this.next = ReversedLinesFileReader.this.readLine();
                }
                String tmp = this.next;
                this.next = null;
                return tmp;
            }

            @Override // org.apache.commons.io.function.IOIterator
            public Iterator<String> unwrap() {
                return null;
            }
        };
    }

    public String readLine() throws IOException {
        String line = this.currentFilePart.readLine();
        while (line == null) {
            this.currentFilePart = this.currentFilePart.rollOver();
            if (this.currentFilePart == null) {
                break;
            }
            line = this.currentFilePart.readLine();
        }
        if ("".equals(line) && !this.trailingNewlineOfFileSkipped) {
            this.trailingNewlineOfFileSkipped = true;
            String line2 = readLine();
            return line2;
        }
        return line;
    }

    public List<String> readLines(int lineCount) throws IOException {
        if (lineCount < 0) {
            throw new IllegalArgumentException("lineCount < 0");
        }
        ArrayList<String> arrayList = new ArrayList<>(lineCount);
        for (int i = 0; i < lineCount; i++) {
            String line = readLine();
            if (line == null) {
                return arrayList;
            }
            arrayList.add(line);
        }
        return arrayList;
    }

    public String toString(int lineCount) throws IOException {
        List<String> lines = readLines(lineCount);
        Collections.reverse(lines);
        return lines.isEmpty() ? "" : String.join(System.lineSeparator(), lines) + System.lineSeparator();
    }

    @Override // org.apache.commons.io.function.IOIterable
    public Iterable<String> unwrap() {
        return null;
    }
}
