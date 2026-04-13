package org.apache.xmlbeans.impl.xb.ltgfmt.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;
import org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestCaseImpl;

/* loaded from: classes11.dex */
public class TestCaseImpl extends XmlComplexContentImpl implements TestCase {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.bea.com/2003/05/xmlbean/ltgfmt", "description"), new QName("http://www.bea.com/2003/05/xmlbean/ltgfmt", "files"), new QName("", "id"), new QName("", "origin"), new QName("", "modified")};
    private static final long serialVersionUID = 1;

    public TestCaseImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public String getDescription() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public XmlString xgetDescription() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public boolean isSetDescription() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public void setDescription(String description) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(description);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public void xsetDescription(XmlString description) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.set(description);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public void unsetDescription() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public TestCase.Files getFiles() {
        TestCase.Files files;
        synchronized (monitor()) {
            check_orphaned();
            TestCase.Files target = (TestCase.Files) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            files = target == null ? null : target;
        }
        return files;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public void setFiles(TestCase.Files files) {
        generatedSetterHelperImpl(files, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public TestCase.Files addNewFiles() {
        TestCase.Files target;
        synchronized (monitor()) {
            check_orphaned();
            target = (TestCase.Files) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public void xsetId(XmlID id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlID target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlID) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(id);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public String getOrigin() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public XmlToken xgetOrigin() {
        XmlToken target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public boolean isSetOrigin() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public void setOrigin(String origin) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(origin);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public void xsetOrigin(XmlToken origin) {
        synchronized (monitor()) {
            check_orphaned();
            XmlToken target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlToken) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(origin);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public void unsetOrigin() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public boolean getModified() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public XmlBoolean xgetModified() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public boolean isSetModified() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public void setModified(boolean modified) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setBooleanValue(modified);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public void xsetModified(XmlBoolean modified) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(modified);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase
    public void unsetModified() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    /* loaded from: classes11.dex */
    public static class FilesImpl extends XmlComplexContentImpl implements TestCase.Files {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.bea.com/2003/05/xmlbean/ltgfmt", "file")};
        private static final long serialVersionUID = 1;

        public FilesImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase.Files
        public List<FileDesc> getFileList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestCaseImpl$FilesImpl$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return TestCaseImpl.FilesImpl.this.getFileArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestCaseImpl$FilesImpl$$ExternalSyntheticLambda1
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        TestCaseImpl.FilesImpl.this.setFileArray(((Integer) obj).intValue(), (FileDesc) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestCaseImpl$FilesImpl$$ExternalSyntheticLambda2
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return TestCaseImpl.FilesImpl.this.insertNewFile(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestCaseImpl$FilesImpl$$ExternalSyntheticLambda3
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        TestCaseImpl.FilesImpl.this.removeFile(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestCaseImpl$FilesImpl$$ExternalSyntheticLambda4
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(TestCaseImpl.FilesImpl.this.sizeOfFileArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase.Files
        public FileDesc[] getFileArray() {
            return (FileDesc[]) getXmlObjectArray(PROPERTY_QNAME[0], new FileDesc[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase.Files
        public FileDesc getFileArray(int i) {
            FileDesc target;
            synchronized (monitor()) {
                check_orphaned();
                target = (FileDesc) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase.Files
        public int sizeOfFileArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase.Files
        public void setFileArray(FileDesc[] fileArray) {
            check_orphaned();
            arraySetterHelper(fileArray, PROPERTY_QNAME[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase.Files
        public void setFileArray(int i, FileDesc file) {
            generatedSetterHelperImpl(file, PROPERTY_QNAME[0], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase.Files
        public FileDesc insertNewFile(int i) {
            FileDesc target;
            synchronized (monitor()) {
                check_orphaned();
                target = (FileDesc) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase.Files
        public FileDesc addNewFile() {
            FileDesc target;
            synchronized (monitor()) {
                check_orphaned();
                target = (FileDesc) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestCase.Files
        public void removeFile(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }
    }
}
