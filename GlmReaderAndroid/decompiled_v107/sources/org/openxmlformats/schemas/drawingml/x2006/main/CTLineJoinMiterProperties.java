package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTLineJoinMiterProperties extends XmlObject {
    public static final DocumentFactory<CTLineJoinMiterProperties> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlinejoinmiterproperties02abtype");
    public static final SchemaType type = Factory.getType();

    Object getLim();

    boolean isSetLim();

    void setLim(Object obj);

    void unsetLim();

    STPositivePercentage xgetLim();

    void xsetLim(STPositivePercentage sTPositivePercentage);
}
