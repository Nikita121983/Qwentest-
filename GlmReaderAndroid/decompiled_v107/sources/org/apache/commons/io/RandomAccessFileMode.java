package org.apache.commons.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.function.IOFunction;

/* loaded from: classes9.dex */
public enum RandomAccessFileMode {
    READ_ONLY(R, 1),
    READ_WRITE(RW, 2),
    READ_WRITE_SYNC_ALL(RWS, 4),
    READ_WRITE_SYNC_CONTENT(RWD, 3);

    private static final String R = "r";
    private static final String RW = "rw";
    private static final String RWD = "rwd";
    private static final String RWS = "rws";
    private final int level;
    private final String mode;

    public static RandomAccessFileMode valueOf(OpenOption... openOption) {
        RandomAccessFileMode bestFit = READ_ONLY;
        for (OpenOption option : openOption) {
            if (option instanceof StandardOpenOption) {
                switch (AnonymousClass1.$SwitchMap$java$nio$file$StandardOpenOption[((StandardOpenOption) option).ordinal()]) {
                    case 1:
                        if (bestFit.implies(READ_WRITE)) {
                            break;
                        } else {
                            bestFit = READ_WRITE;
                            break;
                        }
                    case 2:
                        if (bestFit.implies(READ_WRITE_SYNC_CONTENT)) {
                            break;
                        } else {
                            bestFit = READ_WRITE_SYNC_CONTENT;
                            break;
                        }
                    case 3:
                        if (bestFit.implies(READ_WRITE_SYNC_ALL)) {
                            break;
                        } else {
                            bestFit = READ_WRITE_SYNC_ALL;
                            break;
                        }
                }
            }
        }
        return bestFit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.apache.commons.io.RandomAccessFileMode$1, reason: invalid class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$nio$file$StandardOpenOption = new int[StandardOpenOption.values().length];

        static {
            try {
                $SwitchMap$java$nio$file$StandardOpenOption[StandardOpenOption.WRITE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$nio$file$StandardOpenOption[StandardOpenOption.DSYNC.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$nio$file$StandardOpenOption[StandardOpenOption.SYNC.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static RandomAccessFileMode valueOfMode(String mode) {
        char c;
        switch (mode.hashCode()) {
            case 114:
                if (mode.equals(R)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3653:
                if (mode.equals(RW)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 113343:
                if (mode.equals(RWD)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 113358:
                if (mode.equals(RWS)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return READ_ONLY;
            case 1:
                return READ_WRITE;
            case 2:
                return READ_WRITE_SYNC_CONTENT;
            case 3:
                return READ_WRITE_SYNC_ALL;
            default:
                throw new IllegalArgumentException(mode);
        }
    }

    RandomAccessFileMode(String mode, int level) {
        this.mode = mode;
        this.level = level;
    }

    public void accept(Path file, IOConsumer<RandomAccessFile> consumer) throws IOException {
        RandomAccessFile raf = create(file);
        try {
            consumer.accept(raf);
            if (raf != null) {
                raf.close();
            }
        } catch (Throwable th) {
            if (raf != null) {
                try {
                    raf.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public <T> T apply(Path file, IOFunction<RandomAccessFile, T> function) throws IOException {
        RandomAccessFile raf = create(file);
        try {
            T apply = function.apply(raf);
            if (raf != null) {
                raf.close();
            }
            return apply;
        } catch (Throwable th) {
            if (raf != null) {
                try {
                    raf.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public RandomAccessFile create(File file) throws FileNotFoundException {
        return new IORandomAccessFile(file, this.mode);
    }

    public RandomAccessFile create(Path file) throws FileNotFoundException {
        return create((File) Objects.requireNonNull(file.toFile(), "file"));
    }

    public RandomAccessFile create(String name) throws FileNotFoundException {
        return new IORandomAccessFile(name, this.mode);
    }

    private int getLevel() {
        return this.level;
    }

    public String getMode() {
        return this.mode;
    }

    public boolean implies(RandomAccessFileMode other) {
        return getLevel() >= other.getLevel();
    }

    public IORandomAccessFile io(String name) throws FileNotFoundException {
        return new IORandomAccessFile(name, this.mode);
    }
}
