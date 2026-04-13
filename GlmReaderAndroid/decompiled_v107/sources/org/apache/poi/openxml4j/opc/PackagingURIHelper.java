package org.apache.poi.openxml4j.opc;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import kotlin.UByte;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

/* loaded from: classes10.dex */
public final class PackagingURIHelper {
    public static final PackagePartName CORE_PROPERTIES_PART_NAME;
    public static final URI CORE_PROPERTIES_URI;
    public static final char FORWARD_SLASH_CHAR = '/';
    public static final String FORWARD_SLASH_STRING = "/";
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) PackagingURIHelper.class);
    public static final String PACKAGE_CORE_PROPERTIES_NAME = "core.xml";
    public static final String PACKAGE_PROPERTIES_SEGMENT_NAME = "docProps";
    public static final PackagePartName PACKAGE_RELATIONSHIPS_ROOT_PART_NAME;
    public static final URI PACKAGE_RELATIONSHIPS_ROOT_URI;
    public static final PackagePartName PACKAGE_ROOT_PART_NAME;
    public static final URI PACKAGE_ROOT_URI;
    public static final String RELATIONSHIP_PART_EXTENSION_NAME = ".rels";
    public static final String RELATIONSHIP_PART_SEGMENT_NAME = "_rels";
    private static final char[] hexDigits;
    private static final Pattern missingAuthPattern;
    private static URI packageRootUri;

    static {
        URI uriPACKAGE_ROOT_URI = null;
        URI uriPACKAGE_RELATIONSHIPS_ROOT_URI = null;
        URI uriPACKAGE_PROPERTIES_URI = null;
        try {
            uriPACKAGE_ROOT_URI = new URI(FORWARD_SLASH_STRING);
            uriPACKAGE_RELATIONSHIPS_ROOT_URI = new URI("/_rels/.rels");
            packageRootUri = new URI(FORWARD_SLASH_STRING);
            uriPACKAGE_PROPERTIES_URI = new URI("/docProps/core.xml");
        } catch (URISyntaxException e) {
        }
        PACKAGE_ROOT_URI = uriPACKAGE_ROOT_URI;
        PACKAGE_RELATIONSHIPS_ROOT_URI = uriPACKAGE_RELATIONSHIPS_ROOT_URI;
        CORE_PROPERTIES_URI = uriPACKAGE_PROPERTIES_URI;
        PackagePartName tmpPACKAGE_ROOT_PART_NAME = null;
        PackagePartName tmpPACKAGE_RELATIONSHIPS_ROOT_PART_NAME = null;
        PackagePartName tmpCORE_PROPERTIES_URI = null;
        try {
            tmpPACKAGE_RELATIONSHIPS_ROOT_PART_NAME = createPartName(PACKAGE_RELATIONSHIPS_ROOT_URI);
            tmpCORE_PROPERTIES_URI = createPartName(CORE_PROPERTIES_URI);
            tmpPACKAGE_ROOT_PART_NAME = new PackagePartName(PACKAGE_ROOT_URI, false);
        } catch (InvalidFormatException e2) {
        }
        PACKAGE_RELATIONSHIPS_ROOT_PART_NAME = tmpPACKAGE_RELATIONSHIPS_ROOT_PART_NAME;
        CORE_PROPERTIES_PART_NAME = tmpCORE_PROPERTIES_URI;
        PACKAGE_ROOT_PART_NAME = tmpPACKAGE_ROOT_PART_NAME;
        missingAuthPattern = Pattern.compile("\\w+://");
        hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    public static URI getPackageRootUri() {
        return packageRootUri;
    }

    public static boolean isRelationshipPartURI(URI partUri) {
        if (partUri == null) {
            throw new IllegalArgumentException("partUri");
        }
        String path = partUri.getPath();
        return path != null && path.matches(".*_rels.*.rels$");
    }

    public static String getFilename(URI uri) {
        char ch1;
        if (uri != null) {
            String path = uri.getPath();
            int len = path.length();
            int num2 = len;
            do {
                num2--;
                if (num2 >= 0) {
                    ch1 = path.charAt(num2);
                } else {
                    return "";
                }
            } while (ch1 != '/');
            return path.substring(num2 + 1, len);
        }
        return "";
    }

    public static String getFilenameWithoutExtension(URI uri) {
        String filename = getFilename(uri);
        int dotIndex = filename.lastIndexOf(46);
        if (dotIndex == -1) {
            return filename;
        }
        return filename.substring(0, dotIndex);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0023, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.net.URI getPath(java.net.URI r6) {
        /*
            r0 = 0
            if (r6 == 0) goto L25
            java.lang.String r1 = r6.getPath()
            int r2 = r1.length()
        Lb:
            int r2 = r2 + (-1)
            if (r2 < 0) goto L25
            char r3 = r1.charAt(r2)
            r4 = 47
            if (r3 != r4) goto L24
            java.net.URI r4 = new java.net.URI     // Catch: java.net.URISyntaxException -> L22
            r5 = 0
            java.lang.String r5 = r1.substring(r5, r2)     // Catch: java.net.URISyntaxException -> L22
            r4.<init>(r5)     // Catch: java.net.URISyntaxException -> L22
            return r4
        L22:
            r4 = move-exception
            return r0
        L24:
            goto Lb
        L25:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.PackagingURIHelper.getPath(java.net.URI):java.net.URI");
    }

    public static URI combine(URI prefix, URI suffix) {
        try {
            URI retUri = new URI(combine(prefix.getPath(), suffix.getPath()));
            return retUri;
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Prefix and suffix can't be combine !");
        }
    }

    public static String combine(String prefix, String suffix) {
        if (!prefix.endsWith(FORWARD_SLASH_STRING) && !suffix.startsWith(FORWARD_SLASH_STRING)) {
            return prefix + '/' + suffix;
        }
        if (suffix.startsWith(FORWARD_SLASH_STRING) ^ prefix.endsWith(FORWARD_SLASH_STRING)) {
            return prefix + suffix;
        }
        return "";
    }

    public static URI relativizeURI(URI sourceURI, URI targetURI, boolean msCompatible) {
        StringBuilder retVal = new StringBuilder();
        String[] segmentsSource = sourceURI.getPath().split(FORWARD_SLASH_STRING, -1);
        String[] segmentsTarget = targetURI.getPath().split(FORWARD_SLASH_STRING, -1);
        if (segmentsSource.length == 0) {
            throw new IllegalArgumentException("Can't relativize an empty source URI !");
        }
        if (segmentsTarget.length == 0) {
            throw new IllegalArgumentException("Can't relativize an empty target URI !");
        }
        if (sourceURI.toString().equals(FORWARD_SLASH_STRING)) {
            String path = targetURI.getPath();
            if (msCompatible && !path.isEmpty() && path.charAt(0) == '/') {
                try {
                    return new URI(path.substring(1));
                } catch (Exception e) {
                    LOG.atWarn().withThrowable(e).log("Failed to relativize");
                    return null;
                }
            }
            return targetURI;
        }
        int segmentsTheSame = 0;
        for (int i = 0; i < segmentsSource.length && i < segmentsTarget.length && segmentsSource[i].equals(segmentsTarget[i]); i++) {
            segmentsTheSame++;
        }
        if ((segmentsTheSame == 0 || segmentsTheSame == 1) && segmentsSource[0].isEmpty() && segmentsTarget[0].isEmpty()) {
            for (int i2 = 0; i2 < segmentsSource.length - 2; i2++) {
                retVal.append("../");
            }
            for (int i3 = 0; i3 < segmentsTarget.length; i3++) {
                if (!segmentsTarget[i3].isEmpty()) {
                    retVal.append(segmentsTarget[i3]);
                    if (i3 != segmentsTarget.length - 1) {
                        retVal.append(FORWARD_SLASH_STRING);
                    }
                }
            }
            try {
                return new URI(retVal.toString());
            } catch (Exception e2) {
                LOG.atWarn().withThrowable(e2).log("Failed to relativize");
                return null;
            }
        }
        if (segmentsTheSame == segmentsSource.length && segmentsTheSame == segmentsTarget.length) {
            if (sourceURI.equals(targetURI)) {
                retVal.append(segmentsSource[segmentsSource.length - 1]);
            }
        } else {
            if (segmentsTheSame == 1) {
                retVal.append(FORWARD_SLASH_STRING);
            } else {
                for (int j = segmentsTheSame; j < segmentsSource.length - 1; j++) {
                    retVal.append("../");
                }
            }
            for (int j2 = segmentsTheSame; j2 < segmentsTarget.length; j2++) {
                if (retVal.length() > 0 && retVal.charAt(retVal.length() - 1) != '/') {
                    retVal.append(FORWARD_SLASH_STRING);
                }
                retVal.append(segmentsTarget[j2]);
            }
        }
        String fragment = targetURI.getRawFragment();
        if (fragment != null) {
            retVal.append("#").append(fragment);
        }
        try {
            return new URI(retVal.toString());
        } catch (Exception e3) {
            LOG.atWarn().withThrowable(e3).log("Failed to relativize");
            return null;
        }
    }

    public static URI relativizeURI(URI sourceURI, URI targetURI) {
        return relativizeURI(sourceURI, targetURI, false);
    }

    public static URI resolvePartUri(URI sourcePartUri, URI targetUri) {
        if (sourcePartUri == null || sourcePartUri.isAbsolute()) {
            throw new IllegalArgumentException("sourcePartUri invalid - " + sourcePartUri);
        }
        if (targetUri == null || targetUri.isAbsolute()) {
            throw new IllegalArgumentException("targetUri invalid - " + targetUri);
        }
        return sourcePartUri.resolve(targetUri);
    }

    public static URI getURIFromPath(String path) {
        try {
            URI retUri = toURI(path);
            return retUri;
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("path");
        }
    }

    public static URI getSourcePartUriFromRelationshipPartUri(URI relationshipPartUri) {
        if (relationshipPartUri == null) {
            throw new IllegalArgumentException("Must not be null");
        }
        if (!isRelationshipPartURI(relationshipPartUri)) {
            throw new IllegalArgumentException("Must be a relationship part");
        }
        if (relationshipPartUri.compareTo(PACKAGE_RELATIONSHIPS_ROOT_URI) == 0) {
            return PACKAGE_ROOT_URI;
        }
        String filename = relationshipPartUri.getPath();
        String filenameWithoutExtension = getFilenameWithoutExtension(relationshipPartUri);
        return getURIFromPath(combine(filename.substring(0, (filename.length() - filenameWithoutExtension.length()) - RELATIONSHIP_PART_EXTENSION_NAME.length()).substring(0, (r0.length() - RELATIONSHIP_PART_SEGMENT_NAME.length()) - 1), filenameWithoutExtension));
    }

    public static PackagePartName createPartName(URI partUri) throws InvalidFormatException {
        if (partUri == null) {
            throw new IllegalArgumentException("partName");
        }
        return new PackagePartName(partUri, true);
    }

    public static PackagePartName createPartName(String partName) throws InvalidFormatException {
        try {
            URI partNameURI = toURI(partName);
            return createPartName(partNameURI);
        } catch (URISyntaxException e) {
            throw new InvalidFormatException(e.getMessage());
        }
    }

    public static PackagePartName createPartName(String partName, PackagePart relativePart) throws InvalidFormatException {
        try {
            URI newPartNameURI = resolvePartUri(relativePart.getPartName().getURI(), new URI(partName));
            return createPartName(newPartNameURI);
        } catch (URISyntaxException e) {
            throw new InvalidFormatException(e.getMessage());
        }
    }

    public static PackagePartName createPartName(URI partName, PackagePart relativePart) throws InvalidFormatException {
        URI newPartNameURI = resolvePartUri(relativePart.getPartName().getURI(), partName);
        return createPartName(newPartNameURI);
    }

    public static boolean isValidPartName(URI partUri) {
        if (partUri == null) {
            throw new IllegalArgumentException("partUri");
        }
        try {
            createPartName(partUri);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String decodeURI(URI uri) {
        StringBuilder retVal = new StringBuilder(64);
        String uriStr = uri.toASCIIString();
        int length = uriStr.length();
        int i = 0;
        while (i < length) {
            char c = uriStr.charAt(i);
            if (c == '%') {
                if (length - i < 2) {
                    throw new IllegalArgumentException("The uri " + uriStr + " contain invalid encoded character !");
                }
                char decodedChar = (char) Integer.parseInt(uriStr.substring(i + 1, i + 3), 16);
                retVal.append(decodedChar);
                i += 2;
            } else {
                retVal.append(c);
            }
            i++;
        }
        return retVal.toString();
    }

    public static PackagePartName getRelationshipPartName(PackagePartName partName) {
        if (partName == null) {
            throw new IllegalArgumentException("partName");
        }
        if (PACKAGE_ROOT_URI.getPath().equals(partName.getURI().getPath())) {
            return PACKAGE_RELATIONSHIPS_ROOT_PART_NAME;
        }
        if (partName.isRelationshipPartURI()) {
            throw new InvalidOperationException("Can't be a relationship part");
        }
        String fullPath = partName.getURI().getPath();
        String filename = getFilename(partName.getURI());
        try {
            PackagePartName retPartName = createPartName(combine(combine(fullPath.substring(0, fullPath.length() - filename.length()), RELATIONSHIP_PART_SEGMENT_NAME), filename) + RELATIONSHIP_PART_EXTENSION_NAME);
            return retPartName;
        } catch (InvalidFormatException e) {
            return null;
        }
    }

    public static URI toURI(String value) throws URISyntaxException {
        if (value.contains("\\")) {
            value = value.replace(IOUtils.DIR_SEPARATOR_WINDOWS, '/');
        }
        int fragmentIdx = value.indexOf(35);
        if (fragmentIdx != -1) {
            String path = value.substring(0, fragmentIdx);
            String fragment = value.substring(fragmentIdx + 1);
            value = path + "#" + encode(fragment);
        }
        if (!value.isEmpty()) {
            StringBuilder b = new StringBuilder();
            int idx = value.length() - 1;
            while (idx >= 0) {
                char c = value.charAt(idx);
                if (!Character.isWhitespace(c) && c != 160) {
                    break;
                }
                b.append(c);
                idx--;
            }
            if (b.length() > 0) {
                value = value.substring(0, idx + 1) + encode(b.reverse().toString());
            }
        }
        if (missingAuthPattern.matcher(value).matches()) {
            value = value + FORWARD_SLASH_STRING;
        }
        return new URI(value);
    }

    public static String encode(String s) {
        int n = s.length();
        if (n == 0) {
            return s;
        }
        ByteBuffer bb = ByteBuffer.wrap(s.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        while (bb.hasRemaining()) {
            int b = bb.get() & UByte.MAX_VALUE;
            if (isUnsafe(b)) {
                sb.append('%');
                sb.append(hexDigits[(b >> 4) & 15]);
                sb.append(hexDigits[(b >> 0) & 15]);
            } else {
                sb.append((char) b);
            }
        }
        return sb.toString();
    }

    private static boolean isUnsafe(int ch) {
        return ch >= 128 || ch == 124 || Character.isWhitespace(ch);
    }
}
