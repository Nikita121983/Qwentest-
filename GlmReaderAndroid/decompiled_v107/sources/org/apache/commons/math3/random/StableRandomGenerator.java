package org.apache.commons.math3.random;

import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class StableRandomGenerator implements NormalizedRandomGenerator {
    private final double alpha;
    private final double beta;
    private final RandomGenerator generator;
    private final double zeta;

    public StableRandomGenerator(RandomGenerator generator, double alpha, double beta) throws NullArgumentException, OutOfRangeException {
        if (generator == null) {
            throw new NullArgumentException();
        }
        if (alpha <= 0.0d || alpha > 2.0d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_LEFT, Double.valueOf(alpha), 0, 2);
        }
        if (beta < -1.0d || beta > 1.0d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_SIMPLE, Double.valueOf(beta), -1, 1);
        }
        this.generator = generator;
        this.alpha = alpha;
        this.beta = beta;
        if (alpha < 2.0d && beta != 0.0d) {
            this.zeta = FastMath.tan((3.141592653589793d * alpha) / 2.0d) * beta;
        } else {
            this.zeta = 0.0d;
        }
    }

    @Override // org.apache.commons.math3.random.NormalizedRandomGenerator
    public double nextNormalizedDouble() {
        double omega = -FastMath.log(this.generator.nextDouble());
        double phi = (this.generator.nextDouble() - 0.5d) * 3.141592653589793d;
        if (this.alpha == 2.0d) {
            return FastMath.sqrt(2.0d * omega) * FastMath.sin(phi);
        }
        if (this.beta == 0.0d) {
            if (this.alpha != 1.0d) {
                return (FastMath.pow(FastMath.cos((1.0d - this.alpha) * phi) * omega, (1.0d / this.alpha) - 1.0d) * FastMath.sin(this.alpha * phi)) / FastMath.pow(FastMath.cos(phi), 1.0d / this.alpha);
            }
            return FastMath.tan(phi);
        }
        double cosPhi = FastMath.cos(phi);
        if (FastMath.abs(this.alpha - 1.0d) <= 1.0E-8d) {
            double betaPhi = (this.beta * phi) + 1.5707963267948966d;
            double x = 0.6366197723675814d * ((FastMath.tan(phi) * betaPhi) - (this.beta * FastMath.log(((1.5707963267948966d * omega) * cosPhi) / betaPhi)));
            if (this.alpha != 1.0d) {
                return x + (this.beta * FastMath.tan((this.alpha * 3.141592653589793d) / 2.0d));
            }
            return x;
        }
        double alphaPhi = this.alpha * phi;
        double invAlphaPhi = phi - alphaPhi;
        return (((FastMath.sin(alphaPhi) + (this.zeta * FastMath.cos(alphaPhi))) / cosPhi) * (FastMath.cos(invAlphaPhi) + (this.zeta * FastMath.sin(invAlphaPhi)))) / FastMath.pow(omega * cosPhi, (1.0d - this.alpha) / this.alpha);
    }
}
