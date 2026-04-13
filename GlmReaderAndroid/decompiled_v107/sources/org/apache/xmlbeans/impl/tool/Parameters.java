package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.apache.xmlbeans.SchemaCodePrinter;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlOptions;
import org.xml.sax.EntityResolver;

/* loaded from: classes11.dex */
public class Parameters {
    private File baseDir;
    private String catalogFile;
    private File classesDir;
    private File[] classpath;
    private String compiler;
    private File[] configFiles;
    private boolean copyAnn;
    private boolean debug;
    private boolean download;
    private EntityResolver entityResolver;
    private Collection<XmlError> errorListener;
    private boolean incrementalSrcGen;
    private File[] javaFiles;
    private String memoryInitialSize;
    private String memoryMaximumSize;
    private String name;
    private boolean noAnn;
    private boolean noExt;
    private boolean noPvr;
    private boolean noUpa;
    private boolean noVDoc;
    private boolean nojavac;
    private File outputJar;
    private boolean quiet;
    private String repackage;
    private SchemaCodePrinter schemaCodePrinter;
    private String sourceCodeEncoding;
    private File srcDir;
    private URL[] urlFiles;
    private boolean verbose;
    private File[] wsdlFiles;
    private File[] xsdFiles;
    private List<Extension> extensions = Collections.emptyList();
    private Set<String> mdefNamespaces = Collections.emptySet();
    private Set<XmlOptions.BeanMethod> partialMethods = Collections.emptySet();

    public File getBaseDir() {
        return this.baseDir;
    }

    public void setBaseDir(File baseDir) {
        this.baseDir = baseDir;
    }

    public File[] getXsdFiles() {
        return this.xsdFiles;
    }

    public void setXsdFiles(File... xsdFiles) {
        this.xsdFiles = xsdFiles == null ? null : (File[]) xsdFiles.clone();
    }

    public File[] getWsdlFiles() {
        return this.wsdlFiles;
    }

    public void setWsdlFiles(File... wsdlFiles) {
        this.wsdlFiles = wsdlFiles == null ? null : (File[]) wsdlFiles.clone();
    }

    public File[] getJavaFiles() {
        return this.javaFiles;
    }

    public void setJavaFiles(File... javaFiles) {
        this.javaFiles = javaFiles == null ? null : (File[]) javaFiles.clone();
    }

    public File[] getConfigFiles() {
        return this.configFiles;
    }

    public void setConfigFiles(File... configFiles) {
        this.configFiles = configFiles == null ? null : (File[]) configFiles.clone();
    }

    public URL[] getUrlFiles() {
        return this.urlFiles;
    }

    public void setUrlFiles(URL... urlFiles) {
        this.urlFiles = urlFiles == null ? null : (URL[]) urlFiles.clone();
    }

    public File[] getClasspath() {
        return this.classpath;
    }

    public void setClasspath(File... classpath) {
        this.classpath = classpath == null ? null : (File[]) classpath.clone();
    }

    public File getOutputJar() {
        return this.outputJar;
    }

    public void setOutputJar(File outputJar) {
        this.outputJar = outputJar;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getSrcDir() {
        return this.srcDir;
    }

    public void setSrcDir(File srcDir) {
        this.srcDir = srcDir;
    }

    public File getClassesDir() {
        return this.classesDir;
    }

    public void setClassesDir(File classesDir) {
        this.classesDir = classesDir;
    }

    public boolean isNojavac() {
        return this.nojavac;
    }

    public void setNojavac(boolean nojavac) {
        this.nojavac = nojavac;
    }

    public boolean isQuiet() {
        return this.quiet;
    }

    public void setQuiet(boolean quiet) {
        this.quiet = quiet;
    }

    public boolean isVerbose() {
        return this.verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public boolean isDownload() {
        return this.download;
    }

    public void setDownload(boolean download) {
        this.download = download;
    }

    public boolean isNoUpa() {
        return this.noUpa;
    }

    public void setNoUpa(boolean noUpa) {
        this.noUpa = noUpa;
    }

    public boolean isNoPvr() {
        return this.noPvr;
    }

    public void setNoPvr(boolean noPvr) {
        this.noPvr = noPvr;
    }

    public boolean isNoAnn() {
        return this.noAnn;
    }

    public String getSourceCodeEncoding() {
        return this.sourceCodeEncoding;
    }

    public void setNoAnn(boolean noAnn) {
        this.noAnn = noAnn;
    }

    public boolean isNoVDoc() {
        return this.noVDoc;
    }

    public void setNoVDoc(boolean newNoVDoc) {
        this.noVDoc = newNoVDoc;
    }

    public boolean isNoExt() {
        return this.noExt;
    }

    public void setNoExt(boolean newNoExt) {
        this.noExt = newNoExt;
    }

    public boolean isIncrementalSrcGen() {
        return this.incrementalSrcGen;
    }

    public void setIncrementalSrcGen(boolean incrSrcGen) {
        this.incrementalSrcGen = incrSrcGen;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setSourceCodeEncoding(String sourceCodeEncoding) {
        this.sourceCodeEncoding = sourceCodeEncoding;
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

    public String getCompiler() {
        return this.compiler;
    }

    public void setCompiler(String compiler) {
        this.compiler = compiler;
    }

    public Collection<XmlError> getErrorListener() {
        return this.errorListener;
    }

    public void setErrorListener(Collection<XmlError> errorListener) {
        this.errorListener = errorListener;
    }

    public String getRepackage() {
        return this.repackage;
    }

    public void setRepackage(String newRepackage) {
        this.repackage = newRepackage;
    }

    public boolean isCopyAnn() {
        return this.copyAnn;
    }

    public void setCopyAnn(boolean newCopyAnn) {
        this.copyAnn = newCopyAnn;
    }

    public List<Extension> getExtensions() {
        return this.extensions;
    }

    public void setExtensions(List<Extension> extensions) {
        this.extensions = extensions;
    }

    public Set<String> getMdefNamespaces() {
        return this.mdefNamespaces;
    }

    public void setMdefNamespaces(Set<String> mdefNamespaces) {
        this.mdefNamespaces = mdefNamespaces;
    }

    public String getCatalogFile() {
        return this.catalogFile;
    }

    public void setCatalogFile(String catalogPropFile) {
        this.catalogFile = catalogPropFile;
    }

    public SchemaCodePrinter getSchemaCodePrinter() {
        return this.schemaCodePrinter;
    }

    public void setSchemaCodePrinter(SchemaCodePrinter schemaCodePrinter) {
        this.schemaCodePrinter = schemaCodePrinter;
    }

    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }

    public void setEntityResolver(EntityResolver entityResolver) {
        this.entityResolver = entityResolver;
    }

    public Set<XmlOptions.BeanMethod> getPartialMethods() {
        return this.partialMethods;
    }

    public void setPartialMethods(Set<XmlOptions.BeanMethod> partialMethods) {
        this.partialMethods = partialMethods;
    }
}
