package org.apache.commons.compress.archivers.tar;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributes;
import java.time.DateTimeException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import kotlin.jvm.internal.ByteCompanionObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.EntryStreamOffsets;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.ParsingUtils;
import org.apache.commons.io.file.attribute.FileTimes;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemProperties;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

/* loaded from: classes9.dex */
public class TarArchiveEntry implements ArchiveEntry, TarConstants, EntryStreamOffsets {
    public static final int DEFAULT_DIR_MODE = 16877;
    public static final int DEFAULT_FILE_MODE = 33188;
    public static final int MAX_NAMELEN = 31;

    @Deprecated
    public static final int MILLIS_PER_SECOND = 1000;
    public static final long UNKNOWN = -1;
    private FileTime aTime;
    private FileTime birthTime;
    private FileTime cTime;
    private boolean checkSumOK;
    private long dataOffset;
    private int devMajor;
    private int devMinor;
    private final Map<String, String> extraPaxHeaders;
    private final Path file;
    private long groupId;
    private String groupName;
    private boolean isExtended;
    private byte linkFlag;
    private String linkName;
    private final LinkOption[] linkOptions;
    private FileTime mTime;
    private String magic;
    private int mode;
    private String name;
    private boolean paxGNU1XSparse;
    private boolean paxGNUSparse;
    private final boolean preserveAbsolutePath;
    private long realSize;
    private long size;
    private List<TarArchiveStructSparse> sparseHeaders;
    private boolean starSparse;
    private long userId;
    private String userName;
    private String version;
    private static final TarArchiveEntry[] EMPTY_TAR_ARCHIVE_ENTRY_ARRAY = new TarArchiveEntry[0];
    private static final Pattern PAX_EXTENDED_HEADER_FILE_TIMES_PATTERN = Pattern.compile("-?\\d{1,19}(?:\\.\\d{1,19})?");

    private static FileTime fileTimeFromOptionalSeconds(long seconds) {
        if (seconds <= 0) {
            return null;
        }
        return FileTimes.fromUnixTime(seconds);
    }

    private static String normalizeFileName(String fileName, boolean preserveAbsolutePath) {
        String property;
        int colon;
        if (!preserveAbsolutePath && (property = SystemProperties.getOsName()) != null) {
            String osName = StringUtils.toRootLowerCase(property);
            if (osName.startsWith("windows")) {
                if (fileName.length() > 2) {
                    char ch1 = fileName.charAt(0);
                    char ch2 = fileName.charAt(1);
                    if (ch2 == ':' && ((ch1 >= 'a' && ch1 <= 'z') || (ch1 >= 'A' && ch1 <= 'Z'))) {
                        fileName = fileName.substring(2);
                    }
                }
            } else if (osName.contains("netware") && (colon = fileName.indexOf(58)) != -1) {
                fileName = fileName.substring(colon + 1);
            }
        }
        String fileName2 = fileName.replace(File.separatorChar, '/');
        while (!preserveAbsolutePath && fileName2.startsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) {
            fileName2 = fileName2.substring(1);
        }
        return fileName2;
    }

    private static Instant parseInstantFromDecimalSeconds(String value) throws IOException {
        if (!PAX_EXTENDED_HEADER_FILE_TIMES_PATTERN.matcher(value).matches()) {
            throw new IOException("Corrupted PAX header. Time field value is invalid '" + value + "'");
        }
        BigDecimal epochSeconds = new BigDecimal(value);
        long seconds = epochSeconds.longValue();
        long nanos = epochSeconds.remainder(BigDecimal.ONE).movePointRight(9).longValue();
        try {
            return Instant.ofEpochSecond(seconds, nanos);
        } catch (ArithmeticException | DateTimeException e) {
            throw new IOException("Corrupted PAX header. Time field value is invalid '" + value + "'", e);
        }
    }

    private TarArchiveEntry(boolean preserveAbsolutePath) {
        this.name = "";
        this.linkName = "";
        this.magic = "ustar\u0000";
        this.version = TarConstants.VERSION_POSIX;
        this.groupName = "";
        this.extraPaxHeaders = new HashMap();
        this.dataOffset = -1L;
        String user = SystemProperties.getUserName("");
        this.userName = user.length() > 31 ? user.substring(0, 31) : user;
        this.file = null;
        this.linkOptions = IOUtils.EMPTY_LINK_OPTIONS;
        this.preserveAbsolutePath = preserveAbsolutePath;
    }

    public TarArchiveEntry(byte[] headerBuf) {
        this(false);
        parseTarHeader(headerBuf);
    }

    public TarArchiveEntry(byte[] headerBuf, ZipEncoding encoding) throws IOException {
        this(headerBuf, encoding, false);
    }

