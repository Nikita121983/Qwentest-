package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTTcMar extends XmlObject {
    public static final DocumentFactory<CTTcMar> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttcmar23c3type");
    public static final SchemaType type = Factory.getType();

    CTTblWidth addNewBottom();

    CTTblWidth addNewEnd();

    CTTblWidth addNewLeft();

    CTTblWidth addNewRight();

    CTTblWidth addNewStart();

    CTTblWidth addNewTop();

    CTTblWidth getBottom();

    CTTblWidth getEnd();

    CTTblWidth getLeft();

    CTTblWidth getRight();

    CTTblWidth getStart();

    CTTblWidth getTop();

    boolean isSetBottom();

    boolean isSetEnd();

    boolean isSetLeft();

    boolean isSetRight();

    boolean isSetStart();

    boolean isSetTop();

    void setBottom(CTTblWidth cTTblWidth);

    void setEnd(CTTblWidth cTTblWidth);

    void setLeft(CTTblWidth cTTblWidth);

    void setRight(CTTblWidth cTTblWidth);

    void setStart(CTTblWidth cTTblWidth);

    void setTop(CTTblWidth cTTblWidth);

    void unsetBottom();

    void unsetEnd();

    void unsetLeft();

    void unsetRight();

    void unsetStart();

    void unsetTop();
}
