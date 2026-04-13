package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTConditionalFormats extends XmlObject {
    public static final DocumentFactory<CTConditionalFormats> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctconditionalformats02fftype");
    public static final SchemaType type = Factory.getType();

    CTConditionalFormat addNewConditionalFormat();

    CTConditionalFormat getConditionalFormatArray(int i);

    CTConditionalFormat[] getConditionalFormatArray();

    List<CTConditionalFormat> getConditionalFormatList();

    long getCount();

    CTConditionalFormat insertNewConditionalFormat(int i);

    boolean isSetCount();

    void removeConditionalFormat(int i);

    void setConditionalFormatArray(int i, CTConditionalFormat cTConditionalFormat);

    void setConditionalFormatArray(CTConditionalFormat[] cTConditionalFormatArr);

    void setCount(long j);

    int sizeOfConditionalFormatArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);
}
