package com.microsoft.schemas.vml;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes9.dex */
public interface CTF extends XmlObject {
    public static final DocumentFactory<CTF> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfbc3atype");
    public static final SchemaType type = Factory.getType();

    String getEqn();

    boolean isSetEqn();

    void setEqn(String str);

    void unsetEqn();

    XmlString xgetEqn();

    void xsetEqn(XmlString xmlString);
}
