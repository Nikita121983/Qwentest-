package org.apache.xmlbeans.impl.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.soap.SOAPConstants;
import org.apache.xmlbeans.xml.stream.XMLName;

/* loaded from: classes11.dex */
public class QNameHelper {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int MAX_NAME_LENGTH = 64;
    public static final String URI_SHA1_PREFIX = "URI_SHA_1_";
    private static final Map<String, String> WELL_KNOWN_PREFIXES = buildWKP();
    private static final char[] hexdigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static XMLName getXMLName(QName qname) {
        if (qname == null) {
            return null;
        }
        return XMLNameHelper.forLNS(qname.getLocalPart(), qname.getNamespaceURI());
    }

    public static QName forLNS(String localname, String uri) {
        if (uri == null) {
            uri = "";
        }
        return new QName(uri, localname);
    }

    public static QName forLN(String localname) {
        return new QName("", localname);
    }

    public static QName forPretty(String pretty, int offset) {
        int at = pretty.indexOf(64, offset);
        if (at < 0) {
            return new QName("", pretty.substring(offset));
        }
        return new QName(pretty.substring(at + 1), pretty.substring(offset, at));
    }

    public static String pretty(QName name) {
        if (name == null) {
            return "null";
        }
        if (name.getNamespaceURI() == null || name.getNamespaceURI().isEmpty()) {
            return name.getLocalPart();
        }
        return name.getLocalPart() + "@" + name.getNamespaceURI();
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
        byte[] inputBytes;
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
        int i2 = result.length();
        if (i2 <= 64) {
            return result.toString();
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            try {
                inputBytes = s.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e2) {
                byte[] inputBytes2 = new byte[0];
                inputBytes = inputBytes2;
            }
            byte[] digest = md.digest(inputBytes);
            if (digest.length != 20) {
                throw new AssertionError();
            }
            StringBuilder result2 = new StringBuilder(URI_SHA1_PREFIX);
            for (int j2 = 0; j2 < digest.length; j2++) {
                result2.append(hexdigits[(digest[j2] >> 4) & 15]);
                result2.append(hexdigits[digest[j2] & IntersectionPtg.sid]);
            }
            return result2.toString();
        } catch (NoSuchAlgorithmException e3) {
            throw new IllegalStateException("Using in a JDK without an SHA implementation");
        }
    }

    public static String hexsafedir(QName name) {
        if (name.getNamespaceURI() == null || name.getNamespaceURI().isEmpty()) {
            return "_nons/" + hexsafe(name.getLocalPart());
        }
        return hexsafe(name.getNamespaceURI()) + PackagingURIHelper.FORWARD_SLASH_STRING + hexsafe(name.getLocalPart());
    }

    private static Map<String, String> buildWKP() {
        Map<String, String> result = new HashMap<>();
        result.put("http://www.w3.org/XML/1998/namespace", "xml");
        result.put("http://www.w3.org/2001/XMLSchema", "xs");
        result.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
        result.put("http://schemas.xmlsoap.org/wsdl/", "wsdl");
        result.put("http://schemas.xmlsoap.org/soap/encoding/", "soapenc");
        result.put(SOAPConstants.URI_NS_SOAP_ENVELOPE, "soapenv");
        return Collections.unmodifiableMap(result);
    }

    public static String readable(SchemaType sType) {
        return readable(sType, WELL_KNOWN_PREFIXES);
    }

