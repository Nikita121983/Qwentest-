package org.apache.poi.xslf.draw;

import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.ext.awt.RenderingHintsKeyExt;
import org.apache.batik.ext.awt.image.renderable.ClipRable;
import org.apache.batik.ext.awt.image.renderable.ClipRable8Bit;
import org.apache.batik.ext.awt.image.renderable.Filter;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.draw.ImageRenderer;
import org.apache.poi.sl.usermodel.PictureData;
import org.w3c.dom.svg.SVGDocument;

/* loaded from: classes10.dex */
public class SVGImageRenderer implements ImageRenderer {
    private final BridgeContext context;
    private final SAXSVGDocumentFactory svgFact;
    private final GVTBuilder builder = new GVTBuilder();
    private double alpha = 1.0d;

    public SVGImageRenderer() {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        this.svgFact = new SAXSVGDocumentFactory(parser);
        SVGUserAgent agent = new SVGUserAgent();
        DocumentLoader loader = new DocumentLoader(agent);
        this.context = new BridgeContext(agent, loader);
        this.context.setDynamic(true);
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public void loadImage(InputStream data, String contentType) throws IOException {
        SVGDocument document = this.svgFact.createDocument("", data);
        this.context.getUserAgent().initViewbox(document);
        this.builder.build(this.context, document);
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public void loadImage(byte[] data, String contentType) throws IOException {
        loadImage(UnsynchronizedByteArrayInputStream.builder().setByteArray(data).get(), contentType);
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public Rectangle2D getBounds() {
        return this.context.getUserAgent().getViewbox();
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public BufferedImage getImage() {
        return getImage(getDimension());
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public BufferedImage getImage(Dimension2D dim) {
        BufferedImage bi = new BufferedImage((int) dim.getWidth(), (int) dim.getHeight(), 2);
        Graphics2D g2d = bi.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHintsKeyExt.KEY_BUFFERED_IMAGE, new WeakReference(bi));
        Dimension2D dimSVG = getDimension();
        double scaleX = dim.getWidth() / dimSVG.getWidth();
        double scaleY = dim.getHeight() / dimSVG.getHeight();
        g2d.scale(scaleX, scaleY);
        getSVGRoot().paint(g2d);
        g2d.dispose();
        return bi;
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public boolean drawImage(Graphics2D graphics, Rectangle2D anchor) {
        return drawImage(graphics, anchor, null);
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public boolean drawImage(Graphics2D graphics, Rectangle2D anchor, Insets clip) {
        graphics.setRenderingHint(RenderingHintsKeyExt.KEY_BUFFERED_IMAGE, graphics.getRenderingHint(Drawable.BUFFERED_IMAGE));
        GraphicsNode svgRoot = getSVGRoot();
        Dimension2D bounds = getDimension();
        AffineTransform at = new AffineTransform();
        at.translate(anchor.getX(), anchor.getY());
        at.scale(anchor.getWidth() / bounds.getWidth(), anchor.getHeight() / bounds.getHeight());
        svgRoot.setTransform(at);
        if (clip == null) {
            svgRoot.setClip((ClipRable) null);
        } else {
            svgRoot.setClip(new ClipRable8Bit((Filter) null, new Rectangle2D.Double(anchor.getX() + clip.left, anchor.getY() + clip.top, anchor.getWidth() - (clip.left + clip.right), anchor.getHeight() - (clip.top + clip.bottom))));
        }
        svgRoot.paint(graphics);
        return true;
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public boolean canRender(String contentType) {
        return PictureData.PictureType.SVG.contentType.equalsIgnoreCase(contentType);
    }

    @Override // org.apache.poi.sl.draw.ImageRenderer
    public Rectangle2D getNativeBounds() {
        return getSVGRoot().getPrimitiveBounds();
    }

    public GraphicsNode getSVGRoot() {
        return this.context.getGraphicsNode(this.context.getDocument());
    }
}
