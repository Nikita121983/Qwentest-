package org.apache.poi.sl.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.sl.usermodel.ObjectMetaData;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.util.IOUtils;

/* loaded from: classes10.dex */
public interface ObjectShape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> extends Shape<S, P>, PlaceableShape<S, P> {
    String getFullName();

    ObjectData getObjectData();

    PictureData getPictureData();

    String getProgId();

    OutputStream updateObjectData(ObjectMetaData.Application application, ObjectMetaData objectMetaData) throws IOException;

    default InputStream readObjectData() throws IOException {
        String progId = getProgId();
        if (progId == null) {
            throw new IllegalStateException("Ole object hasn't been initialized or provided in the source xml. use updateObjectData() first or check the corresponding slideXXX.xml");
        }
        ObjectMetaData.Application app = ObjectMetaData.Application.lookup(progId);
        UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().get();
        try {
            InputStream is = FileMagic.prepareToCheckMagic(readObjectDataRaw());
            try {
                FileMagic fm = FileMagic.valueOf(is);
                if (fm == FileMagic.OLE2) {
                    POIFSFileSystem poifs = new POIFSFileSystem(is);
                    try {
                        String[] names = new String[5];
                        int i = 0;
                        names[0] = app == null ? null : app.getMetaData().getOleEntry();
                        names[1] = ExtractorFactory.OOXML_PACKAGE;
                        names[2] = "Contents";
                        names[3] = "CONTENTS";
                        names[4] = "CONTENTSV30";
                        DirectoryNode root = poifs.getRoot();
                        String entryName = null;
                        int length = names.length;
                        while (true) {
                            if (i >= length) {
                                break;
                            }
                            String n = names[i];
                            if (!root.hasEntryCaseInsensitive(n)) {
                                i++;
                            } else {
                                entryName = n;
                                break;
                            }
                        }
                        if (entryName == null) {
                            poifs.writeFilesystem(bos);
                        } else {
                            InputStream is2 = poifs.createDocumentInputStream(entryName);
                            try {
                                IOUtils.copy(is2, bos);
                                if (is2 != null) {
                                    is2.close();
                                }
                            } finally {
                            }
                        }
                        poifs.close();
                    } finally {
                    }
                } else {
                    IOUtils.copy(is, bos);
                }
                InputStream inputStream = bos.toInputStream();
                if (is != null) {
                    is.close();
                }
                if (bos != null) {
                    bos.close();
                }
                return inputStream;
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

    default InputStream readObjectDataRaw() throws IOException {
        return getObjectData().getInputStream();
    }
}
