package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CommentsDocument extends XmlObject {
    public static final DocumentFactory<CommentsDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "comments3da0doctype");
    public static final SchemaType type = Factory.getType();

    CTComments addNewComments();

    CTComments getComments();

    void setComments(CTComments cTComments);
}
