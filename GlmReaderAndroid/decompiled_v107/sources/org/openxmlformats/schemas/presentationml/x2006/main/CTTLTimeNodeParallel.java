package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTTLTimeNodeParallel extends XmlObject {
    public static final DocumentFactory<CTTLTimeNodeParallel> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttltimenodeparallelf917type");
    public static final SchemaType type = Factory.getType();

    CTTLCommonTimeNodeData addNewCTn();

    CTTLCommonTimeNodeData getCTn();

    void setCTn(CTTLCommonTimeNodeData cTTLCommonTimeNodeData);
}
