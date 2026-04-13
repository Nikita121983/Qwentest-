package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;

/* loaded from: classes12.dex */
public interface CTOnOff extends XmlObject {
    public static final DocumentFactory<CTOnOff> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctonoff04c2type");
    public static final SchemaType type = Factory.getType();

    Object getVal();

    boolean isSetVal();

    void setVal(Object obj);

    void unsetVal();

    STOnOff xgetVal();

    void xsetVal(STOnOff sTOnOff);
}
