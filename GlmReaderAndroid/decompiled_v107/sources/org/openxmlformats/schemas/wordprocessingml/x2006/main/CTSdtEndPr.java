package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTSdtEndPr extends XmlObject {
    public static final DocumentFactory<CTSdtEndPr> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsdtendprbc6etype");
    public static final SchemaType type = Factory.getType();

    CTRPr addNewRPr();

    CTRPr getRPrArray(int i);

    CTRPr[] getRPrArray();

    List<CTRPr> getRPrList();

    CTRPr insertNewRPr(int i);

    void removeRPr(int i);

    void setRPrArray(int i, CTRPr cTRPr);

    void setRPrArray(CTRPr[] cTRPrArr);

    int sizeOfRPrArray();
}
