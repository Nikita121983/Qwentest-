package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtContentCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtEndPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr;

/* loaded from: classes12.dex */
public class CTSdtCellImpl extends XmlComplexContentImpl implements CTSdtCell {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "sdtPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "sdtEndPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "sdtContent")};
    private static final long serialVersionUID = 1;

    public CTSdtCellImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell
    public CTSdtPr getSdtPr() {
        CTSdtPr cTSdtPr;
        synchronized (monitor()) {
            check_orphaned();
            CTSdtPr target = (CTSdtPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTSdtPr = target == null ? null : target;
        }
        return cTSdtPr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell
    public boolean isSetSdtPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell
    public void setSdtPr(CTSdtPr sdtPr) {
        generatedSetterHelperImpl(sdtPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell
    public CTSdtPr addNewSdtPr() {
        CTSdtPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell
    public void unsetSdtPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell
    public CTSdtEndPr getSdtEndPr() {
        CTSdtEndPr cTSdtEndPr;
        synchronized (monitor()) {
            check_orphaned();
            CTSdtEndPr target = (CTSdtEndPr) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTSdtEndPr = target == null ? null : target;
        }
        return cTSdtEndPr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell
    public boolean isSetSdtEndPr() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell
    public void setSdtEndPr(CTSdtEndPr sdtEndPr) {
        generatedSetterHelperImpl(sdtEndPr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell
    public CTSdtEndPr addNewSdtEndPr() {
        CTSdtEndPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtEndPr) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell
    public void unsetSdtEndPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell
    public CTSdtContentCell getSdtContent() {
        CTSdtContentCell cTSdtContentCell;
        synchronized (monitor()) {
            check_orphaned();
            CTSdtContentCell target = (CTSdtContentCell) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTSdtContentCell = target == null ? null : target;
        }
        return cTSdtContentCell;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell
    public boolean isSetSdtContent() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell
    public void setSdtContent(CTSdtContentCell sdtContent) {
        generatedSetterHelperImpl(sdtContent, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell
    public CTSdtContentCell addNewSdtContent() {
        CTSdtContentCell target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtContentCell) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell
    public void unsetSdtContent() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }
}
