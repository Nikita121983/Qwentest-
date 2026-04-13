package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.PageContentsDocument;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xdgf.exceptions.XDGFException;
import org.apache.xmlbeans.XmlException;

/* loaded from: classes10.dex */
public class XDGFPageContents extends XDGFBaseContents {
    protected Map<Long, XDGFMaster> _masters;
    protected XDGFPage _page;

    public XDGFPageContents(PackagePart part) {
        super(part);
        this._masters = new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.xdgf.usermodel.XDGFBaseContents, org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() {
        try {
            try {
                InputStream stream = getPackagePart().getInputStream();
                try {
                    this._pageContents = PageContentsDocument.Factory.parse(stream).getPageContents();
                    if (stream != null) {
                        stream.close();
                    }
                    for (POIXMLDocumentPart part : getRelations()) {
                        if (part instanceof XDGFMasterContents) {
                            XDGFMaster master = ((XDGFMasterContents) part).getMaster();
                            if (master == null) {
                                throw new POIXMLException("Master entry is missing in XDGFPageContents");
                            }
                            this._masters.put(Long.valueOf(master.getID()), master);
                        }
                    }
                    super.onDocumentRead();
                    for (XDGFShape shape : this._shapes.values()) {
                        if (shape.isTopmost()) {
                            shape.setupMaster(this, null);
                        }
                    }
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

    public XDGFPage getPage() {
        return this._page;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPage(XDGFPage page) {
        this._page = page;
    }

    public XDGFMaster getMasterById(long id) {
        return this._masters.get(Long.valueOf(id));
    }
}
