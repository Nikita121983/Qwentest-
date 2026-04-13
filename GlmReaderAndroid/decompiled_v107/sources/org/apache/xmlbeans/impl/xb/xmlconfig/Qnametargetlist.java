package org.apache.xmlbeans.impl.xb.xmlconfig;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;
import org.apache.xmlbeans.metadata.system.sXMLCONFIG.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface Qnametargetlist extends XmlAnySimpleType {
    public static final SimpleTypeFactory<Qnametargetlist> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "qnametargetlist16actype");
    public static final SchemaType type = Factory.getType();

    List getListValue();

    void setListValue(List<?> list);

    List xgetListValue();
}
