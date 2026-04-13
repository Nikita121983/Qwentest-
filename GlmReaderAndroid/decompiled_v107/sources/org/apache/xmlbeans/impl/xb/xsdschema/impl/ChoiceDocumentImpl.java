package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.ChoiceDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;

/* loaded from: classes11.dex */
public class ChoiceDocumentImpl extends XmlComplexContentImpl implements ChoiceDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "choice")};
    private static final long serialVersionUID = 1;

    public ChoiceDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ChoiceDocument
    public ExplicitGroup getChoice() {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            ExplicitGroup target = (ExplicitGroup) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            explicitGroup = target == null ? null : target;
        }
        return explicitGroup;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ChoiceDocument
    public void setChoice(ExplicitGroup choice) {
        generatedSetterHelperImpl(choice, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ChoiceDocument
    public ExplicitGroup addNewChoice() {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
