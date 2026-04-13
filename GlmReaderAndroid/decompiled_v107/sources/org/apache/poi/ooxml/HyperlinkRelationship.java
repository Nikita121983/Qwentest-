package org.apache.poi.ooxml;

import java.net.URI;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;

/* loaded from: classes10.dex */
public class HyperlinkRelationship extends ReferenceRelationship {
    /* JADX INFO: Access modifiers changed from: protected */
    public HyperlinkRelationship(POIXMLDocumentPart container, URI hyperlinkUri, boolean isExternal, String id) {
        super(container, hyperlinkUri, isExternal, PackageRelationshipTypes.HYPERLINK_PART, id);
    }

    @Override // org.apache.poi.ooxml.ReferenceRelationship
    public String getRelationshipType() {
        return PackageRelationshipTypes.HYPERLINK_PART;
    }
}
