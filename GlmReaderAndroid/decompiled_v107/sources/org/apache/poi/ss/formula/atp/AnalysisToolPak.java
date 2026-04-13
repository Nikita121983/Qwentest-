package org.apache.poi.ss.formula.atp;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.NotImplementedFunctionException;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.function.FunctionMetadata;
import org.apache.poi.ss.formula.function.FunctionMetadataRegistry;
import org.apache.poi.ss.formula.functions.AverageIf;
import org.apache.poi.ss.formula.functions.Averageifs;
import org.apache.poi.ss.formula.functions.BesselJ;
import org.apache.poi.ss.formula.functions.Bin2Dec;
import org.apache.poi.ss.formula.functions.CeilingMath;
import org.apache.poi.ss.formula.functions.CeilingPrecise;
import org.apache.poi.ss.formula.functions.Complex;
import org.apache.poi.ss.formula.functions.Countifs;
import org.apache.poi.ss.formula.functions.Covar;
import org.apache.poi.ss.formula.functions.Days;
import org.apache.poi.ss.formula.functions.Dec2Bin;
import org.apache.poi.ss.formula.functions.Dec2Hex;
import org.apache.poi.ss.formula.functions.Delta;
import org.apache.poi.ss.formula.functions.DollarDe;
import org.apache.poi.ss.formula.functions.DollarFr;
import org.apache.poi.ss.formula.functions.EDate;
import org.apache.poi.ss.formula.functions.EOMonth;
import org.apache.poi.ss.formula.functions.FactDouble;
import org.apache.poi.ss.formula.functions.FloorMath;
import org.apache.poi.ss.formula.functions.FloorPrecise;
import org.apache.poi.ss.formula.functions.Forecast;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.Gcd;
import org.apache.poi.ss.formula.functions.Hex2Dec;
import org.apache.poi.ss.formula.functions.ImReal;
import org.apache.poi.ss.formula.functions.Imaginary;
import org.apache.poi.ss.formula.functions.Lcm;
import org.apache.poi.ss.formula.functions.Maxifs;
import org.apache.poi.ss.formula.functions.Minifs;
import org.apache.poi.ss.formula.functions.NormDist;
import org.apache.poi.ss.formula.functions.NormInv;
import org.apache.poi.ss.formula.functions.NormSDist;
import org.apache.poi.ss.formula.functions.NormSInv;
import org.apache.poi.ss.formula.functions.NumberValueFunction;
import org.apache.poi.ss.formula.functions.Oct2Dec;
import org.apache.poi.ss.formula.functions.Poisson;
import org.apache.poi.ss.formula.functions.Quotient;
import org.apache.poi.ss.formula.functions.Sheet;
import org.apache.poi.ss.formula.functions.Single;
import org.apache.poi.ss.formula.functions.Sqrtpi;
import org.apache.poi.ss.formula.functions.Sumifs;
import org.apache.poi.ss.formula.functions.TDist2t;
import org.apache.poi.ss.formula.functions.TDistLt;
import org.apache.poi.ss.formula.functions.TDistRt;
import org.apache.poi.ss.formula.functions.TextFunction;
import org.apache.poi.ss.formula.functions.WeekNum;
import org.apache.poi.ss.formula.udf.UDFFinder;

