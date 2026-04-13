package org.apache.poi.xssf.usermodel.helpers;

import java.util.Iterator;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Pxg;
import org.apache.poi.ss.formula.ptg.Pxg3D;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellFormula;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: classes10.dex */
public final class XSSFFormulaUtils {
    private final XSSFEvaluationWorkbook _fpwb;
    private final XSSFWorkbook _wb;

    public XSSFFormulaUtils(XSSFWorkbook wb) {
        this._wb = wb;
        this._fpwb = XSSFEvaluationWorkbook.create(this._wb);
    }

    public void updateSheetName(int sheetIndex, String oldName, String newName) {
        for (XSSFName nm : this._wb.getAllNames()) {
            if (nm.getSheetIndex() == -1 || nm.getSheetIndex() == sheetIndex) {
                updateName(nm, oldName, newName);
            }
        }
        Iterator<Sheet> it = this._wb.iterator();
        while (it.hasNext()) {
            Sheet sh = it.next();
            for (Row row : sh) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.FORMULA) {
                        updateFormula((XSSFCell) cell, oldName, newName);
                    }
                }
            }
        }
        List<POIXMLDocumentPart> rels = this._wb.getSheetAt(sheetIndex).getRelations();
        for (POIXMLDocumentPart r : rels) {
            if (r instanceof XSSFDrawing) {
                XSSFDrawing dg = (XSSFDrawing) r;
                for (XSSFChart chart : dg.getCharts()) {
                    Node dom = chart.getCTChartSpace().getDomNode();
                    updateDomSheetReference(dom, oldName, newName);
                }
            }
        }
    }

    private void updateFormula(XSSFCell cell, String oldName, String newName) {
        String formula;
        CTCellFormula f = cell.getCTCell().getF();
        if (f != null && (formula = f.getStringValue()) != null && formula.length() > 0) {
            int sheetIndex = this._wb.getSheetIndex(cell.getSheet());
            Ptg[] ptgs = FormulaParser.parse(formula, this._fpwb, FormulaType.CELL, sheetIndex, cell.getRowIndex());
            for (Ptg ptg : ptgs) {
                updatePtg(ptg, oldName, newName);
            }
            String updatedFormula = FormulaRenderer.toFormulaString(this._fpwb, ptgs);
            if (!formula.equals(updatedFormula)) {
                f.setStringValue(updatedFormula);
            }
        }
    }

    private void updateName(XSSFName name, String oldName, String newName) {
        String formula = name.getRefersToFormula();
        if (formula != null) {
            int sheetIndex = name.getSheetIndex();
            Ptg[] ptgs = FormulaParser.parse(formula, this._fpwb, FormulaType.NAMEDRANGE, sheetIndex, -1);
            for (Ptg ptg : ptgs) {
                updatePtg(ptg, oldName, newName);
            }
            String updatedFormula = FormulaRenderer.toFormulaString(this._fpwb, ptgs);
            if (!formula.equals(updatedFormula)) {
                name.setRefersToFormula(updatedFormula);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void updatePtg(Ptg ptg, String oldName, String newName) {
        if (ptg instanceof Pxg) {
            Pxg pxg = (Pxg) ptg;
            if (pxg.getExternalWorkbookNumber() < 1) {
                if (pxg.getSheetName() != null && pxg.getSheetName().equals(oldName)) {
                    pxg.setSheetName(newName);
                }
                if (pxg instanceof Pxg3D) {
                    Pxg3D pxg3D = (Pxg3D) pxg;
                    if (pxg3D.getLastSheetName() != null && pxg3D.getLastSheetName().equals(oldName)) {
                        pxg3D.setLastSheetName(newName);
                    }
                }
            }
        }
    }

    private void updateDomSheetReference(Node dom, String oldName, String newName) {
        String value = dom.getNodeValue();
        if (value != null && (value.contains(oldName + "!") || value.contains(oldName + "'!"))) {
            XSSFName temporary = this._wb.createName();
            temporary.setRefersToFormula(value);
            updateName(temporary, oldName, newName);
            dom.setNodeValue(temporary.getRefersToFormula());
            this._wb.removeName(temporary);
        }
        NodeList nl = dom.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            updateDomSheetReference(nl.item(i), oldName, newName);
        }
    }
}
