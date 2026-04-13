package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTStrData extends XmlObject {
    public static final DocumentFactory<CTStrData> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctstrdatad58btype");
    public static final SchemaType type = Factory.getType();

    CTExtensionList addNewExtLst();

    CTStrVal addNewPt();

    CTUnsignedInt addNewPtCount();

    CTExtensionList getExtLst();

    CTStrVal getPtArray(int i);

    CTStrVal[] getPtArray();

    CTUnsignedInt getPtCount();

    List<CTStrVal> getPtList();

    CTStrVal insertNewPt(int i);

    boolean isSetExtLst();

    boolean isSetPtCount();

    void removePt(int i);

    void setExtLst(CTExtensionList cTExtensionList);

    void setPtArray(int i, CTStrVal cTStrVal);

    void setPtArray(CTStrVal[] cTStrValArr);

    void setPtCount(CTUnsignedInt cTUnsignedInt);

    int sizeOfPtArray();

    void unsetExtLst();

    void unsetPtCount();
}
