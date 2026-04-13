package org.apache.commons.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.channels.FileChannels;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.function.IOSupplier;
import org.apache.commons.io.function.IOTriFunction;
import org.apache.commons.io.input.BoundedInputStream;
import org.apache.commons.io.input.CharSequenceReader;
import org.apache.commons.io.input.QueueInputStream;
import org.apache.commons.io.output.AppendableWriter;
import org.apache.commons.io.output.NullOutputStream;
import org.apache.commons.io.output.NullWriter;
import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;

/* loaded from: classes9.dex */
public class IOUtils {
    public static final int CR = 13;
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    public static final char DIR_SEPARATOR_UNIX = '/';
    public static final char DIR_SEPARATOR_WINDOWS = '\\';
    public static final int EOF = -1;
    public static final int LF = 10;
    public static final int SOFT_MAX_ARRAY_LENGTH = 2147483639;
    public static final char DIR_SEPARATOR = File.separatorChar;
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    @Deprecated
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String LINE_SEPARATOR_UNIX = StandardLineSeparator.LF.getString();
    public static final String LINE_SEPARATOR_WINDOWS = StandardLineSeparator.CRLF.getString();

    static /* synthetic */ char[] access$000() {
        return charArray();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class ScratchBytes implements AutoCloseable {
        private final byte[] buffer;
        private static final ThreadLocal<Object[]> LOCAL = ThreadLocal.withInitial(new Supplier() { // from class: org.apache.commons.io.IOUtils$ScratchBytes$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return IOUtils.ScratchBytes.lambda$static$0();
            }
        });
        private static final ScratchBytes INSTANCE = new ScratchBytes(null);

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Object[] lambda$static$0() {
            return new Object[]{false, IOUtils.byteArray()};
        }

        static ScratchBytes get() {
            Object[] holder = LOCAL.get();
            if (!((Boolean) holder[0]).booleanValue()) {
                holder[0] = true;
                return INSTANCE;
            }
            return new ScratchBytes(IOUtils.byteArray());
        }

        private ScratchBytes(byte[] buffer) {
            this.buffer = buffer;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public byte[] array() {
            return this.buffer != null ? this.buffer : (byte[]) LOCAL.get()[1];
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            if (this.buffer == null) {
                Object[] holder = LOCAL.get();
                Arrays.fill((byte[]) holder[1], (byte) 0);
                holder[0] = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class ScratchChars implements AutoCloseable {
        private final char[] buffer;
        private static final ThreadLocal<Object[]> LOCAL = ThreadLocal.withInitial(new Supplier() { // from class: org.apache.commons.io.IOUtils$ScratchChars$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return IOUtils.ScratchChars.lambda$static$0();
            }
        });
        private static final ScratchChars INSTANCE = new ScratchChars(null);

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Object[] lambda$static$0() {
            return new Object[]{false, IOUtils.access$000()};
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static ScratchChars get() {
            Object[] holder = LOCAL.get();
            if (!((Boolean) holder[0]).booleanValue()) {
                holder[0] = true;
                return INSTANCE;
            }
            return new ScratchChars(IOUtils.access$000());
        }

        private ScratchChars(char[] buffer) {
            this.buffer = buffer;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public char[] array() {
            return this.buffer != null ? this.buffer : (char[]) LOCAL.get()[1];
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            if (this.buffer == null) {
                Object[] holder = LOCAL.get();
                Arrays.fill((char[]) holder[1], (char) 0);
                holder[0] = false;
            }
        }
    }

    public static BufferedInputStream buffer(InputStream inputStream) {
        Objects.requireNonNull(inputStream, "inputStream");
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream);
    }

    public static BufferedInputStream buffer(InputStream inputStream, int size) {
        Objects.requireNonNull(inputStream, "inputStream");
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream, size);
    }

    public static BufferedOutputStream buffer(OutputStream outputStream) {
        Objects.requireNonNull(outputStream, "outputStream");
        return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream);
    }

    public static BufferedOutputStream buffer(OutputStream outputStream, int size) {
        Objects.requireNonNull(outputStream, "outputStream");
        return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream, size);
    }

    public static BufferedReader buffer(Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
    }

    public static BufferedReader buffer(Reader reader, int size) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, size);
    }

    public static BufferedWriter buffer(Writer writer) {
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer);
    }

    public static BufferedWriter buffer(Writer writer, int size) {
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer, size);
    }

    public static byte[] byteArray() {
        return byteArray(8192);
    }

    public static byte[] byteArray(int size) {
        return new byte[size];
    }

    private static char[] charArray() {
        return charArray(8192);
    }

    private static char[] charArray(int size) {
        return new char[size];
    }

    public static void checkFromIndexSize(byte[] array, int off, int len) {
        checkFromIndexSize(off, len, ((byte[]) Objects.requireNonNull(array, "byte array")).length);
    }

    public static void checkFromIndexSize(char[] array, int off, int len) {
        checkFromIndexSize(off, len, ((char[]) Objects.requireNonNull(array, "char array")).length);
    }

    static void checkFromIndexSize(int off, int len, int arrayLength) {
        if ((off | len | arrayLength) < 0 || arrayLength - len < off) {
            throw new IndexOutOfBoundsException(String.format("Range [%s, %<s + %s) out of bounds for length %s", Integer.valueOf(off), Integer.valueOf(len), Integer.valueOf(arrayLength)));
        }
    }

    public static void checkFromIndexSize(String str, int off, int len) {
        checkFromIndexSize(off, len, ((String) Objects.requireNonNull(str, "str")).length());
    }

    public static void checkFromToIndex(CharSequence seq, int fromIndex, int toIndex) {
        checkFromToIndex(fromIndex, toIndex, seq != null ? seq.length() : 4);
    }

    static void checkFromToIndex(int fromIndex, int toIndex, int length) {
        if (fromIndex < 0 || toIndex < fromIndex || length < toIndex) {
            throw new IndexOutOfBoundsException(String.format("Range [%s, %s) out of bounds for length %s", Integer.valueOf(fromIndex), Integer.valueOf(toIndex), Integer.valueOf(length)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clear() {
        ScratchBytes.LOCAL.remove();
        ScratchChars.LOCAL.remove();
    }

    public static void close(Closeable closeable) throws IOException {
        if (closeable != null) {
            closeable.close();
        }
    }

    public static void close(Closeable... closeables) throws IOExceptionList {
        IOConsumer.forAll(new IOConsumer() { // from class: org.apache.commons.io.IOUtils$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                IOUtils.close((Closeable) obj);
            }
        }, closeables);
    }

    public static void close(Closeable closeable, IOConsumer<IOException> consumer) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                if (consumer != null) {
                    consumer.accept(e);
                }
            } catch (Exception e2) {
                if (consumer != null) {
                    consumer.accept(new IOException(e2));
                }
            }
        }
    }

    public static void close(URLConnection conn) {
        if (conn instanceof HttpURLConnection) {
            ((HttpURLConnection) conn).disconnect();
        }
    }

    private static void closeQ(Closeable closeable) {
        closeQuietly(closeable, null);
    }

    public static void closeQuietly(Closeable closeable) {
        closeQuietly(closeable, null);
    }

    public static void closeQuietly(Closeable... closeables) {
        if (closeables != null) {
            closeQuietly((Stream<Closeable>) Arrays.stream(closeables));
        }
    }

    public static void closeQuietly(Closeable closeable, Consumer<Exception> consumer) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                if (consumer != null) {
                    consumer.accept(e);
                }
            }
        }
    }

    public static void closeQuietly(InputStream input) {
        closeQ(input);
    }

    public static void closeQuietly(Iterable<Closeable> closeables) {
        if (closeables != null) {
            closeables.forEach(new IOUtils$$ExternalSyntheticLambda0());
        }
    }

    public static void closeQuietly(OutputStream output) {
        closeQ(output);
    }

    public static void closeQuietly(Reader reader) {
        closeQ(reader);
    }

    public static void closeQuietly(Selector selector) {
        closeQ(selector);
    }

    public static void closeQuietly(ServerSocket serverSocket) {
        closeQ(serverSocket);
    }

    public static void closeQuietly(Socket socket) {
        closeQ(socket);
    }

    public static void closeQuietly(Stream<Closeable> closeables) {
        if (closeables != null) {
            closeables.forEach(new IOUtils$$ExternalSyntheticLambda0());
        }
    }

    public static void closeQuietly(Writer writer) {
        closeQ(writer);
    }

    public static long consume(InputStream input) throws IOException {
        return copyLarge(input, NullOutputStream.INSTANCE);
    }

    public static long consume(Reader input) throws IOException {
        return copyLarge(input, NullWriter.INSTANCE);
    }

    public static boolean contentEquals(InputStream input1, InputStream input2) throws IOException {
        if (input1 == input2) {
            return true;
        }
        if (input1 == null || input2 == null) {
            return false;
        }
        return FileChannels.contentEquals(Channels.newChannel(input1), Channels.newChannel(input2), 8192);
    }

    private static boolean contentEquals(Iterator<?> iterator1, Iterator<?> iterator2) {
        while (iterator1.hasNext()) {
            if (!iterator2.hasNext() || !Objects.equals(iterator1.next(), iterator2.next())) {
                return false;
            }
        }
        return !iterator2.hasNext();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x001f, code lost:
    
        if (r5 == r7) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0021, code lost:
    
        r9 = r11.read(r3, r5, 8192 - r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0027, code lost:
    
        if (r9 == 0) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0029, code lost:
    
        if (r9 != (-1)) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003b, code lost:
    
        r5 = r5 + r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x002b, code lost:
    
        if (r6 != r7) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0031, code lost:
    
        if (r12.read() != (-1)) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0035, code lost:
    
        if (r2 == null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0037, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x003a, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0034, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x003c, code lost:
    
        if (r6 != r7) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0040, code lost:
    
        r9 = r12.read(r4, r6, 8192 - r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0044, code lost:
    
        if (r9 == 0) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0046, code lost:
    
        if (r9 != (-1)) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0058, code lost:
    
        r6 = r6 + r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0048, code lost:
    
        if (r5 != r7) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x004e, code lost:
    
        if (r11.read() != (-1)) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0052, code lost:
    
        if (r2 == null) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0054, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0057, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0051, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x005d, code lost:
    
        if (r3[r7] == r4[r7]) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0066, code lost:
    
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0060, code lost:
    
        if (r2 == null) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0062, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0065, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean contentEquals(java.io.Reader r11, java.io.Reader r12) throws java.io.IOException {
        /*
            r0 = 1
            if (r11 != r12) goto L4
            return r0
        L4:
            r1 = 0
            if (r11 == 0) goto L76
            if (r12 != 0) goto Lb
            goto L76
        Lb:
            org.apache.commons.io.IOUtils$ScratchChars r2 = org.apache.commons.io.IOUtils.ScratchChars.get()
            char[] r3 = r2.array()     // Catch: java.lang.Throwable -> L6a
            char[] r4 = charArray()     // Catch: java.lang.Throwable -> L6a
        L17:
            r5 = 0
            r6 = 0
            r7 = 0
        L1a:
            r8 = 8192(0x2000, float:1.14794E-41)
            if (r7 >= r8) goto L69
            r8 = -1
            if (r5 != r7) goto L3c
        L21:
            int r9 = 8192 - r5
            int r9 = r11.read(r3, r5, r9)     // Catch: java.lang.Throwable -> L6a
            if (r9 == 0) goto L21
            if (r9 != r8) goto L3b
            if (r6 != r7) goto L34
            int r10 = r12.read()     // Catch: java.lang.Throwable -> L6a
            if (r10 != r8) goto L34
            goto L35
        L34:
            r0 = r1
        L35:
            if (r2 == 0) goto L3a
            r2.close()
        L3a:
            return r0
        L3b:
            int r5 = r5 + r9
        L3c:
            if (r6 != r7) goto L59
        L3e:
            int r9 = 8192 - r6
            int r9 = r12.read(r4, r6, r9)     // Catch: java.lang.Throwable -> L6a
            if (r9 == 0) goto L3e
            if (r9 != r8) goto L58
            if (r5 != r7) goto L51
            int r10 = r11.read()     // Catch: java.lang.Throwable -> L6a
            if (r10 != r8) goto L51
            goto L52
        L51:
            r0 = r1
        L52:
            if (r2 == 0) goto L57
            r2.close()
        L57:
            return r0
        L58:
            int r6 = r6 + r9
        L59:
            char r8 = r3[r7]     // Catch: java.lang.Throwable -> L6a
            char r9 = r4[r7]     // Catch: java.lang.Throwable -> L6a
            if (r8 == r9) goto L66
        L60:
            if (r2 == 0) goto L65
            r2.close()
        L65:
            return r1
        L66:
            int r7 = r7 + 1
            goto L1a
        L69:
            goto L17
        L6a:
            r0 = move-exception
            if (r2 == 0) goto L75
            r2.close()     // Catch: java.lang.Throwable -> L71
            goto L75
        L71:
            r1 = move-exception
            r0.addSuppressed(r1)
        L75:
            throw r0
        L76:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.IOUtils.contentEquals(java.io.Reader, java.io.Reader):boolean");
    }

    private static boolean contentEquals(Stream<?> stream1, Stream<?> stream2) {
        if (stream1 == stream2) {
            return true;
        }
        if (stream1 == null || stream2 == null) {
            return false;
        }
        return contentEquals(stream1.iterator(), stream2.iterator());
    }

    private static boolean contentEqualsIgnoreEOL(BufferedReader reader1, BufferedReader reader2) {
        if (reader1 == reader2) {
            return true;
        }
        if (reader1 == null || reader2 == null) {
            return false;
        }
        return contentEquals(reader1.lines(), reader2.lines());
    }

    public static boolean contentEqualsIgnoreEOL(Reader reader1, Reader reader2) throws UncheckedIOException {
        if (reader1 == reader2) {
            return true;
        }
        if (reader1 == null || reader2 == null) {
            return false;
        }
        return contentEqualsIgnoreEOL(toBufferedReader(reader1), toBufferedReader(reader2));
    }

    public static int copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        long count = copyLarge(inputStream, outputStream);
        if (count > 2147483647L) {
            return -1;
        }
        return (int) count;
    }

    public static long copy(InputStream inputStream, OutputStream outputStream, int bufferSize) throws IOException {
        return copyLarge(inputStream, outputStream, byteArray(bufferSize));
    }

    @Deprecated
    public static void copy(InputStream input, Writer writer) throws IOException {
        copy(input, writer, Charset.defaultCharset());
    }

    public static void copy(InputStream input, Writer writer, Charset inputCharset) throws IOException {
        copy((Reader) new InputStreamReader(input, Charsets.toCharset(inputCharset)), writer);
    }

    public static void copy(InputStream input, Writer writer, String inputCharsetName) throws IOException {
        copy(input, writer, Charsets.toCharset(inputCharsetName));
    }

    public static QueueInputStream copy(ByteArrayOutputStream outputStream) throws IOException {
        Objects.requireNonNull(outputStream, "outputStream");
        QueueInputStream in = new QueueInputStream();
        outputStream.writeTo(in.newQueueOutputStream());
        return in;
    }

    public static long copy(Reader reader, Appendable output) throws IOException {
        return copy(reader, output, CharBuffer.allocate(8192));
    }

    public static long copy(Reader reader, Appendable output, CharBuffer buffer) throws IOException {
        long count = 0;
        while (true) {
            int n = reader.read(buffer);
            if (-1 != n) {
                buffer.flip();
                output.append(buffer, 0, n);
                count += n;
            } else {
                return count;
            }
        }
    }

    @Deprecated
    public static void copy(Reader reader, OutputStream output) throws IOException {
        copy(reader, output, Charset.defaultCharset());
    }

    public static void copy(Reader reader, OutputStream output, Charset outputCharset) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output, Charsets.toCharset(outputCharset));
        copy(reader, (Writer) writer);
        writer.flush();
    }

    public static void copy(Reader reader, OutputStream output, String outputCharsetName) throws IOException {
        copy(reader, output, Charsets.toCharset(outputCharsetName));
    }

    public static int copy(Reader reader, Writer writer) throws IOException {
        long count = copyLarge(reader, writer);
        if (count > 2147483647L) {
            return -1;
        }
        return (int) count;
    }

    public static long copy(URL url, File file) throws IOException {
        OutputStream outputStream = Files.newOutputStream(((File) Objects.requireNonNull(file, "file")).toPath(), new OpenOption[0]);
        try {
            long copy = copy(url, outputStream);
            if (outputStream != null) {
                outputStream.close();
            }
            return copy;
        } catch (Throwable th) {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static long copy(URL url, OutputStream outputStream) throws IOException {
        InputStream inputStream = ((URL) Objects.requireNonNull(url, "url")).openStream();
        try {
            long copyLarge = copyLarge(inputStream, outputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            return copyLarge;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream) throws IOException {
        return copy(inputStream, outputStream, 8192);
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream, byte[] buffer) throws IOException {
        Objects.requireNonNull(inputStream, "inputStream");
        Objects.requireNonNull(outputStream, "outputStream");
        long count = 0;
        while (true) {
            int n = inputStream.read(buffer);
            if (-1 != n) {
                outputStream.write(buffer, 0, n);
                count += n;
            } else {
                return count;
            }
        }
    }

    public static long copyLarge(InputStream input, OutputStream output, long inputOffset, long length) throws IOException {
        Throwable th;
        ScratchBytes scratch = ScratchBytes.get();
        try {
            try {
                long copyLarge = copyLarge(input, output, inputOffset, length, scratch.array());
                if (scratch != null) {
                    scratch.close();
                }
                return copyLarge;
            } catch (Throwable th2) {
                th = th2;
                if (scratch == null) {
                    throw th;
                }
                try {
                    scratch.close();
                    throw th;
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static long copyLarge(InputStream input, OutputStream output, long inputOffset, long length, byte[] buffer) throws IOException {
        InputStream inputStream = input;
        long j = 0;
        if (inputOffset > 0) {
            skipFully(inputStream, inputOffset);
        }
        if (length == 0) {
            return 0L;
        }
        int bufferLength = buffer.length;
        int bytesToRead = bufferLength;
        if (length > 0 && length < bufferLength) {
            bytesToRead = (int) length;
        }
        long totalRead = 0;
        while (bytesToRead > 0) {
            int read = inputStream.read(buffer, 0, bytesToRead);
            if (-1 == read) {
                break;
            }
            output.write(buffer, 0, read);
            long j2 = j;
            totalRead += read;
            if (length <= j2) {
                inputStream = input;
                j = j2;
            } else {
                bytesToRead = (int) Math.min(length - totalRead, bufferLength);
                inputStream = input;
                j = j2;
            }
        }
        return totalRead;
    }

    public static long copyLarge(Reader reader, Writer writer) throws IOException {
        ScratchChars scratch = ScratchChars.get();
        try {
            long copyLarge = copyLarge(reader, writer, scratch.array());
            if (scratch != null) {
                scratch.close();
            }
            return copyLarge;
        } catch (Throwable th) {
            if (scratch != null) {
                try {
                    scratch.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static long copyLarge(Reader reader, Writer writer, char[] buffer) throws IOException {
        long count = 0;
        while (true) {
            int n = reader.read(buffer);
            if (-1 != n) {
                writer.write(buffer, 0, n);
                count += n;
            } else {
                return count;
            }
        }
    }

    public static long copyLarge(Reader reader, Writer writer, long inputOffset, long length) throws IOException {
        Throwable th;
        ScratchChars scratch = ScratchChars.get();
        try {
            try {
                long copyLarge = copyLarge(reader, writer, inputOffset, length, scratch.array());
                if (scratch != null) {
                    scratch.close();
                }
                return copyLarge;
            } catch (Throwable th2) {
                th = th2;
                if (scratch == null) {
                    throw th;
                }
                try {
                    scratch.close();
                    throw th;
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static long copyLarge(Reader reader, Writer writer, long inputOffset, long length, char[] buffer) throws IOException {
        long j = 0;
        if (inputOffset > 0) {
            skipFully(reader, inputOffset);
        }
        if (length == 0) {
            return 0L;
        }
        int bytesToRead = buffer.length;
        if (length > 0 && length < buffer.length) {
            bytesToRead = (int) length;
        }
        long totalRead = 0;
        while (bytesToRead > 0) {
            int read = reader.read(buffer, 0, bytesToRead);
            if (-1 == read) {
                break;
            }
            writer.write(buffer, 0, read);
            totalRead += read;
            if (length <= j) {
                j = 0;
            } else {
                bytesToRead = (int) Math.min(length - totalRead, buffer.length);
                j = 0;
            }
        }
        return totalRead;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static UnsynchronizedByteArrayOutputStream copyToOutputStream(InputStream input, long limit, int bufferSize) throws IOException {
        UnsynchronizedByteArrayOutputStream output = UnsynchronizedByteArrayOutputStream.builder().setBufferSize(bufferSize).get();
        try {
            InputStream boundedInput = ((BoundedInputStream.Builder) ((BoundedInputStream.Builder) ((BoundedInputStream.Builder) BoundedInputStream.builder().setMaxCount(limit)).setPropagateClose(false)).setInputStream(input)).get();
            try {
                output.write(boundedInput);
                if (boundedInput != null) {
                    boundedInput.close();
                }
                if (output != null) {
                    output.close();
                }
                return output;
            } finally {
            }
        } catch (Throwable th) {
            if (output != null) {
                try {
                    output.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static int length(byte[] array) {
        if (array == null) {
            return 0;
        }
        return array.length;
    }

    public static int length(char[] array) {
        if (array == null) {
            return 0;
        }
        return array.length;
    }

    public static int length(CharSequence csq) {
        if (csq == null) {
            return 0;
        }
        return csq.length();
    }

    public static int length(Object[] array) {
        if (array == null) {
            return 0;
        }
        return array.length;
    }

    public static LineIterator lineIterator(InputStream input, Charset charset) {
        return new LineIterator(new InputStreamReader(input, Charsets.toCharset(charset)));
    }

    public static LineIterator lineIterator(InputStream input, String charsetName) {
        return lineIterator(input, Charsets.toCharset(charsetName));
    }

    public static LineIterator lineIterator(Reader reader) {
        return new LineIterator(reader);
    }

    public static int read(InputStream input, byte[] buffer) throws IOException {
        return read(input, buffer, 0, buffer.length);
    }

    public static int read(InputStream input, byte[] buffer, int offset, int length) throws IOException {
        checkFromIndexSize(buffer, offset, length);
        int remaining = length;
        while (remaining > 0) {
            int location = length - remaining;
            int count = input.read(buffer, offset + location, remaining);
            if (-1 == count) {
                break;
            }
            remaining -= count;
        }
        return length - remaining;
    }

    public static int read(ReadableByteChannel input, ByteBuffer buffer) throws IOException {
        int length = buffer.remaining();
        while (buffer.remaining() > 0) {
            int count = input.read(buffer);
            if (-1 == count) {
                break;
            }
        }
        return length - buffer.remaining();
    }

    public static int read(Reader reader, char[] buffer) throws IOException {
        return read(reader, buffer, 0, buffer.length);
    }

    public static int read(Reader reader, char[] buffer, int offset, int length) throws IOException {
        checkFromIndexSize(buffer, offset, length);
        int remaining = length;
        while (remaining > 0) {
            int location = length - remaining;
            int count = reader.read(buffer, offset + location, remaining);
            if (-1 == count) {
                break;
            }
            remaining -= count;
        }
        return length - remaining;
    }

    public static void readFully(InputStream input, byte[] buffer) throws IOException {
        readFully(input, buffer, 0, buffer.length);
    }

    public static void readFully(InputStream input, byte[] buffer, int offset, int length) throws IOException {
        int actual = read(input, buffer, offset, length);
        if (actual != length) {
            throw new EOFException("Length to read: " + length + " actual: " + actual);
        }
    }

    @Deprecated
    public static byte[] readFully(InputStream input, int length) throws IOException {
        return toByteArray(input, length);
    }

    public static void readFully(ReadableByteChannel input, ByteBuffer buffer) throws IOException {
        int expected = buffer.remaining();
        int actual = read(input, buffer);
        if (actual != expected) {
            throw new EOFException("Length to read: " + expected + " actual: " + actual);
        }
    }

    public static void readFully(Reader reader, char[] buffer) throws IOException {
        readFully(reader, buffer, 0, buffer.length);
    }

    public static void readFully(Reader reader, char[] buffer, int offset, int length) throws IOException {
        int actual = read(reader, buffer, offset, length);
        if (actual != length) {
            throw new EOFException("Length to read: " + length + " actual: " + actual);
        }
    }

    public static List<String> readLines(CharSequence csq) throws UncheckedIOException {
        CharSequenceReader reader = new CharSequenceReader(csq);
        try {
            List<String> readLines = readLines(reader);
            reader.close();
            return readLines;
        } catch (Throwable th) {
            try {
                reader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Deprecated
    public static List<String> readLines(InputStream input) throws UncheckedIOException {
        return readLines(input, Charset.defaultCharset());
    }

    public static List<String> readLines(InputStream input, Charset charset) throws UncheckedIOException {
        return readLines(new InputStreamReader(input, Charsets.toCharset(charset)));
    }

    public static List<String> readLines(InputStream input, String charsetName) throws UncheckedIOException {
        return readLines(input, Charsets.toCharset(charsetName));
    }

    public static List<String> readLines(Reader reader) throws UncheckedIOException {
        return (List) toBufferedReader(reader).lines().collect(Collectors.toList());
    }

    public static byte[] resourceToByteArray(String name) throws IOException {
        return resourceToByteArray(name, null);
    }

    public static byte[] resourceToByteArray(String name, ClassLoader classLoader) throws IOException {
        return toByteArray(resourceToURL(name, classLoader));
    }

    public static String resourceToString(String name, Charset charset) throws IOException {
        return resourceToString(name, charset, null);
    }

    public static String resourceToString(String name, Charset charset, ClassLoader classLoader) throws IOException {
        return toString(resourceToURL(name, classLoader), charset);
    }

    public static URL resourceToURL(String name) throws IOException {
        return resourceToURL(name, null);
    }

    public static URL resourceToURL(String name, ClassLoader classLoader) throws IOException {
        URL resource = classLoader == null ? IOUtils.class.getResource(name) : classLoader.getResource(name);
        if (resource == null) {
            throw new IOException("Resource not found: " + name);
        }
        return resource;
    }

    public static long skip(InputStream input, long skip) throws IOException {
        final ScratchBytes scratch = ScratchBytes.get();
        try {
            Objects.requireNonNull(scratch);
            long skip2 = skip(input, skip, new Supplier() { // from class: org.apache.commons.io.IOUtils$$ExternalSyntheticLambda3
                @Override // java.util.function.Supplier
                public final Object get() {
                    return IOUtils.ScratchBytes.this.array();
                }
            });
            if (scratch != null) {
                scratch.close();
            }
            return skip2;
        } catch (Throwable th) {
            if (scratch != null) {
                try {
                    scratch.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static long skip(InputStream input, long skip, Supplier<byte[]> skipBufferSupplier) throws IOException {
        if (skip < 0) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + skip);
        }
        long remain = skip;
        while (remain > 0) {
            byte[] skipBuffer = skipBufferSupplier.get();
            long n = input.read(skipBuffer, 0, (int) Math.min(remain, skipBuffer.length));
            if (n < 0) {
                break;
            }
            remain -= n;
        }
        return skip - remain;
    }

    public static long skip(ReadableByteChannel input, long toSkip) throws IOException {
        if (toSkip < 0) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        ByteBuffer skipByteBuffer = ByteBuffer.allocate((int) Math.min(toSkip, 8192L));
        long remain = toSkip;
        while (remain > 0) {
            skipByteBuffer.position(0);
            skipByteBuffer.limit((int) Math.min(remain, 8192L));
            int n = input.read(skipByteBuffer);
            if (n == -1) {
                break;
            }
            remain -= n;
        }
        return toSkip - remain;
    }

    public static long skip(Reader reader, long toSkip) throws IOException {
        if (toSkip < 0) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        long remain = toSkip;
        ScratchChars scratch = ScratchChars.get();
        try {
            char[] chars = scratch.array();
            while (remain > 0) {
                long n = reader.read(chars, 0, (int) Math.min(remain, chars.length));
                if (n < 0) {
                    break;
                }
                remain -= n;
            }
            if (scratch != null) {
                scratch.close();
            }
            return toSkip - remain;
        } catch (Throwable th) {
            if (scratch != null) {
                try {
                    scratch.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static void skipFully(InputStream input, long toSkip) throws IOException {
        long skipped = skip(input, toSkip);
        if (skipped != toSkip) {
            throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped);
        }
    }

    public static void skipFully(InputStream input, long toSkip, Supplier<byte[]> skipBufferSupplier) throws IOException {
        if (toSkip < 0) {
            throw new IllegalArgumentException("Bytes to skip must not be negative: " + toSkip);
        }
        long skipped = skip(input, toSkip, skipBufferSupplier);
        if (skipped != toSkip) {
            throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped);
        }
    }

    public static void skipFully(ReadableByteChannel input, long toSkip) throws IOException {
        if (toSkip < 0) {
            throw new IllegalArgumentException("Bytes to skip must not be negative: " + toSkip);
        }
        long skipped = skip(input, toSkip);
        if (skipped != toSkip) {
            throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped);
        }
    }

    public static void skipFully(Reader reader, long toSkip) throws IOException {
        long skipped = skip(reader, toSkip);
        if (skipped != toSkip) {
            throw new EOFException("Chars to skip: " + toSkip + " actual: " + skipped);
        }
    }

    public static InputStream toBufferedInputStream(InputStream input) throws IOException {
        return org.apache.commons.io.output.ByteArrayOutputStream.toBufferedInputStream(input);
    }

    public static InputStream toBufferedInputStream(InputStream input, int size) throws IOException {
        return org.apache.commons.io.output.ByteArrayOutputStream.toBufferedInputStream(input, size);
    }

    public static BufferedReader toBufferedReader(Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
    }

    public static BufferedReader toBufferedReader(Reader reader, int size) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, size);
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        UnsynchronizedByteArrayOutputStream output = copyToOutputStream(inputStream, 2147483640L, 8192);
        if (output.size() > 2147483639) {
            throw new IOException(String.format("Cannot read more than %,d into a byte array", Integer.valueOf(SOFT_MAX_ARRAY_LENGTH)));
        }
        return output.toByteArray();
    }

    public static byte[] toByteArray(InputStream input, int size) throws IOException {
        InputStream inputStream = (InputStream) Objects.requireNonNull(input, "input");
        Objects.requireNonNull(inputStream);
        return toByteArray(new IOUtils$$ExternalSyntheticLambda5(inputStream), size);
    }

    public static byte[] toByteArray(InputStream input, int size, int chunkSize) throws IOException {
        Objects.requireNonNull(input, "input");
        if (chunkSize <= 0) {
            throw new IllegalArgumentException(String.format("chunkSize <= 0, chunkSize = %,d", Integer.valueOf(chunkSize)));
        }
        if (size <= chunkSize) {
            Objects.requireNonNull(input);
            return toByteArray(new IOUtils$$ExternalSyntheticLambda5(input), size);
        }
        UnsynchronizedByteArrayOutputStream output = copyToOutputStream(input, size, chunkSize);
        int outSize = output.size();
        if (outSize != size) {
            throw new EOFException(String.format("Expected read size: %,d, actual: %,d", Integer.valueOf(size), Integer.valueOf(outSize)));
        }
        return output.toByteArray();
    }

    public static byte[] toByteArray(InputStream input, long size) throws IOException {
        if (size > 2147483647L) {
            throw new IllegalArgumentException(String.format("size > Integer.MAX_VALUE, size = %,d", Long.valueOf(size)));
        }
        return toByteArray(input, (int) size);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] toByteArray(IOTriFunction<byte[], Integer, Integer, Integer> input, int size) throws IOException {
        if (size < 0) {
            throw new IllegalArgumentException(String.format("size < 0, size = %,d", Integer.valueOf(size)));
        }
        if (size == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] data = byteArray(size);
        int offset = 0;
        while (offset < size) {
            int read = input.apply(data, Integer.valueOf(offset), Integer.valueOf(size - offset)).intValue();
            if (read == -1) {
                break;
            }
            offset += read;
        }
        if (offset != size) {
            throw new EOFException(String.format("Expected read size: %,d, actual: %,d", Integer.valueOf(size), Integer.valueOf(offset)));
        }
        return data;
    }

    @Deprecated
    public static byte[] toByteArray(Reader reader) throws IOException {
        return toByteArray(reader, Charset.defaultCharset());
    }

    public static byte[] toByteArray(Reader reader, Charset charset) throws IOException {
        org.apache.commons.io.output.ByteArrayOutputStream output = new org.apache.commons.io.output.ByteArrayOutputStream();
        try {
            copy(reader, output, charset);
            byte[] byteArray = output.toByteArray();
            output.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                output.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static byte[] toByteArray(Reader reader, String charsetName) throws IOException {
        return toByteArray(reader, Charsets.toCharset(charsetName));
    }

    @Deprecated
    public static byte[] toByteArray(String input) {
        return input.getBytes(Charset.defaultCharset());
    }

    public static byte[] toByteArray(URI uri) throws IOException {
        return toByteArray(uri.toURL());
    }

    public static byte[] toByteArray(URL url) throws IOException {
        CloseableURLConnection urlConnection = CloseableURLConnection.open(url);
        try {
            byte[] byteArray = toByteArray(urlConnection);
            if (urlConnection != null) {
                urlConnection.close();
            }
            return byteArray;
        } catch (Throwable th) {
            if (urlConnection != null) {
                try {
                    urlConnection.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static byte[] toByteArray(URLConnection urlConnection) throws IOException {
        InputStream inputStream = urlConnection.getInputStream();
        try {
            byte[] byteArray = toByteArray(inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            return byteArray;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Deprecated
    public static char[] toCharArray(InputStream inputStream) throws IOException {
        return toCharArray(inputStream, Charset.defaultCharset());
    }

    public static char[] toCharArray(InputStream inputStream, Charset charset) throws IOException {
        CharArrayWriter writer = new CharArrayWriter();
        copy(inputStream, writer, charset);
        return writer.toCharArray();
    }

    public static char[] toCharArray(InputStream inputStream, String charsetName) throws IOException {
        return toCharArray(inputStream, Charsets.toCharset(charsetName));
    }

    public static char[] toCharArray(Reader reader) throws IOException {
        CharArrayWriter sw = new CharArrayWriter();
        copy(reader, (Writer) sw);
        return sw.toCharArray();
    }

    @Deprecated
    public static InputStream toInputStream(CharSequence input) {
        return toInputStream(input, Charset.defaultCharset());
    }

    public static InputStream toInputStream(CharSequence input, Charset charset) {
        return toInputStream(input.toString(), charset);
    }

    public static InputStream toInputStream(CharSequence input, String charsetName) {
        return toInputStream(input, Charsets.toCharset(charsetName));
    }

    @Deprecated
    public static InputStream toInputStream(String input) {
        return toInputStream(input, Charset.defaultCharset());
    }

    public static InputStream toInputStream(String input, Charset charset) {
        return new ByteArrayInputStream(input.getBytes(Charsets.toCharset(charset)));
    }

    public static InputStream toInputStream(String input, String charsetName) {
        return new ByteArrayInputStream(input.getBytes(Charsets.toCharset(charsetName)));
    }

    @Deprecated
    public static String toString(byte[] input) {
        return new String(input, Charset.defaultCharset());
    }

    public static String toString(byte[] input, String charsetName) {
        return new String(input, Charsets.toCharset(charsetName));
    }

    @Deprecated
    public static String toString(InputStream input) throws IOException {
        return toString(input, Charset.defaultCharset());
    }

    public static String toString(InputStream input, Charset charset) throws IOException {
        StringBuilderWriter sw = new StringBuilderWriter();
        try {
            copy(input, sw, charset);
            String stringBuilderWriter = sw.toString();
            sw.close();
            return stringBuilderWriter;
        } catch (Throwable th) {
            try {
                sw.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static String toString(InputStream input, String charsetName) throws IOException {
        return toString(input, Charsets.toCharset(charsetName));
    }

    public static String toString(IOSupplier<InputStream> input, Charset charset) throws IOException {
        return toString(input, charset, new IOSupplier() { // from class: org.apache.commons.io.IOUtils$$ExternalSyntheticLambda4
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                return IOUtils.lambda$toString$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$toString$0() throws IOException {
        throw new NullPointerException("input");
    }

    public static String toString(IOSupplier<InputStream> input, Charset charset, IOSupplier<String> defaultString) throws IOException {
        if (input == null) {
            return defaultString.get();
        }
        InputStream inputStream = input.get();
        try {
            String iOUtils = inputStream != null ? toString(inputStream, charset) : defaultString.get();
            if (inputStream != null) {
                inputStream.close();
            }
            return iOUtils;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static String toString(Reader reader) throws IOException {
        StringBuilderWriter sw = new StringBuilderWriter();
        try {
            copy(reader, (Writer) sw);
            String stringBuilderWriter = sw.toString();
            sw.close();
            return stringBuilderWriter;
        } catch (Throwable th) {
            try {
                sw.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Deprecated
    public static String toString(URI uri) throws IOException {
        return toString(uri, Charset.defaultCharset());
    }

    public static String toString(URI uri, Charset encoding) throws IOException {
        return toString(uri.toURL(), Charsets.toCharset(encoding));
    }

    public static String toString(URI uri, String charsetName) throws IOException {
        return toString(uri, Charsets.toCharset(charsetName));
    }

    @Deprecated
    public static String toString(URL url) throws IOException {
        return toString(url, Charset.defaultCharset());
    }

    public static String toString(final URL url, Charset encoding) throws IOException {
        Objects.requireNonNull(url);
        return toString((IOSupplier<InputStream>) new IOSupplier() { // from class: org.apache.commons.io.IOUtils$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                InputStream openStream;
                openStream = url.openStream();
                return openStream;
            }
        }, encoding);
    }

    public static String toString(URL url, String charsetName) throws IOException {
        return toString(url, Charsets.toCharset(charsetName));
    }

    public static void write(byte[] data, OutputStream output) throws IOException {
        if (data != null) {
            output.write(data);
        }
    }

    @Deprecated
    public static void write(byte[] data, Writer writer) throws IOException {
        write(data, writer, Charset.defaultCharset());
    }

    public static void write(byte[] data, Writer writer, Charset charset) throws IOException {
        if (data != null) {
            writer.write(new String(data, Charsets.toCharset(charset)));
        }
    }

    public static void write(byte[] data, Writer writer, String charsetName) throws IOException {
        write(data, writer, Charsets.toCharset(charsetName));
    }

    @Deprecated
    public static void write(char[] data, OutputStream output) throws IOException {
        write(data, output, Charset.defaultCharset());
    }

    public static void write(char[] data, OutputStream output, Charset charset) throws IOException {
        if (data != null) {
            write(new String(data), output, charset);
        }
    }

    public static void write(char[] data, OutputStream output, String charsetName) throws IOException {
        write(data, output, Charsets.toCharset(charsetName));
    }

    public static void write(char[] data, Writer writer) throws IOException {
        if (data != null) {
            writer.write(data);
        }
    }

    @Deprecated
    public static void write(CharSequence data, OutputStream output) throws IOException {
        write(data, output, Charset.defaultCharset());
    }

    public static void write(CharSequence data, OutputStream output, Charset charset) throws IOException {
        if (data != null) {
            write(data.toString(), output, charset);
        }
    }

    public static void write(CharSequence data, OutputStream output, String charsetName) throws IOException {
        write(data, output, Charsets.toCharset(charsetName));
    }

    public static void write(CharSequence data, Writer writer) throws IOException {
        if (data != null) {
            write(data.toString(), writer);
        }
    }

    @Deprecated
    public static void write(String data, OutputStream output) throws IOException {
        write(data, output, Charset.defaultCharset());
    }

    public static void write(String data, OutputStream output, Charset charset) throws IOException {
        if (data != null) {
            Channels.newChannel(output).write(Charsets.toCharset(charset).encode(data));
        }
    }

    public static void write(String data, OutputStream output, String charsetName) throws IOException {
        write(data, output, Charsets.toCharset(charsetName));
    }

    public static void write(String data, Writer writer) throws IOException {
        if (data != null) {
            writer.write(data);
        }
    }

    @Deprecated
    public static void write(StringBuffer data, OutputStream output) throws IOException {
        write(data, output, (String) null);
    }

    @Deprecated
    public static void write(StringBuffer data, OutputStream output, String charsetName) throws IOException {
        if (data != null) {
            write(data.toString(), output, Charsets.toCharset(charsetName));
        }
    }

    @Deprecated
    public static void write(StringBuffer data, Writer writer) throws IOException {
        if (data != null) {
            writer.write(data.toString());
        }
    }

    public static void writeChunked(byte[] data, OutputStream output) throws IOException {
        if (data != null) {
            int bytes = data.length;
            int offset = 0;
            while (bytes > 0) {
                int chunk = Math.min(bytes, 8192);
                output.write(data, offset, chunk);
                bytes -= chunk;
                offset += chunk;
            }
        }
    }

    public static void writeChunked(char[] data, Writer writer) throws IOException {
        if (data != null) {
            int bytes = data.length;
            int offset = 0;
            while (bytes > 0) {
                int chunk = Math.min(bytes, 8192);
                writer.write(data, offset, chunk);
                bytes -= chunk;
                offset += chunk;
            }
        }
    }

    @Deprecated
    public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output) throws IOException {
        writeLines(lines, lineEnding, output, Charset.defaultCharset());
    }

    public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output, Charset charset) throws IOException {
        if (lines == null) {
            return;
        }
        if (lineEnding == null) {
            lineEnding = System.lineSeparator();
        }
        if (StandardCharsets.UTF_16.equals(charset)) {
            charset = StandardCharsets.UTF_16BE;
        }
        byte[] eolBytes = lineEnding.getBytes(charset);
        for (Object line : lines) {
            if (line != null) {
                write(line.toString(), output, charset);
            }
            output.write(eolBytes);
        }
    }

    public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output, String charsetName) throws IOException {
        writeLines(lines, lineEnding, output, Charsets.toCharset(charsetName));
    }

    public static void writeLines(Collection<?> lines, String lineEnding, Writer writer) throws IOException {
        if (lines == null) {
            return;
        }
        if (lineEnding == null) {
            lineEnding = System.lineSeparator();
        }
        for (Object line : lines) {
            if (line != null) {
                writer.write(line.toString());
            }
            writer.write(lineEnding);
        }
    }

    public static Writer writer(Appendable appendable) {
        Objects.requireNonNull(appendable, "appendable");
        if (appendable instanceof Writer) {
            return (Writer) appendable;
        }
        if (appendable instanceof StringBuilder) {
            return new StringBuilderWriter((StringBuilder) appendable);
        }
        return new AppendableWriter(appendable);
    }

    @Deprecated
    public IOUtils() {
    }
}
