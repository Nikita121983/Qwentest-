package org.apache.commons.io.input;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;
import kotlin.UByte;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractStreamBuilder;

/* loaded from: classes9.dex */
public class ReadAheadInputStream extends FilterInputStream {
    private static final ThreadLocal<byte[]> BYTE_ARRAY_1 = ThreadLocal.withInitial(new Supplier() { // from class: org.apache.commons.io.input.ReadAheadInputStream$$ExternalSyntheticLambda2
        @Override // java.util.function.Supplier
        public final Object get() {
            return ReadAheadInputStream.lambda$static$0();
        }
    });
    private ByteBuffer activeBuffer;
    private final Condition asyncReadComplete;
    private boolean endOfStream;
    private final ExecutorService executorService;
    private boolean isClosed;
    private boolean isReading;
    private boolean isUnderlyingInputStreamBeingClosed;
    private final AtomicBoolean isWaiting;
    private boolean readAborted;
    private ByteBuffer readAheadBuffer;
    private Throwable readException;
    private boolean readInProgress;
    private final boolean shutdownExecutorService;
    private final ReentrantLock stateChangeLock;

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<ReadAheadInputStream, Builder> {
        private ExecutorService executorService;

        @Override // org.apache.commons.io.function.IOSupplier
        public ReadAheadInputStream get() throws IOException {
            return new ReadAheadInputStream(this);
        }

        public Builder setExecutorService(ExecutorService executorService) {
            this.executorService = executorService;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ byte[] lambda$static$0() {
        return new byte[1];
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Thread newDaemonThread(Runnable r) {
        Thread thread = new Thread(r, "commons-io-read-ahead");
        thread.setDaemon(true);
        return thread;
    }

    private static ExecutorService newExecutorService() {
        return Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: org.apache.commons.io.input.ReadAheadInputStream$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread newDaemonThread;
                newDaemonThread = ReadAheadInputStream.newDaemonThread(runnable);
                return newDaemonThread;
            }
        });
    }

    private ReadAheadInputStream(Builder builder) throws IOException {
        this(builder.getInputStream(), builder.getBufferSize(), builder.executorService != null ? builder.executorService : newExecutorService(), builder.executorService == null);
    }

    @Deprecated
    public ReadAheadInputStream(InputStream inputStream, int bufferSizeInBytes) {
        this(inputStream, bufferSizeInBytes, newExecutorService(), true);
    }

    @Deprecated
    public ReadAheadInputStream(InputStream inputStream, int bufferSizeInBytes, ExecutorService executorService) {
        this(inputStream, bufferSizeInBytes, executorService, false);
    }

    private ReadAheadInputStream(InputStream inputStream, int bufferSizeInBytes, ExecutorService executorService, boolean shutdownExecutorService) {
        super((InputStream) Objects.requireNonNull(inputStream, "inputStream"));
        this.stateChangeLock = new ReentrantLock();
        this.isWaiting = new AtomicBoolean();
        this.asyncReadComplete = this.stateChangeLock.newCondition();
        if (bufferSizeInBytes <= 0) {
            throw new IllegalArgumentException(String.format("bufferSizeInBytes <= 0, bufferSizeInBytes = %,d", Integer.valueOf(bufferSizeInBytes)));
        }
        this.executorService = (ExecutorService) Objects.requireNonNull(executorService, "executorService");
        this.shutdownExecutorService = shutdownExecutorService;
        this.activeBuffer = ByteBuffer.allocate(bufferSizeInBytes);
        this.readAheadBuffer = ByteBuffer.allocate(bufferSizeInBytes);
        this.activeBuffer.flip();
        this.readAheadBuffer.flip();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        this.stateChangeLock.lock();
        try {
            return (int) Math.min(2147483647L, this.activeBuffer.remaining() + this.readAheadBuffer.remaining());
        } finally {
            this.stateChangeLock.unlock();
        }
    }

