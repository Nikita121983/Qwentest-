package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes12.dex */
public interface STCellSpans extends XmlAnySimpleType {
    public static final SimpleTypeFactory<STCellSpans> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stcellspans60f6type");
    public static final SchemaType type = Factory.getType();

    List getListValue();

    void setListValue(List<?> list);

    List xgetListValue();
}
