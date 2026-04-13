package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTFill extends XmlObject {
    public static final DocumentFactory<CTFill> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfill550ctype");
    public static final SchemaType type = Factory.getType();

    CTGradientFill addNewGradientFill();

    CTPatternFill addNewPatternFill();

    CTGradientFill getGradientFill();

    CTPatternFill getPatternFill();

    boolean isSetGradientFill();

    boolean isSetPatternFill();

    void setGradientFill(CTGradientFill cTGradientFill);

    void setPatternFill(CTPatternFill cTPatternFill);

    void unsetGradientFill();

    void unsetPatternFill();
}
