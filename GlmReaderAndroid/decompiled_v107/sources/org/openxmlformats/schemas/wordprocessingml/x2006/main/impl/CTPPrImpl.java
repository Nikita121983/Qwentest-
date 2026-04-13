package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

/* loaded from: classes12.dex */
public class CTPPrImpl extends CTPPrBaseImpl implements CTPPr {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "rPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "sectPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "pPrChange")};
    private static final long serialVersionUID = 1;

    public CTPPrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr
    public CTParaRPr getRPr() {
        CTParaRPr cTParaRPr;
        synchronized (monitor()) {
            check_orphaned();
            CTParaRPr target = (CTParaRPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTParaRPr = target == null ? null : target;
        }
        return cTParaRPr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr
    public boolean isSetRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr
    public void setRPr(CTParaRPr rPr) {
        generatedSetterHelperImpl(rPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr
    public CTParaRPr addNewRPr() {
        CTParaRPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTParaRPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr
    public void unsetRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr
    public CTSectPr getSectPr() {
        CTSectPr cTSectPr;
        synchronized (monitor()) {
            check_orphaned();
            CTSectPr target = (CTSectPr) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTSectPr = target == null ? null : target;
        }
        return cTSectPr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr
    public boolean isSetSectPr() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr
    public void setSectPr(CTSectPr sectPr) {
        generatedSetterHelperImpl(sectPr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr
    public CTSectPr addNewSectPr() {
        CTSectPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSectPr) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr
    public void unsetSectPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr
    public CTPPrChange getPPrChange() {
        CTPPrChange cTPPrChange;
        synchronized (monitor()) {
            check_orphaned();
            CTPPrChange target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTPPrChange = target == null ? null : target;
        }
        return cTPPrChange;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr
    public boolean isSetPPrChange() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr
    public void setPPrChange(CTPPrChange pPrChange) {
        generatedSetterHelperImpl(pPrChange, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr
    public CTPPrChange addNewPPrChange() {
        CTPPrChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr
    public void unsetPPrChange() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }
}
