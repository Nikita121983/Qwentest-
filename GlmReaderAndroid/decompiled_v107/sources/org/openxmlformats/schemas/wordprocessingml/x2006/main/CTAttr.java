package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;

/* loaded from: classes12.dex */
public interface CTAttr extends XmlObject {
    public static final DocumentFactory<CTAttr> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctattre117type");
    public static final SchemaType type = Factory.getType();

    String getName();

    String getUri();

    String getVal();

    boolean isSetUri();

    void setName(String str);

    void setUri(String str);

    void setVal(String str);

    void unsetUri();

    STString xgetName();

    STString xgetUri();

    STString xgetVal();

    void xsetName(STString sTString);

    void xsetUri(STString sTString);

    void xsetVal(STString sTString);
}
