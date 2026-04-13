package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJcTable;

/* loaded from: classes12.dex */
public interface CTJcTable extends XmlObject {
    public static final DocumentFactory<CTJcTable> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctjctablefa9dtype");
    public static final SchemaType type = Factory.getType();

    STJcTable.Enum getVal();

    void setVal(STJcTable.Enum r1);

    STJcTable xgetVal();

    void xsetVal(STJcTable sTJcTable);
}
