package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoFilter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageBreak;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellRef;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSheetState;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSheetViewType;

/* loaded from: classes12.dex */
public class CTCustomSheetViewImpl extends XmlComplexContentImpl implements CTCustomSheetView {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "pane"), new QName(XSSFRelation.NS_SPREADSHEETML, "selection"), new QName(XSSFRelation.NS_SPREADSHEETML, "rowBreaks"), new QName(XSSFRelation.NS_SPREADSHEETML, "colBreaks"), new QName(XSSFRelation.NS_SPREADSHEETML, "pageMargins"), new QName(XSSFRelation.NS_SPREADSHEETML, "printOptions"), new QName(XSSFRelation.NS_SPREADSHEETML, "pageSetup"), new QName(XSSFRelation.NS_SPREADSHEETML, "headerFooter"), new QName(XSSFRelation.NS_SPREADSHEETML, "autoFilter"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst"), new QName("", "guid"), new QName("", "scale"), new QName("", "colorId"), new QName("", "showPageBreaks"), new QName("", "showFormulas"), new QName("", "showGridLines"), new QName("", "showRowCol"), new QName("", "outlineSymbols"), new QName("", "zeroValues"), new QName("", "fitToPage"), new QName("", "printArea"), new QName("", "filter"), new QName("", "showAutoFilter"), new QName("", "hiddenRows"), new QName("", "hiddenColumns"), new QName("", "state"), new QName("", "filterUnique"), new QName("", "view"), new QName("", "showRuler"), new QName("", "topLeftCell")};
    private static final long serialVersionUID = 1;

    public CTCustomSheetViewImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTPane getPane() {
        CTPane cTPane;
        synchronized (monitor()) {
            check_orphaned();
            CTPane target = (CTPane) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTPane = target == null ? null : target;
        }
        return cTPane;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetPane() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setPane(CTPane pane) {
        generatedSetterHelperImpl(pane, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTPane addNewPane() {
        CTPane target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPane) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetPane() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTSelection getSelection() {
        CTSelection cTSelection;
        synchronized (monitor()) {
            check_orphaned();
            CTSelection target = (CTSelection) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTSelection = target == null ? null : target;
        }
        return cTSelection;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetSelection() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setSelection(CTSelection selection) {
        generatedSetterHelperImpl(selection, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTSelection addNewSelection() {
        CTSelection target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSelection) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetSelection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTPageBreak getRowBreaks() {
        CTPageBreak cTPageBreak;
        synchronized (monitor()) {
            check_orphaned();
            CTPageBreak target = (CTPageBreak) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTPageBreak = target == null ? null : target;
        }
        return cTPageBreak;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetRowBreaks() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setRowBreaks(CTPageBreak rowBreaks) {
        generatedSetterHelperImpl(rowBreaks, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTPageBreak addNewRowBreaks() {
        CTPageBreak target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageBreak) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetRowBreaks() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTPageBreak getColBreaks() {
        CTPageBreak cTPageBreak;
        synchronized (monitor()) {
            check_orphaned();
            CTPageBreak target = (CTPageBreak) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTPageBreak = target == null ? null : target;
        }
        return cTPageBreak;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetColBreaks() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setColBreaks(CTPageBreak colBreaks) {
        generatedSetterHelperImpl(colBreaks, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTPageBreak addNewColBreaks() {
        CTPageBreak target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageBreak) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetColBreaks() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTPageMargins getPageMargins() {
        CTPageMargins cTPageMargins;
        synchronized (monitor()) {
            check_orphaned();
            CTPageMargins target = (CTPageMargins) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTPageMargins = target == null ? null : target;
        }
        return cTPageMargins;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetPageMargins() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setPageMargins(CTPageMargins pageMargins) {
        generatedSetterHelperImpl(pageMargins, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTPageMargins addNewPageMargins() {
        CTPageMargins target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageMargins) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetPageMargins() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTPrintOptions getPrintOptions() {
        CTPrintOptions cTPrintOptions;
        synchronized (monitor()) {
            check_orphaned();
            CTPrintOptions target = (CTPrintOptions) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTPrintOptions = target == null ? null : target;
        }
        return cTPrintOptions;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetPrintOptions() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setPrintOptions(CTPrintOptions printOptions) {
        generatedSetterHelperImpl(printOptions, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTPrintOptions addNewPrintOptions() {
        CTPrintOptions target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPrintOptions) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetPrintOptions() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTPageSetup getPageSetup() {
        CTPageSetup cTPageSetup;
        synchronized (monitor()) {
            check_orphaned();
            CTPageSetup target = (CTPageSetup) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTPageSetup = target == null ? null : target;
        }
        return cTPageSetup;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetPageSetup() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setPageSetup(CTPageSetup pageSetup) {
        generatedSetterHelperImpl(pageSetup, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTPageSetup addNewPageSetup() {
        CTPageSetup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageSetup) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetPageSetup() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTHeaderFooter getHeaderFooter() {
        CTHeaderFooter cTHeaderFooter;
        synchronized (monitor()) {
            check_orphaned();
            CTHeaderFooter target = (CTHeaderFooter) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTHeaderFooter = target == null ? null : target;
        }
        return cTHeaderFooter;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetHeaderFooter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setHeaderFooter(CTHeaderFooter headerFooter) {
        generatedSetterHelperImpl(headerFooter, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTHeaderFooter addNewHeaderFooter() {
        CTHeaderFooter target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHeaderFooter) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetHeaderFooter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTAutoFilter getAutoFilter() {
        CTAutoFilter cTAutoFilter;
        synchronized (monitor()) {
            check_orphaned();
            CTAutoFilter target = (CTAutoFilter) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTAutoFilter = target == null ? null : target;
        }
        return cTAutoFilter;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetAutoFilter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setAutoFilter(CTAutoFilter autoFilter) {
        generatedSetterHelperImpl(autoFilter, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTAutoFilter addNewAutoFilter() {
        CTAutoFilter target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAutoFilter) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetAutoFilter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public String getGuid() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public STGuid xgetGuid() {
        STGuid target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STGuid) get_store().find_attribute_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setGuid(String guid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setStringValue(guid);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetGuid(STGuid guid) {
        synchronized (monitor()) {
            check_orphaned();
            STGuid target = (STGuid) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (STGuid) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(guid);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public long getScale() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[11]);
            }
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public XmlUnsignedInt xgetScale() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (XmlUnsignedInt) get_default_attribute_value(PROPERTY_QNAME[11]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetScale() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setScale(long scale) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setLongValue(scale);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetScale(XmlUnsignedInt scale) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(scale);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetScale() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public long getColorId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[12]);
            }
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public XmlUnsignedInt xgetColorId() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (XmlUnsignedInt) get_default_attribute_value(PROPERTY_QNAME[12]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetColorId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setColorId(long colorId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setLongValue(colorId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetColorId(XmlUnsignedInt colorId) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(colorId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetColorId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean getShowPageBreaks() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[13]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public XmlBoolean xgetShowPageBreaks() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[13]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetShowPageBreaks() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setShowPageBreaks(boolean showPageBreaks) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setBooleanValue(showPageBreaks);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetShowPageBreaks(XmlBoolean showPageBreaks) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(showPageBreaks);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetShowPageBreaks() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean getShowFormulas() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[14]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public XmlBoolean xgetShowFormulas() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[14]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetShowFormulas() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setShowFormulas(boolean showFormulas) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.setBooleanValue(showFormulas);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetShowFormulas(XmlBoolean showFormulas) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.set(showFormulas);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetShowFormulas() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[14]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean getShowGridLines() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[15]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public XmlBoolean xgetShowGridLines() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[15]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetShowGridLines() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setShowGridLines(boolean showGridLines) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.setBooleanValue(showGridLines);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetShowGridLines(XmlBoolean showGridLines) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.set(showGridLines);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetShowGridLines() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[15]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean getShowRowCol() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[16]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public XmlBoolean xgetShowRowCol() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[16]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetShowRowCol() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[16]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setShowRowCol(boolean showRowCol) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.setBooleanValue(showRowCol);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetShowRowCol(XmlBoolean showRowCol) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.set(showRowCol);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetShowRowCol() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[16]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean getOutlineSymbols() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[17]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public XmlBoolean xgetOutlineSymbols() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[17]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetOutlineSymbols() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[17]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setOutlineSymbols(boolean outlineSymbols) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.setBooleanValue(outlineSymbols);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetOutlineSymbols(XmlBoolean outlineSymbols) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.set(outlineSymbols);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetOutlineSymbols() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[17]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean getZeroValues() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[18]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public XmlBoolean xgetZeroValues() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[18]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetZeroValues() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[18]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setZeroValues(boolean zeroValues) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.setBooleanValue(zeroValues);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetZeroValues(XmlBoolean zeroValues) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.set(zeroValues);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetZeroValues() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[18]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean getFitToPage() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public XmlBoolean xgetFitToPage() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetFitToPage() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[19]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setFitToPage(boolean fitToPage) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.setBooleanValue(fitToPage);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetFitToPage(XmlBoolean fitToPage) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.set(fitToPage);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetFitToPage() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[19]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean getPrintArea() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[20]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public XmlBoolean xgetPrintArea() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[20]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetPrintArea() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[20]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setPrintArea(boolean printArea) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.setBooleanValue(printArea);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetPrintArea(XmlBoolean printArea) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.set(printArea);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetPrintArea() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[20]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean getFilter() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[21]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public XmlBoolean xgetFilter() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[21]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetFilter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[21]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setFilter(boolean filter) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.setBooleanValue(filter);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetFilter(XmlBoolean filter) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.set(filter);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetFilter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[21]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean getShowAutoFilter() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[22]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public XmlBoolean xgetShowAutoFilter() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[22]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetShowAutoFilter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[22]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setShowAutoFilter(boolean showAutoFilter) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.setBooleanValue(showAutoFilter);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetShowAutoFilter(XmlBoolean showAutoFilter) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.set(showAutoFilter);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetShowAutoFilter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[22]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean getHiddenRows() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[23]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public XmlBoolean xgetHiddenRows() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[23]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetHiddenRows() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[23]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setHiddenRows(boolean hiddenRows) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.setBooleanValue(hiddenRows);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetHiddenRows(XmlBoolean hiddenRows) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.set(hiddenRows);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetHiddenRows() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[23]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean getHiddenColumns() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[24]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public XmlBoolean xgetHiddenColumns() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[24]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetHiddenColumns() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[24]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setHiddenColumns(boolean hiddenColumns) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.setBooleanValue(hiddenColumns);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetHiddenColumns(XmlBoolean hiddenColumns) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.set(hiddenColumns);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetHiddenColumns() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[24]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public STSheetState.Enum getState() {
        STSheetState.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[25]);
            }
            r1 = target == null ? null : (STSheetState.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public STSheetState xgetState() {
        STSheetState target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSheetState) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (STSheetState) get_default_attribute_value(PROPERTY_QNAME[25]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetState() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[25]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setState(STSheetState.Enum state) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.setEnumValue(state);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetState(STSheetState state) {
        synchronized (monitor()) {
            check_orphaned();
            STSheetState target = (STSheetState) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (STSheetState) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.set(state);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetState() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[25]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean getFilterUnique() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[26]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public XmlBoolean xgetFilterUnique() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[26]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetFilterUnique() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[26]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setFilterUnique(boolean filterUnique) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.setBooleanValue(filterUnique);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetFilterUnique(XmlBoolean filterUnique) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.set(filterUnique);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetFilterUnique() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[26]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public STSheetViewType.Enum getView() {
        STSheetViewType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[27]);
            }
            r1 = target == null ? null : (STSheetViewType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public STSheetViewType xgetView() {
        STSheetViewType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSheetViewType) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (STSheetViewType) get_default_attribute_value(PROPERTY_QNAME[27]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetView() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[27]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setView(STSheetViewType.Enum view) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[27]);
            }
            target.setEnumValue(view);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetView(STSheetViewType view) {
        synchronized (monitor()) {
            check_orphaned();
            STSheetViewType target = (STSheetViewType) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (STSheetViewType) get_store().add_attribute_user(PROPERTY_QNAME[27]);
            }
            target.set(view);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetView() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[27]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean getShowRuler() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[28]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public XmlBoolean xgetShowRuler() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[28]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetShowRuler() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[28]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setShowRuler(boolean showRuler) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.setBooleanValue(showRuler);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetShowRuler(XmlBoolean showRuler) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.set(showRuler);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetShowRuler() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[28]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public String getTopLeftCell() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public STCellRef xgetTopLeftCell() {
        STCellRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCellRef) get_store().find_attribute_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public boolean isSetTopLeftCell() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[29]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void setTopLeftCell(String topLeftCell) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[29]);
            }
            target.setStringValue(topLeftCell);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void xsetTopLeftCell(STCellRef topLeftCell) {
        synchronized (monitor()) {
            check_orphaned();
            STCellRef target = (STCellRef) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            if (target == null) {
                target = (STCellRef) get_store().add_attribute_user(PROPERTY_QNAME[29]);
            }
            target.set(topLeftCell);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView
    public void unsetTopLeftCell() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[29]);
        }
    }
}
