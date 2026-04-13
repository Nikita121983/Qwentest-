package org.apache.poi.xssf.eventusermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.compress.compressors.bzip2.BZip2Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/* loaded from: classes10.dex */
public class ReadOnlySharedStringsTable extends DefaultHandler implements SharedStrings {
    private StringBuilder characters;
    protected int count;
    private boolean inRPh;
    protected final boolean includePhoneticRuns;
    private List<String> strings;
    private boolean tIsOpen;
    protected int uniqueCount;

    public ReadOnlySharedStringsTable(OPCPackage pkg) throws IOException, SAXException {
        this(pkg, true);
    }

    public ReadOnlySharedStringsTable(OPCPackage pkg, boolean includePhoneticRuns) throws IOException, SAXException {
        this.includePhoneticRuns = includePhoneticRuns;
        ArrayList<PackagePart> parts = pkg.getPartsByContentType(XSSFRelation.SHARED_STRINGS.getContentType());
        if (!parts.isEmpty()) {
            PackagePart sstPart = parts.get(0);
            InputStream stream = sstPart.getInputStream();
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
    }

    public ReadOnlySharedStringsTable(PackagePart part) throws IOException, SAXException {
        this(part, true);
    }

    public ReadOnlySharedStringsTable(PackagePart part, boolean includePhoneticRuns) throws IOException, SAXException {
        this.includePhoneticRuns = includePhoneticRuns;
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

    public ReadOnlySharedStringsTable(InputStream stream) throws IOException, SAXException {
        this(stream, true);
    }

    public ReadOnlySharedStringsTable(InputStream stream, boolean includePhoneticRuns) throws IOException, SAXException {
        this.includePhoneticRuns = includePhoneticRuns;
        readFrom(stream);
    }

    public void readFrom(InputStream is) throws IOException, SAXException {
        PushbackInputStream pis = new PushbackInputStream(is, 1);
        int emptyTest = pis.read();
        if (emptyTest > -1) {
            pis.unread(emptyTest);
            InputSource sheetSource = new InputSource(pis);
            try {
                XMLReader sheetParser = XMLHelper.newXMLReader();
                sheetParser.setContentHandler(this);
                sheetParser.parse(sheetSource);
            } catch (ParserConfigurationException e) {
                throw new SAXException("SAX parser appears to be broken - " + e.getMessage());
            }
        }
    }

    @Override // org.apache.poi.xssf.model.SharedStrings
    public int getCount() {
        return this.count;
    }

    @Override // org.apache.poi.xssf.model.SharedStrings
    public int getUniqueCount() {
        return this.uniqueCount;
    }

    @Override // org.apache.poi.xssf.model.SharedStrings
    public RichTextString getItemAt(int idx) {
        if (this.strings == null || idx >= this.strings.size()) {
            throw new IllegalStateException("Cannot get item at " + idx + " with strings: " + this.strings);
        }
        return new XSSFRichTextString(this.strings.get(idx));
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        if (uri != null && !uri.equals(XSSFRelation.NS_SPREADSHEETML)) {
            return;
        }
        if ("sst".equals(localName)) {
            String count = attributes.getValue("count");
            if (count != null) {
                this.count = (int) Long.parseLong(count);
            }
            String uniqueCount = attributes.getValue("uniqueCount");
            if (uniqueCount != null) {
                this.uniqueCount = (int) Long.parseLong(uniqueCount);
            }
            this.strings = new ArrayList(Math.min(this.uniqueCount, BZip2Constants.BASEBLOCKSIZE));
            this.characters = new StringBuilder(64);
            return;
        }
        if ("si".equals(localName)) {
            if (this.characters != null) {
                this.characters.setLength(0);
            }
        } else {
            if ("t".equals(localName)) {
                this.tIsOpen = true;
                return;
            }
            if ("rPh".equals(localName)) {
                this.inRPh = true;
                if (this.includePhoneticRuns && this.characters != null && this.characters.length() > 0) {
                    this.characters.append(StringUtils.SPACE);
                }
            }
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endElement(String uri, String localName, String name) throws SAXException {
        if (uri != null && !uri.equals(XSSFRelation.NS_SPREADSHEETML)) {
            return;
        }
        if ("si".equals(localName)) {
            if (this.strings != null && this.characters != null) {
                this.strings.add(this.characters.toString());
                return;
            }
            return;
        }
        if ("t".equals(localName)) {
            this.tIsOpen = false;
        } else if ("rPh".equals(localName)) {
            this.inRPh = false;
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.tIsOpen) {
            if (this.inRPh && this.includePhoneticRuns) {
                if (this.characters != null) {
                    this.characters.append(ch, start, length);
                }
            } else if (!this.inRPh && this.characters != null) {
                this.characters.append(ch, start, length);
            }
        }
    }
}
