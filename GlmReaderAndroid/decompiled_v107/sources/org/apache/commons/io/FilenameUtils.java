package org.apache.commons.io;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/* loaded from: classes9.dex */
public class FilenameUtils {
    private static final int BASE_16 = 16;
    private static final String EMPTY_STRING = "";
    public static final char EXTENSION_SEPARATOR = '.';
    private static final int IPV4_MAX_OCTET_VALUE = 255;
    private static final int IPV6_MAX_HEX_DIGITS_PER_GROUP = 4;
    private static final int IPV6_MAX_HEX_GROUPS = 8;
    private static final int MAX_UNSIGNED_SHORT = 65535;
    private static final int NOT_FOUND = -1;
    private static final char UNIX_NAME_SEPARATOR = '/';
    private static final char WINDOWS_NAME_SEPARATOR = '\\';
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final String EXTENSION_SEPARATOR_STR = Character.toString('.');
    private static final char SYSTEM_NAME_SEPARATOR = File.separatorChar;
    private static final char OTHER_SEPARATOR = flipSeparator(SYSTEM_NAME_SEPARATOR);
    private static final Pattern IPV4_PATTERN = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
    private static final Pattern REG_NAME_PART_PATTERN = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9-]*$");

    public static String concat(String basePath, String fullFileNameToAdd) {
        int prefix = getPrefixLength(fullFileNameToAdd);
        if (prefix < 0) {
            return null;
        }
        if (prefix > 0) {
            return normalize(fullFileNameToAdd);
        }
        if (basePath == null) {
            return null;
        }
        int len = basePath.length();
        if (len == 0) {
            return normalize(fullFileNameToAdd);
        }
        char ch = basePath.charAt(len - 1);
        if (isSeparator(ch)) {
            return normalize(basePath + fullFileNameToAdd);
        }
        return normalize(basePath + '/' + fullFileNameToAdd);
    }

    public static boolean directoryContains(String canonicalParent, String canonicalChild) {
        if (isEmpty(canonicalParent) || isEmpty(canonicalChild) || IOCase.SYSTEM.checkEquals(canonicalParent, canonicalChild)) {
            return false;
        }
        char separator = toSeparator(canonicalParent.charAt(0) == '/');
        String parentWithEndSeparator = canonicalParent.charAt(canonicalParent.length() - 1) == separator ? canonicalParent : canonicalParent + separator;
        return IOCase.SYSTEM.checkStartsWith(canonicalChild, parentWithEndSeparator);
    }

    private static String doGetFullPath(String str, boolean z) {
        int prefixLength;
        if (str == null || (prefixLength = getPrefixLength(str)) < 0) {
            return null;
        }
        if (prefixLength >= str.length()) {
            if (z) {
                return getPrefix(str);
            }
            return str;
        }
        int indexOfLastSeparator = indexOfLastSeparator(str);
        if (indexOfLastSeparator < 0) {
            return str.substring(0, prefixLength);
        }
        int i = indexOfLastSeparator + (z ? 1 : 0);
        if (i == 0) {
            i++;
        }
        return str.substring(0, i);
    }

    private static String doGetPath(String fileName, int separatorAdd) {
        int prefix;
        if (fileName == null || (prefix = getPrefixLength(fileName)) < 0) {
            return null;
        }
        int index = indexOfLastSeparator(fileName);
        int endIndex = index + separatorAdd;
        if (prefix >= fileName.length() || index < 0 || prefix >= endIndex) {
            return "";
        }
        return requireNonNullChars(fileName.substring(prefix, endIndex));
    }

    private static String doNormalize(String fileName, char separator, boolean keepSeparator) {
        if (fileName == null) {
            return null;
        }
        requireNonNullChars(fileName);
        int size = fileName.length();
        if (size == 0) {
            return fileName;
        }
        int prefix = getPrefixLength(fileName);
        if (prefix < 0) {
            return null;
        }
        char[] array = new char[size + 2];
        fileName.getChars(0, fileName.length(), array, 0);
        char otherSeparator = flipSeparator(separator);
        for (int i = 0; i < array.length; i++) {
            if (array[i] == otherSeparator) {
                array[i] = separator;
            }
        }
        int i2 = 1;
        if (array[size - 1] != separator) {
            array[size] = separator;
            i2 = 0;
            size++;
        }
        int i3 = prefix != 0 ? prefix : 1;
        while (i3 < size) {
            if (array[i3] == separator && array[i3 - 1] == separator) {
                System.arraycopy(array, i3, array, i3 - 1, size - i3);
                size--;
                i3--;
            }
            i3++;
        }
        int i4 = prefix + 1;
        while (i4 < size) {
            if (array[i4] == separator && array[i4 - 1] == '.' && (i4 == prefix + 1 || array[i4 - 2] == separator)) {
                if (i4 == size - 1) {
                    i2 = 1;
                }
                System.arraycopy(array, i4 + 1, array, i4 - 1, size - i4);
                size -= 2;
                i4--;
            }
            i4++;
        }
        int i5 = prefix + 2;
        while (i5 < size) {
            if (array[i5] == separator && array[i5 - 1] == '.' && array[i5 - 2] == '.' && (i5 == prefix + 2 || array[i5 - 3] == separator)) {
                if (i5 == prefix + 2) {
                    return null;
                }
                if (i5 == size - 1) {
                    i2 = 1;
                }
                int j = i5 - 4;
                while (true) {
                    if (j >= prefix) {
                        if (array[j] == separator) {
                            System.arraycopy(array, i5 + 1, array, j + 1, size - i5);
                            size -= i5 - j;
                            i5 = j + 1;
                            break;
                        }
                        j--;
                    } else {
                        System.arraycopy(array, i5 + 1, array, prefix, size - i5);
                        size -= (i5 + 1) - prefix;
                        i5 = prefix + 1;
                        break;
                    }
                }
            }
            i5++;
        }
        if (size <= 0) {
            return "";
        }
        if (size <= prefix) {
            return new String(array, 0, size);
        }
        if (i2 != 0 && keepSeparator) {
            return new String(array, 0, size);
        }
        return new String(array, 0, size - 1);
    }

    public static boolean equals(String fileName1, String fileName2) {
        return equals(fileName1, fileName2, false, IOCase.SENSITIVE);
    }

    public static boolean equals(String fileName1, String fileName2, boolean normalize, IOCase ioCase) {
        if (fileName1 == null || fileName2 == null) {
            return fileName1 == null && fileName2 == null;
        }
        if (normalize && ((fileName1 = normalize(fileName1)) == null || (fileName2 = normalize(fileName2)) == null)) {
            return false;
        }
        return IOCase.value(ioCase, IOCase.SENSITIVE).checkEquals(fileName1, fileName2);
    }

    public static boolean equalsNormalized(String fileName1, String fileName2) {
        return equals(fileName1, fileName2, true, IOCase.SENSITIVE);
    }

    public static boolean equalsNormalizedOnSystem(String fileName1, String fileName2) {
        return equals(fileName1, fileName2, true, IOCase.SYSTEM);
    }

    public static boolean equalsOnSystem(String fileName1, String fileName2) {
        return equals(fileName1, fileName2, false, IOCase.SYSTEM);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static char flipSeparator(char ch) {
        if (ch == '/') {
            return '\\';
        }
        if (ch == '\\') {
            return '/';
        }
        throw new IllegalArgumentException(String.valueOf(ch));
    }

    private static int getAdsCriticalOffset(String fileName) {
        int offset1 = fileName.lastIndexOf(SYSTEM_NAME_SEPARATOR);
        int offset2 = fileName.lastIndexOf(OTHER_SEPARATOR);
        if (offset1 == -1) {
            if (offset2 == -1) {
                return 0;
            }
            return offset2 + 1;
        }
        if (offset2 == -1) {
            return offset1 + 1;
        }
        return Math.max(offset1, offset2) + 1;
    }

    public static String getBaseName(String fileName) {
        return removeExtension(getName(fileName));
    }

    public static String getExtension(String fileName) throws IllegalArgumentException {
        if (fileName == null) {
            return null;
        }
        int index = indexOfExtension(fileName);
        if (index == -1) {
            return "";
        }
        return fileName.substring(index + 1);
    }

    public static String getFullPath(String fileName) {
        return doGetFullPath(fileName, true);
    }

    public static String getFullPathNoEndSeparator(String fileName) {
        return doGetFullPath(fileName, false);
    }

    public static String getName(String fileName) {
        if (fileName == null) {
            return null;
        }
        return requireNonNullChars(fileName).substring(indexOfLastSeparator(fileName) + 1);
    }

    public static String getPath(String fileName) {
        return doGetPath(fileName, 1);
    }

    public static String getPathNoEndSeparator(String fileName) {
        return doGetPath(fileName, 0);
    }

    public static String getPrefix(String fileName) {
        int len;
        if (fileName == null || (len = getPrefixLength(fileName)) < 0) {
            return null;
        }
        if (len > fileName.length()) {
            requireNonNullChars(fileName);
            return fileName + '/';
        }
        return requireNonNullChars(fileName.substring(0, len));
    }

    public static int getPrefixLength(String str) {
        if (str == null) {
            return -1;
        }
        int length = str.length();
        if (length == 0) {
            return 0;
        }
        char charAt = str.charAt(0);
        if (charAt == ':') {
            return -1;
        }
        if (length != 1) {
            if (charAt == '~') {
                int indexOf = str.indexOf(47, 1);
                int indexOf2 = str.indexOf(92, 1);
                if (indexOf == -1 && indexOf2 == -1) {
                    return length + 1;
                }
                int i = indexOf == -1 ? indexOf2 : indexOf;
                return Math.min(i, indexOf2 == -1 ? i : indexOf2) + 1;
            }
            char charAt2 = str.charAt(1);
            if (charAt2 == ':') {
                char upperCase = Character.toUpperCase(charAt);
                if (upperCase < 'A' || upperCase > 'Z') {
                    return upperCase == '/' ? 1 : -1;
                }
                if (length != 2 || FileSystem.getCurrent().supportsDriveLetter()) {
                    return (length == 2 || !isSeparator(str.charAt(2))) ? 2 : 3;
                }
                return 0;
            }
            if (!isSeparator(charAt) || !isSeparator(charAt2)) {
                return isSeparator(charAt) ? 1 : 0;
            }
            int indexOf3 = str.indexOf(47, 2);
            int indexOf4 = str.indexOf(92, 2);
            if ((indexOf3 == -1 && indexOf4 == -1) || indexOf3 == 2 || indexOf4 == 2) {
                return -1;
            }
            int i2 = indexOf3 == -1 ? indexOf4 : indexOf3;
            int min = Math.min(i2, indexOf4 == -1 ? i2 : indexOf4) + 1;
            if (isValidHostName(str.substring(2, min - 1))) {
                return min;
            }
            return -1;
        }
        if (charAt == '~') {
            return 2;
        }
        return isSeparator(charAt) ? 1 : 0;
    }

    public static int indexOfExtension(String fileName) throws IllegalArgumentException {
        if (fileName == null) {
            return -1;
        }
        if (isSystemWindows()) {
            int offset = fileName.indexOf(58, getAdsCriticalOffset(fileName));
            if (offset != -1) {
                throw new IllegalArgumentException("NTFS ADS separator (':') in file name is forbidden.");
            }
        }
        int extensionPos = fileName.lastIndexOf(46);
        int lastSeparator = indexOfLastSeparator(fileName);
        if (lastSeparator > extensionPos) {
            return -1;
        }
        return extensionPos;
    }

    public static int indexOfLastSeparator(String fileName) {
        if (fileName == null) {
            return -1;
        }
        int lastUnixPos = fileName.lastIndexOf(47);
        int lastWindowsPos = fileName.lastIndexOf(92);
        return Math.max(lastUnixPos, lastWindowsPos);
    }

    private static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isExtension(String fileName, Collection<String> extensions) {
        if (fileName == null) {
            return false;
        }
        requireNonNullChars(fileName);
        if (extensions == null || extensions.isEmpty()) {
            return indexOfExtension(fileName) == -1;
        }
        return extensions.contains(getExtension(fileName));
    }

    public static boolean isExtension(String fileName, String extension) {
        if (fileName == null) {
            return false;
        }
        requireNonNullChars(fileName);
        if (isEmpty(extension)) {
            return indexOfExtension(fileName) == -1;
        }
        return getExtension(fileName).equals(extension);
    }

    public static boolean isExtension(String fileName, String... extensions) {
        if (fileName == null) {
            return false;
        }
        requireNonNullChars(fileName);
        if (extensions == null || extensions.length == 0) {
            return indexOfExtension(fileName) == -1;
        }
        final String fileExt = getExtension(fileName);
        Stream of = Stream.of((Object[]) extensions);
        Objects.requireNonNull(fileExt);
        return of.anyMatch(new Predicate() { // from class: org.apache.commons.io.FilenameUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = fileExt.equals((String) obj);
                return equals;
            }
        });
    }

    private static boolean isIPv4Address(String name) {
        Matcher m = IPV4_PATTERN.matcher(name);
        if (!m.matches() || m.groupCount() != 4) {
            return false;
        }
        for (int i = 1; i <= 4; i++) {
            String ipSegment = m.group(i);
            int iIpSegment = Integer.parseInt(ipSegment);
            if (iIpSegment > 255) {
                return false;
            }
            if (ipSegment.length() > 1 && ipSegment.startsWith("0")) {
                return false;
            }
        }
        return true;
    }

    private static boolean isIPv6Address(String inet6Address) {
        boolean containsCompressedZeroes = inet6Address.contains("::");
        if (containsCompressedZeroes && inet6Address.indexOf("::") != inet6Address.lastIndexOf("::")) {
            return false;
        }
        if ((inet6Address.startsWith(":") && !inet6Address.startsWith("::")) || (inet6Address.endsWith(":") && !inet6Address.endsWith("::"))) {
            return false;
        }
        String[] octets = inet6Address.split(":");
        if (containsCompressedZeroes) {
            List<String> octetList = new ArrayList<>(Arrays.asList(octets));
            if (inet6Address.endsWith("::")) {
                octetList.add("");
            } else if (inet6Address.startsWith("::") && !octetList.isEmpty()) {
                octetList.remove(0);
            }
            octets = (String[]) octetList.toArray(EMPTY_STRING_ARRAY);
        }
        if (octets.length > 8) {
            return false;
        }
        int validOctets = 0;
        int emptyOctets = 0;
        for (int index = 0; index < octets.length; index++) {
            String octet = octets[index];
            if (octet.isEmpty()) {
                emptyOctets++;
                if (emptyOctets > 1) {
                    return false;
                }
            } else {
                emptyOctets = 0;
                if (index == octets.length - 1 && octet.contains(".")) {
                    if (!isIPv4Address(octet)) {
                        return false;
                    }
                    validOctets += 2;
                } else {
                    if (octet.length() > 4) {
                        return false;
                    }
                    try {
                        int octetInt = Integer.parseInt(octet, 16);
                        if (octetInt < 0 || octetInt > 65535) {
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
            }
            validOctets++;
        }
        if (validOctets <= 8) {
            return validOctets >= 8 || containsCompressedZeroes;
        }
        return false;
    }

    private static boolean isRFC3986HostName(String name) {
        String[] parts = name.split("\\.", -1);
        int i = 0;
        while (i < parts.length) {
            if (parts[i].isEmpty()) {
                return i == parts.length - 1;
            }
            if (!REG_NAME_PART_PATTERN.matcher(parts[i]).matches()) {
                return false;
            }
            i++;
        }
        return true;
    }

    private static boolean isSeparator(char ch) {
        return ch == '/' || ch == '\\';
    }

    static boolean isSystemWindows() {
        return SYSTEM_NAME_SEPARATOR == '\\';
    }

    private static boolean isValidHostName(String name) {
        return isIPv6Address(name) || isRFC3986HostName(name);
    }

    public static String normalize(String fileName) {
        return doNormalize(fileName, SYSTEM_NAME_SEPARATOR, true);
    }

    public static String normalize(String fileName, boolean unixSeparator) {
        return doNormalize(fileName, toSeparator(unixSeparator), true);
    }

    public static String normalizeNoEndSeparator(String fileName) {
        return doNormalize(fileName, SYSTEM_NAME_SEPARATOR, false);
    }

    public static String normalizeNoEndSeparator(String fileName, boolean unixSeparator) {
        return doNormalize(fileName, toSeparator(unixSeparator), false);
    }

    public static String removeExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        requireNonNullChars(fileName);
        int index = indexOfExtension(fileName);
        if (index == -1) {
            return fileName;
        }
        return fileName.substring(0, index);
    }

    private static String requireNonNullChars(String path) {
        if (path.indexOf(0) >= 0) {
            throw new IllegalArgumentException("Null character present in file/path name. There are no known legitimate use cases for such data, but several injection attacks may use it");
        }
        return path;
    }

    public static String separatorsToSystem(String path) {
        return FileSystem.getCurrent().normalizeSeparators(path);
    }

    public static String separatorsToUnix(String path) {
        return FileSystem.LINUX.normalizeSeparators(path);
    }

    public static String separatorsToWindows(String path) {
        return FileSystem.WINDOWS.normalizeSeparators(path);
    }

    static String[] splitOnTokens(String text) {
        if (text.indexOf(63) == -1 && text.indexOf(42) == -1) {
            return new String[]{text};
        }
        char[] array = text.toCharArray();
        ArrayList<String> list = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        char prevChar = 0;
        for (char ch : array) {
            if (ch == '?' || ch == '*') {
                if (buffer.length() != 0) {
                    list.add(buffer.toString());
                    buffer.setLength(0);
                }
                if (ch == '?') {
                    list.add("?");
                } else if (prevChar != '*') {
                    list.add("*");
                }
            } else {
                buffer.append(ch);
            }
            prevChar = ch;
        }
        if (buffer.length() != 0) {
            list.add(buffer.toString());
        }
        return (String[]) list.toArray(EMPTY_STRING_ARRAY);
    }

    private static char toSeparator(boolean unixSeparator) {
        return unixSeparator ? '/' : '\\';
    }

    public static boolean wildcardMatch(String fileName, String wildcardMatcher) {
        return wildcardMatch(fileName, wildcardMatcher, IOCase.SENSITIVE);
    }

    public static boolean wildcardMatch(String fileName, String wildcardMatcher, IOCase ioCase) {
        if (fileName == null && wildcardMatcher == null) {
            return true;
        }
        if (fileName == null || wildcardMatcher == null) {
            return false;
        }
        IOCase ioCase2 = IOCase.value(ioCase, IOCase.SENSITIVE);
        String[] wcs = splitOnTokens(wildcardMatcher);
        boolean anyChars = false;
        int textIdx = 0;
        int wcsIdx = 0;
        Deque<int[]> backtrack = new ArrayDeque<>(wcs.length);
        do {
            if (!backtrack.isEmpty()) {
                int[] array = backtrack.pop();
                wcsIdx = array[0];
                textIdx = array[1];
                anyChars = true;
            }
            while (wcsIdx < wcs.length) {
                if (wcs[wcsIdx].equals("?")) {
                    textIdx++;
                    if (textIdx > fileName.length()) {
                        break;
                    }
                    anyChars = false;
                    wcsIdx++;
                } else {
                    if (wcs[wcsIdx].equals("*")) {
                        anyChars = true;
                        if (wcsIdx == wcs.length - 1) {
                            textIdx = fileName.length();
                        }
                    } else if (anyChars) {
                        textIdx = ioCase2.checkIndexOf(fileName, textIdx, wcs[wcsIdx]);
                        if (textIdx == -1) {
                            break;
                        }
                        int repeat = ioCase2.checkIndexOf(fileName, textIdx + 1, wcs[wcsIdx]);
                        if (repeat >= 0) {
                            backtrack.push(new int[]{wcsIdx, repeat});
                        }
                        textIdx += wcs[wcsIdx].length();
                        anyChars = false;
                    } else {
                        if (!ioCase2.checkRegionMatches(fileName, textIdx, wcs[wcsIdx])) {
                            break;
                        }
                        textIdx += wcs[wcsIdx].length();
                        anyChars = false;
                    }
                    wcsIdx++;
                }
            }
            if (wcsIdx == wcs.length && textIdx == fileName.length()) {
                return true;
            }
        } while (!backtrack.isEmpty());
        return false;
    }

    public static boolean wildcardMatchOnSystem(String fileName, String wildcardMatcher) {
        return wildcardMatch(fileName, wildcardMatcher, IOCase.SYSTEM);
    }

    @Deprecated
    public FilenameUtils() {
    }
}
