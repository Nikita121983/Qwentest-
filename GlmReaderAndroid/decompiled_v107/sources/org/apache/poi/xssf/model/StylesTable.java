package org.apache.poi.xssf.model;

import androidx.recyclerview.widget.ItemTouchHelper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.FontScheme;
import org.apache.poi.ss.usermodel.TableStyle;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.CustomIndexedColorMap;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFBuiltinTableStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFTableStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellFill;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorders;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyleXfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellXfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFills;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFonts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyles;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPatternType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.StyleSheetDocument;

/* loaded from: classes10.dex */
public class StylesTable extends POIXMLDocumentPart implements Styles {
    public static final int FIRST_CUSTOM_STYLE_ID = 165;
    private static final short FIRST_USER_DEFINED_NUMBER_FORMAT_ID = 164;
    private static final int MAXIMUM_STYLE_ID = SpreadsheetVersion.EXCEL2007.getMaxCellStyles();
    private int MAXIMUM_NUMBER_OF_DATA_FORMATS;
    private final List<XSSFCellBorder> borders;
    private StyleSheetDocument doc;
    private final List<CTDxf> dxfs;
    private final List<XSSFCellFill> fills;
    private final List<XSSFFont> fonts;
    private IndexedColorMap indexedColors;
    private final SortedMap<Short, String> numberFormats;
    private final List<CTXf> styleXfs;
    private final Map<String, TableStyle> tableStyles;
    private ThemesTable theme;
    private XSSFWorkbook workbook;
    private final List<CTXf> xfs;

    public void setMaxNumberOfDataFormats(int num) {
        if (num < getNumDataFormats()) {
            if (num < 0) {
                throw new IllegalArgumentException("Maximum Number of Data Formats must be greater than or equal to 0");
            }
            throw new IllegalStateException("Cannot set the maximum number of data formats less than the current quantity. Data formats must be explicitly removed (via StylesTable.removeNumberFormat) before the limit can be decreased.");
        }
        this.MAXIMUM_NUMBER_OF_DATA_FORMATS = num;
    }

    public int getMaxNumberOfDataFormats() {
        return this.MAXIMUM_NUMBER_OF_DATA_FORMATS;
    }

    public StylesTable() {
        this.numberFormats = new TreeMap();
        this.fonts = new ArrayList();
        this.fills = new ArrayList();
        this.borders = new ArrayList();
        this.styleXfs = new ArrayList();
        this.xfs = new ArrayList();
        this.dxfs = new ArrayList();
        this.tableStyles = new HashMap();
        this.indexedColors = new DefaultIndexedColorMap();
        this.MAXIMUM_NUMBER_OF_DATA_FORMATS = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        this.doc = StyleSheetDocument.Factory.newInstance();
        this.doc.addNewStyleSheet();
        initialize();
    }

