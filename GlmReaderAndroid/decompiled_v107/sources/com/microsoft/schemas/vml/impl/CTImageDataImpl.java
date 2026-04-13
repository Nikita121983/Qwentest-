package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.vml.CTImageData;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlFloat;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STColorType;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;

/* loaded from: classes9.dex */
public class CTImageDataImpl extends XmlComplexContentImpl implements CTImageData {
    private static final QName[] PROPERTY_QNAME = {new QName("", "id"), new QName("", "src"), new QName("", "cropleft"), new QName("", "croptop"), new QName("", "cropright"), new QName("", "cropbottom"), new QName("", "gain"), new QName("", "blacklevel"), new QName("", "gamma"), new QName("", "grayscale"), new QName("", "bilevel"), new QName("", "chromakey"), new QName("", "embosscolor"), new QName("", "recolortarget"), new QName("urn:schemas-microsoft-com:office:office", "href"), new QName("urn:schemas-microsoft-com:office:office", "althref"), new QName("urn:schemas-microsoft-com:office:office", "title"), new QName("urn:schemas-microsoft-com:office:office", "oleid"), new QName("urn:schemas-microsoft-com:office:office", "detectmouseclick"), new QName("urn:schemas-microsoft-com:office:office", "movie"), new QName("urn:schemas-microsoft-com:office:office", "relid"), new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "id"), new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, ContentTypes.EXTENSION_PICT), new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "href")};
    private static final long serialVersionUID = 1;

    public CTImageDataImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public XmlString xgetId() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(id);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetId(XmlString id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(id);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getSrc() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public XmlString xgetSrc() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetSrc() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().find_attribute_user(PROPERTY_QNAME[1]) == null) {
                z = false;
            }
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setSrc(String src) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(src);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetSrc(XmlString src) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(src);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetSrc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getCropleft() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public XmlString xgetCropleft() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetCropleft() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setCropleft(String cropleft) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(cropleft);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetCropleft(XmlString cropleft) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(cropleft);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetCropleft() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getCroptop() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public XmlString xgetCroptop() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetCroptop() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setCroptop(String croptop) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(croptop);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetCroptop(XmlString croptop) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(croptop);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetCroptop() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getCropright() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public XmlString xgetCropright() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetCropright() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setCropright(String cropright) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(cropright);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetCropright(XmlString cropright) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(cropright);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetCropright() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getCropbottom() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public XmlString xgetCropbottom() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetCropbottom() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setCropbottom(String cropbottom) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setStringValue(cropbottom);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetCropbottom(XmlString cropbottom) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(cropbottom);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetCropbottom() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getGain() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public XmlString xgetGain() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetGain() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setGain(String gain) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setStringValue(gain);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetGain(XmlString gain) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(gain);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetGain() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getBlacklevel() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public XmlString xgetBlacklevel() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetBlacklevel() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setBlacklevel(String blacklevel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setStringValue(blacklevel);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetBlacklevel(XmlString blacklevel) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(blacklevel);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetBlacklevel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getGamma() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public XmlString xgetGamma() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetGamma() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setGamma(String gamma) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setStringValue(gamma);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetGamma(XmlString gamma) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(gamma);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetGamma() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public STTrueFalse.Enum getGrayscale() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public STTrueFalse xgetGrayscale() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetGrayscale() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setGrayscale(STTrueFalse.Enum grayscale) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setEnumValue(grayscale);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetGrayscale(STTrueFalse grayscale) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(grayscale);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetGrayscale() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public STTrueFalse.Enum getBilevel() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public STTrueFalse xgetBilevel() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetBilevel() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setBilevel(STTrueFalse.Enum bilevel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setEnumValue(bilevel);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetBilevel(STTrueFalse bilevel) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(bilevel);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetBilevel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getChromakey() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public STColorType xgetChromakey() {
        STColorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetChromakey() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setChromakey(String chromakey) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setStringValue(chromakey);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetChromakey(STColorType chromakey) {
        synchronized (monitor()) {
            check_orphaned();
            STColorType target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (STColorType) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(chromakey);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetChromakey() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getEmbosscolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public STColorType xgetEmbosscolor() {
        STColorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetEmbosscolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setEmbosscolor(String embosscolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setStringValue(embosscolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetEmbosscolor(STColorType embosscolor) {
        synchronized (monitor()) {
            check_orphaned();
            STColorType target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (STColorType) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(embosscolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetEmbosscolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getRecolortarget() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public STColorType xgetRecolortarget() {
        STColorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetRecolortarget() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setRecolortarget(String recolortarget) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setStringValue(recolortarget);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetRecolortarget(STColorType recolortarget) {
        synchronized (monitor()) {
            check_orphaned();
            STColorType target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (STColorType) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(recolortarget);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetRecolortarget() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getHref() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public XmlString xgetHref() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetHref() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setHref(String href) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.setStringValue(href);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetHref(XmlString href) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.set(href);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetHref() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[14]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getAlthref() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public XmlString xgetAlthref() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetAlthref() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setAlthref(String althref) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.setStringValue(althref);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetAlthref(XmlString althref) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.set(althref);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetAlthref() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[15]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getTitle() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public XmlString xgetTitle() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetTitle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[16]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setTitle(String title) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.setStringValue(title);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetTitle(XmlString title) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.set(title);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetTitle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[16]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public float getOleid() {
        float floatValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            floatValue = target == null ? 0.0f : target.getFloatValue();
        }
        return floatValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public XmlFloat xgetOleid() {
        XmlFloat target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlFloat) get_store().find_attribute_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetOleid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[17]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setOleid(float oleid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.setFloatValue(oleid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetOleid(XmlFloat oleid) {
        synchronized (monitor()) {
            check_orphaned();
            XmlFloat target = (XmlFloat) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (XmlFloat) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.set(oleid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetOleid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[17]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public STTrueFalse.Enum getDetectmouseclick() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public STTrueFalse xgetDetectmouseclick() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetDetectmouseclick() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[18]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setDetectmouseclick(STTrueFalse.Enum detectmouseclick) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.setEnumValue(detectmouseclick);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetDetectmouseclick(STTrueFalse detectmouseclick) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.set(detectmouseclick);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetDetectmouseclick() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[18]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public float getMovie() {
        float floatValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            floatValue = target == null ? 0.0f : target.getFloatValue();
        }
        return floatValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public XmlFloat xgetMovie() {
        XmlFloat target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlFloat) get_store().find_attribute_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetMovie() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[19]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setMovie(float movie) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.setFloatValue(movie);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetMovie(XmlFloat movie) {
        synchronized (monitor()) {
            check_orphaned();
            XmlFloat target = (XmlFloat) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (XmlFloat) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.set(movie);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetMovie() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[19]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getRelid() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public STRelationshipId xgetRelid() {
        STRelationshipId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetRelid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[20]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setRelid(String relid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.setStringValue(relid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetRelid(STRelationshipId relid) {
        synchronized (monitor()) {
            check_orphaned();
            STRelationshipId target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (STRelationshipId) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.set(relid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetRelid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[20]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getId2() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public STRelationshipId xgetId2() {
        STRelationshipId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetId2() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[21]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setId2(String id2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.setStringValue(id2);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetId2(STRelationshipId id2) {
        synchronized (monitor()) {
            check_orphaned();
            STRelationshipId target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (STRelationshipId) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.set(id2);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetId2() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[21]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getPict() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public STRelationshipId xgetPict() {
        STRelationshipId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetPict() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[22]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setPict(String pict) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.setStringValue(pict);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetPict(STRelationshipId pict) {
        synchronized (monitor()) {
            check_orphaned();
            STRelationshipId target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (STRelationshipId) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.set(pict);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetPict() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[22]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public String getHref2() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public STRelationshipId xgetHref2() {
        STRelationshipId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public boolean isSetHref2() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[23]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void setHref2(String href2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.setStringValue(href2);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void xsetHref2(STRelationshipId href2) {
        synchronized (monitor()) {
            check_orphaned();
            STRelationshipId target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (STRelationshipId) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.set(href2);
        }
    }

    @Override // com.microsoft.schemas.vml.CTImageData
    public void unsetHref2() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[23]);
        }
    }
}
