package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTNumRef extends XmlObject {
    public static final DocumentFactory<CTNumRef> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctnumref062ftype");
    public static final SchemaType type = Factory.getType();

    CTExtensionList addNewExtLst();

    CTNumData addNewNumCache();

    CTExtensionList getExtLst();

    String getF();

    CTNumData getNumCache();

    boolean isSetExtLst();

    boolean isSetNumCache();

    void setExtLst(CTExtensionList cTExtensionList);

    void setF(String str);

    void setNumCache(CTNumData cTNumData);

    void unsetExtLst();

    void unsetNumCache();

    XmlString xgetF();

    void xsetF(XmlString xmlString);
}
