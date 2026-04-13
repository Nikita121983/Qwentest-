package org.apache.commons.compress.compressors.lz77support;

/* loaded from: classes9.dex */
public final class Parameters {
    public static final int TRUE_MIN_BACK_REFERENCE_LENGTH = 3;
    private final boolean lazyMatching;
    private final int lazyThreshold;
    private final int maxBackReferenceLength;
    private final int maxCandidates;
    private final int maxLiteralLength;
    private final int maxOffset;
    private final int minBackReferenceLength;
    private final int niceBackReferenceLength;
    private final int windowSize;

    /* loaded from: classes9.dex */
    public static class Builder {
        private Boolean lazyMatches;
        private Integer lazyThreshold;
        private int maxBackReferenceLength;
        private Integer maxCandidates;
        private int maxLiteralLength;
        private int maxOffset;
        private int minBackReferenceLength;
        private Integer niceBackReferenceLength;
        private final int windowSize;

        private Builder(int windowSize) {
            if (windowSize < 2 || !Parameters.isPowerOfTwo(windowSize)) {
                throw new IllegalArgumentException("windowSize must be a power of two");
            }
            this.windowSize = windowSize;
            this.minBackReferenceLength = 3;
            this.maxBackReferenceLength = windowSize - 1;
            this.maxOffset = windowSize - 1;
            this.maxLiteralLength = windowSize;
        }

        public Parameters build() {
            int i;
            int threshold;
            int niceLen = this.niceBackReferenceLength != null ? this.niceBackReferenceLength.intValue() : Math.max(this.minBackReferenceLength, this.maxBackReferenceLength / 2);
            int candidates = this.maxCandidates != null ? this.maxCandidates.intValue() : Math.max(256, this.windowSize / 128);
            boolean lazy = this.lazyMatches == null || this.lazyMatches.booleanValue();
            if (!lazy) {
                i = this.minBackReferenceLength;
            } else {
                if (this.lazyThreshold == null) {
                    threshold = niceLen;
                    return new Parameters(this.windowSize, this.minBackReferenceLength, this.maxBackReferenceLength, this.maxOffset, this.maxLiteralLength, niceLen, candidates, lazy, threshold);
                }
                i = this.lazyThreshold.intValue();
            }
            threshold = i;
            return new Parameters(this.windowSize, this.minBackReferenceLength, this.maxBackReferenceLength, this.maxOffset, this.maxLiteralLength, niceLen, candidates, lazy, threshold);
        }

        public Builder tunedForCompressionRatio() {
            Integer valueOf = Integer.valueOf(this.maxBackReferenceLength);
            this.lazyThreshold = valueOf;
            this.niceBackReferenceLength = valueOf;
            this.maxCandidates = Integer.valueOf(Math.max(32, this.windowSize / 16));
            this.lazyMatches = true;
            return this;
        }

        public Builder tunedForSpeed() {
            this.niceBackReferenceLength = Integer.valueOf(Math.max(this.minBackReferenceLength, this.maxBackReferenceLength / 8));
            this.maxCandidates = Integer.valueOf(Math.max(32, this.windowSize / 1024));
            this.lazyMatches = false;
            this.lazyThreshold = Integer.valueOf(this.minBackReferenceLength);
            return this;
        }

        public Builder withLazyMatching(boolean lazy) {
            this.lazyMatches = Boolean.valueOf(lazy);
            return this;
        }

        public Builder withLazyThreshold(int threshold) {
            this.lazyThreshold = Integer.valueOf(threshold);
            return this;
        }

        public Builder withMaxBackReferenceLength(int maxBackReferenceLength) {
            this.maxBackReferenceLength = maxBackReferenceLength < this.minBackReferenceLength ? this.minBackReferenceLength : Math.min(maxBackReferenceLength, this.windowSize - 1);
            return this;
        }

        public Builder withMaxLiteralLength(int maxLiteralLength) {
            this.maxLiteralLength = maxLiteralLength < 1 ? this.windowSize : Math.min(maxLiteralLength, this.windowSize);
            return this;
        }

        public Builder withMaxNumberOfCandidates(int maxCandidates) {
            this.maxCandidates = Integer.valueOf(maxCandidates);
            return this;
        }

        public Builder withMaxOffset(int maxOffset) {
            int i = this.windowSize - 1;
            if (maxOffset >= 1) {
                i = Math.min(maxOffset, i);
            }
            this.maxOffset = i;
            return this;
        }

        public Builder withMinBackReferenceLength(int minBackReferenceLength) {
            this.minBackReferenceLength = Math.max(3, minBackReferenceLength);
            if (this.windowSize < this.minBackReferenceLength) {
                throw new IllegalArgumentException("minBackReferenceLength can't be bigger than windowSize");
            }
            if (this.maxBackReferenceLength < this.minBackReferenceLength) {
                this.maxBackReferenceLength = this.minBackReferenceLength;
            }
            return this;
        }

        public Builder withNiceBackReferenceLength(int niceLen) {
            this.niceBackReferenceLength = Integer.valueOf(niceLen);
            return this;
        }
    }

    public static Builder builder(int windowSize) {
        return new Builder(windowSize);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPowerOfTwo(int x) {
        return ((x + (-1)) & x) == 0;
    }

    private Parameters(int windowSize, int minBackReferenceLength, int maxBackReferenceLength, int maxOffset, int maxLiteralLength, int niceBackReferenceLength, int maxCandidates, boolean lazyMatching, int lazyThreshold) {
        this.windowSize = windowSize;
        this.minBackReferenceLength = minBackReferenceLength;
        this.maxBackReferenceLength = maxBackReferenceLength;
        this.maxOffset = maxOffset;
        this.maxLiteralLength = maxLiteralLength;
        this.niceBackReferenceLength = niceBackReferenceLength;
        this.maxCandidates = maxCandidates;
        this.lazyMatching = lazyMatching;
        this.lazyThreshold = lazyThreshold;
    }

    public boolean getLazyMatching() {
        return this.lazyMatching;
    }

    public int getLazyMatchingThreshold() {
        return this.lazyThreshold;
    }

    public int getMaxBackReferenceLength() {
        return this.maxBackReferenceLength;
    }

    public int getMaxCandidates() {
        return this.maxCandidates;
    }

    public int getMaxLiteralLength() {
        return this.maxLiteralLength;
    }

    public int getMaxOffset() {
        return this.maxOffset;
    }

    public int getMinBackReferenceLength() {
        return this.minBackReferenceLength;
    }

    public int getNiceBackReferenceLength() {
        return this.niceBackReferenceLength;
    }

    public int getWindowSize() {
        return this.windowSize;
    }
}
