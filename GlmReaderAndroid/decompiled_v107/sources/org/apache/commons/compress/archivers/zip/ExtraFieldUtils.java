package org.apache.commons.compress.archivers.zip;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;
import java.util.zip.ZipException;

/* loaded from: classes9.dex */
public class ExtraFieldUtils {
    static final ZipExtraField[] EMPTY_ZIP_EXTRA_FIELD_ARRAY;
    private static final ConcurrentMap<ZipShort, Supplier<ZipExtraField>> IMPLEMENTATIONS = new ConcurrentHashMap();
    private static final int WORD = 4;

    /* loaded from: classes9.dex */
    public static final class UnparseableExtraField implements UnparseableExtraFieldBehavior {
        public static final int READ_KEY = 2;
        public static final int SKIP_KEY = 1;
        public static final int THROW_KEY = 0;
        private final int key;
        public static final UnparseableExtraField THROW = new UnparseableExtraField(0);
        public static final UnparseableExtraField SKIP = new UnparseableExtraField(1);
        public static final UnparseableExtraField READ = new UnparseableExtraField(2);

        private UnparseableExtraField(int k) {
            this.key = k;
        }

        public int getKey() {
            return this.key;
        }

        @Override // org.apache.commons.compress.archivers.zip.UnparseableExtraFieldBehavior
        public ZipExtraField onUnparseableExtraField(byte[] data, int off, int len, boolean local, int claimedLength) throws ZipException {
            switch (this.key) {
                case 0:
                    throw new ZipException("Bad extra field starting at " + off + ".  Block length of " + claimedLength + " bytes exceeds remaining data of " + (len - 4) + " bytes.");
                case 1:
                    return null;
                case 2:
                    UnparseableExtraFieldData field = new UnparseableExtraFieldData();
                    if (local) {
                        field.parseFromLocalFileData(data, off, len);
                    } else {
                        field.parseFromCentralDirectoryData(data, off, len);
                    }
                    return field;
                default:
                    throw new ZipException("Unknown UnparseableExtraField key: " + this.key);
            }
        }
    }

