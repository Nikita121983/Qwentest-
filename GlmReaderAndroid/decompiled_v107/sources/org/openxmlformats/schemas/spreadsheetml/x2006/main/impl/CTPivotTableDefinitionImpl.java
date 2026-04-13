package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartFormats;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColHierarchiesUsage;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColItems;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConditionalFormats;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFormats;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLocation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotFilters;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotHierarchies;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRowFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRowHierarchiesUsage;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRowItems;

/* loaded from: classes12.dex */
public class CTPivotTableDefinitionImpl extends XmlComplexContentImpl implements CTPivotTableDefinition {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "location"), new QName(XSSFRelation.NS_SPREADSHEETML, "pivotFields"), new QName(XSSFRelation.NS_SPREADSHEETML, "rowFields"), new QName(XSSFRelation.NS_SPREADSHEETML, "rowItems"), new QName(XSSFRelation.NS_SPREADSHEETML, "colFields"), new QName(XSSFRelation.NS_SPREADSHEETML, "colItems"), new QName(XSSFRelation.NS_SPREADSHEETML, "pageFields"), new QName(XSSFRelation.NS_SPREADSHEETML, "dataFields"), new QName(XSSFRelation.NS_SPREADSHEETML, "formats"), new QName(XSSFRelation.NS_SPREADSHEETML, "conditionalFormats"), new QName(XSSFRelation.NS_SPREADSHEETML, "chartFormats"), new QName(XSSFRelation.NS_SPREADSHEETML, "pivotHierarchies"), new QName(XSSFRelation.NS_SPREADSHEETML, "pivotTableStyleInfo"), new QName(XSSFRelation.NS_SPREADSHEETML, "filters"), new QName(XSSFRelation.NS_SPREADSHEETML, "rowHierarchiesUsage"), new QName(XSSFRelation.NS_SPREADSHEETML, "colHierarchiesUsage"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst"), new QName("", "name"), new QName("", "cacheId"), new QName("", "dataOnRows"), new QName("", "dataPosition"), new QName("", "autoFormatId"), new QName("", "applyNumberFormats"), new QName("", "applyBorderFormats"), new QName("", "applyFontFormats"), new QName("", "applyPatternFormats"), new QName("", "applyAlignmentFormats"), new QName("", "applyWidthHeightFormats"), new QName("", "dataCaption"), new QName("", "grandTotalCaption"), new QName("", "errorCaption"), new QName("", "showError"), new QName("", "missingCaption"), new QName("", "showMissing"), new QName("", "pageStyle"), new QName("", "pivotTableStyle"), new QName("", "vacatedStyle"), new QName("", "tag"), new QName("", "updatedVersion"), new QName("", "minRefreshableVersion"), new QName("", "asteriskTotals"), new QName("", "showItems"), new QName("", "editData"), new QName("", "disableFieldList"), new QName("", "showCalcMbrs"), new QName("", "visualTotals"), new QName("", "showMultipleLabel"), new QName("", "showDataDropDown"), new QName("", "showDrill"), new QName("", "printDrill"), new QName("", "showMemberPropertyTips"), new QName("", "showDataTips"), new QName("", "enableWizard"), new QName("", "enableDrill"), new QName("", "enableFieldProperties"), new QName("", "preserveFormatting"), new QName("", "useAutoFormatting"), new QName("", "pageWrap"), new QName("", "pageOverThenDown"), new QName("", "subtotalHiddenItems"), new QName("", "rowGrandTotals"), new QName("", "colGrandTotals"), new QName("", "fieldPrintTitles"), new QName("", "itemPrintTitles"), new QName("", "mergeItem"), new QName("", "showDropZones"), new QName("", "createdVersion"), new QName("", "indent"), new QName("", "showEmptyRow"), new QName("", "showEmptyCol"), new QName("", "showHeaders"), new QName("", "compact"), new QName("", "outline"), new QName("", "outlineData"), new QName("", "compactData"), new QName("", "published"), new QName("", "gridDropZones"), new QName("", "immersive"), new QName("", "multipleFieldFilters"), new QName("", "chartFormat"), new QName("", "rowHeaderCaption"), new QName("", "colHeaderCaption"), new QName("", "fieldListSortAscending"), new QName("", "mdxSubqueries"), new QName("", "customListSort")};
    private static final long serialVersionUID = 1;

    public CTPivotTableDefinitionImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTLocation getLocation() {
        CTLocation cTLocation;
        synchronized (monitor()) {
            check_orphaned();
            CTLocation target = (CTLocation) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTLocation = target == null ? null : target;
        }
        return cTLocation;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setLocation(CTLocation location) {
        generatedSetterHelperImpl(location, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTLocation addNewLocation() {
        CTLocation target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLocation) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTPivotFields getPivotFields() {
        CTPivotFields cTPivotFields;
        synchronized (monitor()) {
            check_orphaned();
            CTPivotFields target = (CTPivotFields) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTPivotFields = target == null ? null : target;
        }
        return cTPivotFields;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetPivotFields() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setPivotFields(CTPivotFields pivotFields) {
        generatedSetterHelperImpl(pivotFields, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTPivotFields addNewPivotFields() {
        CTPivotFields target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPivotFields) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetPivotFields() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTRowFields getRowFields() {
        CTRowFields cTRowFields;
        synchronized (monitor()) {
            check_orphaned();
            CTRowFields target = (CTRowFields) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTRowFields = target == null ? null : target;
        }
        return cTRowFields;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetRowFields() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setRowFields(CTRowFields rowFields) {
        generatedSetterHelperImpl(rowFields, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTRowFields addNewRowFields() {
        CTRowFields target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRowFields) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetRowFields() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTRowItems getRowItems() {
        CTRowItems cTRowItems;
        synchronized (monitor()) {
            check_orphaned();
            CTRowItems target = (CTRowItems) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTRowItems = target == null ? null : target;
        }
        return cTRowItems;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetRowItems() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setRowItems(CTRowItems rowItems) {
        generatedSetterHelperImpl(rowItems, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTRowItems addNewRowItems() {
        CTRowItems target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRowItems) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetRowItems() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTColFields getColFields() {
        CTColFields cTColFields;
        synchronized (monitor()) {
            check_orphaned();
            CTColFields target = (CTColFields) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTColFields = target == null ? null : target;
        }
        return cTColFields;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetColFields() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setColFields(CTColFields colFields) {
        generatedSetterHelperImpl(colFields, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTColFields addNewColFields() {
        CTColFields target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColFields) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetColFields() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTColItems getColItems() {
        CTColItems cTColItems;
        synchronized (monitor()) {
            check_orphaned();
            CTColItems target = (CTColItems) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTColItems = target == null ? null : target;
        }
        return cTColItems;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetColItems() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setColItems(CTColItems colItems) {
        generatedSetterHelperImpl(colItems, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTColItems addNewColItems() {
        CTColItems target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColItems) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetColItems() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTPageFields getPageFields() {
        CTPageFields cTPageFields;
        synchronized (monitor()) {
            check_orphaned();
            CTPageFields target = (CTPageFields) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTPageFields = target == null ? null : target;
        }
        return cTPageFields;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetPageFields() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setPageFields(CTPageFields pageFields) {
        generatedSetterHelperImpl(pageFields, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTPageFields addNewPageFields() {
        CTPageFields target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageFields) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetPageFields() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTDataFields getDataFields() {
        CTDataFields cTDataFields;
        synchronized (monitor()) {
            check_orphaned();
            CTDataFields target = (CTDataFields) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTDataFields = target == null ? null : target;
        }
        return cTDataFields;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetDataFields() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setDataFields(CTDataFields dataFields) {
        generatedSetterHelperImpl(dataFields, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTDataFields addNewDataFields() {
        CTDataFields target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDataFields) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetDataFields() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTFormats getFormats() {
        CTFormats cTFormats;
        synchronized (monitor()) {
            check_orphaned();
            CTFormats target = (CTFormats) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTFormats = target == null ? null : target;
        }
        return cTFormats;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetFormats() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setFormats(CTFormats formats) {
        generatedSetterHelperImpl(formats, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTFormats addNewFormats() {
        CTFormats target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFormats) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetFormats() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTConditionalFormats getConditionalFormats() {
        CTConditionalFormats cTConditionalFormats;
        synchronized (monitor()) {
            check_orphaned();
            CTConditionalFormats target = (CTConditionalFormats) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTConditionalFormats = target == null ? null : target;
        }
        return cTConditionalFormats;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetConditionalFormats() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setConditionalFormats(CTConditionalFormats conditionalFormats) {
        generatedSetterHelperImpl(conditionalFormats, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTConditionalFormats addNewConditionalFormats() {
        CTConditionalFormats target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTConditionalFormats) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetConditionalFormats() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTChartFormats getChartFormats() {
        CTChartFormats cTChartFormats;
        synchronized (monitor()) {
            check_orphaned();
            CTChartFormats target = (CTChartFormats) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTChartFormats = target == null ? null : target;
        }
        return cTChartFormats;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetChartFormats() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setChartFormats(CTChartFormats chartFormats) {
        generatedSetterHelperImpl(chartFormats, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTChartFormats addNewChartFormats() {
        CTChartFormats target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTChartFormats) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetChartFormats() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTPivotHierarchies getPivotHierarchies() {
        CTPivotHierarchies cTPivotHierarchies;
        synchronized (monitor()) {
            check_orphaned();
            CTPivotHierarchies target = (CTPivotHierarchies) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTPivotHierarchies = target == null ? null : target;
        }
        return cTPivotHierarchies;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetPivotHierarchies() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setPivotHierarchies(CTPivotHierarchies pivotHierarchies) {
        generatedSetterHelperImpl(pivotHierarchies, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTPivotHierarchies addNewPivotHierarchies() {
        CTPivotHierarchies target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPivotHierarchies) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetPivotHierarchies() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTPivotTableStyle getPivotTableStyleInfo() {
        CTPivotTableStyle cTPivotTableStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTPivotTableStyle target = (CTPivotTableStyle) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTPivotTableStyle = target == null ? null : target;
        }
        return cTPivotTableStyle;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetPivotTableStyleInfo() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setPivotTableStyleInfo(CTPivotTableStyle pivotTableStyleInfo) {
        generatedSetterHelperImpl(pivotTableStyleInfo, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTPivotTableStyle addNewPivotTableStyleInfo() {
        CTPivotTableStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPivotTableStyle) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetPivotTableStyleInfo() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTPivotFilters getFilters() {
        CTPivotFilters cTPivotFilters;
        synchronized (monitor()) {
            check_orphaned();
            CTPivotFilters target = (CTPivotFilters) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTPivotFilters = target == null ? null : target;
        }
        return cTPivotFilters;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetFilters() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setFilters(CTPivotFilters filters) {
        generatedSetterHelperImpl(filters, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTPivotFilters addNewFilters() {
        CTPivotFilters target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPivotFilters) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetFilters() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTRowHierarchiesUsage getRowHierarchiesUsage() {
        CTRowHierarchiesUsage cTRowHierarchiesUsage;
        synchronized (monitor()) {
            check_orphaned();
            CTRowHierarchiesUsage target = (CTRowHierarchiesUsage) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            cTRowHierarchiesUsage = target == null ? null : target;
        }
        return cTRowHierarchiesUsage;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetRowHierarchiesUsage() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setRowHierarchiesUsage(CTRowHierarchiesUsage rowHierarchiesUsage) {
        generatedSetterHelperImpl(rowHierarchiesUsage, PROPERTY_QNAME[14], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTRowHierarchiesUsage addNewRowHierarchiesUsage() {
        CTRowHierarchiesUsage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRowHierarchiesUsage) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetRowHierarchiesUsage() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTColHierarchiesUsage getColHierarchiesUsage() {
        CTColHierarchiesUsage cTColHierarchiesUsage;
        synchronized (monitor()) {
            check_orphaned();
            CTColHierarchiesUsage target = (CTColHierarchiesUsage) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            cTColHierarchiesUsage = target == null ? null : target;
        }
        return cTColHierarchiesUsage;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetColHierarchiesUsage() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setColHierarchiesUsage(CTColHierarchiesUsage colHierarchiesUsage) {
        generatedSetterHelperImpl(colHierarchiesUsage, PROPERTY_QNAME[15], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTColHierarchiesUsage addNewColHierarchiesUsage() {
        CTColHierarchiesUsage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColHierarchiesUsage) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetColHierarchiesUsage() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[16], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public STXstring xgetName() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetName(STXstring name) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.set(name);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public long getCacheId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlUnsignedInt xgetCacheId() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setCacheId(long cacheId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.setLongValue(cacheId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetCacheId(XmlUnsignedInt cacheId) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.set(cacheId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getDataOnRows() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[19]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetDataOnRows() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[19]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetDataOnRows() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[19]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setDataOnRows(boolean dataOnRows) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.setBooleanValue(dataOnRows);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetDataOnRows(XmlBoolean dataOnRows) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.set(dataOnRows);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetDataOnRows() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[19]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public long getDataPosition() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlUnsignedInt xgetDataPosition() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetDataPosition() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[20]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setDataPosition(long dataPosition) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.setLongValue(dataPosition);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetDataPosition(XmlUnsignedInt dataPosition) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.set(dataPosition);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetDataPosition() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[20]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public long getAutoFormatId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlUnsignedInt xgetAutoFormatId() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetAutoFormatId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[21]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setAutoFormatId(long autoFormatId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.setLongValue(autoFormatId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetAutoFormatId(XmlUnsignedInt autoFormatId) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.set(autoFormatId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetAutoFormatId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[21]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getApplyNumberFormats() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetApplyNumberFormats() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetApplyNumberFormats() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[22]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setApplyNumberFormats(boolean applyNumberFormats) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.setBooleanValue(applyNumberFormats);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetApplyNumberFormats(XmlBoolean applyNumberFormats) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.set(applyNumberFormats);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetApplyNumberFormats() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[22]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getApplyBorderFormats() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetApplyBorderFormats() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetApplyBorderFormats() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[23]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setApplyBorderFormats(boolean applyBorderFormats) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.setBooleanValue(applyBorderFormats);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetApplyBorderFormats(XmlBoolean applyBorderFormats) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.set(applyBorderFormats);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetApplyBorderFormats() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[23]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getApplyFontFormats() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetApplyFontFormats() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetApplyFontFormats() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[24]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setApplyFontFormats(boolean applyFontFormats) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.setBooleanValue(applyFontFormats);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetApplyFontFormats(XmlBoolean applyFontFormats) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.set(applyFontFormats);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetApplyFontFormats() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[24]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getApplyPatternFormats() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetApplyPatternFormats() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetApplyPatternFormats() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[25]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setApplyPatternFormats(boolean applyPatternFormats) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.setBooleanValue(applyPatternFormats);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetApplyPatternFormats(XmlBoolean applyPatternFormats) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.set(applyPatternFormats);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetApplyPatternFormats() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[25]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getApplyAlignmentFormats() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetApplyAlignmentFormats() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetApplyAlignmentFormats() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[26]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setApplyAlignmentFormats(boolean applyAlignmentFormats) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.setBooleanValue(applyAlignmentFormats);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetApplyAlignmentFormats(XmlBoolean applyAlignmentFormats) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.set(applyAlignmentFormats);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetApplyAlignmentFormats() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[26]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getApplyWidthHeightFormats() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetApplyWidthHeightFormats() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetApplyWidthHeightFormats() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[27]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setApplyWidthHeightFormats(boolean applyWidthHeightFormats) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[27]);
            }
            target.setBooleanValue(applyWidthHeightFormats);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetApplyWidthHeightFormats(XmlBoolean applyWidthHeightFormats) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[27]);
            }
            target.set(applyWidthHeightFormats);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetApplyWidthHeightFormats() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[27]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public String getDataCaption() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public STXstring xgetDataCaption() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setDataCaption(String dataCaption) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.setStringValue(dataCaption);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetDataCaption(STXstring dataCaption) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.set(dataCaption);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public String getGrandTotalCaption() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public STXstring xgetGrandTotalCaption() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetGrandTotalCaption() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[29]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setGrandTotalCaption(String grandTotalCaption) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[29]);
            }
            target.setStringValue(grandTotalCaption);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetGrandTotalCaption(STXstring grandTotalCaption) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[29]);
            }
            target.set(grandTotalCaption);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetGrandTotalCaption() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[29]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public String getErrorCaption() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public STXstring xgetErrorCaption() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[30]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetErrorCaption() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[30]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setErrorCaption(String errorCaption) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[30]);
            }
            target.setStringValue(errorCaption);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetErrorCaption(STXstring errorCaption) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[30]);
            }
            target.set(errorCaption);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetErrorCaption() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[30]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getShowError() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[31]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetShowError() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[31]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetShowError() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[31]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setShowError(boolean showError) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[31]);
            }
            target.setBooleanValue(showError);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetShowError(XmlBoolean showError) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[31]);
            }
            target.set(showError);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetShowError() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[31]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public String getMissingCaption() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[32]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public STXstring xgetMissingCaption() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[32]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetMissingCaption() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[32]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setMissingCaption(String missingCaption) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[32]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[32]);
            }
            target.setStringValue(missingCaption);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetMissingCaption(STXstring missingCaption) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[32]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[32]);
            }
            target.set(missingCaption);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetMissingCaption() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[32]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getShowMissing() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[33]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetShowMissing() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[33]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetShowMissing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[33]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setShowMissing(boolean showMissing) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[33]);
            }
            target.setBooleanValue(showMissing);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetShowMissing(XmlBoolean showMissing) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[33]);
            }
            target.set(showMissing);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetShowMissing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[33]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public String getPageStyle() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public STXstring xgetPageStyle() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[34]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetPageStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[34]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setPageStyle(String pageStyle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[34]);
            }
            target.setStringValue(pageStyle);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetPageStyle(STXstring pageStyle) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[34]);
            }
            target.set(pageStyle);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetPageStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[34]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public String getPivotTableStyle() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public STXstring xgetPivotTableStyle() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[35]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetPivotTableStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[35]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setPivotTableStyle(String pivotTableStyle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[35]);
            }
            target.setStringValue(pivotTableStyle);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetPivotTableStyle(STXstring pivotTableStyle) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[35]);
            }
            target.set(pivotTableStyle);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetPivotTableStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[35]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public String getVacatedStyle() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public STXstring xgetVacatedStyle() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[36]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetVacatedStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[36]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setVacatedStyle(String vacatedStyle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[36]);
            }
            target.setStringValue(vacatedStyle);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetVacatedStyle(STXstring vacatedStyle) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[36]);
            }
            target.set(vacatedStyle);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetVacatedStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[36]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public String getTag() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[37]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public STXstring xgetTag() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[37]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetTag() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[37]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setTag(String tag) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[37]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[37]);
            }
            target.setStringValue(tag);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetTag(STXstring tag) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[37]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[37]);
            }
            target.set(tag);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetTag() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[37]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public short getUpdatedVersion() {
        short shortValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[38]);
            }
            shortValue = target == null ? (short) 0 : target.getShortValue();
        }
        return shortValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlUnsignedByte xgetUpdatedVersion() {
        XmlUnsignedByte target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedByte) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            if (target == null) {
                target = (XmlUnsignedByte) get_default_attribute_value(PROPERTY_QNAME[38]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetUpdatedVersion() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[38]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setUpdatedVersion(short updatedVersion) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[38]);
            }
            target.setShortValue(updatedVersion);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetUpdatedVersion(XmlUnsignedByte updatedVersion) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedByte target = (XmlUnsignedByte) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            if (target == null) {
                target = (XmlUnsignedByte) get_store().add_attribute_user(PROPERTY_QNAME[38]);
            }
            target.set(updatedVersion);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetUpdatedVersion() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[38]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public short getMinRefreshableVersion() {
        short shortValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[39]);
            }
            shortValue = target == null ? (short) 0 : target.getShortValue();
        }
        return shortValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlUnsignedByte xgetMinRefreshableVersion() {
        XmlUnsignedByte target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedByte) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            if (target == null) {
                target = (XmlUnsignedByte) get_default_attribute_value(PROPERTY_QNAME[39]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetMinRefreshableVersion() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[39]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setMinRefreshableVersion(short minRefreshableVersion) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[39]);
            }
            target.setShortValue(minRefreshableVersion);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetMinRefreshableVersion(XmlUnsignedByte minRefreshableVersion) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedByte target = (XmlUnsignedByte) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            if (target == null) {
                target = (XmlUnsignedByte) get_store().add_attribute_user(PROPERTY_QNAME[39]);
            }
            target.set(minRefreshableVersion);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetMinRefreshableVersion() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[39]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getAsteriskTotals() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[40]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[40]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetAsteriskTotals() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[40]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[40]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetAsteriskTotals() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[40]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setAsteriskTotals(boolean asteriskTotals) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[40]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[40]);
            }
            target.setBooleanValue(asteriskTotals);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetAsteriskTotals(XmlBoolean asteriskTotals) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[40]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[40]);
            }
            target.set(asteriskTotals);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetAsteriskTotals() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[40]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getShowItems() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[41]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[41]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetShowItems() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[41]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[41]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetShowItems() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[41]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setShowItems(boolean showItems) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[41]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[41]);
            }
            target.setBooleanValue(showItems);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetShowItems(XmlBoolean showItems) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[41]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[41]);
            }
            target.set(showItems);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetShowItems() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[41]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getEditData() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[42]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[42]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetEditData() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[42]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[42]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetEditData() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[42]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setEditData(boolean editData) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[42]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[42]);
            }
            target.setBooleanValue(editData);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetEditData(XmlBoolean editData) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[42]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[42]);
            }
            target.set(editData);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetEditData() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[42]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getDisableFieldList() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[43]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[43]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetDisableFieldList() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[43]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[43]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetDisableFieldList() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[43]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setDisableFieldList(boolean disableFieldList) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[43]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[43]);
            }
            target.setBooleanValue(disableFieldList);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetDisableFieldList(XmlBoolean disableFieldList) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[43]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[43]);
            }
            target.set(disableFieldList);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetDisableFieldList() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[43]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getShowCalcMbrs() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[44]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[44]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetShowCalcMbrs() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[44]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[44]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetShowCalcMbrs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[44]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setShowCalcMbrs(boolean showCalcMbrs) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[44]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[44]);
            }
            target.setBooleanValue(showCalcMbrs);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetShowCalcMbrs(XmlBoolean showCalcMbrs) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[44]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[44]);
            }
            target.set(showCalcMbrs);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetShowCalcMbrs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[44]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getVisualTotals() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[45]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[45]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetVisualTotals() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[45]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[45]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetVisualTotals() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[45]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setVisualTotals(boolean visualTotals) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[45]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[45]);
            }
            target.setBooleanValue(visualTotals);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetVisualTotals(XmlBoolean visualTotals) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[45]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[45]);
            }
            target.set(visualTotals);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetVisualTotals() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[45]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getShowMultipleLabel() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[46]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[46]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetShowMultipleLabel() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[46]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[46]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetShowMultipleLabel() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[46]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setShowMultipleLabel(boolean showMultipleLabel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[46]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[46]);
            }
            target.setBooleanValue(showMultipleLabel);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetShowMultipleLabel(XmlBoolean showMultipleLabel) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[46]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[46]);
            }
            target.set(showMultipleLabel);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetShowMultipleLabel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[46]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getShowDataDropDown() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[47]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[47]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetShowDataDropDown() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[47]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[47]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetShowDataDropDown() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[47]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setShowDataDropDown(boolean showDataDropDown) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[47]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[47]);
            }
            target.setBooleanValue(showDataDropDown);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetShowDataDropDown(XmlBoolean showDataDropDown) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[47]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[47]);
            }
            target.set(showDataDropDown);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetShowDataDropDown() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[47]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getShowDrill() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[48]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[48]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetShowDrill() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[48]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[48]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetShowDrill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[48]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setShowDrill(boolean showDrill) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[48]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[48]);
            }
            target.setBooleanValue(showDrill);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetShowDrill(XmlBoolean showDrill) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[48]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[48]);
            }
            target.set(showDrill);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetShowDrill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[48]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getPrintDrill() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[49]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[49]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetPrintDrill() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[49]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[49]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetPrintDrill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[49]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setPrintDrill(boolean printDrill) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[49]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[49]);
            }
            target.setBooleanValue(printDrill);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetPrintDrill(XmlBoolean printDrill) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[49]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[49]);
            }
            target.set(printDrill);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetPrintDrill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[49]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getShowMemberPropertyTips() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[50]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[50]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetShowMemberPropertyTips() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[50]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[50]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetShowMemberPropertyTips() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[50]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setShowMemberPropertyTips(boolean showMemberPropertyTips) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[50]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[50]);
            }
            target.setBooleanValue(showMemberPropertyTips);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetShowMemberPropertyTips(XmlBoolean showMemberPropertyTips) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[50]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[50]);
            }
            target.set(showMemberPropertyTips);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetShowMemberPropertyTips() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[50]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getShowDataTips() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[51]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[51]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetShowDataTips() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[51]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[51]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetShowDataTips() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[51]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setShowDataTips(boolean showDataTips) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[51]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[51]);
            }
            target.setBooleanValue(showDataTips);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetShowDataTips(XmlBoolean showDataTips) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[51]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[51]);
            }
            target.set(showDataTips);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetShowDataTips() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[51]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getEnableWizard() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[52]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[52]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetEnableWizard() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[52]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[52]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetEnableWizard() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[52]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setEnableWizard(boolean enableWizard) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[52]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[52]);
            }
            target.setBooleanValue(enableWizard);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetEnableWizard(XmlBoolean enableWizard) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[52]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[52]);
            }
            target.set(enableWizard);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetEnableWizard() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[52]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getEnableDrill() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[53]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[53]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetEnableDrill() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[53]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[53]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetEnableDrill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[53]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setEnableDrill(boolean enableDrill) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[53]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[53]);
            }
            target.setBooleanValue(enableDrill);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetEnableDrill(XmlBoolean enableDrill) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[53]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[53]);
            }
            target.set(enableDrill);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetEnableDrill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[53]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getEnableFieldProperties() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[54]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[54]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetEnableFieldProperties() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[54]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[54]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetEnableFieldProperties() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[54]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setEnableFieldProperties(boolean enableFieldProperties) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[54]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[54]);
            }
            target.setBooleanValue(enableFieldProperties);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetEnableFieldProperties(XmlBoolean enableFieldProperties) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[54]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[54]);
            }
            target.set(enableFieldProperties);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetEnableFieldProperties() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[54]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getPreserveFormatting() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[55]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[55]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetPreserveFormatting() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[55]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[55]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetPreserveFormatting() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[55]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setPreserveFormatting(boolean preserveFormatting) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[55]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[55]);
            }
            target.setBooleanValue(preserveFormatting);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetPreserveFormatting(XmlBoolean preserveFormatting) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[55]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[55]);
            }
            target.set(preserveFormatting);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetPreserveFormatting() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[55]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getUseAutoFormatting() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[56]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[56]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetUseAutoFormatting() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[56]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[56]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetUseAutoFormatting() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[56]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setUseAutoFormatting(boolean useAutoFormatting) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[56]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[56]);
            }
            target.setBooleanValue(useAutoFormatting);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetUseAutoFormatting(XmlBoolean useAutoFormatting) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[56]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[56]);
            }
            target.set(useAutoFormatting);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetUseAutoFormatting() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[56]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public long getPageWrap() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[57]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[57]);
            }
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlUnsignedInt xgetPageWrap() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[57]);
            if (target == null) {
                target = (XmlUnsignedInt) get_default_attribute_value(PROPERTY_QNAME[57]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetPageWrap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[57]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setPageWrap(long pageWrap) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[57]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[57]);
            }
            target.setLongValue(pageWrap);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetPageWrap(XmlUnsignedInt pageWrap) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[57]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[57]);
            }
            target.set(pageWrap);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetPageWrap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[57]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getPageOverThenDown() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[58]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[58]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetPageOverThenDown() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[58]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[58]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetPageOverThenDown() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[58]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setPageOverThenDown(boolean pageOverThenDown) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[58]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[58]);
            }
            target.setBooleanValue(pageOverThenDown);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetPageOverThenDown(XmlBoolean pageOverThenDown) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[58]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[58]);
            }
            target.set(pageOverThenDown);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetPageOverThenDown() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[58]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getSubtotalHiddenItems() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[59]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[59]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetSubtotalHiddenItems() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[59]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[59]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetSubtotalHiddenItems() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[59]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setSubtotalHiddenItems(boolean subtotalHiddenItems) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[59]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[59]);
            }
            target.setBooleanValue(subtotalHiddenItems);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetSubtotalHiddenItems(XmlBoolean subtotalHiddenItems) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[59]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[59]);
            }
            target.set(subtotalHiddenItems);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetSubtotalHiddenItems() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[59]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getRowGrandTotals() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[60]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[60]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetRowGrandTotals() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[60]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[60]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetRowGrandTotals() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[60]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setRowGrandTotals(boolean rowGrandTotals) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[60]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[60]);
            }
            target.setBooleanValue(rowGrandTotals);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetRowGrandTotals(XmlBoolean rowGrandTotals) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[60]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[60]);
            }
            target.set(rowGrandTotals);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetRowGrandTotals() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[60]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getColGrandTotals() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[61]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[61]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetColGrandTotals() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[61]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[61]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetColGrandTotals() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[61]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setColGrandTotals(boolean colGrandTotals) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[61]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[61]);
            }
            target.setBooleanValue(colGrandTotals);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetColGrandTotals(XmlBoolean colGrandTotals) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[61]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[61]);
            }
            target.set(colGrandTotals);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetColGrandTotals() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[61]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getFieldPrintTitles() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[62]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[62]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetFieldPrintTitles() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[62]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[62]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetFieldPrintTitles() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[62]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setFieldPrintTitles(boolean fieldPrintTitles) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[62]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[62]);
            }
            target.setBooleanValue(fieldPrintTitles);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetFieldPrintTitles(XmlBoolean fieldPrintTitles) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[62]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[62]);
            }
            target.set(fieldPrintTitles);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetFieldPrintTitles() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[62]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getItemPrintTitles() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[63]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[63]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetItemPrintTitles() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[63]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[63]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetItemPrintTitles() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[63]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setItemPrintTitles(boolean itemPrintTitles) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[63]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[63]);
            }
            target.setBooleanValue(itemPrintTitles);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetItemPrintTitles(XmlBoolean itemPrintTitles) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[63]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[63]);
            }
            target.set(itemPrintTitles);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetItemPrintTitles() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[63]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getMergeItem() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[64]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[64]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetMergeItem() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[64]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[64]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetMergeItem() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[64]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setMergeItem(boolean mergeItem) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[64]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[64]);
            }
            target.setBooleanValue(mergeItem);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetMergeItem(XmlBoolean mergeItem) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[64]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[64]);
            }
            target.set(mergeItem);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetMergeItem() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[64]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getShowDropZones() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[65]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[65]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetShowDropZones() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[65]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[65]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetShowDropZones() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[65]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setShowDropZones(boolean showDropZones) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[65]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[65]);
            }
            target.setBooleanValue(showDropZones);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetShowDropZones(XmlBoolean showDropZones) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[65]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[65]);
            }
            target.set(showDropZones);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetShowDropZones() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[65]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public short getCreatedVersion() {
        short shortValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[66]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[66]);
            }
            shortValue = target == null ? (short) 0 : target.getShortValue();
        }
        return shortValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlUnsignedByte xgetCreatedVersion() {
        XmlUnsignedByte target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedByte) get_store().find_attribute_user(PROPERTY_QNAME[66]);
            if (target == null) {
                target = (XmlUnsignedByte) get_default_attribute_value(PROPERTY_QNAME[66]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetCreatedVersion() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[66]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setCreatedVersion(short createdVersion) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[66]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[66]);
            }
            target.setShortValue(createdVersion);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetCreatedVersion(XmlUnsignedByte createdVersion) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedByte target = (XmlUnsignedByte) get_store().find_attribute_user(PROPERTY_QNAME[66]);
            if (target == null) {
                target = (XmlUnsignedByte) get_store().add_attribute_user(PROPERTY_QNAME[66]);
            }
            target.set(createdVersion);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetCreatedVersion() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[66]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public long getIndent() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[67]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[67]);
            }
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlUnsignedInt xgetIndent() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[67]);
            if (target == null) {
                target = (XmlUnsignedInt) get_default_attribute_value(PROPERTY_QNAME[67]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetIndent() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[67]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setIndent(long indent) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[67]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[67]);
            }
            target.setLongValue(indent);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetIndent(XmlUnsignedInt indent) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[67]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[67]);
            }
            target.set(indent);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetIndent() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[67]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getShowEmptyRow() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[68]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[68]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetShowEmptyRow() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[68]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[68]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetShowEmptyRow() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[68]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setShowEmptyRow(boolean showEmptyRow) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[68]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[68]);
            }
            target.setBooleanValue(showEmptyRow);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetShowEmptyRow(XmlBoolean showEmptyRow) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[68]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[68]);
            }
            target.set(showEmptyRow);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetShowEmptyRow() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[68]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getShowEmptyCol() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[69]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[69]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetShowEmptyCol() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[69]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[69]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetShowEmptyCol() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[69]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setShowEmptyCol(boolean showEmptyCol) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[69]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[69]);
            }
            target.setBooleanValue(showEmptyCol);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetShowEmptyCol(XmlBoolean showEmptyCol) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[69]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[69]);
            }
            target.set(showEmptyCol);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetShowEmptyCol() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[69]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getShowHeaders() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[70]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[70]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetShowHeaders() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[70]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[70]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetShowHeaders() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[70]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setShowHeaders(boolean showHeaders) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[70]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[70]);
            }
            target.setBooleanValue(showHeaders);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetShowHeaders(XmlBoolean showHeaders) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[70]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[70]);
            }
            target.set(showHeaders);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetShowHeaders() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[70]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getCompact() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[71]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[71]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetCompact() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[71]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[71]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetCompact() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[71]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setCompact(boolean compact) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[71]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[71]);
            }
            target.setBooleanValue(compact);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetCompact(XmlBoolean compact) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[71]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[71]);
            }
            target.set(compact);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetCompact() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[71]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getOutline() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[72]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[72]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetOutline() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[72]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[72]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetOutline() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[72]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setOutline(boolean outline) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[72]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[72]);
            }
            target.setBooleanValue(outline);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetOutline(XmlBoolean outline) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[72]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[72]);
            }
            target.set(outline);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetOutline() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[72]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getOutlineData() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[73]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[73]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetOutlineData() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[73]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[73]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetOutlineData() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[73]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setOutlineData(boolean outlineData) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[73]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[73]);
            }
            target.setBooleanValue(outlineData);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetOutlineData(XmlBoolean outlineData) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[73]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[73]);
            }
            target.set(outlineData);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetOutlineData() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[73]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getCompactData() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[74]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[74]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetCompactData() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[74]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[74]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetCompactData() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[74]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setCompactData(boolean compactData) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[74]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[74]);
            }
            target.setBooleanValue(compactData);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetCompactData(XmlBoolean compactData) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[74]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[74]);
            }
            target.set(compactData);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetCompactData() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[74]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getPublished() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[75]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[75]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetPublished() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[75]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[75]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetPublished() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[75]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setPublished(boolean published) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[75]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[75]);
            }
            target.setBooleanValue(published);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetPublished(XmlBoolean published) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[75]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[75]);
            }
            target.set(published);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetPublished() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[75]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getGridDropZones() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[76]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[76]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetGridDropZones() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[76]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[76]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetGridDropZones() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[76]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setGridDropZones(boolean gridDropZones) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[76]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[76]);
            }
            target.setBooleanValue(gridDropZones);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetGridDropZones(XmlBoolean gridDropZones) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[76]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[76]);
            }
            target.set(gridDropZones);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetGridDropZones() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[76]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getImmersive() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[77]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[77]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetImmersive() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[77]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[77]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetImmersive() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[77]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setImmersive(boolean immersive) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[77]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[77]);
            }
            target.setBooleanValue(immersive);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetImmersive(XmlBoolean immersive) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[77]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[77]);
            }
            target.set(immersive);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetImmersive() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[77]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getMultipleFieldFilters() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[78]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[78]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetMultipleFieldFilters() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[78]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[78]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetMultipleFieldFilters() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[78]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setMultipleFieldFilters(boolean multipleFieldFilters) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[78]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[78]);
            }
            target.setBooleanValue(multipleFieldFilters);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetMultipleFieldFilters(XmlBoolean multipleFieldFilters) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[78]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[78]);
            }
            target.set(multipleFieldFilters);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetMultipleFieldFilters() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[78]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public long getChartFormat() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[79]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[79]);
            }
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlUnsignedInt xgetChartFormat() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[79]);
            if (target == null) {
                target = (XmlUnsignedInt) get_default_attribute_value(PROPERTY_QNAME[79]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetChartFormat() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[79]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setChartFormat(long chartFormat) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[79]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[79]);
            }
            target.setLongValue(chartFormat);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetChartFormat(XmlUnsignedInt chartFormat) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[79]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[79]);
            }
            target.set(chartFormat);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetChartFormat() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[79]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public String getRowHeaderCaption() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[80]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public STXstring xgetRowHeaderCaption() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[80]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetRowHeaderCaption() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[80]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setRowHeaderCaption(String rowHeaderCaption) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[80]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[80]);
            }
            target.setStringValue(rowHeaderCaption);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetRowHeaderCaption(STXstring rowHeaderCaption) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[80]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[80]);
            }
            target.set(rowHeaderCaption);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetRowHeaderCaption() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[80]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public String getColHeaderCaption() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[81]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public STXstring xgetColHeaderCaption() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[81]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetColHeaderCaption() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[81]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setColHeaderCaption(String colHeaderCaption) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[81]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[81]);
            }
            target.setStringValue(colHeaderCaption);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetColHeaderCaption(STXstring colHeaderCaption) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[81]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[81]);
            }
            target.set(colHeaderCaption);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetColHeaderCaption() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[81]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getFieldListSortAscending() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[82]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[82]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetFieldListSortAscending() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[82]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[82]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetFieldListSortAscending() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[82]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setFieldListSortAscending(boolean fieldListSortAscending) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[82]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[82]);
            }
            target.setBooleanValue(fieldListSortAscending);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetFieldListSortAscending(XmlBoolean fieldListSortAscending) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[82]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[82]);
            }
            target.set(fieldListSortAscending);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetFieldListSortAscending() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[82]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getMdxSubqueries() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[83]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[83]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetMdxSubqueries() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[83]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[83]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetMdxSubqueries() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[83]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setMdxSubqueries(boolean mdxSubqueries) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[83]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[83]);
            }
            target.setBooleanValue(mdxSubqueries);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetMdxSubqueries(XmlBoolean mdxSubqueries) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[83]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[83]);
            }
            target.set(mdxSubqueries);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetMdxSubqueries() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[83]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean getCustomListSort() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[84]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[84]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public XmlBoolean xgetCustomListSort() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[84]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[84]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public boolean isSetCustomListSort() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[84]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void setCustomListSort(boolean customListSort) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[84]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[84]);
            }
            target.setBooleanValue(customListSort);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void xsetCustomListSort(XmlBoolean customListSort) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[84]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[84]);
            }
            target.set(customListSort);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition
    public void unsetCustomListSort() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[84]);
        }
    }
}
