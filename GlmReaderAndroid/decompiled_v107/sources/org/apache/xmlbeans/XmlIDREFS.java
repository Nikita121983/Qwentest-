package org.apache.xmlbeans;

import java.util.List;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlIDREFS extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlIDREFS> Factory = new XmlObjectFactory<>("_BI_IDREFS");
    public static final SchemaType type = Factory.getType();

    List<?> getListValue();

    void setListValue(List<?> list);

    List<? extends XmlAnySimpleType> xgetListValue();
}
