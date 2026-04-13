package org.apache.poi.xssf.binary;

import java.util.Objects;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public class XSSFHyperlinkRecord {
    private final CellRangeAddress cellRangeAddress;
    private String display;
    private String location;
    private final String relId;
    private String toolTip;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSSFHyperlinkRecord(CellRangeAddress cellRangeAddress, String relId, String location, String toolTip, String display) {
        this.cellRangeAddress = cellRangeAddress;
        this.relId = relId;
        this.location = location;
        this.toolTip = toolTip;
        this.display = display;
    }

    void setLocation(String location) {
        this.location = location;
    }

    void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    void setDisplay(String display) {
        this.display = display;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CellRangeAddress getCellRangeAddress() {
        return this.cellRangeAddress;
    }

    public String getRelId() {
        return this.relId;
    }

    public String getLocation() {
        return this.location;
    }

    public String getToolTip() {
        return this.toolTip;
    }

    public String getDisplay() {
        return this.display;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        XSSFHyperlinkRecord that = (XSSFHyperlinkRecord) o;
        if (Objects.equals(this.cellRangeAddress, that.cellRangeAddress) && Objects.equals(this.relId, that.relId) && Objects.equals(this.location, that.location) && Objects.equals(this.toolTip, that.toolTip) && Objects.equals(this.display, that.display)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.cellRangeAddress, this.relId, this.location, this.toolTip, this.display);
    }

    public String toString() {
        return "XSSFHyperlinkRecord{cellRangeAddress=" + this.cellRangeAddress + ", relId='" + this.relId + Chars.QUOTE + ", location='" + this.location + Chars.QUOTE + ", toolTip='" + this.toolTip + Chars.QUOTE + ", display='" + this.display + Chars.QUOTE + '}';
    }
}
