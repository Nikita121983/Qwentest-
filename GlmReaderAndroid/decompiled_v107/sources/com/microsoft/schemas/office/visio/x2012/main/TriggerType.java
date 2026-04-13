package com.microsoft.schemas.office.visio.x2012.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes.dex */
public interface TriggerType extends XmlObject {
    public static final DocumentFactory<TriggerType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "triggertype2933type");
    public static final SchemaType type = Factory.getType();

    RefByType addNewRefBy();

    String getN();

    RefByType getRefByArray(int i);

    RefByType[] getRefByArray();

    List<RefByType> getRefByList();

    RefByType insertNewRefBy(int i);

    void removeRefBy(int i);

    void setN(String str);

    void setRefByArray(int i, RefByType refByType);

    void setRefByArray(RefByType[] refByTypeArr);

    int sizeOfRefByArray();

    XmlString xgetN();

    void xsetN(XmlString xmlString);
}
