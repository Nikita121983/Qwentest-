package org.apache.poi.openxml4j.opc;

import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;

/* loaded from: classes10.dex */
public final class PackagePartName implements Comparable<PackagePartName> {
    private static final String RFC3986_PCHAR_AUTHORIZED_SUP = ":@";
    private static final String RFC3986_PCHAR_SUB_DELIMS = "!$&'()*+,;=";
    private static final String RFC3986_PCHAR_UNRESERVED_SUP = "-._~";
    private final boolean isRelationship;
    private final URI partNameURI;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PackagePartName(URI uri, boolean checkConformance) throws InvalidFormatException {
        if (checkConformance) {
            throwExceptionIfInvalidPartUri(uri);
        } else if (!PackagingURIHelper.PACKAGE_ROOT_URI.equals(uri)) {
            throw new OpenXML4JRuntimeException("OCP conformance must be check for ALL part name except special cases : ['/']");
        }
        this.partNameURI = uri;
        this.isRelationship = isRelationshipPartURI(this.partNameURI);
    }

    PackagePartName(String partName, boolean checkConformance) throws InvalidFormatException {
        try {
            URI partURI = new URI(partName);
            if (checkConformance) {
                throwExceptionIfInvalidPartUri(partURI);
            } else if (!PackagingURIHelper.PACKAGE_ROOT_URI.equals(partURI)) {
                throw new OpenXML4JRuntimeException("OCP conformance must be check for ALL part name except special cases : ['/']");
            }
            this.partNameURI = partURI;
            this.isRelationship = isRelationshipPartURI(this.partNameURI);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("partName argument is not a valid OPC part name !");
        }
    }

    private boolean isRelationshipPartURI(URI partUri) {
        if (partUri == null) {
            throw new IllegalArgumentException("partUri");
        }
        String uriPath = partUri.getPath();
        return uriPath != null && uriPath.matches("^.*/_rels/.*\\.rels$");
    }

    public boolean isRelationshipPartURI() {
        return this.isRelationship;
    }

    private static void throwExceptionIfInvalidPartUri(URI partUri) throws InvalidFormatException {
        if (partUri == null) {
            throw new IllegalArgumentException("partUri");
        }
        throwExceptionIfEmptyURI(partUri);
        throwExceptionIfAbsoluteUri(partUri);
        throwExceptionIfPartNameNotStartsWithForwardSlashChar(partUri);
        throwExceptionIfPartNameEndsWithForwardSlashChar(partUri);
        throwExceptionIfPartNameHaveInvalidSegments(partUri);
    }

    private static void throwExceptionIfEmptyURI(URI partURI) throws InvalidFormatException {
        if (partURI == null) {
            throw new IllegalArgumentException("partURI");
        }
        String uriPath = partURI.getPath();
        if (uriPath == null || uriPath.isEmpty() || (uriPath.length() == 1 && uriPath.charAt(0) == '/')) {
            throw new InvalidFormatException("A part name shall not be empty [M1.1]: " + partURI.getPath());
        }
    }

    private static void throwExceptionIfPartNameHaveInvalidSegments(URI partUri) throws InvalidFormatException {
        if (partUri == null) {
            throw new IllegalArgumentException("partUri");
        }
        String[] segments = partUri.toASCIIString().replaceFirst("^/", "").split(PackagingURIHelper.FORWARD_SLASH_STRING);
        if (segments.length < 1) {
            throw new InvalidFormatException("A part name shall not have empty segments [M1.3]: " + partUri.getPath());
        }
        for (String seg : segments) {
            if (seg == null || seg.isEmpty()) {
                throw new InvalidFormatException("A part name shall not have empty segments [M1.3]: " + partUri.getPath());
            }
            if (seg.endsWith(".")) {
                throw new InvalidFormatException("A segment shall not end with a dot ('.') character [M1.9]: " + partUri.getPath());
            }
            if (seg.replaceAll("\\\\.", "").isEmpty()) {
                throw new InvalidFormatException("A segment shall include at least one non-dot character. [M1.10]: " + partUri.getPath());
            }
            checkPCharCompliance(seg);
        }
    }

