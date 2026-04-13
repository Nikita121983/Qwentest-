package org.apache.poi.xslf.extractor;

import org.apache.poi.ooxml.extractor.POIXMLPropertiesTextExtractor;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.sl.extractor.SlideShowExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;

/* loaded from: classes10.dex */
public class XSLFExtractor extends SlideShowExtractor<XSLFShape, XSLFTextParagraph> implements POIXMLTextExtractor {
    public XSLFExtractor(XMLSlideShow slideshow) {
        super(slideshow);
    }

    @Override // org.apache.poi.sl.extractor.SlideShowExtractor, org.apache.poi.extractor.POITextExtractor
    public XMLSlideShow getDocument() {
        return (XMLSlideShow) this.slideshow;
    }

    @Override // org.apache.poi.sl.extractor.SlideShowExtractor, org.apache.poi.extractor.POITextExtractor
    public POIXMLPropertiesTextExtractor getMetadataTextExtractor() {
        return super.getMetadataTextExtractor();
    }
}
