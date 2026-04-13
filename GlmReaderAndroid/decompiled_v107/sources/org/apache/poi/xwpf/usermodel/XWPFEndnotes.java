package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.EndnotesDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFtnEdn;

/* loaded from: classes10.dex */
public class XWPFEndnotes extends XWPFAbstractFootnotesEndnotes {
    protected CTEndnotes ctEndnotes;

    public XWPFEndnotes() {
    }

    public XWPFEndnotes(PackagePart part) {
        super(part);
    }

    @Internal
    public void setEndnotes(CTEndnotes endnotes) {
        this.ctEndnotes = endnotes;
    }

    public XWPFEndnote createEndnote() {
        CTFtnEdn newNote = CTFtnEdn.Factory.newInstance();
        newNote.setType(STFtnEdn.NORMAL);
        XWPFEndnote footnote = addEndnote(newNote);
        footnote.getCTFtnEdn().setId(getIdManager().nextId());
        return footnote;
    }

    public boolean removeFootnote(int pos) {
        if (this.ctEndnotes.sizeOfEndnoteArray() >= pos - 1) {
            this.ctEndnotes.removeEndnote(pos);
            this.listFootnote.remove(pos);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() throws IOException {
        try {
            InputStream is = getPackagePart().getInputStream();
            try {
                EndnotesDocument notesDoc = EndnotesDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                this.ctEndnotes = notesDoc.getEndnotes();
                if (is != null) {
                    is.close();
                }
                for (CTFtnEdn note : this.ctEndnotes.getEndnoteList()) {
                    this.listFootnote.add(new XWPFEndnote(note, this));
                }
            } finally {
            }
        } catch (XmlException e) {
            throw new POIXMLException();
        }
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTEndnotes.type.getName().getNamespaceURI(), "endnotes"));
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            this.ctEndnotes.save(out, xmlOptions);
            if (out != null) {
                out.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (out != null) {
                    try {
                        out.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public void addEndnote(XWPFEndnote endnote) {
        this.listFootnote.add(endnote);
        this.ctEndnotes.addNewEndnote().set(endnote.getCTFtnEdn());
    }

    @Internal
    public XWPFEndnote addEndnote(CTFtnEdn note) {
        CTFtnEdn newNote = this.ctEndnotes.addNewEndnote();
        newNote.set(note);
        XWPFEndnote xNote = new XWPFEndnote(newNote, this);
        this.listFootnote.add(xNote);
        return xNote;
    }

    @Override // org.apache.poi.xwpf.usermodel.XWPFAbstractFootnotesEndnotes
    public XWPFEndnote getFootnoteById(int id) {
        return (XWPFEndnote) super.getFootnoteById(id);
    }

    public List<XWPFEndnote> getEndnotesList() {
        List<XWPFEndnote> resultList = new ArrayList<>();
        for (XWPFAbstractFootnoteEndnote note : this.listFootnote) {
            resultList.add((XWPFEndnote) note);
        }
        return resultList;
    }

    public boolean removeEndnote(int pos) {
        if (this.ctEndnotes.sizeOfEndnoteArray() >= pos - 1) {
            this.ctEndnotes.removeEndnote(pos);
            this.listFootnote.remove(pos);
            return true;
        }
        return false;
    }
}
