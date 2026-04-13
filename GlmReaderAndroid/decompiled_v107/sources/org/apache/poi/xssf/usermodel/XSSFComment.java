package org.apache.poi.xssf.usermodel;

import com.microsoft.schemas.office.excel.CTClientData;
import com.microsoft.schemas.vml.CTShape;
import java.math.BigInteger;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.model.Comments;
import org.apache.poi.xssf.model.CommentsTable;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalseBlank;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst;

/* loaded from: classes10.dex */
public class XSSFComment implements Comment {
    private final CTComment _comment;
    private final Comments _comments;
    private XSSFRichTextString _str;
    private final CTShape _vmlShape;

    public XSSFComment(CommentsTable comments, CTComment comment, CTShape vmlShape) {
        this((Comments) comments, comment, vmlShape);
    }

    public XSSFComment(Comments comments, CTComment comment, CTShape vmlShape) {
        this._comment = comment;
        this._comments = comments;
        this._vmlShape = vmlShape;
        if (comment != null && vmlShape != null && vmlShape.sizeOfClientDataArray() > 0) {
            CellReference ref = new CellReference(comment.getRef());
            CTClientData clientData = vmlShape.getClientDataArray(0);
            clientData.setRowArray(0, new BigInteger(String.valueOf(ref.getRow())));
            clientData.setColumnArray(0, new BigInteger(String.valueOf((int) ref.getCol())));
            avoidXmlbeansCorruptPointer(vmlShape);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public String getAuthor() {
        return this._comments.getAuthor(this._comment.getAuthorId());
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public void setAuthor(String author) {
        this._comment.setAuthorId(this._comments.findAuthor(author));
        this._comments.commentUpdated(this);
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public int getColumn() {
        return getAddress().getColumn();
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public int getRow() {
        return getAddress().getRow();
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public boolean isVisible() {
        if (this._vmlShape == null) {
            return false;
        }
        if (this._vmlShape.sizeOfClientDataArray() > 0) {
            CTClientData clientData = this._vmlShape.getClientDataArray(0);
            boolean visible = clientData != null && clientData.sizeOfVisibleArray() > 0;
            return visible;
        }
        String style = this._vmlShape.getStyle();
        boolean visible2 = style != null && style.contains("visibility:visible");
        return visible2;
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public void setVisible(boolean visible) {
        if (this._vmlShape != null) {
            if (visible) {
                this._vmlShape.setStyle("position:absolute");
                CTClientData clientData = this._vmlShape.getClientDataArray(0);
                if (clientData != null && clientData.sizeOfVisibleArray() == 0) {
                    clientData.addVisible(STTrueFalseBlank.X);
                }
            } else {
                this._vmlShape.setStyle("position:absolute;visibility:hidden");
                CTClientData clientData2 = this._vmlShape.getClientDataArray(0);
                if (clientData2 != null && clientData2.sizeOfVisibleArray() > 0) {
                    clientData2.removeVisible(0);
                }
            }
        }
        this._comments.commentUpdated(this);
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public CellAddress getAddress() {
        return new CellAddress(this._comment.getRef());
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public void setAddress(int row, int col) {
        setAddress(new CellAddress(row, col));
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public void setAddress(CellAddress address) {
        CellAddress oldRef = new CellAddress(this._comment.getRef());
        if (address.equals(oldRef)) {
            return;
        }
        this._comment.setRef(address.formatAsString());
        this._comments.referenceUpdated(oldRef, this);
        if (this._vmlShape != null) {
            CTClientData clientData = this._vmlShape.getClientDataArray(0);
            clientData.setRowArray(0, new BigInteger(String.valueOf(address.getRow())));
            clientData.setColumnArray(0, new BigInteger(String.valueOf(address.getColumn())));
            avoidXmlbeansCorruptPointer(this._vmlShape);
        }
        this._comments.commentUpdated(this);
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public void setColumn(int col) {
        setAddress(getRow(), col);
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public void setRow(int row) {
        setAddress(row, getColumn());
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public XSSFRichTextString getString() {
        if (this._str == null) {
            CTRst rst = this._comment.getText();
            if (rst != null) {
                this._str = new XSSFRichTextString(this._comment.getText());
            }
        }
        return this._str;
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public void setString(RichTextString string) {
        if (!(string instanceof XSSFRichTextString)) {
            throw new IllegalArgumentException("Only XSSFRichTextString argument is supported");
        }
        this._str = (XSSFRichTextString) string;
        this._comment.setText(this._str.getCTRst());
        this._comments.commentUpdated(this);
    }

    public void setString(String string) {
        setString(new XSSFRichTextString(string));
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public ClientAnchor getClientAnchor() {
        if (this._vmlShape == null) {
            return null;
        }
        String position = this._vmlShape.getClientDataArray(0).getAnchorArray(0);
        int[] pos = new int[8];
        int i = 0;
        String[] split = position.split(CollectionUtils.COMMA);
        int length = split.length;
        int i2 = 0;
        while (i2 < length) {
            String s = split[i2];
            pos[i] = Integer.parseInt(s.trim());
            i2++;
            i++;
        }
        return new XSSFClientAnchor(pos[1] * 9525, pos[3] * 9525, pos[5] * 9525, pos[7] * 9525, pos[0], pos[2], pos[4], pos[6]);
    }

    public CTComment getCTComment() {
        return this._comment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CTShape getCTShape() {
        return this._vmlShape;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof XSSFComment)) {
            return false;
        }
        XSSFComment other = (XSSFComment) obj;
        return getCTComment() == other.getCTComment() && getCTShape() == other.getCTShape();
    }

    public int hashCode() {
        return ((getRow() * 17) + getColumn()) * 31;
    }

    private static void avoidXmlbeansCorruptPointer(CTShape vmlShape) {
        vmlShape.getClientDataList().toString();
    }
}
