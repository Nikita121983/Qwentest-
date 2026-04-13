package org.apache.poi.sl.usermodel;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;

/* loaded from: classes10.dex */
public interface TextShape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> extends SimpleShape<S, P>, Iterable<P> {

    /* loaded from: classes10.dex */
    public enum TextAutofit {
        NONE,
        NORMAL,
        SHAPE
    }

    /* loaded from: classes10.dex */
    public enum TextDirection {
        HORIZONTAL,
        VERTICAL,
        VERTICAL_270,
        STACKED
    }

    TextRun appendText(String str, boolean z);

    Insets2D getInsets();

    String getText();

    TextDirection getTextDirection();

    double getTextHeight();

    double getTextHeight(Graphics2D graphics2D);

    List<P> getTextParagraphs();

    TextPlaceholder getTextPlaceholder();

    Double getTextRotation();

    VerticalAlignment getVerticalAlignment();

    boolean getWordWrap();

    boolean isHorizontalCentered();

    Rectangle2D resizeToFitText();

    Rectangle2D resizeToFitText(Graphics2D graphics2D);

    void setHorizontalCentered(Boolean bool);

    void setInsets(Insets2D insets2D);

    TextRun setText(String str);

    void setTextDirection(TextDirection textDirection);

    void setTextPlaceholder(TextPlaceholder textPlaceholder);

    void setTextRotation(Double d);

    void setVerticalAlignment(VerticalAlignment verticalAlignment);

    void setWordWrap(boolean z);

    /* loaded from: classes10.dex */
    public enum TextPlaceholder {
        TITLE(0),
        BODY(1),
        CENTER_TITLE(6),
        CENTER_BODY(5),
        HALF_BODY(7),
        QUARTER_BODY(8),
        NOTES(2),
        OTHER(4);

        public final int nativeId;

        TextPlaceholder(int nativeId) {
            this.nativeId = nativeId;
        }

        public static TextPlaceholder fromNativeId(int nativeId) {
            for (TextPlaceholder ld : values()) {
                if (ld.nativeId == nativeId) {
                    return ld;
                }
            }
            return null;
        }

        public static boolean isTitle(int nativeId) {
            return nativeId == TITLE.nativeId || nativeId == CENTER_TITLE.nativeId;
        }
    }
}
