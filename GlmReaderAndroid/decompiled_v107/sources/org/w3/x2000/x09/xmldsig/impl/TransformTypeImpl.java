package org.w3.x2000.x09.xmldsig.impl;

import com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda66;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.JavaListObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.w3.x2000.x09.xmldsig.TransformType;

/* loaded from: classes12.dex */
public class TransformTypeImpl extends XmlComplexContentImpl implements TransformType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XML_DIGSIG_NS, "XPath"), new QName("", "Algorithm")};
    private static final long serialVersionUID = 1;

    public TransformTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public List<String> getXPathList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.w3.x2000.x09.xmldsig.impl.TransformTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TransformTypeImpl.this.getXPathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.w3.x2000.x09.xmldsig.impl.TransformTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    TransformTypeImpl.this.setXPathArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: org.w3.x2000.x09.xmldsig.impl.TransformTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    TransformTypeImpl.this.insertXPath(((Integer) obj).intValue(), (String) obj2);
                }
            }, new TransformTypeImpl$$ExternalSyntheticLambda4(this), new TransformTypeImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getXPathArray$0(int x$0) {
        return new String[x$0];
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public String[] getXPathArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[0], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: org.w3.x2000.x09.xmldsig.impl.TransformTypeImpl$$ExternalSyntheticLambda6
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return TransformTypeImpl.lambda$getXPathArray$0(i);
            }
        });
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public String getXPathArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public List<XmlString> xgetXPathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.w3.x2000.x09.xmldsig.impl.TransformTypeImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TransformTypeImpl.this.xgetXPathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.w3.x2000.x09.xmldsig.impl.TransformTypeImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    TransformTypeImpl.this.xsetXPathArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: org.w3.x2000.x09.xmldsig.impl.TransformTypeImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TransformTypeImpl.this.insertNewXPath(((Integer) obj).intValue());
                }
            }, new TransformTypeImpl$$ExternalSyntheticLambda4(this), new TransformTypeImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetXPathArray$1(int x$0) {
        return new XmlString[x$0];
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public XmlString[] xgetXPathArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[0], new IntFunction() { // from class: org.w3.x2000.x09.xmldsig.impl.TransformTypeImpl$$ExternalSyntheticLambda1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return TransformTypeImpl.lambda$xgetXPathArray$1(i);
            }
        });
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public XmlString xgetXPathArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public int sizeOfXPathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public void setXPathArray(String[] xPathArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(xPathArray, PROPERTY_QNAME[0]);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public void setXPathArray(int i, String xPath) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(xPath);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public void xsetXPathArray(XmlString[] xPathArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(xPathArray, PROPERTY_QNAME[0]);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public void xsetXPathArray(int i, XmlString xPath) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(xPath);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public void insertXPath(int i, String xPath) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            target.setStringValue(xPath);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public void addXPath(String xPath) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            target.setStringValue(xPath);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public XmlString insertNewXPath(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public XmlString addNewXPath() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public void removeXPath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public String getAlgorithm() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public XmlAnyURI xgetAlgorithm() {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public void setAlgorithm(String algorithm) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(algorithm);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformType
    public void xsetAlgorithm(XmlAnyURI algorithm) {
        synchronized (monitor()) {
            check_orphaned();
            XmlAnyURI target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlAnyURI) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(algorithm);
        }
    }
}