    public static String readable(SchemaType sType, Map<String, String> nsPrefix) {
        if (sType.getName() != null) {
            return readable(sType.getName(), nsPrefix);
        }
        if (sType.isAttributeType()) {
            return "attribute type " + readable(sType.getAttributeTypeAttributeName(), nsPrefix);
        }
        if (sType.isDocumentType()) {
            return "document type " + readable(sType.getDocumentElementName(), nsPrefix);
        }
        if (sType.isNoType() || sType.getOuterType() == null) {
            return "invalid type";
        }
        SchemaType outerType = sType.getOuterType();
        SchemaField container = sType.getContainerField();
        if (outerType.isAttributeType()) {
            return "type of attribute " + readable(container.getName(), nsPrefix);
        }
        if (outerType.isDocumentType()) {
            return "type of element " + readable(container.getName(), nsPrefix);
        }
        if (container != null) {
            if (container.isAttribute()) {
                return "type of " + container.getName().getLocalPart() + " attribute in " + readable(outerType, nsPrefix);
            }
            return "type of " + container.getName().getLocalPart() + " element in " + readable(outerType, nsPrefix);
        }
        if (outerType.getBaseType() == sType) {
            return "base type of " + readable(outerType, nsPrefix);
        }
        if (outerType.getSimpleVariety() == 3) {
            return "item type of " + readable(outerType, nsPrefix);
        }
        if (outerType.getSimpleVariety() == 2) {
            return "member type " + sType.getAnonymousUnionMemberOrdinal() + " of " + readable(outerType, nsPrefix);
        }
        return "inner type in " + readable(outerType, nsPrefix);
    }

    public static String readable(QName name) {
        return readable(name, WELL_KNOWN_PREFIXES);
    }

    public static String readable(QName name, Map<String, String> prefixes) {
        if (name.getNamespaceURI().isEmpty()) {
            return name.getLocalPart();
        }
        String prefix = prefixes.get(name.getNamespaceURI());
        if (prefix != null) {
            return prefix + ":" + name.getLocalPart();
        }
        return name.getLocalPart() + " in namespace " + name.getNamespaceURI();
    }

    public static String suggestPrefix(String namespace) {
        String result = WELL_KNOWN_PREFIXES.get(namespace);
        if (result != null) {
            return result;
        }
        int len = namespace.length();
        int i = namespace.lastIndexOf(47);
        if (i > 0 && i == namespace.length() - 1) {
            len = i;
            i = namespace.lastIndexOf(47, i - 1);
        }
        int i2 = i + 1;
        if (namespace.startsWith("www.", i2)) {
            i2 += 4;
        }
        while (i2 < len && !XMLChar.isNCNameStart(namespace.charAt(i2))) {
            i2++;
        }
        for (int end = i2 + 1; end < len; end++) {
            if (!XMLChar.isNCName(namespace.charAt(end)) || !Character.isLetterOrDigit(namespace.charAt(end))) {
                len = end;
                break;
            }
        }
        int end2 = namespace.length();
        if (end2 >= i2 + 3 && startsWithXml(namespace, i2)) {
            return namespace.length() >= i2 + 4 ? "x" + Character.toLowerCase(namespace.charAt(i2 + 3)) : "ns";
        }
        if (len - i2 > 4) {
            if (isVowel(namespace.charAt(i2 + 2)) && !isVowel(namespace.charAt(i2 + 3))) {
                len = i2 + 4;
            } else {
                len = i2 + 3;
            }
        }
        return len - i2 == 0 ? "ns" : namespace.substring(i2, len).toLowerCase(Locale.ROOT);
    }

    private static boolean startsWithXml(String s, int i) {
        if (s.length() < i + 3) {
            return false;
        }
        if (s.charAt(i) != 'X' && s.charAt(i) != 'x') {
            return false;
        }
        if (s.charAt(i + 1) == 'M' || s.charAt(i + 1) == 'm') {
            return s.charAt(i + 2) == 'L' || s.charAt(i + 2) == 'l';
        }
        return false;
    }

    private static boolean isVowel(char ch) {
        switch (ch) {
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;
        }
    }

    public static String namespace(SchemaType sType) {
        while (sType != null) {
            if (sType.getName() != null) {
                return sType.getName().getNamespaceURI();
            }
            if (sType.getContainerField() != null && !sType.getContainerField().getName().getNamespaceURI().isEmpty()) {
                return sType.getContainerField().getName().getNamespaceURI();
            }
            sType = sType.getOuterType();
        }
        return "";
    }

    public static String getLocalPart(String qname) {
        int index = qname.indexOf(58);
        return index < 0 ? qname : qname.substring(index + 1);
    }

    public static String getPrefixPart(String qname) {
        int index = qname.indexOf(58);
        return index >= 0 ? qname.substring(0, index) : "";
    }
}
