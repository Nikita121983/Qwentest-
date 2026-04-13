package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.usermodel.XSLFTheme;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet;
import org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument;

/* loaded from: classes10.dex */
public class XWPFTheme extends POIXMLDocumentPart {
    private CTOfficeStyleSheet _theme;

    public XWPFTheme(PackagePart part) {
        super(part);
    }

    public XWPFTheme() {
        this._theme = CTOfficeStyleSheet.Factory.newInstance();
    }

    public void importTheme(XSLFTheme theme) {
        this._theme = theme.getXmlObject();
    }

    public String getName() {
        return this._theme.getName();
    }

    public void setName(String name) {
        this._theme.setName(name);
    }

    @Internal
    public CTColor getCTColor(String name) {
        CTBaseStyles elems = this._theme.getThemeElements();
        CTColorScheme scheme = elems == null ? null : elems.getClrScheme();
        return getMapColor(name, scheme);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static CTColor getMapColor(String mapName, CTColorScheme scheme) {
        char c;
        if (mapName == null || scheme == null) {
            return null;
        }
        switch (mapName.hashCode()) {
            case -1177623385:
                if (mapName.equals("accent1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1177623384:
                if (mapName.equals("accent2")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1177623383:
                if (mapName.equals("accent3")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1177623382:
                if (mapName.equals("accent4")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1177623381:
                if (mapName.equals("accent5")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1177623380:
                if (mapName.equals("accent6")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 99466:
                if (mapName.equals("dk1")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 99467:
                if (mapName.equals("dk2")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 107433:
                if (mapName.equals("lt1")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 107434:
                if (mapName.equals("lt2")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 99368034:
                if (mapName.equals("hlink")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 268452191:
                if (mapName.equals("folHlink")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return scheme.getAccent1();
            case 1:
                return scheme.getAccent2();
            case 2:
                return scheme.getAccent3();
            case 3:
                return scheme.getAccent4();
            case 4:
                return scheme.getAccent5();
            case 5:
                return scheme.getAccent6();
            case 6:
                return scheme.getDk1();
            case 7:
                return scheme.getDk2();
            case '\b':
                return scheme.getFolHlink();
            case '\t':
                return scheme.getHlink();
            case '\n':
                return scheme.getLt1();
            case 11:
                return scheme.getLt2();
            default:
                return null;
        }
    }

    public String getMajorFont() {
        return this._theme.getThemeElements().getFontScheme().getMajorFont().getLatin().getTypeface();
    }

    public String getMinorFont() {
        return this._theme.getThemeElements().getFontScheme().getMinorFont().getLatin().getTypeface();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() throws IOException {
        try {
            InputStream is = getPackagePart().getInputStream();
            try {
                ThemeDocument themeDoc = ThemeDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                setTheme(themeDoc.getTheme());
                if (is != null) {
                    is.close();
                }
            } finally {
            }
        } catch (XmlException e) {
            throw new POIXMLException("Unable to read theme", e);
        }
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        if (this._theme == null) {
            throw new IOException("Unable to write out theme that was never read in!");
        }
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(XSSFRelation.NS_DRAWINGML, "theme"));
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            this._theme.save(out, xmlOptions);
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

    public void setTheme(CTOfficeStyleSheet theme) {
        this._theme = theme;
    }
}
