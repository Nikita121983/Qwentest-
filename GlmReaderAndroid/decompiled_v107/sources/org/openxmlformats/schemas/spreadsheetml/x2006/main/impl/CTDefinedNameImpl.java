package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.ss.util.CellUtil;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName;

/* loaded from: classes12.dex */
public class CTDefinedNameImpl extends JavaStringHolderEx implements CTDefinedName {
    private static final QName[] PROPERTY_QNAME = {new QName("", "name"), new QName("", "comment"), new QName("", "customMenu"), new QName("", "description"), new QName("", "help"), new QName("", "statusBar"), new QName("", "localSheetId"), new QName("", CellUtil.HIDDEN), new QName("", "function"), new QName("", "vbProcedure"), new QName("", "xlm"), new QName("", "functionGroupId"), new QName("", "shortcutKey"), new QName("", "publishToServer"), new QName("", "workbookParameter")};
    private static final long serialVersionUID = 1;

    public CTDefinedNameImpl(SchemaType sType) {
        super(sType, true);
    }

    protected CTDefinedNameImpl(SchemaType sType, boolean b) {
        super(sType, b);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public STXstring xgetName() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void xsetName(STXstring name) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(name);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public String getComment() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public STXstring xgetComment() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean isSetComment() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void setComment(String comment) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(comment);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void xsetComment(STXstring comment) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(comment);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void unsetComment() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public String getCustomMenu() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public STXstring xgetCustomMenu() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean isSetCustomMenu() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void setCustomMenu(String customMenu) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(customMenu);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void xsetCustomMenu(STXstring customMenu) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(customMenu);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void unsetCustomMenu() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public String getDescription() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public STXstring xgetDescription() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean isSetDescription() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void setDescription(String description) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(description);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void xsetDescription(STXstring description) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(description);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void unsetDescription() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public String getHelp() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public STXstring xgetHelp() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean isSetHelp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void setHelp(String help) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(help);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void xsetHelp(STXstring help) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(help);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void unsetHelp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public String getStatusBar() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public STXstring xgetStatusBar() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean isSetStatusBar() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void setStatusBar(String statusBar) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setStringValue(statusBar);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void xsetStatusBar(STXstring statusBar) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(statusBar);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void unsetStatusBar() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public long getLocalSheetId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public XmlUnsignedInt xgetLocalSheetId() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean isSetLocalSheetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void setLocalSheetId(long localSheetId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setLongValue(localSheetId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void xsetLocalSheetId(XmlUnsignedInt localSheetId) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(localSheetId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void unsetLocalSheetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean getHidden() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[7]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public XmlBoolean xgetHidden() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[7]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean isSetHidden() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void setHidden(boolean hidden) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setBooleanValue(hidden);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void xsetHidden(XmlBoolean hidden) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(hidden);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void unsetHidden() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean getFunction() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[8]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public XmlBoolean xgetFunction() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[8]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean isSetFunction() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void setFunction(boolean function) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setBooleanValue(function);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void xsetFunction(XmlBoolean function) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(function);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void unsetFunction() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean getVbProcedure() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public XmlBoolean xgetVbProcedure() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean isSetVbProcedure() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void setVbProcedure(boolean vbProcedure) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setBooleanValue(vbProcedure);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void xsetVbProcedure(XmlBoolean vbProcedure) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(vbProcedure);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void unsetVbProcedure() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean getXlm() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[10]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public XmlBoolean xgetXlm() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[10]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean isSetXlm() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void setXlm(boolean xlm) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setBooleanValue(xlm);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void xsetXlm(XmlBoolean xlm) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(xlm);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void unsetXlm() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public long getFunctionGroupId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public XmlUnsignedInt xgetFunctionGroupId() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean isSetFunctionGroupId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void setFunctionGroupId(long functionGroupId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setLongValue(functionGroupId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void xsetFunctionGroupId(XmlUnsignedInt functionGroupId) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(functionGroupId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void unsetFunctionGroupId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public String getShortcutKey() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public STXstring xgetShortcutKey() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean isSetShortcutKey() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void setShortcutKey(String shortcutKey) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setStringValue(shortcutKey);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void xsetShortcutKey(STXstring shortcutKey) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(shortcutKey);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void unsetShortcutKey() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean getPublishToServer() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[13]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public XmlBoolean xgetPublishToServer() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[13]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean isSetPublishToServer() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void setPublishToServer(boolean publishToServer) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setBooleanValue(publishToServer);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void xsetPublishToServer(XmlBoolean publishToServer) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(publishToServer);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void unsetPublishToServer() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean getWorkbookParameter() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[14]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public XmlBoolean xgetWorkbookParameter() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[14]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public boolean isSetWorkbookParameter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void setWorkbookParameter(boolean workbookParameter) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.setBooleanValue(workbookParameter);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void xsetWorkbookParameter(XmlBoolean workbookParameter) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.set(workbookParameter);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName
    public void unsetWorkbookParameter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[14]);
        }
    }
}
