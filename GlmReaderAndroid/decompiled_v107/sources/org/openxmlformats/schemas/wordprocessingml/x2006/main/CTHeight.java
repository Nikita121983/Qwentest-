package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHeightRule;

/* loaded from: classes12.dex */
public interface CTHeight extends XmlObject {
    public static final DocumentFactory<CTHeight> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctheighta2e1type");
    public static final SchemaType type = Factory.getType();

    STHeightRule.Enum getHRule();

    Object getVal();

    boolean isSetHRule();

    boolean isSetVal();

    void setHRule(STHeightRule.Enum r1);

    void setVal(Object obj);

    void unsetHRule();

    void unsetVal();

    STHeightRule xgetHRule();

    STTwipsMeasure xgetVal();

    void xsetHRule(STHeightRule sTHeightRule);

    void xsetVal(STTwipsMeasure sTTwipsMeasure);
}
