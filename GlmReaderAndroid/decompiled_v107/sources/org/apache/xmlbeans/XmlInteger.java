package org.apache.xmlbeans;

import java.math.BigInteger;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlInteger extends XmlDecimal {
    public static final XmlObjectFactory<XmlInteger> Factory = new XmlObjectFactory<>("_BI_integer");
    public static final SchemaType type = Factory.getType();

    BigInteger getBigIntegerValue();

    void setBigIntegerValue(BigInteger bigInteger);
}
