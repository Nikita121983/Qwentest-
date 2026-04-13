package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;

/* loaded from: classes10.dex */
public class XDDFFont {
    private CTTextFont font;
    private FontGroup group;

    public static XDDFFont unsetFontForGroup(FontGroup group) {
        return new XDDFFont(group, null);
    }

    public XDDFFont(FontGroup group, String typeface, Byte charset, Byte pitch, byte[] panose) {
        this(group, CTTextFont.Factory.newInstance());
        if (typeface == null) {
            if (this.font.getTypeface() != null && !this.font.getTypeface().equals("")) {
                this.font.setTypeface("");
            }
        } else {
            this.font.setTypeface(typeface);
        }
        if (charset == null) {
            if (this.font.isSetCharset()) {
                this.font.unsetCharset();
            }
        } else {
            this.font.setCharset(charset.byteValue());
        }
        if (pitch == null) {
            if (this.font.isSetPitchFamily()) {
                this.font.unsetPitchFamily();
            }
        } else {
            this.font.setPitchFamily(pitch.byteValue());
        }
        if (panose == null || panose.length == 0) {
            if (this.font.isSetPanose()) {
                this.font.unsetPanose();
                return;
            }
            return;
        }
        this.font.setPanose(panose);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFFont(FontGroup group, CTTextFont font) {
        this.group = group;
        this.font = font;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTTextFont getXmlObject() {
        return this.font;
    }

    public FontGroup getGroup() {
        return this.group;
    }

    public String getTypeface() {
        return this.font.getTypeface();
    }

    public Byte getCharset() {
        if (this.font.isSetCharset()) {
            return Byte.valueOf(this.font.getCharset());
        }
        return null;
    }

    public Byte getPitchFamily() {
        if (this.font.isSetPitchFamily()) {
            return Byte.valueOf(this.font.getPitchFamily());
        }
        return null;
    }

    public byte[] getPanose() {
        if (this.font.isSetPanose()) {
            return this.font.getPanose();
        }
        return null;
    }
}
