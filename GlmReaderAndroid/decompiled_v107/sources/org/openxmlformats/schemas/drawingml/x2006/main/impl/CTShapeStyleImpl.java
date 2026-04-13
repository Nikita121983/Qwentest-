package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference;

/* loaded from: classes11.dex */
public class CTShapeStyleImpl extends XmlComplexContentImpl implements CTShapeStyle {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "lnRef"), new QName(XSSFRelation.NS_DRAWINGML, "fillRef"), new QName(XSSFRelation.NS_DRAWINGML, "effectRef"), new QName(XSSFRelation.NS_DRAWINGML, "fontRef")};
    private static final long serialVersionUID = 1;

    public CTShapeStyleImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle
    public CTStyleMatrixReference getLnRef() {
        CTStyleMatrixReference cTStyleMatrixReference;
        synchronized (monitor()) {
            check_orphaned();
            CTStyleMatrixReference target = (CTStyleMatrixReference) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTStyleMatrixReference = target == null ? null : target;
        }
        return cTStyleMatrixReference;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle
    public void setLnRef(CTStyleMatrixReference lnRef) {
        generatedSetterHelperImpl(lnRef, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle
    public CTStyleMatrixReference addNewLnRef() {
        CTStyleMatrixReference target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStyleMatrixReference) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle
    public CTStyleMatrixReference getFillRef() {
        CTStyleMatrixReference cTStyleMatrixReference;
        synchronized (monitor()) {
            check_orphaned();
            CTStyleMatrixReference target = (CTStyleMatrixReference) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTStyleMatrixReference = target == null ? null : target;
        }
        return cTStyleMatrixReference;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle
    public void setFillRef(CTStyleMatrixReference fillRef) {
        generatedSetterHelperImpl(fillRef, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle
    public CTStyleMatrixReference addNewFillRef() {
        CTStyleMatrixReference target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStyleMatrixReference) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle
    public CTStyleMatrixReference getEffectRef() {
        CTStyleMatrixReference cTStyleMatrixReference;
        synchronized (monitor()) {
            check_orphaned();
            CTStyleMatrixReference target = (CTStyleMatrixReference) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTStyleMatrixReference = target == null ? null : target;
        }
        return cTStyleMatrixReference;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle
    public void setEffectRef(CTStyleMatrixReference effectRef) {
        generatedSetterHelperImpl(effectRef, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle
    public CTStyleMatrixReference addNewEffectRef() {
        CTStyleMatrixReference target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStyleMatrixReference) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle
    public CTFontReference getFontRef() {
        CTFontReference cTFontReference;
        synchronized (monitor()) {
            check_orphaned();
            CTFontReference target = (CTFontReference) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTFontReference = target == null ? null : target;
        }
        return cTFontReference;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle
    public void setFontRef(CTFontReference fontRef) {
        generatedSetterHelperImpl(fontRef, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle
    public CTFontReference addNewFontRef() {
        CTFontReference target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFontReference) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }
}
