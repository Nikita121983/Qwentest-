package org.apache.poi.sl.draw;

import org.apache.poi.common.usermodel.fonts.FontInfo;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
class DrawFontInfo implements FontInfo {
    private final String typeface;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DrawFontInfo(String typeface) {
        this.typeface = typeface;
    }

    @Override // org.apache.poi.common.usermodel.fonts.FontInfo
    public String getTypeface() {
        return this.typeface;
    }
}
