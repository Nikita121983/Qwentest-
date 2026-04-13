package org.apache.poi.sl.usermodel;

import java.awt.Insets;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;

/* loaded from: classes10.dex */
public interface PictureShape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> extends SimpleShape<S, P> {
    Insets getClipping();

    PictureData getPictureData();

    default PictureData getAlternativePictureData() {
        return null;
    }
}