    static {
        IMPLEMENTATIONS.put(AsiExtraField.HEADER_ID, new Supplier() { // from class: org.apache.commons.compress.archivers.zip.ExtraFieldUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return new AsiExtraField();
            }
        });
        IMPLEMENTATIONS.put(X5455_ExtendedTimestamp.HEADER_ID, new Supplier() { // from class: org.apache.commons.compress.archivers.zip.ExtraFieldUtils$$ExternalSyntheticLambda10
            @Override // java.util.function.Supplier
            public final Object get() {
                return new X5455_ExtendedTimestamp();
            }
        });
        IMPLEMENTATIONS.put(X7875_NewUnix.HEADER_ID, new Supplier() { // from class: org.apache.commons.compress.archivers.zip.ExtraFieldUtils$$ExternalSyntheticLambda11
            @Override // java.util.function.Supplier
            public final Object get() {
                return new X7875_NewUnix();
            }
        });
        IMPLEMENTATIONS.put(JarMarker.ID, new Supplier() { // from class: org.apache.commons.compress.archivers.zip.ExtraFieldUtils$$ExternalSyntheticLambda12
            @Override // java.util.function.Supplier
            public final Object get() {
                return new JarMarker();
            }
        });
        IMPLEMENTATIONS.put(UnicodePathExtraField.UPATH_ID, new Supplier() { // from class: org.apache.commons.compress.archivers.zip.ExtraFieldUtils$$ExternalSyntheticLambda13
            @Override // java.util.function.Supplier
            public final Object get() {
                return new UnicodePathExtraField();
            }
        });
        IMPLEMENTATIONS.put(UnicodeCommentExtraField.UCOM_ID, new Supplier() { // from class: org.apache.commons.compress.archivers.zip.ExtraFieldUtils$$ExternalSyntheticLambda14
            @Override // java.util.function.Supplier
            public final Object get() {
                return new UnicodeCommentExtraField();
            }
        });
        IMPLEMENTATIONS.put(Zip64ExtendedInformationExtraField.HEADER_ID, new Supplier() { // from class: org.apache.commons.compress.archivers.zip.ExtraFieldUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return new Zip64ExtendedInformationExtraField();
            }
        });
        IMPLEMENTATIONS.put(X000A_NTFS.HEADER_ID, new Supplier() { // from class: org.apache.commons.compress.archivers.zip.ExtraFieldUtils$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return new X000A_NTFS();
            }
        });
        IMPLEMENTATIONS.put(X0014_X509Certificates.HEADER_ID, new Supplier() { // from class: org.apache.commons.compress.archivers.zip.ExtraFieldUtils$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return new X0014_X509Certificates();
            }
        });
        IMPLEMENTATIONS.put(X0015_CertificateIdForFile.HEADER_ID, new Supplier() { // from class: org.apache.commons.compress.archivers.zip.ExtraFieldUtils$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return new X0015_CertificateIdForFile();
            }
        });
        IMPLEMENTATIONS.put(X0016_CertificateIdForCentralDirectory.HEADER_ID, new Supplier() { // from class: org.apache.commons.compress.archivers.zip.ExtraFieldUtils$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return new X0016_CertificateIdForCentralDirectory();
            }
        });
        IMPLEMENTATIONS.put(X0017_StrongEncryptionHeader.HEADER_ID, new Supplier() { // from class: org.apache.commons.compress.archivers.zip.ExtraFieldUtils$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return new X0017_StrongEncryptionHeader();
            }
        });
        IMPLEMENTATIONS.put(X0019_EncryptionRecipientCertificateList.HEADER_ID, new Supplier() { // from class: org.apache.commons.compress.archivers.zip.ExtraFieldUtils$$ExternalSyntheticLambda8
            @Override // java.util.function.Supplier
            public final Object get() {
                return new X0019_EncryptionRecipientCertificateList();
            }
        });
        IMPLEMENTATIONS.put(ResourceAlignmentExtraField.ID, new Supplier() { // from class: org.apache.commons.compress.archivers.zip.ExtraFieldUtils$$ExternalSyntheticLambda9
            @Override // java.util.function.Supplier
            public final Object get() {
                return new ResourceAlignmentExtraField();
            }
        });
        EMPTY_ZIP_EXTRA_FIELD_ARRAY = new ZipExtraField[0];
    }

    public static ZipExtraField createExtraField(ZipShort headerId) {
        ZipExtraField field = createExtraFieldNoDefault(headerId);
        if (field != null) {
            return field;
        }
        UnrecognizedExtraField u = new UnrecognizedExtraField();
        u.setHeaderId(headerId);
        return u;
    }

    public static ZipExtraField createExtraFieldNoDefault(ZipShort headerId) {
        Supplier<ZipExtraField> provider = IMPLEMENTATIONS.get(headerId);
        if (provider != null) {
            return provider.get();
        }
        return null;
    }

    public static ZipExtraField fillExtraField(ZipExtraField ze, byte[] data, int off, int len, boolean local) throws ZipException {
        try {
            if (local) {
                ze.parseFromLocalFileData(data, off, len);
            } else {
                ze.parseFromCentralDirectoryData(data, off, len);
            }
            return ze;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw ZipUtil.newZipException("Failed to parse corrupt ZIP extra field of type " + Integer.toHexString(ze.getHeaderId().getValue()), e);
        }
    }

    public static byte[] mergeCentralDirectoryData(ZipExtraField[] data) {
        byte[] central;
        int dataLength = data.length;
        boolean lastIsUnparseableHolder = dataLength > 0 && (data[dataLength + (-1)] instanceof UnparseableExtraFieldData);
        int regularExtraFieldCount = lastIsUnparseableHolder ? dataLength - 1 : dataLength;
        int sum = regularExtraFieldCount * 4;
        for (ZipExtraField element : data) {
            sum += element.getCentralDirectoryLength().getValue();
        }
        byte[] result = new byte[sum];
        int start = 0;
        for (int i = 0; i < regularExtraFieldCount; i++) {
            System.arraycopy(data[i].getHeaderId().getBytes(), 0, result, start, 2);
            System.arraycopy(data[i].getCentralDirectoryLength().getBytes(), 0, result, start + 2, 2);
            start += 4;
            byte[] central2 = data[i].getCentralDirectoryData();
            if (central2 != null) {
                System.arraycopy(central2, 0, result, start, central2.length);
                start += central2.length;
            }
        }
        if (lastIsUnparseableHolder && (central = data[dataLength - 1].getCentralDirectoryData()) != null) {
            System.arraycopy(central, 0, result, start, central.length);
        }
        return result;
    }

    public static byte[] mergeLocalFileDataData(ZipExtraField[] data) {
        byte[] local;
        int dataLength = data.length;
        boolean lastIsUnparseableHolder = dataLength > 0 && (data[dataLength + (-1)] instanceof UnparseableExtraFieldData);
        int regularExtraFieldCount = lastIsUnparseableHolder ? dataLength - 1 : dataLength;
        int sum = regularExtraFieldCount * 4;
        for (ZipExtraField element : data) {
            sum += element.getLocalFileDataLength().getValue();
        }
        byte[] result = new byte[sum];
        int start = 0;
        for (int i = 0; i < regularExtraFieldCount; i++) {
            System.arraycopy(data[i].getHeaderId().getBytes(), 0, result, start, 2);
            System.arraycopy(data[i].getLocalFileDataLength().getBytes(), 0, result, start + 2, 2);
            start += 4;
            byte[] local2 = data[i].getLocalFileDataData();
            if (local2 != null) {
                System.arraycopy(local2, 0, result, start, local2.length);
                start += local2.length;
            }
        }
        if (lastIsUnparseableHolder && (local = data[dataLength - 1].getLocalFileDataData()) != null) {
            System.arraycopy(local, 0, result, start, local.length);
        }
        return result;
    }

    public static ZipExtraField[] parse(byte[] data) throws ZipException {
        return parse(data, true, UnparseableExtraField.THROW);
    }

    public static ZipExtraField[] parse(byte[] data, boolean local) throws ZipException {
        return parse(data, local, UnparseableExtraField.THROW);
    }

    public static ZipExtraField[] parse(byte[] data, boolean local, ExtraFieldParsingBehavior parsingBehavior) throws ZipException {
        List<ZipExtraField> v = new ArrayList<>();
        int dataLength = data.length;
        int start = 0;
        while (true) {
            int start2 = dataLength - 4;
            if (start > start2) {
                break;
            }
            ZipShort headerId = new ZipShort(data, start);
            int length = new ZipShort(data, start + 2).getValue();
            if (start + 4 + length > dataLength) {
                ZipExtraField field = parsingBehavior.onUnparseableExtraField(data, start, dataLength - start, local, length);
                if (field != null) {
                    v.add(field);
                }
            } else {
                int start3 = start;
                try {
                    ZipExtraField ze = (ZipExtraField) Objects.requireNonNull(parsingBehavior.createExtraField(headerId), "createExtraField must not return null");
                    v.add((ZipExtraField) Objects.requireNonNull(parsingBehavior.fill(ze, data, start3 + 4, length, local), "fill must not return null"));
                    start = start3 + length + 4;
                } catch (IllegalAccessException | InstantiationException e) {
                    throw ZipUtil.newZipException(e.getMessage(), e);
                }
            }
        }
        return (ZipExtraField[]) v.toArray(EMPTY_ZIP_EXTRA_FIELD_ARRAY);
    }

    public static ZipExtraField[] parse(byte[] data, boolean local, final UnparseableExtraField onUnparseableData) throws ZipException {
        return parse(data, local, new ExtraFieldParsingBehavior() { // from class: org.apache.commons.compress.archivers.zip.ExtraFieldUtils.1
            @Override // org.apache.commons.compress.archivers.zip.ExtraFieldParsingBehavior
            public ZipExtraField createExtraField(ZipShort headerId) {
                return ExtraFieldUtils.createExtraField(headerId);
            }

            @Override // org.apache.commons.compress.archivers.zip.ExtraFieldParsingBehavior
            public ZipExtraField fill(ZipExtraField field, byte[] data2, int off, int len, boolean local2) throws ZipException {
                return ExtraFieldUtils.fillExtraField(field, data2, off, len, local2);
            }

            @Override // org.apache.commons.compress.archivers.zip.UnparseableExtraFieldBehavior
            public ZipExtraField onUnparseableExtraField(byte[] data2, int off, int len, boolean local2, int claimedLength) throws ZipException {
                return UnparseableExtraField.this.onUnparseableExtraField(data2, off, len, local2, claimedLength);
            }
        });
    }

    @Deprecated
    public static void register(final Class<?> clazz) {
        try {
            final Constructor<? extends ZipExtraField> constructor = clazz.asSubclass(ZipExtraField.class).getConstructor(new Class[0]);
            ZipExtraField zef = (ZipExtraField) clazz.asSubclass(ZipExtraField.class).getConstructor(new Class[0]).newInstance(new Object[0]);
            IMPLEMENTATIONS.put(zef.getHeaderId(), new Supplier() { // from class: org.apache.commons.compress.archivers.zip.ExtraFieldUtils$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ExtraFieldUtils.lambda$register$0(constructor, clazz);
                }
            });
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException(clazz.toString(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ZipExtraField lambda$register$0(Constructor constructor, Class clazz) {
        try {
            return (ZipExtraField) constructor.newInstance(new Object[0]);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(clazz.toString(), e);
        }
    }
}
