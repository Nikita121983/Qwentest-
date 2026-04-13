package org.apache.poi.hssf.record;

import androidx.core.view.InputDeviceCompat;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.commons.lang3.SystemProperties;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class SupBookRecord extends StandardRecord {
    static final char CH_ALT_STARTUP_DIR = 7;
    static final char CH_DOWN_DIR = 3;
    static final char CH_LIB_DIR = '\b';
    static final char CH_LONG_VOLUME = 5;
    static final char CH_SAME_VOLUME = 2;
    static final char CH_STARTUP_DIR = 6;
    static final char CH_UP_DIR = 4;
    static final char CH_VOLUME = 1;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) SupBookRecord.class);
    static final String PATH_SEPERATOR = System.getProperty(SystemProperties.FILE_SEPARATOR);
    private static final short SMALL_RECORD_SIZE = 4;
    private static final short TAG_ADD_IN_FUNCTIONS = 14849;
    private static final short TAG_INTERNAL_REFERENCES = 1025;
    public static final short sid = 430;
    private final boolean _isAddInFunctions;
    private short field_1_number_of_sheets;
    private String field_2_encoded_url;
    private final String[] field_3_sheet_names;

    public SupBookRecord(SupBookRecord other) {
        super(other);
        this.field_1_number_of_sheets = other.field_1_number_of_sheets;
        this.field_2_encoded_url = other.field_2_encoded_url;
        this.field_3_sheet_names = other.field_3_sheet_names;
        this._isAddInFunctions = other._isAddInFunctions;
    }

    private SupBookRecord(boolean isAddInFuncs, short numberOfSheets) {
        this.field_1_number_of_sheets = numberOfSheets;
        this.field_2_encoded_url = null;
        this.field_3_sheet_names = null;
        this._isAddInFunctions = isAddInFuncs;
    }

    public SupBookRecord(String url, String[] sheetNames) {
        this.field_1_number_of_sheets = (short) sheetNames.length;
        this.field_2_encoded_url = url;
        this.field_3_sheet_names = sheetNames;
        this._isAddInFunctions = false;
    }

    public static SupBookRecord createInternalReferences(short numberOfSheets) {
        return new SupBookRecord(false, numberOfSheets);
    }

    public static SupBookRecord createAddInFunctions() {
        return new SupBookRecord(true, (short) 1);
    }

    public static SupBookRecord createExternalReferences(String url, String[] sheetNames) {
        return new SupBookRecord(url, sheetNames);
    }

    public boolean isExternalReferences() {
        return this.field_3_sheet_names != null;
    }

    public boolean isInternalReferences() {
        return this.field_3_sheet_names == null && !this._isAddInFunctions;
    }

    public boolean isAddInFunctions() {
        return this.field_3_sheet_names == null && this._isAddInFunctions;
    }

    public SupBookRecord(RecordInputStream in) {
        int recLen = in.remaining();
        this.field_1_number_of_sheets = in.readShort();
        if (recLen > 4) {
            this._isAddInFunctions = false;
            this.field_2_encoded_url = in.readString();
            String[] sheetNames = new String[this.field_1_number_of_sheets];
            for (int i = 0; i < sheetNames.length; i++) {
                sheetNames[i] = in.readString();
            }
            this.field_3_sheet_names = sheetNames;
            return;
        }
        this.field_2_encoded_url = null;
        this.field_3_sheet_names = null;
        short nextShort = in.readShort();
        if (nextShort == 1025) {
            this._isAddInFunctions = false;
        } else {
            if (nextShort == 14849) {
                this._isAddInFunctions = true;
                if (this.field_1_number_of_sheets != 1) {
                    throw new IllegalArgumentException("Expected 0x0001 for number of sheets field in 'Add-In Functions' but got (" + ((int) this.field_1_number_of_sheets) + ")");
                }
                return;
            }
            throw new IllegalArgumentException("invalid EXTERNALBOOK code (" + Integer.toHexString(nextShort) + ")");
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        if (!isExternalReferences()) {
            return 4;
        }
        int sum = 2 + StringUtil.getEncodedSize(this.field_2_encoded_url);
        for (String field_3_sheet_name : this.field_3_sheet_names) {
            sum += StringUtil.getEncodedSize(field_3_sheet_name);
        }
        return sum;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_number_of_sheets);
        if (isExternalReferences()) {
            StringUtil.writeUnicodeString(out, this.field_2_encoded_url);
            for (String field_3_sheet_name : this.field_3_sheet_names) {
                StringUtil.writeUnicodeString(out, field_3_sheet_name);
            }
            return;
        }
        int field2val = this._isAddInFunctions ? 14849 : InputDeviceCompat.SOURCE_GAMEPAD;
        out.writeShort(field2val);
    }

    public void setNumberOfSheets(short number) {
        this.field_1_number_of_sheets = number;
    }

    public short getNumberOfSheets() {
        return this.field_1_number_of_sheets;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    public String getURL() {
        String encodedUrl = this.field_2_encoded_url;
        if (encodedUrl != null && encodedUrl.length() >= 2) {
            switch (encodedUrl.charAt(0)) {
                case 0:
                case 2:
                    return encodedUrl.substring(1);
                case 1:
                    return decodeFileName(encodedUrl);
            }
        }
        return encodedUrl;
    }

    private static String decodeFileName(String encodedUrl) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        while (i < encodedUrl.length()) {
            char c = encodedUrl.charAt(i);
            switch (c) {
                case 1:
                    i++;
                    char driveLetter = encodedUrl.charAt(i);
                    if (driveLetter == '@') {
                        sb.append("\\\\");
                        break;
                    } else {
                        sb.append(driveLetter).append(":");
                        break;
                    }
                case 2:
                case 3:
                    sb.append(PATH_SEPERATOR);
                    break;
                case 4:
                    sb.append("..").append(PATH_SEPERATOR);
                    break;
                case 5:
                    LOG.atWarn().log("Found unexpected key: ChLongVolume - IGNORING");
                    break;
                case 6:
                case 7:
                case '\b':
                    LOG.atWarn().log("EXCEL.EXE path unknown - using this directory instead: .");
                    sb.append('.').append(PATH_SEPERATOR);
                    break;
                default:
                    sb.append(c);
                    break;
            }
            i++;
        }
        return sb.toString();
    }

    public String[] getSheetNames() {
        if (this.field_3_sheet_names == null) {
            return null;
        }
        return (String[]) this.field_3_sheet_names.clone();
    }

    public void setURL(String pUrl) {
        this.field_2_encoded_url = this.field_2_encoded_url.charAt(0) + pUrl;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public SupBookRecord copy() {
        return new SupBookRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SUP_BOOK;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("externalReferences", new Supplier() { // from class: org.apache.poi.hssf.record.SupBookRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(SupBookRecord.this.isExternalReferences());
            }
        }, "internalReferences", new Supplier() { // from class: org.apache.poi.hssf.record.SupBookRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(SupBookRecord.this.isInternalReferences());
            }
        }, "url", new Supplier() { // from class: org.apache.poi.hssf.record.SupBookRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return SupBookRecord.this.getURL();
            }
        }, "numberOfSheets", new Supplier() { // from class: org.apache.poi.hssf.record.SupBookRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(SupBookRecord.this.getNumberOfSheets());
            }
        }, "sheetNames", new Supplier() { // from class: org.apache.poi.hssf.record.SupBookRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return SupBookRecord.this.getSheetNames();
            }
        }, "addInFunctions", new Supplier() { // from class: org.apache.poi.hssf.record.SupBookRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(SupBookRecord.this.isAddInFunctions());
            }
        });
    }
}
