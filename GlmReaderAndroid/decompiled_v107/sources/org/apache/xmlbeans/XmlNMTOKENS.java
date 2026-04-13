package org.apache.xmlbeans;

import java.util.List;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlNMTOKENS extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlNMTOKENS> Factory = new XmlObjectFactory<>("_BI_NMTOKENS");
    public static final SchemaType type = Factory.getType();

    List<?> getListValue();

    void setListValue(List<?> list);

    List<? extends XmlAnySimpleType> xgetListValue();
}
