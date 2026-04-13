package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STEdGrp;

/* loaded from: classes12.dex */
public interface CTPermStart extends CTPerm {
    public static final DocumentFactory<CTPermStart> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpermstart0140type");
    public static final SchemaType type = Factory.getType();

    BigInteger getColFirst();

    BigInteger getColLast();

    String getEd();

    STEdGrp.Enum getEdGrp();

    boolean isSetColFirst();

    boolean isSetColLast();

    boolean isSetEd();

    boolean isSetEdGrp();

    void setColFirst(BigInteger bigInteger);

    void setColLast(BigInteger bigInteger);

    void setEd(String str);

    void setEdGrp(STEdGrp.Enum r1);

    void unsetColFirst();

    void unsetColLast();

    void unsetEd();

    void unsetEdGrp();

    STDecimalNumber xgetColFirst();

    STDecimalNumber xgetColLast();

    STString xgetEd();

    STEdGrp xgetEdGrp();

    void xsetColFirst(STDecimalNumber sTDecimalNumber);

    void xsetColLast(STDecimalNumber sTDecimalNumber);

    void xsetEd(STString sTString);

    void xsetEdGrp(STEdGrp sTEdGrp);
}
