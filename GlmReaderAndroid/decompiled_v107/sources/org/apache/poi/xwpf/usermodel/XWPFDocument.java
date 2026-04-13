package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.POIException;
import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.util.IdentifierManager;
import org.apache.poi.ooxml.util.PackageHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CommentsDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.DocumentDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.EndnotesDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.FootnotesDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.NumberingDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument;

/* loaded from: classes10.dex */
public class XWPFDocument extends POIXMLDocument implements Document, IBody {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XWPFDocument.class);
    private static final int MAX_NODE_DEPTH = 1000;
    protected List<IBodyElement> bodyElements;
    protected final List<XWPFChart> charts;
    private XWPFComments comments;
    protected List<XWPFSDT> contentControls;
    private CTDocument1 ctDocument;
    private final IdentifierManager drawingIdManager;
    protected XWPFEndnotes endnotes;
    protected List<XWPFFooter> footers;
    private final FootnoteEndnoteIdManager footnoteIdManager;
    protected XWPFFootnotes footnotes;
    private XWPFHeaderFooterPolicy headerFooterPolicy;
    protected List<XWPFHeader> headers;
    protected List<XWPFHyperlink> hyperlinks;
    protected XWPFNumbering numbering;
    protected Map<Long, List<XWPFPictureData>> packagePictures;
    protected List<XWPFParagraph> paragraphs;
    protected List<XWPFPictureData> pictures;
    private XWPFSettings settings;
    protected XWPFStyles styles;
    protected List<XWPFTable> tables;
    protected XWPFTheme theme;

    /* renamed from: $r8$lambda$GS-ECQyPUrmJMrW7a_HjCuF6CYo, reason: not valid java name */
    public static /* synthetic */ NoSuchElementException m2586$r8$lambda$GSECQyPUrmJMrW7a_HjCuF6CYo() {
        return new NoSuchElementException();
    }

    public XWPFDocument(OPCPackage pkg) throws IOException {
        super(pkg);
        this.footers = new ArrayList();
        this.headers = new ArrayList();
        this.hyperlinks = new ArrayList();
        this.paragraphs = new ArrayList();
        this.tables = new ArrayList();
        this.contentControls = new ArrayList();
        this.bodyElements = new ArrayList();
        this.pictures = new ArrayList();
        this.packagePictures = new HashMap();
        this.charts = new ArrayList();
        this.drawingIdManager = new IdentifierManager(0L, 4294967295L);
        this.footnoteIdManager = new FootnoteEndnoteIdManager(this);
        load(XWPFFactory.getInstance());
    }

    public XWPFDocument(InputStream stream) throws IOException {
        this(stream, true);
    }

    public XWPFDocument(InputStream stream, boolean closeStream) throws IOException {
        super(PackageHelper.open(stream, closeStream));
        this.footers = new ArrayList();
        this.headers = new ArrayList();
        this.hyperlinks = new ArrayList();
        this.paragraphs = new ArrayList();
        this.tables = new ArrayList();
        this.contentControls = new ArrayList();
        this.bodyElements = new ArrayList();
        this.pictures = new ArrayList();
        this.packagePictures = new HashMap();
        this.charts = new ArrayList();
        this.drawingIdManager = new IdentifierManager(0L, 4294967295L);
        this.footnoteIdManager = new FootnoteEndnoteIdManager(this);
        load(XWPFFactory.getInstance());
    }

    public XWPFDocument() {
        super(newPackage());
        this.footers = new ArrayList();
        this.headers = new ArrayList();
        this.hyperlinks = new ArrayList();
        this.paragraphs = new ArrayList();
        this.tables = new ArrayList();
        this.contentControls = new ArrayList();
        this.bodyElements = new ArrayList();
        this.pictures = new ArrayList();
        this.packagePictures = new HashMap();
        this.charts = new ArrayList();
        this.drawingIdManager = new IdentifierManager(0L, 4294967295L);
        this.footnoteIdManager = new FootnoteEndnoteIdManager(this);
        onDocumentCreate();
    }

    protected static OPCPackage newPackage() {
        OPCPackage pkg = null;
        try {
            pkg = OPCPackage.create(UnsynchronizedByteArrayOutputStream.builder().get());
            PackagePartName corePartName = PackagingURIHelper.createPartName(XWPFRelation.DOCUMENT.getDefaultFileName());
            pkg.addRelationship(corePartName, TargetMode.INTERNAL, PackageRelationshipTypes.CORE_DOCUMENT);
            pkg.createPart(corePartName, XWPFRelation.DOCUMENT.getContentType());
            pkg.getPackageProperties().setCreatorProperty(POIXMLDocument.DOCUMENT_CREATOR);
            return pkg;
        } catch (Exception e) {
            IOUtils.closeQuietly(pkg);
            throw new POIXMLException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() throws IOException {
        try {
            InputStream stream = getPackagePart().getInputStream();
            try {
                DocumentDocument doc = DocumentDocument.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                this.ctDocument = doc.getDocument();
                if (stream != null) {
                    stream.close();
                }
                int nodeDepth = XMLHelper.getDepthOfChildNodes(this.ctDocument.getDomNode(), 1000);
                if (nodeDepth > 1000) {
                    throw new IOException(String.format(Locale.ROOT, "The document is too complex, it has a node depth of %s, which exceeds the maximum allowed of %s", Integer.valueOf(nodeDepth), 1000));
                }
                initFootnotes();
                XmlCursor docCursor = this.ctDocument.newCursor();
                try {
                    docCursor.selectPath("./*");
                    while (docCursor.toNextSelection()) {
                        XmlObject o = docCursor.getObject();
                        if (o instanceof CTBody) {
                            XmlCursor bodyCursor = o.newCursor();
                            try {
                                bodyCursor.selectPath("./*");
                                while (bodyCursor.toNextSelection()) {
                                    XmlObject bodyObj = bodyCursor.getObject();
                                    if (bodyObj instanceof CTP) {
                                        XWPFParagraph p = new XWPFParagraph((CTP) bodyObj, this);
                                        this.bodyElements.add(p);
                                        this.paragraphs.add(p);
                                    } else if (bodyObj instanceof CTTbl) {
                                        XWPFTable t = new XWPFTable((CTTbl) bodyObj, this, false);
                                        this.bodyElements.add(t);
                                        this.tables.add(t);
                                    } else if (bodyObj instanceof CTSdtBlock) {
                                        XWPFSDT c = new XWPFSDT((CTSdtBlock) bodyObj, this);
                                        this.bodyElements.add(c);
                                        this.contentControls.add(c);
                                    }
                                }
                                if (bodyCursor != null) {
                                    bodyCursor.close();
                                }
                            } finally {
                            }
                        }
                    }
                    if (docCursor != null) {
                        docCursor.close();
                    }
                    if (doc.getDocument().getBody() != null && doc.getDocument().getBody().getSectPr() != null) {
                        this.headerFooterPolicy = new XWPFHeaderFooterPolicy(this);
                    }
                    for (POIXMLDocumentPart.RelationPart rp : getRelationParts()) {
                        POIXMLDocumentPart p2 = rp.getDocumentPart();
                        String relation = rp.getRelationship().getRelationshipType();
                        try {
                            if (relation.equals(XWPFRelation.STYLES.getRelation())) {
                                this.styles = (XWPFStyles) p2;
                                this.styles.onDocumentRead();
                            } else if (relation.equals(XWPFRelation.THEME.getRelation())) {
                                this.theme = (XWPFTheme) p2;
                                this.theme.onDocumentRead();
                            } else if (relation.equals(XWPFRelation.NUMBERING.getRelation())) {
                                this.numbering = (XWPFNumbering) p2;
                                this.numbering.onDocumentRead();
                            } else if (relation.equals(XWPFRelation.FOOTER.getRelation())) {
                                XWPFFooter footer = (XWPFFooter) p2;
                                this.footers.add(footer);
                                footer.onDocumentRead();
                            } else if (relation.equals(XWPFRelation.HEADER.getRelation())) {
                                XWPFHeader header = (XWPFHeader) p2;
                                this.headers.add(header);
                                header.onDocumentRead();
                            } else if (relation.equals(XWPFRelation.COMMENT.getRelation())) {
                                this.comments = (XWPFComments) p2;
                                this.comments.onDocumentRead();
                            } else if (relation.equals(XWPFRelation.SETTINGS.getRelation())) {
                                this.settings = (XWPFSettings) p2;
                                this.settings.onDocumentRead();
                            } else if (relation.equals(XWPFRelation.IMAGES.getRelation())) {
                                XWPFPictureData picData = (XWPFPictureData) p2;
                                picData.onDocumentRead();
                                registerPackagePictureData(picData);
                                this.pictures.add(picData);
                            } else if (relation.equals(XWPFRelation.CHART.getRelation())) {
                                XWPFChart chartData = (XWPFChart) p2;
                                this.charts.add(chartData);
                            } else if (relation.equals(XWPFRelation.GLOSSARY_DOCUMENT.getRelation())) {
                                for (POIXMLDocumentPart gp : p2.getRelations()) {
                                    POIXMLDocumentPart._invokeOnDocumentRead(gp);
                                }
                            }
                        } catch (ClassCastException e) {
                            throw new IllegalArgumentException("Relation and type of document-part did not match, had relation " + relation + " and type of document-part: " + p2.getClass(), e);
                        }
                    }
                    initHyperlinks();
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (docCursor != null) {
                            try {
                                docCursor.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th4) {
                try {
                    throw th4;
                } catch (Throwable th5) {
                    if (stream != null) {
                        try {
                            stream.close();
                        } catch (Throwable th6) {
                            th4.addSuppressed(th6);
                        }
                    }
                    throw th5;
                }
            }
        } catch (POIException e2) {
            throw new IOException(e2);
        } catch (XmlException e3) {
            throw new POIXMLException(e3);
        }
    }

    private void initHyperlinks() {
        try {
            this.hyperlinks = new ArrayList();
            Iterator<PackageRelationship> it = getPackagePart().getRelationshipsByType(XWPFRelation.HYPERLINK.getRelation()).iterator();
            while (it.hasNext()) {
                PackageRelationship rel = it.next();
                this.hyperlinks.add(new XWPFHyperlink(rel.getId(), rel.getTargetURI().toString()));
            }
        } catch (InvalidFormatException e) {
            throw new POIXMLException(e);
        }
    }

    private void initFootnotes() throws XmlException, IOException {
        for (POIXMLDocumentPart.RelationPart rp : getRelationParts()) {
            POIXMLDocumentPart p = rp.getDocumentPart();
            String relation = rp.getRelationship().getRelationshipType();
            if (relation.equals(XWPFRelation.FOOTNOTE.getRelation()) && (p instanceof XWPFFootnotes)) {
                this.footnotes = (XWPFFootnotes) p;
                this.footnotes.onDocumentRead();
                this.footnotes.setIdManager(this.footnoteIdManager);
            } else if (relation.equals(XWPFRelation.ENDNOTE.getRelation()) && (p instanceof XWPFEndnotes)) {
                this.endnotes = (XWPFEndnotes) p;
                this.endnotes.onDocumentRead();
                this.endnotes.setIdManager(this.footnoteIdManager);
            }
        }
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void onDocumentCreate() {
        this.ctDocument = CTDocument1.Factory.newInstance();
        this.ctDocument.addNewBody();
        this.settings = (XWPFSettings) createRelationship(XWPFRelation.SETTINGS, XWPFFactory.getInstance());
        POIXMLProperties.ExtendedProperties expProps = getProperties().getExtendedProperties();
        expProps.getUnderlyingProperties().setApplication(POIXMLDocument.DOCUMENT_CREATOR);
    }

    @Internal
    public CTDocument1 getDocument() {
        return this.ctDocument;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IdentifierManager getDrawingIdManager() {
        return this.drawingIdManager;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public List<IBodyElement> getBodyElements() {
        return Collections.unmodifiableList(this.bodyElements);
    }

    public Iterator<IBodyElement> getBodyElementsIterator() {
        return this.bodyElements.iterator();
    }

    public Spliterator<IBodyElement> getBodyElementsSpliterator() {
        return this.bodyElements.spliterator();
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public List<XWPFParagraph> getParagraphs() {
        return Collections.unmodifiableList(this.paragraphs);
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public List<XWPFTable> getTables() {
        return Collections.unmodifiableList(this.tables);
    }

    public List<XWPFChart> getCharts() {
        return Collections.unmodifiableList(this.charts);
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFTable getTableArray(int pos) {
        if (pos >= 0 && pos < this.tables.size()) {
            return this.tables.get(pos);
        }
        return null;
    }

    public List<XWPFFooter> getFooterList() {
        return Collections.unmodifiableList(this.footers);
    }

    public XWPFFooter getFooterArray(int pos) {
        if (pos >= 0 && pos < this.footers.size()) {
            return this.footers.get(pos);
        }
        return null;
    }

    public List<XWPFHeader> getHeaderList() {
        return Collections.unmodifiableList(this.headers);
    }

    public XWPFHeader getHeaderArray(int pos) {
        if (pos >= 0 && pos < this.headers.size()) {
            return this.headers.get(pos);
        }
        return null;
    }

    public String getTblStyle(XWPFTable table) {
        return table.getStyleID();
    }

    public XWPFHyperlink getHyperlinkByID(String id) {
        for (XWPFHyperlink link : this.hyperlinks) {
            if (link.getId().equals(id)) {
                return link;
            }
        }
        initHyperlinks();
        for (XWPFHyperlink link2 : this.hyperlinks) {
            if (link2.getId().equals(id)) {
                return link2;
            }
        }
        return null;
    }

    public XWPFFootnote getFootnoteByID(int id) {
        if (this.footnotes == null) {
            return null;
        }
        return (XWPFFootnote) this.footnotes.getFootnoteById(id);
    }

    public XWPFEndnote getEndnoteByID(int id) {
        if (this.endnotes == null) {
            return null;
        }
        return this.endnotes.getFootnoteById(id);
    }

    public List<XWPFFootnote> getFootnotes() {
        if (this.footnotes == null) {
            return Collections.emptyList();
        }
        return this.footnotes.getFootnotesList();
    }

    public XWPFTheme getTheme() {
        return this.theme;
    }

    public XWPFHyperlink[] getHyperlinks() {
        return (XWPFHyperlink[]) this.hyperlinks.toArray(new XWPFHyperlink[0]);
    }

    public XWPFComments getDocComments() {
        return this.comments;
    }

    public XWPFComment getCommentByID(String id) {
        if (this.comments == null) {
            return null;
        }
        return this.comments.getCommentByID(id);
    }

    public XWPFComment[] getComments() {
        if (this.comments == null) {
            return null;
        }
        return (XWPFComment[]) this.comments.getComments().toArray(new XWPFComment[0]);
    }

    public PackagePart getPartById(String id) {
        try {
            PackagePart corePart = getCorePart();
            return corePart.getRelatedPart(corePart.getRelationship(id));
        } catch (InvalidFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public XWPFHeaderFooterPolicy getHeaderFooterPolicy() {
        return this.headerFooterPolicy;
    }

    public XWPFHeaderFooterPolicy createHeaderFooterPolicy() {
        if (this.headerFooterPolicy == null) {
            this.headerFooterPolicy = new XWPFHeaderFooterPolicy(this);
        }
        return this.headerFooterPolicy;
    }

    public XWPFHeader createHeader(HeaderFooterType type) {
        XWPFHeaderFooterPolicy hfPolicy = createHeaderFooterPolicy();
        if (type == HeaderFooterType.FIRST) {
            CTSectPr ctSectPr = getSection();
            if (!ctSectPr.isSetTitlePg()) {
                CTOnOff titlePg = ctSectPr.addNewTitlePg();
                titlePg.setVal(STOnOff1.ON);
            }
        }
        return hfPolicy.createHeader(STHdrFtr.Enum.forInt(type.toInt()));
    }

    public XWPFFooter createFooter(HeaderFooterType type) {
        XWPFHeaderFooterPolicy hfPolicy = createHeaderFooterPolicy();
        if (type == HeaderFooterType.FIRST) {
            CTSectPr ctSectPr = getSection();
            if (!ctSectPr.isSetTitlePg()) {
                CTOnOff titlePg = ctSectPr.addNewTitlePg();
                titlePg.setVal(STOnOff1.ON);
            }
        }
        return hfPolicy.createFooter(STHdrFtr.Enum.forInt(type.toInt()));
    }

    private CTSectPr getSection() {
        CTBody ctBody = getDocument().getBody();
        if (ctBody.isSetSectPr()) {
            return ctBody.getSectPr();
        }
        return ctBody.addNewSectPr();
    }

    @Internal
    public CTStyles getStyle() throws XmlException, IOException {
        try {
            PackagePart[] parts = getRelatedByType(XWPFRelation.STYLES.getRelation());
            if (parts.length != 1) {
                throw new IOException("Expecting one Styles document part, but found " + parts.length);
            }
            InputStream stream = parts[0].getInputStream();
            try {
                StylesDocument sd = StylesDocument.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                CTStyles styles = sd.getStyles();
                if (stream != null) {
                    stream.close();
                }
                return styles;
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
        } catch (InvalidFormatException e) {
            throw new IOException(e);
        }
    }

    @Override // org.apache.poi.ooxml.POIXMLDocument
    public List<PackagePart> getAllEmbeddedParts() throws OpenXML4JException {
        List<PackagePart> embedds = new LinkedList<>();
        PackagePart part = getPackagePart();
        Iterator<PackageRelationship> it = getPackagePart().getRelationshipsByType(POIXMLDocument.OLE_OBJECT_REL_TYPE).iterator();
        while (it.hasNext()) {
            PackageRelationship rel = it.next();
            embedds.add(part.getRelatedPart(rel));
        }
        Iterator<PackageRelationship> it2 = getPackagePart().getRelationshipsByType(POIXMLDocument.PACK_OBJECT_REL_TYPE).iterator();
        while (it2.hasNext()) {
            PackageRelationship rel2 = it2.next();
            embedds.add(part.getRelatedPart(rel2));
        }
        return embedds;
    }

    private int getBodyElementSpecificPos(int pos, List<? extends IBodyElement> list) {
        if (!list.isEmpty() && pos >= 0 && pos < this.bodyElements.size()) {
            IBodyElement needle = this.bodyElements.get(pos);
            if (needle.getElementType() != list.get(0).getElementType()) {
                return -1;
            }
            int startPos = Math.min(pos, list.size() - 1);
            for (int i = startPos; i >= 0; i--) {
                if (list.get(i) == needle) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int getParagraphPos(int pos) {
        return getBodyElementSpecificPos(pos, this.paragraphs);
    }

    public int getTablePos(int pos) {
        return getBodyElementSpecificPos(pos, this.tables);
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFParagraph insertNewParagraph(XmlCursor cursor) {
        Deque<XmlObject> path = getPathToObject(cursor);
        String uri = CTP.type.getName().getNamespaceURI();
        cursor.beginElement("p", uri);
        cursor.toParent();
        CTP p = (CTP) cursor.getObject();
        XWPFParagraph newP = new XWPFParagraph(p, this);
        insertIntoParentElement(newP, path);
        cursor.toCursor(newP.getCTP().newCursor());
        cursor.toEndToken();
        return newP;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFTable insertNewTbl(XmlCursor cursor) {
        Deque<XmlObject> path = getPathToObject(cursor);
        String uri = CTTbl.type.getName().getNamespaceURI();
        cursor.beginElement("tbl", uri);
        cursor.toParent();
        CTTbl t = (CTTbl) cursor.getObject();
        XWPFTable newT = new XWPFTable(t, this);
        insertIntoParentElement(newT, path);
        cursor.toCursor(newT.getCTTbl().newCursor());
        cursor.toEndToken();
        return newT;
    }

    private Deque<XmlObject> getPathToObject(XmlCursor cursor) {
        Deque<XmlObject> searchPath = new LinkedList<>();
        XmlCursor verify = cursor.newCursor();
        while (verify.toParent() && searchPath.peekFirst() != this.ctDocument.getBody()) {
            try {
                searchPath.addFirst(verify.getObject());
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (verify != null) {
                        try {
                            verify.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        if (verify != null) {
            verify.close();
        }
        return searchPath;
    }

    private void insertIntoParentElement(IBodyElement iBodyElement, Deque<XmlObject> path) {
        path.pop();
        if (path.isEmpty()) {
            if (iBodyElement instanceof XWPFParagraph) {
                insertIntoParagraphsAndElements((XWPFParagraph) iBodyElement, this.paragraphs, this.bodyElements);
                return;
            } else {
                if (iBodyElement instanceof XWPFTable) {
                    insertIntoTablesAndElements((XWPFTable) iBodyElement, this.tables, this.bodyElements);
                    return;
                }
                return;
            }
        }
        CTTbl ctTbl = (CTTbl) path.pop();
        for (XWPFTable xwpfTable : this.tables) {
            if (ctTbl == xwpfTable.getCTTbl()) {
                insertElementIntoTable(xwpfTable, iBodyElement, path);
            }
        }
    }

    private void insertIntoParagraphsAndElements(XWPFParagraph newP, List<XWPFParagraph> paragraphs, List<IBodyElement> bodyElements) {
        insertIntoParagraphs(newP, paragraphs);
        insertIntoBodyElements(newP, bodyElements);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void insertIntoParagraphs(org.apache.poi.xwpf.usermodel.XWPFParagraph r5, java.util.List<org.apache.poi.xwpf.usermodel.XWPFParagraph> r6) {
        /*
            r4 = this;
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP r0 = r5.getCTP()
            org.apache.xmlbeans.XmlCursor r0 = r0.newCursor()
            org.apache.xmlbeans.XmlObject r1 = r0.getObject()     // Catch: java.lang.Throwable -> L3f
            r2 = 0
        Ld:
            boolean r3 = r2 instanceof org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP     // Catch: java.lang.Throwable -> L3f
            if (r3 != 0) goto L1d
            boolean r3 = r0.toPrevSibling()     // Catch: java.lang.Throwable -> L3f
            if (r3 == 0) goto L1d
            org.apache.xmlbeans.XmlObject r3 = r0.getObject()     // Catch: java.lang.Throwable -> L3f
            r2 = r3
            goto Ld
        L1d:
            boolean r3 = r2 instanceof org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP     // Catch: java.lang.Throwable -> L3f
            if (r3 == 0) goto L35
            if (r2 != r1) goto L24
            goto L35
        L24:
            r3 = r2
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP r3 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP) r3     // Catch: java.lang.Throwable -> L3f
            org.apache.poi.xwpf.usermodel.XWPFParagraph r3 = r4.getParagraph(r3)     // Catch: java.lang.Throwable -> L3f
            int r3 = r6.indexOf(r3)     // Catch: java.lang.Throwable -> L3f
            int r3 = r3 + 1
            r6.add(r3, r5)     // Catch: java.lang.Throwable -> L3f
            goto L39
        L35:
            r3 = 0
            r6.add(r3, r5)     // Catch: java.lang.Throwable -> L3f
        L39:
            if (r0 == 0) goto L3e
            r0.close()
        L3e:
            return
        L3f:
            r1 = move-exception
            throw r1     // Catch: java.lang.Throwable -> L41
        L41:
            r2 = move-exception
            if (r0 == 0) goto L4c
            r0.close()     // Catch: java.lang.Throwable -> L48
            goto L4c
        L48:
            r3 = move-exception
            r1.addSuppressed(r3)
        L4c:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFDocument.insertIntoParagraphs(org.apache.poi.xwpf.usermodel.XWPFParagraph, java.util.List):void");
    }

    private void insertIntoTablesAndElements(XWPFTable newT, List<XWPFTable> tables, List<IBodyElement> bodyElements) {
        insertIntoTables(newT, tables);
        insertIntoBodyElements(newT, bodyElements);
    }

    private void insertIntoTables(XWPFTable newT, List<XWPFTable> tables) {
        XmlCursor cursor = newT.getCTTbl().newCursor();
        try {
            cursor.getObject();
            XmlObject o = null;
            while (!(o instanceof CTTbl) && cursor.toPrevSibling()) {
                o = cursor.getObject();
            }
            if (!(o instanceof CTTbl)) {
                tables.add(0, newT);
            } else {
                int pos = tables.indexOf(getTable((CTTbl) o)) + 1;
                tables.add(pos, newT);
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private void insertIntoBodyElements(IBodyElement iBodyElement, List<IBodyElement> bodyElements) {
        try {
            XmlCursor cursor = getNewCursor(iBodyElement).orElseThrow(new Supplier() { // from class: org.apache.poi.xwpf.usermodel.XWPFDocument$$ExternalSyntheticLambda1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return XWPFDocument.m2586$r8$lambda$GSECQyPUrmJMrW7a_HjCuF6CYo();
                }
            });
            try {
                XmlCursor newParaPos = getNewCursor(iBodyElement).orElseThrow(new Supplier() { // from class: org.apache.poi.xwpf.usermodel.XWPFDocument$$ExternalSyntheticLambda1
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return XWPFDocument.m2586$r8$lambda$GSECQyPUrmJMrW7a_HjCuF6CYo();
                    }
                });
                int i = 0;
                try {
                    cursor.toCursor(newParaPos);
                    while (cursor.toPrevSibling()) {
                        XmlObject o = cursor.getObject();
                        if ((o instanceof CTP) || (o instanceof CTTbl) || (o instanceof CTSdtBlock)) {
                            i++;
                        }
                    }
                    bodyElements.add(i, iBodyElement);
                    cursor.toCursor(newParaPos);
                    cursor.toEndToken();
                    if (newParaPos != null) {
                        newParaPos.close();
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                } finally {
                }
            } finally {
            }
        } catch (NoSuchElementException e) {
        }
    }

    private Optional<XmlCursor> getNewCursor(IBodyElement iBodyElement) {
        if (iBodyElement instanceof XWPFParagraph) {
            return Optional.ofNullable(((XWPFParagraph) iBodyElement).getCTP().newCursor());
        }
        if (iBodyElement instanceof XWPFTable) {
            return Optional.ofNullable(((XWPFTable) iBodyElement).getCTTbl().newCursor());
        }
        return Optional.empty();
    }

    private void insertElementIntoTable(XWPFTable xwpfTable, IBodyElement iBodyElement, Deque<XmlObject> path) {
        CTRow row = (CTRow) path.pop();
        for (XWPFTableRow tableRow : xwpfTable.getRows()) {
            if (tableRow.getCtRow() == row) {
                insertElementIntoRow(tableRow, iBodyElement, path);
            }
        }
    }

    private void insertElementIntoRow(XWPFTableRow tableRow, IBodyElement iBodyElement, Deque<XmlObject> path) {
        CTTc cell = (CTTc) path.pop();
        for (XWPFTableCell tableCell : tableRow.getTableCells()) {
            if (tableCell.getCTTc() == cell) {
                insertElementIntoCell(tableCell, iBodyElement, path);
            }
        }
    }

    private void insertElementIntoCell(XWPFTableCell tableCell, IBodyElement iBodyElement, Deque<XmlObject> path) {
        if (path.isEmpty()) {
            if (iBodyElement instanceof XWPFParagraph) {
                insertIntoParagraphsAndElements((XWPFParagraph) iBodyElement, tableCell.paragraphs, tableCell.bodyElements);
                return;
            } else {
                if (iBodyElement instanceof XWPFTable) {
                    insertIntoTablesAndElements((XWPFTable) iBodyElement, tableCell.tables, tableCell.bodyElements);
                    return;
                }
                return;
            }
        }
        insertElementIntoTable((XWPFTable) path.pop(), iBodyElement, path);
    }

    private int getPosOfBodyElement(IBodyElement needle) {
        BodyElementType type = needle.getElementType();
        for (int i = 0; i < this.bodyElements.size(); i++) {
            IBodyElement current = this.bodyElements.get(i);
            if (current.getElementType() == type && current.equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    public int getPosOfParagraph(XWPFParagraph p) {
        return getPosOfBodyElement(p);
    }

    public int getPosOfTable(XWPFTable t) {
        return getPosOfBodyElement(t);
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTDocument1.type.getName().getNamespaceURI(), "document"));
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            this.ctDocument.save(out, xmlOptions);
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

    private int getRelationIndex(XWPFRelation relation) {
        int i = 1;
        for (POIXMLDocumentPart.RelationPart rp : getRelationParts()) {
            if (rp.getRelationship().getRelationshipType().equals(relation.getRelation())) {
                i++;
            }
        }
        return i;
    }

    public XWPFParagraph createParagraph() {
        XWPFParagraph p = new XWPFParagraph(this.ctDocument.getBody().addNewP(), this);
        this.bodyElements.add(p);
        this.paragraphs.add(p);
        return p;
    }

    public XWPFComments createComments() {
        if (this.comments == null) {
            CommentsDocument commentsDoc = CommentsDocument.Factory.newInstance();
            XWPFRelation relation = XWPFRelation.COMMENT;
            int i = getRelationIndex(relation);
            XWPFComments wrapper = (XWPFComments) createRelationship(relation, XWPFFactory.getInstance(), i);
            wrapper.setCtComments(commentsDoc.addNewComments());
            wrapper.setXWPFDocument(getXWPFDocument());
            this.comments = wrapper;
        }
        return this.comments;
    }

    public XWPFNumbering createNumbering() {
        if (this.numbering == null) {
            NumberingDocument numberingDoc = NumberingDocument.Factory.newInstance();
            XWPFRelation relation = XWPFRelation.NUMBERING;
            int i = getRelationIndex(relation);
            XWPFNumbering wrapper = (XWPFNumbering) createRelationship(relation, XWPFFactory.getInstance(), i);
            wrapper.setNumbering(numberingDoc.addNewNumbering());
            this.numbering = wrapper;
        }
        return this.numbering;
    }

    public XWPFStyles createStyles() {
        if (this.styles == null) {
            StylesDocument stylesDoc = StylesDocument.Factory.newInstance();
            XWPFRelation relation = XWPFRelation.STYLES;
            int i = getRelationIndex(relation);
            XWPFStyles wrapper = (XWPFStyles) createRelationship(relation, XWPFFactory.getInstance(), i);
            wrapper.setStyles(stylesDoc.addNewStyles());
            this.styles = wrapper;
        }
        return this.styles;
    }

    public XWPFTheme createTheme() {
        if (this.theme == null) {
            ThemeDocument themeDoc = ThemeDocument.Factory.newInstance();
            XWPFRelation relation = XWPFRelation.THEME;
            int i = getRelationIndex(relation);
            XWPFTheme wrapper = (XWPFTheme) createRelationship(relation, XWPFFactory.getInstance(), i);
            wrapper.setTheme(themeDoc.addNewTheme());
            this.theme = wrapper;
        }
        return this.theme;
    }

    public XWPFFootnotes createFootnotes() {
        if (this.footnotes == null) {
            FootnotesDocument footnotesDoc = FootnotesDocument.Factory.newInstance();
            XWPFRelation relation = XWPFRelation.FOOTNOTE;
            int i = getRelationIndex(relation);
            XWPFFootnotes wrapper = (XWPFFootnotes) createRelationship(relation, XWPFFactory.getInstance(), i);
            wrapper.setFootnotes(footnotesDoc.addNewFootnotes());
            wrapper.setIdManager(this.footnoteIdManager);
            this.footnotes = wrapper;
        }
        return this.footnotes;
    }

    @Internal
    public XWPFFootnote addFootnote(CTFtnEdn note) {
        return this.footnotes.addFootnote(note);
    }

    @Internal
    public XWPFEndnote addEndnote(CTFtnEdn note) {
        XWPFEndnote endnote = new XWPFEndnote(this, note);
        this.endnotes.addEndnote(note);
        return endnote;
    }

    public boolean removeBodyElement(int pos) {
        if (pos >= 0 && pos < this.bodyElements.size()) {
            BodyElementType type = this.bodyElements.get(pos).getElementType();
            if (type == BodyElementType.TABLE) {
                int tablePos = getTablePos(pos);
                this.tables.remove(tablePos);
                this.ctDocument.getBody().removeTbl(tablePos);
            }
            if (type == BodyElementType.PARAGRAPH) {
                int paraPos = getParagraphPos(pos);
                this.paragraphs.remove(paraPos);
                this.ctDocument.getBody().removeP(paraPos);
            }
            this.bodyElements.remove(pos);
            return true;
        }
        return false;
    }

    public void setParagraph(XWPFParagraph paragraph, int pos) {
        this.paragraphs.set(pos, paragraph);
        this.ctDocument.getBody().setPArray(pos, paragraph.getCTP());
    }

    public XWPFParagraph getLastParagraph() {
        int lastPos = this.paragraphs.size() - 1;
        return this.paragraphs.get(lastPos);
    }

    public XWPFTable createTable() {
        XWPFTable table = new XWPFTable(this.ctDocument.getBody().addNewTbl(), this);
        this.bodyElements.add(table);
        this.tables.add(table);
        return table;
    }

    public XWPFTable createTable(int rows, int cols) {
        XWPFTable table = new XWPFTable(this.ctDocument.getBody().addNewTbl(), this, rows, cols);
        this.bodyElements.add(table);
        this.tables.add(table);
        return table;
    }

    public void createTOC() {
        CTSdtBlock block = getDocument().getBody().addNewSdt();
        TOC toc = new TOC(block);
        for (XWPFParagraph par : this.paragraphs) {
            String parStyle = par.getStyle();
            if (parStyle != null && parStyle.startsWith("Heading")) {
                try {
                    int level = Integer.parseInt(parStyle.substring("Heading".length()));
                    toc.addRow(level, par.getText(), 1, "112723803");
                } catch (NumberFormatException e) {
                    LOG.atError().withThrowable(e).log("can't format number in TOC heading");
                }
            }
        }
    }

    public void setTable(int pos, XWPFTable table) {
        this.tables.set(pos, table);
        this.ctDocument.getBody().setTblArray(pos, table.getCTTbl());
    }

    public boolean isEnforcedProtection() {
        return this.settings.isEnforcedWith();
    }

    public boolean isEnforcedReadonlyProtection() {
        return this.settings.isEnforcedWith(STDocProtect.READ_ONLY);
    }

    public boolean isEnforcedFillingFormsProtection() {
        return this.settings.isEnforcedWith(STDocProtect.FORMS);
    }

    public boolean isEnforcedCommentsProtection() {
        return this.settings.isEnforcedWith(STDocProtect.COMMENTS);
    }

    public boolean isEnforcedTrackedChangesProtection() {
        return this.settings.isEnforcedWith(STDocProtect.TRACKED_CHANGES);
    }

    public boolean isEnforcedUpdateFields() {
        return this.settings.isUpdateFields();
    }

    public void enforceReadonlyProtection() {
        this.settings.setEnforcementEditValue(STDocProtect.READ_ONLY);
    }

    public void enforceReadonlyProtection(String password, HashAlgorithm hashAlgo) {
        this.settings.setEnforcementEditValue(STDocProtect.READ_ONLY, password, hashAlgo);
    }

    public void enforceFillingFormsProtection() {
        this.settings.setEnforcementEditValue(STDocProtect.FORMS);
    }

    public void enforceFillingFormsProtection(String password, HashAlgorithm hashAlgo) {
        this.settings.setEnforcementEditValue(STDocProtect.FORMS, password, hashAlgo);
    }

    public void enforceCommentsProtection() {
        this.settings.setEnforcementEditValue(STDocProtect.COMMENTS);
    }

    public void enforceCommentsProtection(String password, HashAlgorithm hashAlgo) {
        this.settings.setEnforcementEditValue(STDocProtect.COMMENTS, password, hashAlgo);
    }

    public void enforceTrackedChangesProtection() {
        this.settings.setEnforcementEditValue(STDocProtect.TRACKED_CHANGES);
    }

    public void enforceTrackedChangesProtection(String password, HashAlgorithm hashAlgo) {
        this.settings.setEnforcementEditValue(STDocProtect.TRACKED_CHANGES, password, hashAlgo);
    }

    public boolean validateProtectionPassword(String password) {
        return this.settings.validateProtectionPassword(password);
    }

    public void removeProtectionEnforcement() {
        this.settings.removeEnforcement();
    }

    public void enforceUpdateFields() {
        this.settings.setUpdateFields();
    }

    public boolean isTrackRevisions() {
        return this.settings.isTrackRevisions();
    }

    public void setTrackRevisions(boolean enable) {
        this.settings.setTrackRevisions(enable);
    }

    public long getZoomPercent() {
        return this.settings.getZoomPercent();
    }

    public void setZoomPercent(long zoomPercent) {
        this.settings.setZoomPercent(zoomPercent);
    }

    public boolean getEvenAndOddHeadings() {
        return this.settings.getEvenAndOddHeadings();
    }

    public void setEvenAndOddHeadings(boolean enable) {
        this.settings.setEvenAndOddHeadings(enable);
    }

    public boolean getMirrorMargins() {
        return this.settings.getMirrorMargins();
    }

    public void setMirrorMargins(boolean enable) {
        this.settings.setMirrorMargins(enable);
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public void insertTable(int pos, XWPFTable table) {
        this.bodyElements.add(pos, table);
        int i = 0;
        for (CTTbl tbl : this.ctDocument.getBody().getTblArray()) {
            if (tbl == table.getCTTbl()) {
                break;
            }
            i++;
        }
        this.tables.add(i, table);
    }

    public List<XWPFPictureData> getAllPictures() {
        return Collections.unmodifiableList(this.pictures);
    }

    public List<XWPFPictureData> getAllPackagePictures() {
        List<XWPFPictureData> result = new ArrayList<>();
        Collection<List<XWPFPictureData>> values = this.packagePictures.values();
        for (List<XWPFPictureData> list : values) {
            result.addAll(list);
        }
        return Collections.unmodifiableList(result);
    }

    public XWPFSettings getSettings() {
        return this.settings;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$registerPackagePictureData$0(Long k) {
        return new ArrayList(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerPackagePictureData(XWPFPictureData picData) {
        List<XWPFPictureData> list = this.packagePictures.computeIfAbsent(picData.getChecksum(), new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFDocument$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XWPFDocument.lambda$registerPackagePictureData$0((Long) obj);
            }
        });
        if (!list.contains(picData)) {
            list.add(picData);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XWPFPictureData findPackagePictureData(byte[] pictureData) {
        long checksum = IOUtils.calculateChecksum(pictureData);
        XWPFPictureData xwpfPicData = null;
        List<XWPFPictureData> xwpfPicDataList = this.packagePictures.get(Long.valueOf(checksum));
        if (xwpfPicDataList != null) {
            Iterator<XWPFPictureData> iter = xwpfPicDataList.iterator();
            while (iter.hasNext() && xwpfPicData == null) {
                XWPFPictureData curElem = iter.next();
                if (Arrays.equals(pictureData, curElem.getData())) {
                    xwpfPicData = curElem;
                }
            }
        }
        return xwpfPicData;
    }

    public String addPictureData(byte[] pictureData, int format) throws InvalidFormatException {
        return addPictureData(pictureData, PictureType.findByOoxmlId(format));
    }

    public String addPictureData(byte[] pictureData, PictureType pictureType) throws InvalidFormatException {
        if (pictureType == null) {
            throw new InvalidFormatException("pictureType is not supported");
        }
        XWPFPictureData xwpfPicData = findPackagePictureData(pictureData);
        POIXMLRelation relDesc = XWPFPictureData.RELATIONS[pictureType.ooxmlId];
        if (xwpfPicData == null) {
            int idx = getNextPicNameNumber(pictureType);
            XWPFPictureData xwpfPicData2 = (XWPFPictureData) createRelationship(relDesc, XWPFFactory.getInstance(), idx);
            PackagePart picDataPart = xwpfPicData2.getPackagePart();
            try {
                OutputStream out = picDataPart.getOutputStream();
                try {
                    out.write(pictureData);
                    if (out != null) {
                        out.close();
                    }
                    registerPackagePictureData(xwpfPicData2);
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
                return rp.getRelationship().getId();
            }
            return getRelationId(xwpfPicData);
        }
    }

    public String addPictureData(InputStream is, int format) throws InvalidFormatException {
        try {
            byte[] data = IOUtils.toByteArrayWithMaxLength(is, XWPFPictureData.getMaxImageSize());
            return addPictureData(data, format);
        } catch (IOException e) {
            throw new POIXMLException(e);
        }
    }

    public String addPictureData(InputStream is, PictureType pictureType) throws InvalidFormatException {
        try {
            byte[] data = IOUtils.toByteArrayWithMaxLength(is, XWPFPictureData.getMaxImageSize());
            return addPictureData(data, pictureType);
        } catch (IOException e) {
            throw new POIXMLException(e);
        }
    }

    @Removal(version = "7.0.0")
    @Deprecated
    public int getNextPicNameNumber(int format) throws InvalidFormatException {
        return getNextPicNameNumber(PictureType.findByOoxmlId(format));
    }

    public int getNextPicNameNumber(PictureType pictureType) throws InvalidFormatException {
        if (pictureType == null) {
            throw new InvalidFormatException("pictureType is not supported");
        }
        int img = getAllPackagePictures().size() + 1;
        String proposal = XWPFPictureData.RELATIONS[pictureType.ooxmlId].getFileName(img);
        PackagePartName createPartName = PackagingURIHelper.createPartName(proposal);
        while (getPackage().getPart(createPartName) != null) {
            img++;
            String proposal2 = XWPFPictureData.RELATIONS[pictureType.ooxmlId].getFileName(img);
            createPartName = PackagingURIHelper.createPartName(proposal2);
        }
        return img;
    }

    public XWPFPictureData getPictureDataByID(String blipID) {
        POIXMLDocumentPart relatedPart = getRelationById(blipID);
        if (relatedPart instanceof XWPFPictureData) {
            return (XWPFPictureData) relatedPart;
        }
        return null;
    }

    public XWPFNumbering getNumbering() {
        return this.numbering;
    }

    public XWPFStyles getStyles() {
        return this.styles;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFParagraph getParagraph(CTP p) {
        for (XWPFParagraph paragraph : this.paragraphs) {
            if (paragraph.getCTP() == p) {
                return paragraph;
            }
        }
        return null;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFTable getTable(CTTbl ctTbl) {
        for (int i = 0; i < this.tables.size(); i++) {
            if (getTables().get(i).getCTTbl() == ctTbl) {
                return getTables().get(i);
            }
        }
        return null;
    }

    public Iterator<XWPFTable> getTablesIterator() {
        return this.tables.iterator();
    }

    public Spliterator<XWPFTable> getTablesSpliterator() {
        return this.tables.spliterator();
    }

    public Iterator<XWPFParagraph> getParagraphsIterator() {
        return this.paragraphs.iterator();
    }

    public Spliterator<XWPFParagraph> getParagraphsSpliterator() {
        return this.paragraphs.spliterator();
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFParagraph getParagraphArray(int pos) {
        if (pos >= 0 && pos < this.paragraphs.size()) {
            return this.paragraphs.get(pos);
        }
        return null;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public POIXMLDocumentPart getPart() {
        return this;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public BodyType getPartType() {
        return BodyType.DOCUMENT;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFTableCell getTableCell(CTTc cell) {
        XWPFTableRow tableRow;
        XmlCursor cursor = cell.newCursor();
        try {
            cursor.toParent();
            XmlObject o = cursor.getObject();
            if (o instanceof CTRow) {
                CTRow row = (CTRow) o;
                cursor.toParent();
                XmlObject o2 = cursor.getObject();
                if (cursor != null) {
                    cursor.close();
                }
                if (!(o2 instanceof CTTbl)) {
                    return null;
                }
                CTTbl tbl = (CTTbl) o2;
                XWPFTable table = getTable(tbl);
                if (table == null || (tableRow = table.getRow(row)) == null) {
                    return null;
                }
                return tableRow.getTableCell(cell);
            }
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFDocument getXWPFDocument() {
        return this;
    }

    public XWPFChart createChart() throws InvalidFormatException, IOException {
        return createChart(500000, 500000);
    }

    public XWPFChart createChart(int width, int height) throws InvalidFormatException, IOException {
        return createChart(createParagraph().createRun(), width, height);
    }

    public XWPFChart createChart(XWPFRun run, int width, int height) throws InvalidFormatException, IOException {
        int chartNumber = getNextPartNumber(XWPFRelation.CHART, this.charts.size() + 1);
        POIXMLDocumentPart.RelationPart rp = createRelationship(XWPFRelation.CHART, XWPFFactory.getInstance(), chartNumber, false);
        XWPFChart xwpfChart = (XWPFChart) rp.getDocumentPart();
        xwpfChart.setChartIndex(chartNumber);
        xwpfChart.attach(rp.getRelationship().getId(), run);
        xwpfChart.setChartBoundingBox(width, height);
        this.charts.add(xwpfChart);
        return xwpfChart;
    }

    public XWPFFootnote createFootnote() {
        XWPFFootnotes footnotes = createFootnotes();
        return footnotes.createFootnote();
    }

    public boolean removeFootnote(int pos) {
        if (this.footnotes != null) {
            return this.footnotes.removeFootnote(pos);
        }
        return false;
    }

    public XWPFEndnote createEndnote() {
        XWPFEndnotes endnotes = createEndnotes();
        return endnotes.createEndnote();
    }

    public XWPFEndnotes createEndnotes() {
        if (this.endnotes == null) {
            EndnotesDocument endnotesDoc = EndnotesDocument.Factory.newInstance();
            XWPFRelation relation = XWPFRelation.ENDNOTE;
            int i = getRelationIndex(relation);
            XWPFEndnotes wrapper = (XWPFEndnotes) createRelationship(relation, XWPFFactory.getInstance(), i);
            wrapper.setEndnotes(endnotesDoc.addNewEndnotes());
            wrapper.setIdManager(this.footnoteIdManager);
            this.endnotes = wrapper;
        }
        return this.endnotes;
    }

    public List<XWPFEndnote> getEndnotes() {
        if (this.endnotes == null) {
            return Collections.emptyList();
        }
        return this.endnotes.getEndnotesList();
    }

    public boolean removeEndnote(int pos) {
        if (this.endnotes != null) {
            return this.endnotes.removeEndnote(pos);
        }
        return false;
    }
}
