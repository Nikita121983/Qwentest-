package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSdtDateMappingType;

/* loaded from: classes12.dex */
public interface CTSdtDateMappingType extends XmlObject {
    public static final DocumentFactory<CTSdtDateMappingType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsdtdatemappingtype5fb1type");
    public static final SchemaType type = Factory.getType();

    STSdtDateMappingType.Enum getVal();

    boolean isSetVal();

    void setVal(STSdtDateMappingType.Enum r1);

    void unsetVal();

    STSdtDateMappingType xgetVal();

    void xsetVal(STSdtDateMappingType sTSdtDateMappingType);
}
