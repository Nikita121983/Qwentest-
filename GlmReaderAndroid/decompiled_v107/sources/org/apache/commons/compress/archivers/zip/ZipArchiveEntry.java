package org.apache.commons.compress.archivers.zip;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.EntryStreamOffsets;
import org.apache.commons.compress.archivers.zip.ExtraFieldUtils;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.file.attribute.FileTimes;
import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes9.dex */
public class ZipArchiveEntry extends ZipEntry implements ArchiveEntry, EntryStreamOffsets {
    public static final int CRC_UNKNOWN = -1;
    static final ZipArchiveEntry[] EMPTY_ARRAY = new ZipArchiveEntry[0];
    static LinkedList<ZipArchiveEntry> EMPTY_LINKED_LIST = new LinkedList<>();
    public static final int PLATFORM_FAT = 0;
    public static final int PLATFORM_UNIX = 3;
    private static final int SHORT_MASK = 65535;
    private static final int SHORT_SHIFT = 16;
    private static final String ZIP_DIR_SEP = "/";
    private int alignment;
    private CommentSource commentSource;
    private long dataOffset;
    private long diskNumberStart;
    private long externalAttributes;
    private final Function<ZipShort, ZipExtraField> extraFieldFactory;
    private ZipExtraField[] extraFields;
    private GeneralPurposeBit generalPurposeBit;
    private int internalAttributes;
    private boolean isStreamContiguous;
    private boolean lastModifiedDateSet;
    private long localHeaderOffset;
    private int method;
    private String name;
    private NameSource nameSource;
    private int platform;
    private int rawFlag;
    private byte[] rawName;
    private long size;
    private long time;
    private UnparseableExtraFieldData unparseableExtra;
    private int versionMadeBy;
    private int versionRequired;

    /* loaded from: classes9.dex */
    public enum CommentSource {
        COMMENT,
        UNICODE_EXTRA_FIELD
    }

    /* loaded from: classes9.dex */
    public enum NameSource {
        NAME,
        NAME_WITH_EFS_FLAG,
        UNICODE_EXTRA_FIELD
    }

    /* loaded from: classes9.dex */
    public enum ExtraFieldParsingMode implements ExtraFieldParsingBehavior {
        BEST_EFFORT(ExtraFieldUtils.UnparseableExtraField.READ) { // from class: org.apache.commons.compress.archivers.zip.ZipArchiveEntry.ExtraFieldParsingMode.1
            @Override // org.apache.commons.compress.archivers.zip.ZipArchiveEntry.ExtraFieldParsingMode, org.apache.commons.compress.archivers.zip.ExtraFieldParsingBehavior
            public ZipExtraField fill(ZipExtraField field, byte[] data, int off, int len, boolean local) {
                return ExtraFieldParsingMode.fillAndMakeUnrecognizedOnError(field, data, off, len, local);
            }
        },
        STRICT_FOR_KNOW_EXTRA_FIELDS(ExtraFieldUtils.UnparseableExtraField.READ),
        ONLY_PARSEABLE_LENIENT(ExtraFieldUtils.UnparseableExtraField.SKIP) { // from class: org.apache.commons.compress.archivers.zip.ZipArchiveEntry.ExtraFieldParsingMode.2
            @Override // org.apache.commons.compress.archivers.zip.ZipArchiveEntry.ExtraFieldParsingMode, org.apache.commons.compress.archivers.zip.ExtraFieldParsingBehavior
            public ZipExtraField fill(ZipExtraField field, byte[] data, int off, int len, boolean local) {
                return ExtraFieldParsingMode.fillAndMakeUnrecognizedOnError(field, data, off, len, local);
            }
        },
        ONLY_PARSEABLE_STRICT(ExtraFieldUtils.UnparseableExtraField.SKIP),
        DRACONIC(ExtraFieldUtils.UnparseableExtraField.THROW);

        private final ExtraFieldUtils.UnparseableExtraField onUnparseableData;

        /* JADX INFO: Access modifiers changed from: private */
        public static ZipExtraField fillAndMakeUnrecognizedOnError(ZipExtraField field, byte[] data, int off, int len, boolean local) {
            try {
                return ExtraFieldUtils.fillExtraField(field, data, off, len, local);
            } catch (ZipException e) {
                UnrecognizedExtraField u = new UnrecognizedExtraField();
                u.setHeaderId(field.getHeaderId());
                if (local) {
                    u.setLocalFileDataData(Arrays.copyOfRange(data, off, off + len));
                } else {
                    u.setCentralDirectoryData(Arrays.copyOfRange(data, off, off + len));
                }
                return u;
            }
        }

