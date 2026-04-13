package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTHandoutMasterIdList extends XmlObject {
    public static final DocumentFactory<CTHandoutMasterIdList> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cthandoutmasteridlist5b95type");
    public static final SchemaType type = Factory.getType();

    CTHandoutMasterIdListEntry addNewHandoutMasterId();

    CTHandoutMasterIdListEntry getHandoutMasterId();

    boolean isSetHandoutMasterId();

    void setHandoutMasterId(CTHandoutMasterIdListEntry cTHandoutMasterIdListEntry);

    void unsetHandoutMasterId();
}
