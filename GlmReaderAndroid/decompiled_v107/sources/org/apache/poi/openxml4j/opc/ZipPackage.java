package org.apache.poi.openxml4j.opc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.util.Supplier;
import org.apache.poi.UnsupportedFileFormatException;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.openxml4j.exceptions.ODFNotOfficeXmlFileException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;
import org.apache.poi.openxml4j.opc.ZipPackage;
import org.apache.poi.openxml4j.opc.internal.ContentTypeManager;
import org.apache.poi.openxml4j.opc.internal.EncryptedTempFilePackagePart;
import org.apache.poi.openxml4j.opc.internal.FileHelper;
import org.apache.poi.openxml4j.opc.internal.InvalidZipException;
import org.apache.poi.openxml4j.opc.internal.MemoryPackagePart;
import org.apache.poi.openxml4j.opc.internal.PartMarshaller;
import org.apache.poi.openxml4j.opc.internal.TempFilePackagePart;
import org.apache.poi.openxml4j.opc.internal.ZipContentTypeManager;
import org.apache.poi.openxml4j.opc.internal.ZipHelper;
import org.apache.poi.openxml4j.opc.internal.marshallers.ZipPartMarshaller;
import org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream;
import org.apache.poi.openxml4j.util.ZipEntrySource;
import org.apache.poi.openxml4j.util.ZipFileZipEntrySource;
import org.apache.poi.openxml4j.util.ZipInputStreamZipEntrySource;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.TempFile;

