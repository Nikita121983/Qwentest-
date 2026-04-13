package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.xmlbeans.XmlError;

/* loaded from: classes11.dex */
public class XMLBean extends MatchingTask {
    private static final String JAVA = ".java";
    private static final String WSDL = ".wsdl";
    private static final String XSD = ".xsd";
    private static final String XSDCONFIG = ".xsdconfig";
    private String catalog;
    private File classgendir;
    private Path classpath;
    private String compiler;
    private boolean debug;
    private String debugLevel;
    private File destfile;
    private boolean download;
    private String forkedExecutable;
    private Set<String> mdefnamespaces;
    private String memoryInitialSize;
    private String memoryMaximumSize;
    private boolean noSrcRegen;
    private boolean noann;
    private boolean nopvr;
    private boolean noupa;
    private boolean novdoc;
    private boolean optimize;
    private String partialMethods;
    private boolean quiet;
    private String repackage;
    private File schema;
    private File srcgendir;
    private boolean srconly;
    private String typesystemname;
    private boolean verbose;
    private final List<FileSet> schemas = new ArrayList();
    private boolean noext = false;
    private boolean failonerror = true;
    private boolean fork = true;
    private boolean includeAntRuntime = true;
    private boolean includeJavaRuntime = false;
    private boolean nowarn = false;
    private final List<Extension> extensions = new ArrayList();
    private final Map<String, Set<File>> _extRouter = new HashMap(5);
    private String source = null;

