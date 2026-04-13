package org.apache.commons.math3.geometry.enclosing;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;

/* loaded from: classes10.dex */
public class WelzlEncloser<S extends Space, P extends Point<S>> implements Encloser<S, P> {
    private final SupportBallGenerator<S, P> generator;
    private final double tolerance;

    public WelzlEncloser(double tolerance, SupportBallGenerator<S, P> generator) {
        this.tolerance = tolerance;
        this.generator = generator;
    }

    @Override // org.apache.commons.math3.geometry.enclosing.Encloser
    public EnclosingBall<S, P> enclose(Iterable<P> points) {
        if (points == null || !points.iterator().hasNext()) {
            return this.generator.ballOnSupport(new ArrayList());
        }
        return pivotingBall(points);
    }

    private EnclosingBall<S, P> pivotingBall(Iterable<P> points) {
        P first = points.iterator().next();
        List<P> extreme = new ArrayList<>(first.getSpace().getDimension() + 1);
        List<P> support = new ArrayList<>(first.getSpace().getDimension() + 1);
        extreme.add(first);
        EnclosingBall<S, P> ball = moveToFrontBall(extreme, extreme.size(), support);
        while (true) {
            P farthest = selectFarthest(points, ball);
            if (ball.contains(farthest, this.tolerance)) {
                return ball;
            }
            support.clear();
            support.add(farthest);
            EnclosingBall<S, P> savedBall = ball;
            ball = moveToFrontBall(extreme, extreme.size(), support);
            if (ball.getRadius() < savedBall.getRadius()) {
                throw new MathInternalError();
            }
            extreme.add(0, farthest);
            extreme.subList(ball.getSupportSize(), extreme.size()).clear();
        }
    }

    private EnclosingBall<S, P> moveToFrontBall(List<P> extreme, int nbExtreme, List<P> support) {
        EnclosingBall<S, P> ball = this.generator.ballOnSupport(support);
        if (ball.getSupportSize() <= ball.getCenter().getSpace().getDimension()) {
            for (int i = 0; i < nbExtreme; i++) {
                P pi = extreme.get(i);
                if (!ball.contains(pi, this.tolerance)) {
                    support.add(pi);
                    ball = moveToFrontBall(extreme, i, support);
                    support.remove(support.size() - 1);
                    for (int j = i; j > 0; j--) {
                        extreme.set(j, extreme.get(j - 1));
                    }
                    extreme.set(0, pi);
                }
            }
        }
        return ball;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public P selectFarthest(Iterable<P> iterable, EnclosingBall<S, P> enclosingBall) {
        P center = enclosingBall.getCenter();
        P p = null;
        double d = -1.0d;
        for (P p2 : iterable) {
            double distance = p2.distance(center);
            if (distance > d) {
                p = p2;
                d = distance;
            }
        }
        return p;
    }
}
