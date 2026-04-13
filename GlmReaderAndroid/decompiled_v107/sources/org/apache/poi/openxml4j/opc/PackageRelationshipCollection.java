package org.apache.poi.openxml4j.opc;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Spliterator;
import java.util.TreeMap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/* loaded from: classes10.dex */
public final class PackageRelationshipCollection implements Iterable<PackageRelationship> {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) PackageRelationshipCollection.class);
    private OPCPackage container;
    private final HashMap<String, PackageRelationship> internalRelationshipsByTargetName;
    private int nextRelationshipId;
    private PackagePartName partName;
    private PackagePart relationshipPart;
    private final TreeMap<String, PackageRelationship> relationshipsByID;
    private PackagePart sourcePart;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PackageRelationshipCollection() {
        this.relationshipsByID = new TreeMap<>();
        this.internalRelationshipsByTargetName = new HashMap<>();
        this.nextRelationshipId = -1;
    }

    public PackageRelationshipCollection(PackageRelationshipCollection coll, String filter) {
        this();
        for (PackageRelationship rel : coll.relationshipsByID.values()) {
            if (filter == null || rel.getRelationshipType().equals(filter)) {
                addRelationship(rel);
            }
        }
    }

    public PackageRelationshipCollection(OPCPackage container) throws InvalidFormatException {
        this(container, (PackagePart) null);
    }

    public PackageRelationshipCollection(PackagePart part) throws InvalidFormatException {
        this(part._container, part);
    }

    public PackageRelationshipCollection(OPCPackage container, PackagePart part) throws InvalidFormatException {
        this.relationshipsByID = new TreeMap<>();
        this.internalRelationshipsByTargetName = new HashMap<>();
        this.nextRelationshipId = -1;
        if (container == null) {
            throw new IllegalArgumentException("container needs to be specified");
        }
        if (part != null && part.isRelationshipPart()) {
            throw new IllegalArgumentException("part");
        }
        this.container = container;
        this.sourcePart = part;
        this.partName = getRelationshipPartName(part);
        if (container.getPackageAccess() != PackageAccess.WRITE && container.containPart(this.partName)) {
            this.relationshipPart = container.getPart(this.partName);
            parseRelationshipsPart(this.relationshipPart);
        }
    }

    private static PackagePartName getRelationshipPartName(PackagePart part) throws InvalidOperationException {
        PackagePartName partName;
        if (part == null) {
            partName = PackagingURIHelper.PACKAGE_ROOT_PART_NAME;
        } else {
            partName = part.getPartName();
        }
        return PackagingURIHelper.getRelationshipPartName(partName);
    }

    public void addRelationship(PackageRelationship relPart) {
        if (relPart == null || relPart.getId() == null || relPart.getId().isEmpty()) {
            throw new IllegalArgumentException("invalid relationship part/id: " + (relPart == null ? "<null>" : relPart.getId()) + " for relationship: " + relPart);
        }
        this.relationshipsByID.put(relPart.getId(), relPart);
    }

    public PackageRelationship addRelationship(URI targetUri, TargetMode targetMode, String relationshipType, String id) {
        String id2;
        String id3;
        if (id == null || id.isEmpty()) {
            if (this.nextRelationshipId == -1) {
                this.nextRelationshipId = size() + 1;
            }
            do {
                StringBuilder append = new StringBuilder().append("rId");
                int i = this.nextRelationshipId;
                this.nextRelationshipId = i + 1;
                id2 = append.append(i).toString();
            } while (this.relationshipsByID.get(id2) != null);
            id3 = id2;
        } else {
            id3 = id;
        }
        PackageRelationship rel = new PackageRelationship(this.container, this.sourcePart, targetUri, targetMode, relationshipType, id3);
        addRelationship(rel);
        if (targetMode == TargetMode.INTERNAL) {
            this.internalRelationshipsByTargetName.put(targetUri.toASCIIString(), rel);
        }
        return rel;
    }

    public void removeRelationship(String id) {
        PackageRelationship rel = this.relationshipsByID.get(id);
        if (rel != null) {
            this.relationshipsByID.remove(rel.getId());
            this.internalRelationshipsByTargetName.values().remove(rel);
        }
    }

    public PackageRelationship getRelationship(int index) {
        if (index < 0 || index > this.relationshipsByID.values().size()) {
            throw new IllegalArgumentException("index");
        }
        int i = 0;
        for (PackageRelationship rel : this.relationshipsByID.values()) {
            int i2 = i + 1;
            if (index == i) {
                return rel;
            }
            i = i2;
        }
        return null;
    }

    public PackageRelationship getRelationshipByID(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot read relationship, provided ID is empty: " + id + ", having relationships: " + this.relationshipsByID.keySet());
        }
        return this.relationshipsByID.get(id);
    }

    public int size() {
        return this.relationshipsByID.size();
    }

    public boolean isEmpty() {
        return this.relationshipsByID.isEmpty();
    }

    public void parseRelationshipsPart(PackagePart relPart) throws InvalidFormatException {
        InputStream partStream;
        boolean fCorePropertiesRelationship;
        TargetMode targetMode;
        try {
            LOG.atDebug().log("Parsing relationship: {}", relPart.getPartName());
            partStream = relPart.getInputStream();
        } catch (Exception e) {
            e = e;
        }
        try {
            try {
                Document xmlRelationshipsDoc = DocumentHelper.readDocument(partStream);
                if (partStream != null) {
                    partStream.close();
                }
                Element root = xmlRelationshipsDoc.getDocumentElement();
                boolean fCorePropertiesRelationship2 = false;
                NodeList nodeList = root.getElementsByTagNameNS(PackageNamespaces.RELATIONSHIPS, PackageRelationship.RELATIONSHIP_TAG_NAME);
                int nodeCount = nodeList.getLength();
                int i = 0;
                while (i < nodeCount) {
                    Element element = (Element) nodeList.item(i);
                    String id = element.getAttribute(PackageRelationship.ID_ATTRIBUTE_NAME);
                    String type = element.getAttribute(PackageRelationship.TYPE_ATTRIBUTE_NAME);
                    if (!type.equals(PackageRelationshipTypes.CORE_PROPERTIES)) {
                        fCorePropertiesRelationship = fCorePropertiesRelationship2;
                    } else if (!fCorePropertiesRelationship2) {
                        fCorePropertiesRelationship = true;
                    } else {
                        throw new InvalidFormatException("OPC Compliance error [M4.1]: there is more than one core properties relationship in the package !");
                    }
                    Attr targetModeAttr = element.getAttributeNode(PackageRelationship.TARGET_MODE_ATTRIBUTE_NAME);
                    TargetMode targetMode2 = TargetMode.INTERNAL;
                    if (targetModeAttr == null) {
                        targetMode = targetMode2;
                    } else {
                        targetMode = targetModeAttr.getValue().toLowerCase(Locale.ROOT).equals("internal") ? TargetMode.INTERNAL : TargetMode.EXTERNAL;
                    }
                    URI target = PackagingURIHelper.toURI("http://invalid.uri");
                    String value = element.getAttribute(PackageRelationship.TARGET_ATTRIBUTE_NAME);
                    try {
                        target = PackagingURIHelper.toURI(value);
                    } catch (URISyntaxException e2) {
                        LOG.atError().withThrowable(e2).log("Cannot convert {} in a valid relationship URI-> dummy-URI used", value);
                    }
                    addRelationship(target, targetMode, type, id);
                    i++;
                    fCorePropertiesRelationship2 = fCorePropertiesRelationship;
                }
            } finally {
            }
        } catch (Exception e3) {
            e = e3;
            throw new InvalidFormatException("Failed to parse relationships", e);
        }
    }

    public PackageRelationshipCollection getRelationships(String typeFilter) {
        return new PackageRelationshipCollection(this, typeFilter);
    }

    @Override // java.lang.Iterable
    public Iterator<PackageRelationship> iterator() {
        return this.relationshipsByID.values().iterator();
    }

    @Override // java.lang.Iterable
    public Spliterator<PackageRelationship> spliterator() {
        return this.relationshipsByID.values().spliterator();
    }

    public Iterator<PackageRelationship> iterator(String typeFilter) {
        ArrayList<PackageRelationship> retArr = new ArrayList<>();
        for (PackageRelationship rel : this.relationshipsByID.values()) {
            if (rel.getRelationshipType().equals(typeFilter)) {
                retArr.add(rel);
            }
        }
        return retArr.iterator();
    }

    public void clear() {
        this.relationshipsByID.clear();
        this.internalRelationshipsByTargetName.clear();
    }

    public PackageRelationship findExistingInternalRelation(PackagePart packagePart) {
        return this.internalRelationshipsByTargetName.get(packagePart.getPartName().getName());
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        String str4 = this.relationshipsByID.size() + " relationship(s) = [";
        if (this.relationshipPart != null && this.relationshipPart._partName != null) {
            str = str4 + this.relationshipPart._partName;
        } else {
            str = str4 + "relationshipPart=null";
        }
        if (this.sourcePart != null && this.sourcePart._partName != null) {
            str2 = str + CollectionUtils.COMMA + this.sourcePart._partName;
        } else {
            str2 = str + ",sourcePart=null";
        }
        if (this.partName != null) {
            str3 = str2 + CollectionUtils.COMMA + this.partName;
        } else {
            str3 = str2 + ",uri=null)";
        }
        return str3 + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
