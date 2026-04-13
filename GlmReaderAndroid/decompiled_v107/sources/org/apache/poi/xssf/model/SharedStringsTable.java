package org.apache.poi.xssf.model;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSst;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.SstDocument;

/* loaded from: classes10.dex */
public class SharedStringsTable extends POIXMLDocumentPart implements SharedStrings, Closeable {
    private static final XmlOptions options = new XmlOptions();
    private SstDocument _sstDoc;
    protected int count;
    private final Map<String, Integer> stmap;
    private final List<CTRst> strings;
    protected int uniqueCount;

    static {
        options.setSaveInner();
        options.setSaveAggressiveNamespaces();
        options.setUseDefaultNamespace(true);
        options.setSaveImplicitNamespaces(Collections.singletonMap("", XSSFRelation.NS_SPREADSHEETML));
    }

    public SharedStringsTable() {
        this.strings = new ArrayList();
        this.stmap = new HashMap();
        this._sstDoc = SstDocument.Factory.newInstance();
        this._sstDoc.addNewSst();
    }

    public SharedStringsTable(PackagePart part) throws IOException {
        super(part);
        this.strings = new ArrayList();
        this.stmap = new HashMap();
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

    public void readFrom(InputStream is) throws IOException {
        int cnt = 0;
        try {
            this._sstDoc = SstDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            CTSst sst = this._sstDoc.getSst();
            this.count = (int) sst.getCount();
            this.uniqueCount = (int) sst.getUniqueCount();
            for (CTRst st : sst.getSiArray()) {
                this.stmap.put(xmlText(st), Integer.valueOf(cnt));
                this.strings.add(st);
                cnt++;
            }
        } catch (XmlException e) {
            throw new IOException("unable to parse shared strings table", e);
        }
    }

    protected String xmlText(CTRst st) {
        return st.xmlText(options);
    }

    @Override // org.apache.poi.xssf.model.SharedStrings
    public RichTextString getItemAt(int idx) {
        return new XSSFRichTextString(this.strings.get(idx));
    }

    @Override // org.apache.poi.xssf.model.SharedStrings
    public int getCount() {
        return this.count;
    }

    @Override // org.apache.poi.xssf.model.SharedStrings
    public int getUniqueCount() {
        return this.uniqueCount;
    }

    @Internal
    int addEntry(CTRst st) {
        String s = xmlText(st);
        this.count++;
        if (this.stmap.containsKey(s)) {
            return this.stmap.get(s).intValue();
        }
        this.uniqueCount++;
        CTRst newSt = this._sstDoc.getSst().addNewSi();
        newSt.set(st);
        int idx = this.strings.size();
        this.stmap.put(s, Integer.valueOf(idx));
        this.strings.add(newSt);
        return idx;
    }

    public int addSharedStringItem(RichTextString string) {
        if (!(string instanceof XSSFRichTextString)) {
            throw new IllegalArgumentException("Only XSSFRichTextString argument is supported");
        }
        return addEntry(((XSSFRichTextString) string).getCTRst());
    }

    public List<RichTextString> getSharedStringItems() {
        ArrayList<RichTextString> items = new ArrayList<>();
        for (CTRst rst : this.strings) {
            items.add(new XSSFRichTextString(rst));
        }
        return Collections.unmodifiableList(items);
    }

    public void writeTo(OutputStream out) throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveCDataLengthThreshold(1000000);
        xmlOptions.setSaveCDataEntityCountThreshold(-1);
        CTSst sst = this._sstDoc.getSst();
        sst.setCount(this.count);
        sst.setUniqueCount(this.uniqueCount);
        this._sstDoc.save(out, xmlOptions);
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

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }
}
