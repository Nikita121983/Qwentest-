package org.apache.poi.poifs.property;

/* loaded from: classes10.dex */
public final class RootProperty extends DirectoryProperty {
    private static final String NAME = "Root Entry";

    /* JADX INFO: Access modifiers changed from: package-private */
    public RootProperty() {
        super(NAME);
        setNodeColor((byte) 1);
        setPropertyType((byte) 5);
        setStartBlock(-2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RootProperty(int index, byte[] array, int offset) {
        super(index, array, offset);
    }

    @Override // org.apache.poi.poifs.property.Property
    public void setSize(int size) {
        super.setSize(Math.multiplyExact(size, 64));
    }

    @Override // org.apache.poi.poifs.property.Property
    public String getName() {
        return NAME;
    }
}
