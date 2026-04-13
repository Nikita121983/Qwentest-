package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabTlc;

/* loaded from: classes12.dex */
public interface CTTabStop extends XmlObject {
    public static final DocumentFactory<CTTabStop> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttabstop5ebbtype");
    public static final SchemaType type = Factory.getType();

    STTabTlc.Enum getLeader();

    Object getPos();

    STTabJc.Enum getVal();

    boolean isSetLeader();

    void setLeader(STTabTlc.Enum r1);

    void setPos(Object obj);

    void setVal(STTabJc.Enum r1);

    void unsetLeader();

    STTabTlc xgetLeader();

    STSignedTwipsMeasure xgetPos();

    STTabJc xgetVal();

    void xsetLeader(STTabTlc sTTabTlc);

    void xsetPos(STSignedTwipsMeasure sTSignedTwipsMeasure);

    void xsetVal(STTabJc sTTabJc);
}
