package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTGraphicalObjectFrameLocking extends XmlObject {
    public static final DocumentFactory<CTGraphicalObjectFrameLocking> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgraphicalobjectframelocking42adtype");
    public static final SchemaType type = Factory.getType();

    CTOfficeArtExtensionList addNewExtLst();

    CTOfficeArtExtensionList getExtLst();

    boolean getNoChangeAspect();

    boolean getNoDrilldown();

    boolean getNoGrp();

    boolean getNoMove();

    boolean getNoResize();

    boolean getNoSelect();

    boolean isSetExtLst();

    boolean isSetNoChangeAspect();

    boolean isSetNoDrilldown();

    boolean isSetNoGrp();

    boolean isSetNoMove();

    boolean isSetNoResize();

    boolean isSetNoSelect();

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setNoChangeAspect(boolean z);

    void setNoDrilldown(boolean z);

    void setNoGrp(boolean z);

    void setNoMove(boolean z);

    void setNoResize(boolean z);

    void setNoSelect(boolean z);

    void unsetExtLst();

    void unsetNoChangeAspect();

    void unsetNoDrilldown();

    void unsetNoGrp();

    void unsetNoMove();

    void unsetNoResize();

    void unsetNoSelect();

    XmlBoolean xgetNoChangeAspect();

    XmlBoolean xgetNoDrilldown();

    XmlBoolean xgetNoGrp();

    XmlBoolean xgetNoMove();

    XmlBoolean xgetNoResize();

    XmlBoolean xgetNoSelect();

    void xsetNoChangeAspect(XmlBoolean xmlBoolean);

    void xsetNoDrilldown(XmlBoolean xmlBoolean);

    void xsetNoGrp(XmlBoolean xmlBoolean);

    void xsetNoMove(XmlBoolean xmlBoolean);

    void xsetNoResize(XmlBoolean xmlBoolean);

    void xsetNoSelect(XmlBoolean xmlBoolean);
}
