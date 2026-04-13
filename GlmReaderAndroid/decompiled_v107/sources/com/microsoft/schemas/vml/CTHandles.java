package com.microsoft.schemas.vml;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes9.dex */
public interface CTHandles extends XmlObject {
    public static final DocumentFactory<CTHandles> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cthandles5c1ftype");
    public static final SchemaType type = Factory.getType();

    CTH addNewH();

    CTH getHArray(int i);

    CTH[] getHArray();

    List<CTH> getHList();

    CTH insertNewH(int i);

    void removeH(int i);

    void setHArray(int i, CTH cth);

    void setHArray(CTH[] cthArr);

    int sizeOfHArray();
}
