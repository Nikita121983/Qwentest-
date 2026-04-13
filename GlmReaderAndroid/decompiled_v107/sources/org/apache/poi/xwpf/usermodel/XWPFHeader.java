package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.HdrDocument;

/* loaded from: classes10.dex */
public class XWPFHeader extends XWPFHeaderFooter {
    public XWPFHeader() {
    }

    public XWPFHeader(POIXMLDocumentPart parent, PackagePart part) throws IOException {
        super(parent, part);
    }

    public XWPFHeader(XWPFDocument doc, CTHdrFtr hdrFtr) {
        super(doc, hdrFtr);
        XmlCursor cursor = this.headerFooter.newCursor();
        try {
            cursor.selectPath("./*");
            while (cursor.toNextSelection()) {
                XmlObject o = cursor.getObject();
                if (o instanceof CTP) {
                    XWPFParagraph p = new XWPFParagraph((CTP) o, this);
                    this.paragraphs.add(p);
                }
                if (o instanceof CTTbl) {
                    XWPFTable t = new XWPFTable((CTTbl) o, this, false);
                    this.tables.add(t);
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTNumbering.type.getName().getNamespaceURI(), "hdr"));
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            super._getHdrFtr().save(out, xmlOptions);
            if (out != null) {
                out.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (out != null) {
                    try {
                        out.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.xwpf.usermodel.XWPFHeaderFooter, org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() throws IOException {
        super.onDocumentRead();
        try {
            InputStream is = getPackagePart().getInputStream();
            try {
                HdrDocument hdrDocument = HdrDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                this.headerFooter = hdrDocument.getHdr();
                XmlCursor cursor = this.headerFooter.newCursor();
                try {
                    cursor.selectPath("./*");
                    while (cursor.toNextSelection()) {
                        XmlObject o = cursor.getObject();
                        if (o instanceof CTP) {
                            XWPFParagraph p = new XWPFParagraph((CTP) o, this);
                            this.paragraphs.add(p);
                            this.bodyElements.add(p);
                        }
                        if (o instanceof CTTbl) {
                            XWPFTable t = new XWPFTable((CTTbl) o, this, false);
                            this.tables.add(t);
                            this.bodyElements.add(t);
                        }
                        if (o instanceof CTSdtBlock) {
                            XWPFSDT c = new XWPFSDT((CTSdtBlock) o, this);
                            this.bodyElements.add(c);
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                } finally {
                }
            } finally {
            }
        } catch (XmlException e) {
            throw new POIXMLException(e);
        }
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public BodyType getPartType() {
        return BodyType.HEADER;
    }
}
