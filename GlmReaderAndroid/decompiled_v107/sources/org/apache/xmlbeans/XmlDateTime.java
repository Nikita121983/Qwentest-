package org.apache.xmlbeans;

import java.util.Calendar;
import java.util.Date;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlDateTime extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlDateTime> Factory = new XmlObjectFactory<>("_BI_dateTime");
    public static final SchemaType type = Factory.getType();

    Calendar getCalendarValue();

    Date getDateValue();

    GDate getGDateValue();

    void setCalendarValue(Calendar calendar);

    void setDateValue(Date date);

    void setGDateValue(GDate gDate);
}
