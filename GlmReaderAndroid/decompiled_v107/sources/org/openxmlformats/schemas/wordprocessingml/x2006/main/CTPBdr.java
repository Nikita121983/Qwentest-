package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTPBdr extends XmlObject {
    public static final DocumentFactory<CTPBdr> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpbdre388type");
    public static final SchemaType type = Factory.getType();

    CTBorder addNewBar();

    CTBorder addNewBetween();

    CTBorder addNewBottom();

    CTBorder addNewLeft();

    CTBorder addNewRight();

    CTBorder addNewTop();

    CTBorder getBar();

    CTBorder getBetween();

    CTBorder getBottom();

    CTBorder getLeft();

    CTBorder getRight();

    CTBorder getTop();

    boolean isSetBar();

    boolean isSetBetween();

    boolean isSetBottom();

    boolean isSetLeft();

    boolean isSetRight();

    boolean isSetTop();

    void setBar(CTBorder cTBorder);

    void setBetween(CTBorder cTBorder);

    void setBottom(CTBorder cTBorder);

    void setLeft(CTBorder cTBorder);

    void setRight(CTBorder cTBorder);

    void setTop(CTBorder cTBorder);

    void unsetBar();

    void unsetBetween();

    void unsetBottom();

    void unsetLeft();

    void unsetRight();

    void unsetTop();
}
