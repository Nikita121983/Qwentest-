package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTFFName extends XmlObject {
    public static final DocumentFactory<CTFFName> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctffname7b3dtype");
    public static final SchemaType type = Factory.getType();

    String getVal();

    boolean isSetVal();

    void setVal(String str);

    void unsetVal();

    STFFName xgetVal();

    void xsetVal(STFFName sTFFName);
}
