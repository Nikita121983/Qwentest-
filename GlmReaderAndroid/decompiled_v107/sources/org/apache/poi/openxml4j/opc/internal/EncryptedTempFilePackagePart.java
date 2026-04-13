package org.apache.poi.openxml4j.opc.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.internal.marshallers.ZipPartMarshaller;
import org.apache.poi.poifs.crypt.temp.EncryptedTempData;
import org.apache.poi.util.IOUtils;

/* loaded from: classes10.dex */
public final class EncryptedTempFilePackagePart extends PackagePart {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) EncryptedTempFilePackagePart.class);
    private EncryptedTempData tempFile;

    public EncryptedTempFilePackagePart(OPCPackage pack, PackagePartName partName, String contentType) throws InvalidFormatException, IOException {
        this(pack, partName, contentType, true);
    }

    public EncryptedTempFilePackagePart(OPCPackage pack, PackagePartName partName, String contentType, boolean loadRelationships) throws InvalidFormatException, IOException {
        super(pack, partName, new ContentType(contentType), loadRelationships);
        this.tempFile = new EncryptedTempData();
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    protected InputStream getInputStreamImpl() throws IOException {
        return this.tempFile.getInputStream();
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    protected OutputStream getOutputStreamImpl() throws IOException {
        return this.tempFile.getOutputStream();
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public long getSize() {
        return this.tempFile.getByteCount();
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public void clear() {
        try {
            OutputStream os = getOutputStreamImpl();
            try {
                os.write(new byte[0]);
                if (os != null) {
                    os.close();
                }
            } finally {
            }
        } catch (IOException e) {
            LOG.atWarn().log("Failed to clear data in temp file", e);
        }
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public boolean save(OutputStream os) throws OpenXML4JException {
        return new ZipPartMarshaller().marshall(this, os);
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public boolean load(InputStream is) throws InvalidFormatException {
        try {
            OutputStream os = getOutputStreamImpl();
            try {
                IOUtils.copy(is, os);
                if (os != null) {
                    os.close();
                    return true;
                }
                return true;
            } finally {
            }
        } catch (IOException e) {
            throw new InvalidFormatException(e.getMessage(), e);
        }
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public void close() {
        this.tempFile.dispose();
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public void flush() {
    }
}
