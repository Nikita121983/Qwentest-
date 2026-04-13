package org.apache.poi.hpsf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hpsf.wellknown.PropertyIDMap;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.util.CodePageUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LocaleUtil;

/* loaded from: classes10.dex */
public class Property {
    public static final int DEFAULT_CODEPAGE = 1252;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) Property.class);
    private long id;
    private long type;
    private Object value;

    public Property() {
    }

    public Property(Property p) {
        this(p.id, p.type, p.value);
    }

    public Property(long id, long type, Object value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public Property(long id, byte[] src, long offset, int length, int codepage) throws UnsupportedEncodingException {
        this.id = id;
        if (id == 0) {
            throw new UnsupportedEncodingException("Dictionary not allowed here");
        }
        int o = (int) offset;
        this.type = LittleEndian.getUInt(src, o);
        try {
            this.value = VariantSupport.read(src, o + 4, length, (int) this.type, codepage);
        } catch (UnsupportedVariantTypeException ex) {
            VariantSupport.writeUnsupportedTypeMessage(ex);
            this.value = ex.getValue();
        }
    }

    public Property(long id, LittleEndianByteArrayInputStream leis, int length, int codepage) throws UnsupportedEncodingException {
        this.id = id;
        if (id == 0) {
            throw new UnsupportedEncodingException("Dictionary not allowed here");
        }
        this.type = leis.readUInt();
        try {
            this.value = VariantSupport.read(leis, length, (int) this.type, codepage);
        } catch (UnsupportedVariantTypeException ex) {
            VariantSupport.writeUnsupportedTypeMessage(ex);
            this.value = ex.getValue();
        }
    }

    public long getID() {
        return this.id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public long getType() {
        return this.type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    protected int getSize(int property) throws WritingNotSupportedException {
        int length = Variant.getVariantLength(this.type);
        if (length >= 0 || this.type == 0) {
            return length;
        }
        if (length == -2) {
            throw new WritingNotSupportedException(this.type, null);
        }
        if (this.type == 30 || this.type == 31) {
            UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().get();
            try {
                int length2 = write(bos, property) - 8;
                return length2 + ((4 - (length2 & 3)) & 3);
            } catch (IOException e) {
                throw new WritingNotSupportedException(this.type, this.value);
            }
        }
        throw new WritingNotSupportedException(this.type, this.value);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Property)) {
            return false;
        }
        Property p = (Property) o;
        Object pValue = p.getValue();
        long pId = p.getID();
        if (this.id != pId || (this.id != 0 && !typesAreEqual(this.type, p.getType()))) {
            return false;
        }
        if (this.value == null && pValue == null) {
            return true;
        }
        if (this.value == null || pValue == null) {
            return false;
        }
        Class<?> valueClass = this.value.getClass();
        Class<?> pValueClass = pValue.getClass();
        if (!valueClass.isAssignableFrom(pValueClass) && !pValueClass.isAssignableFrom(valueClass)) {
            return false;
        }
        if (this.value instanceof byte[]) {
            byte[] thisVal = (byte[]) this.value;
            byte[] otherVal = (byte[]) pValue;
            int len = unpaddedLength(thisVal);
            if (len != unpaddedLength(otherVal)) {
                return false;
            }
            for (int i = 0; i < len; i++) {
                if (thisVal[i] != otherVal[i]) {
                    return false;
                }
            }
            return true;
        }
        return this.value.equals(pValue);
    }

    private static int unpaddedLength(byte[] buf) {
        int end = buf.length - ((buf.length + 3) % 4);
        for (int i = buf.length; i > end; i--) {
            if (buf[i - 1] != 0) {
                return i;
            }
        }
        return end;
    }

    private boolean typesAreEqual(long t1, long t2) {
        return t1 == t2 || (t1 == 30 && t2 == 31) || (t2 == 30 && t1 == 31);
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.id), Long.valueOf(this.type), this.value);
    }

    public String toString() {
        return toString(1252, null);
    }

    public String toString(int codepage, PropertyIDMap idMap) {
        String idName;
        StringBuilder b = new StringBuilder();
        b.append("Property[");
        b.append("id: ");
        b.append(this.id);
        String idName2 = idMap == null ? null : idMap.get((Object) Long.valueOf(this.id));
        if (idName2 == null) {
            idName = PropertyIDMap.getFallbackProperties().get((Object) Long.valueOf(this.id));
        } else {
            idName = idName2;
        }
        if (idName != null) {
            b.append(" (");
            b.append(idName);
            b.append(")");
        }
        b.append(", type: ");
        b.append(getType());
        b.append(" (");
        b.append(getVariantName());
        b.append(") ");
        Object value = getValue();
        b.append(", value: ");
        if (value instanceof String) {
            b.append((String) value);
            b.append(StringUtils.LF);
            UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().get();
            try {
                write(bos, codepage);
            } catch (Exception e) {
                LOG.atWarn().withThrowable(e).log("can't serialize string");
            }
            if (bos.size() > 8) {
                String hex = HexDump.dump(bos.toByteArray(), -8L, 8);
                b.append(hex);
            }
        } else if (value instanceof byte[]) {
            b.append(StringUtils.LF);
            byte[] bytes = (byte[]) value;
            if (bytes.length > 0) {
                String hex2 = HexDump.dump(bytes, 0L, 0);
                b.append(hex2);
            }
        } else if (value instanceof java.util.Date) {
            java.util.Date d = (java.util.Date) value;
            long filetime = Filetime.dateToFileTime(d);
            if (Filetime.isUndefined(d)) {
                b.append("<undefined>");
            } else if ((filetime >>> 32) == 0) {
                long l = 100 * filetime;
                TimeUnit tu = TimeUnit.NANOSECONDS;
                long hr = tu.toHours(l);
                long l2 = l - TimeUnit.HOURS.toNanos(hr);
                long min = tu.toMinutes(l2);
                long l3 = l2 - TimeUnit.MINUTES.toNanos(min);
                long sec = tu.toSeconds(l3);
                long ms = tu.toMillis(l3 - TimeUnit.SECONDS.toNanos(sec));
                String str = String.format(Locale.ROOT, "%02d:%02d:%02d.%03d", Long.valueOf(hr), Long.valueOf(min), Long.valueOf(sec), Long.valueOf(ms));
                b.append(str);
            } else {
                DateFormat df = new SimpleDateFormat(SignatureConfig.SIGNATURE_TIME_FORMAT, Locale.ROOT);
                df.setTimeZone(LocaleUtil.TIMEZONE_UTC);
                b.append(df.format(d));
            }
        } else if (this.type == 0 || this.type == 1 || value == null) {
            b.append("null");
        } else {
            b.append(value);
            String decoded = decodeValueFromID();
            if (decoded != null) {
                b.append(" (");
                b.append(decoded);
                b.append(")");
            }
        }
        b.append(']');
        return b.toString();
    }

    private String getVariantName() {
        if (getID() == 0) {
            return "dictionary";
        }
        return Variant.getVariantName(getType());
    }

    private String decodeValueFromID() {
        try {
            switch ((int) getID()) {
                case Integer.MIN_VALUE:
                    return LocaleUtil.getLocaleFromLCID(((Number) this.value).intValue());
                case 1:
                    return CodePageUtil.codepageToEncoding(((Number) this.value).intValue());
                default:
                    return null;
            }
        } catch (Exception e) {
            LOG.atWarn().log("Can't decode id {}", Unbox.box(getID()));
            return null;
        }
    }

    public int write(OutputStream out, int codepage) throws IOException, WritingNotSupportedException {
        long variantType = getType();
        if (variantType == 30 && codepage != 1200) {
            String csStr = CodePageUtil.codepageToEncoding(codepage > 0 ? codepage : 1252);
            if (!Charset.forName(csStr).newEncoder().canEncode((String) this.value)) {
                variantType = 31;
            }
        }
        LittleEndian.putUInt(variantType, out);
        int length = 0 + 4;
        return length + VariantSupport.write(out, variantType, getValue(), codepage);
    }
}
