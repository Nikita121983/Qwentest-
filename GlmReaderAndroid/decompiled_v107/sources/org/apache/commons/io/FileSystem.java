package org.apache.commons.io;

import androidx.core.view.PointerIconCompat;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.text.BreakIterator;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import org.apache.commons.lang3.SystemProperties;

/* loaded from: classes9.dex */
public enum FileSystem {
    GENERIC(4096, false, false, PointerIconCompat.TYPE_GRAB, 1048576, new int[]{0}, new String[0], false, false, '/', NameLengthStrategy.BYTES),
    LINUX(8192, true, true, 255, 4096, new int[]{0, 47}, new String[0], false, false, '/', NameLengthStrategy.BYTES),
    MAC_OSX(4096, true, true, 255, 1024, new int[]{0, 47, 58}, new String[0], false, false, '/', NameLengthStrategy.BYTES),
    WINDOWS(4096, false, true, 255, 32767, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 34, 42, 47, 58, 60, 62, 63, 92, 124}, new String[]{"AUX", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "COM²", "COM³", "COM¹", "CON", "CONIN$", "CONOUT$", "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9", "LPT²", "LPT³", "LPT¹", "NUL", "PRN"}, true, true, IOUtils.DIR_SEPARATOR_WINDOWS, NameLengthStrategy.UTF16_CODE_UNITS);

    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final int blockSize;
    private final boolean casePreserving;
    private final boolean caseSensitive;
    private final int[] illegalFileNameChars;
    private final int maxFileNameLength;
    private final int maxPathLength;
    private final NameLengthStrategy nameLengthStrategy;
    private final char nameSeparator;
    private final char nameSeparatorOther;
    private final String[] reservedFileNames;
    private final boolean reservedFileNamesExtensions;
    private final boolean supportsDriveLetter;
    private static final boolean IS_OS_LINUX = getOsMatchesName("Linux");
    private static final boolean IS_OS_MAC = getOsMatchesName("Mac");
    private static final String OS_NAME_WINDOWS_PREFIX = "Windows";
    private static final boolean IS_OS_WINDOWS = getOsMatchesName(OS_NAME_WINDOWS_PREFIX);
    private static final FileSystem CURRENT = current();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public enum NameLengthStrategy {
        BYTES { // from class: org.apache.commons.io.FileSystem.NameLengthStrategy.1
            @Override // org.apache.commons.io.FileSystem.NameLengthStrategy
            int getLength(CharSequence value, Charset charset) {
                CharsetEncoder enc = charset.newEncoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
                try {
                    return enc.encode(CharBuffer.wrap(value)).remaining();
                } catch (CharacterCodingException e) {
                    return Integer.MAX_VALUE;
                }
            }

            @Override // org.apache.commons.io.FileSystem.NameLengthStrategy
            CharSequence truncate(CharSequence value, int limit, Charset charset) {
                CharsetEncoder encoder = charset.newEncoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
                if (!encoder.canEncode(value)) {
                    throw new IllegalArgumentException("The value " + ((Object) value) + " cannot be encoded using " + charset.name());
                }
                if (value.length() <= Math.floor(limit / encoder.maxBytesPerChar())) {
                    return value;
                }
                CharSequence[] parts = FileSystem.splitExtension(value);
                int extensionLength = getLength(parts[1], charset);
                if (extensionLength > 0 && extensionLength >= limit) {
                    throw new IllegalArgumentException("The extension of " + ((Object) value) + " is too long to fit within " + limit + " bytes");
                }
                ByteBuffer byteBuffer = ByteBuffer.allocate(limit - extensionLength);
                CharBuffer charBuffer = CharBuffer.wrap(parts[0]);
                CoderResult cr = encoder.encode(charBuffer, byteBuffer, true);
                if (!cr.isUnderflow()) {
                    CharSequence truncated = FileSystem.safeTruncate(value, charBuffer.position());
                    return extensionLength == 0 ? truncated : truncated.toString() + ((Object) parts[1]);
                }
                return value;
            }
        },
        UTF16_CODE_UNITS { // from class: org.apache.commons.io.FileSystem.NameLengthStrategy.2
            @Override // org.apache.commons.io.FileSystem.NameLengthStrategy
            int getLength(CharSequence value, Charset charset) {
                return value.length();
            }

            @Override // org.apache.commons.io.FileSystem.NameLengthStrategy
            CharSequence truncate(CharSequence value, int limit, Charset charset) {
                if (!StandardCharsets.UTF_16.newEncoder().canEncode(value)) {
                    throw new IllegalArgumentException("The value " + ((Object) value) + " can not be encoded using " + StandardCharsets.UTF_16.name());
                }
                if (value.length() <= limit) {
                    return value;
                }
                CharSequence[] parts = FileSystem.splitExtension(value);
                int extensionLength = parts[1].length();
                if (extensionLength <= 0 || extensionLength < limit) {
                    CharSequence truncated = FileSystem.safeTruncate(value, limit - extensionLength);
                    return extensionLength == 0 ? truncated : truncated.toString() + ((Object) parts[1]);
                }
                throw new IllegalArgumentException("The extension of " + ((Object) value) + " is too long to fit within " + limit + " characters");
            }
        };

        abstract int getLength(CharSequence charSequence, Charset charset);

        abstract CharSequence truncate(CharSequence charSequence, int i, Charset charset);

        final boolean isWithinLimit(CharSequence value, int limit, Charset charset) {
            return getLength(value, charset) <= limit;
        }
    }

    private static FileSystem current() {
        if (IS_OS_LINUX) {
            return LINUX;
        }
        if (IS_OS_MAC) {
            return MAC_OSX;
        }
        if (IS_OS_WINDOWS) {
            return WINDOWS;
        }
        return GENERIC;
    }

    public static FileSystem getCurrent() {
        return CURRENT;
    }

    private static boolean getOsMatchesName(String osNamePrefix) {
        return isOsNameMatch(getSystemProperty(SystemProperties.OS_NAME), osNamePrefix);
    }

    private static String getSystemProperty(String property) {
        try {
            return System.getProperty(property);
        } catch (SecurityException e) {
            System.err.println("Caught a SecurityException reading the system property '" + property + "'; the SystemUtils property value will default to null.");
            return null;
        }
    }

    private static int indexOfFirstDot(CharSequence cs) {
        if (cs instanceof String) {
            return ((String) cs).indexOf(46);
        }
        for (int i = 0; i < cs.length(); i++) {
            if (cs.charAt(i) == '.') {
                return i;
            }
        }
        return -1;
    }

    private static boolean isOsNameMatch(String osName, String osNamePrefix) {
        if (osName == null) {
            return false;
        }
        return osName.toUpperCase(Locale.ROOT).startsWith(osNamePrefix.toUpperCase(Locale.ROOT));
    }

    private static String replace(String path, char oldChar, char newChar) {
        if (path == null) {
            return null;
        }
        return path.replace(oldChar, newChar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CharSequence safeTruncate(CharSequence value, int limit) {
        if (value.length() <= limit) {
            return value;
        }
        BreakIterator boundary = BreakIterator.getCharacterInstance(Locale.ROOT);
        String text = value.toString();
        boundary.setText(text);
        int end = boundary.preceding(limit + 1);
        if (end == -1) {
            throw new AssertionError();
        }
        if (end == 0) {
            String limitMessage = limit <= 1 ? "1 character" : limit + " characters";
            throw new IllegalArgumentException("The value " + ((Object) value) + " can not be truncated to " + limitMessage + " without breaking the first codepoint or grapheme cluster");
        }
        return text.substring(0, end);
    }

    static CharSequence[] splitExtension(CharSequence value) {
        int index = indexOfFirstDot(value);
        if (index < 1) {
            return new CharSequence[]{value, ""};
        }
        return new CharSequence[]{value.subSequence(0, index), value.subSequence(index, value.length())};
    }

    static CharSequence trimExtension(CharSequence cs) {
        int index = indexOfFirstDot(cs);
        return index < 1 ? cs : cs.subSequence(0, index);
    }

    FileSystem(int blockSize, boolean caseSensitive, boolean casePreserving, int maxFileLength, int maxPathLength, int[] illegalFileNameChars, String[] reservedFileNames, boolean reservedFileNamesExtensions, boolean supportsDriveLetter, char nameSeparator, NameLengthStrategy nameLengthStrategy) {
        this.blockSize = blockSize;
        this.maxFileNameLength = maxFileLength;
        this.maxPathLength = maxPathLength;
        this.illegalFileNameChars = (int[]) Objects.requireNonNull(illegalFileNameChars, "illegalFileNameChars");
        this.reservedFileNames = (String[]) Objects.requireNonNull(reservedFileNames, "reservedFileNames");
        this.reservedFileNamesExtensions = reservedFileNamesExtensions;
        this.caseSensitive = caseSensitive;
        this.casePreserving = casePreserving;
        this.supportsDriveLetter = supportsDriveLetter;
        this.nameSeparator = nameSeparator;
        this.nameSeparatorOther = FilenameUtils.flipSeparator(nameSeparator);
        this.nameLengthStrategy = nameLengthStrategy;
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public char[] getIllegalFileNameChars() {
        char[] chars = new char[this.illegalFileNameChars.length];
        for (int i = 0; i < this.illegalFileNameChars.length; i++) {
            chars[i] = (char) this.illegalFileNameChars[i];
        }
        return chars;
    }

    public int[] getIllegalFileNameCodePoints() {
        return (int[]) this.illegalFileNameChars.clone();
    }

    public int getMaxFileNameLength() {
        return this.maxFileNameLength;
    }

    public int getMaxPathLength() {
        return this.maxPathLength;
    }

    NameLengthStrategy getNameLengthStrategy() {
        return this.nameLengthStrategy;
    }

    public char getNameSeparator() {
        return this.nameSeparator;
    }

    public String[] getReservedFileNames() {
        return (String[]) this.reservedFileNames.clone();
    }

    public boolean isCasePreserving() {
        return this.casePreserving;
    }

    public boolean isCaseSensitive() {
        return this.caseSensitive;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isIllegalFileNameChar(int c) {
        return Arrays.binarySearch(this.illegalFileNameChars, c) >= 0;
    }

    public boolean isLegalFileName(CharSequence candidate) {
        return isLegalFileName(candidate, Charset.defaultCharset());
    }

    public boolean isLegalFileName(CharSequence candidate, Charset charset) {
        return (candidate == null || candidate.length() == 0 || !this.nameLengthStrategy.isWithinLimit(candidate, getMaxFileNameLength(), charset) || isReservedFileName(candidate) || !candidate.chars().noneMatch(new IntPredicate() { // from class: org.apache.commons.io.FileSystem$$ExternalSyntheticLambda1
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                boolean isIllegalFileNameChar;
                isIllegalFileNameChar = FileSystem.this.isIllegalFileNameChar(i);
                return isIllegalFileNameChar;
            }
        })) ? false : true;
    }

    public boolean isReservedFileName(CharSequence candidate) {
        CharSequence test = this.reservedFileNamesExtensions ? trimExtension(candidate) : candidate;
        return Arrays.binarySearch(this.reservedFileNames, test) >= 0;
    }

    public String normalizeSeparators(String path) {
        return replace(path, this.nameSeparatorOther, this.nameSeparator);
    }

    public boolean supportsDriveLetter() {
        return this.supportsDriveLetter;
    }

    public String toLegalFileName(CharSequence candidate, final char replacement, Charset charset) {
        Objects.requireNonNull(candidate, "candidate");
        if (candidate.length() == 0) {
            throw new IllegalArgumentException("The candidate file name is empty");
        }
        if (isIllegalFileNameChar(replacement)) {
            throw new IllegalArgumentException(String.format("The replacement character '%s' cannot be one of the %s illegal characters: %s", replacement == 0 ? "\\0" : Character.valueOf(replacement), name(), Arrays.toString(this.illegalFileNameChars)));
        }
        CharSequence truncated = this.nameLengthStrategy.truncate(candidate, getMaxFileNameLength(), charset);
        int[] array = truncated.chars().map(new IntUnaryOperator() { // from class: org.apache.commons.io.FileSystem$$ExternalSyntheticLambda0
            @Override // java.util.function.IntUnaryOperator
            public final int applyAsInt(int i) {
                return FileSystem.this.m2091lambda$toLegalFileName$0$orgapachecommonsioFileSystem(replacement, i);
            }
        }).toArray();
        return new String(array, 0, array.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$toLegalFileName$0$org-apache-commons-io-FileSystem, reason: not valid java name */
    public /* synthetic */ int m2091lambda$toLegalFileName$0$orgapachecommonsioFileSystem(char replacement, int i) {
        return isIllegalFileNameChar(i) ? replacement : i;
    }

    public String toLegalFileName(String candidate, char replacement) {
        return toLegalFileName(candidate, replacement, Charset.defaultCharset());
    }
}
