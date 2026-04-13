package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyContent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr;

/* loaded from: classes12.dex */
public class CTRubyImpl extends XmlComplexContentImpl implements CTRuby {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "rubyPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rt"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rubyBase")};
    private static final long serialVersionUID = 1;

    public CTRubyImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby
    public CTRubyPr getRubyPr() {
        CTRubyPr cTRubyPr;
        synchronized (monitor()) {
            check_orphaned();
            CTRubyPr target = (CTRubyPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTRubyPr = target == null ? null : target;
        }
        return cTRubyPr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby
    public void setRubyPr(CTRubyPr rubyPr) {
        generatedSetterHelperImpl(rubyPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby
    public CTRubyPr addNewRubyPr() {
        CTRubyPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRubyPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby
    public CTRubyContent getRt() {
        CTRubyContent cTRubyContent;
        synchronized (monitor()) {
            check_orphaned();
            CTRubyContent target = (CTRubyContent) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTRubyContent = target == null ? null : target;
        }
        return cTRubyContent;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby
    public void setRt(CTRubyContent rt) {
        generatedSetterHelperImpl(rt, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby
    public CTRubyContent addNewRt() {
        CTRubyContent target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRubyContent) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby
    public CTRubyContent getRubyBase() {
        CTRubyContent cTRubyContent;
        synchronized (monitor()) {
            check_orphaned();
            CTRubyContent target = (CTRubyContent) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTRubyContent = target == null ? null : target;
        }
        return cTRubyContent;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby
    public void setRubyBase(CTRubyContent rubyBase) {
        generatedSetterHelperImpl(rubyBase, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby
    public CTRubyContent addNewRubyBase() {
        CTRubyContent target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRubyContent) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }
}
