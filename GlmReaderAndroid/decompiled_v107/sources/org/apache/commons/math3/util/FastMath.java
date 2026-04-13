package org.apache.commons.math3.util;

import java.io.PrintStream;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.poi.ss.util.IEEEDouble;

/* loaded from: classes10.dex */
public class FastMath {
    public static final double E = 2.718281828459045d;
    static final int EXP_FRAC_TABLE_LEN = 1025;
    static final int EXP_INT_TABLE_LEN = 1500;
    static final int EXP_INT_TABLE_MAX_INDEX = 750;
    private static final double F_11_12 = 0.9166666666666666d;
    private static final double F_13_14 = 0.9285714285714286d;
    private static final double F_15_16 = 0.9375d;
    private static final double F_1_11 = 0.09090909090909091d;
    private static final double F_1_13 = 0.07692307692307693d;
    private static final double F_1_15 = 0.06666666666666667d;
    private static final double F_1_17 = 0.058823529411764705d;
    private static final double F_1_3 = 0.3333333333333333d;
    private static final double F_1_5 = 0.2d;
    private static final double F_1_7 = 0.14285714285714285d;
    private static final double F_1_9 = 0.1111111111111111d;
    private static final double F_5_6 = 0.8333333333333334d;
    private static final double F_9_10 = 0.9d;
    private static final long HEX_40000000 = 1073741824;
    private static final long IMPLICIT_HIGH_BIT = 4503599627370496L;
    private static final double LN_2_A = 0.6931470632553101d;
    private static final double LN_2_B = 1.1730463525082348E-7d;
    static final int LN_MANT_LEN = 1024;
    private static final long MASK_30BITS = -1073741824;
    private static final long MASK_DOUBLE_EXPONENT = 9218868437227405312L;
    private static final long MASK_DOUBLE_MANTISSA = 4503599627370495L;
    private static final int MASK_NON_SIGN_INT = Integer.MAX_VALUE;
    private static final long MASK_NON_SIGN_LONG = Long.MAX_VALUE;
    public static final double PI = 3.141592653589793d;
    private static final boolean RECOMPUTE_TABLES_AT_RUNTIME = false;
    private static final int SINE_TABLE_LEN = 14;
    private static final double TWO_POWER_52 = 4.503599627370496E15d;
    private static final double LOG_MAX_VALUE = StrictMath.log(Double.MAX_VALUE);
    private static final double[][] LN_QUICK_COEF = {new double[]{1.0d, 5.669184079525E-24d}, new double[]{-0.25d, -0.25d}, new double[]{0.3333333134651184d, 1.986821492305628E-8d}, new double[]{-0.25d, -6.663542893624021E-14d}, new double[]{0.19999998807907104d, 1.1921056801463227E-8d}, new double[]{-0.1666666567325592d, -7.800414592973399E-9d}, new double[]{0.1428571343421936d, 5.650007086920087E-9d}, new double[]{-0.12502530217170715d, -7.44321345601866E-11d}, new double[]{0.11113807559013367d, 9.219544613762692E-9d}};
    private static final double[][] LN_HI_PREC_COEF = {new double[]{1.0d, -6.032174644509064E-23d}, new double[]{-0.25d, -0.25d}, new double[]{0.3333333134651184d, 1.9868161777724352E-8d}, new double[]{-0.2499999701976776d, -2.957007209750105E-8d}, new double[]{0.19999954104423523d, 1.5830993332061267E-10d}, new double[]{-0.16624879837036133d, -2.6033824355191673E-8d}};
    private static final double[] SINE_TABLE_A = {0.0d, 0.1246747374534607d, 0.24740394949913025d, 0.366272509098053d, 0.4794255495071411d, 0.5850973129272461d, 0.6816387176513672d, 0.7675435543060303d, 0.8414709568023682d, 0.902267575263977d, 0.9489846229553223d, 0.9808930158615112d, 0.9974949359893799d, 0.9985313415527344d};
    private static final double[] SINE_TABLE_B = {0.0d, -4.068233003401932E-9d, 9.755392680573412E-9d, 1.9987994582857286E-8d, -1.0902938113007961E-8d, -3.9986783938944604E-8d, 4.23719669792332E-8d, -5.207000323380292E-8d, 2.800552834259E-8d, 1.883511811213715E-8d, -3.5997360512765566E-9d, 4.116164446561962E-8d, 5.0614674548127384E-8d, -1.0129027912496858E-9d};
    private static final double[] COSINE_TABLE_A = {1.0d, 0.9921976327896118d, 0.9689123630523682d, 0.9305076599121094d, 0.8775825500488281d, 0.8109631538391113d, 0.7316888570785522d, 0.6409968137741089d, 0.5403022766113281d, 0.4311765432357788d, 0.3153223395347595d, 0.19454771280288696d, 0.07073719799518585d, -0.05417713522911072d};
    private static final double[] COSINE_TABLE_B = {0.0d, 3.4439717236742845E-8d, 5.865827662008209E-8d, -3.7999795083850525E-8d, 1.184154459111628E-8d, -3.43338934259355E-8d, 1.1795268640216787E-8d, 4.438921624363781E-8d, 2.925681159240093E-8d, -2.6437112632041807E-8d, 2.2860509143963117E-8d, -4.813899778443457E-9d, 3.6725170580355583E-9d, 2.0217439756338078E-10d};
    private static final double[] TANGENT_TABLE_A = {0.0d, 0.1256551444530487d, 0.25534194707870483d, 0.3936265707015991d, 0.5463024377822876d, 0.7214844226837158d, 0.9315965175628662d, 1.1974215507507324d, 1.5574076175689697d, 2.092571258544922d, 3.0095696449279785d, 5.041914939880371d, 14.101419448852539d, -18.430862426757812d};
    private static final double[] TANGENT_TABLE_B = {0.0d, -7.877917738262007E-9d, -2.5857668567479893E-8d, 5.2240336371356666E-9d, 5.206150291559893E-8d, 1.8307188599677033E-8d, -5.7618793749770706E-8d, 7.848361555046424E-8d, 1.0708593250394448E-7d, 1.7827257129423813E-8d, 2.893485277253286E-8d, 3.1660099222737955E-7d, 4.983191803254889E-7d, -3.356118100840571E-7d};
    private static final long[] RECIP_2PI = {2935890503282001226L, 9154082963658192752L, 3952090531849364496L, 9193070505571053912L, 7910884519577875640L, 113236205062349959L, 4577762542105553359L, -5034868814120038111L, 4208363204685324176L, 5648769086999809661L, 2819561105158720014L, -4035746434778044925L, -302932621132653753L, -2644281811660520851L, -3183605296591799669L, 6722166367014452318L, -3512299194304650054L, -7278142539171889152L};
    private static final long[] PI_O_4_BITS = {-3958705157555305932L, -4267615245585081135L};
    private static final double F_1_4 = 0.25d;
    private static final double F_1_2 = 0.5d;
    private static final double F_3_4 = 0.75d;
    private static final double F_7_8 = 0.875d;
    private static final double[] EIGHTHS = {0.0d, 0.125d, F_1_4, 0.375d, F_1_2, 0.625d, F_3_4, F_7_8, 1.0d, 1.125d, 1.25d, 1.375d, 1.5d, 1.625d};
    private static final double[] CBRTTWO = {0.6299605249474366d, 0.7937005259840998d, 1.0d, 1.2599210498948732d, 1.5874010519681994d};

    private FastMath() {
    }

    private static double doubleHighPart(double d) {
        if (d > (-Precision.SAFE_MIN) && d < Precision.SAFE_MIN) {
            return d;
        }
        long xl = Double.doubleToRawLongBits(d);
        return Double.longBitsToDouble(xl & MASK_30BITS);
    }

    public static double sqrt(double a) {
        return Math.sqrt(a);
    }

    public static double cosh(double x) {
        double x2 = x;
        if (x2 != x2) {
            return x2;
        }
        if (x2 > 20.0d) {
            if (x2 >= LOG_MAX_VALUE) {
                double t = exp(x2 * F_1_2);
                return F_1_2 * t * t;
            }
            return exp(x2) * F_1_2;
        }
        if (x2 < -20.0d) {
            if (x2 <= (-LOG_MAX_VALUE)) {
                double t2 = exp((-0.5d) * x2);
                return F_1_2 * t2 * t2;
            }
            return exp(-x2) * F_1_2;
        }
        double[] hiPrec = new double[2];
        if (x2 < 0.0d) {
            x2 = -x2;
        }
        exp(x2, 0.0d, hiPrec);
        double ya = hiPrec[0] + hiPrec[1];
        double yb = -((ya - hiPrec[0]) - hiPrec[1]);
        double temp = ya * 1.073741824E9d;
        double yaa = (ya + temp) - temp;
        double yab = ya - yaa;
        double recip = 1.0d / ya;
        double temp2 = 1.073741824E9d * recip;
        double recipa = (recip + temp2) - temp2;
        double recipb = recip - recipa;
        double recipb2 = recipb + (((((1.0d - (yaa * recipa)) - (yaa * recipb)) - (yab * recipa)) - (yab * recipb)) * recip) + ((-yb) * recip * recip);
        double temp3 = ya + recipa;
        double yb2 = yb + (-((temp3 - ya) - recipa));
        double temp4 = temp3 + recipb2;
        double result = temp4 + yb2 + (-((temp4 - temp3) - recipb2));
        return result * F_1_2;
    }

