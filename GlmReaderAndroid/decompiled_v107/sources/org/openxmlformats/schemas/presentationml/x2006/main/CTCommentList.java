package org.openxmlformats.schemas.presentationml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTCommentList extends XmlObject {
    public static final DocumentFactory<CTCommentList> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcommentlistf692type");
    public static final SchemaType type = Factory.getType();

    CTComment addNewCm();

    CTComment getCmArray(int i);

    CTComment[] getCmArray();

    List<CTComment> getCmList();

    CTComment insertNewCm(int i);

    void removeCm(int i);

    void setCmArray(int i, CTComment cTComment);

    void setCmArray(CTComment[] cTCommentArr);

    int sizeOfCmArray();
}
