package org.apache.xmlbeans.impl.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument;

/* loaded from: classes11.dex */
public class SchemaAnnotationImpl implements SchemaAnnotation {
    private AppinfoDocument.Appinfo[] _appInfo;
    private String[] _appInfoAsXml;
    private SchemaAnnotation.Attribute[] _attributes;
    private SchemaContainer _container;
    private DocumentationDocument.Documentation[] _documentation;
    private String[] _documentationAsXml;
    private String _filename;

    public void setFilename(String filename) {
        this._filename = filename;
    }

    @Override // org.apache.xmlbeans.SchemaComponent
    public String getSourceName() {
        return this._filename;
    }

    @Override // org.apache.xmlbeans.SchemaAnnotation
    public XmlObject[] getApplicationInformation() {
        if (this._appInfo == null) {
            int n = this._appInfoAsXml.length;
            this._appInfo = new AppinfoDocument.Appinfo[n];
            for (int i = 0; i < n; i++) {
                String appInfo = this._appInfoAsXml[i];
                try {
                    this._appInfo[i] = AppinfoDocument.Factory.parse(appInfo).getAppinfo();
                } catch (XmlException e) {
                    this._appInfo[i] = AppinfoDocument.Factory.newInstance().getAppinfo();
                }
            }
        }
        return this._appInfo;
    }

    @Override // org.apache.xmlbeans.SchemaAnnotation
    public XmlObject[] getUserInformation() {
        if (this._documentation == null) {
            int n = this._documentationAsXml.length;
            this._documentation = new DocumentationDocument.Documentation[n];
            for (int i = 0; i < n; i++) {
                String doc = this._documentationAsXml[i];
                try {
                    this._documentation[i] = DocumentationDocument.Factory.parse(doc).getDocumentation();
                } catch (XmlException e) {
                    this._documentation[i] = DocumentationDocument.Factory.newInstance().getDocumentation();
                }
            }
        }
        return this._documentation;
    }

    @Override // org.apache.xmlbeans.SchemaAnnotation
    public SchemaAnnotation.Attribute[] getAttributes() {
        return this._attributes;
    }

    @Override // org.apache.xmlbeans.SchemaComponent
    public int getComponentType() {
        return 8;
    }

    @Override // org.apache.xmlbeans.SchemaComponent
    public SchemaTypeSystem getTypeSystem() {
        if (this._container != null) {
            return this._container.getTypeSystem();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaContainer getContainer() {
        return this._container;
    }

    @Override // org.apache.xmlbeans.SchemaComponent
    public QName getName() {
        return null;
    }

    @Override // org.apache.xmlbeans.SchemaComponent
    public SchemaComponent.Ref getComponentRef() {
        return null;
    }

    public static SchemaAnnotationImpl getAnnotation(SchemaContainer c, Annotated elem) {
        AnnotationDocument.Annotation ann = elem.getAnnotation();
        return getAnnotation(c, elem, ann);
    }

    public static SchemaAnnotationImpl getAnnotation(SchemaContainer c, XmlObject elem, AnnotationDocument.Annotation ann) {
        if (StscState.get().noAnn()) {
            return null;
        }
        SchemaAnnotationImpl result = new SchemaAnnotationImpl(c);
        ArrayList<AttributeImpl> attrArray = new ArrayList<>(2);
        addNoSchemaAttributes(elem, attrArray);
        if (ann == null) {
            if (attrArray.isEmpty()) {
                return null;
            }
            result._appInfo = new AppinfoDocument.Appinfo[0];
            result._documentation = new DocumentationDocument.Documentation[0];
        } else {
            result._appInfo = ann.getAppinfoArray();
            result._documentation = ann.getDocumentationArray();
            addNoSchemaAttributes(ann, attrArray);
        }
        result._attributes = (AttributeImpl[]) attrArray.toArray(new AttributeImpl[attrArray.size()]);
        return result;
    }

    private static void addNoSchemaAttributes(XmlObject elem, List<AttributeImpl> attrList) {
        String prefix;
        XmlCursor cursor = elem.newCursor();
        try {
            for (boolean hasAttributes = cursor.toFirstAttribute(); hasAttributes; hasAttributes = cursor.toNextAttribute()) {
                QName name = cursor.getName();
                String namespaceURI = name.getNamespaceURI();
                if (!"".equals(namespaceURI) && !"http://www.w3.org/2001/XMLSchema".equals(namespaceURI)) {
                    String attValue = cursor.getTextValue();
                    if (attValue.indexOf(58) <= 0) {
                        prefix = "";
                    } else {
                        prefix = attValue.substring(0, attValue.indexOf(58));
                    }
                    cursor.push();
                    cursor.toParent();
                    String valUri = cursor.namespaceForPrefix(prefix);
                    cursor.pop();
                    attrList.add(new AttributeImpl(name, attValue, valUri));
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private SchemaAnnotationImpl(SchemaContainer c) {
        this._container = c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaAnnotationImpl(SchemaContainer c, String[] aapStrings, String[] adocStrings, SchemaAnnotation.Attribute[] aat) {
        this._container = c;
        this._appInfoAsXml = aapStrings;
        this._documentationAsXml = adocStrings;
        this._attributes = aat;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class AttributeImpl implements SchemaAnnotation.Attribute {
        private QName _name;
        private String _value;
        private String _valueUri;

        /* JADX INFO: Access modifiers changed from: package-private */
        public AttributeImpl(QName name, String value, String valueUri) {
            this._name = name;
            this._value = value;
            this._valueUri = valueUri;
        }

        @Override // org.apache.xmlbeans.SchemaAnnotation.Attribute
        public QName getName() {
            return this._name;
        }

        @Override // org.apache.xmlbeans.SchemaAnnotation.Attribute
        public String getValue() {
            return this._value;
        }

        @Override // org.apache.xmlbeans.SchemaAnnotation.Attribute
        public String getValueUri() {
            return this._valueUri;
        }
    }
}
