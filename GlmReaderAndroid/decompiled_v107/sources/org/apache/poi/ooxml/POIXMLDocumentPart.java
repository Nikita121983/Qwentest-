package org.apache.poi.ooxml;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.exceptions.PartAlreadyExistsException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFRelation;

/* loaded from: classes10.dex */
public class POIXMLDocumentPart {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) POIXMLDocumentPart.class);
    private String coreDocumentRel;
    private boolean isCommitted;
    private PackagePart packagePart;
    private POIXMLDocumentPart parent;
    private final Map<String, ReferenceRelationship> referenceRelationships;
    private int relationCounter;
    private final Map<String, RelationPart> relations;

    public boolean isCommitted() {
        return this.isCommitted;
    }

    public void setCommitted(boolean isCommitted) {
        this.isCommitted = isCommitted;
    }

    /* loaded from: classes10.dex */
    public static class RelationPart {
        private final POIXMLDocumentPart documentPart;
        private final PackageRelationship relationship;

        RelationPart(PackageRelationship relationship, POIXMLDocumentPart documentPart) {
            this.relationship = relationship;
            this.documentPart = documentPart;
        }

        public PackageRelationship getRelationship() {
            return this.relationship;
        }

        public <T extends POIXMLDocumentPart> T getDocumentPart() {
            return (T) this.documentPart;
        }
    }

    int incrementRelationCounter() {
        this.relationCounter++;
        return this.relationCounter;
    }

    int decrementRelationCounter() {
        this.relationCounter--;
        return this.relationCounter;
    }

    int getRelationCounter() {
        return this.relationCounter;
    }

    public POIXMLDocumentPart(OPCPackage pkg) {
        this(pkg, PackageRelationshipTypes.CORE_DOCUMENT);
    }

    public POIXMLDocumentPart(OPCPackage pkg, String coreDocumentRel) {
        this(getPartFromOPCPackage(pkg, coreDocumentRel));
        this.coreDocumentRel = coreDocumentRel;
    }

    public POIXMLDocumentPart() {
        this.coreDocumentRel = PackageRelationshipTypes.CORE_DOCUMENT;
        this.relations = new LinkedHashMap();
        this.referenceRelationships = new LinkedHashMap();
        this.isCommitted = false;
    }

    public POIXMLDocumentPart(PackagePart part) {
        this((POIXMLDocumentPart) null, part);
    }

    public POIXMLDocumentPart(POIXMLDocumentPart parent, PackagePart part) {
        this.coreDocumentRel = PackageRelationshipTypes.CORE_DOCUMENT;
        this.relations = new LinkedHashMap();
        this.referenceRelationships = new LinkedHashMap();
        this.isCommitted = false;
        this.packagePart = part;
        this.parent = parent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void rebase(OPCPackage pkg) throws InvalidFormatException {
        PackageRelationshipCollection cores = this.packagePart.getRelationshipsByType(this.coreDocumentRel);
        if (cores.size() != 1) {
            throw new IllegalStateException("Tried to rebase using " + this.coreDocumentRel + " but found " + cores.size() + " parts of the right type");
        }
        this.packagePart = this.packagePart.getRelatedPart(cores.getRelationship(0));
    }

    public final PackagePart getPackagePart() {
        return this.packagePart;
    }

    public final List<POIXMLDocumentPart> getRelations() {
        List<POIXMLDocumentPart> l = new ArrayList<>();
        for (RelationPart rp : this.relations.values()) {
            l.add(rp.getDocumentPart());
        }
        return Collections.unmodifiableList(l);
    }

    public final List<RelationPart> getRelationParts() {
        List<RelationPart> l = new ArrayList<>(this.relations.values());
        return Collections.unmodifiableList(l);
    }

    public final POIXMLDocumentPart getRelationById(String id) {
        RelationPart rp = getRelationPartById(id);
        if (rp == null) {
            return null;
        }
        return rp.getDocumentPart();
    }

    public final RelationPart getRelationPartById(String id) {
        return this.relations.get(id);
    }

    public final String getRelationId(POIXMLDocumentPart part) {
        for (RelationPart rp : this.relations.values()) {
            if (rp.getDocumentPart() == part) {
                return rp.getRelationship().getId();
            }
        }
        return null;
    }

    public final RelationPart addRelation(String relId, POIXMLRelation relationshipType, POIXMLDocumentPart part) {
        PackageRelationship pr = this.packagePart.findExistingRelation(part.getPackagePart());
        if (pr == null) {
            PackagePartName ppn = part.getPackagePart().getPartName();
            String relType = relationshipType.getRelation();
            pr = this.packagePart.addRelationship(ppn, TargetMode.INTERNAL, relType, relId);
        }
        addRelation(pr, part);
        return new RelationPart(pr, part);
    }

    private void addRelation(PackageRelationship pr, POIXMLDocumentPart part) {
        this.relations.put(pr.getId(), new RelationPart(pr, part));
        part.incrementRelationCounter();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void removeRelation(POIXMLDocumentPart part) {
        removeRelation(part, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean removeRelation(POIXMLDocumentPart part, boolean removeUnusedParts) {
        String id = getRelationId(part);
        return removeRelation(id, removeUnusedParts);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void removeRelation(String partId) {
        removeRelation(partId, true);
    }

    private boolean removeRelation(String partId, boolean removeUnusedParts) {
        RelationPart rp = this.relations.get(partId);
        if (rp == null) {
            return false;
        }
        POIXMLDocumentPart part = rp.getDocumentPart();
        part.decrementRelationCounter();
        getPackagePart().removeRelationship(partId);
        this.relations.remove(partId);
        if (removeUnusedParts && part.getRelationCounter() == 0) {
            try {
                part.onDocumentRemove();
                getPackagePart().getPackage().removePart(part.getPackagePart());
                return true;
            } catch (IOException e) {
                throw new POIXMLException(e);
            }
        }
        return true;
    }

    public final POIXMLDocumentPart getParent() {
        return this.parent;
    }

    public String toString() {
        return this.packagePart == null ? "" : this.packagePart.toString();
    }

    protected void commit() throws IOException {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onSave(Set<PackagePart> alreadySaved) throws IOException {
        if (this.isCommitted) {
            return;
        }
        prepareForCommit();
        commit();
        alreadySaved.add(getPackagePart());
        for (RelationPart rp : this.relations.values()) {
            POIXMLDocumentPart p = rp.getDocumentPart();
            if (!alreadySaved.contains(p.getPackagePart())) {
                p.onSave(alreadySaved);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void prepareForCommit() {
        PackagePart part = getPackagePart();
        if (part != null) {
            part.clear();
        }
    }

    public final POIXMLDocumentPart createRelationship(POIXMLRelation descriptor, POIXMLFactory factory) {
        return createRelationship(descriptor, factory, -1, false).getDocumentPart();
    }

    public final POIXMLDocumentPart createRelationship(POIXMLRelation descriptor, POIXMLFactory factory, int idx) {
        return createRelationship(descriptor, factory, idx, false).getDocumentPart();
    }

    @Internal
    public final int getNextPartNumber(POIXMLRelation descriptor, int minIdx) {
        OPCPackage pkg = this.packagePart.getPackage();
        try {
            String name = descriptor.getDefaultFileName();
            if (name.equals(descriptor.getFileName(9999))) {
                PackagePartName ppName = PackagingURIHelper.createPartName(name);
                return pkg.containPart(ppName) ? -1 : 0;
            }
            int maxIdx = pkg.getParts().size() + minIdx;
            for (int idx = minIdx < 0 ? 1 : minIdx; idx <= maxIdx; idx++) {
                PackagePartName ppName2 = PackagingURIHelper.createPartName(descriptor.getFileName(idx));
                if (!pkg.containPart(ppName2)) {
                    return idx;
                }
            }
            return -1;
        } catch (InvalidFormatException e) {
            throw new POIXMLException(e);
        }
    }

    public final RelationPart createRelationship(POIXMLRelation descriptor, POIXMLFactory factory, int idx, boolean noRelation) {
        try {
            PackagePartName ppName = PackagingURIHelper.createPartName(descriptor.getFileName(idx));
            PackageRelationship rel = null;
            PackagePart part = this.packagePart.getPackage().createPart(ppName, descriptor.getContentType());
            if (!noRelation) {
                rel = this.packagePart.addRelationship(ppName, TargetMode.INTERNAL, descriptor.getRelation());
            }
            POIXMLDocumentPart doc = factory.newDocumentPart(descriptor);
            doc.packagePart = part;
            doc.parent = this;
            if (!noRelation) {
                addRelation(rel, doc);
            }
            return new RelationPart(rel, doc);
        } catch (PartAlreadyExistsException pae) {
            throw pae;
        } catch (Exception e) {
            throw new POIXMLException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void read(POIXMLFactory factory, Map<PackagePart, POIXMLDocumentPart> context) throws OpenXML4JException {
        PackagePartName relName;
        PackagePart pp = getPackagePart();
        if (pp.getContentType().equals(XWPFRelation.GLOSSARY_DOCUMENT.getContentType())) {
            LOG.atWarn().log("POI does not currently support template.main+xml (glossary) parts.  Skipping this part for now.");
            return;
        }
        POIXMLDocumentPart otherChild = context.put(pp, this);
        if (otherChild != null && otherChild != this) {
            throw new POIXMLException("Unique PackagePart-POIXMLDocumentPart relation broken!");
        }
        if (pp.hasRelationships()) {
            PackageRelationshipCollection rels = this.packagePart.getRelationships();
            List<POIXMLDocumentPart> readLater = new ArrayList<>();
            Iterator<PackageRelationship> it = rels.iterator();
            while (it.hasNext()) {
                PackageRelationship rel = it.next();
                if (Objects.equals(rel.getRelationshipType(), PackageRelationshipTypes.HYPERLINK_PART)) {
                    this.referenceRelationships.put(rel.getId(), new HyperlinkRelationship(this, rel.getTargetURI(), rel.getTargetMode() == TargetMode.EXTERNAL, rel.getId()));
                } else if (rel.getTargetMode() == TargetMode.INTERNAL) {
                    URI uri = rel.getTargetURI();
                    if (uri.getRawFragment() != null) {
                        relName = PackagingURIHelper.createPartName(uri.getPath());
                    } else {
                        relName = PackagingURIHelper.createPartName(uri);
                    }
                    PackagePart p = this.packagePart.getPackage().getPart(relName);
                    if (p == null) {
                        LOG.atError().log("Skipped invalid entry {}", rel.getTargetURI());
                    } else {
                        POIXMLDocumentPart childPart = context.get(p);
                        if (childPart == null) {
                            childPart = factory.createDocumentPart(this, p);
                            if ((this instanceof XDDFChart) && (childPart instanceof XSSFWorkbook)) {
                                ((XDDFChart) this).setWorkbook((XSSFWorkbook) childPart);
                            }
                            childPart.parent = this;
                            context.put(p, childPart);
                            readLater.add(childPart);
                        }
                        addRelation(rel, childPart);
                    }
                }
            }
            Iterator<POIXMLDocumentPart> it2 = readLater.iterator();
            while (it2.hasNext()) {
                it2.next().read(factory, context);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PackagePart getTargetPart(PackageRelationship rel) throws InvalidFormatException {
        return getPackagePart().getRelatedPart(rel);
    }

    protected void onDocumentCreate() throws IOException {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDocumentRead() throws IOException {
    }

    protected void onDocumentRemove() throws IOException {
    }

    @Internal
    @Deprecated
    public static void _invokeOnDocumentRead(POIXMLDocumentPart part) throws IOException {
        part.onDocumentRead();
    }

    public final boolean removeReferenceRelationship(String relId) {
        ReferenceRelationship existing = this.referenceRelationships.remove(relId);
        if (existing != null) {
            this.packagePart.removeRelationship(relId);
            return true;
        }
        return false;
    }

    public final ReferenceRelationship getReferenceRelationship(String relId) {
        return this.referenceRelationships.get(relId);
    }

    public final HyperlinkRelationship createHyperlink(URI uri, boolean isExternal, String relId) {
        this.packagePart.addRelationship(uri, isExternal ? TargetMode.EXTERNAL : TargetMode.INTERNAL, PackageRelationshipTypes.HYPERLINK_PART, relId);
        HyperlinkRelationship hyperlink = new HyperlinkRelationship(this, uri, isExternal, relId);
        this.referenceRelationships.put(relId, hyperlink);
        return hyperlink;
    }

    public final List<ReferenceRelationship> getReferenceRelationships() {
        List<ReferenceRelationship> list = new ArrayList<>(this.referenceRelationships.values());
        return Collections.unmodifiableList(list);
    }

    private static PackagePart getPartFromOPCPackage(OPCPackage pkg, String coreDocumentRel) {
        try {
            PackageRelationship coreRel = pkg.getRelationshipsByType(coreDocumentRel).getRelationship(0);
            if (coreRel != null) {
                PackagePart pp = pkg.getPart(coreRel);
                if (pp == null) {
                    pkg.revert();
                    throw new POIXMLException("OOXML file structure broken/invalid - core document '" + coreRel.getTargetURI() + "' not found.");
                }
                return pp;
            }
            if (pkg.getRelationshipsByType(PackageRelationshipTypes.STRICT_CORE_DOCUMENT).getRelationship(0) != null) {
                pkg.revert();
                throw new POIXMLException("Strict OOXML isn't currently supported, please see bug #57699");
            }
            pkg.revert();
            throw new POIXMLException("OOXML file structure broken/invalid - no core document found!");
        } catch (POIXMLException e) {
            throw e;
        } catch (RuntimeException e2) {
            pkg.revert();
            throw new POIXMLException("OOXML file structure broken/invalid", e2);
        }
    }
}
