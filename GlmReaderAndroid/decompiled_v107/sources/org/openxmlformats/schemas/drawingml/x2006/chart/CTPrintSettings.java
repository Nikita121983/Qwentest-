package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTPrintSettings extends XmlObject {
    public static final DocumentFactory<CTPrintSettings> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctprintsettings61b6type");
    public static final SchemaType type = Factory.getType();

    CTHeaderFooter addNewHeaderFooter();

    CTRelId addNewLegacyDrawingHF();

    CTPageMargins addNewPageMargins();

    CTPageSetup addNewPageSetup();

    CTHeaderFooter getHeaderFooter();

    CTRelId getLegacyDrawingHF();

    CTPageMargins getPageMargins();

    CTPageSetup getPageSetup();

    boolean isSetHeaderFooter();

    boolean isSetLegacyDrawingHF();

    boolean isSetPageMargins();

    boolean isSetPageSetup();

    void setHeaderFooter(CTHeaderFooter cTHeaderFooter);

    void setLegacyDrawingHF(CTRelId cTRelId);

    void setPageMargins(CTPageMargins cTPageMargins);

    void setPageSetup(CTPageSetup cTPageSetup);

    void unsetHeaderFooter();

    void unsetLegacyDrawingHF();

    void unsetPageMargins();

    void unsetPageSetup();
}
