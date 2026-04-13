package org.apache.poi.xssf.binary;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.SAXException;

@Internal
/* loaded from: classes10.dex */
public class XSSFBSharedStringsTable implements SharedStrings {
    private int count;
    private List<String> strings = new ArrayList();
    private int uniqueCount;

    public XSSFBSharedStringsTable(OPCPackage pkg) throws IOException, SAXException {
        ArrayList<PackagePart> parts = pkg.getPartsByContentType(XSSFBRelation.SHARED_STRINGS_BINARY.getContentType());
        if (!parts.isEmpty()) {
            PackagePart sstPart = parts.get(0);
            InputStream stream = sstPart.getInputStream();
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
    }

    XSSFBSharedStringsTable(PackagePart part) throws IOException, SAXException {
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

    private void readFrom(InputStream inputStream) throws IOException {
        SSTBinaryReader reader = new SSTBinaryReader(inputStream);
        reader.parse();
    }

    @Override // org.apache.poi.xssf.model.SharedStrings
    public RichTextString getItemAt(int idx) {
        return new XSSFRichTextString(this.strings.get(idx));
    }

    @Override // org.apache.poi.xssf.model.SharedStrings
    public int getCount() {
        return this.count;
    }

    @Override // org.apache.poi.xssf.model.SharedStrings
    public int getUniqueCount() {
        return this.uniqueCount;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class SSTBinaryReader extends XSSFBParser {
        SSTBinaryReader(InputStream is) {
            super(is);
        }

        @Override // org.apache.poi.xssf.binary.XSSFBParser
        public void handleRecord(int recordType, byte[] data) throws XSSFBParseException {
            XSSFBRecordType type = XSSFBRecordType.lookup(recordType);
            switch (type) {
                case BrtSstItem:
                    XSSFBRichStr rstr = XSSFBRichStr.build(data, 0);
                    XSSFBSharedStringsTable.this.strings.add(rstr.getString());
                    return;
                case BrtBeginSst:
                    XSSFBSharedStringsTable.this.count = XSSFBUtils.castToInt(LittleEndian.getUInt(data, 0));
                    XSSFBSharedStringsTable.this.uniqueCount = XSSFBUtils.castToInt(LittleEndian.getUInt(data, 4));
                    return;
                default:
                    return;
            }
        }
    }
}
