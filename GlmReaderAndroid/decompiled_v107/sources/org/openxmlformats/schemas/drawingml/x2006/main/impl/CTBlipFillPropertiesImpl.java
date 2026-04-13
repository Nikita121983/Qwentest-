package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStretchInfoProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties;

/* loaded from: classes11.dex */
public class CTBlipFillPropertiesImpl extends XmlComplexContentImpl implements CTBlipFillProperties {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "blip"), new QName(XSSFRelation.NS_DRAWINGML, "srcRect"), new QName(XSSFRelation.NS_DRAWINGML, "tile"), new QName(XSSFRelation.NS_DRAWINGML, "stretch"), new QName("", "dpi"), new QName("", "rotWithShape")};
    private static final long serialVersionUID = 1;

    public CTBlipFillPropertiesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public CTBlip getBlip() {
        CTBlip cTBlip;
        synchronized (monitor()) {
            check_orphaned();
            CTBlip target = (CTBlip) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTBlip = target == null ? null : target;
        }
        return cTBlip;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public boolean isSetBlip() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public void setBlip(CTBlip blip) {
        generatedSetterHelperImpl(blip, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public CTBlip addNewBlip() {
        CTBlip target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBlip) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public void unsetBlip() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public CTRelativeRect getSrcRect() {
        CTRelativeRect cTRelativeRect;
        synchronized (monitor()) {
            check_orphaned();
            CTRelativeRect target = (CTRelativeRect) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTRelativeRect = target == null ? null : target;
        }
        return cTRelativeRect;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public boolean isSetSrcRect() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public void setSrcRect(CTRelativeRect srcRect) {
        generatedSetterHelperImpl(srcRect, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public CTRelativeRect addNewSrcRect() {
        CTRelativeRect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRelativeRect) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public void unsetSrcRect() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public CTTileInfoProperties getTile() {
        CTTileInfoProperties cTTileInfoProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTileInfoProperties target = (CTTileInfoProperties) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTTileInfoProperties = target == null ? null : target;
        }
        return cTTileInfoProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public boolean isSetTile() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public void setTile(CTTileInfoProperties tile) {
        generatedSetterHelperImpl(tile, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public CTTileInfoProperties addNewTile() {
        CTTileInfoProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTileInfoProperties) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public void unsetTile() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public CTStretchInfoProperties getStretch() {
        CTStretchInfoProperties cTStretchInfoProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTStretchInfoProperties target = (CTStretchInfoProperties) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTStretchInfoProperties = target == null ? null : target;
        }
        return cTStretchInfoProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public boolean isSetStretch() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public void setStretch(CTStretchInfoProperties stretch) {
        generatedSetterHelperImpl(stretch, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public CTStretchInfoProperties addNewStretch() {
        CTStretchInfoProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStretchInfoProperties) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public void unsetStretch() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public long getDpi() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public XmlUnsignedInt xgetDpi() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public boolean isSetDpi() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public void setDpi(long dpi) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setLongValue(dpi);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public void xsetDpi(XmlUnsignedInt dpi) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(dpi);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public void unsetDpi() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public boolean getRotWithShape() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public XmlBoolean xgetRotWithShape() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public boolean isSetRotWithShape() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public void setRotWithShape(boolean rotWithShape) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setBooleanValue(rotWithShape);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public void xsetRotWithShape(XmlBoolean rotWithShape) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(rotWithShape);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties
    public void unsetRotWithShape() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }
}
