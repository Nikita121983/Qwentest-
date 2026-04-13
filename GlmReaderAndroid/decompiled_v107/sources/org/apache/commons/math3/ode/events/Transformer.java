package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
enum Transformer {
    UNINITIALIZED { // from class: org.apache.commons.math3.ode.events.Transformer.1
        @Override // org.apache.commons.math3.ode.events.Transformer
        protected double transformed(double g) {
            return 0.0d;
        }
    },
    PLUS { // from class: org.apache.commons.math3.ode.events.Transformer.2
        @Override // org.apache.commons.math3.ode.events.Transformer
        protected double transformed(double g) {
            return g;
        }
    },
    MINUS { // from class: org.apache.commons.math3.ode.events.Transformer.3
        @Override // org.apache.commons.math3.ode.events.Transformer
        protected double transformed(double g) {
            return -g;
        }
    },
    MIN { // from class: org.apache.commons.math3.ode.events.Transformer.4
        @Override // org.apache.commons.math3.ode.events.Transformer
        protected double transformed(double g) {
            return FastMath.min(-Precision.SAFE_MIN, FastMath.min(-g, g));
        }
    },
    MAX { // from class: org.apache.commons.math3.ode.events.Transformer.5
        @Override // org.apache.commons.math3.ode.events.Transformer
        protected double transformed(double g) {
            return FastMath.max(Precision.SAFE_MIN, FastMath.max(-g, g));
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract double transformed(double d);
}
