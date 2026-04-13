package org.apache.poi.hssf.record.aggregates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.model.RecordStream;
import org.apache.poi.hssf.record.CFHeader12Record;
import org.apache.poi.hssf.record.CFHeaderBase;
import org.apache.poi.hssf.record.CFHeaderRecord;
import org.apache.poi.hssf.record.CFRule12Record;
import org.apache.poi.hssf.record.CFRuleBase;
import org.apache.poi.hssf.record.CFRuleRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.helpers.BaseRowColShifter;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public final class CFRecordsAggregate extends RecordAggregate implements GenericRecord {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) CFRecordsAggregate.class);
    private static final int MAX_97_2003_CONDTIONAL_FORMAT_RULES = 3;
    private final CFHeaderBase header;
    private final List<CFRuleBase> rules;

    public CFRecordsAggregate(CFRecordsAggregate other) {
        this.rules = new ArrayList();
        this.header = other.header.copy();
        Stream<R> map = other.rules.stream().map(new Function() { // from class: org.apache.poi.hssf.record.aggregates.CFRecordsAggregate$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CFRuleBase) obj).copy();
            }
        });
        final List<CFRuleBase> list = this.rules;
        list.getClass();
        map.forEach(new Consumer() { // from class: org.apache.poi.hssf.record.aggregates.CFRecordsAggregate$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                list.add((CFRuleBase) obj);
            }
        });
    }

    private CFRecordsAggregate(CFHeaderBase pHeader, CFRuleBase[] pRules) {
        this.rules = new ArrayList();
        if (pHeader == null) {
            throw new IllegalArgumentException("header must not be null");
        }
        if (pRules == null) {
            throw new IllegalArgumentException("rules must not be null");
        }
        if (pRules.length > 3) {
            LOG.atWarn().log("Excel versions before 2007 require that No more than 3 rules may be specified, {} were found, this file will cause problems with old Excel versions", Unbox.box(pRules.length));
        }
        if (pRules.length != pHeader.getNumberOfConditionalFormats()) {
            throw new RecordFormatException("Mismatch number of rules");
        }
        this.header = pHeader;
        for (CFRuleBase pRule : pRules) {
            checkRuleType(pRule);
            this.rules.add(pRule);
        }
    }

    public CFRecordsAggregate(CellRangeAddress[] regions, CFRuleBase[] rules) {
        this(createHeader(regions, rules), rules);
    }

    private static CFHeaderBase createHeader(CellRangeAddress[] regions, CFRuleBase[] rules) {
        CFHeaderBase header;
        if (rules.length == 0 || (rules[0] instanceof CFRuleRecord)) {
            header = new CFHeaderRecord(regions, rules.length);
        } else {
            header = new CFHeader12Record(regions, rules.length);
        }
        header.setNeedRecalculation(true);
        return header;
    }

    public static CFRecordsAggregate createCFAggregate(RecordStream rs) {
        Record rec = rs.getNext();
        if (rec.getSid() != 432 && rec.getSid() != 2169) {
            throw new IllegalStateException("next record sid was " + ((int) rec.getSid()) + " instead of 432 or 2169 as expected");
        }
        CFHeaderBase header = (CFHeaderBase) rec;
        int nRules = header.getNumberOfConditionalFormats();
        CFRuleBase[] rules = new CFRuleBase[nRules];
        for (int i = 0; i < rules.length; i++) {
            Record record = rs.getNext();
            if (!(record instanceof CFRuleBase)) {
                throw new IllegalArgumentException("Did not have a CFRuleBase: " + record);
            }
            rules[i] = (CFRuleBase) record;
        }
        return new CFRecordsAggregate(header, rules);
    }

    public CFRecordsAggregate cloneCFAggregate() {
        return new CFRecordsAggregate(this);
    }

    public CFHeaderBase getHeader() {
        return this.header;
    }

    private void checkRuleIndex(int idx) {
        if (idx < 0 || idx >= this.rules.size()) {
            throw new IllegalArgumentException("Bad rule record index (" + idx + ") nRules=" + this.rules.size());
        }
    }

    private void checkRuleType(CFRuleBase r) {
        if ((this.header instanceof CFHeaderRecord) && (r instanceof CFRuleRecord)) {
            return;
        }
        if ((this.header instanceof CFHeader12Record) && (r instanceof CFRule12Record)) {
        } else {
            throw new IllegalArgumentException("Header and Rule must both be CF or both be CF12, can't mix");
        }
    }

    public CFRuleBase getRule(int idx) {
        checkRuleIndex(idx);
        return this.rules.get(idx);
    }

    public void setRule(int idx, CFRuleBase r) {
        if (r == null) {
            throw new IllegalArgumentException("r must not be null");
        }
        checkRuleIndex(idx);
        checkRuleType(r);
        this.rules.set(idx, r);
    }

    public void addRule(CFRuleBase r) {
        if (r == null) {
            throw new IllegalArgumentException("r must not be null");
        }
        if (this.rules.size() >= 3) {
            LOG.atWarn().log("Excel versions before 2007 cannot cope with any more than 3 - this file will cause problems with old Excel versions");
        }
        checkRuleType(r);
        this.rules.add(r);
        this.header.setNumberOfConditionalFormats(this.rules.size());
    }

    public int getNumberOfRules() {
        return this.rules.size();
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("header", new Supplier() { // from class: org.apache.poi.hssf.record.aggregates.CFRecordsAggregate$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRecordsAggregate.this.getHeader();
            }
        }, "rules", new Supplier() { // from class: org.apache.poi.hssf.record.aggregates.CFRecordsAggregate$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRecordsAggregate.this.m2399x4bd4409a();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-aggregates-CFRecordsAggregate, reason: not valid java name */
    public /* synthetic */ Object m2399x4bd4409a() {
        return this.rules;
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    @Override // org.apache.poi.hssf.record.aggregates.RecordAggregate
    public void visitContainedRecords(RecordAggregate.RecordVisitor rv) {
        rv.visitRecord(this.header);
        for (CFRuleBase rule : this.rules) {
            rv.visitRecord(rule);
        }
    }

    public boolean updateFormulasAfterCellShift(FormulaShifter shifter, int currentExternSheetIx) {
        CFRule12Record rule12;
        Ptg[] ptgs;
        CellRangeAddress[] cellRanges = this.header.getCellRanges();
        boolean changed = false;
        List<CellRangeAddress> temp = new ArrayList<>();
        for (CellRangeAddress craOld : cellRanges) {
            CellRangeAddress craNew = BaseRowColShifter.shiftRange(shifter, craOld, currentExternSheetIx);
            if (craNew == null) {
                changed = true;
            } else {
                temp.add(craNew);
                if (craNew != craOld) {
                    changed = true;
                }
            }
        }
        if (changed) {
            int nRanges = temp.size();
            if (nRanges == 0) {
                return false;
            }
            CellRangeAddress[] newRanges = new CellRangeAddress[nRanges];
            temp.toArray(newRanges);
            this.header.setCellRanges(newRanges);
        }
        for (CFRuleBase rule : this.rules) {
            Ptg[] ptgs2 = rule.getParsedExpression1();
            if (ptgs2 != null && shifter.adjustFormula(ptgs2, currentExternSheetIx)) {
                rule.setParsedExpression1(ptgs2);
            }
            Ptg[] ptgs3 = rule.getParsedExpression2();
            if (ptgs3 != null && shifter.adjustFormula(ptgs3, currentExternSheetIx)) {
                rule.setParsedExpression2(ptgs3);
            }
            if ((rule instanceof CFRule12Record) && (ptgs = (rule12 = (CFRule12Record) rule).getParsedExpressionScale()) != null && shifter.adjustFormula(ptgs, currentExternSheetIx)) {
                rule12.setParsedExpressionScale(ptgs);
            }
        }
        return true;
    }
}
