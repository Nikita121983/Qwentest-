package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTHeaderFooter extends XmlObject {
    public static final DocumentFactory<CTHeaderFooter> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctheaderfooterb29dtype");
    public static final SchemaType type = Factory.getType();

    CTExtensionListModify addNewExtLst();

    boolean getDt();

    CTExtensionListModify getExtLst();

    boolean getFtr();

    boolean getHdr();

    boolean getSldNum();

    boolean isSetDt();

    boolean isSetExtLst();

    boolean isSetFtr();

    boolean isSetHdr();

    boolean isSetSldNum();

    void setDt(boolean z);

    void setExtLst(CTExtensionListModify cTExtensionListModify);

    void setFtr(boolean z);

    void setHdr(boolean z);

    void setSldNum(boolean z);

    void unsetDt();

    void unsetExtLst();

    void unsetFtr();

    void unsetHdr();

    void unsetSldNum();

    XmlBoolean xgetDt();

    XmlBoolean xgetFtr();

    XmlBoolean xgetHdr();

    XmlBoolean xgetSldNum();

    void xsetDt(XmlBoolean xmlBoolean);

    void xsetFtr(XmlBoolean xmlBoolean);

    void xsetHdr(XmlBoolean xmlBoolean);

    void xsetSldNum(XmlBoolean xmlBoolean);
}
