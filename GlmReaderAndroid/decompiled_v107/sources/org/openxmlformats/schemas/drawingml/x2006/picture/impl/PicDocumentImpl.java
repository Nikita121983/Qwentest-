package org.openxmlformats.schemas.drawingml.x2006.picture.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.picture.PicDocument;

/* loaded from: classes11.dex */
public class PicDocumentImpl extends XmlComplexContentImpl implements PicDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/picture", "pic")};
    private static final long serialVersionUID = 1;

    public PicDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.PicDocument
    public CTPicture getPic() {
        CTPicture cTPicture;
        synchronized (monitor()) {
            check_orphaned();
            CTPicture target = (CTPicture) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTPicture = target == null ? null : target;
        }
        return cTPicture;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.PicDocument
    public void setPic(CTPicture pic) {
        generatedSetterHelperImpl(pic, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.picture.PicDocument
    public CTPicture addNewPic() {
        CTPicture target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPicture) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
