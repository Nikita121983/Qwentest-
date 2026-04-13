package org.etsi.uri.x01903.v13;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CRLRefsType extends XmlObject {
    public static final DocumentFactory<CRLRefsType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "crlrefstype2a59type");
    public static final SchemaType type = Factory.getType();

    CRLRefType addNewCRLRef();

    CRLRefType getCRLRefArray(int i);

    CRLRefType[] getCRLRefArray();

    List<CRLRefType> getCRLRefList();

    CRLRefType insertNewCRLRef(int i);

    void removeCRLRef(int i);

    void setCRLRefArray(int i, CRLRefType cRLRefType);

    void setCRLRefArray(CRLRefType[] cRLRefTypeArr);

    int sizeOfCRLRefArray();
}
