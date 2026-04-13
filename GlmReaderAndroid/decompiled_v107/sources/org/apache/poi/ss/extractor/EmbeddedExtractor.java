package org.apache.poi.ss.extractor;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.hpsf.ClassIDPredefined;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.Ole10Native;
import org.apache.poi.poifs.filesystem.Ole10NativeException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.ObjectData;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Shape;
import org.apache.poi.ss.usermodel.ShapeContainer;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public class EmbeddedExtractor implements Iterable<EmbeddedExtractor> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String CONTENT_TYPE_BYTES = "binary/octet-stream";
    private static final String CONTENT_TYPE_DOC = "application/msword";
    private static final String CONTENT_TYPE_PDF = "application/pdf";
    private static final String CONTENT_TYPE_XLS = "application/vnd.ms-excel";
    private static final int DEFAULT_MAX_RECORD_LENGTH = 1000000;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) EmbeddedExtractor.class);
    private static int MAX_RECORD_LENGTH = 1000000;

    public static void setMaxRecordLength(int length) {
        MAX_RECORD_LENGTH = length;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    @Override // java.lang.Iterable
    public Iterator<EmbeddedExtractor> iterator() {
        EmbeddedExtractor[] ee = {new Ole10Extractor(), new PdfExtractor(), new BiffExtractor(), new OOXMLExtractor(), new FsExtractor()};
        return Arrays.asList(ee).iterator();
    }

    public EmbeddedData extractOne(DirectoryNode src) throws IOException {
        Iterator<EmbeddedExtractor> it = iterator();
        while (it.hasNext()) {
            EmbeddedExtractor ee = it.next();
            if (ee.canExtract(src)) {
                return ee.extract(src);
            }
        }
        return null;
    }

    public EmbeddedData extractOne(Picture src) throws IOException {
        Iterator<EmbeddedExtractor> it = iterator();
        while (it.hasNext()) {
            EmbeddedExtractor ee = it.next();
            if (ee.canExtract(src)) {
                return ee.extract(src);
            }
        }
        return null;
    }

    public List<EmbeddedData> extractAll(Sheet sheet) throws IOException {
        Drawing<?> patriarch = sheet.getDrawingPatriarch();
        if (patriarch == null) {
            return Collections.emptyList();
        }
        List<EmbeddedData> embeddings = new ArrayList<>();
        extractAll(patriarch, embeddings);
        return embeddings;
    }

    protected void extractAll(ShapeContainer<?> parent, List<EmbeddedData> embeddings) throws IOException {
        EmbeddedData data;
        Iterator<T> it = parent.iterator();
        while (it.hasNext()) {
            Shape shape = (Shape) it.next();
            EmbeddedData data2 = null;
            if (shape instanceof ObjectData) {
                ObjectData od = (ObjectData) shape;
                try {
                    if (od.hasDirectoryEntry()) {
                        data = extractOne((DirectoryNode) od.getDirectory());
                    } else {
                        data = new EmbeddedData(od.getFileName(), od.getObjectData(), od.getContentType());
                    }
                    data2 = data;
                } catch (Exception e) {
                    LOG.atWarn().withThrowable(e).log("Entry not found / readable - ignoring OLE embedding");
                }
            } else if (shape instanceof Picture) {
                data2 = extractOne((Picture) shape);
            } else if (shape instanceof ShapeContainer) {
                extractAll((ShapeContainer) shape, embeddings);
            }
            if (data2 != null) {
                data2.setShape(shape);
                String filename = data2.getFilename();
                String extension = (filename == null || filename.lastIndexOf(46) == -1) ? ".bin" : filename.substring(filename.lastIndexOf(46));
                if ((filename == null || filename.isEmpty() || filename.startsWith("MBD") || filename.startsWith("Root Entry")) && (filename = shape.getShapeName()) != null) {
                    filename = filename + extension;
                }
                if (filename == null || filename.isEmpty()) {
                    filename = "picture_" + embeddings.size() + extension;
                }
                data2.setFilename(filename.trim());
                embeddings.add(data2);
            }
        }
    }

    public boolean canExtract(DirectoryNode source) {
        return false;
    }

    public boolean canExtract(Picture source) {
        return false;
    }

    protected EmbeddedData extract(DirectoryNode dn) throws IOException {
        if (!canExtract(dn)) {
            throw new AssertionError();
        }
        UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().setBufferSize(AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH).get();
        try {
            POIFSFileSystem dest = new POIFSFileSystem();
            try {
                copyNodes(dn, dest.getRoot());
                dest.writeFilesystem(bos);
                EmbeddedData embeddedData = new EmbeddedData(dn.getName(), bos.toByteArray(), CONTENT_TYPE_BYTES);
                dest.close();
                if (bos != null) {
                    bos.close();
                }
                return embeddedData;
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    protected EmbeddedData extract(Picture source) throws IOException {
        return null;
    }

    /* loaded from: classes10.dex */
    public static class Ole10Extractor extends EmbeddedExtractor {
        @Override // org.apache.poi.ss.extractor.EmbeddedExtractor
        public boolean canExtract(DirectoryNode dn) {
            ClassID clsId = dn.getStorageClsid();
            return ClassIDPredefined.lookup(clsId) == ClassIDPredefined.OLE_V1_PACKAGE;
        }

        @Override // org.apache.poi.ss.extractor.EmbeddedExtractor
        public EmbeddedData extract(DirectoryNode dn) throws IOException {
            try {
                Ole10Native ole10 = Ole10Native.createFromEmbeddedOleObject(dn);
                return new EmbeddedData(ole10.getFileName(), ole10.getDataBuffer(), EmbeddedExtractor.CONTENT_TYPE_BYTES);
            } catch (Ole10NativeException e) {
                throw new IOException(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class PdfExtractor extends EmbeddedExtractor {
        PdfExtractor() {
        }

        @Override // org.apache.poi.ss.extractor.EmbeddedExtractor
        public boolean canExtract(DirectoryNode dn) {
            ClassID clsId = dn.getStorageClsid();
            return ClassIDPredefined.PDF.equals(clsId) || dn.hasEntryCaseInsensitive("CONTENTS");
        }

        @Override // org.apache.poi.ss.extractor.EmbeddedExtractor
        public EmbeddedData extract(DirectoryNode dn) throws IOException {
            UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().get();
            try {
                InputStream is = dn.createDocumentInputStream("CONTENTS");
                try {
                    IOUtils.copy(is, bos);
                    EmbeddedData embeddedData = new EmbeddedData(dn.getName() + ".pdf", bos.toByteArray(), EmbeddedExtractor.CONTENT_TYPE_PDF);
                    if (is != null) {
                        is.close();
                    }
                    if (bos != null) {
                        bos.close();
                    }
                    return embeddedData;
                } finally {
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (bos != null) {
                        try {
                            bos.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }

        @Override // org.apache.poi.ss.extractor.EmbeddedExtractor
        public boolean canExtract(Picture source) {
            PictureData pd = source.getPictureData();
            return pd != null && pd.getPictureType() == 2;
        }

        @Override // org.apache.poi.ss.extractor.EmbeddedExtractor
        protected EmbeddedData extract(Picture source) throws IOException {
            int idxEnd;
            PictureData pd = source.getPictureData();
            if (pd == null || pd.getPictureType() != 2) {
                return null;
            }
            byte[] pictureBytes = pd.getData();
            int idxStart = EmbeddedExtractor.indexOf(pictureBytes, 0, "%PDF-".getBytes(LocaleUtil.CHARSET_1252));
            if (idxStart == -1 || (idxEnd = EmbeddedExtractor.indexOf(pictureBytes, idxStart, "%%EOF".getBytes(LocaleUtil.CHARSET_1252))) == -1) {
                return null;
            }
            int pictureBytesLen = (idxEnd - idxStart) + 6;
            byte[] pdfBytes = IOUtils.safelyClone(pictureBytes, idxStart, pictureBytesLen, EmbeddedExtractor.MAX_RECORD_LENGTH);
            String filename = source.getShapeName().trim();
            if (!StringUtil.endsWithIgnoreCase(filename, ".pdf")) {
                filename = filename + ".pdf";
            }
            return new EmbeddedData(filename, pdfBytes, EmbeddedExtractor.CONTENT_TYPE_PDF);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class OOXMLExtractor extends EmbeddedExtractor {
        OOXMLExtractor() {
        }

        @Override // org.apache.poi.ss.extractor.EmbeddedExtractor
        public boolean canExtract(DirectoryNode dn) {
            return dn.hasEntryCaseInsensitive("package");
        }

        @Override // org.apache.poi.ss.extractor.EmbeddedExtractor
        public EmbeddedData extract(DirectoryNode dn) throws IOException {
            ClassIDPredefined clsId = ClassIDPredefined.lookup(dn.getStorageClsid());
            String contentType = null;
            String ext = null;
            if (clsId != null) {
                contentType = clsId.getContentType();
                ext = clsId.getFileExtension();
            }
            if (contentType == null || ext == null) {
                contentType = "application/zip";
                ext = ".zip";
            }
            DocumentInputStream dis = dn.createDocumentInputStream("package");
            byte[] data = IOUtils.toByteArray(dis);
            dis.close();
            return new EmbeddedData(dn.getName() + ext, data, contentType);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class BiffExtractor extends EmbeddedExtractor {
        BiffExtractor() {
        }

        @Override // org.apache.poi.ss.extractor.EmbeddedExtractor
        public boolean canExtract(DirectoryNode dn) {
            return canExtractExcel(dn) || canExtractWord(dn);
        }

        protected boolean canExtractExcel(DirectoryNode dn) {
            ClassIDPredefined clsId = ClassIDPredefined.lookup(dn.getStorageClsid());
            return ClassIDPredefined.EXCEL_V7 == clsId || ClassIDPredefined.EXCEL_V8 == clsId || dn.hasEntryCaseInsensitive("Workbook");
        }

        protected boolean canExtractWord(DirectoryNode dn) {
            ClassIDPredefined clsId = ClassIDPredefined.lookup(dn.getStorageClsid());
            return ClassIDPredefined.WORD_V7 == clsId || ClassIDPredefined.WORD_V8 == clsId || dn.hasEntryCaseInsensitive("WordDocument");
        }

        @Override // org.apache.poi.ss.extractor.EmbeddedExtractor
        public EmbeddedData extract(DirectoryNode dn) throws IOException {
            EmbeddedData ed = super.extract(dn);
            if (canExtractExcel(dn)) {
                ed.setFilename(dn.getName() + ".xls");
                ed.setContentType(EmbeddedExtractor.CONTENT_TYPE_XLS);
            } else if (canExtractWord(dn)) {
                ed.setFilename(dn.getName() + ".doc");
                ed.setContentType(EmbeddedExtractor.CONTENT_TYPE_DOC);
            }
            return ed;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class FsExtractor extends EmbeddedExtractor {
        FsExtractor() {
        }

        @Override // org.apache.poi.ss.extractor.EmbeddedExtractor
        public boolean canExtract(DirectoryNode dn) {
            return true;
        }

        @Override // org.apache.poi.ss.extractor.EmbeddedExtractor
        public EmbeddedData extract(DirectoryNode dn) throws IOException {
            EmbeddedData ed = super.extract(dn);
            ed.setFilename(dn.getName() + ".ole");
            return ed;
        }
    }

    protected static void copyNodes(DirectoryNode src, DirectoryNode dest) throws IOException {
        Iterator<Entry> it = src.iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            if (e instanceof DirectoryNode) {
                DirectoryNode srcDir = (DirectoryNode) e;
                DirectoryNode destDir = (DirectoryNode) dest.createDirectory(srcDir.getName());
                destDir.setStorageClsid(srcDir.getStorageClsid());
                copyNodes(srcDir, destDir);
            } else {
                InputStream is = src.createDocumentInputStream(e);
                try {
                    dest.createDocument(e.getName(), is);
                    if (is != null) {
                        is.close();
                    }
                } finally {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(byte[] data, int offset, byte[] pattern) {
        int[] failure = computeFailure(pattern);
        int j = 0;
        if (data.length == 0) {
            return -1;
        }
        for (int i = offset; i < data.length; i++) {
            while (j > 0 && pattern[j] != data[i]) {
                j = failure[j - 1];
            }
            if (pattern[j] == data[i]) {
                j++;
            }
            if (j == pattern.length) {
                return (i - pattern.length) + 1;
            }
        }
        return -1;
    }

    private static int[] computeFailure(byte[] pattern) {
        int[] failure = new int[pattern.length];
        int j = 0;
        for (int i = 1; i < pattern.length; i++) {
            while (j > 0 && pattern[j] != pattern[i]) {
                j = failure[j - 1];
            }
            if (pattern[j] == pattern[i]) {
                j++;
            }
            failure[i] = j;
        }
        return failure;
    }
}
