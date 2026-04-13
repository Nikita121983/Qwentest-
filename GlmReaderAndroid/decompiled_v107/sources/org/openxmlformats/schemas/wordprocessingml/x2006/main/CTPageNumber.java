package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;

/* loaded from: classes12.dex */
public interface CTPageNumber extends XmlObject {
    public static final DocumentFactory<CTPageNumber> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpagenumber7570type");
    public static final SchemaType type = Factory.getType();

    STChapterSep$Enum getChapSep();

    BigInteger getChapStyle();

    STNumberFormat.Enum getFmt();

    BigInteger getStart();

    boolean isSetChapSep();

    boolean isSetChapStyle();

    boolean isSetFmt();

    boolean isSetStart();

    void setChapSep(STChapterSep$Enum sTChapterSep$Enum);

    void setChapStyle(BigInteger bigInteger);

    void setFmt(STNumberFormat.Enum r1);

    void setStart(BigInteger bigInteger);

    void unsetChapSep();

    void unsetChapStyle();

    void unsetFmt();

    void unsetStart();

    STChapterSep xgetChapSep();

    STDecimalNumber xgetChapStyle();

    STNumberFormat xgetFmt();

    STDecimalNumber xgetStart();

    void xsetChapSep(STChapterSep sTChapterSep);

    void xsetChapStyle(STDecimalNumber sTDecimalNumber);

    void xsetFmt(STNumberFormat sTNumberFormat);

    void xsetStart(STDecimalNumber sTDecimalNumber);
}
