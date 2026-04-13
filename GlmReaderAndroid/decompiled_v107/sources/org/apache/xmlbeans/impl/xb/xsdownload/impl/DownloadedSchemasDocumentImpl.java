package org.apache.xmlbeans.impl.xb.xsdownload.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry;
import org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument;
import org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemasDocumentImpl;

/* loaded from: classes11.dex */
public class DownloadedSchemasDocumentImpl extends XmlComplexContentImpl implements DownloadedSchemasDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.bea.com/2003/01/xmlbean/xsdownload", "downloaded-schemas")};
    private static final long serialVersionUID = 1;

    public DownloadedSchemasDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument
    public DownloadedSchemasDocument.DownloadedSchemas getDownloadedSchemas() {
        DownloadedSchemasDocument.DownloadedSchemas downloadedSchemas;
        synchronized (monitor()) {
            check_orphaned();
            DownloadedSchemasDocument.DownloadedSchemas target = (DownloadedSchemasDocument.DownloadedSchemas) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            downloadedSchemas = target == null ? null : target;
        }
        return downloadedSchemas;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument
    public void setDownloadedSchemas(DownloadedSchemasDocument.DownloadedSchemas downloadedSchemas) {
        generatedSetterHelperImpl(downloadedSchemas, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument
    public DownloadedSchemasDocument.DownloadedSchemas addNewDownloadedSchemas() {
        DownloadedSchemasDocument.DownloadedSchemas target;
        synchronized (monitor()) {
            check_orphaned();
            target = (DownloadedSchemasDocument.DownloadedSchemas) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class DownloadedSchemasImpl extends XmlComplexContentImpl implements DownloadedSchemasDocument.DownloadedSchemas {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.bea.com/2003/01/xmlbean/xsdownload", "entry"), new QName("", "defaultDirectory")};
        private static final long serialVersionUID = 1;

        public DownloadedSchemasImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument.DownloadedSchemas
        public List<DownloadedSchemaEntry> getEntryList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemasDocumentImpl$DownloadedSchemasImpl$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return DownloadedSchemasDocumentImpl.DownloadedSchemasImpl.this.getEntryArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemasDocumentImpl$DownloadedSchemasImpl$$ExternalSyntheticLambda1
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        DownloadedSchemasDocumentImpl.DownloadedSchemasImpl.this.setEntryArray(((Integer) obj).intValue(), (DownloadedSchemaEntry) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemasDocumentImpl$DownloadedSchemasImpl$$ExternalSyntheticLambda2
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return DownloadedSchemasDocumentImpl.DownloadedSchemasImpl.this.insertNewEntry(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemasDocumentImpl$DownloadedSchemasImpl$$ExternalSyntheticLambda3
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        DownloadedSchemasDocumentImpl.DownloadedSchemasImpl.this.removeEntry(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemasDocumentImpl$DownloadedSchemasImpl$$ExternalSyntheticLambda4
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(DownloadedSchemasDocumentImpl.DownloadedSchemasImpl.this.sizeOfEntryArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument.DownloadedSchemas
        public DownloadedSchemaEntry[] getEntryArray() {
            return (DownloadedSchemaEntry[]) getXmlObjectArray(PROPERTY_QNAME[0], new DownloadedSchemaEntry[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument.DownloadedSchemas
        public DownloadedSchemaEntry getEntryArray(int i) {
            DownloadedSchemaEntry target;
            synchronized (monitor()) {
                check_orphaned();
                target = (DownloadedSchemaEntry) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument.DownloadedSchemas
        public int sizeOfEntryArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument.DownloadedSchemas
        public void setEntryArray(DownloadedSchemaEntry[] entryArray) {
            check_orphaned();
            arraySetterHelper(entryArray, PROPERTY_QNAME[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument.DownloadedSchemas
        public void setEntryArray(int i, DownloadedSchemaEntry entry) {
            generatedSetterHelperImpl(entry, PROPERTY_QNAME[0], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument.DownloadedSchemas
        public DownloadedSchemaEntry insertNewEntry(int i) {
            DownloadedSchemaEntry target;
            synchronized (monitor()) {
                check_orphaned();
                target = (DownloadedSchemaEntry) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument.DownloadedSchemas
        public DownloadedSchemaEntry addNewEntry() {
            DownloadedSchemaEntry target;
            synchronized (monitor()) {
                check_orphaned();
                target = (DownloadedSchemaEntry) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument.DownloadedSchemas
        public void removeEntry(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument.DownloadedSchemas
        public String getDefaultDirectory() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument.DownloadedSchemas
        public XmlToken xgetDefaultDirectory() {
            XmlToken target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument.DownloadedSchemas
        public boolean isSetDefaultDirectory() {
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

        @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument.DownloadedSchemas
        public void setDefaultDirectory(String defaultDirectory) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                }
                target.setStringValue(defaultDirectory);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument.DownloadedSchemas
        public void xsetDefaultDirectory(XmlToken defaultDirectory) {
            synchronized (monitor()) {
                check_orphaned();
                XmlToken target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (XmlToken) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                }
                target.set(defaultDirectory);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument.DownloadedSchemas
        public void unsetDefaultDirectory() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[1]);
            }
        }
    }
}
