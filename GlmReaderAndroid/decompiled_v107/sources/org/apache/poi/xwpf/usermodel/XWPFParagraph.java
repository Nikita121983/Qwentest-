package org.apache.poi.xwpf.usermodel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.Paragraph;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSmartTagRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextAlignment;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTextAlignment;

/* loaded from: classes10.dex */
public class XWPFParagraph implements IBodyElement, IRunBody, ISDTContents, Paragraph {
    protected XWPFDocument document;
    private final StringBuilder footnoteText = new StringBuilder(64);
    protected List<IRunElement> iruns;
    private final CTP paragraph;
    protected IBody part;
    protected List<XWPFRun> runs;

    public XWPFParagraph(CTP prgrph, IBody part) {
        XWPFAbstractFootnoteEndnote footnote;
        this.paragraph = prgrph;
        this.part = part;
        this.document = part.getXWPFDocument();
        if (this.document == null) {
            throw new NullPointerException("null document in XWPFParagraph");
        }
        this.runs = new ArrayList();
        this.iruns = new ArrayList();
        buildRunsInOrderFromXml(this.paragraph);
        for (XWPFRun run : this.runs) {
            CTR r = run.getCTR();
            XmlCursor c = r.newCursor();
            try {
                c.selectPath("child::*");
                while (c.toNextSelection()) {
                    XmlObject o = c.getObject();
                    if (o instanceof CTFtnEdnRef) {
                        CTFtnEdnRef ftn = (CTFtnEdnRef) o;
                        BigInteger id = ftn.getId();
                        this.footnoteText.append(" [").append(id).append(": ");
                        int i = 0;
                        if (ftn.getDomNode().getLocalName().equals("footnoteReference")) {
                            XWPFDocument xWPFDocument = this.document;
                            if (id != null) {
                                i = id.intValue();
                            }
                            footnote = xWPFDocument.getFootnoteByID(i);
                        } else {
                            XWPFDocument xWPFDocument2 = this.document;
                            if (id != null) {
                                i = id.intValue();
                            }
                            footnote = xWPFDocument2.getEndnoteByID(i);
                        }
                        if (footnote != null) {
                            boolean first = true;
                            for (XWPFParagraph p : footnote.getParagraphs()) {
                                if (!first) {
                                    this.footnoteText.append(StringUtils.LF);
                                }
                                first = false;
                                this.footnoteText.append(p.getText());
                            }
                        } else {
                            this.footnoteText.append("!!! End note with ID \"").append(id).append("\" not found in document.");
                        }
                        this.footnoteText.append("] ");
                    }
                }
                if (c != null) {
                    c.close();
                }
            } finally {
            }
        }
    }

