package org.apache.commons.io.file.spi;

import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/* loaded from: classes9.dex */
public class FileSystemProviders {
    private static final FileSystemProviders INSTALLED = new FileSystemProviders(FileSystemProvider.installedProviders());
    private static final String SCHEME_FILE = "file";
    private final List<FileSystemProvider> providers;

    public static FileSystemProvider getFileSystemProvider(Path path) {
        return ((Path) Objects.requireNonNull(path, "path")).getFileSystem().provider();
    }

    public static FileSystemProviders installed() {
        return INSTALLED;
    }

    private FileSystemProviders(List<FileSystemProvider> providers) {
        this.providers = providers != null ? providers : Collections.emptyList();
    }

    public FileSystemProvider getFileSystemProvider(final String scheme) {
        Objects.requireNonNull(scheme, "scheme");
        if (scheme.equalsIgnoreCase(SCHEME_FILE)) {
            return FileSystems.getDefault().provider();
        }
        return this.providers.stream().filter(new Predicate() { // from class: org.apache.commons.io.file.spi.FileSystemProviders$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equalsIgnoreCase;
                equalsIgnoreCase = ((FileSystemProvider) obj).getScheme().equalsIgnoreCase(scheme);
                return equalsIgnoreCase;
            }
        }).findFirst().orElse(null);
    }

    public FileSystemProvider getFileSystemProvider(URI uri) {
        return getFileSystemProvider(((URI) Objects.requireNonNull(uri, "uri")).getScheme());
    }

    public FileSystemProvider getFileSystemProvider(URL url) {
        return getFileSystemProvider(((URL) Objects.requireNonNull(url, "url")).getProtocol());
    }
}
