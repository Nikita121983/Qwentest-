package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTAnchorClientData extends XmlObject {
    public static final DocumentFactory<CTAnchorClientData> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctanchorclientdata02betype");
    public static final SchemaType type = Factory.getType();

    boolean getFLocksWithSheet();

    boolean getFPrintsWithSheet();

    boolean isSetFLocksWithSheet();

    boolean isSetFPrintsWithSheet();

    void setFLocksWithSheet(boolean z);

    void setFPrintsWithSheet(boolean z);

    void unsetFLocksWithSheet();

    void unsetFPrintsWithSheet();

    XmlBoolean xgetFLocksWithSheet();

    XmlBoolean xgetFPrintsWithSheet();

    void xsetFLocksWithSheet(XmlBoolean xmlBoolean);

    void xsetFPrintsWithSheet(XmlBoolean xmlBoolean);
}
