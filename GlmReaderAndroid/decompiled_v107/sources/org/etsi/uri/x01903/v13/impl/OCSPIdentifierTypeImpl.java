package org.etsi.uri.x01903.v13.impl;

import java.util.Calendar;
import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.OCSPIdentifierType;
import org.etsi.uri.x01903.v13.ResponderIDType;

/* loaded from: classes11.dex */
public class OCSPIdentifierTypeImpl extends XmlComplexContentImpl implements OCSPIdentifierType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "ResponderID"), new QName(SignatureFacet.XADES_132_NS, "ProducedAt"), new QName("", "URI")};
    private static final long serialVersionUID = 1;

    public OCSPIdentifierTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.OCSPIdentifierType
    public ResponderIDType getResponderID() {
        ResponderIDType responderIDType;
        synchronized (monitor()) {
            check_orphaned();
            ResponderIDType target = (ResponderIDType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            responderIDType = target == null ? null : target;
        }
        return responderIDType;
    }

    @Override // org.etsi.uri.x01903.v13.OCSPIdentifierType
    public void setResponderID(ResponderIDType responderID) {
        generatedSetterHelperImpl(responderID, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.OCSPIdentifierType
    public ResponderIDType addNewResponderID() {
        ResponderIDType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ResponderIDType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.OCSPIdentifierType
    public Calendar getProducedAt() {
        Calendar calendarValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            calendarValue = target == null ? null : target.getCalendarValue();
        }
        return calendarValue;
    }

    @Override // org.etsi.uri.x01903.v13.OCSPIdentifierType
    public XmlDateTime xgetProducedAt() {
        XmlDateTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDateTime) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.OCSPIdentifierType
    public void setProducedAt(Calendar producedAt) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setCalendarValue(producedAt);
        }
    }

    @Override // org.etsi.uri.x01903.v13.OCSPIdentifierType
    public void xsetProducedAt(XmlDateTime producedAt) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDateTime target = (XmlDateTime) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (XmlDateTime) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(producedAt);
        }
    }

    @Override // org.etsi.uri.x01903.v13.OCSPIdentifierType
    public String getURI() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.OCSPIdentifierType
    public XmlAnyURI xgetURI() {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.OCSPIdentifierType
    public boolean isSetURI() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.OCSPIdentifierType
    public void setURI(String uri) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(uri);
        }
    }

    @Override // org.etsi.uri.x01903.v13.OCSPIdentifierType
    public void xsetURI(XmlAnyURI uri) {
        synchronized (monitor()) {
            check_orphaned();
            XmlAnyURI target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlAnyURI) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(uri);
        }
    }

    @Override // org.etsi.uri.x01903.v13.OCSPIdentifierType
    public void unsetURI() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }
}
