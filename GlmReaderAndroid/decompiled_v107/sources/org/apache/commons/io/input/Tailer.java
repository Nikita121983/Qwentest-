package org.apache.commons.io.input;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.ThreadUtils;
import org.apache.commons.io.build.AbstractOrigin;
import org.apache.commons.io.build.AbstractOriginSupplier;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.file.PathUtils;
import org.apache.commons.io.file.attribute.FileTimes;
import org.apache.commons.io.input.Tailer;

/* loaded from: classes9.dex */
public class Tailer implements Runnable, AutoCloseable {
    private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
    private static final int DEFAULT_DELAY_MILLIS = 1000;
    private static final boolean DEFAULT_IGNORE_TOUCH = false;
    private static final String RAF_READ_ONLY_MODE = "r";
    private final Charset charset;
    private final Duration delayDuration;
    private final boolean ignoreTouch;
    private final byte[] inbuf;
    private final TailerListener listener;
    private final boolean reOpen;
    private volatile boolean run;
    private final boolean tailFromEnd;
    private final Tailable tailable;

    /* loaded from: classes9.dex */
    public interface RandomAccessResourceBridge extends Closeable {
        long getPointer() throws IOException;

        int read(byte[] bArr) throws IOException;

        void seek(long j) throws IOException;
    }

    /* loaded from: classes9.dex */
    public interface Tailable {
        RandomAccessResourceBridge getRandomAccess(String str) throws FileNotFoundException;

        boolean isNewer(FileTime fileTime) throws IOException;

        FileTime lastModifiedFileTime() throws IOException;

