package org.apache.poi.xwpf.usermodel;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;

/* loaded from: classes10.dex */
public class XWPFSDT extends XWPFAbstractSDT implements IBodyElement, IRunBody, ISDTContents, IRunElement {
    private final ISDTContent content;

    public XWPFSDT(CTSdtRun sdtRun, IBody part) {
        super(sdtRun.getSdtPr(), part);
        this.content = new XWPFSDTContent(sdtRun.getSdtContent(), part, this);
    }

    public XWPFSDT(CTSdtBlock block, IBody part) {
        super(block.getSdtPr(), part);
        this.content = new XWPFSDTContent(block.getSdtContent(), part, this);
    }

    public XWPFSDT(CTSdtRow row, IBody part) {
        super(row.getSdtPr(), part);
        this.content = new XWPFSDTContent(row.getSdtContent(), part, this);
    }

    @Override // org.apache.poi.xwpf.usermodel.XWPFAbstractSDT
    public ISDTContent getContent() {
        return this.content;
    }
}
