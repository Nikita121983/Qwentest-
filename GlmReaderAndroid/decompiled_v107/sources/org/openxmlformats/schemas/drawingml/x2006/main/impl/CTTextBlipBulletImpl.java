package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBlipBullet;

/* loaded from: classes11.dex */
public class CTTextBlipBulletImpl extends XmlComplexContentImpl implements CTTextBlipBullet {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "blip")};
    private static final long serialVersionUID = 1;

    public CTTextBlipBulletImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBlipBullet
    public CTBlip getBlip() {
        CTBlip cTBlip;
        synchronized (monitor()) {
            check_orphaned();
            CTBlip target = (CTBlip) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTBlip = target == null ? null : target;
        }
        return cTBlip;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBlipBullet
    public void setBlip(CTBlip blip) {
        generatedSetterHelperImpl(blip, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBlipBullet
    public CTBlip addNewBlip() {
        CTBlip target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBlip) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
