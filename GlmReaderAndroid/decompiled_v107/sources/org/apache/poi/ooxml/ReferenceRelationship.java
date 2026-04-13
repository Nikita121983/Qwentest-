package org.apache.poi.ooxml;

import java.net.URI;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.TargetMode;

/* loaded from: classes10.dex */
public abstract class ReferenceRelationship {
    private POIXMLDocumentPart container;
    private final boolean external;
    private final String id;
    private final String relationshipType;
    private final URI uri;

    protected ReferenceRelationship(POIXMLDocumentPart container, PackageRelationship packageRelationship) {
        if (packageRelationship == null) {
            throw new IllegalArgumentException("packageRelationship");
        }
        this.container = container;
        this.relationshipType = packageRelationship.getRelationshipType();
        this.uri = packageRelationship.getTargetURI();
        this.external = packageRelationship.getTargetMode() == TargetMode.EXTERNAL;
        this.id = packageRelationship.getId();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ReferenceRelationship(POIXMLDocumentPart container, URI targetUri, boolean isExternal, String relationshipType, String id) {
        if (targetUri == null) {
            throw new NullPointerException("targetUri cannot be null");
        }
        this.container = container;
        this.relationshipType = relationshipType;
        this.uri = targetUri;
        this.id = id;
        this.external = isExternal;
    }

    public POIXMLDocumentPart getContainer() {
        return this.container;
    }

    public String getRelationshipType() {
        return this.relationshipType;
    }

    public boolean isExternal() {
        return this.external;
    }

    public String getId() {
        return this.id;
    }

    public URI getUri() {
        return this.uri;
    }
}
