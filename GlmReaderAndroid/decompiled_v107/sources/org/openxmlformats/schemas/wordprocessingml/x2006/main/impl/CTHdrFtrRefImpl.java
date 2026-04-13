package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtrRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;

/* loaded from: classes12.dex */
public class CTHdrFtrRefImpl extends CTRelImpl implements CTHdrFtrRef {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY)};
    private static final long serialVersionUID = 1;

    public CTHdrFtrRefImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtrRef
    public STHdrFtr.Enum getType() {
        STHdrFtr.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STHdrFtr.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtrRef
    public STHdrFtr xgetType() {
        STHdrFtr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STHdrFtr) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtrRef
    public void setType(STHdrFtr.Enum type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(type);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtrRef
    public void xsetType(STHdrFtr type) {
        synchronized (monitor()) {
            check_orphaned();
            STHdrFtr target = (STHdrFtr) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STHdrFtr) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(type);
        }
    }
}
