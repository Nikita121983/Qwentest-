package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLock;

/* loaded from: classes12.dex */
public interface CTLock extends XmlObject {
    public static final DocumentFactory<CTLock> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlock201dtype");
    public static final SchemaType type = Factory.getType();

    STLock.Enum getVal();

    boolean isSetVal();

    void setVal(STLock.Enum r1);

    void unsetVal();

    STLock xgetVal();

    void xsetVal(STLock sTLock);
}
