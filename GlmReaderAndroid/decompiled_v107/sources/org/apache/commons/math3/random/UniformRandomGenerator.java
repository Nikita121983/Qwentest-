package org.apache.commons.math3.random;

import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class UniformRandomGenerator implements NormalizedRandomGenerator {
    private static final double SQRT3 = FastMath.sqrt(3.0d);
    private final RandomGenerator generator;

    public UniformRandomGenerator(RandomGenerator generator) {
        this.generator = generator;
    }

    @Override // org.apache.commons.math3.random.NormalizedRandomGenerator
    public double nextNormalizedDouble() {
        return SQRT3 * ((this.generator.nextDouble() * 2.0d) - 1.0d);
    }
}
