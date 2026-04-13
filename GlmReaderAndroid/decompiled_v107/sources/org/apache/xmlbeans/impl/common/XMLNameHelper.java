package org.apache.xmlbeans.impl.common;

import java.io.UnsupportedEncodingException;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.xmlbeans.xml.stream.XMLName;

/* loaded from: classes11.dex */
public class XMLNameHelper {
    private static final char[] hexdigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static QName getQName(XMLName xmlName) {
        if (xmlName == null) {
            return null;
        }
        return QNameHelper.forLNS(xmlName.getLocalName(), xmlName.getNamespaceUri());
    }

    public static XMLName forLNS(String localname, String uri) {
        if (uri == null) {
            uri = "";
        }
        return new XmlNameImpl(uri, localname);
    }

    public static XMLName forLN(String localname) {
        return new XmlNameImpl("", localname);
    }

    public static XMLName forPretty(String pretty, int offset) {
        int at = pretty.indexOf(64, offset);
        if (at < 0) {
            return new XmlNameImpl("", pretty.substring(offset));
        }
        return new XmlNameImpl(pretty.substring(at + 1), pretty.substring(offset, at));
    }

    public static String pretty(XMLName name) {
        if (name == null) {
            return "null";
        }
        if (name.getNamespaceUri() == null || name.getNamespaceUri().isEmpty()) {
            return name.getLocalName();
        }
        return name.getLocalName() + "@" + name.getNamespaceUri();
    }

    private static boolean isSafe(int c) {
        if (c >= 97 && c <= 122) {
            return true;
        }
        if (c < 65 || c > 90) {
            return c >= 48 && c <= 57;
        }
        return true;
    }

    public static String hexsafe(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (isSafe(ch)) {
                result.append(ch);
            } else {
                try {
                    byte[] utf8 = s.substring(i, i + 1).getBytes("UTF-8");
                    for (int j = 0; j < utf8.length; j++) {
                        result.append(NameUtil.USCORE);
                        result.append(hexdigits[(utf8[j] >> 4) & 15]);
                        result.append(hexdigits[utf8[j] & IntersectionPtg.sid]);
                    }
                } catch (UnsupportedEncodingException e) {
                    result.append("_BAD_UTF8_CHAR");
                }
            }
        }
        return result.toString();
    }

    public static String hexsafedir(XMLName name) {
        if (name.getNamespaceUri() == null || name.getNamespaceUri().isEmpty()) {
            return "_nons/" + hexsafe(name.getLocalName());
        }
        return hexsafe(name.getNamespaceUri()) + PackagingURIHelper.FORWARD_SLASH_STRING + hexsafe(name.getLocalName());
    }
}
