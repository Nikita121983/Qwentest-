package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Notes;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesSlide;
import org.openxmlformats.schemas.presentationml.x2006.main.NotesDocument;

/* loaded from: classes10.dex */
public final class XSLFNotes extends XSLFSheet implements Notes<XSLFShape, XSLFTextParagraph> {
    private CTNotesSlide _notes;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFNotes() {
        this._notes = prototype();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFNotes(PackagePart part) throws IOException, XmlException {
        super(part);
        InputStream stream = getPackagePart().getInputStream();
        try {
            NotesDocument doc = NotesDocument.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this._notes = doc.getNotes();
            if (stream != null) {
                stream.close();
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

    private static CTNotesSlide prototype() {
        CTNotesSlide ctNotes = CTNotesSlide.Factory.newInstance();
        CTCommonSlideData cSld = ctNotes.addNewCSld();
        cSld.addNewSpTree();
        return ctNotes;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    public CTNotesSlide getXmlObject() {
        return this._notes;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    protected String getRootElementName() {
        return "notes";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.poi.xslf.usermodel.XSLFNotesMaster] */
    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    public XSLFTheme getTheme() {
        ?? masterSheet = getMasterSheet();
        if (masterSheet != 0) {
            return masterSheet.getTheme();
        }
        return null;
    }

    @Override // org.apache.poi.sl.usermodel.Sheet
    public MasterSheet<XSLFShape, XSLFTextParagraph> getMasterSheet() {
        for (POIXMLDocumentPart p : getRelations()) {
            if (p instanceof XSLFNotesMaster) {
                return (XSLFNotesMaster) p;
            }
        }
        return null;
    }

    @Override // org.apache.poi.sl.usermodel.Notes
    public List<? extends List<XSLFTextParagraph>> getTextParagraphs() {
        List<List<XSLFTextParagraph>> tp = new ArrayList<>();
        for (XSLFShape sh : super.getShapes()) {
            if (sh instanceof XSLFTextShape) {
                XSLFTextShape txt = (XSLFTextShape) sh;
                tp.add(txt.getTextParagraphs());
            }
        }
        return tp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    public String mapSchemeColor(String schemeColor) {
        return mapSchemeColor(this._notes.getClrMapOvr(), schemeColor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeRelations(XSLFSlide slide, XSLFNotesMaster master) {
        super.removeRelation(slide);
        super.removeRelation(master);
    }
}
