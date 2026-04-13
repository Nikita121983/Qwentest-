package org.apache.poi.common.usermodel.fonts;

/* loaded from: classes10.dex */
public interface FontFacet {
    default int getWeight() {
        return FontHeader.REGULAR_WEIGHT;
    }

    default void setWeight(int weight) {
        throw new UnsupportedOperationException("FontFacet is read-only.");
    }

    default boolean isItalic() {
        return false;
    }

    default void setItalic(boolean italic) {
        throw new UnsupportedOperationException("FontFacet is read-only.");
    }

    default Object getFontData() {
        return null;
    }
}
