package org.apache.poi.xslf.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Dimension2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StructuredDataId;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.draw.EmbeddedExtractor;
import org.apache.poi.util.Dimension2DDouble;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.LocaleUtil;
import org.apache.xmlbeans.XmlErrorCodes;

/* loaded from: classes10.dex */
public final class PPTX2PNG {
    private static final String OUTPUT_PAT_REGEX = "${basename}-${slideno}.${format}";
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) PPTX2PNG.class);
    private static final String INPUT_PAT_REGEX = "(?<slideno>[^|]+)\\|(?<format>[^|]+)\\|(?<basename>.+)\\.(?<ext>[^.]++)";
    private static final Pattern INPUT_PATTERN = Pattern.compile(INPUT_PAT_REGEX);
    private String slidenumStr = StructuredDataId.RESERVED;
    private float scale = 1.0f;
    private File file = null;
    private String format = ContentTypes.EXTENSION_PNG;
    private File outdir = null;
    private String outfile = null;
    private boolean quiet = false;
    private String outPattern = OUTPUT_PAT_REGEX;
    private File dumpfile = null;
    private String fixSide = "scale";
    private boolean ignoreParse = false;
    private boolean extractEmbedded = false;
    private FileMagic defaultFileType = FileMagic.OLE2;
    private boolean textAsShapes = false;
    private Charset charset = LocaleUtil.CHARSET_1252;
    private boolean emfHeaderBounds = false;
    private String fontDir = null;
    private String fontTtf = null;
    private String fontMap = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public interface ProxyConsumer {
        void parse(MFProxy mFProxy) throws IOException;
    }

    private static void usage(String error) {
        String msg = "Usage: PPTX2PNG [options] <.ppt/.pptx/.emf/.wmf file or 'stdin'>\n" + (error == null ? "" : "Error: " + error + StringUtils.LF) + "Options:\n    -scale <float>    scale factor\n    -fixSide <side>   specify side (long,short,width,height) to fix - use <scale> as amount of pixels\n    -slide <integer>  1-based index of a slide to render\n    -format <type>    png,gif,jpg,svg,pdf (,log,null for testing)\n    -outdir <dir>     output directory, defaults to origin of the ppt/pptx file\n    -outfile <file>   output filename, defaults to '" + OUTPUT_PAT_REGEX + "'\n    -outpat <pattern> output filename pattern, defaults to '" + OUTPUT_PAT_REGEX + "'\n                      patterns: basename, slideno, format, ext\n    -dump <file>      dump the annotated records to a file\n    -quiet            do not write to console (for normal processing)\n    -ignoreParse      ignore parsing error and continue with the records read until the error\n    -extractEmbedded  extract embedded parts\n    -inputType <type> default input file type (OLE2,WMF,EMF), default is OLE2 = Powerpoint\n                      some files (usually wmf) don't have a header, i.e. an identifiable file magic\n    -textAsShapes     text elements are saved as shapes in SVG, necessary for variable spacing\n                      often found in math formulas\n    -charset <cs>     sets the default charset to be used, defaults to Windows-1252\n    -emfHeaderBounds  force the usage of the emf header bounds to calculate the bounding box\n    -fontdir <dir>    (PDF only) font directories separated by \";\" - use $HOME for current users home dir\n                      defaults to the usual plattform directories\n    -fontTtf <regex>  (PDF only) regex to match the .ttf filenames\n    -fontMap <map>    \";\"-separated list of font mappings <typeface from>:<typeface to>";
        System.out.println(msg);
    }

    public static void main(String[] args) throws Exception {
        PPTX2PNG p2p = new PPTX2PNG();
        if (p2p.parseCommandLine(args)) {
            p2p.processFile();
        }
    }

    private PPTX2PNG() {
    }

    private boolean parseCommandLine(String[] args) {
        char c;
        if (args.length == 0) {
            usage(null);
            return false;
        }
        int i = 0;
        while (i < args.length) {
            String opt = i + 1 < args.length ? args[i + 1] : null;
            String lowerCase = args[i].toLowerCase(Locale.ROOT);
            switch (lowerCase.hashCode()) {
                case -1227488812:
                    if (lowerCase.equals("-ignoreparse")) {
                        c = 11;
                        break;
                    }
                    break;
                case -1148328386:
                    if (lowerCase.equals("-extractembedded")) {
                        c = '\f';
                        break;
                    }
                    break;
                case -1022087042:
                    if (lowerCase.equals("-textasshapes")) {
                        c = '\n';
                        break;
                    }
                    break;
                case -558344333:
                    if (lowerCase.equals("-emfheaderbounds")) {
                        c = 14;
                        break;
                    }
                    break;
                case 10629373:
                    if (lowerCase.equals("-outfile")) {
                        c = 4;
                        break;
                    }
                    break;
                case 14180964:
                    if (lowerCase.equals("-format")) {
                        c = 2;
                        break;
                    }
                    break;
                case 44653473:
                    if (lowerCase.equals("-dump")) {
                        c = 7;
                        break;
                    }
                    break;
                case 273562143:
                    if (lowerCase.equals("-fixside")) {
                        c = '\b';
                        break;
                    }
                    break;
                case 277435628:
                    if (lowerCase.equals("-outdir")) {
                        c = 3;
                        break;
                    }
                    break;
                case 277446914:
                    if (lowerCase.equals("-outpat")) {
                        c = 5;
                        break;
                    }
                    break;
                case 436126993:
                    if (lowerCase.equals("-fontdir")) {
                        c = 15;
                        break;
                    }
                    break;
                case 436135392:
                    if (lowerCase.equals("-fontmap")) {
                        c = 17;
                        break;
                    }
                    break;
                case 436142698:
                    if (lowerCase.equals("-fontttf")) {
                        c = 16;
                        break;
                    }
                    break;
                case 1396259367:
                    if (lowerCase.equals("-quiet")) {
                        c = 6;
                        break;
                    }
                    break;
                case 1397562685:
                    if (lowerCase.equals("-scale")) {
                        c = 0;
                        break;
                    }
                    break;
                case 1397838244:
                    if (lowerCase.equals("-slide")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1454756983:
                    if (lowerCase.equals("-inputtype")) {
                        c = '\t';
                        break;
                    }
                    break;
                case 1856128127:
                    if (lowerCase.equals("-charset")) {
                        c = '\r';
                        break;
                    }
                    break;
            }
            c = 65535;
            switch (c) {
                case 0:
                    if (opt == null) {
                        break;
                    } else {
                        this.scale = Float.parseFloat(opt);
                        i++;
                        break;
                    }
                case 1:
                    this.slidenumStr = opt;
                    i++;
                    break;
                case 2:
                    this.format = opt;
                    i++;
                    break;
                case 3:
                    if (opt == null) {
                        break;
                    } else {
                        this.outdir = new File(opt);
                        i++;
                        break;
                    }
                case 4:
                    this.outfile = opt;
                    i++;
                    break;
                case 5:
                    this.outPattern = opt;
                    i++;
                    break;
                case 6:
                    this.quiet = true;
                    break;
                case 7:
                    if (opt != null) {
                        this.dumpfile = new File(opt);
                        i++;
                        break;
                    } else {
                        this.dumpfile = new File("pptx2png.dump");
                        break;
                    }
                case '\b':
                    if (opt != null) {
                        this.fixSide = opt.toLowerCase(Locale.ROOT);
                        i++;
                        break;
                    } else {
                        this.fixSide = XmlErrorCodes.LONG;
                        break;
                    }
                case '\t':
                    if (opt != null) {
                        this.defaultFileType = FileMagic.valueOf(opt);
                        i++;
                        break;
                    } else {
                        this.defaultFileType = FileMagic.OLE2;
                        break;
                    }
                case '\n':
                    this.textAsShapes = true;
                    break;
                case 11:
                    this.ignoreParse = true;
                    break;
                case '\f':
                    this.extractEmbedded = true;
                    break;
                case '\r':
                    if (opt != null) {
                        this.charset = Charset.forName(opt);
                        i++;
                        break;
                    } else {
                        this.charset = LocaleUtil.CHARSET_1252;
                        break;
                    }
                case 14:
                    this.emfHeaderBounds = true;
                    break;
                case 15:
                    if (opt != null) {
                        this.fontDir = opt;
                        i++;
                        break;
                    } else {
                        this.fontDir = null;
                        break;
                    }
                case 16:
                    if (opt != null) {
                        this.fontTtf = opt;
                        i++;
                        break;
                    } else {
                        this.fontTtf = null;
                        break;
                    }
                case 17:
                    if (opt != null) {
                        this.fontMap = opt;
                        i++;
                        break;
                    } else {
                        this.fontMap = null;
                        break;
                    }
                default:
                    this.file = new File(args[i]);
                    break;
            }
            i++;
        }
        boolean isStdin = this.file != null && "stdin".equalsIgnoreCase(this.file.getName());
        if (!isStdin && (this.file == null || !this.file.exists())) {
            usage("File not specified or it doesn't exist");
            return false;
        }
        if (this.format == null || !this.format.matches("^(png|gif|jpg|null|svg|pdf|log)$")) {
            usage("Invalid format given");
            return false;
        }
        if (this.outdir == null) {
            if (isStdin) {
                usage("When reading from STDIN, you need to specify an outdir.");
                return false;
            }
            this.outdir = this.file.getAbsoluteFile().getParentFile();
        }
        if (!this.outdir.exists()) {
            usage("Outdir doesn't exist");
            return false;
        }
        if (!"null".equals(this.format) && (this.outdir == null || !this.outdir.exists() || !this.outdir.isDirectory())) {
            usage("Output directory doesn't exist");
            return false;
        }
        if (this.scale < 0.0f) {
            usage("Invalid scale given");
            return false;
        }
        if ("long,short,width,height,scale".contains(this.fixSide)) {
            return true;
        }
        usage("<fixside> must be one of long / short / width / height / scale");
        return false;
    }

    private void processFile() throws IOException {
        if (!this.quiet) {
            System.out.println("Processing " + this.file);
        }
        try {
            MFProxy proxy = initProxy(this.file);
            try {
                Set<Integer> slidenum = proxy.slideIndexes(this.slidenumStr);
                if (slidenum.isEmpty()) {
                    usage("slidenum must be either -1 (for all) or within range: [1.." + proxy.getSlideCount() + "] for " + this.file);
                    if (proxy != null) {
                        proxy.close();
                        return;
                    }
                    return;
                }
                Dimension2D dim = new Dimension2DDouble();
                double lenSide = getDimensions(proxy, dim);
                int width = Math.max((int) Math.rint(dim.getWidth()), 1);
                int height = Math.max((int) Math.rint(dim.getHeight()), 1);
                OutputFormat outputFormat = getOutput();
                try {
                    Iterator<Integer> it = slidenum.iterator();
                    while (it.hasNext()) {
                        int slideNo = it.next().intValue();
                        proxy.setSlideNo(slideNo);
                        if (!this.quiet) {
                            String title = proxy.getTitle();
                            System.out.println("Rendering slide " + slideNo + (title == null ? "" : ": " + title.trim()));
                        }
                        dumpRecords(proxy);
                        extractEmbedded(proxy, slideNo);
                        Graphics2D graphics = outputFormat.addSlide(width, height);
                        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                        graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
                        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                        graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                        graphics.setRenderingHint(Drawable.DEFAULT_CHARSET, getDefaultCharset());
                        graphics.setRenderingHint(Drawable.EMF_FORCE_HEADER_BOUNDS, Boolean.valueOf(this.emfHeaderBounds));
                        if (this.fontMap != null) {
                            Map<String, String> fmap = (Map) Arrays.stream(this.fontMap.split(";")).map(new Function() { // from class: org.apache.poi.xslf.util.PPTX2PNG$$ExternalSyntheticLambda2
                                @Override // java.util.function.Function
                                public final Object apply(Object obj) {
                                    String[] split;
                                    split = ((String) obj).split(":");
                                    return split;
                                }
                            }).collect(Collectors.toMap(new Function() { // from class: org.apache.poi.xslf.util.PPTX2PNG$$ExternalSyntheticLambda3
                                @Override // java.util.function.Function
                                public final Object apply(Object obj) {
                                    return PPTX2PNG.lambda$processFile$1((String[]) obj);
                                }
                            }, new Function() { // from class: org.apache.poi.xslf.util.PPTX2PNG$$ExternalSyntheticLambda4
                                @Override // java.util.function.Function
                                public final Object apply(Object obj) {
                                    return PPTX2PNG.lambda$processFile$2((String[]) obj);
                                }
                            }));
                            graphics.setRenderingHint(Drawable.FONT_MAP, fmap);
                        }
                        graphics.scale(this.scale / lenSide, this.scale / lenSide);
                        graphics.setComposite(AlphaComposite.Clear);
                        graphics.fillRect(0, 0, width, height);
                        graphics.setComposite(AlphaComposite.SrcOver);
                        proxy.draw(graphics);
                        outputFormat.writeSlide(proxy, new File(this.outdir, calcOutFile(proxy, slideNo)));
                    }
                    outputFormat.writeDocument(proxy, new File(this.outdir, calcOutFile(proxy, 0)));
                    if (outputFormat != null) {
                        outputFormat.close();
                    }
                    if (proxy != null) {
                        proxy.close();
                    }
                    if (!this.quiet) {
                        System.out.println("Done");
                    }
                } finally {
                }
            } finally {
            }
        } catch (NoScratchpadException e) {
            usage("'" + this.file.getName() + "': Format not supported - try to include poi-scratchpad.jar into the CLASSPATH.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$processFile$1(String[] s) {
        return s[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$processFile$2(String[] s) {
        return s[1];
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private OutputFormat getOutput() {
        char c;
        String str = this.format;
        switch (str.hashCode()) {
            case 107332:
                if (str.equals("log")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 110834:
                if (str.equals("pdf")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 114276:
                if (str.equals("svg")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                try {
                    return new SVGFormat(this.textAsShapes);
                } catch (Exception | NoClassDefFoundError e) {
                    LOG.atError().withThrowable(e).log("Batik is not added to/working on the module-path. Use classpath mode instead of JPMS. Fallback to PNG.");
                    return new BitmapFormat(ContentTypes.EXTENSION_PNG);
                }
            case 1:
                return new PDFFormat(this.textAsShapes, this.fontDir, this.fontTtf);
            case 2:
                return new DummyFormat();
            default:
                return new BitmapFormat(this.format);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private double getDimensions(MFProxy proxy, Dimension2D dim) {
        char c;
        double lenSide;
        Dimension2D pgsize = proxy.getSize();
        String str = this.fixSide;
        switch (str.hashCode()) {
            case -1221029593:
                if (str.equals("height")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 3327612:
                if (str.equals(XmlErrorCodes.LONG)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 109250890:
                if (str.equals("scale")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 109413500:
                if (str.equals("short")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 113126854:
                if (str.equals("width")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 2:
                double lenSide2 = pgsize.getWidth();
                lenSide = Math.max(lenSide2, pgsize.getHeight());
                break;
            case 3:
                double lenSide3 = pgsize.getWidth();
                lenSide = Math.min(lenSide3, pgsize.getHeight());
                break;
            case 4:
                lenSide = pgsize.getWidth();
                break;
            case 5:
                lenSide = pgsize.getHeight();
                break;
            default:
                lenSide = 1.0d;
                break;
        }
        dim.setSize((pgsize.getWidth() * this.scale) / lenSide, (pgsize.getHeight() * this.scale) / lenSide);
        return lenSide;
    }

    private void dumpRecords(MFProxy proxy) throws IOException {
        if (this.dumpfile == null || "null".equals(this.dumpfile.getPath())) {
            return;
        }
        GenericRecord gr = proxy.getRoot();
        GenericRecordJsonWriter fw = new GenericRecordJsonWriter(this.dumpfile) { // from class: org.apache.poi.xslf.util.PPTX2PNG.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.apache.poi.util.GenericRecordJsonWriter
            public boolean printBytes(String name, Object o) {
                return false;
            }
        };
        try {
            if (gr == null) {
                fw.writeError(this.file.getName() + " doesn't support GenericRecord interface and can't be dumped to a file.");
            } else {
                fw.write(gr);
            }
            fw.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    fw.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    private void extractEmbedded(MFProxy proxy, int slideNo) throws IOException {
        if (!this.extractEmbedded) {
            return;
        }
        for (EmbeddedExtractor.EmbeddedPart ep : proxy.getEmbeddings(slideNo)) {
            String filename = ep.getName();
            OutputStream fos = Files.newOutputStream(new File(this.outdir, calcOutFile(proxy, slideNo).replaceFirst("\\.\\w+$", "") + "_" + new File(filename == null ? "dummy.dat" : filename).getName()).toPath(), new OpenOption[0]);
            try {
                fos.write(ep.getData().get());
                if (fos != null) {
                    fos.close();
                }
            } finally {
            }
        }
    }

    private MFProxy initProxy(final File file) throws IOException {
        FileMagic fm;
        ProxyConsumer con;
        MFProxy proxy;
        String fileName = file.getName().toLowerCase(Locale.ROOT);
        if ("stdin".equals(fileName)) {
            final InputStream bis = FileMagic.prepareToCheckMagic(System.in);
            fm = FileMagic.valueOf(bis);
            con = new ProxyConsumer() { // from class: org.apache.poi.xslf.util.PPTX2PNG$$ExternalSyntheticLambda0
                @Override // org.apache.poi.xslf.util.PPTX2PNG.ProxyConsumer
                public final void parse(MFProxy mFProxy) {
                    mFProxy.parse(bis);
                }
            };
        } else {
            fm = FileMagic.valueOf(file);
            con = new ProxyConsumer() { // from class: org.apache.poi.xslf.util.PPTX2PNG$$ExternalSyntheticLambda1
                @Override // org.apache.poi.xslf.util.PPTX2PNG.ProxyConsumer
                public final void parse(MFProxy mFProxy) {
                    mFProxy.parse(file);
                }
            };
        }
        if (fm == FileMagic.UNKNOWN) {
            fm = this.defaultFileType;
        }
        switch (fm) {
            case EMF:
                proxy = new EMFHandler();
                break;
            case WMF:
                proxy = new WMFHandler();
                break;
            default:
                proxy = new PPTHandler();
                break;
        }
        proxy.setIgnoreParse(this.ignoreParse);
        proxy.setQuiet(this.quiet);
        con.parse(proxy);
        proxy.setDefaultCharset(this.charset);
        return proxy;
    }

    private String calcOutFile(MFProxy proxy, int slideNo) {
        if (this.outfile != null) {
            return this.outfile;
        }
        String fileName = this.file.getName();
        if ("stdin".equals(fileName)) {
            fileName = fileName + ".ext";
        }
        String inname = String.format(Locale.ROOT, "%04d|%s|%s", Integer.valueOf(slideNo), this.format, fileName);
        String outpat = (proxy.getSlideCount() <= 1 || slideNo <= 0) ? this.outPattern.replaceAll("-?\\$\\{slideno}", "") : this.outPattern;
        return INPUT_PATTERN.matcher(inname).replaceAll(outpat);
    }

    private Charset getDefaultCharset() {
        return this.charset;
    }

    /* loaded from: classes10.dex */
    static class NoScratchpadException extends IOException {
        /* JADX INFO: Access modifiers changed from: package-private */
        public NoScratchpadException() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public NoScratchpadException(Throwable cause) {
            super(cause);
        }
    }
}
