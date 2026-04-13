package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTNumPr extends XmlObject {
    public static final DocumentFactory<CTNumPr> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctnumpr16aatype");
    public static final SchemaType type = Factory.getType();

    CTDecimalNumber addNewIlvl();

    CTTrackChange addNewIns();

    CTDecimalNumber addNewNumId();

    CTTrackChangeNumbering addNewNumberingChange();

    CTDecimalNumber getIlvl();

    CTTrackChange getIns();

    CTDecimalNumber getNumId();

    CTTrackChangeNumbering getNumberingChange();

    boolean isSetIlvl();

    boolean isSetIns();

    boolean isSetNumId();

    boolean isSetNumberingChange();

    void setIlvl(CTDecimalNumber cTDecimalNumber);

    void setIns(CTTrackChange cTTrackChange);

    void setNumId(CTDecimalNumber cTDecimalNumber);

    void setNumberingChange(CTTrackChangeNumbering cTTrackChangeNumbering);

    void unsetIlvl();

    void unsetIns();

    void unsetNumId();

    void unsetNumberingChange();
}
