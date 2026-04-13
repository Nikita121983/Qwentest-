package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrDefault;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPrDefault;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument;

/* loaded from: classes10.dex */
public class XWPFStyles extends POIXMLDocumentPart {
    private CTStyles ctStyles;
    private XWPFDefaultParagraphStyle defaultParaStyle;
    private XWPFDefaultRunStyle defaultRunStyle;
    private XWPFLatentStyles latentStyles;
    private final List<XWPFStyle> listStyle;

    public XWPFStyles(PackagePart part) {
        super(part);
        this.listStyle = new ArrayList();
    }

    public XWPFStyles() {
        this.listStyle = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() throws IOException {
        try {
            InputStream is = getPackagePart().getInputStream();
            try {
                StylesDocument stylesDoc = StylesDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                setStyles(stylesDoc.getStyles());
                this.latentStyles = new XWPFLatentStyles(this.ctStyles.getLatentStyles(), this);
                if (is != null) {
                    is.close();
                }
            } finally {
            }
        } catch (XmlException e) {
            throw new POIXMLException("Unable to read styles", e);
        }
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        if (this.ctStyles == null) {
            throw new IOException("Unable to write out styles that were never read in!");
        }
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTStyles.type.getName().getNamespaceURI(), "styles"));
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            this.ctStyles.save(out, xmlOptions);
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

    protected void ensureDocDefaults() {
        if (!this.ctStyles.isSetDocDefaults()) {
            this.ctStyles.addNewDocDefaults();
        }
        CTDocDefaults docDefaults = this.ctStyles.getDocDefaults();
        if (!docDefaults.isSetPPrDefault()) {
            docDefaults.addNewPPrDefault();
        }
        if (!docDefaults.isSetRPrDefault()) {
            docDefaults.addNewRPrDefault();
        }
        CTPPrDefault pprd = docDefaults.getPPrDefault();
        CTRPrDefault rprd = docDefaults.getRPrDefault();
        if (!pprd.isSetPPr()) {
            pprd.addNewPPr();
        }
        if (!rprd.isSetRPr()) {
            rprd.addNewRPr();
        }
        this.defaultRunStyle = new XWPFDefaultRunStyle(rprd.getRPr());
        this.defaultParaStyle = new XWPFDefaultParagraphStyle(pprd.getPPr());
    }

    public void setStyles(CTStyles styles) {
        this.ctStyles = styles;
        for (CTStyle style : this.ctStyles.getStyleArray()) {
            this.listStyle.add(new XWPFStyle(style, this));
        }
        if (this.ctStyles.isSetDocDefaults()) {
            CTDocDefaults docDefaults = this.ctStyles.getDocDefaults();
            if (docDefaults.isSetRPrDefault() && docDefaults.getRPrDefault().isSetRPr()) {
                this.defaultRunStyle = new XWPFDefaultRunStyle(docDefaults.getRPrDefault().getRPr());
            }
            if (docDefaults.isSetPPrDefault() && docDefaults.getPPrDefault().isSetPPr()) {
                this.defaultParaStyle = new XWPFDefaultParagraphStyle(docDefaults.getPPrDefault().getPPr());
            }
        }
    }

    public CTStyles getCtStyles() {
        return this.ctStyles;
    }

    public List<XWPFStyle> getStyles() {
        return Collections.unmodifiableList(this.listStyle);
    }

    public boolean removeStyle(int pos) {
        if (pos >= 0 && pos < getNumberOfStyles()) {
            this.listStyle.remove(pos);
            this.ctStyles.removeStyle(pos);
            return true;
        }
        return false;
    }

    public boolean styleExist(String styleID) {
        return getStyle(styleID) != null;
    }

    public void addStyle(XWPFStyle style) {
        this.listStyle.add(style);
        this.ctStyles.addNewStyle();
        int pos = this.ctStyles.sizeOfStyleArray() - 1;
        this.ctStyles.setStyleArray(pos, style.getCTStyle());
    }

    public XWPFStyle getStyle(String styleID) {
        for (XWPFStyle style : this.listStyle) {
            if (style.getStyleId() != null && style.getStyleId().equals(styleID)) {
                return style;
            }
        }
        return null;
    }

    public int getNumberOfStyles() {
        return this.listStyle.size();
    }

    public List<XWPFStyle> getUsedStyleList(XWPFStyle style) {
        List<XWPFStyle> usedStyleList = new ArrayList<>();
        usedStyleList.add(style);
        return getUsedStyleList(style, usedStyleList);
    }

    private List<XWPFStyle> getUsedStyleList(XWPFStyle style, List<XWPFStyle> usedStyleList) {
        String basisStyleID = style.getBasisStyleID();
        XWPFStyle basisStyle = getStyle(basisStyleID);
        if (basisStyle != null && !usedStyleList.contains(basisStyle)) {
            usedStyleList.add(basisStyle);
            getUsedStyleList(basisStyle, usedStyleList);
        }
        String linkStyleID = style.getLinkStyleID();
        XWPFStyle linkStyle = getStyle(linkStyleID);
        if (linkStyle != null && !usedStyleList.contains(linkStyle)) {
            usedStyleList.add(linkStyle);
            getUsedStyleList(linkStyle, usedStyleList);
        }
        String nextStyleID = style.getNextStyleID();
        XWPFStyle nextStyle = getStyle(nextStyleID);
        if (nextStyle != null && !usedStyleList.contains(nextStyle)) {
            usedStyleList.add(nextStyle);
            getUsedStyleList(nextStyle, usedStyleList);
        }
        return usedStyleList;
    }

    protected CTLanguage getCTLanguage() {
        ensureDocDefaults();
        if (this.defaultRunStyle.getRPr().sizeOfLangArray() > 0) {
            return this.defaultRunStyle.getRPr().getLangArray(0);
        }
        return this.defaultRunStyle.getRPr().addNewLang();
    }

    public void setSpellingLanguage(String strSpellingLanguage) {
        CTLanguage lang = getCTLanguage();
        lang.setVal(strSpellingLanguage);
        lang.setBidi(strSpellingLanguage);
    }

    public void setEastAsia(String strEastAsia) {
        CTLanguage lang = getCTLanguage();
        lang.setEastAsia(strEastAsia);
    }

    public void setDefaultFonts(CTFonts fonts) {
        ensureDocDefaults();
        CTRPr runProps = this.defaultRunStyle.getRPr();
        if (runProps.sizeOfRFontsArray() == 0) {
            runProps.addNewRFonts();
        }
        runProps.setRFontsArray(0, fonts);
    }

    public XWPFStyle getStyleWithSameName(XWPFStyle style) {
        for (XWPFStyle ownStyle : this.listStyle) {
            if (ownStyle.hasSameName(style)) {
                return ownStyle;
            }
        }
        return null;
    }

    public XWPFDefaultRunStyle getDefaultRunStyle() {
        ensureDocDefaults();
        return this.defaultRunStyle;
    }

    public XWPFDefaultParagraphStyle getDefaultParagraphStyle() {
        ensureDocDefaults();
        return this.defaultParaStyle;
    }

    public XWPFLatentStyles getLatentStyles() {
        return this.latentStyles;
    }

    public XWPFStyle getStyleWithName(String styleName) {
        for (XWPFStyle cand : this.listStyle) {
            if (styleName.equals(cand.getName())) {
                return cand;
            }
        }
        return null;
    }
}
