package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTPaperSource extends XmlObject {
    public static final DocumentFactory<CTPaperSource> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpapersource8aabtype");
    public static final SchemaType type = Factory.getType();

    BigInteger getFirst();

    BigInteger getOther();

    boolean isSetFirst();

    boolean isSetOther();

    void setFirst(BigInteger bigInteger);

    void setOther(BigInteger bigInteger);

    void unsetFirst();

    void unsetOther();

    STDecimalNumber xgetFirst();

    STDecimalNumber xgetOther();

    void xsetFirst(STDecimalNumber sTDecimalNumber);

    void xsetOther(STDecimalNumber sTDecimalNumber);
}