    public TarArchiveEntry(byte[] headerBuf, ZipEncoding encoding, boolean lenient) throws IOException {
        this((Map<String, String>) Collections.emptyMap(), headerBuf, encoding, lenient);
    }

    public TarArchiveEntry(byte[] headerBuf, ZipEncoding encoding, boolean lenient, long dataOffset) throws IOException {
        this(headerBuf, encoding, lenient);
        setDataOffset(dataOffset);
    }

    public TarArchiveEntry(File file) {
        this(file, file.getPath());
    }

    public TarArchiveEntry(File file, String fileName) {
        this.name = "";
        this.linkName = "";
        this.magic = "ustar\u0000";
        this.version = TarConstants.VERSION_POSIX;
        this.groupName = "";
        this.extraPaxHeaders = new HashMap();
        this.dataOffset = -1L;
        String normalizedName = normalizeFileName(fileName, false);
        this.file = file.toPath();
        this.linkOptions = IOUtils.EMPTY_LINK_OPTIONS;
        try {
            readFileMode(this.file, normalizedName, new LinkOption[0]);
        } catch (IOException e) {
            if (!file.isDirectory()) {
                this.size = file.length();
            }
        }
        this.userName = "";
        try {
            readOsSpecificProperties(this.file, new LinkOption[0]);
        } catch (IOException e2) {
            this.mTime = FileTime.fromMillis(file.lastModified());
        }
        this.preserveAbsolutePath = false;
    }

    public TarArchiveEntry(Map<String, String> globalPaxHeaders, byte[] headerBuf, ZipEncoding encoding, boolean lenient) throws IOException {
        this(false);
        parseTarHeader(globalPaxHeaders, headerBuf, encoding, false, lenient);
    }

    public TarArchiveEntry(Map<String, String> globalPaxHeaders, byte[] headerBuf, ZipEncoding encoding, boolean lenient, long dataOffset) throws IOException {
        this(globalPaxHeaders, headerBuf, encoding, lenient);
        setDataOffset(dataOffset);
    }

    public TarArchiveEntry(Path file) throws IOException {
        this(file, file.toString(), new LinkOption[0]);
    }

    public TarArchiveEntry(Path file, String fileName, LinkOption... linkOptions) throws IOException {
        this.name = "";
        this.linkName = "";
        this.magic = "ustar\u0000";
        this.version = TarConstants.VERSION_POSIX;
        this.groupName = "";
        this.extraPaxHeaders = new HashMap();
        this.dataOffset = -1L;
        String normalizedName = normalizeFileName(fileName, false);
        this.file = file;
        this.linkOptions = linkOptions == null ? IOUtils.EMPTY_LINK_OPTIONS : linkOptions;
        readFileMode(file, normalizedName, linkOptions);
        this.userName = "";
        readOsSpecificProperties(file, new LinkOption[0]);
        this.preserveAbsolutePath = false;
    }

    public TarArchiveEntry(String name) {
        this(name, false);
    }

    public TarArchiveEntry(String name, boolean preserveAbsolutePath) {
        this(preserveAbsolutePath);
        String name2 = normalizeFileName(name, preserveAbsolutePath);
        boolean isDir = name2.endsWith(PackagingURIHelper.FORWARD_SLASH_STRING);
        this.name = name2;
        this.mode = isDir ? DEFAULT_DIR_MODE : DEFAULT_FILE_MODE;
        this.linkFlag = isDir ? TarConstants.LF_DIR : TarConstants.LF_NORMAL;
        this.mTime = FileTime.from(Instant.now());
        this.userName = "";
    }

    public TarArchiveEntry(String name, byte linkFlag) {
        this(name, linkFlag, false);
    }

    public TarArchiveEntry(String name, byte linkFlag, boolean preserveAbsolutePath) {
        this(name, preserveAbsolutePath);
        this.linkFlag = linkFlag;
        if (linkFlag == 76) {
            this.magic = TarConstants.MAGIC_GNU;
            this.version = TarConstants.VERSION_GNU_SPACE;
        }
    }

    public void addPaxHeader(String name, String value) {
        try {
            processPaxHeader(name, value);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Invalid input", ex);
        }
    }

    public void clearExtraPaxHeaders() {
        this.extraPaxHeaders.clear();
    }

    public boolean equals(Object it) {
        if (it == null || getClass() != it.getClass()) {
            return false;
        }
        return equals((TarArchiveEntry) it);
    }

    public boolean equals(TarArchiveEntry it) {
        return it != null && getName().equals(it.getName());
    }