/* loaded from: classes10.dex */
public final class ZipPackage extends OPCPackage {
    private static final String MIMETYPE = "mimetype";
    private static final String SETTINGS_XML = "settings.xml";
    private final ZipEntrySource zipArchive;
    private static boolean useTempFilePackageParts = false;
    private static boolean encryptTempFilePackageParts = false;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) ZipPackage.class);

    public static void setUseTempFilePackageParts(boolean tempFilePackageParts) {
        useTempFilePackageParts = tempFilePackageParts;
    }

    public static void setEncryptTempFilePackageParts(boolean encryptTempFiles) {
        encryptTempFilePackageParts = encryptTempFiles;
    }

    public static boolean useTempFilePackageParts() {
        return useTempFilePackageParts;
    }

    public static boolean encryptTempFilePackageParts() {
        return encryptTempFilePackageParts;
    }

    public ZipPackage() {
        this(OPCComplianceFlags.enforceAll());
    }

    public ZipPackage(OPCComplianceFlags opcComplianceFlags) {
        super(defaultPackageAccess, opcComplianceFlags);
        this.zipArchive = null;
        try {
            this.contentTypeManager = new ZipContentTypeManager(null, this);
        } catch (InvalidFormatException e) {
            LOG.atWarn().withThrowable(e).log("Could not parse ZipPackage");
        }
    }

    ZipPackage(InputStream in, PackageAccess access) throws IOException {
        this(in, access, OPCComplianceFlags.enforceAll());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZipPackage(InputStream in, PackageAccess access, OPCComplianceFlags opcComplianceFlags) throws IOException {
        super(access, opcComplianceFlags);
        ZipArchiveThresholdInputStream zis = ZipHelper.openZipStream(in);
        try {
            this.zipArchive = new ZipInputStreamZipEntrySource(zis);
            if (zis != null) {
                zis.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (zis != null) {
                    try {
                        zis.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    ZipPackage(InputStream in, PackageAccess access, boolean closeStream) throws IOException {
        this(in, access, closeStream, OPCComplianceFlags.enforceAll());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZipPackage(InputStream in, PackageAccess access, boolean closeStream, OPCComplianceFlags opcComplianceFlags) throws IOException {
        super(access, opcComplianceFlags);
        ZipArchiveThresholdInputStream zis = ZipHelper.openZipStream(in, closeStream);
        try {
            this.zipArchive = new ZipInputStreamZipEntrySource(zis);
            if (zis != null) {
                zis.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (zis != null) {
                    try {
                        zis.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    ZipPackage(String path, PackageAccess access) throws InvalidOperationException {
        this(path, access, OPCComplianceFlags.enforceAll());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZipPackage(String path, PackageAccess access, OPCComplianceFlags opcComplianceFlags) throws InvalidOperationException {
        this(new File(path), access, opcComplianceFlags);
    }

    ZipPackage(File file, PackageAccess access) throws InvalidOperationException {
        this(file, access, OPCComplianceFlags.enforceAll());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZipPackage(File file, PackageAccess access, OPCComplianceFlags opcComplianceFlags) throws InvalidOperationException {
        super(access, opcComplianceFlags);
        ZipEntrySource ze;
        try {
            ZipFile zipFile = ZipHelper.openZipFile(file);
            ze = new ZipFileZipEntrySource(zipFile);
        } catch (InvalidZipException e) {
            throw new InvalidOperationException("Can't open the specified file: '" + file + "'", e);
        } catch (IOException e2) {
            if (access == PackageAccess.WRITE) {
                throw new InvalidOperationException("Can't open the specified file: '" + file + "'", e2);
            }
            LOG.atWarn().log("Error in zip file {} - falling back to stream processing (i.e. ignoring zip central directory)", file);
            ze = openZipEntrySourceStream(file);
        }
        this.zipArchive = ze;
    }

    private static ZipEntrySource openZipEntrySourceStream(File file) throws InvalidOperationException {
        try {
            InputStream fis = Files.newInputStream(file.toPath(), new OpenOption[0]);
            ZipArchiveThresholdInputStream zis = null;
            try {
                zis = ZipHelper.openZipStream(fis);
                return new ZipInputStreamZipEntrySource(zis);
            } catch (UnsupportedFileFormatException e) {
                e = e;
                IOUtils.closeQuietly(fis);
                IOUtils.closeQuietly(zis);
                throw e;
            } catch (InvalidOperationException e2) {
                e = e2;
                IOUtils.closeQuietly(fis);
                IOUtils.closeQuietly(zis);
                throw e;
            } catch (Exception e3) {
                IOUtils.closeQuietly(fis);
                IOUtils.closeQuietly(zis);
                throw new InvalidOperationException("Failed to read the file input stream from file: '" + file + "'", e3);
            }
        } catch (IOException e4) {
            throw new InvalidOperationException("Can't open the specified file input stream from file: '" + file + "'", e4);
        }
    }

    ZipPackage(ZipEntrySource zipEntry, PackageAccess access) {
        this(zipEntry, access, OPCComplianceFlags.enforceAll());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZipPackage(ZipEntrySource zipEntry, PackageAccess access, OPCComplianceFlags opcComplianceFlags) {
        super(access, opcComplianceFlags);
        this.zipArchive = zipEntry;
    }

    @Override // org.apache.poi.openxml4j.opc.OPCPackage
    protected PackagePartCollection getPartsImpl() throws InvalidFormatException {
        PackagePartCollection newPartList = new PackagePartCollection();
        if (this.zipArchive == null) {
            return newPartList;
        }
        ZipArchiveEntry contentTypeEntry = this.zipArchive.getEntry(ContentTypeManager.CONTENT_TYPES_PART_NAME);
        if (contentTypeEntry != null) {
            if (this.contentTypeManager != null) {
                throw new InvalidFormatException("ContentTypeManager can only be created once. This must be a cyclic relation?");
            }
            try {
                this.contentTypeManager = new ZipContentTypeManager(this.zipArchive.getInputStream(contentTypeEntry), this);
                Enumeration<? extends ZipArchiveEntry> zipEntries = this.zipArchive.getEntries();
                List<? extends ZipArchiveEntry> list = Collections.list(zipEntries);
                if (list.size() > ZipSecureFile.getMaxFileCount()) {
                    throw new InvalidFormatException(String.format(Locale.ROOT, ZipSecureFile.MAX_FILE_COUNT_MSG, Long.valueOf(ZipSecureFile.getMaxFileCount())));
                }
                List<EntryTriple> entries = (List) list.stream().filter(new Predicate() { // from class: org.apache.poi.openxml4j.opc.ZipPackage$$ExternalSyntheticLambda0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return ZipPackage.lambda$getPartsImpl$0((ZipArchiveEntry) obj);
                    }
                }).map(new Function() { // from class: org.apache.poi.openxml4j.opc.ZipPackage$$ExternalSyntheticLambda1
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ZipPackage.this.m2495lambda$getPartsImpl$1$orgapachepoiopenxml4jopcZipPackage((ZipArchiveEntry) obj);
                    }
                }).filter(new Predicate() { // from class: org.apache.poi.openxml4j.opc.ZipPackage$$ExternalSyntheticLambda2
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return ZipPackage.lambda$getPartsImpl$2((ZipPackage.EntryTriple) obj);
                    }
                }).sorted().collect(Collectors.toList());
                for (EntryTriple et : entries) {
                    et.register(newPartList);
                }
                return newPartList;
            } catch (IOException e) {
                throw new InvalidFormatException(e.getMessage(), e);
            }
        }
        boolean hasMimetype = this.zipArchive.getEntry(MIMETYPE) != null;
        boolean hasSettingsXML = this.zipArchive.getEntry(SETTINGS_XML) != null;
        if (hasMimetype && hasSettingsXML) {
            throw new ODFNotOfficeXmlFileException("The supplied data appears to be in ODF (Open Document) Format. Formats like these (eg ODS, ODP) are not supported, try Apache ODFToolkit");
        }
        Enumeration<? extends ZipArchiveEntry> zipEntries2 = this.zipArchive.getEntries();
        if (!zipEntries2.hasMoreElements()) {
            throw new NotOfficeXmlFileException("No valid entries or contents found, this is not a valid OOXML (Office Open XML) file");
        }
        throw new InvalidFormatException("Package should contain a content type part [M1.13]");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$getPartsImpl$0(ZipArchiveEntry zipArchiveEntry) {
        return !ignoreEntry(zipArchiveEntry);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getPartsImpl$1$org-apache-poi-openxml4j-opc-ZipPackage, reason: not valid java name */
    public /* synthetic */ EntryTriple m2495lambda$getPartsImpl$1$orgapachepoiopenxml4jopcZipPackage(ZipArchiveEntry zae) {
        return new EntryTriple(zae, this.contentTypeManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$getPartsImpl$2(EntryTriple mm) {
        return mm.partName != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean ignoreEntry(ZipArchiveEntry zipArchiveEntry) {
        String name = zipArchiveEntry.getName();
        return name.startsWith("[trash]") || name.endsWith(PackagingURIHelper.FORWARD_SLASH_STRING);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class EntryTriple implements Comparable<EntryTriple> {
        final String contentType;
        final PackagePartName partName;
        final ZipArchiveEntry zipArchiveEntry;

        EntryTriple(ZipArchiveEntry zipArchiveEntry, ContentTypeManager contentTypeManager) {
            this.zipArchiveEntry = zipArchiveEntry;
            String entryName = zipArchiveEntry.getName();
            PackagePartName ppn = null;
            if (!ZipPackage.ignoreEntry(zipArchiveEntry)) {
                try {
                    ppn = ContentTypeManager.CONTENT_TYPES_PART_NAME.equalsIgnoreCase(entryName) ? null : PackagingURIHelper.createPartName(ZipHelper.getOPCNameFromZipItemName(entryName));
                } catch (Exception e) {
                    ZipPackage.LOG.atWarn().withThrowable(e).log("Entry {} is not valid, so this part won't be added to the package.", entryName);
                }
            }
            this.partName = ppn;
            this.contentType = ppn != null ? contentTypeManager.getContentType(this.partName) : null;
        }

        void register(PackagePartCollection partList) throws InvalidFormatException {
            if (this.contentType == null) {
                throw new InvalidFormatException("The part " + this.partName.getURI().getPath() + " does not have any content type ! Rule: Package require content types when retrieving a part from a package. [M.1.14]");
            }
            if (partList.containsKey(this.partName)) {
                throw new InvalidFormatException("A part with the name '" + this.partName + "' already exists : Packages shall not contain equivalent part names and package implementers shall neither create nor recognize packages with equivalent part names. [M1.12]");
            }
            try {
                partList.put(this.partName, new ZipPackagePart(ZipPackage.this, this.zipArchiveEntry, this.partName, this.contentType, false));
            } catch (InvalidOperationException e) {
                throw new InvalidFormatException(e.getMessage(), e);
            }
        }

        @Override // java.lang.Comparable
        public int compareTo(EntryTriple o) {
            int contentTypeOrder1 = ContentTypes.RELATIONSHIPS_PART.equals(this.contentType) ? -1 : 1;
            int contentTypeOrder2 = ContentTypes.RELATIONSHIPS_PART.equals(o.contentType) ? -1 : 1;
            int cmpCT = Integer.compare(contentTypeOrder1, contentTypeOrder2);
            return cmpCT != 0 ? cmpCT : this.partName.compareTo(o.partName);
        }
    }

    @Override // org.apache.poi.openxml4j.opc.OPCPackage
    protected PackagePart createPartImpl(PackagePartName partName, String contentType, boolean loadRelationships) {
        if (contentType == null) {
            throw new IllegalArgumentException("contentType cannot be null");
        }
        if (partName == null) {
            throw new IllegalArgumentException("partName cannot be null");
        }
        try {
            if (useTempFilePackageParts) {
                if (encryptTempFilePackageParts) {
                    return new EncryptedTempFilePackagePart(this, partName, contentType, loadRelationships);
                }
                return new TempFilePackagePart(this, partName, contentType, loadRelationships);
            }
            return new MemoryPackagePart(this, partName, contentType, loadRelationships);
        } catch (Exception e) {
            LOG.atWarn().withThrowable(e).log("Failed to create part {}", partName);
            return null;
        }
    }

    @Override // org.apache.poi.openxml4j.opc.OPCPackage
    protected void flushImpl() {
    }

    @Override // org.apache.poi.openxml4j.opc.OPCPackage
    protected void closeImpl() throws IOException {
        try {
            flush();
            if (this.originalPackagePath == null || this.originalPackagePath.isEmpty()) {
                IOUtils.closeQuietly(this.zipArchive);
                return;
            }
            File targetFile = new File(this.originalPackagePath);
            if (!targetFile.exists()) {
                IOUtils.closeQuietly(this.zipArchive);
                throw new InvalidOperationException("Can't close a package not previously open with the open() method !");
            }
            try {
                String tempFileName = generateTempFileName(FileHelper.getDirectory(targetFile));
                File tempFile = TempFile.createTempFile(tempFileName, ".tmp");
                try {
                    save(tempFile);
                    IOUtils.closeQuietly(this.zipArchive);
                    if (1 != 0) {
                        try {
                            FileHelper.copyFile(tempFile, targetFile);
                        } finally {
                        }
                    }
                    closeParts();
                } catch (Throwable th) {
                    IOUtils.closeQuietly(this.zipArchive);
                    if (0 != 0) {
                        try {
                            FileHelper.copyFile(tempFile, targetFile);
                        } finally {
                            if (!tempFile.delete()) {
                                LOG.atWarn().log("The temporary file: '{}' cannot be deleted ! Make sure that no other application use it.", targetFile.getAbsolutePath());
                            }
                        }
                    }
                    if (!tempFile.delete()) {
                        LOG.atWarn().log("The temporary file: '{}' cannot be deleted ! Make sure that no other application use it.", targetFile.getAbsolutePath());
                    }
                    throw th;
                }
            } catch (IOException | Error | RuntimeException e) {
                IOUtils.closeQuietly(this.zipArchive);
                throw e;
            }
        } catch (Error | RuntimeException e2) {
            IOUtils.closeQuietly(this.zipArchive);
            throw e2;
        }
    }

    private synchronized String generateTempFileName(File directory) {
        File tmpFilename;
        do {
            tmpFilename = new File(directory.getAbsoluteFile() + File.separator + "OpenXML4J" + System.nanoTime());
        } while (tmpFilename.exists());
        return FileHelper.getFilename(tmpFilename.getAbsoluteFile());
    }

    @Override // org.apache.poi.openxml4j.opc.OPCPackage
    protected void revertImpl() {
        try {
            if (this.zipArchive != null) {
                this.zipArchive.close();
            }
        } catch (IOException e) {
        }
    }

    @Override // org.apache.poi.openxml4j.opc.OPCPackage
    public void saveImpl(OutputStream outputStream) {
        throwExceptionIfReadOnly();
        ZipArchiveOutputStream zos = outputStream instanceof ZipArchiveOutputStream ? (ZipArchiveOutputStream) outputStream : new ZipArchiveOutputStream(outputStream);
        try {
            if (getPartsByRelationshipType(PackageRelationshipTypes.CORE_PROPERTIES).isEmpty() && getPartsByRelationshipType(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376).isEmpty()) {
                LOG.atDebug().log("Save core properties part");
                getPackageProperties();
                if (!hasPackagePart(this.packageProperties)) {
                    addPackagePart(this.packageProperties);
                }
                this.relationships.addRelationship(this.packageProperties.getPartName().getURI(), TargetMode.INTERNAL, PackageRelationshipTypes.CORE_PROPERTIES, null);
                if (!this.contentTypeManager.isContentTypeRegister(ContentTypes.CORE_PROPERTIES_PART)) {
                    this.contentTypeManager.addContentType(this.packageProperties.getPartName(), ContentTypes.CORE_PROPERTIES_PART);
                }
            }
            LOG.atDebug().log("Save content types part");
            if (!this.contentTypeManager.save(zos)) {
                throw new OpenXML4JRuntimeException("Failed to save: content types part");
            }
            LOG.atDebug().log("Save package relationships");
            if (!ZipPartMarshaller.marshallRelationshipPart(getRelationships(), PackagingURIHelper.PACKAGE_RELATIONSHIPS_ROOT_PART_NAME, zos)) {
                throw new OpenXML4JRuntimeException("Failed to save: package relationships part");
            }
            Iterator<PackagePart> it = getParts().iterator();
            while (it.hasNext()) {
                PackagePart part = it.next();
                if (!part.isRelationshipPart()) {
                    final PackagePartName ppn = part.getPartName();
                    LOG.atDebug().log(new Supplier() { // from class: org.apache.poi.openxml4j.opc.ZipPackage$$ExternalSyntheticLambda3
                        @Override // org.apache.logging.log4j.util.Supplier, java.util.function.Supplier
                        public final Object get() {
                            return ZipPackage.lambda$saveImpl$3(PackagePartName.this);
                        }
                    });
                    PartMarshaller marshaller = this.partMarshallers.get(part._contentType);
                    PartMarshaller pm = marshaller != null ? marshaller : this.defaultPartMarshaller;
                    if (!pm.marshall(part, zos)) {
                        String errMsg = "The part " + ppn.getURI() + " failed to be saved in the stream with marshaller " + pm + ". Enable logging via Log4j 2 for more details.";
                        throw new OpenXML4JException(errMsg);
                    }
                }
            }
            zos.finish();
        } catch (OpenXML4JRuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new OpenXML4JRuntimeException("Fail to save: an error occurs while saving the package : " + e2.getMessage(), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Message lambda$saveImpl$3(PackagePartName ppn) {
        return new SimpleMessage("Save part '" + ZipHelper.getZipItemNameFromOPCName(ppn.getName()) + "'");
    }

    public ZipEntrySource getZipArchive() {
        return this.zipArchive;
    }

    @Override // org.apache.poi.openxml4j.opc.OPCPackage
    public boolean isClosed() {
        return this.zipArchive != null && this.zipArchive.isClosed();
    }
}
