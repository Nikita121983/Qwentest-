package org.apache.xmlbeans.impl.tool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.apache.xmlbeans.impl.tool.SchemaImportResolver;
import org.apache.xmlbeans.impl.util.HexBin;
import org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry;
import org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

/* loaded from: classes11.dex */
public abstract class BaseSchemaResourceManager extends SchemaImportResolver {
    private static final String USER_AGENT = "XMLBeans/" + XmlBeans.getVersion() + " (" + XmlBeans.getTitle() + ")";
    private String _defaultCopyDirectory;
    private DownloadedSchemasDocument _importsDoc;
    private final Map<String, SchemaResource> _resourceForFilename = new HashMap();
    private final Map<String, SchemaResource> _resourceForURL = new HashMap();
    private final Map<String, SchemaResource> _resourceForNamespace = new HashMap();
    private final Map<String, SchemaResource> _resourceForDigest = new HashMap();
    private final Map<DownloadedSchemaEntry, SchemaResource> _resourceForCacheEntry = new HashMap();
    private Set<SchemaResource> _redownloadSet = new HashSet();

    protected abstract void deleteFile(String str);

    protected abstract boolean fileExists(String str);

    protected abstract String[] getAllXSDFilenames();

    protected abstract InputStream inputStreamForFile(String str) throws IOException;

    protected abstract void warning(String str);

    protected abstract void writeInputStreamToFile(InputStream inputStream, String str) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public final void init() {
        if (fileExists(getIndexFilename())) {
            try {
                this._importsDoc = DownloadedSchemasDocument.Factory.parse(inputStreamForFile(getIndexFilename()));
            } catch (IOException e) {
                this._importsDoc = null;
            } catch (Exception e2) {
                throw new IllegalStateException("Problem reading xsdownload.xml: please fix or delete this file", e2);
            }
        }
        if (this._importsDoc == null) {
            try {
                this._importsDoc = DownloadedSchemasDocument.Factory.parse("<dls:downloaded-schemas xmlns:dls='http://www.bea.com/2003/01/xmlbean/xsdownload' defaultDirectory='" + getDefaultSchemaDir() + "'/>");
            } catch (Exception e3) {
                throw new IllegalStateException(e3);
            }
        }
        String defaultDir = this._importsDoc.getDownloadedSchemas().getDefaultDirectory();
        if (defaultDir == null) {
            defaultDir = getDefaultSchemaDir();
        }
        this._defaultCopyDirectory = defaultDir;
        DownloadedSchemaEntry[] entries = this._importsDoc.getDownloadedSchemas().getEntryArray();
        for (DownloadedSchemaEntry entry : entries) {
            updateResource(entry);
        }
    }

    public final void writeCache() throws IOException {
        InputStream input = this._importsDoc.newInputStream(new XmlOptions().setSavePrettyPrint());
        writeInputStreamToFile(input, getIndexFilename());
    }

    public final void processAll(boolean sync, boolean refresh, boolean imports) {
        this._redownloadSet = refresh ? new HashSet() : null;
        String[] allFilenames = getAllXSDFilenames();
        if (sync) {
            syncCacheWithLocalXsdFiles(allFilenames, false);
        }
        SchemaResource[] starters = (SchemaResource[]) this._resourceForFilename.values().toArray(new SchemaResource[0]);
        if (refresh) {
            redownloadEntries(starters);
        }
        if (imports) {
            resolveImports(starters);
        }
        this._redownloadSet = null;
    }

    public final void process(String[] uris, String[] filenames, boolean sync, boolean refresh, boolean imports) {
        this._redownloadSet = refresh ? new HashSet() : null;
        if (filenames.length > 0) {
            syncCacheWithLocalXsdFiles(filenames, true);
        } else if (sync) {
            syncCacheWithLocalXsdFiles(getAllXSDFilenames(), false);
        }
        Set<SchemaResource> starterset = new HashSet<>();
        for (String s : uris) {
            SchemaResource resource = (SchemaResource) lookupResource(null, s);
            if (resource != null) {
                starterset.add(resource);
            }
        }
        for (String filename : filenames) {
            SchemaResource resource2 = this._resourceForFilename.get(filename);
            if (resource2 != null) {
                starterset.add(resource2);
            }
        }
        SchemaResource[] starters = (SchemaResource[]) starterset.toArray(new SchemaResource[0]);
        if (refresh) {
            redownloadEntries(starters);
        }
        if (imports) {
            resolveImports(starters);
        }
        this._redownloadSet = null;
    }

