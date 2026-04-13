package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles;

/* loaded from: classes11.dex */
public class CTSlideMasterTextStylesImpl extends XmlComplexContentImpl implements CTSlideMasterTextStyles {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "titleStyle"), new QName(XSSFRelation.NS_PRESENTATIONML, "bodyStyle"), new QName(XSSFRelation.NS_PRESENTATIONML, "otherStyle"), new QName(XSSFRelation.NS_PRESENTATIONML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTSlideMasterTextStylesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public CTTextListStyle getTitleStyle() {
        CTTextListStyle cTTextListStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTextListStyle target = (CTTextListStyle) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTextListStyle = target == null ? null : target;
        }
        return cTTextListStyle;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public boolean isSetTitleStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public void setTitleStyle(CTTextListStyle titleStyle) {
        generatedSetterHelperImpl(titleStyle, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public CTTextListStyle addNewTitleStyle() {
        CTTextListStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextListStyle) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public void unsetTitleStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public CTTextListStyle getBodyStyle() {
        CTTextListStyle cTTextListStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTextListStyle target = (CTTextListStyle) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTextListStyle = target == null ? null : target;
        }
        return cTTextListStyle;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public boolean isSetBodyStyle() {
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public void setBodyStyle(CTTextListStyle bodyStyle) {
        generatedSetterHelperImpl(bodyStyle, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public CTTextListStyle addNewBodyStyle() {
        CTTextListStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextListStyle) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public void unsetBodyStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public CTTextListStyle getOtherStyle() {
        CTTextListStyle cTTextListStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTextListStyle target = (CTTextListStyle) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTTextListStyle = target == null ? null : target;
        }
        return cTTextListStyle;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public boolean isSetOtherStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public void setOtherStyle(CTTextListStyle otherStyle) {
        generatedSetterHelperImpl(otherStyle, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public CTTextListStyle addNewOtherStyle() {
        CTTextListStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextListStyle) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public void unsetOtherStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
