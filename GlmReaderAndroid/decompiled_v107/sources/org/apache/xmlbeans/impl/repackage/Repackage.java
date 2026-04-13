package org.apache.xmlbeans.impl.repackage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes11.dex */
public class Repackage {
    private List<List<String>> _fromPackages;
    private List<String> _moveAlongFiles;
    private Map<String, String> _movedDirs;
    private Pattern _packagePattern;
    private final Repackager _repackager;
    private int _skippedFiles;
    private final File _sourceBase;
    private final File _targetBase;
    private List<List<String>> _toPackages;

    public static void main(String[] args) throws Exception {
        new Repackage(args).repackage();
    }

    private Repackage(String[] args) {
        String sourceDir = null;
        String targetDir = null;
        String repackageSpec = null;
        boolean failure = false;
        int i = 0;
        while (true) {
            if (i >= args.length) {
                break;
            }
            if (args[i].equals("-repackage") && i + 1 < args.length) {
                i++;
                repackageSpec = args[i];
            } else if (args[i].equals("-f") && i + 1 < args.length) {
                i++;
                sourceDir = args[i];
            } else if (args[i].equals("-t") && i + 1 < args.length) {
                i++;
                targetDir = args[i];
            } else {
                failure = true;
            }
            i++;
        }
        if (!failure && repackageSpec != null) {
            if (!((sourceDir == null) ^ (targetDir == null))) {
                this._repackager = new Repackager(repackageSpec);
                if (sourceDir == null || targetDir == null) {
                    this._targetBase = null;
                    this._sourceBase = null;
                    return;
                } else {
                    this._sourceBase = new File(sourceDir);
                    this._targetBase = new File(targetDir);
                    return;
                }
            }
        }
        throw new RuntimeException("Usage: repackage -repackage [spec] [ -f [sourcedir] -t [targetdir] ]");
    }

    public void repackage() throws Exception {
        if (this._sourceBase == null || this._targetBase == null) {
            System.out.println(this._repackager.repackage(readInputStream(System.in)).toString());
            return;
        }
        this._fromPackages = this._repackager.getFromPackages();
        this._toPackages = this._repackager.getToPackages();
        this._packagePattern = Pattern.compile("^\\s*package\\s+((?:\\w|\\.)*)\\s*;", 8);
        this._moveAlongFiles = new ArrayList();
        this._movedDirs = new HashMap();
        this._targetBase.mkdirs();
        List<File> files = new ArrayList<>();
        fillFiles(files, this._sourceBase);
        System.out.println("Repackaging " + files.size() + " files ...");
        int prefixLength = this._sourceBase.getCanonicalPath().length();
        for (File from : files) {
            String name = from.getCanonicalPath().substring(prefixLength + 1);
            repackageFile(name);
        }
        finishMovingFiles();
        if (this._skippedFiles > 0) {
            System.out.println("Skipped " + this._skippedFiles + " unmodified files.");
        }
    }

    public void repackageFile(String name) throws IOException {
        if (name.endsWith(".java")) {
            repackageJavaFile(name);
            return;
        }
        if (name.endsWith(".xsdconfig") || name.endsWith(".xml") || name.endsWith(".g")) {
            repackageNonJavaFile(name);
        } else if (name.startsWith("bin" + File.separatorChar)) {
            repackageNonJavaFile(name);
        } else {
            moveAlongWithJavaFiles(name);
        }
    }

    public void moveAlongWithJavaFiles(String name) {
        this._moveAlongFiles.add(name);
    }

    public void finishMovingFiles() throws IOException {
        for (String name : this._moveAlongFiles) {
            String srcDir = Repackager.dirForPath(name);
            String toDir = this._movedDirs.get(srcDir);
            String toName = toDir == null ? name : new File(toDir, new File(name).getName()).toString();
            if (name.endsWith(".html")) {
                repackageNonJavaFile(name, toName);
            } else {
                justMoveNonJavaFile(name, toName);
            }
        }
    }

