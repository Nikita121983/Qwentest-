package com.microsoft.schemas.office.office.impl;

import com.microsoft.schemas.office.office.CTShapeLayout;
import com.microsoft.schemas.office.office.ShapelayoutDocument;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class ShapelayoutDocumentImpl extends XmlComplexContentImpl implements ShapelayoutDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-microsoft-com:office:office", "shapelayout")};
    private static final long serialVersionUID = 1;

    public ShapelayoutDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.office.ShapelayoutDocument
    public CTShapeLayout getShapelayout() {
        CTShapeLayout cTShapeLayout;
        synchronized (monitor()) {
            check_orphaned();
            CTShapeLayout target = (CTShapeLayout) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTShapeLayout = target == null ? null : target;
        }
        return cTShapeLayout;
    }

    @Override // com.microsoft.schemas.office.office.ShapelayoutDocument
    public void setShapelayout(CTShapeLayout shapelayout) {
        generatedSetterHelperImpl(shapelayout, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.office.ShapelayoutDocument
    public CTShapeLayout addNewShapelayout() {
        CTShapeLayout target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShapeLayout) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
