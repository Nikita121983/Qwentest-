package com.microsoft.schemas.office.visio.x2012.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes.dex */
public interface MastersType extends XmlObject {
    public static final DocumentFactory<MastersType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "masterstypeaebatype");
    public static final SchemaType type = Factory.getType();

    MasterType addNewMaster();

    MasterShortcutType addNewMasterShortcut();

    MasterType getMasterArray(int i);

    MasterType[] getMasterArray();

    List<MasterType> getMasterList();

    MasterShortcutType getMasterShortcutArray(int i);

    MasterShortcutType[] getMasterShortcutArray();

    List<MasterShortcutType> getMasterShortcutList();

    MasterType insertNewMaster(int i);

    MasterShortcutType insertNewMasterShortcut(int i);

    void removeMaster(int i);

    void removeMasterShortcut(int i);

    void setMasterArray(int i, MasterType masterType);

    void setMasterArray(MasterType[] masterTypeArr);

    void setMasterShortcutArray(int i, MasterShortcutType masterShortcutType);

    void setMasterShortcutArray(MasterShortcutType[] masterShortcutTypeArr);

    int sizeOfMasterArray();

    int sizeOfMasterShortcutArray();
}
