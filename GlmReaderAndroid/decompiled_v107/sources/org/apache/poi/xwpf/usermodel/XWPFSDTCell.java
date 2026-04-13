package org.apache.poi.xwpf.usermodel;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell;

/* loaded from: classes10.dex */
public class XWPFSDTCell extends XWPFAbstractSDT implements ICell {
    private final XWPFSDTContentCell cellContent;
    private final CTSdtCell sdtCell;

    public XWPFSDTCell(CTSdtCell sdtCell, XWPFTableRow xwpfTableRow, IBody part) {
        super(sdtCell.getSdtPr(), part);
        this.sdtCell = sdtCell;
        this.cellContent = new XWPFSDTContentCell(sdtCell.getSdtContent(), xwpfTableRow, part);
    }

    @Override // org.apache.poi.xwpf.usermodel.XWPFAbstractSDT
    public ISDTContent getContent() {
        return this.cellContent;
    }

    public CTSdtCell getCTSdtCell() {
        return this.sdtCell;
    }
}