    public static double sinh(double x) {
        boolean negate;
        double result;
        double x2 = x;
        boolean negate2 = false;
        if (x2 != x2) {
            return x2;
        }
        if (x2 > 20.0d) {
            if (x2 >= LOG_MAX_VALUE) {
                double t = exp(x2 * F_1_2);
                return F_1_2 * t * t;
            }
            return exp(x2) * F_1_2;
        }
        if (x2 < -20.0d) {
            if (x2 <= (-LOG_MAX_VALUE)) {
                double t2 = exp(x2 * (-0.5d));
                return (-0.5d) * t2 * t2;
            }
            return exp(-x2) * (-0.5d);
        }
        if (x2 == 0.0d) {
            return x2;
        }
        if (x2 < 0.0d) {
            x2 = -x2;
            negate2 = true;
        }
        if (x2 > F_1_4) {
            double[] hiPrec = new double[2];
            exp(x2, 0.0d, hiPrec);
            double ya = hiPrec[0] + hiPrec[1];
            double yb = -((ya - hiPrec[0]) - hiPrec[1]);
            double temp = ya * 1.073741824E9d;
            double yaa = (ya + temp) - temp;
            double yab = ya - yaa;
            double recip = 1.0d / ya;
            double temp2 = 1.073741824E9d * recip;
            double recipa = (recip + temp2) - temp2;
            double recipb = recip - recipa;
            double recipb2 = recipb + (((((1.0d - (yaa * recipa)) - (yaa * recipb)) - (yab * recipa)) - (yab * recipb)) * recip);
            double recipa2 = -recipa;
            double recipb3 = -(((-yb) * recip * recip) + recipb2);
            double temp3 = ya + recipa2;
            negate = negate2;
            double yb2 = yb + (-((temp3 - ya) - recipa2));
            double ya2 = temp3 + recipb3;
            double result2 = ya2 + yb2 + (-((ya2 - temp3) - recipb3));
            result = result2 * F_1_2;
        } else {
            negate = negate2;
            double[] hiPrec2 = new double[2];
            expm1(x2, hiPrec2);
            double ya3 = hiPrec2[0] + hiPrec2[1];
            double yb3 = -((ya3 - hiPrec2[0]) - hiPrec2[1]);
            double denom = ya3 + 1.0d;
            double denomr = 1.0d / denom;
            double denomb = (-((denom - 1.0d) - ya3)) + yb3;
            double ratio = ya3 * denomr;
            double temp4 = ratio * 1.073741824E9d;
            double ra = (ratio + temp4) - temp4;
            double rb = ratio - ra;
            double temp5 = 1.073741824E9d * denom;
            double za = (denom + temp5) - temp5;
            double zb = denom - za;
            double x3 = -ya3;
            double rb2 = rb + (((((ya3 - (za * ra)) - (za * rb)) - (zb * ra)) - (zb * rb)) * denomr) + (yb3 * denomr) + (x3 * denomb * denomr * denomr);
            double temp6 = ya3 + ra;
            double yb4 = yb3 + (-((temp6 - ya3) - ra));
            double temp7 = temp6 + rb2;
            double result3 = temp7 + yb4 + (-((temp7 - temp6) - rb2));
            result = result3 * F_1_2;
        }
        if (negate) {
            return -result;
        }
        return result;
    }

    public static double tanh(double x) {
        boolean negate;
        double db;
        double x2 = x;
        boolean negate2 = false;
        if (x2 != x2) {
            return x2;
        }
        if (x2 > 20.0d) {
            return 1.0d;
        }
        if (x2 < -20.0d) {
            return -1.0d;
        }
        if (x2 == 0.0d) {
            return x2;
        }
        if (x2 < 0.0d) {
            x2 = -x2;
            negate2 = true;
        }
        if (x2 >= F_1_2) {
            double[] hiPrec = new double[2];
            exp(x2 * 2.0d, 0.0d, hiPrec);
            double ya = hiPrec[0] + hiPrec[1];
            double yb = -((ya - hiPrec[0]) - hiPrec[1]);
            double na = (-1.0d) + ya;
            double nb = -((na + 1.0d) - ya);
            double temp = na + yb;
            double nb2 = nb + (-((temp - na) - yb));
            double da = ya + 1.0d;
            double x3 = (da - 1.0d) - ya;
            double db2 = -x3;
            double temp2 = da + yb;
            double db3 = (temp2 - da) - yb;
            double temp3 = temp2 * 1.073741824E9d;
            double daa = (temp2 + temp3) - temp3;
            double dab = temp2 - daa;
            double ratio = temp / temp2;
            double temp4 = ratio * 1.073741824E9d;
            double ratioa = (ratio + temp4) - temp4;
            double ratiob = ratio - ratioa;
            negate = negate2;
            double result = ratioa + ratiob + (((((temp - (daa * ratioa)) - (daa * ratiob)) - (dab * ratioa)) - (dab * ratiob)) / temp2) + (nb2 / temp2) + ((((-(db2 + (-db3))) * temp) / temp2) / temp2);
            db = result;
        } else {
            double x4 = x2;
            negate = negate2;
            double[] hiPrec2 = new double[2];
            expm1(x4 * 2.0d, hiPrec2);
            double ya2 = hiPrec2[0] + hiPrec2[1];
            double yb2 = -((ya2 - hiPrec2[0]) - hiPrec2[1]);
            double da2 = ya2 + 2.0d;
            double db4 = -((da2 - 2.0d) - ya2);
            double temp5 = da2 + yb2;
            double db5 = db4 + (-((temp5 - da2) - yb2));
            double da3 = temp5 * 1.073741824E9d;
            double daa2 = (temp5 + da3) - da3;
            double dab2 = temp5 - daa2;
            double ratio2 = ya2 / temp5;
            double temp6 = ratio2 * 1.073741824E9d;
            double ratioa2 = (ratio2 + temp6) - temp6;
            double ratiob2 = ratio2 - ratioa2;
            db = ratioa2 + ratiob2 + (((((ya2 - (daa2 * ratioa2)) - (daa2 * ratiob2)) - (dab2 * ratioa2)) - (dab2 * ratiob2)) / temp5) + (yb2 / temp5) + ((((-db5) * ya2) / temp5) / temp5);
        }
        if (negate) {
            double result2 = -db;
            return result2;
        }
        return db;
    }

    public static double acosh(double a) {
        return log(sqrt((a * a) - 1.0d) + a);
    }

    public static double asinh(double a) {
        double absAsinh;
        double a2 = a;
        boolean negative = false;
        if (a2 < 0.0d) {
            negative = true;
            a2 = -a2;
        }
        if (a2 > 0.167d) {
            absAsinh = log(sqrt((a2 * a2) + 1.0d) + a2);
        } else {
            double a22 = a2 * a2;
            if (a2 > 0.097d) {
                absAsinh = a2 * (1.0d - (((F_1_3 - (((F_1_5 - (((F_1_7 - (((F_1_9 - (((F_1_11 - (((F_1_13 - (((F_1_15 - ((F_1_17 * a22) * F_15_16)) * a22) * F_13_14)) * a22) * F_11_12)) * a22) * F_9_10)) * a22) * F_7_8)) * a22) * F_5_6)) * a22) * F_3_4)) * a22) * F_1_2));
            } else if (a2 > 0.036d) {
                absAsinh = a2 * (1.0d - (((F_1_3 - (((F_1_5 - (((F_1_7 - (((F_1_9 - (((F_1_11 - ((F_1_13 * a22) * F_11_12)) * a22) * F_9_10)) * a22) * F_7_8)) * a22) * F_5_6)) * a22) * F_3_4)) * a22) * F_1_2));
            } else if (a2 > 0.0036d) {
                absAsinh = a2 * (1.0d - (((F_1_3 - (((F_1_5 - (((F_1_7 - ((F_1_9 * a22) * F_7_8)) * a22) * F_5_6)) * a22) * F_3_4)) * a22) * F_1_2));
            } else {
                absAsinh = a2 * (1.0d - (((F_1_3 - ((F_1_5 * a22) * F_3_4)) * a22) * F_1_2));
            }
        }
        return negative ? -absAsinh : absAsinh;
    }

    public static double atanh(double a) {
        double absAtanh;
        double a2 = a;
        boolean negative = false;
        if (a2 < 0.0d) {
            negative = true;
            a2 = -a2;
        }
        if (a2 > 0.15d) {
            absAtanh = log((a2 + 1.0d) / (1.0d - a2)) * F_1_2;
        } else {
            double a22 = a2 * a2;
            if (a2 > 0.087d) {
                absAtanh = a2 * ((((((((((((((((F_1_17 * a22) + F_1_15) * a22) + F_1_13) * a22) + F_1_11) * a22) + F_1_9) * a22) + F_1_7) * a22) + F_1_5) * a22) + F_1_3) * a22) + 1.0d);
            } else if (a2 > 0.031d) {
                absAtanh = a2 * ((((((((((((F_1_13 * a22) + F_1_11) * a22) + F_1_9) * a22) + F_1_7) * a22) + F_1_5) * a22) + F_1_3) * a22) + 1.0d);
            } else if (a2 > 0.003d) {
                absAtanh = a2 * ((((((((F_1_9 * a22) + F_1_7) * a22) + F_1_5) * a22) + F_1_3) * a22) + 1.0d);
            } else {
                absAtanh = a2 * ((((F_1_5 * a22) + F_1_3) * a22) + 1.0d);
            }
        }
        return negative ? -absAtanh : absAtanh;
    }

    public static double signum(double a) {
        if (a < 0.0d) {
            return -1.0d;
        }
        if (a > 0.0d) {
            return 1.0d;
        }
        return a;
    }

    public static float signum(float a) {
        if (a < 0.0f) {
            return -1.0f;
        }
        if (a > 0.0f) {
            return 1.0f;
        }
        return a;
    }

    public static double nextUp(double a) {
        return nextAfter(a, Double.POSITIVE_INFINITY);
    }

    public static float nextUp(float a) {
        return nextAfter(a, Double.POSITIVE_INFINITY);
    }

    public static double nextDown(double a) {
        return nextAfter(a, Double.NEGATIVE_INFINITY);
    }

    public static float nextDown(float a) {
        return nextAfter(a, Double.NEGATIVE_INFINITY);
    }

    public static double random() {
        return Math.random();
    }

    public static double exp(double x) {
        return exp(x, 0.0d, null);
    }

    private static double exp(double x, double extra, double[] hiPrec) {
        double result;
        int intVal = (int) x;
        if (x < 0.0d) {
            if (x < -746.0d) {
                if (hiPrec != null) {
                    hiPrec[0] = 0.0d;
                    hiPrec[1] = 0.0d;
                }
                return 0.0d;
            }
            if (intVal < -709) {
                double result2 = exp(40.19140625d + x, extra, hiPrec) / 2.85040095144011776E17d;
                if (hiPrec != null) {
                    hiPrec[0] = hiPrec[0] / 2.85040095144011776E17d;
                    hiPrec[1] = hiPrec[1] / 2.85040095144011776E17d;
                }
                return result2;
            }
            if (intVal == -709) {
                double result3 = exp(1.494140625d + x, extra, hiPrec) / 4.455505956692757d;
                if (hiPrec != null) {
                    hiPrec[0] = hiPrec[0] / 4.455505956692757d;
                    hiPrec[1] = hiPrec[1] / 4.455505956692757d;
                }
                return result3;
            }
            intVal--;
        } else if (intVal > 709) {
            if (hiPrec != null) {
                hiPrec[0] = Double.POSITIVE_INFINITY;
                hiPrec[1] = 0.0d;
            }
            return Double.POSITIVE_INFINITY;
        }
        double intPartA = ExpIntTable.EXP_INT_TABLE_A[intVal + EXP_INT_TABLE_MAX_INDEX];
        double intPartB = ExpIntTable.EXP_INT_TABLE_B[intVal + EXP_INT_TABLE_MAX_INDEX];
        int intFrac = (int) ((x - intVal) * 1024.0d);
        double fracPartA = ExpFracTable.EXP_FRAC_TABLE_A[intFrac];
        double fracPartB = ExpFracTable.EXP_FRAC_TABLE_B[intFrac];
        double epsilon = x - (intVal + (intFrac / 1024.0d));
        double z = (((((((0.04168701738764507d * epsilon) + 0.1666666505023083d) * epsilon) + 0.5000000000042687d) * epsilon) + 1.0d) * epsilon) - 3.940510424527919E-20d;
        double z2 = intPartA * fracPartA;
        double tempB = (intPartA * fracPartB) + (intPartB * fracPartA) + (intPartB * fracPartB);
        double tempC = tempB + z2;
        if (tempC == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }
        if (extra != 0.0d) {
            result = (tempC * extra * z) + (tempC * extra) + (tempC * z) + tempB + z2;
        } else {
            double result4 = tempC * z;
            result = result4 + tempB + z2;
        }
        if (hiPrec != null) {
            hiPrec[0] = z2;
            hiPrec[1] = (tempC * extra * z) + (tempC * extra) + (tempC * z) + tempB;
        }
        return result;
    }

