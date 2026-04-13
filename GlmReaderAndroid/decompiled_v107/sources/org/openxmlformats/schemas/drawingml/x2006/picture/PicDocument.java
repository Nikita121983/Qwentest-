package org.openxmlformats.schemas.drawingml.x2006.picture;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface PicDocument extends XmlObject {
    public static final DocumentFactory<PicDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "pic8010doctype");
    public static final SchemaType type = Factory.getType();

    CTPicture addNewPic();

    CTPicture getPic();

    void setPic(CTPicture cTPicture);
}
