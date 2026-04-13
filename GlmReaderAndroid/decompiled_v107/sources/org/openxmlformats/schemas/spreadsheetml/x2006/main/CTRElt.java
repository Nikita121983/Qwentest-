package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

/* loaded from: classes12.dex */
public interface CTRElt extends XmlObject {
    public static final DocumentFactory<CTRElt> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctrelt6464type");
    public static final SchemaType type = Factory.getType();

    CTRPrElt addNewRPr();

    CTRPrElt getRPr();

    String getT();

    boolean isSetRPr();

    void setRPr(CTRPrElt cTRPrElt);

    void setT(String str);

    void unsetRPr();

    STXstring xgetT();

    void xsetT(STXstring sTXstring);
}
