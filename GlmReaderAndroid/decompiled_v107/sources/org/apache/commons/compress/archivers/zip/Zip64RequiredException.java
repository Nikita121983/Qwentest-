package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;

/* loaded from: classes9.dex */
public class Zip64RequiredException extends ZipException {
    static final String ARCHIVE_TOO_BIG_MESSAGE = "Archive's size exceeds the limit of 4GByte.";
    static final String CENTRAL_DIRECTORY_DISK_NUMBER_TOO_BIG_MESSAGE = "Number of the disk with the start of Central Directory exceeds the limit of 65535.";
    static final String CENTRAL_DIRECTORY_SIZE_TOO_BIG_MESSAGE = "The size of the entire central directory exceeds the limit of 4GByte.";
    static final String DISK_NUMBER_TOO_BIG_MESSAGE = "Number of the disk of End Of Central Directory exceeds the limit of 65535.";
    static final String TOO_MANY_ENTRIES_MESSAGE = "Archive contains more than 65535 entries.";
    static final String TOO_MANY_ENTRIES_ON_DISK_MESSAGE = "Number of entries on this disk exceeds the limit of 65535.";
    private static final long serialVersionUID = 20110809;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getEntryTooBigMessage(ZipArchiveEntry ze) {
        return ze.getName() + "'s size exceeds the limit of 4GByte.";
    }

    public Zip64RequiredException(String message) {
        super(message);
    }
}
