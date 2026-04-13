package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupLocking;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGroupDrawingShapeProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;

/* loaded from: classes11.dex */
public class CTNonVisualGroupDrawingShapePropsImpl extends XmlComplexContentImpl implements CTNonVisualGroupDrawingShapeProps {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "grpSpLocks"), new QName(XSSFRelation.NS_DRAWINGML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTNonVisualGroupDrawingShapePropsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGroupDrawingShapeProps
    public CTGroupLocking getGrpSpLocks() {
        CTGroupLocking cTGroupLocking;
        synchronized (monitor()) {
            check_orphaned();
            CTGroupLocking target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTGroupLocking = target == null ? null : target;
        }
        return cTGroupLocking;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGroupDrawingShapeProps
    public boolean isSetGrpSpLocks() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGroupDrawingShapeProps
    public void setGrpSpLocks(CTGroupLocking grpSpLocks) {
        generatedSetterHelperImpl(grpSpLocks, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGroupDrawingShapeProps
    public CTGroupLocking addNewGrpSpLocks() {
        CTGroupLocking target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGroupDrawingShapeProps
    public void unsetGrpSpLocks() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGroupDrawingShapeProps
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGroupDrawingShapeProps
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGroupDrawingShapeProps
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGroupDrawingShapeProps
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGroupDrawingShapeProps
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
