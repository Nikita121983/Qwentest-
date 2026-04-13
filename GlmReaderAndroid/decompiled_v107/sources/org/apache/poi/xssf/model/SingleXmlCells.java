package org.apache.poi.xssf.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Vector;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.helpers.XSSFSingleXmlCell;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCells;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.SingleXmlCellsDocument;

/* loaded from: classes10.dex */
public class SingleXmlCells extends POIXMLDocumentPart {
    private CTSingleXmlCells singleXMLCells;

    public SingleXmlCells() {
        this.singleXMLCells = CTSingleXmlCells.Factory.newInstance();
    }

    public SingleXmlCells(PackagePart part) throws IOException {
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

    public void readFrom(InputStream is) throws IOException {
        try {
            SingleXmlCellsDocument doc = SingleXmlCellsDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this.singleXMLCells = doc.getSingleXmlCells();
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public XSSFSheet getXSSFSheet() {
        return (XSSFSheet) getParent();
    }

    protected void writeTo(OutputStream out) throws IOException {
        SingleXmlCellsDocument doc = SingleXmlCellsDocument.Factory.newInstance();
        doc.setSingleXmlCells(this.singleXMLCells);
        doc.save(out, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            writeTo(out);
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

    public CTSingleXmlCells getCTSingleXMLCells() {
        return this.singleXMLCells;
    }

    public List<XSSFSingleXmlCell> getAllSimpleXmlCell() {
        List<XSSFSingleXmlCell> list = new Vector<>();
        for (CTSingleXmlCell singleXmlCell : this.singleXMLCells.getSingleXmlCellArray()) {
            list.add(new XSSFSingleXmlCell(singleXmlCell, this));
        }
        return list;
    }
}
