package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTSchema extends XmlObject {
    public static final DocumentFactory<CTSchema> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctschema0e6atype");
    public static final SchemaType type = Factory.getType();

    String getID();

    String getNamespace();

    String getSchemaLanguage();

    String getSchemaRef();

    boolean isSetNamespace();

    boolean isSetSchemaLanguage();

    boolean isSetSchemaRef();

    void setID(String str);

    void setNamespace(String str);

    void setSchemaLanguage(String str);

    void setSchemaRef(String str);

    void unsetNamespace();

    void unsetSchemaLanguage();

    void unsetSchemaRef();

    XmlString xgetID();

    XmlString xgetNamespace();

    XmlToken xgetSchemaLanguage();

    XmlString xgetSchemaRef();

    void xsetID(XmlString xmlString);

    void xsetNamespace(XmlString xmlString);

    void xsetSchemaLanguage(XmlToken xmlToken);

    void xsetSchemaRef(XmlString xmlString);
}
