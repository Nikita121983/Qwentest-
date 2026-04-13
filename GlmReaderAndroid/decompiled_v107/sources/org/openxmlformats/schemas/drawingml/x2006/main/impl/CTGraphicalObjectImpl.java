package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;

/* loaded from: classes11.dex */
public class CTGraphicalObjectImpl extends XmlComplexContentImpl implements CTGraphicalObject {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "graphicData")};
    private static final long serialVersionUID = 1;

    public CTGraphicalObjectImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject
    public CTGraphicalObjectData getGraphicData() {
        CTGraphicalObjectData cTGraphicalObjectData;
        synchronized (monitor()) {
            check_orphaned();
            CTGraphicalObjectData target = (CTGraphicalObjectData) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTGraphicalObjectData = target == null ? null : target;
        }
        return cTGraphicalObjectData;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject
    public void setGraphicData(CTGraphicalObjectData graphicData) {
        generatedSetterHelperImpl(graphicData, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject
    public CTGraphicalObjectData addNewGraphicData() {
        CTGraphicalObjectData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGraphicalObjectData) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
