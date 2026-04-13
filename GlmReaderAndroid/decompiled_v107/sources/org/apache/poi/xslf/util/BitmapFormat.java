package org.apache.poi.xslf.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import javax.imageio.ImageIO;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public class BitmapFormat implements OutputFormat {
    private final String format;
    private Graphics2D graphics;
    private BufferedImage img;

    public BitmapFormat(String format) {
        this.format = format;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // org.apache.poi.xslf.util.OutputFormat
    public Graphics2D addSlide(double width, double height) {
        char c;
        int type;
        String str = this.format;
        switch (str.hashCode()) {
            case 102340:
                if (str.equals(ContentTypes.EXTENSION_GIF)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 111145:
                if (str.equals(ContentTypes.EXTENSION_PNG)) {
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
            case 1:
                type = 2;
                break;
            default:
                type = 1;
                break;
        }
        this.img = new BufferedImage((int) width, (int) height, type);
        this.graphics = this.img.createGraphics();
        this.graphics.setRenderingHint(Drawable.BUFFERED_IMAGE, new WeakReference(this.img));
        return this.graphics;
    }

    @Override // org.apache.poi.xslf.util.OutputFormat
    public void writeSlide(MFProxy proxy, File outFile) throws IOException {
        if (!"null".equals(this.format)) {
            ImageIO.write(this.img, this.format, outFile);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.graphics != null) {
            this.graphics.dispose();
            this.img.flush();
        }
    }
}
