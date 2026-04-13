package org.openxmlformats.schemas.drawingml.x2006.picture.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualPictureProperties;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPictureNonVisual;

/* loaded from: classes11.dex */
public class CTPictureNonVisualImpl extends XmlComplexContentImpl implements CTPictureNonVisual {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/picture", "cNvPr"), new QName("http://schemas.openxmlformats.org/drawingml/2006/picture", "cNvPicPr")};
    private static final long serialVersionUID = 1;

    public CTPictureNonVisualImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.CTPictureNonVisual
    public CTNonVisualDrawingProps getCNvPr() {
        CTNonVisualDrawingProps cTNonVisualDrawingProps;
        synchronized (monitor()) {
            check_orphaned();
            CTNonVisualDrawingProps target = (CTNonVisualDrawingProps) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTNonVisualDrawingProps = target == null ? null : target;
        }
        return cTNonVisualDrawingProps;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.CTPictureNonVisual
    public void setCNvPr(CTNonVisualDrawingProps cNvPr) {
        generatedSetterHelperImpl(cNvPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.CTPictureNonVisual
    public CTNonVisualDrawingProps addNewCNvPr() {
        CTNonVisualDrawingProps target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNonVisualDrawingProps) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.CTPictureNonVisual
    public CTNonVisualPictureProperties getCNvPicPr() {
        CTNonVisualPictureProperties cTNonVisualPictureProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTNonVisualPictureProperties target = (CTNonVisualPictureProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTNonVisualPictureProperties = target == null ? null : target;
        }
        return cTNonVisualPictureProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.CTPictureNonVisual
    public void setCNvPicPr(CTNonVisualPictureProperties cNvPicPr) {
        generatedSetterHelperImpl(cNvPicPr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.CTPictureNonVisual
    public CTNonVisualPictureProperties addNewCNvPicPr() {
        CTNonVisualPictureProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNonVisualPictureProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }
}
