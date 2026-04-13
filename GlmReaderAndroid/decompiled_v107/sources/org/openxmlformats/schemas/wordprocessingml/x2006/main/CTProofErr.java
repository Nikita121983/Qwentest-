package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STProofErr;

/* loaded from: classes12.dex */
public interface CTProofErr extends XmlObject {
    public static final DocumentFactory<CTProofErr> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctprooferr1e07type");
    public static final SchemaType type = Factory.getType();

    STProofErr.Enum getType();

    void setType(STProofErr.Enum r1);

    STProofErr xgetType();

    void xsetType(STProofErr sTProofErr);
}