    private static void checkPCharCompliance(String segment) throws InvalidFormatException {
        int length = segment.length();
        int i = 0;
        while (i < length) {
            char c = segment.charAt(i);
            if (!isDigitOrLetter(c) && RFC3986_PCHAR_UNRESERVED_SUP.indexOf(c) <= -1 && RFC3986_PCHAR_AUTHORIZED_SUP.indexOf(c) <= -1 && RFC3986_PCHAR_SUB_DELIMS.indexOf(c) <= -1) {
                if (c != '%') {
                    throw new InvalidFormatException("A segment shall not hold any characters other than pchar characters. [M1.6]");
                }
                if (length - i < 2 || !isHexDigit(segment.charAt(i + 1)) || !isHexDigit(segment.charAt(i + 2))) {
                    throw new InvalidFormatException("The segment " + segment + " contain invalid encoded character !");
                }
                char decodedChar = (char) Integer.parseInt(segment.substring(i + 1, i + 3), 16);
                i += 2;
                if (decodedChar == '/' || decodedChar == '\\') {
                    throw new InvalidFormatException("A segment shall not contain percent-encoded forward slash ('/'), or backward slash ('\\') characters. [M1.7]");
                }
                if (isDigitOrLetter(decodedChar) || RFC3986_PCHAR_UNRESERVED_SUP.indexOf(decodedChar) > -1) {
                    throw new InvalidFormatException("A segment shall not contain percent-encoded unreserved characters. [M1.8]");
                }
            }
            i++;
        }
    }

    private static void throwExceptionIfPartNameNotStartsWithForwardSlashChar(URI partUri) throws InvalidFormatException {
        String uriPath = partUri.getPath();
        if (!uriPath.isEmpty() && uriPath.charAt(0) != '/') {
            throw new InvalidFormatException("A part name shall start with a forward slash ('/') character [M1.4]: " + partUri.getPath());
        }
    }

    private static void throwExceptionIfPartNameEndsWithForwardSlashChar(URI partUri) throws InvalidFormatException {
        String uriPath = partUri.getPath();
        if (!uriPath.isEmpty() && uriPath.charAt(uriPath.length() - 1) == '/') {
            throw new InvalidFormatException("A part name shall not have a forward slash as the last character [M1.5]: " + partUri.getPath());
        }
    }

    private static void throwExceptionIfAbsoluteUri(URI partUri) throws InvalidFormatException {
        if (partUri.isAbsolute()) {
            throw new InvalidFormatException("Absolute URI forbidden: " + partUri);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(PackagePartName other) {
        return compare(this, other);
    }

    public String getExtension() {
        int i;
        String fragment = this.partNameURI.getPath();
        if (!fragment.isEmpty() && (i = fragment.lastIndexOf(46)) > -1) {
            return fragment.substring(i + 1);
        }
        return "";
    }

    public String getName() {
        return getURI().toASCIIString();
    }

    public boolean equals(Object other) {
        return (other instanceof PackagePartName) && compare(getName(), ((PackagePartName) other).getName()) == 0;
    }

    public int hashCode() {
        return getName().toLowerCase(Locale.ROOT).hashCode();
    }

    public String toString() {
        return getName();
    }

    public URI getURI() {
        return this.partNameURI;
    }

    public static int compare(PackagePartName obj1, PackagePartName obj2) {
        String name;
        String str = null;
        if (obj1 == null) {
            name = null;
        } else {
            name = obj1.getName();
        }
        if (obj2 != null) {
            str = obj2.getName();
        }
        return compare(name, str);
    }

    public static int compare(String str1, String str2) {
        if (str1 == null) {
            return str2 == null ? 0 : -1;
        }
        if (str2 == null) {
            return 1;
        }
        if (str1.equalsIgnoreCase(str2)) {
            return 0;
        }
        String name1 = str1.toLowerCase(Locale.ROOT);
        String name2 = str2.toLowerCase(Locale.ROOT);
        int len1 = name1.length();
        int len2 = name2.length();
        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < len1 && idx2 < len2) {
            int idx12 = idx1 + 1;
            char c1 = name1.charAt(idx1);
            int idx22 = idx2 + 1;
            char c2 = name2.charAt(idx2);
            if (Character.isDigit(c1) && Character.isDigit(c2)) {
                int beg1 = idx12 - 1;
                while (idx12 < len1 && Character.isDigit(name1.charAt(idx12))) {
                    idx12++;
                }
                int beg2 = idx22 - 1;
                while (idx22 < len2 && Character.isDigit(name2.charAt(idx22))) {
                    idx22++;
                }
                BigInteger b1 = new BigInteger(name1.substring(beg1, idx12));
                BigInteger b2 = new BigInteger(name2.substring(beg2, idx22));
                int cmp = b1.compareTo(b2);
                if (cmp != 0) {
                    return cmp;
                }
                idx1 = idx12;
                idx2 = idx22;
            } else if (c1 == c2) {
                idx1 = idx12;
                idx2 = idx22;
            } else {
                return c1 - c2;
            }
        }
        int idx13 = len1 - len2;
        return idx13;
    }

    private static boolean isDigitOrLetter(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    private static boolean isHexDigit(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }
}
