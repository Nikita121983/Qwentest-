package org.apache.poi.sl.draw.geom;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: classes10.dex */
public class Context {
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)([eE][+-]?(\\p{Digit}+))?)|(\\.(\\p{Digit}+)([eE][+-]?(\\p{Digit}+))?)|(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");
    private final Rectangle2D _anchor;
    private final Map<String, Double> _ctx = new HashMap();
    private final IAdjustableShape _props;

    public Context(CustomGeometry geom, Rectangle2D anchor, IAdjustableShape props) {
        this._props = props;
        this._anchor = anchor;
        for (GuideIf gd : geom.adjusts) {
            evaluate(gd);
        }
        for (GuideIf gd2 : geom.guides) {
            evaluate(gd2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Rectangle2D getShapeAnchor() {
        return this._anchor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GuideIf getAdjustValue(String name) {
        return this._props.getAdjustValue(name);
    }

    public double getValue(String key) {
        if (DOUBLE_PATTERN.matcher(key).matches()) {
            return Double.parseDouble(key);
        }
        return this._ctx.containsKey(key) ? this._ctx.get(key).doubleValue() : evaluate(BuiltInGuide.valueOf("_" + key));
    }

    public double evaluate(Formula fmla) {
        String key;
        double result = fmla.evaluate(this);
        if ((fmla instanceof GuideIf) && (key = ((GuideIf) fmla).getName()) != null) {
            this._ctx.put(key, Double.valueOf(result));
        }
        return result;
    }
}
