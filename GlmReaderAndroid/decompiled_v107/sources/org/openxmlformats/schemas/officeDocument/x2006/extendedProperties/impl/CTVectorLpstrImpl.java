package org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl;

import javax.xml.namespace.QName;
import org.apache.commons.math3.linear.ConjugateGradient;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTVectorLpstr;

/* loaded from: classes11.dex */
public class CTVectorLpstrImpl extends XmlComplexContentImpl implements CTVectorLpstr {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", ConjugateGradient.VECTOR)};
    private static final long serialVersionUID = 1;

    public CTVectorLpstrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTVectorLpstr
    public CTVector getVector() {
        CTVector cTVector;
        synchronized (monitor()) {
            check_orphaned();
            CTVector target = (CTVector) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTVector = target == null ? null : target;
        }
        return cTVector;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTVectorLpstr
    public void setVector(CTVector vector) {
        generatedSetterHelperImpl(vector, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTVectorLpstr
    public CTVector addNewVector() {
        CTVector target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTVector) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
