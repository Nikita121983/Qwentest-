package org.apache.poi.openxml4j.opc;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

/* loaded from: classes10.dex */
public final class PackageRelationship {
    public static final String ID_ATTRIBUTE_NAME = "Id";
    public static final String RELATIONSHIPS_TAG_NAME = "Relationships";
    public static final String RELATIONSHIP_TAG_NAME = "Relationship";
    public static final String TARGET_ATTRIBUTE_NAME = "Target";
    public static final String TARGET_MODE_ATTRIBUTE_NAME = "TargetMode";
    public static final String TYPE_ATTRIBUTE_NAME = "Type";
    private static URI containerRelationshipPart;
    private final OPCPackage container;
    private final String id;
    private final String relationshipType;
    private final PackagePart source;
    private final TargetMode targetMode;
    private final URI targetUri;

    static {
        try {
            containerRelationshipPart = new URI("/_rels/.rels");
        } catch (URISyntaxException e) {
        }
    }

    public PackageRelationship(OPCPackage pkg, PackagePart sourcePart, URI targetUri, TargetMode targetMode, String relationshipType, String id) {
        if (pkg == null) {
            throw new NullPointerException("pkg cannot be null");
        }
        if (targetUri == null) {
            throw new NullPointerException("targetUri cannot be null");
        }
        if (relationshipType == null) {
            throw new NullPointerException("relationshipType cannot be null");
        }
        if (id == null) {
            throw new NullPointerException("id cannot be null");
        }
        this.container = pkg;
        this.source = sourcePart;
        this.targetUri = targetUri;
        this.targetMode = targetMode;
        this.relationshipType = relationshipType;
        this.id = id;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PackageRelationship)) {
            return false;
        }
        PackageRelationship rel = (PackageRelationship) obj;
        if (this.id.equals(rel.id) && this.relationshipType.equals(rel.relationshipType)) {
            return (rel.source == null || rel.source.equals(this.source)) && this.targetMode == rel.targetMode && this.targetUri.equals(rel.targetUri);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.id, this.relationshipType, this.source, this.targetMode, this.targetUri);
    }

    public static URI getContainerPartRelationship() {
        return containerRelationshipPart;
    }

    public OPCPackage getPackage() {
        return this.container;
    }

    public String getId() {
        return this.id;
    }

    public String getRelationshipType() {
        return this.relationshipType;
    }

    public PackagePart getSource() {
        return this.source;
    }

    public URI getSourceURI() {
        if (this.source == null) {
            return PackagingURIHelper.PACKAGE_ROOT_URI;
        }
        return this.source._partName.getURI();
    }

    public TargetMode getTargetMode() {
        return this.targetMode;
    }

    public URI getTargetURI() {
        if (this.targetMode == TargetMode.EXTERNAL) {
            return this.targetUri;
        }
        if (PackageRelationshipTypes.HYPERLINK_PART.equals(this.relationshipType)) {
            return this.targetUri;
        }
        if (!this.targetUri.toASCIIString().startsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) {
            return PackagingURIHelper.resolvePartUri(getSourceURI(), this.targetUri);
        }
        return this.targetUri;
    }

    public String toString() {
        return "id=" + this.id + " - container=" + this.container + " - relationshipType=" + this.relationshipType + (this.source == null ? " - source=null" : " - source=" + getSourceURI().toASCIIString()) + " - target=" + getTargetURI().toASCIIString() + (this.targetMode == null ? ",targetMode=null" : ",targetMode=" + this.targetMode);
    }
}
