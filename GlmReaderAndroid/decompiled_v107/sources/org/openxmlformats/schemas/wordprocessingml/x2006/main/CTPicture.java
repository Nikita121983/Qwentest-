package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTPicture extends XmlObject {
    public static final DocumentFactory<CTPicture> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpicture1054type");
    public static final SchemaType type = Factory.getType();

    CTControl addNewControl();

    CTRel addNewMovie();

    CTControl getControl();

    CTRel getMovie();

    boolean isSetControl();

    boolean isSetMovie();

    void setControl(CTControl cTControl);

    void setMovie(CTRel cTRel);

    void unsetControl();

    void unsetMovie();
}
