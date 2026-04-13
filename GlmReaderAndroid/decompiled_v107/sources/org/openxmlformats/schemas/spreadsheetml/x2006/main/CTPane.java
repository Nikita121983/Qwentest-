package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPane;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPaneState;

/* loaded from: classes12.dex */
public interface CTPane extends XmlObject {
    public static final DocumentFactory<CTPane> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpaneaab1type");
    public static final SchemaType type = Factory.getType();

    STPane.Enum getActivePane();

    STPaneState.Enum getState();

    String getTopLeftCell();

    double getXSplit();

    double getYSplit();

    boolean isSetActivePane();

    boolean isSetState();

    boolean isSetTopLeftCell();

    boolean isSetXSplit();

    boolean isSetYSplit();

    void setActivePane(STPane.Enum r1);

    void setState(STPaneState.Enum r1);

    void setTopLeftCell(String str);

    void setXSplit(double d);

    void setYSplit(double d);

    void unsetActivePane();

    void unsetState();

    void unsetTopLeftCell();

    void unsetXSplit();

    void unsetYSplit();

    STPane xgetActivePane();

    STPaneState xgetState();

    STCellRef xgetTopLeftCell();

    XmlDouble xgetXSplit();

    XmlDouble xgetYSplit();

    void xsetActivePane(STPane sTPane);

    void xsetState(STPaneState sTPaneState);

    void xsetTopLeftCell(STCellRef sTCellRef);

    void xsetXSplit(XmlDouble xmlDouble);

    void xsetYSplit(XmlDouble xmlDouble);
}
