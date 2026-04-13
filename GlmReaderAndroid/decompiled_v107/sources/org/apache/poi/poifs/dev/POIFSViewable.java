package org.apache.poi.poifs.dev;

import java.util.Iterator;

/* loaded from: classes10.dex */
public interface POIFSViewable {
    String getShortDescription();

    Object[] getViewableArray();

    Iterator<Object> getViewableIterator();

    boolean preferArray();
}