    private void checkReadException() throws IOException {
        if (this.readAborted) {
            if (this.readException instanceof IOException) {
                throw ((IOException) this.readException);
            }
            throw new IOException(this.readException);
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        boolean isSafeToCloseUnderlyingInputStream = false;
        this.stateChangeLock.lock();
        try {
            if (this.isClosed) {
                return;
            }
            this.isClosed = true;
            if (!this.isReading) {
                isSafeToCloseUnderlyingInputStream = true;
                this.isUnderlyingInputStreamBeingClosed = true;
            }
            this.stateChangeLock.unlock();
            if (this.shutdownExecutorService) {
                try {
                    try {
                        this.executorService.shutdownNow();
                        this.executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        InterruptedIOException iio = new InterruptedIOException(e.getMessage());
                        iio.initCause(e);
                        throw iio;
                    }
                } finally {
                    if (isSafeToCloseUnderlyingInputStream) {
                        super.close();
                    }
                }
            }
        } finally {
            this.stateChangeLock.unlock();
        }
    }

    private void closeUnderlyingInputStreamIfNecessary() {
        boolean needToCloseUnderlyingInputStream = false;
        this.stateChangeLock.lock();
        try {
            this.isReading = false;
            if (this.isClosed) {
                if (!this.isUnderlyingInputStreamBeingClosed) {
                    needToCloseUnderlyingInputStream = true;
                }
            }
            if (needToCloseUnderlyingInputStream) {
                try {
                    super.close();
                } catch (IOException e) {
                }
            }
        } finally {
            this.stateChangeLock.unlock();
        }
    }

    private boolean isEndOfStream() {
        return (this.activeBuffer.hasRemaining() || this.readAheadBuffer.hasRemaining() || !this.endOfStream) ? false : true;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.activeBuffer.hasRemaining()) {
            return this.activeBuffer.get() & UByte.MAX_VALUE;
        }
        byte[] oneByteArray = BYTE_ARRAY_1.get();
        oneByteArray[0] = 0;
        if (read(oneByteArray, 0, 1) == -1) {
            return -1;
        }
        return oneByteArray[0] & UByte.MAX_VALUE;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int offset, int len) throws IOException {
        IOUtils.checkFromIndexSize(b, offset, len);
        if (len == 0) {
            return 0;
        }
        if (!this.activeBuffer.hasRemaining()) {
            this.stateChangeLock.lock();
            try {
                waitForAsyncReadComplete();
                if (!this.readAheadBuffer.hasRemaining()) {
                    readAsync();
                    waitForAsyncReadComplete();
                    if (isEndOfStream()) {
                        this.stateChangeLock.unlock();
                        return -1;
                    }
                }
                swapBuffers();
                readAsync();
            } finally {
                this.stateChangeLock.unlock();
            }
        }
        int len2 = Math.min(len, this.activeBuffer.remaining());
        this.activeBuffer.get(b, offset, len2);
        return len2;
    }

