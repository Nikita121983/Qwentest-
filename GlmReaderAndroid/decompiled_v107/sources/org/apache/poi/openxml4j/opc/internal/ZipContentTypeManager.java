package org.apache.poi.openxml4j.opc.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.StreamHelper;
import org.w3c.dom.Document;

/* loaded from: classes10.dex */
public class ZipContentTypeManager extends ContentTypeManager {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) ZipContentTypeManager.class);

    public ZipContentTypeManager(InputStream in, OPCPackage pkg) throws InvalidFormatException {
        super(in, pkg);
    }

    @Override // org.apache.poi.openxml4j.opc.internal.ContentTypeManager
    public boolean saveImpl(Document content, OutputStream out) {
        ZipArchiveOutputStream zos = out instanceof ZipArchiveOutputStream ? (ZipArchiveOutputStream) out : new ZipArchiveOutputStream(out);
        ZipArchiveEntry partEntry = new ZipArchiveEntry(ContentTypeManager.CONTENT_TYPES_PART_NAME);
        try {
            ZipHelper.adjustEntryTime(partEntry);
            zos.putArchiveEntry(partEntry);
            try {
                return StreamHelper.saveXmlInStream(content, zos);
            } finally {
                zos.closeArchiveEntry();
            }
        } catch (IOException ioe) {
            LOG.atError().withThrowable(ioe).log("Cannot write: [Content_Types].xml in Zip !");
            return false;
        }
    }
}
