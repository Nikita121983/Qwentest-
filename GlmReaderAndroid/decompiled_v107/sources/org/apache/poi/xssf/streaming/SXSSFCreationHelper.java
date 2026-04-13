package org.apache.poi.xssf.streaming;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.ExtendedColor;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

/* loaded from: classes10.dex */
public class SXSSFCreationHelper implements CreationHelper {
    private final XSSFCreationHelper helper;
    private final SXSSFWorkbook wb;

    @Internal
    public SXSSFCreationHelper(SXSSFWorkbook workbook) {
        this.helper = new XSSFCreationHelper(workbook.getXSSFWorkbook());
        this.wb = workbook;
    }

    @Override // org.apache.poi.ss.usermodel.CreationHelper
    public XSSFRichTextString createRichTextString(String text) {
        return new XSSFRichTextString(text);
    }

    @Override // org.apache.poi.ss.usermodel.CreationHelper
    public SXSSFFormulaEvaluator createFormulaEvaluator() {
        return new SXSSFFormulaEvaluator(this.wb);
    }

    @Override // org.apache.poi.ss.usermodel.CreationHelper
    public DataFormat createDataFormat() {
        return this.helper.createDataFormat();
    }

    @Override // org.apache.poi.ss.usermodel.CreationHelper
    public Hyperlink createHyperlink(HyperlinkType type) {
        return this.helper.createHyperlink(type);
    }

    @Override // org.apache.poi.ss.usermodel.CreationHelper
    public ExtendedColor createExtendedColor() {
        return this.helper.createExtendedColor();
    }

    @Override // org.apache.poi.ss.usermodel.CreationHelper
    public ClientAnchor createClientAnchor() {
        return this.helper.createClientAnchor();
    }

    @Override // org.apache.poi.ss.usermodel.CreationHelper
    public AreaReference createAreaReference(String reference) {
        return new AreaReference(reference, this.wb.getSpreadsheetVersion());
    }

    @Override // org.apache.poi.ss.usermodel.CreationHelper
    public AreaReference createAreaReference(CellReference topLeft, CellReference bottomRight) {
        return new AreaReference(topLeft, bottomRight, this.wb.getSpreadsheetVersion());
    }
}
