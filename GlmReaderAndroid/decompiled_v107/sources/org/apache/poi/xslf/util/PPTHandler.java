package org.apache.poi.xslf.util;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.sl.draw.EmbeddedExtractor;
import org.apache.poi.sl.usermodel.ObjectData;
import org.apache.poi.sl.usermodel.ObjectShape;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.sl.usermodel.SlideShowFactory;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.util.PPTX2PNG;

/* JADX INFO: Access modifiers changed from: package-private */
@Internal
/* loaded from: classes10.dex */
public class PPTHandler extends MFProxy {
    private static final String RANGE_PATTERN = "(^|,)(?<from>\\d+)?(-(?<to>\\d+))?";
    private SlideShow<?, ?> ppt;
    private Slide<?, ?> slide;

    public static /* synthetic */ TreeSet $r8$lambda$4gJ5v9tAfXSpuA52TufZtTtrB6c() {
        return new TreeSet();
    }

    @Override // org.apache.poi.xslf.util.MFProxy
    public void parse(File file) throws IOException {
        try {
            this.ppt = SlideShowFactory.create(file, null, true);
            if (this.ppt == null) {
                throw new IOException("Unknown file format or missing poi-scratchpad.jar / poi-ooxml.jar");
            }
            this.slide = this.ppt.getSlides().get(0);
        } catch (IOException e) {
            if (e.getMessage().contains("scratchpad")) {
                throw new PPTX2PNG.NoScratchpadException(e);
            }
            throw e;
        }
    }

    @Override // org.apache.poi.xslf.util.MFProxy
    public void parse(InputStream is) throws IOException {
        try {
            this.ppt = SlideShowFactory.create(is, (String) null);
            if (this.ppt == null) {
                throw new IOException("Unknown file format or missing poi-scratchpad.jar / poi-ooxml.jar");
            }
            this.slide = this.ppt.getSlides().get(0);
        } catch (IOException e) {
            if (e.getMessage().contains("scratchpad")) {
                throw new PPTX2PNG.NoScratchpadException(e);
            }
            throw e;
        }
    }

    @Override // org.apache.poi.xslf.util.MFProxy
    public Dimension2D getSize() {
        return this.ppt.getPageSize();
    }

    @Override // org.apache.poi.xslf.util.MFProxy
    public int getSlideCount() {
        return this.ppt.getSlides().size();
    }

    @Override // org.apache.poi.xslf.util.MFProxy
    public void setSlideNo(int slideNo) {
        this.slide = this.ppt.getSlides().get(slideNo - 1);
    }

    @Override // org.apache.poi.xslf.util.MFProxy
    public String getTitle() {
        return this.slide.getTitle();
    }

    @Override // org.apache.poi.xslf.util.MFProxy
    public Set<Integer> slideIndexes(String range) {
        final Matcher matcher = Pattern.compile(RANGE_PATTERN).matcher(range);
        Spliterator<Matcher> sp = new Spliterators.AbstractSpliterator<Matcher>(range.length(), 272) { // from class: org.apache.poi.xslf.util.PPTHandler.1
            @Override // java.util.Spliterator
            public boolean tryAdvance(Consumer<? super Matcher> action) {
                boolean b = matcher.find();
                if (b) {
                    action.accept(matcher);
                }
                return b;
            }
        };
        return (Set) StreamSupport.stream(sp, false).flatMap(new Function() { // from class: org.apache.poi.xslf.util.PPTHandler$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Stream range2;
                range2 = PPTHandler.this.range((Matcher) obj);
                return range2;
            }
        }).collect(Collectors.toCollection(new Supplier() { // from class: org.apache.poi.xslf.util.PPTHandler$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return PPTHandler.$r8$lambda$4gJ5v9tAfXSpuA52TufZtTtrB6c();
            }
        }));
    }

    @Override // org.apache.poi.xslf.util.MFProxy
    public void draw(Graphics2D ctx) {
        this.slide.draw(ctx);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.ppt != null) {
            this.ppt.close();
        }
    }

    @Override // org.apache.poi.xslf.util.MFProxy
    public GenericRecord getRoot() {
        if (this.ppt instanceof GenericRecord) {
            return (GenericRecord) this.ppt;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Stream<Integer> range(Matcher m) {
        int to;
        final int slideCount = this.ppt.getSlides().size();
        String fromStr = m.group(TypedValues.TransitionType.S_FROM);
        String toStr = m.group(TypedValues.TransitionType.S_TO);
        int from = (fromStr == null || fromStr.isEmpty()) ? 1 : Integer.parseInt(fromStr);
        if (toStr == null) {
            to = from;
        } else if (toStr.isEmpty() || ((fromStr == null || fromStr.isEmpty()) && "1".equals(toStr))) {
            to = slideCount;
        } else {
            to = Integer.parseInt(toStr);
        }
        return IntStream.rangeClosed(from, to).filter(new IntPredicate() { // from class: org.apache.poi.xslf.util.PPTHandler$$ExternalSyntheticLambda6
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return PPTHandler.lambda$range$0(slideCount, i);
            }
        }).boxed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$range$0(int slideCount, int i) {
        return i <= slideCount;
    }

    @Override // org.apache.poi.xslf.util.MFProxy
    public Iterable<EmbeddedExtractor.EmbeddedPart> getEmbeddings(final int slideNo) {
        return new Iterable() { // from class: org.apache.poi.xslf.util.PPTHandler$$ExternalSyntheticLambda5
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return PPTHandler.this.m2584lambda$getEmbeddings$2$orgapachepoixslfutilPPTHandler(slideNo);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getEmbeddings$2$org-apache-poi-xslf-util-PPTHandler, reason: not valid java name */
    public /* synthetic */ Iterator m2584lambda$getEmbeddings$2$orgapachepoixslfutilPPTHandler(int slideNo) {
        return this.ppt.getSlides().get(slideNo).getShapes().stream().filter(new Predicate() { // from class: org.apache.poi.xslf.util.PPTHandler$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return PPTHandler.lambda$null$1((Shape) obj);
            }
        }).map(new Function() { // from class: org.apache.poi.xslf.util.PPTHandler$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                EmbeddedExtractor.EmbeddedPart fromObjectShape;
                fromObjectShape = PPTHandler.fromObjectShape((Shape) obj);
                return fromObjectShape;
            }
        }).iterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$null$1(Shape s) {
        return s instanceof ObjectShape;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EmbeddedExtractor.EmbeddedPart fromObjectShape(Shape<?, ?> s) {
        ObjectShape<?, ?> os = (ObjectShape) s;
        final ObjectData od = os.getObjectData();
        EmbeddedExtractor.EmbeddedPart embed = new EmbeddedExtractor.EmbeddedPart();
        embed.setName(od.getFileName());
        embed.setData(new Supplier() { // from class: org.apache.poi.xslf.util.PPTHandler$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return PPTHandler.lambda$fromObjectShape$3(ObjectData.this);
            }
        });
        return embed;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ byte[] lambda$fromObjectShape$3(ObjectData od) {
        try {
            InputStream is = od.getInputStream();
            try {
                byte[] byteArray = IOUtils.toByteArray(is);
                if (is != null) {
                    is.close();
                }
                return byteArray;
            } finally {
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.poi.xslf.util.MFProxy
    public void setDefaultCharset(Charset charset) {
    }
}
