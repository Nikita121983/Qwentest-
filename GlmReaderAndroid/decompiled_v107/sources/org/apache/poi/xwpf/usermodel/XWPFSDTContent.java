package org.apache.poi.xwpf.usermodel;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtContentBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtContentRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtContentRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;

/* loaded from: classes10.dex */
public class XWPFSDTContent implements ISDTContent {
    private final List<ISDTContents> bodyElements = new ArrayList();

    public XWPFSDTContent(CTSdtContentRun sdtRun, IBody part, IRunBody parent) {
        if (sdtRun == null) {
            return;
        }
        XmlCursor cursor = sdtRun.newCursor();
        try {
            cursor.selectPath("./*");
            while (cursor.toNextSelection()) {
                XmlObject o = cursor.getObject();
                if (o instanceof CTR) {
                    XWPFRun run = new XWPFRun((CTR) o, parent);
                    this.bodyElements.add(run);
                } else if (o instanceof CTSdtRun) {
                    XWPFSDT c = new XWPFSDT((CTSdtRun) o, part);
                    this.bodyElements.add(c);
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public XWPFSDTContent(CTSdtContentBlock block, IBody part, IRunBody parent) {
        if (block == null) {
            return;
        }
        XmlCursor cursor = block.newCursor();
        try {
            cursor.selectPath("./*");
            while (cursor.toNextSelection()) {
                XmlObject o = cursor.getObject();
                if (o instanceof CTP) {
                    XWPFParagraph p = new XWPFParagraph((CTP) o, part);
                    this.bodyElements.add(p);
                } else if (o instanceof CTTbl) {
                    XWPFTable t = new XWPFTable((CTTbl) o, part, false);
                    this.bodyElements.add(t);
                } else if (o instanceof CTSdtBlock) {
                    XWPFSDT c = new XWPFSDT((CTSdtBlock) o, part);
                    this.bodyElements.add(c);
                } else if (o instanceof CTR) {
                    XWPFRun run = new XWPFRun((CTR) o, parent);
                    this.bodyElements.add(run);
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public XWPFSDTContent(CTSdtContentRow sdtContentRow, IBody part, IRunBody parent) {
        if (sdtContentRow == null) {
            return;
        }
        XmlCursor cursor = sdtContentRow.newCursor();
        try {
            cursor.selectPath("./*");
            while (cursor.toNextSelection()) {
                XmlObject o = cursor.getObject();
                if (o instanceof CTSdtRow) {
                    XWPFSDT c = new XWPFSDT((CTSdtRow) o, part);
                    this.bodyElements.add(c);
                } else {
                    boolean z = o instanceof CTRow;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.poi.xwpf.usermodel.ISDTContent
    public String getText() {
        StringBuilder text = new StringBuilder();
        boolean addNewLine = false;
        for (int i = 0; i < this.bodyElements.size(); i++) {
            Object o = this.bodyElements.get(i);
            if (o instanceof XWPFParagraph) {
                appendParagraph((XWPFParagraph) o, text);
                addNewLine = true;
            } else if (o instanceof XWPFTable) {
                appendTable((XWPFTable) o, text);
                addNewLine = true;
            } else if (o instanceof XWPFSDT) {
                text.append(((XWPFSDT) o).getContent().getText());
                addNewLine = true;
            } else if (o instanceof XWPFRun) {
                text.append(o);
                addNewLine = false;
            }
            if (addNewLine && i < this.bodyElements.size() - 1) {
                text.append(StringUtils.LF);
            }
        }
        return text.toString();
    }

    private void appendTable(XWPFTable table, StringBuilder text) {
        for (XWPFTableRow row : table.getRows()) {
            List<ICell> cells = row.getTableICells();
            for (int i = 0; i < cells.size(); i++) {
                ICell cell = cells.get(i);
                if (cell instanceof XWPFTableCell) {
                    text.append(((XWPFTableCell) cell).getTextRecursively());
                } else if (cell instanceof XWPFSDTCell) {
                    text.append(((XWPFSDTCell) cell).getContent().getText());
                }
                if (i < cells.size() - 1) {
                    text.append("\t");
                }
            }
            text.append('\n');
        }
    }

    private void appendParagraph(XWPFParagraph paragraph, StringBuilder text) {
        for (IRunElement run : paragraph.getRuns()) {
            text.append(run);
        }
    }

    @Override // org.apache.poi.xwpf.usermodel.ISDTContent
    public String toString() {
        return getText();
    }
}
