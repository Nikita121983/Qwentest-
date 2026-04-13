package org.apache.commons.collections4.properties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

/* loaded from: classes9.dex */
public abstract class AbstractPropertiesFactory<T extends Properties> {
    protected abstract T createProperties();

    /* loaded from: classes9.dex */
    public enum PropertyFormat {
        PROPERTIES,
        XML;

        static PropertyFormat toPropertyFormat(String fileName) {
            return ((String) Objects.requireNonNull(fileName, "fileName")).endsWith(".xml") ? XML : PROPERTIES;
        }
    }

    public T load(ClassLoader classLoader, String name) throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(name);
        try {
            T load = load(inputStream, PropertyFormat.toPropertyFormat(name));
            if (inputStream != null) {
                inputStream.close();
            }
            return load;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public T load(File file) throws FileNotFoundException, IOException {
        return load(file.toPath());
    }

    public T load(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        T properties = createProperties();
        properties.load(inputStream);
        return properties;
    }

    public T load(InputStream inputStream, PropertyFormat propertyFormat) throws IOException {
        if (inputStream == null) {
            return null;
        }
        T properties = createProperties();
        if (propertyFormat == PropertyFormat.XML) {
            properties.loadFromXML(inputStream);
        } else {
            properties.load(inputStream);
        }
        return properties;
    }

    public T load(Path path) throws IOException {
        InputStream inputStream = Files.newInputStream(path, new OpenOption[0]);
        try {
            T load = load(inputStream, PropertyFormat.toPropertyFormat(Objects.toString(path.getFileName(), null)));
            if (inputStream != null) {
                inputStream.close();
            }
            return load;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public T load(Reader reader) throws IOException {
        T properties = createProperties();
        properties.load(reader);
        return properties;
    }

    public T load(String name) throws IOException {
        return load(Paths.get(name, new String[0]));
    }

    public T load(URI uri) throws IOException {
        return load(Paths.get(uri));
    }

    public T load(URL url) throws IOException {
        InputStream inputStream = url.openStream();
        try {
            T load = load(inputStream, PropertyFormat.toPropertyFormat(url.getFile()));
            if (inputStream != null) {
                inputStream.close();
            }
            return load;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
