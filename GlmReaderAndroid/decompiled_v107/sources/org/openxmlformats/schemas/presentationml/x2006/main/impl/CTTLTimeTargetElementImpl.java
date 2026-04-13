package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEmbeddedWAVAudioFile;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmpty;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLSubShapeId;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement;

/* loaded from: classes11.dex */
public class CTTLTimeTargetElementImpl extends XmlComplexContentImpl implements CTTLTimeTargetElement {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "sldTgt"), new QName(XSSFRelation.NS_PRESENTATIONML, "sndTgt"), new QName(XSSFRelation.NS_PRESENTATIONML, "spTgt"), new QName(XSSFRelation.NS_PRESENTATIONML, "inkTgt")};
    private static final long serialVersionUID = 1;

    public CTTLTimeTargetElementImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public CTEmpty getSldTgt() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            CTEmpty target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTEmpty = target == null ? null : target;
        }
        return cTEmpty;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public boolean isSetSldTgt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public void setSldTgt(CTEmpty sldTgt) {
        generatedSetterHelperImpl(sldTgt, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public CTEmpty addNewSldTgt() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public void unsetSldTgt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public CTEmbeddedWAVAudioFile getSndTgt() {
        CTEmbeddedWAVAudioFile cTEmbeddedWAVAudioFile;
        synchronized (monitor()) {
            check_orphaned();
            CTEmbeddedWAVAudioFile target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTEmbeddedWAVAudioFile = target == null ? null : target;
        }
        return cTEmbeddedWAVAudioFile;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public boolean isSetSndTgt() {
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public void setSndTgt(CTEmbeddedWAVAudioFile sndTgt) {
        generatedSetterHelperImpl(sndTgt, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public CTEmbeddedWAVAudioFile addNewSndTgt() {
        CTEmbeddedWAVAudioFile target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public void unsetSndTgt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public CTTLShapeTargetElement getSpTgt() {
        CTTLShapeTargetElement cTTLShapeTargetElement;
        synchronized (monitor()) {
            check_orphaned();
            CTTLShapeTargetElement target = (CTTLShapeTargetElement) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTTLShapeTargetElement = target == null ? null : target;
        }
        return cTTLShapeTargetElement;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public boolean isSetSpTgt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public void setSpTgt(CTTLShapeTargetElement spTgt) {
        generatedSetterHelperImpl(spTgt, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public CTTLShapeTargetElement addNewSpTgt() {
        CTTLShapeTargetElement target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLShapeTargetElement) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public void unsetSpTgt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public CTTLSubShapeId getInkTgt() {
        CTTLSubShapeId cTTLSubShapeId;
        synchronized (monitor()) {
            check_orphaned();
            CTTLSubShapeId target = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTTLSubShapeId = target == null ? null : target;
        }
        return cTTLSubShapeId;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public boolean isSetInkTgt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public void setInkTgt(CTTLSubShapeId inkTgt) {
        generatedSetterHelperImpl(inkTgt, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public CTTLSubShapeId addNewInkTgt() {
        CTTLSubShapeId target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
    public void unsetInkTgt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
