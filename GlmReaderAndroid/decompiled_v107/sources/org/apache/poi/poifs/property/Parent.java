package org.apache.poi.poifs.property;

import java.io.IOException;
import java.util.Iterator;

/* loaded from: classes10.dex */
public interface Parent extends Child, Iterable<Property> {
    void addChild(Property property) throws IOException;

    Iterator<Property> getChildren();

    @Override // org.apache.poi.poifs.property.Child
    void setNextChild(Child child);

    @Override // org.apache.poi.poifs.property.Child
    void setPreviousChild(Child child);
}
