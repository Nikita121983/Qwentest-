package org.openxmlformats.schemas.officeDocument.x2006.math;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;

/* loaded from: classes11.dex */
public interface CTText extends STString {
    public static final DocumentFactory<CTText> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttext8965type");
    public static final SchemaType type = Factory.getType();

    SpaceAttribute.Space.Enum getSpace();

    boolean isSetSpace();

    void setSpace(SpaceAttribute.Space.Enum r1);

    void unsetSpace();

    SpaceAttribute.Space xgetSpace();

    void xsetSpace(SpaceAttribute.Space space);
}
