package org.apache.poi.xslf.draw;

import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.bridge.ViewBox;
import org.apache.batik.parser.DefaultLengthHandler;
import org.apache.batik.parser.LengthParser;
import org.apache.batik.parser.ParseException;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.Dimension2DDouble;
import org.apache.poi.util.Internal;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGSVGElement;

@Internal
/* loaded from: classes10.dex */
public class SVGUserAgent extends UserAgentAdapter {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) SVGUserAgent.class);
    private Rectangle2D viewbox;

    public SVGUserAgent() {
        addStdFeatures();
    }

    public Dimension2D getViewportSize() {
        if (this.viewbox != null) {
            return new Dimension2DDouble(this.viewbox.getWidth(), this.viewbox.getHeight());
        }
        return super.getViewportSize();
    }

    public Rectangle2D getViewbox() {
        return this.viewbox != null ? this.viewbox : new Rectangle2D.Double(0.0d, 0.0d, 1.0d, 1.0d);
    }

    public void initViewbox(SVGDocument doc) {
        this.viewbox = null;
        SVGSVGElement el = doc.getRootElement();
        if (el != null) {
            String viewBoxStr = el.getAttributeNS((String) null, "viewBox");
            if (viewBoxStr != null && !viewBoxStr.isEmpty()) {
                float[] rect = ViewBox.parseViewBoxAttribute(el, viewBoxStr, (BridgeContext) null);
                this.viewbox = new Rectangle2D.Float(rect[0], rect[1], rect[2], rect[3]);
                return;
            }
            float w = parseLength(el, "width");
            float h = parseLength(el, "height");
            if (w != 0.0f && h != 0.0f) {
                this.viewbox = new Rectangle2D.Double(0.0d, 0.0d, w, h);
            }
        }
    }

    private static float parseLength(SVGSVGElement el, String attr) {
        String a = el.getAttributeNS((String) null, attr);
        if (a == null || a.isEmpty()) {
            return 0.0f;
        }
        final float[] val = {0.0f};
        LengthParser lp = new LengthParser();
        lp.setLengthHandler(new DefaultLengthHandler() { // from class: org.apache.poi.xslf.draw.SVGUserAgent.1
            public void lengthValue(float v) throws ParseException {
                val[0] = v;
            }
        });
        lp.parse(a);
        return val[0];
    }

    public void displayMessage(String message) {
        LOG.atInfo().log(message);
    }

    public void displayError(String message) {
        LOG.atError().log(message);
    }

    public void displayError(Exception e) {
        LOG.atError().withThrowable(e).log(e.getMessage());
    }

    public void showAlert(String message) {
        LOG.atWarn().log(message);
    }
}
