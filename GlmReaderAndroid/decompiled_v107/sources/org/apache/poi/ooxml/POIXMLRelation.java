package org.apache.poi.ooxml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlException;

/* loaded from: classes10.dex */
public abstract class POIXMLRelation {
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) POIXMLRelation.class);
    private final String _defaultName;
    private final String _relation;
    private final String _type;
    private final NoArgConstructor noArgConstructor;
    private final PackagePartConstructor packagePartConstructor;
    private final ParentPartConstructor parentPartConstructor;

    @Internal
    /* loaded from: classes10.dex */
    public interface NoArgConstructor {
        POIXMLDocumentPart init();
    }

    @Internal
    /* loaded from: classes10.dex */
    public interface PackagePartConstructor {
        POIXMLDocumentPart init(PackagePart packagePart) throws IOException, XmlException;
    }

    @Internal
    /* loaded from: classes10.dex */
    public interface ParentPartConstructor {
        POIXMLDocumentPart init(POIXMLDocumentPart pOIXMLDocumentPart, PackagePart packagePart) throws IOException, XmlException;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public POIXMLRelation(String type, String rel, String defaultName, NoArgConstructor noArgConstructor, PackagePartConstructor packagePartConstructor, ParentPartConstructor parentPartConstructor) {
        this._type = type;
        this._relation = rel;
        this._defaultName = defaultName;
        this.noArgConstructor = noArgConstructor;
        this.packagePartConstructor = packagePartConstructor;
        this.parentPartConstructor = parentPartConstructor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public POIXMLRelation(String type, String rel, String defaultName) {
        this(type, rel, defaultName, null, null, null);
    }

    public String getContentType() {
        return this._type;
    }

    public String getRelation() {
        return this._relation;
    }

    public String getDefaultFileName() {
        return this._defaultName;
    }

    public String getFileName(int index) {
        if (!this._defaultName.contains("#")) {
            return getDefaultFileName();
        }
        return this._defaultName.replace("#", Integer.toString(index));
    }

    public Integer getFileNameIndex(POIXMLDocumentPart part) {
        String regex = this._defaultName.replace("#", "(\\d+)");
        return Integer.valueOf(part.getPackagePart().getPartName().getName().replaceAll(regex, "$1"));
    }

    public NoArgConstructor getNoArgConstructor() {
        return this.noArgConstructor;
    }

    public PackagePartConstructor getPackagePartConstructor() {
        return this.packagePartConstructor;
    }

    public ParentPartConstructor getParentPartConstructor() {
        return this.parentPartConstructor;
    }

    public InputStream getContents(PackagePart corePart) throws IOException, InvalidFormatException {
        if (corePart == null) {
            throw new IllegalArgumentException("Core-Part cannot be empty");
        }
        PackageRelationshipCollection prc = corePart.getRelationshipsByType(getRelation());
        Iterator<PackageRelationship> it = prc.iterator();
        if (it.hasNext()) {
            PackageRelationship rel = it.next();
            PackagePartName relName = PackagingURIHelper.createPartName(rel.getTargetURI());
            PackagePart part = corePart.getPackage().getPart(relName);
            if (part == null) {
                throw new IllegalArgumentException("Could not read part " + relName + " from " + corePart);
            }
            return part.getInputStream();
        }
        LOGGER.atWarn().log("No part {} found", getDefaultFileName());
        return null;
    }
}
