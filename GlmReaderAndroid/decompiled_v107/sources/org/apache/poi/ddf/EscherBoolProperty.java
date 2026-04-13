package org.apache.poi.ddf;

/* loaded from: classes10.dex */
public class EscherBoolProperty extends EscherSimpleProperty {
    public EscherBoolProperty(short propertyNumber, int value) {
        super(propertyNumber, value);
    }

    public EscherBoolProperty(EscherPropertyTypes propertyType, int value) {
        super(propertyType.propNumber, value);
    }

    public boolean isTrue() {
        return getPropertyValue() != 0;
    }
}
