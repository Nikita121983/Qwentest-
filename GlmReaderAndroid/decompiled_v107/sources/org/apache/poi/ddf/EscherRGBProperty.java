package org.apache.poi.ddf;

/* loaded from: classes10.dex */
public class EscherRGBProperty extends EscherSimpleProperty {
    public EscherRGBProperty(short propertyNumber, int rgbColor) {
        super(propertyNumber, rgbColor);
    }

    public EscherRGBProperty(EscherPropertyTypes propertyType, int rgbColor) {
        super(propertyType.propNumber, rgbColor);
    }

    public int getRgbColor() {
        return getPropertyValue();
    }

    public byte getRed() {
        return (byte) (getRgbColor() & 255);
    }

    public byte getGreen() {
        return (byte) ((getRgbColor() >> 8) & 255);
    }

    public byte getBlue() {
        return (byte) ((getRgbColor() >> 16) & 255);
    }
}
