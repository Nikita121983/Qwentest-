package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTCustSplit extends XmlObject {
    public static final DocumentFactory<CTCustSplit> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcustsplit93bftype");
    public static final SchemaType type = Factory.getType();

    CTUnsignedInt addNewSecondPiePt();

    CTUnsignedInt getSecondPiePtArray(int i);

    CTUnsignedInt[] getSecondPiePtArray();

    List<CTUnsignedInt> getSecondPiePtList();

    CTUnsignedInt insertNewSecondPiePt(int i);

    void removeSecondPiePt(int i);

    void setSecondPiePtArray(int i, CTUnsignedInt cTUnsignedInt);

    void setSecondPiePtArray(CTUnsignedInt[] cTUnsignedIntArr);

    int sizeOfSecondPiePtArray();
}
