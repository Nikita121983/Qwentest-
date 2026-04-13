package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.PageType;
import com.microsoft.schemas.office.visio.x2012.main.PagesDocument;
import com.microsoft.schemas.office.visio.x2012.main.PagesType;
import com.microsoft.schemas.office.visio.x2012.main.RelType;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.exceptions.XDGFException;
import org.apache.poi.xdgf.xml.XDGFXMLDocumentPart;
import org.apache.xmlbeans.XmlException;

/* loaded from: classes10.dex */
public class XDGFPages extends XDGFXMLDocumentPart {
    List<XDGFPage> _pages;
    PagesType _pagesObject;

    public XDGFPages(PackagePart part) {
        super(part);
        this._pages = new ArrayList();
    }

    @Internal
    PagesType getXmlObject() {
        return this._pagesObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() {
        try {
            try {
                InputStream stream = getPackagePart().getInputStream();
                try {
                    this._pagesObject = PagesDocument.Factory.parse(stream).getPages();
                    if (stream != null) {
                        stream.close();
                    }
                    for (PageType pageSettings : this._pagesObject.getPageArray()) {
                        RelType rel = pageSettings.getRel();
                        if (rel == null) {
                            throw new IllegalStateException("Could not read relation for page settings");
                        }
                        String relId = rel.getId();
                        POIXMLDocumentPart pageContentsPart = getRelationById(relId);
                        if (pageContentsPart == null) {
                            throw new POIXMLException("PageSettings relationship for " + relId + " not found");
                        }
                        if (!(pageContentsPart instanceof XDGFPageContents)) {
                            throw new POIXMLException("Unexpected pages relationship for " + relId + ": " + pageContentsPart);
                        }
                        XDGFPageContents contents = (XDGFPageContents) pageContentsPart;
                        XDGFPage page = new XDGFPage(pageSettings, contents, this._document, this);
                        contents.onDocumentRead();
                        this._pages.add(page);
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

    public List<XDGFPage> getPageList() {
        return Collections.unmodifiableList(this._pages);
    }
}
