package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

/* loaded from: classes11.dex */
public interface CTTx extends XmlObject {
    public static final DocumentFactory<CTTx> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttx9678type");
    public static final SchemaType type = Factory.getType();

    CTTextBody addNewRich();

    CTStrRef addNewStrRef();

    CTTextBody getRich();

    CTStrRef getStrRef();

    boolean isSetRich();

    boolean isSetStrRef();

    void setRich(CTTextBody cTTextBody);

    void setStrRef(CTStrRef cTStrRef);

    void unsetRich();

    void unsetStrRef();
}
