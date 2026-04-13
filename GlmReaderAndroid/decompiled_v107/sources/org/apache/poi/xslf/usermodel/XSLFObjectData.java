package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.usermodel.ObjectData;

/* loaded from: classes10.dex */
public final class XSLFObjectData extends POIXMLDocumentPart implements ObjectData {
    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFObjectData() {
    }

    public XSLFObjectData(PackagePart part) {
        super(part);
    }

    @Override // org.apache.poi.sl.usermodel.ObjectData
    public InputStream getInputStream() throws IOException {
        return getPackagePart().getInputStream();
    }

    @Override // org.apache.poi.sl.usermodel.ObjectData
    public OutputStream getOutputStream() throws IOException {
        PackagePart pp = getPackagePart();
        pp.clear();
        return pp.getOutputStream();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void prepareForCommit() {
    }

    public void setData(byte[] data) throws IOException {
        OutputStream os = getPackagePart().getOutputStream();
        try {
            os.write(data);
            if (os != null) {
                os.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (os != null) {
                    try {
                        os.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.poi.sl.usermodel.ObjectData
    public String getOLE2ClassName() {
        return null;
    }

    @Override // org.apache.poi.sl.usermodel.ObjectData
    public String getFileName() {
        return null;
    }
}
