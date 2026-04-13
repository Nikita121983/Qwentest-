package org.apache.poi.xssf.binary;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import kotlin.UByte;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public class XSSFBStylesTable extends XSSFBParser {
    private boolean inCellXFS;
    private boolean inFmts;
    private final SortedMap<Short, String> numberFormats;
    private final List<Short> styleIds;

    public XSSFBStylesTable(InputStream is) throws IOException {
        super(is);
        this.numberFormats = new TreeMap();
        this.styleIds = new ArrayList();
        parse();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getNumberFormatString(int idx) {
        short numberFormatIdx = getNumberFormatIndex(idx);
        if (this.numberFormats.containsKey(Short.valueOf(numberFormatIdx))) {
            return this.numberFormats.get(Short.valueOf(numberFormatIdx));
        }
        return BuiltinFormats.getBuiltinFormat(numberFormatIdx);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public short getNumberFormatIndex(int idx) {
        return this.styleIds.get(idx).shortValue();
    }

    @Override // org.apache.poi.xssf.binary.XSSFBParser
    public void handleRecord(int recordType, byte[] data) throws XSSFBParseException {
        XSSFBRecordType type = XSSFBRecordType.lookup(recordType);
        switch (type) {
            case BrtBeginCellXFs:
                this.inCellXFS = true;
                return;
            case BrtEndCellXFs:
                this.inCellXFS = false;
                return;
            case BrtXf:
                if (this.inCellXFS) {
                    handleBrtXFInCellXF(data);
                    return;
                }
                return;
            case BrtBeginFmts:
                this.inFmts = true;
                return;
            case BrtEndFmts:
                this.inFmts = false;
                return;
            case BrtFmt:
                if (this.inFmts) {
                    handleFormat(data);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void handleFormat(byte[] data) {
        int ifmt = data[0] & UByte.MAX_VALUE;
        if (ifmt > 32767) {
            throw new POIXMLException("Format id must be a short");
        }
        StringBuilder sb = new StringBuilder();
        XSSFBUtils.readXLWideString(data, 2, sb);
        String fmt = sb.toString();
        this.numberFormats.put(Short.valueOf((short) ifmt), fmt);
    }

    private void handleBrtXFInCellXF(byte[] data) {
        int ifmt = data[2] & UByte.MAX_VALUE;
        this.styleIds.add(Short.valueOf((short) ifmt));
    }
}
