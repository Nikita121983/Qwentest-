package org.apache.poi.xwpf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;

/* loaded from: classes10.dex */
public interface IBodyElement {
    IBody getBody();

    BodyElementType getElementType();

    POIXMLDocumentPart getPart();

    BodyType getPartType();
}
