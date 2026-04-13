package org.apache.poi.xssf.usermodel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.regex.Pattern;
import javax.xml.namespace.QName;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.POIException;
import org.apache.poi.hpsf.ClassIDPredefined;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.HyperlinkRelationship;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.ReferenceRelationship;
import org.apache.poi.ooxml.util.PackageHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.Ole10Native;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.ss.formula.udf.AggregatingUDFFinder;
import org.apache.poi.ss.formula.udf.IndexedUDFFinder;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.CellReferenceType;
import org.apache.poi.ss.usermodel.Date1904Support;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetVisibility;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.Removal;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.XLSBUnsupportedException;
import org.apache.poi.xssf.model.CalculationChain;
import org.apache.poi.xssf.model.ExternalLinksTable;
import org.apache.poi.xssf.model.MapInfo;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.model.ThemesTable;
import org.apache.poi.xssf.usermodel.helpers.XSSFFormulaUtils;
import org.apache.poi.xssf.usermodel.helpers.XSSFPasswordHelper;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookView;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReference;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCache;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbookPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbookProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCalcMode;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STRefMode;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSheetState;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.WorkbookDocument;

/* loaded from: classes10.dex */
public class XSSFWorkbook extends POIXMLDocument implements Workbook, Date1904Support {
    private static final Pattern COMMA_PATTERN = Pattern.compile(CollectionUtils.COMMA);
    private static final Pattern GET_ALL_PICTURES_PATTERN = Pattern.compile("/xl/media/.*?");
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XSSFWorkbook.class);
    private static final int MAX_NODE_DEPTH = 1000;
    public static final int PICTURE_TYPE_BMP = 11;
    public static final int PICTURE_TYPE_EPS = 10;
    public static final int PICTURE_TYPE_GIF = 8;
    public static final int PICTURE_TYPE_TIFF = 9;
    public static final int PICTURE_TYPE_WPG = 12;
    private XSSFCreationHelper _creationHelper;
    private Row.MissingCellPolicy _missingCellPolicy;
    private final IndexedUDFFinder _udfFinder;
    private CalculationChain calcChain;
    private boolean cellFormulaValidation;
    private List<ExternalLinksTable> externalLinks;
    private XSSFDataFormat formatter;
    private MapInfo mapInfo;
    private List<XSSFName> namedRanges;
    private ListValuedMap<String, XSSFName> namedRangesByName;
    private List<XSSFPictureData> pictures;
    private List<CTPivotCache> pivotCaches;
    private final List<XSSFPivotTable> pivotTables;
    private SharedStringsTable sharedStringSource;
    private List<XSSFSheet> sheets;
    private StylesTable stylesSource;
    private CTWorkbook workbook;
    private final XSSFFactory xssfFactory;

    public XSSFWorkbook() {
        this(XSSFWorkbookType.XLSX);
    }

    public XSSFWorkbook(XSSFFactory factory) {
        this(XSSFWorkbookType.XLSX, factory);
    }

    public XSSFWorkbook(XSSFWorkbookType workbookType) {
        this(workbookType, (XSSFFactory) null);
    }

    private XSSFWorkbook(XSSFWorkbookType workbookType, XSSFFactory factory) {
        super(newPackage(workbookType));
        this._udfFinder = new IndexedUDFFinder(AggregatingUDFFinder.DEFAULT);
        this._missingCellPolicy = Row.MissingCellPolicy.RETURN_NULL_AND_BLANK;
        this.cellFormulaValidation = true;
        this.pivotTables = new ArrayList();
        this.xssfFactory = factory == null ? XSSFFactory.getInstance() : factory;
        onWorkbookCreate();
    }

    public XSSFWorkbook(OPCPackage pkg) throws IOException {
        super(pkg);
        this._udfFinder = new IndexedUDFFinder(AggregatingUDFFinder.DEFAULT);
        this._missingCellPolicy = Row.MissingCellPolicy.RETURN_NULL_AND_BLANK;
        this.cellFormulaValidation = true;
        this.pivotTables = new ArrayList();
        this.xssfFactory = XSSFFactory.getInstance();
        beforeDocumentRead();
        load(this.xssfFactory);
        setBookViewsIfMissing();
    }

    public XSSFWorkbook(InputStream stream) throws IOException {
        this(stream, true);
    }

    public XSSFWorkbook(InputStream stream, boolean closeStream) throws IOException {
        this(PackageHelper.open(stream, closeStream));
    }

    public XSSFWorkbook(File file) throws IOException, InvalidFormatException {
        this(OPCPackage.open(file));
    }

    public XSSFWorkbook(String path) throws IOException {
        this(openPackage(path));
    }

    public XSSFWorkbook(PackagePart part) throws IOException {
        this(part.getInputStream(), true);
    }

    public XSSFFactory getXssfFactory() {
        return this.xssfFactory;
    }

    protected void beforeDocumentRead() {
        if (getCorePart().getContentType().equals(XSSFRelation.XLSB_BINARY_WORKBOOK.getContentType())) {
            throw new XLSBUnsupportedException();
        }
        this.pivotCaches = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() throws IOException {
        try {
            InputStream stream = getPackagePart().getInputStream();
            try {
                WorkbookDocument doc = WorkbookDocument.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                this.workbook = doc.getWorkbook();
                if (stream != null) {
                    stream.close();
                }
                int nodeDepth = XMLHelper.getDepthOfChildNodes(this.workbook.getDomNode(), 1000);
                if (nodeDepth > 1000) {
                    throw new IOException(String.format(Locale.ROOT, "The document is too complex, it has a node depth of %s, which exceeds the maximum allowed of %s", Integer.valueOf(nodeDepth), 1000));
                }
                ThemesTable theme = null;
                Map<String, XSSFSheet> shIdMap = new HashMap<>();
                Map<String, ExternalLinksTable> elIdMap = new HashMap<>();
                for (POIXMLDocumentPart.RelationPart rp : getRelationParts()) {
                    POIXMLDocumentPart p = rp.getDocumentPart();
                    if (p instanceof SharedStringsTable) {
                        this.sharedStringSource = (SharedStringsTable) p;
                    } else if (p instanceof StylesTable) {
                        this.stylesSource = (StylesTable) p;
                    } else if (p instanceof ThemesTable) {
                        theme = (ThemesTable) p;
                    } else if (p instanceof CalculationChain) {
                        this.calcChain = (CalculationChain) p;
                    } else if (p instanceof MapInfo) {
                        this.mapInfo = (MapInfo) p;
                    } else if (p instanceof XSSFSheet) {
                        shIdMap.put(rp.getRelationship().getId(), (XSSFSheet) p);
                    } else if (p instanceof ExternalLinksTable) {
                        elIdMap.put(rp.getRelationship().getId(), (ExternalLinksTable) p);
                    }
                }
                boolean packageReadOnly = getPackage().getPackageAccess() == PackageAccess.READ;
                if (this.stylesSource == null) {
                    if (packageReadOnly) {
                        this.stylesSource = new StylesTable();
                    } else {
                        this.stylesSource = (StylesTable) createRelationship(XSSFRelation.STYLES, this.xssfFactory);
                    }
                }
                this.stylesSource.setWorkbook(this);
                this.stylesSource.setTheme(theme);
                if (this.sharedStringSource == null) {
                    if (packageReadOnly) {
                        this.sharedStringSource = new SharedStringsTable();
                    } else {
                        List<PackagePart> matchingParts = getPackagePart().getPackage().getPartsByContentType(XSSFRelation.SHARED_STRINGS.getContentType());
                        if (matchingParts.isEmpty()) {
                            this.sharedStringSource = (SharedStringsTable) createRelationship(XSSFRelation.SHARED_STRINGS, this.xssfFactory);
                        } else {
                            this.sharedStringSource = new SharedStringsTable(matchingParts.get(0));
                        }
                    }
                }
                this.sheets = new ArrayList(shIdMap.size());
                if (this.workbook == null || this.workbook.getSheets() == null || this.workbook.getSheets().getSheetArray() == null) {
                    throw new POIXMLException("Cannot read a workbook without sheets");
                }
                for (CTSheet ctSheet : this.workbook.getSheets().getSheetArray()) {
                    parseSheet(shIdMap, ctSheet);
                }
                this.externalLinks = new ArrayList(elIdMap.size());
                if (this.workbook.isSetExternalReferences()) {
                    for (CTExternalReference er : this.workbook.getExternalReferences().getExternalReferenceArray()) {
                        ExternalLinksTable el = elIdMap.get(er.getId());
                        if (el == null) {
                            LOG.atWarn().log("ExternalLinksTable with r:id {} was defined, but didn't exist in package, skipping", er.getId());
                        } else {
                            this.externalLinks.add(el);
                        }
                    }
                }
                reprocessNamedRanges();
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
        } catch (POIException e) {
            throw new IOException(e);
        } catch (XmlException e2) {
            throw new POIXMLException(e2);
        }
    }

    public void parseSheet(Map<String, XSSFSheet> shIdMap, CTSheet ctSheet) {
        XSSFSheet sh = shIdMap.get(ctSheet.getId());
        if (sh == null) {
            LOG.atWarn().log("Sheet with name {} and r:id {} was defined, but didn't exist in package, skipping", ctSheet.getName(), ctSheet.getId());
            return;
        }
        sh.sheet = ctSheet;
        sh.onDocumentRead();
        this.sheets.add(sh);
    }

    private void onWorkbookCreate() {
        this.workbook = CTWorkbook.Factory.newInstance();
        CTWorkbookPr workbookPr = this.workbook.addNewWorkbookPr();
        workbookPr.setDate1904(false);
        setBookViewsIfMissing();
        this.workbook.addNewSheets();
        POIXMLProperties.ExtendedProperties expProps = getProperties().getExtendedProperties();
        expProps.getUnderlyingProperties().setApplication(POIXMLDocument.DOCUMENT_CREATOR);
        this.sharedStringSource = (SharedStringsTable) createRelationship(XSSFRelation.SHARED_STRINGS, this.xssfFactory);
        this.stylesSource = (StylesTable) createRelationship(XSSFRelation.STYLES, this.xssfFactory);
        this.stylesSource.setWorkbook(this);
        this.namedRanges = new ArrayList();
        this.namedRangesByName = new ArrayListValuedHashMap();
        this.sheets = new ArrayList();
        this.externalLinks = new ArrayList();
    }

    private void setBookViewsIfMissing() {
        if (!this.workbook.isSetBookViews()) {
            CTBookViews bvs = this.workbook.addNewBookViews();
            CTBookView bv = bvs.addNewWorkbookView();
            bv.setActiveTab(0L);
        }
    }

    protected static OPCPackage newPackage(XSSFWorkbookType workbookType) {
        OPCPackage pkg = null;
        try {
            pkg = OPCPackage.create(UnsynchronizedByteArrayOutputStream.builder().get());
            PackagePartName corePartName = PackagingURIHelper.createPartName(XSSFRelation.WORKBOOK.getDefaultFileName());
            pkg.addRelationship(corePartName, TargetMode.INTERNAL, PackageRelationshipTypes.CORE_DOCUMENT);
            pkg.createPart(corePartName, workbookType.getContentType());
            pkg.getPackageProperties().setCreatorProperty(POIXMLDocument.DOCUMENT_CREATOR);
            return pkg;
        } catch (Exception e) {
            IOUtils.closeQuietly(pkg);
            throw new POIXMLException(e);
        }
    }

    @Internal
    public CTWorkbook getCTWorkbook() {
        return this.workbook;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int addPicture(byte[] pictureData, int format) {
        int imageNumber = getAllPictures().size() + 1;
        XSSFPictureData img = (XSSFPictureData) createRelationship(XSSFPictureData.RELATIONS[format], this.xssfFactory, imageNumber, true).getDocumentPart();
        try {
            OutputStream out = img.getPackagePart().getOutputStream();
            try {
                out.write(pictureData);
                if (out != null) {
                    out.close();
                }
                this.pictures.add(img);
                return imageNumber - 1;
            } finally {
            }
        } catch (IOException e) {
            throw new POIXMLException(e);
        }
    }

    public int addPicture(InputStream is, int format) throws IOException {
        int imageNumber = getAllPictures().size() + 1;
        XSSFPictureData img = (XSSFPictureData) createRelationship(XSSFPictureData.RELATIONS[format], this.xssfFactory, imageNumber, true).getDocumentPart();
        OutputStream out = img.getPackagePart().getOutputStream();
        try {
            IOUtils.copy(is, out);
            if (out != null) {
                out.close();
            }
            this.pictures.add(img);
            return imageNumber - 1;
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

    @Override // org.apache.poi.ss.usermodel.Workbook
    public XSSFSheet cloneSheet(int sheetNum) {
        return cloneSheet(sheetNum, null);
    }

    @Override // org.apache.poi.ooxml.POIXMLDocument, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            super.close();
        } finally {
            IOUtils.closeQuietly(this.sharedStringSource);
        }
    }

    public XSSFSheet cloneSheet(int sheetNum, String newName) {
        String srcName;
        CTWorksheet ct;
        validateSheetIndex(sheetNum);
        XSSFSheet srcSheet = this.sheets.get(sheetNum);
        if (newName == null) {
            String srcName2 = srcSheet.getSheetName();
            srcName = getUniqueSheetName(srcName2);
        } else {
            validateSheetName(newName);
            srcName = newName;
        }
        XSSFSheet clonedSheet = createSheet(srcName);
        List<POIXMLDocumentPart.RelationPart> rels = srcSheet.getRelationParts();
        XSSFDrawing dg = null;
        for (POIXMLDocumentPart.RelationPart rp : rels) {
            POIXMLDocumentPart r = rp.getDocumentPart();
            if (r instanceof XSSFDrawing) {
                dg = (XSSFDrawing) r;
            } else {
                addRelation(rp, clonedSheet);
            }
        }
        List<ReferenceRelationship> referenceRelationships = srcSheet.getReferenceRelationships();
        for (ReferenceRelationship ref : referenceRelationships) {
            if (ref instanceof HyperlinkRelationship) {
                createHyperlink(ref.getUri(), ref.isExternal(), ref.getId());
            }
        }
        try {
            Iterator<PackageRelationship> it = srcSheet.getPackagePart().getRelationships().iterator();
            while (it.hasNext()) {
                PackageRelationship pr = it.next();
                if (pr.getTargetMode() == TargetMode.EXTERNAL) {
                    clonedSheet.getPackagePart().addExternalRelationship(pr.getTargetURI().toASCIIString(), pr.getRelationshipType(), pr.getId());
                }
            }
            try {
                UnsynchronizedByteArrayOutputStream out = UnsynchronizedByteArrayOutputStream.builder().get();
                try {
                    srcSheet.write(out);
                    InputStream bis = out.toInputStream();
                    try {
                        clonedSheet.read(bis);
                        if (bis != null) {
                            bis.close();
                        }
                        if (out != null) {
                            out.close();
                        }
                        CTWorksheet ct2 = clonedSheet.getCTWorksheet();
                        if (ct2.isSetLegacyDrawing()) {
                            LOG.atWarn().log("Cloning sheets with comments is not yet supported.");
                            ct2.unsetLegacyDrawing();
                        }
                        if (ct2.isSetPageSetup()) {
                            LOG.atWarn().log("Cloning sheets with page setup is not yet supported.");
                            ct2.unsetPageSetup();
                        }
                        clonedSheet.setSelected(false);
                        if (dg != null) {
                            if (ct2.isSetDrawing()) {
                                ct2.unsetDrawing();
                            }
                            XSSFDrawing clonedDg = clonedSheet.createDrawingPatriarch();
                            clonedDg.getCTDrawing().set(dg.getCTDrawing().copy());
                            XSSFDrawing drawingPatriarch = srcSheet.getDrawingPatriarch();
                            if (drawingPatriarch != null) {
                                List<POIXMLDocumentPart.RelationPart> srcRels = drawingPatriarch.getRelationParts();
                                for (POIXMLDocumentPart.RelationPart rp2 : srcRels) {
                                    POIXMLDocumentPart r2 = rp2.getDocumentPart();
                                    if (r2 instanceof XSSFChart) {
                                        POIXMLDocumentPart.RelationPart chartPart = clonedDg.createChartRelationPart();
                                        ct = ct2;
                                        XSSFChart chart = (XSSFChart) chartPart.getDocumentPart();
                                        chart.importContent((XSSFChart) r2);
                                        chart.replaceReferences(clonedSheet);
                                    } else {
                                        ct = ct2;
                                        addRelation(rp2, clonedDg);
                                    }
                                    ct2 = ct;
                                }
                                List<ReferenceRelationship> srcRefs = drawingPatriarch.getReferenceRelationships();
                                for (ReferenceRelationship ref2 : srcRefs) {
                                    if (ref2 instanceof HyperlinkRelationship) {
                                        clonedDg.createHyperlink(ref2.getUri(), ref2.isExternal(), ref2.getId());
                                    }
                                }
                            }
                        }
                        XSSFSheet.cloneTables(clonedSheet);
                        return clonedSheet;
                    } finally {
                    }
                } finally {
                }
            } catch (IOException e) {
                throw new POIXMLException("Failed to clone sheet", e);
            }
        } catch (InvalidFormatException e2) {
            throw new POIXMLException("Failed to clone sheet", e2);
        }
    }

    private static boolean addRelation(POIXMLDocumentPart.RelationPart rp, POIXMLDocumentPart target) {
        PackageRelationship rel = rp.getRelationship();
        if (rel.getTargetMode() == TargetMode.EXTERNAL) {
            target.getPackagePart().addRelationship(rel.getTargetURI(), rel.getTargetMode(), rel.getRelationshipType(), rel.getId());
            return true;
        }
        XSSFRelation xssfRel = XSSFRelation.getInstance(rel.getRelationshipType());
        if (xssfRel == null) {
            LOG.atWarn().log("Can't clone sheet relationship (some data will be lost in the cloned sheet) - unknown relation type found: {}", rel.getRelationshipType());
            return false;
        }
        target.addRelation(rel.getId(), xssfRel, rp.getDocumentPart());
        return true;
    }

    private String getUniqueSheetName(String srcName) {
        String name;
        int uniqueIndex = 2;
        String baseName = srcName;
        int bracketPos = srcName.lastIndexOf(40);
        if (bracketPos > 0 && srcName.endsWith(")")) {
            String suffix = srcName.substring(bracketPos + 1, srcName.length() - ")".length());
            try {
                uniqueIndex = Integer.parseInt(suffix.trim()) + 1;
                baseName = srcName.substring(0, bracketPos).trim();
            } catch (NumberFormatException e) {
            }
        }
        while (true) {
            int uniqueIndex2 = uniqueIndex + 1;
            String index = Integer.toString(uniqueIndex);
            if (baseName.length() + index.length() + 2 >= 31) {
                name = baseName.substring(0, (31 - index.length()) - 2) + "(" + index + ")";
            } else {
                name = baseName + " (" + index + ")";
            }
            if (getSheetIndex(name) == -1) {
                return name;
            }
            uniqueIndex = uniqueIndex2;
        }
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public XSSFCellStyle createCellStyle() {
        return this.stylesSource.createCellStyle();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public XSSFDataFormat createDataFormat() {
        if (this.formatter == null) {
            this.formatter = new XSSFDataFormat(this.stylesSource);
        }
        return this.formatter;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public XSSFFont createFont() {
        XSSFFont font = new XSSFFont();
        font.registerTo(this.stylesSource);
        return font;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public XSSFName createName() {
        CTDefinedName ctName = CTDefinedName.Factory.newInstance();
        ctName.setName("");
        return createAndStoreName(ctName);
    }

    private XSSFName createAndStoreName(CTDefinedName ctName) {
        XSSFName name = new XSSFName(ctName, this);
        this.namedRanges.add(name);
        this.namedRangesByName.put(ctName.getName() == null ? null : ctName.getName().toLowerCase(Locale.ENGLISH), name);
        return name;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public XSSFSheet createSheet() {
        String sheetname = "Sheet" + this.sheets.size();
        int idx = 0;
        while (getSheet(sheetname) != null) {
            sheetname = "Sheet" + idx;
            idx++;
        }
        return createSheet(sheetname);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public XSSFSheet createSheet(String sheetname) {
        if (sheetname == null) {
            throw new IllegalArgumentException("sheetName must not be null");
        }
        validateSheetName(sheetname);
        if (sheetname.length() > 31) {
            String trimmedSheetname = sheetname.substring(0, 31);
            LOG.atWarn().log("Sheet '{}' will be added with a trimmed name '{}' for MS Excel compliance.", sheetname, trimmedSheetname);
            sheetname = trimmedSheetname;
        }
        WorkbookUtil.validateSheetName(sheetname);
        CTSheet sheet = addSheet(sheetname);
        int sheetNumber = 1;
        loop0: while (true) {
            for (XSSFSheet sh : this.sheets) {
                sheetNumber = (int) Math.max(sh.sheet.getSheetId() + 1, sheetNumber);
            }
            String sheetName = XSSFRelation.WORKSHEET.getFileName(sheetNumber);
            for (POIXMLDocumentPart relation : getRelations()) {
                if (relation.getPackagePart() == null || !sheetName.equals(relation.getPackagePart().getPartName().getName())) {
                }
            }
            sheetNumber++;
        }
        POIXMLDocumentPart.RelationPart rp = createRelationship(XSSFRelation.WORKSHEET, this.xssfFactory, sheetNumber, false);
        XSSFSheet wrapper = (XSSFSheet) rp.getDocumentPart();
        wrapper.sheet = sheet;
        sheet.setId(rp.getRelationship().getId());
        sheet.setSheetId(sheetNumber);
        if (this.sheets.isEmpty()) {
            wrapper.setSelected(true);
        }
        this.sheets.add(wrapper);
        return wrapper;
    }

    private void validateSheetName(String sheetName) throws IllegalArgumentException {
        if (containsSheet(sheetName, this.sheets.size())) {
            throw new IllegalArgumentException("The workbook already contains a sheet named '" + sheetName + "'");
        }
    }

    protected XSSFDialogsheet createDialogsheet(String sheetname, CTDialogsheet dialogsheet) {
        XSSFSheet sheet = createSheet(sheetname);
        return new XSSFDialogsheet(sheet);
    }

    private CTSheet addSheet(String sheetname) {
        CTSheet sheet = this.workbook.getSheets().addNewSheet();
        sheet.setName(sheetname);
        return sheet;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public XSSFFont findFont(boolean bold, short color, short fontHeight, String name, boolean italic, boolean strikeout, short typeOffset, byte underline) {
        return this.stylesSource.findFont(bold, color, fontHeight, name, italic, strikeout, typeOffset, underline);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int getActiveSheetIndex() {
        return (int) this.workbook.getBookViews().getWorkbookViewArray(0).getActiveTab();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public List<XSSFPictureData> getAllPictures() {
        if (this.pictures == null) {
            List<PackagePart> mediaParts = getPackage().getPartsByName(GET_ALL_PICTURES_PATTERN);
            this.pictures = new ArrayList(mediaParts.size());
            for (PackagePart part : mediaParts) {
                this.pictures.add(new XSSFPictureData(part));
            }
        }
        return Collections.unmodifiableList(this.pictures);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public XSSFCellStyle getCellStyleAt(int idx) {
        return this.stylesSource.getStyleAt(idx);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public XSSFFont getFontAt(int idx) {
        return this.stylesSource.getFontAt(idx);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public XSSFName getName(String name) {
        Collection<XSSFName> list = getNames(name);
        if (list.isEmpty()) {
            return null;
        }
        return list.iterator().next();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public List<XSSFName> getNames(String name) {
        return Collections.unmodifiableList(this.namedRangesByName.get((ListValuedMap<String, XSSFName>) name.toLowerCase(Locale.ENGLISH)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public XSSFName getNameAt(int nameIndex) {
        int nNames = this.namedRanges.size();
        if (nNames < 1) {
            throw new IllegalStateException("There are no defined names in this workbook");
        }
        if (nameIndex < 0 || nameIndex > nNames) {
            throw new IllegalArgumentException("Specified name index " + nameIndex + " is outside the allowable range (0.." + (nNames - 1) + ").");
        }
        return this.namedRanges.get(nameIndex);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public List<XSSFName> getAllNames() {
        return Collections.unmodifiableList(this.namedRanges);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public int getNameIndex(String name) {
        XSSFName nm = getName(name);
        if (nm != null) {
            return this.namedRanges.indexOf(nm);
        }
        return -1;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int getNumCellStyles() {
        return this.stylesSource.getNumCellStyles();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int getNumberOfFonts() {
        return this.stylesSource.getFonts().size();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    @Removal(version = "6.0.0")
    @Deprecated
    public int getNumberOfFontsAsInt() {
        return getNumberOfFonts();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int getNumberOfNames() {
        return this.namedRanges.size();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int getNumberOfSheets() {
        return this.sheets.size();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public String getPrintArea(int sheetIndex) {
        XSSFName name = getBuiltInName(XSSFName.BUILTIN_PRINT_AREA, sheetIndex);
        if (name == null) {
            return null;
        }
        return name.getRefersToFormula();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public XSSFSheet getSheet(String name) {
        for (XSSFSheet sheet : this.sheets) {
            if (name.equalsIgnoreCase(sheet.getSheetName())) {
                return sheet;
            }
        }
        return null;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public XSSFSheet getSheetAt(int index) {
        validateSheetIndex(index);
        return this.sheets.get(index);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int getSheetIndex(String name) {
        int idx = 0;
        for (XSSFSheet sh : this.sheets) {
            if (name.equalsIgnoreCase(sh.getSheetName())) {
                return idx;
            }
            idx++;
        }
        return -1;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int getSheetIndex(Sheet sheet) {
        int idx = 0;
        for (XSSFSheet sh : this.sheets) {
            if (sh == sheet) {
                return idx;
            }
            idx++;
        }
        return -1;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public String getSheetName(int sheetIx) {
        validateSheetIndex(sheetIx);
        return this.sheets.get(sheetIx).getSheetName();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public Iterator<Sheet> sheetIterator() {
        return new SheetIterator();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook, java.lang.Iterable
    public Iterator<Sheet> iterator() {
        return sheetIterator();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook, java.lang.Iterable
    public Spliterator<Sheet> spliterator() {
        return this.sheets.spliterator();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public final class SheetIterator<T extends Sheet> implements Iterator<T> {
        private final Iterator<T> it;

        public SheetIterator() {
            this.it = XSSFWorkbook.this.sheets.iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.it.hasNext();
        }

        @Override // java.util.Iterator
        public T next() throws NoSuchElementException {
            return this.it.next();
        }

        @Override // java.util.Iterator
        public void remove() throws IllegalStateException {
            throw new UnsupportedOperationException("remove method not supported on XSSFWorkbook.iterator(). Use Sheet.removeSheetAt(int) instead.");
        }
    }

    public boolean isMacroEnabled() {
        return getPackagePart().getContentType().equals(XSSFRelation.MACROS_WORKBOOK.getContentType());
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void removeName(Name name) {
        if (!this.namedRangesByName.removeMapping(name.getNameName().toLowerCase(Locale.ENGLISH), name) || !this.namedRanges.remove(name)) {
            throw new IllegalArgumentException("Name was not found: " + name);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateName(XSSFName name, String oldName) {
        if (!this.namedRangesByName.removeMapping(oldName.toLowerCase(Locale.ENGLISH), name)) {
            throw new IllegalArgumentException("Name was not found: " + name);
        }
        this.namedRangesByName.put(name.getNameName().toLowerCase(Locale.ENGLISH), name);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void removePrintArea(int sheetIndex) {
        XSSFName name = getBuiltInName(XSSFName.BUILTIN_PRINT_AREA, sheetIndex);
        if (name != null) {
            removeName(name);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void removeSheetAt(int index) {
        validateSheetIndex(index);
        onSheetDelete(index);
        XSSFSheet sheet = getSheetAt(index);
        removeRelation(sheet);
        this.sheets.remove(index);
        if (this.sheets.isEmpty()) {
            return;
        }
        int newSheetIndex = index;
        if (newSheetIndex >= this.sheets.size()) {
            newSheetIndex = this.sheets.size() - 1;
        }
        int active = getActiveSheetIndex();
        if (active == index) {
            setActiveSheet(newSheetIndex);
        } else if (active > index) {
            setActiveSheet(active - 1);
        }
    }

    private void onSheetDelete(int index) {
        XSSFSheet sheet = getSheetAt(index);
        sheet.onSheetDelete();
        this.workbook.getSheets().removeSheet(index);
        if (this.calcChain != null) {
            removeRelation(this.calcChain);
            this.calcChain = null;
        }
        List<XSSFName> toRemove = new ArrayList<>();
        for (XSSFName nm : this.namedRanges) {
            CTDefinedName ct = nm.getCTName();
            if (ct.isSetLocalSheetId()) {
                if (ct.getLocalSheetId() == index) {
                    toRemove.add(nm);
                } else if (ct.getLocalSheetId() > index) {
                    ct.setLocalSheetId(ct.getLocalSheetId() - 1);
                }
            }
        }
        Iterator<XSSFName> it = toRemove.iterator();
        while (it.hasNext()) {
            removeName(it.next());
        }
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public Row.MissingCellPolicy getMissingCellPolicy() {
        return this._missingCellPolicy;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setMissingCellPolicy(Row.MissingCellPolicy missingCellPolicy) {
        this._missingCellPolicy = missingCellPolicy;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setActiveSheet(int index) {
        validateSheetIndex(index);
        for (CTBookView arrayBook : this.workbook.getBookViews().getWorkbookViewArray()) {
            arrayBook.setActiveTab(index);
        }
    }

    private void validateSheetIndex(int index) {
        int lastSheetIx = this.sheets.size() - 1;
        if (index < 0 || index > lastSheetIx) {
            String range = "(0.." + lastSheetIx + ")";
            if (lastSheetIx == -1) {
                range = "(no sheets)";
            }
            throw new IllegalArgumentException("Sheet index (" + index + ") is out of range " + range);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int getFirstVisibleTab() {
        CTBookViews bookViews = this.workbook.getBookViews();
        CTBookView bookView = bookViews.getWorkbookViewArray(0);
        return (short) bookView.getFirstSheet();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setFirstVisibleTab(int index) {
        CTBookViews bookViews = this.workbook.getBookViews();
        CTBookView bookView = bookViews.getWorkbookViewArray(0);
        bookView.setFirstSheet(index);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setPrintArea(int sheetIndex, String reference) {
        XSSFName name = getBuiltInName(XSSFName.BUILTIN_PRINT_AREA, sheetIndex);
        if (name == null) {
            name = createBuiltInName(XSSFName.BUILTIN_PRINT_AREA, sheetIndex);
        }
        String[] parts = COMMA_PATTERN.split(reference);
        StringBuilder sb = new StringBuilder(32);
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            SheetNameFormatter.appendFormat(sb, getSheetName(sheetIndex));
            sb.append('!');
            sb.append(parts[i]);
        }
        name.setRefersToFormula(sb.toString());
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setPrintArea(int sheetIndex, int startColumn, int endColumn, int startRow, int endRow) {
        String reference = getReferencePrintArea(getSheetName(sheetIndex), startColumn, endColumn, startRow, endRow);
        setPrintArea(sheetIndex, reference);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public CellReferenceType getCellReferenceType() {
        CTCalcPr calcPr = getCTWorkbook().getCalcPr();
        if (calcPr == null) {
            return CellReferenceType.UNKNOWN;
        }
        if (calcPr.getRefMode() == STRefMode.R_1_C_1) {
            return CellReferenceType.R1C1;
        }
        if (calcPr.getRefMode() == STRefMode.A_1) {
            return CellReferenceType.A1;
        }
        return CellReferenceType.UNKNOWN;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setCellReferenceType(CellReferenceType cellReferenceType) {
        CTCalcPr calcPr = getCTWorkbook().getCalcPr();
        if (cellReferenceType == CellReferenceType.UNKNOWN) {
            if (calcPr != null) {
                calcPr.unsetRefMode();
            }
        } else {
            if (calcPr == null) {
                calcPr = getCTWorkbook().addNewCalcPr();
            }
            STRefMode.Enum refMode = cellReferenceType == CellReferenceType.R1C1 ? STRefMode.R_1_C_1 : STRefMode.A_1;
            calcPr.setRefMode(refMode);
        }
    }

    private static String getReferencePrintArea(String sheetName, int startC, int endC, int startR, int endR) {
        CellReference colRef = new CellReference(sheetName, startR, startC, true, true);
        CellReference colRef2 = new CellReference(sheetName, endR, endC, true, true);
        return "$" + colRef.getCellRefParts()[2] + "$" + colRef.getCellRefParts()[1] + ":$" + colRef2.getCellRefParts()[2] + "$" + colRef2.getCellRefParts()[1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSSFName getBuiltInName(String builtInCode, int sheetNumber) {
        for (XSSFName name : this.namedRangesByName.get((ListValuedMap<String, XSSFName>) builtInCode.toLowerCase(Locale.ENGLISH))) {
            if (name.getSheetIndex() == sheetNumber) {
                return name;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSSFName createBuiltInName(String builtInName, int sheetNumber) {
        validateSheetIndex(sheetNumber);
        CTDefinedNames names = this.workbook.getDefinedNames() == null ? this.workbook.addNewDefinedNames() : this.workbook.getDefinedNames();
        CTDefinedName nameRecord = names.addNewDefinedName();
        nameRecord.setName(builtInName);
        nameRecord.setLocalSheetId(sheetNumber);
        if (getBuiltInName(builtInName, sheetNumber) != null) {
            throw new POIXMLException("Builtin (" + builtInName + ") already exists for sheet (" + sheetNumber + ")");
        }
        return createAndStoreName(nameRecord);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setSelectedTab(int index) {
        int idx = 0;
        for (XSSFSheet sh : this.sheets) {
            sh.setSelected(idx == index);
            idx++;
        }
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setSheetName(int sheetIndex, String sheetname) {
        if (sheetname == null) {
            throw new IllegalArgumentException("sheetName must not be null");
        }
        validateSheetIndex(sheetIndex);
        String oldSheetName = getSheetName(sheetIndex);
        if (sheetname.length() > 31) {
            sheetname = sheetname.substring(0, 31);
        }
        WorkbookUtil.validateSheetName(sheetname);
        if (sheetname.equals(oldSheetName)) {
            return;
        }
        if (containsSheet(sheetname, sheetIndex)) {
            throw new IllegalArgumentException("The workbook already contains a sheet of this name");
        }
        XSSFFormulaUtils utils = new XSSFFormulaUtils(this);
        utils.updateSheetName(sheetIndex, oldSheetName, sheetname);
        this.workbook.getSheets().getSheetArray(sheetIndex).setName(sheetname);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setSheetOrder(String sheetname, int pos) {
        int idx = getSheetIndex(sheetname);
        this.sheets.add(pos, this.sheets.remove(idx));
        CTSheets ct = this.workbook.getSheets();
        XmlObject cts = ct.getSheetArray(idx).copy();
        this.workbook.getSheets().removeSheet(idx);
        CTSheet newcts = ct.insertNewSheet(pos);
        newcts.set(cts);
        CTSheet[] sheetArray = ct.getSheetArray();
        for (int i = 0; i < sheetArray.length; i++) {
            this.sheets.get(i).sheet = sheetArray[i];
        }
        updateNamedRangesAfterSheetReorder(idx, pos);
        updateActiveSheetAfterSheetReorder(idx, pos);
    }

    private void updateNamedRangesAfterSheetReorder(int oldIndex, int newIndex) {
        for (XSSFName name : this.namedRanges) {
            int i = name.getSheetIndex();
            if (i != -1) {
                if (i == oldIndex) {
                    name.setSheetIndex(newIndex);
                } else if (newIndex <= i && i < oldIndex) {
                    name.setSheetIndex(i + 1);
                } else if (oldIndex < i && i <= newIndex) {
                    name.setSheetIndex(i - 1);
                }
            }
        }
    }

    private void updateActiveSheetAfterSheetReorder(int oldIndex, int newIndex) {
        int active = getActiveSheetIndex();
        if (active == oldIndex) {
            setActiveSheet(newIndex);
            return;
        }
        if (active >= oldIndex || active >= newIndex) {
            if (active <= oldIndex || active <= newIndex) {
                if (newIndex > oldIndex) {
                    setActiveSheet(active - 1);
                } else {
                    setActiveSheet(active + 1);
                }
            }
        }
    }

    private void saveNamedRanges() {
        if (!this.namedRanges.isEmpty()) {
            CTDefinedNames names = CTDefinedNames.Factory.newInstance();
            CTDefinedName[] nr = new CTDefinedName[this.namedRanges.size()];
            int i = 0;
            for (XSSFName name : this.namedRanges) {
                nr[i] = name.getCTName();
                i++;
            }
            names.setDefinedNameArray(nr);
            if (this.workbook.isSetDefinedNames()) {
                this.workbook.unsetDefinedNames();
            }
            this.workbook.setDefinedNames(names);
            reprocessNamedRanges();
            return;
        }
        if (this.workbook.isSetDefinedNames()) {
            this.workbook.unsetDefinedNames();
        }
    }

    private void reprocessNamedRanges() {
        this.namedRangesByName = new ArrayListValuedHashMap();
        this.namedRanges = new ArrayList();
        if (this.workbook.isSetDefinedNames()) {
            for (CTDefinedName ctName : this.workbook.getDefinedNames().getDefinedNameArray()) {
                createAndStoreName(ctName);
            }
        }
    }

    private void saveCalculationChain() {
        if (this.calcChain != null) {
            int count = this.calcChain.getCTCalcChain().sizeOfCArray();
            if (count == 0) {
                removeRelation(this.calcChain);
                this.calcChain = null;
            }
        }
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        saveNamedRanges();
        saveCalculationChain();
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTWorkbook.type.getName().getNamespaceURI(), "workbook"));
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            this.workbook.save(out, xmlOptions);
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

    @Internal
    public SharedStringsTable getSharedStringSource() {
        return this.sharedStringSource;
    }

    public StylesTable getStylesSource() {
        return this.stylesSource;
    }

    public ThemesTable getTheme() {
        if (this.stylesSource == null) {
            return null;
        }
        return this.stylesSource.getTheme();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public XSSFCreationHelper getCreationHelper() {
        if (this._creationHelper == null) {
            this._creationHelper = new XSSFCreationHelper(this);
        }
        return this._creationHelper;
    }

    private boolean containsSheet(String name, int excludeSheetIdx) {
        CTSheet[] ctSheetArray = this.workbook.getSheets().getSheetArray();
        if (name.length() > 31) {
            name = name.substring(0, 31);
        }
        for (int i = 0; i < ctSheetArray.length; i++) {
            String ctName = ctSheetArray[i].getName();
            if (ctName.length() > 31) {
                ctName = ctName.substring(0, 31);
            }
            if (excludeSheetIdx != i && name.equalsIgnoreCase(ctName)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.poi.ss.usermodel.Date1904Support
    @Internal
    public boolean isDate1904() {
        CTWorkbookPr workbookPr = this.workbook.getWorkbookPr();
        return workbookPr != null && workbookPr.getDate1904();
    }

    @Override // org.apache.poi.ooxml.POIXMLDocument
    public List<PackagePart> getAllEmbeddedParts() throws OpenXML4JException {
        List<PackagePart> embedds = new LinkedList<>();
        for (XSSFSheet sheet : this.sheets) {
            Iterator<PackageRelationship> it = sheet.getPackagePart().getRelationshipsByType(XSSFRelation.OLEEMBEDDINGS.getRelation()).iterator();
            while (it.hasNext()) {
                PackageRelationship rel = it.next();
                embedds.add(sheet.getPackagePart().getRelatedPart(rel));
            }
            Iterator<PackageRelationship> it2 = sheet.getPackagePart().getRelationshipsByType(XSSFRelation.PACKEMBEDDINGS.getRelation()).iterator();
            while (it2.hasNext()) {
                PackageRelationship rel2 = it2.next();
                embedds.add(sheet.getPackagePart().getRelatedPart(rel2));
            }
        }
        return embedds;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    @NotImplemented
    public boolean isHidden() {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    @NotImplemented
    public void setHidden(boolean hiddenFlag) {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public boolean isSheetHidden(int sheetIx) {
        validateSheetIndex(sheetIx);
        CTSheet ctSheet = this.sheets.get(sheetIx).sheet;
        return ctSheet.getState() == STSheetState.HIDDEN;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public boolean isSheetVeryHidden(int sheetIx) {
        validateSheetIndex(sheetIx);
        CTSheet ctSheet = this.sheets.get(sheetIx).sheet;
        return ctSheet.getState() == STSheetState.VERY_HIDDEN;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public SheetVisibility getSheetVisibility(int sheetIx) {
        validateSheetIndex(sheetIx);
        CTSheet ctSheet = this.sheets.get(sheetIx).sheet;
        STSheetState.Enum state = ctSheet.getState();
        if (state == STSheetState.VISIBLE) {
            return SheetVisibility.VISIBLE;
        }
        if (state == STSheetState.HIDDEN) {
            return SheetVisibility.HIDDEN;
        }
        if (state == STSheetState.VERY_HIDDEN) {
            return SheetVisibility.VERY_HIDDEN;
        }
        throw new IllegalArgumentException("This should never happen");
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setSheetHidden(int sheetIx, boolean hidden) {
        setSheetVisibility(sheetIx, hidden ? SheetVisibility.HIDDEN : SheetVisibility.VISIBLE);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setSheetVisibility(int sheetIx, SheetVisibility visibility) {
        validateSheetIndex(sheetIx);
        CTSheet ctSheet = this.sheets.get(sheetIx).sheet;
        switch (visibility) {
            case VISIBLE:
                ctSheet.setState(STSheetState.VISIBLE);
                return;
            case HIDDEN:
                ctSheet.setState(STSheetState.HIDDEN);
                return;
            case VERY_HIDDEN:
                ctSheet.setState(STSheetState.VERY_HIDDEN);
                return;
            default:
                throw new IllegalArgumentException("This should never happen");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDeleteFormula(XSSFCell cell) {
        if (this.calcChain != null) {
            int sheetId = (int) cell.getSheet().sheet.getSheetId();
            this.calcChain.removeItem(sheetId, cell.getReference());
        }
    }

    @Internal
    public CalculationChain getCalculationChain() {
        return this.calcChain;
    }

    @Internal
    public List<ExternalLinksTable> getExternalLinksTables() {
        return this.externalLinks;
    }

    @Removal(version = "7.0.0")
    @Internal
    @Deprecated
    public List<ExternalLinksTable> getExternalLinksTable() {
        return this.externalLinks;
    }

    @Internal
    public void addExternalLinksTable(ExternalLinksTable externalLinksTable) {
        if (this.externalLinks == null) {
            this.externalLinks = new ArrayList();
        }
        this.externalLinks.add(externalLinksTable);
    }

    @Internal
    public ExternalLinksTable getExternalLinksTable(int index) {
        if (this.externalLinks == null) {
            return null;
        }
        return this.externalLinks.get(index);
    }

    public Collection<XSSFMap> getCustomXMLMappings() {
        return this.mapInfo == null ? new ArrayList() : this.mapInfo.getAllXSSFMaps();
    }

    @Internal
    public MapInfo getMapInfo() {
        return this.mapInfo;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int linkExternalWorkbook(String name, Workbook workbook) {
        int externalLinkIdx = -1;
        if (!getCreationHelper().getReferencedWorkbooks().containsKey(name)) {
            externalLinkIdx = getNextPartNumber(XSSFRelation.EXTERNAL_LINKS, getPackagePart().getPackage().getPartsByContentType(XSSFRelation.EXTERNAL_LINKS.getContentType()).size() + 1);
            POIXMLDocumentPart.RelationPart rp = createRelationship(XSSFRelation.EXTERNAL_LINKS, this.xssfFactory, externalLinkIdx, false);
            ExternalLinksTable linksTable = (ExternalLinksTable) rp.getDocumentPart();
            linksTable.setLinkedFileName(name);
            addExternalLinksTable(linksTable);
            CTExternalReference ctExternalReference = getCTWorkbook().addNewExternalReferences().addNewExternalReference();
            ctExternalReference.setId(rp.getRelationship().getId());
        } else {
            List<POIXMLDocumentPart.RelationPart> relationParts = getRelationParts();
            Iterator<POIXMLDocumentPart.RelationPart> it = relationParts.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                POIXMLDocumentPart.RelationPart relationPart = it.next();
                if (relationPart.getDocumentPart() instanceof ExternalLinksTable) {
                    String linkedFileName = ((ExternalLinksTable) relationPart.getDocumentPart()).getLinkedFileName();
                    if (linkedFileName.equals(name)) {
                        String s = relationPart.getRelationship().getTargetURI().toString();
                        String s2 = XSSFRelation.EXTERNAL_LINKS.getDefaultFileName();
                        String numStr = s.substring(s2.indexOf(35), s2.indexOf(46));
                        externalLinkIdx = Integer.parseInt(numStr);
                        break;
                    }
                }
            }
        }
        XSSFCreationHelper creationHelper = getCreationHelper();
        creationHelper.addExternalWorkbook(name, workbook);
        return externalLinkIdx;
    }

    public boolean isStructureLocked() {
        return workbookProtectionPresent() && this.workbook.getWorkbookProtection().getLockStructure();
    }

    public boolean isWindowsLocked() {
        return workbookProtectionPresent() && this.workbook.getWorkbookProtection().getLockWindows();
    }

    public boolean isRevisionLocked() {
        return workbookProtectionPresent() && this.workbook.getWorkbookProtection().getLockRevision();
    }

    public void lockStructure() {
        safeGetWorkbookProtection().setLockStructure(true);
    }

    public void unLockStructure() {
        safeGetWorkbookProtection().setLockStructure(false);
    }

    public void lockWindows() {
        safeGetWorkbookProtection().setLockWindows(true);
    }

    public void unLockWindows() {
        safeGetWorkbookProtection().setLockWindows(false);
    }

    public void lockRevision() {
        safeGetWorkbookProtection().setLockRevision(true);
    }

    public void unLockRevision() {
        safeGetWorkbookProtection().setLockRevision(false);
    }

    public void setWorkbookPassword(String password, HashAlgorithm hashAlgo) {
        if (password == null && !workbookProtectionPresent()) {
            return;
        }
        XSSFPasswordHelper.setPassword(safeGetWorkbookProtection(), password, hashAlgo, "workbook");
    }

    public boolean validateWorkbookPassword(String password) {
        if (workbookProtectionPresent()) {
            return XSSFPasswordHelper.validatePassword(safeGetWorkbookProtection(), password, "workbook");
        }
        return password == null;
    }

    public void setRevisionsPassword(String password, HashAlgorithm hashAlgo) {
        if (password == null && !workbookProtectionPresent()) {
            return;
        }
        XSSFPasswordHelper.setPassword(safeGetWorkbookProtection(), password, hashAlgo, "revisions");
    }

    public boolean validateRevisionsPassword(String password) {
        if (workbookProtectionPresent()) {
            return XSSFPasswordHelper.validatePassword(safeGetWorkbookProtection(), password, "revisions");
        }
        return password == null;
    }

    public void unLock() {
        if (workbookProtectionPresent()) {
            this.workbook.unsetWorkbookProtection();
        }
    }

    private boolean workbookProtectionPresent() {
        return this.workbook.isSetWorkbookProtection();
    }

    private CTWorkbookProtection safeGetWorkbookProtection() {
        if (!workbookProtectionPresent()) {
            return this.workbook.addNewWorkbookProtection();
        }
        return this.workbook.getWorkbookProtection();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UDFFinder getUDFFinder() {
        return this._udfFinder;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void addToolPack(UDFFinder toolpack) {
        this._udfFinder.add(toolpack);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setForceFormulaRecalculation(boolean value) {
        CTWorkbook ctWorkbook = getCTWorkbook();
        CTCalcPr calcPr = ctWorkbook.isSetCalcPr() ? ctWorkbook.getCalcPr() : ctWorkbook.addNewCalcPr();
        calcPr.setFullCalcOnLoad(value);
        if (value && calcPr.getCalcMode() == STCalcMode.MANUAL) {
            calcPr.setCalcMode(STCalcMode.AUTO);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public boolean getForceFormulaRecalculation() {
        CTWorkbook ctWorkbook = getCTWorkbook();
        CTCalcPr calcPr = ctWorkbook.getCalcPr();
        return calcPr != null && calcPr.isSetFullCalcOnLoad() && calcPr.getFullCalcOnLoad();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CTPivotCache addPivotCache(String rId) {
        CTPivotCaches caches;
        CTWorkbook ctWorkbook = getCTWorkbook();
        if (ctWorkbook.isSetPivotCaches()) {
            caches = ctWorkbook.getPivotCaches();
        } else {
            caches = ctWorkbook.addNewPivotCaches();
        }
        CTPivotCache cache = caches.addNewPivotCache();
        int tableId = this.pivotTables.size() + 1;
        cache.setCacheId(tableId);
        cache.setId(rId);
        if (this.pivotCaches == null) {
            this.pivotCaches = new ArrayList();
        }
        this.pivotCaches.add(cache);
        return cache;
    }

    public List<XSSFPivotTable> getPivotTables() {
        return this.pivotTables;
    }

    @Internal
    public void addPivotTable(XSSFPivotTable pivotTable) {
        this.pivotTables.add(pivotTable);
    }

    public XSSFWorkbookType getWorkbookType() {
        return isMacroEnabled() ? XSSFWorkbookType.XLSM : XSSFWorkbookType.XLSX;
    }

    public void setWorkbookType(XSSFWorkbookType type) {
        try {
            getPackagePart().setContentType(type.getContentType());
        } catch (InvalidFormatException e) {
            throw new POIXMLException(e);
        }
    }

    public void setVBAProject(InputStream vbaProjectStream) throws IOException {
        OutputStream outputStream;
        if (!isMacroEnabled()) {
            setWorkbookType(XSSFWorkbookType.XLSM);
        }
        try {
            PackagePartName ppName = PackagingURIHelper.createPartName(XSSFRelation.VBA_MACROS.getDefaultFileName());
            OPCPackage opc = getPackage();
            if (!opc.containPart(ppName)) {
                POIXMLDocumentPart relationship = createRelationship(XSSFRelation.VBA_MACROS, this.xssfFactory);
                outputStream = relationship.getPackagePart().getOutputStream();
            } else {
                PackagePart part = opc.getPart(ppName);
                outputStream = part.getOutputStream();
            }
            try {
                IOUtils.copy(vbaProjectStream, outputStream);
            } finally {
                IOUtils.closeQuietly(outputStream);
            }
        } catch (InvalidFormatException e) {
            throw new POIXMLException(e);
        }
    }

    public void setVBAProject(XSSFWorkbook macroWorkbook) throws IOException, InvalidFormatException {
        InputStream vbaProjectStream;
        if (macroWorkbook.isMacroEnabled() && (vbaProjectStream = XSSFRelation.VBA_MACROS.getContents(macroWorkbook.getCorePart())) != null) {
            setVBAProject(vbaProjectStream);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL2007;
    }

    public XSSFTable getTable(String name) {
        if (name != null && this.sheets != null) {
            for (XSSFSheet sheet : this.sheets) {
                for (XSSFTable tbl : sheet.getTables()) {
                    if (name.equalsIgnoreCase(tbl.getName())) {
                        return tbl;
                    }
                }
            }
            return null;
        }
        return null;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int addOlePackage(byte[] oleData, String label, String fileName, String command) throws IOException {
        XSSFRelation rel = XSSFRelation.OLEEMBEDDINGS;
        OPCPackage opc = getPackage();
        try {
            int oleId = opc.getUnusedPartIndex(rel.getDefaultFileName());
            PackagePartName pnOLE = PackagingURIHelper.createPartName(rel.getFileName(oleId));
            PackagePart pp = opc.createPart(pnOLE, rel.getContentType());
            Ole10Native ole10 = new Ole10Native(label, fileName, command, oleData);
            UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().setBufferSize(oleData.length + 500).get();
            try {
                ole10.writeOut(bos);
                POIFSFileSystem poifs = new POIFSFileSystem();
                try {
                    DirectoryNode root = poifs.getRoot();
                    root.createDocument(Ole10Native.OLE10_NATIVE, bos.toInputStream());
                    root.setStorageClsid(ClassIDPredefined.OLE_V1_PACKAGE.getClassID());
                    OutputStream os = pp.getOutputStream();
                    try {
                        poifs.writeFilesystem(os);
                        if (os != null) {
                            os.close();
                        }
                        poifs.close();
                        if (bos != null) {
                            bos.close();
                        }
                        return oleId;
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } catch (InvalidFormatException e) {
            throw new IOException("ole object name not recognized", e);
        }
    }

    public void setCellFormulaValidation(boolean value) {
        this.cellFormulaValidation = value;
    }

    public boolean getCellFormulaValidation() {
        return this.cellFormulaValidation;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public XSSFEvaluationWorkbook createEvaluationWorkbook() {
        return XSSFEvaluationWorkbook.create(this);
    }
}
