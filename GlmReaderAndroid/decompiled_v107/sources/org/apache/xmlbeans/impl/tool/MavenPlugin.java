package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.XmlError;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* loaded from: classes11.dex */
public class MavenPlugin extends AbstractMojo {
    private String baseSchemaLocation;
    private File basedir;
    private boolean buildSchemas;
    private String catalogLocation;
    private String classPath;
    private String classTargetDir;
    private String compiler;
    private boolean copyAnn;
    private boolean debug;
    private boolean download;
    private List<Extension> extensions;
    private String javaTargetDir;
    private List<String> mdefNamespaces;
    private String memoryInitialSize;
    private String memoryMaximumSize;
    private String name;
    private boolean noAnn;
    private boolean noPvr;
    private boolean noUpa;
    private boolean noVDoc;
    private File outputJar;
    private String partialMethods;
    private MavenProject project;
    private boolean quiet;
    private boolean quite;
    private boolean recurseSourceSubdirs;
    private String repackage;
    private List<Resource> resources;
    private String sourceCodeEncoding;
    private String sourceDir;
    private boolean sourceOnly;
    private String sourceSchemas;
    private boolean verbose;
    private String xmlConfigs;

    public void execute() throws MojoExecutionException, MojoFailureException {
        List<File> emptyList;
        List<File> xsds;
        char c;
        if (this.sourceDir == null || this.sourceDir.isEmpty() || !new File(this.sourceDir).isDirectory()) {
            throw new MojoFailureException("Set configuration <sourceDir> (='" + this.sourceDir + "') to a valid directory containing *.xsd,*.wsdl files.");
        }
        if (this.baseSchemaLocation == null || this.baseSchemaLocation.isEmpty()) {
            throw new MojoFailureException("baseSchemaLocation is empty");
        }
        if (this.sourceSchemas == null) {
            getLog().debug("sourceSchemas is null");
        }
        if (this.classPath == null) {
            getLog().debug("classPath is null");
        }
        List<File> xsds2 = new ArrayList<>();
        List<File> wsdls = new ArrayList<>();
        List<File> javas = new ArrayList<>();
        final File base = new File(this.sourceDir);
        Resource resource = new Resource();
        resource.setDirectory(this.sourceDir);
        resource.setTargetPath(this.baseSchemaLocation);
        Pattern pat = Pattern.compile(this.sourceSchemas != null ? "(" + this.sourceSchemas.replace(CollectionUtils.COMMA, "|").replace(".", "\\.").replace("*", ".*") + ")" : ".*");
        Collection<File> schemaFiles = FileUtil.find(base, pat, this.recurseSourceSubdirs);
        for (File sf : schemaFiles) {
            String name = sf.getName();
            String replaceAll = name.replaceAll(".*\\.", "");
            switch (replaceAll.hashCode()) {
                case 3254818:
                    if (replaceAll.equals("java")) {
                        c = 1;
                        break;
                    }
                    break;
                case 3658852:
                    if (replaceAll.equals("wsdl")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            c = 65535;
            switch (c) {
                case 0:
                    wsdls.add(sf);
                    break;
                case 1:
                    javas.add(sf);
                    break;
                default:
                    xsds2.add(sf);
                    break;
            }
            resource.addInclude(name);
        }
        this.resources = Collections.singletonList(resource);
        if (this.buildSchemas) {
            if (this.xmlConfigs == null || this.xmlConfigs.isEmpty()) {
                emptyList = Collections.emptyList();
            } else {
                emptyList = (List) Stream.of((Object[]) this.xmlConfigs.split(CollectionUtils.COMMA)).flatMap(new Function() { // from class: org.apache.xmlbeans.impl.tool.MavenPlugin$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        Stream filter;
                        filter = Stream.of((Object[]) new File[]{new File(r2), new File(base, (String) obj)}).filter(new Predicate() { // from class: org.apache.xmlbeans.impl.tool.MavenPlugin$$ExternalSyntheticLambda2
                            @Override // java.util.function.Predicate
                            public final boolean test(Object obj2) {
                                boolean exists;
                                exists = ((File) obj2).exists();
                                return exists;
                            }
                        });
                        return filter;
                    }
                }).collect(Collectors.toList());
            }
            List<File> configs = emptyList;
            List<File> classPathList = new ArrayList<>();
            List<URL> urls = new ArrayList<>();
            if (this.classPath == null) {
                xsds = xsds2;
            } else {
                String[] split = this.classPath.split(CollectionUtils.COMMA);
                int length = split.length;
                int i = 0;
                while (i < length) {
                    String classpathElement = split[i];
                    File file = new File(classpathElement);
                    classPathList.add(file);
                    try {
                        String[] strArr = split;
                        urls.add(file.toURI().toURL());
                        i++;
                        split = strArr;
                    } catch (MalformedURLException e) {
                        throw new MojoFailureException("invalid classpath: " + file, e);
                    }
                }
                xsds = xsds2;
            }
            ClassLoader cl = new URLClassLoader((URL[]) urls.toArray(new URL[0]));
            EntityResolver entityResolver = MavenPluginResolver.getResolver(this.catalogLocation);
            URI sourceDirURI = new File(this.sourceDir).toURI();
            EntityResolver entityResolver2 = new PassThroughResolver(cl, entityResolver, sourceDirURI, this.baseSchemaLocation);
            Parameters params = new Parameters();
            params.setXsdFiles(files(xsds));
            params.setWsdlFiles(files(wsdls));
            params.setJavaFiles(files(javas));
            params.setConfigFiles(files(configs));
            params.setClasspath(files(classPathList));
            params.setName(this.name);
            params.setSrcDir(new File(this.javaTargetDir));
            params.setClassesDir(new File(this.classTargetDir));
            params.setNojavac(this.sourceOnly);
            params.setVerbose(this.verbose);
            params.setEntityResolver(entityResolver2);
            params.setQuiet(this.quiet && this.quite);
            params.setNoUpa(this.noUpa);
            params.setNoPvr(this.noPvr);
            params.setNoAnn(this.noAnn);
            params.setCopyAnn(this.copyAnn);
            params.setNoVDoc(this.noVDoc);
            if (this.repackage != null && !this.repackage.isEmpty()) {
                params.setRepackage("org.apache.xmlbeans.metadata:" + this.repackage);
            }
            if (this.mdefNamespaces != null && !this.mdefNamespaces.isEmpty()) {
                params.setMdefNamespaces(new HashSet<>(this.mdefNamespaces));
            }
            List<XmlError> errorList = new ArrayList<>();
            params.setErrorListener(errorList);
            if (this.partialMethods != null && !this.partialMethods.isEmpty()) {
                params.setPartialMethods(SchemaCompiler.parsePartialMethods(this.partialMethods));
            }
            params.setDownload(this.download);
            params.setBaseDir(this.basedir);
            params.setCompiler(this.compiler);
            params.setMemoryInitialSize(this.memoryInitialSize);
            params.setMemoryMaximumSize(this.memoryMaximumSize);
            params.setOutputJar(this.outputJar);
            params.setDebug(this.debug);
            params.setExtensions(this.extensions);
            if (this.sourceCodeEncoding != null && !this.sourceCodeEncoding.isEmpty()) {
                params.setSourceCodeEncoding(this.sourceCodeEncoding);
            }
            boolean result = SchemaCompiler.compile(params);
            if (!result) {
                throw new MojoFailureException("Schema compilation failed!\n" + ((String) errorList.stream().map(new Function() { // from class: org.apache.xmlbeans.impl.tool.MavenPlugin$$ExternalSyntheticLambda1
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((XmlError) obj).toString();
                    }
                }).collect(Collectors.joining(StringUtils.LF))));
            }
            Resource genResource = new Resource();
            genResource.setDirectory(this.classTargetDir);
            this.project.addResource(genResource);
            this.project.addCompileSourceRoot(this.javaTargetDir);
        }
    }

