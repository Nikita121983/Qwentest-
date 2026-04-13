package org.apache.xmlbeans.impl.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.attribute.FileAttribute;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.SystemProperties;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.apache.xmlbeans.impl.util.ExceptionUtil;

/* loaded from: classes11.dex */
public class CodeGenUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String DEFAULT_COMPILER = "javac";
    public static final String DEFAULT_MEM_MAX = "256m";
    public static final String DEFAULT_MEM_START = "8m";

    public static URI resolve(URI base, URI child) {
        URI ruri = base.resolve(child);
        if ("file".equals(ruri.getScheme()) && !child.equals(ruri) && base.getPath().startsWith("//") && !ruri.getPath().startsWith("//")) {
            String path = "///".concat(ruri.getPath());
            try {
                return new URI("file", null, path, ruri.getQuery(), ruri.getFragment());
            } catch (URISyntaxException e) {
                return ruri;
            }
        }
        return ruri;
    }

    static void addAllJavaFiles(List<File> srcFiles, List<String> args) {
        for (File f : srcFiles) {
            if (f.isDirectory()) {
                File[] files = f.listFiles(new FileFilter() { // from class: org.apache.xmlbeans.impl.tool.CodeGenUtil$$ExternalSyntheticLambda0
                    @Override // java.io.FileFilter
                    public final boolean accept(File file) {
                        return CodeGenUtil.lambda$addAllJavaFiles$0(file);
                    }
                });
                if (files != null) {
                    addAllJavaFiles(Arrays.asList(files), args);
                }
            } else {
                args.add(quoteAndEscapeFilename(f.getAbsolutePath()));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$addAllJavaFiles$0(File file) {
        return (file.isFile() && file.getName().endsWith(".java")) || file.isDirectory();
    }

    private static String quoteAndEscapeFilename(String filename) {
        if (!filename.contains(StringUtils.SPACE)) {
            return filename;
        }
        return "\"" + filename.replaceAll("\\\\", "\\\\\\\\") + "\"";
    }

    public static boolean externalCompile(List<File> srcFiles, File outdir, File[] cp, boolean debug) {
        return externalCompile(srcFiles, outdir, cp, debug, DEFAULT_COMPILER, null, DEFAULT_MEM_START, DEFAULT_MEM_MAX, false, false, null);
    }

    public static boolean externalCompile(List<File> srcFiles, File outdir, File[] cp, boolean debug, String javacPath, String memStart, String memMax, boolean quiet, boolean verbose) {
        return externalCompile(srcFiles, outdir, cp, debug, javacPath, null, memStart, memMax, quiet, verbose, null);
    }

    public static boolean externalCompile(List<File> srcFiles, File outdir, File[] cp, boolean debug, String javacPath, String memStart, String memMax, boolean quiet, boolean verbose, String sourceCodeEncoding) {
        return externalCompile(srcFiles, outdir, cp, debug, javacPath, null, memStart, memMax, quiet, verbose, sourceCodeEncoding);
    }

    public static boolean externalCompile(List<File> srcFiles, File outdir, File[] cp, boolean debug, String javacPath, String genver, String memStart, String memMax, boolean quiet, boolean verbose) {
        return externalCompile(srcFiles, outdir, cp, debug, javacPath, genver, memStart, memMax, quiet, verbose, null);
    }

    public static boolean externalCompile(List<File> srcFiles, File outdir, File[] cp, boolean debug, String javacPath, String genver, String memStart, String memMax, boolean quiet, boolean verbose, String sourceCodeEncoding) {
        File outdir2;
        boolean z;
        List<String> args = new ArrayList<>();
        File javac = findJavaTool(javacPath == null ? DEFAULT_COMPILER : javacPath);
        if (!javac.exists()) {
            throw new AssertionError("compiler not found " + javac);
        }
        args.add(javac.getAbsolutePath());
        if (outdir == null) {
            outdir2 = new File(".");
        } else {
            args.add("-d");
            args.add(quoteAndEscapeFilename(outdir.getAbsolutePath()));
            outdir2 = outdir;
        }
        File[] cp2 = cp == null ? systemClasspath() : cp;
        if (sourceCodeEncoding != null && !sourceCodeEncoding.isEmpty()) {
            args.add("-encoding");
            args.add(sourceCodeEncoding);
        }
        boolean z2 = false;
        if (cp2.length > 0) {
            StringBuilder classPath = new StringBuilder();
            classPath.append(outdir2.getAbsolutePath());
            for (File file : cp2) {
                classPath.append(File.pathSeparator);
                classPath.append(file.getAbsolutePath());
            }
            args.add("-classpath");
            args.add(quoteAndEscapeFilename(classPath.toString()));
        }
        String genver2 = genver == null ? "1.8" : genver;
        args.add("-source");
        args.add(genver2);
        args.add("-target");
        args.add(genver2);
        args.add(debug ? "-g" : "-g:none");
        if (verbose) {
            args.add("-verbose");
        }
        addAllJavaFiles(srcFiles, args);
        File clFile = null;
        try {
            clFile = Files.createTempFile(IOUtil.getTempDir(), DEFAULT_COMPILER, ".tmp", new FileAttribute[0]).toFile();
            Writer fw = Files.newBufferedWriter(clFile.toPath(), Charset.defaultCharset(), new OpenOption[0]);
            try {
                Iterator<String> i = args.iterator();
                i.next();
                while (i.hasNext()) {
                    String arg = i.next();
                    fw.write(arg);
                    fw.write(10);
                }
                if (fw != null) {
                    fw.close();
                }
                List<String> newargs = new ArrayList<>();
                newargs.add(args.get(0));
                if (memStart != null && !memStart.isEmpty()) {
                    newargs.add("-J-Xms" + memStart);
                }
                if (memMax != null && !memMax.isEmpty()) {
                    newargs.add("-J-Xmx" + memMax);
                }
                newargs.add("@" + clFile.getAbsolutePath());
                args = newargs;
            } finally {
            }
        } catch (Exception e) {
            System.err.println("Could not create command-line file for javac");
        }
        try {
            String[] strArgs = (String[]) args.toArray(new String[0]);
            if (verbose) {
                System.out.print("compile command:");
                int length = strArgs.length;
                int i2 = 0;
                while (i2 < length) {
                    String strArg = strArgs[i2];
                    z = z2;
                    try {
                        System.out.print(StringUtils.SPACE + strArg);
                        i2++;
                        z2 = z;
                    } catch (Throwable th) {
                        e = th;
                        try {
                            if (ExceptionUtil.isFatal(e)) {
                                ExceptionUtil.rethrow(e);
                            }
                            System.err.println(e.toString());
                            System.err.println(e.getCause());
                            e.printStackTrace(System.err);
                            return z;
                        } finally {
                            if (!debug && clFile != null) {
                                clFile.delete();
                            }
                        }
                    }
                }
                z = z2;
                System.out.println();
            } else {
                z = false;
            }
            Process proc = Runtime.getRuntime().exec(strArgs);
            StringBuilder errorBuffer = new StringBuilder();
            StringBuilder outputBuffer = new StringBuilder();
            copy(proc.getInputStream(), outputBuffer);
            copy(proc.getErrorStream(), errorBuffer);
            proc.waitFor();
            if (verbose || proc.exitValue() != 0) {
                if (outputBuffer.length() > 0) {
                    System.out.println(outputBuffer.toString());
                    System.out.flush();
                }
                if (errorBuffer.length() > 0) {
                    System.err.println(errorBuffer.toString());
                    System.err.flush();
                }
                if (proc.exitValue() != 0) {
                    if (!debug && clFile != null) {
                        clFile.delete();
                    }
                    return z;
                }
            }
            if (debug || clFile == null) {
                return true;
            }
            clFile.delete();
            return true;
        } catch (Throwable th2) {
            e = th2;
            z = z2;
        }
    }

    public static File[] systemClasspath() {
        List<File> cp = new ArrayList<>();
        CodeSource cs = CodeGenUtil.class.getProtectionDomain().getCodeSource();
        if (cs != null) {
            cp.add(new File(cs.getLocation().getPath()));
        } else {
            System.err.println("Can't determine path of xmlbeans-*.jar - specify classpath explicitly!");
        }
        String jcp = SystemProperties.getProperty(org.apache.commons.lang3.SystemProperties.JAVA_CLASS_PATH);
        if (jcp != null) {
            String[] systemcp = jcp.split(File.pathSeparator);
            for (String s : systemcp) {
                cp.add(new File(s));
            }
        }
        return (File[]) cp.toArray(new File[0]);
    }

    private static File findJavaTool(String tool) {
        File toolFile = new File(tool);
        if (toolFile.isFile()) {
            return toolFile;
        }
        File result = new File(tool + ".exe");
        if (result.isFile()) {
            return result;
        }
        String home = SystemProperties.getProperty("java.home");
        String sep = File.separator;
        File result2 = new File(home + sep + ".." + sep + "bin", tool);
        if (result2.isFile()) {
            return result2;
        }
        File result3 = new File(result2.getPath() + ".exe");
        if (result3.isFile()) {
            return result3;
        }
        File result4 = new File(home + sep + "bin", tool);
        if (result4.isFile()) {
            return result4;
        }
        File result5 = new File(result4.getPath() + ".exe");
        if (result5.isFile()) {
            return result5;
        }
        return toolFile;
    }

    private static Thread copy(InputStream stream, final StringBuilder output) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(stream, Charset.defaultCharset()));
        Thread readerThread = new Thread(new Runnable() { // from class: org.apache.xmlbeans.impl.tool.CodeGenUtil$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                reader.lines().forEach(new Consumer() { // from class: org.apache.xmlbeans.impl.tool.CodeGenUtil$$ExternalSyntheticLambda1
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        r1.append((String) obj).append(StringUtils.LF);
                    }
                });
            }
        });
        readerThread.start();
        return readerThread;
    }
}
