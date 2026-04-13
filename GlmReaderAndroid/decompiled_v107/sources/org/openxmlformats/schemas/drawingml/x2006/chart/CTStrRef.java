package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTStrRef extends XmlObject {
    public static final DocumentFactory<CTStrRef> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctstrref5d1atype");
    public static final SchemaType type = Factory.getType();

    CTExtensionList addNewExtLst();

    CTStrData addNewStrCache();

    CTExtensionList getExtLst();

    String getF();

    CTStrData getStrCache();

    boolean isSetExtLst();

    boolean isSetStrCache();

    void setExtLst(CTExtensionList cTExtensionList);

    void setF(String str);

    void setStrCache(CTStrData cTStrData);

    void unsetExtLst();

    void unsetStrCache();

    XmlString xgetF();

    void xsetF(XmlString xmlString);
}
