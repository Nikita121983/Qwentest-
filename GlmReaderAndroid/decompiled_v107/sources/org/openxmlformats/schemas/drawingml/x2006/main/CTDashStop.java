package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTDashStop extends XmlObject {
    public static final DocumentFactory<CTDashStop> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdashstopdc4ftype");
    public static final SchemaType type = Factory.getType();

    Object getD();

    Object getSp();

    void setD(Object obj);

    void setSp(Object obj);

    STPositivePercentage xgetD();

    STPositivePercentage xgetSp();

    void xsetD(STPositivePercentage sTPositivePercentage);

    void xsetSp(STPositivePercentage sTPositivePercentage);
}
