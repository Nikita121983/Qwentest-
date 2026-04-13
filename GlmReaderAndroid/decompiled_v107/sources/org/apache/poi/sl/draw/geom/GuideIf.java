package org.apache.poi.sl.draw.geom;

import java.util.regex.Pattern;

/* loaded from: classes10.dex */
public interface GuideIf extends Formula {
    public static final Pattern WHITESPACE = Pattern.compile("\\s+");

    /* loaded from: classes10.dex */
    public enum Op {
        muldiv,
        addsub,
        adddiv,
        ifelse,
        val,
        abs,
        sqrt,
        max,
        min,
        at2,
        sin,
        cos,
        tan,
        cat2,
        sat2,
        pin,
        mod
    }

    String getFmla();

    String getName();

    void setFmla(String str);

    void setName(String str);

    @Override // org.apache.poi.sl.draw.geom.Formula
    default double evaluate(Context ctx) {
        return evaluateGuide(ctx);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    default double evaluateGuide(Context ctx) {
        char c;
        Op op;
        String[] operands = WHITESPACE.split(getFmla());
        String str = operands[0];
        switch (str.hashCode()) {
            case 1349:
                if (str.equals("*/")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1378:
                if (str.equals("+-")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1380:
                if (str.equals("+/")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 2011:
                if (str.equals("?:")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                op = Op.muldiv;
                break;
            case 1:
                op = Op.addsub;
                break;
            case 2:
                op = Op.adddiv;
                break;
            case 3:
                op = Op.ifelse;
                break;
            default:
                op = Op.valueOf(operands[0]);
                break;
        }
        double x = operands.length > 1 ? ctx.getValue(operands[1]) : 0.0d;
        double y = operands.length > 2 ? ctx.getValue(operands[2]) : 0.0d;
        double z = operands.length > 3 ? ctx.getValue(operands[3]) : 0.0d;
        switch (op) {
            case abs:
                return Math.abs(x);
            case adddiv:
                if (z == 0.0d) {
                    return 0.0d;
                }
                return (x + y) / z;
            case addsub:
                return (x + y) - z;
            case at2:
                return Math.toDegrees(Math.atan2(y, x)) * 60000.0d;
            case cos:
                return Math.cos(Math.toRadians(y / 60000.0d)) * x;
            case cat2:
                return Math.cos(Math.atan2(z, y)) * x;
            case ifelse:
                return x > 0.0d ? y : z;
            case val:
                return x;
            case max:
                return Math.max(x, y);
            case min:
                return Math.min(x, y);
            case mod:
                return Math.sqrt((x * x) + (y * y) + (z * z));
            case muldiv:
                if (z == 0.0d) {
                    return 0.0d;
                }
                return (x * y) / z;
            case pin:
                return Math.max(x, Math.min(y, z));
            case sat2:
                return Math.sin(Math.atan2(z, y)) * x;
            case sin:
                return Math.sin(Math.toRadians(y / 60000.0d)) * x;
            case sqrt:
                return Math.sqrt(x);
            case tan:
                return Math.tan(Math.toRadians(y / 60000.0d)) * x;
            default:
                return 0.0d;
        }
    }
}
