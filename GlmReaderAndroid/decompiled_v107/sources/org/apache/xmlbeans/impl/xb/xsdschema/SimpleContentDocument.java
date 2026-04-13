package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface SimpleContentDocument extends XmlObject {
    public static final DocumentFactory<SimpleContentDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "simplecontent8acedoctype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface SimpleContent extends Annotated {
        public static final ElementFactory<SimpleContent> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "simplecontent9a5belemtype");
        public static final SchemaType type = Factory.getType();

        SimpleExtensionType addNewExtension();

        SimpleRestrictionType addNewRestriction();

        SimpleExtensionType getExtension();

        SimpleRestrictionType getRestriction();

        boolean isSetExtension();

        boolean isSetRestriction();

        void setExtension(SimpleExtensionType simpleExtensionType);

        void setRestriction(SimpleRestrictionType simpleRestrictionType);

        void unsetExtension();

        void unsetRestriction();
    }

    SimpleContent addNewSimpleContent();

    SimpleContent getSimpleContent();

    void setSimpleContent(SimpleContent simpleContent);
}
