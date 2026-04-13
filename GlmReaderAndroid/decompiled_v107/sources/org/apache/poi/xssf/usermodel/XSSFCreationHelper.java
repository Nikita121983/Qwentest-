package org.apache.poi.xssf.usermodel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;

/* loaded from: classes10.dex */
public class XSSFCreationHelper implements CreationHelper {
    private final Map<String, Workbook> referencedWorkbooks = new HashMap();
    private final XSSFWorkbook workbook;

    @Internal
    public XSSFCreationHelper(XSSFWorkbook wb) {
        this.workbook = wb;
    }

    @Override // org.apache.poi.ss.usermodel.CreationHelper
    public XSSFRichTextString createRichTextString(String text) {
        XSSFRichTextString rt = new XSSFRichTextString(text);
        rt.setStylesTableReference(this.workbook.getStylesSource());
        return rt;
    }

    @Override // org.apache.poi.ss.usermodel.CreationHelper
    public XSSFDataFormat createDataFormat() {
        return this.workbook.createDataFormat();
    }

    @Override // org.apache.poi.ss.usermodel.CreationHelper
    public XSSFColor createExtendedColor() {
        return XSSFColor.from(CTColor.Factory.newInstance(), this.workbook.getStylesSource().getIndexedColors());
    }

    @Override // org.apache.poi.ss.usermodel.CreationHelper
    public XSSFHyperlink createHyperlink(HyperlinkType type) {
        return new XSSFHyperlink(type);
    }

    @Override // org.apache.poi.ss.usermodel.CreationHelper
    public XSSFFormulaEvaluator createFormulaEvaluator() {
        XSSFFormulaEvaluator evaluator = new XSSFFormulaEvaluator(this.workbook);
        final Map<String, FormulaEvaluator> evaluatorMap = new HashMap<>();
        evaluatorMap.put("", evaluator);
        this.referencedWorkbooks.forEach(new BiConsumer() { // from class: org.apache.poi.xssf.usermodel.XSSFCreationHelper$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                XSSFCreationHelper.lambda$createFormulaEvaluator$0(evaluatorMap, (String) obj, (Workbook) obj2);
            }
        });
        evaluator.setupReferencedWorkbooks(evaluatorMap);
        return evaluator;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$createFormulaEvaluator$0(Map evaluatorMap, String name, Workbook otherWorkbook) {
    }

    @Override // org.apache.poi.ss.usermodel.CreationHelper
    public XSSFClientAnchor createClientAnchor() {
        return new XSSFClientAnchor();
    }

    @Override // org.apache.poi.ss.usermodel.CreationHelper
    public AreaReference createAreaReference(String reference) {
        return new AreaReference(reference, this.workbook.getSpreadsheetVersion());
    }

    @Override // org.apache.poi.ss.usermodel.CreationHelper
    public AreaReference createAreaReference(CellReference topLeft, CellReference bottomRight) {
        return new AreaReference(topLeft, bottomRight, this.workbook.getSpreadsheetVersion());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Map<String, Workbook> getReferencedWorkbooks() {
        return this.referencedWorkbooks;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addExternalWorkbook(String name, Workbook workbook) {
        this.referencedWorkbooks.put(name, workbook);
    }
}
