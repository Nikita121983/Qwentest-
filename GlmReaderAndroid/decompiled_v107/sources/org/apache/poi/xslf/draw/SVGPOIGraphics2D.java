package org.apache.poi.xslf.draw;

import java.awt.RenderingHints;
import java.util.Map;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.poi.util.Internal;
import org.w3c.dom.Document;

@Internal
/* loaded from: classes10.dex */
public class SVGPOIGraphics2D extends SVGGraphics2D {
    private final RenderingHints hints;

    public SVGPOIGraphics2D(Document document, boolean textAsShapes) {
        super(getCtx(document), textAsShapes);
        this.hints = getGeneratorContext().getGraphicContextDefaults().getRenderingHints();
        getGeneratorContext().getExtensionHandler().setSvgGraphics2D(this);
    }

    private static SVGGeneratorContext getCtx(Document document) {
        SVGGeneratorContext context = SVGGeneratorContext.createDefault(document);
        context.setExtensionHandler(new SVGRenderExtension());
        SVGGeneratorContext.GraphicContextDefaults defs = new SVGGeneratorContext.GraphicContextDefaults();
        defs.setRenderingHints(new RenderingHints((Map) null));
        context.setGraphicContextDefaults(defs);
        return context;
    }

    public void setRenderingHint(RenderingHints.Key hintKey, Object hintValue) {
        this.hints.put(hintKey, hintValue);
        super.setRenderingHint(hintKey, hintValue);
    }

    public void setRenderingHints(Map hints) {
        this.hints.clear();
        this.hints.putAll(hints);
        super.setRenderingHints(hints);
    }

    public void addRenderingHints(Map hints) {
        this.hints.putAll(hints);
        super.addRenderingHints(hints);
    }
}
