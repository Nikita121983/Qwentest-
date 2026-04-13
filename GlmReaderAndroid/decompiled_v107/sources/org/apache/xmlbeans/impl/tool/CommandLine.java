package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.impl.common.DefaultClassLoaderResourceLoader;
import org.apache.xmlbeans.impl.common.IOUtil;

/* loaded from: classes11.dex */
public class CommandLine {
    private static final File[] EMPTY_FILEARRAY = new File[0];
    private static final URL[] EMPTY_URLARRAY = new URL[0];
    private String[] _args;
    private String[] _badopts;
    private File _baseDir;
    private List<File> _files;
    private Map<String, String> _options;
    private List<URL> _urls;

    public CommandLine(String[] args, Collection<String> flags, Collection<String> scheme) {
        if (flags == null || scheme == null) {
            throw new IllegalArgumentException("collection required (use Collections.EMPTY_SET if no options)");
        }
        this._options = new LinkedHashMap();
        ArrayList<String> badopts = new ArrayList<>();
        ArrayList<String> endargs = new ArrayList<>();
        int i = 0;
        while (i < args.length) {
            if (args[i].indexOf(45) == 0) {
                String opt = args[i].substring(1);
                String val = null;
                if (flags.contains(opt)) {
                    val = "";
                } else if (scheme.contains(opt)) {
                    if (i + 1 < args.length) {
                        i++;
                        val = args[i];
                    } else {
                        val = "";
                    }
                } else {
                    badopts.add(args[i]);
                }
                this._options.put(opt, val);
            } else {
                endargs.add(args[i]);
            }
            i++;
        }
        int i2 = badopts.size();
        this._badopts = (String[]) badopts.toArray(new String[i2]);
        this._args = (String[]) endargs.toArray(new String[endargs.size()]);
    }

    public static void printLicense() {
        try {
            IOUtil.copyCompletely(new DefaultClassLoaderResourceLoader().getResourceAsStream("LICENSE.txt"), System.out);
        } catch (Exception e) {
            System.out.println("License available in this JAR in LICENSE.txt");
        }
    }

    public static void printVersion() {
        System.out.println(XmlBeans.getVendor() + ", " + XmlBeans.getTitle() + ".XmlBeans version " + XmlBeans.getVersion());
    }

    public String[] args() {
        String[] result = new String[this._args.length];
        System.arraycopy(this._args, 0, result, 0, this._args.length);
        return result;
    }

    public String[] getBadOpts() {
        return this._badopts;
    }

    public String getOpt(String opt) {
        return this._options.get(opt);
    }

    private static List<File> collectFiles(File[] dirs) {
        List<File> files = new ArrayList<>();
        for (File f : dirs) {
            if (!f.isDirectory()) {
                files.add(f);
            } else {
                files.addAll(collectFiles(f.listFiles()));
            }
        }
        return files;
    }

    private List<File> getFileList() {
        if (this._files == null) {
            String[] args = args();
            File[] files = new File[args.length];
            boolean noBaseDir = false;
            for (int i = 0; i < args.length; i++) {
                files[i] = new File(args[i]);
                if (!noBaseDir && this._baseDir == null) {
                    if (files[i].isDirectory()) {
                        this._baseDir = files[i];
                    } else {
                        this._baseDir = files[i].getParentFile();
                    }
                } else {
                    URI currUri = files[i].toURI();
                    if (this._baseDir != null && this._baseDir.toURI().relativize(currUri).equals(currUri)) {
                        this._baseDir = null;
                        noBaseDir = true;
                    }
                }
            }
            this._files = Collections.unmodifiableList(collectFiles(files));
        }
        return this._files;
    }

    private List<URL> getUrlList() {
        if (this._urls == null) {
            String[] args = args();
            List<URL> urls = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                if (looksLikeURL(args[i])) {
                    try {
                        urls.add(new URL(args[i]));
                    } catch (MalformedURLException mfEx) {
                        System.err.println("ignoring invalid url: " + args[i] + ": " + mfEx.getMessage());
                    }
                }
            }
            this._urls = Collections.unmodifiableList(urls);
        }
        return this._urls;
    }

    private static boolean looksLikeURL(String str) {
        return str.startsWith("http:") || str.startsWith("https:") || str.startsWith("ftp:") || str.startsWith("file:");
    }

    public URL[] getURLs() {
        return (URL[]) getUrlList().toArray(EMPTY_URLARRAY);
    }

    public File[] getFiles() {
        return (File[]) getFileList().toArray(EMPTY_FILEARRAY);
    }

    public File getBaseDir() {
        return this._baseDir;
    }

    public File[] filesEndingWith(String ext) {
        List<File> result = new ArrayList<>();
        for (File f : getFileList()) {
            if (f.getName().endsWith(ext) && !looksLikeURL(f.getPath())) {
                result.add(f);
            }
        }
        return (File[]) result.toArray(EMPTY_FILEARRAY);
    }
}
