package org.apache.poi.poifs.filesystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.poifs.dev.POIFSViewable;
import org.apache.poi.poifs.property.DirectoryProperty;
import org.apache.poi.poifs.property.DocumentProperty;
import org.apache.poi.poifs.property.Property;

/* loaded from: classes10.dex */
public class DirectoryNode extends EntryNode implements DirectoryEntry, POIFSViewable, Iterable<Entry> {
    private final Map<String, Entry> _byUCName;
    private final Map<String, Entry> _byname;
    private final ArrayList<Entry> _entries;
    private final POIFSFileSystem _filesystem;
    private final POIFSDocumentPath _path;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DirectoryNode(DirectoryProperty property, POIFSFileSystem filesystem, DirectoryNode parent) {
        super(property, parent);
        Entry childNode;
        this._byname = new HashMap();
        this._byUCName = new HashMap();
        this._entries = new ArrayList<>();
        this._filesystem = filesystem;
        if (parent == null) {
            this._path = new POIFSDocumentPath();
        } else {
            this._path = new POIFSDocumentPath(parent._path, new String[]{property.getName()});
        }
        Iterator<Property> iter = property.getChildren();
        while (iter.hasNext()) {
            Property child = iter.next();
            if (child.isDirectory()) {
                DirectoryProperty childDir = (DirectoryProperty) child;
                childNode = new DirectoryNode(childDir, this._filesystem, this);
            } else {
                childNode = new DocumentNode((DocumentProperty) child, this);
            }
            this._entries.add(childNode);
            this._byname.put(childNode.getName(), childNode);
            this._byUCName.put(childNode.getName().toUpperCase(Locale.ROOT), childNode);
        }
    }

    public POIFSDocumentPath getPath() {
        return this._path;
    }

    public POIFSFileSystem getFileSystem() {
        return this._filesystem;
    }

    public DocumentInputStream createDocumentInputStream(String documentName) throws IOException {
        return createDocumentInputStream(getEntryCaseInsensitive(documentName));
    }

    public DocumentInputStream createDocumentInputStream(Entry document) throws IOException {
        if (!document.isDocumentEntry()) {
            throw new IOException("Entry '" + document.getName() + "' is not a DocumentEntry");
        }
        DocumentEntry entry = (DocumentEntry) document;
        return new DocumentInputStream(entry);
    }

