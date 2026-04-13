package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTRel;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;

/* loaded from: classes11.dex */
public class CTAbsoluteAnchorImpl extends XmlComplexContentImpl implements CTAbsoluteAnchor {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "pos"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "ext"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "sp"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "grpSp"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "graphicFrame"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "cxnSp"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "pic"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "contentPart"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "clientData")};
    private static final long serialVersionUID = 1;

    public CTAbsoluteAnchorImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTPoint2D getPos() {
        CTPoint2D cTPoint2D;
        synchronized (monitor()) {
            check_orphaned();
            CTPoint2D target = (CTPoint2D) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTPoint2D = target == null ? null : target;
        }
        return cTPoint2D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public void setPos(CTPoint2D pos) {
        generatedSetterHelperImpl(pos, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTPoint2D addNewPos() {
        CTPoint2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPoint2D) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTPositiveSize2D getExt() {
        CTPositiveSize2D cTPositiveSize2D;
        synchronized (monitor()) {
            check_orphaned();
            CTPositiveSize2D target = (CTPositiveSize2D) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTPositiveSize2D = target == null ? null : target;
        }
        return cTPositiveSize2D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public void setExt(CTPositiveSize2D ext) {
        generatedSetterHelperImpl(ext, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTPositiveSize2D addNewExt() {
        CTPositiveSize2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveSize2D) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTShape getSp() {
        CTShape cTShape;
        synchronized (monitor()) {
            check_orphaned();
            CTShape target = (CTShape) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTShape = target == null ? null : target;
        }
        return cTShape;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public boolean isSetSp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public void setSp(CTShape sp) {
        generatedSetterHelperImpl(sp, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTShape addNewSp() {
        CTShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShape) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public void unsetSp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTGroupShape getGrpSp() {
        CTGroupShape cTGroupShape;
        synchronized (monitor()) {
            check_orphaned();
            CTGroupShape target = (CTGroupShape) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTGroupShape = target == null ? null : target;
        }
        return cTGroupShape;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public boolean isSetGrpSp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public void setGrpSp(CTGroupShape grpSp) {
        generatedSetterHelperImpl(grpSp, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTGroupShape addNewGrpSp() {
        CTGroupShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupShape) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public void unsetGrpSp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTGraphicalObjectFrame getGraphicFrame() {
        CTGraphicalObjectFrame cTGraphicalObjectFrame;
        synchronized (monitor()) {
            check_orphaned();
            CTGraphicalObjectFrame target = (CTGraphicalObjectFrame) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTGraphicalObjectFrame = target == null ? null : target;
        }
        return cTGraphicalObjectFrame;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public boolean isSetGraphicFrame() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public void setGraphicFrame(CTGraphicalObjectFrame graphicFrame) {
        generatedSetterHelperImpl(graphicFrame, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTGraphicalObjectFrame addNewGraphicFrame() {
        CTGraphicalObjectFrame target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGraphicalObjectFrame) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public void unsetGraphicFrame() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTConnector getCxnSp() {
        CTConnector cTConnector;
        synchronized (monitor()) {
            check_orphaned();
            CTConnector target = (CTConnector) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTConnector = target == null ? null : target;
        }
        return cTConnector;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public boolean isSetCxnSp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public void setCxnSp(CTConnector cxnSp) {
        generatedSetterHelperImpl(cxnSp, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTConnector addNewCxnSp() {
        CTConnector target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTConnector) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public void unsetCxnSp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTPicture getPic() {
        CTPicture cTPicture;
        synchronized (monitor()) {
            check_orphaned();
            CTPicture target = (CTPicture) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTPicture = target == null ? null : target;
        }
        return cTPicture;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public boolean isSetPic() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public void setPic(CTPicture pic) {
        generatedSetterHelperImpl(pic, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTPicture addNewPic() {
        CTPicture target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPicture) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public void unsetPic() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTRel getContentPart() {
        CTRel cTRel;
        synchronized (monitor()) {
            check_orphaned();
            CTRel target = get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTRel = target == null ? null : target;
        }
        return cTRel;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public boolean isSetContentPart() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public void setContentPart(CTRel contentPart) {
        generatedSetterHelperImpl(contentPart, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTRel addNewContentPart() {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public void unsetContentPart() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTAnchorClientData getClientData() {
        CTAnchorClientData cTAnchorClientData;
        synchronized (monitor()) {
            check_orphaned();
            CTAnchorClientData target = (CTAnchorClientData) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTAnchorClientData = target == null ? null : target;
        }
        return cTAnchorClientData;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public void setClientData(CTAnchorClientData clientData) {
        generatedSetterHelperImpl(clientData, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor
    public CTAnchorClientData addNewClientData() {
        CTAnchorClientData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAnchorClientData) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }
}