        ExtraFieldParsingMode(ExtraFieldUtils.UnparseableExtraField onUnparseableData) {
            this.onUnparseableData = onUnparseableData;
        }

        @Override // org.apache.commons.compress.archivers.zip.ExtraFieldParsingBehavior
        public ZipExtraField createExtraField(ZipShort headerId) {
            return ExtraFieldUtils.createExtraField(headerId);
        }

        @Override // org.apache.commons.compress.archivers.zip.ExtraFieldParsingBehavior
        public ZipExtraField fill(ZipExtraField field, byte[] data, int off, int len, boolean local) throws ZipException {
            return ExtraFieldUtils.fillExtraField(field, data, off, len, local);
        }

        @Override // org.apache.commons.compress.archivers.zip.UnparseableExtraFieldBehavior
        public ZipExtraField onUnparseableExtraField(byte[] data, int off, int len, boolean local, int claimedLength) throws ZipException {
            return this.onUnparseableData.onUnparseableExtraField(data, off, len, local, claimedLength);
        }
    }

    private static boolean canConvertToInfoZipExtendedTimestamp(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime creationTime) {
        return FileTimes.isUnixTime(lastModifiedTime) && FileTimes.isUnixTime(lastAccessTime) && FileTimes.isUnixTime(creationTime);
    }

    private static boolean isDirectoryEntryName(String entryName) {
        return entryName.endsWith("/");
    }

    private static String toDirectoryEntryName(String entryName) {
        return isDirectoryEntryName(entryName) ? entryName : entryName + "/";
    }

    private static String toEntryName(File inputFile, String entryName) {
        return inputFile.isDirectory() ? toDirectoryEntryName(entryName) : entryName;
    }

