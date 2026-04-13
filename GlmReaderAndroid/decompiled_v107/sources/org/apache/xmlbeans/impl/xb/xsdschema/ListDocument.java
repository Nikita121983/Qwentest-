package org.apache.xmlbeans.impl.xb.xsdschema;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface ListDocument extends XmlObject {
    public static final DocumentFactory<ListDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "listcde5doctype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface List extends Annotated {
        public static final ElementFactory<List> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "list391felemtype");
        public static final SchemaType type = Factory.getType();

        LocalSimpleType addNewSimpleType();

        QName getItemType();

        LocalSimpleType getSimpleType();

        boolean isSetItemType();

        boolean isSetSimpleType();

        void setItemType(QName qName);

        void setSimpleType(LocalSimpleType localSimpleType);

        void unsetItemType();

        void unsetSimpleType();

        XmlQName xgetItemType();

        void xsetItemType(XmlQName xmlQName);
    }

    List addNewList();

    List getList();

    void setList(List list);
}
