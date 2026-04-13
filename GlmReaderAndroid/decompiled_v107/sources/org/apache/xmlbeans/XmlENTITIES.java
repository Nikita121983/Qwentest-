package org.apache.xmlbeans;

import java.util.List;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlENTITIES extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlENTITIES> Factory = new XmlObjectFactory<>("_BI_ENTITIES");
    public static final SchemaType type = Factory.getType();

    List<?> getListValue();

    void setListValue(List<?> list);

    List<? extends XmlAnySimpleType> xgetListValue();
}
