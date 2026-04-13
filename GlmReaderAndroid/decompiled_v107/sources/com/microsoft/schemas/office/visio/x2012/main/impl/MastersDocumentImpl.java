package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.MastersDocument;
import com.microsoft.schemas.office.visio.x2012.main.MastersType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class MastersDocumentImpl extends XmlComplexContentImpl implements MastersDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "Masters")};
    private static final long serialVersionUID = 1;

    public MastersDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersDocument
    public MastersType getMasters() {
        MastersType mastersType;
        synchronized (monitor()) {
            check_orphaned();
            MastersType target = (MastersType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            mastersType = target == null ? null : target;
        }
        return mastersType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersDocument
    public void setMasters(MastersType masters) {
        generatedSetterHelperImpl(masters, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MastersDocument
    public MastersType addNewMasters() {
        MastersType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (MastersType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
