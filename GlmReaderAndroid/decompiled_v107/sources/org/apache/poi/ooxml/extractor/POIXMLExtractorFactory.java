package org.apache.poi.ooxml.extractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.extractor.ExtractorProvider;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xdgf.extractor.XDGFVisioExtractor;
import org.apache.poi.xslf.extractor.XSLFExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFRelation;
import org.apache.poi.xssf.extractor.XSSFBEventBasedExcelExtractor;
import org.apache.poi.xssf.extractor.XSSFEventBasedExcelExtractor;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFRelation;
import org.apache.xmlbeans.XmlException;

/* loaded from: classes10.dex */
public final class POIXMLExtractorFactory implements ExtractorProvider {
    private static final String CORE_DOCUMENT_REL = "http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument";
    private static final String STRICT_DOCUMENT_REL = "http://purl.oclc.org/ooxml/officeDocument/relationships/officeDocument";
    private static final List<XSLFRelation> SUPPORTED_XSLF_TYPES = Collections.unmodifiableList(Arrays.asList(XSLFRelation.MAIN, XSLFRelation.MACRO, XSLFRelation.MACRO_TEMPLATE, XSLFRelation.PRESENTATIONML, XSLFRelation.PRESENTATIONML_TEMPLATE, XSLFRelation.PRESENTATION_MACRO));
    private static final String VISIO_DOCUMENT_REL = "http://schemas.microsoft.com/visio/2010/relationships/document";

    @Override // org.apache.poi.extractor.ExtractorProvider
    public boolean accepts(FileMagic fm) {
        return fm == FileMagic.OOXML;
    }

    public static boolean getThreadPrefersEventExtractors() {
        return ExtractorFactory.getThreadPrefersEventExtractors();
    }

    public static Boolean getAllThreadsPreferEventExtractors() {
        return ExtractorFactory.getAllThreadsPreferEventExtractors();
    }

    public static void setThreadPrefersEventExtractors(boolean preferEventExtractors) {
        ExtractorFactory.setThreadPrefersEventExtractors(preferEventExtractors);
    }

    public static void removeThreadPrefersEventExtractorsSetting() {
        ExtractorFactory.removeThreadPrefersEventExtractorsSetting();
    }

    public static void setAllThreadsPreferEventExtractors(Boolean preferEventExtractors) {
        ExtractorFactory.setAllThreadsPreferEventExtractors(preferEventExtractors);
    }

    public static boolean getPreferEventExtractor() {
        return ExtractorFactory.getPreferEventExtractor();
    }

    @Override // org.apache.poi.extractor.ExtractorProvider
    public POITextExtractor create(File f, String password) throws IOException {
        if (FileMagic.valueOf(f) != FileMagic.OOXML) {
            return ExtractorFactory.createExtractor(f, password);
        }
        OPCPackage pkg = null;
        try {
            pkg = OPCPackage.open(f.toString(), PackageAccess.READ);
            POIXMLTextExtractor ex = create(pkg);
            if (ex == null) {
                pkg.revert();
            }
            return ex;
        } catch (IOException e) {
            if (pkg != null) {
                pkg.revert();
            }
            throw e;
        } catch (InvalidFormatException ife) {
            throw new IOException(ife);
        }
    }

    @Override // org.apache.poi.extractor.ExtractorProvider
    public POITextExtractor create(InputStream inp, String password) throws IOException {
        InputStream is = FileMagic.prepareToCheckMagic(inp);
        if (FileMagic.valueOf(is) != FileMagic.OOXML) {
            return ExtractorFactory.createExtractor(is, password);
        }
        OPCPackage pkg = null;
        try {
            pkg = OPCPackage.open(is);
            POIXMLTextExtractor ex = create(pkg);
            if (ex == null) {
                pkg.revert();
            }
            return ex;
        } catch (IOException e) {
            if (pkg != null) {
                pkg.revert();
            }
            throw e;
        } catch (RuntimeException e2) {
            if (pkg != null) {
                pkg.revert();
            }
            throw new IOException(e2);
        } catch (InvalidFormatException e3) {
            throw new IOException(e3);
        }
    }