    public final void syncCacheWithLocalXsdFiles(String[] filenames, boolean deleteOnlyMentioned) {
        Set<SchemaResource> seenResources = new HashSet<>();
        Set<SchemaResource> vanishedResources = new HashSet<>();
        for (String filename : filenames) {
            SchemaResource resource = this._resourceForFilename.get(filename);
            if (resource != null) {
                if (fileExists(filename)) {
                    seenResources.add(resource);
                } else {
                    vanishedResources.add(resource);
                }
            } else {
                String digest = null;
                try {
                    digest = shaDigestForFile(filename);
                    SchemaResource resource2 = this._resourceForDigest.get(digest);
                    if (resource2 != null) {
                        String oldFilename = resource2.getFilename();
                        if (!fileExists(oldFilename)) {
                            warning("File " + filename + " is a rename of " + oldFilename);
                            resource2.setFilename(filename);
                            seenResources.add(resource2);
                            if (this._resourceForFilename.get(oldFilename) == resource2) {
                                this._resourceForFilename.remove(oldFilename);
                            }
                            if (this._resourceForFilename.containsKey(filename)) {
                                this._resourceForFilename.put(filename, resource2);
                            }
                        }
                    }
                } catch (IOException e) {
                }
                DownloadedSchemaEntry newEntry = addNewEntry();
                newEntry.setFilename(filename);
                warning("Caching information on new local file " + filename);
                if (digest != null) {
                    newEntry.setSha1(digest);
                }
                seenResources.add(updateResource(newEntry));
            }
        }
        if (deleteOnlyMentioned) {
            deleteResourcesInSet(vanishedResources, true);
        } else {
            deleteResourcesInSet(seenResources, false);
        }
    }

    private void redownloadEntries(SchemaResource[] resources) {
        for (SchemaResource resource : resources) {
            redownloadResource(resource);
        }
    }

    private void deleteResourcesInSet(Set<SchemaResource> seenResources, boolean setToDelete) {
        Set<DownloadedSchemaEntry> seenCacheEntries = new HashSet<>();
        Iterator<SchemaResource> it = seenResources.iterator();
        while (it.hasNext()) {
            seenCacheEntries.add(it.next()._cacheEntry);
        }
        DownloadedSchemasDocument.DownloadedSchemas downloadedSchemas = this._importsDoc.getDownloadedSchemas();
        int i = 0;
        while (i < downloadedSchemas.sizeOfEntryArray()) {
            DownloadedSchemaEntry cacheEntry = downloadedSchemas.getEntryArray(i);
            if (seenCacheEntries.contains(cacheEntry) == setToDelete) {
                SchemaResource resource = this._resourceForCacheEntry.get(cacheEntry);
                if (resource != null) {
                    warning("Removing obsolete cache entry for " + resource.getFilename());
                    this._resourceForCacheEntry.remove(cacheEntry);
                    if (resource == this._resourceForFilename.get(resource.getFilename())) {
                        this._resourceForFilename.remove(resource.getFilename());
                    }
                    if (resource == this._resourceForDigest.get(resource.getSha1())) {
                        this._resourceForDigest.remove(resource.getSha1());
                    }
                    if (resource == this._resourceForNamespace.get(resource.getNamespace())) {
                        this._resourceForNamespace.remove(resource.getNamespace());
                    }
                    String[] urls = resource.getSchemaLocationArray();
                    for (String url : urls) {
                        if (resource == this._resourceForURL.get(url)) {
                            this._resourceForURL.remove(url);
                        }
                    }
                }
                downloadedSchemas.removeEntry(i);
                i--;
            }
            i++;
        }
    }

    private SchemaResource updateResource(DownloadedSchemaEntry entry) {
        String filename = entry.getFilename();
        if (filename == null) {
            return null;
        }
        SchemaResource resource = new SchemaResource(entry);
        this._resourceForCacheEntry.put(entry, resource);
        if (!this._resourceForFilename.containsKey(filename)) {
            this._resourceForFilename.put(filename, resource);
        }
        String digest = resource.getSha1();
        if (digest != null && !this._resourceForDigest.containsKey(digest)) {
            this._resourceForDigest.put(digest, resource);
        }
        String namespace = resource.getNamespace();
        if (namespace != null && !this._resourceForNamespace.containsKey(namespace)) {
            this._resourceForNamespace.put(namespace, resource);
        }
        String[] urls = resource.getSchemaLocationArray();
        for (String url : urls) {
            if (!this._resourceForURL.containsKey(url)) {
                this._resourceForURL.put(url, resource);
            }
        }
        return resource;
    }

