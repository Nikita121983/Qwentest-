package org.apache.poi.poifs.eventfilesystem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.POIFSDocument;
import org.apache.poi.poifs.filesystem.POIFSDocumentPath;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.poifs.property.DirectoryProperty;
import org.apache.poi.poifs.property.DocumentProperty;
import org.apache.poi.poifs.property.Property;
import org.apache.poi.poifs.property.PropertyTable;
import org.apache.poi.poifs.property.RootProperty;
import org.apache.poi.util.IOUtils;

/* loaded from: classes10.dex */
public class POIFSReader {
    private boolean notifyEmptyDirectories;
    private final POIFSReaderRegistry registry = new POIFSReaderRegistry();
    private boolean registryClosed = false;

    public void read(InputStream stream) throws IOException {
        POIFSFileSystem poifs = new POIFSFileSystem(stream);
        try {
            read(poifs);
            poifs.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    poifs.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public void read(File poifsFile) throws IOException {
        POIFSFileSystem poifs = new POIFSFileSystem(poifsFile, true);
        try {
            read(poifs);
            poifs.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    poifs.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public void read(POIFSFileSystem poifs) throws IOException {
        this.registryClosed = true;
        PropertyTable properties = poifs.getPropertyTable();
        RootProperty root = properties.getRoot();
        processProperties(poifs, root, new POIFSDocumentPath());
    }

    public void registerListener(POIFSReaderListener listener) {
        if (listener == null) {
            throw new NullPointerException();
        }
        if (this.registryClosed) {
            throw new IllegalStateException();
        }
        this.registry.registerListener(listener);
    }

    public void registerListener(POIFSReaderListener listener, String name) {
        registerListener(listener, null, name);
    }

    public void registerListener(POIFSReaderListener listener, POIFSDocumentPath path, String name) {
        if (listener == null || name == null) {
            throw new NullPointerException("invalid null parameter");
        }
        if (name.isEmpty()) {
            throw new IllegalStateException("name must not be empty");
        }
        if (this.registryClosed) {
            throw new IllegalStateException("registry closed");
        }
        this.registry.registerListener(listener, path == null ? new POIFSDocumentPath() : path, name);
    }

    public void setNotifyEmptyDirectories(boolean notifyEmptyDirectories) {
        this.notifyEmptyDirectories = notifyEmptyDirectories;
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("at least one argument required: input filename(s)");
            System.exit(1);
        }
        for (String arg : args) {
            POIFSReader reader = new POIFSReader();
            reader.registerListener(new POIFSReaderListener() { // from class: org.apache.poi.poifs.eventfilesystem.POIFSReader$$ExternalSyntheticLambda0
                @Override // org.apache.poi.poifs.eventfilesystem.POIFSReaderListener
                public final void processPOIFSReaderEvent(POIFSReaderEvent pOIFSReaderEvent) {
                    POIFSReader.readEntry(pOIFSReaderEvent);
                }
            });
            System.out.println("reading " + arg);
            reader.read(new File(arg));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void readEntry(POIFSReaderEvent event) {
        POIFSDocumentPath path = event.getPath();
        StringBuilder sb = new StringBuilder();
        try {
            DocumentInputStream istream = event.getStream();
            try {
                sb.setLength(0);
                int pathLength = path.length();
                for (int k = 0; k < pathLength; k++) {
                    sb.append('/').append(path.getComponent(k));
                }
                byte[] data = IOUtils.toByteArray(istream);
                sb.append('/').append(event.getName()).append(": ").append(data.length).append(" bytes read");
                System.out.println(sb);
                if (istream != null) {
                    istream.close();
                }
            } finally {
            }
        } catch (IOException e) {
        }
    }

    private void processProperties(POIFSFileSystem poifs, DirectoryProperty dir, POIFSDocumentPath path) {
        boolean hasChildren = false;
        Iterator<Property> it = dir.iterator();
        while (it.hasNext()) {
            Property property = it.next();
            hasChildren = true;
            String name = property.getName();
            if (property.isDirectory()) {
                POIFSDocumentPath new_path = new POIFSDocumentPath(path, new String[]{name});
                processProperties(poifs, (DirectoryProperty) property, new_path);
            } else {
                POIFSDocument document = null;
                for (POIFSReaderListener rl : this.registry.getListeners(path, name)) {
                    if (document == null) {
                        document = new POIFSDocument((DocumentProperty) property, poifs);
                    }
                    DocumentInputStream dis = new DocumentInputStream(document);
                    try {
                        POIFSReaderEvent pe = new POIFSReaderEvent(dis, path, name, dir.getStorageClsid());
                        rl.processPOIFSReaderEvent(pe);
                        dis.close();
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            try {
                                dis.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                            throw th2;
                        }
                    }
                }
            }
        }
        if (hasChildren || !this.notifyEmptyDirectories) {
            return;
        }
        for (POIFSReaderListener rl2 : this.registry.getListeners(path, ".")) {
            POIFSReaderEvent pe2 = new POIFSReaderEvent(null, path, null, dir.getStorageClsid());
            rl2.processPOIFSReaderEvent(pe2);
        }
    }
}
