package org.apache.poi.sl.image;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;

@Internal
/* loaded from: classes10.dex */
public class ImageHeaderBitmap {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) ImageHeaderBitmap.class);
    private final Dimension size;

    public ImageHeaderBitmap(byte[] data, int offset) {
        Dimension dimension;
        BufferedImage img = null;
        try {
            img = ImageIO.read(UnsynchronizedByteArrayInputStream.builder().setByteArray(data).setOffset(offset).setLength(data.length - offset).get());
        } catch (IOException e) {
            LOG.atWarn().withThrowable(e).log("Can't determine image dimensions");
        }
        if (img == null) {
            dimension = new Dimension(200, 200);
        } else {
            dimension = new Dimension((int) Units.pixelToPoints(img.getWidth()), (int) Units.pixelToPoints(img.getHeight()));
        }
        this.size = dimension;
    }

    public Dimension getSize() {
        return this.size;
    }
}
