package org.apache.poi.sl.draw.geom;

/* loaded from: classes10.dex */
public interface AdjustValueIf extends GuideIf {
    @Override // org.apache.poi.sl.draw.geom.GuideIf, org.apache.poi.sl.draw.geom.Formula
    default double evaluate(Context ctx) {
        return evaluateAdjustValue(ctx);
    }

    default double evaluateAdjustValue(Context ctx) {
        String name = getName();
        GuideIf adj = ctx.getAdjustValue(name);
        return adj != null ? adj.evaluate(ctx) : evaluateGuide(ctx);
    }
}
