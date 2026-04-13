package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.AttachedToolbarsType;
import com.microsoft.schemas.office.visio.x2012.main.CustomMenusFileType;
import com.microsoft.schemas.office.visio.x2012.main.CustomToolbarsFileType;
import com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType;
import com.microsoft.schemas.office.visio.x2012.main.DynamicGridEnabledType;
import com.microsoft.schemas.office.visio.x2012.main.GlueSettingsType;
import com.microsoft.schemas.office.visio.x2012.main.ProtectBkgndsType;
import com.microsoft.schemas.office.visio.x2012.main.ProtectMastersType;
import com.microsoft.schemas.office.visio.x2012.main.ProtectShapesType;
import com.microsoft.schemas.office.visio.x2012.main.ProtectStylesType;
import com.microsoft.schemas.office.visio.x2012.main.SnapAnglesType;
import com.microsoft.schemas.office.visio.x2012.main.SnapExtensionsType;
import com.microsoft.schemas.office.visio.x2012.main.SnapSettingsType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class DocumentSettingsTypeImpl extends XmlComplexContentImpl implements DocumentSettingsType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "GlueSettings"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "SnapSettings"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "SnapExtensions"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "SnapAngles"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "DynamicGridEnabled"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "ProtectStyles"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "ProtectShapes"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "ProtectMasters"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "ProtectBkgnds"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "CustomMenusFile"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "CustomToolbarsFile"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "AttachedToolbars"), new QName("", "TopPage"), new QName("", "DefaultTextStyle"), new QName("", "DefaultLineStyle"), new QName("", "DefaultFillStyle"), new QName("", "DefaultGuideStyle")};
    private static final long serialVersionUID = 1;

    public DocumentSettingsTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public GlueSettingsType getGlueSettings() {
        GlueSettingsType glueSettingsType;
        synchronized (monitor()) {
            check_orphaned();
            GlueSettingsType target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            glueSettingsType = target == null ? null : target;
        }
        return glueSettingsType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public boolean isSetGlueSettings() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void setGlueSettings(GlueSettingsType glueSettings) {
        generatedSetterHelperImpl(glueSettings, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public GlueSettingsType addNewGlueSettings() {
        GlueSettingsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void unsetGlueSettings() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public SnapSettingsType getSnapSettings() {
        SnapSettingsType snapSettingsType;
        synchronized (monitor()) {
            check_orphaned();
            SnapSettingsType target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            snapSettingsType = target == null ? null : target;
        }
        return snapSettingsType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public boolean isSetSnapSettings() {
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

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void setSnapSettings(SnapSettingsType snapSettings) {
        generatedSetterHelperImpl(snapSettings, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public SnapSettingsType addNewSnapSettings() {
        SnapSettingsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void unsetSnapSettings() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public SnapExtensionsType getSnapExtensions() {
        SnapExtensionsType snapExtensionsType;
        synchronized (monitor()) {
            check_orphaned();
            SnapExtensionsType target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            snapExtensionsType = target == null ? null : target;
        }
        return snapExtensionsType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public boolean isSetSnapExtensions() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void setSnapExtensions(SnapExtensionsType snapExtensions) {
        generatedSetterHelperImpl(snapExtensions, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public SnapExtensionsType addNewSnapExtensions() {
        SnapExtensionsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void unsetSnapExtensions() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public SnapAnglesType getSnapAngles() {
        SnapAnglesType snapAnglesType;
        synchronized (monitor()) {
            check_orphaned();
            SnapAnglesType target = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            snapAnglesType = target == null ? null : target;
        }
        return snapAnglesType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public boolean isSetSnapAngles() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void setSnapAngles(SnapAnglesType snapAngles) {
        generatedSetterHelperImpl(snapAngles, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public SnapAnglesType addNewSnapAngles() {
        SnapAnglesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void unsetSnapAngles() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public DynamicGridEnabledType getDynamicGridEnabled() {
        DynamicGridEnabledType dynamicGridEnabledType;
        synchronized (monitor()) {
            check_orphaned();
            DynamicGridEnabledType target = get_store().find_element_user(PROPERTY_QNAME[4], 0);
            dynamicGridEnabledType = target == null ? null : target;
        }
        return dynamicGridEnabledType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public boolean isSetDynamicGridEnabled() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void setDynamicGridEnabled(DynamicGridEnabledType dynamicGridEnabled) {
        generatedSetterHelperImpl(dynamicGridEnabled, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public DynamicGridEnabledType addNewDynamicGridEnabled() {
        DynamicGridEnabledType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void unsetDynamicGridEnabled() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public ProtectStylesType getProtectStyles() {
        ProtectStylesType protectStylesType;
        synchronized (monitor()) {
            check_orphaned();
            ProtectStylesType target = get_store().find_element_user(PROPERTY_QNAME[5], 0);
            protectStylesType = target == null ? null : target;
        }
        return protectStylesType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public boolean isSetProtectStyles() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void setProtectStyles(ProtectStylesType protectStyles) {
        generatedSetterHelperImpl(protectStyles, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public ProtectStylesType addNewProtectStyles() {
        ProtectStylesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void unsetProtectStyles() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public ProtectShapesType getProtectShapes() {
        ProtectShapesType protectShapesType;
        synchronized (monitor()) {
            check_orphaned();
            ProtectShapesType target = get_store().find_element_user(PROPERTY_QNAME[6], 0);
            protectShapesType = target == null ? null : target;
        }
        return protectShapesType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public boolean isSetProtectShapes() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void setProtectShapes(ProtectShapesType protectShapes) {
        generatedSetterHelperImpl(protectShapes, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public ProtectShapesType addNewProtectShapes() {
        ProtectShapesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void unsetProtectShapes() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public ProtectMastersType getProtectMasters() {
        ProtectMastersType protectMastersType;
        synchronized (monitor()) {
            check_orphaned();
            ProtectMastersType target = get_store().find_element_user(PROPERTY_QNAME[7], 0);
            protectMastersType = target == null ? null : target;
        }
        return protectMastersType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public boolean isSetProtectMasters() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void setProtectMasters(ProtectMastersType protectMasters) {
        generatedSetterHelperImpl(protectMasters, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public ProtectMastersType addNewProtectMasters() {
        ProtectMastersType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void unsetProtectMasters() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public ProtectBkgndsType getProtectBkgnds() {
        ProtectBkgndsType protectBkgndsType;
        synchronized (monitor()) {
            check_orphaned();
            ProtectBkgndsType target = get_store().find_element_user(PROPERTY_QNAME[8], 0);
            protectBkgndsType = target == null ? null : target;
        }
        return protectBkgndsType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public boolean isSetProtectBkgnds() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void setProtectBkgnds(ProtectBkgndsType protectBkgnds) {
        generatedSetterHelperImpl(protectBkgnds, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public ProtectBkgndsType addNewProtectBkgnds() {
        ProtectBkgndsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void unsetProtectBkgnds() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public CustomMenusFileType getCustomMenusFile() {
        CustomMenusFileType customMenusFileType;
        synchronized (monitor()) {
            check_orphaned();
            CustomMenusFileType target = get_store().find_element_user(PROPERTY_QNAME[9], 0);
            customMenusFileType = target == null ? null : target;
        }
        return customMenusFileType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public boolean isSetCustomMenusFile() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void setCustomMenusFile(CustomMenusFileType customMenusFile) {
        generatedSetterHelperImpl(customMenusFile, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public CustomMenusFileType addNewCustomMenusFile() {
        CustomMenusFileType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void unsetCustomMenusFile() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public CustomToolbarsFileType getCustomToolbarsFile() {
        CustomToolbarsFileType customToolbarsFileType;
        synchronized (monitor()) {
            check_orphaned();
            CustomToolbarsFileType target = get_store().find_element_user(PROPERTY_QNAME[10], 0);
            customToolbarsFileType = target == null ? null : target;
        }
        return customToolbarsFileType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public boolean isSetCustomToolbarsFile() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void setCustomToolbarsFile(CustomToolbarsFileType customToolbarsFile) {
        generatedSetterHelperImpl(customToolbarsFile, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public CustomToolbarsFileType addNewCustomToolbarsFile() {
        CustomToolbarsFileType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void unsetCustomToolbarsFile() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public AttachedToolbarsType getAttachedToolbars() {
        AttachedToolbarsType attachedToolbarsType;
        synchronized (monitor()) {
            check_orphaned();
            AttachedToolbarsType target = get_store().find_element_user(PROPERTY_QNAME[11], 0);
            attachedToolbarsType = target == null ? null : target;
        }
        return attachedToolbarsType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public boolean isSetAttachedToolbars() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void setAttachedToolbars(AttachedToolbarsType attachedToolbars) {
        generatedSetterHelperImpl(attachedToolbars, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public AttachedToolbarsType addNewAttachedToolbars() {
        AttachedToolbarsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void unsetAttachedToolbars() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public long getTopPage() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public XmlUnsignedInt xgetTopPage() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public boolean isSetTopPage() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void setTopPage(long topPage) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setLongValue(topPage);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void xsetTopPage(XmlUnsignedInt topPage) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(topPage);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void unsetTopPage() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public long getDefaultTextStyle() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public XmlUnsignedInt xgetDefaultTextStyle() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public boolean isSetDefaultTextStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void setDefaultTextStyle(long defaultTextStyle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setLongValue(defaultTextStyle);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void xsetDefaultTextStyle(XmlUnsignedInt defaultTextStyle) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(defaultTextStyle);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void unsetDefaultTextStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public long getDefaultLineStyle() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public XmlUnsignedInt xgetDefaultLineStyle() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public boolean isSetDefaultLineStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void setDefaultLineStyle(long defaultLineStyle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.setLongValue(defaultLineStyle);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void xsetDefaultLineStyle(XmlUnsignedInt defaultLineStyle) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.set(defaultLineStyle);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void unsetDefaultLineStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[14]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public long getDefaultFillStyle() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public XmlUnsignedInt xgetDefaultFillStyle() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public boolean isSetDefaultFillStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void setDefaultFillStyle(long defaultFillStyle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.setLongValue(defaultFillStyle);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void xsetDefaultFillStyle(XmlUnsignedInt defaultFillStyle) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.set(defaultFillStyle);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void unsetDefaultFillStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[15]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public long getDefaultGuideStyle() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public XmlUnsignedInt xgetDefaultGuideStyle() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public boolean isSetDefaultGuideStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[16]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void setDefaultGuideStyle(long defaultGuideStyle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.setLongValue(defaultGuideStyle);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void xsetDefaultGuideStyle(XmlUnsignedInt defaultGuideStyle) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.set(defaultGuideStyle);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType
    public void unsetDefaultGuideStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[16]);
        }
    }
}
