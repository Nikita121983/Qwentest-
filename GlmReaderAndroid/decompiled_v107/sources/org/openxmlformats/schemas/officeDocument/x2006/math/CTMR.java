package org.openxmlformats.schemas.officeDocument.x2006.math;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTMR extends XmlObject {
    public static final DocumentFactory<CTMR> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctmr7ccdtype");
    public static final SchemaType type = Factory.getType();

    CTOMathArg addNewE();

    CTOMathArg getEArray(int i);

    CTOMathArg[] getEArray();

    List<CTOMathArg> getEList();

    CTOMathArg insertNewE(int i);

    void removeE(int i);

    void setEArray(int i, CTOMathArg cTOMathArg);

    void setEArray(CTOMathArg[] cTOMathArgArr);

    int sizeOfEArray();
}
