package org.apache.poi.hssf.record;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.IntMapper;

/* loaded from: classes10.dex */
class SSTDeserializer {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) SSTDeserializer.class);
    private IntMapper<UnicodeString> strings;

    public SSTDeserializer(IntMapper<UnicodeString> strings) {
        this.strings = strings;
    }

    public void manufactureStrings(int stringCount, RecordInputStream in) {
        for (int i = 0; i < stringCount; i++) {
            if (in.available() == 0 && (!in.hasNextRecord() || in.getNextSid() != 60)) {
                LOG.atError().log("Ran out of data before creating all the strings! String at index {}", Unbox.box(i));
                return;
            } else {
                UnicodeString str = new UnicodeString(in);
                addToStringTable(this.strings, str);
            }
        }
    }

    public static void addToStringTable(IntMapper<UnicodeString> strings, UnicodeString string) {
        strings.add(string);
    }
}
