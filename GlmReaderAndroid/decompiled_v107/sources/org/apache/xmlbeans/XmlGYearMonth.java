package org.apache.xmlbeans;

import java.util.Calendar;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlGYearMonth extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlGYearMonth> Factory = new XmlObjectFactory<>("_BI_gYearMonth");
    public static final SchemaType type = Factory.getType();

    Calendar getCalendarValue();

    GDate getGDateValue();

    void setCalendarValue(Calendar calendar);

    void setGDateValue(GDate gDate);
}
