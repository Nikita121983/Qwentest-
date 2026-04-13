package org.apache.poi.ooxml.util;

import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.util.Removal;
import org.apache.poi.util.XMLHelper;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

@Removal(version = "6.0.0")
@Deprecated
/* loaded from: classes10.dex */
public final class SAXHelper {
    public static XMLReader newXMLReader() throws SAXException, ParserConfigurationException {
        return XMLHelper.newXMLReader();
    }
}
