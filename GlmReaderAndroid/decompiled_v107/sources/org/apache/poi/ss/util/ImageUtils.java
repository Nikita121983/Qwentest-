package org.apache.poi.ss.util;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.Units;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/* loaded from: classes10.dex */
public final class ImageUtils {
    private static final int HEIGHT_UNITS = 256;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) ImageUtils.class);
    private static final int WIDTH_UNITS = 1024;

    private ImageUtils() {
    }

    public static Dimension getImageDimension(InputStream is, int type) {
        Dimension size = new Dimension();
        switch (type) {
            case 5:
            case 6:
            case 7:
                try {
                    ImageInputStream iis = ImageIO.createImageInputStream(is);
                    try {
                        Iterator<ImageReader> i = ImageIO.getImageReaders(iis);
                        if (i.hasNext()) {
                            ImageReader r = i.next();
                            try {
                                r.setInput(iis);
                                BufferedImage img = r.read(0);
                                int[] dpi = getResolution(r);
                                if (dpi[0] == 0) {
                                    dpi[0] = 96;
                                }
                                if (dpi[1] == 0) {
                                    dpi[1] = 96;
                                }
                                size.width = (img.getWidth() * 96) / dpi[0];
                                size.height = (img.getHeight() * 96) / dpi[1];
                                r.dispose();
                            } catch (Throwable th) {
                                r.dispose();
                                throw th;
                            }
                        } else {
                            LOG.atWarn().log("ImageIO found no images");
                        }
                        if (iis != null) {
                            iis.close();
                        }
                    } finally {
                    }
                } catch (IOException e) {
                    LOG.atWarn().withThrowable(e).log("Failed to determine image dimensions");
                }
                return size;
            default:
                LOG.atWarn().log("Only JPEG, PNG and DIB pictures can be automatically sized");
                return size;
        }
    }

    public static int[] getResolution(ImageReader r) throws IOException {
        int hdpi = 96;
        int vdpi = 96;
        Element node = (Element) r.getImageMetadata(0).getAsTree("javax_imageio_1.0");
        NodeList lst = node.getElementsByTagName("HorizontalPixelSize");
        if (lst != null && lst.getLength() == 1) {
            hdpi = (int) (25.4d / Float.parseFloat(((Element) lst.item(0)).getAttribute("value")));
        }
        NodeList lst2 = node.getElementsByTagName("VerticalPixelSize");
        if (lst2 != null && lst2.getLength() == 1) {
            vdpi = (int) (25.4d / Float.parseFloat(((Element) lst2.item(0)).getAttribute("value")));
        }
        return new int[]{hdpi, vdpi};
    }

    public static Dimension setPreferredSize(Picture picture, double scaleX, double scaleY) {
        final ClientAnchor anchor = picture.getClientAnchor();
        boolean isHSSF = anchor instanceof HSSFClientAnchor;
        PictureData data = picture.getPictureData();
        final Sheet sheet = picture.getSheet();
        try {
            Dimension imgSize = (scaleX == Double.MAX_VALUE || scaleY == Double.MAX_VALUE) ? getImageDimension(UnsynchronizedByteArrayInputStream.builder().setByteArray(data.getData()).get(), data.getPictureType()) : new Dimension();
            Dimension anchorSize = (scaleX == Double.MAX_VALUE && scaleY == Double.MAX_VALUE) ? new Dimension() : getDimensionFromAnchor(picture);
            double scaledWidth = scaleX == Double.MAX_VALUE ? imgSize.getWidth() : (anchorSize.getWidth() / 9525.0d) * scaleX;
            double scaledHeight = scaleY == Double.MAX_VALUE ? imgSize.getHeight() : (anchorSize.getHeight() / 9525.0d) * scaleY;
            short col1 = anchor.getCol1();
            int dx1 = anchor.getDx1();
            anchor.getClass();
            Consumer consumer = new Consumer() { // from class: org.apache.poi.ss.util.ImageUtils$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ClientAnchor.this.setCol2(((Integer) obj).intValue());
                }
            };
            anchor.getClass();
            Consumer consumer2 = new Consumer() { // from class: org.apache.poi.ss.util.ImageUtils$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ClientAnchor.this.setDx2(((Integer) obj).intValue());
                }
            };
            int i = isHSSF ? 1024 : 0;
            sheet.getClass();
            scaleCell(scaledWidth, col1, dx1, consumer, consumer2, i, new ImageUtils$$ExternalSyntheticLambda2(sheet));
            int row1 = anchor.getRow1();
            int dy1 = anchor.getDy1();
            anchor.getClass();
            Consumer consumer3 = new Consumer() { // from class: org.apache.poi.ss.util.ImageUtils$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ClientAnchor.this.setRow2(((Integer) obj).intValue());
                }
            };
            anchor.getClass();
            double scaledHeight2 = scaledHeight;
            scaleCell(scaledHeight2, row1, dy1, consumer3, new Consumer() { // from class: org.apache.poi.ss.util.ImageUtils$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ClientAnchor.this.setDy2(((Integer) obj).intValue());
                }
            }, isHSSF ? 256 : 0, new Function() { // from class: org.apache.poi.ss.util.ImageUtils$$ExternalSyntheticLambda5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    Number valueOf;
                    valueOf = Double.valueOf(ImageUtils.getRowHeightInPixels(Sheet.this, ((Integer) obj).intValue()));
                    return valueOf;
                }
            });
            return new Dimension((int) Math.round(scaledWidth * 9525.0d), (int) Math.round(scaledHeight2 * 9525.0d));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Dimension getDimensionFromAnchor(Picture picture) {
        ClientAnchor anchor = picture.getClientAnchor();
        boolean isHSSF = anchor instanceof HSSFClientAnchor;
        final Sheet sheet = picture.getSheet();
        Dimension imgSize = null;
        if (anchor.getCol2() < anchor.getCol1() || anchor.getRow2() < anchor.getRow1()) {
            PictureData data = picture.getPictureData();
            try {
                imgSize = getImageDimension(UnsynchronizedByteArrayInputStream.builder().setByteArray(data.getData()).get(), data.getPictureType());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        double width = imgSize == null ? 0.0d : imgSize.getWidth();
        short col1 = anchor.getCol1();
        int dx1 = anchor.getDx1();
        short col2 = anchor.getCol2();
        int dx2 = anchor.getDx2();
        int i = isHSSF ? 1024 : 0;
        sheet.getClass();
        int w = getDimFromCell(width, col1, dx1, col2, dx2, i, new ImageUtils$$ExternalSyntheticLambda2(sheet));
        int h = getDimFromCell(imgSize != null ? imgSize.getHeight() : 0.0d, anchor.getRow1(), anchor.getDy1(), anchor.getRow2(), anchor.getDy2(), isHSSF ? 256 : 0, new Function() { // from class: org.apache.poi.ss.util.ImageUtils$$ExternalSyntheticLambda6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Number valueOf;
                valueOf = Double.valueOf(ImageUtils.getRowHeightInPixels(Sheet.this, ((Integer) obj).intValue()));
                return valueOf;
            }
        });
        return new Dimension(w, h);
    }

    public static double getRowHeightInPixels(Sheet sheet, int rowNum) {
        Row r = sheet.getRow(rowNum);
        double points = r == null ? sheet.getDefaultRowHeightInPoints() : r.getHeightInPoints();
        return Units.toEMU(points) / 9525.0d;
    }

    private static void scaleCell(double targetSize, int startCell, int startD, Consumer<Integer> endCell, Consumer<Integer> endD, int hssfUnits, Function<Integer, Number> nextSize) {
        double dim;
        double totalDim;
        double d;
        double totalDim2;
        double endDval;
        int i = startCell;
        int i2 = startD;
        if (targetSize < 0.0d) {
            throw new IllegalArgumentException("target size < 0");
        }
        if (Double.isInfinite(targetSize) || Double.isNaN(targetSize)) {
            throw new IllegalArgumentException("target size " + targetSize + " is not supported");
        }
        int cellIdx = startCell;
        double delta = 0.0d;
        while (true) {
            dim = nextSize.apply(Integer.valueOf(cellIdx)).doubleValue();
            double remDim = dim;
            if (cellIdx != i) {
                totalDim = delta;
                d = 9525.0d;
            } else if (hssfUnits > 0) {
                d = 9525.0d;
                totalDim = delta;
                double totalDim3 = hssfUnits;
                remDim *= 1.0d - (i2 / totalDim3);
            } else {
                totalDim = delta;
                d = 9525.0d;
                double totalDim4 = i2;
                remDim -= totalDim4 / 9525.0d;
            }
            totalDim2 = targetSize - totalDim;
            if (totalDim2 < remDim) {
                break;
            }
            cellIdx++;
            i = startCell;
            i2 = startD;
            delta = totalDim + remDim;
        }
        if (hssfUnits > 0) {
            endDval = (totalDim2 / dim) * hssfUnits;
        } else {
            endDval = totalDim2 * d;
        }
        if (cellIdx == i) {
            endDval += i2;
        }
        endCell.accept(Integer.valueOf(cellIdx));
        endD.accept(Integer.valueOf((int) Math.rint(endDval)));
    }

    private static int getDimFromCell(double imgSize, int startCell, int startD, int endCell, int endD, int hssfUnits, Function<Integer, Number> nextSize) {
        double targetSize;
        int i = startCell;
        int i2 = startD;
        double d = 9525.0d;
        if (endCell < i) {
            targetSize = 9525.0d * imgSize;
        } else {
            double targetSize2 = 0.0d;
            int cellIdx = startCell;
            while (cellIdx <= endCell) {
                double dim = nextSize.apply(Integer.valueOf(cellIdx)).doubleValue() * d;
                double leadSpace = 0.0d;
                if (cellIdx == i) {
                    double d2 = i2;
                    if (hssfUnits > 0) {
                        d2 = (d2 * dim) / hssfUnits;
                    }
                    leadSpace = d2;
                }
                double trailSpace = 0.0d;
                if (cellIdx == endCell) {
                    trailSpace = hssfUnits > 0 ? ((hssfUnits - endD) * dim) / hssfUnits : dim - endD;
                }
                targetSize2 += (dim - leadSpace) - trailSpace;
                cellIdx++;
                i = startCell;
                i2 = startD;
                d = 9525.0d;
            }
            targetSize = targetSize2;
        }
        return (int) Math.rint(targetSize);
    }
}
