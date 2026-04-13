package org.apache.poi.openxml4j.opc.internal.marshallers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Iterator;
import java.util.Objects;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackageNamespaces;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.StreamHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.openxml4j.opc.internal.PartMarshaller;
import org.apache.poi.openxml4j.opc.internal.ZipHelper;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* loaded from: classes10.dex */
public final class ZipPartMarshaller implements PartMarshaller {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) ZipPartMarshaller.class);

    @Override // org.apache.poi.openxml4j.opc.internal.PartMarshaller
    public boolean marshall(PackagePart part, OutputStream os) throws OpenXML4JException {
        if (!(os instanceof ZipArchiveOutputStream)) {
            LOG.atError().log("Unexpected class {}", os.getClass().getName());
            throw new OpenXML4JException("ZipArchiveOutputStream expected !");
        }
        if (part.getSize() == 0 && part.getPartName().getName().equals(XSSFRelation.SHARED_STRINGS.getDefaultFileName())) {
            return true;
        }
        ZipArchiveOutputStream zos = (ZipArchiveOutputStream) os;
        ZipArchiveEntry partEntry = new ZipArchiveEntry(ZipHelper.getZipItemNameFromOPCName(part.getPartName().getURI().getPath()));
        try {
            ZipHelper.adjustEntryTime(partEntry);
            zos.putArchiveEntry(partEntry);
            try {
                InputStream ins = part.getInputStream();
                try {
                    IOUtils.copy(ins, zos);
                    if (ins != null) {
                        ins.close();
                    }
                    if (!part.hasRelationships()) {
                        return true;
                    }
                    PackagePartName relationshipPartName = PackagingURIHelper.getRelationshipPartName(part.getPartName());
                    return marshallRelationshipPart(part.getRelationships(), relationshipPartName, zos);
                } finally {
                }
            } finally {
                zos.closeArchiveEntry();
            }
        } catch (IOException ioe) {
            LOG.atError().withThrowable(ioe).log("Cannot write: {}: in ZIP", part.getPartName());
            return false;
        }
    }

    public static boolean marshallRelationshipPart(PackageRelationshipCollection rels, PackagePartName relPartName, ZipArchiveOutputStream zos) {
        String targetValue;
        Document xmlOutDoc = DocumentHelper.createDocument();
        Element root = xmlOutDoc.createElementNS(PackageNamespaces.RELATIONSHIPS, PackageRelationship.RELATIONSHIPS_TAG_NAME);
        xmlOutDoc.appendChild(root);
        URI sourcePartURI = PackagingURIHelper.getSourcePartUriFromRelationshipPartUri(relPartName.getURI());
        Iterator<PackageRelationship> it = rels.iterator();
        while (it.hasNext()) {
            PackageRelationship rel = it.next();
            Element relElem = xmlOutDoc.createElementNS(PackageNamespaces.RELATIONSHIPS, PackageRelationship.RELATIONSHIP_TAG_NAME);
            root.appendChild(relElem);
            relElem.setAttribute(PackageRelationship.ID_ATTRIBUTE_NAME, rel.getId());
            relElem.setAttribute(PackageRelationship.TYPE_ATTRIBUTE_NAME, rel.getRelationshipType());
            URI uri = rel.getTargetURI();
            if (Objects.equals(rel.getRelationshipType(), PackageRelationshipTypes.HYPERLINK_PART)) {
                targetValue = uri.toString();
                if (rel.getTargetMode() == TargetMode.EXTERNAL) {
                    relElem.setAttribute(PackageRelationship.TARGET_MODE_ATTRIBUTE_NAME, "External");
                }
            } else if (rel.getTargetMode() == TargetMode.EXTERNAL) {
                targetValue = uri.toString();
                relElem.setAttribute(PackageRelationship.TARGET_MODE_ATTRIBUTE_NAME, "External");
            } else {
                URI targetURI = rel.getTargetURI();
                targetValue = PackagingURIHelper.relativizeURI(sourcePartURI, targetURI, true).toString();
            }
            relElem.setAttribute(PackageRelationship.TARGET_ATTRIBUTE_NAME, targetValue);
        }
        xmlOutDoc.normalize();
        ZipArchiveEntry ctEntry = new ZipArchiveEntry(ZipHelper.getZipURIFromOPCName(relPartName.getURI().toASCIIString()).getPath());
        try {
            ZipHelper.adjustEntryTime(ctEntry);
            zos.putArchiveEntry(ctEntry);
            try {
                return StreamHelper.saveXmlInStream(xmlOutDoc, zos);
            } finally {
                zos.closeArchiveEntry();
            }
        } catch (IOException e) {
            LOG.atError().withThrowable(e).log("Cannot create zip entry {}", relPartName);
            return false;
        }
    }
}