    private void buildRunsInOrderFromXml(XmlObject object) {
        XmlCursor c = object.newCursor();
        try {
            c.selectPath("child::*");
            while (c.toNextSelection()) {
                XmlObject o = c.getObject();
                if (o instanceof CTR) {
                    XWPFRun r = new XWPFRun((CTR) o, (IRunBody) this);
                    this.runs.add(r);
                    this.iruns.add(r);
                }
                if (o instanceof CTHyperlink) {
                    CTHyperlink link = (CTHyperlink) o;
                    for (CTR r2 : link.getRArray()) {
                        XWPFHyperlinkRun hr = new XWPFHyperlinkRun(link, r2, this);
                        this.runs.add(hr);
                        this.iruns.add(hr);
                    }
                }
                if (o instanceof CTSimpleField) {
                    CTSimpleField field = (CTSimpleField) o;
                    for (CTR r3 : field.getRArray()) {
                        XWPFFieldRun fr = new XWPFFieldRun(field, r3, this);
                        this.runs.add(fr);
                        this.iruns.add(fr);
                    }
                }
                if (o instanceof CTSdtBlock) {
                    XWPFSDT cc = new XWPFSDT((CTSdtBlock) o, this.part);
                    this.iruns.add(cc);
                }
                if (o instanceof CTSdtRun) {
                    XWPFSDT cc2 = new XWPFSDT((CTSdtRun) o, this.part);
                    this.iruns.add(cc2);
                }
                if (o instanceof CTRunTrackChange) {
                    CTRunTrackChange parentRecord = (CTRunTrackChange) o;
                    for (CTR r4 : parentRecord.getRArray()) {
                        XWPFRun cr = new XWPFRun(r4, (IRunBody) this);
                        this.runs.add(cr);
                        this.iruns.add(cr);
                    }
                    for (CTRunTrackChange change : parentRecord.getInsArray()) {
                        buildRunsInOrderFromXml(change);
                    }
                }
                if (o instanceof CTSmartTagRun) {
                    buildRunsInOrderFromXml(o);
                }
            }
            if (c != null) {
                c.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (c != null) {
                    try {
                        c.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Internal
    public CTP getCTP() {
        return this.paragraph;
    }

    public List<XWPFRun> getRuns() {
        return Collections.unmodifiableList(this.runs);
    }

    public boolean runsIsEmpty() {
        return this.runs.isEmpty();
    }

    public List<IRunElement> getIRuns() {
        return Collections.unmodifiableList(this.iruns);
    }

    public boolean isEmpty() {
        return !this.paragraph.getDomNode().hasChildNodes();
    }

    @Override // org.apache.poi.xwpf.usermodel.IRunBody
    public XWPFDocument getDocument() {
        return this.document;
    }

    public String getText() {
        StringBuilder out = new StringBuilder(64);
        for (IRunElement run : this.iruns) {
            if (run instanceof XWPFRun) {
                XWPFRun xRun = (XWPFRun) run;
                if (xRun.getCTR().sizeOfDelTextArray() == 0) {
                    out.append(xRun);
                }
            } else if (run instanceof XWPFSDT) {
                out.append(((XWPFSDT) run).getContent().getText());
            } else {
                out.append(run);
            }
        }
        out.append(this.footnoteText);
        return out.toString();
    }

    public String getStyleID() {
        if (this.paragraph.getPPr() != null && this.paragraph.getPPr().getPStyle() != null && this.paragraph.getPPr().getPStyle().getVal() != null) {
            return this.paragraph.getPPr().getPStyle().getVal();
        }
        return null;
    }

    public BigInteger getNumID() {
        if (this.paragraph.getPPr() != null && this.paragraph.getPPr().getNumPr() != null && this.paragraph.getPPr().getNumPr().getNumId() != null) {
            return this.paragraph.getPPr().getNumPr().getNumId().getVal();
        }
        return null;
    }

    public void setNumID(BigInteger numPos) {
        if (this.paragraph.getPPr() == null) {
            this.paragraph.addNewPPr();
        }
        if (this.paragraph.getPPr().getNumPr() == null) {
            this.paragraph.getPPr().addNewNumPr();
        }
        if (this.paragraph.getPPr().getNumPr().getNumId() == null) {
            this.paragraph.getPPr().getNumPr().addNewNumId();
        }
        this.paragraph.getPPr().getNumPr().getNumId().setVal(numPos);
    }

    public void setNumILvl(BigInteger iLvl) {
        if (this.paragraph.getPPr() == null) {
            this.paragraph.addNewPPr();
        }
        if (this.paragraph.getPPr().getNumPr() == null) {
            this.paragraph.getPPr().addNewNumPr();
        }
        if (this.paragraph.getPPr().getNumPr().getIlvl() == null) {
            this.paragraph.getPPr().getNumPr().addNewIlvl();
        }
        this.paragraph.getPPr().getNumPr().getIlvl().setVal(iLvl);
    }

    public BigInteger getNumIlvl() {
        if (this.paragraph.getPPr() != null && this.paragraph.getPPr().getNumPr() != null && this.paragraph.getPPr().getNumPr().getIlvl() != null) {
            return this.paragraph.getPPr().getNumPr().getIlvl().getVal();
        }
        return null;
    }

    public String getNumFmt() {
        XWPFNum num;
        BigInteger numID = getNumID();
        XWPFNumbering numbering = this.document.getNumbering();
        if (numID != null && numbering != null && (num = numbering.getNum(numID)) != null) {
            BigInteger ilvl = getNumIlvl();
            BigInteger abstractNumId = num.getCTNum().getAbstractNumId().getVal();
            CTAbstractNum anum = numbering.getAbstractNum(abstractNumId).getAbstractNum();
            CTLvl level = null;
            int i = 0;
            while (true) {
                if (i >= anum.sizeOfLvlArray()) {
                    break;
                }
                CTLvl lvl = anum.getLvlArray(i);
                if (!lvl.getIlvl().equals(ilvl)) {
                    i++;
                } else {
                    level = lvl;
                    break;
                }
            }
            if (level != null && level.getNumFmt() != null && level.getNumFmt().getVal() != null) {
                return level.getNumFmt().getVal().toString();
            }
            return null;
        }
        return null;
    }

    public String getNumLevelText() {
        XWPFNum num;
        CTDecimalNumber ctDecimalNumber;
        BigInteger abstractNumId;
        XWPFAbstractNum xwpfAbstractNum;
        CTAbstractNum anum;
        BigInteger numID = getNumID();
        XWPFNumbering numbering = this.document.getNumbering();
        if (numID != null && numbering != null && (num = numbering.getNum(numID)) != null) {
            BigInteger ilvl = getNumIlvl();
            CTNum ctNum = num.getCTNum();
            if (ctNum == null || (ctDecimalNumber = ctNum.getAbstractNumId()) == null || (abstractNumId = ctDecimalNumber.getVal()) == null || (xwpfAbstractNum = numbering.getAbstractNum(abstractNumId)) == null || (anum = xwpfAbstractNum.getCTAbstractNum()) == null) {
                return null;
            }
            CTLvl level = null;
            int i = 0;
            while (true) {
                if (i < anum.sizeOfLvlArray()) {
                    CTLvl lvl = anum.getLvlArray(i);
                    if (lvl == null || lvl.getIlvl() == null || !lvl.getIlvl().equals(ilvl)) {
                        i++;
                    } else {
                        level = lvl;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (level != null && level.getLvlText() != null && level.getLvlText().getVal() != null) {
                return level.getLvlText().getVal();
            }
        }
        return null;
    }

    public BigInteger getNumStartOverride() {
        XWPFNum num;
        CTNum ctNum;
        BigInteger numID = getNumID();
        XWPFNumbering numbering = this.document.getNumbering();
        if (numID == null || numbering == null || (num = numbering.getNum(numID)) == null || (ctNum = num.getCTNum()) == null) {
            return null;
        }
        BigInteger ilvl = getNumIlvl();
        CTNumLvl level = null;
        int i = 0;
        while (true) {
            if (i < ctNum.sizeOfLvlOverrideArray()) {
                CTNumLvl ctNumLvl = ctNum.getLvlOverrideArray(i);
                if (ctNumLvl == null || ctNumLvl.getIlvl() == null || !ctNumLvl.getIlvl().equals(ilvl)) {
                    i++;
                } else {
                    level = ctNumLvl;
                    break;
                }
            } else {
                break;
            }
        }
        if (level != null && level.getStartOverride() != null) {
            return level.getStartOverride().getVal();
        }
        return null;
    }

    public boolean isKeepNext() {
        if (getCTP() != null && getCTP().getPPr() != null && getCTP().getPPr().isSetKeepNext()) {
            return POIXMLUnits.parseOnOff(getCTP().getPPr().getKeepNext().xgetVal());
        }
        return false;
    }

    public void setKeepNext(boolean keepNext) {
        CTOnOff state = CTOnOff.Factory.newInstance();
        state.setVal(keepNext ? STOnOff1.ON : STOnOff1.OFF);
        getCTP().getPPr().setKeepNext(state);
    }

    public String getParagraphText() {
        StringBuilder out = new StringBuilder(64);
        for (XWPFRun run : this.runs) {
            out.append(run);
        }
        return out.toString();
    }

    public String getPictureText() {
        StringBuilder out = new StringBuilder(64);
        for (XWPFRun run : this.runs) {
            out.append(run.getPictureText());
        }
        return out.toString();
    }

    public String getFootnoteText() {
        return this.footnoteText.toString();
    }

    public ParagraphAlignment getAlignment() {
        CTPPr pr = getCTPPr(false);
        if (pr == null || !pr.isSetJc()) {
            return ParagraphAlignment.LEFT;
        }
        return ParagraphAlignment.valueOf(pr.getJc().getVal().intValue());
    }

    public void setAlignment(ParagraphAlignment align) {
        if (align == null) {
            CTPPr pr = getCTPPr(false);
            if (pr != null) {
                pr.unsetJc();
                return;
            }
            return;
        }
        CTPPr pr2 = getCTPPr(true);
        CTJc jc = pr2.isSetJc() ? pr2.getJc() : pr2.addNewJc();
        STJc.Enum en = STJc.Enum.forInt(align.getValue());
        jc.setVal(en);
    }

    public boolean isAlignmentSet() {
        CTPPr pr = getCTPPr(false);
        return pr != null && pr.isSetJc();
    }

    @Override // org.apache.poi.wp.usermodel.Paragraph
    public int getFontAlignment() {
        return getAlignment().getValue();
    }

    @Override // org.apache.poi.wp.usermodel.Paragraph
    public void setFontAlignment(int align) {
        ParagraphAlignment pAlign = ParagraphAlignment.valueOf(align);
        setAlignment(pAlign);
    }

    public TextAlignment getVerticalAlignment() {
        CTPPr pr = getCTPPr(false);
        if (pr == null || !pr.isSetTextAlignment()) {
            return TextAlignment.AUTO;
        }
        return TextAlignment.valueOf(pr.getTextAlignment().getVal().intValue());
    }

    public void setVerticalAlignment(TextAlignment valign) {
        CTPPr pr = getCTPPr();
        CTTextAlignment textAlignment = pr.isSetTextAlignment() ? pr.getTextAlignment() : pr.addNewTextAlignment();
        STTextAlignment.Enum en = STTextAlignment.Enum.forInt(valign.getValue());
        textAlignment.setVal(en);
    }

    public Borders getBorderTop() {
        CTPBdr border = getCTPBrd(false);
        CTBorder ct = null;
        if (border != null) {
            ct = border.getTop();
        }
        STBorder.Enum ptrn = ct != null ? ct.getVal() : STBorder.NONE;
        return Borders.valueOf(ptrn.intValue());
    }

    public void setBorderTop(Borders border) {
        CTPBdr ct = getCTPBrd(true);
        if (ct == null) {
            throw new IllegalStateException("invalid paragraph state");
        }
        CTBorder pr = ct.isSetTop() ? ct.getTop() : ct.addNewTop();
        if (border.getValue() == Borders.NONE.getValue()) {
            ct.unsetTop();
        } else {
            pr.setVal(STBorder.Enum.forInt(border.getValue()));
        }
    }

    public Borders getBorderBottom() {
        CTPBdr border = getCTPBrd(false);
        CTBorder ct = null;
        if (border != null) {
            ct = border.getBottom();
        }
        STBorder.Enum ptrn = ct != null ? ct.getVal() : STBorder.NONE;
        return Borders.valueOf(ptrn.intValue());
    }

    public void setBorderBottom(Borders border) {
        CTPBdr ct = getCTPBrd(true);
        CTBorder pr = ct.isSetBottom() ? ct.getBottom() : ct.addNewBottom();
        if (border.getValue() == Borders.NONE.getValue()) {
            ct.unsetBottom();
        } else {
            pr.setVal(STBorder.Enum.forInt(border.getValue()));
        }
    }

    public Borders getBorderLeft() {
        CTPBdr border = getCTPBrd(false);
        CTBorder ct = null;
        if (border != null) {
            ct = border.getLeft();
        }
        STBorder.Enum ptrn = ct != null ? ct.getVal() : STBorder.NONE;
        return Borders.valueOf(ptrn.intValue());
    }

    public void setBorderLeft(Borders border) {
        CTPBdr ct = getCTPBrd(true);
        CTBorder pr = ct.isSetLeft() ? ct.getLeft() : ct.addNewLeft();
        if (border.getValue() == Borders.NONE.getValue()) {
            ct.unsetLeft();
        } else {
            pr.setVal(STBorder.Enum.forInt(border.getValue()));
        }
    }

    public Borders getBorderRight() {
        CTPBdr border = getCTPBrd(false);
        CTBorder ct = null;
        if (border != null) {
            ct = border.getRight();
        }
        STBorder.Enum ptrn = ct != null ? ct.getVal() : STBorder.NONE;
        return Borders.valueOf(ptrn.intValue());
    }

    public void setBorderRight(Borders border) {
        CTPBdr ct = getCTPBrd(true);
        CTBorder pr = ct.isSetRight() ? ct.getRight() : ct.addNewRight();
        if (border.getValue() == Borders.NONE.getValue()) {
            ct.unsetRight();
        } else {
            pr.setVal(STBorder.Enum.forInt(border.getValue()));
        }
    }

    public Borders getBorderBetween() {
        CTPBdr border = getCTPBrd(false);
        CTBorder ct = null;
        if (border != null) {
            ct = border.getBetween();
        }
        STBorder.Enum ptrn = ct != null ? ct.getVal() : STBorder.NONE;
        return Borders.valueOf(ptrn.intValue());
    }

    public void setBorderBetween(Borders border) {
        CTPBdr ct = getCTPBrd(true);
        CTBorder pr = ct.isSetBetween() ? ct.getBetween() : ct.addNewBetween();
        if (border.getValue() == Borders.NONE.getValue()) {
            ct.unsetBetween();
        } else {
            pr.setVal(STBorder.Enum.forInt(border.getValue()));
        }
    }

    public boolean isPageBreak() {
        CTPPr ppr = getCTPPr(false);
        if (ppr == null) {
            return false;
        }
        CTOnOff ctPageBreak = ppr.isSetPageBreakBefore() ? ppr.getPageBreakBefore() : null;
        if (ctPageBreak == null) {
            return false;
        }
        return POIXMLUnits.parseOnOff(ctPageBreak.xgetVal());
    }

    public void setPageBreak(boolean pageBreak) {
        CTPPr ppr = getCTPPr();
        CTOnOff ctPageBreak = ppr.isSetPageBreakBefore() ? ppr.getPageBreakBefore() : ppr.addNewPageBreakBefore();
        ctPageBreak.setVal(pageBreak ? STOnOff1.ON : STOnOff1.OFF);
    }

    public int getSpacingAfter() {
        CTSpacing spacing = getCTSpacing(false);
        if (spacing == null || !spacing.isSetAfter()) {
            return -1;
        }
        return (int) Units.toDXA(POIXMLUnits.parseLength(spacing.xgetAfter()));
    }

    public void setSpacingAfter(int spaces) {
        CTSpacing spacing = getCTSpacing(true);
        if (spacing != null) {
            BigInteger bi = new BigInteger(Integer.toString(spaces));
            spacing.setAfter(bi);
        }
    }

    public int getSpacingAfterLines() {
        CTSpacing spacing = getCTSpacing(false);
        if (spacing == null || !spacing.isSetAfterLines()) {
            return -1;
        }
        return spacing.getAfterLines().intValue();
    }

    public void setSpacingAfterLines(int spaces) {
        CTSpacing spacing = getCTSpacing(true);
        BigInteger bi = new BigInteger(Integer.toString(spaces));
        spacing.setAfterLines(bi);
    }

    public int getSpacingBefore() {
        CTSpacing spacing = getCTSpacing(false);
        if (spacing == null || !spacing.isSetBefore()) {
            return -1;
        }
        return (int) Units.toDXA(POIXMLUnits.parseLength(spacing.xgetBefore()));
    }

    public void setSpacingBefore(int spaces) {
        CTSpacing spacing = getCTSpacing(true);
        BigInteger bi = new BigInteger(Integer.toString(spaces));
        spacing.setBefore(bi);
    }

    public int getSpacingBeforeLines() {
        CTSpacing spacing = getCTSpacing(false);
        if (spacing == null || !spacing.isSetBeforeLines()) {
            return -1;
        }
        return spacing.getBeforeLines().intValue();
    }

    public void setSpacingBeforeLines(int spaces) {
        CTSpacing spacing = getCTSpacing(true);
        BigInteger bi = new BigInteger(Integer.toString(spaces));
        spacing.setBeforeLines(bi);
    }

    public LineSpacingRule getSpacingLineRule() {
        CTSpacing spacing = getCTSpacing(false);
        return (spacing == null || !spacing.isSetLineRule()) ? LineSpacingRule.AUTO : LineSpacingRule.valueOf(spacing.getLineRule().intValue());
    }

    public void setSpacingLineRule(LineSpacingRule rule) {
        CTSpacing spacing = getCTSpacing(true);
        spacing.setLineRule(STLineSpacingRule.Enum.forInt(rule.getValue()));
    }

    public double getSpacingBetween() {
        CTSpacing spacing = getCTSpacing(false);
        if (spacing == null || !spacing.isSetLine()) {
            return -1.0d;
        }
        double twips = Units.toDXA(POIXMLUnits.parseLength(spacing.xgetLine()));
        return twips / ((spacing.getLineRule() == null || spacing.getLineRule() == STLineSpacingRule.AUTO) ? 240 : 20);
    }

    public void setSpacingBetween(double spacing, LineSpacingRule rule) {
        CTSpacing ctSp = getCTSpacing(true);
        if (rule == LineSpacingRule.AUTO) {
            ctSp.setLine(new BigInteger(String.valueOf(Math.round(240.0d * spacing))));
        } else {
            ctSp.setLine(new BigInteger(String.valueOf(Math.round(20.0d * spacing))));
        }
        ctSp.setLineRule(STLineSpacingRule.Enum.forInt(rule.getValue()));
    }

    public void setSpacingBetween(double spacing) {
        setSpacingBetween(spacing, LineSpacingRule.AUTO);
    }

    public int getIndentationLeft() {
        CTInd indentation = getCTInd(false);
        if (indentation == null || !indentation.isSetLeft()) {
            return -1;
        }
        return (int) Units.toDXA(POIXMLUnits.parseLength(indentation.xgetLeft()));
    }

    public void setIndentationLeft(int indentation) {
        CTInd indent = getCTInd(true);
        BigInteger bi = new BigInteger(Integer.toString(indentation));
        indent.setLeft(bi);
    }

    public int getIndentationLeftChars() {
        CTInd indentation = getCTInd(false);
        if (indentation == null || !indentation.isSetLeftChars()) {
            return -1;
        }
        return indentation.getLeftChars().intValue();
    }

    public void setIndentationLeftChars(int indentation) {
        CTInd indent = getCTInd(true);
        BigInteger bi = new BigInteger(Integer.toString(indentation));
        indent.setLeftChars(bi);
    }

    public int getIndentationRight() {
        CTInd indentation = getCTInd(false);
        if (indentation == null || !indentation.isSetRight()) {
            return -1;
        }
        return (int) Units.toDXA(POIXMLUnits.parseLength(indentation.xgetRight()));
    }

    public void setIndentationRight(int indentation) {
        CTInd indent = getCTInd(true);
        BigInteger bi = new BigInteger(Integer.toString(indentation));
        indent.setRight(bi);
    }

    public int getIndentationRightChars() {
        CTInd indentation = getCTInd(false);
        if (indentation == null || !indentation.isSetRightChars()) {
            return -1;
        }
        return indentation.getRightChars().intValue();
    }

    public void setIndentationRightChars(int indentation) {
        CTInd indent = getCTInd(true);
        BigInteger bi = new BigInteger(Integer.toString(indentation));
        indent.setRightChars(bi);
    }

    public int getIndentationHanging() {
        CTInd indentation = getCTInd(false);
        if (indentation == null || !indentation.isSetHanging()) {
            return -1;
        }
        return (int) Units.toDXA(POIXMLUnits.parseLength(indentation.xgetHanging()));
    }

    public void setIndentationHanging(int indentation) {
        CTInd indent = getCTInd(true);
        BigInteger bi = new BigInteger(Integer.toString(indentation));
        indent.setHanging(bi);
    }

    public int getIndentationFirstLine() {
        CTInd indentation = getCTInd(false);
        if (indentation == null || !indentation.isSetFirstLine()) {
            return -1;
        }
        return (int) Units.toDXA(POIXMLUnits.parseLength(indentation.xgetFirstLine()));
    }

    public void setIndentationFirstLine(int indentation) {
        CTInd indent = getCTInd(true);
        BigInteger bi = new BigInteger(Integer.toString(indentation));
        indent.setFirstLine(bi);
    }

    @Override // org.apache.poi.wp.usermodel.Paragraph
    public int getIndentFromLeft() {
        return getIndentationLeft();
    }

    @Override // org.apache.poi.wp.usermodel.Paragraph
    public void setIndentFromLeft(int dxaLeft) {
        setIndentationLeft(dxaLeft);
    }

    @Override // org.apache.poi.wp.usermodel.Paragraph
    public int getIndentFromRight() {
        return getIndentationRight();
    }

    @Override // org.apache.poi.wp.usermodel.Paragraph
    public void setIndentFromRight(int dxaRight) {
        setIndentationRight(dxaRight);
    }

    @Override // org.apache.poi.wp.usermodel.Paragraph
    public int getFirstLineIndent() {
        return getIndentationFirstLine();
    }

    @Override // org.apache.poi.wp.usermodel.Paragraph
    public void setFirstLineIndent(int first) {
        setIndentationFirstLine(first);
    }

    @Override // org.apache.poi.wp.usermodel.Paragraph
    public boolean isWordWrapped() {
        CTPPr ppr = getCTPPr(false);
        return ppr != null && ppr.isSetWordWrap() && POIXMLUnits.parseOnOff(ppr.getWordWrap());
    }

    @Override // org.apache.poi.wp.usermodel.Paragraph
    public void setWordWrapped(boolean wrap) {
        CTPPr ppr = getCTPPr();
        if (wrap) {
            CTOnOff wordWrap = ppr.isSetWordWrap() ? ppr.getWordWrap() : ppr.addNewWordWrap();
            wordWrap.setVal(STOnOff1.ON);
        } else if (ppr.isSetWordWrap()) {
            ppr.unsetWordWrap();
        }
    }

    public boolean isWordWrap() {
        return isWordWrapped();
    }

    @Deprecated
    public void setWordWrap(boolean wrap) {
        setWordWrapped(wrap);
    }

    public String getStyle() {
        CTPPr pr = getCTPPr(false);
        if (pr == null) {
            return null;
        }
        CTString style = pr.isSetPStyle() ? pr.getPStyle() : null;
        if (style != null) {
            return style.getVal();
        }
        return null;
    }

    public void setStyle(String styleId) {
        CTPPr pr = getCTPPr();
        CTString style = pr.getPStyle() != null ? pr.getPStyle() : pr.addNewPStyle();
        style.setVal(styleId);
    }

    private CTPBdr getCTPBrd(boolean create) {
        CTPPr pr = getCTPPr(create);
        if (pr == null) {
            return null;
        }
        CTPBdr ct = pr.isSetPBdr() ? pr.getPBdr() : null;
        if (create && ct == null) {
            return pr.addNewPBdr();
        }
        return ct;
    }

    private CTSpacing getCTSpacing(boolean create) {
        CTPPr pr = getCTPPr(create);
        if (pr == null) {
            return null;
        }
        CTSpacing ct = pr.getSpacing();
        if (create && ct == null) {
            return pr.addNewSpacing();
        }
        return ct;
    }

    private CTInd getCTInd(boolean create) {
        CTPPr pr = getCTPPr(create);
        if (pr == null) {
            return null;
        }
        CTInd ct = pr.getInd();
        if (create && ct == null) {
            return pr.addNewInd();
        }
        return ct;
    }

    @Internal
    public CTPPr getCTPPr() {
        return getCTPPr(true);
    }

    private CTPPr getCTPPr(boolean create) {
        if (this.paragraph.isSetPPr() || !create) {
            return this.paragraph.getPPr();
        }
        return this.paragraph.addNewPPr();
    }

    protected void addRun(CTR run) {
        int pos = this.paragraph.sizeOfRArray();
        this.paragraph.addNewR();
        this.paragraph.setRArray(pos, run);
    }

    public XWPFRun createRun() {
        XWPFRun xwpfRun = new XWPFRun(this.paragraph.addNewR(), (IRunBody) this);
        this.runs.add(xwpfRun);
        this.iruns.add(xwpfRun);
        return xwpfRun;
    }

    public XWPFHyperlinkRun createHyperlinkRun(String uri) {
        String rId = getPart().getPackagePart().addExternalRelationship(uri, XWPFRelation.HYPERLINK.getRelation()).getId();
        CTHyperlink ctHyperLink = getCTP().addNewHyperlink();
        ctHyperLink.setId(rId);
        ctHyperLink.addNewR();
        XWPFHyperlinkRun link = new XWPFHyperlinkRun(ctHyperLink, ctHyperLink.getRArray(0), this);
        this.runs.add(link);
        this.iruns.add(link);
        return link;
    }

    public XWPFFieldRun createFieldRun() {
        CTSimpleField ctSimpleField = this.paragraph.addNewFldSimple();
        XWPFFieldRun newRun = new XWPFFieldRun(ctSimpleField, ctSimpleField.addNewR(), this);
        this.runs.add(newRun);
        this.iruns.add(newRun);
        return newRun;
    }

    public XWPFRun insertNewRun(int pos) {
        if (pos == this.runs.size()) {
            return createRun();
        }
        return insertNewProvidedRun(pos, new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFParagraph$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XWPFParagraph.this.m2589xaa5ab5f4((XmlCursor) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$insertNewRun$0$org-apache-poi-xwpf-usermodel-XWPFParagraph, reason: not valid java name */
    public /* synthetic */ XWPFRun m2589xaa5ab5f4(XmlCursor newCursor) {
        String uri = CTR.type.getName().getNamespaceURI();
        newCursor.beginElement("r", uri);
        newCursor.toParent();
        CTR r = (CTR) newCursor.getObject();
        return new XWPFRun(r, (IRunBody) this);
    }

    public XWPFHyperlinkRun insertNewHyperlinkRun(int pos, String uri) {
        if (pos == this.runs.size()) {
            return createHyperlinkRun(uri);
        }
        XWPFHyperlinkRun newRun = (XWPFHyperlinkRun) insertNewProvidedRun(pos, new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFParagraph$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XWPFParagraph.this.m2588x5e708e43((XmlCursor) obj);
            }
        });
        if (newRun != null) {
            String rId = getPart().getPackagePart().addExternalRelationship(uri, XWPFRelation.HYPERLINK.getRelation()).getId();
            newRun.getCTHyperlink().setId(rId);
        }
        return newRun;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$insertNewHyperlinkRun$1$org-apache-poi-xwpf-usermodel-XWPFParagraph, reason: not valid java name */
    public /* synthetic */ XWPFHyperlinkRun m2588x5e708e43(XmlCursor newCursor) {
        String namespaceURI = CTHyperlink.type.getName().getNamespaceURI();
        newCursor.beginElement("hyperlink", namespaceURI);
        newCursor.toParent();
        CTHyperlink ctHyperLink = (CTHyperlink) newCursor.getObject();
        return new XWPFHyperlinkRun(ctHyperLink, ctHyperLink.addNewR(), this);
    }

    public XWPFFieldRun insertNewFieldRun(int pos) {
        if (pos == this.runs.size()) {
            return createFieldRun();
        }
        return (XWPFFieldRun) insertNewProvidedRun(pos, new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFParagraph$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XWPFParagraph.this.m2587xfc63b7b0((XmlCursor) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$insertNewFieldRun$2$org-apache-poi-xwpf-usermodel-XWPFParagraph, reason: not valid java name */
    public /* synthetic */ XWPFFieldRun m2587xfc63b7b0(XmlCursor newCursor) {
        String uri = CTSimpleField.type.getName().getNamespaceURI();
        newCursor.beginElement("fldSimple", uri);
        newCursor.toParent();
        CTSimpleField ctSimpleField = (CTSimpleField) newCursor.getObject();
        return new XWPFFieldRun(ctSimpleField, ctSimpleField.addNewR(), this);
    }

    private <T extends XWPFRun> T insertNewProvidedRun(int pos, Function<XmlCursor, T> provider) {
        if (pos >= 0 && pos < this.runs.size()) {
            XWPFRun run = this.runs.get(pos);
            CTR ctr = run.getCTR();
            XmlCursor newCursor = ctr.newCursor();
            try {
                if (!isCursorInParagraph(newCursor)) {
                    newCursor.toParent();
                }
                if (isCursorInParagraph(newCursor)) {
                    T newRun = provider.apply(newCursor);
                    int iPos = this.iruns.size();
                    int oldAt = this.iruns.indexOf(run);
                    if (oldAt != -1) {
                        iPos = oldAt;
                    }
                    this.iruns.add(iPos, newRun);
                    this.runs.add(pos, newRun);
                    if (newCursor != null) {
                        newCursor.close();
                    }
                    return newRun;
                }
                if (newCursor != null) {
                    newCursor.close();
                    return null;
                }
                return null;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (newCursor != null) {
                        try {
                            newCursor.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        return null;
    }

    private boolean isCursorInParagraph(XmlCursor cursor) {
        XmlCursor verify = cursor.newCursor();
        try {
            verify.toParent();
            boolean z = verify.getObject() == this.paragraph;
            if (verify != null) {
                verify.close();
            }
            return z;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (verify != null) {
                    try {
                        verify.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0098, code lost:
    
        r3 = new org.apache.poi.xwpf.usermodel.TextSegment();
        r3.setBeginRun(r5);
        r3.setBeginText(r7);
        r3.setBeginChar(r8);
        r3.setEndRun(r12);
        r3.setEndText(r13);
        r3.setEndChar(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00b0, code lost:
    
        if (r15 == null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b2, code lost:
    
        r15.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b5, code lost:
    
        return r3;
     */
    /* JADX WARN: Incorrect condition in loop: B:3:0x0023 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.poi.xwpf.usermodel.TextSegment searchText(java.lang.String r23, org.apache.poi.xwpf.usermodel.PositionInParagraph r24) {
        /*
            Method dump skipped, instructions count: 294
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFParagraph.searchText(java.lang.String, org.apache.poi.xwpf.usermodel.PositionInParagraph):org.apache.poi.xwpf.usermodel.TextSegment");
    }

    public String getText(TextSegment segment) {
        int runBegin = segment.getBeginRun();
        int textBegin = segment.getBeginText();
        int charBegin = segment.getBeginChar();
        int runEnd = segment.getEndRun();
        int textEnd = segment.getEndText();
        int charEnd = segment.getEndChar();
        StringBuilder out = new StringBuilder();
        CTR[] rArray = this.paragraph.getRArray();
        for (int i = runBegin; i <= runEnd; i++) {
            CTText[] tArray = rArray[i].getTArray();
            int startText = 0;
            int endText = tArray.length - 1;
            if (i == runBegin) {
                startText = textBegin;
            }
            if (i == runEnd) {
                endText = textEnd;
            }
            int j = startText;
            while (j <= endText) {
                String tmpText = tArray[j].getStringValue();
                int startChar = 0;
                int endChar = tmpText.length() - 1;
                if (j == textBegin && i == runBegin) {
                    startChar = charBegin;
                }
                if (j == textEnd && i == runEnd) {
                    endChar = charEnd;
                }
                out.append((CharSequence) tmpText, startChar, endChar + 1);
                j++;
                runBegin = runBegin;
            }
        }
        return out.toString();
    }

    public boolean removeRun(int pos) {
        XmlCursor c;
        if (pos >= 0 && pos < this.runs.size()) {
            XWPFRun run = this.runs.get(pos);
            if ((run instanceof XWPFHyperlinkRun) && isTheOnlyCTHyperlinkInRuns((XWPFHyperlinkRun) run)) {
                c = ((XWPFHyperlinkRun) run).getCTHyperlink().newCursor();
                try {
                    c.removeXml();
                    if (c != null) {
                        c.close();
                    }
                    this.runs.remove(pos);
                    this.iruns.remove(run);
                    return true;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } finally {
                    }
                }
            }
            if ((run instanceof XWPFFieldRun) && isTheOnlyCTFieldInRuns((XWPFFieldRun) run)) {
                c = ((XWPFFieldRun) run).getCTField().newCursor();
                try {
                    c.removeXml();
                    if (c != null) {
                        c.close();
                    }
                    this.runs.remove(pos);
                    this.iruns.remove(run);
                    return true;
                } catch (Throwable th2) {
                    try {
                        throw th2;
                    } finally {
                    }
                }
            }
            XmlCursor c2 = run.getCTR().newCursor();
            try {
                c2.removeXml();
                if (c2 != null) {
                    c2.close();
                }
                this.runs.remove(pos);
                this.iruns.remove(run);
                return true;
            } catch (Throwable th3) {
                try {
                    throw th3;
                } finally {
                    if (c2 != null) {
                        try {
                            c2.close();
                        } catch (Throwable th4) {
                            th3.addSuppressed(th4);
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isTheOnlyCTHyperlinkInRuns(XWPFHyperlinkRun run) {
        final CTHyperlink ctHyperlink = run.getCTHyperlink();
        long count = this.runs.stream().filter(new Predicate() { // from class: org.apache.poi.xwpf.usermodel.XWPFParagraph$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return XWPFParagraph.lambda$isTheOnlyCTHyperlinkInRuns$3(CTHyperlink.this, (XWPFRun) obj);
            }
        }).count();
        return count <= 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$isTheOnlyCTHyperlinkInRuns$3(CTHyperlink ctHyperlink, XWPFRun r) {
        return (r instanceof XWPFHyperlinkRun) && ctHyperlink == ((XWPFHyperlinkRun) r).getCTHyperlink();
    }

    private boolean isTheOnlyCTFieldInRuns(XWPFFieldRun run) {
        final CTSimpleField ctField = run.getCTField();
        long count = this.runs.stream().filter(new Predicate() { // from class: org.apache.poi.xwpf.usermodel.XWPFParagraph$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return XWPFParagraph.lambda$isTheOnlyCTFieldInRuns$4(CTSimpleField.this, (XWPFRun) obj);
            }
        }).count();
        return count <= 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$isTheOnlyCTFieldInRuns$4(CTSimpleField ctField, XWPFRun r) {
        return (r instanceof XWPFFieldRun) && ctField == ((XWPFFieldRun) r).getCTField();
    }

    @Override // org.apache.poi.xwpf.usermodel.IBodyElement
    public BodyElementType getElementType() {
        return BodyElementType.PARAGRAPH;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBodyElement
    public IBody getBody() {
        return this.part;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBodyElement, org.apache.poi.xwpf.usermodel.IRunBody
    public POIXMLDocumentPart getPart() {
        if (this.part != null) {
            return this.part.getPart();
        }
        return null;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBodyElement
    public BodyType getPartType() {
        return this.part.getPartType();
    }

    public void addRun(XWPFRun r) {
        if (!this.runs.contains(r)) {
            this.runs.add(r);
            this.iruns.add(r);
        }
    }

    public XWPFRun getRun(CTR r) {
        for (int i = 0; i < getRuns().size(); i++) {
            if (getRuns().get(i).getCTR() == r) {
                return getRuns().get(i);
            }
        }
        return null;
    }

    public void addFootnoteReference(XWPFAbstractFootnoteEndnote footnote) {
        XWPFRun run = createRun();
        CTR ctRun = run.getCTR();
        ctRun.addNewRPr().addNewRStyle().setVal("FootnoteReference");
        if (footnote instanceof XWPFEndnote) {
            ctRun.addNewEndnoteReference().setId(footnote.getId());
        } else {
            ctRun.addNewFootnoteReference().setId(footnote.getId());
        }
    }
}
