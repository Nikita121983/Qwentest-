package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STRestartNumber;

/* loaded from: classes12.dex */
public interface CTNumRestart extends XmlObject {
    public static final DocumentFactory<CTNumRestart> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctnumrestart261ftype");
    public static final SchemaType type = Factory.getType();

    STRestartNumber.Enum getVal();

    void setVal(STRestartNumber.Enum r1);

    STRestartNumber xgetVal();

    void xsetVal(STRestartNumber sTRestartNumber);
}
