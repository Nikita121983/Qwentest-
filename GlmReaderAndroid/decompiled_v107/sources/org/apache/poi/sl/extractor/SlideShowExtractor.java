package org.apache.poi.sl.extractor;

import com.zaxxer.sparsebits.SparseBitSet;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.util.IterableStringTokenizer$$ExternalSyntheticLambda0;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.sl.usermodel.Comment;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Notes;
import org.apache.poi.sl.usermodel.ObjectShape;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.PlaceholderDetails;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.ShapeContainer;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TableShape;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.Removal;

/* loaded from: classes10.dex */
public class SlideShowExtractor<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> implements POITextExtractor {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) SlideShowExtractor.class);
    private static final String SLIDE_NUMBER_PH = "‹#›";
    private boolean commentsByDefault;
    private boolean masterByDefault;
    private boolean notesByDefault;
    protected final SlideShow<S, P> slideshow;
    private boolean slidesByDefault = true;
    private Predicate<Object> filter = new Predicate() { // from class: org.apache.poi.sl.extractor.SlideShowExtractor$$ExternalSyntheticLambda10
        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return SlideShowExtractor.lambda$new$0(obj);
        }
    };
    private boolean doCloseFilesystem = true;

    public static /* synthetic */ boolean lambda$new$0(Object o) {
        return true;
    }

    public SlideShowExtractor(SlideShow<S, P> slideshow) {
        this.slideshow = slideshow;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public SlideShow<S, P> getDocument() {
        return this.slideshow;
    }

    public void setSlidesByDefault(boolean slidesByDefault) {
        this.slidesByDefault = slidesByDefault;
    }

    public void setNotesByDefault(boolean notesByDefault) {
        this.notesByDefault = notesByDefault;
    }

    public void setCommentsByDefault(boolean commentsByDefault) {
        this.commentsByDefault = commentsByDefault;
    }

    public void setMasterByDefault(boolean masterByDefault) {
        this.masterByDefault = masterByDefault;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public POITextExtractor getMetadataTextExtractor() {
        return this.slideshow.getMetadataTextExtractor();
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (Slide<S, P> slide : this.slideshow.getSlides()) {
            sb.getClass();
            getText(slide, new SlideShowExtractor$$ExternalSyntheticLambda4(sb));
        }
        return sb.toString();
    }

    public String getText(Slide<S, P> slide) {
        StringBuilder sb = new StringBuilder();
        sb.getClass();
        getText(slide, new SlideShowExtractor$$ExternalSyntheticLambda4(sb));
        return sb.toString();
    }

    private void getText(Slide<S, P> slide, Consumer<String> consumer) {
        if (this.slidesByDefault) {
            printShapeText((Sheet) slide, consumer);
        }
        if (this.masterByDefault) {
            MasterSheet<S, P> ms = slide.getMasterSheet();
            printSlideMaster(ms, consumer);
            MasterSheet<S, P> sl = slide.getSlideLayout();
            if (sl != ms) {
                printSlideMaster(sl, consumer);
            }
        }
        if (this.commentsByDefault) {
            printComments(slide, consumer);
        }
        if (this.notesByDefault) {
            printNotes(slide, consumer);
        }
    }

    private void printSlideMaster(MasterSheet<S, P> master, Consumer<String> consumer) {
        TextShape<S, P> ts;
        String text;
        if (master == null) {
            return;
        }
        Iterator it = master.iterator();
        while (it.hasNext()) {
            Shape<S, P> shape = (Shape) it.next();
            if ((shape instanceof TextShape) && (text = (ts = (TextShape) shape).getText()) != null && !text.isEmpty() && !"*".equals(text)) {
                if (ts.isPlaceholder()) {
                    LOG.atInfo().log("Ignoring boiler plate (placeholder) text on slide master: {}", text);
                } else {
                    printTextParagraphs(ts.getTextParagraphs(), consumer);
                }
            }
        }
    }

    private void printTextParagraphs(List<P> paras, Consumer<String> consumer) {
        printTextParagraphs(paras, consumer, StringUtils.LF);
    }

    private void printTextParagraphs(List<P> paras, Consumer<String> consumer, String trailer) {
        printTextParagraphs(paras, consumer, trailer, new Function() { // from class: org.apache.poi.sl.extractor.SlideShowExtractor$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String replaceTextCap;
                replaceTextCap = SlideShowExtractor.replaceTextCap((TextRun) obj);
                return replaceTextCap;
            }
        });
    }

    private void printTextParagraphs(List<P> paras, Consumer<String> consumer, String trailer, Function<TextRun, String> converter) {
        for (P<TextRun> p : paras) {
            for (TextRun r : p) {
                if (this.filter.test(r)) {
                    consumer.accept(converter.apply(r));
                }
            }
            if (!trailer.isEmpty() && this.filter.test(trailer)) {
                consumer.accept(trailer);
            }
        }
    }

    private void printHeaderFooter(Sheet<S, P> sheet, Consumer<String> consumer, Consumer<String> footerCon) {
        TextShape<S, P> ts;
        PlaceholderDetails pd;
        Sheet<S, P> m = sheet instanceof Slide ? sheet.getMasterSheet() : sheet;
        addSheetPlaceholderDatails(sheet, Placeholder.HEADER, consumer);
        addSheetPlaceholderDatails(sheet, Placeholder.FOOTER, footerCon);
        if (!this.masterByDefault) {
            return;
        }
        Iterator it = m.iterator();
        while (it.hasNext()) {
            Shape<S, P> s = (Shape) it.next();
            if ((s instanceof TextShape) && (pd = (ts = (TextShape) s).getPlaceholderDetails()) != null && pd.isVisible() && pd.getPlaceholder() != null) {
                switch (pd.getPlaceholder()) {
                    case HEADER:
                        printTextParagraphs(ts.getTextParagraphs(), consumer);
                        break;
                    case FOOTER:
                        printTextParagraphs(ts.getTextParagraphs(), footerCon);
                        break;
                    case SLIDE_NUMBER:
                        printTextParagraphs(ts.getTextParagraphs(), footerCon, StringUtils.LF, new Function() { // from class: org.apache.poi.sl.extractor.SlideShowExtractor$$ExternalSyntheticLambda1
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                String replaceSlideNumber;
                                replaceSlideNumber = SlideShowExtractor.replaceSlideNumber((TextRun) obj);
                                return replaceSlideNumber;
                            }
                        });
                        break;
                }
            }
        }
    }

    private void addSheetPlaceholderDatails(Sheet<S, P> sheet, Placeholder placeholder, Consumer<String> consumer) {
        PlaceholderDetails headerPD = sheet.getPlaceholderDetails(placeholder);
        String headerStr = headerPD != null ? headerPD.getText() : null;
        if (headerStr != null && this.filter.test(headerPD)) {
            consumer.accept(headerStr);
        }
    }

    private void printShapeText(Sheet<S, P> sheet, Consumer<String> consumer) {
        List<String> footer = new LinkedList<>();
        footer.getClass();
        printHeaderFooter(sheet, consumer, new IterableStringTokenizer$$ExternalSyntheticLambda0(footer));
        printShapeText((ShapeContainer) sheet, consumer);
        footer.forEach(consumer);
    }

    private void printShapeText(ShapeContainer<S, P> container, Consumer<String> consumer) {
        Iterator<S> it = container.iterator();
        while (it.hasNext()) {
            Shape<S, P> shape = (Shape) it.next();
            if (shape instanceof TextShape) {
                printTextParagraphs(((TextShape) shape).getTextParagraphs(), consumer);
            } else if (shape instanceof TableShape) {
                printShapeText((TableShape) shape, consumer);
            } else if (shape instanceof ShapeContainer) {
                printShapeText((ShapeContainer) shape, consumer);
            }
        }
    }

    private void printShapeText(TableShape<S, P> shape, Consumer<String> consumer) {
        int nrows = shape.getNumberOfRows();
        int ncols = shape.getNumberOfColumns();
        for (int row = 0; row < nrows; row++) {
            String trailer = "";
            int col = 0;
            while (true) {
                String trailer2 = StringUtils.LF;
                if (col >= ncols) {
                    break;
                }
                TableCell<S, P> cell = shape.getCell(row, col);
                if (cell != null) {
                    if (col < ncols - 1) {
                        trailer2 = "\t";
                    }
                    printTextParagraphs(cell.getTextParagraphs(), consumer, trailer2);
                    trailer = trailer2;
                }
                col++;
            }
            if (!trailer.equals(StringUtils.LF) && this.filter.test(StringUtils.LF)) {
                consumer.accept(StringUtils.LF);
            }
        }
    }

    public static /* synthetic */ String lambda$printComments$1(Comment c) {
        return c.getAuthor() + " - " + c.getText();
    }

    private void printComments(Slide<S, P> slide, Consumer<String> consumer) {
        slide.getComments().stream().filter(this.filter).map(new Function() { // from class: org.apache.poi.sl.extractor.SlideShowExtractor$$ExternalSyntheticLambda11
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SlideShowExtractor.lambda$printComments$1((Comment) obj);
            }
        }).forEach(consumer);
    }

    private void printNotes(Slide<S, P> slide, Consumer<String> consumer) {
        Notes<S, P> notes = slide.getNotes();
        if (notes == null) {
            return;
        }
        List<String> footer = new LinkedList<>();
        footer.getClass();
        printHeaderFooter(notes, consumer, new IterableStringTokenizer$$ExternalSyntheticLambda0(footer));
        printShapeText((Sheet) notes, consumer);
        footer.forEach(consumer);
    }

    public List<? extends ObjectShape<S, P>> getOLEShapes() {
        List<ObjectShape<S, P>> oleShapes = new ArrayList<>();
        for (Slide<S, P> slide : this.slideshow.getSlides()) {
            addOLEShapes(oleShapes, slide);
        }
        return oleShapes;
    }

    private void addOLEShapes(List<ObjectShape<S, P>> oleShapes, ShapeContainer<S, P> container) {
        Iterator<S> it = container.iterator();
        while (it.hasNext()) {
            Shape<S, P> shape = (Shape) it.next();
            if (shape instanceof ShapeContainer) {
                addOLEShapes(oleShapes, (ShapeContainer) shape);
            } else if (shape instanceof ObjectShape) {
                oleShapes.add((ObjectShape) shape);
            }
        }
    }

    public static String replaceSlideNumber(TextRun tr) {
        String raw = tr.getRawText();
        if (!raw.contains(SLIDE_NUMBER_PH)) {
            return raw;
        }
        TextParagraph<?, ?, ?> tp = tr.getParagraph();
        TextShape<?, ?> ps = tp != null ? tp.getParentShape() : null;
        Sheet<?, ?> sh = ps != null ? ps.getSheet() : null;
        String slideNr = sh instanceof Slide ? Integer.toString(((Slide) sh).getSlideNumber() + 1) : "";
        return raw.replace(SLIDE_NUMBER_PH, slideNr);
    }

    public static String replaceTextCap(TextRun tr) {
        TextParagraph<?, ?, ?> tp = tr.getParagraph();
        TextShape<?, ?> sh = tp != null ? tp.getParentShape() : null;
        Placeholder ph = sh != null ? sh.getPlaceholder() : null;
        char sep = (ph == Placeholder.TITLE || ph == Placeholder.CENTERED_TITLE || ph == Placeholder.SUBTITLE) ? '\n' : Chars.SPACE;
        String txt = tr.getRawText();
        if (txt == null) {
            return "";
        }
        String txt2 = txt.replace('\r', '\n').replace((char) 11, sep);
        switch (tr.getTextCap()) {
            case ALL:
                return txt2.toUpperCase(LocaleUtil.getUserLocale());
            case SMALL:
                return txt2.toLowerCase(LocaleUtil.getUserLocale());
            default:
                return txt2;
        }
    }

    @Removal(version = "6.0.0")
    @Deprecated
    public BitSet getCodepoints(final String typeface, final Boolean italic, final Boolean bold) {
        final BitSet glyphs = new BitSet();
        Predicate<Object> filterOld = this.filter;
        try {
            this.filter = new Predicate() { // from class: org.apache.poi.sl.extractor.SlideShowExtractor$$ExternalSyntheticLambda2
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean filterFonts;
                    filterFonts = SlideShowExtractor.filterFonts(obj, typeface, italic, bold);
                    return filterFonts;
                }
            };
            this.slideshow.getSlides().forEach(new Consumer() { // from class: org.apache.poi.sl.extractor.SlideShowExtractor$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    SlideShowExtractor.this.m2516x7dc313a5(glyphs, (Slide) obj);
                }
            });
            return glyphs;
        } finally {
            this.filter = filterOld;
        }
    }

    public static /* synthetic */ void lambda$null$3(final BitSet glyphs, String s) {
        IntStream codePoints = s.codePoints();
        glyphs.getClass();
        codePoints.forEach(new IntConsumer() { // from class: org.apache.poi.sl.extractor.SlideShowExtractor$$ExternalSyntheticLambda12
            @Override // java.util.function.IntConsumer
            public final void accept(int i) {
                glyphs.set(i);
            }
        });
    }

    /* renamed from: lambda$getCodepoints$4$org-apache-poi-sl-extractor-SlideShowExtractor */
    public /* synthetic */ void m2516x7dc313a5(final BitSet glyphs, Slide slide) {
        getText(slide, new Consumer() { // from class: org.apache.poi.sl.extractor.SlideShowExtractor$$ExternalSyntheticLambda8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SlideShowExtractor.lambda$null$3(glyphs, (String) obj);
            }
        });
    }

    @Internal
    public SparseBitSet getCodepointsInSparseBitSet(final String typeface, final Boolean italic, final Boolean bold) {
        final SparseBitSet glyphs = new SparseBitSet();
        Predicate<Object> filterOld = this.filter;
        try {
            this.filter = new Predicate() { // from class: org.apache.poi.sl.extractor.SlideShowExtractor$$ExternalSyntheticLambda6
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean filterFonts;
                    filterFonts = SlideShowExtractor.filterFonts(obj, typeface, italic, bold);
                    return filterFonts;
                }
            };
            this.slideshow.getSlides().forEach(new Consumer() { // from class: org.apache.poi.sl.extractor.SlideShowExtractor$$ExternalSyntheticLambda7
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    SlideShowExtractor.this.m2517xd4d68408(glyphs, (Slide) obj);
                }
            });
            return glyphs;
        } finally {
            this.filter = filterOld;
        }
    }

    public static /* synthetic */ void lambda$null$6(final SparseBitSet glyphs, String s) {
        IntStream codePoints = s.codePoints();
        glyphs.getClass();
        codePoints.forEach(new IntConsumer() { // from class: org.apache.poi.sl.extractor.SlideShowExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.IntConsumer
            public final void accept(int i) {
                SparseBitSet.this.set(i);
            }
        });
    }

    /* renamed from: lambda$getCodepointsInSparseBitSet$7$org-apache-poi-sl-extractor-SlideShowExtractor */
    public /* synthetic */ void m2517xd4d68408(final SparseBitSet glyphs, Slide slide) {
        getText(slide, new Consumer() { // from class: org.apache.poi.sl.extractor.SlideShowExtractor$$ExternalSyntheticLambda9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SlideShowExtractor.lambda$null$6(SparseBitSet.this, (String) obj);
            }
        });
    }

    public static boolean filterFonts(Object o, String typeface, Boolean italic, Boolean bold) {
        if (!(o instanceof TextRun)) {
            return false;
        }
        TextRun tr = (TextRun) o;
        if (!typeface.equalsIgnoreCase(tr.getFontFamily())) {
            return false;
        }
        if (italic == null || tr.isItalic() == italic.booleanValue()) {
            return bold == null || tr.isBold() == bold.booleanValue();
        }
        return false;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public void setCloseFilesystem(boolean doCloseFilesystem) {
        this.doCloseFilesystem = doCloseFilesystem;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public boolean isCloseFilesystem() {
        return this.doCloseFilesystem;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public SlideShow<S, P> getFilesystem() {
        return getDocument();
    }
}
