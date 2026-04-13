package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate32;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeID;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.presentationml.x2006.main.CTOleObjectEmbed;
import org.openxmlformats.schemas.presentationml.x2006.main.CTOleObjectLink;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;

/* loaded from: classes11.dex */
public class CTOleObjectImpl extends XmlComplexContentImpl implements CTOleObject {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "embed"), new QName(XSSFRelation.NS_PRESENTATIONML, "link"), new QName(XSSFRelation.NS_PRESENTATIONML, "pic"), new QName("", "spid"), new QName("", "name"), new QName("", "showAsIcon"), new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "id"), new QName("", "imgW"), new QName("", "imgH"), new QName("", "progId")};
    private static final long serialVersionUID = 1;

    public CTOleObjectImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public CTOleObjectEmbed getEmbed() {
        CTOleObjectEmbed cTOleObjectEmbed;
        synchronized (monitor()) {
            check_orphaned();
            CTOleObjectEmbed target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTOleObjectEmbed = target == null ? null : target;
        }
        return cTOleObjectEmbed;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public boolean isSetEmbed() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void setEmbed(CTOleObjectEmbed embed) {
        generatedSetterHelperImpl(embed, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public CTOleObjectEmbed addNewEmbed() {
        CTOleObjectEmbed target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void unsetEmbed() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public CTOleObjectLink getLink() {
        CTOleObjectLink cTOleObjectLink;
        synchronized (monitor()) {
            check_orphaned();
            CTOleObjectLink target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTOleObjectLink = target == null ? null : target;
        }
        return cTOleObjectLink;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public boolean isSetLink() {
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void setLink(CTOleObjectLink link) {
        generatedSetterHelperImpl(link, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public CTOleObjectLink addNewLink() {
        CTOleObjectLink target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void unsetLink() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public CTPicture getPic() {
        CTPicture cTPicture;
        synchronized (monitor()) {
            check_orphaned();
            CTPicture target = (CTPicture) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTPicture = target == null ? null : target;
        }
        return cTPicture;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public boolean isSetPic() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void setPic(CTPicture pic) {
        generatedSetterHelperImpl(pic, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public CTPicture addNewPic() {
        CTPicture target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPicture) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void unsetPic() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public String getSpid() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public STShapeID xgetSpid() {
        STShapeID target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public boolean isSetSpid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void setSpid(String spid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(spid);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void xsetSpid(STShapeID spid) {
        synchronized (monitor()) {
            check_orphaned();
            STShapeID target = get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STShapeID) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(spid);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void unsetSpid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public XmlString xgetName() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlString) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void xsetName(XmlString name) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(name);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public boolean getShowAsIcon() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[5]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public XmlBoolean xgetShowAsIcon() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[5]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public boolean isSetShowAsIcon() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void setShowAsIcon(boolean showAsIcon) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setBooleanValue(showAsIcon);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void xsetShowAsIcon(XmlBoolean showAsIcon) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(showAsIcon);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void unsetShowAsIcon() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public STRelationshipId xgetId() {
        STRelationshipId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void xsetId(STRelationshipId id) {
        synchronized (monitor()) {
            check_orphaned();
            STRelationshipId target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STRelationshipId) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(id);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public int getImgW() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public STPositiveCoordinate32 xgetImgW() {
        STPositiveCoordinate32 target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositiveCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public boolean isSetImgW() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void setImgW(int imgW) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setIntValue(imgW);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void xsetImgW(STPositiveCoordinate32 imgW) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveCoordinate32 target = (STPositiveCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STPositiveCoordinate32) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(imgW);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void unsetImgW() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public int getImgH() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public STPositiveCoordinate32 xgetImgH() {
        STPositiveCoordinate32 target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositiveCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public boolean isSetImgH() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void setImgH(int imgH) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setIntValue(imgH);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void xsetImgH(STPositiveCoordinate32 imgH) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveCoordinate32 target = (STPositiveCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STPositiveCoordinate32) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(imgH);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void unsetImgH() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public String getProgId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public XmlString xgetProgId() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public boolean isSetProgId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void setProgId(String progId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setStringValue(progId);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void xsetProgId(XmlString progId) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(progId);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject
    public void unsetProgId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }
}
