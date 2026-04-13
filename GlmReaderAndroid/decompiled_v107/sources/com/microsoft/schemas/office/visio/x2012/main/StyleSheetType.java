package com.microsoft.schemas.office.visio.x2012.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes.dex */
public interface StyleSheetType extends SheetType {
    public static final DocumentFactory<StyleSheetType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "stylesheettypeebcbtype");
    public static final SchemaType type = Factory.getType();

    long getID();

    boolean getIsCustomName();

    boolean getIsCustomNameU();

    String getName();

    String getNameU();

    boolean isSetIsCustomName();

    boolean isSetIsCustomNameU();

    boolean isSetName();

    boolean isSetNameU();

    void setID(long j);

    void setIsCustomName(boolean z);

    void setIsCustomNameU(boolean z);

    void setName(String str);

    void setNameU(String str);

    void unsetIsCustomName();

    void unsetIsCustomNameU();

    void unsetName();

    void unsetNameU();

    XmlUnsignedInt xgetID();

    XmlBoolean xgetIsCustomName();

    XmlBoolean xgetIsCustomNameU();

    XmlString xgetName();

    XmlString xgetNameU();

    void xsetID(XmlUnsignedInt xmlUnsignedInt);

    void xsetIsCustomName(XmlBoolean xmlBoolean);

    void xsetIsCustomNameU(XmlBoolean xmlBoolean);

    void xsetName(XmlString xmlString);

    void xsetNameU(XmlString xmlString);
}
