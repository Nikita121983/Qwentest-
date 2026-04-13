package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrameNonVisual;

/* loaded from: classes11.dex */
public class CTGraphicalObjectFrameImpl extends XmlComplexContentImpl implements CTGraphicalObjectFrame {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "nvGraphicFramePr"), new QName(XSSFRelation.NS_PRESENTATIONML, "xfrm"), new QName(XSSFRelation.NS_DRAWINGML, "graphic"), new QName(XSSFRelation.NS_PRESENTATIONML, "extLst"), new QName("", "bwMode")};
    private static final long serialVersionUID = 1;

    public CTGraphicalObjectFrameImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public CTGraphicalObjectFrameNonVisual getNvGraphicFramePr() {
        CTGraphicalObjectFrameNonVisual cTGraphicalObjectFrameNonVisual;
        synchronized (monitor()) {
            check_orphaned();
            CTGraphicalObjectFrameNonVisual target = (CTGraphicalObjectFrameNonVisual) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTGraphicalObjectFrameNonVisual = target == null ? null : target;
        }
        return cTGraphicalObjectFrameNonVisual;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public void setNvGraphicFramePr(CTGraphicalObjectFrameNonVisual nvGraphicFramePr) {
        generatedSetterHelperImpl(nvGraphicFramePr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public CTGraphicalObjectFrameNonVisual addNewNvGraphicFramePr() {
        CTGraphicalObjectFrameNonVisual target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGraphicalObjectFrameNonVisual) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public CTTransform2D getXfrm() {
        CTTransform2D cTTransform2D;
        synchronized (monitor()) {
            check_orphaned();
            CTTransform2D target = (CTTransform2D) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTransform2D = target == null ? null : target;
        }
        return cTTransform2D;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public void setXfrm(CTTransform2D xfrm) {
        generatedSetterHelperImpl(xfrm, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public CTTransform2D addNewXfrm() {
        CTTransform2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTransform2D) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public CTGraphicalObject getGraphic() {
        CTGraphicalObject cTGraphicalObject;
        synchronized (monitor()) {
            check_orphaned();
            CTGraphicalObject target = (CTGraphicalObject) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTGraphicalObject = target == null ? null : target;
        }
        return cTGraphicalObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public void setGraphic(CTGraphicalObject graphic) {
        generatedSetterHelperImpl(graphic, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public CTGraphicalObject addNewGraphic() {
        CTGraphicalObject target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGraphicalObject) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public CTExtensionListModify getExtLst() {
        CTExtensionListModify cTExtensionListModify;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionListModify target = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTExtensionListModify = target == null ? null : target;
        }
        return cTExtensionListModify;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public void setExtLst(CTExtensionListModify extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public CTExtensionListModify addNewExtLst() {
        CTExtensionListModify target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public STBlackWhiteMode.Enum getBwMode() {
        STBlackWhiteMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            r1 = target == null ? null : (STBlackWhiteMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public STBlackWhiteMode xgetBwMode() {
        STBlackWhiteMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBlackWhiteMode) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public boolean isSetBwMode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public void setBwMode(STBlackWhiteMode.Enum bwMode) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setEnumValue(bwMode);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public void xsetBwMode(STBlackWhiteMode bwMode) {
        synchronized (monitor()) {
            check_orphaned();
            STBlackWhiteMode target = (STBlackWhiteMode) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STBlackWhiteMode) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(bwMode);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame
    public void unsetBwMode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
