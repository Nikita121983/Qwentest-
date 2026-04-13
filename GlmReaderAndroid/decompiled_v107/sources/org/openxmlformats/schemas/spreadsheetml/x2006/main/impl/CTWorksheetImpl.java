package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoFilter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellWatches;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCols;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConditionalFormatting;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTControls;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomProperties;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataConsolidate;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidations;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawingHF;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHyperlinks;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIgnoredErrors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLegacyDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMergeCells;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageBreak;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRanges;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTScenarios;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetBackgroundPicture;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetCalcPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetData;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetDimension;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetFormatPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTags;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortState;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableParts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItems;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;

/* loaded from: classes12.dex */
public class CTWorksheetImpl extends XmlComplexContentImpl implements CTWorksheet {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "sheetPr"), new QName(XSSFRelation.NS_SPREADSHEETML, TypedValues.Custom.S_DIMENSION), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetViews"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetFormatPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "cols"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetData"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetCalcPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetProtection"), new QName(XSSFRelation.NS_SPREADSHEETML, "protectedRanges"), new QName(XSSFRelation.NS_SPREADSHEETML, "scenarios"), new QName(XSSFRelation.NS_SPREADSHEETML, "autoFilter"), new QName(XSSFRelation.NS_SPREADSHEETML, "sortState"), new QName(XSSFRelation.NS_SPREADSHEETML, "dataConsolidate"), new QName(XSSFRelation.NS_SPREADSHEETML, "customSheetViews"), new QName(XSSFRelation.NS_SPREADSHEETML, "mergeCells"), new QName(XSSFRelation.NS_SPREADSHEETML, "phoneticPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "conditionalFormatting"), new QName(XSSFRelation.NS_SPREADSHEETML, "dataValidations"), new QName(XSSFRelation.NS_SPREADSHEETML, "hyperlinks"), new QName(XSSFRelation.NS_SPREADSHEETML, "printOptions"), new QName(XSSFRelation.NS_SPREADSHEETML, "pageMargins"), new QName(XSSFRelation.NS_SPREADSHEETML, "pageSetup"), new QName(XSSFRelation.NS_SPREADSHEETML, "headerFooter"), new QName(XSSFRelation.NS_SPREADSHEETML, "rowBreaks"), new QName(XSSFRelation.NS_SPREADSHEETML, "colBreaks"), new QName(XSSFRelation.NS_SPREADSHEETML, "customProperties"), new QName(XSSFRelation.NS_SPREADSHEETML, "cellWatches"), new QName(XSSFRelation.NS_SPREADSHEETML, "ignoredErrors"), new QName(XSSFRelation.NS_SPREADSHEETML, "smartTags"), new QName(XSSFRelation.NS_SPREADSHEETML, "drawing"), new QName(XSSFRelation.NS_SPREADSHEETML, "legacyDrawing"), new QName(XSSFRelation.NS_SPREADSHEETML, "legacyDrawingHF"), new QName(XSSFRelation.NS_SPREADSHEETML, "drawingHF"), new QName(XSSFRelation.NS_SPREADSHEETML, "picture"), new QName(XSSFRelation.NS_SPREADSHEETML, "oleObjects"), new QName(XSSFRelation.NS_SPREADSHEETML, "controls"), new QName(XSSFRelation.NS_SPREADSHEETML, "webPublishItems"), new QName(XSSFRelation.NS_SPREADSHEETML, "tableParts"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTWorksheetImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSheetPr getSheetPr() {
        CTSheetPr cTSheetPr;
        synchronized (monitor()) {
            check_orphaned();
            CTSheetPr target = (CTSheetPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTSheetPr = target == null ? null : target;
        }
        return cTSheetPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetSheetPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setSheetPr(CTSheetPr sheetPr) {
        generatedSetterHelperImpl(sheetPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSheetPr addNewSheetPr() {
        CTSheetPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSheetPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetSheetPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSheetDimension getDimension() {
        CTSheetDimension cTSheetDimension;
        synchronized (monitor()) {
            check_orphaned();
            CTSheetDimension target = (CTSheetDimension) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTSheetDimension = target == null ? null : target;
        }
        return cTSheetDimension;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetDimension() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setDimension(CTSheetDimension dimension) {
        generatedSetterHelperImpl(dimension, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSheetDimension addNewDimension() {
        CTSheetDimension target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSheetDimension) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetDimension() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSheetViews getSheetViews() {
        CTSheetViews cTSheetViews;
        synchronized (monitor()) {
            check_orphaned();
            CTSheetViews target = (CTSheetViews) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTSheetViews = target == null ? null : target;
        }
        return cTSheetViews;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetSheetViews() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setSheetViews(CTSheetViews sheetViews) {
        generatedSetterHelperImpl(sheetViews, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSheetViews addNewSheetViews() {
        CTSheetViews target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSheetViews) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetSheetViews() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSheetFormatPr getSheetFormatPr() {
        CTSheetFormatPr cTSheetFormatPr;
        synchronized (monitor()) {
            check_orphaned();
            CTSheetFormatPr target = (CTSheetFormatPr) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTSheetFormatPr = target == null ? null : target;
        }
        return cTSheetFormatPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetSheetFormatPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setSheetFormatPr(CTSheetFormatPr sheetFormatPr) {
        generatedSetterHelperImpl(sheetFormatPr, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSheetFormatPr addNewSheetFormatPr() {
        CTSheetFormatPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSheetFormatPr) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetSheetFormatPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public List<CTCols> getColsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorksheetImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTWorksheetImpl.this.getColsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorksheetImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTWorksheetImpl.this.setColsArray(((Integer) obj).intValue(), (CTCols) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorksheetImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTWorksheetImpl.this.insertNewCols(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorksheetImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTWorksheetImpl.this.removeCols(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorksheetImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTWorksheetImpl.this.sizeOfColsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTCols[] getColsArray() {
        return (CTCols[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTCols[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTCols getColsArray(int i) {
        CTCols target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCols) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public int sizeOfColsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setColsArray(CTCols[] colsArray) {
        check_orphaned();
        arraySetterHelper(colsArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setColsArray(int i, CTCols cols) {
        generatedSetterHelperImpl(cols, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTCols insertNewCols(int i) {
        CTCols target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCols) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTCols addNewCols() {
        CTCols target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCols) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void removeCols(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSheetData getSheetData() {
        CTSheetData cTSheetData;
        synchronized (monitor()) {
            check_orphaned();
            CTSheetData target = (CTSheetData) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTSheetData = target == null ? null : target;
        }
        return cTSheetData;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setSheetData(CTSheetData sheetData) {
        generatedSetterHelperImpl(sheetData, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSheetData addNewSheetData() {
        CTSheetData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSheetData) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSheetCalcPr getSheetCalcPr() {
        CTSheetCalcPr cTSheetCalcPr;
        synchronized (monitor()) {
            check_orphaned();
            CTSheetCalcPr target = (CTSheetCalcPr) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTSheetCalcPr = target == null ? null : target;
        }
        return cTSheetCalcPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetSheetCalcPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setSheetCalcPr(CTSheetCalcPr sheetCalcPr) {
        generatedSetterHelperImpl(sheetCalcPr, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSheetCalcPr addNewSheetCalcPr() {
        CTSheetCalcPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSheetCalcPr) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetSheetCalcPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSheetProtection getSheetProtection() {
        CTSheetProtection cTSheetProtection;
        synchronized (monitor()) {
            check_orphaned();
            CTSheetProtection target = (CTSheetProtection) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTSheetProtection = target == null ? null : target;
        }
        return cTSheetProtection;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetSheetProtection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setSheetProtection(CTSheetProtection sheetProtection) {
        generatedSetterHelperImpl(sheetProtection, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSheetProtection addNewSheetProtection() {
        CTSheetProtection target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSheetProtection) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetSheetProtection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTProtectedRanges getProtectedRanges() {
        CTProtectedRanges cTProtectedRanges;
        synchronized (monitor()) {
            check_orphaned();
            CTProtectedRanges target = (CTProtectedRanges) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTProtectedRanges = target == null ? null : target;
        }
        return cTProtectedRanges;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetProtectedRanges() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setProtectedRanges(CTProtectedRanges protectedRanges) {
        generatedSetterHelperImpl(protectedRanges, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTProtectedRanges addNewProtectedRanges() {
        CTProtectedRanges target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTProtectedRanges) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetProtectedRanges() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTScenarios getScenarios() {
        CTScenarios cTScenarios;
        synchronized (monitor()) {
            check_orphaned();
            CTScenarios target = get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTScenarios = target == null ? null : target;
        }
        return cTScenarios;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetScenarios() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setScenarios(CTScenarios scenarios) {
        generatedSetterHelperImpl(scenarios, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTScenarios addNewScenarios() {
        CTScenarios target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetScenarios() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTAutoFilter getAutoFilter() {
        CTAutoFilter cTAutoFilter;
        synchronized (monitor()) {
            check_orphaned();
            CTAutoFilter target = (CTAutoFilter) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTAutoFilter = target == null ? null : target;
        }
        return cTAutoFilter;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetAutoFilter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setAutoFilter(CTAutoFilter autoFilter) {
        generatedSetterHelperImpl(autoFilter, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTAutoFilter addNewAutoFilter() {
        CTAutoFilter target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAutoFilter) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetAutoFilter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSortState getSortState() {
        CTSortState cTSortState;
        synchronized (monitor()) {
            check_orphaned();
            CTSortState target = (CTSortState) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTSortState = target == null ? null : target;
        }
        return cTSortState;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetSortState() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setSortState(CTSortState sortState) {
        generatedSetterHelperImpl(sortState, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSortState addNewSortState() {
        CTSortState target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSortState) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetSortState() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTDataConsolidate getDataConsolidate() {
        CTDataConsolidate cTDataConsolidate;
        synchronized (monitor()) {
            check_orphaned();
            CTDataConsolidate target = (CTDataConsolidate) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTDataConsolidate = target == null ? null : target;
        }
        return cTDataConsolidate;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetDataConsolidate() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setDataConsolidate(CTDataConsolidate dataConsolidate) {
        generatedSetterHelperImpl(dataConsolidate, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTDataConsolidate addNewDataConsolidate() {
        CTDataConsolidate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDataConsolidate) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetDataConsolidate() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTCustomSheetViews getCustomSheetViews() {
        CTCustomSheetViews cTCustomSheetViews;
        synchronized (monitor()) {
            check_orphaned();
            CTCustomSheetViews target = (CTCustomSheetViews) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTCustomSheetViews = target == null ? null : target;
        }
        return cTCustomSheetViews;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetCustomSheetViews() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setCustomSheetViews(CTCustomSheetViews customSheetViews) {
        generatedSetterHelperImpl(customSheetViews, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTCustomSheetViews addNewCustomSheetViews() {
        CTCustomSheetViews target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomSheetViews) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetCustomSheetViews() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTMergeCells getMergeCells() {
        CTMergeCells cTMergeCells;
        synchronized (monitor()) {
            check_orphaned();
            CTMergeCells target = (CTMergeCells) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            cTMergeCells = target == null ? null : target;
        }
        return cTMergeCells;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetMergeCells() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setMergeCells(CTMergeCells mergeCells) {
        generatedSetterHelperImpl(mergeCells, PROPERTY_QNAME[14], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTMergeCells addNewMergeCells() {
        CTMergeCells target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMergeCells) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetMergeCells() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTPhoneticPr getPhoneticPr() {
        CTPhoneticPr cTPhoneticPr;
        synchronized (monitor()) {
            check_orphaned();
            CTPhoneticPr target = (CTPhoneticPr) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            cTPhoneticPr = target == null ? null : target;
        }
        return cTPhoneticPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetPhoneticPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setPhoneticPr(CTPhoneticPr phoneticPr) {
        generatedSetterHelperImpl(phoneticPr, PROPERTY_QNAME[15], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTPhoneticPr addNewPhoneticPr() {
        CTPhoneticPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPhoneticPr) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetPhoneticPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public List<CTConditionalFormatting> getConditionalFormattingList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorksheetImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTWorksheetImpl.this.getConditionalFormattingArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorksheetImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTWorksheetImpl.this.setConditionalFormattingArray(((Integer) obj).intValue(), (CTConditionalFormatting) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorksheetImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTWorksheetImpl.this.insertNewConditionalFormatting(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorksheetImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTWorksheetImpl.this.removeConditionalFormatting(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorksheetImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTWorksheetImpl.this.sizeOfConditionalFormattingArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTConditionalFormatting[] getConditionalFormattingArray() {
        return (CTConditionalFormatting[]) getXmlObjectArray(PROPERTY_QNAME[16], new CTConditionalFormatting[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTConditionalFormatting getConditionalFormattingArray(int i) {
        CTConditionalFormatting target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTConditionalFormatting) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public int sizeOfConditionalFormattingArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setConditionalFormattingArray(CTConditionalFormatting[] conditionalFormattingArray) {
        check_orphaned();
        arraySetterHelper(conditionalFormattingArray, PROPERTY_QNAME[16]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setConditionalFormattingArray(int i, CTConditionalFormatting conditionalFormatting) {
        generatedSetterHelperImpl(conditionalFormatting, PROPERTY_QNAME[16], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTConditionalFormatting insertNewConditionalFormatting(int i) {
        CTConditionalFormatting target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTConditionalFormatting) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTConditionalFormatting addNewConditionalFormatting() {
        CTConditionalFormatting target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTConditionalFormatting) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void removeConditionalFormatting(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTDataValidations getDataValidations() {
        CTDataValidations cTDataValidations;
        synchronized (monitor()) {
            check_orphaned();
            CTDataValidations target = (CTDataValidations) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            cTDataValidations = target == null ? null : target;
        }
        return cTDataValidations;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetDataValidations() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setDataValidations(CTDataValidations dataValidations) {
        generatedSetterHelperImpl(dataValidations, PROPERTY_QNAME[17], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTDataValidations addNewDataValidations() {
        CTDataValidations target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDataValidations) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetDataValidations() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTHyperlinks getHyperlinks() {
        CTHyperlinks cTHyperlinks;
        synchronized (monitor()) {
            check_orphaned();
            CTHyperlinks target = (CTHyperlinks) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            cTHyperlinks = target == null ? null : target;
        }
        return cTHyperlinks;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetHyperlinks() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setHyperlinks(CTHyperlinks hyperlinks) {
        generatedSetterHelperImpl(hyperlinks, PROPERTY_QNAME[18], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTHyperlinks addNewHyperlinks() {
        CTHyperlinks target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHyperlinks) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetHyperlinks() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTPrintOptions getPrintOptions() {
        CTPrintOptions cTPrintOptions;
        synchronized (monitor()) {
            check_orphaned();
            CTPrintOptions target = (CTPrintOptions) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            cTPrintOptions = target == null ? null : target;
        }
        return cTPrintOptions;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetPrintOptions() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[19]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setPrintOptions(CTPrintOptions printOptions) {
        generatedSetterHelperImpl(printOptions, PROPERTY_QNAME[19], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTPrintOptions addNewPrintOptions() {
        CTPrintOptions target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPrintOptions) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetPrintOptions() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTPageMargins getPageMargins() {
        CTPageMargins cTPageMargins;
        synchronized (monitor()) {
            check_orphaned();
            CTPageMargins target = (CTPageMargins) get_store().find_element_user(PROPERTY_QNAME[20], 0);
            cTPageMargins = target == null ? null : target;
        }
        return cTPageMargins;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetPageMargins() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[20]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setPageMargins(CTPageMargins pageMargins) {
        generatedSetterHelperImpl(pageMargins, PROPERTY_QNAME[20], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTPageMargins addNewPageMargins() {
        CTPageMargins target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageMargins) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetPageMargins() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTPageSetup getPageSetup() {
        CTPageSetup cTPageSetup;
        synchronized (monitor()) {
            check_orphaned();
            CTPageSetup target = (CTPageSetup) get_store().find_element_user(PROPERTY_QNAME[21], 0);
            cTPageSetup = target == null ? null : target;
        }
        return cTPageSetup;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetPageSetup() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[21]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setPageSetup(CTPageSetup pageSetup) {
        generatedSetterHelperImpl(pageSetup, PROPERTY_QNAME[21], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTPageSetup addNewPageSetup() {
        CTPageSetup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageSetup) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetPageSetup() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTHeaderFooter getHeaderFooter() {
        CTHeaderFooter cTHeaderFooter;
        synchronized (monitor()) {
            check_orphaned();
            CTHeaderFooter target = (CTHeaderFooter) get_store().find_element_user(PROPERTY_QNAME[22], 0);
            cTHeaderFooter = target == null ? null : target;
        }
        return cTHeaderFooter;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetHeaderFooter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[22]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setHeaderFooter(CTHeaderFooter headerFooter) {
        generatedSetterHelperImpl(headerFooter, PROPERTY_QNAME[22], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTHeaderFooter addNewHeaderFooter() {
        CTHeaderFooter target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHeaderFooter) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetHeaderFooter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTPageBreak getRowBreaks() {
        CTPageBreak cTPageBreak;
        synchronized (monitor()) {
            check_orphaned();
            CTPageBreak target = (CTPageBreak) get_store().find_element_user(PROPERTY_QNAME[23], 0);
            cTPageBreak = target == null ? null : target;
        }
        return cTPageBreak;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetRowBreaks() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[23]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setRowBreaks(CTPageBreak rowBreaks) {
        generatedSetterHelperImpl(rowBreaks, PROPERTY_QNAME[23], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTPageBreak addNewRowBreaks() {
        CTPageBreak target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageBreak) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetRowBreaks() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTPageBreak getColBreaks() {
        CTPageBreak cTPageBreak;
        synchronized (monitor()) {
            check_orphaned();
            CTPageBreak target = (CTPageBreak) get_store().find_element_user(PROPERTY_QNAME[24], 0);
            cTPageBreak = target == null ? null : target;
        }
        return cTPageBreak;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetColBreaks() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[24]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setColBreaks(CTPageBreak colBreaks) {
        generatedSetterHelperImpl(colBreaks, PROPERTY_QNAME[24], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTPageBreak addNewColBreaks() {
        CTPageBreak target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageBreak) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetColBreaks() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTCustomProperties getCustomProperties() {
        CTCustomProperties cTCustomProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTCustomProperties target = (CTCustomProperties) get_store().find_element_user(PROPERTY_QNAME[25], 0);
            cTCustomProperties = target == null ? null : target;
        }
        return cTCustomProperties;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetCustomProperties() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[25]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setCustomProperties(CTCustomProperties customProperties) {
        generatedSetterHelperImpl(customProperties, PROPERTY_QNAME[25], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTCustomProperties addNewCustomProperties() {
        CTCustomProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomProperties) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetCustomProperties() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTCellWatches getCellWatches() {
        CTCellWatches cTCellWatches;
        synchronized (monitor()) {
            check_orphaned();
            CTCellWatches target = (CTCellWatches) get_store().find_element_user(PROPERTY_QNAME[26], 0);
            cTCellWatches = target == null ? null : target;
        }
        return cTCellWatches;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetCellWatches() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[26]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setCellWatches(CTCellWatches cellWatches) {
        generatedSetterHelperImpl(cellWatches, PROPERTY_QNAME[26], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTCellWatches addNewCellWatches() {
        CTCellWatches target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCellWatches) get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetCellWatches() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTIgnoredErrors getIgnoredErrors() {
        CTIgnoredErrors cTIgnoredErrors;
        synchronized (monitor()) {
            check_orphaned();
            CTIgnoredErrors target = (CTIgnoredErrors) get_store().find_element_user(PROPERTY_QNAME[27], 0);
            cTIgnoredErrors = target == null ? null : target;
        }
        return cTIgnoredErrors;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetIgnoredErrors() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[27]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setIgnoredErrors(CTIgnoredErrors ignoredErrors) {
        generatedSetterHelperImpl(ignoredErrors, PROPERTY_QNAME[27], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTIgnoredErrors addNewIgnoredErrors() {
        CTIgnoredErrors target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTIgnoredErrors) get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetIgnoredErrors() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSmartTags getSmartTags() {
        CTSmartTags cTSmartTags;
        synchronized (monitor()) {
            check_orphaned();
            CTSmartTags target = get_store().find_element_user(PROPERTY_QNAME[28], 0);
            cTSmartTags = target == null ? null : target;
        }
        return cTSmartTags;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetSmartTags() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[28]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setSmartTags(CTSmartTags smartTags) {
        generatedSetterHelperImpl(smartTags, PROPERTY_QNAME[28], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSmartTags addNewSmartTags() {
        CTSmartTags target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetSmartTags() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTDrawing getDrawing() {
        CTDrawing cTDrawing;
        synchronized (monitor()) {
            check_orphaned();
            CTDrawing target = (CTDrawing) get_store().find_element_user(PROPERTY_QNAME[29], 0);
            cTDrawing = target == null ? null : target;
        }
        return cTDrawing;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetDrawing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[29]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setDrawing(CTDrawing drawing) {
        generatedSetterHelperImpl(drawing, PROPERTY_QNAME[29], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTDrawing addNewDrawing() {
        CTDrawing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDrawing) get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetDrawing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTLegacyDrawing getLegacyDrawing() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            CTLegacyDrawing target = (CTLegacyDrawing) get_store().find_element_user(PROPERTY_QNAME[30], 0);
            cTLegacyDrawing = target == null ? null : target;
        }
        return cTLegacyDrawing;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetLegacyDrawing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[30]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setLegacyDrawing(CTLegacyDrawing legacyDrawing) {
        generatedSetterHelperImpl(legacyDrawing, PROPERTY_QNAME[30], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTLegacyDrawing addNewLegacyDrawing() {
        CTLegacyDrawing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLegacyDrawing) get_store().add_element_user(PROPERTY_QNAME[30]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetLegacyDrawing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[30], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTLegacyDrawing getLegacyDrawingHF() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            CTLegacyDrawing target = (CTLegacyDrawing) get_store().find_element_user(PROPERTY_QNAME[31], 0);
            cTLegacyDrawing = target == null ? null : target;
        }
        return cTLegacyDrawing;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetLegacyDrawingHF() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[31]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setLegacyDrawingHF(CTLegacyDrawing legacyDrawingHF) {
        generatedSetterHelperImpl(legacyDrawingHF, PROPERTY_QNAME[31], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTLegacyDrawing addNewLegacyDrawingHF() {
        CTLegacyDrawing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLegacyDrawing) get_store().add_element_user(PROPERTY_QNAME[31]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetLegacyDrawingHF() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[31], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTDrawingHF getDrawingHF() {
        CTDrawingHF cTDrawingHF;
        synchronized (monitor()) {
            check_orphaned();
            CTDrawingHF target = get_store().find_element_user(PROPERTY_QNAME[32], 0);
            cTDrawingHF = target == null ? null : target;
        }
        return cTDrawingHF;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetDrawingHF() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[32]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setDrawingHF(CTDrawingHF drawingHF) {
        generatedSetterHelperImpl(drawingHF, PROPERTY_QNAME[32], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTDrawingHF addNewDrawingHF() {
        CTDrawingHF target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[32]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetDrawingHF() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[32], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSheetBackgroundPicture getPicture() {
        CTSheetBackgroundPicture cTSheetBackgroundPicture;
        synchronized (monitor()) {
            check_orphaned();
            CTSheetBackgroundPicture target = (CTSheetBackgroundPicture) get_store().find_element_user(PROPERTY_QNAME[33], 0);
            cTSheetBackgroundPicture = target == null ? null : target;
        }
        return cTSheetBackgroundPicture;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetPicture() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[33]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setPicture(CTSheetBackgroundPicture picture) {
        generatedSetterHelperImpl(picture, PROPERTY_QNAME[33], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTSheetBackgroundPicture addNewPicture() {
        CTSheetBackgroundPicture target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSheetBackgroundPicture) get_store().add_element_user(PROPERTY_QNAME[33]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetPicture() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[33], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTOleObjects getOleObjects() {
        CTOleObjects cTOleObjects;
        synchronized (monitor()) {
            check_orphaned();
            CTOleObjects target = (CTOleObjects) get_store().find_element_user(PROPERTY_QNAME[34], 0);
            cTOleObjects = target == null ? null : target;
        }
        return cTOleObjects;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetOleObjects() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[34]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setOleObjects(CTOleObjects oleObjects) {
        generatedSetterHelperImpl(oleObjects, PROPERTY_QNAME[34], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTOleObjects addNewOleObjects() {
        CTOleObjects target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOleObjects) get_store().add_element_user(PROPERTY_QNAME[34]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetOleObjects() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[34], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTControls getControls() {
        CTControls cTControls;
        synchronized (monitor()) {
            check_orphaned();
            CTControls target = (CTControls) get_store().find_element_user(PROPERTY_QNAME[35], 0);
            cTControls = target == null ? null : target;
        }
        return cTControls;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetControls() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[35]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setControls(CTControls controls) {
        generatedSetterHelperImpl(controls, PROPERTY_QNAME[35], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTControls addNewControls() {
        CTControls target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTControls) get_store().add_element_user(PROPERTY_QNAME[35]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetControls() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[35], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTWebPublishItems getWebPublishItems() {
        CTWebPublishItems cTWebPublishItems;
        synchronized (monitor()) {
            check_orphaned();
            CTWebPublishItems target = get_store().find_element_user(PROPERTY_QNAME[36], 0);
            cTWebPublishItems = target == null ? null : target;
        }
        return cTWebPublishItems;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetWebPublishItems() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[36]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setWebPublishItems(CTWebPublishItems webPublishItems) {
        generatedSetterHelperImpl(webPublishItems, PROPERTY_QNAME[36], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTWebPublishItems addNewWebPublishItems() {
        CTWebPublishItems target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[36]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetWebPublishItems() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[36], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTTableParts getTableParts() {
        CTTableParts cTTableParts;
        synchronized (monitor()) {
            check_orphaned();
            CTTableParts target = (CTTableParts) get_store().find_element_user(PROPERTY_QNAME[37], 0);
            cTTableParts = target == null ? null : target;
        }
        return cTTableParts;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetTableParts() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[37]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setTableParts(CTTableParts tableParts) {
        generatedSetterHelperImpl(tableParts, PROPERTY_QNAME[37], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTTableParts addNewTableParts() {
        CTTableParts target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTableParts) get_store().add_element_user(PROPERTY_QNAME[37]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetTableParts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[37], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[38], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[38]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[38], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[38]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[38], 0);
        }
    }
}
