package com.microsoft.schemas.office.x2006.digsig.impl;

import com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1;
import com.microsoft.schemas.office.x2006.digsig.STPositiveInteger;
import com.microsoft.schemas.office.x2006.digsig.STSignatureComments;
import com.microsoft.schemas.office.x2006.digsig.STSignatureProviderUrl;
import com.microsoft.schemas.office.x2006.digsig.STSignatureText;
import com.microsoft.schemas.office.x2006.digsig.STSignatureType;
import com.microsoft.schemas.office.x2006.digsig.STUniqueIdentifierWithBraces;
import com.microsoft.schemas.office.x2006.digsig.STVersion;
import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class CTSignatureInfoV1Impl extends XmlComplexContentImpl implements CTSignatureInfoV1 {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.MS_DIGSIG_NS, "SetupID"), new QName(SignatureFacet.MS_DIGSIG_NS, "SignatureText"), new QName(SignatureFacet.MS_DIGSIG_NS, "SignatureImage"), new QName(SignatureFacet.MS_DIGSIG_NS, "SignatureComments"), new QName(SignatureFacet.MS_DIGSIG_NS, "WindowsVersion"), new QName(SignatureFacet.MS_DIGSIG_NS, "OfficeVersion"), new QName(SignatureFacet.MS_DIGSIG_NS, "ApplicationVersion"), new QName(SignatureFacet.MS_DIGSIG_NS, "Monitors"), new QName(SignatureFacet.MS_DIGSIG_NS, "HorizontalResolution"), new QName(SignatureFacet.MS_DIGSIG_NS, "VerticalResolution"), new QName(SignatureFacet.MS_DIGSIG_NS, "ColorDepth"), new QName(SignatureFacet.MS_DIGSIG_NS, "SignatureProviderId"), new QName(SignatureFacet.MS_DIGSIG_NS, "SignatureProviderUrl"), new QName(SignatureFacet.MS_DIGSIG_NS, "SignatureProviderDetails"), new QName(SignatureFacet.MS_DIGSIG_NS, "SignatureType"), new QName(SignatureFacet.MS_DIGSIG_NS, "DelegateSuggestedSigner"), new QName(SignatureFacet.MS_DIGSIG_NS, "DelegateSuggestedSigner2"), new QName(SignatureFacet.MS_DIGSIG_NS, "DelegateSuggestedSignerEmail"), new QName(SignatureFacet.MS_DIGSIG_NS, "ManifestHashAlgorithm")};
    private static final long serialVersionUID = 1;

    public CTSignatureInfoV1Impl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public String getSetupID() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public STUniqueIdentifierWithBraces xgetSetupID() {
        STUniqueIdentifierWithBraces target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STUniqueIdentifierWithBraces) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setSetupID(String setupID) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(setupID);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetSetupID(STUniqueIdentifierWithBraces setupID) {
        synchronized (monitor()) {
            check_orphaned();
            STUniqueIdentifierWithBraces target = (STUniqueIdentifierWithBraces) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (STUniqueIdentifierWithBraces) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.set(setupID);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public String getSignatureText() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public STSignatureText xgetSignatureText() {
        STSignatureText target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setSignatureText(String signatureText) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(signatureText);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetSignatureText(STSignatureText signatureText) {
        synchronized (monitor()) {
            check_orphaned();
            STSignatureText target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (STSignatureText) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(signatureText);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public byte[] getSignatureImage() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public XmlBase64Binary xgetSignatureImage() {
        XmlBase64Binary target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBase64Binary) get_store().find_element_user(PROPERTY_QNAME[2], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setSignatureImage(byte[] signatureImage) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.setByteArrayValue(signatureImage);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetSignatureImage(XmlBase64Binary signatureImage) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBase64Binary target = (XmlBase64Binary) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (XmlBase64Binary) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.set(signatureImage);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public String getSignatureComments() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public STSignatureComments xgetSignatureComments() {
        STSignatureComments target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSignatureComments) get_store().find_element_user(PROPERTY_QNAME[3], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setSignatureComments(String signatureComments) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(signatureComments);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetSignatureComments(STSignatureComments signatureComments) {
        synchronized (monitor()) {
            check_orphaned();
            STSignatureComments target = (STSignatureComments) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (target == null) {
                target = (STSignatureComments) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            target.set(signatureComments);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public String getWindowsVersion() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public STVersion xgetWindowsVersion() {
        STVersion target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[4], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setWindowsVersion(String windowsVersion) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(windowsVersion);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetWindowsVersion(STVersion windowsVersion) {
        synchronized (monitor()) {
            check_orphaned();
            STVersion target = get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (target == null) {
                target = (STVersion) get_store().add_element_user(PROPERTY_QNAME[4]);
            }
            target.set(windowsVersion);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public String getOfficeVersion() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public STVersion xgetOfficeVersion() {
        STVersion target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[5], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setOfficeVersion(String officeVersion) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[5]);
            }
            target.setStringValue(officeVersion);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetOfficeVersion(STVersion officeVersion) {
        synchronized (monitor()) {
            check_orphaned();
            STVersion target = get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (target == null) {
                target = (STVersion) get_store().add_element_user(PROPERTY_QNAME[5]);
            }
            target.set(officeVersion);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public String getApplicationVersion() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public STVersion xgetApplicationVersion() {
        STVersion target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[6], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setApplicationVersion(String applicationVersion) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[6]);
            }
            target.setStringValue(applicationVersion);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetApplicationVersion(STVersion applicationVersion) {
        synchronized (monitor()) {
            check_orphaned();
            STVersion target = get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (target == null) {
                target = (STVersion) get_store().add_element_user(PROPERTY_QNAME[6]);
            }
            target.set(applicationVersion);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public int getMonitors() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public STPositiveInteger xgetMonitors() {
        STPositiveInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[7], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setMonitors(int monitors) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[7]);
            }
            target.setIntValue(monitors);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetMonitors(STPositiveInteger monitors) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveInteger target = get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (target == null) {
                target = (STPositiveInteger) get_store().add_element_user(PROPERTY_QNAME[7]);
            }
            target.set(monitors);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public int getHorizontalResolution() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public STPositiveInteger xgetHorizontalResolution() {
        STPositiveInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[8], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setHorizontalResolution(int horizontalResolution) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[8]);
            }
            target.setIntValue(horizontalResolution);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetHorizontalResolution(STPositiveInteger horizontalResolution) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveInteger target = get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (target == null) {
                target = (STPositiveInteger) get_store().add_element_user(PROPERTY_QNAME[8]);
            }
            target.set(horizontalResolution);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public int getVerticalResolution() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public STPositiveInteger xgetVerticalResolution() {
        STPositiveInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[9], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setVerticalResolution(int verticalResolution) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[9]);
            }
            target.setIntValue(verticalResolution);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetVerticalResolution(STPositiveInteger verticalResolution) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveInteger target = get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (target == null) {
                target = (STPositiveInteger) get_store().add_element_user(PROPERTY_QNAME[9]);
            }
            target.set(verticalResolution);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public int getColorDepth() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public STPositiveInteger xgetColorDepth() {
        STPositiveInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[10], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setColorDepth(int colorDepth) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[10]);
            }
            target.setIntValue(colorDepth);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetColorDepth(STPositiveInteger colorDepth) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveInteger target = get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (target == null) {
                target = (STPositiveInteger) get_store().add_element_user(PROPERTY_QNAME[10]);
            }
            target.set(colorDepth);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public String getSignatureProviderId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public STUniqueIdentifierWithBraces xgetSignatureProviderId() {
        STUniqueIdentifierWithBraces target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STUniqueIdentifierWithBraces) get_store().find_element_user(PROPERTY_QNAME[11], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setSignatureProviderId(String signatureProviderId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[11]);
            }
            target.setStringValue(signatureProviderId);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetSignatureProviderId(STUniqueIdentifierWithBraces signatureProviderId) {
        synchronized (monitor()) {
            check_orphaned();
            STUniqueIdentifierWithBraces target = (STUniqueIdentifierWithBraces) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (target == null) {
                target = (STUniqueIdentifierWithBraces) get_store().add_element_user(PROPERTY_QNAME[11]);
            }
            target.set(signatureProviderId);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public String getSignatureProviderUrl() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public STSignatureProviderUrl xgetSignatureProviderUrl() {
        STSignatureProviderUrl target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[12], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setSignatureProviderUrl(String signatureProviderUrl) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[12]);
            }
            target.setStringValue(signatureProviderUrl);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetSignatureProviderUrl(STSignatureProviderUrl signatureProviderUrl) {
        synchronized (monitor()) {
            check_orphaned();
            STSignatureProviderUrl target = get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (target == null) {
                target = (STSignatureProviderUrl) get_store().add_element_user(PROPERTY_QNAME[12]);
            }
            target.set(signatureProviderUrl);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public int getSignatureProviderDetails() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public XmlInt xgetSignatureProviderDetails() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[13], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setSignatureProviderDetails(int signatureProviderDetails) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[13]);
            }
            target.setIntValue(signatureProviderDetails);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetSignatureProviderDetails(XmlInt signatureProviderDetails) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (target == null) {
                target = (XmlInt) get_store().add_element_user(PROPERTY_QNAME[13]);
            }
            target.set(signatureProviderDetails);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public int getSignatureType() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public STSignatureType xgetSignatureType() {
        STSignatureType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSignatureType) get_store().find_element_user(PROPERTY_QNAME[14], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setSignatureType(int signatureType) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[14]);
            }
            target.setIntValue(signatureType);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetSignatureType(STSignatureType signatureType) {
        synchronized (monitor()) {
            check_orphaned();
            STSignatureType target = (STSignatureType) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            if (target == null) {
                target = (STSignatureType) get_store().add_element_user(PROPERTY_QNAME[14]);
            }
            target.set(signatureType);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public String getDelegateSuggestedSigner() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public XmlString xgetDelegateSuggestedSigner() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[15], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public boolean isSetDelegateSuggestedSigner() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setDelegateSuggestedSigner(String delegateSuggestedSigner) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[15]);
            }
            target.setStringValue(delegateSuggestedSigner);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetDelegateSuggestedSigner(XmlString delegateSuggestedSigner) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[15]);
            }
            target.set(delegateSuggestedSigner);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void unsetDelegateSuggestedSigner() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public String getDelegateSuggestedSigner2() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public XmlString xgetDelegateSuggestedSigner2() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[16], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public boolean isSetDelegateSuggestedSigner2() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setDelegateSuggestedSigner2(String delegateSuggestedSigner2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[16]);
            }
            target.setStringValue(delegateSuggestedSigner2);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetDelegateSuggestedSigner2(XmlString delegateSuggestedSigner2) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[16]);
            }
            target.set(delegateSuggestedSigner2);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void unsetDelegateSuggestedSigner2() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public String getDelegateSuggestedSignerEmail() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public XmlString xgetDelegateSuggestedSignerEmail() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[17], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public boolean isSetDelegateSuggestedSignerEmail() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setDelegateSuggestedSignerEmail(String delegateSuggestedSignerEmail) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[17]);
            }
            target.setStringValue(delegateSuggestedSignerEmail);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetDelegateSuggestedSignerEmail(XmlString delegateSuggestedSignerEmail) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[17]);
            }
            target.set(delegateSuggestedSignerEmail);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void unsetDelegateSuggestedSignerEmail() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public String getManifestHashAlgorithm() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public XmlAnyURI xgetManifestHashAlgorithm() {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().find_element_user(PROPERTY_QNAME[18], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public boolean isSetManifestHashAlgorithm() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void setManifestHashAlgorithm(String manifestHashAlgorithm) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[18]);
            }
            target.setStringValue(manifestHashAlgorithm);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void xsetManifestHashAlgorithm(XmlAnyURI manifestHashAlgorithm) {
        synchronized (monitor()) {
            check_orphaned();
            XmlAnyURI target = (XmlAnyURI) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            if (target == null) {
                target = (XmlAnyURI) get_store().add_element_user(PROPERTY_QNAME[18]);
            }
            target.set(manifestHashAlgorithm);
        }
    }

    @Override // com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1
    public void unsetManifestHashAlgorithm() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }
}
