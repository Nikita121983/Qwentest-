package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTControl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObjectEmbed;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObjectLink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel;

/* loaded from: classes12.dex */
public class CTObjectImpl extends XmlComplexContentImpl implements CTObject {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "drawing"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "control"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "objectLink"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "objectEmbed"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "movie"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "dxaOrig"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "dyaOrig")};
    private static final long serialVersionUID = 1;

    public CTObjectImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public CTDrawing getDrawing() {
        CTDrawing cTDrawing;
        synchronized (monitor()) {
            check_orphaned();
            CTDrawing target = (CTDrawing) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTDrawing = target == null ? null : target;
        }
        return cTDrawing;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public boolean isSetDrawing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public void setDrawing(CTDrawing drawing) {
        generatedSetterHelperImpl(drawing, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public CTDrawing addNewDrawing() {
        CTDrawing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDrawing) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public void unsetDrawing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public CTControl getControl() {
        CTControl cTControl;
        synchronized (monitor()) {
            check_orphaned();
            CTControl target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTControl = target == null ? null : target;
        }
        return cTControl;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public boolean isSetControl() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public void setControl(CTControl control) {
        generatedSetterHelperImpl(control, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public CTControl addNewControl() {
        CTControl target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public void unsetControl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public CTObjectLink getObjectLink() {
        CTObjectLink cTObjectLink;
        synchronized (monitor()) {
            check_orphaned();
            CTObjectLink target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTObjectLink = target == null ? null : target;
        }
        return cTObjectLink;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public boolean isSetObjectLink() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public void setObjectLink(CTObjectLink objectLink) {
        generatedSetterHelperImpl(objectLink, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public CTObjectLink addNewObjectLink() {
        CTObjectLink target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public void unsetObjectLink() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public CTObjectEmbed getObjectEmbed() {
        CTObjectEmbed cTObjectEmbed;
        synchronized (monitor()) {
            check_orphaned();
            CTObjectEmbed target = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTObjectEmbed = target == null ? null : target;
        }
        return cTObjectEmbed;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public boolean isSetObjectEmbed() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public void setObjectEmbed(CTObjectEmbed objectEmbed) {
        generatedSetterHelperImpl(objectEmbed, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public CTObjectEmbed addNewObjectEmbed() {
        CTObjectEmbed target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public void unsetObjectEmbed() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public CTRel getMovie() {
        CTRel cTRel;
        synchronized (monitor()) {
            check_orphaned();
            CTRel target = (CTRel) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTRel = target == null ? null : target;
        }
        return cTRel;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public boolean isSetMovie() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public void setMovie(CTRel movie) {
        generatedSetterHelperImpl(movie, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public CTRel addNewMovie() {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRel) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public void unsetMovie() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public Object getDxaOrig() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public STTwipsMeasure xgetDxaOrig() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public boolean isSetDxaOrig() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public void setDxaOrig(Object dxaOrig) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setObjectValue(dxaOrig);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public void xsetDxaOrig(STTwipsMeasure dxaOrig) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(dxaOrig);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public void unsetDxaOrig() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public Object getDyaOrig() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public STTwipsMeasure xgetDyaOrig() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public boolean isSetDyaOrig() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public void setDyaOrig(Object dyaOrig) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setObjectValue(dyaOrig);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public void xsetDyaOrig(STTwipsMeasure dyaOrig) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(dyaOrig);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject
    public void unsetDyaOrig() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }
}
