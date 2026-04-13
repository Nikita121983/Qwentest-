package org.apache.poi.openxml4j.opc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.internal.ContentType;
import org.apache.poi.openxml4j.opc.internal.marshallers.ZipPartMarshaller;
import org.apache.poi.util.NotImplemented;

/* loaded from: classes10.dex */
public class ZipPackagePart extends PackagePart {
    private ZipArchiveEntry zipEntry;

    public ZipPackagePart(OPCPackage container, ZipArchiveEntry zipEntry, PackagePartName partName, String contentType) throws InvalidFormatException {
        this(container, zipEntry, partName, contentType, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZipPackagePart(OPCPackage container, ZipArchiveEntry zipEntry, PackagePartName partName, String contentType, boolean loadRelationships) throws InvalidFormatException {
        super(container, partName, new ContentType(contentType), loadRelationships);
        this.zipEntry = zipEntry;
    }

    public ZipArchiveEntry getZipArchive() {
        return this.zipEntry;
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    protected InputStream getInputStreamImpl() throws IOException {
        return ((ZipPackage) this._container).getZipArchive().getInputStream(this.zipEntry);
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    protected OutputStream getOutputStreamImpl() {
        return null;
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public long getSize() {
        return this.zipEntry.getSize();
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public boolean save(OutputStream os) throws OpenXML4JException {
        return new ZipPartMarshaller().marshall(this, os);
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    @NotImplemented
    public boolean load(InputStream ios) {
        throw new InvalidOperationException("Method not implemented !");
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public void close() {
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public void flush() {
    }
}
