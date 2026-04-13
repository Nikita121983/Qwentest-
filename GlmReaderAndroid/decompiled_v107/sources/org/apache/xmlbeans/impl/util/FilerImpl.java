package org.apache.xmlbeans.impl.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.xmlbeans.Filer;
import org.apache.xmlbeans.impl.repackage.Repackager;

/* loaded from: classes11.dex */
public class FilerImpl implements Filer {
    private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
    private final File classdir;
    private final boolean incrSrcGen;
    private final Repackager repackager;
    private Set<String> seenTypes;
    private final List<File> sourceFiles = new ArrayList();
    private final File srcdir;
    private final boolean verbose;

    public FilerImpl(File classdir, File srcdir, Repackager repackager, boolean verbose, boolean incrSrcGen) {
        this.classdir = classdir;
        this.srcdir = srcdir;
        this.repackager = repackager;
        this.verbose = verbose;
        this.incrSrcGen = incrSrcGen;
        if (this.incrSrcGen) {
            this.seenTypes = new HashSet();
        }
    }

    @Override // org.apache.xmlbeans.Filer
    public OutputStream createBinaryFile(String typename) throws IOException {
        if (this.verbose) {
            System.err.println("created binary: " + typename);
        }
        File source = new File(this.classdir, typename);
        source.getParentFile().mkdirs();
        return Files.newOutputStream(source.toPath(), new OpenOption[0]);
    }

    @Override // org.apache.xmlbeans.Filer
    public Writer createSourceFile(String typename, String sourceCodeEncoding) throws IOException {
        if (this.incrSrcGen) {
            this.seenTypes.add(typename);
        }
        if (typename.indexOf(36) > 0) {
            typename = typename.substring(0, typename.lastIndexOf(46)) + "." + typename.substring(typename.indexOf(36) + 1);
        }
        String filename = typename.replace('.', File.separatorChar) + ".java";
        File sourcefile = new File(this.srcdir, filename);
        sourcefile.getParentFile().mkdirs();
        if (this.verbose) {
            System.err.println("created source: " + sourcefile.getAbsolutePath());
        }
        this.sourceFiles.add(sourcefile);
        if (this.incrSrcGen && sourcefile.exists()) {
            return new IncrFileWriter(sourcefile, this.repackager);
        }
        return this.repackager == null ? writerForFile(sourcefile, sourceCodeEncoding) : new RepackagingWriter(sourcefile, this.repackager);
    }

    public List<File> getSourceFiles() {
        return new ArrayList(this.sourceFiles);
    }

    public Repackager getRepackager() {
        return this.repackager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Writer writerForFile(File f, String sourceCodeEncoding) throws IOException {
        if (sourceCodeEncoding == null || sourceCodeEncoding.isEmpty()) {
            OutputStream fileStream = Files.newOutputStream(f.toPath(), new OpenOption[0]);
            CharsetEncoder ce = DEFAULT_CHARSET.newEncoder();
            ce.onUnmappableCharacter(CodingErrorAction.REPORT);
            return new OutputStreamWriter(fileStream, ce);
        }
        return Files.newBufferedWriter(f.toPath(), getCharset(sourceCodeEncoding), new OpenOption[0]);
    }

    private static Charset getCharset(String sourceCodeEncoding) throws IOException {
        try {
            return Charset.forName(sourceCodeEncoding);
        } catch (RuntimeException e) {
            throw new IOException("Unsupported encoding: " + sourceCodeEncoding, e);
        }
    }

    /* loaded from: classes11.dex */
    static class IncrFileWriter extends StringWriter {
        private final File _file;
        private final Repackager _repackager;

        public IncrFileWriter(File file, Repackager repackager) {
            this._file = file;
            this._repackager = repackager;
        }

        @Override // java.io.StringWriter, java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            StringBuffer sb;
            super.close();
            if (this._repackager != null) {
                sb = this._repackager.repackage(getBuffer());
            } else {
                sb = getBuffer();
            }
            String str = sb.toString();
            List<String> diffs = new ArrayList<>();
            StringReader sReader = new StringReader(str);
            try {
                Reader fReader = Files.newBufferedReader(this._file.toPath(), StandardCharsets.ISO_8859_1);
                try {
                    Diff.readersAsText(sReader, "<generated>", fReader, this._file.getName(), diffs);
                    if (fReader != null) {
                        fReader.close();
                    }
                    sReader.close();
                    if (!diffs.isEmpty()) {
                        Writer fw = FilerImpl.writerForFile(this._file, null);
                        try {
                            fw.write(str);
                            if (fw != null) {
                                fw.close();
                            }
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                if (fw != null) {
                                    try {
                                        fw.close();
                                    } catch (Throwable th3) {
                                        th.addSuppressed(th3);
                                    }
                                }
                                throw th2;
                            }
                        }
                    }
                } finally {
                }
            } catch (Throwable th4) {
                try {
                    throw th4;
                } catch (Throwable th5) {
                    try {
                        sReader.close();
                    } catch (Throwable th6) {
                        th4.addSuppressed(th6);
                    }
                    throw th5;
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    static class RepackagingWriter extends StringWriter {
        private final File _file;
        private final Repackager _repackager;

        public RepackagingWriter(File file, Repackager repackager) {
            this._file = file;
            this._repackager = repackager;
        }

        @Override // java.io.StringWriter, java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            Writer fw = FilerImpl.writerForFile(this._file, null);
            try {
                fw.write(this._repackager.repackage(getBuffer()).toString());
                if (fw != null) {
                    fw.close();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (fw != null) {
                        try {
                            fw.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
    }
}
