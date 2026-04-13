package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.TextType;
import com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.Internal;

/* loaded from: classes10.dex */
public class XDGFText {
    XDGFShape _parent;
    TextType _text;

    public XDGFText(TextType text, XDGFShape parent) {
        this._text = text;
        this._parent = parent;
    }

    @Internal
    TextType getXmlObject() {
        return this._text;
    }

    public String getTextContent() {
        return ((TextTypeImpl) this._text).getStringValue();
    }

    public Rectangle2D.Double getTextBounds() {
        double txtPinX = this._parent.getTxtPinX().doubleValue();
        double txtPinY = this._parent.getTxtPinY().doubleValue();
        double txtLocPinX = this._parent.getTxtLocPinX().doubleValue();
        double txtLocPinY = this._parent.getTxtLocPinY().doubleValue();
        double txtWidth = this._parent.getTxtWidth().doubleValue();
        double txtHeight = this._parent.getTxtHeight().doubleValue();
        double x = txtPinX - txtLocPinX;
        double y = txtPinY - txtLocPinY;
        return new Rectangle2D.Double(x, y, txtWidth, txtHeight);
    }

    public Path2D.Double getBoundsAsPath() {
        Rectangle2D.Double rect = getTextBounds();
        double w = rect.getWidth();
        double h = rect.getHeight();
        Path2D.Double bounds = new Path2D.Double();
        bounds.moveTo(0.0d, 0.0d);
        bounds.lineTo(w, 0.0d);
        bounds.lineTo(w, h);
        bounds.lineTo(0.0d, h);
        bounds.lineTo(0.0d, 0.0d);
        return bounds;
    }

    public Point2D.Double getTextCenter() {
        return new Point2D.Double(this._parent.getTxtLocPinX().doubleValue(), this._parent.getTxtLocPinY().doubleValue());
    }

    public void draw(Graphics2D graphics) {
        FontRenderContext frc;
        String[] lines;
        String textContent;
        Rectangle2D.Double bounds;
        String textContent2 = getTextContent();
        if (textContent2.isEmpty()) {
            return;
        }
        Rectangle2D.Double bounds2 = getTextBounds();
        String[] lines2 = textContent2.trim().split(StringUtils.LF);
        FontRenderContext frc2 = graphics.getFontRenderContext();
        Font font = graphics.getFont();
        AffineTransform oldTr = graphics.getTransform();
        Boolean flipX = this._parent.getFlipX();
        Boolean flipY = this._parent.getFlipY();
        if (flipY == null || !this._parent.getFlipY().booleanValue()) {
            graphics.translate(bounds2.x, bounds2.y);
            graphics.scale(1.0d, -1.0d);
            graphics.translate(0.0d, (-bounds2.height) + graphics.getFontMetrics().getMaxCharBounds(graphics).getHeight());
        }
        if (flipX != null && this._parent.getFlipX().booleanValue()) {
            graphics.scale(-1.0d, 1.0d);
            graphics.translate(-bounds2.width, 0.0d);
        }
        Double txtAngle = this._parent.getTxtAngle();
        if (txtAngle != null && Math.abs(txtAngle.doubleValue()) > 0.01d) {
            graphics.rotate(txtAngle.doubleValue());
        }
        float nextY = 0.0f;
        int length = lines2.length;
        int i = 0;
        while (i < length) {
            String line = lines2[i];
            if (line.isEmpty()) {
                textContent = textContent2;
                bounds = bounds2;
                lines = lines2;
                frc = frc2;
            } else {
                TextLayout layout = new TextLayout(line, font, frc2);
                if (layout.isLeftToRight()) {
                    layout.draw(graphics, 0.0f, nextY);
                    textContent = textContent2;
                    bounds = bounds2;
                    lines = lines2;
                    frc = frc2;
                } else {
                    frc = frc2;
                    lines = lines2;
                    textContent = textContent2;
                    bounds = bounds2;
                    layout.draw(graphics, (float) (bounds2.width - layout.getAdvance()), nextY);
                }
                nextY += layout.getAscent() + layout.getDescent() + layout.getLeading();
            }
            i++;
            frc2 = frc;
            lines2 = lines;
            textContent2 = textContent;
            bounds2 = bounds;
        }
        graphics.setTransform(oldTr);
    }
}