    private int evaluateType(Map<String, String> globalPaxHeaders, byte[] header) {
        if (ArchiveUtils.matchAsciiBuffer(TarConstants.MAGIC_GNU, header, 257, 6)) {
            return 2;
        }
        if (ArchiveUtils.matchAsciiBuffer("ustar\u0000", header, 257, 6)) {
            if (isXstar(globalPaxHeaders, header)) {
                return 4;
            }
            return 3;
        }
        return 0;
    }

    private int fill(byte value, int offset, byte[] outbuf, int length) {
        for (int i = 0; i < length; i++) {
            outbuf[offset + i] = value;
        }
        int i2 = offset + length;
        return i2;
    }

    private int fill(int value, int offset, byte[] outbuf, int length) {
        return fill((byte) value, offset, outbuf, length);
    }

    void fillGNUSparse0xData(Map<String, String> headers) throws IOException {
        this.paxGNUSparse = true;
        this.realSize = ParsingUtils.parseIntValue(headers.get("GNU.sparse.size"));
        if (headers.containsKey("GNU.sparse.name")) {
            this.name = headers.get("GNU.sparse.name");
        }
    }

    void fillGNUSparse1xData(Map<String, String> headers) throws IOException {
        this.paxGNUSparse = true;
        this.paxGNU1XSparse = true;
        if (headers.containsKey("GNU.sparse.name")) {
            this.name = headers.get("GNU.sparse.name");
        }
        if (headers.containsKey("GNU.sparse.realsize")) {
            this.realSize = ParsingUtils.parseIntValue(headers.get("GNU.sparse.realsize"));
        }
    }

    void fillStarSparseData(Map<String, String> headers) throws IOException {
        this.starSparse = true;
        if (headers.containsKey("SCHILY.realsize")) {
            this.realSize = ParsingUtils.parseLongValue(headers.get("SCHILY.realsize"));
        }
    }

    public FileTime getCreationTime() {
        return this.birthTime;
    }

    @Override // org.apache.commons.compress.archivers.EntryStreamOffsets
    public long getDataOffset() {
        return this.dataOffset;
    }

    public int getDevMajor() {
        return this.devMajor;
    }

    public int getDevMinor() {
        return this.devMinor;
    }

    public TarArchiveEntry[] getDirectoryEntries() {
        if (this.file == null || !isDirectory()) {
            return EMPTY_TAR_ARCHIVE_ENTRY_ARRAY;
        }
        List<TarArchiveEntry> entries = new ArrayList<>();
        try {
            DirectoryStream<Path> dirStream = Files.newDirectoryStream(this.file);
            try {
                for (Path p : dirStream) {
                    entries.add(new TarArchiveEntry(p));
                }
                if (dirStream != null) {
                    dirStream.close();
                }
                return (TarArchiveEntry[]) entries.toArray(EMPTY_TAR_ARCHIVE_ENTRY_ARRAY);
            } finally {
            }
        } catch (IOException e) {
            return EMPTY_TAR_ARCHIVE_ENTRY_ARRAY;
        }
    }

    public String getExtraPaxHeader(String name) {
        return this.extraPaxHeaders.get(name);
    }

    public Map<String, String> getExtraPaxHeaders() {
        return Collections.unmodifiableMap(this.extraPaxHeaders);
    }

    public File getFile() {
        if (this.file != null) {
            return this.file.toFile();
        }
        return null;
    }

    @Deprecated
    public int getGroupId() {
        return (int) (this.groupId & (-1));
    }

    public String getGroupName() {
        return this.groupName;
    }

