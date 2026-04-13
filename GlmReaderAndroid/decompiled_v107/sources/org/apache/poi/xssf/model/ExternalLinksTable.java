package org.apache.poi.xssf.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalLink;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalRow;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetData;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetDataSet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetNames;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.ExternalLinkDocument;

/* loaded from: classes10.dex */
public class ExternalLinksTable extends POIXMLDocumentPart {
    private CTExternalLink link;

    public ExternalLinksTable() {
        this.link = CTExternalLink.Factory.newInstance();
        this.link.addNewExternalBook();
    }

    public ExternalLinksTable(PackagePart part) throws IOException {
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
            ExternalLinkDocument doc = ExternalLinkDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this.link = doc.getExternalLink();
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public void writeTo(OutputStream out) throws IOException {
        ExternalLinkDocument doc = ExternalLinkDocument.Factory.newInstance();
        doc.setExternalLink(this.link);
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

    @Removal(version = "6.0.0")
    @Internal
    public CTExternalLink getCTExternalLink() {
        return this.link;
    }

    public String getLinkedFileName() {
        String rId = this.link.getExternalBook().getId();
        PackageRelationship rel = getPackagePart().getRelationship(rId);
        if (rel != null && rel.getTargetMode() == TargetMode.EXTERNAL) {
            return rel.getTargetURI().toString();
        }
        return null;
    }

    public void setLinkedFileName(String target) {
        String rId = this.link.getExternalBook().getId();
        if (rId != null && !rId.isEmpty()) {
            getPackagePart().removeRelationship(rId);
        }
        PackageRelationship newRel = getPackagePart().addExternalRelationship(target, PackageRelationshipTypes.EXTERNAL_LINK_PATH);
        this.link.getExternalBook().setId(newRel.getId());
    }

    public List<String> getSheetNames() {
        CTExternalSheetName[] sheetNames = this.link.getExternalBook().getSheetNames().getSheetNameArray();
        List<String> names = new ArrayList<>(sheetNames.length);
        for (CTExternalSheetName name : sheetNames) {
            names.add(name.getVal());
        }
        return names;
    }

    public List<Name> getDefinedNames() {
        CTExternalDefinedName[] extNames = this.link.getExternalBook().getDefinedNames().getDefinedNameArray();
        List<Name> names = new ArrayList<>(extNames.length);
        for (CTExternalDefinedName extName : extNames) {
            names.add(new ExternalName(extName));
        }
        return names;
    }

    public void cacheData(String sheetName, long rowR, String cellR, String v) {
        CTExternalBook externalBook = this.link.getExternalBook();
        synchronized (externalBook.monitor()) {
            CTExternalSheetData sheetData = getSheetData(getSheetNameIndex(sheetName));
            CTExternalRow row = getRow(sheetData, rowR);
            CTExternalCell cell = getCell(row, cellR);
            cell.setV(v);
        }
    }

    private int getSheetNameIndex(String sheetName) {
        CTExternalBook externalBook = this.link.getExternalBook();
        CTExternalSheetNames sheetNames = externalBook.getSheetNames();
        if (sheetNames == null) {
            sheetNames = externalBook.addNewSheetNames();
        }
        int index = -1;
        int i = 0;
        while (true) {
            if (i >= sheetNames.sizeOfSheetNameArray()) {
                break;
            }
            CTExternalSheetName ctExternalSheetName = sheetNames.getSheetNameArray(i);
            if (!ctExternalSheetName.getVal().equals(sheetName)) {
                i++;
            } else {
                index = i;
                break;
            }
        }
        if (index == -1) {
            CTExternalSheetName ctExternalSheetName2 = sheetNames.addNewSheetName();
            ctExternalSheetName2.setVal(sheetName);
            int index2 = sheetNames.sizeOfSheetNameArray() - 1;
            return index2;
        }
        return index;
    }

    private CTExternalSheetData getSheetData(int sheetId) {
        CTExternalSheetDataSet sheetDataSet = this.link.getExternalBook().getSheetDataSet();
        if (sheetDataSet == null) {
            sheetDataSet = this.link.getExternalBook().addNewSheetDataSet();
        }
        CTExternalSheetData ctExternalSheetData = null;
        CTExternalSheetData[] sheetDataArray = sheetDataSet.getSheetDataArray();
        int length = sheetDataArray.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            CTExternalSheetData item = sheetDataArray[i];
            if (item.getSheetId() != sheetId) {
                i++;
            } else {
                ctExternalSheetData = item;
                break;
            }
        }
        if (ctExternalSheetData == null) {
            CTExternalSheetData ctExternalSheetData2 = sheetDataSet.addNewSheetData();
            ctExternalSheetData2.setSheetId(sheetId);
            return ctExternalSheetData2;
        }
        return ctExternalSheetData;
    }

    private CTExternalRow getRow(CTExternalSheetData sheetData, long rowR) {
        for (CTExternalRow ctExternalRow : sheetData.getRowArray()) {
            if (ctExternalRow.getR() == rowR) {
                return ctExternalRow;
            }
        }
        CTExternalRow ctExternalRow2 = sheetData.addNewRow();
        ctExternalRow2.setR(rowR);
        return ctExternalRow2;
    }

    private CTExternalCell getCell(CTExternalRow row, String cellR) {
        for (CTExternalCell ctExternalCell : row.getCellArray()) {
            if (ctExternalCell.getR().equals(cellR)) {
                return ctExternalCell;
            }
        }
        CTExternalCell ctExternalCell2 = row.addNewCell();
        ctExternalCell2.setR(cellR);
        return ctExternalCell2;
    }

    /* loaded from: classes10.dex */
    protected class ExternalName implements Name {
        private final CTExternalDefinedName name;

        protected ExternalName(CTExternalDefinedName name) {
            this.name = name;
        }

        @Override // org.apache.poi.ss.usermodel.Name
        public String getNameName() {
            return this.name.getName();
        }

        @Override // org.apache.poi.ss.usermodel.Name
        public void setNameName(String name) {
            this.name.setName(name);
        }

        @Override // org.apache.poi.ss.usermodel.Name
        public String getSheetName() {
            int sheetId = getSheetIndex();
            if (sheetId >= 0) {
                return ExternalLinksTable.this.getSheetNames().get(sheetId);
            }
            return null;
        }

        @Override // org.apache.poi.ss.usermodel.Name
        public int getSheetIndex() {
            if (this.name.isSetSheetId()) {
                return (int) this.name.getSheetId();
            }
            return -1;
        }

        @Override // org.apache.poi.ss.usermodel.Name
        public void setSheetIndex(int sheetId) {
            this.name.setSheetId(sheetId);
        }

        @Override // org.apache.poi.ss.usermodel.Name
        public String getRefersToFormula() {
            return this.name.getRefersTo().substring(1);
        }

        @Override // org.apache.poi.ss.usermodel.Name
        public void setRefersToFormula(String formulaText) {
            this.name.setRefersTo(Chars.EQ + formulaText);
        }

        @Override // org.apache.poi.ss.usermodel.Name
        public boolean isFunctionName() {
            return false;
        }

        @Override // org.apache.poi.ss.usermodel.Name
        public boolean isDeleted() {
            return false;
        }

        @Override // org.apache.poi.ss.usermodel.Name
        public boolean isHidden() {
            return false;
        }

        @Override // org.apache.poi.ss.usermodel.Name
        public String getComment() {
            return null;
        }

        @Override // org.apache.poi.ss.usermodel.Name
        public void setComment(String comment) {
            throw new IllegalStateException("Not Supported");
        }

        @Override // org.apache.poi.ss.usermodel.Name
        public void setFunction(boolean value) {
            throw new IllegalStateException("Not Supported");
        }
    }
}