    DocumentEntry createDocument(POIFSDocument document) throws IOException {
        DocumentProperty property = document.getDocumentProperty();
        DocumentNode rval = new DocumentNode(property, this);
        ((DirectoryProperty) getProperty()).addChild(property);
        this._filesystem.addDocument(document);
        this._entries.add(rval);
        this._byname.put(property.getName(), rval);
        this._byUCName.put(property.getName().toUpperCase(Locale.ROOT), rval);
        return rval;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean changeName(String oldName, String newName) {
        boolean rval = false;
        EntryNode child = (EntryNode) this._byUCName.get(oldName.toUpperCase(Locale.ROOT));
        if (child != null && (rval = ((DirectoryProperty) getProperty()).changeName(child.getProperty(), newName))) {
            this._byname.remove(oldName);
            this._byname.put(child.getProperty().getName(), child);
            this._byUCName.remove(oldName.toUpperCase(Locale.ROOT));
            this._byUCName.put(child.getProperty().getName().toUpperCase(Locale.ROOT), child);
        }
        return rval;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean deleteEntry(EntryNode entry) {
        boolean rval = ((DirectoryProperty) getProperty()).deleteChild(entry.getProperty());
        if (rval) {
            this._entries.remove(entry);
            this._byname.remove(entry.getName());
            this._byUCName.remove(entry.getName().toUpperCase(Locale.ROOT));
            try {
                this._filesystem.remove(entry);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        return rval;
    }

    @Override // org.apache.poi.poifs.filesystem.DirectoryEntry
    public Iterator<Entry> getEntries() {
        return this._entries.iterator();
    }

    @Override // org.apache.poi.poifs.filesystem.DirectoryEntry
    public Set<String> getEntryNames() {
        return this._byname.keySet();
    }

    @Override // org.apache.poi.poifs.filesystem.DirectoryEntry
    public boolean isEmpty() {
        return this._entries.isEmpty();
    }

    @Override // org.apache.poi.poifs.filesystem.DirectoryEntry
    public int getEntryCount() {
        return this._entries.size();
    }

    @Override // org.apache.poi.poifs.filesystem.DirectoryEntry
    public boolean hasEntry(String name) {
        return name != null && this._byname.containsKey(name);
    }

    @Override // org.apache.poi.poifs.filesystem.DirectoryEntry
    public boolean hasEntryCaseInsensitive(String name) {
        return name != null && this._byUCName.containsKey(name.toUpperCase(Locale.ROOT));
    }

    @Override // org.apache.poi.poifs.filesystem.DirectoryEntry
    public Entry getEntry(String name) throws FileNotFoundException {
        Entry rval = null;
        if (name != null) {
            Entry rval2 = this._byname.get(name);
            rval = rval2;
        }
        if (rval == null) {
            if (this._byname.containsKey("Workbook")) {
                throw new IllegalArgumentException("The document is really a XLS file");
            }
            if (this._byname.containsKey("PowerPoint Document")) {
                throw new IllegalArgumentException("The document is really a PPT file");
            }
            if (this._byname.containsKey("VisioDocument")) {
                throw new IllegalArgumentException("The document is really a VSD file");
            }
            throw new FileNotFoundException("no such entry: \"" + name + "\", had: " + this._byname.keySet());
        }
        return rval;
    }

    @Override // org.apache.poi.poifs.filesystem.DirectoryEntry
    public Entry getEntryCaseInsensitive(String name) throws FileNotFoundException {
        Entry rval = null;
        if (name != null) {
            Entry rval2 = this._byUCName.get(name.toUpperCase(Locale.ROOT));
            rval = rval2;
        }
        if (rval == null) {
            if (this._byname.containsKey("Workbook")) {
                throw new IllegalArgumentException("The document is really a XLS file");
            }
            if (this._byname.containsKey("PowerPoint Document")) {
                throw new IllegalArgumentException("The document is really a PPT file");
            }
            if (this._byname.containsKey("VisioDocument")) {
                throw new IllegalArgumentException("The document is really a VSD file");
            }
            throw new FileNotFoundException("no such entry: \"" + name + "\", had: " + this._byUCName.keySet());
        }
        return rval;
    }

    @Override // org.apache.poi.poifs.filesystem.DirectoryEntry
    public DocumentEntry createDocument(String name, InputStream stream) throws IOException {
        return createDocument(new POIFSDocument(name, this._filesystem, stream));
    }

    @Override // org.apache.poi.poifs.filesystem.DirectoryEntry
    public DocumentEntry createDocument(String name, int size, POIFSWriterListener writer) throws IOException {
        return createDocument(new POIFSDocument(name, size, this._filesystem, writer));
    }

    @Override // org.apache.poi.poifs.filesystem.DirectoryEntry
    public DirectoryEntry createDirectory(String name) throws IOException {
        DirectoryProperty property = new DirectoryProperty(name);
        DirectoryNode rval = new DirectoryNode(property, this._filesystem, this);
        this._filesystem.addDirectory(property);
        ((DirectoryProperty) getProperty()).addChild(property);
        this._entries.add(rval);
        this._byname.put(name, rval);
        this._byUCName.put(name.toUpperCase(Locale.ROOT), rval);
        return rval;
    }

    public DocumentEntry createOrUpdateDocument(String name, InputStream stream) throws IOException {
        if (!hasEntryCaseInsensitive(name)) {
            return createDocument(name, stream);
        }
        DocumentNode existing = (DocumentNode) getEntryCaseInsensitive(name);
        POIFSDocument nDoc = new POIFSDocument(existing);
        nDoc.replaceContents(stream);
        return existing;
    }

    @Override // org.apache.poi.poifs.filesystem.DirectoryEntry
    public ClassID getStorageClsid() {
        return getProperty().getStorageClsid();
    }

    @Override // org.apache.poi.poifs.filesystem.DirectoryEntry
    public void setStorageClsid(ClassID clsidStorage) {
        getProperty().setStorageClsid(clsidStorage);
    }

    @Override // org.apache.poi.poifs.filesystem.EntryNode, org.apache.poi.poifs.filesystem.Entry
    public boolean isDirectoryEntry() {
        return true;
    }

    @Override // org.apache.poi.poifs.filesystem.EntryNode
    protected boolean isDeleteOK() {
        return isEmpty();
    }

    @Override // org.apache.poi.poifs.dev.POIFSViewable
    public Object[] getViewableArray() {
        return new Object[0];
    }

    @Override // org.apache.poi.poifs.dev.POIFSViewable
    public Iterator<Object> getViewableIterator() {
        List<Object> components = new ArrayList<>();
        components.add(getProperty());
        components.addAll(this._entries);
        return components.iterator();
    }

    @Override // org.apache.poi.poifs.dev.POIFSViewable
    public boolean preferArray() {
        return false;
    }

    @Override // org.apache.poi.poifs.dev.POIFSViewable
    public String getShortDescription() {
        return getName();
    }

    @Override // java.lang.Iterable
    public Iterator<Entry> iterator() {
        return getEntries();
    }

    @Override // java.lang.Iterable
    public Spliterator<Entry> spliterator() {
        return this._entries.spliterator();
    }
}
