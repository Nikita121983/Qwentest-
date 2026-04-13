package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTAdjustHandleList extends XmlObject {
    public static final DocumentFactory<CTAdjustHandleList> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctadjusthandlelistfdb0type");
    public static final SchemaType type = Factory.getType();

    CTPolarAdjustHandle addNewAhPolar();

    CTXYAdjustHandle addNewAhXY();

    CTPolarAdjustHandle getAhPolarArray(int i);

    CTPolarAdjustHandle[] getAhPolarArray();

    List<CTPolarAdjustHandle> getAhPolarList();

    CTXYAdjustHandle getAhXYArray(int i);

    CTXYAdjustHandle[] getAhXYArray();

    List<CTXYAdjustHandle> getAhXYList();

    CTPolarAdjustHandle insertNewAhPolar(int i);

    CTXYAdjustHandle insertNewAhXY(int i);

    void removeAhPolar(int i);

    void removeAhXY(int i);

    void setAhPolarArray(int i, CTPolarAdjustHandle cTPolarAdjustHandle);

    void setAhPolarArray(CTPolarAdjustHandle[] cTPolarAdjustHandleArr);

    void setAhXYArray(int i, CTXYAdjustHandle cTXYAdjustHandle);

    void setAhXYArray(CTXYAdjustHandle[] cTXYAdjustHandleArr);

    int sizeOfAhPolarArray();

    int sizeOfAhXYArray();
}
