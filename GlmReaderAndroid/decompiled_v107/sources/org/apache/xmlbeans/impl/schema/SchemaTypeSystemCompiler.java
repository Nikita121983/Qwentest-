package org.apache.xmlbeans.impl.schema;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.xmlbeans.BindingConfig;
import org.apache.xmlbeans.Filer;
import org.apache.xmlbeans.SchemaCodePrinter;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.XmlErrorWatcher;
import org.apache.xmlbeans.impl.repackage.Repackager;
import org.apache.xmlbeans.impl.schema.StscImporter;
import org.apache.xmlbeans.impl.util.FilerImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

/* loaded from: classes11.dex */
public class SchemaTypeSystemCompiler {

    /* loaded from: classes11.dex */
    public static class Parameters {
        private URI baseURI;
        private BindingConfig config;
        private Collection<XmlError> errorListener;
        private SchemaTypeSystem existingSystem;
        private boolean javaize;
        private SchemaTypeLoader linkTo;
        private String name;
        private XmlOptions options;
        private SchemaDocument.Schema[] schemas;
        private File schemasDir;
        private Map<String, String> sourcesToCopyMap;

        public SchemaTypeSystem getExistingTypeSystem() {
            return this.existingSystem;
        }

        public void setExistingTypeSystem(SchemaTypeSystem system) {
            this.existingSystem = system;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public SchemaDocument.Schema[] getSchemas() {
            return this.schemas;
        }

        public void setSchemas(SchemaDocument.Schema[] schemas) {
            this.schemas = schemas == null ? null : (SchemaDocument.Schema[]) schemas.clone();
        }

        public BindingConfig getConfig() {
            return this.config;
        }

        public void setConfig(BindingConfig config) {
            this.config = config;
        }

        public SchemaTypeLoader getLinkTo() {
            return this.linkTo;
        }

        public void setLinkTo(SchemaTypeLoader linkTo) {
            this.linkTo = linkTo;
        }

        public XmlOptions getOptions() {
            return this.options;
        }

        public void setOptions(XmlOptions options) {
            this.options = options;
        }

        public Collection<XmlError> getErrorListener() {
            return this.errorListener;
        }

        public void setErrorListener(Collection<XmlError> errorListener) {
            this.errorListener = errorListener;
        }

        public boolean isJavaize() {
            return this.javaize;
        }

        public void setJavaize(boolean javaize) {
            this.javaize = javaize;
        }

        public URI getBaseURI() {
            return this.baseURI;
        }

        public void setBaseURI(URI baseURI) {
            this.baseURI = baseURI;
        }

        public Map<String, String> getSourcesToCopyMap() {
            return this.sourcesToCopyMap;
        }

        public void setSourcesToCopyMap(Map<String, String> sourcesToCopyMap) {
            this.sourcesToCopyMap = sourcesToCopyMap;
        }

        public File getSchemasDir() {
            return this.schemasDir;
        }

        public void setSchemasDir(File schemasDir) {
            this.schemasDir = schemasDir;
        }
    }

    public static SchemaTypeSystem compile(Parameters params) {
        return compileImpl(params.getExistingTypeSystem(), params.getName(), params.getSchemas(), params.getConfig(), params.getLinkTo(), params.getOptions(), params.getErrorListener(), params.isJavaize(), params.getBaseURI(), params.getSourcesToCopyMap(), params.getSchemasDir());
    }

    public static SchemaTypeSystemImpl compile(String name, SchemaTypeSystem existingSTS, XmlObject[] input, BindingConfig config, SchemaTypeLoader linkTo, Filer filer, XmlOptions options) throws XmlException {
        XmlOptions options2 = XmlOptions.maskNull(options);
        ArrayList<SchemaDocument.Schema> schemas = new ArrayList<>();
        if (input != null) {
            for (int i = 0; i < input.length; i++) {
                if (input[i] instanceof SchemaDocument.Schema) {
                    schemas.add((SchemaDocument.Schema) input[i]);
                } else if ((input[i] instanceof SchemaDocument) && ((SchemaDocument) input[i]).getSchema() != null) {
                    schemas.add(((SchemaDocument) input[i]).getSchema());
                } else {
                    throw new XmlException("Thread " + Thread.currentThread().getName() + ": The " + i + "th supplied input is not a schema document: its type is " + input[i].schemaType());
                }
            }
        }
        Collection<XmlError> userErrors = options2.getErrorListener();
        XmlErrorWatcher errorWatcher = new XmlErrorWatcher(userErrors);
        SchemaTypeSystemImpl stsi = compileImpl(existingSTS, name, (SchemaDocument.Schema[]) schemas.toArray(new SchemaDocument.Schema[0]), config, linkTo, options2, errorWatcher, filer != null, options2.getBaseURI(), null, null);
        if (errorWatcher.hasError() && stsi == null) {
            throw new XmlException(errorWatcher.firstError());
        }
        if (stsi != null && !stsi.isIncomplete() && filer != null) {
            stsi.save(filer);
            generateTypes(stsi, filer, options2);
        }
        return stsi;
    }

