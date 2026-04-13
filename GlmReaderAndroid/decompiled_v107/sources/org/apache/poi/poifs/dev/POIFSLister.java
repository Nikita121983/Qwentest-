package org.apache.poi.poifs.dev;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.Iterator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentNode;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/* loaded from: classes10.dex */
public class POIFSLister {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Must specify at least one file to view");
            System.exit(1);
        }
        boolean withSizes = false;
        boolean newPOIFS = true;
        for (String arg : args) {
            if (arg.equalsIgnoreCase("-size") || arg.equalsIgnoreCase("-sizes")) {
                withSizes = true;
            } else if (arg.equalsIgnoreCase("-old") || arg.equalsIgnoreCase("-old-poifs")) {
                newPOIFS = false;
            } else if (newPOIFS) {
                viewFile(arg, withSizes);
            } else {
                viewFileOld(arg, withSizes);
            }
        }
    }

    public static void viewFile(String filename, boolean withSizes) throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem(new File(filename));
        try {
            displayDirectory(fs.getRoot(), "", withSizes);
            fs.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    fs.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public static void viewFileOld(String filename, boolean withSizes) throws IOException {
        InputStream fis = Files.newInputStream(Paths.get(filename, new String[0]), new OpenOption[0]);
        try {
            POIFSFileSystem fs = new POIFSFileSystem(fis);
            try {
                displayDirectory(fs.getRoot(), "", withSizes);
                fs.close();
                if (fis != null) {
                    fis.close();
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static void displayDirectory(DirectoryNode dir, String indent, boolean withSizes) {
        System.out.println(indent + dir.getName() + " -");
        String newIndent = indent + "  ";
        boolean hadChildren = false;
        Iterator<Entry> it = dir.getEntries();
        while (it.hasNext()) {
            hadChildren = true;
            Entry entry = it.next();
            if (entry instanceof DirectoryNode) {
                displayDirectory((DirectoryNode) entry, newIndent, withSizes);
            } else {
                DocumentNode doc = (DocumentNode) entry;
                String name = doc.getName();
                String size = "";
                if (name.charAt(0) < '\n') {
                    String altname = "(0x0" + ((int) name.charAt(0)) + ")" + name.substring(1);
                    name = name.substring(1) + " <" + altname + ">";
                }
                if (withSizes) {
                    size = " [" + doc.getSize() + " / 0x" + Integer.toHexString(doc.getSize()) + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
                }
                System.out.println(newIndent + name + size);
            }
        }
        if (!hadChildren) {
            System.out.println(newIndent + "(no children)");
        }
    }
}
