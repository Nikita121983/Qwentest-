package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCacheRecords;

/* loaded from: classes10.dex */
public class XSSFPivotCacheRecords extends POIXMLDocumentPart {
    private CTPivotCacheRecords ctPivotCacheRecords;

    public XSSFPivotCacheRecords() {
        this.ctPivotCacheRecords = CTPivotCacheRecords.Factory.newInstance();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFPivotCacheRecords(PackagePart part) throws IOException {
        super(part);
        InputStream stream = part.getInputStream();
        try {
            readFrom(stream);
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

    protected void readFrom(InputStream is) throws IOException {
        try {
            XmlOptions options = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            options.setLoadReplaceDocumentElement(null);
            this.ctPivotCacheRecords = CTPivotCacheRecords.Factory.parse(is, options);
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    @Internal
    public CTPivotCacheRecords getCtPivotCacheRecords() {
        return this.ctPivotCacheRecords;
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            xmlOptions.setSaveSyntheticDocumentElement(new QName(CTPivotCacheRecords.type.getName().getNamespaceURI(), "pivotCacheRecords"));
            this.ctPivotCacheRecords.save(out, xmlOptions);
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
}
