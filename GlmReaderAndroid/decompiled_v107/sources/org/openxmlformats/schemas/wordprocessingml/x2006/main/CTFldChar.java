package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;

/* loaded from: classes12.dex */
public interface CTFldChar extends XmlObject {
    public static final DocumentFactory<CTFldChar> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfldchare83etype");
    public static final SchemaType type = Factory.getType();

    CTFFData addNewFfData();

    CTText addNewFldData();

    CTTrackChangeNumbering addNewNumberingChange();

    Object getDirty();

    CTFFData getFfData();

    STFldCharType.Enum getFldCharType();

    CTText getFldData();

    Object getFldLock();

    CTTrackChangeNumbering getNumberingChange();

    boolean isSetDirty();

    boolean isSetFfData();

    boolean isSetFldData();

    boolean isSetFldLock();

    boolean isSetNumberingChange();

    void setDirty(Object obj);

    void setFfData(CTFFData cTFFData);

    void setFldCharType(STFldCharType.Enum r1);

    void setFldData(CTText cTText);

    void setFldLock(Object obj);

    void setNumberingChange(CTTrackChangeNumbering cTTrackChangeNumbering);

    void unsetDirty();

    void unsetFfData();

    void unsetFldData();

    void unsetFldLock();

    void unsetNumberingChange();

    STOnOff xgetDirty();

    STFldCharType xgetFldCharType();

    STOnOff xgetFldLock();

    void xsetDirty(STOnOff sTOnOff);

    void xsetFldCharType(STFldCharType sTFldCharType);

    void xsetFldLock(STOnOff sTOnOff);
}
