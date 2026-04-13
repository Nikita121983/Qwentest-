package org.apache.poi.sl.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import org.apache.commons.collections4.iterators.IteratorIterable;
import org.apache.commons.compress.compressors.bzip2.BZip2Constants;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.IOUtils;

/* loaded from: classes10.dex */
public class BitmapImageRenderer implements ImageRenderer {
    private static final String UNSUPPORTED_IMAGE_TYPE = "Unsupported Image Type";
    private String cachedContentType;
    private byte[] cachedImage;
    private boolean doCache;
    protected BufferedImage img;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) BitmapImageRenderer.class);
    private static final ImageLoader[] IMAGE_LOADERS = {new ImageLoader() { // from class: org.apache.poi.sl.draw.BitmapImageRenderer$$ExternalSyntheticLambda3
        @Override // org.apache.poi.sl.draw.BitmapImageRenderer.ImageLoader
        public final BufferedImage load(ImageReader imageReader, ImageInputStream imageInputStream, ImageReadParam imageReadParam) {
            BufferedImage loadColored;
            loadColored = BitmapImageRenderer.loadColored(imageReader, imageInputStream, imageReadParam);
            return loadColored;
        }
    }, new ImageLoader() { // from class: org.apache.poi.sl.draw.BitmapImageRenderer$$ExternalSyntheticLambda4
        @Override // org.apache.poi.sl.draw.BitmapImageRenderer.ImageLoader
        public final BufferedImage load(ImageReader imageReader, ImageInputStream imageInputStream, ImageReadParam imageReadParam) {
            BufferedImage loadGrayScaled;
            loadGrayScaled = BitmapImageRenderer.loadGrayScaled(imageReader, imageInputStream, imageReadParam);
            return loadGrayScaled;
        }
    }, new ImageLoader() { // from class: org.apache.poi.sl.draw.BitmapImageRenderer$$ExternalSyntheticLambda5
        @Override // org.apache.poi.sl.draw.BitmapImageRenderer.ImageLoader
        public final BufferedImage load(ImageReader imageReader, ImageInputStream imageInputStream, ImageReadParam imageReadParam) {
            BufferedImage loadTruncated;
            loadTruncated = BitmapImageRenderer.loadTruncated(imageReader, imageInputStream, imageReadParam);
            return loadTruncated;
        }
    }};
    private static final PictureData.PictureType[] ALLOWED_TYPES = {PictureData.PictureType.JPEG, PictureData.PictureType.PNG, PictureData.PictureType.BMP, PictureData.PictureType.GIF};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public interface ImageLoader {
        BufferedImage load(ImageReader imageReader, ImageInputStream imageInputStream, ImageReadParam imageReadParam) throws IOException;
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public boolean canRender(final String contentType) {
        return Stream.of((Object[]) ALLOWED_TYPES).anyMatch(new Predicate() { // from class: org.apache.poi.sl.draw.BitmapImageRenderer$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equalsIgnoreCase;
                equalsIgnoreCase = ((PictureData.PictureType) obj).contentType.equalsIgnoreCase(contentType);
                return equalsIgnoreCase;
            }
        });
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public void loadImage(InputStream data, String contentType) throws IOException {
        InputStream in = data;
        if (this.doCache) {
            UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().get();
            try {
                IOUtils.copy(data, bos);
                this.cachedImage = bos.toByteArray();
                this.cachedContentType = contentType;
                in = bos.toInputStream();
                if (bos != null) {
                    bos.close();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (bos != null) {
                        try {
                            bos.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        this.img = readImage(in, contentType);
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public void loadImage(byte[] data, String contentType) throws IOException {
        if (data == null) {
            return;
        }
        if (this.doCache) {
            this.cachedImage = (byte[]) data.clone();
            this.cachedContentType = contentType;
        }
        this.img = readImage(UnsynchronizedByteArrayInputStream.builder().setByteArray(data).get(), contentType);
    }

    private static BufferedImage readImage(InputStream data, String contentType) throws IOException {
        IOException lastException = null;
        BufferedImage img = null;
        ImageInputStream iis = new MemoryCacheImageInputStream(data);
        try {
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            while (true) {
                if (img != null) {
                    break;
                }
                if (!iter.hasNext()) {
                    break;
                }
                lastException = null;
                ImageReader reader = iter.next();
                ImageReadParam param = reader.getDefaultReadParam();
                for (ImageLoader il : IMAGE_LOADERS) {
                    iis.reset();
                    iis.mark();
                    try {
                        img = il.load(reader, iis, param);
                    } catch (IOException e) {
                        lastException = e;
                        if (UNSUPPORTED_IMAGE_TYPE.equals(e.getMessage())) {
                        }
                    } catch (RuntimeException e2) {
                        lastException = new IOException("ImageIO runtime exception", e2);
                    }
                    if (img != null) {
                        break;
                    }
                }
                reader.dispose();
            }
            iis.close();
            if (img == null) {
                if (lastException != null) {
                    throw lastException;
                }
                LOG.atWarn().log("Content-type: {} is not supported. Image ignored.", contentType);
                return null;
            }
            if (img.getColorModel().hasAlpha()) {
                return img;
            }
            BufferedImage argbImg = new BufferedImage(img.getWidth(), img.getHeight(), 2);
            Graphics g = argbImg.getGraphics();
            g.drawImage(img, 0, 0, (ImageObserver) null);
            g.dispose();
            return argbImg;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    iis.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BufferedImage loadColored(ImageReader reader, ImageInputStream iis, ImageReadParam param) throws IOException {
        reader.setInput(iis, false, true);
        return reader.read(0, param);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BufferedImage loadGrayScaled(ImageReader reader, ImageInputStream iis, final ImageReadParam param) throws IOException {
        Iterable<ImageTypeSpecifier> specs = new IteratorIterable<>(reader.getImageTypes(0));
        Optional findFirst = StreamSupport.stream(specs.spliterator(), false).filter(new Predicate() { // from class: org.apache.poi.sl.draw.BitmapImageRenderer$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return BitmapImageRenderer.lambda$loadGrayScaled$1((ImageTypeSpecifier) obj);
            }
        }).findFirst();
        param.getClass();
        findFirst.ifPresent(new Consumer() { // from class: org.apache.poi.sl.draw.BitmapImageRenderer$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                param.setDestinationType((ImageTypeSpecifier) obj);
            }
        });
        reader.setInput(iis, false, true);
        return reader.read(0, param);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$loadGrayScaled$1(ImageTypeSpecifier its) {
        return its.getBufferedImageType() == 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BufferedImage loadTruncated(ImageReader reader, ImageInputStream iis, ImageReadParam param) throws IOException {
        reader.setInput(iis, false, true);
        int height = reader.getHeight(0);
        int width = reader.getWidth(0);
        Iterator<ImageTypeSpecifier> imageTypes = reader.getImageTypes(0);
        if (!imageTypes.hasNext()) {
            return null;
        }
        ImageTypeSpecifier imageTypeSpecifier = imageTypes.next();
        BufferedImage img = imageTypeSpecifier.createBufferedImage(width, height);
        param.setDestination(img);
        try {
            reader.read(0, param);
        } catch (IOException e) {
        }
        if (img.getColorModel().hasAlpha()) {
            return img;
        }
        int y = findTruncatedBlackBox(img, width, height);
        if (y >= height) {
            return img;
        }
        BufferedImage argbImg = new BufferedImage(width, height, 2);
        Graphics2D g = argbImg.createGraphics();
        g.clipRect(0, 0, width, y);
        g.drawImage(img, 0, 0, (ImageObserver) null);
        g.dispose();
        img.flush();
        return argbImg;
    }

    private static int findTruncatedBlackBox(BufferedImage img, int width, int height) {
        for (int h = height - 1; h > 0; h--) {
            int w = width - 1;
            while (w > 0) {
                int p = img.getRGB(w, h);
                if (p == -16777216) {
                    int p2 = width / 10;
                    w -= p2;
                } else {
                    return h + 1;
                }
            }
        }
        return 0;
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public BufferedImage getImage() {
        return this.img;
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public BufferedImage getImage(Dimension2D dim) {
        if (this.img == null) {
            return null;
        }
        double w_old = this.img.getWidth();
        double h_old = this.img.getHeight();
        double w_new = dim.getWidth();
        double h_new = dim.getHeight();
        if (w_old == w_new && h_old == h_new) {
            return this.img;
        }
        BufferedImage scaled = new BufferedImage((int) w_new, (int) h_new, 2);
        AffineTransform at = new AffineTransform();
        at.scale(w_new / w_old, h_new / h_old);
        AffineTransformOp scaleOp = new AffineTransformOp(at, 2);
        scaleOp.filter(this.img, scaled);
        return scaled;
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public Rectangle2D getBounds() {
        return this.img == null ? new Rectangle2D.Double() : new Rectangle2D.Double(0.0d, 0.0d, this.img.getWidth(), this.img.getHeight());
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public void setAlpha(double alpha) {
        this.img = setAlpha(this.img, alpha);
    }

    public static BufferedImage setAlpha(BufferedImage image, double alpha) {
        if (image == null) {
            return new BufferedImage(1, 1, 2);
        }
        if (alpha == 0.0d) {
            return image;
        }
        float[] scalefactors = {1.0f, 1.0f, 1.0f, (float) alpha};
        float[] offsets = {0.0f, 0.0f, 0.0f, 0.0f};
        RescaleOp op = new RescaleOp(scalefactors, offsets, (RenderingHints) null);
        return op.filter(image, (BufferedImage) null);
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public boolean drawImage(Graphics2D graphics, Rectangle2D anchor) {
        return drawImage(graphics, anchor, null);
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public boolean drawImage(Graphics2D graphics, Rectangle2D anchor, Insets clip) {
        Insets clip2;
        if (this.img == null) {
            return false;
        }
        boolean isClipped = true;
        if (clip != null) {
            clip2 = clip;
        } else {
            isClipped = false;
            clip2 = new Insets(0, 0, 0, 0);
        }
        int iw = this.img.getWidth();
        int ih = this.img.getHeight();
        double cw = ((BZip2Constants.BASEBLOCKSIZE - clip2.left) - clip2.right) / 100000.0d;
        double ch = ((BZip2Constants.BASEBLOCKSIZE - clip2.top) - clip2.bottom) / 100000.0d;
        double sx = anchor.getWidth() / (iw * cw);
        double sy = anchor.getHeight() / (ih * ch);
        double tx = anchor.getX() - (((iw * sx) * clip2.left) / 100000.0d);
        double ty = anchor.getY() - (((ih * sy) * clip2.top) / 100000.0d);
        AffineTransform at = new AffineTransform(sx, 0.0d, 0.0d, sy, tx, ty);
        Shape clipOld = graphics.getClip();
        if (isClipped) {
            graphics.clip(anchor.getBounds2D());
        }
        graphics.drawRenderedImage(this.img, at);
        graphics.setClip(clipOld);
        return true;
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public Rectangle2D getNativeBounds() {
        return new Rectangle2D.Double(0.0d, 0.0d, this.img.getWidth(), this.img.getHeight());
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public void setCacheInput(boolean enable) {
        this.doCache = enable;
        if (!enable) {
            this.cachedContentType = null;
            this.cachedImage = null;
        }
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public byte[] getCachedImage() {
        return this.cachedImage;
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public String getCachedContentType() {
        return this.cachedContentType;
    }
}
