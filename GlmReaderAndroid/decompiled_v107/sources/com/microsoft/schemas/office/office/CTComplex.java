package com.microsoft.schemas.office.office;

import com.microsoft.schemas.vml.STExt;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes.dex */
public interface CTComplex extends XmlObject {
    public static final DocumentFactory<CTComplex> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcomplexd4a9type");
    public static final SchemaType type = Factory.getType();

    STExt.Enum getExt();

    boolean isSetExt();

    void setExt(STExt.Enum r1);

    void unsetExt();

    STExt xgetExt();

    void xsetExt(STExt sTExt);
}
