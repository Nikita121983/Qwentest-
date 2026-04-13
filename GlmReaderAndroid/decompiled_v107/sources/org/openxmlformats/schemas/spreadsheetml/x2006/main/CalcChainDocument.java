package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CalcChainDocument extends XmlObject {
    public static final DocumentFactory<CalcChainDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "calcchainfc37doctype");
    public static final SchemaType type = Factory.getType();

    CTCalcChain addNewCalcChain();

    CTCalcChain getCalcChain();

    void setCalcChain(CTCalcChain cTCalcChain);
}
