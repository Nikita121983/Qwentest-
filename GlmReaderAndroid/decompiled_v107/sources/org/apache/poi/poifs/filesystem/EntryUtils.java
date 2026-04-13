package org.apache.poi.poifs.filesystem;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.poi.hpsf.NoPropertySetStreamException;
import org.apache.poi.hpsf.PropertySet;
import org.apache.poi.hpsf.PropertySetFactory;
import org.apache.poi.poifs.filesystem.EntryUtils;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public final class EntryUtils {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public interface POIDelegate {
    }

    private EntryUtils() {
    }

    @Internal
    public static void copyNodeRecursively(Entry entry, DirectoryEntry target) throws IOException {
        if (entry.isDirectoryEntry()) {
            DirectoryEntry dirEntry = (DirectoryEntry) entry;
            DirectoryEntry newTarget = target.createDirectory(entry.getName());
            newTarget.setStorageClsid(dirEntry.getStorageClsid());
            Iterator<Entry> entries = dirEntry.getEntries();
            while (entries.hasNext()) {
                copyNodeRecursively(entries.next(), newTarget);
            }
            return;
        }
        DocumentEntry dentry = (DocumentEntry) entry;
        DocumentInputStream dstream = new DocumentInputStream(dentry);
        target.createDocument(dentry.getName(), dstream);
        dstream.close();
    }

    public static void copyNodes(DirectoryEntry sourceRoot, DirectoryEntry targetRoot) throws IOException {
        for (Entry entry : sourceRoot) {
            copyNodeRecursively(entry, targetRoot);
        }
    }

    public static void copyNodes(POIFSFileSystem source, POIFSFileSystem target) throws IOException {
        copyNodes(source.getRoot(), target.getRoot());
    }

    public static void copyNodes(POIFSFileSystem source, POIFSFileSystem target, List<String> excepts) throws IOException {
        copyNodes(new FilteringDirectoryNode(source.getRoot(), excepts), new FilteringDirectoryNode(target.getRoot(), excepts));
    }

    public static boolean areDirectoriesIdentical(DirectoryEntry dirA, DirectoryEntry dirB) {
        return new DirectoryDelegate(dirA).equals(new DirectoryDelegate(dirB));
    }

    public static boolean areDocumentsIdentical(DocumentEntry docA, DocumentEntry docB) throws IOException {
        try {
            return new DocumentDelegate(docA).equals(new DocumentDelegate(docB));
        } catch (RuntimeException e) {
            if (e.getCause() instanceof IOException) {
                throw ((IOException) e.getCause());
            }
            throw e;
        }
    }

    /* loaded from: classes10.dex */
    private static class DirectoryDelegate implements POIDelegate {
        final DirectoryEntry dir;

        DirectoryDelegate(DirectoryEntry dir) {
            this.dir = dir;
        }

        private Map<String, POIDelegate> entries() {
            return (Map) StreamSupport.stream(this.dir.spliterator(), false).collect(Collectors.toMap(new Function() { // from class: org.apache.poi.poifs.filesystem.EntryUtils$DirectoryDelegate$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Entry) obj).getName();
                }
            }, new Function() { // from class: org.apache.poi.poifs.filesystem.EntryUtils$DirectoryDelegate$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    EntryUtils.POIDelegate delegate;
                    delegate = EntryUtils.DirectoryDelegate.toDelegate((Entry) obj);
                    return delegate;
                }
            }));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static POIDelegate toDelegate(Entry entry) {
            return entry.isDirectoryEntry() ? new DirectoryDelegate((DirectoryEntry) entry) : new DocumentDelegate((DocumentEntry) entry);
        }

        public int hashCode() {
            return this.dir.getName().hashCode();
        }

        public boolean equals(Object other) {
            if (!(other instanceof DirectoryDelegate)) {
                return false;
            }
            DirectoryDelegate dd = (DirectoryDelegate) other;
            if (this == dd) {
                return true;
            }
            if (Objects.equals(this.dir.getName(), dd.dir.getName()) && this.dir.getEntryCount() == dd.dir.getEntryCount() && this.dir.getStorageClsid().equals(dd.dir.getStorageClsid())) {
                return entries().equals(dd.entries());
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class DocumentDelegate implements POIDelegate {
        final DocumentEntry doc;

        DocumentDelegate(DocumentEntry doc) {
            this.doc = doc;
        }

        public int hashCode() {
            return this.doc.getName().hashCode();
        }

        public boolean equals(Object other) {
            if (!(other instanceof DocumentDelegate)) {
                return false;
            }
            DocumentDelegate dd = (DocumentDelegate) other;
            if (this == dd) {
                return true;
            }
            if (!Objects.equals(this.doc.getName(), dd.doc.getName())) {
                return false;
            }
            try {
                DocumentInputStream inpA = new DocumentInputStream(this.doc);
                try {
                    DocumentInputStream inpB = new DocumentInputStream(dd.doc);
                    try {
                        if (!PropertySet.isPropertySetStream(inpA) || !PropertySet.isPropertySetStream(inpB)) {
                            boolean isEqual = isEqual(inpA, inpB);
                            inpB.close();
                            inpA.close();
                            return isEqual;
                        }
                        PropertySet ps1 = PropertySetFactory.create(inpA);
                        PropertySet ps2 = PropertySetFactory.create(inpB);
                        boolean equals = ps1.equals(ps2);
                        inpB.close();
                        inpA.close();
                        return equals;
                    } finally {
                    }
                } finally {
                }
            } catch (IOException | NoPropertySetStreamException ex) {
                throw new IllegalStateException(ex);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x0023, code lost:
        
            if (r8.read() >= 0) goto L26;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0025, code lost:
        
            return true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:?, code lost:
        
            return false;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static boolean isEqual(org.apache.poi.poifs.filesystem.DocumentInputStream r7, org.apache.poi.poifs.filesystem.DocumentInputStream r8) throws java.io.IOException {
            /*
                r0 = 4096(0x1000, float:5.74E-42)
                byte[] r1 = new byte[r0]
                byte[] r0 = new byte[r0]
            L6:
                r2 = 0
                int r3 = r7.read(r1)     // Catch: java.lang.RuntimeException -> L27 java.io.EOFException -> L29
                r4 = r3
                if (r3 <= 0) goto L1f
                r8.readFully(r0, r2, r4)     // Catch: java.lang.RuntimeException -> L27 java.io.EOFException -> L29
                r3 = 0
            L12:
                if (r3 >= r4) goto L1e
                r5 = r1[r3]     // Catch: java.lang.RuntimeException -> L27 java.io.EOFException -> L29
                r6 = r0[r3]     // Catch: java.lang.RuntimeException -> L27 java.io.EOFException -> L29
                if (r5 == r6) goto L1b
                return r2
            L1b:
                int r3 = r3 + 1
                goto L12
            L1e:
                goto L6
            L1f:
                int r3 = r8.read()     // Catch: java.lang.RuntimeException -> L27 java.io.EOFException -> L29
                if (r3 >= 0) goto L26
                r2 = 1
            L26:
                return r2
            L27:
                r3 = move-exception
                goto L2a
            L29:
                r3 = move-exception
            L2a:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.filesystem.EntryUtils.DocumentDelegate.isEqual(org.apache.poi.poifs.filesystem.DocumentInputStream, org.apache.poi.poifs.filesystem.DocumentInputStream):boolean");
        }
    }
}
