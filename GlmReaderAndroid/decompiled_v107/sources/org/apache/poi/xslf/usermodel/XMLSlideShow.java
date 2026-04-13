package org.apache.poi.xslf.usermodel;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.OptionalLong;
import java.util.function.Consumer;
import java.util.function.ToLongFunction;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.POIException;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.extractor.POIXMLPropertiesTextExtractor;
import org.apache.poi.ooxml.util.PackageHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.Units;
import org.apache.poi.util.XMLHelper;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMasterIdListEntry;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdListEntry;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterIdListEntry;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize;
import org.openxmlformats.schemas.presentationml.x2006.main.PresentationDocument;

/* loaded from: classes10.dex */
public class XMLSlideShow extends POIXMLDocument implements SlideShow<XSLFShape, XSLFTextParagraph> {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 1000000;
    private static final int MAX_NODE_DEPTH = 1000;
    private final List<XSLFChart> _charts;
    private XSLFCommentAuthors _commentAuthors;
    private final List<XSLFSlideMaster> _masters;
    private XSLFNotesMaster _notesMaster;
    private final List<XSLFPictureData> _pictures;
    private CTPresentation _presentation;
    private final List<XSLFSlide> _slides;
    private XSLFTableStyles _tableStyles;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XMLSlideShow.class);
    private static int MAX_RECORD_LENGTH = 1000000;
    private static final Pattern GET_ALL_EMBEDDED_PARTS_PATTERN = Pattern.compile("/ppt/embeddings/.*?");
    private static final Pattern GET_PICTURE_DATA_PATTERN = Pattern.compile("/ppt/media/.*?");

    public static void setMaxRecordLength(int length) {
        MAX_RECORD_LENGTH = length;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public XMLSlideShow() {
        this(empty());
    }

    public XMLSlideShow(OPCPackage pkg) {
        super(pkg);
        this._slides = new ArrayList();
        this._masters = new ArrayList();
        this._pictures = new ArrayList();
        this._charts = new ArrayList();
        try {
            if (getCorePart().getContentType().equals(XSLFRelation.THEME_MANAGER.getContentType())) {
                rebase(getPackage());
            }
            load(XSLFFactory.getInstance());
        } catch (Exception e) {
            throw new POIXMLException(e);
        }
    }

    public XMLSlideShow(InputStream stream) throws IOException {
        this(stream, true);
    }

    public XMLSlideShow(InputStream stream, boolean closeStream) throws IOException {
        this(PackageHelper.open(stream, closeStream));
    }

    static OPCPackage empty() {
        InputStream is = XMLSlideShow.class.getResourceAsStream("empty.pptx");
        try {
            if (is == null) {
                throw new POIXMLException("Missing resource 'empty.pptx'");
            }
            try {
                return OPCPackage.open(is);
            } catch (Exception e) {
                throw new POIXMLException(e);
            }
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() throws IOException {
        try {
            InputStream stream = getCorePart().getInputStream();
            try {
                PresentationDocument doc = PresentationDocument.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                this._presentation = doc.getPresentation();
                if (stream != null) {
                    stream.close();
                }
                int nodeDepth = XMLHelper.getDepthOfChildNodes(this._presentation.getDomNode(), 1000);
                if (nodeDepth > 1000) {
                    throw new IOException(String.format(Locale.ROOT, "The document is too complex, it has a node depth of %s, which exceeds the maximum allowed of %s", Integer.valueOf(nodeDepth), 1000));
                }
                final Map<String, XSLFSlideMaster> masterMap = new HashMap<>();
                final Map<String, XSLFSlide> shIdMap = new HashMap<>();
                Map<String, XSLFChart> chartMap = new HashMap<>();
                for (POIXMLDocumentPart.RelationPart rp : getRelationParts()) {
                    POIXMLDocumentPart p = rp.getDocumentPart();
                    if (p instanceof XSLFSlide) {
                        shIdMap.put(rp.getRelationship().getId(), (XSLFSlide) p);
                        for (POIXMLDocumentPart c : p.getRelations()) {
                            if (c instanceof XSLFChart) {
                                chartMap.put(c.getPackagePart().getPartName().getName(), (XSLFChart) c);
                            }
                        }
                    } else if (p instanceof XSLFSlideMaster) {
                        masterMap.put(getRelationId(p), (XSLFSlideMaster) p);
                    } else if (p instanceof XSLFTableStyles) {
                        this._tableStyles = (XSLFTableStyles) p;
                    } else if (p instanceof XSLFNotesMaster) {
                        this._notesMaster = (XSLFNotesMaster) p;
                    } else if (p instanceof XSLFCommentAuthors) {
                        this._commentAuthors = (XSLFCommentAuthors) p;
                    }
                }
                this._charts.clear();
                this._charts.addAll(chartMap.values());
                this._masters.clear();
                if (this._presentation.isSetSldMasterIdLst()) {
                    this._presentation.getSldMasterIdLst().getSldMasterIdList().forEach(new Consumer() { // from class: org.apache.poi.xslf.usermodel.XMLSlideShow$$ExternalSyntheticLambda2
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            XMLSlideShow.this.m2573x2c6a8ad0(masterMap, (CTSlideMasterIdListEntry) obj);
                        }
                    });
                }
                this._slides.clear();
                if (this._presentation.isSetSldIdLst()) {
                    this._presentation.getSldIdLst().getSldIdList().forEach(new Consumer() { // from class: org.apache.poi.xslf.usermodel.XMLSlideShow$$ExternalSyntheticLambda3
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            XMLSlideShow.this.m2574xef56f42f(shIdMap, (CTSlideIdListEntry) obj);
                        }
                    });
                }
            } finally {
            }
        } catch (POIException e) {
            throw new IOException(e);
        } catch (XmlException e2) {
            throw new POIXMLException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: lambda$onDocumentRead$0$org-apache-poi-xslf-usermodel-XMLSlideShow, reason: not valid java name */
    public /* synthetic */ void m2573x2c6a8ad0(Map masterMap, CTSlideMasterIdListEntry id) {
        this._masters.add(masterMap.get(id.getId2()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onDocumentRead$1$org-apache-poi-xslf-usermodel-XMLSlideShow, reason: not valid java name */
    public /* synthetic */ void m2574xef56f42f(Map shIdMap, CTSlideIdListEntry id) {
        XSLFSlide sh = (XSLFSlide) shIdMap.get(id.getId2());
        if (sh == null) {
            LOG.atWarn().log("Slide with r:id {} was defined, but didn't exist in package, skipping", Unbox.box(id.getId()));
        } else {
            this._slides.add(sh);
        }
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            this._presentation.save(out, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
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

    @Override // org.apache.poi.ooxml.POIXMLDocument
    public List<PackagePart> getAllEmbeddedParts() {
        return Collections.unmodifiableList(getPackage().getPartsByName(GET_ALL_EMBEDDED_PARTS_PATTERN));
    }

    @Override // org.apache.poi.sl.usermodel.SlideShow
    public List<XSLFPictureData> getPictureData() {
        if (this._pictures.isEmpty()) {
            getPackage().getPartsByName(GET_PICTURE_DATA_PATTERN).forEach(new Consumer() { // from class: org.apache.poi.xslf.usermodel.XMLSlideShow$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    XMLSlideShow.this.m2572x8ad15d0c((PackagePart) obj);
                }
            });
        }
        return Collections.unmodifiableList(this._pictures);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getPictureData$2$org-apache-poi-xslf-usermodel-XMLSlideShow, reason: not valid java name */
    public /* synthetic */ void m2572x8ad15d0c(PackagePart part) {
        XSLFPictureData pd = new XSLFPictureData(part);
        pd.setIndex(this._pictures.size());
        this._pictures.add(pd);
    }

    public XSLFSlide createSlide(XSLFSlideLayout layout) {
        CTSlideIdList slideList = this._presentation.isSetSldIdLst() ? this._presentation.getSldIdLst() : this._presentation.addNewSldIdLst();
        OptionalLong maxId = Stream.of((Object[]) slideList.getSldIdArray()).mapToLong(new ToLongFunction() { // from class: org.apache.poi.xslf.usermodel.XMLSlideShow$$ExternalSyntheticLambda1
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((CTSlideIdListEntry) obj).getId();
            }
        }).max();
        XSLFRelation relationType = XSLFRelation.SLIDE;
        int slideNumber = (int) (Math.max(maxId.orElse(0L), 255L) + 1);
        int cnt = findNextAvailableFileNameIndex(relationType);
        POIXMLDocumentPart.RelationPart rp = createRelationship(relationType, XSLFFactory.getInstance(), cnt, false);
        XSLFSlide slide = (XSLFSlide) rp.getDocumentPart();
        CTSlideIdListEntry slideId = slideList.addNewSldId();
        slideId.setId(slideNumber);
        slideId.setId2(rp.getRelationship().getId());
        layout.copyLayout(slide);
        slide.getPackagePart().clearRelationships();
        slide.addRelation(null, XSLFRelation.SLIDE_LAYOUT, layout);
        this._slides.add(slide);
        return slide;
    }

    private int findNextAvailableFileNameIndex(XSLFRelation relationType) {
        try {
            return getPackage().getUnusedPartIndex(relationType.getDefaultFileName());
        } catch (InvalidFormatException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // org.apache.poi.sl.usermodel.SlideShow
    public Slide<XSLFShape, XSLFTextParagraph> createSlide() {
        XSLFSlideMaster sm = this._masters.get(0);
        XSLFSlideLayout layout = sm.getLayout(SlideLayout.BLANK);
        if (layout == null) {
            LOG.atWarn().log("Blank layout was not found - defaulting to first slide layout in master");
            XSLFSlideLayout[] sl = sm.getSlideLayouts();
            if (sl.length == 0) {
                throw new POIXMLException("SlideMaster must contain a SlideLayout.");
            }
            layout = sl[0];
        }
        return createSlide(layout);
    }

    public XSLFChart createChart(XSLFSlide slide) {
        XSLFChart chart = createChart();
        slide.addRelation(null, XSLFRelation.CHART, chart);
        return chart;
    }

    public XSLFChart createChart() {
        int chartIdx = findNextAvailableFileNameIndex(XSLFRelation.CHART);
        XSLFChart chart = (XSLFChart) createRelationship(XSLFRelation.CHART, XSLFFactory.getInstance(), chartIdx, true).getDocumentPart();
        chart.setChartIndex(chartIdx);
        this._charts.add(chart);
        return chart;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.poi.xslf.usermodel.XSLFNotes] */
    public XSLFNotes getNotesSlide(XSLFSlide slide) {
        ?? notes = slide.getNotes();
        if (notes == 0) {
            XSLFNotes notesSlide = createNotesSlide(slide);
            return notesSlide;
        }
        return notes;
    }

    private XSLFNotes createNotesSlide(XSLFSlide slide) {
        if (this._notesMaster == null) {
            createNotesMaster();
        }
        XSLFRelation relationType = XSLFRelation.NOTES;
        int slideIndex = findNextAvailableFileNameIndex(relationType);
        XSLFNotes notesSlide = (XSLFNotes) createRelationship(relationType, XSLFFactory.getInstance(), slideIndex);
        slide.addRelation(null, relationType, notesSlide);
        notesSlide.addRelation(null, XSLFRelation.NOTES_MASTER, this._notesMaster);
        notesSlide.addRelation(null, XSLFRelation.SLIDE, slide);
        notesSlide.importContent(this._notesMaster);
        return notesSlide;
    }

    public void createNotesMaster() {
        POIXMLDocumentPart.RelationPart rp = createRelationship(XSLFRelation.NOTES_MASTER, XSLFFactory.getInstance(), 1, false);
        this._notesMaster = (XSLFNotesMaster) rp.getDocumentPart();
        CTNotesMasterIdList notesMasterIdList = this._presentation.addNewNotesMasterIdLst();
        CTNotesMasterIdListEntry notesMasterId = notesMasterIdList.addNewNotesMasterId();
        notesMasterId.setId(rp.getRelationship().getId());
        int themeIndex = 1;
        List<Integer> themeIndexList = new ArrayList<>();
        for (POIXMLDocumentPart p : getRelations()) {
            if (p instanceof XSLFTheme) {
                themeIndexList.add(XSLFRelation.THEME.getFileNameIndex(p));
            }
        }
        if (!themeIndexList.isEmpty()) {
            boolean found = false;
            for (int i = 1; i <= themeIndexList.size(); i++) {
                if (!themeIndexList.contains(Integer.valueOf(i))) {
                    found = true;
                    themeIndex = i;
                }
            }
            if (!found) {
                themeIndex = themeIndexList.size() + 1;
            }
        }
        XSLFTheme theme = (XSLFTheme) createRelationship(XSLFRelation.THEME, XSLFFactory.getInstance(), themeIndex);
        theme.importTheme(((XSLFSlide) getSlides().get(0)).getTheme());
        this._notesMaster.addRelation(null, XSLFRelation.THEME, theme);
    }

    public XSLFNotesMaster getNotesMaster() {
        return this._notesMaster;
    }

    @Override // org.apache.poi.sl.usermodel.SlideShow
    public List<? extends MasterSheet<XSLFShape, XSLFTextParagraph>> getSlideMasters() {
        return this._masters;
    }

    @Override // org.apache.poi.sl.usermodel.SlideShow
    public List<? extends Slide<XSLFShape, XSLFTextParagraph>> getSlides() {
        return this._slides;
    }

    public List<XSLFChart> getCharts() {
        return Collections.unmodifiableList(this._charts);
    }

    public XSLFCommentAuthors getCommentAuthors() {
        return this._commentAuthors;
    }

    public void setSlideOrder(XSLFSlide slide, int newIndex) {
        int oldIndex = this._slides.indexOf(slide);
        if (oldIndex == -1) {
            throw new IllegalArgumentException("Slide not found");
        }
        if (oldIndex == newIndex) {
            return;
        }
        this._slides.add(newIndex, this._slides.remove(oldIndex));
        CTSlideIdList sldIdLst = this._presentation.getSldIdLst();
        CTSlideIdListEntry[] entries = sldIdLst.getSldIdArray();
        CTSlideIdListEntry oldEntry = entries[oldIndex];
        if (oldIndex < newIndex) {
            System.arraycopy(entries, oldIndex + 1, entries, oldIndex, newIndex - oldIndex);
        } else {
            System.arraycopy(entries, newIndex, entries, newIndex + 1, oldIndex - newIndex);
        }
        entries[newIndex] = oldEntry;
        sldIdLst.setSldIdArray(entries);
    }

    public XSLFSlide removeSlide(int index) {
        XSLFSlide slide = this._slides.remove(index);
        removeRelation(slide);
        this._presentation.getSldIdLst().removeSldId(index);
        for (POIXMLDocumentPart p : slide.getRelations()) {
            if (p instanceof XSLFChart) {
                XSLFChart chart = (XSLFChart) p;
                slide.removeChartRelation(chart);
                this._charts.remove(chart);
            } else if (p instanceof XSLFSlideLayout) {
                XSLFSlideLayout layout = (XSLFSlideLayout) p;
                slide.removeLayoutRelation(layout);
            } else if (p instanceof XSLFNotes) {
                POIXMLDocumentPart notes = slide.removeNotes(this._notesMaster);
                removeRelation(notes);
            } else if (p instanceof XSLFPictureData) {
                XSLFPictureData picture = (XSLFPictureData) p;
                removePictureRelations(slide, picture);
                this._pictures.remove(picture);
            }
        }
        return slide;
    }

    private void removePictureRelations(XSLFSlide slide, XSLFPictureData picture) {
        removePictureRelations(slide, slide, picture);
    }

    private void removePictureRelations(XSLFSlide slide, XSLFShapeContainer container, XSLFPictureData picture) {
        for (XSLFShape shape : container.getShapes()) {
            if (shape instanceof XSLFGroupShape) {
                removePictureRelations(slide, (XSLFGroupShape) shape, picture);
            }
            if (shape instanceof XSLFPictureShape) {
                XSLFPictureShape pic = (XSLFPictureShape) shape;
                if (pic.getPictureData() == picture) {
                    slide.removePictureRelation(pic);
                }
            }
        }
    }

    @Override // org.apache.poi.sl.usermodel.SlideShow
    public Dimension getPageSize() {
        CTSlideSize sz = this._presentation.getSldSz();
        int cx = sz.getCx();
        int cy = sz.getCy();
        return new Dimension((int) Units.toPoints(cx), (int) Units.toPoints(cy));
    }

    @Override // org.apache.poi.sl.usermodel.SlideShow
    public void setPageSize(Dimension pgSize) {
        CTSlideSize sz = CTSlideSize.Factory.newInstance();
        sz.setCx(Units.toEMU(pgSize.getWidth()));
        sz.setCy(Units.toEMU(pgSize.getHeight()));
        this._presentation.setSldSz(sz);
    }

    @Internal
    public CTPresentation getCTPresentation() {
        return this._presentation;
    }

    @Override // org.apache.poi.sl.usermodel.SlideShow
    public XSLFPictureData addPicture(byte[] pictureData, PictureData.PictureType format) {
        int imageNumber;
        XSLFPictureData img = findPictureData(pictureData);
        if (img != null) {
            return img;
        }
        XSLFRelation relType = XSLFPictureData.getRelationForType(format);
        if (relType == null) {
            throw new IllegalArgumentException("Picture type " + format + " is not supported.");
        }
        try {
            imageNumber = getPackage().getUnusedPartIndex("/ppt/media/image#\\..+");
        } catch (InvalidFormatException e) {
            imageNumber = this._pictures.size() + 1;
        }
        XSLFPictureData img2 = (XSLFPictureData) createRelationship(relType, XSLFFactory.getInstance(), imageNumber, true).getDocumentPart();
        img2.setIndex(this._pictures.size());
        this._pictures.add(img2);
        try {
            OutputStream out = img2.getPackagePart().getOutputStream();
            try {
                out.write(pictureData);
                if (out != null) {
                    out.close();
                }
                return img2;
            } finally {
            }
        } catch (IOException e2) {
            throw new POIXMLException(e2);
        }
    }

    @Override // org.apache.poi.sl.usermodel.SlideShow
    public XSLFPictureData addPicture(InputStream is, PictureData.PictureType format) throws IOException {
        return addPicture(IOUtils.toByteArrayWithMaxLength(is, XSLFPictureData.getMaxImageSize()), format);
    }

    @Override // org.apache.poi.sl.usermodel.SlideShow
    public XSLFPictureData addPicture(File pict, PictureData.PictureType format) throws IOException {
        byte[] data = IOUtils.safelyAllocate(pict.length(), MAX_RECORD_LENGTH);
        InputStream is = Files.newInputStream(pict.toPath(), new OpenOption[0]);
        try {
            IOUtils.readFully(is, data);
            if (is != null) {
                is.close();
            }
            return addPicture(data, format);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.poi.sl.usermodel.SlideShow
    public XSLFPictureData findPictureData(byte[] pictureData) {
        long checksum = IOUtils.calculateChecksum(pictureData);
        byte[] cs = new byte[8];
        LittleEndian.putLong(cs, 0, checksum);
        for (XSLFPictureData pic : getPictureData()) {
            if (Arrays.equals(pic.getChecksum(), cs)) {
                return pic;
            }
        }
        return null;
    }

    public XSLFSlideLayout findLayout(String name) {
        Iterator<? extends MasterSheet<XSLFShape, XSLFTextParagraph>> it = getSlideMasters().iterator();
        while (it.hasNext()) {
            XSLFSlideMaster master = (XSLFSlideMaster) it.next();
            XSLFSlideLayout layout = master.getLayout(name);
            if (layout != null) {
                return layout;
            }
        }
        return null;
    }

    public XSLFTableStyles getTableStyles() {
        return this._tableStyles;
    }

    @Override // org.apache.poi.sl.usermodel.SlideShow
    @NotImplemented
    public MasterSheet<XSLFShape, XSLFTextParagraph> createMasterSheet() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.poi.sl.usermodel.SlideShow
    public POIXMLPropertiesTextExtractor getMetadataTextExtractor() {
        return new POIXMLPropertiesTextExtractor(this);
    }

    @Override // org.apache.poi.sl.usermodel.SlideShow
    public Object getPersistDocument() {
        return this;
    }

    @Override // org.apache.poi.sl.usermodel.SlideShow
    public XSLFFontInfo addFont(InputStream fontStream) throws IOException {
        return XSLFFontInfo.addFontToSlideShow(this, fontStream);
    }

    @Override // org.apache.poi.sl.usermodel.SlideShow
    public List<XSLFFontInfo> getFonts() {
        return XSLFFontInfo.getFonts(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String importBlip(String blipId, POIXMLDocumentPart parent, POIXMLDocumentPart target) {
        XSLFPictureData pictureData;
        OPCPackage targetPackage = target.getPackagePart().getPackage();
        if (targetPackage != getPackage()) {
            throw new IllegalStateException("the target document part is not a child of this package");
        }
        POIXMLDocumentPart docPart = parent.getRelationPartById(blipId).getDocumentPart();
        if (docPart instanceof XSLFPictureData) {
            XSLFPictureData parData = (XSLFPictureData) docPart;
            if (targetPackage == parent.getPackagePart().getPackage()) {
                pictureData = parData;
            } else {
                pictureData = addPicture(parData.getData(), parData.getType());
            }
            POIXMLDocumentPart.RelationPart rp = target.addRelation(null, XSLFRelation.IMAGES, pictureData);
            return rp.getRelationship().getId();
        }
        throw new IllegalStateException("cannot import blip " + blipId + " - its document part is not XSLFPictureData");
    }
}
