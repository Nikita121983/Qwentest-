package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTDashStopList extends XmlObject {
    public static final DocumentFactory<CTDashStopList> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdashstoplist920dtype");
    public static final SchemaType type = Factory.getType();

    CTDashStop addNewDs();

    CTDashStop getDsArray(int i);

    CTDashStop[] getDsArray();

    List<CTDashStop> getDsList();

    CTDashStop insertNewDs(int i);

    void removeDs(int i);

    void setDsArray(int i, CTDashStop cTDashStop);

    void setDsArray(CTDashStop[] cTDashStopArr);

    int sizeOfDsArray();
}
