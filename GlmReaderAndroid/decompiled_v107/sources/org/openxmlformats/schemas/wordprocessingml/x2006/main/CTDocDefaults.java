package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTDocDefaults extends XmlObject {
    public static final DocumentFactory<CTDocDefaults> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdocdefaults2ea8type");
    public static final SchemaType type = Factory.getType();

    CTPPrDefault addNewPPrDefault();

    CTRPrDefault addNewRPrDefault();

    CTPPrDefault getPPrDefault();

    CTRPrDefault getRPrDefault();

    boolean isSetPPrDefault();

    boolean isSetRPrDefault();

    void setPPrDefault(CTPPrDefault cTPPrDefault);

    void setRPrDefault(CTRPrDefault cTRPrDefault);

    void unsetPPrDefault();

    void unsetRPrDefault();
}