        long size() throws IOException;
    }

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<Tailer, Builder> {
        private static final Duration DEFAULT_DELAY_DURATION = Duration.ofMillis(1000);
        private boolean reOpen;
        private boolean tailFromEnd;
        private Tailable tailable;
        private TailerListener tailerListener;
        private Duration delayDuration = DEFAULT_DELAY_DURATION;
        private boolean startThread = true;
        private boolean ignoreTouch = false;
        private ExecutorService executorService = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: org.apache.commons.io.input.Tailer$Builder$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread newDaemonThread;
                newDaemonThread = Tailer.Builder.newDaemonThread(runnable);
                return newDaemonThread;
            }
        });

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.commons.io.build.AbstractOriginSupplier
        public /* bridge */ /* synthetic */ AbstractOriginSupplier setOrigin(AbstractOrigin abstractOrigin) {
            return setOrigin((AbstractOrigin<?, ?>) abstractOrigin);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Thread newDaemonThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "commons-io-tailer");
            thread.setDaemon(true);
            return thread;
        }

        @Override // org.apache.commons.io.function.IOSupplier
        public Tailer get() {
            Tailer tailer = new Tailer(this);
            if (this.startThread) {
                this.executorService.submit(tailer);
            }
            return tailer;
        }

        public Builder setDelayDuration(Duration delayDuration) {
            this.delayDuration = delayDuration != null ? delayDuration : DEFAULT_DELAY_DURATION;
            return this;
        }

        public Builder setExecutorService(ExecutorService executorService) {
            this.executorService = (ExecutorService) Objects.requireNonNull(executorService, "executorService");
            return this;
        }

        public Builder setIgnoreTouch(boolean ignoreTouch) {
            this.ignoreTouch = ignoreTouch;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.commons.io.build.AbstractOriginSupplier
        public Builder setOrigin(AbstractOrigin<?, ?> origin) {
            setTailable(new TailablePath(origin.getPath(), new LinkOption[0]));
            return (Builder) super.setOrigin(origin);
        }

        public Builder setReOpen(boolean reOpen) {
            this.reOpen = reOpen;
            return this;
        }

        public Builder setStartThread(boolean startThread) {
            this.startThread = startThread;
            return this;
        }

        public Builder setTailable(Tailable tailable) {
            this.tailable = (Tailable) Objects.requireNonNull(tailable, "tailable");
            return this;
        }

        public Builder setTailerListener(TailerListener tailerListener) {
            this.tailerListener = (TailerListener) Objects.requireNonNull(tailerListener, "tailerListener");
            return this;
        }

        public Builder setTailFromEnd(boolean end) {
            this.tailFromEnd = end;
            return this;
        }
    }

    /* loaded from: classes9.dex */
    private static final class RandomAccessFileBridge implements RandomAccessResourceBridge {
        private final RandomAccessFile randomAccessFile;

        private RandomAccessFileBridge(File file, String mode) throws FileNotFoundException {
            this.randomAccessFile = new RandomAccessFile(file, mode);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.randomAccessFile.close();
        }

        @Override // org.apache.commons.io.input.Tailer.RandomAccessResourceBridge
        public long getPointer() throws IOException {
            return this.randomAccessFile.getFilePointer();
        }

        @Override // org.apache.commons.io.input.Tailer.RandomAccessResourceBridge
        public int read(byte[] b) throws IOException {
            return this.randomAccessFile.read(b);
        }

        @Override // org.apache.commons.io.input.Tailer.RandomAccessResourceBridge
        public void seek(long position) throws IOException {
            this.randomAccessFile.seek(position);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class TailablePath implements Tailable {
        private final LinkOption[] linkOptions;
        private final Path path;

        private TailablePath(Path path, LinkOption... linkOptions) {
            this.path = (Path) Objects.requireNonNull(path, "path");
            this.linkOptions = linkOptions;
        }

        Path getPath() {
            return this.path;
        }

        @Override // org.apache.commons.io.input.Tailer.Tailable
        public RandomAccessResourceBridge getRandomAccess(String mode) throws FileNotFoundException {
            return new RandomAccessFileBridge(this.path.toFile(), mode);
        }

        @Override // org.apache.commons.io.input.Tailer.Tailable
        public boolean isNewer(FileTime fileTime) throws IOException {
            return PathUtils.isNewer(this.path, fileTime, this.linkOptions);
        }

        @Override // org.apache.commons.io.input.Tailer.Tailable
        public FileTime lastModifiedFileTime() throws IOException {
            return Files.getLastModifiedTime(this.path, this.linkOptions);
        }

        @Override // org.apache.commons.io.input.Tailer.Tailable
        public long size() throws IOException {
            return Files.size(this.path);
        }

        public String toString() {
            return "TailablePath [file=" + this.path + ", linkOptions=" + Arrays.toString(this.linkOptions) + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public static Tailer create(File file, Charset charset, TailerListener tailerListener, long delayMillis, boolean end, boolean reOpen, int bufferSize) {
        return ((Builder) builder().setFile(file)).setTailerListener(tailerListener).setCharset(charset).setDelayDuration(Duration.ofMillis(delayMillis)).setTailFromEnd(end).setReOpen(reOpen).setBufferSize(bufferSize).get();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public static Tailer create(File file, TailerListener tailerListener) {
        return ((Builder) builder().setFile(file)).setTailerListener(tailerListener).get();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public static Tailer create(File file, TailerListener tailerListener, long delayMillis) {
        return ((Builder) builder().setFile(file)).setTailerListener(tailerListener).setDelayDuration(Duration.ofMillis(delayMillis)).get();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public static Tailer create(File file, TailerListener tailerListener, long delayMillis, boolean end) {
        return ((Builder) builder().setFile(file)).setTailerListener(tailerListener).setDelayDuration(Duration.ofMillis(delayMillis)).setTailFromEnd(end).get();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public static Tailer create(File file, TailerListener tailerListener, long delayMillis, boolean end, boolean reOpen) {
        return ((Builder) builder().setFile(file)).setTailerListener(tailerListener).setDelayDuration(Duration.ofMillis(delayMillis)).setTailFromEnd(end).setReOpen(reOpen).get();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public static Tailer create(File file, TailerListener tailerListener, long delayMillis, boolean end, boolean reOpen, int bufferSize) {
        return ((Builder) builder().setFile(file)).setTailerListener(tailerListener).setDelayDuration(Duration.ofMillis(delayMillis)).setTailFromEnd(end).setReOpen(reOpen).setBufferSize(bufferSize).get();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public static Tailer create(File file, TailerListener tailerListener, long delayMillis, boolean end, int bufferSize) {
        return ((Builder) builder().setFile(file)).setTailerListener(tailerListener).setDelayDuration(Duration.ofMillis(delayMillis)).setTailFromEnd(end).setBufferSize(bufferSize).get();
    }

    private Tailer(Builder builder) {
        this.run = true;
        this.tailable = (Tailable) Objects.requireNonNull(builder.tailable, "tailable");
        this.listener = (TailerListener) Objects.requireNonNull(builder.tailerListener, "listener");
        this.delayDuration = builder.delayDuration;
        this.tailFromEnd = builder.tailFromEnd;
        this.inbuf = IOUtils.byteArray(builder.getBufferSize());
        this.listener.init(this);
        this.reOpen = builder.reOpen;
        this.charset = builder.getCharset();
        this.ignoreTouch = builder.ignoreTouch;
    }

    @Deprecated
    public Tailer(File file, Charset charset, TailerListener tailerListener, long delayMillis, boolean end, boolean reOpen, int bufSize) {
        this(new TailablePath(file.toPath(), new LinkOption[0]), charset, tailerListener, Duration.ofMillis(delayMillis), end, reOpen, bufSize, false);
    }

    @Deprecated
    public Tailer(File file, TailerListener tailerListener) {
        this(file, tailerListener, 1000L);
    }

    @Deprecated
    public Tailer(File file, TailerListener tailerListener, long delayMillis) {
        this(file, tailerListener, delayMillis, false);
    }

    @Deprecated
    public Tailer(File file, TailerListener tailerListener, long delayMillis, boolean end) {
        this(file, tailerListener, delayMillis, end, 8192);
    }

    @Deprecated
    public Tailer(File file, TailerListener tailerListener, long delayMillis, boolean end, boolean reOpen) {
        this(file, tailerListener, delayMillis, end, reOpen, 8192);
    }

    @Deprecated
    public Tailer(File file, TailerListener tailerListener, long delayMillis, boolean end, boolean reOpen, int bufferSize) {
        this(file, DEFAULT_CHARSET, tailerListener, delayMillis, end, reOpen, bufferSize);
    }

    @Deprecated
    public Tailer(File file, TailerListener tailerListener, long delayMillis, boolean end, int bufferSize) {
        this(file, tailerListener, delayMillis, end, false, bufferSize);
    }

    private Tailer(Tailable tailable, Charset charset, TailerListener tailerListener, Duration delayDuration, boolean tailAtEnd, boolean reOpen, int bufferSize, boolean ignoreTouch) {
        this.run = true;
        this.tailable = (Tailable) Objects.requireNonNull(tailable, "tailable");
        this.listener = (TailerListener) Objects.requireNonNull(tailerListener, "listener");
        this.delayDuration = delayDuration;
        this.tailFromEnd = tailAtEnd;
        this.inbuf = IOUtils.byteArray(bufferSize);
        tailerListener.init(this);
        this.reOpen = reOpen;
        this.charset = charset;
        this.ignoreTouch = ignoreTouch;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.run = false;
    }

    @Deprecated
    public long getDelay() {
        return this.delayDuration.toMillis();
    }

    public Duration getDelayDuration() {
        return this.delayDuration;
    }

    public File getFile() {
        if (this.tailable instanceof TailablePath) {
            return ((TailablePath) this.tailable).getPath().toFile();
        }
        throw new IllegalStateException("Cannot extract java.io.File from " + this.tailable.getClass().getName());
    }

    protected boolean getRun() {
        return this.run;
    }

    public Tailable getTailable() {
        return this.tailable;
    }

    private long readLines(RandomAccessResourceBridge reader) throws IOException {
        int num;
        ByteArrayOutputStream lineBuf = new ByteArrayOutputStream(64);
        try {
            long pos = reader.getPointer();
            long rePos = pos;
            boolean seenCR = false;
            while (getRun() && (num = reader.read(this.inbuf)) != -1) {
                for (int i = 0; i < num; i++) {
                    byte ch = this.inbuf[i];
                    switch (ch) {
                        case 10:
                            seenCR = false;
                            this.listener.handle(new String(lineBuf.toByteArray(), this.charset));
                            lineBuf.reset();
                            rePos = i + pos + 1;
                            break;
                        case 13:
                            if (seenCR) {
                                lineBuf.write(13);
                            }
                            seenCR = true;
                            break;
                        default:
                            if (seenCR) {
                                seenCR = false;
                                this.listener.handle(new String(lineBuf.toByteArray(), this.charset));
                                lineBuf.reset();
                                rePos = i + pos + 1;
                            }
                            lineBuf.write(ch);
                            break;
                    }
                }
                pos = reader.getPointer();
            }
            reader.seek(rePos);
            if (this.listener instanceof TailerListenerAdapter) {
                ((TailerListenerAdapter) this.listener).endOfFileReached();
            }
            lineBuf.close();
            return rePos;
        } finally {
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        RandomAccessResourceBridge reader = null;
        try {
            try {
                FileTime last = FileTimes.EPOCH;
                long position = 0;
                while (getRun() && reader == null) {
                    try {
                        reader = this.tailable.getRandomAccess(RAF_READ_ONLY_MODE);
                    } catch (FileNotFoundException e) {
                        this.listener.fileNotFound();
                    }
                    if (reader == null) {
                        ThreadUtils.sleep(this.delayDuration);
                    } else {
                        position = this.tailFromEnd ? this.tailable.size() : 0L;
                        last = this.tailable.lastModifiedFileTime();
                        reader.seek(position);
                    }
                }
                while (getRun()) {
                    boolean newer = this.tailable.isNewer(last);
                    long length = this.tailable.size();
                    if (length < position) {
                        this.listener.fileRotated();
                        RandomAccessResourceBridge save = reader;
                        try {
                            reader = this.tailable.getRandomAccess(RAF_READ_ONLY_MODE);
                            try {
                                readLines(save);
                            } catch (IOException ioe) {
                                this.listener.handle(ioe);
                            }
                            position = 0;
                            if (save != null) {
                                try {
                                    save.close();
                                } catch (FileNotFoundException e2) {
                                    this.listener.fileNotFound();
                                    ThreadUtils.sleep(this.delayDuration);
                                }
                            }
                        } catch (Throwable th) {
                            if (save != null) {
                                try {
                                    save.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            }
                            throw th;
                            break;
                        }
                    } else {
                        if (length > position) {
                            long position2 = readLines(reader);
                            last = this.tailable.lastModifiedFileTime();
                            position = position2;
                        } else if (newer) {
                            if (!this.ignoreTouch) {
                                reader.seek(0L);
                                position = readLines(reader);
                            }
                            last = this.tailable.lastModifiedFileTime();
                        }
                        if (this.reOpen && reader != null) {
                            reader.close();
                        }
                        ThreadUtils.sleep(this.delayDuration);
                        if (getRun() && this.reOpen) {
                            reader = this.tailable.getRandomAccess(RAF_READ_ONLY_MODE);
                            reader.seek(position);
                        }
                    }
                }
            } catch (InterruptedException e3) {
                Thread.currentThread().interrupt();
                this.listener.handle(e3);
                try {
                    IOUtils.close(reader);
                } catch (IOException e4) {
                    e = e4;
                    this.listener.handle(e);
                    close();
                }
            } catch (Exception e5) {
                this.listener.handle(e5);
                try {
                    IOUtils.close(reader);
                } catch (IOException e6) {
                    e = e6;
                    this.listener.handle(e);
                    close();
                }
            }
            try {
                IOUtils.close(reader);
            } catch (IOException e7) {
                e = e7;
                this.listener.handle(e);
                close();
            }
            close();
        } catch (Throwable th3) {
            try {
                IOUtils.close(reader);
            } catch (IOException e8) {
                this.listener.handle(e8);
            }
            close();
            throw th3;
        }
    }

    @Deprecated
    public void stop() {
        close();
    }
}
