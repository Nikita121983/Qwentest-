package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass$Enum;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReferences;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileRecoveryPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileSharing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleSize;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTagPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTagTypes;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishObjects;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbookPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbookProtection;

/* loaded from: classes12.dex */
public class CTWorkbookImpl extends XmlComplexContentImpl implements CTWorkbook {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "fileVersion"), new QName(XSSFRelation.NS_SPREADSHEETML, "fileSharing"), new QName(XSSFRelation.NS_SPREADSHEETML, "workbookPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "workbookProtection"), new QName(XSSFRelation.NS_SPREADSHEETML, "bookViews"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheets"), new QName(XSSFRelation.NS_SPREADSHEETML, "functionGroups"), new QName(XSSFRelation.NS_SPREADSHEETML, "externalReferences"), new QName(XSSFRelation.NS_SPREADSHEETML, "definedNames"), new QName(XSSFRelation.NS_SPREADSHEETML, "calcPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "oleSize"), new QName(XSSFRelation.NS_SPREADSHEETML, "customWorkbookViews"), new QName(XSSFRelation.NS_SPREADSHEETML, "pivotCaches"), new QName(XSSFRelation.NS_SPREADSHEETML, "smartTagPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "smartTagTypes"), new QName(XSSFRelation.NS_SPREADSHEETML, "webPublishing"), new QName(XSSFRelation.NS_SPREADSHEETML, "fileRecoveryPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "webPublishObjects"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst"), new QName("", "conformance")};
    private static final long serialVersionUID = 1;

    public CTWorkbookImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTFileVersion getFileVersion() {
        CTFileVersion cTFileVersion;
        synchronized (monitor()) {
            check_orphaned();
            CTFileVersion target = (CTFileVersion) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTFileVersion = target == null ? null : target;
        }
        return cTFileVersion;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetFileVersion() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setFileVersion(CTFileVersion fileVersion) {
        generatedSetterHelperImpl(fileVersion, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTFileVersion addNewFileVersion() {
        CTFileVersion target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFileVersion) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetFileVersion() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTFileSharing getFileSharing() {
        CTFileSharing cTFileSharing;
        synchronized (monitor()) {
            check_orphaned();
            CTFileSharing target = (CTFileSharing) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTFileSharing = target == null ? null : target;
        }
        return cTFileSharing;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetFileSharing() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setFileSharing(CTFileSharing fileSharing) {
        generatedSetterHelperImpl(fileSharing, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTFileSharing addNewFileSharing() {
        CTFileSharing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFileSharing) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetFileSharing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTWorkbookPr getWorkbookPr() {
        CTWorkbookPr cTWorkbookPr;
        synchronized (monitor()) {
            check_orphaned();
            CTWorkbookPr target = (CTWorkbookPr) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTWorkbookPr = target == null ? null : target;
        }
        return cTWorkbookPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetWorkbookPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setWorkbookPr(CTWorkbookPr workbookPr) {
        generatedSetterHelperImpl(workbookPr, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTWorkbookPr addNewWorkbookPr() {
        CTWorkbookPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTWorkbookPr) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetWorkbookPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTWorkbookProtection getWorkbookProtection() {
        CTWorkbookProtection cTWorkbookProtection;
        synchronized (monitor()) {
            check_orphaned();
            CTWorkbookProtection target = (CTWorkbookProtection) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTWorkbookProtection = target == null ? null : target;
        }
        return cTWorkbookProtection;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetWorkbookProtection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setWorkbookProtection(CTWorkbookProtection workbookProtection) {
        generatedSetterHelperImpl(workbookProtection, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTWorkbookProtection addNewWorkbookProtection() {
        CTWorkbookProtection target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTWorkbookProtection) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetWorkbookProtection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTBookViews getBookViews() {
        CTBookViews cTBookViews;
        synchronized (monitor()) {
            check_orphaned();
            CTBookViews target = (CTBookViews) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTBookViews = target == null ? null : target;
        }
        return cTBookViews;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetBookViews() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setBookViews(CTBookViews bookViews) {
        generatedSetterHelperImpl(bookViews, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTBookViews addNewBookViews() {
        CTBookViews target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBookViews) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetBookViews() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTSheets getSheets() {
        CTSheets cTSheets;
        synchronized (monitor()) {
            check_orphaned();
            CTSheets target = (CTSheets) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTSheets = target == null ? null : target;
        }
        return cTSheets;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setSheets(CTSheets sheets) {
        generatedSetterHelperImpl(sheets, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTSheets addNewSheets() {
        CTSheets target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSheets) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTFunctionGroups getFunctionGroups() {
        CTFunctionGroups cTFunctionGroups;
        synchronized (monitor()) {
            check_orphaned();
            CTFunctionGroups target = (CTFunctionGroups) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTFunctionGroups = target == null ? null : target;
        }
        return cTFunctionGroups;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetFunctionGroups() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setFunctionGroups(CTFunctionGroups functionGroups) {
        generatedSetterHelperImpl(functionGroups, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTFunctionGroups addNewFunctionGroups() {
        CTFunctionGroups target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFunctionGroups) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetFunctionGroups() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTExternalReferences getExternalReferences() {
        CTExternalReferences cTExternalReferences;
        synchronized (monitor()) {
            check_orphaned();
            CTExternalReferences target = (CTExternalReferences) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTExternalReferences = target == null ? null : target;
        }
        return cTExternalReferences;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetExternalReferences() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setExternalReferences(CTExternalReferences externalReferences) {
        generatedSetterHelperImpl(externalReferences, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTExternalReferences addNewExternalReferences() {
        CTExternalReferences target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExternalReferences) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetExternalReferences() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTDefinedNames getDefinedNames() {
        CTDefinedNames cTDefinedNames;
        synchronized (monitor()) {
            check_orphaned();
            CTDefinedNames target = (CTDefinedNames) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTDefinedNames = target == null ? null : target;
        }
        return cTDefinedNames;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetDefinedNames() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setDefinedNames(CTDefinedNames definedNames) {
        generatedSetterHelperImpl(definedNames, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTDefinedNames addNewDefinedNames() {
        CTDefinedNames target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDefinedNames) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetDefinedNames() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTCalcPr getCalcPr() {
        CTCalcPr cTCalcPr;
        synchronized (monitor()) {
            check_orphaned();
            CTCalcPr target = (CTCalcPr) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTCalcPr = target == null ? null : target;
        }
        return cTCalcPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetCalcPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setCalcPr(CTCalcPr calcPr) {
        generatedSetterHelperImpl(calcPr, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTCalcPr addNewCalcPr() {
        CTCalcPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCalcPr) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetCalcPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTOleSize getOleSize() {
        CTOleSize cTOleSize;
        synchronized (monitor()) {
            check_orphaned();
            CTOleSize target = (CTOleSize) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTOleSize = target == null ? null : target;
        }
        return cTOleSize;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetOleSize() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setOleSize(CTOleSize oleSize) {
        generatedSetterHelperImpl(oleSize, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTOleSize addNewOleSize() {
        CTOleSize target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOleSize) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetOleSize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTCustomWorkbookViews getCustomWorkbookViews() {
        CTCustomWorkbookViews cTCustomWorkbookViews;
        synchronized (monitor()) {
            check_orphaned();
            CTCustomWorkbookViews target = (CTCustomWorkbookViews) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTCustomWorkbookViews = target == null ? null : target;
        }
        return cTCustomWorkbookViews;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetCustomWorkbookViews() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setCustomWorkbookViews(CTCustomWorkbookViews customWorkbookViews) {
        generatedSetterHelperImpl(customWorkbookViews, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTCustomWorkbookViews addNewCustomWorkbookViews() {
        CTCustomWorkbookViews target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomWorkbookViews) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetCustomWorkbookViews() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTPivotCaches getPivotCaches() {
        CTPivotCaches cTPivotCaches;
        synchronized (monitor()) {
            check_orphaned();
            CTPivotCaches target = (CTPivotCaches) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTPivotCaches = target == null ? null : target;
        }
        return cTPivotCaches;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetPivotCaches() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setPivotCaches(CTPivotCaches pivotCaches) {
        generatedSetterHelperImpl(pivotCaches, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTPivotCaches addNewPivotCaches() {
        CTPivotCaches target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPivotCaches) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetPivotCaches() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTSmartTagPr getSmartTagPr() {
        CTSmartTagPr cTSmartTagPr;
        synchronized (monitor()) {
            check_orphaned();
            CTSmartTagPr target = get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTSmartTagPr = target == null ? null : target;
        }
        return cTSmartTagPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetSmartTagPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setSmartTagPr(CTSmartTagPr smartTagPr) {
        generatedSetterHelperImpl(smartTagPr, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTSmartTagPr addNewSmartTagPr() {
        CTSmartTagPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetSmartTagPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTSmartTagTypes getSmartTagTypes() {
        CTSmartTagTypes cTSmartTagTypes;
        synchronized (monitor()) {
            check_orphaned();
            CTSmartTagTypes target = get_store().find_element_user(PROPERTY_QNAME[14], 0);
            cTSmartTagTypes = target == null ? null : target;
        }
        return cTSmartTagTypes;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetSmartTagTypes() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setSmartTagTypes(CTSmartTagTypes smartTagTypes) {
        generatedSetterHelperImpl(smartTagTypes, PROPERTY_QNAME[14], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTSmartTagTypes addNewSmartTagTypes() {
        CTSmartTagTypes target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetSmartTagTypes() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTWebPublishing getWebPublishing() {
        CTWebPublishing cTWebPublishing;
        synchronized (monitor()) {
            check_orphaned();
            CTWebPublishing target = (CTWebPublishing) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            cTWebPublishing = target == null ? null : target;
        }
        return cTWebPublishing;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetWebPublishing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setWebPublishing(CTWebPublishing webPublishing) {
        generatedSetterHelperImpl(webPublishing, PROPERTY_QNAME[15], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTWebPublishing addNewWebPublishing() {
        CTWebPublishing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTWebPublishing) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetWebPublishing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public List<CTFileRecoveryPr> getFileRecoveryPrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorkbookImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTWorkbookImpl.this.getFileRecoveryPrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorkbookImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTWorkbookImpl.this.setFileRecoveryPrArray(((Integer) obj).intValue(), (CTFileRecoveryPr) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorkbookImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTWorkbookImpl.this.insertNewFileRecoveryPr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorkbookImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTWorkbookImpl.this.removeFileRecoveryPr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorkbookImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTWorkbookImpl.this.sizeOfFileRecoveryPrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTFileRecoveryPr[] getFileRecoveryPrArray() {
        return (CTFileRecoveryPr[]) getXmlObjectArray(PROPERTY_QNAME[16], new CTFileRecoveryPr[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTFileRecoveryPr getFileRecoveryPrArray(int i) {
        CTFileRecoveryPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFileRecoveryPr) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public int sizeOfFileRecoveryPrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setFileRecoveryPrArray(CTFileRecoveryPr[] fileRecoveryPrArray) {
        check_orphaned();
        arraySetterHelper(fileRecoveryPrArray, PROPERTY_QNAME[16]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setFileRecoveryPrArray(int i, CTFileRecoveryPr fileRecoveryPr) {
        generatedSetterHelperImpl(fileRecoveryPr, PROPERTY_QNAME[16], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTFileRecoveryPr insertNewFileRecoveryPr(int i) {
        CTFileRecoveryPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFileRecoveryPr) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTFileRecoveryPr addNewFileRecoveryPr() {
        CTFileRecoveryPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFileRecoveryPr) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void removeFileRecoveryPr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTWebPublishObjects getWebPublishObjects() {
        CTWebPublishObjects cTWebPublishObjects;
        synchronized (monitor()) {
            check_orphaned();
            CTWebPublishObjects target = get_store().find_element_user(PROPERTY_QNAME[17], 0);
            cTWebPublishObjects = target == null ? null : target;
        }
        return cTWebPublishObjects;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetWebPublishObjects() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setWebPublishObjects(CTWebPublishObjects webPublishObjects) {
        generatedSetterHelperImpl(webPublishObjects, PROPERTY_QNAME[17], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTWebPublishObjects addNewWebPublishObjects() {
        CTWebPublishObjects target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetWebPublishObjects() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[18], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public STConformanceClass$Enum getConformance() {
        STConformanceClass$Enum sTConformanceClass$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            sTConformanceClass$Enum = target == null ? null : (STConformanceClass$Enum) target.getEnumValue();
        }
        return sTConformanceClass$Enum;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public STConformanceClass xgetConformance() {
        STConformanceClass target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public boolean isSetConformance() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[19]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void setConformance(STConformanceClass$Enum conformance) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.setEnumValue(conformance);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void xsetConformance(STConformanceClass conformance) {
        synchronized (monitor()) {
            check_orphaned();
            STConformanceClass target = get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (STConformanceClass) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.set(conformance);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook
    public void unsetConformance() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[19]);
        }
    }
}
