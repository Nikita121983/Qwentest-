package org.apache.xmlbeans;

import java.util.Calendar;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlGYear extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlGYear> Factory = new XmlObjectFactory<>("_BI_gYear");
    public static final SchemaType type = Factory.getType();

    Calendar getCalendarValue();

    GDate getGDateValue();

    int getIntValue();

    void setCalendarValue(Calendar calendar);

    void setGDateValue(GDate gDate);

    void setIntValue(int i);
}
