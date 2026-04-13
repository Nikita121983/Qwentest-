package org.apache.poi.poifs.dev;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.Iterator;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.DocumentNode;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.poifs.filesystem.POIFSStream;
import org.apache.poi.poifs.property.PropertyTable;
import org.apache.poi.poifs.storage.HeaderBlock;
import org.apache.poi.util.IOUtils;

/* loaded from: classes10.dex */
public final class POIFSDump {
    private POIFSDump() {
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Must specify at least one file to dump");
            System.exit(1);
        }
        int length = args.length;
        int i = 0;
        boolean dumpMini = false;
        boolean dumpMini2 = false;
        int i2 = 0;
        while (i2 < length) {
            String filename = args[i2];
            if (filename.equalsIgnoreCase("-dumprops") || filename.equalsIgnoreCase("-dump-props") || filename.equalsIgnoreCase("-dump-properties")) {
                dumpMini2 = true;
            } else if (filename.equalsIgnoreCase("-dumpmini") || filename.equalsIgnoreCase("-dump-mini") || filename.equalsIgnoreCase("-dump-ministream") || filename.equalsIgnoreCase("-dump-mini-stream")) {
                dumpMini = true;
            } else {
                System.out.println("Dumping " + filename);
                InputStream is = Files.newInputStream(Paths.get(filename, new String[i]), new OpenOption[i]);
                try {
                    POIFSFileSystem fs = new POIFSFileSystem(is);
                    try {
                        DirectoryEntry root = fs.getRoot();
                        String filenameWithoutPath = new File(filename).getName();
                        File dumpDir = new File(filenameWithoutPath + "_dump");
                        File file = IOUtils.newFile(dumpDir, root.getName());
                        if (!file.exists() && !file.mkdirs()) {
                            throw new IOException("Could not create directory " + file);
                        }
                        dump(root, file);
                        if (dumpMini2) {
                            HeaderBlock header = fs.getHeaderBlock();
                            dump(fs, header.getPropertyStart(), "properties", file);
                        }
                        if (dumpMini) {
                            PropertyTable props = fs.getPropertyTable();
                            int startBlock = props.getRoot().getStartBlock();
                            if (startBlock == -2) {
                                System.err.println("No Mini Stream in file");
                            } else {
                                dump(fs, startBlock, "mini-stream", file);
                            }
                        }
                        fs.close();
                        if (is != null) {
                            is.close();
                        }
                    } finally {
                    }
                } finally {
                }
            }
            i2++;
            i = 0;
        }
    }

    public static void dump(DirectoryEntry root, File parent) throws IOException {
        Iterator<Entry> it = root.getEntries();
        while (it.hasNext()) {
            Entry entry = it.next();
            if (entry instanceof DocumentNode) {
                DocumentNode node = (DocumentNode) entry;
                DocumentInputStream is = new DocumentInputStream(node);
                try {
                    byte[] bytes = IOUtils.toByteArray(is);
                    is.close();
                    OutputStream out = Files.newOutputStream(IOUtils.newFile(parent, node.getName().trim()).toPath(), new OpenOption[0]);
                    try {
                        out.write(bytes);
                        if (out != null) {
                            out.close();
                        }
                    } finally {
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        try {
                            is.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                        throw th2;
                    }
                }
            } else if (entry instanceof DirectoryEntry) {
                DirectoryEntry dir = (DirectoryEntry) entry;
                File file = IOUtils.newFile(parent, entry.getName());
                if (!file.exists() && !file.mkdirs()) {
                    throw new IOException("Could not create directory " + file);
                }
                dump(dir, file);
            } else {
                System.err.println("Skipping unsupported POIFS entry: " + entry);
            }
        }
    }

    public static void dump(POIFSFileSystem fs, int startBlock, String name, File parent) throws IOException {
        File file = IOUtils.newFile(parent, name);
        OutputStream out = Files.newOutputStream(file.toPath(), new OpenOption[0]);
        try {
            POIFSStream stream = new POIFSStream(fs, startBlock);
            byte[] b = IOUtils.safelyAllocate(fs.getBigBlockSize(), POIFSFileSystem.getMaxRecordLength());
            Iterator<ByteBuffer> it = stream.iterator();
            while (it.hasNext()) {
                ByteBuffer bb = it.next();
                int len = bb.remaining();
                bb.get(b);
                out.write(b, 0, len);
            }
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
}