    /* JADX WARN: Code restructure failed: missing block: B:145:0x0152, code lost:
    
        if (r27.classgendir == null) goto L58;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void execute() throws org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instructions count: 961
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.tool.XMLBean.execute():void");
    }

    private void processPaths(String[] paths, File baseDir) {
        for (String s : paths) {
            int dot = s.lastIndexOf(46);
            if (dot > -1) {
                String possExt = s.substring(dot).toLowerCase(Locale.ROOT);
                Set<File> set = this._extRouter.get(possExt);
                if (set != null) {
                    set.add(new File(baseDir, s));
                }
            }
        }
    }

    public void addFileset(FileSet fileset) {
        this.schemas.add(fileset);
    }

    public File getSchema() {
        return this.schema;
    }

    public void setSchema(File schema) {
        this.schema = schema;
    }

    public void setClasspath(Path classpath) {
        if (this.classpath != null) {
            this.classpath.append(classpath);
        } else {
            this.classpath = classpath;
        }
    }

    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(getProject());
        }
        return this.classpath.createPath();
    }

    public void setClasspathRef(Reference classpathref) {
        if (this.classpath == null) {
            this.classpath = new Path(getProject());
        }
        this.classpath.createPath().setRefid(classpathref);
    }

    public Path getClasspath() {
        return this.classpath;
    }

    public File getDestfile() {
        return this.destfile;
    }

    public void setDestfile(File destfile) {
        this.destfile = destfile;
    }

    public File getSrcgendir() {
        return this.srcgendir;
    }

    public void setSrcgendir(File srcgendir) {
        this.srcgendir = srcgendir;
    }

    public File getClassgendir() {
        return this.classgendir;
    }

    public void setClassgendir(File classgendir) {
        this.classgendir = classgendir;
    }

    public void setCompiler(String compiler) {
        this.compiler = compiler;
    }

    public boolean isDownload() {
        return this.download;
    }

    public void setDownload(boolean download) {
        this.download = download;
    }

    public void setOptimize(boolean optimize) {
        this.optimize = optimize;
    }

    public boolean getOptimize() {
        return this.optimize;
    }

    public boolean isVerbose() {
        return this.verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public boolean isQuiet() {
        return this.quiet;
    }

    public void setQuiet(boolean quiet) {
        this.quiet = quiet;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public String getDebugLevel() {
        return this.debugLevel;
    }

    public void setDebugLevel(String v) {
        this.debugLevel = v;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setFork(boolean f) {
        this.fork = f;
    }

    public void setExecutable(String forkExec) {
        this.forkedExecutable = forkExec;
    }

    public String getExecutable() {
        return this.forkedExecutable;
    }

    public boolean isSrconly() {
        return this.srconly;
    }

    public void setSrconly(boolean srconly) {
        this.srconly = srconly;
    }

    public String getTypesystemname() {
        return this.typesystemname;
    }

    public Extension createExtension() {
        Extension e = new Extension();
        this.extensions.add(e);
        return e;
    }

    public void setIgnoreDuplicatesInNamespaces(String namespaces) {
        this.mdefnamespaces = new HashSet();
        StringTokenizer st = new StringTokenizer(namespaces, CollectionUtils.COMMA);
        while (st.hasMoreTokens()) {
            String namespace = st.nextToken().trim();
            this.mdefnamespaces.add(namespace);
        }
    }

    public String getIgnoreDuplicatesInNamespaces() {
        if (this.mdefnamespaces == null) {
            return null;
        }
        return String.join(CollectionUtils.COMMA, this.mdefnamespaces);
    }

    public void setTypesystemname(String typesystemname) {
        this.typesystemname = typesystemname;
    }

    public boolean isFailonerror() {
        return this.failonerror;
    }

    public void setFailonerror(boolean failonerror) {
        this.failonerror = failonerror;
    }

    public boolean isIncludeAntRuntime() {
        return this.includeAntRuntime;
    }

    public void setIncludeAntRuntime(boolean includeAntRuntime) {
        this.includeAntRuntime = includeAntRuntime;
    }

    public boolean isIncludeJavaRuntime() {
        return this.includeJavaRuntime;
    }

    public void setIncludeJavaRuntime(boolean includeJavaRuntime) {
        this.includeJavaRuntime = includeJavaRuntime;
    }

    public boolean isNowarn() {
        return this.nowarn;
    }

    public void setNowarn(boolean nowarn) {
        this.nowarn = nowarn;
    }

    public boolean isNoSrcRegen() {
        return this.noSrcRegen;
    }

    public void setNoSrcRegen(boolean noSrcRegen) {
        this.noSrcRegen = noSrcRegen;
    }

    public String getMemoryInitialSize() {
        return this.memoryInitialSize;
    }

    public void setMemoryInitialSize(String memoryInitialSize) {
        this.memoryInitialSize = memoryInitialSize;
    }

    public String getMemoryMaximumSize() {
        return this.memoryMaximumSize;
    }

    public void setMemoryMaximumSize(String memoryMaximumSize) {
        this.memoryMaximumSize = memoryMaximumSize;
    }

    public void setNoUpa(boolean noupa) {
        this.noupa = noupa;
    }

    public boolean isNoUpa() {
        return this.noupa;
    }

    public void setNoPvr(boolean nopvr) {
        this.nopvr = nopvr;
    }

    public boolean isNoPvr() {
        return this.nopvr;
    }

    public void setNoAnnotations(boolean noann) {
        this.noann = noann;
    }

    public boolean isNoAnnotations() {
        return this.noann;
    }

    public void setNoValidateDoc(boolean novdoc) {
        this.novdoc = novdoc;
    }

    public boolean isNoValidateDoc() {
        return this.novdoc;
    }

    public void setNoExt(boolean noext) {
        this.noext = noext;
    }

    public boolean isNoExt() {
        return this.noext;
    }

    public void setSource(String s) {
        this.source = s;
    }

    public String getCatalog() {
        return this.catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getRepackage() {
        return this.repackage;
    }

    public void setRepackage(String repackage) {
        this.repackage = repackage;
    }

    public String getPartialMethods() {
        return this.partialMethods;
    }

    public void setPartialMethods(String partialMethods) {
        this.partialMethods = partialMethods;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static URI uriFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            return f.getCanonicalFile().toURI();
        } catch (IOException e) {
            return f.getAbsoluteFile().toURI();
        }
    }

    /* loaded from: classes11.dex */
    public class ErrorLogger extends AbstractCollection<XmlError> {
        private final URI _baseURI;
        private final boolean _noisy;

        public ErrorLogger(boolean noisy) {
            this._noisy = noisy;
            this._baseURI = XMLBean.uriFromFile(XMLBean.this.getProject().getBaseDir());
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(XmlError err) {
            if (err.getSeverity() == 0) {
                XMLBean.this.log(err.toString(this._baseURI), 0);
            } else if (err.getSeverity() == 1) {
                XMLBean.this.log(err.toString(this._baseURI), 1);
            } else if (this._noisy) {
                XMLBean.this.log(err.toString(this._baseURI), 2);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<XmlError> iterator() {
            return Collections.emptyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return 0;
        }
    }
}
