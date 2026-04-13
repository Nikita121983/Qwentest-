package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAnimationElementChoice;
import org.openxmlformats.schemas.drawingml.x2006.main.STDrawingElementId;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmpty;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleChartTargetElement;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLSubShapeId;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTextTargetElement;

/* loaded from: classes11.dex */
public class CTTLShapeTargetElementImpl extends XmlComplexContentImpl implements CTTLShapeTargetElement {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "bg"), new QName(XSSFRelation.NS_PRESENTATIONML, "subSp"), new QName(XSSFRelation.NS_PRESENTATIONML, "oleChartEl"), new QName(XSSFRelation.NS_PRESENTATIONML, "txEl"), new QName(XSSFRelation.NS_PRESENTATIONML, "graphicEl"), new QName("", "spid")};
    private static final long serialVersionUID = 1;

    public CTTLShapeTargetElementImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public CTEmpty getBg() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            CTEmpty target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTEmpty = target == null ? null : target;
        }
        return cTEmpty;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public boolean isSetBg() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public void setBg(CTEmpty bg) {
        generatedSetterHelperImpl(bg, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public CTEmpty addNewBg() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public void unsetBg() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public CTTLSubShapeId getSubSp() {
        CTTLSubShapeId cTTLSubShapeId;
        synchronized (monitor()) {
            check_orphaned();
            CTTLSubShapeId target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTLSubShapeId = target == null ? null : target;
        }
        return cTTLSubShapeId;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public boolean isSetSubSp() {
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public void setSubSp(CTTLSubShapeId subSp) {
        generatedSetterHelperImpl(subSp, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public CTTLSubShapeId addNewSubSp() {
        CTTLSubShapeId target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public void unsetSubSp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public CTTLOleChartTargetElement getOleChartEl() {
        CTTLOleChartTargetElement cTTLOleChartTargetElement;
        synchronized (monitor()) {
            check_orphaned();
            CTTLOleChartTargetElement target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTTLOleChartTargetElement = target == null ? null : target;
        }
        return cTTLOleChartTargetElement;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public boolean isSetOleChartEl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public void setOleChartEl(CTTLOleChartTargetElement oleChartEl) {
        generatedSetterHelperImpl(oleChartEl, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public CTTLOleChartTargetElement addNewOleChartEl() {
        CTTLOleChartTargetElement target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public void unsetOleChartEl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public CTTLTextTargetElement getTxEl() {
        CTTLTextTargetElement cTTLTextTargetElement;
        synchronized (monitor()) {
            check_orphaned();
            CTTLTextTargetElement target = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTTLTextTargetElement = target == null ? null : target;
        }
        return cTTLTextTargetElement;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public boolean isSetTxEl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public void setTxEl(CTTLTextTargetElement txEl) {
        generatedSetterHelperImpl(txEl, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public CTTLTextTargetElement addNewTxEl() {
        CTTLTextTargetElement target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public void unsetTxEl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public CTAnimationElementChoice getGraphicEl() {
        CTAnimationElementChoice cTAnimationElementChoice;
        synchronized (monitor()) {
            check_orphaned();
            CTAnimationElementChoice target = get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTAnimationElementChoice = target == null ? null : target;
        }
        return cTAnimationElementChoice;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public boolean isSetGraphicEl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public void setGraphicEl(CTAnimationElementChoice graphicEl) {
        generatedSetterHelperImpl(graphicEl, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public CTAnimationElementChoice addNewGraphicEl() {
        CTAnimationElementChoice target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public void unsetGraphicEl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public long getSpid() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public STDrawingElementId xgetSpid() {
        STDrawingElementId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDrawingElementId) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public void setSpid(long spid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setLongValue(spid);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
    public void xsetSpid(STDrawingElementId spid) {
        synchronized (monitor()) {
            check_orphaned();
            STDrawingElementId target = (STDrawingElementId) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STDrawingElementId) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(spid);
        }
    }
}
