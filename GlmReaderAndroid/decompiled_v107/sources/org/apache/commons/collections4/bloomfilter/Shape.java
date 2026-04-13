package org.apache.commons.collections4.bloomfilter;

/* loaded from: classes9.dex */
public final class Shape {
    private final int numberOfBits;
    private final int numberOfHashFunctions;
    private static final double LN_2 = Math.log(2.0d);
    private static final double DENOMINATOR = (-LN_2) * LN_2;

    private static int calculateNumberOfHashFunctions(int numberOfItems, int numberOfBits) {
        long k = Math.round((LN_2 * numberOfBits) / numberOfItems);
        if (k < 1) {
            throw new IllegalArgumentException(String.format("Filter too small: Calculated number of hash functions (%s) was less than 1", Long.valueOf(k)));
        }
        return (int) k;
    }

    private static void checkCalculatedProbability(double probability) {
        if (probability >= 1.0d) {
            throw new IllegalArgumentException("Calculated probability is greater than or equal to 1: " + probability);
        }
    }

    private static int checkNumberOfBits(int numberOfBits) {
        if (numberOfBits < 1) {
            throw new IllegalArgumentException("Number of bits must be greater than 0: " + numberOfBits);
        }
        return numberOfBits;
    }

    private static int checkNumberOfHashFunctions(int numberOfHashFunctions) {
        if (numberOfHashFunctions < 1) {
            throw new IllegalArgumentException("Number of hash functions must be greater than 0: " + numberOfHashFunctions);
        }
        return numberOfHashFunctions;
    }

    private static int checkNumberOfItems(int numberOfItems) {
        if (numberOfItems < 1) {
            throw new IllegalArgumentException("Number of items must be greater than 0: " + numberOfItems);
        }
        return numberOfItems;
    }

    private static void checkProbability(double probability) {
        if (probability <= 0.0d || probability >= 1.0d) {
            throw new IllegalArgumentException("Probability must be greater than 0 and less than 1: " + probability);
        }
    }

    public static Shape fromKM(int numberOfHashFunctions, int numberOfBits) {
        return new Shape(numberOfHashFunctions, numberOfBits);
    }

    public static Shape fromNM(int numberOfItems, int numberOfBits) {
        checkNumberOfItems(numberOfItems);
        checkNumberOfBits(numberOfBits);
        int numberOfHashFunctions = calculateNumberOfHashFunctions(numberOfItems, numberOfBits);
        Shape shape = new Shape(numberOfHashFunctions, numberOfBits);
        checkCalculatedProbability(shape.getProbability(numberOfItems));
        return shape;
    }

    public static Shape fromNMK(int numberOfItems, int numberOfBits, int numberOfHashFunctions) {
        checkNumberOfItems(numberOfItems);
        checkNumberOfBits(numberOfBits);
        checkNumberOfHashFunctions(numberOfHashFunctions);
        Shape shape = new Shape(numberOfHashFunctions, numberOfBits);
        checkCalculatedProbability(shape.getProbability(numberOfItems));
        return shape;
    }

    public static Shape fromNP(int numberOfItems, double probability) {
        checkNumberOfItems(numberOfItems);
        checkProbability(probability);
        double m = Math.ceil((numberOfItems * Math.log(probability)) / DENOMINATOR);
        if (m > 2.147483647E9d) {
            throw new IllegalArgumentException("Resulting filter has more than 2147483647 bits: " + m);
        }
        int numberOfBits = (int) m;
        int numberOfHashFunctions = calculateNumberOfHashFunctions(numberOfItems, numberOfBits);
        Shape shape = new Shape(numberOfHashFunctions, numberOfBits);
        checkCalculatedProbability(shape.getProbability(numberOfItems));
        return shape;
    }

    public static Shape fromPMK(double probability, int numberOfBits, int numberOfHashFunctions) {
        checkProbability(probability);
        checkNumberOfBits(numberOfBits);
        checkNumberOfHashFunctions(numberOfHashFunctions);
        double n = Math.ceil(numberOfBits / ((-numberOfHashFunctions) / Math.log(-Math.expm1(Math.log(probability) / numberOfHashFunctions))));
        Shape shape = new Shape(numberOfHashFunctions, numberOfBits);
        checkCalculatedProbability(shape.getProbability((int) n));
        return shape;
    }

    private Shape(int numberOfHashFunctions, int numberOfBits) {
        this.numberOfHashFunctions = checkNumberOfHashFunctions(numberOfHashFunctions);
        this.numberOfBits = checkNumberOfBits(numberOfBits);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Shape)) {
            return false;
        }
        Shape other = (Shape) obj;
        return this.numberOfBits == other.numberOfBits && this.numberOfHashFunctions == other.numberOfHashFunctions;
    }

    public double estimateMaxN() {
        return (this.numberOfBits * LN_2) / this.numberOfHashFunctions;
    }

    public double estimateN(int cardinality) {
        double c = cardinality;
        double m = this.numberOfBits;
        double k = this.numberOfHashFunctions;
        return (-(m / k)) * Math.log1p((-c) / m);
    }

    public int getNumberOfBits() {
        return this.numberOfBits;
    }

    public int getNumberOfHashFunctions() {
        return this.numberOfHashFunctions;
    }

    public double getProbability(int numberOfItems) {
        if (numberOfItems < 0) {
            throw new IllegalArgumentException("Number of items must be greater than or equal to 0: " + numberOfItems);
        }
        if (numberOfItems == 0) {
            return 0.0d;
        }
        return Math.pow(-Math.expm1(((this.numberOfHashFunctions * (-1.0d)) * numberOfItems) / this.numberOfBits), this.numberOfHashFunctions);
    }

    public int hashCode() {
        return ((this.numberOfBits + 31) * 31) + this.numberOfHashFunctions;
    }

    public boolean isSparse(int cardinality) {
        return cardinality <= BitMaps.numberOfBitMaps(this) * 2;
    }

    public String toString() {
        return String.format("Shape[k=%s m=%s]", Integer.valueOf(this.numberOfHashFunctions), Integer.valueOf(this.numberOfBits));
    }
}
