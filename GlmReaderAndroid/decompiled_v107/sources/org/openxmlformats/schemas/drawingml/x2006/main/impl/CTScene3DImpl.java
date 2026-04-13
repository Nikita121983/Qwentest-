package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBackdrop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCamera;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLightRig;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D;

/* loaded from: classes11.dex */
public class CTScene3DImpl extends XmlComplexContentImpl implements CTScene3D {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "camera"), new QName(XSSFRelation.NS_DRAWINGML, "lightRig"), new QName(XSSFRelation.NS_DRAWINGML, "backdrop"), new QName(XSSFRelation.NS_DRAWINGML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTScene3DImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D
    public CTCamera getCamera() {
        CTCamera cTCamera;
        synchronized (monitor()) {
            check_orphaned();
            CTCamera target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTCamera = target == null ? null : target;
        }
        return cTCamera;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D
    public void setCamera(CTCamera camera) {
        generatedSetterHelperImpl(camera, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D
    public CTCamera addNewCamera() {
        CTCamera target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D
    public CTLightRig getLightRig() {
        CTLightRig cTLightRig;
        synchronized (monitor()) {
            check_orphaned();
            CTLightRig target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTLightRig = target == null ? null : target;
        }
        return cTLightRig;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D
    public void setLightRig(CTLightRig lightRig) {
        generatedSetterHelperImpl(lightRig, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D
    public CTLightRig addNewLightRig() {
        CTLightRig target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D
    public CTBackdrop getBackdrop() {
        CTBackdrop cTBackdrop;
        synchronized (monitor()) {
            check_orphaned();
            CTBackdrop target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTBackdrop = target == null ? null : target;
        }
        return cTBackdrop;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D
    public boolean isSetBackdrop() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D
    public void setBackdrop(CTBackdrop backdrop) {
        generatedSetterHelperImpl(backdrop, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D
    public CTBackdrop addNewBackdrop() {
        CTBackdrop target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D
    public void unsetBackdrop() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
