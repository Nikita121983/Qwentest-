package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.impl.common.IOUtil;

/* loaded from: classes11.dex */
public class SchemaResourceManager extends BaseSchemaResourceManager {
    private File _directory;

    public static void printUsage() {
        System.out.println("Maintains \"xsdownload.xml\", an index of locally downloaded .xsd files");
        System.out.println("usage: sdownload [-dir directory] [-refresh] [-recurse] [-sync] [url/file...]");
        System.out.println("");
        System.out.println("URLs that are specified are downloaded if they aren't already cached.");
        System.out.println("In addition:");
        System.out.println("  -dir specifies the directory for the xsdownload.xml file (default .).");
        System.out.println("  -sync synchronizes the index to any local .xsd files in the tree.");
        System.out.println("  -recurse recursively downloads imported and included .xsd files.");
        System.out.println("  -refresh redownloads all indexed .xsd files.");
        System.out.println("If no files or URLs are specified, all indexed files are relevant.");
    }

    public static void main(String[] args) throws IOException {
        String dir;
        if (args.length == 0) {
            printUsage();
            System.exit(0);
            return;
        }
        Set<String> flags = new HashSet<>();
        flags.add("h");
        flags.add("help");
        flags.add("usage");
        flags.add("license");
        flags.add("version");
        flags.add("sync");
        flags.add("refresh");
        flags.add("recurse");
        Set<String> opts = new HashSet<>();
        opts.add("dir");
        CommandLine cl = new CommandLine(args, flags, opts);
        if (cl.getOpt("h") != null || cl.getOpt("help") != null || cl.getOpt("usage") != null) {
            printUsage();
            System.exit(0);
            return;
        }
        String[] badopts = cl.getBadOpts();
        if (badopts.length > 0) {
            for (String str : badopts) {
                System.out.println("Unrecognized option: " + str);
            }
            printUsage();
            System.exit(0);
            return;
        }
        if (cl.getOpt("license") != null) {
            CommandLine.printLicense();
            System.exit(0);
            return;
        }
        if (cl.getOpt("version") != null) {
            CommandLine.printVersion();
            System.exit(0);
            return;
        }
        String[] args2 = cl.args();
        boolean sync = cl.getOpt("sync") != null;
        boolean refresh = cl.getOpt("refresh") != null;
        boolean imports = cl.getOpt("recurse") != null;
        String dir2 = cl.getOpt("dir");
        if (dir2 != null) {
            dir = dir2;
        } else {
            dir = ".";
        }
        File directory = new File(dir);
        try {
            SchemaResourceManager mgr = new SchemaResourceManager(directory);
            List<String> uriList = new ArrayList<>();
            List<File> fileList = new ArrayList<>();
            for (int i = 0; i < args2.length; i++) {
                if (looksLikeURL(args2[i])) {
                    uriList.add(args2[i]);
                } else {
                    fileList.add(new File(directory, args2[i]));
                }
            }
            Iterator<File> i2 = fileList.iterator();
            while (i2.hasNext()) {
                File file = i2.next();
                if (!isInDirectory(file, directory)) {
                    System.err.println("File not within directory: " + file);
                    i2.remove();
                }
            }
            List<File> fileList2 = collectXSDFiles((File[]) fileList.toArray(new File[0]));
            String[] uris = (String[]) uriList.toArray(new String[0]);
            File[] files = (File[]) fileList2.toArray(new File[0]);
            String[] filenames = relativeFilenames(files, directory);
            if (uris.length + filenames.length > 0) {
                mgr.process(uris, filenames, sync, refresh, imports);
            } else {
                mgr.processAll(sync, refresh, imports);
            }
            mgr.writeCache();
            System.exit(0);
        } catch (IllegalStateException e) {
            if (e.getMessage() != null) {
                System.out.println(e.getMessage());
            } else {
                e.printStackTrace();
            }
            System.exit(1);
        }
    }

    private static boolean looksLikeURL(String str) {
        return str.startsWith("http:") || str.startsWith("https:") || str.startsWith("ftp:") || str.startsWith("file:");
    }

    private static String relativeFilename(File file, File directory) {
        if (file == null || file.equals(directory)) {
            return ".";
        }
        return relativeFilename(file.getParentFile(), directory) + PackagingURIHelper.FORWARD_SLASH_STRING + file.getName();
    }

    private static String[] relativeFilenames(File[] files, File directory) {
        String[] result = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            result[i] = relativeFilename(files[i], directory);
        }
        return result;
    }

    private static boolean isInDirectory(File file, File dir) {
        if (file == null) {
            return false;
        }
        if (file.equals(dir)) {
            return true;
        }
        return isInDirectory(file.getParentFile(), dir);
    }

    public SchemaResourceManager(File directory) {
        this._directory = directory;
        init();
    }

    @Override // org.apache.xmlbeans.impl.tool.BaseSchemaResourceManager
    protected void warning(String msg) {
        System.out.println(msg);
    }

    @Override // org.apache.xmlbeans.impl.tool.BaseSchemaResourceManager
    protected boolean fileExists(String filename) {
        return new File(this._directory, filename).exists();
    }

    @Override // org.apache.xmlbeans.impl.tool.BaseSchemaResourceManager
    protected InputStream inputStreamForFile(String filename) throws IOException {
        return Files.newInputStream(new File(this._directory, filename).toPath(), new OpenOption[0]);
    }

    @Override // org.apache.xmlbeans.impl.tool.BaseSchemaResourceManager
    protected void writeInputStreamToFile(InputStream input, String filename) throws IOException {
        File targetFile = new File(this._directory, filename);
        File parent = targetFile.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        OutputStream output = Files.newOutputStream(targetFile.toPath(), new OpenOption[0]);
        IOUtil.copyCompletely(input, output);
    }

    @Override // org.apache.xmlbeans.impl.tool.BaseSchemaResourceManager
    protected void deleteFile(String filename) {
        new File(this._directory, filename).delete();
    }

    @Override // org.apache.xmlbeans.impl.tool.BaseSchemaResourceManager
    protected String[] getAllXSDFilenames() {
        File[] allFiles = (File[]) collectXSDFiles(new File[]{this._directory}).toArray(new File[0]);
        return relativeFilenames(allFiles, this._directory);
    }

    private static List collectXSDFiles(File[] dirs) {
        List<File> files = new ArrayList<>();
        for (File f : dirs) {
            if (!f.isDirectory()) {
                files.add(f);
            } else {
                files.addAll(collectXSDFiles(f.listFiles(new FileFilter() { // from class: org.apache.xmlbeans.impl.tool.SchemaResourceManager.1
                    @Override // java.io.FileFilter
                    public boolean accept(File file) {
                        return file.isDirectory() || (file.isFile() && file.getName().endsWith(".xsd"));
                    }
                })));
            }
        }
        return files;
    }
}
