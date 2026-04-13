package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFtnPos;

/* loaded from: classes12.dex */
public interface CTFtnPos extends XmlObject {
    public static final DocumentFactory<CTFtnPos> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctftnposd254type");
    public static final SchemaType type = Factory.getType();

    STFtnPos.Enum getVal();

    void setVal(STFtnPos.Enum r1);

    STFtnPos xgetVal();

    void xsetVal(STFtnPos sTFtnPos);
}
