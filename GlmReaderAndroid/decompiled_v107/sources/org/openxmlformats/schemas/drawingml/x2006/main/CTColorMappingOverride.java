package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTColorMappingOverride extends XmlObject {
    public static final DocumentFactory<CTColorMappingOverride> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcolormappingoverridea2b2type");
    public static final SchemaType type = Factory.getType();

    CTEmptyElement addNewMasterClrMapping();

    CTColorMapping addNewOverrideClrMapping();

    CTEmptyElement getMasterClrMapping();

    CTColorMapping getOverrideClrMapping();

    boolean isSetMasterClrMapping();

    boolean isSetOverrideClrMapping();

    void setMasterClrMapping(CTEmptyElement cTEmptyElement);

    void setOverrideClrMapping(CTColorMapping cTColorMapping);

    void unsetMasterClrMapping();

    void unsetOverrideClrMapping();
}
