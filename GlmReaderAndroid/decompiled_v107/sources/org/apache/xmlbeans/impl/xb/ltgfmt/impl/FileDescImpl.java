package org.apache.xmlbeans.impl.xb.ltgfmt.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.ltgfmt.Code;
import org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc;

/* loaded from: classes11.dex */
public class FileDescImpl extends XmlComplexContentImpl implements FileDesc {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.bea.com/2003/05/xmlbean/ltgfmt", "code"), new QName("", "tsDir"), new QName("", "folder"), new QName("", "fileName"), new QName("", "role"), new QName("", "validity")};
    private static final long serialVersionUID = 1;

    public FileDescImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public Code getCode() {
        Code code;
        synchronized (monitor()) {
            check_orphaned();
            Code target = (Code) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            code = target == null ? null : target;
        }
        return code;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public boolean isSetCode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public void setCode(Code code) {
        generatedSetterHelperImpl(code, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public Code addNewCode() {
        Code target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Code) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public void unsetCode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public String getTsDir() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public XmlToken xgetTsDir() {
        XmlToken target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public boolean isSetTsDir() {
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

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public void setTsDir(String tsDir) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(tsDir);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public void xsetTsDir(XmlToken tsDir) {
        synchronized (monitor()) {
            check_orphaned();
            XmlToken target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlToken) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(tsDir);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public void unsetTsDir() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public String getFolder() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public XmlToken xgetFolder() {
        XmlToken target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public boolean isSetFolder() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public void setFolder(String folder) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(folder);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public void xsetFolder(XmlToken folder) {
        synchronized (monitor()) {
            check_orphaned();
            XmlToken target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlToken) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(folder);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public void unsetFolder() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public String getFileName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public XmlToken xgetFileName() {
        XmlToken target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public boolean isSetFileName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public void setFileName(String fileName) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(fileName);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public void xsetFileName(XmlToken fileName) {
        synchronized (monitor()) {
            check_orphaned();
            XmlToken target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlToken) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(fileName);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public void unsetFileName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public FileDesc.Role.Enum getRole() {
        FileDesc.Role.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            r1 = target == null ? null : (FileDesc.Role.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public FileDesc.Role xgetRole() {
        FileDesc.Role target;
        synchronized (monitor()) {
            check_orphaned();
            target = (FileDesc.Role) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public boolean isSetRole() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public void setRole(FileDesc.Role.Enum role) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setEnumValue(role);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public void xsetRole(FileDesc.Role role) {
        synchronized (monitor()) {
            check_orphaned();
            FileDesc.Role target = (FileDesc.Role) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (FileDesc.Role) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(role);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public void unsetRole() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public boolean getValidity() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public XmlBoolean xgetValidity() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public boolean isSetValidity() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public void setValidity(boolean validity) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setBooleanValue(validity);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public void xsetValidity(XmlBoolean validity) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(validity);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc
    public void unsetValidity() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    /* loaded from: classes11.dex */
    public static class RoleImpl extends JavaStringEnumerationHolderEx implements FileDesc.Role {
        private static final long serialVersionUID = 1;

        public RoleImpl(SchemaType sType) {
            super(sType, false);
        }

        protected RoleImpl(SchemaType sType, boolean b) {
            super(sType, b);
        }
    }
}
