package org.apache.poi.xssf.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument;

/* loaded from: classes10.dex */
public class ThemesTable extends POIXMLDocumentPart implements Themes {
    private IndexedColorMap colorMap;
    private ThemeDocument theme;

    /* loaded from: classes10.dex */
    public enum ThemeElement {
        LT1(0, "Lt1"),
        DK1(1, "Dk1"),
        LT2(2, "Lt2"),
        DK2(3, "Dk2"),
        ACCENT1(4, "Accent1"),
        ACCENT2(5, "Accent2"),
        ACCENT3(6, "Accent3"),
        ACCENT4(7, "Accent4"),
        ACCENT5(8, "Accent5"),
        ACCENT6(9, "Accent6"),
        HLINK(10, "Hlink"),
        FOLHLINK(11, "FolHlink"),
        UNKNOWN(-1, null);

        public final int idx;
        public final String name;

        public static ThemeElement byId(int idx) {
            if (idx >= values().length || idx < 0) {
                return UNKNOWN;
            }
            return values()[idx];
        }

        ThemeElement(int idx, String name) {
            this.idx = idx;
            this.name = name;
        }
    }

    public ThemesTable() {
        this.theme = ThemeDocument.Factory.newInstance();
        this.theme.addNewTheme().addNewThemeElements();
    }

    public ThemesTable(PackagePart part) throws IOException {
        super(part);
        InputStream stream = part.getInputStream();
        try {
            readFrom(stream);
            if (stream != null) {
                stream.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public ThemesTable(InputStream stream) throws IOException {
        readFrom(stream);
    }

    public ThemesTable(ThemeDocument theme) {
        this.theme = theme;
    }

    public void readFrom(InputStream is) throws IOException {
        try {
            this.theme = ThemeDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setColorMap(IndexedColorMap colorMap) {
        this.colorMap = colorMap;
    }

    @Override // org.apache.poi.xssf.model.Themes
    public XSSFColor getThemeColor(int idx) {
        CTColor ctColor;
        byte[] rgb;
        CTColorScheme colorScheme = this.theme.getTheme().getThemeElements().getClrScheme();
        switch (ThemeElement.byId(idx)) {
            case LT1:
                ctColor = colorScheme.getLt1();
                break;
            case DK1:
                ctColor = colorScheme.getDk1();
                break;
            case LT2:
                ctColor = colorScheme.getLt2();
                break;
            case DK2:
                ctColor = colorScheme.getDk2();
                break;
            case ACCENT1:
                ctColor = colorScheme.getAccent1();
                break;
            case ACCENT2:
                ctColor = colorScheme.getAccent2();
                break;
            case ACCENT3:
                ctColor = colorScheme.getAccent3();
                break;
            case ACCENT4:
                ctColor = colorScheme.getAccent4();
                break;
            case ACCENT5:
                ctColor = colorScheme.getAccent5();
                break;
            case ACCENT6:
                ctColor = colorScheme.getAccent6();
                break;
            case HLINK:
                ctColor = colorScheme.getHlink();
                break;
            case FOLHLINK:
                ctColor = colorScheme.getFolHlink();
                break;
            default:
                return null;
        }
        if (ctColor.isSetSrgbClr()) {
            rgb = ctColor.getSrgbClr().getVal();
        } else {
            if (!ctColor.isSetSysClr()) {
                return null;
            }
            rgb = ctColor.getSysClr().getLastClr();
        }
        return new XSSFColor(rgb, this.colorMap);
    }

    @Override // org.apache.poi.xssf.model.Themes
    public void inheritFromThemeAsRequired(XSSFColor color) {
        if (color == null || !color.getCTColor().isSetTheme()) {
            return;
        }
        XSSFColor themeColor = getThemeColor(color.getTheme());
        color.getCTColor().setRgb(themeColor.getCTColor().getRgb());
    }

    public void writeTo(OutputStream out) throws IOException {
        this.theme.save(out, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            writeTo(out);
            if (out != null) {
                out.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (out != null) {
                    try {
                        out.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }
}
