package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.poi.common.usermodel.fonts.FontCharset;
import org.apache.poi.common.usermodel.fonts.FontFacet;
import org.apache.poi.common.usermodel.fonts.FontFamily;
import org.apache.poi.common.usermodel.fonts.FontHeader;
import org.apache.poi.common.usermodel.fonts.FontInfo;
import org.apache.poi.common.usermodel.fonts.FontPitch;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.IOUtils;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation;

/* loaded from: classes10.dex */
public class XSLFFontInfo implements FontInfo {
    final CTEmbeddedFontListEntry fontListEntry;
    final XMLSlideShow ppt;
    final String typeface;

    public XSLFFontInfo(XMLSlideShow ppt, String typeface) {
        this.ppt = ppt;
        this.typeface = typeface;
        CTPresentation pres = ppt.getCTPresentation();
        CTEmbeddedFontList fontList = pres.isSetEmbeddedFontLst() ? pres.getEmbeddedFontLst() : pres.addNewEmbeddedFontLst();
        for (CTEmbeddedFontListEntry fe : fontList.getEmbeddedFontArray()) {
            if (typeface.equalsIgnoreCase(fe.getFont().getTypeface())) {
                this.fontListEntry = fe;
                return;
            }
        }
        this.fontListEntry = fontList.addNewEmbeddedFont();
        this.fontListEntry.addNewFont().setTypeface(typeface);
    }

    public XSLFFontInfo(XMLSlideShow ppt, CTEmbeddedFontListEntry fontListEntry) {
        this.ppt = ppt;
        this.typeface = fontListEntry.getFont().getTypeface();
        this.fontListEntry = fontListEntry;
    }

    @Override // org.apache.poi.common.usermodel.fonts.FontInfo
    public String getTypeface() {
        return getFont().getTypeface();
    }

    @Override // org.apache.poi.common.usermodel.fonts.FontInfo
    public void setTypeface(String typeface) {
        getFont().setTypeface(typeface);
    }

    @Override // org.apache.poi.common.usermodel.fonts.FontInfo
    public FontCharset getCharset() {
        return FontCharset.valueOf(getFont().getCharset());
    }

    @Override // org.apache.poi.common.usermodel.fonts.FontInfo
    public void setCharset(FontCharset charset) {
        getFont().setCharset((byte) charset.getNativeId());
    }

    @Override // org.apache.poi.common.usermodel.fonts.FontInfo
    public FontFamily getFamily() {
        return FontFamily.valueOfPitchFamily(getFont().getPitchFamily());
    }

    @Override // org.apache.poi.common.usermodel.fonts.FontInfo
    public void setFamily(FontFamily family) {
        byte pitchAndFamily = getFont().getPitchFamily();
        FontPitch pitch = FontPitch.valueOfPitchFamily(pitchAndFamily);
        getFont().setPitchFamily(FontPitch.getNativeId(pitch, family));
    }

    @Override // org.apache.poi.common.usermodel.fonts.FontInfo
    public FontPitch getPitch() {
        return FontPitch.valueOfPitchFamily(getFont().getPitchFamily());
    }

    @Override // org.apache.poi.common.usermodel.fonts.FontInfo
    public void setPitch(FontPitch pitch) {
        byte pitchAndFamily = getFont().getPitchFamily();
        FontFamily family = FontFamily.valueOfPitchFamily(pitchAndFamily);
        getFont().setPitchFamily(FontPitch.getNativeId(pitch, family));
    }

    @Override // org.apache.poi.common.usermodel.fonts.FontInfo
    public byte[] getPanose() {
        return getFont().getPanose();
    }

    @Override // org.apache.poi.common.usermodel.fonts.FontInfo
    public List<FontFacet> getFacets() {
        List<FontFacet> facetList = new ArrayList<>();
        if (this.fontListEntry.isSetRegular()) {
            facetList.add(new XSLFFontFacet(this.fontListEntry.getRegular()));
        }
        if (this.fontListEntry.isSetItalic()) {
            facetList.add(new XSLFFontFacet(this.fontListEntry.getItalic()));
        }
        if (this.fontListEntry.isSetBold()) {
            facetList.add(new XSLFFontFacet(this.fontListEntry.getBold()));
        }
        if (this.fontListEntry.isSetBoldItalic()) {
            facetList.add(new XSLFFontFacet(this.fontListEntry.getBoldItalic()));
        }
        return facetList;
    }