    private static File[] files(List<File> files) {
        if (files == null || files.isEmpty()) {
            return null;
        }
        return (File[]) files.toArray(new File[0]);
    }

    /* loaded from: classes11.dex */
    private static class PassThroughResolver implements EntityResolver {
        private final String baseSchemaLocation;
        private final ClassLoader cl;
        private final EntityResolver delegate;
        private final URI sourceDir;

        public PassThroughResolver(ClassLoader cl, EntityResolver delegate, URI sourceDir, String baseSchemaLocation) {
            this.cl = cl;
            this.delegate = delegate;
            this.sourceDir = sourceDir;
            this.baseSchemaLocation = baseSchemaLocation + PackagingURIHelper.FORWARD_SLASH_STRING;
        }

        @Override // org.xml.sax.EntityResolver
        public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
            InputSource is;
            if (this.delegate != null && (is = this.delegate.resolveEntity(publicId, systemId)) != null) {
                return is;
            }
            System.out.println("Could not resolve publicId: " + publicId + ", systemId: " + systemId + " from catalog");
            try {
                String localSystemId = this.sourceDir.relativize(new URI(systemId)).toString();
                InputStream in = this.cl.getResourceAsStream(localSystemId);
                if (in != null) {
                    System.out.println("found in classpath at: " + localSystemId);
                    return new InputSource(in);
                }
                InputStream in2 = this.cl.getResourceAsStream(this.baseSchemaLocation + localSystemId);
                if (in2 != null) {
                    System.out.println("found in classpath at: META-INF/" + localSystemId);
                    return new InputSource(in2);
                }
                System.out.println("Not found in classpath, looking in current directory: " + systemId);
                return new InputSource(systemId);
            } catch (URISyntaxException e) {
                throw new IOException("Could not relativeize systemId", e);
            }
        }
    }
}
