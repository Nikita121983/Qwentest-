package org.apache.poi.poifs.crypt;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;

/* loaded from: classes10.dex */
public class EncryptionInfo implements GenericRecord {
    public static final String ENCRYPTION_INFO_ENTRY = "EncryptionInfo";
    private Decryptor decryptor;
    private final int encryptionFlags;
    private final EncryptionMode encryptionMode;
    private Encryptor encryptor;
    private EncryptionHeader header;
    private EncryptionVerifier verifier;
    private final int versionMajor;
    private final int versionMinor;
    public static final BitField flagCryptoAPI = BitFieldFactory.getInstance(4);
    public static final BitField flagDocProps = BitFieldFactory.getInstance(8);
    public static final BitField flagExternal = BitFieldFactory.getInstance(16);
    public static final BitField flagAES = BitFieldFactory.getInstance(32);
    private static final int[] FLAGS_MASKS = {4, 8, 16, 32};
    private static final String[] FLAGS_NAMES = {"CRYPTO_API", "DOC_PROPS", "EXTERNAL", "AES"};

    public EncryptionInfo(POIFSFileSystem fs) throws IOException {
        this(fs.getRoot());
    }

    public EncryptionInfo(DirectoryNode dir) throws IOException {
        this(dir.createDocumentInputStream(ENCRYPTION_INFO_ENTRY), null);
    }

    public EncryptionInfo(LittleEndianInput dis, EncryptionMode preferredEncryptionMode) throws IOException {
        if (preferredEncryptionMode == EncryptionMode.xor) {
            this.versionMajor = EncryptionMode.xor.versionMajor;
            this.versionMinor = EncryptionMode.xor.versionMinor;
        } else {
            this.versionMajor = dis.readUShort();
            this.versionMinor = dis.readUShort();
        }
        if (this.versionMajor == EncryptionMode.xor.versionMajor && this.versionMinor == EncryptionMode.xor.versionMinor) {
            this.encryptionMode = EncryptionMode.xor;
            this.encryptionFlags = -1;
        } else if (this.versionMajor == EncryptionMode.binaryRC4.versionMajor && this.versionMinor == EncryptionMode.binaryRC4.versionMinor) {
            this.encryptionMode = EncryptionMode.binaryRC4;
            this.encryptionFlags = -1;
        } else if (2 <= this.versionMajor && this.versionMajor <= 4 && this.versionMinor == 2) {
            this.encryptionFlags = dis.readInt();
            this.encryptionMode = (preferredEncryptionMode == EncryptionMode.cryptoAPI || !flagAES.isSet(this.encryptionFlags)) ? EncryptionMode.cryptoAPI : EncryptionMode.standard;
        } else if (this.versionMajor == EncryptionMode.agile.versionMajor && this.versionMinor == EncryptionMode.agile.versionMinor) {
            this.encryptionMode = EncryptionMode.agile;
            this.encryptionFlags = dis.readInt();
        } else {
            this.encryptionFlags = dis.readInt();
            throw new EncryptedDocumentException("Unknown encryption: version major: " + this.versionMajor + " / version minor: " + this.versionMinor + " / fCrypto: " + flagCryptoAPI.isSet(this.encryptionFlags) + " / fExternal: " + flagExternal.isSet(this.encryptionFlags) + " / fDocProps: " + flagDocProps.isSet(this.encryptionFlags) + " / fAES: " + flagAES.isSet(this.encryptionFlags));
        }
        try {
            EncryptionInfoBuilder eib = getBuilder(this.encryptionMode);
            eib.initialize(this, dis);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public EncryptionInfo(EncryptionMode encryptionMode) {
        this(encryptionMode, null, null, -1, -1, null);
    }

    public EncryptionInfo(EncryptionMode encryptionMode, CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int keyBits, int blockSize, ChainingMode chainingMode) {
        this.encryptionMode = encryptionMode;
        this.versionMajor = encryptionMode.versionMajor;
        this.versionMinor = encryptionMode.versionMinor;
        this.encryptionFlags = encryptionMode.encryptionFlags;
        try {
            EncryptionInfoBuilder eib = getBuilder(encryptionMode);
            eib.initialize(this, cipherAlgorithm, hashAlgorithm, keyBits, blockSize, chainingMode);
        } catch (Exception e) {
            throw new EncryptedDocumentException(e);
        }
    }

    public EncryptionInfo(EncryptionInfo other) {
        this.encryptionMode = other.encryptionMode;
        this.versionMajor = other.versionMajor;
        this.versionMinor = other.versionMinor;
        this.encryptionFlags = other.encryptionFlags;
        this.header = other.header == null ? null : other.header.copy();
        this.verifier = other.verifier != null ? other.verifier.copy() : null;
        if (other.decryptor != null) {
            this.decryptor = other.decryptor.copy();
            this.decryptor.setEncryptionInfo(this);
        }
        if (other.encryptor != null) {
            this.encryptor = other.encryptor.copy();
            this.encryptor.setEncryptionInfo(this);
        }
    }

    private static EncryptionInfoBuilder getBuilder(EncryptionMode encryptionMode) {
        return encryptionMode.builder.get();
    }

    public int getVersionMajor() {
        return this.versionMajor;
    }

    public int getVersionMinor() {
        return this.versionMinor;
    }

    public int getEncryptionFlags() {
        return this.encryptionFlags;
    }

    public EncryptionHeader getHeader() {
        return this.header;
    }

    public EncryptionVerifier getVerifier() {
        return this.verifier;
    }

    public Decryptor getDecryptor() {
        return this.decryptor;
    }

    public Encryptor getEncryptor() {
        return this.encryptor;
    }

    public void setHeader(EncryptionHeader header) {
        this.header = header;
    }

    public void setVerifier(EncryptionVerifier verifier) {
        this.verifier = verifier;
    }

    public void setDecryptor(Decryptor decryptor) {
        this.decryptor = decryptor;
    }

    public void setEncryptor(Encryptor encryptor) {
        this.encryptor = encryptor;
    }

    public EncryptionMode getEncryptionMode() {
        return this.encryptionMode;
    }

    public boolean isDocPropsEncrypted() {
        return !flagDocProps.isSet(getEncryptionFlags());
    }

    public EncryptionInfo copy() {
        return new EncryptionInfo(this);
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Map<String, Supplier<?>> m = new LinkedHashMap<>();
        m.put("encryptionMode", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionInfo$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionInfo.this.getEncryptionMode();
            }
        });
        m.put("versionMajor", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionInfo$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EncryptionInfo.this.getVersionMajor());
            }
        });
        m.put("versionMinor", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionInfo$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EncryptionInfo.this.getVersionMinor());
            }
        });
        m.put("encryptionFlags", GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionInfo$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EncryptionInfo.this.getEncryptionFlags());
            }
        }, FLAGS_MASKS, FLAGS_NAMES));
        m.put("header", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionInfo$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionInfo.this.getHeader();
            }
        });
        m.put("verifier", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionInfo$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionInfo.this.getVerifier();
            }
        });
        m.put("decryptor", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionInfo$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionInfo.this.getDecryptor();
            }
        });
        m.put("encryptor", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionInfo$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionInfo.this.getEncryptor();
            }
        });
        return Collections.unmodifiableMap(m);
    }
}
