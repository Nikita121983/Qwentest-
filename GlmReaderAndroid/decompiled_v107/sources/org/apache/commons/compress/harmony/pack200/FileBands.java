package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import kotlin.UByte;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.harmony.pack200.Archive;
import org.apache.commons.compress.utils.ExactMath;
import org.objectweb.asm.ClassReader;

/* loaded from: classes9.dex */
public class FileBands extends BandSet {
    private final CpBands cpBands;
    private final List<Archive.PackingFile> fileList;
    private final CPUTF8[] fileName;
    private final byte[][] file_bits;
    private final int[] file_modtime;
    private int[] file_name;
    private final int[] file_options;
    private final long[] file_size;
    private final PackingOptions options;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileBands(CpBands cpBands, SegmentHeader segmentHeader, PackingOptions options, Archive.SegmentUnit segmentUnit, int effort) {
        super(effort, segmentHeader);
        CpBands cpBands2 = cpBands;
        PackingOptions packingOptions = options;
        this.fileList = segmentUnit.getFileList();
        this.options = packingOptions;
        this.cpBands = cpBands2;
        int size = this.fileList.size();
        this.fileName = new CPUTF8[size];
        this.file_modtime = new int[size];
        this.file_size = new long[size];
        this.file_options = new int[size];
        int totalSize = 0;
        this.file_bits = new byte[size];
        int archiveModtime = segmentHeader.getArchive_modtime();
        Set<String> classNames = new HashSet<>();
        for (ClassReader reader : segmentUnit.getClassList()) {
            classNames.add(reader.getClassName());
        }
        CPUTF8 emptyString = cpBands2.getCPUtf8("");
        int latestModtime = Integer.MIN_VALUE;
        boolean isLatest = !"keep".equals(packingOptions.getModificationTime());
        int i = 0;
        while (i < size) {
            Archive.PackingFile packingFile = this.fileList.get(i);
            String name = packingFile.getName();
            if (name.endsWith(".class") && !packingOptions.isPassFile(name)) {
                int[] iArr = this.file_options;
                iArr[i] = iArr[i] | 2;
                if (classNames.contains(name.substring(0, name.length() - 6))) {
                    this.fileName[i] = emptyString;
                } else {
                    this.fileName[i] = cpBands2.getCPUtf8(name);
                }
            } else {
                this.fileName[i] = cpBands2.getCPUtf8(name);
            }
            if (options.isKeepDeflateHint() && packingFile.isDefalteHint()) {
                int[] iArr2 = this.file_options;
                iArr2[i] = iArr2[i] | 1;
            }
            byte[] bytes = packingFile.getContents();
            this.file_size[i] = bytes.length;
            totalSize = ExactMath.add(totalSize, this.file_size[i]);
            long modtime = (packingFile.getModtime() + TimeZone.getDefault().getRawOffset()) / 1000;
            long modtime2 = archiveModtime;
            this.file_modtime[i] = (int) (modtime - modtime2);
            if (isLatest && latestModtime < this.file_modtime[i]) {
                latestModtime = this.file_modtime[i];
            }
            this.file_bits[i] = packingFile.getContents();
            i++;
            cpBands2 = cpBands;
            packingOptions = options;
        }
        if (isLatest) {
            Arrays.fill(this.file_modtime, latestModtime);
        }
    }

    public void finaliseBands() {
        this.file_name = new int[this.fileName.length];
        for (int i = 0; i < this.file_name.length; i++) {
            if (this.fileName[i].equals(this.cpBands.getCPUtf8(""))) {
                Archive.PackingFile packingFile = this.fileList.get(i);
                String name = packingFile.getName();
                if (this.options.isPassFile(name)) {
                    this.fileName[i] = this.cpBands.getCPUtf8(name);
                    int[] iArr = this.file_options;
                    iArr[i] = iArr[i] & (-3);
                }
            }
            this.file_name[i] = this.fileName[i].getIndex();
        }
    }

    private int[] flatten(byte[][] bytes) {
        int total = 0;
        for (byte[] bArr : bytes) {
            total += bArr.length;
        }
        int[] band = new int[total];
        int index = 0;
        for (byte[] element : bytes) {
            int length = element.length;
            int i = 0;
            while (i < length) {
                byte element2 = element[i];
                band[index] = element2 & UByte.MAX_VALUE;
                i++;
                index++;
            }
        }
        return band;
    }

    @Override // org.apache.commons.compress.harmony.pack200.BandSet
    public void pack(OutputStream out) throws IOException, Pack200Exception {
        PackingUtils.log("Writing file bands...");
        byte[] encodedBand = encodeBandInt("file_name", this.file_name, Codec.UNSIGNED5);
        out.write(encodedBand);
        PackingUtils.log("Wrote " + encodedBand.length + " bytes from file_name[" + this.file_name.length + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        byte[] encodedBand2 = encodeFlags("file_size", this.file_size, Codec.UNSIGNED5, Codec.UNSIGNED5, this.segmentHeader.have_file_size_hi());
        out.write(encodedBand2);
        PackingUtils.log("Wrote " + encodedBand2.length + " bytes from file_size[" + this.file_size.length + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        if (this.segmentHeader.have_file_modtime()) {
            byte[] encodedBand3 = encodeBandInt("file_modtime", this.file_modtime, Codec.DELTA5);
            out.write(encodedBand3);
            PackingUtils.log("Wrote " + encodedBand3.length + " bytes from file_modtime[" + this.file_modtime.length + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        }
        if (this.segmentHeader.have_file_options()) {
            byte[] encodedBand4 = encodeBandInt("file_options", this.file_options, Codec.UNSIGNED5);
            out.write(encodedBand4);
            PackingUtils.log("Wrote " + encodedBand4.length + " bytes from file_options[" + this.file_options.length + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        }
        byte[] encodedBand5 = encodeBandInt("file_bits", flatten(this.file_bits), Codec.BYTE1);
        out.write(encodedBand5);
        PackingUtils.log("Wrote " + encodedBand5.length + " bytes from file_bits[" + this.file_bits.length + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
    }
}