    public static double expm1(double x) {
        return expm1(x, null);
    }

    private static double expm1(double x, double[] hiPrecOut) {
        double d;
        char c;
        char c2;
        double x2 = x;
        if (x2 != x2 || x2 == 0.0d) {
            return x2;
        }
        if (x2 <= -1.0d) {
            d = 1.0d;
            c = 0;
            c2 = 1;
        } else {
            if (x2 < 1.0d) {
                boolean negative = false;
                if (x2 < 0.0d) {
                    x2 = -x2;
                    negative = true;
                }
                int intFrac = (int) (x2 * 1024.0d);
                double tempA = ExpFracTable.EXP_FRAC_TABLE_A[intFrac] - 1.0d;
                double tempB = ExpFracTable.EXP_FRAC_TABLE_B[intFrac];
                double temp = tempA + tempB;
                double tempB2 = -((temp - tempA) - tempB);
                double temp2 = temp * 1.073741824E9d;
                double baseA = (temp + temp2) - temp2;
                double baseB = tempB2 + (temp - baseA);
                double tempB3 = x2 - (intFrac / 1024.0d);
                double zb = ((((((0.008336750013465571d * tempB3) + 0.041666663879186654d) * tempB3) + 0.16666666666745392d) * tempB3) + 0.49999999999999994d) * tempB3 * tempB3;
                double temp3 = tempB3 + zb;
                double zb2 = -((temp3 - tempB3) - zb);
                double temp4 = temp3 * 1.073741824E9d;
                double temp5 = (temp3 + temp4) - temp4;
                double zb3 = zb2 + (temp3 - temp5);
                double ya = temp5 * baseA;
                double temp6 = ya + (temp5 * baseB);
                double yb = -((temp6 - ya) - (temp5 * baseB));
                double temp7 = (zb3 * baseA) + temp6;
                double temp8 = (zb3 * baseB) + temp7;
                double x3 = (temp8 - temp7) - (zb3 * baseB);
                double ya2 = temp8 + baseA;
                double yb2 = yb + (-((temp7 - temp6) - (zb3 * baseA))) + (-x3) + (-((ya2 - baseA) - temp8));
                double temp9 = ya2 + temp5;
                double yb3 = yb2 + (-((temp9 - ya2) - temp5));
                double temp10 = temp9 + baseB;
                double yb4 = yb3 + (-((temp10 - temp9) - baseB));
                double temp11 = temp10 + zb3;
                double yb5 = yb4 + (-((temp11 - temp10) - zb3));
                double ya3 = temp11;
                if (negative) {
                    double denom = ya3 + 1.0d;
                    double denomr = 1.0d / denom;
                    double epsilon = (denom - 1.0d) - ya3;
                    double denomb = (-epsilon) + yb5;
                    double ratio = ya3 * denomr;
                    double temp12 = ratio * 1.073741824E9d;
                    double denomb2 = (ratio + temp12) - temp12;
                    double rb = ratio - denomb2;
                    double temp13 = denom * 1.073741824E9d;
                    double za = (denom + temp13) - temp13;
                    double zb4 = denom - za;
                    double rb2 = rb + (((((ya3 - (za * denomb2)) - (za * rb)) - (zb4 * denomb2)) - (zb4 * rb)) * denomr) + (yb5 * denomr) + ((-ya3) * denomb * denomr * denomr);
                    ya3 = -denomb2;
                    yb5 = -rb2;
                }
                if (hiPrecOut != null) {
                    hiPrecOut[0] = ya3;
                    hiPrecOut[1] = yb5;
                }
                return ya3 + yb5;
            }
            d = 1.0d;
            c = 0;
            c2 = 1;
        }
        double[] hiPrec = new double[2];
        exp(x2, 0.0d, hiPrec);
        if (x2 > 0.0d) {
            return (hiPrec[c] - 1.0d) + hiPrec[c2];
        }
        double ra = hiPrec[c] - 1.0d;
        return ra + (-((ra + d) - hiPrec[c])) + hiPrec[c2];
    }

    public static double log(double x) {
        return log(x, (double[]) null);
    }

