package org.apache.poi.xslf.util;

import de.rototor.pdfbox.graphics2d.PdfBoxGraphics2D;
import de.rototor.pdfbox.graphics2d.PdfBoxGraphics2DFontTextDrawer;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public class PDFFormat implements OutputFormat {
    private PDPageContentStream contentStream;
    private final PDDocument document;
    private PdfBoxGraphics2DFontTextDrawer fontTextDrawer;
    private PdfBoxGraphics2D pdfBoxGraphics2D;

    public PDFFormat(boolean textAsShapes, String fontDir, String fontTtf) {
        if (!textAsShapes) {
            this.fontTextDrawer = new PDFFontMapper(fontDir, fontTtf);
        }
        this.document = new PDDocument();
    }

    @Override // org.apache.poi.xslf.util.OutputFormat
    public Graphics2D addSlide(double width, double height) throws IOException {
        PDPage page = new PDPage(new PDRectangle((float) width, (float) height));
        this.document.addPage(page);
        this.contentStream = new PDPageContentStream(this.document, page);
        this.pdfBoxGraphics2D = new PdfBoxGraphics2D(this.document, (float) width, (float) height);
        if (this.fontTextDrawer != null) {
            this.pdfBoxGraphics2D.setFontTextDrawer(this.fontTextDrawer);
        }
        return this.pdfBoxGraphics2D;
    }

    @Override // org.apache.poi.xslf.util.OutputFormat
    public void writeSlide(MFProxy proxy, File outFile) throws IOException {
        try {
            this.pdfBoxGraphics2D.dispose();
            PDFormXObject appearanceStream = this.pdfBoxGraphics2D.getXFormObject();
            this.contentStream.drawForm(appearanceStream);
        } finally {
            this.contentStream.close();
        }
    }

    @Override // org.apache.poi.xslf.util.OutputFormat
    public void writeDocument(MFProxy proxy, File outFile) throws IOException {
        this.document.save(new File(outFile.getCanonicalPath()));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            this.document.close();
        } finally {
            if (this.fontTextDrawer != null) {
                this.fontTextDrawer.close();
            }
        }
    }
}
