package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;

/* loaded from: classes12.dex */
public interface CTDrawing extends XmlObject {
    public static final DocumentFactory<CTDrawing> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdrawing8d34type");
    public static final SchemaType type = Factory.getType();

    CTAnchor addNewAnchor();

    CTInline addNewInline();

    CTAnchor getAnchorArray(int i);

    CTAnchor[] getAnchorArray();

    List<CTAnchor> getAnchorList();

    CTInline getInlineArray(int i);

    CTInline[] getInlineArray();

    List<CTInline> getInlineList();

    CTAnchor insertNewAnchor(int i);

    CTInline insertNewInline(int i);

    void removeAnchor(int i);

    void removeInline(int i);

    void setAnchorArray(int i, CTAnchor cTAnchor);

    void setAnchorArray(CTAnchor[] cTAnchorArr);

    void setInlineArray(int i, CTInline cTInline);

    void setInlineArray(CTInline[] cTInlineArr);

    int sizeOfAnchorArray();

    int sizeOfInlineArray();
}
