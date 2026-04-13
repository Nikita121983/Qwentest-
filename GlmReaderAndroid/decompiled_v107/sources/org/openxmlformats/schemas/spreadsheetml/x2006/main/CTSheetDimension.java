package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTSheetDimension extends XmlObject {
    public static final DocumentFactory<CTSheetDimension> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsheetdimensiond310type");
    public static final SchemaType type = Factory.getType();

    String getRef();

    void setRef(String str);

    STRef xgetRef();

    void xsetRef(STRef sTRef);
}
