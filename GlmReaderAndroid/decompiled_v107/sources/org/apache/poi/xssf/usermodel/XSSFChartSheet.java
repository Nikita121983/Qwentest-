package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.namespace.QName;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLegacyDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.ChartsheetDocument;

/* loaded from: classes10.dex */
public class XSSFChartSheet extends XSSFSheet {
    private static final byte[] BLANK_WORKSHEET = blankWorksheet();
    protected CTChartsheet chartsheet;

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFChartSheet(PackagePart part) {
        super(part);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.xssf.usermodel.XSSFSheet
    public void read(InputStream is) throws IOException {
        super.read(UnsynchronizedByteArrayInputStream.builder().setByteArray(BLANK_WORKSHEET).get());
        try {
            this.chartsheet = ChartsheetDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS).getChartsheet();
        } catch (XmlException e) {
            throw new POIXMLException(e);
        }
    }

    public CTChartsheet getCTChartsheet() {
        return this.chartsheet;
    }

    @Override // org.apache.poi.xssf.usermodel.XSSFSheet
    protected CTDrawing getCTDrawing() {
        return this.chartsheet.getDrawing();
    }

    @Override // org.apache.poi.xssf.usermodel.XSSFSheet
    protected CTLegacyDrawing getCTLegacyDrawing() {
        return this.chartsheet.getLegacyDrawing();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.xssf.usermodel.XSSFSheet
    public void write(OutputStream out) throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTChartsheet.type.getName().getNamespaceURI(), "chartsheet"));
        if (this.chartsheet != null) {
            this.chartsheet.save(out, xmlOptions);
        }
    }

    private static byte[] blankWorksheet() {
        UnsynchronizedByteArrayOutputStream out = UnsynchronizedByteArrayOutputStream.builder().get();
        try {
            new XSSFSheet().write(out);
            return out.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
