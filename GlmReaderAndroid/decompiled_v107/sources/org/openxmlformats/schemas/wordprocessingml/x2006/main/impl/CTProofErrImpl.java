package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STProofErr;

/* loaded from: classes12.dex */
public class CTProofErrImpl extends XmlComplexContentImpl implements CTProofErr {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY)};
    private static final long serialVersionUID = 1;

    public CTProofErrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr
    public STProofErr.Enum getType() {
        STProofErr.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STProofErr.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr
    public STProofErr xgetType() {
        STProofErr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STProofErr) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr
    public void setType(STProofErr.Enum type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(type);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr
    public void xsetType(STProofErr type) {
        synchronized (monitor()) {
            check_orphaned();
            STProofErr target = (STProofErr) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STProofErr) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(type);
        }
    }
}
