package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

/* loaded from: classes12.dex */
public interface CTXmlPr extends XmlObject {
    public static final DocumentFactory<CTXmlPr> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctxmlpr2c58type");
    public static final SchemaType type = Factory.getType();

    CTExtensionList addNewExtLst();

    CTExtensionList getExtLst();

    long getMapId();

    String getXmlDataType();

    String getXpath();

    boolean isSetExtLst();

    void setExtLst(CTExtensionList cTExtensionList);

    void setMapId(long j);

    void setXmlDataType(String str);

    void setXpath(String str);

    void unsetExtLst();

    XmlUnsignedInt xgetMapId();

    STXmlDataType xgetXmlDataType();

    STXstring xgetXpath();

    void xsetMapId(XmlUnsignedInt xmlUnsignedInt);

    void xsetXmlDataType(STXmlDataType sTXmlDataType);

    void xsetXpath(STXstring sTXstring);
}
