package org.apache.xmlbeans.impl.tool;

import com.sun.org.apache.xml.internal.resolver.CatalogManager;
import com.sun.org.apache.xml.internal.resolver.tools.CatalogResolver;
import org.xml.sax.EntityResolver;

/* loaded from: classes11.dex */
public class MavenPluginResolver {
    public static EntityResolver getResolver(String catalogLocation) {
        if (catalogLocation == null) {
            return null;
        }
        CatalogManager catalogManager = CatalogManager.getStaticManager();
        catalogManager.setCatalogFiles(catalogLocation);
        return new CatalogResolver(catalogManager);
    }
}
