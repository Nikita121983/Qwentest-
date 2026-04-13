package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;

/* loaded from: classes10.dex */
public class XSLFFontData extends POIXMLDocumentPart {
    /* JADX INFO: Access modifiers changed from: protected */
    public XSLFFontData() {
    }

    public XSLFFontData(PackagePart part) {
        super(part);
    }

    public InputStream getInputStream() throws IOException {
        return getPackagePart().getInputStream();
    }

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
}
