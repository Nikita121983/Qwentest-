package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTHeaderFooter;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPageMargins;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPageSetup;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRelId;

/* loaded from: classes11.dex */
public class CTPrintSettingsImpl extends XmlComplexContentImpl implements CTPrintSettings {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "headerFooter"), new QName(XSSFRelation.NS_CHART, "pageMargins"), new QName(XSSFRelation.NS_CHART, "pageSetup"), new QName(XSSFRelation.NS_CHART, "legacyDrawingHF")};
    private static final long serialVersionUID = 1;

    public CTPrintSettingsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public CTHeaderFooter getHeaderFooter() {
        CTHeaderFooter cTHeaderFooter;
        synchronized (monitor()) {
            check_orphaned();
            CTHeaderFooter target = (CTHeaderFooter) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTHeaderFooter = target == null ? null : target;
        }
        return cTHeaderFooter;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public boolean isSetHeaderFooter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public void setHeaderFooter(CTHeaderFooter headerFooter) {
        generatedSetterHelperImpl(headerFooter, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public CTHeaderFooter addNewHeaderFooter() {
        CTHeaderFooter target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHeaderFooter) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public void unsetHeaderFooter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public CTPageMargins getPageMargins() {
        CTPageMargins cTPageMargins;
        synchronized (monitor()) {
            check_orphaned();
            CTPageMargins target = (CTPageMargins) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTPageMargins = target == null ? null : target;
        }
        return cTPageMargins;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public boolean isSetPageMargins() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public void setPageMargins(CTPageMargins pageMargins) {
        generatedSetterHelperImpl(pageMargins, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public CTPageMargins addNewPageMargins() {
        CTPageMargins target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageMargins) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public void unsetPageMargins() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public CTPageSetup getPageSetup() {
        CTPageSetup cTPageSetup;
        synchronized (monitor()) {
            check_orphaned();
            CTPageSetup target = (CTPageSetup) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTPageSetup = target == null ? null : target;
        }
        return cTPageSetup;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public boolean isSetPageSetup() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public void setPageSetup(CTPageSetup pageSetup) {
        generatedSetterHelperImpl(pageSetup, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public CTPageSetup addNewPageSetup() {
        CTPageSetup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageSetup) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public void unsetPageSetup() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public CTRelId getLegacyDrawingHF() {
        CTRelId cTRelId;
        synchronized (monitor()) {
            check_orphaned();
            CTRelId target = (CTRelId) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTRelId = target == null ? null : target;
        }
        return cTRelId;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public boolean isSetLegacyDrawingHF() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public void setLegacyDrawingHF(CTRelId legacyDrawingHF) {
        generatedSetterHelperImpl(legacyDrawingHF, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public CTRelId addNewLegacyDrawingHF() {
        CTRelId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRelId) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings
    public void unsetLegacyDrawingHF() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
