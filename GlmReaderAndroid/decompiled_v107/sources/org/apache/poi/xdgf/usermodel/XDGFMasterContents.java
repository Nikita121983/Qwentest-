package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.MasterContentsDocument;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xdgf.exceptions.XDGFException;
import org.apache.xmlbeans.XmlException;

/* loaded from: classes10.dex */
public class XDGFMasterContents extends XDGFBaseContents {
    protected XDGFMaster _master;

    public XDGFMasterContents(PackagePart part) {
        super(part);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.xdgf.usermodel.XDGFBaseContents, org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() {
        try {
            try {
                InputStream stream = getPackagePart().getInputStream();
                try {
                    this._pageContents = MasterContentsDocument.Factory.parse(stream).getMasterContents();
                    if (stream != null) {
                        stream.close();
                    }
                    super.onDocumentRead();
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (stream != null) {
                            try {
                                stream.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        }
                        throw th2;
                    }
                }
            } catch (POIXMLException e) {
                throw XDGFException.wrap(this, e);
            }
        } catch (IOException | XmlException e2) {
            throw new POIXMLException(e2);
        }
    }

    public XDGFMaster getMaster() {
        return this._master;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setMaster(XDGFMaster master) {
        this._master = master;
    }
}
