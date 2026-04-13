package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.RandomAccessFileMode;
import org.apache.commons.io.RandomAccessFiles;
import org.apache.commons.io.function.IOFunction;

/* loaded from: classes9.dex */
public class MagicNumberFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -547733176983104172L;
    private final long byteOffset;
    private final byte[] magicNumbers;

    public MagicNumberFileFilter(byte[] magicNumber) {
        this(magicNumber, 0L);
    }

    public MagicNumberFileFilter(byte[] magicNumbers, long offset) {
        Objects.requireNonNull(magicNumbers, "magicNumbers");
        if (magicNumbers.length == 0) {
            throw new IllegalArgumentException("The magic number must contain at least one byte");
        }
        if (offset < 0) {
            throw new IllegalArgumentException("The offset cannot be negative");
        }
        this.magicNumbers = (byte[]) magicNumbers.clone();
        this.byteOffset = offset;
    }

    public MagicNumberFileFilter(String magicNumber) {
        this(magicNumber, 0L);
    }

    public MagicNumberFileFilter(String magicNumber, long offset) {
        this(magicNumber.getBytes(Charset.defaultCharset()), offset);
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter, org.apache.commons.io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        if (isFile(file) && file.canRead()) {
            try {
                return ((Boolean) RandomAccessFileMode.READ_ONLY.apply(file.toPath(), new IOFunction() { // from class: org.apache.commons.io.filefilter.MagicNumberFileFilter$$ExternalSyntheticLambda0
                    @Override // org.apache.commons.io.function.IOFunction
                    public final Object apply(Object obj) {
                        return MagicNumberFileFilter.this.m2118x5edb28f0((RandomAccessFile) obj);
                    }
                })).booleanValue();
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$accept$0$org-apache-commons-io-filefilter-MagicNumberFileFilter, reason: not valid java name */
    public /* synthetic */ Boolean m2118x5edb28f0(RandomAccessFile raf) throws IOException {
        return Boolean.valueOf(Arrays.equals(this.magicNumbers, RandomAccessFiles.read(raf, this.byteOffset, this.magicNumbers.length)));
    }

    @Override // org.apache.commons.io.filefilter.IOFileFilter, org.apache.commons.io.file.PathFilter
    public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
        if (file != null && Files.isRegularFile(file, new LinkOption[0]) && Files.isReadable(file)) {
            try {
                FileChannel fileChannel = FileChannel.open(file, new OpenOption[0]);
                try {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(this.magicNumbers.length);
                    fileChannel.position(this.byteOffset);
                    int read = fileChannel.read(byteBuffer);
                    if (read != this.magicNumbers.length) {
                        FileVisitResult fileVisitResult = FileVisitResult.TERMINATE;
                        if (fileChannel != null) {
                            fileChannel.close();
                        }
                        return fileVisitResult;
                    }
                    FileVisitResult fileVisitResult2 = toFileVisitResult(Arrays.equals(this.magicNumbers, byteBuffer.array()));
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                    return fileVisitResult2;
                } finally {
                }
            } catch (IOException e) {
            }
        }
        return FileVisitResult.TERMINATE;
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter
    public String toString() {
        return super.toString() + "(" + new String(this.magicNumbers, Charset.defaultCharset()) + CollectionUtils.COMMA + this.byteOffset + ")";
    }
}
