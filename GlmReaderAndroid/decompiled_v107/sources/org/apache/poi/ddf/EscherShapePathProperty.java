package org.apache.poi.ddf;

/* loaded from: classes10.dex */
public class EscherShapePathProperty extends EscherSimpleProperty {
    public static final int CLOSED_CURVES = 3;
    public static final int CLOSED_POLYGON = 1;
    public static final int COMPLEX = 4;
    public static final int CURVES = 2;
    public static final int LINE_OF_STRAIGHT_SEGMENTS = 0;

    public EscherShapePathProperty(short propertyNumber, int shapePath) {
        super(propertyNumber, false, false, shapePath);
    }

    public EscherShapePathProperty(EscherPropertyTypes type, int shapePath) {
        super(type, false, false, shapePath);
    }
}
