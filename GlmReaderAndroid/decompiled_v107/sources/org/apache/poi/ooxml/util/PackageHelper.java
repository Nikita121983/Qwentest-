package org.apache.poi.ooxml.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Iterator;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageProperties;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.openxml4j.opc.internal.InvalidZipException;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Removal;

/* loaded from: classes10.dex */
public final class PackageHelper {
    public static OPCPackage open(InputStream stream) throws IOException {
        return open(stream, true);
    }

    public static OPCPackage open(InputStream stream, boolean closeStream) throws IOException {
        try {
            try {
                return OPCPackage.open(stream, closeStream);
            } catch (InvalidFormatException e) {
                Throwable cause = e.getCause();
                if (cause instanceof InvalidZipException) {
                    throw ((InvalidZipException) cause);
                }
                throw new POIXMLException(e);
            }
        } finally {
            if (closeStream) {
                stream.close();
            }
        }
    }

    @Removal(version = "6.0.0")
    @Deprecated
    public static OPCPackage clone(OPCPackage pkg, File file) throws OpenXML4JException, IOException {
        String path = file.getAbsolutePath();
        OPCPackage dest = OPCPackage.create(path);
        try {
            PackageRelationshipCollection rels = pkg.getRelationships();
            Iterator<PackageRelationship> it = rels.iterator();
            while (it.hasNext()) {
                PackageRelationship rel = it.next();
                PackagePart part = pkg.getPart(rel);
                if (rel.getRelationshipType().equals(PackageRelationshipTypes.CORE_PROPERTIES)) {
                    copyProperties(pkg.getPackageProperties(), dest.getPackageProperties());
                } else {
                    dest.addRelationship(part.getPartName(), rel.getTargetMode(), rel.getRelationshipType());
                    PackagePart part_tgt = dest.createPart(part.getPartName(), part.getContentType());
                    InputStream in = part.getInputStream();
                    try {
                        OutputStream out = part_tgt.getOutputStream();
                        try {
                            IOUtils.copy(in, out);
                            if (out != null) {
                                out.close();
                            }
                            if (in != null) {
                                in.close();
                            }
                            if (part.hasRelationships()) {
                                copy(pkg, part, dest, part_tgt);
                            }
                        } finally {
                        }
                    } finally {
                    }
                }
            }
            if (dest != null) {
                dest.close();
            }
            new File(path).deleteOnExit();
            return OPCPackage.open(path);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (dest != null) {
                    try {
                        dest.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private static void copy(OPCPackage pkg, PackagePart part, OPCPackage tgt, PackagePart part_tgt) throws OpenXML4JException, IOException {
        PackageRelationshipCollection rels = part.getRelationships();
        if (rels != null) {
            Iterator<PackageRelationship> it = rels.iterator();
            while (it.hasNext()) {
                PackageRelationship rel = it.next();
                if (rel.getTargetMode() == TargetMode.EXTERNAL) {
                    part_tgt.addExternalRelationship(rel.getTargetURI().toString(), rel.getRelationshipType(), rel.getId());
                } else {
                    URI uri = rel.getTargetURI();
                    if (uri.getRawFragment() != null) {
                        part_tgt.addRelationship(uri, rel.getTargetMode(), rel.getRelationshipType(), rel.getId());
                    } else {
                        PackagePartName relName = PackagingURIHelper.createPartName(rel.getTargetURI());
                        PackagePart p = pkg.getPart(relName);
                        part_tgt.addRelationship(p.getPartName(), rel.getTargetMode(), rel.getRelationshipType(), rel.getId());
                        if (tgt.containPart(p.getPartName())) {
                            continue;
                        } else {
                            PackagePart dest = tgt.createPart(p.getPartName(), p.getContentType());
                            InputStream in = p.getInputStream();
                            try {
                                OutputStream out = dest.getOutputStream();
                                try {
                                    IOUtils.copy(in, out);
                                    if (out != null) {
                                        out.close();
                                    }
                                    if (in != null) {
                                        in.close();
                                    }
                                    copy(pkg, p, tgt, dest);
                                } finally {
                                }
                            } catch (Throwable th) {
                                try {
                                    throw th;
                                } catch (Throwable th2) {
                                    if (in != null) {
                                        try {
                                            in.close();
                                        } catch (Throwable th3) {
                                            th.addSuppressed(th3);
                                        }
                                    }
                                    throw th2;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void copyProperties(PackageProperties src, PackageProperties tgt) {
        tgt.setCategoryProperty(src.getCategoryProperty());
        tgt.setContentStatusProperty(src.getContentStatusProperty());
        tgt.setContentTypeProperty(src.getContentTypeProperty());
        tgt.setCreatorProperty(src.getCreatorProperty());
        tgt.setDescriptionProperty(src.getDescriptionProperty());
        tgt.setIdentifierProperty(src.getIdentifierProperty());
        tgt.setKeywordsProperty(src.getKeywordsProperty());
        tgt.setLanguageProperty(src.getLanguageProperty());
        tgt.setRevisionProperty(src.getRevisionProperty());
        tgt.setSubjectProperty(src.getSubjectProperty());
        tgt.setTitleProperty(src.getTitleProperty());
        tgt.setVersionProperty(src.getVersionProperty());
    }
}
