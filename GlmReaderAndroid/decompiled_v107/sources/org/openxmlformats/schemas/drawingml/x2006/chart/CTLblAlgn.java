package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLblAlgn;

/* loaded from: classes11.dex */
public interface CTLblAlgn extends XmlObject {
    public static final DocumentFactory<CTLblAlgn> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlblalgn133etype");
    public static final SchemaType type = Factory.getType();

    STLblAlgn.Enum getVal();

    void setVal(STLblAlgn.Enum r1);

    STLblAlgn xgetVal();

    void xsetVal(STLblAlgn sTLblAlgn);
}
