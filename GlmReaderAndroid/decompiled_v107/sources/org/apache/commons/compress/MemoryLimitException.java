package org.apache.commons.compress;

/* loaded from: classes9.dex */
public class MemoryLimitException extends CompressException {
    private static final long serialVersionUID = 1;
    private final int memoryLimitKiB;
    private final long memoryNeededKiB;

    private static String buildMessage(long memoryNeededInKb, int memoryLimitInKb) {
        return String.format("%,d KiB of memory would be needed; limit was %,d KiB. If the file is not corrupt, consider increasing the memory limit.", Long.valueOf(memoryNeededInKb), Integer.valueOf(memoryLimitInKb));
    }

    public MemoryLimitException(long memoryNeededKiB, int memoryLimitKiB) {
        super(buildMessage(memoryNeededKiB, memoryLimitKiB));
        this.memoryNeededKiB = memoryNeededKiB;
        this.memoryLimitKiB = memoryLimitKiB;
    }

    @Deprecated
    public MemoryLimitException(long memoryNeededKiB, int memoryLimitKiB, Exception cause) {
        super(buildMessage(memoryNeededKiB, memoryLimitKiB), cause);
        this.memoryNeededKiB = memoryNeededKiB;
        this.memoryLimitKiB = memoryLimitKiB;
    }

    public MemoryLimitException(long memoryNeededKiB, int memoryLimitKiB, Throwable cause) {
        super(buildMessage(memoryNeededKiB, memoryLimitKiB), cause);
        this.memoryNeededKiB = memoryNeededKiB;
        this.memoryLimitKiB = memoryLimitKiB;
    }

    public int getMemoryLimitInKb() {
        return this.memoryLimitKiB;
    }

    public long getMemoryNeededInKb() {
        return this.memoryNeededKiB;
    }
}
