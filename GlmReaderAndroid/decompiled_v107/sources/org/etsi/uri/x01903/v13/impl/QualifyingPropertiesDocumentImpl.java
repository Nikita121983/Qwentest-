package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.QualifyingPropertiesDocument;
import org.etsi.uri.x01903.v13.QualifyingPropertiesType;

/* loaded from: classes11.dex */
public class QualifyingPropertiesDocumentImpl extends XmlComplexContentImpl implements QualifyingPropertiesDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "QualifyingProperties")};
    private static final long serialVersionUID = 1;

    public QualifyingPropertiesDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesDocument
    public QualifyingPropertiesType getQualifyingProperties() {
        QualifyingPropertiesType qualifyingPropertiesType;
        synchronized (monitor()) {
            check_orphaned();
            QualifyingPropertiesType target = (QualifyingPropertiesType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            qualifyingPropertiesType = target == null ? null : target;
        }
        return qualifyingPropertiesType;
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesDocument
    public void setQualifyingProperties(QualifyingPropertiesType qualifyingProperties) {
        generatedSetterHelperImpl(qualifyingProperties, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesDocument
    public QualifyingPropertiesType addNewQualifyingProperties() {
        QualifyingPropertiesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (QualifyingPropertiesType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
