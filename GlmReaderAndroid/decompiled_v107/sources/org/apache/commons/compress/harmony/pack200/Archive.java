package org.apache.commons.compress.harmony.pack200;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.compress.harmony.pack200.Archive;

/* loaded from: classes9.dex */
public class Archive {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private long currentSegmentSize;
    private JarFile jarFile;
    private final JarInputStream jarInputStream;
    private final PackingOptions options;
    private final OutputStream outputStream;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class PackingFile {
        private byte[] contents;
        private final boolean deflateHint;
        private final boolean isDirectory;
        private final long modtime;
        private final String name;

        /* JADX INFO: Access modifiers changed from: package-private */
        public PackingFile(byte[] bytes, JarEntry jarEntry) {
            this.name = jarEntry.getName();
            this.contents = bytes;
            this.modtime = jarEntry.getTime();
            this.deflateHint = jarEntry.getMethod() == 8;
            this.isDirectory = jarEntry.isDirectory();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public PackingFile(String name, byte[] contents, long modtime) {
            this.name = name;
            this.contents = contents;
            this.modtime = modtime;
            this.deflateHint = false;
            this.isDirectory = false;
        }

        public byte[] getContents() {
            return this.contents;
        }

        public long getModtime() {
            return this.modtime;
        }

        public String getName() {
            return this.name;
        }

        public boolean isDefalteHint() {
            return this.deflateHint;
        }

        public boolean isDirectory() {
            return this.isDirectory;
        }

        public void setContents(byte[] contents) {
            this.contents = contents;
        }

        public String toString() {
            return this.name;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class SegmentUnit {
        private int byteAmount = 0;
        private final List<Pack200ClassReader> classList;
        private final List<PackingFile> fileList;
        private int packedByteAmount;

        SegmentUnit(List<Pack200ClassReader> classes, List<PackingFile> files) {
            this.classList = classes;
            this.fileList = files;
            this.byteAmount += this.classList.stream().mapToInt(new ToIntFunction() { // from class: org.apache.commons.compress.harmony.pack200.Archive$SegmentUnit$$ExternalSyntheticLambda0
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return Archive.SegmentUnit.lambda$new$0((Pack200ClassReader) obj);
                }
            }).sum();
            this.byteAmount += this.fileList.stream().mapToInt(new ToIntFunction() { // from class: org.apache.commons.compress.harmony.pack200.Archive$SegmentUnit$$ExternalSyntheticLambda1
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return Archive.SegmentUnit.lambda$new$1((Archive.PackingFile) obj);
                }
            }).sum();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ int lambda$new$0(Pack200ClassReader element) {
            return element.b.length;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ int lambda$new$1(PackingFile element) {
            return element.contents.length;
        }

        public void addPackedByteAmount(int amount) {
            this.packedByteAmount += amount;
        }

        public int classListSize() {
            return this.classList.size();
        }

        public int fileListSize() {
            return this.fileList.size();
        }

        public int getByteAmount() {
            return this.byteAmount;
        }

        public List<Pack200ClassReader> getClassList() {
            return this.classList;
        }

        public List<PackingFile> getFileList() {
            return this.fileList;
        }

        public int getPackedByteAmount() {
            return this.packedByteAmount;
        }
    }

    public Archive(JarFile jarFile, OutputStream outputStream, PackingOptions options) throws IOException {
        options = options == null ? new PackingOptions() : options;
        this.options = options;
        this.outputStream = new BufferedOutputStream(options.isGzip() ? new GZIPOutputStream(outputStream) : outputStream);
        this.jarFile = jarFile;
        this.jarInputStream = null;
        PackingUtils.config(options);
    }

    public Archive(JarInputStream inputStream, OutputStream outputStream, PackingOptions options) throws IOException {
        this.jarInputStream = inputStream;
        options = options == null ? new PackingOptions() : options;
        this.options = options;
        this.outputStream = new BufferedOutputStream(options.isGzip() ? new GZIPOutputStream(outputStream) : outputStream);
        PackingUtils.config(options);
    }

    private boolean addJarEntry(PackingFile packingFile, List<Pack200ClassReader> javaClasses, List<PackingFile> files) {
        long segmentLimit = this.options.getSegmentLimit();
        if (segmentLimit != -1 && segmentLimit != 0) {
            long packedSize = estimateSize(packingFile);
            if (this.currentSegmentSize + packedSize > segmentLimit && this.currentSegmentSize > 0) {
                return false;
            }
            this.currentSegmentSize += packedSize;
        }
        String name = packingFile.getName();
        if (name.endsWith(".class") && !this.options.isPassFile(name)) {
            Pack200ClassReader classParser = new Pack200ClassReader(packingFile.contents);
            classParser.setFileName(name);
            javaClasses.add(classParser);
            packingFile.contents = EMPTY_BYTE_ARRAY;
        }
        files.add(packingFile);
        return true;
    }

    private void doNormalPack() throws IOException, Pack200Exception {
        List<PackingFile> packingFileList;
        PackingUtils.log("Start to perform a normal packing");
        if (this.jarInputStream != null) {
            packingFileList = PackingUtils.getPackingFileListFromJar(this.jarInputStream, this.options.isKeepFileOrder());
        } else {
            packingFileList = PackingUtils.getPackingFileListFromJar(this.jarFile, this.options.isKeepFileOrder());
        }
        List<SegmentUnit> segmentUnitList = splitIntoSegments(packingFileList);
        int previousByteAmount = 0;
        int packedByteAmount = 0;
        int segmentSize = segmentUnitList.size();
        for (int index = 0; index < segmentSize; index++) {
            SegmentUnit segmentUnit = segmentUnitList.get(index);
            new Segment().pack(segmentUnit, this.outputStream, this.options);
            previousByteAmount += segmentUnit.getByteAmount();
            packedByteAmount += segmentUnit.getPackedByteAmount();
        }
        PackingUtils.log("Total: Packed " + previousByteAmount + " input bytes of " + packingFileList.size() + " files into " + packedByteAmount + " bytes in " + segmentSize + " segments");
        this.outputStream.close();
    }

    private void doZeroEffortPack() throws IOException {
        PackingUtils.log("Start to perform a zero-effort packing");
        if (this.jarInputStream != null) {
            PackingUtils.copyThroughJar(this.jarInputStream, this.outputStream);
        } else {
            PackingUtils.copyThroughJar(this.jarFile, this.outputStream);
        }
    }

    private long estimateSize(PackingFile packingFile) {
        String name = packingFile.getName();
        if (name.startsWith("META-INF") || name.startsWith("/META-INF")) {
            return 0L;
        }
        long fileSize = packingFile.contents.length;
        if (fileSize < 0) {
            fileSize = 0;
        }
        return name.length() + fileSize + 5;
    }

    public void pack() throws Pack200Exception, IOException {
        if (this.options.getEffort() == 0) {
            doZeroEffortPack();
        } else {
            doNormalPack();
        }
    }

    private List<SegmentUnit> splitIntoSegments(List<PackingFile> packingFileList) {
        List<SegmentUnit> segmentUnitList = new ArrayList<>();
        List<Pack200ClassReader> classes = new ArrayList<>();
        List<PackingFile> files = new ArrayList<>();
        long segmentLimit = this.options.getSegmentLimit();
        int size = packingFileList.size();
        for (int index = 0; index < size; index++) {
            PackingFile packingFile = packingFileList.get(index);
            if (!addJarEntry(packingFile, classes, files)) {
                segmentUnitList.add(new SegmentUnit(classes, files));
                List<Pack200ClassReader> classes2 = new ArrayList<>();
                List<PackingFile> files2 = new ArrayList<>();
                this.currentSegmentSize = 0L;
                addJarEntry(packingFile, classes2, files2);
                this.currentSegmentSize = 0L;
                files = files2;
                classes = classes2;
            } else if (segmentLimit == 0 && estimateSize(packingFile) > 0) {
                segmentUnitList.add(new SegmentUnit(classes, files));
                List<Pack200ClassReader> classes3 = new ArrayList<>();
                files = new ArrayList<>();
                classes = classes3;
            }
        }
        int index2 = classes.size();
        if (index2 > 0 || files.size() > 0) {
            segmentUnitList.add(new SegmentUnit(classes, files));
        }
        return segmentUnitList;
    }
}
