package org.apache.poi.xslf.util;

import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.sl.draw.BitmapImageRenderer;
import org.apache.poi.sl.draw.DrawPictureShape;
import org.apache.poi.sl.draw.EmbeddedExtractor;
import org.apache.poi.sl.draw.ImageRenderer;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.util.PPTX2PNG;

/* JADX INFO: Access modifiers changed from: package-private */
@Internal
/* loaded from: classes10.dex */
public class EMFHandler extends MFProxy {
    private ImageRenderer imgr = null;
    private InputStream is;

    @Override // org.apache.poi.xslf.util.MFProxy
    public void parse(File file) throws IOException {
        this.is = file.toURI().toURL().openStream();
        parse(this.is);
    }

    @Override // org.apache.poi.xslf.util.MFProxy
    public void parse(InputStream is) throws IOException {
        this.imgr = DrawPictureShape.getImageRenderer(null, getContentType());
        if (this.imgr instanceof BitmapImageRenderer) {
            throw new PPTX2PNG.NoScratchpadException();
        }
        this.imgr.loadImage(is, getContentType());
        if (this.ignoreParse) {
            try {
                this.imgr.getDimension();
            } catch (Exception e) {
            }
        }
    }

    protected String getContentType() {
        return PictureData.PictureType.EMF.contentType;
    }

    @Override // org.apache.poi.xslf.util.MFProxy
    public Dimension2D getSize() {
        return this.imgr.getDimension();
    }

    @Override // org.apache.poi.xslf.util.MFProxy
    public String getTitle() {
        return "";
    }

    @Override // org.apache.poi.xslf.util.MFProxy
    public void draw(Graphics2D ctx) {
        Dimension2D dim = getSize();
        this.imgr.drawImage(ctx, new Rectangle2D.Double(0.0d, 0.0d, dim.getWidth(), dim.getHeight()));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.is != null) {
            try {
                this.is.close();
            } finally {
                this.is = null;
            }
        }
    }

    @Override // org.apache.poi.xslf.util.MFProxy
    public GenericRecord getRoot() {
        return this.imgr.getGenericRecord();
    }

    @Override // org.apache.poi.xslf.util.MFProxy
    public Iterable<EmbeddedExtractor.EmbeddedPart> getEmbeddings(int slideNo) {
        if (this.imgr instanceof EmbeddedExtractor) {
            return ((EmbeddedExtractor) this.imgr).getEmbeddings();
        }
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.poi.xslf.util.MFProxy
    public void setDefaultCharset(Charset charset) {
        this.imgr.setDefaultCharset(charset);
    }
}
