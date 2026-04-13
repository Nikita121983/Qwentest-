package org.apache.poi.xssf.usermodel.helpers;

import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXmlColumnPr;

/* loaded from: classes10.dex */
public class XSSFXmlColumnPr {
    private CTXmlColumnPr ctXmlColumnPr;
    private XSSFTable table;
    private XSSFTableColumn tableColumn;

    @Internal
    public XSSFXmlColumnPr(XSSFTableColumn tableColumn, CTXmlColumnPr ctXmlColumnPr) {
        this.table = tableColumn.getTable();
        this.tableColumn = tableColumn;
        this.ctXmlColumnPr = ctXmlColumnPr;
    }

    public XSSFTableColumn getTableColumn() {
        return this.tableColumn;
    }

    public long getMapId() {
        return this.ctXmlColumnPr.getMapId();
    }

    public String getXPath() {
        return this.ctXmlColumnPr.getXpath();
    }

    public String getLocalXPath() {
        StringBuilder localXPath = new StringBuilder();
        int numberOfCommonXPathAxis = this.table.getCommonXpath().split(PackagingURIHelper.FORWARD_SLASH_STRING).length - 1;
        String[] xPathTokens = this.ctXmlColumnPr.getXpath().split(PackagingURIHelper.FORWARD_SLASH_STRING);
        for (int i = numberOfCommonXPathAxis; i < xPathTokens.length; i++) {
            localXPath.append(PackagingURIHelper.FORWARD_SLASH_STRING).append(xPathTokens[i]);
        }
        return localXPath.toString();
    }

    public String getXmlDataType() {
        return this.ctXmlColumnPr.getXmlDataType();
    }
}
