package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTLayout extends XmlObject {
    public static final DocumentFactory<CTLayout> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlayout3192type");
    public static final SchemaType type = Factory.getType();

    CTExtensionList addNewExtLst();

    CTManualLayout addNewManualLayout();

    CTExtensionList getExtLst();

    CTManualLayout getManualLayout();

    boolean isSetExtLst();

    boolean isSetManualLayout();

    void setExtLst(CTExtensionList cTExtensionList);

    void setManualLayout(CTManualLayout cTManualLayout);

    void unsetExtLst();

    void unsetManualLayout();
}
