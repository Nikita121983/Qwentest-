package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCacheField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCacheFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCacheDefinition;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheetSource;

/* loaded from: classes10.dex */
public class XSSFPivotCacheDefinition extends POIXMLDocumentPart {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private CTPivotCacheDefinition ctPivotCacheDefinition;

    public XSSFPivotCacheDefinition() {
        this.ctPivotCacheDefinition = CTPivotCacheDefinition.Factory.newInstance();
        createDefaultValues();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFPivotCacheDefinition(PackagePart part) throws IOException {
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
            XmlOptions options = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            options.setLoadReplaceDocumentElement(null);
            this.ctPivotCacheDefinition = CTPivotCacheDefinition.Factory.parse(is, options);
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage(), e);
        }
    }

    @Internal
    public CTPivotCacheDefinition getCTPivotCacheDefinition() {
        return this.ctPivotCacheDefinition;
    }

    private void createDefaultValues() {
        this.ctPivotCacheDefinition.setCreatedVersion((short) 3);
        this.ctPivotCacheDefinition.setMinRefreshableVersion((short) 3);
        this.ctPivotCacheDefinition.setRefreshedVersion((short) 3);
        this.ctPivotCacheDefinition.setRefreshedBy(POIXMLDocument.DOCUMENT_CREATOR);
        this.ctPivotCacheDefinition.setRefreshedDate(new Date().getTime());
        this.ctPivotCacheDefinition.setRefreshOnLoad(true);
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            xmlOptions.setSaveSyntheticDocumentElement(new QName(CTPivotCacheDefinition.type.getName().getNamespaceURI(), "pivotCacheDefinition"));
            this.ctPivotCacheDefinition.save(out, xmlOptions);
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

    public AreaReference getPivotArea(Workbook wb) throws IllegalArgumentException {
        CTWorksheetSource wsSource = this.ctPivotCacheDefinition.getCacheSource().getWorksheetSource();
        String ref = wsSource.getRef();
        String name = wsSource.getName();
        if (ref == null && name == null) {
            throw new IllegalArgumentException("Pivot cache must reference an area, named range, or table.");
        }
        if (ref != null) {
            return new AreaReference(ref, SpreadsheetVersion.EXCEL2007);
        }
        if (name == null) {
            throw new AssertionError();
        }
        Name range = wb.getName(name);
        if (range != null) {
            return new AreaReference(range.getRefersToFormula(), SpreadsheetVersion.EXCEL2007);
        }
        XSSFSheet sheet = (XSSFSheet) wb.getSheet(wsSource.getSheet());
        for (XSSFTable table : sheet.getTables()) {
            if (name.equals(table.getName())) {
                return new AreaReference(table.getStartCellReference(), table.getEndCellReference(), SpreadsheetVersion.EXCEL2007);
            }
        }
        throw new IllegalArgumentException("Name '" + name + "' was not found.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void createCacheFields(Sheet sheet) {
        CTCacheFields cFields;
        AreaReference ar = getPivotArea(sheet.getWorkbook());
        CellReference firstCell = ar.getFirstCell();
        CellReference lastCell = ar.getLastCell();
        int columnStart = firstCell.getCol();
        int columnEnd = lastCell.getCol();
        Row row = sheet.getRow(firstCell.getRow());
        if (this.ctPivotCacheDefinition.getCacheFields() != null) {
            cFields = this.ctPivotCacheDefinition.getCacheFields();
        } else {
            cFields = this.ctPivotCacheDefinition.addNewCacheFields();
        }
        for (int i = columnStart; i <= columnEnd; i++) {
            CTCacheField cf = cFields.addNewCacheField();
            if (i == columnEnd) {
                cFields.setCount(cFields.sizeOfCacheFieldArray());
            }
            cf.setNumFmtId(0L);
            cf.setName(row.getCell(i).getStringCellValue());
            cf.addNewSharedItems();
        }
    }
}