    public void repackageNonJavaFile(String name) throws IOException {
        File sourceFile = new File(this._sourceBase, name);
        File targetFile = new File(this._targetBase, name);
        if (sourceFile.lastModified() < targetFile.lastModified()) {
            this._skippedFiles++;
        } else {
            writeFile(targetFile, this._repackager.repackage(readFile(sourceFile)));
        }
    }

    public void repackageNonJavaFile(String sourceName, String targetName) throws IOException {
        File sourceFile = new File(this._sourceBase, sourceName);
        File targetFile = new File(this._targetBase, targetName);
        if (sourceFile.lastModified() < targetFile.lastModified()) {
            this._skippedFiles++;
        } else {
            writeFile(targetFile, this._repackager.repackage(readFile(sourceFile)));
        }
    }

    public void justMoveNonJavaFile(String sourceName, String targetName) throws IOException {
        File sourceFile = new File(this._sourceBase, sourceName);
        File targetFile = new File(this._targetBase, targetName);
        if (sourceFile.lastModified() < targetFile.lastModified()) {
            this._skippedFiles++;
        } else {
            copyFile(sourceFile, targetFile);
        }
    }

    public void repackageJavaFile(String name) throws IOException {
        File sourceFile;
        String pkg;
        String name2 = name;
        File sourceFile2 = new File(this._sourceBase, name2);
        StringBuffer sb = readFile(sourceFile2);
        Matcher packageMatcher = this._packagePattern.matcher(sb);
        boolean z = true;
        if (!packageMatcher.find()) {
            sourceFile = sourceFile2;
        } else {
            String pkg2 = packageMatcher.group(1);
            int pkgStart = packageMatcher.start(1);
            int pkgEnd = packageMatcher.end(1);
            if (packageMatcher.find()) {
                throw new RuntimeException("Two package specifications found: " + name2);
            }
            List<String> filePath = Repackager.splitPath(name2, File.separatorChar);
            String srcDir = Repackager.dirForPath(name2);
            while (true) {
                boolean swapped = false;
                int i = 1;
                while (i < filePath.size()) {
                    String spec1 = filePath.get(i - 1);
                    String spec2 = filePath.get(i);
                    boolean z2 = z;
                    if (spec1.indexOf(58) < spec2.indexOf(58)) {
                        filePath.set(i - 1, spec2);
                        filePath.set(i, spec1);
                        swapped = true;
                    }
                    i++;
                    z = z2;
                }
                boolean z3 = z;
                if (!swapped) {
                    break;
                } else {
                    z = z3;
                }
            }
            List<String> pkgPath = Repackager.splitPath(pkg2, '.');
            int f = filePath.size() - 2;
            if (f < 0 || filePath.size() - 1 < pkgPath.size()) {
                throw new RuntimeException("Package spec differs from file path: " + name2);
            }
            for (int i2 = pkgPath.size() - 1; i2 >= 0; i2--) {
                if (!pkgPath.get(i2).equals(filePath.get(f))) {
                    throw new RuntimeException("Package spec differs from file path: " + name2);
                }
                f--;
            }
            List<String> changeTo = null;
            List<String> changeFrom = null;
            int i3 = 0;
            loop3: while (true) {
                if (i3 >= this._fromPackages.size()) {
                    sourceFile = sourceFile2;
                    break;
                }
                List<String> from = this._fromPackages.get(i3);
                sourceFile = sourceFile2;
                Matcher packageMatcher2 = packageMatcher;
                if (from.size() > pkgPath.size()) {
                    pkg = pkg2;
                } else {
                    int j = 0;
                    while (j < from.size()) {
                        pkg = pkg2;
                        if (!from.get(j).equals(pkgPath.get(j))) {
                            break;
                        }
                        j++;
                        pkg2 = pkg;
                    }
                    changeFrom = from;
                    List<String> changeTo2 = this._toPackages.get(i3);
                    changeTo = changeTo2;
                    break loop3;
                }
                i3++;
                sourceFile2 = sourceFile;
                packageMatcher = packageMatcher2;
                pkg2 = pkg;
            }
            if (changeTo != null) {
                String newPkg = "";
                String newName = "";
                for (int i4 = 0; i4 < changeTo.size(); i4++) {
                    if (i4 > 0) {
                        newPkg = newPkg + ".";
                        newName = newName + File.separatorChar;
                    }
                    newPkg = newPkg + changeTo.get(i4);
                    newName = newName + changeTo.get(i4);
                }
                int i5 = filePath.size();
                for (int i6 = (i5 - pkgPath.size()) - 2; i6 >= 0; i6--) {
                    newName = filePath.get(i6) + File.separatorChar + newName;
                }
                for (int i7 = changeFrom.size(); i7 < pkgPath.size(); i7++) {
                    newName = newName + File.separatorChar + pkgPath.get(i7);
                    newPkg = newPkg + '.' + pkgPath.get(i7);
                }
                String newName2 = newName + File.separatorChar + filePath.get(filePath.size() - 1);
                sb.replace(pkgStart, pkgEnd, newPkg);
                name2 = newName2;
                String newDir = Repackager.dirForPath(name2);
                if (!srcDir.equals(newDir)) {
                    this._movedDirs.put(srcDir, newDir);
                }
            }
        }
        File targetFile = new File(this._targetBase, name2);
        if (sourceFile.lastModified() < targetFile.lastModified()) {
            this._skippedFiles++;
        } else {
            writeFile(new File(this._targetBase, name2), this._repackager.repackage(sb));
        }
    }

