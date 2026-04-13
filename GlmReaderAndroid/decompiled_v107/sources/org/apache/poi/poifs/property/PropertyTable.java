package org.apache.poi.poifs.property;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.poifs.common.POIFSBigBlockSize;
import org.apache.poi.poifs.filesystem.BATManaged;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.poifs.filesystem.POIFSStream;
import org.apache.poi.poifs.storage.HeaderBlock;
import org.apache.poi.util.IOUtils;

/* loaded from: classes10.dex */
public final class PropertyTable implements BATManaged {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) PropertyTable.class);
    private static final int MAX_PROPERTY_DEPTH = 1000;
    private final POIFSBigBlockSize _bigBigBlockSize;
    private final HeaderBlock _header_block;
    private final List<Property> _properties;

    public PropertyTable(HeaderBlock headerBlock) {
        this._properties = new ArrayList();
        this._header_block = headerBlock;
        this._bigBigBlockSize = headerBlock.getBigBlockSize();
        addProperty(new RootProperty());
    }

    public PropertyTable(HeaderBlock headerBlock, POIFSFileSystem filesystem) throws IOException {
        this(headerBlock, new POIFSStream(filesystem, headerBlock.getPropertyStart()));
    }

    PropertyTable(HeaderBlock headerBlock, Iterable<ByteBuffer> dataSource) throws IOException {
        this._properties = new ArrayList();
        this._header_block = headerBlock;
        this._bigBigBlockSize = headerBlock.getBigBlockSize();
        for (ByteBuffer bb : dataSource) {
            byte[] data = null;
            if (bb.hasArray() && bb.arrayOffset() == 0) {
                byte[] array = bb.array();
                if (array.length == this._bigBigBlockSize.getBigBlockSize()) {
                    data = array;
                }
            }
            if (data == null) {
                data = IOUtils.safelyAllocate(this._bigBigBlockSize.getBigBlockSize(), POIFSFileSystem.getMaxRecordLength());
                int toRead = data.length;
                if (bb.remaining() < this._bigBigBlockSize.getBigBlockSize()) {
                    LOG.atWarn().log("Short Property Block, {} bytes instead of the expected {}", Unbox.box(bb.remaining()), Unbox.box(this._bigBigBlockSize.getBigBlockSize()));
                    toRead = bb.remaining();
                }
                bb.get(data, 0, toRead);
            }
            PropertyFactory.convertToProperties(data, this._properties);
        }
        Property property = this._properties.get(0);
        if (property != null) {
            if (property instanceof DirectoryProperty) {
                populatePropertyTree((DirectoryProperty) property, 0);
                return;
            }
            throw new IOException("Invalid format, cannot convert property " + property + " to DirectoryProperty");
        }
    }

    public void addProperty(Property property) {
        this._properties.add(property);
    }

    public void removeProperty(Property property) {
        this._properties.remove(property);
    }

    public RootProperty getRoot() {
        Property property = this._properties.get(0);
        if (property instanceof RootProperty) {
            return (RootProperty) property;
        }
        throw new IllegalStateException("Invalid format, cannot convert property " + property + " to RootProperty");
    }

    public int getStartBlock() {
        return this._header_block.getPropertyStart();
    }

    @Override // org.apache.poi.poifs.filesystem.BATManaged
    public void setStartBlock(int index) {
        this._header_block.setPropertyStart(index);
    }

    @Override // org.apache.poi.poifs.filesystem.BATManaged
    public int countBlocks() {
        long rawSize = this._properties.size() * 128;
        int blkSize = this._bigBigBlockSize.getBigBlockSize();
        int numBlocks = (int) (rawSize / blkSize);
        if (rawSize % blkSize != 0) {
            return numBlocks + 1;
        }
        return numBlocks;
    }

    public void preWrite() {
        List<Property> pList = new ArrayList<>();
        int i = 0;
        for (Property p : this._properties) {
            if (p != null) {
                p.setIndex(i);
                pList.add(p);
                i++;
            }
        }
        Iterator<Property> it = pList.iterator();
        while (it.hasNext()) {
            it.next().preWrite();
        }
    }

    public void write(POIFSStream stream) throws IOException {
        OutputStream os = stream.getOutputStream();
        for (Property property : this._properties) {
            if (property != null) {
                property.writeData(os);
            }
        }
        os.close();
        if (getStartBlock() != stream.getStartBlock()) {
            setStartBlock(stream.getStartBlock());
        }
        this._header_block.setPropertyCount(countBlocks());
    }

    private void populatePropertyTree(DirectoryProperty root, int depth) throws IOException {
        if (depth > 1000) {
            throw new IOException("Property tree too deep, likely a corrupt file");
        }
        int index = root.getChildIndex();
        if (!Property.isValidIndex(index)) {
            return;
        }
        Stack<Property> children = new Stack<>();
        children.push(this._properties.get(index));
        while (!children.empty()) {
            Property property = children.pop();
            if (property != null) {
                root.addChild(property);
                if (property.isDirectory()) {
                    populatePropertyTree((DirectoryProperty) property, depth + 1);
                }
                int index2 = property.getPreviousChildIndex();
                if (isValidIndex(index2)) {
                    children.push(this._properties.get(index2));
                }
                int index3 = property.getNextChildIndex();
                if (isValidIndex(index3)) {
                    children.push(this._properties.get(index3));
                }
            }
        }
    }

    private boolean isValidIndex(int index) {
        if (!Property.isValidIndex(index)) {
            return false;
        }
        if (index < 0 || index >= this._properties.size()) {
            LOG.atWarn().log("Property index {} outside the valid range 0..{}", Unbox.box(index), Unbox.box(this._properties.size()));
            return false;
        }
        return true;
    }
}
