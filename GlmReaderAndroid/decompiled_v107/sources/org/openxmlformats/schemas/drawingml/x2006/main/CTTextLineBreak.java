package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTTextLineBreak extends XmlObject {
    public static final DocumentFactory<CTTextLineBreak> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextlinebreak932ftype");
    public static final SchemaType type = Factory.getType();

    CTTextCharacterProperties addNewRPr();

    CTTextCharacterProperties getRPr();

    boolean isSetRPr();

    void setRPr(CTTextCharacterProperties cTTextCharacterProperties);

    void unsetRPr();
}
