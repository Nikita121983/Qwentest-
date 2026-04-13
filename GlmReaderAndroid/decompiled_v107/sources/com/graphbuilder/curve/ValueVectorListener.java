package com.graphbuilder.curve;

/* loaded from: classes.dex */
public interface ValueVectorListener {
    void valueChanged(ValueVector valueVector, int i, double d);

    void valueInserted(ValueVector valueVector, int i, double d);

    void valueRemoved(ValueVector valueVector, int i, double d);
}