    public FileTime getLastAccessTime() {
        return this.aTime;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public Date getLastModifiedDate() {
        return getModTime();
    }

    public FileTime getLastModifiedTime() {
        return this.mTime;
    }

    public byte getLinkFlag() {
        return this.linkFlag;
    }

    public String getLinkName() {
        return this.linkName;
    }

    public long getLongGroupId() {
        return this.groupId;
    }

    public long getLongUserId() {
        return this.userId;
    }

    public int getMode() {
        return this.mode;
    }

    public Date getModTime() {
        return FileTimes.toDate(this.mTime);
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public String getName() {
        return this.name;
    }

    public List<TarArchiveStructSparse> getOrderedSparseHeaders() throws IOException {
        if (this.sparseHeaders == null || this.sparseHeaders.isEmpty()) {
            return Collections.emptyList();
        }
        List<TarArchiveStructSparse> orderedAndFiltered = (List) this.sparseHeaders.stream().filter(new Predicate() { // from class: org.apache.commons.compress.archivers.tar.TarArchiveEntry$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TarArchiveEntry.lambda$getOrderedSparseHeaders$0((TarArchiveStructSparse) obj);
            }
        }).sorted(Comparator.comparingLong(new ToLongFunction() { // from class: org.apache.commons.compress.archivers.tar.TarArchiveEntry$$ExternalSyntheticLambda1
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((TarArchiveStructSparse) obj).getOffset();
            }
        })).collect(Collectors.toList());
        int numberOfHeaders = orderedAndFiltered.size();
        for (int i = 0; i < numberOfHeaders; i++) {
            TarArchiveStructSparse str = orderedAndFiltered.get(i);
            if (i + 1 < numberOfHeaders && str.getOffset() + str.getNumbytes() > orderedAndFiltered.get(i + 1).getOffset()) {
                throw new IOException("Corrupted TAR archive. Sparse blocks for " + getName() + " overlap each other.");
            }
            if (str.getOffset() + str.getNumbytes() < 0) {
                throw new IOException("Unreadable TAR archive. Offset and numbytes for sparse block in " + getName() + " too large.");
            }
        }
        if (!orderedAndFiltered.isEmpty()) {
            TarArchiveStructSparse last = orderedAndFiltered.get(numberOfHeaders - 1);
            if (last.getOffset() + last.getNumbytes() > getRealSize()) {
                throw new IOException("Corrupted TAR archive. Sparse block extends beyond real size of the entry");
            }
        }
        return orderedAndFiltered;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$getOrderedSparseHeaders$0(TarArchiveStructSparse s) {
        return s.getOffset() > 0 || s.getNumbytes() > 0;
    }

    public Path getPath() {
        return this.file;
    }

    public long getRealSize() {
        if (!isSparse()) {
            return getSize();
        }
        return this.realSize;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public long getSize() {
        return this.size;
    }

    public List<TarArchiveStructSparse> getSparseHeaders() {
        return this.sparseHeaders;
    }

    public FileTime getStatusChangeTime() {
        return this.cTime;
    }

    @Deprecated
    public int getUserId() {
        return (int) (this.userId & (-1));
    }

    public String getUserName() {
        return this.userName;
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean isBlockDevice() {
        return this.linkFlag == 52;
    }

    public boolean isCharacterDevice() {
        return this.linkFlag == 51;
    }

    public boolean isCheckSumOK() {
        return this.checkSumOK;
    }

    public boolean isDescendent(TarArchiveEntry desc) {
        return desc.getName().startsWith(getName());
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public boolean isDirectory() {
        if (this.file != null) {
            return Files.isDirectory(this.file, this.linkOptions);
        }
        if (this.linkFlag == 53) {
            return true;
        }
        return (isPaxHeader() || isGlobalPaxHeader() || !getName().endsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) ? false : true;
    }

    public boolean isExtended() {
        return this.isExtended;
    }

    public boolean isFIFO() {
        return this.linkFlag == 54;
    }

    public boolean isFile() {
        if (this.file != null) {
            return Files.isRegularFile(this.file, this.linkOptions);
        }
        if (this.linkFlag == 0 || this.linkFlag == 48) {
            return true;
        }
        return (this.linkFlag == 53 || getName().endsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) ? false : true;
    }

    public boolean isGlobalPaxHeader() {
        return this.linkFlag == 103;
    }

    public boolean isGNULongLinkEntry() {
        return this.linkFlag == 75;
    }

    public boolean isGNULongNameEntry() {
        return this.linkFlag == 76;
    }

    public boolean isGNUSparse() {
        return isOldGNUSparse() || isPaxGNUSparse();
    }

    private boolean isInvalidPrefix(byte[] header) {
        if (header[475] != 0) {
            if (header[156] != 77) {
                return true;
            }
            return (header[464] & ByteCompanionObject.MIN_VALUE) == 0 && header[475] != 32;
        }
        return false;
    }

    private boolean isInvalidXtarTime(byte[] buffer, int offset, int length) {
        if ((buffer[offset] & ByteCompanionObject.MIN_VALUE) == 0) {
            int lastIndex = length - 1;
            for (int i = 0; i < lastIndex; i++) {
                byte b = buffer[offset + i];
                if (b < 48 || b > 55) {
                    return true;
                }
            }
            int i2 = offset + lastIndex;
            byte b2 = buffer[i2];
            return (b2 == 32 || b2 == 0) ? false : true;
        }
        return false;
    }

    public boolean isLink() {
        return this.linkFlag == 49;
    }

    public boolean isOldGNUSparse() {
        return this.linkFlag == 83;
    }

    public boolean isPaxGNU1XSparse() {
        return this.paxGNU1XSparse;
    }

    public boolean isPaxGNUSparse() {
        return this.paxGNUSparse;
    }

    public boolean isPaxHeader() {
        return this.linkFlag == 120 || this.linkFlag == 88;
    }

    public boolean isSparse() {
        return isGNUSparse() || isStarSparse();
    }

    public boolean isStarSparse() {
        return this.starSparse;
    }

    @Override // org.apache.commons.compress.archivers.EntryStreamOffsets
    public boolean isStreamContiguous() {
        return true;
    }

    public boolean isSymbolicLink() {
        return this.linkFlag == 50;
    }

    boolean isTypeFlagUstar() {
        return this.linkFlag == 0 || (this.linkFlag >= 48 && this.linkFlag <= 55) || (this.linkFlag >= 65 && this.linkFlag <= 90);
    }

    private boolean isXstar(Map<String, String> globalPaxHeaders, byte[] header) {
        if (ArchiveUtils.matchAsciiBuffer(TarConstants.MAGIC_XSTAR, header, 508, 4)) {
            return true;
        }
        String archType = globalPaxHeaders.get("SCHILY.archtype");
        return archType != null ? "xustar".equals(archType) || "exustar".equals(archType) : (isInvalidPrefix(header) || isInvalidXtarTime(header, TarConstants.XSTAR_ATIME_OFFSET, 12) || isInvalidXtarTime(header, TarConstants.XSTAR_CTIME_OFFSET, 12)) ? false : true;
    }

    private long parseOctalOrBinary(byte[] header, int offset, int length, boolean lenient) {
        if (lenient) {
            try {
                return TarUtils.parseOctalOrBinary(header, offset, length);
            } catch (IllegalArgumentException e) {
                return -1L;
            }
        }
        return TarUtils.parseOctalOrBinary(header, offset, length);
    }

    public void parseTarHeader(byte[] header) {
        try {
            parseTarHeader(header, TarUtils.DEFAULT_ENCODING);
        } catch (IOException e) {
            try {
                parseTarHeader(header, TarUtils.DEFAULT_ENCODING, true, false);
            } catch (IOException ex2) {
                throw new UncheckedIOException(ex2);
            }
        }
    }

    public void parseTarHeader(byte[] header, ZipEncoding encoding) throws IOException {
        parseTarHeader(header, encoding, false, false);
    }

    private void parseTarHeader(byte[] header, ZipEncoding encoding, boolean oldStyle, boolean lenient) throws IOException {
        parseTarHeader(Collections.emptyMap(), header, encoding, oldStyle, lenient);
    }

    private void parseTarHeader(Map<String, String> globalPaxHeaders, byte[] header, ZipEncoding encoding, boolean oldStyle, boolean lenient) throws IOException {
        try {
            parseUstarHeaderBlock(globalPaxHeaders, header, encoding, oldStyle, lenient);
        } catch (IllegalArgumentException ex) {
            throw new IOException("Corrupted TAR archive.", ex);
        }
    }

    private int parseTarHeaderBlock(byte[] header, ZipEncoding encoding, boolean oldStyle, boolean lenient, int offset) throws IOException {
        this.name = oldStyle ? TarUtils.parseName(header, offset, 100) : TarUtils.parseName(header, offset, 100, encoding);
        int offset2 = offset + 100;
        this.mode = (int) parseOctalOrBinary(header, offset2, 8, lenient);
        this.userId = (int) parseOctalOrBinary(header, r12, 8, lenient);
        this.groupId = (int) parseOctalOrBinary(header, r12, 8, lenient);
        int offset3 = offset2 + 8 + 8 + 8;
        this.size = TarUtils.parseOctalOrBinary(header, offset3, 12);
        if (this.size < 0) {
            throw new IOException("broken archive, entry with negative size");
        }
        int offset4 = offset3 + 12;
        this.mTime = FileTimes.fromUnixTime(parseOctalOrBinary(header, offset4, 12, lenient));
        this.checkSumOK = TarUtils.verifyCheckSum(header);
        int offset5 = offset4 + 12 + 8;
        int offset6 = offset5 + 1;
        this.linkFlag = header[offset5];
        this.linkName = oldStyle ? TarUtils.parseName(header, offset6, 100) : TarUtils.parseName(header, offset6, 100, encoding);
        return offset6;
    }

    private void parseUstarHeaderBlock(Map<String, String> globalPaxHeaders, byte[] header, ZipEncoding encoding, boolean oldStyle, boolean lenient) throws IOException {
        int offset;
        String xstarPrefix;
        int offset2 = parseTarHeaderBlock(header, encoding, oldStyle, lenient, 0) + 100;
        this.magic = TarUtils.parseName(header, offset2, 6);
        int offset3 = offset2 + 6;
        this.version = TarUtils.parseName(header, offset3, 2);
        int offset4 = offset3 + 2;
        this.userName = oldStyle ? TarUtils.parseName(header, offset4, 32) : TarUtils.parseName(header, offset4, 32, encoding);
        int offset5 = offset4 + 32;
        this.groupName = oldStyle ? TarUtils.parseName(header, offset5, 32) : TarUtils.parseName(header, offset5, 32, encoding);
        int offset6 = offset5 + 32;
        if (this.linkFlag == 51 || this.linkFlag == 52) {
            this.devMajor = (int) parseOctalOrBinary(header, offset6, 8, lenient);
            int offset7 = offset6 + 8;
            this.devMinor = (int) parseOctalOrBinary(header, offset7, 8, lenient);
            offset = offset7 + 8;
        } else {
            offset = offset6 + 16;
        }
        int type = evaluateType(globalPaxHeaders, header);
        switch (type) {
            case 2:
                this.aTime = fileTimeFromOptionalSeconds(parseOctalOrBinary(header, offset, 12, lenient));
                int offset8 = offset + 12;
                this.cTime = fileTimeFromOptionalSeconds(parseOctalOrBinary(header, offset8, 12, lenient));
                int offset9 = offset8 + 12 + 12 + 4 + 1;
                this.sparseHeaders = new ArrayList(TarUtils.readSparseStructs(header, offset9, 4));
                int offset10 = offset9 + 96;
                this.isExtended = TarUtils.parseBoolean(header, offset10);
                int offset11 = offset10 + 1;
                this.realSize = TarUtils.parseOctal(header, offset11, 12);
                int i = offset11 + 12;
                return;
            case 3:
            default:
                String prefix = oldStyle ? TarUtils.parseName(header, offset, 155) : TarUtils.parseName(header, offset, 155, encoding);
                int i2 = offset + 155;
                if (isDirectory() && !this.name.endsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) {
                    this.name += PackagingURIHelper.FORWARD_SLASH_STRING;
                }
                if (!prefix.isEmpty()) {
                    this.name = prefix + PackagingURIHelper.FORWARD_SLASH_STRING + this.name;
                    return;
                }
                return;
            case 4:
                if (oldStyle) {
                    xstarPrefix = TarUtils.parseName(header, offset, 131);
                } else {
                    xstarPrefix = TarUtils.parseName(header, offset, 131, encoding);
                }
                int offset12 = offset + 131;
                if (!xstarPrefix.isEmpty()) {
                    this.name = xstarPrefix + PackagingURIHelper.FORWARD_SLASH_STRING + this.name;
                }
                this.aTime = fileTimeFromOptionalSeconds(parseOctalOrBinary(header, offset12, 12, lenient));
                int offset13 = offset12 + 12;
                this.cTime = fileTimeFromOptionalSeconds(parseOctalOrBinary(header, offset13, 12, lenient));
                int i3 = offset13 + 12;
                return;
        }
    }

    private void processPaxHeader(String key, String val) throws IOException {
        processPaxHeader(key, val, this.extraPaxHeaders);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void processPaxHeader(String key, String val, Map<String, String> headers) throws IOException {
        char c;
        switch (key.hashCode()) {
            case -1916861932:
                if (key.equals("SCHILY.devmajor")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -1916619760:
                if (key.equals("SCHILY.devminor")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -277496563:
                if (key.equals("GNU.sparse.realsize")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -160380561:
                if (key.equals("GNU.sparse.size")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 102338:
                if (key.equals("gid")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 115792:
                if (key.equals("uid")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 3433509:
                if (key.equals("path")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3530753:
                if (key.equals("size")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 93141678:
                if (key.equals("atime")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 94988720:
                if (key.equals("ctime")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 98496370:
                if (key.equals("gname")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 104223930:
                if (key.equals("mtime")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 111425664:
                if (key.equals("uname")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 304222685:
                if (key.equals("LIBARCHIVE.creationtime")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 530706950:
                if (key.equals("SCHILY.filetype")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 1195018015:
                if (key.equals("linkpath")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                setName(val);
                return;
            case 1:
                setLinkName(val);
                return;
            case 2:
                setGroupId(ParsingUtils.parseLongValue(val));
                return;
            case 3:
                setGroupName(val);
                return;
            case 4:
                setUserId(ParsingUtils.parseLongValue(val));
                return;
            case 5:
                setUserName(val);
                return;
            case 6:
                long size = ParsingUtils.parseLongValue(val);
                if (size < 0) {
                    throw new IOException("Corrupted TAR archive. Entry size is negative");
                }
                setSize(size);
                return;
            case 7:
                setLastModifiedTime(FileTime.from(parseInstantFromDecimalSeconds(val)));
                return;
            case '\b':
                setLastAccessTime(FileTime.from(parseInstantFromDecimalSeconds(val)));
                return;
            case '\t':
                setStatusChangeTime(FileTime.from(parseInstantFromDecimalSeconds(val)));
                return;
            case '\n':
                setCreationTime(FileTime.from(parseInstantFromDecimalSeconds(val)));
                return;
            case 11:
                int devMinor = ParsingUtils.parseIntValue(val);
                if (devMinor < 0) {
                    throw new IOException("Corrupted TAR archive. Dev-Minor is negative");
                }
                setDevMinor(devMinor);
                return;
            case '\f':
                int devMajor = ParsingUtils.parseIntValue(val);
                if (devMajor < 0) {
                    throw new IOException("Corrupted TAR archive. Dev-Major is negative");
                }
                setDevMajor(devMajor);
                return;
            case '\r':
                fillGNUSparse0xData(headers);
                return;
            case 14:
                fillGNUSparse1xData(headers);
                return;
            case 15:
                if ("sparse".equals(val)) {
                    fillStarSparseData(headers);
                    return;
                }
                return;
            default:
                this.extraPaxHeaders.put(key, val);
                return;
        }
    }

    private void readFileMode(Path path, String normalizedName, LinkOption... options) throws IOException {
        if (Files.isDirectory(path, options)) {
            this.mode = DEFAULT_DIR_MODE;
            this.linkFlag = TarConstants.LF_DIR;
            int nameLength = normalizedName.length();
            if (nameLength == 0 || normalizedName.charAt(nameLength - 1) != '/') {
                this.name = normalizedName + PackagingURIHelper.FORWARD_SLASH_STRING;
                return;
            } else {
                this.name = normalizedName;
                return;
            }
        }
        this.mode = DEFAULT_FILE_MODE;
        this.linkFlag = TarConstants.LF_NORMAL;
        this.name = normalizedName;
        this.size = Files.size(path);
    }

    private void readOsSpecificProperties(Path path, LinkOption... options) throws IOException {
        Set<String> availableAttributeViews = path.getFileSystem().supportedFileAttributeViews();
        if (availableAttributeViews.contains("posix")) {
            PosixFileAttributes posixFileAttributes = (PosixFileAttributes) Files.readAttributes(path, PosixFileAttributes.class, options);
            setLastModifiedTime(posixFileAttributes.lastModifiedTime());
            setCreationTime(posixFileAttributes.creationTime());
            setLastAccessTime(posixFileAttributes.lastAccessTime());
            this.userName = posixFileAttributes.owner().getName();
            this.groupName = posixFileAttributes.group().getName();
            if (availableAttributeViews.contains("unix")) {
                this.userId = ((Number) Files.getAttribute(path, "unix:uid", options)).longValue();
                this.groupId = ((Number) Files.getAttribute(path, "unix:gid", options)).longValue();
                try {
                    setStatusChangeTime((FileTime) Files.getAttribute(path, "unix:ctime", options));
                    return;
                } catch (IllegalArgumentException e) {
                    return;
                }
            }
            return;
        }
        if (availableAttributeViews.contains("dos")) {
            DosFileAttributes dosFileAttributes = (DosFileAttributes) Files.readAttributes(path, DosFileAttributes.class, options);
            setLastModifiedTime(dosFileAttributes.lastModifiedTime());
            setCreationTime(dosFileAttributes.creationTime());
            setLastAccessTime(dosFileAttributes.lastAccessTime());
        } else {
            BasicFileAttributes basicFileAttributes = Files.readAttributes(path, (Class<BasicFileAttributes>) BasicFileAttributes.class, options);
            setLastModifiedTime(basicFileAttributes.lastModifiedTime());
            setCreationTime(basicFileAttributes.creationTime());
            setLastAccessTime(basicFileAttributes.lastAccessTime());
        }
        this.userName = Files.getOwner(path, options).getName();
    }

    public void setCreationTime(FileTime birthTime) {
        this.birthTime = birthTime;
    }

    public void setDataOffset(long dataOffset) {
        if (dataOffset < 0) {
            throw new IllegalArgumentException("The offset cannot be smaller than 0");
        }
        this.dataOffset = dataOffset;
    }

    public void setDevMajor(int devNo) {
        if (devNo < 0) {
            throw new IllegalArgumentException("Major device number is out of range: " + devNo);
        }
        this.devMajor = devNo;
    }

    public void setDevMinor(int devNo) {
        if (devNo < 0) {
            throw new IllegalArgumentException("Minor device number is out of range: " + devNo);
        }
        this.devMinor = devNo;
    }

    public void setGroupId(int groupId) {
        setGroupId(groupId);
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setIds(int userId, int groupId) {
        setUserId(userId);
        setGroupId(groupId);
    }

    public void setLastAccessTime(FileTime time) {
        this.aTime = time;
    }

    public void setLastModifiedTime(FileTime time) {
        this.mTime = (FileTime) Objects.requireNonNull(time, "time");
    }

    public void setLinkName(String link) {
        this.linkName = link;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setModTime(Date time) {
        setLastModifiedTime(FileTimes.toFileTime(time));
    }

    public void setModTime(FileTime time) {
        setLastModifiedTime(time);
    }

    public void setModTime(long time) {
        setLastModifiedTime(FileTime.fromMillis(time));
    }

    public void setName(String name) {
        this.name = normalizeFileName(name, this.preserveAbsolutePath);
    }

    public void setNames(String userName, String groupName) {
        setUserName(userName);
        setGroupName(groupName);
    }

    public void setSize(long size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size is out of range: " + size);
        }
        this.size = size;
    }

    public void setSparseHeaders(List<TarArchiveStructSparse> sparseHeaders) {
        this.sparseHeaders = sparseHeaders;
    }

    public void setStatusChangeTime(FileTime time) {
        this.cTime = time;
    }

    public void setUserId(int userId) {
        setUserId(userId);
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String toString() {
        return getClass().getSimpleName() + CollectionUtils.DEFAULT_TOSTRING_PREFIX + this.name + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateEntryFromPaxHeaders(Map<String, String> headers) throws IOException {
        for (Map.Entry<String, String> ent : headers.entrySet()) {
            processPaxHeader(ent.getKey(), ent.getValue(), headers);
        }
    }

    public void writeEntryHeader(byte[] outbuf) {
        try {
            writeEntryHeader(outbuf, TarUtils.DEFAULT_ENCODING, false);
        } catch (IOException e) {
            try {
                writeEntryHeader(outbuf, TarUtils.FALLBACK_ENCODING, false);
            } catch (IOException ex2) {
                throw new UncheckedIOException(ex2);
            }
        }
    }

    public void writeEntryHeader(byte[] outbuf, ZipEncoding encoding, boolean starMode) throws IOException {
        int offset = TarUtils.formatNameBytes(this.name, outbuf, 0, 100, encoding);
        int offset2 = this.mode;
        int offset3 = writeEntryHeaderField(this.size, outbuf, writeEntryHeaderField(this.groupId, outbuf, writeEntryHeaderField(this.userId, outbuf, writeEntryHeaderField(offset2, outbuf, offset, 8, starMode), 8, starMode), 8, starMode), 12, starMode);
        FileTime fileTime = this.mTime;
        int offset4 = writeEntryHeaderField(FileTimes.toUnixTime(fileTime), outbuf, offset3, 12, starMode);
        int offset5 = fill((byte) 32, offset4, outbuf, 8);
        outbuf[offset5] = this.linkFlag;
        int offset6 = TarUtils.formatNameBytes(this.groupName, outbuf, TarUtils.formatNameBytes(this.userName, outbuf, TarUtils.formatNameBytes(this.version, outbuf, TarUtils.formatNameBytes(this.magic, outbuf, TarUtils.formatNameBytes(this.linkName, outbuf, offset5 + 1, 100, encoding), 6), 2), 32, encoding), 32, encoding);
        int offset7 = this.devMajor;
        int offset8 = writeEntryHeaderField(this.devMinor, outbuf, writeEntryHeaderField(offset7, outbuf, offset6, 8, starMode), 8, starMode);
        if (starMode) {
            offset8 = fill(0, fill(0, writeEntryHeaderOptionalTimeField(this.cTime, writeEntryHeaderOptionalTimeField(this.aTime, fill(0, offset8, outbuf, 131), outbuf, 12), outbuf, 12), outbuf, 8), outbuf, 4);
        }
        fill(0, offset8, outbuf, outbuf.length - offset8);
        long chk = TarUtils.computeCheckSum(outbuf);
        TarUtils.formatCheckSumOctalBytes(chk, outbuf, offset4, 8);
    }

    private int writeEntryHeaderField(long value, byte[] outbuf, int offset, int length, boolean starMode) {
        if (!starMode && (value < 0 || value >= (1 << ((length - 1) * 3)))) {
            return TarUtils.formatLongOctalBytes(0L, outbuf, offset, length);
        }
        return TarUtils.formatLongOctalOrBinaryBytes(value, outbuf, offset, length);
    }

    private int writeEntryHeaderOptionalTimeField(FileTime time, int offset, byte[] outbuf, int fieldLength) {
        return time != null ? writeEntryHeaderField(FileTimes.toUnixTime(time), outbuf, offset, fieldLength, true) : fill(0, offset, outbuf, fieldLength);
    }
}
