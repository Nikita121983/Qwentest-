package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTExternalDefinedNames extends XmlObject {
    public static final DocumentFactory<CTExternalDefinedNames> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctexternaldefinednamesccf3type");
    public static final SchemaType type = Factory.getType();

    CTExternalDefinedName addNewDefinedName();

    CTExternalDefinedName getDefinedNameArray(int i);

    CTExternalDefinedName[] getDefinedNameArray();

    List<CTExternalDefinedName> getDefinedNameList();

    CTExternalDefinedName insertNewDefinedName(int i);

    void removeDefinedName(int i);

    void setDefinedNameArray(int i, CTExternalDefinedName cTExternalDefinedName);

    void setDefinedNameArray(CTExternalDefinedName[] cTExternalDefinedNameArr);

    int sizeOfDefinedNameArray();
}
