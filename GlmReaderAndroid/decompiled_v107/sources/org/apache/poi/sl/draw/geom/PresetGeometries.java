package org.apache.poi.sl.draw.geom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import org.apache.poi.sl.draw.geom.PresetParser;
import org.apache.poi.util.XMLHelper;

/* loaded from: classes10.dex */
public final class PresetGeometries {
    private final Map<String, CustomGeometry> map;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class SingletonHelper {
        private static final PresetGeometries INSTANCE = new PresetGeometries();

        private SingletonHelper() {
        }
    }

    public static PresetGeometries getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private PresetGeometries() {
        this.map = new TreeMap();
        try {
            InputStream is = PresetGeometries.class.getResourceAsStream("presetShapeDefinitions.xml");
            try {
                XMLInputFactory staxFactory = XMLHelper.newXMLInputFactory();
                XMLStreamReader sr = staxFactory.createXMLStreamReader(new StreamSource(is));
                try {
                    PresetParser p = new PresetParser(PresetParser.Mode.FILE);
                    p.parse(sr);
                    this.map.putAll(p.getGeom());
                    if (is != null) {
                        is.close();
                    }
                } finally {
                    sr.close();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        } catch (IOException | XMLStreamException e) {
            throw new IllegalStateException(e);
        }
    }

    public CustomGeometry get(String name) {
        if (name == null) {
            return null;
        }
        return this.map.get(name);
    }

    public Set<String> keySet() {
        return this.map.keySet();
    }

    public int size() {
        return this.map.size();
    }

    public boolean equals(Object o) {
        return this == o;
    }

    public int hashCode() {
        return Objects.hash(this.map);
    }
}
