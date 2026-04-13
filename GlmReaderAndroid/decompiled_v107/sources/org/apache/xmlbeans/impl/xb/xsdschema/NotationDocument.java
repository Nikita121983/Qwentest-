package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface NotationDocument extends XmlObject {
    public static final DocumentFactory<NotationDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "notation3381doctype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface Notation extends Annotated {
        public static final ElementFactory<Notation> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "notation8b1felemtype");
        public static final SchemaType type = Factory.getType();

        String getName();

        String getPublic();

        String getSystem();

        boolean isSetPublic();

        boolean isSetSystem();

        void setName(String str);

        void setPublic(String str);

        void setSystem(String str);

        void unsetPublic();

        void unsetSystem();

        XmlNCName xgetName();

        Public xgetPublic();

        XmlAnyURI xgetSystem();

        void xsetName(XmlNCName xmlNCName);

        void xsetPublic(Public r1);

        void xsetSystem(XmlAnyURI xmlAnyURI);
    }

    Notation addNewNotation();

    Notation getNotation();

    void setNotation(Notation notation);
}
