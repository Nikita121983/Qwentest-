package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

/* loaded from: classes12.dex */
public interface CTXmlColumnPr extends XmlObject {
    public static final DocumentFactory<CTXmlColumnPr> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctxmlcolumnprc14etype");
    public static final SchemaType type = Factory.getType();

    CTExtensionList addNewExtLst();

    boolean getDenormalized();

    CTExtensionList getExtLst();

    long getMapId();

    String getXmlDataType();

    String getXpath();

    boolean isSetDenormalized();

    boolean isSetExtLst();

    void setDenormalized(boolean z);

    void setExtLst(CTExtensionList cTExtensionList);

    void setMapId(long j);

    void setXmlDataType(String str);

    void setXpath(String str);

    void unsetDenormalized();

    void unsetExtLst();

    XmlBoolean xgetDenormalized();

    XmlUnsignedInt xgetMapId();

    STXmlDataType xgetXmlDataType();

    STXstring xgetXpath();

    void xsetDenormalized(XmlBoolean xmlBoolean);

    void xsetMapId(XmlUnsignedInt xmlUnsignedInt);

    void xsetXmlDataType(STXmlDataType sTXmlDataType);

    void xsetXpath(STXstring sTXstring);
}
