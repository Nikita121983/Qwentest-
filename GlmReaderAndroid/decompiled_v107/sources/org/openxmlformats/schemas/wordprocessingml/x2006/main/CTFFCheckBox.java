package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTFFCheckBox extends XmlObject {
    public static final DocumentFactory<CTFFCheckBox> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctffcheckboxf3a5type");
    public static final SchemaType type = Factory.getType();

    CTOnOff addNewChecked();

    CTOnOff addNewDefault();

    CTHpsMeasure addNewSize();

    CTOnOff addNewSizeAuto();

    CTOnOff getChecked();

    CTOnOff getDefault();

    CTHpsMeasure getSize();

    CTOnOff getSizeAuto();

    boolean isSetChecked();

    boolean isSetDefault();

    boolean isSetSize();

    boolean isSetSizeAuto();

    void setChecked(CTOnOff cTOnOff);

    void setDefault(CTOnOff cTOnOff);

    void setSize(CTHpsMeasure cTHpsMeasure);

    void setSizeAuto(CTOnOff cTOnOff);

    void unsetChecked();

    void unsetDefault();

    void unsetSize();

    void unsetSizeAuto();
}
