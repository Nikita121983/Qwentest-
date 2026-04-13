package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthor;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList;
import org.openxmlformats.schemas.presentationml.x2006.main.CmAuthorLstDocument;

/* loaded from: classes10.dex */
public class XSLFCommentAuthors extends POIXMLDocumentPart {
    private final CTCommentAuthorList _authors;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFCommentAuthors() {
        CmAuthorLstDocument doc = CmAuthorLstDocument.Factory.newInstance();
        this._authors = doc.addNewCmAuthorLst();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFCommentAuthors(PackagePart part) throws IOException, XmlException {
        super(part);
        InputStream stream = getPackagePart().getInputStream();
        try {
            CmAuthorLstDocument doc = CmAuthorLstDocument.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this._authors = doc.getCmAuthorLst();
            if (stream != null) {
                stream.close();
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
    }

    public CTCommentAuthorList getCTCommentAuthorsList() {
        return this._authors;
    }

    public CTCommentAuthor getAuthorById(long id) {
        for (CTCommentAuthor author : this._authors.getCmAuthorArray()) {
            if (author.getId() == id) {
                return author;
            }
        }
        return null;
    }
}
