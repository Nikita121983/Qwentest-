package org.apache.poi.ooxml;

import java.io.IOException;
import java.util.Iterator;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.xmlbeans.XmlException;

/* loaded from: classes10.dex */
public abstract class POIXMLFactory {
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) POIXMLFactory.class);

    protected abstract POIXMLRelation getDescriptor(String str);

    public POIXMLDocumentPart createDocumentPart(POIXMLDocumentPart parent, PackagePart part) {
        PackageRelationship rel = getPackageRelationship(parent, part);
        String relType = rel.getRelationshipType();
        POIXMLRelation descriptor = getDescriptor(relType);
        if (descriptor != null) {
            try {
                if (!POIXMLDocument.PACK_OBJECT_REL_TYPE.equals(relType)) {
                    POIXMLRelation.ParentPartConstructor parentPartConstructor = descriptor.getParentPartConstructor();
                    if (parentPartConstructor != null) {
                        return parentPartConstructor.init(parent, part);
                    }
                    POIXMLRelation.PackagePartConstructor packagePartConstructor = descriptor.getPackagePartConstructor();
                    if (packagePartConstructor != null) {
                        return packagePartConstructor.init(part);
                    }
                }
            } catch (IOException | XmlException e) {
                throw new POIXMLException(e.getMessage(), e);
            }
        }
        LOGGER.atDebug().log("using default POIXMLDocumentPart for {}", rel.getRelationshipType());
        return new POIXMLDocumentPart(parent, part);
    }

    public POIXMLDocumentPart newDocumentPart(POIXMLRelation descriptor) {
        if (descriptor == null || descriptor.getNoArgConstructor() == null) {
            throw new POIXMLException("can't initialize POIXMLDocumentPart");
        }
        return descriptor.getNoArgConstructor().init();
    }

    protected PackageRelationship getPackageRelationship(POIXMLDocumentPart parent, PackagePart part) {
        try {
            String partName = part.getPartName().getName();
            Iterator<PackageRelationship> it = parent.getPackagePart().getRelationships().iterator();
            while (it.hasNext()) {
                PackageRelationship pr = it.next();
                String packName = pr.getTargetURI().toASCIIString();
                if (packName.equalsIgnoreCase(partName)) {
                    return pr;
                }
            }
            throw new POIXMLException("package part isn't a child of the parent document.");
        } catch (InvalidFormatException e) {
            throw new POIXMLException("error while determining package relations", e);
        }
    }
}
