package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CRLValuesType;
import org.etsi.uri.x01903.v13.OCSPValuesType;
import org.etsi.uri.x01903.v13.OtherCertStatusValuesType;
import org.etsi.uri.x01903.v13.RevocationValuesType;

/* loaded from: classes11.dex */
public class RevocationValuesTypeImpl extends XmlComplexContentImpl implements RevocationValuesType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "CRLValues"), new QName(SignatureFacet.XADES_132_NS, "OCSPValues"), new QName(SignatureFacet.XADES_132_NS, "OtherValues"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public RevocationValuesTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public CRLValuesType getCRLValues() {
        CRLValuesType cRLValuesType;
        synchronized (monitor()) {
            check_orphaned();
            CRLValuesType target = (CRLValuesType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cRLValuesType = target == null ? null : target;
        }
        return cRLValuesType;
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public boolean isSetCRLValues() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public void setCRLValues(CRLValuesType crlValues) {
        generatedSetterHelperImpl(crlValues, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public CRLValuesType addNewCRLValues() {
        CRLValuesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CRLValuesType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public void unsetCRLValues() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public OCSPValuesType getOCSPValues() {
        OCSPValuesType oCSPValuesType;
        synchronized (monitor()) {
            check_orphaned();
            OCSPValuesType target = (OCSPValuesType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            oCSPValuesType = target == null ? null : target;
        }
        return oCSPValuesType;
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public boolean isSetOCSPValues() {
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

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public void setOCSPValues(OCSPValuesType ocspValues) {
        generatedSetterHelperImpl(ocspValues, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public OCSPValuesType addNewOCSPValues() {
        OCSPValuesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (OCSPValuesType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public void unsetOCSPValues() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public OtherCertStatusValuesType getOtherValues() {
        OtherCertStatusValuesType otherCertStatusValuesType;
        synchronized (monitor()) {
            check_orphaned();
            OtherCertStatusValuesType target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            otherCertStatusValuesType = target == null ? null : target;
        }
        return otherCertStatusValuesType;
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public boolean isSetOtherValues() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public void setOtherValues(OtherCertStatusValuesType otherValues) {
        generatedSetterHelperImpl(otherValues, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public OtherCertStatusValuesType addNewOtherValues() {
        OtherCertStatusValuesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public void unsetOtherValues() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public void xsetId(XmlID id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlID target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlID) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(id);
        }
    }

    @Override // org.etsi.uri.x01903.v13.RevocationValuesType
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }
}
