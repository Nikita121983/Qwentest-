package org.apache.xmlbeans.impl.schema;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.apache.xmlbeans.impl.common.XmlEncodingSniffer;
import org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* loaded from: classes11.dex */
public class StscImporter {
    private static final String PROJECT_URL_PREFIX = "project://local";

    public static SchemaToProcess[] resolveImportsAndIncludes(SchemaDocument.Schema[] startWith, boolean forceSrcSave) {
        DownloadTable engine = new DownloadTable(startWith);
        return engine.resolveImportsAndIncludes(forceSrcSave);
    }

    /* loaded from: classes11.dex */
    public static class SchemaToProcess {
        private final String chameleonNamespace;
        private List<SchemaToProcess> includes;
        private Set<SchemaToProcess> indirectIncludedBy;
        private Set<SchemaToProcess> indirectIncludes;
        private List<RedefineDocument.Redefine> redefineObjects;
        private List<SchemaToProcess> redefines;
        private final SchemaDocument.Schema schema;

        public SchemaToProcess(SchemaDocument.Schema schema, String chameleonNamespace) {
            this.schema = schema;
            this.chameleonNamespace = chameleonNamespace;
        }

        public SchemaDocument.Schema getSchema() {
            return this.schema;
        }

        public String getSourceName() {
            return this.schema.documentProperties().getSourceName();
        }

        public String getChameleonNamespace() {
            return this.chameleonNamespace;
        }

        public List<SchemaToProcess> getRedefines() {
            return this.redefines;
        }

