package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesSlide;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlide;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdListEntry;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterIdListEntry;
import org.openxmlformats.schemas.presentationml.x2006.main.CmLstDocument;
import org.openxmlformats.schemas.presentationml.x2006.main.NotesDocument;
import org.openxmlformats.schemas.presentationml.x2006.main.PresentationDocument;
import org.openxmlformats.schemas.presentationml.x2006.main.SldDocument;
import org.openxmlformats.schemas.presentationml.x2006.main.SldMasterDocument;

/* loaded from: classes10.dex */
public class XSLFSlideShow extends POIXMLDocument {
    private final List<PackagePart> embeddedParts;
    private final PresentationDocument presentationDoc;

    public XSLFSlideShow(OPCPackage container) throws OpenXML4JException, IOException, XmlException {
        super(container);
        if (getCorePart().getContentType().equals(XSLFRelation.THEME_MANAGER.getContentType())) {
            rebase(getPackage());
        }
        InputStream stream = getCorePart().getInputStream();
        try {
            this.presentationDoc = PresentationDocument.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            if (stream != null) {
                stream.close();
            }
            this.embeddedParts = new LinkedList();
            for (CTSlideIdListEntry ctSlide : getSlideReferences().getSldIdArray()) {
                PackagePart corePart = getCorePart();
                PackagePart slidePart = corePart.getRelatedPart(corePart.getRelationship(ctSlide.getId2()));
                Iterator<PackageRelationship> it = slidePart.getRelationshipsByType(POIXMLDocument.OLE_OBJECT_REL_TYPE).iterator();
                while (it.hasNext()) {
                    PackageRelationship rel = it.next();
                    if (TargetMode.EXTERNAL != rel.getTargetMode()) {
                        this.embeddedParts.add(slidePart.getRelatedPart(rel));
                    }
                }
                Iterator<PackageRelationship> it2 = slidePart.getRelationshipsByType(POIXMLDocument.PACK_OBJECT_REL_TYPE).iterator();
                while (it2.hasNext()) {
                    this.embeddedParts.add(slidePart.getRelatedPart(it2.next()));
                }
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public XSLFSlideShow(String file) throws OpenXML4JException, IOException, XmlException {
        this(openPackage(file));
    }

    @Internal
    public CTPresentation getPresentation() {
        return this.presentationDoc.getPresentation();
    }

    @Internal
    public CTSlideIdList getSlideReferences() {
        if (!getPresentation().isSetSldIdLst()) {
            getPresentation().setSldIdLst(CTSlideIdList.Factory.newInstance());
        }
        return getPresentation().getSldIdLst();
    }

    @Internal
    public CTSlideMasterIdList getSlideMasterReferences() {
        return getPresentation().getSldMasterIdLst();
    }

    public PackagePart getSlideMasterPart(CTSlideMasterIdListEntry master) throws IOException, XmlException {
        try {
            PackagePart corePart = getCorePart();
            return corePart.getRelatedPart(corePart.getRelationship(master.getId2()));
        } catch (InvalidFormatException e) {
            throw new XmlException(e);
        }
    }

    @Internal
    public CTSlideMaster getSlideMaster(CTSlideMasterIdListEntry master) throws IOException, XmlException {
        PackagePart masterPart = getSlideMasterPart(master);
        InputStream stream = masterPart.getInputStream();
        try {
            SldMasterDocument masterDoc = SldMasterDocument.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            CTSlideMaster sldMaster = masterDoc.getSldMaster();
            if (stream != null) {
                stream.close();
            }
            return sldMaster;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public PackagePart getSlidePart(CTSlideIdListEntry slide) throws IOException, XmlException {
        try {
            PackagePart corePart = getCorePart();
            return corePart.getRelatedPart(corePart.getRelationship(slide.getId2()));
        } catch (InvalidFormatException e) {
            throw new XmlException(e);
        }
    }

    @Internal
    public CTSlide getSlide(CTSlideIdListEntry slide) throws IOException, XmlException {
        PackagePart slidePart = getSlidePart(slide);
        InputStream stream = slidePart.getInputStream();
        try {
            SldDocument slideDoc = SldDocument.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            CTSlide sld = slideDoc.getSld();
            if (stream != null) {
                stream.close();
            }
            return sld;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public PackagePart getNodesPart(CTSlideIdListEntry parentSlide) throws IOException, XmlException {
        PackagePart slidePart = getSlidePart(parentSlide);
        try {
            PackageRelationshipCollection notes = slidePart.getRelationshipsByType(XSLFRelation.NOTES.getRelation());
            if (notes.isEmpty()) {
                return null;
            }
            if (notes.size() > 1) {
                throw new IOException("Expecting 0 or 1 notes for a slide, but found " + notes.size());
            }
            try {
                return slidePart.getRelatedPart(notes.getRelationship(0));
            } catch (InvalidFormatException e) {
                throw new IllegalStateException(e);
            }
        } catch (InvalidFormatException e2) {
            throw new IOException(e2);
        }
    }

    @Internal
    public CTNotesSlide getNotes(CTSlideIdListEntry slide) throws IOException, XmlException {
        PackagePart notesPart = getNodesPart(slide);
        if (notesPart == null) {
            return null;
        }
        InputStream stream = notesPart.getInputStream();
        try {
            NotesDocument notesDoc = NotesDocument.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            CTNotesSlide notes = notesDoc.getNotes();
            if (stream != null) {
                stream.close();
            }
            return notes;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Internal
    public CTCommentList getSlideComments(CTSlideIdListEntry slide) throws IOException, XmlException {
        PackagePart slidePart = getSlidePart(slide);
        try {
            PackageRelationshipCollection commentRels = slidePart.getRelationshipsByType(XSLFRelation.COMMENTS.getRelation());
            if (commentRels.isEmpty()) {
                return null;
            }
            if (commentRels.size() > 1) {
                throw new IOException("Expecting 0 or 1 comments for a slide, but found " + commentRels.size());
            }
            try {
                PackagePart cPart = slidePart.getRelatedPart(commentRels.getRelationship(0));
                InputStream stream = cPart.getInputStream();
                try {
                    CmLstDocument commDoc = CmLstDocument.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                    CTCommentList cmLst = commDoc.getCmLst();
                    if (stream != null) {
                        stream.close();
                    }
                    return cmLst;
                } finally {
                }
            } catch (InvalidFormatException e) {
                throw new IOException(e);
            }
        } catch (InvalidFormatException e2) {
            throw new IOException(e2);
        }
    }

    @Override // org.apache.poi.ooxml.POIXMLDocument
    public List<PackagePart> getAllEmbeddedParts() throws OpenXML4JException {
        return this.embeddedParts;
    }
}
