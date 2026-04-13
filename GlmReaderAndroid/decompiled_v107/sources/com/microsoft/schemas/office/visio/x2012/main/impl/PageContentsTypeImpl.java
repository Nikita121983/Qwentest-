package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.ConnectsType;
import com.microsoft.schemas.office.visio.x2012.main.PageContentsType;
import com.microsoft.schemas.office.visio.x2012.main.ShapesType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class PageContentsTypeImpl extends XmlComplexContentImpl implements PageContentsType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "Shapes"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "Connects")};
    private static final long serialVersionUID = 1;

    public PageContentsTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageContentsType
    public ShapesType getShapes() {
        ShapesType shapesType;
        synchronized (monitor()) {
            check_orphaned();
            ShapesType target = (ShapesType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            shapesType = target == null ? null : target;
        }
        return shapesType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageContentsType
    public boolean isSetShapes() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageContentsType
    public void setShapes(ShapesType shapes) {
        generatedSetterHelperImpl(shapes, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageContentsType
    public ShapesType addNewShapes() {
        ShapesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ShapesType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageContentsType
    public void unsetShapes() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageContentsType
    public ConnectsType getConnects() {
        ConnectsType connectsType;
        synchronized (monitor()) {
            check_orphaned();
            ConnectsType target = (ConnectsType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            connectsType = target == null ? null : target;
        }
        return connectsType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageContentsType
    public boolean isSetConnects() {
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

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageContentsType
    public void setConnects(ConnectsType connects) {
        generatedSetterHelperImpl(connects, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageContentsType
    public ConnectsType addNewConnects() {
        ConnectsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ConnectsType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageContentsType
    public void unsetConnects() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