    private static double log(double x, double[] hiPrec) {
        char c;
        char c2;
        double lnza;
        if (x == 0.0d) {
            return Double.NEGATIVE_INFINITY;
        }
        long bits = Double.doubleToRawLongBits(x);
        if (((Long.MIN_VALUE & bits) != 0 || x != x) && x != 0.0d) {
            if (hiPrec != null) {
                hiPrec[0] = Double.NaN;
            }
            return Double.NaN;
        }
        if (x == Double.POSITIVE_INFINITY) {
            if (hiPrec != null) {
                hiPrec[0] = Double.POSITIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        int exp = ((int) (bits >> 52)) - 1023;
        if ((MASK_DOUBLE_EXPONENT & bits) == 0) {
            if (x == 0.0d) {
                if (hiPrec != null) {
                    hiPrec[0] = Double.NEGATIVE_INFINITY;
                }
                return Double.NEGATIVE_INFINITY;
            }
            bits <<= 1;
            while ((4503599627370496L & bits) == 0) {
                exp--;
                bits <<= 1;
            }
        }
        if (exp != -1 && exp != 0) {
            c = 0;
        } else if (x >= 1.01d || x <= 0.99d || hiPrec != null) {
            c = 0;
        } else {
            double xa = x - 1.0d;
            double d = (xa - x) + 1.0d;
            double tmp = xa * 1.073741824E9d;
            double aa = (xa + tmp) - tmp;
            double ab = xa - aa;
            double[] lnCoef_last = LN_QUICK_COEF[LN_QUICK_COEF.length - 1];
            double yb = lnCoef_last[0];
            double yb2 = lnCoef_last[1];
            int i = LN_QUICK_COEF.length - 2;
            while (i >= 0) {
                double aa2 = yb * aa;
                double ab2 = (yb * ab) + (yb2 * aa) + (yb2 * ab);
                double tmp2 = aa2 * 1.073741824E9d;
                double ya = (aa2 + tmp2) - tmp2;
                double yb3 = (aa2 - ya) + ab2;
                double[] lnCoef_i = LN_QUICK_COEF[i];
                double aa3 = ya + lnCoef_i[0];
                double tmp3 = aa3 * 1.073741824E9d;
                double ya2 = (aa3 + tmp3) - tmp3;
                double yb4 = (aa3 - ya2) + yb3 + lnCoef_i[1];
                i--;
                yb = ya2;
                yb2 = yb4;
            }
            double aa4 = yb * aa;
            double tmp4 = 1.073741824E9d * aa4;
            double ya3 = (aa4 + tmp4) - tmp4;
            return ya3 + (aa4 - ya3) + (yb * ab) + (yb2 * aa) + (yb2 * ab);
        }
        double[] lnm = lnMant.LN_MANT[(int) ((bits & 4499201580859392L) >> 42)];
        double epsilon = (bits & 4398046511103L) / ((bits & 4499201580859392L) + TWO_POWER_52);
        double lnzb = 0.0d;
        if (hiPrec != null) {
            double tmp5 = epsilon * 1.073741824E9d;
            double aa5 = (epsilon + tmp5) - tmp5;
            double ab3 = epsilon - aa5;
            double numer = 4398046511103L & bits;
            double denom = (4499201580859392L & bits) + TWO_POWER_52;
            double xb = ab3 + (((numer - (aa5 * denom)) - (ab3 * denom)) / denom);
            c2 = 1;
            double[] lnCoef_last2 = LN_HI_PREC_COEF[LN_HI_PREC_COEF.length - 1];
            double yb5 = lnCoef_last2[c];
            double ab4 = lnCoef_last2[1];
            int i2 = LN_HI_PREC_COEF.length - 2;
            while (i2 >= 0) {
                double aa6 = yb5 * aa5;
                double tmp6 = aa6 * 1.073741824E9d;
                double ya4 = (aa6 + tmp6) - tmp6;
                double yb6 = (aa6 - ya4) + (yb5 * xb) + (ab4 * aa5) + (ab4 * xb);
                double[] lnCoef_i2 = LN_HI_PREC_COEF[i2];
                double aa7 = ya4 + lnCoef_i2[c];
                double tmp7 = aa7 * 1.073741824E9d;
                double ya5 = (aa7 + tmp7) - tmp7;
                double yb7 = (aa7 - ya5) + yb6 + lnCoef_i2[1];
                i2--;
                yb5 = ya5;
                ab4 = yb7;
            }
            double aa8 = yb5 * aa5;
            double ab5 = (yb5 * xb) + (ab4 * aa5) + (ab4 * xb);
            lnza = aa8 + ab5;
            double lnzb2 = -((lnza - aa8) - ab5);
            lnzb = lnzb2;
        } else {
            c2 = 1;
            double lnza2 = (-0.16624882440418567d) * epsilon;
            lnza = (((((((((lnza2 + 0.19999954120254515d) * epsilon) - 0.2499999997677497d) * epsilon) + 0.3333333333332802d) * epsilon) - 0.5d) * epsilon) + 1.0d) * epsilon;
        }
        double a = exp * LN_2_A;
        double c3 = lnm[c] + a;
        double d2 = -((c3 - a) - lnm[c]);
        double b = 0.0d + d2;
        double c4 = c3 + lnza;
        double b2 = (c4 - c3) - lnza;
        double d3 = -b2;
        double b3 = b + d3;
        double d4 = exp;
        double c5 = (d4 * LN_2_B) + c4;
        double d5 = -((c5 - c4) - (exp * LN_2_B));
        double b4 = b3 + d5;
        double c6 = lnm[c2] + c5;
        double d6 = (c6 - c5) - lnm[c2];
        double b5 = b4 + (-d6);
        double c7 = c6 + lnzb;
        double d7 = (c7 - c6) - lnzb;
        double b6 = b5 + (-d7);
        if (hiPrec != null) {
            hiPrec[c] = c7;
            hiPrec[c2] = b6;
        }
        return c7 + b6;
    }

    public static double log1p(double x) {
        if (x == -1.0d) {
            return Double.NEGATIVE_INFINITY;
        }
        if (x == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }
        if (x > 1.0E-6d || x < -1.0E-6d) {
            double y = x + 1.0d;
            double xpb = -((y - 1.0d) - x);
            double[] hiPrec = new double[2];
            double lores = log(y, hiPrec);
            if (Double.isInfinite(lores)) {
                return lores;
            }
            double fx1 = xpb / y;
            double epsilon = (F_1_2 * fx1) + 1.0d;
            return (epsilon * fx1) + hiPrec[1] + hiPrec[0];
        }
        return ((((F_1_3 * x) - F_1_2) * x) + 1.0d) * x;
    }

    public static double log10(double x) {
        double[] hiPrec = new double[2];
        double lores = log(x, hiPrec);
        if (Double.isInfinite(lores)) {
            return lores;
        }
        double tmp = hiPrec[0] * 1.073741824E9d;
        double lna = (hiPrec[0] + tmp) - tmp;
        double lnb = (hiPrec[0] - lna) + hiPrec[1];
        return (lnb * 1.9699272335463627E-8d) + (1.9699272335463627E-8d * lna) + (lnb * 0.4342944622039795d) + (0.4342944622039795d * lna);
    }

    public static double log(double base, double x) {
        return log(x) / log(base);
    }

    public static double pow(double x, double y) {
        if (y == 0.0d) {
            return 1.0d;
        }
        long yBits = Double.doubleToRawLongBits(y);
        int yRawExp = (int) ((yBits & MASK_DOUBLE_EXPONENT) >> 52);
        long yRawMantissa = yBits & 4503599627370495L;
        long xBits = Double.doubleToRawLongBits(x);
        int xRawExp = (int) ((xBits & MASK_DOUBLE_EXPONENT) >> 52);
        long xRawMantissa = xBits & 4503599627370495L;
        if (yRawExp > 1085) {
            if ((yRawExp == 2047 && yRawMantissa != 0) || (xRawExp == 2047 && xRawMantissa != 0)) {
                return Double.NaN;
            }
            if (xRawExp == 1023 && xRawMantissa == 0) {
                return yRawExp == 2047 ? Double.NaN : 1.0d;
            }
            return ((y > 0.0d ? 1 : (y == 0.0d ? 0 : -1)) > 0) ^ (xRawExp < 1023) ? Double.POSITIVE_INFINITY : 0.0d;
        }
        if (yRawExp >= 1023) {
            long yFullMantissa = yRawMantissa | 4503599627370496L;
            if (yRawExp < 1075) {
                long integralMask = (-1) << (1075 - yRawExp);
                if ((yFullMantissa & integralMask) == yFullMantissa) {
                    long l = yFullMantissa >> (1075 - yRawExp);
                    return pow(x, y < 0.0d ? -l : l);
                }
            } else {
                long l2 = yFullMantissa << (yRawExp - 1075);
                return pow(x, y < 0.0d ? -l2 : l2);
            }
        }
        if (x == 0.0d) {
            return y < 0.0d ? Double.POSITIVE_INFINITY : 0.0d;
        }
        if (xRawExp == 2047) {
            if (xRawMantissa == 0) {
                return y < 0.0d ? 0.0d : Double.POSITIVE_INFINITY;
            }
            return Double.NaN;
        }
        if (x < 0.0d) {
            return Double.NaN;
        }
        double tmp = y * 1.073741824E9d;
        double ya = (y + tmp) - tmp;
        double yb = y - ya;
        double[] lns = new double[2];
        double lores = log(x, lns);
        if (Double.isInfinite(lores)) {
            return lores;
        }
        double lna = lns[0];
        double tmp1 = 1.073741824E9d * lna;
        double tmp2 = (lna + tmp1) - tmp1;
        double lnb = lns[1] + (lna - tmp2);
        double aa = tmp2 * ya;
        double ab = (tmp2 * yb) + (lnb * ya) + (lnb * yb);
        double lna2 = aa + ab;
        double lnb2 = -((lna2 - aa) - ab);
        double z = (0.008333333333333333d * lnb2) + 0.041666666666666664d;
        double result = exp(lna2, ((((((z * lnb2) + 0.16666666666666666d) * lnb2) + F_1_2) * lnb2) + 1.0d) * lnb2, null);
        return result;
    }

    public static double pow(double d, int e) {
        return pow(d, e);
    }

    public static double pow(double d, long e) {
        if (e == 0) {
            return 1.0d;
        }
        return e > 0 ? new Split(d).pow(e).full : new Split(d).reciprocal().pow(-e).full;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class Split {
        private final double full;
        private final double high;
        private final double low;
        public static final Split NAN = new Split(Double.NaN, 0.0d);
        public static final Split POSITIVE_INFINITY = new Split(Double.POSITIVE_INFINITY, 0.0d);
        public static final Split NEGATIVE_INFINITY = new Split(Double.NEGATIVE_INFINITY, 0.0d);

        Split(double x) {
            this.full = x;
            this.high = Double.longBitsToDouble(Double.doubleToRawLongBits(x) & (-134217728));
            this.low = x - this.high;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        Split(double r10, double r12) {
            /*
                r9 = this;
                r0 = 0
                int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                if (r2 != 0) goto L19
                int r0 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
                if (r0 != 0) goto L17
                long r0 = java.lang.Double.doubleToRawLongBits(r10)
                r2 = -9223372036854775808
                int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r0 != 0) goto L17
                r0 = -9223372036854775808
                goto L1b
            L17:
                r3 = r12
                goto L1c
            L19:
                double r0 = r10 + r12
            L1b:
                r3 = r0
            L1c:
                r2 = r9
                r5 = r10
                r7 = r12
                r2.<init>(r3, r5, r7)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.util.FastMath.Split.<init>(double, double):void");
        }

        Split(double full, double high, double low) {
            this.full = full;
            this.high = high;
            this.low = low;
        }

        public Split multiply(Split b) {
            Split mulBasic = new Split(this.full * b.full);
            double mulError = (this.low * b.low) - (((mulBasic.full - (this.high * b.high)) - (this.low * b.high)) - (this.high * b.low));
            return new Split(mulBasic.high, mulBasic.low + mulError);
        }

        public Split reciprocal() {
            double approximateInv = 1.0d / this.full;
            Split splitInv = new Split(approximateInv);
            Split product = multiply(splitInv);
            double error = (product.high - 1.0d) + product.low;
            return Double.isNaN(error) ? splitInv : new Split(splitInv.high, splitInv.low - (error / this.full));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Split pow(long e) {
            Split result = new Split(1.0d);
            Split d2p = new Split(this.full, this.high, this.low);
            for (long p = e; p != 0; p >>>= 1) {
                if ((p & 1) != 0) {
                    result = result.multiply(d2p);
                }
                d2p = d2p.multiply(d2p);
            }
            if (Double.isNaN(result.full)) {
                if (Double.isNaN(this.full)) {
                    return NAN;
                }
                if (FastMath.abs(this.full) < 1.0d) {
                    return new Split(FastMath.copySign(0.0d, this.full), 0.0d);
                }
                if (this.full < 0.0d && (e & 1) == 1) {
                    return NEGATIVE_INFINITY;
                }
                return POSITIVE_INFINITY;
            }
            return result;
        }
    }

    private static double polySine(double x) {
        double x2 = x * x;
        double p = (2.7553817452272217E-6d * x2) - 1.9841269659586505E-4d;
        return ((((p * x2) + 0.008333333333329196d) * x2) - 0.16666666666666666d) * x2 * x;
    }

    private static double polyCosine(double x) {
        double x2 = x * x;
        double p = (2.479773539153719E-5d * x2) - 0.0013888888689039883d;
        return ((((p * x2) + 0.041666666666621166d) * x2) - 0.49999999999999994d) * x2;
    }

    private static double sinQ(double xa, double xb) {
        int idx = (int) ((8.0d * xa) + F_1_2);
        double epsilon = xa - EIGHTHS[idx];
        double sintA = SINE_TABLE_A[idx];
        double sintB = SINE_TABLE_B[idx];
        double costA = COSINE_TABLE_A[idx];
        double costB = COSINE_TABLE_B[idx];
        double sinEpsB = polySine(epsilon);
        double cosEpsB = polyCosine(epsilon);
        double temp = 1.073741824E9d * epsilon;
        double temp2 = (epsilon + temp) - temp;
        double sinEpsB2 = sinEpsB + (epsilon - temp2);
        double c = 0.0d + sintA;
        double d = -((c - 0.0d) - sintA);
        double b = 0.0d + d;
        double t = costA * temp2;
        double c2 = c + t;
        double d2 = (c2 - c) - t;
        double a = c2;
        double b2 = b + (-d2) + (sintA * cosEpsB) + (costA * sinEpsB2) + sintB + (costB * temp2) + (sintB * cosEpsB) + (costB * sinEpsB2);
        if (xb != 0.0d) {
            double t2 = (((costA + costB) * (cosEpsB + 1.0d)) - ((sintA + sintB) * (temp2 + sinEpsB2))) * xb;
            double c3 = a + t2;
            double d3 = (c3 - a) - t2;
            a = c3;
            b2 += -d3;
        }
        double result = a + b2;
        return result;
    }

    private static double cosQ(double xa, double xb) {
        double a = 1.5707963267948966d - xa;
        double b = -((a - 1.5707963267948966d) + xa);
        return sinQ(a, b + (6.123233995736766E-17d - xb));
    }

    private static double tanQ(double xa, double xb, boolean cotanFlag) {
        double cosa;
        double cosb;
        double sina;
        double tmp;
        int idx = (int) ((8.0d * xa) + F_1_2);
        double epsilon = xa - EIGHTHS[idx];
        double sintA = SINE_TABLE_A[idx];
        double sintB = SINE_TABLE_B[idx];
        double costA = COSINE_TABLE_A[idx];
        double costB = COSINE_TABLE_B[idx];
        double sinEpsB = polySine(epsilon);
        double cosEpsB = polyCosine(epsilon);
        double temp = epsilon * 1.073741824E9d;
        double temp2 = (epsilon + temp) - temp;
        double sinEpsB2 = sinEpsB + (epsilon - temp2);
        double c = 0.0d + sintA;
        double d = -((c - 0.0d) - sintA);
        double b = 0.0d + d;
        double t = costA * temp2;
        double c2 = c + t;
        double d2 = (c2 - c) - t;
        double b2 = b + (-d2) + (sintA * cosEpsB) + (costA * sinEpsB2) + sintB + (costB * temp2) + (sintB * cosEpsB) + (costB * sinEpsB2);
        double sina2 = c2 + b2;
        double d3 = (sina2 - c2) - b2;
        double sinb = -d3;
        double t2 = costA * 1.0d;
        double c3 = 0.0d + t2;
        double d4 = -((c3 - 0.0d) - t2);
        double b3 = 0.0d + d4;
        double d5 = -sintA;
        double t3 = d5 * temp2;
        double t4 = c3 + t3;
        double d6 = -((t4 - c3) - t3);
        double b4 = ((b3 + d6) + (((1.0d * costB) + (costA * cosEpsB)) + (costB * cosEpsB))) - (((sintB * temp2) + (sintA * sinEpsB2)) + (sintB * sinEpsB2));
        double cosa2 = t4 + b4;
        double d7 = (cosa2 - t4) - b4;
        double cosb2 = -d7;
        if (!cotanFlag) {
            cosa = cosb2;
            cosb = sina2;
            sina = cosa2;
            tmp = sinb;
        } else {
            cosa = sinb;
            cosb = cosa2;
            sina = sina2;
            tmp = cosb2;
        }
        double sinb2 = cosb / sina;
        double temp3 = sinb2 * 1.073741824E9d;
        double esta = (sinb2 + temp3) - temp3;
        double estb = sinb2 - esta;
        double temp4 = 1.073741824E9d * sina;
        double cosaa = (sina + temp4) - temp4;
        double cosab = sina - cosaa;
        double err = (((((cosb - (esta * cosaa)) - (esta * cosab)) - (estb * cosaa)) - (estb * cosab)) / sina) + (tmp / sina) + ((((-cosb) * cosa) / sina) / sina);
        if (xb != 0.0d) {
            double xbadj = xb + (sinb2 * sinb2 * xb);
            if (cotanFlag) {
                xbadj = -xbadj;
            }
            err += xbadj;
        }
        return sinb2 + err;
    }

    private static void reducePayneHanek(double x, double[] result) {
        long shpi0;
        long shpiA;
        long shpiB;
        long inbits = Double.doubleToRawLongBits(x);
        int exponent = (((int) ((inbits >> 52) & 2047)) - 1023) + 1;
        long inbits2 = ((inbits & 4503599627370495L) | 4503599627370496L) << 11;
        int idx = exponent >> 6;
        int shift = exponent - (idx << 6);
        if (shift != 0) {
            long shpi02 = idx == 0 ? 0L : RECIP_2PI[idx - 1] << shift;
            shpi0 = shpi02 | (RECIP_2PI[idx] >>> (64 - shift));
            shpiA = (RECIP_2PI[idx] << shift) | (RECIP_2PI[idx + 1] >>> (64 - shift));
            shpiB = (RECIP_2PI[idx + 1] << shift) | (RECIP_2PI[idx + 2] >>> (64 - shift));
        } else {
            shpi0 = idx == 0 ? 0L : RECIP_2PI[idx - 1];
            long shpiA2 = RECIP_2PI[idx];
            shpiA = shpiA2;
            shpiB = RECIP_2PI[idx + 1];
        }
        long a = inbits2 >>> 32;
        long b = inbits2 & 4294967295L;
        long c = shpiA >>> 32;
        long d = shpiA & 4294967295L;
        long ac = a * c;
        long bd = b * d;
        long bc = b * c;
        long ad = a * d;
        long prodB = bd + (ad << 32);
        long prodA = ac + (ad >>> 32);
        boolean bita = (bd & Long.MIN_VALUE) != 0;
        boolean bitb = (ad & 2147483648L) != 0;
        boolean bitsum = (prodB & Long.MIN_VALUE) != 0;
        if ((bita && bitb) || ((bita || bitb) && !bitsum)) {
            prodA++;
        }
        boolean bita2 = (prodB & Long.MIN_VALUE) != 0;
        boolean bitb2 = (bc & 2147483648L) != 0;
        long prodB2 = prodB + (bc << 32);
        long prodA2 = prodA + (bc >>> 32);
        boolean bitsum2 = (prodB2 & Long.MIN_VALUE) != 0;
        if ((bita2 && bitb2) || ((bita2 || bitb2) && !bitsum2)) {
            prodA2++;
        }
        long c2 = shpiB >>> 32;
        long ac2 = a * c2;
        long ac3 = ac2 + (((b * c2) + (a * (shpiB & 4294967295L))) >>> 32);
        boolean bita3 = (prodB2 & Long.MIN_VALUE) != 0;
        boolean bitb3 = (ac3 & Long.MIN_VALUE) != 0;
        long prodB3 = prodB2 + ac3;
        boolean bitsum3 = (prodB3 & Long.MIN_VALUE) != 0;
        if ((bita3 && bitb3) || ((bita3 || bitb3) && !bitsum3)) {
            prodA2++;
        }
        long d2 = shpi0 & 4294967295L;
        long prodA3 = prodA2 + (b * d2) + (((b * (shpi0 >>> 32)) + (a * d2)) << 32);
        int intPart = (int) (prodA3 >>> 62);
        long prodA4 = (prodA3 << 2) | (prodB3 >>> 62);
        long prodB4 = prodB3 << 2;
        long a2 = prodA4 >>> 32;
        long b2 = prodA4 & 4294967295L;
        long c3 = PI_O_4_BITS[0] >>> 32;
        long d3 = PI_O_4_BITS[0] & 4294967295L;
        long ac4 = a2 * c3;
        long bd2 = b2 * d3;
        long bc2 = b2 * c3;
        long ad2 = a2 * d3;
        long prod2B = bd2 + (ad2 << 32);
        long prod2A = ac4 + (ad2 >>> 32);
        boolean bita4 = (bd2 & Long.MIN_VALUE) != 0;
        boolean bitb4 = (ad2 & 2147483648L) != 0;
        boolean bitsum4 = (prod2B & Long.MIN_VALUE) != 0;
        if ((bita4 && bitb4) || ((bita4 || bitb4) && !bitsum4)) {
            prod2A++;
        }
        boolean bita5 = (prod2B & Long.MIN_VALUE) != 0;
        boolean bitb5 = (bc2 & 2147483648L) != 0;
        long prod2B2 = prod2B + (bc2 << 32);
        long prod2A2 = prod2A + (bc2 >>> 32);
        boolean bitsum5 = (prod2B2 & Long.MIN_VALUE) != 0;
        if ((bita5 && bitb5) || ((bita5 || bitb5) && !bitsum5)) {
            prod2A2++;
        }
        long c4 = PI_O_4_BITS[1] >>> 32;
        long ac5 = a2 * c4;
        long ac6 = ac5 + (((b2 * c4) + (a2 * (PI_O_4_BITS[1] & 4294967295L))) >>> 32);
        boolean bita6 = (prod2B2 & Long.MIN_VALUE) != 0;
        boolean bitb6 = (ac6 & Long.MIN_VALUE) != 0;
        long prod2B3 = prod2B2 + ac6;
        boolean bitsum6 = (prod2B3 & Long.MIN_VALUE) != 0;
        if ((bita6 && bitb6) || ((bita6 || bitb6) && !bitsum6)) {
            prod2A2++;
        }
        long a3 = prodB4 >>> 32;
        long c5 = PI_O_4_BITS[0] >>> 32;
        long ac7 = (prodB4 & 4294967295L) * c5;
        long ac8 = (a3 * c5) + ((ac7 + (a3 * (PI_O_4_BITS[0] & 4294967295L))) >>> 32);
        boolean bita7 = (prod2B3 & Long.MIN_VALUE) != 0;
        boolean bitb7 = (ac8 & Long.MIN_VALUE) != 0;
        boolean bitsum7 = ((prod2B3 + ac8) & Long.MIN_VALUE) != 0;
        if ((bita7 && bitb7) || ((bita7 || bitb7) && !bitsum7)) {
            prod2A2++;
        }
        double tmpA = (prod2A2 >>> 12) / TWO_POWER_52;
        double tmpB = ((((prod2A2 & 4095) << 40) + (r50 >>> 24)) / TWO_POWER_52) / TWO_POWER_52;
        double sumA = tmpA + tmpB;
        double sumB = -((sumA - tmpA) - tmpB);
        double sumB2 = intPart;
        result[0] = sumB2;
        result[1] = sumA * 2.0d;
        result[2] = 2.0d * sumB;
    }

    public static double sin(double x) {
        boolean negative = false;
        int quadrant = 0;
        double xb = 0.0d;
        double xa = x;
        if (x < 0.0d) {
            negative = true;
            xa = -xa;
        }
        if (xa == 0.0d) {
            long bits = Double.doubleToRawLongBits(x);
            if (bits >= 0) {
                return 0.0d;
            }
            return -0.0d;
        }
        if (xa != xa || xa == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }
        if (xa > 3294198.0d) {
            double[] reduceResults = new double[3];
            reducePayneHanek(xa, reduceResults);
            quadrant = ((int) reduceResults[0]) & 3;
            xa = reduceResults[1];
            xb = reduceResults[2];
        } else if (xa > 1.5707963267948966d) {
            CodyWaite cw = new CodyWaite(xa);
            quadrant = cw.getK() & 3;
            xa = cw.getRemA();
            xb = cw.getRemB();
        }
        if (negative) {
            quadrant ^= 2;
        }
        switch (quadrant) {
            case 0:
                return sinQ(xa, xb);
            case 1:
                return cosQ(xa, xb);
            case 2:
                return -sinQ(xa, xb);
            case 3:
                return -cosQ(xa, xb);
            default:
                return Double.NaN;
        }
    }

    public static double cos(double x) {
        int quadrant = 0;
        double xa = x;
        if (x < 0.0d) {
            xa = -xa;
        }
        if (xa != xa || xa == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }
        double xb = 0.0d;
        if (xa > 3294198.0d) {
            double[] reduceResults = new double[3];
            reducePayneHanek(xa, reduceResults);
            quadrant = ((int) reduceResults[0]) & 3;
            xa = reduceResults[1];
            xb = reduceResults[2];
        } else if (xa > 1.5707963267948966d) {
            CodyWaite cw = new CodyWaite(xa);
            quadrant = cw.getK() & 3;
            xa = cw.getRemA();
            xb = cw.getRemB();
        }
        switch (quadrant) {
            case 0:
                return cosQ(xa, xb);
            case 1:
                return -sinQ(xa, xb);
            case 2:
                return -cosQ(xa, xb);
            case 3:
                return sinQ(xa, xb);
            default:
                return Double.NaN;
        }
    }

    public static double tan(double x) {
        double result;
        boolean negative = false;
        int quadrant = 0;
        double xa = x;
        if (x < 0.0d) {
            negative = true;
            xa = -xa;
        }
        if (xa == 0.0d) {
            long bits = Double.doubleToRawLongBits(x);
            if (bits >= 0) {
                return 0.0d;
            }
            return -0.0d;
        }
        if (xa != xa || xa == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }
        double xb = 0.0d;
        if (xa > 3294198.0d) {
            double[] reduceResults = new double[3];
            reducePayneHanek(xa, reduceResults);
            quadrant = ((int) reduceResults[0]) & 3;
            xa = reduceResults[1];
            xb = reduceResults[2];
        } else if (xa > 1.5707963267948966d) {
            CodyWaite cw = new CodyWaite(xa);
            quadrant = cw.getK() & 3;
            xa = cw.getRemA();
            xb = cw.getRemB();
        }
        if (xa > 1.5d) {
            double a = 1.5707963267948966d - xa;
            double b = (-((a - 1.5707963267948966d) + xa)) + (6.123233995736766E-17d - xb);
            xa = a + b;
            xb = -((xa - a) - b);
            quadrant ^= 1;
            negative = !negative;
        }
        if ((quadrant & 1) == 0) {
            result = tanQ(xa, xb, false);
        } else {
            result = -tanQ(xa, xb, true);
        }
        if (negative) {
            return -result;
        }
        return result;
    }

    public static double atan(double x) {
        return atan(x, 0.0d, false);
    }

    private static double atan(double xa, double xb, boolean leftPlane) {
        double xb2;
        boolean negate;
        int idx;
        double ya;
        double denom;
        double zb;
        double xa2 = xa;
        if (xa2 == 0.0d) {
            return leftPlane ? copySign(3.141592653589793d, xa2) : xa2;
        }
        if (xa2 < 0.0d) {
            xa2 = -xa2;
            xb2 = -xb;
            negate = true;
        } else {
            xb2 = xb;
            negate = false;
        }
        if (xa2 > 1.633123935319537E16d) {
            return negate ^ leftPlane ? -1.5707963267948966d : 1.5707963267948966d;
        }
        if (xa2 < 1.0d) {
            idx = (int) (((((-1.7168146928204135d) * xa2 * xa2) + 8.0d) * xa2) + F_1_2);
        } else {
            double oneOverXa = 1.0d / xa2;
            idx = (int) ((-((((-1.7168146928204135d) * oneOverXa * oneOverXa) + 8.0d) * oneOverXa)) + 13.07d);
        }
        double ttA = TANGENT_TABLE_A[idx];
        double ttB = TANGENT_TABLE_B[idx];
        double epsA = xa2 - ttA;
        double epsB = (-((epsA - xa2) + ttA)) + (xb2 - ttB);
        double temp = epsA + epsB;
        double epsB2 = -((temp - epsA) - epsB);
        double temp2 = xa2 * 1.073741824E9d;
        double ya2 = (xa2 + temp2) - temp2;
        double yb = (xb2 + xa2) - ya2;
        double xb3 = xb2 + yb;
        if (idx == 0) {
            double denom2 = 1.0d / (((ya2 + xb3) * (ttA + ttB)) + 1.0d);
            ya = temp * denom2;
            denom = denom2 * epsB2;
        } else {
            double temp22 = ya2 * ttA;
            double za = temp22 + 1.0d;
            double xa3 = (za - 1.0d) - temp22;
            double zb2 = -xa3;
            double temp23 = (xb3 * ttA) + (ya2 * ttB);
            double temp3 = za + temp23;
            double zb3 = (temp3 - za) - temp23;
            double zb4 = zb2 + (-zb3) + (xb3 * ttB);
            ya = temp / temp3;
            double temp4 = ya * 1.073741824E9d;
            double yaa = (ya + temp4) - temp4;
            double yab = ya - yaa;
            double temp5 = temp3 * 1.073741824E9d;
            double zaa = (temp3 + temp5) - temp5;
            double zab = temp3 - zaa;
            double yb2 = ((((temp - (yaa * zaa)) - (yaa * zab)) - (yab * zaa)) - (yab * zab)) / temp3;
            double zb5 = -temp;
            denom = yb2 + (((zb5 * zb4) / temp3) / temp3) + (epsB2 / temp3);
        }
        double epsA2 = ya;
        double epsB3 = denom;
        double epsA22 = epsA2 * epsA2;
        double yb3 = (0.07490822288864472d * epsA22) - 0.09088450866185192d;
        double yb4 = ((((((((yb3 * epsA22) + 0.11111095942313305d) * epsA22) - 0.1428571423679182d) * epsA22) + 0.19999999999923582d) * epsA22) - 0.33333333333333287d) * epsA22 * epsA2;
        double temp6 = ya + yb4;
        double yb5 = (-((temp6 - ya) - yb4)) + (epsB3 / ((epsA2 * epsA2) + 1.0d));
        double eighths = EIGHTHS[idx];
        double za2 = eighths + temp6;
        double yb6 = (za2 - eighths) - temp6;
        double zb6 = -yb6;
        double temp7 = za2 + yb5;
        double zb7 = (temp7 - za2) - yb5;
        double zb8 = zb6 + (-zb7);
        double result = temp7 + zb8;
        if (leftPlane) {
            double resultb = -((result - temp7) - zb8);
            double za3 = 3.141592653589793d - result;
            double resultb2 = (za3 - 3.141592653589793d) + result;
            double zb9 = -resultb2;
            zb = za3 + zb9 + (1.2246467991473532E-16d - resultb);
        } else {
            zb = result;
        }
        if (negate ^ leftPlane) {
            return -zb;
        }
        return zb;
    }

    public static double atan2(double y, double x) {
        if (x != x || y != y) {
            return Double.NaN;
        }
        if (y == 0.0d) {
            double result = x * y;
            double invx = 1.0d / x;
            double invy = 1.0d / y;
            if (invx == 0.0d) {
                if (x > 0.0d) {
                    return y;
                }
                return copySign(3.141592653589793d, y);
            }
            if (x < 0.0d || invx < 0.0d) {
                return (y < 0.0d || invy < 0.0d) ? -3.141592653589793d : 3.141592653589793d;
            }
            return result;
        }
        if (y == Double.POSITIVE_INFINITY) {
            if (x == Double.POSITIVE_INFINITY) {
                return 0.7853981633974483d;
            }
            return x == Double.NEGATIVE_INFINITY ? 2.356194490192345d : 1.5707963267948966d;
        }
        if (y == Double.NEGATIVE_INFINITY) {
            if (x == Double.POSITIVE_INFINITY) {
                return -0.7853981633974483d;
            }
            return x == Double.NEGATIVE_INFINITY ? -2.356194490192345d : -1.5707963267948966d;
        }
        if (x == Double.POSITIVE_INFINITY) {
            if (y > 0.0d || 1.0d / y > 0.0d) {
                return 0.0d;
            }
            if (y < 0.0d || 1.0d / y < 0.0d) {
                return -0.0d;
            }
        }
        if (x == Double.NEGATIVE_INFINITY) {
            if (y > 0.0d || 1.0d / y > 0.0d) {
                return 3.141592653589793d;
            }
            if (y < 0.0d || 1.0d / y < 0.0d) {
                return -3.141592653589793d;
            }
        }
        if (x == 0.0d) {
            if (y > 0.0d || 1.0d / y > 0.0d) {
                return 1.5707963267948966d;
            }
            if (y < 0.0d || 1.0d / y < 0.0d) {
                return -1.5707963267948966d;
            }
        }
        double r = y / x;
        if (Double.isInfinite(r)) {
            return atan(r, 0.0d, x < 0.0d);
        }
        double ra = doubleHighPart(r);
        double rb = r - ra;
        double xa = doubleHighPart(x);
        double xb = x - xa;
        double rb2 = rb + (((((y - (ra * xa)) - (ra * xb)) - (rb * xa)) - (rb * xb)) / x);
        double temp = ra + rb2;
        double rb3 = -((temp - ra) - rb2);
        double ra2 = temp;
        if (ra2 == 0.0d) {
            ra2 = copySign(0.0d, y);
        }
        double result2 = atan(ra2, rb3, x < 0.0d);
        return result2;
    }

    public static double asin(double x) {
        if (x != x || x > 1.0d || x < -1.0d) {
            return Double.NaN;
        }
        if (x == 1.0d) {
            return 1.5707963267948966d;
        }
        if (x == -1.0d) {
            return -1.5707963267948966d;
        }
        if (x == 0.0d) {
            return x;
        }
        double temp = x * 1.073741824E9d;
        double xa = (x + temp) - temp;
        double xb = x - xa;
        double ya = -(xa * xa);
        double yb = -((xa * xb * 2.0d) + (xb * xb));
        double za = ya + 1.0d;
        double zb = -((za - 1.0d) - ya);
        double temp2 = za + yb;
        double zb2 = zb + (-((temp2 - za) - yb));
        double y = sqrt(temp2);
        double temp3 = y * 1.073741824E9d;
        double ya2 = (y + temp3) - temp3;
        double ya3 = y - ya2;
        double yb2 = ya3 + ((((temp2 - (ya2 * ya2)) - ((ya2 * 2.0d) * ya3)) - (ya3 * ya3)) / (y * 2.0d));
        double dx = zb2 / (2.0d * y);
        double r = x / y;
        double temp4 = r * 1.073741824E9d;
        double ra = (r + temp4) - temp4;
        double rb = r - ra;
        double rb2 = rb + (((((x - (ra * ya2)) - (ra * yb2)) - (rb * ya2)) - (rb * yb2)) / y) + ((((-x) * dx) / y) / y);
        double temp5 = ra + rb2;
        return atan(temp5, -((temp5 - ra) - rb2), false);
    }

    public static double acos(double x) {
        if (x != x || x > 1.0d || x < -1.0d) {
            return Double.NaN;
        }
        if (x == -1.0d) {
            return 3.141592653589793d;
        }
        if (x == 1.0d) {
            return 0.0d;
        }
        if (x == 0.0d) {
            return 1.5707963267948966d;
        }
        double temp = x * 1.073741824E9d;
        double xa = (x + temp) - temp;
        double xb = x - xa;
        double ya = -(xa * xa);
        double yb = -((xa * xb * 2.0d) + (xb * xb));
        double za = ya + 1.0d;
        double zb = -((za - 1.0d) - ya);
        double temp2 = za + yb;
        double zb2 = zb + (-((temp2 - za) - yb));
        double y = sqrt(temp2);
        double temp3 = y * 1.073741824E9d;
        double ya2 = (y + temp3) - temp3;
        double ya3 = y - ya2;
        double yb2 = ya3 + ((((temp2 - (ya2 * ya2)) - ((ya2 * 2.0d) * ya3)) - (ya3 * ya3)) / (y * 2.0d)) + (zb2 / (2.0d * y));
        double y2 = ya2 + yb2;
        double yb3 = -((y2 - ya2) - yb2);
        double r = y2 / x;
        if (Double.isInfinite(r)) {
            return 1.5707963267948966d;
        }
        double ra = doubleHighPart(r);
        double rb = r - ra;
        double rb2 = rb + (((((y2 - (ra * xa)) - (ra * xb)) - (rb * xa)) - (rb * xb)) / x) + (yb3 / x);
        double temp4 = ra + rb2;
        return atan(temp4, -((temp4 - ra) - rb2), x < 0.0d);
    }

    public static double cbrt(double x) {
        double x2;
        long inbits = Double.doubleToRawLongBits(x);
        int exponent = ((int) ((inbits >> 52) & 2047)) - 1023;
        boolean subnormal = false;
        if (exponent != -1023) {
            x2 = x;
        } else {
            if (x == 0.0d) {
                return x;
            }
            subnormal = true;
            x2 = 1.8014398509481984E16d * x;
            inbits = Double.doubleToRawLongBits(x2);
            exponent = ((int) (2047 & (inbits >> 52))) - 1023;
        }
        if (exponent == 1024) {
            return x2;
        }
        int exp3 = exponent / 3;
        double p2 = Double.longBitsToDouble((Long.MIN_VALUE & inbits) | (((exp3 + IEEEDouble.EXPONENT_BIAS) & IEEEDouble.BIASED_EXPONENT_SPECIAL_VALUE) << 52));
        double mant = Double.longBitsToDouble((4503599627370495L & inbits) | 4607182418800017408L);
        double est = (((((((((-0.010714690733195933d) * mant) + 0.0875862700108075d) * mant) - 0.3058015757857271d) * mant) + 0.7249995199969751d) * mant) + 0.5039018405998233d) * CBRTTWO[(exponent % 3) + 2];
        double xs = x2 / ((p2 * p2) * p2);
        double est2 = est + ((xs - ((est * est) * est)) / ((est * 3.0d) * est));
        double est3 = est2 + ((xs - ((est2 * est2) * est2)) / ((est2 * 3.0d) * est2));
        double temp = est3 * 1.073741824E9d;
        double ya = (est3 + temp) - temp;
        double yb = est3 - ya;
        double za = ya * ya;
        double temp2 = 1.073741824E9d * za;
        double temp22 = (za + temp2) - temp2;
        double zb = (ya * yb * 2.0d) + (yb * yb) + (za - temp22);
        double zb2 = (temp22 * yb) + (ya * zb) + (zb * yb);
        double za2 = temp22 * ya;
        double na = xs - za2;
        double nb = -((na - xs) + za2);
        double est4 = (est3 + ((na + (nb - zb2)) / ((3.0d * est3) * est3))) * p2;
        if (subnormal) {
            return est4 * 3.814697265625E-6d;
        }
        return est4;
    }

    public static double toRadians(double x) {
        if (Double.isInfinite(x) || x == 0.0d) {
            return x;
        }
        double xa = doubleHighPart(x);
        double xb = x - xa;
        double result = (xb * 1.997844754509471E-9d) + (xb * 0.01745329052209854d) + (1.997844754509471E-9d * xa) + (0.01745329052209854d * xa);
        if (result == 0.0d) {
            return result * x;
        }
        return result;
    }

    public static double toDegrees(double x) {
        if (Double.isInfinite(x) || x == 0.0d) {
            return x;
        }
        double xa = doubleHighPart(x);
        double xb = x - xa;
        return (xb * 3.145894820876798E-6d) + (xb * 57.2957763671875d) + (3.145894820876798E-6d * xa) + (57.2957763671875d * xa);
    }

    public static int abs(int x) {
        int i = x >>> 31;
        return (((~i) + 1) ^ x) + i;
    }

    public static long abs(long x) {
        long l = x >>> 63;
        return (((~l) + 1) ^ x) + l;
    }

    public static float abs(float x) {
        return Float.intBitsToFloat(Integer.MAX_VALUE & Float.floatToRawIntBits(x));
    }

    public static double abs(double x) {
        return Double.longBitsToDouble(Long.MAX_VALUE & Double.doubleToRawLongBits(x));
    }

    public static double ulp(double x) {
        if (Double.isInfinite(x)) {
            return Double.POSITIVE_INFINITY;
        }
        return abs(x - Double.longBitsToDouble(Double.doubleToRawLongBits(x) ^ 1));
    }

    public static float ulp(float x) {
        if (Float.isInfinite(x)) {
            return Float.POSITIVE_INFINITY;
        }
        return abs(x - Float.intBitsToFloat(Float.floatToIntBits(x) ^ 1));
    }

    public static double scalb(double d, int n) {
        if (n > -1023 && n < 1024) {
            return Double.longBitsToDouble((n + IEEEDouble.EXPONENT_BIAS) << 52) * d;
        }
        if (!Double.isNaN(d) && !Double.isInfinite(d) && d != 0.0d) {
            if (n < -2098) {
                return d > 0.0d ? 0.0d : -0.0d;
            }
            if (n > 2097) {
                return d > 0.0d ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
            }
            long bits = Double.doubleToRawLongBits(d);
            long sign = Long.MIN_VALUE & bits;
            int exponent = ((int) (bits >>> 52)) & IEEEDouble.BIASED_EXPONENT_SPECIAL_VALUE;
            long mantissa = bits & 4503599627370495L;
            int scaledExponent = exponent + n;
            if (n < 0) {
                if (scaledExponent > 0) {
                    return Double.longBitsToDouble((scaledExponent << 52) | sign | mantissa);
                }
                if (scaledExponent <= -53) {
                    return sign == 0 ? 0.0d : -0.0d;
                }
                long mantissa2 = mantissa | 4503599627370496L;
                long mostSignificantLostBit = (1 << (-scaledExponent)) & mantissa2;
                long mantissa3 = mantissa2 >>> (1 - scaledExponent);
                if (mostSignificantLostBit != 0) {
                    mantissa3++;
                }
                return Double.longBitsToDouble(sign | mantissa3);
            }
            if (exponent == 0) {
                while ((mantissa >>> 52) != 1) {
                    mantissa <<= 1;
                    scaledExponent--;
                }
                int scaledExponent2 = scaledExponent + 1;
                long mantissa4 = mantissa & 4503599627370495L;
                if (scaledExponent2 < 2047) {
                    return Double.longBitsToDouble((scaledExponent2 << 52) | sign | mantissa4);
                }
                return sign == 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
            }
            if (scaledExponent < 2047) {
                return Double.longBitsToDouble((scaledExponent << 52) | sign | mantissa);
            }
            return sign == 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
        }
        return d;
    }

    public static float scalb(float f, int n) {
        if (n > -127 && n < 128) {
            return Float.intBitsToFloat((n + 127) << 23) * f;
        }
        if (Float.isNaN(f) || Float.isInfinite(f) || f == 0.0f) {
            return f;
        }
        if (n < -277) {
            return f > 0.0f ? 0.0f : -0.0f;
        }
        if (n > 276) {
            return f > 0.0f ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
        }
        int bits = Float.floatToIntBits(f);
        int sign = Integer.MIN_VALUE & bits;
        int exponent = (bits >>> 23) & 255;
        int mantissa = bits & 8388607;
        int scaledExponent = exponent + n;
        if (n < 0) {
            if (scaledExponent > 0) {
                return Float.intBitsToFloat((scaledExponent << 23) | sign | mantissa);
            }
            if (scaledExponent <= -24) {
                return sign == 0 ? 0.0f : -0.0f;
            }
            int mantissa2 = 8388608 | mantissa;
            int mostSignificantLostBit = (1 << (-scaledExponent)) & mantissa2;
            int mantissa3 = mantissa2 >>> (1 - scaledExponent);
            if (mostSignificantLostBit != 0) {
                mantissa3++;
            }
            return Float.intBitsToFloat(sign | mantissa3);
        }
        if (exponent == 0) {
            while ((mantissa >>> 23) != 1) {
                mantissa <<= 1;
                scaledExponent--;
            }
            int scaledExponent2 = scaledExponent + 1;
            int mantissa4 = mantissa & 8388607;
            if (scaledExponent2 < 255) {
                return Float.intBitsToFloat((scaledExponent2 << 23) | sign | mantissa4);
            }
            return sign == 0 ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
        }
        if (scaledExponent < 255) {
            return Float.intBitsToFloat((scaledExponent << 23) | sign | mantissa);
        }
        return sign == 0 ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
    }

    public static double nextAfter(double d, double direction) {
        if (Double.isNaN(d) || Double.isNaN(direction)) {
            return Double.NaN;
        }
        if (d == direction) {
            return direction;
        }
        if (Double.isInfinite(d)) {
            return d < 0.0d ? -1.7976931348623157E308d : Double.MAX_VALUE;
        }
        if (d == 0.0d) {
            return direction < 0.0d ? -4.9E-324d : Double.MIN_VALUE;
        }
        long bits = Double.doubleToRawLongBits(d);
        long sign = Long.MIN_VALUE & bits;
        if ((direction < d) ^ (sign == 0)) {
            return Double.longBitsToDouble(sign | ((Long.MAX_VALUE & bits) + 1));
        }
        return Double.longBitsToDouble(sign | ((Long.MAX_VALUE & bits) - 1));
    }

    public static float nextAfter(float f, double direction) {
        if (Double.isNaN(f) || Double.isNaN(direction)) {
            return Float.NaN;
        }
        if (f == direction) {
            return (float) direction;
        }
        if (Float.isInfinite(f)) {
            return f < 0.0f ? -3.4028235E38f : Float.MAX_VALUE;
        }
        if (f == 0.0f) {
            return direction < 0.0d ? -1.4E-45f : Float.MIN_VALUE;
        }
        int bits = Float.floatToIntBits(f);
        int sign = Integer.MIN_VALUE & bits;
        if ((direction < ((double) f)) ^ (sign == 0)) {
            return Float.intBitsToFloat(((bits & Integer.MAX_VALUE) + 1) | sign);
        }
        return Float.intBitsToFloat(((bits & Integer.MAX_VALUE) - 1) | sign);
    }

    public static double floor(double x) {
        if (x != x) {
            return x;
        }
        if (x >= TWO_POWER_52 || x <= -4.503599627370496E15d) {
            return x;
        }
        long y = (long) x;
        if (x < 0.0d && y != x) {
            y--;
        }
        if (y == 0) {
            return y * x;
        }
        return y;
    }

    public static double ceil(double x) {
        if (x != x) {
            return x;
        }
        double y = floor(x);
        if (y == x) {
            return y;
        }
        double y2 = y + 1.0d;
        if (y2 == 0.0d) {
            return x * y2;
        }
        return y2;
    }

    public static double rint(double x) {
        double y = floor(x);
        double d = x - y;
        if (d > F_1_2) {
            if (y == -1.0d) {
                return -0.0d;
            }
            return 1.0d + y;
        }
        if (d < F_1_2) {
            return y;
        }
        long z = (long) y;
        return (1 & z) == 0 ? y : 1.0d + y;
    }

    public static long round(double x) {
        return (long) floor(F_1_2 + x);
    }

    public static int round(float x) {
        return (int) floor(0.5f + x);
    }

    public static int min(int a, int b) {
        return a <= b ? a : b;
    }

    public static long min(long a, long b) {
        return a <= b ? a : b;
    }

    public static float min(float a, float b) {
        if (a > b) {
            return b;
        }
        if (a < b) {
            return a;
        }
        if (a != b) {
            return Float.NaN;
        }
        int bits = Float.floatToRawIntBits(a);
        if (bits == Integer.MIN_VALUE) {
            return a;
        }
        return b;
    }

    public static double min(double a, double b) {
        if (a > b) {
            return b;
        }
        if (a < b) {
            return a;
        }
        if (a != b) {
            return Double.NaN;
        }
        long bits = Double.doubleToRawLongBits(a);
        if (bits == Long.MIN_VALUE) {
            return a;
        }
        return b;
    }

    public static int max(int a, int b) {
        return a <= b ? b : a;
    }

    public static long max(long a, long b) {
        return a <= b ? b : a;
    }

    public static float max(float a, float b) {
        if (a > b) {
            return a;
        }
        if (a < b) {
            return b;
        }
        if (a != b) {
            return Float.NaN;
        }
        int bits = Float.floatToRawIntBits(a);
        if (bits == Integer.MIN_VALUE) {
            return b;
        }
        return a;
    }

    public static double max(double a, double b) {
        if (a > b) {
            return a;
        }
        if (a < b) {
            return b;
        }
        if (a != b) {
            return Double.NaN;
        }
        long bits = Double.doubleToRawLongBits(a);
        if (bits == Long.MIN_VALUE) {
            return b;
        }
        return a;
    }

    public static double hypot(double x, double y) {
        if (Double.isInfinite(x) || Double.isInfinite(y)) {
            return Double.POSITIVE_INFINITY;
        }
        if (Double.isNaN(x) || Double.isNaN(y)) {
            return Double.NaN;
        }
        int expX = getExponent(x);
        int expY = getExponent(y);
        if (expX > expY + 27) {
            return abs(x);
        }
        if (expY > expX + 27) {
            return abs(y);
        }
        int middleExp = (expX + expY) / 2;
        double scaledX = scalb(x, -middleExp);
        double scaledY = scalb(y, -middleExp);
        double scaledH = sqrt((scaledX * scaledX) + (scaledY * scaledY));
        return scalb(scaledH, middleExp);
    }

    public static double IEEEremainder(double dividend, double divisor) {
        return StrictMath.IEEEremainder(dividend, divisor);
    }

    public static int toIntExact(long n) throws MathArithmeticException {
        if (n < -2147483648L || n > 2147483647L) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW, new Object[0]);
        }
        return (int) n;
    }

    public static int incrementExact(int n) throws MathArithmeticException {
        if (n == Integer.MAX_VALUE) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, Integer.valueOf(n), 1);
        }
        return n + 1;
    }

    public static long incrementExact(long n) throws MathArithmeticException {
        if (n == Long.MAX_VALUE) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, Long.valueOf(n), 1);
        }
        return 1 + n;
    }

    public static int decrementExact(int n) throws MathArithmeticException {
        if (n == Integer.MIN_VALUE) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, Integer.valueOf(n), 1);
        }
        return n - 1;
    }

    public static long decrementExact(long n) throws MathArithmeticException {
        if (n == Long.MIN_VALUE) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, Long.valueOf(n), 1);
        }
        return n - 1;
    }

    public static int addExact(int a, int b) throws MathArithmeticException {
        int sum = a + b;
        if ((a ^ b) >= 0 && (sum ^ b) < 0) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, Integer.valueOf(a), Integer.valueOf(b));
        }
        return sum;
    }

    public static long addExact(long a, long b) throws MathArithmeticException {
        long sum = a + b;
        if ((a ^ b) >= 0 && (sum ^ b) < 0) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, Long.valueOf(a), Long.valueOf(b));
        }
        return sum;
    }

    public static int subtractExact(int a, int b) {
        int sub = a - b;
        if ((a ^ b) < 0 && (sub ^ b) >= 0) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, Integer.valueOf(a), Integer.valueOf(b));
        }
        return sub;
    }

    public static long subtractExact(long a, long b) {
        long sub = a - b;
        if ((a ^ b) < 0 && (sub ^ b) >= 0) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, Long.valueOf(a), Long.valueOf(b));
        }
        return sub;
    }

    public static int multiplyExact(int a, int b) {
        if ((b > 0 && (a > Integer.MAX_VALUE / b || a < Integer.MIN_VALUE / b)) || ((b < -1 && (a > Integer.MIN_VALUE / b || a < Integer.MAX_VALUE / b)) || (b == -1 && a == Integer.MIN_VALUE))) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_MULTIPLICATION, Integer.valueOf(a), Integer.valueOf(b));
        }
        return a * b;
    }

    public static long multiplyExact(long a, long b) {
        if ((b > 0 && (a > Long.MAX_VALUE / b || a < Long.MIN_VALUE / b)) || ((b < -1 && (a > Long.MIN_VALUE / b || a < Long.MAX_VALUE / b)) || (b == -1 && a == Long.MIN_VALUE))) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_MULTIPLICATION, Long.valueOf(a), Long.valueOf(b));
        }
        return a * b;
    }

    public static int floorDiv(int a, int b) throws MathArithmeticException {
        if (b == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
        }
        int m = a % b;
        if ((a ^ b) >= 0 || m == 0) {
            return a / b;
        }
        return (a / b) - 1;
    }

    public static long floorDiv(long a, long b) throws MathArithmeticException {
        if (b == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
        }
        long m = a % b;
        if ((a ^ b) >= 0 || m == 0) {
            return a / b;
        }
        return (a / b) - 1;
    }

    public static int floorMod(int a, int b) throws MathArithmeticException {
        if (b == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
        }
        int m = a % b;
        if ((a ^ b) >= 0 || m == 0) {
            return m;
        }
        return b + m;
    }

    public static long floorMod(long a, long b) {
        if (b == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
        }
        long m = a % b;
        if ((a ^ b) >= 0 || m == 0) {
            return m;
        }
        return b + m;
    }

    public static double copySign(double magnitude, double sign) {
        long m = Double.doubleToRawLongBits(magnitude);
        long s = Double.doubleToRawLongBits(sign);
        if ((m ^ s) >= 0) {
            return magnitude;
        }
        return -magnitude;
    }

    public static float copySign(float magnitude, float sign) {
        int m = Float.floatToRawIntBits(magnitude);
        int s = Float.floatToRawIntBits(sign);
        if ((m ^ s) >= 0) {
            return magnitude;
        }
        return -magnitude;
    }

    public static int getExponent(double d) {
        return ((int) ((Double.doubleToRawLongBits(d) >>> 52) & 2047)) - 1023;
    }

    public static int getExponent(float f) {
        return ((Float.floatToRawIntBits(f) >>> 23) & 255) - 127;
    }

    public static void main(String[] a) {
        PrintStream out = System.out;
        FastMathCalc.printarray(out, "EXP_INT_TABLE_A", EXP_INT_TABLE_LEN, ExpIntTable.EXP_INT_TABLE_A);
        FastMathCalc.printarray(out, "EXP_INT_TABLE_B", EXP_INT_TABLE_LEN, ExpIntTable.EXP_INT_TABLE_B);
        FastMathCalc.printarray(out, "EXP_FRAC_TABLE_A", 1025, ExpFracTable.EXP_FRAC_TABLE_A);
        FastMathCalc.printarray(out, "EXP_FRAC_TABLE_B", 1025, ExpFracTable.EXP_FRAC_TABLE_B);
        FastMathCalc.printarray(out, "LN_MANT", 1024, lnMant.LN_MANT);
        FastMathCalc.printarray(out, "SINE_TABLE_A", 14, SINE_TABLE_A);
        FastMathCalc.printarray(out, "SINE_TABLE_B", 14, SINE_TABLE_B);
        FastMathCalc.printarray(out, "COSINE_TABLE_A", 14, COSINE_TABLE_A);
        FastMathCalc.printarray(out, "COSINE_TABLE_B", 14, COSINE_TABLE_B);
        FastMathCalc.printarray(out, "TANGENT_TABLE_A", 14, TANGENT_TABLE_A);
        FastMathCalc.printarray(out, "TANGENT_TABLE_B", 14, TANGENT_TABLE_B);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class ExpIntTable {
        private static final double[] EXP_INT_TABLE_A = FastMathLiteralArrays.loadExpIntA();
        private static final double[] EXP_INT_TABLE_B = FastMathLiteralArrays.loadExpIntB();

        private ExpIntTable() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class ExpFracTable {
        private static final double[] EXP_FRAC_TABLE_A = FastMathLiteralArrays.loadExpFracA();
        private static final double[] EXP_FRAC_TABLE_B = FastMathLiteralArrays.loadExpFracB();

        private ExpFracTable() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class lnMant {
        private static final double[][] LN_MANT = FastMathLiteralArrays.loadLnMant();

        private lnMant() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class CodyWaite {
        private final int finalK;
        private final double finalRemA;
        private final double finalRemB;

        CodyWaite(double xa) {
            int k = (int) (0.6366197723675814d * xa);
            while (true) {
                double a = (-k) * 1.570796251296997d;
                double remA = xa + a;
                double remB = -((remA - xa) - a);
                double a2 = (-k) * 7.549789948768648E-8d;
                double remA2 = a2 + remA;
                double remB2 = remB + (-((remA2 - remA) - a2));
                double a3 = (-k) * 6.123233995736766E-17d;
                double remA3 = a3 + remA2;
                double remB3 = remB2 + (-((remA3 - remA2) - a3));
                if (remA3 > 0.0d) {
                    this.finalK = k;
                    this.finalRemA = remA3;
                    this.finalRemB = remB3;
                    return;
                }
                k--;
            }
        }

        int getK() {
            return this.finalK;
        }

        double getRemA() {
            return this.finalRemA;
        }

        double getRemB() {
            return this.finalRemB;
        }
    }
}
