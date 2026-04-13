package org.apache.poi.util;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

@Removal(version = "6.0.0")
@Deprecated
/* loaded from: classes10.dex */
public final class StaxHelper {
    private StaxHelper() {
    }

    public static XMLInputFactory newXMLInputFactory() {
        return XMLHelper.newXMLInputFactory();
    }

    public static XMLOutputFactory newXMLOutputFactory() {
        return XMLHelper.newXMLOutputFactory();
    }

    public static XMLEventFactory newXMLEventFactory() {
        return XMLHelper.newXMLEventFactory();
    }
}
