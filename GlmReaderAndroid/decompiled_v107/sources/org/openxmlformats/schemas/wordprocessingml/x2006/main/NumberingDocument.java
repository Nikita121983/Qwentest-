package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface NumberingDocument extends XmlObject {
    public static final DocumentFactory<NumberingDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "numbering1c4ddoctype");
    public static final SchemaType type = Factory.getType();

    CTNumbering addNewNumbering();

    CTNumbering getNumbering();

    void setNumbering(CTNumbering cTNumbering);
}