    private static String toEntryName(Path inputPath, String entryName, LinkOption... options) {
        return Files.isDirectory(inputPath, options) ? toDirectoryEntryName(entryName) : entryName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ZipArchiveEntry() {
        this("");
    }

    public ZipArchiveEntry(File inputFile, String entryName) {
        this((Function<ZipShort, ZipExtraField>) null, inputFile, entryName);
    }

    private ZipArchiveEntry(Function<ZipShort, ZipExtraField> extraFieldFactory, File inputFile, String entryName) {
        this(extraFieldFactory, toEntryName(inputFile, entryName));
        try {
            setAttributes(inputFile.toPath(), new LinkOption[0]);
        } catch (IOException e) {
            if (inputFile.isFile()) {
                setSize(inputFile.length());
            }
            setTime(inputFile.lastModified());
        }
    }

    private ZipArchiveEntry(Function<ZipShort, ZipExtraField> extraFieldFactory, Path inputPath, String entryName, LinkOption... options) throws IOException {
        this(extraFieldFactory, toEntryName(inputPath, entryName, options));
        setAttributes(inputPath, options);
    }

    private ZipArchiveEntry(Function<ZipShort, ZipExtraField> extraFieldFactory, String name) {
        super(name);
        this.method = -1;
        this.size = -1L;
        this.platform = 0;
        this.generalPurposeBit = new GeneralPurposeBit();
        this.localHeaderOffset = -1L;
        this.dataOffset = -1L;
        this.nameSource = NameSource.NAME;
        this.commentSource = CommentSource.COMMENT;
        this.time = -1L;
        this.extraFieldFactory = extraFieldFactory;
        setName(name);
    }

    private ZipArchiveEntry(Function<ZipShort, ZipExtraField> extraFieldFactory, ZipEntry entry) throws ZipException {
        super(entry);
        this.method = -1;
        this.size = -1L;
        this.platform = 0;
        this.generalPurposeBit = new GeneralPurposeBit();
        this.localHeaderOffset = -1L;
        this.dataOffset = -1L;
        this.nameSource = NameSource.NAME;
        this.commentSource = CommentSource.COMMENT;
        this.time = -1L;
        this.extraFieldFactory = extraFieldFactory;
        setName(entry.getName());
        byte[] extra = entry.getExtra();
        if (extra != null) {
            setExtraFields(parseExtraFields(extra, true, ExtraFieldParsingMode.BEST_EFFORT));
        } else {
            setExtra();
        }
        setMethod(entry.getMethod());
        this.size = entry.getSize();
    }

    public ZipArchiveEntry(Path inputPath, String entryName, LinkOption... options) throws IOException {
        this(null, inputPath, entryName, options);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ZipArchiveEntry(String name) {
        this((Function<ZipShort, ZipExtraField>) null, name);
    }

    public ZipArchiveEntry(ZipArchiveEntry entry) throws ZipException {
        this((ZipEntry) entry);
        setInternalAttributes(entry.getInternalAttributes());
        setExternalAttributes(entry.getExternalAttributes());
        setExtraFields(entry.getAllExtraFieldsNoCopy());
        setPlatform(entry.getPlatform());
        GeneralPurposeBit other = entry.getGeneralPurposeBit();
        setGeneralPurposeBit(other == null ? null : (GeneralPurposeBit) other.clone());
    }

    public ZipArchiveEntry(ZipEntry entry) throws ZipException {
        this((Function<ZipShort, ZipExtraField>) null, entry);
    }

    public void addAsFirstExtraField(ZipExtraField ze) {
        if (ze instanceof UnparseableExtraFieldData) {
            this.unparseableExtra = (UnparseableExtraFieldData) ze;
        } else {
            if (getExtraField(ze.getHeaderId()) != null) {
                internalRemoveExtraField(ze.getHeaderId());
            }
            ZipExtraField[] copy = this.extraFields;
            int newLen = ArrayUtils.getLength(this.extraFields) + 1;
            this.extraFields = new ZipExtraField[newLen];
            this.extraFields[0] = ze;
            if (copy != null) {
                System.arraycopy(copy, 0, this.extraFields, 1, this.extraFields.length - 1);
            }
        }
        setExtra();
    }

    public void addExtraField(ZipExtraField ze) {
        internalAddExtraField(ze);
        setExtra();
    }

    private void addInfoZipExtendedTimestamp(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime creationTime) {
        X5455_ExtendedTimestamp infoZipTimestamp = new X5455_ExtendedTimestamp();
        if (lastModifiedTime != null) {
            infoZipTimestamp.setModifyFileTime(lastModifiedTime);
        }
        if (lastAccessTime != null) {
            infoZipTimestamp.setAccessFileTime(lastAccessTime);
        }
        if (creationTime != null) {
            infoZipTimestamp.setCreateFileTime(creationTime);
        }
        internalAddExtraField(infoZipTimestamp);
    }

    private void addNTFSTimestamp(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime creationTime) {
        X000A_NTFS ntfsTimestamp = new X000A_NTFS();
        if (lastModifiedTime != null) {
            ntfsTimestamp.setModifyFileTime(lastModifiedTime);
        }
        if (lastAccessTime != null) {
            ntfsTimestamp.setAccessFileTime(lastAccessTime);
        }
        if (creationTime != null) {
            ntfsTimestamp.setCreateFileTime(creationTime);
        }
        internalAddExtraField(ntfsTimestamp);
    }

    @Override // java.util.zip.ZipEntry
    public Object clone() {
        ZipArchiveEntry e = (ZipArchiveEntry) super.clone();
        e.setInternalAttributes(getInternalAttributes());
        e.setExternalAttributes(getExternalAttributes());
        e.setExtraFields(getAllExtraFieldsNoCopy());
        return e;
    }

    private ZipExtraField[] copyOf(ZipExtraField[] src, int length) {
        return (ZipExtraField[]) Arrays.copyOf(src, length);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ZipArchiveEntry other = (ZipArchiveEntry) obj;
        String myName = getName();
        String otherName = other.getName();
        if (!Objects.equals(myName, otherName)) {
            return false;
        }
        String myComment = getComment();
        String otherComment = other.getComment();
        if (myComment == null) {
            myComment = "";
        }
        if (otherComment == null) {
            otherComment = "";
        }
        if (Objects.equals(getLastModifiedTime(), other.getLastModifiedTime()) && Objects.equals(getLastAccessTime(), other.getLastAccessTime()) && Objects.equals(getCreationTime(), other.getCreationTime()) && myComment.equals(otherComment) && getInternalAttributes() == other.getInternalAttributes() && getPlatform() == other.getPlatform() && getExternalAttributes() == other.getExternalAttributes() && getMethod() == other.getMethod() && getSize() == other.getSize() && getCrc() == other.getCrc() && getCompressedSize() == other.getCompressedSize() && Arrays.equals(getCentralDirectoryExtra(), other.getCentralDirectoryExtra()) && Arrays.equals(getLocalFileDataExtra(), other.getLocalFileDataExtra()) && this.localHeaderOffset == other.localHeaderOffset && this.dataOffset == other.dataOffset && this.generalPurposeBit.equals(other.generalPurposeBit)) {
            return true;
        }
        return false;
    }

    private ZipExtraField findMatching(final ZipShort headerId, List<ZipExtraField> fs) {
        return fs.stream().filter(new Predicate() { // from class: org.apache.commons.compress.archivers.zip.ZipArchiveEntry$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = ZipShort.this.equals(((ZipExtraField) obj).getHeaderId());
                return equals;
            }
        }).findFirst().orElse(null);
    }

    private ZipExtraField findUnparseable(List<ZipExtraField> fs) {
        Stream<ZipExtraField> stream = fs.stream();
        final Class<UnparseableExtraFieldData> cls = UnparseableExtraFieldData.class;
        Objects.requireNonNull(UnparseableExtraFieldData.class);
        return stream.filter(new Predicate() { // from class: org.apache.commons.compress.archivers.zip.ZipArchiveEntry$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean isInstance;
                isInstance = cls.isInstance((ZipExtraField) obj);
                return isInstance;
            }
        }).findFirst().orElse(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getAlignment() {
        return this.alignment;
    }

    private ZipExtraField[] getAllExtraFields() {
        ZipExtraField[] allExtraFieldsNoCopy = getAllExtraFieldsNoCopy();
        return allExtraFieldsNoCopy == this.extraFields ? copyOf(allExtraFieldsNoCopy, allExtraFieldsNoCopy.length) : allExtraFieldsNoCopy;
    }

    private ZipExtraField[] getAllExtraFieldsNoCopy() {
        if (this.extraFields == null) {
            return getUnparseableOnly();
        }
        return this.unparseableExtra != null ? getMergedFields() : this.extraFields;
    }

    public byte[] getCentralDirectoryExtra() {
        return ExtraFieldUtils.mergeCentralDirectoryData(getAllExtraFieldsNoCopy());
    }

    public CommentSource getCommentSource() {
        return this.commentSource;
    }

    @Override // org.apache.commons.compress.archivers.EntryStreamOffsets
    public long getDataOffset() {
        return this.dataOffset;
    }

    public long getDiskNumberStart() {
        return this.diskNumberStart;
    }

    public long getExternalAttributes() {
        return this.externalAttributes;
    }

    public ZipExtraField getExtraField(ZipShort type) {
        if (this.extraFields != null) {
            for (ZipExtraField extraField : this.extraFields) {
                if (type.equals(extraField.getHeaderId())) {
                    return extraField;
                }
            }
            return null;
        }
        return null;
    }

    public ZipExtraField[] getExtraFields() {
        return getParseableExtraFields();
    }

    public ZipExtraField[] getExtraFields(boolean includeUnparseable) {
        return includeUnparseable ? getAllExtraFields() : getParseableExtraFields();
    }

    public ZipExtraField[] getExtraFields(ExtraFieldParsingBehavior parsingBehavior) throws ZipException {
        ZipExtraField c;
        if (parsingBehavior == ExtraFieldParsingMode.BEST_EFFORT) {
            return getExtraFields(true);
        }
        if (parsingBehavior == ExtraFieldParsingMode.ONLY_PARSEABLE_LENIENT) {
            return getExtraFields(false);
        }
        byte[] local = getExtra();
        List<ZipExtraField> localFields = new ArrayList<>(Arrays.asList(parseExtraFields(local, true, parsingBehavior)));
        byte[] central = getCentralDirectoryExtra();
        ArrayList arrayList = new ArrayList(Arrays.asList(parseExtraFields(central, false, parsingBehavior)));
        List<ZipExtraField> merged = new ArrayList<>();
        for (ZipExtraField l : localFields) {
            if (l instanceof UnparseableExtraFieldData) {
                c = findUnparseable(arrayList);
            } else {
                c = findMatching(l.getHeaderId(), arrayList);
            }
            if (c != null) {
                byte[] cd = c.getCentralDirectoryData();
                if (!ArrayUtils.isEmpty(cd)) {
                    l.parseFromCentralDirectoryData(cd, 0, cd.length);
                }
                arrayList.remove(c);
            }
            merged.add(l);
        }
        merged.addAll(arrayList);
        return (ZipExtraField[]) merged.toArray(ExtraFieldUtils.EMPTY_ZIP_EXTRA_FIELD_ARRAY);
    }

    public GeneralPurposeBit getGeneralPurposeBit() {
        return this.generalPurposeBit;
    }

    public int getInternalAttributes() {
        return this.internalAttributes;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public Date getLastModifiedDate() {
        return new Date(getTime());
    }

    public byte[] getLocalFileDataExtra() {
        byte[] extra = getExtra();
        return extra != null ? extra : ByteUtils.EMPTY_BYTE_ARRAY;
    }

    public long getLocalHeaderOffset() {
        return this.localHeaderOffset;
    }

    private ZipExtraField[] getMergedFields() {
        ZipExtraField[] zipExtraFields = copyOf(this.extraFields, this.extraFields.length + 1);
        zipExtraFields[this.extraFields.length] = this.unparseableExtra;
        return zipExtraFields;
    }

    @Override // java.util.zip.ZipEntry
    public int getMethod() {
        return this.method;
    }

    @Override // java.util.zip.ZipEntry, org.apache.commons.compress.archivers.ArchiveEntry
    public String getName() {
        return this.name == null ? super.getName() : this.name;
    }

    public NameSource getNameSource() {
        return this.nameSource;
    }

    private ZipExtraField[] getParseableExtraFields() {
        ZipExtraField[] parseableExtraFields = getParseableExtraFieldsNoCopy();
        return parseableExtraFields == this.extraFields ? copyOf(parseableExtraFields, parseableExtraFields.length) : parseableExtraFields;
    }

    private ZipExtraField[] getParseableExtraFieldsNoCopy() {
        if (this.extraFields == null) {
            return ExtraFieldUtils.EMPTY_ZIP_EXTRA_FIELD_ARRAY;
        }
        return this.extraFields;
    }

    public int getPlatform() {
        return this.platform;
    }

    public int getRawFlag() {
        return this.rawFlag;
    }

    public byte[] getRawName() {
        if (this.rawName != null) {
            return Arrays.copyOf(this.rawName, this.rawName.length);
        }
        return null;
    }

    @Override // java.util.zip.ZipEntry, org.apache.commons.compress.archivers.ArchiveEntry
    public long getSize() {
        return this.size;
    }

    @Override // java.util.zip.ZipEntry
    public long getTime() {
        if (this.lastModifiedDateSet) {
            return getLastModifiedTime().toMillis();
        }
        return this.time != -1 ? this.time : super.getTime();
    }

    public int getUnixMode() {
        if (this.platform != 3) {
            return 0;
        }
        return (int) ((getExternalAttributes() >> 16) & 65535);
    }

    public UnparseableExtraFieldData getUnparseableExtraFieldData() {
        return this.unparseableExtra;
    }

    private ZipExtraField[] getUnparseableOnly() {
        return this.unparseableExtra == null ? ExtraFieldUtils.EMPTY_ZIP_EXTRA_FIELD_ARRAY : new ZipExtraField[]{this.unparseableExtra};
    }

    public int getVersionMadeBy() {
        return this.versionMadeBy;
    }

    public int getVersionRequired() {
        return this.versionRequired;
    }

    @Override // java.util.zip.ZipEntry
    public int hashCode() {
        return getName().hashCode();
    }

    private void internalAddExtraField(ZipExtraField ze) {
        if (ze instanceof UnparseableExtraFieldData) {
            this.unparseableExtra = (UnparseableExtraFieldData) ze;
            return;
        }
        if (this.extraFields == null) {
            this.extraFields = new ZipExtraField[]{ze};
            return;
        }
        if (getExtraField(ze.getHeaderId()) != null) {
            internalRemoveExtraField(ze.getHeaderId());
        }
        ZipExtraField[] zipExtraFields = copyOf(this.extraFields, this.extraFields.length + 1);
        zipExtraFields[zipExtraFields.length - 1] = ze;
        this.extraFields = zipExtraFields;
    }

    private void internalRemoveExtraField(ZipShort type) {
        if (this.extraFields == null) {
            return;
        }
        List<ZipExtraField> newResult = new ArrayList<>();
        for (ZipExtraField extraField : this.extraFields) {
            if (!type.equals(extraField.getHeaderId())) {
                newResult.add(extraField);
            }
        }
        if (this.extraFields.length == newResult.size()) {
            return;
        }
        this.extraFields = (ZipExtraField[]) newResult.toArray(ExtraFieldUtils.EMPTY_ZIP_EXTRA_FIELD_ARRAY);
    }

    private void internalSetLastModifiedTime(FileTime time) {
        super.setLastModifiedTime(time);
        this.time = time.toMillis();
        this.lastModifiedDateSet = true;
    }

    @Override // java.util.zip.ZipEntry, org.apache.commons.compress.archivers.ArchiveEntry
    public boolean isDirectory() {
        return isDirectoryEntryName(getName());
    }

    @Override // org.apache.commons.compress.archivers.EntryStreamOffsets
    public boolean isStreamContiguous() {
        return this.isStreamContiguous;
    }

    public boolean isUnixSymlink() {
        return (getUnixMode() & 61440) == 40960;
    }

    private void mergeExtraFields(ZipExtraField[] f, boolean local) {
        ZipExtraField existing;
        if (this.extraFields == null) {
            setExtraFields(f);
            return;
        }
        for (ZipExtraField element : f) {
            if (element instanceof UnparseableExtraFieldData) {
                existing = this.unparseableExtra;
            } else {
                existing = getExtraField(element.getHeaderId());
            }
            if (existing == null) {
                internalAddExtraField(element);
            } else {
                byte[] b = local ? element.getLocalFileDataData() : element.getCentralDirectoryData();
                if (local) {
                    try {
                        existing.parseFromLocalFileData(b, 0, b.length);
                    } catch (ZipException e) {
                        UnrecognizedExtraField u = new UnrecognizedExtraField();
                        u.setHeaderId(existing.getHeaderId());
                        if (local) {
                            u.setLocalFileDataData(b);
                            u.setCentralDirectoryData(existing.getCentralDirectoryData());
                        } else {
                            u.setLocalFileDataData(existing.getLocalFileDataData());
                            u.setCentralDirectoryData(b);
                        }
                        internalRemoveExtraField(existing.getHeaderId());
                        internalAddExtraField(u);
                    }
                } else {
                    existing.parseFromCentralDirectoryData(b, 0, b.length);
                }
            }
        }
        setExtra();
    }

    private ZipExtraField[] parseExtraFields(byte[] data, boolean local, final ExtraFieldParsingBehavior parsingBehavior) throws ZipException {
        if (this.extraFieldFactory != null) {
            return ExtraFieldUtils.parse(data, local, new ExtraFieldParsingBehavior() { // from class: org.apache.commons.compress.archivers.zip.ZipArchiveEntry.1
                @Override // org.apache.commons.compress.archivers.zip.ExtraFieldParsingBehavior
                public ZipExtraField createExtraField(ZipShort headerId) throws ZipException, InstantiationException, IllegalAccessException {
                    ZipExtraField field = (ZipExtraField) ZipArchiveEntry.this.extraFieldFactory.apply(headerId);
                    return field == null ? parsingBehavior.createExtraField(headerId) : field;
                }

                @Override // org.apache.commons.compress.archivers.zip.ExtraFieldParsingBehavior
                public ZipExtraField fill(ZipExtraField field, byte[] data2, int off, int len, boolean local2) throws ZipException {
                    return parsingBehavior.fill(field, data2, off, len, local2);
                }

                @Override // org.apache.commons.compress.archivers.zip.UnparseableExtraFieldBehavior
                public ZipExtraField onUnparseableExtraField(byte[] data2, int off, int len, boolean local2, int claimedLength) throws ZipException {
                    return parsingBehavior.onUnparseableExtraField(data2, off, len, local2, claimedLength);
                }
            });
        }
        return ExtraFieldUtils.parse(data, local, parsingBehavior);
    }

    public void removeExtraField(ZipShort type) {
        if (getExtraField(type) == null) {
            throw new NoSuchElementException();
        }
        internalRemoveExtraField(type);
        setExtra();
    }

    public void removeUnparseableExtraFieldData() {
        if (this.unparseableExtra == null) {
            throw new NoSuchElementException();
        }
        this.unparseableExtra = null;
        setExtra();
    }

    private boolean requiresExtraTimeFields() {
        if (getLastAccessTime() != null || getCreationTime() != null) {
            return true;
        }
        return this.lastModifiedDateSet;
    }

    public void setAlignment(int alignment) {
        if (((alignment - 1) & alignment) != 0 || alignment > 65535) {
            throw new IllegalArgumentException("Invalid value for alignment, must be power of two and no bigger than 65535 but is " + alignment);
        }
        this.alignment = alignment;
    }

    private void setAttributes(Path inputPath, LinkOption... options) throws IOException {
        BasicFileAttributes attributes = Files.readAttributes(inputPath, (Class<BasicFileAttributes>) BasicFileAttributes.class, options);
        if (attributes.isRegularFile()) {
            setSize(attributes.size());
        }
        super.setLastModifiedTime(attributes.lastModifiedTime());
        super.setCreationTime(attributes.creationTime());
        super.setLastAccessTime(attributes.lastAccessTime());
        setExtraTimeFields();
    }

    public void setCentralDirectoryExtra(byte[] b) {
        try {
            mergeExtraFields(parseExtraFields(b, false, ExtraFieldParsingMode.BEST_EFFORT), false);
        } catch (ZipException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    public void setCommentSource(CommentSource commentSource) {
        this.commentSource = commentSource;
    }

    @Override // java.util.zip.ZipEntry
    public ZipEntry setCreationTime(FileTime time) {
        super.setCreationTime(time);
        setExtraTimeFields();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setDataOffset(long dataOffset) {
        this.dataOffset = dataOffset;
    }

    public void setDiskNumberStart(long diskNumberStart) {
        this.diskNumberStart = diskNumberStart;
    }

    public void setExternalAttributes(long value) {
        this.externalAttributes = value;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setExtra() {
        super.setExtra(ExtraFieldUtils.mergeLocalFileDataData(getAllExtraFieldsNoCopy()));
        updateTimeFieldsFromExtraFields();
    }

    @Override // java.util.zip.ZipEntry
    public void setExtra(byte[] extra) throws RuntimeException {
        try {
            mergeExtraFields(parseExtraFields(extra, true, ExtraFieldParsingMode.BEST_EFFORT), true);
        } catch (ZipException e) {
            throw new IllegalArgumentException("Error parsing extra fields for entry: " + getName() + " - " + e.getMessage(), e);
        }
    }

    public void setExtraFields(ZipExtraField[] fields) {
        this.unparseableExtra = null;
        List<ZipExtraField> newFields = new ArrayList<>();
        if (fields != null) {
            for (ZipExtraField field : fields) {
                if (field instanceof UnparseableExtraFieldData) {
                    this.unparseableExtra = (UnparseableExtraFieldData) field;
                } else {
                    newFields.add(field);
                }
            }
        }
        this.extraFields = (ZipExtraField[]) newFields.toArray(ExtraFieldUtils.EMPTY_ZIP_EXTRA_FIELD_ARRAY);
        setExtra();
    }

    private void setExtraTimeFields() {
        if (getExtraField(X5455_ExtendedTimestamp.HEADER_ID) != null) {
            internalRemoveExtraField(X5455_ExtendedTimestamp.HEADER_ID);
        }
        if (getExtraField(X000A_NTFS.HEADER_ID) != null) {
            internalRemoveExtraField(X000A_NTFS.HEADER_ID);
        }
        if (requiresExtraTimeFields()) {
            FileTime lastModifiedTime = getLastModifiedTime();
            FileTime lastAccessTime = getLastAccessTime();
            FileTime creationTime = getCreationTime();
            if (canConvertToInfoZipExtendedTimestamp(lastModifiedTime, lastAccessTime, creationTime)) {
                addInfoZipExtendedTimestamp(lastModifiedTime, lastAccessTime, creationTime);
            }
            addNTFSTimestamp(lastModifiedTime, lastAccessTime, creationTime);
        }
        setExtra();
    }

    public void setGeneralPurposeBit(GeneralPurposeBit generalPurposeBit) {
        this.generalPurposeBit = generalPurposeBit;
    }

    public void setInternalAttributes(int internalAttributes) {
        this.internalAttributes = internalAttributes;
    }

    @Override // java.util.zip.ZipEntry
    public ZipEntry setLastAccessTime(FileTime fileTime) {
        super.setLastAccessTime(fileTime);
        setExtraTimeFields();
        return this;
    }

    @Override // java.util.zip.ZipEntry
    public ZipEntry setLastModifiedTime(FileTime fileTime) {
        internalSetLastModifiedTime(fileTime);
        setExtraTimeFields();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setLocalHeaderOffset(long localHeaderOffset) {
        this.localHeaderOffset = localHeaderOffset;
    }

    @Override // java.util.zip.ZipEntry
    public void setMethod(int method) {
        if (method < 0) {
            throw new IllegalArgumentException("ZIP compression method cannot be negative: " + method);
        }
        this.method = method;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setName(String name) {
        if (name != null && getPlatform() == 0 && !name.contains("/")) {
            name = name.replace(IOUtils.DIR_SEPARATOR_WINDOWS, '/');
        }
        this.name = name;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setName(String name, byte[] rawName) {
        setName(name);
        this.rawName = rawName;
    }

    public void setNameSource(NameSource nameSource) {
        this.nameSource = nameSource;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public void setRawFlag(int rawFlag) {
        this.rawFlag = rawFlag;
    }

    @Override // java.util.zip.ZipEntry
    public void setSize(long size) {
        if (size < 0) {
            throw new IllegalArgumentException("Invalid entry size");
        }
        this.size = size;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setStreamContiguous(boolean isStreamContiguous) {
        this.isStreamContiguous = isStreamContiguous;
    }

    public void setTime(FileTime fileTime) {
        setTime(fileTime.toMillis());
    }

    @Override // java.util.zip.ZipEntry
    public void setTime(long timeEpochMillis) {
        if (ZipUtil.isDosTime(timeEpochMillis)) {
            super.setTime(timeEpochMillis);
            this.time = timeEpochMillis;
            this.lastModifiedDateSet = false;
            setExtraTimeFields();
            return;
        }
        setLastModifiedTime(FileTime.fromMillis(timeEpochMillis));
    }

    public void setUnixMode(int mode) {
        setExternalAttributes((mode << 16) | ((mode & 128) == 0 ? 1 : 0) | (isDirectory() ? 16 : 0));
        this.platform = 3;
    }

    public void setVersionMadeBy(int versionMadeBy) {
        this.versionMadeBy = versionMadeBy;
    }

    public void setVersionRequired(int versionRequired) {
        this.versionRequired = versionRequired;
    }

    private void updateTimeFieldsFromExtraFields() {
        updateTimeFromExtendedTimestampField();
        updateTimeFromNtfsField();
    }

    private void updateTimeFromExtendedTimestampField() {
        FileTime creationTime;
        FileTime accessTime;
        FileTime modifyTime;
        ZipExtraField extraField = getExtraField(X5455_ExtendedTimestamp.HEADER_ID);
        if (extraField instanceof X5455_ExtendedTimestamp) {
            X5455_ExtendedTimestamp extendedTimestamp = (X5455_ExtendedTimestamp) extraField;
            if (extendedTimestamp.isBit0_modifyTimePresent() && (modifyTime = extendedTimestamp.getModifyFileTime()) != null) {
                internalSetLastModifiedTime(modifyTime);
            }
            if (extendedTimestamp.isBit1_accessTimePresent() && (accessTime = extendedTimestamp.getAccessFileTime()) != null) {
                super.setLastAccessTime(accessTime);
            }
            if (extendedTimestamp.isBit2_createTimePresent() && (creationTime = extendedTimestamp.getCreateFileTime()) != null) {
                super.setCreationTime(creationTime);
            }
        }
    }

    private void updateTimeFromNtfsField() {
        ZipExtraField extraField = getExtraField(X000A_NTFS.HEADER_ID);
        if (extraField instanceof X000A_NTFS) {
            X000A_NTFS ntfsTimestamp = (X000A_NTFS) extraField;
            FileTime modifyTime = ntfsTimestamp.getModifyFileTime();
            if (modifyTime != null) {
                internalSetLastModifiedTime(modifyTime);
            }
            FileTime accessTime = ntfsTimestamp.getAccessFileTime();
            if (accessTime != null) {
                super.setLastAccessTime(accessTime);
            }
            FileTime creationTime = ntfsTimestamp.getCreateFileTime();
            if (creationTime != null) {
                super.setCreationTime(creationTime);
            }
        }
    }
}
