package org.openxmlformats.schemas.drawingml.x2006.picture.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPictureNonVisual;

/* loaded from: classes11.dex */
public class CTPictureImpl extends XmlComplexContentImpl implements CTPicture {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/picture", "nvPicPr"), new QName("http://schemas.openxmlformats.org/drawingml/2006/picture", "blipFill"), new QName("http://schemas.openxmlformats.org/drawingml/2006/picture", "spPr")};
    private static final long serialVersionUID = 1;

    public CTPictureImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture
    public CTPictureNonVisual getNvPicPr() {
        CTPictureNonVisual cTPictureNonVisual;
        synchronized (monitor()) {
            check_orphaned();
            CTPictureNonVisual target = (CTPictureNonVisual) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTPictureNonVisual = target == null ? null : target;
        }
        return cTPictureNonVisual;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture
    public void setNvPicPr(CTPictureNonVisual nvPicPr) {
        generatedSetterHelperImpl(nvPicPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture
    public CTPictureNonVisual addNewNvPicPr() {
        CTPictureNonVisual target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPictureNonVisual) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture
    public CTBlipFillProperties getBlipFill() {
        CTBlipFillProperties cTBlipFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTBlipFillProperties target = (CTBlipFillProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTBlipFillProperties = target == null ? null : target;
        }
        return cTBlipFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture
    public void setBlipFill(CTBlipFillProperties blipFill) {
        generatedSetterHelperImpl(blipFill, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture
    public CTBlipFillProperties addNewBlipFill() {
        CTBlipFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBlipFillProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture
    public CTShapeProperties getSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTShapeProperties target = (CTShapeProperties) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTShapeProperties = target == null ? null : target;
        }
        return cTShapeProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture
    public void setSpPr(CTShapeProperties spPr) {
        generatedSetterHelperImpl(spPr, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture
    public CTShapeProperties addNewSpPr() {
        CTShapeProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShapeProperties) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }
}
