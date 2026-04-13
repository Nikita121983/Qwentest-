package org.apache.poi.xssf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.helpers.XSSFXmlColumnPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXmlColumnPr;

/* loaded from: classes10.dex */
public class XSSFTableColumn {
    private final CTTableColumn ctTableColumn;
    private final XSSFTable table;
    private XSSFXmlColumnPr xmlColumnPr;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XSSFTableColumn(XSSFTable table, CTTableColumn ctTableColumn) {
        this.table = table;
        this.ctTableColumn = ctTableColumn;
    }

    public XSSFTable getTable() {
        return this.table;
    }

    public long getId() {
        return this.ctTableColumn.getId();
    }

    public void setId(long columnId) {
        this.ctTableColumn.setId(columnId);
    }

    public String getName() {
        return this.ctTableColumn.getName();
    }

    public void setName(String columnName) {
        this.ctTableColumn.setName(columnName);
    }

    public XSSFXmlColumnPr getXmlColumnPr() {
        CTXmlColumnPr ctXmlColumnPr;
        if (this.xmlColumnPr == null && (ctXmlColumnPr = this.ctTableColumn.getXmlColumnPr()) != null) {
            this.xmlColumnPr = new XSSFXmlColumnPr(this, ctXmlColumnPr);
        }
        return this.xmlColumnPr;
    }

    public int getColumnIndex() {
        return this.table.findColumnIndex(getName());
    }
}
