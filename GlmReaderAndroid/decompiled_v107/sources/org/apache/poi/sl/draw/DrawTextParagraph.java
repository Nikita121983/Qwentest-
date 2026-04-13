package org.apache.poi.sl.draw;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.InvalidObjectException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiConsumer;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.common.usermodel.fonts.FontInfo;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.sl.draw.DrawTextParagraph;
import org.apache.poi.sl.usermodel.AutoNumberingScheme;
import org.apache.poi.sl.usermodel.HighlightColorSupport;
import org.apache.poi.sl.usermodel.Hyperlink;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.PlaceholderDetails;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.StringUtil;
import org.apache.poi.util.Units;

/* loaded from: classes10.dex */
public class DrawTextParagraph implements Drawable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected int autoNbrIdx;
    protected DrawTextFragment bullet;
    protected TextParagraph<?, ?, ?> paragraph;
    protected String rawText;
    double x;
    double y;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) DrawTextParagraph.class);
    public static final XlinkAttribute HYPERLINK_HREF = new XlinkAttribute("href");
    public static final XlinkAttribute HYPERLINK_LABEL = new XlinkAttribute("label");
    protected List<DrawTextFragment> lines = new ArrayList();
    protected boolean firstParagraph = true;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class XlinkAttribute extends AttributedCharacterIterator.Attribute {
        XlinkAttribute(String name) {
            super(name);
        }

        @Override // java.text.AttributedCharacterIterator.Attribute
        protected Object readResolve() throws InvalidObjectException {
            if (DrawTextParagraph.HYPERLINK_HREF.getName().equals(getName())) {
                return DrawTextParagraph.HYPERLINK_HREF;
            }
            if (DrawTextParagraph.HYPERLINK_LABEL.getName().equals(getName())) {
                return DrawTextParagraph.HYPERLINK_LABEL;
            }
            throw new InvalidObjectException("unknown attribute name");
        }
    }

    public DrawTextParagraph(TextParagraph<?, ?, ?> paragraph) {
        this.paragraph = paragraph;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getY() {
        return this.y;
    }

    public void setAutoNumberingIdx(int index) {
        this.autoNbrIdx = index;
    }

    @Override // org.apache.poi.sl.draw.Drawable
    public void draw(Graphics2D graphics) {
        double penY;
        int indentLevel;
        Double leftMargin;
        Double spacing;
        double doubleValue;
        double d;
        double max;
        if (this.lines.isEmpty()) {
            return;
        }
        boolean isHSLF = isHSLF();
        double penY2 = this.y;
        int indentLevel2 = this.paragraph.getIndentLevel();
        Double leftMargin2 = this.paragraph.getLeftMargin();
        if (leftMargin2 == null) {
            leftMargin2 = Double.valueOf(Units.toPoints(indentLevel2 * 347663));
        }
        Double indent = this.paragraph.getIndent();
        if (indent == null) {
            indent = Double.valueOf(Units.toPoints(indentLevel2 * 347663));
        }
        Double spacing2 = this.paragraph.getLineSpacing();
        if (spacing2 == null) {
            spacing2 = Double.valueOf(100.0d);
        }
        DrawTextFragment lastLine = null;
        for (DrawTextFragment line : this.lines) {
            if (!isFirstParagraph() || lastLine != null) {
                double penY3 = penY2 - (line.getLeading() + (lastLine == null ? 0.0f : lastLine.getLayout().getDescent()));
                if (spacing2.doubleValue() > 0.0d) {
                    penY = penY3 + (spacing2.doubleValue() * 0.01d * line.getHeight());
                } else {
                    penY = penY3 + (-spacing2.doubleValue());
                }
                penY2 = penY - line.getLayout().getAscent();
            }
            double penX = this.x + leftMargin2.doubleValue();
            if (lastLine != null) {
                indentLevel = indentLevel2;
                leftMargin = leftMargin2;
                spacing = spacing2;
            } else {
                if (!isEmptyParagraph()) {
                    this.bullet = getBullet(graphics, line.getAttributedString().getIterator());
                }
                if (this.bullet == null) {
                    indentLevel = indentLevel2;
                    leftMargin = leftMargin2;
                    spacing = spacing2;
                } else {
                    DrawTextFragment drawTextFragment = this.bullet;
                    if (isHSLF) {
                        indentLevel = indentLevel2;
                        leftMargin = leftMargin2;
                        doubleValue = this.x;
                    } else {
                        indentLevel = indentLevel2;
                        leftMargin = leftMargin2;
                        doubleValue = this.x + leftMargin.doubleValue();
                    }
                    drawTextFragment.setPosition(doubleValue + indent.doubleValue(), penY2);
                    this.bullet.draw(graphics);
                    double bulletWidth = this.bullet.getLayout().getAdvance() + 1.0f;
                    double bulletWidth2 = this.x;
                    if (isHSLF) {
                        d = bulletWidth2;
                        max = leftMargin.doubleValue();
                        spacing = spacing2;
                    } else {
                        d = bulletWidth2;
                        spacing = spacing2;
                        max = Math.max(leftMargin.doubleValue(), leftMargin.doubleValue() + indent.doubleValue() + bulletWidth);
                    }
                    penX = d + max;
                }
            }
            Rectangle2D anchor = DrawShape.getAnchor(graphics, this.paragraph.getParentShape());
            Insets2D insets = this.paragraph.getParentShape().getInsets();
            double leftInset = insets.left;
            double leftInset2 = insets.right;
            boolean isHSLF2 = isHSLF;
            TextParagraph.TextAlign ta = this.paragraph.getTextAlign();
            if (ta == null) {
                ta = TextParagraph.TextAlign.LEFT;
            }
            switch (ta) {
                case CENTER:
                    penX += ((((anchor.getWidth() - line.getWidth()) - leftInset) - leftInset2) - leftMargin.doubleValue()) / 2.0d;
                    break;
                case RIGHT:
                    penX += ((anchor.getWidth() - line.getWidth()) - leftInset) - leftInset2;
                    break;
            }
            line.setPosition(penX, penY2);
            line.draw(graphics);
            penY2 += line.getHeight();
            lastLine = line;
            spacing2 = spacing;
            indentLevel2 = indentLevel;
            leftMargin2 = leftMargin;
            isHSLF = isHSLF2;
        }
        this.y = penY2 - this.y;
    }

    public float getFirstLineLeading() {
        if (this.lines.isEmpty()) {
            return 0.0f;
        }
        return this.lines.get(0).getLeading();
    }

    public float getFirstLineHeight() {
        if (this.lines.isEmpty()) {
            return 0.0f;
        }
        return this.lines.get(0).getHeight();
    }

    public float getLastLineHeight() {
        if (this.lines.isEmpty()) {
            return 0.0f;
        }
        return this.lines.get(this.lines.size() - 1).getHeight();
    }

    public boolean isEmptyParagraph() {
        return this.lines.isEmpty() || StringUtil.isBlank(this.rawText);
    }

    @Override // org.apache.poi.sl.draw.Drawable
    public void applyTransform(Graphics2D graphics) {
    }

    @Override // org.apache.poi.sl.draw.Drawable
    public void drawContent(Graphics2D graphics) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void breakText(Graphics2D graphics) {
        int endIndex;
        TextLayout layout;
        int endIndex2;
        Graphics2D graphics2D = graphics;
        this.lines.clear();
        DrawFactory fact = DrawFactory.getInstance(graphics2D);
        StringBuilder text = new StringBuilder();
        List<AttributedStringData> attList = getAttributedString(graphics2D, text);
        AttributedString as = new AttributedString(text.toString());
        AttributedString asNoCR = new AttributedString(text.toString().replaceAll("[\\r\\n]", StringUtils.SPACE));
        for (AttributedStringData asd : attList) {
            as.addAttribute(asd.attribute, asd.value, asd.beginIndex, asd.endIndex);
            asNoCR.addAttribute(asd.attribute, asd.value, asd.beginIndex, asd.endIndex);
        }
        AttributedCharacterIterator it = as.getIterator();
        AttributedCharacterIterator itNoCR = asNoCR.getIterator();
        LineBreakMeasurer measurer = new LineBreakMeasurer(it, graphics2D.getFontRenderContext());
        while (true) {
            int startIndex = measurer.getPosition();
            double wrappingWidth = getWrappingWidth(this.lines.isEmpty(), graphics2D) + 1.0d;
            if (wrappingWidth < 0.0d) {
                wrappingWidth = 1.0d;
            }
            if (startIndex != 0 || !text.toString().startsWith(StringUtils.LF)) {
                int nextBreak = text.indexOf(StringUtils.LF, startIndex + 1);
                if (nextBreak == -1) {
                    nextBreak = it.getEndIndex();
                }
                TextLayout layout2 = measurer.nextLayout((float) wrappingWidth, nextBreak, true);
                if (layout2 == null) {
                    layout2 = measurer.nextLayout((float) wrappingWidth, nextBreak, false);
                }
                if (layout2 == null) {
                    break;
                }
                int endIndex3 = measurer.getPosition();
                if (endIndex3 < it.getEndIndex()) {
                    endIndex = endIndex3;
                    if (text.charAt(endIndex3) == '\n') {
                        measurer.setPosition(endIndex + 1);
                    }
                } else {
                    endIndex = endIndex3;
                }
                TextParagraph.TextAlign hAlign = this.paragraph.getTextAlign();
                if (hAlign == TextParagraph.TextAlign.JUSTIFY || hAlign == TextParagraph.TextAlign.JUSTIFY_LOW) {
                    layout = layout2.getJustifiedLayout((float) wrappingWidth);
                    endIndex2 = endIndex;
                } else {
                    layout = layout2;
                    endIndex2 = endIndex;
                }
            } else {
                layout = measurer.nextLayout((float) wrappingWidth, 1, false);
                endIndex2 = 1;
            }
            AttributedString str = new AttributedString(itNoCR, startIndex, endIndex2);
            DrawTextFragment line = fact.getTextFragment(layout, str);
            this.lines.add(line);
            if (endIndex2 == it.getEndIndex()) {
                break;
            } else {
                graphics2D = graphics;
            }
        }
        this.rawText = text.toString();
    }

    protected DrawTextFragment getBullet(Graphics2D graphics, AttributedCharacterIterator firstLineAttr) {
        String buCharacter;
        Paint fgPaint;
        float fontSize;
        TextParagraph.BulletStyle bulletStyle = this.paragraph.getBulletStyle();
        if (bulletStyle == null) {
            return null;
        }
        AutoNumberingScheme ans = bulletStyle.getAutoNumberingScheme();
        if (ans != null) {
            buCharacter = ans.format(this.autoNbrIdx);
        } else {
            buCharacter = bulletStyle.getBulletCharacter();
        }
        if (buCharacter == null) {
            return null;
        }
        PlaceableShape<?, ?> ps = getParagraphShape();
        PaintStyle fgPaintStyle = bulletStyle.getBulletFontColor();
        if (fgPaintStyle == null) {
            fgPaint = (Paint) firstLineAttr.getAttribute(TextAttribute.FOREGROUND);
        } else {
            fgPaint = new DrawPaint(ps).getPaint(graphics, fgPaintStyle);
        }
        float fontSize2 = ((Float) firstLineAttr.getAttribute(TextAttribute.SIZE)).floatValue();
        Double buSz = bulletStyle.getBulletFontSize();
        if (buSz == null) {
            buSz = Double.valueOf(100.0d);
        }
        if (buSz.doubleValue() > 0.0d) {
            fontSize = fontSize2 * ((float) (buSz.doubleValue() * 0.01d));
        } else {
            fontSize = (float) (-buSz.doubleValue());
        }
        String buFontStr = bulletStyle.getBulletFont();
        if (buFontStr == null) {
            buFontStr = this.paragraph.getDefaultFontFamily();
        }
        if (buFontStr == null) {
            throw new AssertionError();
        }
        FontInfo buFont = new DrawFontInfo(buFontStr);
        DrawFontManager dfm = DrawFactory.getInstance(graphics).getFontManager(graphics);
        FontInfo buFont2 = dfm.getMappedFont(graphics, buFont);
        Map<TextAttribute, Object> att = new HashMap<>();
        att.put(TextAttribute.FOREGROUND, fgPaint);
        att.put(TextAttribute.FAMILY, buFont2.getTypeface());
        att.put(TextAttribute.SIZE, Float.valueOf(fontSize));
        att.put(TextAttribute.FONT, new Font(att));
        final AttributedString str = new AttributedString(dfm.mapFontCharset(graphics, buFont2, buCharacter));
        str.getClass();
        att.forEach(new BiConsumer() { // from class: org.apache.poi.sl.draw.DrawTextParagraph$$ExternalSyntheticLambda2
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                str.addAttribute((TextAttribute) obj, obj2);
            }
        });
        TextLayout layout = new TextLayout(str.getIterator(), graphics.getFontRenderContext());
        DrawFactory fact = DrawFactory.getInstance(graphics);
        return fact.getTextFragment(layout, str);
    }

    protected String getRenderableText(Graphics2D graphics, TextRun tr) {
        TextRun.FieldType ft = tr.getFieldType();
        if (ft == null) {
            return getRenderableText(tr);
        }
        if (tr.getRawText() != null && !tr.getRawText().isEmpty()) {
            switch (ft) {
                case SLIDE_NUMBER:
                    Slide<?, ?> slide = (Slide) graphics.getRenderingHint(Drawable.CURRENT_SLIDE);
                    return slide == null ? "" : Integer.toString(slide.getSlideNumber());
                case DATE_TIME:
                    PlaceholderDetails pd = ((SimpleShape) getParagraphShape()).getPlaceholderDetails();
                    pd.getPlaceholder();
                    String uda = pd.getUserDate();
                    if (uda != null) {
                        return uda;
                    }
                    Calendar cal = LocaleUtil.getLocaleCalendar();
                    LocalDateTime now = LocalDateTime.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId());
                    return now.format(pd.getDateFormat());
            }
        }
        return "";
    }

    @Internal
    public String getRenderableText(TextRun tr) {
        String txtSpace = tr.getRawText();
        if (txtSpace == null) {
            return null;
        }
        if (txtSpace.contains("\t")) {
            txtSpace = txtSpace.replace("\t", tab2space(tr));
        }
        String txtSpace2 = txtSpace.replace((char) 11, '\n');
        Locale loc = LocaleUtil.getUserLocale();
        switch (tr.getTextCap()) {
            case ALL:
                return txtSpace2.toUpperCase(loc);
            case SMALL:
                return txtSpace2.toLowerCase(loc);
            default:
                return txtSpace2;
        }
    }

    private String tab2space(TextRun tr) {
        int numSpaces;
        AttributedString string = new AttributedString(StringUtils.SPACE);
        String fontFamily = tr.getFontFamily();
        if (fontFamily == null) {
            fontFamily = "Lucida Sans";
        }
        string.addAttribute(TextAttribute.FAMILY, fontFamily);
        Double fs = tr.getFontSize();
        if (fs == null) {
            fs = Double.valueOf(12.0d);
        }
        string.addAttribute(TextAttribute.SIZE, Float.valueOf(fs.floatValue()));
        TextLayout l = new TextLayout(string.getIterator(), new FontRenderContext((AffineTransform) null, true, true));
        double wspace = l.getAdvance();
        Double tabSz = this.paragraph.getDefaultTabSize();
        if (wspace <= 0.0d) {
            numSpaces = 4;
        } else {
            if (tabSz == null) {
                tabSz = Double.valueOf(4.0d * wspace);
            }
            numSpaces = (int) Math.min(Math.ceil(tabSz.doubleValue() / wspace), 20.0d);
        }
        char[] buf = new char[numSpaces];
        Arrays.fill(buf, Chars.SPACE);
        return new String(buf);
    }

    protected double getWrappingWidth(boolean firstLine, Graphics2D graphics) {
        double width;
        double width2;
        TextShape<?, ?> ts = this.paragraph.getParentShape();
        Insets2D insets = ts.getInsets();
        double leftInset = insets.left;
        double rightInset = insets.right;
        int indentLevel = this.paragraph.getIndentLevel();
        if (indentLevel == -1) {
            indentLevel = 0;
        }
        Double leftMargin = this.paragraph.getLeftMargin();
        if (leftMargin == null) {
            leftMargin = Double.valueOf(Units.toPoints(indentLevel * 347663));
        }
        Double indent = this.paragraph.getIndent();
        if (indent == null) {
            indent = Double.valueOf(0.0d);
        }
        Double rightMargin = this.paragraph.getRightMargin();
        if (rightMargin == null) {
            rightMargin = Double.valueOf(0.0d);
        }
        Rectangle2D anchor = DrawShape.getAnchor(graphics, ts);
        TextShape.TextDirection textDir = ts.getTextDirection();
        if (!ts.getWordWrap()) {
            Dimension pageDim = ts.getSheet().getSlideShow().getPageSize();
            switch (textDir) {
                case VERTICAL:
                    double width3 = pageDim.getHeight();
                    width2 = width3 - anchor.getX();
                    break;
                case VERTICAL_270:
                    width2 = anchor.getX();
                    break;
                default:
                    width2 = pageDim.getWidth() - anchor.getX();
                    break;
            }
            return width2;
        }
        switch (textDir) {
            case VERTICAL:
            case VERTICAL_270:
                double width4 = anchor.getHeight();
                width = (((width4 - leftInset) - rightInset) - leftMargin.doubleValue()) - rightMargin.doubleValue();
                break;
            default:
                double width5 = (((anchor.getWidth() - leftInset) - rightInset) - leftMargin.doubleValue()) - rightMargin.doubleValue();
                width = width5;
                break;
        }
        if (firstLine && this.bullet == null) {
            return width + (isHSLF() ? leftMargin.doubleValue() - indent.doubleValue() : -indent.doubleValue());
        }
        return width;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class AttributedStringData {
        AttributedCharacterIterator.Attribute attribute;
        int beginIndex;
        int endIndex;
        Object value;

        AttributedStringData(AttributedCharacterIterator.Attribute attribute, Object value, int beginIndex, int endIndex) {
            this.attribute = attribute;
            this.value = value;
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
        }
    }

    private PlaceableShape<?, ?> getParagraphShape() {
        return this.paragraph.getParentShape();
    }

    protected List<AttributedStringData> getAttributedString(Graphics2D graphics, StringBuilder text) {
        StringBuilder text2;
        DrawFontManager dfm;
        Double fontSz;
        Graphics2D graphics2D = graphics;
        if (text != null) {
            text2 = text;
        } else {
            text2 = new StringBuilder();
        }
        DrawPaint dp = new DrawPaint(getParagraphShape());
        DrawFontManager dfm2 = DrawFactory.getInstance(graphics2D).getFontManager(graphics2D);
        if (dfm2 == null) {
            throw new AssertionError();
        }
        Map<AttributedCharacterIterator.Attribute, Object> att = new HashMap<>();
        final List<AttributedStringData> attList = new ArrayList<>();
        Iterator<T> it = this.paragraph.iterator();
        while (it.hasNext()) {
            TextRun run = (TextRun) it.next();
            String runText = getRenderableText(graphics2D, run);
            if (!runText.isEmpty()) {
                att.clear();
                FontInfo fontInfo = run.getFontInfo(null);
                String runText2 = dfm2.mapFontCharset(graphics2D, fontInfo, runText);
                final int beginIndex = text2.length();
                text2.append(runText2);
                final int endIndex = text2.length();
                PaintStyle fgPaintStyle = run.getFontColor();
                Paint fgPaint = dp.getPaint(graphics2D, fgPaintStyle);
                att.put(TextAttribute.FOREGROUND, fgPaint);
                if (!(run instanceof HighlightColorSupport)) {
                    dfm = dfm2;
                } else {
                    PaintStyle highlightPaintStyle = ((HighlightColorSupport) run).getHighlightColor();
                    if (highlightPaintStyle == null) {
                        dfm = dfm2;
                    } else {
                        dfm = dfm2;
                        Paint bgPaint = dp.getPaint(graphics2D, highlightPaintStyle);
                        att.put(TextAttribute.BACKGROUND, bgPaint);
                    }
                }
                Double fontSz2 = run.getFontSize();
                if (fontSz2 != null) {
                    fontSz = fontSz2;
                } else {
                    Double fontSz3 = this.paragraph.getDefaultFontSize();
                    fontSz = fontSz3;
                }
                att.put(TextAttribute.SIZE, Float.valueOf(fontSz.floatValue()));
                if (run.isBold()) {
                    att.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
                }
                if (run.isItalic()) {
                    att.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
                }
                if (run.isUnderlined()) {
                    att.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    att.put(TextAttribute.INPUT_METHOD_UNDERLINE, TextAttribute.UNDERLINE_LOW_TWO_PIXEL);
                }
                if (run.isStrikethrough()) {
                    att.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
                }
                if (run.isSubscript()) {
                    att.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB);
                }
                if (run.isSuperscript()) {
                    att.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
                }
                Hyperlink<?, ?> hl = run.getHyperlink();
                if (hl != null) {
                    att.put(HYPERLINK_HREF, hl.getAddress());
                    att.put(HYPERLINK_LABEL, hl.getLabel());
                }
                if (fontInfo == null) {
                    att.put(TextAttribute.FAMILY, this.paragraph.getDefaultFontFamily());
                } else {
                    att.put(TextAttribute.FAMILY, fontInfo.getTypeface());
                }
                att.put(TextAttribute.FONT, new Font(att));
                att.forEach(new BiConsumer() { // from class: org.apache.poi.sl.draw.DrawTextParagraph$$ExternalSyntheticLambda0
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        attList.add(new DrawTextParagraph.AttributedStringData((AttributedCharacterIterator.Attribute) obj, obj2, beginIndex, endIndex));
                    }
                });
                graphics2D = graphics;
                dfm2 = dfm;
                processGlyphs(graphics2D, dfm2, attList, beginIndex, run, runText2);
            }
        }
        if (text2.length() == 0) {
            text2.append(StringUtils.SPACE);
            att.put(TextAttribute.SIZE, Float.valueOf(this.paragraph.getDefaultFontSize().floatValue()));
            att.put(TextAttribute.FAMILY, this.paragraph.getDefaultFontFamily());
            att.put(TextAttribute.FONT, new Font(att));
            att.forEach(new BiConsumer() { // from class: org.apache.poi.sl.draw.DrawTextParagraph$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    attList.add(new DrawTextParagraph.AttributedStringData((AttributedCharacterIterator.Attribute) obj, obj2, 0, 1));
                }
            });
        }
        return attList;
    }

    private void processGlyphs(Graphics2D graphics, DrawFontManager dfm, List<AttributedStringData> attList, int beginIndex, TextRun run, String runText) {
        FontInfo fiRun;
        FontInfo fiFallback;
        int rangeLen;
        Graphics2D graphics2D = graphics;
        DrawFontManager drawFontManager = dfm;
        TextRun textRun = run;
        List<FontGroup.FontGroupRange> ttrList = FontGroup.getFontGroupRanges(runText);
        int rangeBegin = 0;
        for (FontGroup.FontGroupRange ttr : ttrList) {
            FontInfo fiRun2 = textRun.getFontInfo(ttr.getFontGroup());
            if (fiRun2 != null) {
                fiRun = fiRun2;
            } else {
                fiRun = textRun.getFontInfo(FontGroup.LATIN);
            }
            FontInfo fiMapped = drawFontManager.getMappedFont(graphics2D, fiRun);
            FontInfo fiFallback2 = drawFontManager.getFallbackFont(graphics2D, fiRun);
            if (fiFallback2 == null) {
                throw new AssertionError();
            }
            if (fiMapped == null) {
                fiMapped = drawFontManager.getMappedFont(graphics2D, new DrawFontInfo(this.paragraph.getDefaultFontFamily()));
            }
            if (fiMapped == null) {
                fiMapped = fiFallback2;
            }
            Font fontMapped = drawFontManager.createAWTFont(graphics2D, fiMapped, 10.0d, textRun.isBold(), textRun.isItalic());
            Font fontFallback = dfm.createAWTFont(graphics, fiFallback2, 10.0d, textRun.isBold(), textRun.isItalic());
            FontInfo fiFallback3 = fiFallback2;
            int rangeLen2 = ttr.getLength();
            int partEnd = rangeBegin;
            while (partEnd < rangeBegin + rangeLen2) {
                int partBegin = partEnd;
                int partEnd2 = nextPart(fontMapped, runText, partBegin, rangeBegin + rangeLen2, true);
                if (partBegin >= partEnd2) {
                    fiFallback = fiFallback3;
                    rangeLen = rangeLen2;
                } else {
                    String fontName = fontMapped.getFontName(Locale.ROOT);
                    int startIndex = beginIndex + partBegin;
                    fiFallback = fiFallback3;
                    int endIndex = beginIndex + partEnd2;
                    rangeLen = rangeLen2;
                    attList.add(new AttributedStringData(TextAttribute.FAMILY, fontName, startIndex, endIndex));
                    LOG.atDebug().log("mapped: {} {} {} - {}", fontName, Unbox.box(startIndex), Unbox.box(endIndex), runText.substring(partBegin, partEnd2));
                }
                partEnd = nextPart(fontMapped, runText, partEnd2, rangeBegin + rangeLen, false);
                if (partEnd2 < partEnd) {
                    String fontName2 = fontFallback.getFontName(Locale.ROOT);
                    int startIndex2 = beginIndex + partEnd2;
                    int endIndex2 = beginIndex + partEnd;
                    attList.add(new AttributedStringData(TextAttribute.FAMILY, fontName2, startIndex2, endIndex2));
                    LOG.atDebug().log("fallback: {} {} {} - {}", fontName2, Unbox.box(startIndex2), Unbox.box(endIndex2), runText.substring(partEnd2, partEnd));
                }
                fiFallback3 = fiFallback;
                rangeLen2 = rangeLen;
            }
            rangeBegin += rangeLen2;
            graphics2D = graphics;
            drawFontManager = dfm;
            textRun = run;
        }
    }

    private static int nextPart(Font fontMapped, String runText, int beginPart, int endPart, boolean isDisplayed) {
        int rIdx = beginPart;
        while (rIdx < endPart) {
            int codepoint = runText.codePointAt(rIdx);
            if (fontMapped.canDisplay(codepoint) != isDisplayed) {
                break;
            }
            rIdx += Character.charCount(codepoint);
        }
        return rIdx;
    }

    protected boolean isHSLF() {
        return DrawShape.isHSLF(this.paragraph.getParentShape());
    }

    protected boolean isFirstParagraph() {
        return this.firstParagraph;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setFirstParagraph(boolean firstParagraph) {
        this.firstParagraph = firstParagraph;
    }
}
