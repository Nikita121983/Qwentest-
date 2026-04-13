package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.IconType;
import com.microsoft.schemas.office.visio.x2012.main.MasterType;
import com.microsoft.schemas.office.visio.x2012.main.PageSheetType;
import com.microsoft.schemas.office.visio.x2012.main.RelType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.XmlUnsignedShort;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class MasterTypeImpl extends XmlComplexContentImpl implements MasterType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "PageSheet"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "Rel"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "Icon"), new QName("", "ID"), new QName("", "BaseID"), new QName("", "UniqueID"), new QName("", "MatchByName"), new QName("", "Name"), new QName("", "NameU"), new QName("", "IsCustomName"), new QName("", "IsCustomNameU"), new QName("", "IconSize"), new QName("", "PatternFlags"), new QName("", "Prompt"), new QName("", "Hidden"), new QName("", "IconUpdate"), new QName("", "AlignName"), new QName("", "MasterType")};
    private static final long serialVersionUID = 1;

    public MasterTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public PageSheetType getPageSheet() {
        PageSheetType pageSheetType;
        synchronized (monitor()) {
            check_orphaned();
            PageSheetType target = (PageSheetType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            pageSheetType = target == null ? null : target;
        }
        return pageSheetType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean isSetPageSheet() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setPageSheet(PageSheetType pageSheet) {
        generatedSetterHelperImpl(pageSheet, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public PageSheetType addNewPageSheet() {
        PageSheetType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (PageSheetType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void unsetPageSheet() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public RelType getRel() {
        RelType relType;
        synchronized (monitor()) {
            check_orphaned();
            RelType target = (RelType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            relType = target == null ? null : target;
        }
        return relType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setRel(RelType rel) {
        generatedSetterHelperImpl(rel, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public RelType addNewRel() {
        RelType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (RelType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public IconType getIcon() {
        IconType iconType;
        synchronized (monitor()) {
            check_orphaned();
            IconType target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            iconType = target == null ? null : target;
        }
        return iconType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean isSetIcon() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setIcon(IconType icon) {
        generatedSetterHelperImpl(icon, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public IconType addNewIcon() {
        IconType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void unsetIcon() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public long getID() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public XmlUnsignedInt xgetID() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setID(long id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setLongValue(id);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void xsetID(XmlUnsignedInt id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(id);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public String getBaseID() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public XmlString xgetBaseID() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean isSetBaseID() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setBaseID(String baseID) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(baseID);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void xsetBaseID(XmlString baseID) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(baseID);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void unsetBaseID() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public String getUniqueID() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public XmlString xgetUniqueID() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean isSetUniqueID() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setUniqueID(String uniqueID) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setStringValue(uniqueID);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void xsetUniqueID(XmlString uniqueID) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(uniqueID);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void unsetUniqueID() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean getMatchByName() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public XmlBoolean xgetMatchByName() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean isSetMatchByName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setMatchByName(boolean matchByName) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setBooleanValue(matchByName);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void xsetMatchByName(XmlBoolean matchByName) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(matchByName);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void unsetMatchByName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public XmlString xgetName() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setStringValue(name);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void xsetName(XmlString name) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(name);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public String getNameU() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public XmlString xgetNameU() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean isSetNameU() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setNameU(String nameU) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setStringValue(nameU);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void xsetNameU(XmlString nameU) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(nameU);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void unsetNameU() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean getIsCustomName() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public XmlBoolean xgetIsCustomName() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean isSetIsCustomName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setIsCustomName(boolean isCustomName) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setBooleanValue(isCustomName);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void xsetIsCustomName(XmlBoolean isCustomName) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(isCustomName);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void unsetIsCustomName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean getIsCustomNameU() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public XmlBoolean xgetIsCustomNameU() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean isSetIsCustomNameU() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setIsCustomNameU(boolean isCustomNameU) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setBooleanValue(isCustomNameU);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void xsetIsCustomNameU(XmlBoolean isCustomNameU) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(isCustomNameU);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void unsetIsCustomNameU() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public int getIconSize() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public XmlUnsignedShort xgetIconSize() {
        XmlUnsignedShort target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedShort) get_store().find_attribute_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean isSetIconSize() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setIconSize(int iconSize) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setIntValue(iconSize);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void xsetIconSize(XmlUnsignedShort iconSize) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedShort target = (XmlUnsignedShort) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (XmlUnsignedShort) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(iconSize);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void unsetIconSize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public int getPatternFlags() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public XmlUnsignedShort xgetPatternFlags() {
        XmlUnsignedShort target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedShort) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean isSetPatternFlags() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setPatternFlags(int patternFlags) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setIntValue(patternFlags);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void xsetPatternFlags(XmlUnsignedShort patternFlags) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedShort target = (XmlUnsignedShort) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (XmlUnsignedShort) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(patternFlags);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void unsetPatternFlags() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public String getPrompt() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public XmlString xgetPrompt() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean isSetPrompt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setPrompt(String prompt) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setStringValue(prompt);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void xsetPrompt(XmlString prompt) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(prompt);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void unsetPrompt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean getHidden() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public XmlBoolean xgetHidden() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean isSetHidden() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setHidden(boolean hidden) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.setBooleanValue(hidden);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void xsetHidden(XmlBoolean hidden) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.set(hidden);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void unsetHidden() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[14]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean getIconUpdate() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public XmlBoolean xgetIconUpdate() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean isSetIconUpdate() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setIconUpdate(boolean iconUpdate) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.setBooleanValue(iconUpdate);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void xsetIconUpdate(XmlBoolean iconUpdate) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.set(iconUpdate);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void unsetIconUpdate() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[15]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public int getAlignName() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public XmlUnsignedShort xgetAlignName() {
        XmlUnsignedShort target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedShort) get_store().find_attribute_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean isSetAlignName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[16]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setAlignName(int alignName) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.setIntValue(alignName);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void xsetAlignName(XmlUnsignedShort alignName) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedShort target = (XmlUnsignedShort) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (XmlUnsignedShort) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.set(alignName);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void unsetAlignName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[16]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public int getMasterType() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public XmlUnsignedShort xgetMasterType() {
        XmlUnsignedShort target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedShort) get_store().find_attribute_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public boolean isSetMasterType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[17]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void setMasterType(int masterType) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.setIntValue(masterType);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void xsetMasterType(XmlUnsignedShort masterType) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedShort target = (XmlUnsignedShort) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (XmlUnsignedShort) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.set(masterType);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.MasterType
    public void unsetMasterType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[17]);
        }
    }
}
