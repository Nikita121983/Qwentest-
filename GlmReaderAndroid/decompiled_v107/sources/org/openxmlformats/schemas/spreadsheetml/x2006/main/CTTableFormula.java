package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTTableFormula extends STFormula {
    public static final DocumentFactory<CTTableFormula> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttableformulaf801type");
    public static final SchemaType type = Factory.getType();

    boolean getArray();

    boolean isSetArray();

    void setArray(boolean z);

    void unsetArray();

    XmlBoolean xgetArray();

    void xsetArray(XmlBoolean xmlBoolean);
}
