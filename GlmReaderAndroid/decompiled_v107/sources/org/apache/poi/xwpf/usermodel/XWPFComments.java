package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComment;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComments;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CommentsDocument;

/* loaded from: classes10.dex */
public class XWPFComments extends POIXMLDocumentPart {
    private final List<XWPFComment> comments;
    private CTComments ctComments;
    XWPFDocument document;
    private final List<XWPFPictureData> pictures;

    public XWPFComments(POIXMLDocumentPart parent, PackagePart part) {
        super(parent, part);
        this.comments = new ArrayList();
        this.pictures = new ArrayList();
        if (!(getParent() instanceof XWPFDocument)) {
            throw new IllegalStateException("Parent is not a XWPFDocuemnt: " + getParent());
        }
        this.document = (XWPFDocument) getParent();
        if (this.document == null) {
            throw new NullPointerException();
        }
    }

    public XWPFComments() {
        this.comments = new ArrayList();
        this.pictures = new ArrayList();
        this.ctComments = CTComments.Factory.newInstance();
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() throws IOException {
        try {
            InputStream is = getPackagePart().getInputStream();
            try {
                CommentsDocument doc = CommentsDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                this.ctComments = doc.getComments();
                for (CTComment ctComment : this.ctComments.getCommentList()) {
                    this.comments.add(new XWPFComment(ctComment, this));
                }
                if (is != null) {
                    is.close();
                }
                for (POIXMLDocumentPart poixmlDocumentPart : getRelations()) {
                    if (poixmlDocumentPart instanceof XWPFPictureData) {
                        XWPFPictureData xwpfPicData = (XWPFPictureData) poixmlDocumentPart;
                        this.pictures.add(xwpfPicData);
                        this.document.registerPackagePictureData(xwpfPicData);
                    }
                }
            } finally {
            }
        } catch (XmlException e) {
            throw new POIXMLException("Unable to read comments", e);
        }
    }

    public String addPictureData(InputStream is, int format) throws InvalidFormatException, IOException {
        byte[] data = IOUtils.toByteArrayWithMaxLength(is, XWPFPictureData.getMaxImageSize());
        return addPictureData(data, format);
    }

    public String addPictureData(InputStream is, PictureType pictureType) throws InvalidFormatException, IOException {
        byte[] data = IOUtils.toByteArrayWithMaxLength(is, XWPFPictureData.getMaxImageSize());
        return addPictureData(data, pictureType);
    }

    @Removal(version = "7.0.0")
    @Deprecated
    public String addPictureData(byte[] pictureData, int format) throws InvalidFormatException {
        return addPictureData(pictureData, PictureType.findByOoxmlId(format));
    }

    public String addPictureData(byte[] pictureData, PictureType pictureType) throws InvalidFormatException {
        if (pictureType == null) {
            throw new InvalidFormatException("pictureType is not supported");
        }
        XWPFPictureData xwpfPicData = this.document.findPackagePictureData(pictureData);
        POIXMLRelation relDesc = XWPFPictureData.RELATIONS[pictureType.ooxmlId];
        if (xwpfPicData == null) {
            int idx = getXWPFDocument().getNextPicNameNumber(pictureType);
            XWPFPictureData xwpfPicData2 = (XWPFPictureData) createRelationship(relDesc, XWPFFactory.getInstance(), idx);
            PackagePart picDataPart = xwpfPicData2.getPackagePart();
            try {
                OutputStream out = picDataPart.getOutputStream();
                try {
                    out.write(pictureData);
                    if (out != null) {
                        out.close();
                    }
                    this.document.registerPackagePictureData(xwpfPicData2);
                    this.pictures.add(xwpfPicData2);
                    return getRelationId(xwpfPicData2);
                } finally {
                }
            } catch (IOException e) {
                throw new POIXMLException(e);
            }
        } else {
            if (!getRelations().contains(xwpfPicData)) {
                POIXMLDocumentPart.RelationPart rp = addRelation(null, XWPFRelation.IMAGES, xwpfPicData);
                this.pictures.add(xwpfPicData);
                return rp.getRelationship().getId();
            }
            return getRelationId(xwpfPicData);
        }
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTComments.type.getName().getNamespaceURI(), "comments"));
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            this.ctComments.save(out, xmlOptions);
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

    public List<XWPFPictureData> getAllPictures() {
        return Collections.unmodifiableList(this.pictures);
    }

    public CTComments getCtComments() {
        return this.ctComments;
    }

    @Internal
    public void setCtComments(CTComments ctComments) {
        this.ctComments = ctComments;
    }

    public List<XWPFComment> getComments() {
        return Collections.unmodifiableList(this.comments);
    }

    public XWPFComment getComment(int pos) {
        if (pos >= 0 && pos < this.ctComments.sizeOfCommentArray()) {
            return getComments().get(pos);
        }
        return null;
    }

    public XWPFComment getCommentByID(String id) {
        for (XWPFComment comment : this.comments) {
            if (comment.getId().equals(id)) {
                return comment;
            }
        }
        return null;
    }

    public XWPFComment getComment(CTComment ctComment) {
        for (XWPFComment comment : this.comments) {
            if (comment.getCtComment() == ctComment) {
                return comment;
            }
        }
        return null;
    }

    public XWPFComment createComment(BigInteger cid) {
        CTComment ctComment = this.ctComments.addNewComment();
        ctComment.setId(cid);
        XWPFComment comment = new XWPFComment(ctComment, this);
        this.comments.add(comment);
        return comment;
    }

    public boolean removeComment(int pos) {
        if (pos >= 0 && pos < this.ctComments.sizeOfCommentArray()) {
            this.comments.remove(pos);
            this.ctComments.removeComment(pos);
            return true;
        }
        return false;
    }

    public XWPFDocument getXWPFDocument() {
        if (this.document != null) {
            return this.document;
        }
        return (XWPFDocument) getParent();
    }

    public void setXWPFDocument(XWPFDocument document) {
        this.document = document;
    }
}
