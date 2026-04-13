package org.apache.logging.log4j;

import java.io.Serializable;

/* loaded from: classes10.dex */
public interface Marker extends Serializable {
    Marker addParents(Marker... markers);

    boolean equals(Object obj);

    String getName();

    Marker[] getParents();

    boolean hasParents();

    int hashCode();

    boolean isInstanceOf(String name);

    boolean isInstanceOf(Marker m);

    boolean remove(Marker marker);

    Marker setParents(Marker... markers);
}
