package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTDefinedNames extends XmlObject {
    public static final DocumentFactory<CTDefinedNames> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdefinednamesce48type");
    public static final SchemaType type = Factory.getType();

    CTDefinedName addNewDefinedName();

    CTDefinedName getDefinedNameArray(int i);

    CTDefinedName[] getDefinedNameArray();

    List<CTDefinedName> getDefinedNameList();

    CTDefinedName insertNewDefinedName(int i);

    void removeDefinedName(int i);

    void setDefinedNameArray(int i, CTDefinedName cTDefinedName);

    void setDefinedNameArray(CTDefinedName[] cTDefinedNameArr);

    int sizeOfDefinedNameArray();
}