    private static DigestInputStream digestInputStream(InputStream input) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            return new DigestInputStream(input, sha);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    private DownloadedSchemaEntry addNewEntry() {
        return this._importsDoc.getDownloadedSchemas().addNewEntry();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class SchemaResource implements SchemaImportResolver.SchemaResource {
        DownloadedSchemaEntry _cacheEntry;

        SchemaResource(DownloadedSchemaEntry entry) {
            this._cacheEntry = entry;
        }

        public void setFilename(String filename) {
            this._cacheEntry.setFilename(filename);
        }

        public String getFilename() {
            return this._cacheEntry.getFilename();
        }

        @Override // org.apache.xmlbeans.impl.tool.SchemaImportResolver.SchemaResource
        public SchemaDocument.Schema getSchema() {
            if (!BaseSchemaResourceManager.this.fileExists(getFilename())) {
                BaseSchemaResourceManager.this.redownloadResource(this);
            }
            try {
                return SchemaDocument.Factory.parse(BaseSchemaResourceManager.this.inputStreamForFile(getFilename())).getSchema();
            } catch (Exception e) {
                return null;
            }
        }

        public String getSha1() {
            return this._cacheEntry.getSha1();
        }

        @Override // org.apache.xmlbeans.impl.tool.SchemaImportResolver.SchemaResource
        public String getNamespace() {
            return this._cacheEntry.getNamespace();
        }

        public void setNamespace(String namespace) {
            this._cacheEntry.setNamespace(namespace);
        }

        @Override // org.apache.xmlbeans.impl.tool.SchemaImportResolver.SchemaResource
        public String getSchemaLocation() {
            if (this._cacheEntry.sizeOfSchemaLocationArray() > 0) {
                return this._cacheEntry.getSchemaLocationArray(0);
            }
            return null;
        }

        public String[] getSchemaLocationArray() {
            return this._cacheEntry.getSchemaLocationArray();
        }

        public int hashCode() {
            return getFilename().hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SchemaResource)) {
                return false;
            }
            SchemaResource sr = (SchemaResource) obj;
            return getFilename().equals(sr.getFilename());
        }