    void writeFile(File f, StringBuffer chars) throws IOException {
        f.getParentFile().mkdirs();
        Writer w = Files.newBufferedWriter(f.toPath(), StandardCharsets.ISO_8859_1, new OpenOption[0]);
        try {
            Reader r = new StringReader(chars.toString());
            try {
                copy(r, w);
                r.close();
                if (w != null) {
                    w.close();
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (w != null) {
                    try {
                        w.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    StringBuffer readFile(File f) throws IOException {
        Reader r = Files.newBufferedReader(f.toPath(), StandardCharsets.ISO_8859_1);
        try {
            StringWriter w = new StringWriter();
            try {
                copy(r, w);
                StringBuffer buffer = w.getBuffer();
                w.close();
                if (r != null) {
                    r.close();
                }
                return buffer;
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (r != null) {
                    try {
                        r.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    StringBuffer readInputStream(InputStream is) throws IOException {
        Reader r = new InputStreamReader(is, StandardCharsets.ISO_8859_1);
        try {
            StringWriter w = new StringWriter();
            try {
                copy(r, w);
                StringBuffer buffer = w.getBuffer();
                w.close();
                r.close();
                return buffer;
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    r.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public static void copyFile(File from, File to) throws IOException {
        to.getParentFile().mkdirs();
        InputStream in = Files.newInputStream(from.toPath(), new OpenOption[0]);
        try {
            OutputStream out = Files.newOutputStream(to.toPath(), new OpenOption[0]);
            try {
                copy(in, out);
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[16384];
        while (true) {
            int n = in.read(buffer, 0, buffer.length);
            if (n >= 0) {
                out.write(buffer, 0, n);
            } else {
                return;
            }
        }
    }

    public static void copy(Reader r, Writer w) throws IOException {
        char[] buffer = new char[16384];
        while (true) {
            int n = r.read(buffer, 0, buffer.length);
            if (n >= 0) {
                w.write(buffer, 0, n);
            } else {
                return;
            }
        }
    }

    public void fillFiles(List<File> files, File file) {
        if (!file.isDirectory()) {
            files.add(file);
            return;
        }
        if (file.getName().equals("build") || file.getName().equals("CVS")) {
            return;
        }
        String[] entries = file.list();
        if (entries == null) {
            throw new RuntimeException("Directory can't be accessed: " + file.toString());
        }
        for (String entry : entries) {
            fillFiles(files, new File(file, entry));
        }
    }

    public void recursiveDelete(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            String[] entries = file.list();
            if (entries == null) {
                throw new RuntimeException("Directory can't be accessed: " + file.toString());
            }
            for (String entry : entries) {
                recursiveDelete(new File(file, entry));
            }
        }
        file.delete();
    }
}
