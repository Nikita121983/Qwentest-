package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTTblBorders extends XmlObject {
    public static final DocumentFactory<CTTblBorders> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttblborders459ftype");
    public static final SchemaType type = Factory.getType();

    CTBorder addNewBottom();

    CTBorder addNewEnd();

    CTBorder addNewInsideH();

    CTBorder addNewInsideV();

    CTBorder addNewLeft();

    CTBorder addNewRight();

    CTBorder addNewStart();

    CTBorder addNewTop();

    CTBorder getBottom();

    CTBorder getEnd();

    CTBorder getInsideH();

    CTBorder getInsideV();

    CTBorder getLeft();

    CTBorder getRight();

    CTBorder getStart();

    CTBorder getTop();

    boolean isSetBottom();

    boolean isSetEnd();

    boolean isSetInsideH();

    boolean isSetInsideV();

    boolean isSetLeft();

    boolean isSetRight();

    boolean isSetStart();

    boolean isSetTop();

    void setBottom(CTBorder cTBorder);

    void setEnd(CTBorder cTBorder);

    void setInsideH(CTBorder cTBorder);

    void setInsideV(CTBorder cTBorder);

    void setLeft(CTBorder cTBorder);

    void setRight(CTBorder cTBorder);

    void setStart(CTBorder cTBorder);

    void setTop(CTBorder cTBorder);

    void unsetBottom();

    void unsetEnd();

    void unsetInsideH();

    void unsetInsideV();

    void unsetLeft();

    void unsetRight();

    void unsetStart();

    void unsetTop();
}
