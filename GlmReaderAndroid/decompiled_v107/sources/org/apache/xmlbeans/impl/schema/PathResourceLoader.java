package org.apache.xmlbeans.impl.schema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.xmlbeans.ResourceLoader;

/* loaded from: classes11.dex */
public class PathResourceLoader implements ResourceLoader {
    private ResourceLoader[] _path;

    public PathResourceLoader(ResourceLoader[] loaderpath) throws IOException {
        this._path = new ResourceLoader[loaderpath.length];
        System.arraycopy(loaderpath, 0, this._path, 0, this._path.length);
    }

    public PathResourceLoader(File[] filepath) {
        List<ResourceLoader> pathlist = new ArrayList<>();
        for (File file : filepath) {
            try {
                ResourceLoader path = new FileResourceLoader(file);
                pathlist.add(path);
            } catch (IOException e) {
            }
        }
        int i = pathlist.size();
        this._path = (ResourceLoader[]) pathlist.toArray(new ResourceLoader[i]);
    }

    @Override // org.apache.xmlbeans.ResourceLoader
    public InputStream getResourceAsStream(String resourceName) {
        for (int i = 0; i < this._path.length; i++) {
            InputStream result = this._path[i].getResourceAsStream(resourceName);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    @Override // org.apache.xmlbeans.ResourceLoader
    public void close() {
        for (int i = 0; i < this._path.length; i++) {
            try {
                this._path[i].close();
            } catch (Exception e) {
            }
        }
    }
}
