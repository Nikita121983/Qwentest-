package org.apache.poi.xssf.usermodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.ss.usermodel.ConditionalFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.util.CellRangeAddress;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConditionalFormatting;

/* loaded from: classes10.dex */
public class XSSFConditionalFormatting implements ConditionalFormatting {
    private final CTConditionalFormatting _cf;
    private final XSSFSheet _sh;

    XSSFConditionalFormatting(XSSFSheet sh) {
        this._cf = CTConditionalFormatting.Factory.newInstance();
        this._sh = sh;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSSFConditionalFormatting(XSSFSheet sh, CTConditionalFormatting cf) {
        this._cf = cf;
        this._sh = sh;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CTConditionalFormatting getCTConditionalFormatting() {
        return this._cf;
    }

    @Override // org.apache.poi.ss.usermodel.ConditionalFormatting
    public CellRangeAddress[] getFormattingRanges() {
        ArrayList<CellRangeAddress> lst = new ArrayList<>();
        Iterator it = this._cf.getSqref().iterator();
        while (true) {
            if (it.hasNext()) {
                Object stRef = it.next();
                String[] regions = stRef.toString().split(StringUtils.SPACE);
                for (String region : regions) {
                    lst.add(CellRangeAddress.valueOf(region));
                }
            } else {
                return (CellRangeAddress[]) lst.toArray(new CellRangeAddress[0]);
            }
        }
    }

    @Override // org.apache.poi.ss.usermodel.ConditionalFormatting
    public void setFormattingRanges(CellRangeAddress[] ranges) {
        if (ranges == null) {
            throw new IllegalArgumentException("cellRanges must not be null");
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (CellRangeAddress range : ranges) {
            if (!first) {
                sb.append(Chars.SPACE);
            } else {
                first = false;
            }
            sb.append(range.formatAsString());
        }
        this._cf.setSqref(Collections.singletonList(sb.toString()));
    }

    @Override // org.apache.poi.ss.usermodel.ConditionalFormatting
    public void setRule(int idx, ConditionalFormattingRule cfRule) {
        XSSFConditionalFormattingRule xRule = (XSSFConditionalFormattingRule) cfRule;
        this._cf.getCfRuleArray(idx).set(xRule.getCTCfRule());
    }

    @Override // org.apache.poi.ss.usermodel.ConditionalFormatting
    public void addRule(ConditionalFormattingRule cfRule) {
        XSSFConditionalFormattingRule xRule = (XSSFConditionalFormattingRule) cfRule;
        this._cf.addNewCfRule().set(xRule.getCTCfRule());
    }

    @Override // org.apache.poi.ss.usermodel.ConditionalFormatting
    public XSSFConditionalFormattingRule getRule(int idx) {
        return new XSSFConditionalFormattingRule(this._sh, this._cf.getCfRuleArray(idx));
    }

    @Override // org.apache.poi.ss.usermodel.ConditionalFormatting
    public int getNumberOfRules() {
        return this._cf.sizeOfCfRuleArray();
    }

    public String toString() {
        return this._cf.toString();
    }
}
