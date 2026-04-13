package org.apache.poi.poifs.property;

import org.apache.poi.poifs.filesystem.POIFSDocument;

/* loaded from: classes10.dex */
public class DocumentProperty extends Property {
    private POIFSDocument _document;

    public DocumentProperty(String name, int size) {
        this._document = null;
        setName(name);
        setSize(size);
        setNodeColor((byte) 1);
        setPropertyType((byte) 2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DocumentProperty(int index, byte[] array, int offset) {
        super(index, array, offset);
        this._document = null;
    }

    public void setDocument(POIFSDocument doc) {
        this._document = doc;
    }

    public POIFSDocument getDocument() {
        return this._document;
    }

    @Override // org.apache.poi.poifs.property.Property
    public boolean shouldUseSmallBlocks() {
        return super.shouldUseSmallBlocks();
    }

    @Override // org.apache.poi.poifs.property.Property
    public boolean isDirectory() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.poifs.property.Property
    public void preWrite() {
    }

    public void updateSize(int size) {
        setSize(size);
    }
}
