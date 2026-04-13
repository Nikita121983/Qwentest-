package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDisplacedByCustomXml;

/* loaded from: classes12.dex */
public interface CTMarkupRange extends CTMarkup {
    public static final DocumentFactory<CTMarkupRange> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctmarkuprangeba3dtype");
    public static final SchemaType type = Factory.getType();

    STDisplacedByCustomXml.Enum getDisplacedByCustomXml();

    boolean isSetDisplacedByCustomXml();

    void setDisplacedByCustomXml(STDisplacedByCustomXml.Enum r1);

    void unsetDisplacedByCustomXml();

    STDisplacedByCustomXml xgetDisplacedByCustomXml();

    void xsetDisplacedByCustomXml(STDisplacedByCustomXml sTDisplacedByCustomXml);
}
