package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTControls;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawingHF;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLegacyDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetFormatPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetViews;

/* loaded from: classes12.dex */
public class CTDialogsheetImpl extends XmlComplexContentImpl implements CTDialogsheet {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "sheetPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetViews"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetFormatPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetProtection"), new QName(XSSFRelation.NS_SPREADSHEETML, "customSheetViews"), new QName(XSSFRelation.NS_SPREADSHEETML, "printOptions"), new QName(XSSFRelation.NS_SPREADSHEETML, "pageMargins"), new QName(XSSFRelation.NS_SPREADSHEETML, "pageSetup"), new QName(XSSFRelation.NS_SPREADSHEETML, "headerFooter"), new QName(XSSFRelation.NS_SPREADSHEETML, "drawing"), new QName(XSSFRelation.NS_SPREADSHEETML, "legacyDrawing"), new QName(XSSFRelation.NS_SPREADSHEETML, "legacyDrawingHF"), new QName(XSSFRelation.NS_SPREADSHEETML, "drawingHF"), new QName(XSSFRelation.NS_SPREADSHEETML, "oleObjects"), new QName(XSSFRelation.NS_SPREADSHEETML, "controls"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTDialogsheetImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTSheetPr getSheetPr() {
        CTSheetPr cTSheetPr;
        synchronized (monitor()) {
            check_orphaned();
            CTSheetPr target = (CTSheetPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTSheetPr = target == null ? null : target;
        }
        return cTSheetPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public boolean isSetSheetPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void setSheetPr(CTSheetPr sheetPr) {
        generatedSetterHelperImpl(sheetPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTSheetPr addNewSheetPr() {
        CTSheetPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSheetPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void unsetSheetPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTSheetViews getSheetViews() {
        CTSheetViews cTSheetViews;
        synchronized (monitor()) {
            check_orphaned();
            CTSheetViews target = (CTSheetViews) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTSheetViews = target == null ? null : target;
        }
        return cTSheetViews;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public boolean isSetSheetViews() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void setSheetViews(CTSheetViews sheetViews) {
        generatedSetterHelperImpl(sheetViews, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTSheetViews addNewSheetViews() {
        CTSheetViews target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSheetViews) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void unsetSheetViews() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTSheetFormatPr getSheetFormatPr() {
        CTSheetFormatPr cTSheetFormatPr;
        synchronized (monitor()) {
            check_orphaned();
            CTSheetFormatPr target = (CTSheetFormatPr) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTSheetFormatPr = target == null ? null : target;
        }
        return cTSheetFormatPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public boolean isSetSheetFormatPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void setSheetFormatPr(CTSheetFormatPr sheetFormatPr) {
        generatedSetterHelperImpl(sheetFormatPr, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTSheetFormatPr addNewSheetFormatPr() {
        CTSheetFormatPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSheetFormatPr) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void unsetSheetFormatPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTSheetProtection getSheetProtection() {
        CTSheetProtection cTSheetProtection;
        synchronized (monitor()) {
            check_orphaned();
            CTSheetProtection target = (CTSheetProtection) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTSheetProtection = target == null ? null : target;
        }
        return cTSheetProtection;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public boolean isSetSheetProtection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void setSheetProtection(CTSheetProtection sheetProtection) {
        generatedSetterHelperImpl(sheetProtection, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTSheetProtection addNewSheetProtection() {
        CTSheetProtection target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSheetProtection) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void unsetSheetProtection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTCustomSheetViews getCustomSheetViews() {
        CTCustomSheetViews cTCustomSheetViews;
        synchronized (monitor()) {
            check_orphaned();
            CTCustomSheetViews target = (CTCustomSheetViews) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTCustomSheetViews = target == null ? null : target;
        }
        return cTCustomSheetViews;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public boolean isSetCustomSheetViews() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void setCustomSheetViews(CTCustomSheetViews customSheetViews) {
        generatedSetterHelperImpl(customSheetViews, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTCustomSheetViews addNewCustomSheetViews() {
        CTCustomSheetViews target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomSheetViews) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void unsetCustomSheetViews() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTPrintOptions getPrintOptions() {
        CTPrintOptions cTPrintOptions;
        synchronized (monitor()) {
            check_orphaned();
            CTPrintOptions target = (CTPrintOptions) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTPrintOptions = target == null ? null : target;
        }
        return cTPrintOptions;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public boolean isSetPrintOptions() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void setPrintOptions(CTPrintOptions printOptions) {
        generatedSetterHelperImpl(printOptions, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTPrintOptions addNewPrintOptions() {
        CTPrintOptions target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPrintOptions) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void unsetPrintOptions() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTPageMargins getPageMargins() {
        CTPageMargins cTPageMargins;
        synchronized (monitor()) {
            check_orphaned();
            CTPageMargins target = (CTPageMargins) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTPageMargins = target == null ? null : target;
        }
        return cTPageMargins;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public boolean isSetPageMargins() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void setPageMargins(CTPageMargins pageMargins) {
        generatedSetterHelperImpl(pageMargins, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTPageMargins addNewPageMargins() {
        CTPageMargins target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageMargins) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void unsetPageMargins() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTPageSetup getPageSetup() {
        CTPageSetup cTPageSetup;
        synchronized (monitor()) {
            check_orphaned();
            CTPageSetup target = (CTPageSetup) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTPageSetup = target == null ? null : target;
        }
        return cTPageSetup;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public boolean isSetPageSetup() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void setPageSetup(CTPageSetup pageSetup) {
        generatedSetterHelperImpl(pageSetup, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTPageSetup addNewPageSetup() {
        CTPageSetup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageSetup) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void unsetPageSetup() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTHeaderFooter getHeaderFooter() {
        CTHeaderFooter cTHeaderFooter;
        synchronized (monitor()) {
            check_orphaned();
            CTHeaderFooter target = (CTHeaderFooter) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTHeaderFooter = target == null ? null : target;
        }
        return cTHeaderFooter;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public boolean isSetHeaderFooter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void setHeaderFooter(CTHeaderFooter headerFooter) {
        generatedSetterHelperImpl(headerFooter, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTHeaderFooter addNewHeaderFooter() {
        CTHeaderFooter target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHeaderFooter) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void unsetHeaderFooter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTDrawing getDrawing() {
        CTDrawing cTDrawing;
        synchronized (monitor()) {
            check_orphaned();
            CTDrawing target = (CTDrawing) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTDrawing = target == null ? null : target;
        }
        return cTDrawing;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public boolean isSetDrawing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void setDrawing(CTDrawing drawing) {
        generatedSetterHelperImpl(drawing, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTDrawing addNewDrawing() {
        CTDrawing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDrawing) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void unsetDrawing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTLegacyDrawing getLegacyDrawing() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            CTLegacyDrawing target = (CTLegacyDrawing) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTLegacyDrawing = target == null ? null : target;
        }
        return cTLegacyDrawing;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public boolean isSetLegacyDrawing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void setLegacyDrawing(CTLegacyDrawing legacyDrawing) {
        generatedSetterHelperImpl(legacyDrawing, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTLegacyDrawing addNewLegacyDrawing() {
        CTLegacyDrawing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLegacyDrawing) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void unsetLegacyDrawing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTLegacyDrawing getLegacyDrawingHF() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            CTLegacyDrawing target = (CTLegacyDrawing) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTLegacyDrawing = target == null ? null : target;
        }
        return cTLegacyDrawing;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public boolean isSetLegacyDrawingHF() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void setLegacyDrawingHF(CTLegacyDrawing legacyDrawingHF) {
        generatedSetterHelperImpl(legacyDrawingHF, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTLegacyDrawing addNewLegacyDrawingHF() {
        CTLegacyDrawing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLegacyDrawing) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void unsetLegacyDrawingHF() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTDrawingHF getDrawingHF() {
        CTDrawingHF cTDrawingHF;
        synchronized (monitor()) {
            check_orphaned();
            CTDrawingHF target = get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTDrawingHF = target == null ? null : target;
        }
        return cTDrawingHF;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public boolean isSetDrawingHF() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void setDrawingHF(CTDrawingHF drawingHF) {
        generatedSetterHelperImpl(drawingHF, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTDrawingHF addNewDrawingHF() {
        CTDrawingHF target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void unsetDrawingHF() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTOleObjects getOleObjects() {
        CTOleObjects cTOleObjects;
        synchronized (monitor()) {
            check_orphaned();
            CTOleObjects target = (CTOleObjects) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTOleObjects = target == null ? null : target;
        }
        return cTOleObjects;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public boolean isSetOleObjects() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void setOleObjects(CTOleObjects oleObjects) {
        generatedSetterHelperImpl(oleObjects, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTOleObjects addNewOleObjects() {
        CTOleObjects target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOleObjects) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void unsetOleObjects() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTControls getControls() {
        CTControls cTControls;
        synchronized (monitor()) {
            check_orphaned();
            CTControls target = (CTControls) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            cTControls = target == null ? null : target;
        }
        return cTControls;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public boolean isSetControls() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void setControls(CTControls controls) {
        generatedSetterHelperImpl(controls, PROPERTY_QNAME[14], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTControls addNewControls() {
        CTControls target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTControls) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void unsetControls() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[15], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }
}
