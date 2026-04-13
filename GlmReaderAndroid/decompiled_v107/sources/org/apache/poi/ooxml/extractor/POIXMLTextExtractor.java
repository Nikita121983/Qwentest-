package org.apache.poi.ooxml.extractor;

import java.io.IOException;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.util.ZipSecureFile;

/* loaded from: classes10.dex */
public interface POIXMLTextExtractor extends POITextExtractor {
    @Override // org.apache.poi.extractor.POITextExtractor
    POIXMLDocument getDocument();

    default POIXMLProperties.CoreProperties getCoreProperties() {
        return getDocument().getProperties().getCoreProperties();
    }

    default POIXMLProperties.ExtendedProperties getExtendedProperties() {
        return getDocument().getProperties().getExtendedProperties();
    }

    default POIXMLProperties.CustomProperties getCustomProperties() {
        return getDocument().getProperties().getCustomProperties();
    }

    default OPCPackage getPackage() {
        POIXMLDocument doc = getDocument();
        if (doc != null) {
            return doc.getPackage();
        }
        return null;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    default POIXMLPropertiesTextExtractor getMetadataTextExtractor() {
        return new POIXMLPropertiesTextExtractor(getDocument());
    }

    @Override // org.apache.poi.extractor.POITextExtractor, java.io.Closeable, java.lang.AutoCloseable
    default void close() throws IOException {
        OPCPackage pkg;
        if (isCloseFilesystem() && (pkg = getPackage()) != null) {
            pkg.revert();
        }
    }

    default void checkMaxTextSize(CharSequence text, String string) {
        if (string == null) {
            return;
        }
        int size = text.length() + string.length();
        if (size > ZipSecureFile.getMaxTextSize()) {
            throw new IllegalStateException("The text would exceed the max allowed overall size of extracted text. By default this is prevented as some documents may exhaust available memory and it may indicate that the file is used to inflate memory usage and thus could pose a security risk. You can adjust this limit via ZipSecureFile.setMaxTextSize() if you need to work with files which have a lot of text. Size: " + size + ", limit: MAX_TEXT_SIZE: " + ZipSecureFile.getMaxTextSize());
        }
    }
}