    public POIXMLTextExtractor create(OPCPackage pkg) throws IOException {
        try {
            PackageRelationshipCollection core = pkg.getRelationshipsByType("http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument");
            if (core.isEmpty()) {
                core = pkg.getRelationshipsByType("http://purl.oclc.org/ooxml/officeDocument/relationships/officeDocument");
            }
            if (core.isEmpty()) {
                core = pkg.getRelationshipsByType("http://schemas.microsoft.com/visio/2010/relationships/document");
                if (core.size() == 1) {
                    return new XDGFVisioExtractor(pkg);
                }
            }
            if (core.size() != 1) {
                throw new IllegalArgumentException("Invalid OOXML Package received - expected 1 core document, found " + core.size());
            }
            PackagePart corePart = pkg.getPart(core.getRelationship(0));
            String contentType = corePart == null ? null : corePart.getContentType();
            for (XSSFRelation rel : XSSFExcelExtractor.SUPPORTED_TYPES) {
                if (rel.getContentType().equals(contentType)) {
                    if (getPreferEventExtractor()) {
                        return new XSSFEventBasedExcelExtractor(pkg);
                    }
                    return new XSSFExcelExtractor(pkg);
                }
            }
            for (XWPFRelation rel2 : XWPFWordExtractor.SUPPORTED_TYPES) {
                if (rel2.getContentType().equals(contentType)) {
                    return new XWPFWordExtractor(pkg);
                }
            }
            for (XSLFRelation rel3 : SUPPORTED_XSLF_TYPES) {
                if (rel3.getContentType().equals(contentType)) {
                    return new XSLFExtractor(new XMLSlideShow(pkg));
                }
            }
            if (XSLFRelation.THEME_MANAGER.getContentType().equals(contentType)) {
                return new XSLFExtractor(new XMLSlideShow(pkg));
            }
            for (XSSFRelation rel4 : XSSFBEventBasedExcelExtractor.SUPPORTED_TYPES) {
                if (rel4.getContentType().equals(contentType)) {
                    return new XSSFBEventBasedExcelExtractor(pkg);
                }
            }
            return null;
        } catch (RuntimeException | OpenXML4JException | XmlException e) {
            throw new IOException(e);
        }
    }

    public POITextExtractor create(POIFSFileSystem fs) throws IOException {
        return create(fs.getRoot(), Biff8EncryptionKey.getCurrentUserPassword());
    }

    @Override // org.apache.poi.extractor.ExtractorProvider
    public POITextExtractor create(DirectoryNode poifsDir, String password) throws IOException {
        if (poifsDir.hasEntryCaseInsensitive(ExtractorFactory.OOXML_PACKAGE)) {
            InputStream is = poifsDir.createDocumentInputStream(ExtractorFactory.OOXML_PACKAGE);
            try {
                POITextExtractor create = create(is, password);
                if (is != null) {
                    is.close();
                }
                return create;
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
        if (poifsDir.hasEntryCaseInsensitive(Decryptor.DEFAULT_POIFS_ENTRY)) {
            EncryptionInfo ei = new EncryptionInfo(poifsDir);
            Decryptor dec = ei.getDecryptor();
            try {
                if (!dec.verifyPassword(password)) {
                    throw new IOException("Invalid password specified");
                }
                try {
                    InputStream is2 = dec.getDataStream(poifsDir);
                    try {
                        POITextExtractor create2 = create(is2, password);
                        if (is2 != null) {
                            is2.close();
                        }
                        return create2;
                    } finally {
                    }
                } finally {
                    POIFSFileSystem fs = poifsDir.getFileSystem();
                    if (fs != null) {
                        fs.close();
                    }
                }
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                throw new IOException(e2);
            }
        } else {
            throw new IOException("The OLE2 file neither contained a plain OOXML package node (\"Package\") nor an encrypted one (\"EncryptedPackage\").");
        }
    }
}
