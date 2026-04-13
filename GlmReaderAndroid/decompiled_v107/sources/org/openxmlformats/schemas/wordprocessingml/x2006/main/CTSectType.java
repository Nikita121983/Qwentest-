package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSectionMark;

/* loaded from: classes12.dex */
public interface CTSectType extends XmlObject {
    public static final DocumentFactory<CTSectType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsecttype7cebtype");
    public static final SchemaType type = Factory.getType();

    STSectionMark.Enum getVal();

    boolean isSetVal();

    void setVal(STSectionMark.Enum r1);

    void unsetVal();

    STSectionMark xgetVal();

    void xsetVal(STSectionMark sTSectionMark);
}
