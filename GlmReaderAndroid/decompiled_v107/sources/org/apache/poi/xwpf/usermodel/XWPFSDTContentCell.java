package org.apache.poi.xwpf.usermodel;

import javax.xml.namespace.QName;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtContentCell;

/* loaded from: classes10.dex */
public class XWPFSDTContentCell implements ISDTContent {
    private final CTSdtContentCell sdtContentCell;
    private String text;

    public XWPFSDTContentCell(CTSdtContentCell sdtContentCell, XWPFTableRow xwpfTableRow, IBody part) {
        this.sdtContentCell = sdtContentCell;
    }

    private String extractTextFromSdtContentCell() {
        if (this.sdtContentCell == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        XmlCursor cursor = this.sdtContentCell.newCursor();
        int tcCnt = 0;
        int iBodyCnt = 0;
        int depth = 1;
        while (cursor.hasNextToken() && depth > 0) {
            try {
                XmlCursor.TokenType t = cursor.toNextToken();
                if (t.isText()) {
                    sb.append(cursor.getTextValue());
                } else if (isStartToken(cursor, "tr")) {
                    tcCnt = 0;
                    iBodyCnt = 0;
                } else if (isStartToken(cursor, "tc")) {
                    int tcCnt2 = tcCnt + 1;
                    if (tcCnt > 0) {
                        sb.append("\t");
                    }
                    iBodyCnt = 0;
                    tcCnt = tcCnt2;
                } else if (isStartToken(cursor, "p") || isStartToken(cursor, "tbl") || isStartToken(cursor, "sdt")) {
                    if (iBodyCnt > 0) {
                        sb.append(StringUtils.LF);
                    }
                    iBodyCnt++;
                }
                if (cursor.isStart()) {
                    depth++;
                } else if (cursor.isEnd()) {
                    depth--;
                }
            } finally {
            }
        }
        String sb2 = sb.toString();
        if (cursor != null) {
            cursor.close();
        }
        return sb2;
    }

    private boolean isStartToken(XmlCursor cursor, String string) {
        QName qName;
        return cursor.isStart() && (qName = cursor.getName()) != null && qName.getLocalPart() != null && qName.getLocalPart().equals(string);
    }

    @Override // org.apache.poi.xwpf.usermodel.ISDTContent
    public String getText() {
        if (this.text == null) {
            this.text = extractTextFromSdtContentCell();
        }
        return this.text;
    }

    @Override // org.apache.poi.xwpf.usermodel.ISDTContent
    public String toString() {
        return getText();
    }

    public CTSdtContentCell getCTSdtContentCell() {
        return this.sdtContentCell;
    }
}
