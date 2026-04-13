package org.apache.poi.extractor;

import org.apache.poi.POIDocument;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hpsf.extractor.HPSFPropertiesExtractor;
import org.apache.poi.poifs.filesystem.DirectoryEntry;

/* loaded from: classes10.dex */
public interface POIOLE2TextExtractor extends POITextExtractor {
    @Override // org.apache.poi.extractor.POITextExtractor
    POIDocument getDocument();

    default DocumentSummaryInformation getDocSummaryInformation() {
        return getDocument().getDocumentSummaryInformation();
    }

    default SummaryInformation getSummaryInformation() {
        return getDocument().getSummaryInformation();
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    default POITextExtractor getMetadataTextExtractor() {
        return new HPSFPropertiesExtractor(this);
    }

    default DirectoryEntry getRoot() {
        return getDocument().getDirectory();
    }
}
