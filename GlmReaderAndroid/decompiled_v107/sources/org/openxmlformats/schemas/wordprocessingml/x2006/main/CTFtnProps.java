package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTFtnProps extends XmlObject {
    public static final DocumentFactory<CTFtnProps> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctftnprops2df8type");
    public static final SchemaType type = Factory.getType();

    CTNumFmt addNewNumFmt();

    CTNumRestart addNewNumRestart();

    CTDecimalNumber addNewNumStart();

    CTFtnPos addNewPos();

    CTNumFmt getNumFmt();

    CTNumRestart getNumRestart();

    CTDecimalNumber getNumStart();

    CTFtnPos getPos();

    boolean isSetNumFmt();

    boolean isSetNumRestart();

    boolean isSetNumStart();

    boolean isSetPos();

    void setNumFmt(CTNumFmt cTNumFmt);

    void setNumRestart(CTNumRestart cTNumRestart);

    void setNumStart(CTDecimalNumber cTDecimalNumber);

    void setPos(CTFtnPos cTFtnPos);

    void unsetNumFmt();

    void unsetNumRestart();

    void unsetNumStart();

    void unsetPos();
}
