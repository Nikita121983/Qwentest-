package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTChartFormats extends XmlObject {
    public static final DocumentFactory<CTChartFormats> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctchartformats9467type");
    public static final SchemaType type = Factory.getType();

    CTChartFormat addNewChartFormat();

    CTChartFormat getChartFormatArray(int i);

    CTChartFormat[] getChartFormatArray();

    List<CTChartFormat> getChartFormatList();

    long getCount();

    CTChartFormat insertNewChartFormat(int i);

    boolean isSetCount();

    void removeChartFormat(int i);

    void setChartFormatArray(int i, CTChartFormat cTChartFormat);

    void setChartFormatArray(CTChartFormat[] cTChartFormatArr);

    void setCount(long j);

    int sizeOfChartFormatArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);
}