        public List<RedefineDocument.Redefine> getRedefineObjects() {
            return this.redefineObjects;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addInclude(SchemaToProcess include) {
            if (this.includes == null) {
                this.includes = new ArrayList();
            }
            this.includes.add(include);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addRedefine(SchemaToProcess redefine, RedefineDocument.Redefine object) {
            if (this.redefines == null || this.redefineObjects == null) {
                this.redefines = new ArrayList();
                this.redefineObjects = new ArrayList();
            }
            this.redefines.add(redefine);
            this.redefineObjects.add(object);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void buildIndirectReferences() {
            if (this.includes != null) {
                for (SchemaToProcess schemaToProcess : this.includes) {
                    addIndirectIncludes(schemaToProcess);
                }
            }
            if (this.redefines != null) {
                for (SchemaToProcess schemaToProcess2 : this.redefines) {
                    addIndirectIncludes(schemaToProcess2);
                }
            }
        }

        private void addIndirectIncludes(SchemaToProcess schemaToProcess) {
            if (this.indirectIncludes == null) {
                this.indirectIncludes = new HashSet();
            }
            this.indirectIncludes.add(schemaToProcess);
            if (schemaToProcess.indirectIncludedBy == null) {
                schemaToProcess.indirectIncludedBy = new HashSet();
            }
            schemaToProcess.indirectIncludedBy.add(this);
            addIndirectIncludesHelper(this, schemaToProcess);
            if (this.indirectIncludedBy != null) {
                for (SchemaToProcess stp : this.indirectIncludedBy) {
                    stp.indirectIncludes.add(schemaToProcess);
                    schemaToProcess.indirectIncludedBy.add(stp);
                    addIndirectIncludesHelper(stp, schemaToProcess);
                }
            }
        }

        private static void addIndirectIncludesHelper(SchemaToProcess including, SchemaToProcess schemaToProcess) {
            if (schemaToProcess.indirectIncludes != null) {
                for (SchemaToProcess stp : schemaToProcess.indirectIncludes) {
                    including.indirectIncludes.add(stp);
                    stp.indirectIncludedBy.add(including);
                }
            }
        }

        public boolean indirectIncludes(SchemaToProcess schemaToProcess) {
            return this.indirectIncludes != null && this.indirectIncludes.contains(schemaToProcess);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof SchemaToProcess)) {
                return false;
            }
            SchemaToProcess schemaToProcess = (SchemaToProcess) o;
            return Objects.equals(this.chameleonNamespace, schemaToProcess.chameleonNamespace) && this.schema == schemaToProcess.schema;
        }

        public int hashCode() {
            int result = this.schema.hashCode();
            return (result * 29) + (this.chameleonNamespace != null ? this.chameleonNamespace.hashCode() : 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String baseURLForDoc(XmlObject obj) {
        String path = obj.documentProperties().getSourceName();
        if (path == null) {
            return null;
        }
        if (path.startsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) {
            return PROJECT_URL_PREFIX + path.replace(IOUtils.DIR_SEPARATOR_WINDOWS, '/');
        }
        int colon = path.indexOf(58);
        if (colon > 1 && path.substring(0, colon).matches("^\\w+$")) {
            return path;
        }
        return "project://local/" + path.replace(IOUtils.DIR_SEPARATOR_WINDOWS, '/');
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static URI parseURI(String s) {
        if (s == null) {
            return null;
        }
        try {
            return new URI(s);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public static URI resolve(URI base, String child) throws URISyntaxException {
        URI childUri = new URI(child);
        URI ruri = base.resolve(childUri);
        if (childUri.equals(ruri) && !childUri.isAbsolute() && (base.getScheme().equals(ArchiveStreamFactory.JAR) || base.getScheme().equals(ArchiveStreamFactory.ZIP))) {
            String r = base.toString();
            int lastslash = r.lastIndexOf(47);
            String r2 = r.substring(0, lastslash) + PackagingURIHelper.FORWARD_SLASH_STRING + childUri;
            int exclPointSlashIndex = r2.lastIndexOf("!/");
            if (exclPointSlashIndex > 0) {
                for (int slashDotDotIndex = r2.indexOf("/..", exclPointSlashIndex); slashDotDotIndex > 0; slashDotDotIndex = r2.indexOf("/..", exclPointSlashIndex)) {
                    int prevSlashIndex = r2.lastIndexOf(PackagingURIHelper.FORWARD_SLASH_STRING, slashDotDotIndex - 1);
                    if (prevSlashIndex >= exclPointSlashIndex) {
                        String temp = r2.substring(slashDotDotIndex + 3);
                        r2 = r2.substring(0, prevSlashIndex).concat(temp);
                    }
                }
            }
            return URI.create(r2);
        }
        if ("file".equals(ruri.getScheme()) && !child.equals(ruri.getPath()) && base.getPath().startsWith("//") && !ruri.getPath().startsWith("//")) {
            String path = "///".concat(ruri.getPath());
            try {
                return new URI("file", null, path, ruri.getQuery(), ruri.getFragment());
            } catch (URISyntaxException e) {
                return ruri;
            }
        }
        return ruri;
    }

    /* loaded from: classes11.dex */
    public static class DownloadTable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final Map<NsLocPair, SchemaDocument.Schema> schemaByNsLocPair = new HashMap();
        private final Map<DigestKey, SchemaDocument.Schema> schemaByDigestKey = new HashMap();
        private final LinkedList<SchemaToProcess> scanNeeded = new LinkedList<>();
        private final Set<SchemaDocument.Schema> emptyNamespaceSchemas = new HashSet();
        private final Map<SchemaToProcess, SchemaToProcess> scannedAlready = new HashMap();
        private final Set<String> failedDownloads = new HashSet();

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes11.dex */
        public static class NsLocPair {
            private final String locationURL;
            private final String namespaceURI;

            public NsLocPair(String namespaceURI, String locationURL) {
                this.namespaceURI = namespaceURI;
                this.locationURL = locationURL;
            }

            public String getNamespaceURI() {
                return this.namespaceURI;
            }

            public String getLocationURL() {
                return this.locationURL;
            }

            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof NsLocPair)) {
                    return false;
                }
                NsLocPair nsLocPair = (NsLocPair) o;
                if (Objects.equals(this.locationURL, nsLocPair.locationURL)) {
                    return Objects.equals(this.namespaceURI, nsLocPair.namespaceURI);
                }
                return false;
            }

            public int hashCode() {
                int result = this.namespaceURI != null ? this.namespaceURI.hashCode() : 0;
                return (result * 29) + (this.locationURL != null ? this.locationURL.hashCode() : 0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes11.dex */
        public static class DigestKey {
            byte[] _digest;
            int _hashCode;

            DigestKey(byte[] digest) {
                this._digest = digest;
                for (int i = 0; i < 4 && i < digest.length; i++) {
                    this._hashCode <<= 8;
                    this._hashCode += digest[i];
                }
            }

            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof DigestKey)) {
                    return false;
                }
                return Arrays.equals(this._digest, ((DigestKey) o)._digest);
            }

            public int hashCode() {
                return this._hashCode;
            }
        }

        private SchemaDocument.Schema downloadSchema(XmlObject referencedBy, String targetNamespace, String locationURL) {
            String uri;
            SchemaDocument.Schema schema;
            XmlObject xdoc;
            SchemaDocument.Schema result;
            String shortname;
            SchemaDocument.Schema result2;
            SchemaDocument.Schema result3;
            if (locationURL == null) {
                return null;
            }
            StscState state = StscState.get();
            URI baseURI = StscImporter.parseURI(StscImporter.baseURLForDoc(referencedBy));
            if (baseURI == null) {
                uri = locationURL;
            } else {
                try {
                    uri = StscImporter.resolve(baseURI, locationURL).toString();
                } catch (URISyntaxException e) {
                    state.error("Could not find resource - invalid location URL: " + e.getMessage(), 56, referencedBy);
                    return null;
                }
            }
            String absoluteURL = uri;
            if (absoluteURL == null) {
                throw new AssertionError();
            }
            if (state.isFileProcessed(absoluteURL)) {
                return null;
            }
            if (targetNamespace != null && (result3 = this.schemaByNsLocPair.get(new NsLocPair(targetNamespace, absoluteURL))) != null) {
                return result3;
            }
            if (targetNamespace != null && !targetNamespace.equals("")) {
                if (!state.shouldDownloadURI(absoluteURL) && (result2 = this.schemaByNsLocPair.get(new NsLocPair(targetNamespace, null))) != null) {
                    return result2;
                }
                if (state.linkerDefinesNamespace(targetNamespace)) {
                    return null;
                }
            }
            SchemaDocument.Schema result22 = this.schemaByNsLocPair.get(new NsLocPair(null, absoluteURL));
            if (result22 != null) {
                return result22;
            }
            if (previouslyFailedToDownload(absoluteURL)) {
                return null;
            }
            if (!state.shouldDownloadURI(absoluteURL)) {
                state.error("Could not load resource \"" + absoluteURL + "\" (network downloads disabled).", 56, referencedBy);
                addFailedDownload(absoluteURL);
                return null;
            }
            try {
                xdoc = downloadDocument(state.getS4SLoader(), targetNamespace, absoluteURL);
                result = findMatchByDigest(xdoc);
                shortname = state.relativize(absoluteURL);
                try {
                } catch (MalformedURLException e2) {
                    state.error("URL \"" + absoluteURL + "\" is not well-formed", 56, referencedBy);
                    addFailedDownload(absoluteURL);
                    return schema;
                } catch (IOException e3) {
                    connectionProblem = e3;
                    state.error(connectionProblem.toString(), 56, referencedBy);
                    addFailedDownload(absoluteURL);
                    return schema;
                } catch (XmlException e4) {
                    e = e4;
                    state.error("Problem parsing referenced XML resource - " + e.getMessage(), 56, referencedBy);
                    addFailedDownload(absoluteURL);
                    return schema;
                }
            } catch (MalformedURLException e5) {
                schema = null;
            } catch (IOException e6) {
                connectionProblem = e6;
                schema = null;
            } catch (XmlException e7) {
                e = e7;
                schema = null;
            }
            if (result != null) {
                String dupname = state.relativize(result.documentProperties().getSourceName());
                if (dupname != null) {
                    state.info(shortname + " is the same as " + dupname + " (ignoring the duplicate file)");
                } else {
                    state.info(shortname + " is the same as another schema");
                }
            } else {
                XmlOptions voptions = new XmlOptions();
                voptions.setErrorListener(state.getErrorListener());
                if (!(xdoc instanceof SchemaDocument)) {
                    schema = null;
                } else if (xdoc.validate(voptions)) {
                    SchemaDocument sDoc = (SchemaDocument) xdoc;
                    result = sDoc.getSchema();
                    state.info("Loading referenced file " + shortname);
                } else {
                    schema = null;
                }
                state.error("Referenced document is not a valid schema", 56, referencedBy);
                addFailedDownload(absoluteURL);
                return schema;
            }
            NsLocPair key = new NsLocPair(emptyStringIfNull(result.getTargetNamespace()), absoluteURL);
            addSuccessfulDownload(key, result);
            return result;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static XmlObject downloadDocument(SchemaTypeLoader loader, String namespace, String absoluteURL) throws IOException, XmlException {
            StscState state = StscState.get();
            EntityResolver resolver = state.getEntityResolver();
            if (resolver != null) {
                try {
                    InputSource source = resolver.resolveEntity(namespace, absoluteURL);
                    if (source != null) {
                        state.addSourceUri(absoluteURL, null);
                        Reader reader = source.getCharacterStream();
                        if (reader != null) {
                            Reader reader2 = copySchemaSource(absoluteURL, reader, state);
                            XmlOptions options = new XmlOptions();
                            options.setLoadLineNumbers();
                            options.setDocumentSourceName(absoluteURL);
                            return loader.parse(reader2, (SchemaType) null, options);
                        }
                        InputStream bytes = source.getByteStream();
                        if (bytes != null) {
                            InputStream bytes2 = copySchemaSource(absoluteURL, bytes, state);
                            String encoding = source.getEncoding();
                            XmlOptions options2 = new XmlOptions();
                            options2.setLoadLineNumbers();
                            options2.setLoadMessageDigest();
                            options2.setDocumentSourceName(absoluteURL);
                            if (encoding != null) {
                                options2.setCharacterEncoding(encoding);
                            }
                            return loader.parse(bytes2, (SchemaType) null, options2);
                        }
                        String urlToLoad = source.getSystemId();
                        if (urlToLoad == null) {
                            throw new IOException("EntityResolver unable to resolve " + absoluteURL + " (for namespace " + namespace + ")");
                        }
                        copySchemaSource(absoluteURL, state, false);
                        XmlOptions options3 = new XmlOptions();
                        options3.setLoadLineNumbers();
                        options3.setLoadMessageDigest();
                        options3.setDocumentSourceName(absoluteURL);
                        URL urlDownload = new URL(urlToLoad);
                        return loader.parse(urlDownload, (SchemaType) null, options3);
                    }
                } catch (SAXException e) {
                    throw new XmlException(e);
                }
            }
            state.addSourceUri(absoluteURL, null);
            copySchemaSource(absoluteURL, state, false);
            XmlOptions options4 = new XmlOptions();
            options4.setLoadLineNumbers();
            options4.setLoadMessageDigest();
            URL urlDownload2 = new URL(absoluteURL);
            return loader.parse(urlDownload2, (SchemaType) null, options4);
        }

        private void addSuccessfulDownload(NsLocPair key, SchemaDocument.Schema schema) {
            byte[] digest = schema.documentProperties().getMessageDigest();
            if (digest == null) {
                StscState.get().addSchemaDigest(null);
            } else {
                DigestKey dk = new DigestKey(digest);
                if (!this.schemaByDigestKey.containsKey(dk)) {
                    this.schemaByDigestKey.put(new DigestKey(digest), schema);
                    StscState.get().addSchemaDigest(digest);
                }
            }
            this.schemaByNsLocPair.put(key, schema);
            NsLocPair key1 = new NsLocPair(key.getNamespaceURI(), null);
            if (!this.schemaByNsLocPair.containsKey(key1)) {
                this.schemaByNsLocPair.put(key1, schema);
            }
            NsLocPair key2 = new NsLocPair(null, key.getLocationURL());
            if (!this.schemaByNsLocPair.containsKey(key2)) {
                this.schemaByNsLocPair.put(key2, schema);
            }
        }

        private SchemaDocument.Schema findMatchByDigest(XmlObject original) {
            byte[] digest = original.documentProperties().getMessageDigest();
            if (digest == null) {
                return null;
            }
            return this.schemaByDigestKey.get(new DigestKey(digest));
        }

        private void addFailedDownload(String locationURL) {
            this.failedDownloads.add(locationURL);
        }

        private boolean previouslyFailedToDownload(String locationURL) {
            return this.failedDownloads.contains(locationURL);
        }

        private static boolean nullableStringsMatch(String s1, String s2) {
            if (s1 == null && s2 == null) {
                return true;
            }
            if (s1 == null || s2 == null) {
                return false;
            }
            return s1.equals(s2);
        }

        private static String emptyStringIfNull(String s) {
            if (s == null) {
                return "";
            }
            return s;
        }

        private SchemaToProcess addScanNeeded(SchemaToProcess stp) {
            if (!this.scannedAlready.containsKey(stp)) {
                this.scannedAlready.put(stp, stp);
                this.scanNeeded.add(stp);
                return stp;
            }
            return this.scannedAlready.get(stp);
        }

        private void addEmptyNamespaceSchema(SchemaDocument.Schema s) {
            this.emptyNamespaceSchemas.add(s);
        }

        private void usedEmptyNamespaceSchema(SchemaDocument.Schema s) {
            this.emptyNamespaceSchemas.remove(s);
        }

        private boolean fetchRemainingEmptyNamespaceSchemas() {
            if (this.emptyNamespaceSchemas.isEmpty()) {
                return false;
            }
            for (SchemaDocument.Schema schema : this.emptyNamespaceSchemas) {
                addScanNeeded(new SchemaToProcess(schema, null));
            }
            this.emptyNamespaceSchemas.clear();
            return true;
        }

        private boolean hasNextToScan() {
            return !this.scanNeeded.isEmpty();
        }

        private SchemaToProcess nextToScan() {
            return this.scanNeeded.removeFirst();
        }

        public DownloadTable(SchemaDocument.Schema[] startWith) {
            for (SchemaDocument.Schema schema : startWith) {
                String targetNamespace = schema.getTargetNamespace();
                NsLocPair key = new NsLocPair(targetNamespace, StscImporter.baseURLForDoc(schema));
                addSuccessfulDownload(key, schema);
                if (targetNamespace != null) {
                    addScanNeeded(new SchemaToProcess(schema, null));
                } else {
                    addEmptyNamespaceSchema(schema);
                }
            }
        }

        public SchemaToProcess[] resolveImportsAndIncludes(boolean forceSave) {
            StscState state;
            boolean hasRedefinitions;
            boolean hasRedefinitions2;
            StscState state2 = StscState.get();
            List<SchemaToProcess> result = new ArrayList<>();
            boolean hasRedefinitions3 = false;
            while (true) {
                if (!hasNextToScan()) {
                    StscState state3 = state2;
                    hasRedefinitions2 = hasRedefinitions3;
                    if (!fetchRemainingEmptyNamespaceSchemas()) {
                        break;
                    }
                    state2 = state3;
                    hasRedefinitions3 = hasRedefinitions2;
                } else {
                    SchemaToProcess stp = nextToScan();
                    String uri = stp.getSourceName();
                    state2.addSourceUri(uri, null);
                    result.add(stp);
                    copySchemaSource(uri, state2, forceSave);
                    ImportDocument.Import[] imports = stp.getSchema().getImportArray();
                    for (ImportDocument.Import anImport : imports) {
                        SchemaDocument.Schema imported = downloadSchema(anImport, emptyStringIfNull(anImport.getNamespace()), anImport.getSchemaLocation());
                        if (imported != null) {
                            if (nullableStringsMatch(imported.getTargetNamespace(), anImport.getNamespace())) {
                                addScanNeeded(new SchemaToProcess(imported, null));
                            } else {
                                StscState.get().error("Imported schema has a target namespace \"" + imported.getTargetNamespace() + "\" that does not match the specified \"" + anImport.getNamespace() + "\"", 4, anImport);
                            }
                        }
                    }
                    IncludeDocument.Include[] includes = stp.getSchema().getIncludeArray();
                    String sourceNamespace = stp.getChameleonNamespace();
                    if (sourceNamespace == null) {
                        sourceNamespace = emptyStringIfNull(stp.getSchema().getTargetNamespace());
                    }
                    int length = includes.length;
                    int i = 0;
                    while (i < length) {
                        IncludeDocument.Include include = includes[i];
                        SchemaDocument.Schema included = downloadSchema(include, null, include.getSchemaLocation());
                        if (included == null) {
                            state = state2;
                            hasRedefinitions = hasRedefinitions3;
                        } else if (emptyStringIfNull(included.getTargetNamespace()).equals(sourceNamespace)) {
                            SchemaToProcess s = addScanNeeded(new SchemaToProcess(included, null));
                            stp.addInclude(s);
                            state = state2;
                            hasRedefinitions = hasRedefinitions3;
                        } else if (included.getTargetNamespace() != null) {
                            state = state2;
                            hasRedefinitions = hasRedefinitions3;
                            StscState.get().error("Included schema has a target namespace \"" + included.getTargetNamespace() + "\" that does not match the source namespace \"" + sourceNamespace + "\"", 4, include);
                        } else {
                            state = state2;
                            hasRedefinitions = hasRedefinitions3;
                            SchemaToProcess s2 = addScanNeeded(new SchemaToProcess(included, sourceNamespace));
                            stp.addInclude(s2);
                            usedEmptyNamespaceSchema(included);
                        }
                        i++;
                        state2 = state;
                        hasRedefinitions3 = hasRedefinitions;
                    }
                    StscState state4 = state2;
                    boolean hasRedefinitions4 = hasRedefinitions3;
                    RedefineDocument.Redefine[] redefines = stp.getSchema().getRedefineArray();
                    String sourceNamespace2 = stp.getChameleonNamespace();
                    if (sourceNamespace2 == null) {
                        sourceNamespace2 = emptyStringIfNull(stp.getSchema().getTargetNamespace());
                    }
                    for (RedefineDocument.Redefine redefine : redefines) {
                        SchemaDocument.Schema redefined = downloadSchema(redefine, null, redefine.getSchemaLocation());
                        if (redefined != null) {
                            if (emptyStringIfNull(redefined.getTargetNamespace()).equals(sourceNamespace2)) {
                                SchemaToProcess s3 = addScanNeeded(new SchemaToProcess(redefined, null));
                                stp.addRedefine(s3, redefine);
                                hasRedefinitions4 = true;
                            } else if (redefined.getTargetNamespace() == null) {
                                SchemaToProcess s4 = addScanNeeded(new SchemaToProcess(redefined, sourceNamespace2));
                                stp.addRedefine(s4, redefine);
                                usedEmptyNamespaceSchema(redefined);
                                hasRedefinitions4 = true;
                            } else {
                                StscState.get().error("Redefined schema has a target namespace \"" + redefined.getTargetNamespace() + "\" that does not match the source namespace \"" + sourceNamespace2 + "\"", 4, redefine);
                            }
                        }
                    }
                    state2 = state4;
                    hasRedefinitions3 = hasRedefinitions4;
                }
            }
            if (hasRedefinitions2) {
                for (SchemaToProcess schemaToProcess : result) {
                    schemaToProcess.buildIndirectReferences();
                }
            }
            return (SchemaToProcess[]) result.toArray(new SchemaToProcess[0]);
        }

        private static Reader copySchemaSource(String url, Reader reader, StscState state) {
            if (state.getSchemasDir() == null) {
                return reader;
            }
            String schemalocation = state.sourceNameForUri(url);
            File targetFile = new File(state.getSchemasDir(), schemalocation);
            if (targetFile.exists()) {
                return reader;
            }
            try {
                File parentDir = new File(targetFile.getParent());
                IOUtil.createDir(parentDir, null);
                CharArrayReader car = copy(reader);
                XmlEncodingSniffer xes = new XmlEncodingSniffer(car, null);
                Writer out = new OutputStreamWriter(Files.newOutputStream(targetFile.toPath(), new OpenOption[0]), xes.getXmlEncoding());
                try {
                    IOUtil.copyCompletely(car, out);
                    out.close();
                    car.reset();
                    return car;
                } finally {
                }
            } catch (IOException e) {
                System.err.println("IO Error " + e);
                return reader;
            }
        }

        private static InputStream copySchemaSource(String url, InputStream bytes, StscState state) {
            if (state.getSchemasDir() == null) {
                return bytes;
            }
            String schemalocation = state.sourceNameForUri(url);
            File targetFile = new File(state.getSchemasDir(), schemalocation);
            if (targetFile.exists()) {
                return bytes;
            }
            try {
                File parentDir = new File(targetFile.getParent());
                IOUtil.createDir(parentDir, null);
                ByteArrayInputStream bais = copy(bytes);
                OutputStream out = Files.newOutputStream(targetFile.toPath(), new OpenOption[0]);
                try {
                    IOUtil.copyCompletely(bais, out);
                    if (out != null) {
                        out.close();
                    }
                    bais.reset();
                    return bais;
                } finally {
                }
            } catch (IOException e) {
                System.err.println("IO Error " + e);
                return bytes;
            }
        }

        private static void copySchemaSource(String urlLoc, StscState state, boolean forceCopy) {
            if (state.getSchemasDir() != null) {
                String schemalocation = state.sourceNameForUri(urlLoc);
                File targetFile = new File(state.getSchemasDir(), schemalocation);
                if (forceCopy || !targetFile.exists()) {
                    InputStream in = null;
                    try {
                        try {
                            try {
                                File parentDir = new File(targetFile.getParent());
                                IOUtil.createDir(parentDir, null);
                                URL url = new URL(urlLoc);
                                try {
                                    in = url.openStream();
                                } catch (FileNotFoundException fnfe) {
                                    if (!forceCopy || !targetFile.exists()) {
                                        throw fnfe;
                                    }
                                    targetFile.delete();
                                }
                                if (in != null) {
                                    OutputStream out = Files.newOutputStream(targetFile.toPath(), new OpenOption[0]);
                                    IOUtil.copyCompletely(in, out);
                                }
                                if (in != null) {
                                    in.close();
                                }
                            } catch (IOException e) {
                                System.err.println("IO Error " + e);
                                if (0 != 0) {
                                    in.close();
                                }
                            }
                        } catch (Throwable th) {
                            if (0 != 0) {
                                try {
                                    in.close();
                                } catch (Exception e2) {
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e3) {
                    }
                }
            }
        }

        private static ByteArrayInputStream copy(InputStream is) throws IOException {
            byte[] buf = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (true) {
                try {
                    int bytesRead = is.read(buf, 0, 1024);
                    if (bytesRead > 0) {
                        baos.write(buf, 0, bytesRead);
                    } else {
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
                        baos.close();
                        return byteArrayInputStream;
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        try {
                            baos.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                        throw th2;
                    }
                }
            }
        }

        private static CharArrayReader copy(Reader is) throws IOException {
            char[] buf = new char[1024];
            CharArrayWriter charArrayWriter = new CharArrayWriter();
            while (true) {
                try {
                    int charRead = is.read(buf, 0, 1024);
                    if (charRead > 0) {
                        charArrayWriter.write(buf, 0, charRead);
                    } else {
                        CharArrayReader charArrayReader = new CharArrayReader(charArrayWriter.toCharArray());
                        charArrayWriter.close();
                        return charArrayReader;
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        try {
                            charArrayWriter.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                        throw th2;
                    }
                }
            }
        }
    }
}
