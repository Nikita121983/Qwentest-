package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTMarkup extends XmlObject {
    public static final DocumentFactory<CTMarkup> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctmarkup2d80type");
    public static final SchemaType type = Factory.getType();

    BigInteger getId();

    void setId(BigInteger bigInteger);

    STDecimalNumber xgetId();

    void xsetId(STDecimalNumber sTDecimalNumber);
}
