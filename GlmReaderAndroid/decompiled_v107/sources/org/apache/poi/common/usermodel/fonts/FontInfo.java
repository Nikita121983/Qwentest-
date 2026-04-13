package org.apache.poi.common.usermodel.fonts;

import java.util.Collections;
import java.util.List;

/* loaded from: classes10.dex */
public interface FontInfo {
    String getTypeface();

    default Integer getIndex() {
        return null;
    }

    default void setIndex(int index) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    default void setTypeface(String typeface) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    default FontCharset getCharset() {
        return FontCharset.ANSI;
    }

    default void setCharset(FontCharset charset) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    default FontFamily getFamily() {
        return FontFamily.FF_DONTCARE;
    }

    default void setFamily(FontFamily family) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    default FontPitch getPitch() {
        return null;
    }

    default void setPitch(FontPitch pitch) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    default byte[] getPanose() {
        return null;
    }

    default void setPanose(byte[] panose) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    default List<? extends FontFacet> getFacets() {
        return Collections.emptyList();
    }
}
