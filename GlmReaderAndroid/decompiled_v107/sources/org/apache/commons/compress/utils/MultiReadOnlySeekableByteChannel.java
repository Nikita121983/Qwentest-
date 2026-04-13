package org.apache.commons.compress.utils;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/* loaded from: classes9.dex */
public class MultiReadOnlySeekableByteChannel implements SeekableByteChannel {
    private static final Path[] EMPTY_PATH_ARRAY = new Path[0];
    private final List<SeekableByteChannel> channelList;
    private int currentChannelIdx;
    private long globalPosition;

    public static SeekableByteChannel forFiles(File... files) throws IOException {
        List<Path> paths = new ArrayList<>();
        for (File f : (File[]) Objects.requireNonNull(files, "files")) {
            paths.add(f.toPath());
        }
        return forPaths((Path[]) paths.toArray(EMPTY_PATH_ARRAY));
    }

    public static SeekableByteChannel forPaths(Path... paths) throws IOException {
        List<SeekableByteChannel> channels = new ArrayList<>();
        for (Path path : (Path[]) Objects.requireNonNull(paths, "paths")) {
            channels.add(Files.newByteChannel(path, StandardOpenOption.READ));
        }
        if (channels.size() == 1) {
            return channels.get(0);
        }
        return new MultiReadOnlySeekableByteChannel(channels);
    }

    public static SeekableByteChannel forSeekableByteChannels(SeekableByteChannel... channels) {
        if (((SeekableByteChannel[]) Objects.requireNonNull(channels, "channels")).length == 1) {
            return channels[0];
        }
        return new MultiReadOnlySeekableByteChannel(Arrays.asList(channels));
    }

    public MultiReadOnlySeekableByteChannel(List<SeekableByteChannel> channels) {
        this.channelList = Collections.unmodifiableList(new ArrayList((Collection) Objects.requireNonNull(channels, "channels")));
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        IOException first = null;
        for (SeekableByteChannel ch : this.channelList) {
            try {
                ch.close();
            } catch (IOException ex) {
                if (first == null) {
                    first = ex;
                }
            }
        }
        if (first != null) {
            throw new IOException("failed to close wrapped channel", first);
        }
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.channelList.stream().allMatch(new Predicate() { // from class: org.apache.commons.compress.utils.MultiReadOnlySeekableByteChannel$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((SeekableByteChannel) obj).isOpen();
            }
        });
    }

    @Override // java.nio.channels.SeekableByteChannel
    public long position() {
        return this.globalPosition;
    }

    @Override // java.nio.channels.SeekableByteChannel
    public synchronized SeekableByteChannel position(long newPosition) throws IOException {
        long newChannelPos;
        try {
            if (newPosition < 0) {
                throw new IllegalArgumentException("Negative position: " + newPosition);
            }
            if (!isOpen()) {
                throw new ClosedChannelException();
            }
            this.globalPosition = newPosition;
            long pos = newPosition;
            for (int i = 0; i < this.channelList.size(); i++) {
                SeekableByteChannel currentChannel = this.channelList.get(i);
                long size = currentChannel.size();
                if (pos == -1) {
                    newChannelPos = 0;
                } else if (pos <= size) {
                    this.currentChannelIdx = i;
                    newChannelPos = pos;
                    pos = -1;
                } else {
                    pos -= size;
                    newChannelPos = size;
                }
                currentChannel.position(newChannelPos);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this;
    }

    public synchronized SeekableByteChannel position(long channelNumber, long relativeOffset) throws IOException {
        long globalPosition;
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        globalPosition = relativeOffset;
        for (int i = 0; i < channelNumber; i++) {
            globalPosition += this.channelList.get(i).size();
        }
        return position(globalPosition);
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer dst) throws IOException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        if (!dst.hasRemaining()) {
            return 0;
        }
        int totalBytesRead = 0;
        while (dst.hasRemaining() && this.currentChannelIdx < this.channelList.size()) {
            SeekableByteChannel currentChannel = this.channelList.get(this.currentChannelIdx);
            int newBytesRead = currentChannel.read(dst);
            if (newBytesRead == -1) {
                this.currentChannelIdx++;
            } else {
                if (currentChannel.position() >= currentChannel.size()) {
                    this.currentChannelIdx++;
                }
                totalBytesRead += newBytesRead;
            }
        }
        if (totalBytesRead <= 0) {
            return -1;
        }
        this.globalPosition += totalBytesRead;
        return totalBytesRead;
    }

    @Override // java.nio.channels.SeekableByteChannel
    public long size() throws IOException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        long acc = 0;
        for (SeekableByteChannel ch : this.channelList) {
            acc += ch.size();
        }
        return acc;
    }

    @Override // java.nio.channels.SeekableByteChannel
    public SeekableByteChannel truncate(long size) {
        throw new NonWritableChannelException();
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer src) {
        throw new NonWritableChannelException();
    }
}
