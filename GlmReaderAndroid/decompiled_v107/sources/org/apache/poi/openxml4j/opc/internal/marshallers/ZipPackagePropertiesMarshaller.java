package org.apache.poi.openxml4j.opc.internal.marshallers;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.StreamHelper;
import org.apache.poi.openxml4j.opc.internal.ZipHelper;

/* loaded from: classes10.dex */
public final class ZipPackagePropertiesMarshaller extends PackagePropertiesMarshaller {
    @Override // org.apache.poi.openxml4j.opc.internal.marshallers.PackagePropertiesMarshaller, org.apache.poi.openxml4j.opc.internal.PartMarshaller
    public boolean marshall(PackagePart part, OutputStream out) throws OpenXML4JException {
        if (!(out instanceof ZipArchiveOutputStream)) {
            throw new IllegalArgumentException("ZipOutputStream expected!");
        }
        ZipArchiveOutputStream zos = (ZipArchiveOutputStream) out;
        ZipArchiveEntry ctEntry = new ZipArchiveEntry(ZipHelper.getZipItemNameFromOPCName(part.getPartName().getURI().toString()));
        try {
            ZipHelper.adjustEntryTime(ctEntry);
            zos.putArchiveEntry(ctEntry);
            try {
                super.marshall(part, out);
                return StreamHelper.saveXmlInStream(this.xmlDoc, out);
            } finally {
                zos.closeArchiveEntry();
            }
        } catch (IOException e) {
            throw new OpenXML4JException(e.getLocalizedMessage(), e);
        }
    }
}
