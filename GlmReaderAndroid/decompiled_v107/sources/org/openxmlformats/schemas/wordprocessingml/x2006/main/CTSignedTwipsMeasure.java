package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTSignedTwipsMeasure extends XmlObject {
    public static final DocumentFactory<CTSignedTwipsMeasure> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsignedtwipsmeasure1037type");
    public static final SchemaType type = Factory.getType();

    Object getVal();

    void setVal(Object obj);

    STSignedTwipsMeasure xgetVal();

    void xsetVal(STSignedTwipsMeasure sTSignedTwipsMeasure);
}
