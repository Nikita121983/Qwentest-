package org.apache.poi.sl.draw.geom;

import java.awt.Shape;

/* loaded from: classes10.dex */
public class Outline {
    private final PathIf path;
    private final Shape shape;

    public Outline(Shape shape, PathIf path) {
        this.shape = shape;
        this.path = path;
    }

    public PathIf getPath() {
        return this.path;
    }

    public Shape getOutline() {
        return this.shape;
    }
}
