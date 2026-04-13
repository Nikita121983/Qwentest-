package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.NotesMasterDocument;

/* loaded from: classes10.dex */
public class XSLFNotesMaster extends XSLFSheet implements MasterSheet<XSLFShape, XSLFTextParagraph> {
    private CTNotesMaster _slide;

    @Override // org.apache.poi.sl.usermodel.MasterSheet
    public /* bridge */ /* synthetic */ SimpleShape<XSLFShape, XSLFTextParagraph> getPlaceholder(Placeholder placeholder) {
        return super.getPlaceholder(placeholder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFNotesMaster() {
        this._slide = prototype();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XSLFNotesMaster(PackagePart part) throws IOException, XmlException {
        super(part);
        InputStream stream = getPackagePart().getInputStream();
        try {
            NotesMasterDocument doc = NotesMasterDocument.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this._slide = doc.getNotesMaster();
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

    private static CTNotesMaster prototype() {
        InputStream is = XSLFNotesMaster.class.getResourceAsStream("notesMaster.xml");
        if (is == null) {
            throw new POIXMLException("Missing resource 'notesMaster.xml'");
        }
        try {
            try {
                NotesMasterDocument doc = NotesMasterDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                return doc.getNotesMaster();
            } finally {
                is.close();
            }
        } catch (Exception e) {
            throw new POIXMLException("Can't initialize NotesMaster", e);
        }
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    public CTNotesMaster getXmlObject() {
        return this._slide;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    protected String getRootElementName() {
        return "notesMaster";
    }

    @Override // org.apache.poi.sl.usermodel.Sheet
    public MasterSheet<XSLFShape, XSLFTextParagraph> getMasterSheet() {
        return null;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    boolean isSupportTheme() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    public String mapSchemeColor(String schemeColor) {
        String notesMasterColor = mapSchemeColor(this._slide.getClrMap(), schemeColor);
        return notesMasterColor == null ? schemeColor : notesMasterColor;
    }
}
