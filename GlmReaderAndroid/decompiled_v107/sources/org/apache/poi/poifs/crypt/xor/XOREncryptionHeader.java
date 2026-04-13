package org.apache.poi.poifs.crypt.xor;

import org.apache.poi.poifs.crypt.EncryptionHeader;
import org.apache.poi.poifs.crypt.standard.EncryptionRecord;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;

/* loaded from: classes10.dex */
public class XOREncryptionHeader extends EncryptionHeader implements EncryptionRecord {
    /* JADX INFO: Access modifiers changed from: protected */
    public XOREncryptionHeader() {
    }

    protected XOREncryptionHeader(XOREncryptionHeader other) {
        super(other);
    }

    @Override // org.apache.poi.poifs.crypt.standard.EncryptionRecord
    public void write(LittleEndianByteArrayOutputStream leos) {
    }

    @Override // org.apache.poi.poifs.crypt.EncryptionHeader, org.apache.poi.common.Duplicatable
    public XOREncryptionHeader copy() {
        return new XOREncryptionHeader(this);
    }
}