/* loaded from: classes10.dex */
public final class AnalysisToolPak implements UDFFinder {
    private final Map<String, FreeRefFunction> _functionsByName = createFunctionsMap();
    public static final UDFFinder instance = new AnalysisToolPak();
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) AnalysisToolPak.class);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class NotImplemented implements FreeRefFunction {
        private final String _functionName;

        public NotImplemented(String functionName) {
            this._functionName = functionName;
        }

        @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
        public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
            throw new NotImplementedFunctionException(this._functionName);
        }
    }

    private AnalysisToolPak() {
    }

    @Override // org.apache.poi.ss.formula.udf.UDFFinder
    public FreeRefFunction findFunction(String name) {
        if (name.startsWith("_xlfn.")) {
            name = name.substring("_xlfn.".length());
        }
        return this._functionsByName.get(name.toUpperCase(Locale.ROOT));
    }

    private Map<String, FreeRefFunction> createFunctionsMap() {
        Map<String, FreeRefFunction> m = new HashMap<>(127);
        r(m, "ACCRINT", null);
        r(m, "ACCRINTM", null);
        r(m, "AMORDEGRC", null);
        r(m, "AMORLINC", null);
        r(m, "AVERAGEIF", AverageIf.instance);
        r(m, "AVERAGEIFS", Averageifs.instance);
        r(m, "BAHTTEXT", null);
        r(m, "BESSELI", null);
        r(m, "BESSELJ", BesselJ.instance);
        r(m, "BESSELK", null);
        r(m, "BESSELY", null);
        r(m, "BIN2DEC", Bin2Dec.instance);
        r(m, "BIN2HEX", null);
        r(m, "BIN2OCT", null);
        r(m, "COMPLEX", Complex.instance);
        r(m, "CEILING.MATH", CeilingMath.instance);
        r(m, "CEILING.PRECISE", CeilingPrecise.instance);
        r(m, "CONCAT", TextFunction.CONCAT);
        r(m, "CONVERT", null);
        r(m, "COUNTIFS", Countifs.instance);
        r(m, "COUPDAYBS", null);
        r(m, "COUPDAYS", null);
        r(m, "COUPDAYSNC", null);
        r(m, "COUPNCD", null);
        r(m, "COUPNUM", null);
        r(m, "COUPPCD", null);
        r(m, "COVARIANCE.P", Covar.instanceP);
        r(m, "COVARIANCE.S", Covar.instanceS);
        r(m, "CUBEKPIMEMBER", null);
        r(m, "CUBEMEMBER", null);
        r(m, "CUBEMEMBERPROPERTY", null);
        r(m, "CUBERANKEDMEMBER", null);
        r(m, "CUBESET", null);
        r(m, "CUBESETCOUNT", null);
        r(m, "CUBEVALUE", null);
        r(m, "CUMIPMT", null);
        r(m, "CUMPRINC", null);
        r(m, "DAYS", Days.instance);
        r(m, "DEC2BIN", Dec2Bin.instance);
        r(m, "DEC2HEX", Dec2Hex.instance);
        r(m, "DEC2OCT", null);
        r(m, "DELTA", Delta.instance);
        r(m, "DISC", null);
        r(m, "DOLLARDE", DollarDe.instance);
        r(m, "DOLLARFR", DollarFr.instance);
        r(m, "DURATION", null);
        r(m, "EDATE", EDate.instance);
        r(m, "EFFECT", null);
        r(m, "EOMONTH", EOMonth.instance);
        r(m, "ERF", null);
        r(m, "ERFC", null);
        r(m, "FACTDOUBLE", FactDouble.instance);
        r(m, "FLOOR.MATH", FloorMath.instance);
        r(m, "FLOOR.PRECISE", FloorPrecise.instance);
        r(m, "FORECAST.LINEAR", Forecast.instance);
        r(m, "FVSCHEDULE", null);
        r(m, "GCD", Gcd.instance);
        r(m, "GESTEP", null);
        r(m, "HEX2BIN", null);
        r(m, "HEX2DEC", Hex2Dec.instance);
        r(m, "HEX2OCT", null);
        r(m, "IFERROR", IfError.instance);
        r(m, "IFNA", IfNa.instance);
        r(m, "IFS", Ifs.instance);
        r(m, "IMABS", null);
        r(m, "IMAGINARY", Imaginary.instance);
        r(m, "IMARGUMENT", null);
        r(m, "IMCONJUGATE", null);
        r(m, "IMCOS", null);
        r(m, "IMDIV", null);
        r(m, "IMEXP", null);
        r(m, "IMLN", null);
        r(m, "IMLOG10", null);
        r(m, "IMLOG2", null);
        r(m, "IMPOWER", null);
        r(m, "IMPRODUCT", null);
        r(m, "IMREAL", ImReal.instance);
        r(m, "IMSIN", null);
        r(m, "IMSQRT", null);
        r(m, "IMSUB", null);
        r(m, "IMSUM", null);
        r(m, "INTRATE", null);
        r(m, "ISEVEN", ParityFunction.IS_EVEN);
        r(m, "ISODD", ParityFunction.IS_ODD);
        r(m, "JIS", null);
        r(m, "LCM", Lcm.instance);
        r(m, "MAXIFS", Maxifs.instance);
        r(m, "MDURATION", null);
        r(m, "MINIFS", Minifs.instance);
        r(m, "MROUND", MRound.instance);
        r(m, "MULTINOMIAL", null);
        r(m, "NETWORKDAYS", NetworkdaysFunction.instance);
        r(m, "NOMINAL", null);
        r(m, "NORM.DIST", NormDist.instance);
        r(m, "NORM.S.DIST", NormSDist.instance);
        r(m, "NORM.INV", NormInv.instance);
        r(m, "NORM.S.INV", NormSInv.instance);
        r(m, "NUMBERVALUE", NumberValueFunction.instance);
        r(m, "OCT2BIN", null);
        r(m, "OCT2DEC", Oct2Dec.instance);
        r(m, "OCT2HEX", null);
        r(m, "ODDFPRICE", null);
        r(m, "ODDFYIELD", null);
        r(m, "ODDLPRICE", null);
        r(m, "ODDLYIELD", null);
        r(m, "PERCENTRANK.EXC", PercentRankExcFunction.instance);
        r(m, "PERCENTRANK.INC", PercentRankIncFunction.instance);
        r(m, "POISSON.DIST", Poisson.instance);
        r(m, "PRICE", null);
        r(m, "PRICEDISC", null);
        r(m, "PRICEMAT", null);
        r(m, "QUOTIENT", Quotient.instance);
        r(m, "RANDBETWEEN", RandBetween.instance);
        r(m, "RECEIVED", null);
        r(m, "RTD", null);
        r(m, "SERIESSUM", null);
        r(m, "SHEET", Sheet.instance);
        r(m, "SINGLE", Single.instance);
        r(m, "SQRTPI", Sqrtpi.instance);
        r(m, "STDEV.S", Stdevs.instance);
        r(m, "STDEV.P", Stdevp.instance);
        r(m, "SUMIFS", Sumifs.instance);
        r(m, "SWITCH", Switch.instance);
        r(m, "TBILLEQ", null);
        r(m, "TBILLPRICE", null);
        r(m, "TBILLYIELD", null);
        r(m, "T.DIST", TDistLt.instance);
        r(m, "T.DIST.2T", TDist2t.instance);
        r(m, "T.DIST.RT", TDistRt.instance);
        r(m, "TEXTJOIN", TextJoinFunction.instance);
        r(m, "WEEKNUM", WeekNum.instance);
        r(m, "WORKDAY", WorkdayFunction.instance);
        r(m, "WORKDAY.INTL", WorkdayIntlFunction.instance);
        r(m, "XIRR", null);
        r(m, "XLOOKUP", XLookupFunction.instance);
        r(m, "XMATCH", XMatchFunction.instance);
        r(m, "XNPV", null);
        r(m, "YEARFRAC", YearFrac.instance);
        r(m, "YIELD", null);
        r(m, "YIELDDISC", null);
        r(m, "YIELDMAT", null);
        r(m, "VAR.S", Vars.instance);
        r(m, "VAR.P", Varp.instance);
        return m;
    }

    private static void r(Map<String, FreeRefFunction> m, String functionName, FreeRefFunction pFunc) {
        FreeRefFunction func = pFunc == null ? new NotImplemented(functionName) : pFunc;
        m.put(functionName, func);
    }

    public static boolean isATPFunction(String name) {
        AnalysisToolPak inst = (AnalysisToolPak) instance;
        return inst._functionsByName.containsKey(name);
    }

    public static Collection<String> getSupportedFunctionNames() {
        AnalysisToolPak inst = (AnalysisToolPak) instance;
        Collection<String> lst = new TreeSet<>();
        for (Map.Entry<String, FreeRefFunction> me : inst._functionsByName.entrySet()) {
            FreeRefFunction func = me.getValue();
            if (func != null && !(func instanceof NotImplemented)) {
                lst.add(me.getKey());
            }
        }
        return Collections.unmodifiableCollection(lst);
    }

    public static Collection<String> getNotSupportedFunctionNames() {
        AnalysisToolPak inst = (AnalysisToolPak) instance;
        Collection<String> lst = new TreeSet<>();
        for (Map.Entry<String, FreeRefFunction> me : inst._functionsByName.entrySet()) {
            FreeRefFunction func = me.getValue();
            if (func instanceof NotImplemented) {
                lst.add(me.getKey());
            }
        }
        return Collections.unmodifiableCollection(lst);
    }

    public static void registerFunction(String name, FreeRefFunction func) {
        registerFunction(name, func, false);
    }

    public static void registerFunction(String name, FreeRefFunction func, boolean force) {
        AnalysisToolPak inst = (AnalysisToolPak) instance;
        if (!force && !isATPFunction(name)) {
            FunctionMetadata metaData = FunctionMetadataRegistry.getFunctionByName(name);
            if (metaData != null) {
                throw new IllegalArgumentException(name + " is a built-in Excel function. Use FunctionEval.registerFunction(String name, Function func) instead.");
            }
            throw new IllegalArgumentException(name + " is not a function from the Excel Analysis Toolpack.");
        }
        FreeRefFunction f = inst.findFunction(name);
        if (f != null && !(f instanceof NotImplemented)) {
            if (force) {
                LOG.info("POI already implements " + name + ". You are overriding the implementation.");
            } else {
                throw new IllegalArgumentException("POI already implements " + name + ". You cannot override POI's implementations of Excel functions");
            }
        }
        inst._functionsByName.put(name, func);
    }
}
