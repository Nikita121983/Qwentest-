package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTFFTextInput extends XmlObject {
    public static final DocumentFactory<CTFFTextInput> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfftextinput3155type");
    public static final SchemaType type = Factory.getType();

    CTString addNewDefault();

    CTString addNewFormat();

    CTDecimalNumber addNewMaxLength();

    CTFFTextType addNewType();

    CTString getDefault();

    CTString getFormat();

    CTDecimalNumber getMaxLength();

    CTFFTextType getType();

    boolean isSetDefault();

    boolean isSetFormat();

    boolean isSetMaxLength();

    boolean isSetType();

    void setDefault(CTString cTString);

    void setFormat(CTString cTString);

    void setMaxLength(CTDecimalNumber cTDecimalNumber);

    void setType(CTFFTextType cTFFTextType);

    void unsetDefault();

    void unsetFormat();

    void unsetMaxLength();

    void unsetType();
}
