package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;

/* loaded from: classes12.dex */
public interface CTLevelText extends XmlObject {
    public static final DocumentFactory<CTLevelText> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctleveltext0621type");
    public static final SchemaType type = Factory.getType();

    Object getNull();

    String getVal();

    boolean isSetNull();

    boolean isSetVal();

    void setNull(Object obj);

    void setVal(String str);

    void unsetNull();

    void unsetVal();

    STOnOff xgetNull();

    STString xgetVal();

    void xsetNull(STOnOff sTOnOff);

    void xsetVal(STString sTString);
}
