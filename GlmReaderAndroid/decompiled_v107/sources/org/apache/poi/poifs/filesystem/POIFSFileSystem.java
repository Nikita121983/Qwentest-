package org.apache.poi.poifs.filesystem;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EmptyFileException;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.poifs.common.POIFSBigBlockSize;
import org.apache.poi.poifs.common.POIFSConstants;
import org.apache.poi.poifs.dev.POIFSViewable;
import org.apache.poi.poifs.filesystem.BlockStore;
import org.apache.poi.poifs.nio.ByteArrayBackedDataSource;
import org.apache.poi.poifs.nio.DataSource;
import org.apache.poi.poifs.nio.FileBackedDataSource;
import org.apache.poi.poifs.property.DirectoryProperty;
import org.apache.poi.poifs.property.DocumentProperty;
import org.apache.poi.poifs.property.PropertyTable;
import org.apache.poi.poifs.storage.BATBlock;
import org.apache.poi.poifs.storage.HeaderBlock;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;

/* loaded from: classes10.dex */
public class POIFSFileSystem extends BlockStore implements POIFSViewable, Closeable {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000;
    private static final int MAX_ALLOCATION_SIZE = 250000000;
    private static final int MAX_BLOCK_COUNT = 65535;
    private final List<BATBlock> _bat_blocks;
    protected DataSource _data;
    private HeaderBlock _header;
    private POIFSMiniStore _mini_store;
    private PropertyTable _property_table;
    private DirectoryNode _root;
    private final List<BATBlock> _xbat_blocks;
    private POIFSBigBlockSize bigBlockSize;
    private static int MAX_RECORD_LENGTH = 100000;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) POIFSFileSystem.class);

    public static void setMaxRecordLength(int length) {
        MAX_RECORD_LENGTH = length;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    private POIFSFileSystem(boolean newFS) {
        this.bigBlockSize = POIFSConstants.SMALLER_BIG_BLOCK_SIZE_DETAILS;
        this._header = new HeaderBlock(this.bigBlockSize);
        this._property_table = new PropertyTable(this._header);
        this._mini_store = new POIFSMiniStore(this, this._property_table.getRoot(), new ArrayList(), this._header);
        this._xbat_blocks = new ArrayList();
        this._bat_blocks = new ArrayList();
        this._root = null;
        if (newFS) {
            createNewDataSource();
        }
    }

    protected void createNewDataSource() {
        long blockSize = Math.multiplyExact(this.bigBlockSize.getBigBlockSize(), 3L);
        this._data = new ByteArrayBackedDataSource(IOUtils.safelyAllocate(blockSize, MAX_RECORD_LENGTH));
    }

    public POIFSFileSystem() {
        this(true);
        this._header.setBATCount(1);
        this._header.setBATArray(new int[]{1});
        BATBlock bb = BATBlock.createEmptyBATBlock(this.bigBlockSize, false);
        bb.setOurBlockIndex(1);
        this._bat_blocks.add(bb);
        setNextBlock(0, -2);
        setNextBlock(1, -3);
        this._property_table.setStartBlock(0);
    }

    public POIFSFileSystem(File file) throws IOException {
        this(file, true);
    }

    public POIFSFileSystem(File file, boolean readOnly) throws IOException {
        this(null, file, readOnly, true, true);
    }

    public POIFSFileSystem(FileChannel channel) throws IOException {
        this(channel, true);
    }

    public POIFSFileSystem(FileChannel channel, boolean readOnly) throws IOException {
        this(channel, null, readOnly, false, true);
    }

    public POIFSFileSystem(FileChannel channel, boolean readOnly, boolean closeChannel) throws IOException {
        this(channel, null, readOnly, closeChannel, closeChannel);
    }

    private POIFSFileSystem(FileChannel channel, File srcFile, boolean readOnly, boolean closeChannelOnError, boolean closeChannelOnClose) throws IOException {
        this(false);
        try {
            if (srcFile == null) {
                this._data = new FileBackedDataSource(channel, readOnly, closeChannelOnClose);
            } else {
                if (srcFile.length() == 0) {
                    throw new EmptyFileException(srcFile);
                }
                FileBackedDataSource d = new FileBackedDataSource(srcFile, readOnly);
                channel = d.getChannel();
                this._data = d;
            }
            ByteBuffer headerBuffer = ByteBuffer.allocate(512);
            IOUtils.readFully(channel, headerBuffer);
            this._header = new HeaderBlock(headerBuffer);
            readCoreContents();
        } catch (IOException | RuntimeException e) {
            if (closeChannelOnError && channel != null) {
                channel.close();
            }
            throw e;
        }
    }

    public POIFSFileSystem(InputStream stream) throws IOException {
        this(false);
        try {
            ReadableByteChannel channel = Channels.newChannel(stream);
            try {
                ByteBuffer headerBuffer = ByteBuffer.allocate(512);
                IOUtils.readFully(channel, headerBuffer);
                this._header = new HeaderBlock(headerBuffer);
                sanityCheckBlockCount(this._header.getBATCount());
                long maxSize = BATBlock.calculateMaximumSize(this._header);
                if (maxSize > 2147483647L) {
                    throw new IllegalArgumentException("Unable read a >2gb file via an InputStream");
                }
                IOUtils.safelyAllocateCheck(maxSize, MAX_ALLOCATION_SIZE);
                ByteBuffer data = ByteBuffer.allocate((int) maxSize);
                headerBuffer.position(0);
                data.put(headerBuffer);
                data.position(headerBuffer.capacity());
                IOUtils.readFully(channel, data);
                this._data = new ByteArrayBackedDataSource(data.array(), data.position());
                if (channel != null) {
                    channel.close();
                }
                closeInputStream(stream, true);
                readCoreContents();
            } finally {
            }
        } catch (Throwable th) {
            closeInputStream(stream, false);
            throw th;
        }
    }

    private void closeInputStream(InputStream stream, boolean success) {
        try {
            stream.close();
        } catch (IOException e) {
            if (success) {
                throw new IllegalStateException(e);
            }
            LOG.atError().withThrowable(e).log("can't close input stream");
        }
    }

    private void readCoreContents() throws IOException {
        this.bigBlockSize = this._header.getBigBlockSize();
        BlockStore.ChainLoopDetector loopDetector = getChainLoopDetector();
        for (int i : this._header.getBATArray()) {
            readBAT(i, loopDetector);
        }
        int remainingFATs = this._header.getBATCount() - this._header.getBATArray().length;
        int nextAt = this._header.getXBATIndex();
        for (int i2 = 0; i2 < this._header.getXBATCount(); i2++) {
            loopDetector.claim(nextAt);
            ByteBuffer fatData = getBlockAt(nextAt);
            BATBlock xfat = BATBlock.createBATBlock(this.bigBlockSize, fatData);
            xfat.setOurBlockIndex(nextAt);
            nextAt = xfat.getValueAt(this.bigBlockSize.getXBATEntriesPerBlock());
            this._xbat_blocks.add(xfat);
            int xbatFATs = Math.min(remainingFATs, this.bigBlockSize.getXBATEntriesPerBlock());
            for (int j = 0; j < xbatFATs; j++) {
                int fatAt = xfat.getValueAt(j);
                if (fatAt != -1 && fatAt != -2) {
                    readBAT(fatAt, loopDetector);
                }
                remainingFATs -= xbatFATs;
            }
            remainingFATs -= xbatFATs;
        }
        this._property_table = new PropertyTable(this._header, this);
        List<BATBlock> sbats = new ArrayList<>();
        this._mini_store = new POIFSMiniStore(this, this._property_table.getRoot(), sbats, this._header);
        int nextAt2 = this._header.getSBATStart();
        for (int i3 = 0; i3 < this._header.getSBATCount() && nextAt2 != -2; i3++) {
            loopDetector.claim(nextAt2);
            ByteBuffer fatData2 = getBlockAt(nextAt2);
            BATBlock sfat = BATBlock.createBATBlock(this.bigBlockSize, fatData2);
            sfat.setOurBlockIndex(nextAt2);
            sbats.add(sfat);
            nextAt2 = getNextBlock(nextAt2);
        }
    }

    private void readBAT(int batAt, BlockStore.ChainLoopDetector loopDetector) throws IOException {
        loopDetector.claim(batAt);
        ByteBuffer fatData = getBlockAt(batAt);
        BATBlock bat = BATBlock.createBATBlock(this.bigBlockSize, fatData);
        bat.setOurBlockIndex(batAt);
        this._bat_blocks.add(bat);
    }

    private BATBlock createBAT(int offset, boolean isBAT) throws IOException {
        BATBlock newBAT = BATBlock.createEmptyBATBlock(this.bigBlockSize, !isBAT);
        newBAT.setOurBlockIndex(offset);
        ByteBuffer buffer = ByteBuffer.allocate(this.bigBlockSize.getBigBlockSize());
        long writeTo = Math.multiplyExact(offset + 1, this.bigBlockSize.getBigBlockSize());
        this._data.write(buffer, writeTo);
        return newBAT;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.poifs.filesystem.BlockStore
    public ByteBuffer getBlockAt(int offset) throws IOException {
        long blockWanted = offset + 1;
        long startAt = this.bigBlockSize.getBigBlockSize() * blockWanted;
        try {
            return this._data.read(this.bigBlockSize.getBigBlockSize(), startAt);
        } catch (IndexOutOfBoundsException e) {
            IndexOutOfBoundsException wrapped = new IndexOutOfBoundsException("Block " + offset + " not found");
            wrapped.initCause(e);
            throw wrapped;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.poifs.filesystem.BlockStore
    public ByteBuffer createBlockIfNeeded(int offset) throws IOException {
        try {
            return getBlockAt(offset);
        } catch (IndexOutOfBoundsException e) {
            long startAt = (offset + 1) * this.bigBlockSize.getBigBlockSize();
            ByteBuffer buffer = ByteBuffer.allocate(getBigBlockSize());
            this._data.write(buffer, startAt);
            return getBlockAt(offset);
        }
    }

    @Override // org.apache.poi.poifs.filesystem.BlockStore
    protected BATBlock.BATBlockAndIndex getBATBlockAndIndex(int offset) {
        return BATBlock.getBATBlockAndIndex(offset, this._header, this._bat_blocks);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.poifs.filesystem.BlockStore
    public int getNextBlock(int offset) {
        BATBlock.BATBlockAndIndex bai = getBATBlockAndIndex(offset);
        return bai.getBlock().getValueAt(bai.getIndex());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.poifs.filesystem.BlockStore
    public void setNextBlock(int offset, int nextBlock) {
        BATBlock.BATBlockAndIndex bai = getBATBlockAndIndex(offset);
        bai.getBlock().setValueAt(bai.getIndex(), nextBlock);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.poifs.filesystem.BlockStore
    public int getFreeBlock() throws IOException {
        int numSectors = this.bigBlockSize.getBATEntriesPerBlock();
        int offset = 0;
        for (BATBlock bat : this._bat_blocks) {
            if (bat.hasFreeSectors()) {
                for (int j = 0; j < numSectors; j++) {
                    int batValue = bat.getValueAt(j);
                    if (batValue == -1) {
                        return offset + j;
                    }
                }
            }
            offset += numSectors;
        }
        BATBlock bat2 = createBAT(offset, true);
        bat2.setValueAt(0, -3);
        this._bat_blocks.add(bat2);
        if (this._header.getBATCount() < 109) {
            int[] newBATs = new int[this._header.getBATCount() + 1];
            System.arraycopy(this._header.getBATArray(), 0, newBATs, 0, newBATs.length - 1);
            newBATs[newBATs.length - 1] = offset;
            this._header.setBATArray(newBATs);
        } else {
            BATBlock xbat = null;
            Iterator<BATBlock> it = this._xbat_blocks.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BATBlock x = it.next();
                if (x.hasFreeSectors()) {
                    xbat = x;
                    break;
                }
            }
            if (xbat == null) {
                BATBlock xbat2 = createBAT(offset + 1, false);
                xbat2.setValueAt(0, offset);
                bat2.setValueAt(1, -4);
                offset++;
                if (this._xbat_blocks.isEmpty()) {
                    this._header.setXBATStart(offset);
                } else {
                    this._xbat_blocks.get(this._xbat_blocks.size() - 1).setValueAt(this.bigBlockSize.getXBATEntriesPerBlock(), offset);
                }
                this._xbat_blocks.add(xbat2);
                this._header.setXBATCount(this._xbat_blocks.size());
            } else {
                int i = 0;
                while (true) {
                    if (i >= this.bigBlockSize.getXBATEntriesPerBlock()) {
                        break;
                    }
                    if (xbat.getValueAt(i) != -1) {
                        i++;
                    } else {
                        xbat.setValueAt(i, offset);
                        break;
                    }
                }
            }
        }
        this._header.setBATCount(this._bat_blocks.size());
        return offset + 1;
    }

    protected long size() throws IOException {
        return this._data.size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.poifs.filesystem.BlockStore
    public BlockStore.ChainLoopDetector getChainLoopDetector() throws IOException {
        return new BlockStore.ChainLoopDetector(this._data.size());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PropertyTable _get_property_table() {
        return this._property_table;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public POIFSMiniStore getMiniStore() {
        return this._mini_store;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addDocument(POIFSDocument document) {
        this._property_table.addProperty(document.getDocumentProperty());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addDirectory(DirectoryProperty directory) {
        this._property_table.addProperty(directory);
    }

    public DocumentEntry createDocument(InputStream stream, String name) throws IOException {
        return getRoot().createDocument(name, stream);
    }

    public DocumentEntry createDocument(String name, int size, POIFSWriterListener writer) throws IOException {
        return getRoot().createDocument(name, size, writer);
    }

    public DirectoryEntry createDirectory(String name) throws IOException {
        return getRoot().createDirectory(name);
    }

    public DocumentEntry createOrUpdateDocument(InputStream stream, String name) throws IOException {
        return getRoot().createOrUpdateDocument(name, stream);
    }

    public boolean isInPlaceWriteable() {
        return (this._data instanceof FileBackedDataSource) && ((FileBackedDataSource) this._data).isWriteable();
    }

    public void writeFilesystem() throws IOException {
        if (!(this._data instanceof FileBackedDataSource)) {
            throw new IllegalArgumentException("POIFS opened from an inputstream, so writeFilesystem() may not be called. Use writeFilesystem(OutputStream) instead");
        }
        if (!((FileBackedDataSource) this._data).isWriteable()) {
            throw new IllegalArgumentException("POIFS opened in read only mode, so writeFilesystem() may not be called. Open the FileSystem in read-write mode first");
        }
        syncWithDataSource();
    }

    public void writeFilesystem(OutputStream stream) throws IOException {
        syncWithDataSource();
        this._data.copyTo(stream);
    }

    private void syncWithDataSource() throws IOException {
        this._mini_store.syncWithDataSource();
        POIFSStream propStream = new POIFSStream(this, this._header.getPropertyStart());
        this._property_table.preWrite();
        this._property_table.write(propStream);
        UnsynchronizedByteArrayOutputStream baos = UnsynchronizedByteArrayOutputStream.builder().setBufferSize(this._header.getBigBlockSize().getBigBlockSize()).get();
        this._header.writeData(baos);
        getBlockAt(-1).put(baos.toByteArray());
        for (BATBlock bat : this._bat_blocks) {
            ByteBuffer block = getBlockAt(bat.getOurBlockIndex());
            bat.writeData(block);
        }
        for (BATBlock bat2 : this._xbat_blocks) {
            ByteBuffer block2 = getBlockAt(bat2.getOurBlockIndex());
            bat2.writeData(block2);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this._data.close();
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("two arguments required: input filename and output filename");
            System.exit(1);
        }
        InputStream istream = Files.newInputStream(Paths.get(args[0], new String[0]), new OpenOption[0]);
        try {
            OutputStream ostream = Files.newOutputStream(Paths.get(args[1], new String[0]), new OpenOption[0]);
            try {
                POIFSFileSystem fs = new POIFSFileSystem(istream);
                try {
                    fs.writeFilesystem(ostream);
                    fs.close();
                    if (ostream != null) {
                        ostream.close();
                    }
                    if (istream != null) {
                        istream.close();
                    }
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (istream != null) {
                    try {
                        istream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public DirectoryNode getRoot() {
        if (this._root == null) {
            this._root = new DirectoryNode(this._property_table.getRoot(), this, null);
        }
        return this._root;
    }

    public DocumentInputStream createDocumentInputStream(String documentName) throws IOException {
        return getRoot().createDocumentInputStream(documentName);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void remove(EntryNode entry) throws IOException {
        if (entry instanceof DocumentEntry) {
            POIFSDocument doc = new POIFSDocument((DocumentProperty) entry.getProperty(), this);
            doc.free();
        }
        this._property_table.removeProperty(entry.getProperty());
    }

    @Override // org.apache.poi.poifs.dev.POIFSViewable
    public Object[] getViewableArray() {
        if (preferArray()) {
            return getRoot().getViewableArray();
        }
        return new Object[0];
    }

    @Override // org.apache.poi.poifs.dev.POIFSViewable
    public Iterator<Object> getViewableIterator() {
        if (!preferArray()) {
            return getRoot().getViewableIterator();
        }
        return Collections.emptyIterator();
    }

    @Override // org.apache.poi.poifs.dev.POIFSViewable
    public boolean preferArray() {
        return getRoot().preferArray();
    }

    @Override // org.apache.poi.poifs.dev.POIFSViewable
    public String getShortDescription() {
        return "POIFS FileSystem";
    }

    public int getBigBlockSize() {
        return this.bigBlockSize.getBigBlockSize();
    }

    public POIFSBigBlockSize getBigBlockSizeDetails() {
        return this.bigBlockSize;
    }

    public static POIFSFileSystem create(File file) throws IOException {
        POIFSFileSystem tmp = new POIFSFileSystem();
        try {
            OutputStream out = Files.newOutputStream(file.toPath(), new OpenOption[0]);
            try {
                tmp.writeFilesystem(out);
                if (out != null) {
                    out.close();
                }
                tmp.close();
                return new POIFSFileSystem(file, false);
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    tmp.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.poifs.filesystem.BlockStore
    public int getBlockStoreBlockSize() {
        return getBigBlockSize();
    }

    @Internal
    public PropertyTable getPropertyTable() {
        return this._property_table;
    }

    @Internal
    public HeaderBlock getHeaderBlock() {
        return this._header;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.poifs.filesystem.BlockStore
    public void releaseBuffer(ByteBuffer buffer) {
        if (this._data instanceof FileBackedDataSource) {
            ((FileBackedDataSource) this._data).releaseBuffer(buffer);
        }
    }

    private static void sanityCheckBlockCount(int block_count) throws IOException {
        if (block_count <= 0) {
            throw new IOException("Illegal block count; minimum count is 1, got " + block_count + " instead");
        }
        if (block_count > 65535) {
            throw new IOException("Block count " + block_count + " is too high. POI maximum is 65535.");
        }
    }
}
