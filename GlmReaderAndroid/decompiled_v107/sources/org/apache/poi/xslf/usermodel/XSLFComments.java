package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTComment;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList;
import org.openxmlformats.schemas.presentationml.x2006.main.CmLstDocument;

/* loaded from: classes10.dex */
public class XSLFComments extends POIXMLDocumentPart {
    private final CmLstDocument doc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFComments() {
        this.doc = CmLstDocument.Factory.newInstance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFComments(PackagePart part) throws IOException, XmlException {
        super(part);
        InputStream stream = getPackagePart().getInputStream();
        try {
            this.doc = CmLstDocument.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
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

    public CTCommentList getCTCommentsList() {
        return this.doc.getCmLst();
    }

    public int getNumberOfComments() {
        return this.doc.getCmLst().sizeOfCmArray();
    }

    public CTComment getCommentAt(int pos) {
        return this.doc.getCmLst().getCmArray(pos);
    }
}
