package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.PageSheetType;
import com.microsoft.schemas.office.visio.x2012.main.PageType;
import com.microsoft.schemas.office.visio.x2012.main.RelType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class PageTypeImpl extends XmlComplexContentImpl implements PageType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "PageSheet"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "Rel"), new QName("", "ID"), new QName("", "Name"), new QName("", "NameU"), new QName("", "IsCustomName"), new QName("", "IsCustomNameU"), new QName("", "Background"), new QName("", "BackPage"), new QName("", "ViewScale"), new QName("", "ViewCenterX"), new QName("", "ViewCenterY"), new QName("", "ReviewerID"), new QName("", "AssociatedPage")};
    private static final long serialVersionUID = 1;

    public PageTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public PageSheetType getPageSheet() {
        PageSheetType pageSheetType;
        synchronized (monitor()) {
            check_orphaned();
            PageSheetType target = (PageSheetType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            pageSheetType = target == null ? null : target;
        }
        return pageSheetType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public boolean isSetPageSheet() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void setPageSheet(PageSheetType pageSheet) {
        generatedSetterHelperImpl(pageSheet, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public PageSheetType addNewPageSheet() {
        PageSheetType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (PageSheetType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void unsetPageSheet() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public RelType getRel() {
        RelType relType;
        synchronized (monitor()) {
            check_orphaned();
            RelType target = (RelType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            relType = target == null ? null : target;
        }
        return relType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void setRel(RelType rel) {
        generatedSetterHelperImpl(rel, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public RelType addNewRel() {
        RelType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (RelType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public long getID() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public XmlUnsignedInt xgetID() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void setID(long id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setLongValue(id);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void xsetID(XmlUnsignedInt id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(id);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public XmlString xgetName() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(name);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void xsetName(XmlString name) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(name);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public String getNameU() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public XmlString xgetNameU() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public boolean isSetNameU() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void setNameU(String nameU) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(nameU);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void xsetNameU(XmlString nameU) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(nameU);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void unsetNameU() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public boolean getIsCustomName() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public XmlBoolean xgetIsCustomName() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public boolean isSetIsCustomName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void setIsCustomName(boolean isCustomName) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setBooleanValue(isCustomName);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void xsetIsCustomName(XmlBoolean isCustomName) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(isCustomName);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void unsetIsCustomName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public boolean getIsCustomNameU() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public XmlBoolean xgetIsCustomNameU() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public boolean isSetIsCustomNameU() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void setIsCustomNameU(boolean isCustomNameU) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setBooleanValue(isCustomNameU);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void xsetIsCustomNameU(XmlBoolean isCustomNameU) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(isCustomNameU);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void unsetIsCustomNameU() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public boolean getBackground() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public XmlBoolean xgetBackground() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public boolean isSetBackground() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void setBackground(boolean background) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setBooleanValue(background);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void xsetBackground(XmlBoolean background) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(background);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void unsetBackground() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public long getBackPage() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public XmlUnsignedInt xgetBackPage() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public boolean isSetBackPage() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void setBackPage(long backPage) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setLongValue(backPage);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void xsetBackPage(XmlUnsignedInt backPage) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(backPage);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void unsetBackPage() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public double getViewScale() {
        double doubleValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            doubleValue = target == null ? 0.0d : target.getDoubleValue();
        }
        return doubleValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public XmlDouble xgetViewScale() {
        XmlDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public boolean isSetViewScale() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void setViewScale(double viewScale) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setDoubleValue(viewScale);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void xsetViewScale(XmlDouble viewScale) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDouble target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlDouble) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(viewScale);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void unsetViewScale() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public double getViewCenterX() {
        double doubleValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            doubleValue = target == null ? 0.0d : target.getDoubleValue();
        }
        return doubleValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public XmlDouble xgetViewCenterX() {
        XmlDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public boolean isSetViewCenterX() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void setViewCenterX(double viewCenterX) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setDoubleValue(viewCenterX);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void xsetViewCenterX(XmlDouble viewCenterX) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDouble target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (XmlDouble) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(viewCenterX);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void unsetViewCenterX() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public double getViewCenterY() {
        double doubleValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            doubleValue = target == null ? 0.0d : target.getDoubleValue();
        }
        return doubleValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public XmlDouble xgetViewCenterY() {
        XmlDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public boolean isSetViewCenterY() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void setViewCenterY(double viewCenterY) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setDoubleValue(viewCenterY);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void xsetViewCenterY(XmlDouble viewCenterY) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDouble target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (XmlDouble) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(viewCenterY);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void unsetViewCenterY() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public long getReviewerID() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public XmlUnsignedInt xgetReviewerID() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public boolean isSetReviewerID() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void setReviewerID(long reviewerID) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setLongValue(reviewerID);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void xsetReviewerID(XmlUnsignedInt reviewerID) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(reviewerID);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void unsetReviewerID() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public long getAssociatedPage() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public XmlUnsignedInt xgetAssociatedPage() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public boolean isSetAssociatedPage() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void setAssociatedPage(long associatedPage) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setLongValue(associatedPage);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void xsetAssociatedPage(XmlUnsignedInt associatedPage) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(associatedPage);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageType
    public void unsetAssociatedPage() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }
}
