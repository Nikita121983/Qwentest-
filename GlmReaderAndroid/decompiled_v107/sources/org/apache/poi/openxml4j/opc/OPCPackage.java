package org.apache.poi.openxml4j.opc;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;
import org.apache.poi.openxml4j.exceptions.PartAlreadyExistsException;
import org.apache.poi.openxml4j.opc.internal.ContentType;
import org.apache.poi.openxml4j.opc.internal.ContentTypeManager;
import org.apache.poi.openxml4j.opc.internal.InvalidZipException;
import org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart;
import org.apache.poi.openxml4j.opc.internal.PartMarshaller;
import org.apache.poi.openxml4j.opc.internal.PartUnmarshaller;
import org.apache.poi.openxml4j.opc.internal.ZipContentTypeManager;
import org.apache.poi.openxml4j.opc.internal.marshallers.DefaultMarshaller;
import org.apache.poi.openxml4j.opc.internal.marshallers.ZipPackagePropertiesMarshaller;
import org.apache.poi.openxml4j.opc.internal.unmarshallers.PackagePropertiesUnmarshaller;
import org.apache.poi.openxml4j.opc.internal.unmarshallers.UnmarshallContext;
import org.apache.poi.openxml4j.util.ZipEntrySource;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public abstract class OPCPackage implements RelationshipSource, Closeable {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) OPCPackage.class);
    protected static final PackageAccess defaultPackageAccess = PackageAccess.READ_WRITE;
    protected ContentTypeManager contentTypeManager;
    protected final PartMarshaller defaultPartMarshaller;
    protected boolean isDirty;
    protected String originalPackagePath;
    protected OutputStream output;
    private final PackageAccess packageAccess;
    protected PackagePropertiesPart packageProperties;
    private PackagePartCollection partList;
    protected final Map<ContentType, PartMarshaller> partMarshallers;
    protected final Map<ContentType, PartUnmarshaller> partUnmarshallers;
    protected PackageRelationshipCollection relationships;

    protected abstract void closeImpl() throws IOException;

    protected abstract PackagePart createPartImpl(PackagePartName packagePartName, String str, boolean z);

    protected abstract void flushImpl();

    protected abstract PackagePartCollection getPartsImpl() throws InvalidFormatException;

    public abstract boolean isClosed();

    protected abstract void revertImpl();

    protected abstract void saveImpl(OutputStream outputStream) throws IOException;

    OPCPackage(PackageAccess access) {
        this(access, OPCComplianceFlags.enforceAll());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OPCPackage(PackageAccess access, OPCComplianceFlags opcComplianceFlags) {
        this.partMarshallers = new HashMap(5);
        this.defaultPartMarshaller = new DefaultMarshaller();
        this.partUnmarshallers = new HashMap(2);
        if (getClass() != ZipPackage.class) {
            throw new IllegalArgumentException("PackageBase may not be subclassed");
        }
        this.packageAccess = access;
        ContentType contentType = newCorePropertiesPart();
        this.partUnmarshallers.put(contentType, new PackagePropertiesUnmarshaller(opcComplianceFlags));
        this.partMarshallers.put(contentType, new ZipPackagePropertiesMarshaller());
    }

    private static ContentType newCorePropertiesPart() {
        try {
            return new ContentType(ContentTypes.CORE_PROPERTIES_PART);
        } catch (InvalidFormatException e) {
            throw new OpenXML4JRuntimeException("Package.init() : this exception should never happen, if you read this message please send a mail to the developers team. : " + e.getMessage(), e);
        }
    }

    public static OPCPackage open(String path) throws InvalidFormatException {
        return open(path, defaultPackageAccess, OPCComplianceFlags.enforceAll());
    }

    public static OPCPackage open(String path, OPCComplianceFlags opcComplianceFlags) throws InvalidFormatException {
        return open(path, defaultPackageAccess, opcComplianceFlags);
    }

    public static OPCPackage open(File file) throws InvalidFormatException {
        return open(file, defaultPackageAccess);
    }

    public static OPCPackage open(File file, OPCComplianceFlags opcComplianceFlags) throws InvalidFormatException {
        return open(file, defaultPackageAccess, opcComplianceFlags);
    }

    public static OPCPackage open(ZipEntrySource zipEntry) throws InvalidFormatException {
        return open(zipEntry, OPCComplianceFlags.enforceAll());
    }

    public static OPCPackage open(ZipEntrySource zipEntry, OPCComplianceFlags opcComplianceFlags) throws InvalidFormatException {
        OPCPackage pack = new ZipPackage(zipEntry, PackageAccess.READ, opcComplianceFlags);
        try {
            if (pack.partList == null) {
                pack.getParts();
            }
            return pack;
        } catch (RuntimeException | InvalidFormatException e) {
            pack.revert();
            throw e;
        }
    }

    public static OPCPackage open(String path, PackageAccess access) throws InvalidFormatException, InvalidOperationException {
        return open(path, access, OPCComplianceFlags.enforceAll());
    }

    public static OPCPackage open(String path, PackageAccess access, OPCComplianceFlags opcComplianceFlags) throws InvalidFormatException, InvalidOperationException {
        if (StringUtil.isBlank(path)) {
            throw new IllegalArgumentException("'path' must be given");
        }
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            throw new IllegalArgumentException("path must not be a directory");
        }
        OPCPackage pack = new ZipPackage(path, access, opcComplianceFlags);
        boolean success = false;
        if (pack.partList == null && access != PackageAccess.WRITE) {
            try {
                pack.getParts();
                success = true;
            } finally {
                if (!success) {
                    IOUtils.closeQuietly(pack);
                }
            }
        }
        pack.originalPackagePath = new File(path).getAbsolutePath();
        return pack;
    }

    public static OPCPackage open(File file, PackageAccess access) throws InvalidFormatException {
        return open(file, access, OPCComplianceFlags.enforceAll());
    }

    public static OPCPackage open(File file, PackageAccess access, OPCComplianceFlags opcComplianceFlags) throws InvalidFormatException {
        if (file == null) {
            throw new IllegalArgumentException("'file' must be given");
        }
        if (file.exists() && file.isDirectory()) {
            throw new IllegalArgumentException("file must not be a directory");
        }
        try {
            OPCPackage pack = new ZipPackage(file, access, opcComplianceFlags);
            try {
                if (pack.partList == null && access != PackageAccess.WRITE) {
                    pack.getParts();
                }
                pack.originalPackagePath = file.getAbsolutePath();
                return pack;
            } catch (RuntimeException | InvalidFormatException e) {
                if (access == PackageAccess.READ) {
                    pack.revert();
                } else {
                    IOUtils.closeQuietly(pack);
                }
                throw e;
            }
        } catch (InvalidOperationException e2) {
            throw new InvalidFormatException(e2.getMessage(), e2);
        }
    }

    public static OPCPackage open(InputStream in) throws InvalidFormatException, IOException {
        return open(in, OPCComplianceFlags.enforceAll());
    }

    public static OPCPackage open(InputStream in, OPCComplianceFlags opcComplianceFlags) throws InvalidFormatException, IOException {
        try {
            OPCPackage pack = new ZipPackage(in, PackageAccess.READ_WRITE, opcComplianceFlags);
            try {
                if (pack.partList == null) {
                    pack.getParts();
                }
                return pack;
            } catch (RuntimeException | InvalidFormatException e) {
                IOUtils.closeQuietly(pack);
                throw e;
            }
        } catch (InvalidZipException e2) {
            throw new InvalidFormatException(e2.getMessage(), e2);
        }
    }

    public static OPCPackage open(InputStream in, boolean closeStream) throws InvalidFormatException, IOException {
        return open(in, closeStream, OPCComplianceFlags.enforceAll());
    }

    public static OPCPackage open(InputStream in, boolean closeStream, OPCComplianceFlags opcComplianceFlags) throws InvalidFormatException, IOException {
        try {
            OPCPackage pack = new ZipPackage(in, PackageAccess.READ_WRITE, closeStream, opcComplianceFlags);
            try {
                if (pack.partList == null) {
                    pack.getParts();
                }
                return pack;
            } catch (RuntimeException | InvalidFormatException e) {
                IOUtils.closeQuietly(pack);
                throw e;
            }
        } catch (InvalidZipException e2) {
            throw new InvalidFormatException(e2.getMessage(), e2);
        }
    }

    public static OPCPackage openOrCreate(File file) throws InvalidFormatException {
        if (file.exists()) {
            return open(file.getAbsolutePath());
        }
        return create(file);
    }

    public static OPCPackage create(String path) {
        return create(new File(path));
    }

    public static OPCPackage create(File file) {
        if (file == null || (file.exists() && file.isDirectory())) {
            throw new IllegalArgumentException("file");
        }
        if (file.exists()) {
            throw new InvalidOperationException("This package (or file) already exists : use the open() method or delete the file.");
        }
        OPCPackage pkg = new ZipPackage();
        pkg.originalPackagePath = file.getAbsolutePath();
        configurePackage(pkg);
        return pkg;
    }

    public static OPCPackage create(OutputStream output) {
        OPCPackage pkg = new ZipPackage();
        pkg.originalPackagePath = null;
        pkg.output = output;
        configurePackage(pkg);
        return pkg;
    }

    private static void configurePackage(OPCPackage pkg) {
        try {
            pkg.contentTypeManager = new ZipContentTypeManager(null, pkg);
            pkg.contentTypeManager.addContentType(PackagingURIHelper.createPartName(PackagingURIHelper.PACKAGE_RELATIONSHIPS_ROOT_URI), ContentTypes.RELATIONSHIPS_PART);
            pkg.contentTypeManager.addContentType(PackagingURIHelper.createPartName("/default.xml"), ContentTypes.PLAIN_OLD_XML);
            pkg.packageProperties = new PackagePropertiesPart(pkg, PackagingURIHelper.CORE_PROPERTIES_PART_NAME);
            pkg.packageProperties.setCreatorProperty("Generated by Apache POI OpenXML4J");
            pkg.packageProperties.setCreatedProperty(Optional.of(new Date()));
        } catch (InvalidFormatException e) {
            throw new IllegalStateException(e);
        }
    }

    public void flush() {
        throwExceptionIfReadOnly();
        if (this.packageProperties != null) {
            this.packageProperties.flush();
        }
        flushImpl();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (isClosed()) {
            return;
        }
        if (this.packageAccess == PackageAccess.READ) {
            LOG.atDebug().log("The close() method is intended to SAVE a package. This package is open in READ ONLY mode, use the revert() method instead!");
            revert();
            return;
        }
        if (this.contentTypeManager == null) {
            LOG.atWarn().log("Unable to call close() on a package that hasn't been fully opened yet");
            revert();
            return;
        }
        if (StringUtil.isNotBlank(this.originalPackagePath)) {
            File targetFile = new File(this.originalPackagePath);
            if (!targetFile.exists() || !this.originalPackagePath.equalsIgnoreCase(targetFile.getAbsolutePath())) {
                save(targetFile);
            } else {
                closeImpl();
            }
        } else if (this.output != null) {
            try {
                save(this.output);
            } finally {
                this.output.close();
            }
        }
        revert();
        closeParts();
        this.contentTypeManager.clearAll();
    }

    public void revert() {
        revertImpl();
    }

    public void addThumbnail(String path) throws IOException {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("path");
        }
        String name = path.substring(path.lastIndexOf(File.separatorChar) + 1);
        InputStream is = Files.newInputStream(Paths.get(path, new String[0]), new OpenOption[0]);
        try {
            addThumbnail(name, is);
            if (is != null) {
                is.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public void addThumbnail(String filename, InputStream data) throws IOException {
        PackagePartName thumbnailPartName;
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("filename");
        }
        String contentType = ContentTypes.getContentTypeFromFileExtension(filename);
        try {
            thumbnailPartName = PackagingURIHelper.createPartName("/docProps/" + filename);
        } catch (InvalidFormatException e) {
            String partName = "/docProps/thumbnail" + filename.substring(filename.lastIndexOf(46) + 1);
            try {
                PackagePartName thumbnailPartName2 = PackagingURIHelper.createPartName(partName);
                thumbnailPartName = thumbnailPartName2;
            } catch (InvalidFormatException e2) {
                throw new InvalidOperationException("Can't add a thumbnail file named '" + filename + "'", e2);
            }
        }
        if (getPart(thumbnailPartName) != null) {
            throw new InvalidOperationException("You already add a thumbnail named '" + filename + "'");
        }
        PackagePart thumbnailPart = createPart(thumbnailPartName, contentType, false);
        addRelationship(thumbnailPartName, TargetMode.INTERNAL, PackageRelationshipTypes.THUMBNAIL);
        StreamHelper.copyStream(data, thumbnailPart.getOutputStream());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void throwExceptionIfReadOnly() throws InvalidOperationException {
        if (this.packageAccess == PackageAccess.READ) {
            throw new InvalidOperationException("Operation not allowed, document open in read only mode!");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void throwExceptionIfWriteOnly() throws InvalidOperationException {
        if (this.packageAccess == PackageAccess.WRITE) {
            throw new InvalidOperationException("Operation not allowed, document open in write only mode!");
        }
    }

    public PackageProperties getPackageProperties() throws InvalidFormatException {
        throwExceptionIfWriteOnly();
        if (this.packageProperties == null) {
            this.packageProperties = new PackagePropertiesPart(this, PackagingURIHelper.CORE_PROPERTIES_PART_NAME);
        }
        return this.packageProperties;
    }

    public PackagePart getPart(PackagePartName partName) {
        throwExceptionIfWriteOnly();
        if (partName == null) {
            throw new IllegalArgumentException("partName");
        }
        if (this.partList == null) {
            try {
                getParts();
            } catch (InvalidFormatException e) {
                return null;
            }
        }
        return this.partList.get(partName);
    }

    public ArrayList<PackagePart> getPartsByContentType(String contentType) {
        ArrayList<PackagePart> retArr = new ArrayList<>();
        for (PackagePart part : this.partList.sortedValues()) {
            if (part.getContentType().equals(contentType)) {
                retArr.add(part);
            }
        }
        return retArr;
    }

    public ArrayList<PackagePart> getPartsByRelationshipType(String relationshipType) {
        if (relationshipType == null) {
            throw new IllegalArgumentException("relationshipType");
        }
        ArrayList<PackagePart> retArr = new ArrayList<>();
        Iterator<PackageRelationship> it = getRelationshipsByType(relationshipType).iterator();
        while (it.hasNext()) {
            PackageRelationship rel = it.next();
            PackagePart part = getPart(rel);
            if (part != null) {
                retArr.add(part);
            }
        }
        Collections.sort(retArr);
        return retArr;
    }

    public List<PackagePart> getPartsByName(Pattern namePattern) {
        if (namePattern == null) {
            throw new IllegalArgumentException("name pattern must not be null");
        }
        Matcher matcher = namePattern.matcher("");
        ArrayList<PackagePart> result = new ArrayList<>();
        for (PackagePart part : this.partList.sortedValues()) {
            PackagePartName partName = part.getPartName();
            if (matcher.reset(partName.getName()).matches()) {
                result.add(part);
            }
        }
        return result;
    }

    public PackagePart getPart(PackageRelationship partRel) {
        ensureRelationships();
        Iterator<PackageRelationship> it = this.relationships.iterator();
        while (it.hasNext()) {
            PackageRelationship rel = it.next();
            if (rel.getRelationshipType().equals(partRel.getRelationshipType())) {
                try {
                    PackagePart retPart = getPart(PackagingURIHelper.createPartName(rel.getTargetURI()));
                    return retPart;
                } catch (InvalidFormatException e) {
                }
            }
        }
        return null;
    }

    public ArrayList<PackagePart> getParts() throws InvalidFormatException {
        throwExceptionIfWriteOnly();
        if (this.partList == null) {
            boolean hasCorePropertiesPart = false;
            boolean needCorePropertiesPart = true;
            this.partList = getPartsImpl();
            Iterator it = new ArrayList(this.partList.sortedValues()).iterator();
            while (it.hasNext()) {
                PackagePart part = (PackagePart) it.next();
                part.loadRelationships();
                if (ContentTypes.CORE_PROPERTIES_PART.equals(part.getContentType())) {
                    if (!hasCorePropertiesPart) {
                        hasCorePropertiesPart = true;
                    } else {
                        LOG.atWarn().log("OPC Compliance error [M4.1]: there is more than one core properties relationship in the package! POI will use only the first, but other software may reject this file.");
                    }
                }
                PartUnmarshaller partUnmarshaller = this.partUnmarshallers.get(part._contentType);
                if (partUnmarshaller != null) {
                    UnmarshallContext context = new UnmarshallContext(this, part._partName);
                    try {
                        InputStream partStream = part.getInputStream();
                        try {
                            PackagePart unmarshallPart = partUnmarshaller.unmarshall(context, partStream);
                            this.partList.remove(part.getPartName());
                            this.partList.put(unmarshallPart._partName, unmarshallPart);
                            if ((unmarshallPart instanceof PackagePropertiesPart) && hasCorePropertiesPart && needCorePropertiesPart) {
                                this.packageProperties = (PackagePropertiesPart) unmarshallPart;
                                needCorePropertiesPart = false;
                            }
                            if (partStream != null) {
                                partStream.close();
                            }
                        } catch (Throwable th) {
                            try {
                                throw th;
                                break;
                            } catch (Throwable th2) {
                                if (partStream != null) {
                                    try {
                                        partStream.close();
                                    } catch (Throwable th3) {
                                        th.addSuppressed(th3);
                                    }
                                }
                                throw th2;
                                break;
                            }
                        }
                    } catch (IOException e) {
                        LOG.atWarn().log("Unmarshall operation : IOException for {}", part._partName);
                    } catch (InvalidOperationException invoe) {
                        throw new InvalidFormatException(invoe.getMessage(), invoe);
                    }
                }
            }
        }
        return new ArrayList<>(this.partList.sortedValues());
    }

    public PackagePart createPart(PackagePartName partName, String contentType) {
        return createPart(partName, contentType, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PackagePart createPart(PackagePartName partName, String contentType, boolean loadRelationships) {
        throwExceptionIfReadOnly();
        if (partName == null) {
            throw new IllegalArgumentException("partName");
        }
        if (contentType == null || contentType.isEmpty()) {
            throw new IllegalArgumentException("contentType");
        }
        if (this.partList.containsKey(partName) && !this.partList.get(partName).isDeleted()) {
            throw new PartAlreadyExistsException("A part with the name '" + partName.getName() + "' already exists : Packages shall not contain equivalent part names and package implementers shall neither create nor recognize packages with equivalent part names. [M1.12]");
        }
        if (contentType.equals(ContentTypes.CORE_PROPERTIES_PART) && this.packageProperties != null) {
            throw new InvalidOperationException("OPC Compliance error [M4.1]: you try to add more than one core properties relationship in the package !");
        }
        PackagePart part = createPartImpl(partName, contentType, loadRelationships);
        try {
            PackagePartName ppn = PackagingURIHelper.createPartName("/.xml");
            this.contentTypeManager.addContentType(ppn, ContentTypes.PLAIN_OLD_XML);
            PackagePartName ppn2 = PackagingURIHelper.createPartName("/.rels");
            this.contentTypeManager.addContentType(ppn2, ContentTypes.RELATIONSHIPS_PART);
            this.contentTypeManager.addContentType(partName, contentType);
            this.partList.put(partName, part);
            this.isDirty = true;
            return part;
        } catch (InvalidFormatException e) {
            throw new InvalidOperationException("unable to create default content-type entries.", e);
        }
    }

    public PackagePart createPart(PackagePartName partName, String contentType, ByteArrayOutputStream content) {
        PackagePart addedPart = createPart(partName, contentType);
        if (addedPart == null || content == null) {
            return null;
        }
        try {
            OutputStream partOutput = addedPart.getOutputStream();
            if (partOutput != null) {
                try {
                    content.writeTo(partOutput);
                    if (partOutput != null) {
                        partOutput.close();
                    }
                    return addedPart;
                } finally {
                }
            } else {
                if (partOutput != null) {
                    partOutput.close();
                }
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PackagePart addPackagePart(PackagePart part) {
        throwExceptionIfReadOnly();
        if (part == null) {
            throw new IllegalArgumentException("part");
        }
        if (hasPackagePart(part)) {
            if (!this.partList.get(part._partName).isDeleted()) {
                throw new InvalidOperationException("A part with the name '" + part._partName.getName() + "' already exists : Packages shall not contain equivalent part names and package implementers shall neither create nor recognize packages with equivalent part names. [M1.12]");
            }
            part.setDeleted(false);
            this.partList.remove(part._partName);
        }
        this.partList.put(part._partName, part);
        this.isDirty = true;
        return part;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasPackagePart(PackagePart part) {
        return this.partList.containsKey(part._partName);
    }

    public void removePart(PackagePart part) {
        if (part != null) {
            removePart(part.getPartName());
        }
    }

    public void removePart(PackagePartName partName) {
        PackagePart part;
        throwExceptionIfReadOnly();
        if (partName == null || !containPart(partName)) {
            throw new IllegalArgumentException("partName");
        }
        if (this.partList.containsKey(partName)) {
            this.partList.get(partName).setDeleted(true);
            removePartImpl(partName);
        } else {
            removePartImpl(partName);
        }
        this.contentTypeManager.removeContentType(partName);
        if (partName.isRelationshipPartURI()) {
            URI sourceURI = PackagingURIHelper.getSourcePartUriFromRelationshipPartUri(partName.getURI());
            try {
                PackagePartName sourcePartName = PackagingURIHelper.createPartName(sourceURI);
                if (sourcePartName.getURI().equals(PackagingURIHelper.PACKAGE_ROOT_URI)) {
                    clearRelationships();
                } else if (containPart(sourcePartName) && (part = getPart(sourcePartName)) != null) {
                    part.clearRelationships();
                }
            } catch (InvalidFormatException e) {
                LOG.atError().log("Part name URI '{}' is not valid! This message is not intended to be displayed!", sourceURI);
                return;
            }
        }
        this.isDirty = true;
    }

    public void removePartRecursive(PackagePartName partName) throws InvalidFormatException {
        PackagePart relPart = this.partList.get(PackagingURIHelper.getRelationshipPartName(partName));
        PackagePart partToRemove = this.partList.get(partName);
        if (relPart != null) {
            PackageRelationshipCollection partRels = new PackageRelationshipCollection(partToRemove);
            Iterator<PackageRelationship> it = partRels.iterator();
            while (it.hasNext()) {
                PackageRelationship rel = it.next();
                PackagePartName partNameToRemove = PackagingURIHelper.createPartName(PackagingURIHelper.resolvePartUri(rel.getSourceURI(), rel.getTargetURI()));
                removePart(partNameToRemove);
            }
            removePart(relPart._partName);
        }
        removePart(partToRemove._partName);
    }

    public void deletePart(PackagePartName partName) {
        if (partName == null) {
            throw new IllegalArgumentException("partName");
        }
        removePart(partName);
        removePart(PackagingURIHelper.getRelationshipPartName(partName));
    }

    public void deletePartRecursive(PackagePartName partName) {
        if (partName == null || !containPart(partName)) {
            throw new IllegalArgumentException("partName");
        }
        PackagePart partToDelete = getPart(partName);
        removePart(partName);
        try {
            Iterator<PackageRelationship> it = partToDelete.getRelationships().iterator();
            while (it.hasNext()) {
                PackageRelationship relationship = it.next();
                PackagePartName targetPartName = PackagingURIHelper.createPartName(PackagingURIHelper.resolvePartUri(partName.getURI(), relationship.getTargetURI()));
                deletePartRecursive(targetPartName);
            }
            PackagePartName relationshipPartName = PackagingURIHelper.getRelationshipPartName(partName);
            if (relationshipPartName != null && containPart(relationshipPartName)) {
                removePart(relationshipPartName);
            }
        } catch (InvalidFormatException e) {
            LOG.atWarn().withThrowable(e).log("An exception occurs while deleting part '{}'. Some parts may remain in the package.", partName.getName());
        }
    }

    public boolean containPart(PackagePartName partName) {
        return getPart(partName) != null;
    }

    @Override // org.apache.poi.openxml4j.opc.RelationshipSource
    public PackageRelationship addRelationship(PackagePartName targetPartName, TargetMode targetMode, String relationshipType, String relID) {
        if (relationshipType.equals(PackageRelationshipTypes.CORE_PROPERTIES) && this.packageProperties != null) {
            throw new InvalidOperationException("OPC Compliance error [M4.1]: can't add another core properties part ! Use the built-in package method instead.");
        }
        if (targetPartName.isRelationshipPartURI()) {
            throw new InvalidOperationException("Rule M1.25: The Relationships part shall not have relationships to any other part.");
        }
        ensureRelationships();
        PackageRelationship retRel = this.relationships.addRelationship(targetPartName.getURI(), targetMode, relationshipType, relID);
        this.isDirty = true;
        return retRel;
    }

    @Override // org.apache.poi.openxml4j.opc.RelationshipSource
    public PackageRelationship addRelationship(PackagePartName targetPartName, TargetMode targetMode, String relationshipType) {
        return addRelationship(targetPartName, targetMode, relationshipType, null);
    }

    @Override // org.apache.poi.openxml4j.opc.RelationshipSource
    public PackageRelationship addExternalRelationship(String target, String relationshipType) {
        return addExternalRelationship(target, relationshipType, null);
    }

    @Override // org.apache.poi.openxml4j.opc.RelationshipSource
    public PackageRelationship addExternalRelationship(String target, String relationshipType, String id) {
        if (target == null) {
            throw new IllegalArgumentException(TypedValues.AttributesType.S_TARGET);
        }
        if (relationshipType == null) {
            throw new IllegalArgumentException("relationshipType");
        }
        try {
            URI targetURI = new URI(target);
            ensureRelationships();
            PackageRelationship retRel = this.relationships.addRelationship(targetURI, TargetMode.EXTERNAL, relationshipType, id);
            this.isDirty = true;
            return retRel;
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid target - " + e);
        }
    }

    @Override // org.apache.poi.openxml4j.opc.RelationshipSource
    public void removeRelationship(String id) {
        if (this.relationships != null) {
            this.relationships.removeRelationship(id);
            this.isDirty = true;
        }
    }

    @Override // org.apache.poi.openxml4j.opc.RelationshipSource
    public PackageRelationshipCollection getRelationships() {
        return getRelationshipsHelper(null);
    }

    @Override // org.apache.poi.openxml4j.opc.RelationshipSource
    public PackageRelationshipCollection getRelationshipsByType(String relationshipType) {
        throwExceptionIfWriteOnly();
        if (relationshipType == null) {
            throw new IllegalArgumentException("relationshipType");
        }
        return getRelationshipsHelper(relationshipType);
    }

    private PackageRelationshipCollection getRelationshipsHelper(String id) {
        throwExceptionIfWriteOnly();
        ensureRelationships();
        return this.relationships.getRelationships(id);
    }

    @Override // org.apache.poi.openxml4j.opc.RelationshipSource
    public void clearRelationships() {
        if (this.relationships != null) {
            this.relationships.clear();
            this.isDirty = true;
        }
    }

    public void ensureRelationships() {
        if (this.relationships == null) {
            try {
                this.relationships = new PackageRelationshipCollection(this);
            } catch (InvalidFormatException e) {
                this.relationships = new PackageRelationshipCollection();
            }
        }
    }

    @Override // org.apache.poi.openxml4j.opc.RelationshipSource
    public PackageRelationship getRelationship(String id) {
        return this.relationships.getRelationshipByID(id);
    }

    @Override // org.apache.poi.openxml4j.opc.RelationshipSource
    public boolean hasRelationships() {
        return !this.relationships.isEmpty();
    }

    @Override // org.apache.poi.openxml4j.opc.RelationshipSource
    public boolean isRelationshipExists(PackageRelationship rel) {
        Iterator<PackageRelationship> it = this.relationships.iterator();
        while (it.hasNext()) {
            PackageRelationship r = it.next();
            if (r == rel) {
                return true;
            }
        }
        return false;
    }

    public void addMarshaller(String contentType, PartMarshaller marshaller) {
        try {
            this.partMarshallers.put(new ContentType(contentType), marshaller);
        } catch (InvalidFormatException e) {
            LOG.atWarn().log("The specified content type is not valid: '{}'. The marshaller will not be added !", e.getMessage());
        }
    }

    public void addUnmarshaller(String contentType, PartUnmarshaller unmarshaller) {
        try {
            this.partUnmarshallers.put(new ContentType(contentType), unmarshaller);
        } catch (InvalidFormatException e) {
            LOG.atWarn().log("The specified content type is not valid: '{}'. The unmarshaller will not be added !", e.getMessage());
        }
    }

    public void removeMarshaller(String contentType) {
        try {
            this.partMarshallers.remove(new ContentType(contentType));
        } catch (InvalidFormatException e) {
            throw new IllegalStateException(e);
        }
    }

    public void removeUnmarshaller(String contentType) {
        try {
            this.partUnmarshallers.remove(new ContentType(contentType));
        } catch (InvalidFormatException e) {
            throw new IllegalStateException(e);
        }
    }

    public PackageAccess getPackageAccess() {
        return this.packageAccess;
    }

    @NotImplemented
    public boolean validatePackage(OPCPackage pkg) throws InvalidFormatException {
        throw new InvalidOperationException("Not implemented yet !!!");
    }

    public void save(File targetFile) throws IOException {
        if (targetFile == null) {
            throw new IllegalArgumentException("targetFile");
        }
        throwExceptionIfReadOnly();
        if (targetFile.exists() && targetFile.getAbsolutePath().equals(this.originalPackagePath)) {
            throw new InvalidOperationException("You can't call save(File) to save to the currently open file. To save to the current file, please just call close()");
        }
        OutputStream fos = Files.newOutputStream(targetFile.toPath(), new OpenOption[0]);
        try {
            save(fos);
            if (fos != null) {
                fos.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public void save(OutputStream outputStream) throws IOException {
        throwExceptionIfReadOnly();
        saveImpl(outputStream);
    }

    protected void removePartImpl(PackagePartName partName) {
        if (partName == null) {
            throw new IllegalArgumentException("partName cannot be null");
        }
        throwExceptionIfReadOnly();
        this.partList.remove(partName);
    }

    public boolean replaceContentType(String oldContentType, String newContentType) {
        boolean success = false;
        ArrayList<PackagePart> list = getPartsByContentType(oldContentType);
        Iterator<PackagePart> it = list.iterator();
        while (it.hasNext()) {
            PackagePart packagePart = it.next();
            if (packagePart.getContentType().equals(oldContentType)) {
                PackagePartName partName = packagePart.getPartName();
                this.contentTypeManager.addContentType(partName, newContentType);
                try {
                    packagePart.setContentType(newContentType);
                    success = true;
                    this.isDirty = true;
                } catch (InvalidFormatException e) {
                    throw new OpenXML4JRuntimeException("invalid content type - " + newContentType, e);
                }
            }
        }
        return success;
    }

    public void registerPartAndContentType(PackagePart part) {
        addPackagePart(part);
        this.contentTypeManager.addContentType(part.getPartName(), part.getContentType());
        this.isDirty = true;
    }

    public void unregisterPartAndContentType(PackagePartName partName) {
        removePart(partName);
        this.contentTypeManager.removeContentType(partName);
        this.isDirty = true;
    }

    public int getUnusedPartIndex(String nameTemplate) throws InvalidFormatException {
        return this.partList.getUnusedPartIndex(nameTemplate);
    }

    public boolean isStrictOoxmlFormat() {
        PackageRelationshipCollection coreDocRelationships = getRelationshipsByType(PackageRelationshipTypes.STRICT_CORE_DOCUMENT);
        return !coreDocRelationships.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void closeParts() {
        this.partList.closeParts();
    }

    public String toString() {
        return "OPCPackage{packageAccess=" + this.packageAccess + ", relationships=" + this.relationships + ", packageProperties=" + this.packageProperties + ", isDirty=" + this.isDirty + '}';
    }
}
