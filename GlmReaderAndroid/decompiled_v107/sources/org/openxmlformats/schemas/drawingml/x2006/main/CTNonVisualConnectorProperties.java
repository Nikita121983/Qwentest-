package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTNonVisualConnectorProperties extends XmlObject {
    public static final DocumentFactory<CTNonVisualConnectorProperties> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctnonvisualconnectorproperties6f8etype");
    public static final SchemaType type = Factory.getType();

    CTConnectorLocking addNewCxnSpLocks();

    CTConnection addNewEndCxn();

    CTOfficeArtExtensionList addNewExtLst();

    CTConnection addNewStCxn();

    CTConnectorLocking getCxnSpLocks();

    CTConnection getEndCxn();

    CTOfficeArtExtensionList getExtLst();

    CTConnection getStCxn();

    boolean isSetCxnSpLocks();

    boolean isSetEndCxn();

    boolean isSetExtLst();

    boolean isSetStCxn();

    void setCxnSpLocks(CTConnectorLocking cTConnectorLocking);

    void setEndCxn(CTConnection cTConnection);

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setStCxn(CTConnection cTConnection);

    void unsetCxnSpLocks();

    void unsetEndCxn();

    void unsetExtLst();

    void unsetStCxn();
}
