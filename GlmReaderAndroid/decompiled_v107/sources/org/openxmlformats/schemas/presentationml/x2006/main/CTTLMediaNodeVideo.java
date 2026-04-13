package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTTLMediaNodeVideo extends XmlObject {
    public static final DocumentFactory<CTTLMediaNodeVideo> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttlmedianodevideoe3f8type");
    public static final SchemaType type = Factory.getType();

    CTTLCommonMediaNodeData addNewCMediaNode();

    CTTLCommonMediaNodeData getCMediaNode();

    boolean getFullScrn();

    boolean isSetFullScrn();

    void setCMediaNode(CTTLCommonMediaNodeData cTTLCommonMediaNodeData);

    void setFullScrn(boolean z);

    void unsetFullScrn();

    XmlBoolean xgetFullScrn();

    void xsetFullScrn(XmlBoolean xmlBoolean);
}