        public void addSchemaLocation(String schemaLocation) {
            this._cacheEntry.addSchemaLocation(schemaLocation);
        }
    }

    @Override // org.apache.xmlbeans.impl.tool.SchemaImportResolver
    public SchemaImportResolver.SchemaResource lookupResource(String nsURI, String schemaLocation) {
        SchemaResource result = fetchFromCache(nsURI, schemaLocation);
        if (result != null) {
            if (this._redownloadSet != null) {
                redownloadResource(result);
            }
            return result;
        }
        if (schemaLocation == null) {
            warning("No cached schema for namespace '" + nsURI + "', and no url specified");
            return null;
        }
        SchemaResource result2 = copyOrIdentifyDuplicateURL(schemaLocation, nsURI);
        if (this._redownloadSet != null) {
            this._redownloadSet.add(result2);
        }
        return result2;
    }

    private SchemaResource fetchFromCache(String nsURI, String schemaLocation) {
        SchemaResource result;
        SchemaResource result2;
        if (schemaLocation != null && (result2 = this._resourceForURL.get(schemaLocation)) != null) {
            return result2;
        }
        if (nsURI != null && (result = this._resourceForNamespace.get(nsURI)) != null) {
            return result;
        }
        return null;
    }

    private String uniqueFilenameForURI(String schemaLocation) throws IOException, URISyntaxException {
        String localFilename = new URI(schemaLocation).getRawPath();
        int i = localFilename.lastIndexOf(47);
        if (i >= 0) {
            localFilename = localFilename.substring(i + 1);
        }
        if (localFilename.endsWith(".xsd")) {
            localFilename = localFilename.substring(0, localFilename.length() - 4);
        }
        if (localFilename.isEmpty()) {
            localFilename = "schema";
        }
        String candidateFilename = localFilename;
        int suffix = 1;
        while (suffix < 1000) {
            String candidate = this._defaultCopyDirectory + PackagingURIHelper.FORWARD_SLASH_STRING + candidateFilename + ".xsd";
            if (!fileExists(candidate)) {
                return candidate;
            }
            suffix++;
            candidateFilename = localFilename + suffix;
        }
        throw new IOException("Problem with filename " + localFilename + ".xsd");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void redownloadResource(SchemaResource resource) {
        if (this._redownloadSet != null) {
            if (this._redownloadSet.contains(resource)) {
                return;
            } else {
                this._redownloadSet.add(resource);
            }
        }
        String filename = resource.getFilename();
        String schemaLocation = resource.getSchemaLocation();
        if (schemaLocation == null || filename == null) {
            return;
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            URL url = new URL(schemaLocation);
            URLConnection conn = url.openConnection();
            conn.addRequestProperty("User-Agent", USER_AGENT);
            conn.addRequestProperty("Accept", "application/xml, text/xml, */*");
            DigestInputStream input = digestInputStream(conn.getInputStream());
            IOUtil.copyCompletely(input, buffer);
            String digest = HexBin.bytesToString(input.getMessageDigest().digest());
            if (digest.equals(resource.getSha1()) && fileExists(filename)) {
                warning("Resource " + filename + " is unchanged from " + schemaLocation + ".");
                return;
            }
            try {
                InputStream source = new ByteArrayInputStream(buffer.toByteArray());
                writeInputStreamToFile(source, filename);
                warning("Refreshed " + filename + " from " + schemaLocation);
            } catch (IOException e) {
                warning("Could not write to file " + filename + " for " + schemaLocation + ":" + e.getMessage());
            }
        } catch (Exception e2) {
            warning("Could not copy remote resource " + schemaLocation + ":" + e2.getMessage());
        }
    }

    private SchemaResource copyOrIdentifyDuplicateURL(String schemaLocation, String namespace) {
        try {
            String targetFilename = uniqueFilenameForURI(schemaLocation);
            try {
                URL url = new URL(schemaLocation);
                DigestInputStream input = digestInputStream(url.openStream());
                writeInputStreamToFile(input, targetFilename);
                String digest = HexBin.bytesToString(input.getMessageDigest().digest());
                SchemaResource result = this._resourceForDigest.get(digest);
                if (result != null) {
                    deleteFile(targetFilename);
                    result.addSchemaLocation(schemaLocation);
                    if (!this._resourceForURL.containsKey(schemaLocation)) {
                        this._resourceForURL.put(schemaLocation, result);
                    }
                    return result;
                }
                warning("Downloaded " + schemaLocation + " to " + targetFilename);
                DownloadedSchemaEntry newEntry = addNewEntry();
                newEntry.setFilename(targetFilename);
                newEntry.setSha1(digest);
                if (namespace != null) {
                    newEntry.setNamespace(namespace);
                }
                newEntry.addSchemaLocation(schemaLocation);
                return updateResource(newEntry);
            } catch (Exception e) {
                warning("Could not copy remote resource " + schemaLocation + ":" + e.getMessage());
                return null;
            }
        } catch (IOException e2) {
            warning("Could not create local file for " + schemaLocation + ":" + e2.getMessage());
            return null;
        } catch (URISyntaxException e3) {
            warning("Invalid URI '" + schemaLocation + "':" + e3.getMessage());
            return null;
        }
    }

    @Override // org.apache.xmlbeans.impl.tool.SchemaImportResolver
    public void reportActualNamespace(SchemaImportResolver.SchemaResource rresource, String actualNamespace) {
        SchemaResource resource = (SchemaResource) rresource;
        String oldNamespace = resource.getNamespace();
        if (oldNamespace != null && this._resourceForNamespace.get(oldNamespace) == resource) {
            this._resourceForNamespace.remove(oldNamespace);
        }
        if (!this._resourceForNamespace.containsKey(actualNamespace)) {
            this._resourceForNamespace.put(actualNamespace, resource);
        }
        resource.setNamespace(actualNamespace);
    }

    private String shaDigestForFile(String filename) throws IOException {
        DigestInputStream str = digestInputStream(inputStreamForFile(filename));
        byte[] dummy = new byte[4096];
        for (int i = 1; i > 0; i = str.read(dummy)) {
        }
        str.close();
        return HexBin.bytesToString(str.getMessageDigest().digest());
    }

    protected String getIndexFilename() {
        return "./xsdownload.xml";
    }

    protected String getDefaultSchemaDir() {
        return "./schema";
    }
}
