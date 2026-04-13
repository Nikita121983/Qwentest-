package org.apache.poi.poifs.dev;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.SystemProperties;

/* loaded from: classes10.dex */
public class POIFSViewEngine {
    private static final String _EOL = System.getProperty(SystemProperties.LINE_SEPARATOR);

    public static List<String> inspectViewable(Object viewable, boolean drilldown, int indentLevel, String indentString) {
        List<String> objects = new ArrayList<>();
        if (viewable instanceof POIFSViewable) {
            POIFSViewable inspected = (POIFSViewable) viewable;
            objects.add(indent(indentLevel, indentString, inspected.getShortDescription()));
            if (drilldown) {
                if (inspected.preferArray()) {
                    Object[] data = inspected.getViewableArray();
                    for (Object datum : data) {
                        objects.addAll(inspectViewable(datum, drilldown, indentLevel + 1, indentString));
                    }
                } else {
                    Iterator<Object> iter = inspected.getViewableIterator();
                    while (iter.hasNext()) {
                        objects.addAll(inspectViewable(iter.next(), drilldown, indentLevel + 1, indentString));
                    }
                }
            }
        } else {
            objects.add(indent(indentLevel, indentString, viewable.toString()));
        }
        return objects;
    }

    private static String indent(int indentLevel, String indentString, String data) {
        StringBuilder finalBuffer = new StringBuilder();
        StringBuilder indentPrefix = new StringBuilder();
        for (int j = 0; j < indentLevel; j++) {
            indentPrefix.append(indentString);
        }
        LineNumberReader reader = new LineNumberReader(new StringReader(data));
        try {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                finalBuffer.append((CharSequence) indentPrefix).append(line).append(_EOL);
            }
        } catch (IOException e) {
            finalBuffer.append((CharSequence) indentPrefix).append(e.getMessage()).append(_EOL);
        }
        return finalBuffer.toString();
    }
}
