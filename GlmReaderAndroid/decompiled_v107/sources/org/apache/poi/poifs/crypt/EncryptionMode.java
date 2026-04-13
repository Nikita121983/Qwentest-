package org.apache.poi.poifs.crypt;

import java.util.function.Supplier;
import org.apache.poi.poifs.crypt.agile.AgileEncryptionInfoBuilder;
import org.apache.poi.poifs.crypt.binaryrc4.BinaryRC4EncryptionInfoBuilder;
import org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIEncryptionInfoBuilder;
import org.apache.poi.poifs.crypt.standard.StandardEncryptionInfoBuilder;
import org.apache.poi.poifs.crypt.xor.XOREncryptionInfoBuilder;

/* loaded from: classes10.dex */
public enum EncryptionMode {
    binaryRC4(new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionMode$$ExternalSyntheticLambda0
        @Override // java.util.function.Supplier
        public final Object get() {
            return new BinaryRC4EncryptionInfoBuilder();
        }
    }, 1, 1, 0),
    cryptoAPI(new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionMode$$ExternalSyntheticLambda1
        @Override // java.util.function.Supplier
        public final Object get() {
            return new CryptoAPIEncryptionInfoBuilder();
        }
    }, 4, 2, 4),
    standard(new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionMode$$ExternalSyntheticLambda2
        @Override // java.util.function.Supplier
        public final Object get() {
            return new StandardEncryptionInfoBuilder();
        }
    }, 4, 2, 36),
    agile(new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionMode$$ExternalSyntheticLambda3
        @Override // java.util.function.Supplier
        public final Object get() {
            return new AgileEncryptionInfoBuilder();
        }
    }, 4, 4, 64),
    xor(new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionMode$$ExternalSyntheticLambda4
        @Override // java.util.function.Supplier
        public final Object get() {
            return new XOREncryptionInfoBuilder();
        }
    }, 0, 0, 0);

    public final Supplier<EncryptionInfoBuilder> builder;
    public final int encryptionFlags;
    public final int versionMajor;
    public final int versionMinor;

    EncryptionMode(Supplier supplier, int versionMajor, int versionMinor, int encryptionFlags) {
        this.builder = supplier;
        this.versionMajor = versionMajor;
        this.versionMinor = versionMinor;
        this.encryptionFlags = encryptionFlags;
    }
}
