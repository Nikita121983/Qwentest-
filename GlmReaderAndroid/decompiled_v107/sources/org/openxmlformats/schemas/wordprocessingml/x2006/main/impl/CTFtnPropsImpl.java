package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnPos;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumFmt;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumRestart;

/* loaded from: classes12.dex */
public class CTFtnPropsImpl extends XmlComplexContentImpl implements CTFtnProps {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "pos"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "numFmt"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "numStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "numRestart")};
    private static final long serialVersionUID = 1;

    public CTFtnPropsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public CTFtnPos getPos() {
        CTFtnPos cTFtnPos;
        synchronized (monitor()) {
            check_orphaned();
            CTFtnPos target = (CTFtnPos) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTFtnPos = target == null ? null : target;
        }
        return cTFtnPos;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public boolean isSetPos() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public void setPos(CTFtnPos pos) {
        generatedSetterHelperImpl(pos, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public CTFtnPos addNewPos() {
        CTFtnPos target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFtnPos) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public void unsetPos() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public CTNumFmt getNumFmt() {
        CTNumFmt cTNumFmt;
        synchronized (monitor()) {
            check_orphaned();
            CTNumFmt target = (CTNumFmt) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTNumFmt = target == null ? null : target;
        }
        return cTNumFmt;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public boolean isSetNumFmt() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public void setNumFmt(CTNumFmt numFmt) {
        generatedSetterHelperImpl(numFmt, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public CTNumFmt addNewNumFmt() {
        CTNumFmt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNumFmt) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public void unsetNumFmt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public CTDecimalNumber getNumStart() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumber target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTDecimalNumber = target == null ? null : target;
        }
        return cTDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public boolean isSetNumStart() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public void setNumStart(CTDecimalNumber numStart) {
        generatedSetterHelperImpl(numStart, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public CTDecimalNumber addNewNumStart() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public void unsetNumStart() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public CTNumRestart getNumRestart() {
        CTNumRestart cTNumRestart;
        synchronized (monitor()) {
            check_orphaned();
            CTNumRestart target = (CTNumRestart) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTNumRestart = target == null ? null : target;
        }
        return cTNumRestart;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public boolean isSetNumRestart() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public void setNumRestart(CTNumRestart numRestart) {
        generatedSetterHelperImpl(numRestart, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public CTNumRestart addNewNumRestart() {
        CTNumRestart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNumRestart) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
    public void unsetNumRestart() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
