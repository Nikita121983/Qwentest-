package com.microsoft.schemas.office.drawing.x2008.diagram.impl;

import com.microsoft.schemas.office.drawing.x2008.diagram.CTShape;
import com.microsoft.schemas.office.drawing.x2008.diagram.CTShapeNonVisual;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.diagram.STModelId;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;

/* loaded from: classes.dex */
public class CTShapeImpl extends XmlComplexContentImpl implements CTShape {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/drawing/2008/diagram", "nvSpPr"), new QName("http://schemas.microsoft.com/office/drawing/2008/diagram", "spPr"), new QName("http://schemas.microsoft.com/office/drawing/2008/diagram", "style"), new QName("http://schemas.microsoft.com/office/drawing/2008/diagram", "txBody"), new QName("http://schemas.microsoft.com/office/drawing/2008/diagram", "txXfrm"), new QName("http://schemas.microsoft.com/office/drawing/2008/diagram", "extLst"), new QName("", "modelId")};
    private static final long serialVersionUID = 1;

    public CTShapeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public CTShapeNonVisual getNvSpPr() {
        CTShapeNonVisual cTShapeNonVisual;
        synchronized (monitor()) {
            check_orphaned();
            CTShapeNonVisual target = (CTShapeNonVisual) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTShapeNonVisual = target == null ? null : target;
        }
        return cTShapeNonVisual;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public void setNvSpPr(CTShapeNonVisual nvSpPr) {
        generatedSetterHelperImpl(nvSpPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public CTShapeNonVisual addNewNvSpPr() {
        CTShapeNonVisual target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShapeNonVisual) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public CTShapeProperties getSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTShapeProperties target = (CTShapeProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTShapeProperties = target == null ? null : target;
        }
        return cTShapeProperties;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public void setSpPr(CTShapeProperties spPr) {
        generatedSetterHelperImpl(spPr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public CTShapeProperties addNewSpPr() {
        CTShapeProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShapeProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public CTShapeStyle getStyle() {
        CTShapeStyle cTShapeStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTShapeStyle target = (CTShapeStyle) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTShapeStyle = target == null ? null : target;
        }
        return cTShapeStyle;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public boolean isSetStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public void setStyle(CTShapeStyle style) {
        generatedSetterHelperImpl(style, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public CTShapeStyle addNewStyle() {
        CTShapeStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShapeStyle) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public void unsetStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public CTTextBody getTxBody() {
        CTTextBody cTTextBody;
        synchronized (monitor()) {
            check_orphaned();
            CTTextBody target = (CTTextBody) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTTextBody = target == null ? null : target;
        }
        return cTTextBody;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public boolean isSetTxBody() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public void setTxBody(CTTextBody txBody) {
        generatedSetterHelperImpl(txBody, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public CTTextBody addNewTxBody() {
        CTTextBody target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextBody) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public void unsetTxBody() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public CTTransform2D getTxXfrm() {
        CTTransform2D cTTransform2D;
        synchronized (monitor()) {
            check_orphaned();
            CTTransform2D target = (CTTransform2D) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTTransform2D = target == null ? null : target;
        }
        return cTTransform2D;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public boolean isSetTxXfrm() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public void setTxXfrm(CTTransform2D txXfrm) {
        generatedSetterHelperImpl(txXfrm, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public CTTransform2D addNewTxXfrm() {
        CTTransform2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTransform2D) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public void unsetTxXfrm() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public Object getModelId() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public STModelId xgetModelId() {
        STModelId target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public void setModelId(Object modelId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setObjectValue(modelId);
        }
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShape
    public void xsetModelId(STModelId modelId) {
        synchronized (monitor()) {
            check_orphaned();
            STModelId target = get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STModelId) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(modelId);
        }
    }
}
