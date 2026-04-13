package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentDocument1;
import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.util.PackageHelper;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.xmlbeans.XmlException;

/* loaded from: classes10.dex */
public class XmlVisioDocument extends POIXMLDocument {
    protected XDGFDocument _document;
    protected XDGFMasters _masters;
    protected XDGFPages _pages;

    public XmlVisioDocument(OPCPackage pkg) throws IOException {
        super(pkg, PackageRelationshipTypes.VISIO_CORE_DOCUMENT);
        try {
            InputStream stream = getPackagePart().getInputStream();
            try {
                VisioDocumentType document = VisioDocumentDocument1.Factory.parse(stream).getVisioDocument();
                if (stream != null) {
                    stream.close();
                }
                this._document = new XDGFDocument(document);
                load(new XDGFFactory(this._document));
            } finally {
            }
        } catch (IOException | XmlException e) {
            throw new POIXMLException(e);
        }
    }

    public XmlVisioDocument(InputStream stream) throws IOException {
        this(stream, true);
    }

    public XmlVisioDocument(InputStream stream, boolean closeStream) throws IOException {
        this(PackageHelper.open(stream, closeStream));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() {
        for (POIXMLDocumentPart part : getRelations()) {
            if (part instanceof XDGFPages) {
                this._pages = (XDGFPages) part;
            } else if (part instanceof XDGFMasters) {
                this._masters = (XDGFMasters) part;
            }
        }
        if (this._masters != null) {
            this._masters.onDocumentRead();
        }
        if (this._pages != null) {
            this._pages.onDocumentRead();
        }
    }

    @Override // org.apache.poi.ooxml.POIXMLDocument
    public List<PackagePart> getAllEmbeddedParts() {
        return new ArrayList();
    }

    public Collection<XDGFPage> getPages() {
        if (this._pages == null) {
            throw new IllegalStateException("No page-information available");
        }
        return this._pages.getPageList();
    }

    public XDGFStyleSheet getStyleById(long id) {
        return this._document.getStyleById(id);
    }
}
