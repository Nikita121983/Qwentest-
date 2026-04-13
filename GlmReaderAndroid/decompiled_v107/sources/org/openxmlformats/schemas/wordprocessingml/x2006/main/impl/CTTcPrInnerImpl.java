package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCellMergeTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;

/* loaded from: classes12.dex */
public class CTTcPrInnerImpl extends CTTcPrBaseImpl implements CTTcPrInner {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "cellIns"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "cellDel"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "cellMerge")};
    private static final long serialVersionUID = 1;

    public CTTcPrInnerImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner
    public CTTrackChange getCellIns() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            CTTrackChange target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTrackChange = target == null ? null : target;
        }
        return cTTrackChange;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner
    public boolean isSetCellIns() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner
    public void setCellIns(CTTrackChange cellIns) {
        generatedSetterHelperImpl(cellIns, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner
    public CTTrackChange addNewCellIns() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner
    public void unsetCellIns() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner
    public CTTrackChange getCellDel() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            CTTrackChange target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTrackChange = target == null ? null : target;
        }
        return cTTrackChange;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner
    public boolean isSetCellDel() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner
    public void setCellDel(CTTrackChange cellDel) {
        generatedSetterHelperImpl(cellDel, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner
    public CTTrackChange addNewCellDel() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner
    public void unsetCellDel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner
    public CTCellMergeTrackChange getCellMerge() {
        CTCellMergeTrackChange cTCellMergeTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            CTCellMergeTrackChange target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTCellMergeTrackChange = target == null ? null : target;
        }
        return cTCellMergeTrackChange;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner
    public boolean isSetCellMerge() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner
    public void setCellMerge(CTCellMergeTrackChange cellMerge) {
        generatedSetterHelperImpl(cellMerge, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner
    public CTCellMergeTrackChange addNewCellMerge() {
        CTCellMergeTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner
    public void unsetCellMerge() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }
}
