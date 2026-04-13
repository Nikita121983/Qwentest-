package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFootnotes;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.FootnotesDocument;

/* loaded from: classes12.dex */
public class FootnotesDocumentImpl extends XmlComplexContentImpl implements FootnotesDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "footnotes")};
    private static final long serialVersionUID = 1;

    public FootnotesDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.FootnotesDocument
    public CTFootnotes getFootnotes() {
        CTFootnotes cTFootnotes;
        synchronized (monitor()) {
            check_orphaned();
            CTFootnotes target = (CTFootnotes) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTFootnotes = target == null ? null : target;
        }
        return cTFootnotes;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.FootnotesDocument
    public void setFootnotes(CTFootnotes footnotes) {
        generatedSetterHelperImpl(footnotes, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.FootnotesDocument
    public CTFootnotes addNewFootnotes() {
        CTFootnotes target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFootnotes) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
