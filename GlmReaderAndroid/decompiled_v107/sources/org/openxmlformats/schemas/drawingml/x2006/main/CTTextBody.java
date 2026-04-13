package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTTextBody extends XmlObject {
    public static final DocumentFactory<CTTextBody> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextbodya3catype");
    public static final SchemaType type = Factory.getType();

    CTTextBodyProperties addNewBodyPr();

    CTTextListStyle addNewLstStyle();

    CTTextParagraph addNewP();

    CTTextBodyProperties getBodyPr();

    CTTextListStyle getLstStyle();

    CTTextParagraph getPArray(int i);

    CTTextParagraph[] getPArray();

    List<CTTextParagraph> getPList();

    CTTextParagraph insertNewP(int i);

    boolean isSetLstStyle();

    void removeP(int i);

    void setBodyPr(CTTextBodyProperties cTTextBodyProperties);

    void setLstStyle(CTTextListStyle cTTextListStyle);

    void setPArray(int i, CTTextParagraph cTTextParagraph);

    void setPArray(CTTextParagraph[] cTTextParagraphArr);

    int sizeOfPArray();

    void unsetLstStyle();
}
