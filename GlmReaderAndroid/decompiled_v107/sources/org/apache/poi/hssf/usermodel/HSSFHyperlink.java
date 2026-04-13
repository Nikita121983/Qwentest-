package org.apache.poi.hssf.usermodel;

import androidx.core.net.MailTo;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.record.HyperlinkRecord;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.util.Internal;

/* loaded from: classes10.dex */
public class HSSFHyperlink implements Hyperlink, Duplicatable {
    protected final HyperlinkType link_type;
    protected final HyperlinkRecord record;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal(since = "3.15 beta 3")
    public HSSFHyperlink(HyperlinkType type) {
        this.link_type = type;
        this.record = new HyperlinkRecord();
        switch (type) {
            case URL:
            case EMAIL:
                this.record.newUrlLink();
                return;
            case FILE:
                this.record.newFileLink();
                return;
            case DOCUMENT:
                this.record.newDocumentLink();
                return;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HSSFHyperlink(HyperlinkRecord record) {
        this.record = record;
        this.link_type = getType(record);
    }

    private static HyperlinkType getType(HyperlinkRecord record) {
        if (record.isFileLink()) {
            HyperlinkType link_type = HyperlinkType.FILE;
            return link_type;
        }
        if (record.isDocumentLink()) {
            HyperlinkType link_type2 = HyperlinkType.DOCUMENT;
            return link_type2;
        }
        if (record.getAddress() != null && record.getAddress().startsWith(MailTo.MAILTO_SCHEME)) {
            HyperlinkType link_type3 = HyperlinkType.EMAIL;
            return link_type3;
        }
        HyperlinkType link_type4 = HyperlinkType.URL;
        return link_type4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HSSFHyperlink(Hyperlink other) {
        if (other instanceof HSSFHyperlink) {
            HSSFHyperlink hlink = (HSSFHyperlink) other;
            this.record = hlink.record.copy();
            this.link_type = getType(this.record);
        } else {
            this.link_type = other.getType();
            this.record = new HyperlinkRecord();
            setFirstRow(other.getFirstRow());
            setFirstColumn(other.getFirstColumn());
            setLastRow(other.getLastRow());
            setLastColumn(other.getLastColumn());
        }
    }

    @Override // org.apache.poi.ss.usermodel.Hyperlink
    public int getFirstRow() {
        return this.record.getFirstRow();
    }

    @Override // org.apache.poi.ss.usermodel.Hyperlink
    public void setFirstRow(int row) {
        this.record.setFirstRow(row);
    }

    @Override // org.apache.poi.ss.usermodel.Hyperlink
    public int getLastRow() {
        return this.record.getLastRow();
    }

    @Override // org.apache.poi.ss.usermodel.Hyperlink
    public void setLastRow(int row) {
        this.record.setLastRow(row);
    }

    @Override // org.apache.poi.ss.usermodel.Hyperlink
    public int getFirstColumn() {
        return this.record.getFirstColumn();
    }

    @Override // org.apache.poi.ss.usermodel.Hyperlink
    public void setFirstColumn(int col) {
        this.record.setFirstColumn((short) col);
    }

    @Override // org.apache.poi.ss.usermodel.Hyperlink
    public int getLastColumn() {
        return this.record.getLastColumn();
    }

    @Override // org.apache.poi.ss.usermodel.Hyperlink
    public void setLastColumn(int col) {
        this.record.setLastColumn((short) col);
    }

    @Override // org.apache.poi.common.usermodel.Hyperlink
    public String getAddress() {
        return this.record.getAddress();
    }

    public String getTextMark() {
        return this.record.getTextMark();
    }

    public void setTextMark(String textMark) {
        this.record.setTextMark(textMark);
    }

    public String getShortFilename() {
        return this.record.getShortFilename();
    }

    public void setShortFilename(String shortFilename) {
        this.record.setShortFilename(shortFilename);
    }

    @Override // org.apache.poi.common.usermodel.Hyperlink
    public void setAddress(String address) {
        this.record.setAddress(address);
    }

    @Override // org.apache.poi.common.usermodel.Hyperlink
    public String getLabel() {
        return this.record.getLabel();
    }

    @Override // org.apache.poi.common.usermodel.Hyperlink
    public void setLabel(String label) {
        this.record.setLabel(label);
    }

    @Override // org.apache.poi.common.usermodel.Hyperlink
    public HyperlinkType getType() {
        return this.link_type;
    }

    @Override // org.apache.poi.common.Duplicatable
    public Duplicatable copy() {
        return new HSSFHyperlink(this);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HSSFHyperlink)) {
            return false;
        }
        HSSFHyperlink otherLink = (HSSFHyperlink) other;
        return this.record == otherLink.record;
    }

    public int hashCode() {
        return this.record.hashCode();
    }
}
