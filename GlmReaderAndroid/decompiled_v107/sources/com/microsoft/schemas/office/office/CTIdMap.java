package com.microsoft.schemas.office.office;

import com.microsoft.schemas.vml.STExt;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes.dex */
public interface CTIdMap extends XmlObject {
    public static final DocumentFactory<CTIdMap> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctidmap63fatype");
    public static final SchemaType type = Factory.getType();

    String getData();

    STExt.Enum getExt();

    boolean isSetData();

    boolean isSetExt();

    void setData(String str);

    void setExt(STExt.Enum r1);

    void unsetData();

    void unsetExt();

    XmlString xgetData();

    STExt xgetExt();

    void xsetData(XmlString xmlString);

    void xsetExt(STExt sTExt);
}
