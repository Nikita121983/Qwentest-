package org.apache.poi.hpsf;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
/* loaded from: classes10.dex */
public class VariantBool {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) VariantBool.class);
    static final int SIZE = 2;
    private boolean _value;

    public void read(LittleEndianByteArrayInputStream lei) {
        short value = lei.readShort();
        switch (value) {
            case -1:
                this._value = true;
                return;
            case 0:
                this._value = false;
                return;
            default:
                LOG.atWarn().log("VARIANT_BOOL value '{}' is incorrect", Unbox.box(value));
                this._value = true;
                return;
        }
    }

    public boolean getValue() {
        return this._value;
    }

    public void setValue(boolean value) {
        this._value = value;
    }
}