    private void readAsync() throws IOException {
        this.stateChangeLock.lock();
        try {
            final byte[] arr = this.readAheadBuffer.array();
            if (!this.endOfStream && !this.readInProgress) {
                checkReadException();
                this.readAheadBuffer.position(0);
                this.readAheadBuffer.flip();
                this.readInProgress = true;
                this.stateChangeLock.unlock();
                this.executorService.execute(new Runnable() { // from class: org.apache.commons.io.input.ReadAheadInputStream$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ReadAheadInputStream.this.m2128x982106fc(arr);
                    }
                });
            }
        } finally {
            this.stateChangeLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$readAsync$1$org-apache-commons-io-input-ReadAheadInputStream, reason: not valid java name */
    public /* synthetic */ void m2128x982106fc(byte[] arr) {
        this.stateChangeLock.lock();
        try {
            if (this.isClosed) {
                this.readInProgress = false;
                return;
            }
            this.isReading = true;
            this.stateChangeLock.unlock();
            int read = 0;
            int off = 0;
            int len = arr.length;
            do {
                try {
                    read = this.in.read(arr, off, len);
                    if (read > 0) {
                        off += read;
                        len -= read;
                        if (len <= 0) {
                            break;
                        }
                    } else {
                        break;
                    }
                } catch (Throwable ex) {
                    try {
                        if (ex instanceof Error) {
                            throw ((Error) ex);
                        }
                        this.stateChangeLock.lock();
                        try {
                            this.readAheadBuffer.limit(off);
                            if (read >= 0 && !(ex instanceof EOFException)) {
                                this.readAborted = true;
                                this.readException = ex;
                                this.readInProgress = false;
                                signalAsyncReadComplete();
                            }
                            this.endOfStream = true;
                            this.readInProgress = false;
                            signalAsyncReadComplete();
                        } finally {
                        }
                    } catch (Throwable ex2) {
                        this.stateChangeLock.lock();
                        try {
                            this.readAheadBuffer.limit(off);
                            if (read < 0 || (ex instanceof EOFException)) {
                                this.endOfStream = true;
                            } else {
                                this.readAborted = true;
                                this.readException = ex;
                            }
                            this.readInProgress = false;
                            signalAsyncReadComplete();
                            this.stateChangeLock.unlock();
                            closeUnderlyingInputStreamIfNecessary();
                            throw ex2;
                        } finally {
                        }
                    }
                }
            } while (!this.isWaiting.get());
            this.stateChangeLock.lock();
            try {
                this.readAheadBuffer.limit(off);
                if (read < 0) {
                    this.endOfStream = true;
                } else if (0 != 0) {
                    this.readAborted = true;
                    this.readException = null;
                }
                this.readInProgress = false;
                signalAsyncReadComplete();
                this.stateChangeLock.unlock();
                closeUnderlyingInputStreamIfNecessary();
            } finally {
            }
        } finally {
        }
    }

    private void signalAsyncReadComplete() {
        this.stateChangeLock.lock();
        try {
            this.asyncReadComplete.signalAll();
        } finally {
            this.stateChangeLock.unlock();
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long n) throws IOException {
        if (n <= 0) {
            return 0L;
        }
        if (n <= this.activeBuffer.remaining()) {
            this.activeBuffer.position(((int) n) + this.activeBuffer.position());
            return n;
        }
        this.stateChangeLock.lock();
        try {
            long skipped = skipInternal(n);
            return skipped;
        } finally {
            this.stateChangeLock.unlock();
        }
    }

    private long skipInternal(long n) throws IOException {
        if (!this.stateChangeLock.isLocked()) {
            throw new IllegalStateException("Expected stateChangeLock to be locked");
        }
        waitForAsyncReadComplete();
        if (isEndOfStream()) {
            return 0L;
        }
        if (available() >= n) {
            int toSkip = (int) n;
            int toSkip2 = toSkip - this.activeBuffer.remaining();
            if (toSkip2 <= 0) {
                throw new IllegalStateException("Expected toSkip > 0, actual: " + toSkip2);
            }
            this.activeBuffer.position(0);
            this.activeBuffer.flip();
            this.readAheadBuffer.position(this.readAheadBuffer.position() + toSkip2);
            swapBuffers();
            readAsync();
            return n;
        }
        int skippedBytes = available();
        long toSkip3 = n - skippedBytes;
        this.activeBuffer.position(0);
        this.activeBuffer.flip();
        this.readAheadBuffer.position(0);
        this.readAheadBuffer.flip();
        long skippedFromInputStream = this.in.skip(toSkip3);
        readAsync();
        return skippedBytes + skippedFromInputStream;
    }

    private void swapBuffers() {
        ByteBuffer temp = this.activeBuffer;
        this.activeBuffer = this.readAheadBuffer;
        this.readAheadBuffer = temp;
    }

    private void waitForAsyncReadComplete() throws IOException {
        this.stateChangeLock.lock();
        try {
            try {
                this.isWaiting.set(true);
                while (this.readInProgress) {
                    this.asyncReadComplete.await();
                }
                try {
                    this.isWaiting.set(false);
                    this.stateChangeLock.unlock();
                    checkReadException();
                } finally {
                }
            } catch (InterruptedException e) {
                InterruptedIOException iio = new InterruptedIOException(e.getMessage());
                iio.initCause(e);
                throw iio;
            }
        } catch (Throwable th) {
            try {
                this.isWaiting.set(false);
                throw th;
            } finally {
            }
        }
    }
}
