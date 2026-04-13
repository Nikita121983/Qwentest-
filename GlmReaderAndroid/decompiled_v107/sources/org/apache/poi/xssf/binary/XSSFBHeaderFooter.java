package org.apache.poi.xssf.binary;

import org.apache.logging.log4j.util.Chars;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.helpers.HeaderFooterHelper;

@Internal
/* loaded from: classes10.dex */
class XSSFBHeaderFooter {
    private static final HeaderFooterHelper HEADER_FOOTER_HELPER = new HeaderFooterHelper();
    private final String headerFooterTypeLabel;
    private final boolean isHeader;
    private String rawString;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSSFBHeaderFooter(String headerFooterTypeLabel, boolean isHeader) {
        this.headerFooterTypeLabel = headerFooterTypeLabel;
        this.isHeader = isHeader;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getHeaderFooterTypeLabel() {
        return this.headerFooterTypeLabel;
    }

    String getRawString() {
        return this.rawString;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getString() {
        StringBuilder sb = new StringBuilder();
        String left = HEADER_FOOTER_HELPER.getLeftSection(this.rawString);
        String center = HEADER_FOOTER_HELPER.getCenterSection(this.rawString);
        String right = HEADER_FOOTER_HELPER.getRightSection(this.rawString);
        if (left != null && !left.isEmpty()) {
            sb.append(left);
        }
        if (center != null && !center.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(Chars.SPACE);
            }
            sb.append(center);
        }
        if (right != null && !right.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(Chars.SPACE);
            }
            sb.append(right);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRawString(String rawString) {
        this.rawString = rawString;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isHeader() {
        return this.isHeader;
    }
}
