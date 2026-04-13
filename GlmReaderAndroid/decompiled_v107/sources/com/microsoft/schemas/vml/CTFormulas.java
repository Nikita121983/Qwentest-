package com.microsoft.schemas.vml;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes9.dex */
public interface CTFormulas extends XmlObject {
    public static final DocumentFactory<CTFormulas> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctformulas808btype");
    public static final SchemaType type = Factory.getType();

    CTF addNewF();

    CTF getFArray(int i);

    CTF[] getFArray();

    List<CTF> getFList();

    CTF insertNewF(int i);

    void removeF(int i);

    void setFArray(int i, CTF ctf);

    void setFArray(CTF[] ctfArr);

    int sizeOfFArray();
}
