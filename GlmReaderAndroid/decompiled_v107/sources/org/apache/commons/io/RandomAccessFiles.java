package org.apache.commons.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.SeekableByteChannel;
import java.util.Objects;
import org.apache.commons.io.channels.FileChannels;
import org.apache.commons.io.function.IOTriFunction;

/* loaded from: classes9.dex */
public class RandomAccessFiles {
    public static boolean contentEquals(RandomAccessFile raf1, RandomAccessFile raf2) throws IOException {
        if (Objects.equals(raf1, raf2)) {
            return true;
        }
        long length1 = length(raf1);
        long length2 = length(raf2);
        if (length1 != length2) {
            return false;
        }
        if (length1 == 0 && length2 == 0) {
            return true;
        }
        return FileChannels.contentEquals((SeekableByteChannel) raf1.getChannel(), (SeekableByteChannel) raf2.getChannel(), 8192);
    }

    private static long length(RandomAccessFile raf) throws IOException {
        if (raf != null) {
            return raf.length();
        }
        return 0L;
    }

    public static byte[] read(final RandomAccessFile input, long position, int length) throws IOException {
        input.seek(position);
        Objects.requireNonNull(input);
        return IOUtils.toByteArray((IOTriFunction<byte[], Integer, Integer, Integer>) new IOTriFunction() { // from class: org.apache.commons.io.RandomAccessFiles$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                int read;
                read = input.read((byte[]) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
                return Integer.valueOf(read);
            }
        }, length);
    }

    public static RandomAccessFile reset(RandomAccessFile raf) throws IOException {
        raf.seek(0L);
        return raf;
    }

    @Deprecated
    public RandomAccessFiles() {
    }
}
