package org.apache.poi.xssf.binary;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;

@Internal
/* loaded from: classes10.dex */
public class XSSFBCommentsTable extends XSSFBParser {
    private StringBuilder authorBuffer;
    private int authorId;
    private List<String> authors;
    private CellAddress cellAddress;
    private XSSFBCellRange cellRange;
    private String comment;
    private Queue<CellAddress> commentAddresses;
    private Map<CellAddress, XSSFBComment> comments;

    public XSSFBCommentsTable(InputStream is) throws IOException {
        super(is);
        this.comments = new TreeMap();
        this.commentAddresses = new LinkedList();
        this.authors = new ArrayList();
        this.authorId = -1;
        this.authorBuffer = new StringBuilder();
        parse();
        this.commentAddresses.addAll(this.comments.keySet());
    }

    @Override // org.apache.poi.xssf.binary.XSSFBParser
    public void handleRecord(int id, byte[] data) throws XSSFBParseException {
        XSSFBRecordType recordType = XSSFBRecordType.lookup(id);
        switch (recordType) {
            case BrtBeginComment:
                this.authorId = XSSFBUtils.castToInt(LittleEndian.getUInt(data));
                int offset = 0 + 4;
                this.cellRange = XSSFBCellRange.parse(data, offset, this.cellRange);
                int i = offset + 16;
                this.cellAddress = new CellAddress(this.cellRange.firstRow, this.cellRange.firstCol);
                return;
            case BrtCommentText:
                XSSFBRichStr xssfbRichStr = XSSFBRichStr.build(data, 0);
                this.comment = xssfbRichStr.getString();
                return;
            case BrtEndComment:
                this.comments.put(this.cellAddress, new XSSFBComment(this.cellAddress, this.authors.get(this.authorId), this.comment));
                this.authorId = -1;
                this.cellAddress = null;
                return;
            case BrtCommentAuthor:
                this.authorBuffer.setLength(0);
                XSSFBUtils.readXLWideString(data, 0, this.authorBuffer);
                this.authors.add(this.authorBuffer.toString());
                return;
            default:
                return;
        }
    }

    public Queue<CellAddress> getAddresses() {
        return this.commentAddresses;
    }

    public XSSFBComment get(CellAddress cellAddress) {
        if (cellAddress == null) {
            return null;
        }
        return this.comments.get(cellAddress);
    }
}
