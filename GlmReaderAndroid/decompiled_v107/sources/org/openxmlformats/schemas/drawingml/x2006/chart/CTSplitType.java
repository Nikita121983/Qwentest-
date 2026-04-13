package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STSplitType;

/* loaded from: classes11.dex */
public interface CTSplitType extends XmlObject {
    public static final DocumentFactory<CTSplitType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsplittypeac32type");
    public static final SchemaType type = Factory.getType();

    STSplitType.Enum getVal();

    boolean isSetVal();

    void setVal(STSplitType.Enum r1);

    void unsetVal();

    STSplitType xgetVal();

    void xsetVal(STSplitType sTSplitType);
}
