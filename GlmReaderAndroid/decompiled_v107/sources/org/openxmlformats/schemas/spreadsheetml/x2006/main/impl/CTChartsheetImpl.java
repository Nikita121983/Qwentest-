package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCsPageSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawingHF;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLegacyDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetBackgroundPicture;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItems;

/* loaded from: classes12.dex */
public class CTChartsheetImpl extends XmlComplexContentImpl implements CTChartsheet {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "sheetPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetViews"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetProtection"), new QName(XSSFRelation.NS_SPREADSHEETML, "customSheetViews"), new QName(XSSFRelation.NS_SPREADSHEETML, "pageMargins"), new QName(XSSFRelation.NS_SPREADSHEETML, "pageSetup"), new QName(XSSFRelation.NS_SPREADSHEETML, "headerFooter"), new QName(XSSFRelation.NS_SPREADSHEETML, "drawing"), new QName(XSSFRelation.NS_SPREADSHEETML, "legacyDrawing"), new QName(XSSFRelation.NS_SPREADSHEETML, "legacyDrawingHF"), new QName(XSSFRelation.NS_SPREADSHEETML, "drawingHF"), new QName(XSSFRelation.NS_SPREADSHEETML, "picture"), new QName(XSSFRelation.NS_SPREADSHEETML, "webPublishItems"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTChartsheetImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTChartsheetPr getSheetPr() {
        CTChartsheetPr cTChartsheetPr;
        synchronized (monitor()) {
            check_orphaned();
            CTChartsheetPr target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTChartsheetPr = target == null ? null : target;
        }
        return cTChartsheetPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public boolean isSetSheetPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void setSheetPr(CTChartsheetPr sheetPr) {
        generatedSetterHelperImpl(sheetPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTChartsheetPr addNewSheetPr() {
        CTChartsheetPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void unsetSheetPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTChartsheetViews getSheetViews() {
        CTChartsheetViews cTChartsheetViews;
        synchronized (monitor()) {
            check_orphaned();
            CTChartsheetViews target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTChartsheetViews = target == null ? null : target;
        }
        return cTChartsheetViews;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void setSheetViews(CTChartsheetViews sheetViews) {
        generatedSetterHelperImpl(sheetViews, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTChartsheetViews addNewSheetViews() {
        CTChartsheetViews target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTChartsheetProtection getSheetProtection() {
        CTChartsheetProtection cTChartsheetProtection;
        synchronized (monitor()) {
            check_orphaned();
            CTChartsheetProtection target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTChartsheetProtection = target == null ? null : target;
        }
        return cTChartsheetProtection;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public boolean isSetSheetProtection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void setSheetProtection(CTChartsheetProtection sheetProtection) {
        generatedSetterHelperImpl(sheetProtection, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTChartsheetProtection addNewSheetProtection() {
        CTChartsheetProtection target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void unsetSheetProtection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTCustomChartsheetViews getCustomSheetViews() {
        CTCustomChartsheetViews cTCustomChartsheetViews;
        synchronized (monitor()) {
            check_orphaned();
            CTCustomChartsheetViews target = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTCustomChartsheetViews = target == null ? null : target;
        }
        return cTCustomChartsheetViews;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public boolean isSetCustomSheetViews() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void setCustomSheetViews(CTCustomChartsheetViews customSheetViews) {
        generatedSetterHelperImpl(customSheetViews, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTCustomChartsheetViews addNewCustomSheetViews() {
        CTCustomChartsheetViews target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void unsetCustomSheetViews() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTPageMargins getPageMargins() {
        CTPageMargins cTPageMargins;
        synchronized (monitor()) {
            check_orphaned();
            CTPageMargins target = (CTPageMargins) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTPageMargins = target == null ? null : target;
        }
        return cTPageMargins;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public boolean isSetPageMargins() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void setPageMargins(CTPageMargins pageMargins) {
        generatedSetterHelperImpl(pageMargins, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTPageMargins addNewPageMargins() {
        CTPageMargins target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageMargins) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void unsetPageMargins() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTCsPageSetup getPageSetup() {
        CTCsPageSetup cTCsPageSetup;
        synchronized (monitor()) {
            check_orphaned();
            CTCsPageSetup target = get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTCsPageSetup = target == null ? null : target;
        }
        return cTCsPageSetup;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public boolean isSetPageSetup() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void setPageSetup(CTCsPageSetup pageSetup) {
        generatedSetterHelperImpl(pageSetup, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTCsPageSetup addNewPageSetup() {
        CTCsPageSetup target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void unsetPageSetup() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTHeaderFooter getHeaderFooter() {
        CTHeaderFooter cTHeaderFooter;
        synchronized (monitor()) {
            check_orphaned();
            CTHeaderFooter target = (CTHeaderFooter) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTHeaderFooter = target == null ? null : target;
        }
        return cTHeaderFooter;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public boolean isSetHeaderFooter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void setHeaderFooter(CTHeaderFooter headerFooter) {
        generatedSetterHelperImpl(headerFooter, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTHeaderFooter addNewHeaderFooter() {
        CTHeaderFooter target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHeaderFooter) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void unsetHeaderFooter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTDrawing getDrawing() {
        CTDrawing cTDrawing;
        synchronized (monitor()) {
            check_orphaned();
            CTDrawing target = (CTDrawing) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTDrawing = target == null ? null : target;
        }
        return cTDrawing;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void setDrawing(CTDrawing drawing) {
        generatedSetterHelperImpl(drawing, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTDrawing addNewDrawing() {
        CTDrawing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDrawing) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTLegacyDrawing getLegacyDrawing() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            CTLegacyDrawing target = (CTLegacyDrawing) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTLegacyDrawing = target == null ? null : target;
        }
        return cTLegacyDrawing;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public boolean isSetLegacyDrawing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void setLegacyDrawing(CTLegacyDrawing legacyDrawing) {
        generatedSetterHelperImpl(legacyDrawing, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTLegacyDrawing addNewLegacyDrawing() {
        CTLegacyDrawing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLegacyDrawing) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void unsetLegacyDrawing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTLegacyDrawing getLegacyDrawingHF() {
        CTLegacyDrawing cTLegacyDrawing;
        synchronized (monitor()) {
            check_orphaned();
            CTLegacyDrawing target = (CTLegacyDrawing) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTLegacyDrawing = target == null ? null : target;
        }
        return cTLegacyDrawing;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public boolean isSetLegacyDrawingHF() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void setLegacyDrawingHF(CTLegacyDrawing legacyDrawingHF) {
        generatedSetterHelperImpl(legacyDrawingHF, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTLegacyDrawing addNewLegacyDrawingHF() {
        CTLegacyDrawing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLegacyDrawing) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void unsetLegacyDrawingHF() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTDrawingHF getDrawingHF() {
        CTDrawingHF cTDrawingHF;
        synchronized (monitor()) {
            check_orphaned();
            CTDrawingHF target = get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTDrawingHF = target == null ? null : target;
        }
        return cTDrawingHF;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public boolean isSetDrawingHF() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void setDrawingHF(CTDrawingHF drawingHF) {
        generatedSetterHelperImpl(drawingHF, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTDrawingHF addNewDrawingHF() {
        CTDrawingHF target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void unsetDrawingHF() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTSheetBackgroundPicture getPicture() {
        CTSheetBackgroundPicture cTSheetBackgroundPicture;
        synchronized (monitor()) {
            check_orphaned();
            CTSheetBackgroundPicture target = (CTSheetBackgroundPicture) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTSheetBackgroundPicture = target == null ? null : target;
        }
        return cTSheetBackgroundPicture;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public boolean isSetPicture() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void setPicture(CTSheetBackgroundPicture picture) {
        generatedSetterHelperImpl(picture, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTSheetBackgroundPicture addNewPicture() {
        CTSheetBackgroundPicture target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSheetBackgroundPicture) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void unsetPicture() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTWebPublishItems getWebPublishItems() {
        CTWebPublishItems cTWebPublishItems;
        synchronized (monitor()) {
            check_orphaned();
            CTWebPublishItems target = get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTWebPublishItems = target == null ? null : target;
        }
        return cTWebPublishItems;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public boolean isSetWebPublishItems() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void setWebPublishItems(CTWebPublishItems webPublishItems) {
        generatedSetterHelperImpl(webPublishItems, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTWebPublishItems addNewWebPublishItems() {
        CTWebPublishItems target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void unsetWebPublishItems() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }
}
