package org.apache.xmlbeans;

import java.math.BigDecimal;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlDecimal extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlDecimal> Factory = new XmlObjectFactory<>("_BI_decimal");
    public static final SchemaType type = Factory.getType();

    BigDecimal getBigDecimalValue();

    void setBigDecimalValue(BigDecimal bigDecimal);
}