    public FontFacet addFacet(InputStream fontData) throws IOException {
        CTEmbeddedFontDataId dataId;
        FontHeader header = new FontHeader();
        InputStream is = header.bufferInit(fontData);
        CTPresentation pres = this.ppt.getCTPresentation();
        pres.setEmbedTrueTypeFonts(true);
        pres.setSaveSubsetFonts(true);
        int style = (header.getWeight() <= 400 ? 0 : 1) | (header.isItalic() ? 2 : 0);
        switch (style) {
            case 0:
                if (!this.fontListEntry.isSetRegular()) {
                    dataId = this.fontListEntry.addNewRegular();
                    break;
                } else {
                    dataId = this.fontListEntry.getRegular();
                    break;
                }
            case 1:
                if (!this.fontListEntry.isSetBold()) {
                    dataId = this.fontListEntry.addNewBold();
                    break;
                } else {
                    dataId = this.fontListEntry.getBold();
                    break;
                }
            case 2:
                if (!this.fontListEntry.isSetItalic()) {
                    dataId = this.fontListEntry.addNewItalic();
                    break;
                } else {
                    dataId = this.fontListEntry.getItalic();
                    break;
                }
            default:
                if (this.fontListEntry.isSetBoldItalic()) {
                    dataId = this.fontListEntry.getBoldItalic();
                    break;
                } else {
                    dataId = this.fontListEntry.addNewBoldItalic();
                    break;
                }
        }
        XSLFFontFacet facet = new XSLFFontFacet(dataId);
        facet.setFontData(is);
        return facet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public final class XSLFFontFacet implements FontFacet {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final CTEmbeddedFontDataId fontEntry;
        private final FontHeader header;

        private XSLFFontFacet(CTEmbeddedFontDataId fontEntry) {
            this.header = new FontHeader();
            this.fontEntry = fontEntry;
        }

        @Override // org.apache.poi.common.usermodel.fonts.FontFacet
        public int getWeight() {
            init();
            return this.header.getWeight();
        }

        @Override // org.apache.poi.common.usermodel.fonts.FontFacet
        public boolean isItalic() {
            init();
            return this.header.isItalic();
        }

        @Override // org.apache.poi.common.usermodel.fonts.FontFacet
        public XSLFFontData getFontData() {
            return (XSLFFontData) XSLFFontInfo.this.ppt.getRelationPartById(this.fontEntry.getId()).getDocumentPart();
        }

        void setFontData(InputStream is) throws IOException {
            XSLFFontData fntData;
            XSLFRelation fntRel = XSLFRelation.FONT;
            String relId = this.fontEntry.getId();
            if (relId == null || relId.isEmpty()) {
                try {
                    int fntDataIdx = XSLFFontInfo.this.ppt.getPackage().getUnusedPartIndex(fntRel.getDefaultFileName());
                    POIXMLDocumentPart.RelationPart rp = XSLFFontInfo.this.ppt.createRelationship(fntRel, XSLFFactory.getInstance(), fntDataIdx, false);
                    XSLFFontData fntData2 = (XSLFFontData) rp.getDocumentPart();
                    this.fontEntry.setId(rp.getRelationship().getId());
                    fntData = fntData2;
                } catch (InvalidFormatException e) {
                    throw new IOException(e);
                }
            } else {
                fntData = (XSLFFontData) XSLFFontInfo.this.ppt.getRelationById(relId);
            }
            if (fntData == null) {
                throw new AssertionError();
            }
            OutputStream os = fntData.getOutputStream();
            try {
                IOUtils.copy(is, os);
                if (os != null) {
                    os.close();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (os != null) {
                        try {
                            os.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }

        private void init() {
            if (this.header.getFamilyName() == null) {
                try {
                    InputStream is = getFontData().getInputStream();
                    try {
                        byte[] buf = IOUtils.toByteArray(is, 1000);
                        this.header.init(buf, 0, buf.length);
                        if (is != null) {
                            is.close();
                        }
                    } finally {
                    }
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

    private CTTextFont getFont() {
        return this.fontListEntry.getFont();
    }

    public static XSLFFontInfo addFontToSlideShow(XMLSlideShow ppt, InputStream fontStream) throws IOException {
        FontHeader header = new FontHeader();
        InputStream is = header.bufferInit(fontStream);
        XSLFFontInfo fontInfo = new XSLFFontInfo(ppt, header.getFamilyName());
        fontInfo.addFacet(is);
        return fontInfo;
    }

    public static List<XSLFFontInfo> getFonts(final XMLSlideShow ppt) {
        CTPresentation pres = ppt.getCTPresentation();
        if (pres.isSetEmbeddedFontLst()) {
            return (List) Stream.of((Object[]) pres.getEmbeddedFontLst().getEmbeddedFontArray()).map(new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFFontInfo$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return XSLFFontInfo.lambda$getFonts$0(XMLSlideShow.this, (CTEmbeddedFontListEntry) obj);
                }
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XSLFFontInfo lambda$getFonts$0(XMLSlideShow ppt, CTEmbeddedFontListEntry fe) {
        return new XSLFFontInfo(ppt, fe);
    }
}
