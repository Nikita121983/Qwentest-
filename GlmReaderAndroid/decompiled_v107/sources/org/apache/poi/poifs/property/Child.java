package org.apache.poi.poifs.property;

/* loaded from: classes10.dex */
public interface Child {
    Child getNextChild();

    Child getPreviousChild();

    void setNextChild(Child child);

    void setPreviousChild(Child child);
}
