package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.function.IOIntConsumer;
import org.apache.commons.io.input.ProxyInputStream;

/* loaded from: classes9.dex */
public class BOMInputStream extends ProxyInputStream {
    private static final Comparator<ByteOrderMark> ByteOrderMarkLengthComparator = Comparator.comparing(new Function() { // from class: org.apache.commons.io.input.BOMInputStream$$ExternalSyntheticLambda1
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Integer.valueOf(((ByteOrderMark) obj).length());
        }
    }).reversed();
    private final List<ByteOrderMark> bomList;
    private ByteOrderMark byteOrderMark;
    private int fbIndex;
    private int[] firstBytes;
    private final boolean include;
    private int markFbIndex;
    private boolean markedAtStart;

    /* loaded from: classes9.dex */
    public static class Builder extends ProxyInputStream.AbstractBuilder<BOMInputStream, Builder> {
        private static final ByteOrderMark[] DEFAULT = {ByteOrderMark.UTF_8};
        private ByteOrderMark[] byteOrderMarks = DEFAULT;
        private boolean include;

        @Override // org.apache.commons.io.input.ProxyInputStream.AbstractBuilder
        public /* bridge */ /* synthetic */ IOIntConsumer getAfterRead() {
            return super.getAfterRead();
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [org.apache.commons.io.build.AbstractStreamBuilder, org.apache.commons.io.input.BOMInputStream$Builder] */
        @Override // org.apache.commons.io.input.ProxyInputStream.AbstractBuilder
        public /* bridge */ /* synthetic */ Builder setAfterRead(IOIntConsumer iOIntConsumer) {
            return super.setAfterRead(iOIntConsumer);
        }

        static ByteOrderMark getDefaultByteOrderMark() {
            return DEFAULT[0];
        }

        @Override // org.apache.commons.io.function.IOSupplier
        public BOMInputStream get() throws IOException {
            return new BOMInputStream(this);
        }

        public Builder setByteOrderMarks(ByteOrderMark... byteOrderMarks) {
            this.byteOrderMarks = byteOrderMarks != null ? (ByteOrderMark[]) byteOrderMarks.clone() : DEFAULT;
            return this;
        }

        public Builder setInclude(boolean include) {
            this.include = include;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private BOMInputStream(Builder builder) throws IOException {
        super(builder);
        if (IOUtils.length(builder.byteOrderMarks) != 0) {
            this.include = builder.include;
            List<ByteOrderMark> list = Arrays.asList(builder.byteOrderMarks);
            list.sort(ByteOrderMarkLengthComparator);
            this.bomList = list;
            return;
        }
        throw new IllegalArgumentException("No ByteOrderMark specified.");
    }

    @Deprecated
    public BOMInputStream(InputStream delegate) {
        this(delegate, false, Builder.DEFAULT);
    }

    @Deprecated
    public BOMInputStream(InputStream delegate, boolean include) {
        this(delegate, include, Builder.DEFAULT);
    }

    @Deprecated
    public BOMInputStream(InputStream delegate, boolean include, ByteOrderMark... boms) {
        super(delegate);
        if (IOUtils.length(boms) == 0) {
            throw new IllegalArgumentException("No BOMs specified");
        }
        this.include = include;
        List<ByteOrderMark> list = Arrays.asList(boms);
        list.sort(ByteOrderMarkLengthComparator);
        this.bomList = list;
    }

    @Deprecated
    public BOMInputStream(InputStream delegate, ByteOrderMark... boms) {
        this(delegate, false, boms);
    }

    private ByteOrderMark find() {
        return this.bomList.stream().filter(new Predicate() { // from class: org.apache.commons.io.input.BOMInputStream$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean matches;
                matches = BOMInputStream.this.matches((ByteOrderMark) obj);
                return matches;
            }
        }).findFirst().orElse(null);
    }

    public ByteOrderMark getBOM() throws IOException {
        if (this.firstBytes == null) {
            this.byteOrderMark = readBom();
        }
        return this.byteOrderMark;
    }

    public String getBOMCharsetName() throws IOException {
        getBOM();
        if (this.byteOrderMark == null) {
            return null;
        }
        return this.byteOrderMark.getCharsetName();
    }

    public boolean hasBOM() throws IOException {
        return getBOM() != null;
    }

    public boolean hasBOM(ByteOrderMark bom) throws IOException {
        if (!this.bomList.contains(bom)) {
            throw new IllegalArgumentException("Stream not configured to detect " + bom);
        }
        return Objects.equals(getBOM(), bom);
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int readLimit) {
        this.markFbIndex = this.fbIndex;
        this.markedAtStart = this.firstBytes == null;
        this.in.mark(readLimit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean matches(ByteOrderMark bom) {
        return bom.matches(this.firstBytes);
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        checkOpen();
        int b = readFirstBytes();
        return b >= 0 ? b : this.in.read();
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] buf) throws IOException {
        return read(buf, 0, buf.length);
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] buf, int off, int len) throws IOException {
        IOUtils.checkFromIndexSize(buf, off, len);
        if (len == 0) {
            return 0;
        }
        int firstCount = 0;
        int b = 0;
        while (len > 0 && b >= 0) {
            b = readFirstBytes();
            if (b >= 0) {
                buf[off] = (byte) (b & 255);
                len--;
                firstCount++;
                off++;
            }
        }
        int secondCount = this.in.read(buf, off, len);
        afterRead(secondCount);
        if (secondCount >= 0) {
            return firstCount + secondCount;
        }
        if (firstCount > 0) {
            return firstCount;
        }
        return -1;
    }

    private ByteOrderMark readBom() throws IOException {
        int fbLength = 0;
        int maxBomSize = this.bomList.get(0).length();
        int[] tmp = new int[maxBomSize];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = this.in.read();
            afterRead(tmp[i]);
            fbLength++;
            if (tmp[i] < 0) {
                break;
            }
        }
        this.firstBytes = Arrays.copyOf(tmp, fbLength);
        ByteOrderMark bom = find();
        if (bom != null && !this.include) {
            if (bom.length() < this.firstBytes.length) {
                this.fbIndex = bom.length();
            } else {
                this.firstBytes = new int[0];
            }
        }
        return bom;
    }

    private int readFirstBytes() throws IOException {
        getBOM();
        if (this.fbIndex >= this.firstBytes.length) {
            return -1;
        }
        int[] iArr = this.firstBytes;
        int i = this.fbIndex;
        this.fbIndex = i + 1;
        return iArr[i];
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        this.fbIndex = this.markFbIndex;
        if (this.markedAtStart) {
            this.firstBytes = null;
        }
        this.in.reset();
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public long skip(long n) throws IOException {
        int skipped = 0;
        while (n > skipped && readFirstBytes() >= 0) {
            skipped++;
        }
        return this.in.skip(n - skipped) + skipped;
    }
}
