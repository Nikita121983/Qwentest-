package org.apache.poi.openxml4j.opc.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.internal.marshallers.ZipPartMarshaller;
import org.apache.poi.util.IOUtils;

/* loaded from: classes10.dex */
public final class MemoryPackagePart extends PackagePart {
    protected byte[] data;

    public MemoryPackagePart(OPCPackage pack, PackagePartName partName, String contentType) throws InvalidFormatException {
        this(pack, partName, contentType, true);
    }

    public MemoryPackagePart(OPCPackage pack, PackagePartName partName, String contentType, boolean loadRelationships) throws InvalidFormatException {
        super(pack, partName, new ContentType(contentType), loadRelationships);
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    protected InputStream getInputStreamImpl() {
        if (this.data == null) {
            this.data = new byte[0];
        }
        try {
            return UnsynchronizedByteArrayInputStream.builder().setByteArray(this.data).get();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    protected OutputStream getOutputStreamImpl() {
        return new MemoryPackagePartOutputStream(this);
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public long getSize() {
        if (this.data == null) {
            return 0L;
        }
        return this.data.length;
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public void clear() {
        this.data = null;
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public boolean save(OutputStream os) throws OpenXML4JException {
        return new ZipPartMarshaller().marshall(this, os);
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public boolean load(InputStream is) throws InvalidFormatException {
        try {
            UnsynchronizedByteArrayOutputStream baos = UnsynchronizedByteArrayOutputStream.builder().get();
            try {
                IOUtils.copy(is, baos);
                this.data = baos.toByteArray();
                if (baos != null) {
                    baos.close();
                    return true;
                }
                return true;
            } finally {
            }
        } catch (IOException e) {
            throw new InvalidFormatException(e.getMessage());
        }
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public void close() {
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public void flush() {
    }
}
