package org.apache.poi.openxml4j.opc.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.openxml4j.exceptions.OLE2NotOfficeXmlFileException;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.ZipPackage;
import org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Reproducibility;

@Internal
/* loaded from: classes10.dex */
public final class ZipHelper {
    private static final String FORWARD_SLASH = "/";

    private ZipHelper() {
    }

    public static ZipArchiveEntry getCorePropertiesZipEntry(ZipPackage pkg) {
        PackageRelationship corePropsRel = pkg.getRelationshipsByType(PackageRelationshipTypes.CORE_PROPERTIES).getRelationship(0);
        if (corePropsRel == null) {
            return null;
        }
        ZipArchiveEntry entry = new ZipArchiveEntry(corePropsRel.getTargetURI().getPath());
        adjustEntryTime(entry);
        return entry;
    }

    public static String getOPCNameFromZipItemName(String zipItemName) {
        if (zipItemName == null) {
            throw new IllegalArgumentException("zipItemName cannot be null");
        }
        if (!zipItemName.startsWith("/")) {
            return "/" + zipItemName;
        }
        return zipItemName;
    }

    public static String getZipItemNameFromOPCName(String opcItemName) {
        if (opcItemName == null) {
            throw new IllegalArgumentException("opcItemName cannot be null");
        }
        String retVal = opcItemName;
        while (retVal.startsWith("/")) {
            retVal = retVal.substring(1);
        }
        return retVal;
    }

    public static URI getZipURIFromOPCName(String opcItemName) {
        if (opcItemName == null) {
            throw new IllegalArgumentException("opcItemName");
        }
        String retVal = opcItemName;
        while (retVal.startsWith("/")) {
            retVal = retVal.substring(1);
        }
        try {
            return new URI(retVal);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private static void verifyZipHeader(InputStream stream) throws NotOfficeXmlFileException, IOException {
        InputStream is = FileMagic.prepareToCheckMagic(stream);
        FileMagic fm = FileMagic.valueOf(is);
        switch (fm) {
            case OLE2:
                throw new OLE2NotOfficeXmlFileException("The supplied data appears to be in the OLE2 Format. You are calling the part of POI that deals with OOXML (Office Open XML) Documents. You need to call a different part of POI to process this data (eg HSSF instead of XSSF)");
            case XML:
                throw new NotOfficeXmlFileException("The supplied data appears to be a raw XML file. Formats such as Office 2003 XML are not supported");
            default:
                return;
        }
    }

    public static ZipArchiveThresholdInputStream openZipStream(InputStream stream) throws IOException {
        return openZipStream(stream, true);
    }

    public static ZipArchiveThresholdInputStream openZipStream(InputStream stream, boolean closeStream) throws IOException {
        InputStream checkedStream = FileMagic.prepareToCheckMagic(stream);
        verifyZipHeader(checkedStream);
        InputStream processStream = closeStream ? checkedStream : new NoCloseInputStream(checkedStream);
        return new ZipArchiveThresholdInputStream(new ZipArchiveInputStream(processStream, StandardCharsets.UTF_8.name(), false, true));
    }

    public static ZipSecureFile openZipFile(File file) throws IOException, NotOfficeXmlFileException {
        if (!file.exists()) {
            throw new FileNotFoundException("File does not exist");
        }
        if (file.isDirectory()) {
            throw new IOException("File is a directory");
        }
        InputStream input = Files.newInputStream(file.toPath(), new OpenOption[0]);
        try {
            verifyZipHeader(input);
            if (input != null) {
                input.close();
            }
            return new ZipSecureFile(file);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (input != null) {
                    try {
                        input.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static ZipSecureFile openZipFile(String path) throws IOException {
        return openZipFile(new File(path));
    }

    public static void adjustEntryTime(ZipArchiveEntry entry) {
        if (Reproducibility.isSourceDateEpoch()) {
            entry.setTime(0L);
        }
    }
}
