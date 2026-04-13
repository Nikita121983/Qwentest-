package org.apache.poi.xslf.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.draw.SVGPOIGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

@Internal
/* loaded from: classes10.dex */
public class SVGFormat implements OutputFormat {
    static final String svgNS = "http://www.w3.org/2000/svg";
    private SVGGraphics2D svgGenerator;
    private final boolean textAsShapes;

    public SVGFormat(boolean textAsShapes) {
        this.textAsShapes = textAsShapes;
    }

    @Override // org.apache.poi.xslf.util.OutputFormat
    public Graphics2D addSlide(double width, double height) {
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        Document document = domImpl.createDocument(svgNS, "svg", null);
        this.svgGenerator = new SVGPOIGraphics2D(document, this.textAsShapes);
        this.svgGenerator.setSVGCanvasSize(new Dimension((int) width, (int) height));
        this.svgGenerator.setRenderingHint(Drawable.CACHE_IMAGE_SOURCE, true);
        return this.svgGenerator;
    }

    @Override // org.apache.poi.xslf.util.OutputFormat
    public void writeSlide(MFProxy proxy, File outFile) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(outFile.toPath(), new OpenOption[0]), StandardCharsets.UTF_8);
        try {
            this.svgGenerator.stream(writer, true);
            writer.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    writer.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.svgGenerator.dispose();
    }
}
