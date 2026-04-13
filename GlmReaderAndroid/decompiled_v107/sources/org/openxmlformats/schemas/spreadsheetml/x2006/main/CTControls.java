package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTControls extends XmlObject {
    public static final DocumentFactory<CTControls> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcontrols75fftype");
    public static final SchemaType type = Factory.getType();

    CTControl addNewControl();

    CTControl getControlArray(int i);

    CTControl[] getControlArray();

    List<CTControl> getControlList();

    CTControl insertNewControl(int i);

    void removeControl(int i);

    void setControlArray(int i, CTControl cTControl);

    void setControlArray(CTControl[] cTControlArr);

    int sizeOfControlArray();
}