    static SchemaTypeSystemImpl compileImpl(SchemaTypeSystem system, String name, SchemaDocument.Schema[] schemas, BindingConfig config, SchemaTypeLoader linkTo, XmlOptions options, Collection<XmlError> outsideErrors, boolean javaize, URI baseURI, Map<String, String> sourcesToCopyMap, File schemasDir) {
        if (linkTo == null) {
            throw new IllegalArgumentException("Must supply linkTo");
        }
        XmlErrorWatcher errorWatcher = new XmlErrorWatcher(outsideErrors);
        boolean incremental = system != null;
        StscState state = StscState.start();
        boolean validate = options == null || !options.isCompileNoValidation();
        try {
            state.setErrorListener(errorWatcher);
            try {
                state.setBindingConfig(config);
                state.setOptions(options);
                try {
                    state.setGivenTypeSystemName(name);
                    try {
                        state.setSchemasDir(schemasDir);
                        if (baseURI != null) {
                            state.setBaseUri(baseURI);
                        }
                        try {
                            state.setImportingTypeLoader(SchemaTypeLoaderImpl.build(new SchemaTypeLoader[]{BuiltinSchemaTypeSystem.get(), linkTo}, null, null));
                            List<SchemaDocument.Schema> validSchemas = new ArrayList<>(schemas.length);
                            if (validate) {
                                XmlOptions validateOptions = new XmlOptions().setErrorListener(errorWatcher);
                                if (options != null && options.isValidateTreatLaxAsSkip()) {
                                    validateOptions.setValidateTreatLaxAsSkip();
                                }
                                int length = schemas.length;
                                int i = 0;
                                while (i < length) {
                                    SchemaDocument.Schema schema = schemas[i];
                                    int i2 = length;
                                    if (schema.validate(validateOptions)) {
                                        validSchemas.add(schema);
                                    }
                                    i++;
                                    length = i2;
                                }
                            } else {
                                validSchemas.addAll(Arrays.asList(schemas));
                            }
                            SchemaDocument.Schema[] startWith = (SchemaDocument.Schema[]) validSchemas.toArray(new SchemaDocument.Schema[0]);
                            if (incremental) {
                                Set<String> namespaces = new HashSet<>();
                                startWith = getSchemasToRecompile((SchemaTypeSystemImpl) system, startWith, namespaces);
                                state.initFromTypeSystem((SchemaTypeSystemImpl) system, namespaces);
                            } else {
                                state.setDependencies(new SchemaDependencies());
                            }
                            StscImporter.SchemaToProcess[] schemasAndChameleons = StscImporter.resolveImportsAndIncludes(startWith, incremental);
                            StscTranslator.addAllDefinitions(schemasAndChameleons);
                            StscResolver.resolveAll();
                            StscChecker.checkAll();
                            StscJavaizer.javaizeAllTypes(javaize);
                            StscState.get().sts().loadFromStscState(state);
                            if (sourcesToCopyMap != null) {
                                sourcesToCopyMap.putAll(state.sourceCopyMap());
                            }
                            if (errorWatcher.hasError()) {
                                if (state.allowPartial() && state.getRecovered() == errorWatcher.size()) {
                                    StscState.get().sts().setIncomplete(true);
                                }
                                StscState.end();
                                return null;
                            }
                            if (system != null) {
                                ((SchemaTypeSystemImpl) system).setIncomplete(true);
                            }
                            SchemaTypeSystemImpl sts = StscState.get().sts();
                            StscState.end();
                            return sts;
                        } catch (Throwable th) {
                            th = th;
                            StscState.end();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        StscState.end();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    StscState.end();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }

    private static SchemaDocument.Schema[] getSchemasToRecompile(SchemaTypeSystemImpl system, SchemaDocument.Schema[] modified, Set<String> namespaces) {
        Set<String> modifiedFiles = new HashSet<>();
        Map<String, SchemaDocument.Schema> haveFile = new HashMap<>();
        List<SchemaDocument.Schema> result = new ArrayList<>();
        for (SchemaDocument.Schema schema : modified) {
            String fileURL = schema.documentProperties().getSourceName();
            if (fileURL == null) {
                throw new IllegalArgumentException("One of the Schema files passed in doesn't have the source set, which prevents it to be incrementally compiled");
            }
            modifiedFiles.add(fileURL);
            haveFile.put(fileURL, schema);
            result.add(schema);
        }
        SchemaDependencies dep = system.getDependencies();
        List<String> nss = dep.getNamespacesTouched(modifiedFiles);
        namespaces.addAll(dep.computeTransitiveClosure(nss));
        List<String> needRecompilation = dep.getFilesTouched(namespaces);
        StscState.get().setDependencies(new SchemaDependencies(dep, namespaces));
        for (String url : needRecompilation) {
            SchemaDocument.Schema have = haveFile.get(url);
            if (have == null) {
                XmlObject xmlObject = null;
                try {
                    try {
                    } catch (IOException e) {
                        ioe = e;
                    }
                    try {
                        XmlObject xdoc = StscImporter.DownloadTable.downloadDocument(StscState.get().getS4SLoader(), null, url);
                        XmlOptions voptions = new XmlOptions();
                        voptions.setErrorListener(StscState.get().getErrorListener());
                        if ((xdoc instanceof SchemaDocument) && xdoc.validate(voptions)) {
                            SchemaDocument sDoc = (SchemaDocument) xdoc;
                            result.add(sDoc.getSchema());
                        }
                        StscState.get().error("Referenced document is not a valid schema, URL = " + url, 56, (XmlObject) null);
                    } catch (IOException e2) {
                        ioe = e2;
                        xmlObject = null;
                        StscState.get().error(XmlErrorCodes.EXCEPTION_LOADING_URL, new Object[]{"IOException", url, ioe.getMessage()}, xmlObject);
                    }
                } catch (MalformedURLException mfe) {
                    StscState.get().error(XmlErrorCodes.EXCEPTION_LOADING_URL, new Object[]{"MalformedURLException", url, mfe.getMessage()}, (XmlObject) null);
                } catch (XmlException xmle) {
                    StscState.get().error(XmlErrorCodes.EXCEPTION_LOADING_URL, new Object[]{"XmlException", url, xmle.getMessage()}, (XmlObject) null);
                }
            }
        }
        return (SchemaDocument.Schema[]) result.toArray(new SchemaDocument.Schema[0]);
    }

    public static boolean generateTypes(SchemaTypeSystem system, Filer filer, XmlOptions options) {
        String characterEncoding;
        if ((system instanceof SchemaTypeSystemImpl) && ((SchemaTypeSystemImpl) system).isIncomplete()) {
            return false;
        }
        boolean success = true;
        List<SchemaType> types = new ArrayList<>();
        types.addAll(Arrays.asList(system.globalTypes()));
        types.addAll(Arrays.asList(system.documentTypes()));
        types.addAll(Arrays.asList(system.attributeTypes()));
        SchemaCodePrinter printer = options == null ? null : options.getSchemaCodePrinter();
        if (printer == null) {
            printer = new SchemaTypeCodePrinter();
        }
        String indexClassName = SchemaTypeCodePrinter.indexClassForSystem(system);
        if (options == null) {
            characterEncoding = null;
        } else {
            try {
                characterEncoding = options.getCharacterEncoding();
            } catch (IOException e) {
                System.err.println("IO Error " + e);
                success = false;
            }
        }
        Writer out = filer.createSourceFile(indexClassName, characterEncoding);
        try {
            Repackager repackager = filer instanceof FilerImpl ? ((FilerImpl) filer).getRepackager() : null;
            printer.printHolder(out, system, options, repackager);
            if (out != null) {
                out.close();
            }
            for (SchemaType type : types) {
                if (!type.isBuiltinType() && type.getFullJavaName() != null) {
                    String fjn = type.getFullJavaName();
                    Writer writer = filer.createSourceFile(fjn, options == null ? null : options.getCharacterEncoding());
                    try {
                        printer.printType(writer, type, options);
                        if (writer != null) {
                            try {
                                writer.close();
                            } catch (IOException e2) {
                                System.err.println("IO Error " + e2);
                                success = false;
                            }
                        }
                        String fjn2 = type.getFullJavaImplName();
                        writer = filer.createSourceFile(fjn2, options == null ? null : options.getCharacterEncoding());
                        try {
                            printer.printTypeImpl(writer, type, options);
                            if (writer != null) {
                                try {
                                    writer.close();
                                } catch (IOException e3) {
                                    System.err.println("IO Error " + e3);
                                    success = false;
                                }
                            }
                        } finally {
                            try {
                                break;
                            } finally {
                            }
                        }
                    } finally {
                        try {
                            break;
                        } finally {
                        }
                    }
                }
            }
            return success;
        } finally {
        }
    }
}