    public StylesTable(PackagePart part) throws IOException {
        super(part);
        this.numberFormats = new TreeMap();
        this.fonts = new ArrayList();
        this.fills = new ArrayList();
        this.borders = new ArrayList();
        this.styleXfs = new ArrayList();
        this.xfs = new ArrayList();
        this.dxfs = new ArrayList();
        this.tableStyles = new HashMap();
        this.indexedColors = new DefaultIndexedColorMap();
        this.MAXIMUM_NUMBER_OF_DATA_FORMATS = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        InputStream stream = part.getInputStream();
        try {
            readFrom(stream);
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

    public StylesTable(InputStream stream) throws IOException {
        this.numberFormats = new TreeMap();
        this.fonts = new ArrayList();
        this.fills = new ArrayList();
        this.borders = new ArrayList();
        this.styleXfs = new ArrayList();
        this.xfs = new ArrayList();
        this.dxfs = new ArrayList();
        this.tableStyles = new HashMap();
        this.indexedColors = new DefaultIndexedColorMap();
        this.MAXIMUM_NUMBER_OF_DATA_FORMATS = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        readFrom(stream);
    }

    public void setWorkbook(XSSFWorkbook wb) {
        this.workbook = wb;
    }

    public ThemesTable getTheme() {
        return this.theme;
    }

    public void setTheme(ThemesTable theme) {
        this.theme = theme;
        if (theme != null) {
            theme.setColorMap(getIndexedColors());
        }
        for (XSSFFont font : this.fonts) {
            font.setThemesTable(theme);
        }
        for (XSSFCellBorder border : this.borders) {
            border.setThemesTable(theme);
        }
    }

    public void ensureThemesTable() {
        if (this.theme == null && this.workbook != null) {
            setTheme((ThemesTable) this.workbook.createRelationship(XSSFRelation.THEME, this.workbook.getXssfFactory()));
        }
    }

    public void readFrom(InputStream is) throws IOException {
        StylesTable stylesTable = this;
        try {
            stylesTable.doc = StyleSheetDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            CTStylesheet styleSheet = stylesTable.doc.getStyleSheet();
            IndexedColorMap customColors = CustomIndexedColorMap.fromColors(styleSheet.getColors());
            if (customColors != null) {
                stylesTable.indexedColors = customColors;
            }
            CTNumFmts ctfmts = styleSheet.getNumFmts();
            int i = 0;
            if (ctfmts != null) {
                for (CTNumFmt nfmt : ctfmts.getNumFmtArray()) {
                    short formatId = (short) nfmt.getNumFmtId();
                    stylesTable.numberFormats.put(Short.valueOf(formatId), nfmt.getFormatCode());
                }
            }
            CTFonts ctfonts = styleSheet.getFonts();
            if (ctfonts != null) {
                int idx = 0;
                for (CTFont font : ctfonts.getFontArray()) {
                    XSSFFont f = new XSSFFont(font, idx, stylesTable.indexedColors);
                    stylesTable.fonts.add(f);
                    idx++;
                }
            }
            CTFills ctfills = styleSheet.getFills();
            if (ctfills != null) {
                for (CTFill fill : ctfills.getFillArray()) {
                    stylesTable.fills.add(new XSSFCellFill(fill, stylesTable.indexedColors));
                }
            }
            CTBorders ctborders = styleSheet.getBorders();
            if (ctborders != null) {
                for (CTBorder border : ctborders.getBorderArray()) {
                    stylesTable.borders.add(new XSSFCellBorder(border, stylesTable.indexedColors));
                }
            }
            CTCellXfs cellXfs = styleSheet.getCellXfs();
            if (cellXfs != null) {
                stylesTable.xfs.addAll(Arrays.asList(cellXfs.getXfArray()));
            }
            CTCellStyleXfs cellStyleXfs = styleSheet.getCellStyleXfs();
            if (cellStyleXfs != null) {
                stylesTable.styleXfs.addAll(Arrays.asList(cellStyleXfs.getXfArray()));
            }
            CTDxfs styleDxfs = styleSheet.getDxfs();
            if (styleDxfs != null) {
                stylesTable.dxfs.addAll(Arrays.asList(styleDxfs.getDxfArray()));
            }
            CTTableStyles ctTableStyles = styleSheet.getTableStyles();
            if (ctTableStyles != null && styleDxfs != null) {
                int idx2 = 0;
                CTTableStyle[] tableStyleArray = ctTableStyles.getTableStyleArray();
                int length = tableStyleArray.length;
                while (i < length) {
                    CTTableStyle style = tableStyleArray[i];
                    CTStylesheet styleSheet2 = styleSheet;
                    IndexedColorMap customColors2 = customColors;
                    CTNumFmts ctfmts2 = ctfmts;
                    stylesTable.tableStyles.put(style.getName(), new XSSFTableStyle(idx2, styleDxfs, style, stylesTable.indexedColors));
                    idx2++;
                    i++;
                    stylesTable = this;
                    styleSheet = styleSheet2;
                    customColors = customColors2;
                    ctfmts = ctfmts2;
                }
            }
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    @Override // org.apache.poi.xssf.model.Styles
    public String getNumberFormatAt(short fmtId) {
        return this.numberFormats.get(Short.valueOf(fmtId));
    }

    private short getNumberFormatId(String fmt) {
        for (Map.Entry<Short, String> numFmt : this.numberFormats.entrySet()) {
            if (numFmt.getValue().equals(fmt)) {
                return numFmt.getKey().shortValue();
            }
        }
        throw new IllegalStateException("Number format not in style table: " + fmt);
    }

    @Override // org.apache.poi.xssf.model.Styles
    public int putNumberFormat(String fmt) {
        short nextKey;
        if (this.numberFormats.containsValue(fmt)) {
            try {
                return getNumberFormatId(fmt);
            } catch (IllegalStateException e) {
                throw new IllegalStateException("Found the format, but couldn't figure out where - should never happen!");
            }
        }
        if (this.numberFormats.size() >= this.MAXIMUM_NUMBER_OF_DATA_FORMATS) {
            throw new IllegalStateException("The maximum number of Data Formats was exceeded. You can define up to " + this.MAXIMUM_NUMBER_OF_DATA_FORMATS + " formats in a .xlsx Workbook.");
        }
        if (this.numberFormats.isEmpty()) {
            nextKey = 164;
        } else {
            short nextKey2 = (short) (this.numberFormats.lastKey().shortValue() + 1);
            if (nextKey2 < 0) {
                throw new IllegalStateException("Cowardly avoiding creating a number format with a negative id. This is probably due to arithmetic overflow.");
            }
            nextKey = (short) Math.max((int) nextKey2, 164);
        }
        this.numberFormats.put(Short.valueOf(nextKey), fmt);
        return nextKey;
    }

    @Override // org.apache.poi.xssf.model.Styles
    public void putNumberFormat(short index, String fmt) {
        this.numberFormats.put(Short.valueOf(index), fmt);
    }

    @Override // org.apache.poi.xssf.model.Styles
    public boolean removeNumberFormat(short index) {
        String fmt = this.numberFormats.remove(Short.valueOf(index));
        boolean removed = fmt != null;
        if (removed) {
            for (CTXf style : this.xfs) {
                if (style.isSetNumFmtId() && style.getNumFmtId() == index) {
                    style.unsetApplyNumberFormat();
                    style.unsetNumFmtId();
                }
            }
        }
        return removed;
    }

    @Override // org.apache.poi.xssf.model.Styles
    public boolean removeNumberFormat(String fmt) {
        short id = getNumberFormatId(fmt);
        return removeNumberFormat(id);
    }

    @Override // org.apache.poi.xssf.model.Styles
    public XSSFFont getFontAt(int idx) {
        return this.fonts.get(idx);
    }

    @Override // org.apache.poi.xssf.model.Styles
    public int putFont(XSSFFont font, boolean forceRegistration) {
        int idx = -1;
        if (!forceRegistration) {
            idx = this.fonts.indexOf(font);
        }
        if (idx != -1) {
            return idx;
        }
        int idx2 = this.fonts.size();
        this.fonts.add(font);
        return idx2;
    }

    @Override // org.apache.poi.xssf.model.Styles
    public int putFont(XSSFFont font) {
        return putFont(font, false);
    }

    @Override // org.apache.poi.xssf.model.Styles
    public XSSFCellStyle getStyleAt(int idx) {
        int styleXfId = 0;
        if (idx < 0 || idx >= this.xfs.size()) {
            return null;
        }
        if (this.xfs.get(idx).getXfId() > 0) {
            styleXfId = (int) this.xfs.get(idx).getXfId();
        }
        return new XSSFCellStyle(idx, styleXfId, this, this.theme);
    }

    @Override // org.apache.poi.xssf.model.Styles
    public int putStyle(XSSFCellStyle style) {
        CTXf mainXF = style.getCoreXf();
        int ret = this.xfs.indexOf(mainXF);
        if (ret == -1) {
            this.xfs.add(mainXF);
            return this.xfs.size() - 1;
        }
        return ret;
    }

    @Override // org.apache.poi.xssf.model.Styles
    public XSSFCellBorder getBorderAt(int idx) {
        return this.borders.get(idx);
    }

    @Override // org.apache.poi.xssf.model.Styles
    public int putBorder(XSSFCellBorder border) {
        int idx = this.borders.indexOf(border);
        if (idx != -1) {
            return idx;
        }
        this.borders.add(border);
        border.setThemesTable(this.theme);
        return this.borders.size() - 1;
    }

    @Override // org.apache.poi.xssf.model.Styles
    public XSSFCellFill getFillAt(int idx) {
        return this.fills.get(idx);
    }

    public List<XSSFCellBorder> getBorders() {
        return Collections.unmodifiableList(this.borders);
    }

    public List<XSSFCellFill> getFills() {
        return Collections.unmodifiableList(this.fills);
    }

    public List<XSSFFont> getFonts() {
        return Collections.unmodifiableList(this.fonts);
    }

    public Map<Short, String> getNumberFormats() {
        return Collections.unmodifiableMap(this.numberFormats);
    }

    @Override // org.apache.poi.xssf.model.Styles
    public int putFill(XSSFCellFill fill) {
        int idx = this.fills.indexOf(fill);
        if (idx != -1) {
            return idx;
        }
        this.fills.add(fill);
        return this.fills.size() - 1;
    }

    @Internal
    public CTXf getCellXfAt(int idx) {
        return this.xfs.get(idx);
    }

    @Internal
    public int putCellXf(CTXf cellXf) {
        this.xfs.add(cellXf);
        return this.xfs.size();
    }

    @Internal
    public void replaceCellXfAt(int idx, CTXf cellXf) {
        this.xfs.set(idx, cellXf);
    }

    @Internal
    public CTXf getCellStyleXfAt(int idx) {
        try {
            return this.styleXfs.get(idx);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Internal
    public int putCellStyleXf(CTXf cellStyleXf) {
        this.styleXfs.add(cellStyleXf);
        return this.styleXfs.size();
    }

    @Internal
    protected void replaceCellStyleXfAt(int idx, CTXf cellStyleXf) {
        this.styleXfs.set(idx, cellStyleXf);
    }

    @Override // org.apache.poi.xssf.model.Styles
    public int getNumCellStyles() {
        return this.xfs.size();
    }

    @Override // org.apache.poi.xssf.model.Styles
    public int getNumDataFormats() {
        return this.numberFormats.size();
    }

    @Internal
    int _getXfsSize() {
        return this.xfs.size();
    }

    @Internal
    public int _getStyleXfsSize() {
        return this.styleXfs.size();
    }

    @Internal
    public CTStylesheet getCTStylesheet() {
        return this.doc.getStyleSheet();
    }

    @Internal
    public int _getDXfsSize() {
        return this.dxfs.size();
    }

    public void writeTo(OutputStream out) throws IOException {
        CTStylesheet styleSheet = this.doc.getStyleSheet();
        CTNumFmts formats = CTNumFmts.Factory.newInstance();
        formats.setCount(this.numberFormats.size());
        for (Map.Entry<Short, String> entry : this.numberFormats.entrySet()) {
            CTNumFmt ctFmt = formats.addNewNumFmt();
            ctFmt.setNumFmtId(entry.getKey().shortValue());
            ctFmt.setFormatCode(entry.getValue());
        }
        styleSheet.setNumFmts(formats);
        CTFonts ctFonts = styleSheet.getFonts();
        if (ctFonts == null) {
            ctFonts = CTFonts.Factory.newInstance();
        }
        ctFonts.setCount(this.fonts.size());
        CTFont[] ctfnt = new CTFont[this.fonts.size()];
        int idx = 0;
        for (XSSFFont f : this.fonts) {
            ctfnt[idx] = f.getCTFont();
            idx++;
        }
        ctFonts.setFontArray(ctfnt);
        styleSheet.setFonts(ctFonts);
        CTFills ctFills = styleSheet.getFills();
        if (ctFills == null) {
            ctFills = CTFills.Factory.newInstance();
        }
        ctFills.setCount(this.fills.size());
        CTFill[] ctf = new CTFill[this.fills.size()];
        int idx2 = 0;
        for (XSSFCellFill f2 : this.fills) {
            ctf[idx2] = f2.getCTFill();
            idx2++;
        }
        ctFills.setFillArray(ctf);
        styleSheet.setFills(ctFills);
        CTBorders ctBorders = styleSheet.getBorders();
        if (ctBorders == null) {
            ctBorders = CTBorders.Factory.newInstance();
        }
        ctBorders.setCount(this.borders.size());
        CTBorder[] ctb = new CTBorder[this.borders.size()];
        int idx3 = 0;
        for (XSSFCellBorder b : this.borders) {
            ctb[idx3] = b.getCTBorder();
            idx3++;
        }
        ctBorders.setBorderArray(ctb);
        styleSheet.setBorders(ctBorders);
        if (!this.xfs.isEmpty()) {
            CTCellXfs ctXfs = styleSheet.getCellXfs();
            if (ctXfs == null) {
                ctXfs = CTCellXfs.Factory.newInstance();
            }
            ctXfs.setCount(this.xfs.size());
            ctXfs.setXfArray((CTXf[]) this.xfs.toArray(new CTXf[0]));
            styleSheet.setCellXfs(ctXfs);
        }
        if (!this.styleXfs.isEmpty()) {
            CTCellStyleXfs ctSXfs = styleSheet.getCellStyleXfs();
            if (ctSXfs == null) {
                ctSXfs = CTCellStyleXfs.Factory.newInstance();
            }
            ctSXfs.setCount(this.styleXfs.size());
            ctSXfs.setXfArray((CTXf[]) this.styleXfs.toArray(new CTXf[0]));
            styleSheet.setCellStyleXfs(ctSXfs);
        }
        if (!this.dxfs.isEmpty()) {
            CTDxfs ctDxfs = styleSheet.getDxfs();
            if (ctDxfs == null) {
                ctDxfs = CTDxfs.Factory.newInstance();
            }
            ctDxfs.setCount(this.dxfs.size());
            ctDxfs.setDxfArray((CTDxf[]) this.dxfs.toArray(new CTDxf[0]));
            styleSheet.setDxfs(ctDxfs);
        }
        this.doc.save(out, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            writeTo(out);
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

    private void initialize() {
        XSSFFont xssfFont = createDefaultFont();
        this.fonts.add(xssfFont);
        CTFill[] ctFill = createDefaultFills();
        this.fills.add(new XSSFCellFill(ctFill[0], this.indexedColors));
        this.fills.add(new XSSFCellFill(ctFill[1], this.indexedColors));
        CTBorder ctBorder = createDefaultBorder();
        this.borders.add(new XSSFCellBorder(ctBorder));
        CTXf styleXf = createDefaultXf();
        this.styleXfs.add(styleXf);
        CTXf xf = createDefaultXf();
        xf.setXfId(0L);
        this.xfs.add(xf);
    }

    private static CTXf createDefaultXf() {
        CTXf ctXf = CTXf.Factory.newInstance();
        ctXf.setNumFmtId(0L);
        ctXf.setFontId(0L);
        ctXf.setFillId(0L);
        ctXf.setBorderId(0L);
        return ctXf;
    }

    private static CTBorder createDefaultBorder() {
        CTBorder ctBorder = CTBorder.Factory.newInstance();
        ctBorder.addNewBottom();
        ctBorder.addNewTop();
        ctBorder.addNewLeft();
        ctBorder.addNewRight();
        ctBorder.addNewDiagonal();
        return ctBorder;
    }

    private static CTFill[] createDefaultFills() {
        CTFill[] ctFill = {CTFill.Factory.newInstance(), CTFill.Factory.newInstance()};
        ctFill[0].addNewPatternFill().setPatternType(STPatternType.NONE);
        ctFill[1].addNewPatternFill().setPatternType(STPatternType.DARK_GRAY);
        return ctFill;
    }

    private static XSSFFont createDefaultFont() {
        CTFont ctFont = CTFont.Factory.newInstance();
        XSSFFont xssfFont = new XSSFFont(ctFont, 0, null);
        xssfFont.setFontHeightInPoints((short) 11);
        xssfFont.setColor(XSSFFont.DEFAULT_FONT_COLOR);
        xssfFont.setFontName(XSSFFont.DEFAULT_FONT_NAME);
        xssfFont.setFamily(FontFamily.SWISS);
        xssfFont.setScheme(FontScheme.MINOR);
        return xssfFont;
    }

    @Internal
    public CTDxf getDxfAt(int idx) {
        return this.dxfs.get(idx);
    }

    @Internal
    public int putDxf(CTDxf dxf) {
        this.dxfs.add(dxf);
        return this.dxfs.size();
    }

    public TableStyle getExplicitTableStyle(String name) {
        return this.tableStyles.get(name);
    }

    public Set<String> getExplicitTableStyleNames() {
        return this.tableStyles.keySet();
    }

    public TableStyle getTableStyle(String name) {
        if (name == null) {
            return null;
        }
        TableStyle tableStyle = getExplicitTableStyle(name);
        if (tableStyle != null) {
            return tableStyle;
        }
        try {
            return XSSFBuiltinTableStyle.valueOf(name).getStyle();
        } catch (IllegalArgumentException e) {
            return getExplicitTableStyle(name);
        }
    }

    public XSSFCellStyle createCellStyle() {
        if (getNumCellStyles() > MAXIMUM_STYLE_ID) {
            throw new IllegalStateException("The maximum number of Cell Styles was exceeded. You can define up to " + MAXIMUM_STYLE_ID + " style in a .xlsx Workbook");
        }
        int xfSize = this.styleXfs.size();
        CTXf xf = CTXf.Factory.newInstance();
        xf.setNumFmtId(0L);
        xf.setFontId(0L);
        xf.setFillId(0L);
        xf.setBorderId(0L);
        xf.setXfId(0L);
        int indexXf = putCellXf(xf);
        return new XSSFCellStyle(indexXf - 1, xfSize - 1, this, this.theme);
    }

    public XSSFFont findFont(boolean bold, short color, short fontHeight, String name, boolean italic, boolean strikeout, short typeOffset, byte underline) {
        for (XSSFFont font : this.fonts) {
            if (font.getBold() == bold && font.getColor() == color && font.getFontHeight() == fontHeight && font.getFontName().equals(name) && font.getItalic() == italic && font.getStrikeout() == strikeout && font.getTypeOffset() == typeOffset && font.getUnderline() == underline) {
                return font;
            }
        }
        return null;
    }

    public XSSFFont findFont(boolean bold, Color color, short fontHeight, String name, boolean italic, boolean strikeout, short typeOffset, byte underline) {
        for (XSSFFont font : this.fonts) {
            if (font.getBold() == bold && font.getXSSFColor().equals(color) && font.getFontHeight() == fontHeight && font.getFontName().equals(name) && font.getItalic() == italic && font.getStrikeout() == strikeout && font.getTypeOffset() == typeOffset && font.getUnderline() == underline) {
                return font;
            }
        }
        return null;
    }

    public IndexedColorMap getIndexedColors() {
        return this.indexedColors;
    }
}
