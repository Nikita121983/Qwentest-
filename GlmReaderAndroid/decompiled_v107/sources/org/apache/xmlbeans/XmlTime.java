package org.apache.xmlbeans;

import java.util.Calendar;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlTime extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlTime> Factory = new XmlObjectFactory<>("_BI_time");
    public static final SchemaType type = Factory.getType();

    Calendar getCalendarValue();

    GDate getGDateValue();

    void setCalendarValue(Calendar calendar);

    void setGDateValue(GDate gDate);
}
