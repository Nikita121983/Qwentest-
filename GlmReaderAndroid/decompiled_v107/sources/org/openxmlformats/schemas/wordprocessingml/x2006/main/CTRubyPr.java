package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTRubyPr extends XmlObject {
    public static final DocumentFactory<CTRubyPr> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctrubyprb2actype");
    public static final SchemaType type = Factory.getType();

    CTOnOff addNewDirty();

    CTHpsMeasure addNewHps();

    CTHpsMeasure addNewHpsBaseText();

    CTHpsMeasure addNewHpsRaise();

    CTLang addNewLid();

    CTRubyAlign addNewRubyAlign();

    CTOnOff getDirty();

    CTHpsMeasure getHps();

    CTHpsMeasure getHpsBaseText();

    CTHpsMeasure getHpsRaise();

    CTLang getLid();

    CTRubyAlign getRubyAlign();

    boolean isSetDirty();

    void setDirty(CTOnOff cTOnOff);

    void setHps(CTHpsMeasure cTHpsMeasure);

    void setHpsBaseText(CTHpsMeasure cTHpsMeasure);

    void setHpsRaise(CTHpsMeasure cTHpsMeasure);

    void setLid(CTLang cTLang);

    void setRubyAlign(CTRubyAlign cTRubyAlign);

    void unsetDirty();
}
