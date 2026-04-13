package org.apache.xmlbeans.impl.xb.xsdownload.impl;

import com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda66;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.JavaListObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry;

/* loaded from: classes11.dex */
public class DownloadedSchemaEntryImpl extends XmlComplexContentImpl implements DownloadedSchemaEntry {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.bea.com/2003/01/xmlbean/xsdownload", "filename"), new QName("http://www.bea.com/2003/01/xmlbean/xsdownload", "sha1"), new QName("http://www.bea.com/2003/01/xmlbean/xsdownload", "schemaLocation"), new QName("http://www.bea.com/2003/01/xmlbean/xsdownload", "namespace")};
    private static final long serialVersionUID = 1;

    public DownloadedSchemaEntryImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public String getFilename() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public XmlToken xgetFilename() {
        XmlToken target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlToken) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public void setFilename(String filename) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(filename);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public void xsetFilename(XmlToken filename) {
        synchronized (monitor()) {
            check_orphaned();
            XmlToken target = (XmlToken) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (XmlToken) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.set(filename);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public String getSha1() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public XmlToken xgetSha1() {
        XmlToken target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlToken) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public void setSha1(String sha1) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(sha1);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public void xsetSha1(XmlToken sha1) {
        synchronized (monitor()) {
            check_orphaned();
            XmlToken target = (XmlToken) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (XmlToken) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(sha1);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public List<String> getSchemaLocationList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemaEntryImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return DownloadedSchemaEntryImpl.this.getSchemaLocationArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemaEntryImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    DownloadedSchemaEntryImpl.this.setSchemaLocationArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemaEntryImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    DownloadedSchemaEntryImpl.this.insertSchemaLocation(((Integer) obj).intValue(), (String) obj2);
                }
            }, new DownloadedSchemaEntryImpl$$ExternalSyntheticLambda4(this), new DownloadedSchemaEntryImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getSchemaLocationArray$0(int x$0) {
        return new String[x$0];
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public String[] getSchemaLocationArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[2], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemaEntryImpl$$ExternalSyntheticLambda7
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return DownloadedSchemaEntryImpl.lambda$getSchemaLocationArray$0(i);
            }
        });
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public String getSchemaLocationArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public List<XmlAnyURI> xgetSchemaLocationList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemaEntryImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return DownloadedSchemaEntryImpl.this.xgetSchemaLocationArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemaEntryImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    DownloadedSchemaEntryImpl.this.xsetSchemaLocationArray(((Integer) obj).intValue(), (XmlAnyURI) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemaEntryImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return DownloadedSchemaEntryImpl.this.insertNewSchemaLocation(((Integer) obj).intValue());
                }
            }, new DownloadedSchemaEntryImpl$$ExternalSyntheticLambda4(this), new DownloadedSchemaEntryImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlAnyURI[] lambda$xgetSchemaLocationArray$1(int x$0) {
        return new XmlAnyURI[x$0];
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public XmlAnyURI[] xgetSchemaLocationArray() {
        return (XmlAnyURI[]) xgetArray(PROPERTY_QNAME[2], new IntFunction() { // from class: org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemaEntryImpl$$ExternalSyntheticLambda6
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return DownloadedSchemaEntryImpl.lambda$xgetSchemaLocationArray$1(i);
            }
        });
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public XmlAnyURI xgetSchemaLocationArray(int i) {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public int sizeOfSchemaLocationArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public void setSchemaLocationArray(String[] schemaLocationArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(schemaLocationArray, PROPERTY_QNAME[2]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public void setSchemaLocationArray(int i, String schemaLocation) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(schemaLocation);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public void xsetSchemaLocationArray(XmlAnyURI[] schemaLocationArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(schemaLocationArray, PROPERTY_QNAME[2]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public void xsetSchemaLocationArray(int i, XmlAnyURI schemaLocation) {
        synchronized (monitor()) {
            check_orphaned();
            XmlAnyURI target = (XmlAnyURI) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(schemaLocation);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public void insertSchemaLocation(int i, String schemaLocation) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[2], i);
            target.setStringValue(schemaLocation);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public void addSchemaLocation(String schemaLocation) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[2]);
            target.setStringValue(schemaLocation);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public XmlAnyURI insertNewSchemaLocation(int i) {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public XmlAnyURI addNewSchemaLocation() {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public void removeSchemaLocation(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public String getNamespace() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public XmlAnyURI xgetNamespace() {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().find_element_user(PROPERTY_QNAME[3], 0);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public boolean isSetNamespace() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public void setNamespace(String namespace) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(namespace);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public void xsetNamespace(XmlAnyURI namespace) {
        synchronized (monitor()) {
            check_orphaned();
            XmlAnyURI target = (XmlAnyURI) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (target == null) {
                target = (XmlAnyURI) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            target.set(namespace);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry
    public void unsetNamespace() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
