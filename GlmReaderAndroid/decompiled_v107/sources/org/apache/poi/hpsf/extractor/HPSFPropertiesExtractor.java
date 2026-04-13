package org.apache.poi.hpsf.extractor;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIDocument;
import org.apache.poi.extractor.POIOLE2TextExtractor;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.hpsf.CustomProperties;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.HPSFPropertiesOnlyDocument;
import org.apache.poi.hpsf.Property;
import org.apache.poi.hpsf.PropertySet;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hpsf.wellknown.PropertyIDMap;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/* loaded from: classes10.dex */
public class HPSFPropertiesExtractor implements POIOLE2TextExtractor {
    private boolean doCloseFilesystem = true;
    private final POIDocument document;

    public HPSFPropertiesExtractor(POIOLE2TextExtractor mainExtractor) {
        this.document = mainExtractor.getDocument();
    }

    public HPSFPropertiesExtractor(POIDocument document) {
        this.document = document;
    }

    public HPSFPropertiesExtractor(POIFSFileSystem fs) {
        this.document = new HPSFPropertiesOnlyDocument(fs);
    }

    public String getDocumentSummaryInformationText() {
        if (this.document == null) {
            return "";
        }
        DocumentSummaryInformation dsi = this.document.getDocumentSummaryInformation();
        StringBuilder text = new StringBuilder();
        text.append(getPropertiesText(dsi));
        CustomProperties cps = dsi == null ? null : dsi.getCustomProperties();
        if (cps != null) {
            for (String key : cps.nameSet()) {
                String val = getPropertyValueText(cps.get(key));
                text.append(key).append(" = ").append(val).append(StringUtils.LF);
            }
        }
        return text.toString();
    }

    public String getSummaryInformationText() {
        if (this.document == null) {
            return "";
        }
        SummaryInformation si = this.document.getSummaryInformation();
        return getPropertiesText(si);
    }

    private static String getPropertiesText(PropertySet ps) {
        if (ps == null) {
            return "";
        }
        StringBuilder text = new StringBuilder();
        PropertyIDMap idMap = ps.getPropertySetIDMap();
        Property[] props = ps.getProperties();
        for (Property prop : props) {
            String type = Long.toString(prop.getID());
            Object typeObj = idMap == null ? null : idMap.get((Object) Long.valueOf(prop.getID()));
            if (typeObj != null) {
                type = typeObj.toString();
            }
            String val = getPropertyValueText(prop.getValue());
            text.append(type).append(" = ").append(val).append(StringUtils.LF);
        }
        return text.toString();
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public String getText() {
        return getSummaryInformationText() + getDocumentSummaryInformationText();
    }

    @Override // org.apache.poi.extractor.POIOLE2TextExtractor, org.apache.poi.extractor.POITextExtractor
    public POITextExtractor getMetadataTextExtractor() {
        throw new IllegalStateException("You already have the Metadata Text Extractor, not recursing!");
    }

    private static String getPropertyValueText(Object val) {
        return val == null ? "(not set)" : PropertySet.getPropertyStringValue(val);
    }

    public boolean equals(Object o) {
        return super.equals(o);
    }

    public int hashCode() {
        return super.hashCode();
    }

    @Override // org.apache.poi.extractor.POIOLE2TextExtractor, org.apache.poi.extractor.POITextExtractor
    public POIDocument getDocument() {
        return this.document;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public void setCloseFilesystem(boolean doCloseFilesystem) {
        this.doCloseFilesystem = doCloseFilesystem;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public boolean isCloseFilesystem() {
        return this.doCloseFilesystem;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public POIDocument getFilesystem() {
        return this.document;
    }
}
